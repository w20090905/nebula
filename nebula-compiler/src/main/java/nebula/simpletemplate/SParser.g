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

import nebula.lang.Operator;

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
        pushLocal("group",Type.getType(StringBuilder.class));
        pushLocal("template",Type.getType(StringBuilder.class));
        pushLocal("sb",Type.getType(StringBuilder.class));
//				pushLocal("object",Type.getType(Object.class));
        pushLocal("argv",Type.getType(Object.class));
      }
			
      private List<Var> arges = new ArrayList<Var>();
      private List<TemplateImpl> subTemplates = new ArrayList<TemplateImpl>();
			
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

templateGroupFile returns [STGroup group] :
  templateGroupDef EOF /* -> template?*/ 
  {group = this.group;}
;

templateGroupDef
@init {
    locals = new HashMap<String, Var>();
    arges= new ArrayList<Var>();    
    subTemplates = new ArrayList<TemplateImpl>();
 }
: (  /*'@' enclosing=ID '.' name=ID '(' ')' | */ 
    name=ID '('')'
    | name=ID '(' id=ID {arg($id.text);} ( ',' id=ID {arg($id.text);})*  ')'
    )
      (':'':''=')  LDELIM tmpl=template RDELIM { c.tpReferTemplate(group,$name.text,tmpl); }INDENT?
      ;
  
templateAndEOF returns[TemplateImpl temp] 
     : t=template{temp=t;} EOF /* -> template?*/ ;

template returns[TemplateImpl temp] 
@init{
      initLocals();
      List<Statement> statments = new ArrayList<Statement>();
    }
  : (e=element {if(e!=null)statments.add(e);} )* {Statement s=c.stBlock(statments);temp=c.tpTemplate(group,s,arges,subTemplates);};

block returns[Statement s] 
@init{
      List<Statement> statments = new ArrayList<Statement>();
    }
  : (e=element {if(e!=null)statments.add(e);} )* {s=c.stBlock(statments);};


element returns[Statement s]
	:	{input.LT(1).getCharPositionInLine()==0}? INDENT? COMMENT NEWLINE // -> // throw away
	|	INDENT se=singleElement{s=se;} // -> ^(INDENTED_EXPR INDENT singleElement?) // singleElement is optional to handle error returning nil
	|	se=singleElement{s=se;}
	|	ce=compoundElement{s=ce;}
	;

singleElement returns[Statement s]
	:	e=exprTag  {s=c.stOutput(c.opLocal(v("sb")),e);}
	|	TEXT           {s=c.stOutput(c.opLocal(v("sb")),c.opStringCst($TEXT.text));}
	|	NEWLINE    {s=c.stOutput(c.opLocal(v("sb")),c.opStringCst($NEWLINE.text));}
	|	COMMENT // throw away
	;

compoundElement returns[Statement s]
	:	i=ifstat { s = i.statement;} 
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

subtemplate returns[int index] 
@init {
    Map<String, Var> outterLocals = locals;
    List<Var> outterArges =arges;
    List<TemplateImpl> outterSubTemplates = subTemplates;
    
    locals = new HashMap<String, Var>();
    arges= new ArrayList<Var>();    
    subTemplates = new ArrayList<TemplateImpl>();
 }
	:	lc='{' (id=ID {arg($id.text);} ( ',' id=ID {arg($id.text);})* '|' )? t=template INDENT?
	{	
    locals = outterLocals;
    arges = outterArges;
    subTemplates = outterSubTemplates;
    
	  subTemplates.add(t);	  
	  index = subTemplates.size()-1;
	}
	 '}'
		// ignore final INDENT before } as it's not part of outer indent
		// -> ^(SUBTEMPLATE[$lc,"SUBTEMPLATE"] ^(ARGS $ids)* template?)
	;

ifstat returns[Statement statement] 
@init {
      Token indent=null;
      List<Expr<?>> conditions = new ArrayList<Expr<?>>();
      List<Statement> statements = new ArrayList<Statement>();
    }
@after{
    retval.statement = c.stIf(conditions,statements, blockElse);
}
	:	i=INDENT? LDELIM 'if' '(' c1=conditional')' RDELIM {if (input.LA(1)!=NEWLINE) indent=$i;}
			t1=block {conditions.add(c1);  statements.add(t1); }
			( INDENT? LDELIM 'elseif' '(' c2=conditional ')' RDELIM t2=block {conditions.add(c2);  statements.add(t2); })*
			( INDENT? LDELIM 'else' RDELIM blockElse=block)?
			INDENT? endif= LDELIM 'endif'
		RDELIM
		// kill \n for <endif> on line by itself if multi-line IF
		({$ifstat.start.getLine()!=input.LT(1).getLine()}?=> NEWLINE)?
		/*-> {indent!=null}?
		   ^(INDENTED_EXPR $i ^('if' $c1 $t1? ^('elseif' $c2 $t2)* ^('else' $t3?)?))
		->                    ^('if' $c1 $t1? ^('elseif' $c2 $t2)* ^('else' $t3?)?)*/

	;

conditional returns [Expr v]
scope {
	boolean inside;
}
	: a=andConditional {v=a;}( '||' a=andConditional {v=c.opConditional(Operator.OR,v,a); })*
	;

andConditional returns [Expr v] : n=notConditional{v=n;} ( '&&' n=notConditional {v=c.opConditional(Operator.AND,v,n); })* ;

notConditional returns [Expr v] 
	:	'!' n=notConditional {v=c.opNot(n);}
	|	m=memberExpr{v=m;}
	;

//notConditionalExpr
//	:	(ID/* ->ID*/)
//		(	p='.' prop=ID						// -> ^(PROP[$p,"PROP"] $notConditionalExpr $prop)
//		|	p='.' '(' mapExpr ')'				// -> ^(PROP_IND[$p,"PROP_IND"] $notConditionalExpr mapExpr)
//		)*
//	;

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
	:	m=memberExpr {v=m;}
		( ':' mt=mapTemplateRef[v]		{v=mt;}			// -> ^(MAP memberExpr mapTemplateRef)
		|										// -> memberExpr
		)
	;

expr returns[Expr v]  : e=mapExpr {v=e;};

// more complicated than necessary to avoid backtracking, which ruins
// error handling
mapExpr returns[Expr v]  
@init{
     List<Expr> params = new ArrayList<Expr>();
}
	:	me=memberExpr {v=me;}
		( (c=',' {params.add(me);} me=memberExpr)+ {params.add(me);}  col=':' mt=mapTemplateRefListParams[params] {v= mt;}
												// -> ^(ZIP[$col] ^(ELEMENTS memberExpr+) mapTemplateRef)
		|										// -> memberExpr
		)
		(	// don't keep queueing x; new list for each iteration
			col=':' mt=mapTemplateRef[v] {v=mt;} ({$c==null}? ',' mt=mapTemplateRef[v]  {v=mt;} )*
												// -> ^(MAP[$col] $mapExpr $x+)
		)*
	;

/**
expr:template(args)  apply template to expr
expr:{arg | ...}     apply subtemplate to expr
expr:(e)(args)       convert e to a string template name and apply to expr
*/
mapTemplateRef [ Expr data] returns[Expr v]
	:	ID '(' as=args ')'				{v=c.opInclude(c.opLocal(v("group")),c.opName($ID.text),data,as);}			// -> ^(INCLUDE ID args?)
	|	st=subtemplate {v=c.opIncludeSub(v("argv"),v("sb"),c.opLocal(v("template")),st,data);}    
	|	lp='(' me=mapExpr rp=')' '(' as=argExprList? ')' {v=c.opInclude(c.opLocal(v("group")),me,data,as);}// -> ^(INCLUDE_IND mapExpr argExprList?)
	;
	
mapTemplateRefListParams [ List<Expr> dataList] returns[Expr v]
  : ID '(' as=args ')'        {v=c.opInclude(c.opLocal(v("group")),c.opName($ID.text),dataList,as);}     // -> ^(INCLUDE ID args?)
  | st=subtemplate  {v=c.opIncludeSub(v("argv"),v("sb"),c.opLocal(v("template")),st,dataList);}    
  | lp='(' me=mapExpr rp=')' '(' as=argExprList? ')' {v=c.opInclude(c.opLocal(v("group")),me,dataList,as);}// -> ^(INCLUDE_IND mapExpr argExprList?)
  ;

memberExpr returns[Expr v]  
	:	(ie=includeExpr {v=ie;}/*/ ->includeExpr*/)
		(	p='.' ID	{v=c.opFieldOf(v,$ID.text);}						// -> ^(PROP[$p,"PROP"] $memberExpr ID)
//		|	p='.' '(' mapExpr ')'				// -> ^(PROP_IND[$p,"PROP_IND"] $memberExpr mapExpr)
		)*
	;

includeExpr returns[Expr v]  
options {k=2;} // prevent full LL(*), which fails, falling back on k=1; need k=2
	:	{Compiler.funcs.containsKey(input.LT(1).getText())}? // predefined function
		ID '(' expr? ')'						// -> ^(EXEC_FUNC ID expr?)
//	|	'super' '.' ID '(' args ')'				// -> ^(INCLUDE_SUPER ID args?)
	|	ID '(' as=args ')'		{v=c.opInclude(c.opLocal(v("group")),c.opName($ID.text),as);}					// -> ^(INCLUDE ID args?)
//	|	'@' 'super' '.' ID '(' rp=')'			// -> ^(INCLUDE_SUPER_REGION ID)
//	|	'@' ID '(' rp=')'						// -> ^(INCLUDE_REGION ID)
	|	p=primary{v=p;}
	;

primary returns[Expr v]  
	:	ID           {v=c.opArg(v("argv"),arg($ID.text));}
	|	STRING   {v=c.opStringCst(Misc.strip($STRING.text,1));}
	|	TRUE       {v=c.opYesnoCst(true);}  
	|	FALSE      {v=c.opYesnoCst(false);}
	|	st=subtemplate {v= c.opIncludeSub(v("argv"),v("sb"),c.opLocal(v("template")),st);}
	|	list
	|	{$conditional.size()>0}?=>  '(' cd=conditional {v=cd;}')'
	|	{$conditional.size()==0}?=> lp='(' name=expr ')'		(	'(' as=argExprList? ')'		{v=c.opInclude(c.opLocal(v("group")),name,as);}          // -> ^(INCLUDE_IND[$lp] expr argExprList?)
		|										// -> ^(TO_STR[$lp] expr)
		)
	;
	
args returns[List<Argument> args]
@init{
  args = new ArrayList();
}
  :	as=argExprList {args=as;}
	|	a=namedArg{c.opAddArgument(args,a);} ( ',' a=namedArg{c.opAddArgument(args,a);} )* (',' '...')? // -> namedArg+ '...'?
    |   '...'{c.opAddArgument(v("argv"),args,arges);}
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