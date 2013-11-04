parser grammar SParser;

options {
	language=Java;
	tokenVocab=STLexer;
	TokenLabelType=CommonToken;
}

tokens {
	EXPR; OPTIONS; PROP; PROP_IND; INCLUDE; INCLUDE_IND; EXEC_FUNC; INCLUDE_SUPER;
	INCLUDE_SUPER_REGION; INCLUDE_REGION; TO_STR; LIST; MAP; ZIP; SUBTEMPLATE; ARGS;
	ELEMENTS; REGION; NULL; INDENTED_EXPR;
	}

@header {
package nebula.simpletemplate;

import org.objectweb.asm.Type;

import org.antlr.runtime.*;
import org.stringtemplate.v4.misc.ErrorManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

}

@members {
	ErrorManager errMgr;
	Token templateToken;

	@Override
	protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
		throws RecognitionException
	{
		throw new MismatchedTokenException(ttype, input);
	}
	
	STGroup group;
	
    public SParser(TokenStream input,STGroup group) {
        this(input, new RecognizerSharedState(),group);
    }
    
	public SParser(TokenStream input, RecognizerSharedState state,STGroup group) {
        super(input, state);
        this.group = group;
  }
    
	 Compiler c = new Compiler();
	
	private Map<String, Var> locals = new HashMap<String, Var>();
	protected int maxLocals = 0;

			protected void initLocals() {
				locals.clear();
				pushLocal("this",Type.getType(Action.class));
				pushLocal("out",Type.getType(StringBuilder.class));
//				pushLocal("object",Type.getType(Object.class));
				argv = pushLocal("argv",Type.getType(Object.class));
			}
			
			private List<Var> arges =null;
			Var argv;
			
			protected Var pushLocal(String name, Type type) {
				Var var = new Var(name,type,locals.size());
				locals.put(var.name, var);
				return var;
			}
			
			protected Var arg(String name) {
			  for(Var var: arges){
			    if(var.name.equals(name)){
			      return var;
			    }
			  }
			  
        Var var = new Var(name,Type.getType(Object.class),arges.size());
        arges.add(var);
        return var;
      }
			
			protected Var v(String name) {
				Var var = locals.get(name);
				return var;
			};
}

@rulecatch {
   catch (RecognitionException re) { throw re; }
}

templateAndEOF returns[TemplateImpl temp]  : t=template{temp=t;} EOF /* -> template?*/ ;

template returns[TemplateImpl temp] 
@init{
      initLocals();
      List<Statement> statments = new ArrayList<Statement>();
      arges = new ArrayList<Var>();
    }
  : (e=element {if(e!=null)statments.add(e);} )* {Statement s=c.stBlock(statments);temp=c.tpTemplate(group,s,arges);};

element returns[Statement s]
	:	{input.LT(1).getCharPositionInLine()==0}? INDENT? COMMENT NEWLINE // -> // throw away
	|	INDENT se=singleElement{s=se;} // -> ^(INDENTED_EXPR INDENT singleElement?) // singleElement is optional to handle error returning nil
	|	se=singleElement{s=se;}
	|	compoundElement
	;

singleElement returns[Statement s]
	:	e=exprTag  {s=c.stOutput(e);}
	|	TEXT           {s=c.stOutput(c.opStringCst($TEXT.text));}
	|	NEWLINE    {s=c.stOutput(c.opStringCst($NEWLINE.text));}
	|	COMMENT // throw away
	;

compoundElement
	:	ifstat
	|	region
	;

exprTag returns[Expr v]
	:	LDELIM e=expr ( ';' exprOptions )? RDELIM {v=e;}
		// -> ^(EXPR[$LDELIM,"EXPR"] expr exprOptions?)
	;

region
@init {Token indent=null;}
	:	i=INDENT? x=LDELIM '@' ID RDELIM {if (input.LA(1)!=NEWLINE) indent=$i;}
		template
		INDENT? LDELIM '@end' RDELIM
		// kill \n for <@end> on line by itself if multi-line embedded region
		({$region.start.getLine()!=input.LT(1).getLine()}?=> NEWLINE)?
		/*-> {indent!=null}?
		   ^(INDENTED_EXPR $i ^(REGION[$x] ID template?))
		->                    ^(REGION[$x] ID template?)*/
	;

subtemplate
	:	lc='{' (ids+= ID ( ',' ids+= ID )* '|' )? template INDENT? '}'
		// ignore final INDENT before } as it's not part of outer indent
		// -> ^(SUBTEMPLATE[$lc,"SUBTEMPLATE"] ^(ARGS $ids)* template?)
	;

ifstat
@init {Token indent=null;}
	:	i=INDENT? LDELIM 'if' '(' c1=conditional ')' RDELIM {if (input.LA(1)!=NEWLINE) indent=$i;}
			t1=template
			( INDENT? LDELIM 'elseif' '(' conditional ')' RDELIM template )*
			( INDENT? LDELIM 'else' RDELIM t3=template )?
			INDENT? endif= LDELIM 'endif'
		RDELIM
		// kill \n for <endif> on line by itself if multi-line IF
		({$ifstat.start.getLine()!=input.LT(1).getLine()}?=> NEWLINE)?
		/*-> {indent!=null}?
		   ^(INDENTED_EXPR $i ^('if' $c1 $t1? ^('elseif' $c2 $t2)* ^('else' $t3?)?))
		->                    ^('if' $c1 $t1? ^('elseif' $c2 $t2)* ^('else' $t3?)?)*/
	;

conditional
scope {
	boolean inside;
}
	: andConditional ( '||' andConditional )*
	;

andConditional : notConditional ( '&&' notConditional )* ;

notConditional
	:	'!' notConditional
	|	memberExpr
	;

notConditionalExpr
	:	(ID/* ->ID*/)
		(	p='.' prop=ID						// -> ^(PROP[$p,"PROP"] $notConditionalExpr $prop)
		|	p='.' '(' mapExpr ')'				// -> ^(PROP_IND[$p,"PROP_IND"] $notConditionalExpr mapExpr)
		)*
	;

exprOptions : option ( ',' option )* /*/ -> ^(OPTIONS option*) */;

option
@init {
	//String id = input.LT(1).getText();
	//String defVal = Compiler.defaultOptionValues.get(id);
	//boolean validOption = Compiler.supportedOptions.get(id)!=null;
}
	:	ID
		{
		//if ( !validOption ) {
            //errMgr.compileTimeError(ErrorType.NO_SUCH_OPTION, templateToken, $ID, $ID.text);
		//}
		}
		(	'=' exprNoComma 					// -> {validOption}? ^('=' ID exprNoComma)
												// ->
		|	{
			//if ( defVal==null ) {
				//errMgr.compileTimeError(ErrorType.NO_DEFAULT_VALUE, templateToken, $ID);
			//}
			}
												/* -> {validOption&&defVal!=null}?
												   ^(EQUALS["="] ID STRING[$ID,'"'+defVal+'"'])
												// ->*/
		)
	;

exprNoComma returns[Expr v]
	:	memberExpr
		( ':' mapTemplateRef					// -> ^(MAP memberExpr mapTemplateRef)
		|										// -> memberExpr
		)
	;

expr returns[Expr v]  : e=mapExpr {v=e;};

// more complicated than necessary to avoid backtracking, which ruins
// error handling
mapExpr returns[Expr v]  
	:	me=memberExpr {v=me;}
		( (c=',' memberExpr)+ col=':' mapTemplateRef
												// -> ^(ZIP[$col] ^(ELEMENTS memberExpr+) mapTemplateRef)
		|										// -> memberExpr
		)
		(	// don't keep queueing x; new list for each iteration
			col=':' mapTemplateRef ({$c==null}?=> ',' mapTemplateRef )*
												// -> ^(MAP[$col] $mapExpr $x+)
		)*
	;

/**
expr:template(args)  apply template to expr
expr:{arg | ...}     apply subtemplate to expr
expr:(e)(args)       convert e to a string template name and apply to expr
*/
mapTemplateRef
	:	ID '(' args ')'							// -> ^(INCLUDE ID args?)
	|	subtemplate
	|	lp='(' mapExpr rp=')' '(' argExprList? ')' // -> ^(INCLUDE_IND mapExpr argExprList?)
	;

memberExpr returns[Expr v]  
	:	(ie=includeExpr {v=ie;}/*/ ->includeExpr*/)
		(	p='.' ID	{v=c.opFieldOf(v,$ID.text);}						// -> ^(PROP[$p,"PROP"] $memberExpr ID)
		|	p='.' '(' mapExpr ')'				// -> ^(PROP_IND[$p,"PROP_IND"] $memberExpr mapExpr)
		)*
	;

includeExpr returns[Expr v]  
options {k=2;} // prevent full LL(*), which fails, falling back on k=1; need k=2
	:	{Compiler.funcs.containsKey(input.LT(1).getText())}? // predefined function
		ID '(' expr? ')'						// -> ^(EXEC_FUNC ID expr?)
	|	'super' '.' ID '(' args ')'				// -> ^(INCLUDE_SUPER ID args?)
	|	ID '(' as=args ')'		{v=c.opInclude($ID.text,as);}					// -> ^(INCLUDE ID args?)
	|	'@' 'super' '.' ID '(' rp=')'			// -> ^(INCLUDE_SUPER_REGION ID)
	|	'@' ID '(' rp=')'						// -> ^(INCLUDE_REGION ID)
	|	p=primary{v=p;}
	;

primary returns[Expr v]  
	:	ID           {v=c.opArg(argv,arg($ID.text));}
	|	STRING   {v=c.opStringCst($STRING.text);}
	|	TRUE       {v=c.opYesnoCst(true);}  
	|	FALSE      {v=c.opYesnoCst(false);}
	|	subtemplate
	|	list
	|	{$conditional.size()>0}?=>  '(' conditional ')'
	|	{$conditional.size()==0}?=> lp='(' expr ')'
		(	'(' argExprList? ')'		        // -> ^(INCLUDE_IND[$lp] expr argExprList?)
		|										// -> ^(TO_STR[$lp] expr)
		)
	;

args returns[List<Argument> args]
  :	as=argExprList {args=as;}
	|	a=namedArg{c.opAddArgument(args,a);} ( ',' a=namedArg{c.opAddArgument(args,a);} )* (',' '...')? // -> namedArg+ '...'?
    |   '...'
	|
	;

argExprList  returns[List<Argument> args] 
@init{
  args = new ArrayList();
}
: a=arg{c.opAddArgument(args,a);} ( ',' a=arg{c.opAddArgument(args,a);} )* /* -> arg+ */;

arg returns[Argument v]   : e=exprNoComma {v=c.opArgument(e);} ;

namedArg returns[Argument v]   : ID '=' a=arg {v=c.opArgument($ID.text,a);}/* -> ^('=' ID arg) */;

list:	{input.LA(2)==RBRACK}? // hush warning; [] special case
		lb='[' ']' // -> LIST[$lb]
	|	lb='[' listElement ( ',' listElement )* ']' // -> ^(LIST[$lb] listElement*)
	;

listElement returns[Expr v] : e=exprNoComma{v=e;} | /* -> NULL */;
