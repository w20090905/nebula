// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g 2013-11-07 11:13:57

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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class SParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IF", "ELSE", "ELSEIF", "ENDIF", "SUPER", "SEMI", "BANG", "ELLIPSIS", "EQUALS", "COLON", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "COMMA", "DOT", "LCURLY", "RCURLY", "TEXT", "LDELIM", "RDELIM", "ID", "STRING", "WS", "PIPE", "OR", "AND", "INDENT", "NEWLINE", "AT", "END", "TRUE", "FALSE", "COMMENT", "ARGS", "ELEMENTS", "EXEC_FUNC", "EXPR", "INCLUDE", "INCLUDE_IND", "INCLUDE_REGION", "INCLUDE_SUPER", "INCLUDE_SUPER_REGION", "INDENTED_EXPR", "LIST", "MAP", "NULL", "OPTIONS", "PROP", "PROP_IND", "REGION", "SUBTEMPLATE", "TO_STR", "ZIP"
    };

    public static final int EOF=-1;
    public static final int RBRACK=17;
    public static final int LBRACK=16;
    public static final int ELSE=5;
    public static final int ELLIPSIS=11;
    public static final int LCURLY=20;
    public static final int BANG=10;
    public static final int EQUALS=12;
    public static final int TEXT=22;
    public static final int ID=25;
    public static final int SEMI=9;
    public static final int LPAREN=14;
    public static final int IF=4;
    public static final int ELSEIF=6;
    public static final int COLON=13;
    public static final int RPAREN=15;
    public static final int WS=27;
    public static final int COMMA=18;
    public static final int RCURLY=21;
    public static final int ENDIF=7;
    public static final int RDELIM=24;
    public static final int SUPER=8;
    public static final int DOT=19;
    public static final int LDELIM=23;
    public static final int STRING=26;
    public static final int PIPE=28;
    public static final int OR=29;
    public static final int AND=30;
    public static final int INDENT=31;
    public static final int NEWLINE=32;
    public static final int AT=33;
    public static final int END=34;
    public static final int TRUE=35;
    public static final int FALSE=36;
    public static final int COMMENT=37;
    public static final int ARGS=38;
    public static final int ELEMENTS=39;
    public static final int EXEC_FUNC=40;
    public static final int EXPR=41;
    public static final int INCLUDE=42;
    public static final int INCLUDE_IND=43;
    public static final int INCLUDE_REGION=44;
    public static final int INCLUDE_SUPER=45;
    public static final int INCLUDE_SUPER_REGION=46;
    public static final int INDENTED_EXPR=47;
    public static final int LIST=48;
    public static final int MAP=49;
    public static final int NULL=50;
    public static final int OPTIONS=51;
    public static final int PROP=52;
    public static final int PROP_IND=53;
    public static final int REGION=54;
    public static final int SUBTEMPLATE=55;
    public static final int TO_STR=56;
    public static final int ZIP=57;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public SParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public SParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return SParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g"; }


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



    // $ANTLR start "templateGroupFile"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:101:1: templateGroupFile returns [STGroup group] : templateGroupDef EOF ;
    public final STGroup templateGroupFile() throws RecognitionException {
        STGroup group = null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:101:43: ( templateGroupDef EOF )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:102:3: templateGroupDef EOF
            {
            pushFollow(FOLLOW_templateGroupDef_in_templateGroupFile128);
            templateGroupDef();

            state._fsp--;


            match(input,EOF,FOLLOW_EOF_in_templateGroupFile130); 

            group = this.group;

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return group;
    }
    // $ANTLR end "templateGroupFile"



    // $ANTLR start "templateGroupDef"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:106:1: templateGroupDef : (name= ID '(' ')' |name= ID '(' id= ID ( ',' id= ID )* ')' ) ( ':' ':' '=' ) LDELIM tmpl= template RDELIM ( INDENT )? ;
    public final void templateGroupDef() throws RecognitionException {
        CommonToken name=null;
        CommonToken id=null;
        TemplateImpl tmpl =null;



            locals = new HashMap<String, Var>();
            arges= new ArrayList<Var>();    
            subTemplates = new ArrayList<TemplateImpl>();
         
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:112:3: ( (name= ID '(' ')' |name= ID '(' id= ID ( ',' id= ID )* ')' ) ( ':' ':' '=' ) LDELIM tmpl= template RDELIM ( INDENT )? )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:112:3: (name= ID '(' ')' |name= ID '(' id= ID ( ',' id= ID )* ')' ) ( ':' ':' '=' ) LDELIM tmpl= template RDELIM ( INDENT )?
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:112:3: (name= ID '(' ')' |name= ID '(' id= ID ( ',' id= ID )* ')' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==ID) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==LPAREN) ) {
                    int LA2_2 = input.LA(3);

                    if ( (LA2_2==RPAREN) ) {
                        alt2=1;
                    }
                    else if ( (LA2_2==ID) ) {
                        alt2=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:113:5: name= ID '(' ')'
                    {
                    name=(CommonToken)match(input,ID,FOLLOW_ID_in_templateGroupDef163); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_templateGroupDef165); 

                    match(input,RPAREN,FOLLOW_RPAREN_in_templateGroupDef166); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:114:7: name= ID '(' id= ID ( ',' id= ID )* ')'
                    {
                    name=(CommonToken)match(input,ID,FOLLOW_ID_in_templateGroupDef176); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_templateGroupDef178); 

                    id=(CommonToken)match(input,ID,FOLLOW_ID_in_templateGroupDef182); 

                    arg((id!=null?id.getText():null));

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:114:42: ( ',' id= ID )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==COMMA) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:114:44: ',' id= ID
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_templateGroupDef188); 

                    	    id=(CommonToken)match(input,ID,FOLLOW_ID_in_templateGroupDef192); 

                    	    arg((id!=null?id.getText():null));

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_templateGroupDef199); 

                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:116:7: ( ':' ':' '=' )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:116:8: ':' ':' '='
            {
            match(input,COLON,FOLLOW_COLON_in_templateGroupDef214); 

            match(input,COLON,FOLLOW_COLON_in_templateGroupDef215); 

            match(input,EQUALS,FOLLOW_EQUALS_in_templateGroupDef216); 

            }


            match(input,LDELIM,FOLLOW_LDELIM_in_templateGroupDef220); 

            pushFollow(FOLLOW_template_in_templateGroupDef224);
            tmpl=template();

            state._fsp--;


            match(input,RDELIM,FOLLOW_RDELIM_in_templateGroupDef226); 

             c.tpReferTemplate(group,(name!=null?name.getText():null),tmpl); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:116:93: ( INDENT )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==INDENT) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:116:93: INDENT
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_templateGroupDef229); 

                    }
                    break;

            }


            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "templateGroupDef"



    // $ANTLR start "templateAndEOF"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:119:1: templateAndEOF returns [TemplateImpl temp] : t= template EOF ;
    public final TemplateImpl templateAndEOF() throws RecognitionException {
        TemplateImpl temp = null;


        TemplateImpl t =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:120:6: (t= template EOF )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:120:8: t= template EOF
            {
            pushFollow(FOLLOW_template_in_templateAndEOF258);
            t=template();

            state._fsp--;


            temp=t;

            match(input,EOF,FOLLOW_EOF_in_templateAndEOF261); 

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return temp;
    }
    // $ANTLR end "templateAndEOF"



    // $ANTLR start "template"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:122:1: template returns [TemplateImpl temp] : (e= element )* ;
    public final TemplateImpl template() throws RecognitionException {
        TemplateImpl temp = null;


        Statement e =null;



              initLocals();
              List<Statement> statments = new ArrayList<Statement>();
            
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:127:3: ( (e= element )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:127:5: (e= element )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:127:5: (e= element )*
            loop4:
            do {
                int alt4=2;
                switch ( input.LA(1) ) {
                case INDENT:
                    {
                    int LA4_2 = input.LA(2);

                    if ( (LA4_2==LDELIM) ) {
                        int LA4_5 = input.LA(3);

                        if ( (LA4_5==IF||LA4_5==LPAREN||LA4_5==LBRACK||LA4_5==LCURLY||(LA4_5 >= ID && LA4_5 <= STRING)||LA4_5==AT||(LA4_5 >= TRUE && LA4_5 <= FALSE)) ) {
                            alt4=1;
                        }


                    }
                    else if ( (LA4_2==TEXT||LA4_2==NEWLINE||LA4_2==COMMENT) ) {
                        alt4=1;
                    }


                    }
                    break;
                case LDELIM:
                    {
                    int LA4_3 = input.LA(2);

                    if ( (LA4_3==IF||LA4_3==LPAREN||LA4_3==LBRACK||LA4_3==LCURLY||(LA4_3 >= ID && LA4_3 <= STRING)||LA4_3==AT||(LA4_3 >= TRUE && LA4_3 <= FALSE)) ) {
                        alt4=1;
                    }


                    }
                    break;
                case TEXT:
                case NEWLINE:
                case COMMENT:
                    {
                    alt4=1;
                    }
                    break;

                }

                switch (alt4) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:127:6: e= element
            	    {
            	    pushFollow(FOLLOW_element_in_template285);
            	    e=element();

            	    state._fsp--;


            	    if(e!=null)statments.add(e);

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            Statement s=c.stBlock(statments);temp=c.tpTemplate(group,s,arges,subTemplates);

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return temp;
    }
    // $ANTLR end "template"



    // $ANTLR start "block"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:129:1: block returns [Statement s] : (e= element )* ;
    public final Statement block() throws RecognitionException {
        Statement s = null;


        Statement e =null;



              List<Statement> statments = new ArrayList<Statement>();
            
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:133:3: ( (e= element )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:133:5: (e= element )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:133:5: (e= element )*
            loop5:
            do {
                int alt5=2;
                switch ( input.LA(1) ) {
                case INDENT:
                    {
                    int LA5_1 = input.LA(2);

                    if ( (LA5_1==LDELIM) ) {
                        int LA5_4 = input.LA(3);

                        if ( (LA5_4==IF||LA5_4==LPAREN||LA5_4==LBRACK||LA5_4==LCURLY||(LA5_4 >= ID && LA5_4 <= STRING)||LA5_4==AT||(LA5_4 >= TRUE && LA5_4 <= FALSE)) ) {
                            alt5=1;
                        }


                    }
                    else if ( (LA5_1==TEXT||LA5_1==NEWLINE||LA5_1==COMMENT) ) {
                        alt5=1;
                    }


                    }
                    break;
                case LDELIM:
                    {
                    int LA5_2 = input.LA(2);

                    if ( (LA5_2==IF||LA5_2==LPAREN||LA5_2==LBRACK||LA5_2==LCURLY||(LA5_2 >= ID && LA5_2 <= STRING)||LA5_2==AT||(LA5_2 >= TRUE && LA5_2 <= FALSE)) ) {
                        alt5=1;
                    }


                    }
                    break;
                case TEXT:
                case NEWLINE:
                case COMMENT:
                    {
                    alt5=1;
                    }
                    break;

                }

                switch (alt5) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:133:6: e= element
            	    {
            	    pushFollow(FOLLOW_element_in_block313);
            	    e=element();

            	    state._fsp--;


            	    if(e!=null)statments.add(e);

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            s=c.stBlock(statments);

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return s;
    }
    // $ANTLR end "block"



    // $ANTLR start "element"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:136:1: element returns [Statement s] : ({...}? ( INDENT )? COMMENT NEWLINE | INDENT se= singleElement |se= singleElement |ce= compoundElement );
    public final Statement element() throws RecognitionException {
        Statement s = null;


        Statement se =null;

        Statement ce =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:137:2: ({...}? ( INDENT )? COMMENT NEWLINE | INDENT se= singleElement |se= singleElement |ce= compoundElement )
            int alt7=4;
            switch ( input.LA(1) ) {
            case INDENT:
                {
                switch ( input.LA(2) ) {
                case COMMENT:
                    {
                    int LA7_5 = input.LA(3);

                    if ( (LA7_5==NEWLINE) ) {
                        int LA7_10 = input.LA(4);

                        if ( ((input.LT(1).getCharPositionInLine()==0)) ) {
                            alt7=1;
                        }
                        else if ( (true) ) {
                            alt7=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 7, 10, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA7_5==EOF||(LA7_5 >= RCURLY && LA7_5 <= RDELIM)||LA7_5==INDENT||LA7_5==COMMENT) ) {
                        alt7=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 5, input);

                        throw nvae;

                    }
                    }
                    break;
                case LDELIM:
                    {
                    int LA7_6 = input.LA(3);

                    if ( (LA7_6==IF||LA7_6==AT) ) {
                        alt7=4;
                    }
                    else if ( (LA7_6==LPAREN||LA7_6==LBRACK||LA7_6==LCURLY||(LA7_6 >= ID && LA7_6 <= STRING)||(LA7_6 >= TRUE && LA7_6 <= FALSE)) ) {
                        alt7=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 6, input);

                        throw nvae;

                    }
                    }
                    break;
                case TEXT:
                case NEWLINE:
                    {
                    alt7=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;

                }

                }
                break;
            case COMMENT:
                {
                int LA7_2 = input.LA(2);

                if ( (LA7_2==NEWLINE) ) {
                    int LA7_8 = input.LA(3);

                    if ( ((input.LT(1).getCharPositionInLine()==0)) ) {
                        alt7=1;
                    }
                    else if ( (true) ) {
                        alt7=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 8, input);

                        throw nvae;

                    }
                }
                else if ( (LA7_2==EOF||(LA7_2 >= RCURLY && LA7_2 <= RDELIM)||LA7_2==INDENT||LA7_2==COMMENT) ) {
                    alt7=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 2, input);

                    throw nvae;

                }
                }
                break;
            case LDELIM:
                {
                int LA7_3 = input.LA(2);

                if ( (LA7_3==IF||LA7_3==AT) ) {
                    alt7=4;
                }
                else if ( (LA7_3==LPAREN||LA7_3==LBRACK||LA7_3==LCURLY||(LA7_3 >= ID && LA7_3 <= STRING)||(LA7_3 >= TRUE && LA7_3 <= FALSE)) ) {
                    alt7=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 3, input);

                    throw nvae;

                }
                }
                break;
            case TEXT:
            case NEWLINE:
                {
                alt7=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:137:4: {...}? ( INDENT )? COMMENT NEWLINE
                    {
                    if ( !((input.LT(1).getCharPositionInLine()==0)) ) {
                        throw new FailedPredicateException(input, "element", "input.LT(1).getCharPositionInLine()==0");
                    }

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:137:46: ( INDENT )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==INDENT) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:137:46: INDENT
                            {
                            match(input,INDENT,FOLLOW_INDENT_in_element335); 

                            }
                            break;

                    }


                    match(input,COMMENT,FOLLOW_COMMENT_in_element338); 

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_element340); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:138:4: INDENT se= singleElement
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_element346); 

                    pushFollow(FOLLOW_singleElement_in_element350);
                    se=singleElement();

                    state._fsp--;


                    s=se;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:139:4: se= singleElement
                    {
                    pushFollow(FOLLOW_singleElement_in_element359);
                    se=singleElement();

                    state._fsp--;


                    s=se;

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:140:4: ce= compoundElement
                    {
                    pushFollow(FOLLOW_compoundElement_in_element367);
                    ce=compoundElement();

                    state._fsp--;


                    s=ce;

                    }
                    break;

            }
        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return s;
    }
    // $ANTLR end "element"



    // $ANTLR start "singleElement"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:143:1: singleElement returns [Statement s] : (e= exprTag | TEXT | NEWLINE | COMMENT );
    public final Statement singleElement() throws RecognitionException {
        Statement s = null;


        CommonToken TEXT1=null;
        CommonToken NEWLINE2=null;
        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:144:2: (e= exprTag | TEXT | NEWLINE | COMMENT )
            int alt8=4;
            switch ( input.LA(1) ) {
            case LDELIM:
                {
                alt8=1;
                }
                break;
            case TEXT:
                {
                alt8=2;
                }
                break;
            case NEWLINE:
                {
                alt8=3;
                }
                break;
            case COMMENT:
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }

            switch (alt8) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:144:4: e= exprTag
                    {
                    pushFollow(FOLLOW_exprTag_in_singleElement384);
                    e=exprTag();

                    state._fsp--;


                    s=c.stOutput(c.opLocal(v("sb")),e);

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:145:4: TEXT
                    {
                    TEXT1=(CommonToken)match(input,TEXT,FOLLOW_TEXT_in_singleElement392); 

                    s=c.stOutput(c.opLocal(v("sb")),c.opStringCst((TEXT1!=null?TEXT1.getText():null)));

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:146:4: NEWLINE
                    {
                    NEWLINE2=(CommonToken)match(input,NEWLINE,FOLLOW_NEWLINE_in_singleElement409); 

                    s=c.stOutput(c.opLocal(v("sb")),c.opStringCst((NEWLINE2!=null?NEWLINE2.getText():null)));

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:147:4: COMMENT
                    {
                    match(input,COMMENT,FOLLOW_COMMENT_in_singleElement419); 

                    }
                    break;

            }
        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return s;
    }
    // $ANTLR end "singleElement"



    // $ANTLR start "compoundElement"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:150:1: compoundElement returns [Statement s] : (i= ifstat | region );
    public final Statement compoundElement() throws RecognitionException {
        Statement s = null;


        SParser.ifstat_return i =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:151:2: (i= ifstat | region )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==INDENT) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==LDELIM) ) {
                    int LA9_2 = input.LA(3);

                    if ( (LA9_2==IF) ) {
                        alt9=1;
                    }
                    else if ( (LA9_2==AT) ) {
                        alt9=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA9_0==LDELIM) ) {
                int LA9_2 = input.LA(2);

                if ( (LA9_2==IF) ) {
                    alt9=1;
                }
                else if ( (LA9_2==AT) ) {
                    alt9=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }
            switch (alt9) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:151:4: i= ifstat
                    {
                    pushFollow(FOLLOW_ifstat_in_compoundElement436);
                    i=ifstat();

                    state._fsp--;


                     s = i.statement;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:152:4: region
                    {
                    pushFollow(FOLLOW_region_in_compoundElement444);
                    region();

                    state._fsp--;


                    }
                    break;

            }
        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return s;
    }
    // $ANTLR end "compoundElement"



    // $ANTLR start "exprTag"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:155:1: exprTag returns [Expr v] : LDELIM e= expr ( ';' exprOptions )? RDELIM ;
    public final Expr exprTag() throws RecognitionException {
        Expr v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:156:2: ( LDELIM e= expr ( ';' exprOptions )? RDELIM )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:156:4: LDELIM e= expr ( ';' exprOptions )? RDELIM
            {
            match(input,LDELIM,FOLLOW_LDELIM_in_exprTag458); 

            pushFollow(FOLLOW_expr_in_exprTag462);
            e=expr();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:156:18: ( ';' exprOptions )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==SEMI) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:156:20: ';' exprOptions
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_exprTag466); 

                    pushFollow(FOLLOW_exprOptions_in_exprTag468);
                    exprOptions();

                    state._fsp--;


                    }
                    break;

            }


            match(input,RDELIM,FOLLOW_RDELIM_in_exprTag473); 

            v=e;

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "exprTag"


    public static class region_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "region"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:160:1: region : (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )? ;
    public final SParser.region_return region() throws RecognitionException {
        SParser.region_return retval = new SParser.region_return();
        retval.start = input.LT(1);


        CommonToken i=null;
        CommonToken x=null;

        Token indent=null;
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:162:2: ( (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )? )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:162:4: (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )?
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:162:5: (i= INDENT )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==INDENT) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:162:5: i= INDENT
                    {
                    i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_region496); 

                    }
                    break;

            }


            x=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_region501); 

            match(input,AT,FOLLOW_AT_in_region503); 

            match(input,ID,FOLLOW_ID_in_region505); 

            match(input,RDELIM,FOLLOW_RDELIM_in_region507); 

            if (input.LA(1)!=NEWLINE) indent=i;

            pushFollow(FOLLOW_template_in_region513);
            template();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:164:3: ( INDENT )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==INDENT) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:164:3: INDENT
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_region517); 

                    }
                    break;

            }


            match(input,LDELIM,FOLLOW_LDELIM_in_region520); 

            match(input,END,FOLLOW_END_in_region522); 

            match(input,RDELIM,FOLLOW_RDELIM_in_region524); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:166:3: ({...}? => NEWLINE )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==NEWLINE) ) {
                int LA13_1 = input.LA(2);

                if ( ((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                    alt13=1;
                }
            }
            switch (alt13) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:166:4: {...}? => NEWLINE
                    {
                    if ( !((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                        throw new FailedPredicateException(input, "region", "$region.start.getLine()!=input.LT(1).getLine()");
                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_region535); 

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "region"



    // $ANTLR start "subtemplate"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:172:1: subtemplate returns [int index] : lc= '{' (id= ID ( ',' id= ID )* '|' )? t= template ( INDENT )? '}' ;
    public final int subtemplate() throws RecognitionException {
        int index = 0;


        CommonToken lc=null;
        CommonToken id=null;
        TemplateImpl t =null;



            Map<String, Var> outterLocals = locals;
            List<Var> outterArges =arges;
            List<TemplateImpl> outterSubTemplates = subTemplates;
            
            locals = new HashMap<String, Var>();
            arges= new ArrayList<Var>();    
            subTemplates = new ArrayList<TemplateImpl>();
         
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:182:2: (lc= '{' (id= ID ( ',' id= ID )* '|' )? t= template ( INDENT )? '}' )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:182:4: lc= '{' (id= ID ( ',' id= ID )* '|' )? t= template ( INDENT )? '}'
            {
            lc=(CommonToken)match(input,LCURLY,FOLLOW_LCURLY_in_subtemplate563); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:182:11: (id= ID ( ',' id= ID )* '|' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==ID) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:182:12: id= ID ( ',' id= ID )* '|'
                    {
                    id=(CommonToken)match(input,ID,FOLLOW_ID_in_subtemplate568); 

                    arg((id!=null?id.getText():null));

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:182:35: ( ',' id= ID )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==COMMA) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:182:37: ',' id= ID
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_subtemplate574); 

                    	    id=(CommonToken)match(input,ID,FOLLOW_ID_in_subtemplate578); 

                    	    arg((id!=null?id.getText():null));

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    match(input,PIPE,FOLLOW_PIPE_in_subtemplate584); 

                    }
                    break;

            }


            pushFollow(FOLLOW_template_in_subtemplate591);
            t=template();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:182:84: ( INDENT )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==INDENT) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:182:84: INDENT
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_subtemplate593); 

                    }
                    break;

            }


            	
                locals = outterLocals;
                arges = outterArges;
                subTemplates = outterSubTemplates;
                
            	  subTemplates.add(t);	  
            	  index = subTemplates.size()-1;
            	

            match(input,RCURLY,FOLLOW_RCURLY_in_subtemplate601); 

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return index;
    }
    // $ANTLR end "subtemplate"


    public static class ifstat_return extends ParserRuleReturnScope {
        public Statement statement;
    };


    // $ANTLR start "ifstat"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:196:1: ifstat returns [Statement statement] : (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= block ( ( INDENT )? LDELIM 'elseif' '(' c2= conditional ')' RDELIM t2= block )* ( ( INDENT )? LDELIM 'else' RDELIM blockElse= block )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )? ;
    public final SParser.ifstat_return ifstat() throws RecognitionException {
        SParser.ifstat_return retval = new SParser.ifstat_return();
        retval.start = input.LT(1);


        CommonToken i=null;
        CommonToken endif=null;
        Expr c1 =null;

        Statement t1 =null;

        Expr c2 =null;

        Statement t2 =null;

        Statement blockElse =null;



              Token indent=null;
              List<Expr<?>> conditions = new ArrayList<Expr<?>>();
              List<Statement> statements = new ArrayList<Statement>();
            
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:205:2: ( (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= block ( ( INDENT )? LDELIM 'elseif' '(' c2= conditional ')' RDELIM t2= block )* ( ( INDENT )? LDELIM 'else' RDELIM blockElse= block )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )? )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:205:4: (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= block ( ( INDENT )? LDELIM 'elseif' '(' c2= conditional ')' RDELIM t2= block )* ( ( INDENT )? LDELIM 'else' RDELIM blockElse= block )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )?
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:205:5: (i= INDENT )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==INDENT) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:205:5: i= INDENT
                    {
                    i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_ifstat633); 

                    }
                    break;

            }


            match(input,LDELIM,FOLLOW_LDELIM_in_ifstat636); 

            match(input,IF,FOLLOW_IF_in_ifstat638); 

            match(input,LPAREN,FOLLOW_LPAREN_in_ifstat640); 

            pushFollow(FOLLOW_conditional_in_ifstat644);
            c1=conditional();

            state._fsp--;


            match(input,RPAREN,FOLLOW_RPAREN_in_ifstat645); 

            match(input,RDELIM,FOLLOW_RDELIM_in_ifstat647); 

            if (input.LA(1)!=NEWLINE) indent=i;

            pushFollow(FOLLOW_block_in_ifstat656);
            t1=block();

            state._fsp--;


            conditions.add(c1);  statements.add(t1); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:207:4: ( ( INDENT )? LDELIM 'elseif' '(' c2= conditional ')' RDELIM t2= block )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==INDENT) ) {
                    int LA19_1 = input.LA(2);

                    if ( (LA19_1==LDELIM) ) {
                        int LA19_2 = input.LA(3);

                        if ( (LA19_2==ELSEIF) ) {
                            alt19=1;
                        }


                    }


                }
                else if ( (LA19_0==LDELIM) ) {
                    int LA19_2 = input.LA(2);

                    if ( (LA19_2==ELSEIF) ) {
                        alt19=1;
                    }


                }


                switch (alt19) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:207:6: ( INDENT )? LDELIM 'elseif' '(' c2= conditional ')' RDELIM t2= block
            	    {
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:207:6: ( INDENT )?
            	    int alt18=2;
            	    int LA18_0 = input.LA(1);

            	    if ( (LA18_0==INDENT) ) {
            	        alt18=1;
            	    }
            	    switch (alt18) {
            	        case 1 :
            	            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:207:6: INDENT
            	            {
            	            match(input,INDENT,FOLLOW_INDENT_in_ifstat665); 

            	            }
            	            break;

            	    }


            	    match(input,LDELIM,FOLLOW_LDELIM_in_ifstat668); 

            	    match(input,ELSEIF,FOLLOW_ELSEIF_in_ifstat670); 

            	    match(input,LPAREN,FOLLOW_LPAREN_in_ifstat672); 

            	    pushFollow(FOLLOW_conditional_in_ifstat676);
            	    c2=conditional();

            	    state._fsp--;


            	    match(input,RPAREN,FOLLOW_RPAREN_in_ifstat678); 

            	    match(input,RDELIM,FOLLOW_RDELIM_in_ifstat680); 

            	    pushFollow(FOLLOW_block_in_ifstat684);
            	    t2=block();

            	    state._fsp--;


            	    conditions.add(c2);  statements.add(t2); 

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:208:4: ( ( INDENT )? LDELIM 'else' RDELIM blockElse= block )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==INDENT) ) {
                int LA21_1 = input.LA(2);

                if ( (LA21_1==LDELIM) ) {
                    int LA21_2 = input.LA(3);

                    if ( (LA21_2==ELSE) ) {
                        alt21=1;
                    }
                }
            }
            else if ( (LA21_0==LDELIM) ) {
                int LA21_2 = input.LA(2);

                if ( (LA21_2==ELSE) ) {
                    alt21=1;
                }
            }
            switch (alt21) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:208:6: ( INDENT )? LDELIM 'else' RDELIM blockElse= block
                    {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:208:6: ( INDENT )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==INDENT) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:208:6: INDENT
                            {
                            match(input,INDENT,FOLLOW_INDENT_in_ifstat695); 

                            }
                            break;

                    }


                    match(input,LDELIM,FOLLOW_LDELIM_in_ifstat698); 

                    match(input,ELSE,FOLLOW_ELSE_in_ifstat700); 

                    match(input,RDELIM,FOLLOW_RDELIM_in_ifstat702); 

                    pushFollow(FOLLOW_block_in_ifstat706);
                    blockElse=block();

                    state._fsp--;


                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:209:4: ( INDENT )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==INDENT) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:209:4: INDENT
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_ifstat713); 

                    }
                    break;

            }


            endif=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_ifstat719); 

            match(input,ENDIF,FOLLOW_ENDIF_in_ifstat721); 

            match(input,RDELIM,FOLLOW_RDELIM_in_ifstat725); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:212:3: ({...}? => NEWLINE )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==NEWLINE) ) {
                int LA23_1 = input.LA(2);

                if ( ((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                    alt23=1;
                }
            }
            switch (alt23) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:212:4: {...}? => NEWLINE
                    {
                    if ( !((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                        throw new FailedPredicateException(input, "ifstat", "$ifstat.start.getLine()!=input.LT(1).getLine()");
                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_ifstat736); 

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);



                retval.statement = c.stIf(conditions,statements, blockElse);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "ifstat"


    protected static class conditional_scope {
        boolean inside;
    }
    protected Stack conditional_stack = new Stack();



    // $ANTLR start "conditional"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:219:1: conditional returns [Expr v] : a= andConditional ( '||' a= andConditional )* ;
    public final Expr conditional() throws RecognitionException {
        conditional_stack.push(new conditional_scope());
        Expr v = null;


        Expr a =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:223:2: (a= andConditional ( '||' a= andConditional )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:223:4: a= andConditional ( '||' a= andConditional )*
            {
            pushFollow(FOLLOW_andConditional_in_conditional764);
            a=andConditional();

            state._fsp--;


            v=a;

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:223:27: ( '||' a= andConditional )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==OR) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:223:29: '||' a= andConditional
            	    {
            	    match(input,OR,FOLLOW_OR_in_conditional769); 

            	    pushFollow(FOLLOW_andConditional_in_conditional773);
            	    a=andConditional();

            	    state._fsp--;


            	    v=c.opConditional(Operator.OR,v,a); 

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
            conditional_stack.pop();
        }
        return v;
    }
    // $ANTLR end "conditional"



    // $ANTLR start "andConditional"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:226:1: andConditional returns [Expr v] : n= notConditional ( '&&' n= notConditional )* ;
    public final Expr andConditional() throws RecognitionException {
        Expr v = null;


        Expr n =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:226:33: (n= notConditional ( '&&' n= notConditional )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:226:35: n= notConditional ( '&&' n= notConditional )*
            {
            pushFollow(FOLLOW_notConditional_in_andConditional793);
            n=notConditional();

            state._fsp--;


            v=n;

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:226:58: ( '&&' n= notConditional )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==AND) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:226:60: '&&' n= notConditional
            	    {
            	    match(input,AND,FOLLOW_AND_in_andConditional798); 

            	    pushFollow(FOLLOW_notConditional_in_andConditional802);
            	    n=notConditional();

            	    state._fsp--;


            	    v=c.opConditional(Operator.AND,v,n); 

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "andConditional"



    // $ANTLR start "notConditional"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:228:1: notConditional returns [Expr v] : ( '!' n= notConditional |m= memberExpr );
    public final Expr notConditional() throws RecognitionException {
        Expr v = null;


        Expr n =null;

        Expr m =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:229:2: ( '!' n= notConditional |m= memberExpr )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==BANG) ) {
                alt26=1;
            }
            else if ( (LA26_0==LBRACK||LA26_0==LCURLY||(LA26_0 >= ID && LA26_0 <= STRING)||(LA26_0 >= TRUE && LA26_0 <= FALSE)) ) {
                alt26=2;
            }
            else if ( (LA26_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }
            switch (alt26) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:229:4: '!' n= notConditional
                    {
                    match(input,BANG,FOLLOW_BANG_in_notConditional821); 

                    pushFollow(FOLLOW_notConditional_in_notConditional825);
                    n=notConditional();

                    state._fsp--;


                    v=c.opNot(n);

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:230:4: m= memberExpr
                    {
                    pushFollow(FOLLOW_memberExpr_in_notConditional834);
                    m=memberExpr();

                    state._fsp--;


                    v=m;

                    }
                    break;

            }
        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "notConditional"



    // $ANTLR start "exprOptions"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:240:1: exprOptions : option ( ',' option )* ;
    public final void exprOptions() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:240:13: ( option ( ',' option )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:240:15: option ( ',' option )*
            {
            pushFollow(FOLLOW_option_in_exprOptions852);
            option();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:240:22: ( ',' option )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==COMMA) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:240:24: ',' option
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_exprOptions856); 

            	    pushFollow(FOLLOW_option_in_exprOptions858);
            	    option();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "exprOptions"



    // $ANTLR start "option"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:242:1: option : ID ( '=' exprNoComma |) ;
    public final void option() throws RecognitionException {
        CommonToken ID3=null;


        	//String id = input.LT(1).getText();
        	//String defVal = Compiler.defaultOptionValues.get(id);
        	//boolean validOption = Compiler.supportedOptions.get(id)!=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:248:2: ( ID ( '=' exprNoComma |) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:248:4: ID ( '=' exprNoComma |)
            {
            ID3=(CommonToken)match(input,ID,FOLLOW_ID_in_option877); 


            		//if ( !validOption ) {
                        //errMgr.compileTimeError(ErrorType.NO_SUCH_OPTION, templateToken, ID3, (ID3!=null?ID3.getText():null));
            		//}
            		

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:254:3: ( '=' exprNoComma |)
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==EQUALS) ) {
                alt28=1;
            }
            else if ( (LA28_0==COMMA||LA28_0==RDELIM) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;

            }
            switch (alt28) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:254:5: '=' exprNoComma
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_option887); 

                    pushFollow(FOLLOW_exprNoComma_in_option889);
                    exprNoComma();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:256:5: 
                    {

                    			//if ( defVal==null ) {
                    				//errMgr.compileTimeError(ErrorType.NO_DEFAULT_VALUE, templateToken, ID3);
                    			//}
                    			

                    }
                    break;

            }


            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "option"



    // $ANTLR start "exprNoComma"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:267:1: exprNoComma returns [Expr v] : m= memberExpr ( ':' mt= mapTemplateRef[v] |) ;
    public final Expr exprNoComma() throws RecognitionException {
        Expr v = null;


        Expr m =null;

        Expr mt =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:268:2: (m= memberExpr ( ':' mt= mapTemplateRef[v] |) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:268:4: m= memberExpr ( ':' mt= mapTemplateRef[v] |)
            {
            pushFollow(FOLLOW_memberExpr_in_exprNoComma948);
            m=memberExpr();

            state._fsp--;


            v=m;

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:269:3: ( ':' mt= mapTemplateRef[v] |)
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==COLON) ) {
                alt29=1;
            }
            else if ( (LA29_0==RPAREN||(LA29_0 >= RBRACK && LA29_0 <= COMMA)||LA29_0==RDELIM) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }
            switch (alt29) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:269:5: ':' mt= mapTemplateRef[v]
                    {
                    match(input,COLON,FOLLOW_COLON_in_exprNoComma956); 

                    pushFollow(FOLLOW_mapTemplateRef_in_exprNoComma960);
                    mt=mapTemplateRef(v);

                    state._fsp--;


                    v=mt;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:271:3: 
                    {
                    }
                    break;

            }


            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "exprNoComma"



    // $ANTLR start "expr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:274:1: expr returns [Expr v] : e= mapExpr ;
    public final Expr expr() throws RecognitionException {
        Expr v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:274:23: (e= mapExpr )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:274:25: e= mapExpr
            {
            pushFollow(FOLLOW_mapExpr_in_expr1001);
            e=mapExpr();

            state._fsp--;


            v=e;

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "expr"



    // $ANTLR start "mapExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:278:1: mapExpr returns [Expr v] : me= memberExpr ( (c= ',' me= memberExpr )+ col= ':' mt= mapTemplateRefListParams[params] |) (col= ':' mt= mapTemplateRef[v] ({...}? ',' mt= mapTemplateRef[v] )* )* ;
    public final Expr mapExpr() throws RecognitionException {
        Expr v = null;


        CommonToken c=null;
        CommonToken col=null;
        Expr me =null;

        Expr mt =null;



             List<Expr> params = new ArrayList<Expr>();

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:282:2: (me= memberExpr ( (c= ',' me= memberExpr )+ col= ':' mt= mapTemplateRefListParams[params] |) (col= ':' mt= mapTemplateRef[v] ({...}? ',' mt= mapTemplateRef[v] )* )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:282:4: me= memberExpr ( (c= ',' me= memberExpr )+ col= ':' mt= mapTemplateRefListParams[params] |) (col= ':' mt= mapTemplateRef[v] ({...}? ',' mt= mapTemplateRef[v] )* )*
            {
            pushFollow(FOLLOW_memberExpr_in_mapExpr1025);
            me=memberExpr();

            state._fsp--;


            v=me;

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:283:3: ( (c= ',' me= memberExpr )+ col= ':' mt= mapTemplateRefListParams[params] |)
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==COMMA) ) {
                alt31=1;
            }
            else if ( (LA31_0==SEMI||LA31_0==COLON||LA31_0==RPAREN||LA31_0==RDELIM) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }
            switch (alt31) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:283:5: (c= ',' me= memberExpr )+ col= ':' mt= mapTemplateRefListParams[params]
                    {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:283:5: (c= ',' me= memberExpr )+
                    int cnt30=0;
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==COMMA) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:283:6: c= ',' me= memberExpr
                    	    {
                    	    c=(CommonToken)match(input,COMMA,FOLLOW_COMMA_in_mapExpr1036); 

                    	    params.add(me);

                    	    pushFollow(FOLLOW_memberExpr_in_mapExpr1042);
                    	    me=memberExpr();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt30 >= 1 ) break loop30;
                                EarlyExitException eee =
                                    new EarlyExitException(30, input);
                                throw eee;
                        }
                        cnt30++;
                    } while (true);


                    params.add(me);

                    col=(CommonToken)match(input,COLON,FOLLOW_COLON_in_mapExpr1051); 

                    pushFollow(FOLLOW_mapTemplateRefListParams_in_mapExpr1055);
                    mt=mapTemplateRefListParams(params);

                    state._fsp--;


                    v= mt;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:286:3: 
                    {
                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:287:3: (col= ':' mt= mapTemplateRef[v] ({...}? ',' mt= mapTemplateRef[v] )* )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==COLON) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:288:4: col= ':' mt= mapTemplateRef[v] ({...}? ',' mt= mapTemplateRef[v] )*
            	    {
            	    col=(CommonToken)match(input,COLON,FOLLOW_COLON_in_mapExpr1101); 

            	    pushFollow(FOLLOW_mapTemplateRef_in_mapExpr1105);
            	    mt=mapTemplateRef(v);

            	    state._fsp--;


            	    v=mt;

            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:288:41: ({...}? ',' mt= mapTemplateRef[v] )*
            	    loop32:
            	    do {
            	        int alt32=2;
            	        int LA32_0 = input.LA(1);

            	        if ( (LA32_0==COMMA) ) {
            	            alt32=1;
            	        }


            	        switch (alt32) {
            	    	case 1 :
            	    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:288:42: {...}? ',' mt= mapTemplateRef[v]
            	    	    {
            	    	    if ( !((c==null)) ) {
            	    	        throw new FailedPredicateException(input, "mapExpr", "$c==null");
            	    	    }

            	    	    match(input,COMMA,FOLLOW_COMMA_in_mapExpr1113); 

            	    	    pushFollow(FOLLOW_mapTemplateRef_in_mapExpr1117);
            	    	    mt=mapTemplateRef(v);

            	    	    state._fsp--;


            	    	    v=mt;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop32;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);


            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "mapExpr"



    // $ANTLR start "mapTemplateRef"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:298:1: mapTemplateRef[ Expr data] returns [Expr v] : ( ID '(' as= args ')' |st= subtemplate |lp= '(' me= mapExpr rp= ')' '(' (as= argExprList )? ')' );
    public final Expr mapTemplateRef(Expr data) throws RecognitionException {
        Expr v = null;


        CommonToken lp=null;
        CommonToken rp=null;
        CommonToken ID4=null;
        List<Argument> as =null;

        int st =0;

        Expr me =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:299:2: ( ID '(' as= args ')' |st= subtemplate |lp= '(' me= mapExpr rp= ')' '(' (as= argExprList )? ')' )
            int alt35=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt35=1;
                }
                break;
            case LCURLY:
                {
                alt35=2;
                }
                break;
            case LPAREN:
                {
                alt35=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }

            switch (alt35) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:299:4: ID '(' as= args ')'
                    {
                    ID4=(CommonToken)match(input,ID,FOLLOW_ID_in_mapTemplateRef1160); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef1162); 

                    pushFollow(FOLLOW_args_in_mapTemplateRef1166);
                    as=args();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef1168); 

                    v=c.opInclude(c.opLocal(v("group")),c.opName((ID4!=null?ID4.getText():null)),data,as);

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:300:4: st= subtemplate
                    {
                    pushFollow(FOLLOW_subtemplate_in_mapTemplateRef1183);
                    st=subtemplate();

                    state._fsp--;


                    v=c.opInclude(c.opLocal(v("template")),st,data);

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:301:4: lp= '(' me= mapExpr rp= ')' '(' (as= argExprList )? ')'
                    {
                    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef1196); 

                    pushFollow(FOLLOW_mapExpr_in_mapTemplateRef1200);
                    me=mapExpr();

                    state._fsp--;


                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef1204); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef1206); 

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:301:35: (as= argExprList )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==LBRACK||LA34_0==LCURLY||(LA34_0 >= ID && LA34_0 <= STRING)||(LA34_0 >= TRUE && LA34_0 <= FALSE)) ) {
                        alt34=1;
                    }
                    else if ( (LA34_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:301:35: as= argExprList
                            {
                            pushFollow(FOLLOW_argExprList_in_mapTemplateRef1210);
                            as=argExprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef1213); 

                    v=c.opInclude(c.opLocal(v("group")),me,data,as);

                    }
                    break;

            }
        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "mapTemplateRef"



    // $ANTLR start "mapTemplateRefListParams"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:304:1: mapTemplateRefListParams[ List<Expr> dataList] returns [Expr v] : ( ID '(' as= args ')' |st= subtemplate |lp= '(' me= mapExpr rp= ')' '(' (as= argExprList )? ')' );
    public final Expr mapTemplateRefListParams(List<Expr> dataList) throws RecognitionException {
        Expr v = null;


        CommonToken lp=null;
        CommonToken rp=null;
        CommonToken ID5=null;
        List<Argument> as =null;

        int st =0;

        Expr me =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:305:3: ( ID '(' as= args ')' |st= subtemplate |lp= '(' me= mapExpr rp= ')' '(' (as= argExprList )? ')' )
            int alt37=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt37=1;
                }
                break;
            case LCURLY:
                {
                alt37=2;
                }
                break;
            case LPAREN:
                {
                alt37=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;

            }

            switch (alt37) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:305:5: ID '(' as= args ')'
                    {
                    ID5=(CommonToken)match(input,ID,FOLLOW_ID_in_mapTemplateRefListParams1233); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRefListParams1235); 

                    pushFollow(FOLLOW_args_in_mapTemplateRefListParams1239);
                    as=args();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRefListParams1241); 

                    v=c.opInclude(c.opLocal(v("group")),c.opName((ID5!=null?ID5.getText():null)),dataList,as);

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:306:5: st= subtemplate
                    {
                    pushFollow(FOLLOW_subtemplate_in_mapTemplateRefListParams1263);
                    st=subtemplate();

                    state._fsp--;


                    v=c.opInclude(c.opLocal(v("template")),st,dataList);

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:307:5: lp= '(' me= mapExpr rp= ')' '(' (as= argExprList )? ')'
                    {
                    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRefListParams1278); 

                    pushFollow(FOLLOW_mapExpr_in_mapTemplateRefListParams1282);
                    me=mapExpr();

                    state._fsp--;


                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRefListParams1286); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRefListParams1288); 

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:307:36: (as= argExprList )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==LBRACK||LA36_0==LCURLY||(LA36_0 >= ID && LA36_0 <= STRING)||(LA36_0 >= TRUE && LA36_0 <= FALSE)) ) {
                        alt36=1;
                    }
                    else if ( (LA36_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:307:36: as= argExprList
                            {
                            pushFollow(FOLLOW_argExprList_in_mapTemplateRefListParams1292);
                            as=argExprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRefListParams1295); 

                    v=c.opInclude(c.opLocal(v("group")),me,dataList,as);

                    }
                    break;

            }
        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "mapTemplateRefListParams"



    // $ANTLR start "memberExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:310:1: memberExpr returns [Expr v] : (ie= includeExpr ) (p= '.' ID )* ;
    public final Expr memberExpr() throws RecognitionException {
        Expr v = null;


        CommonToken p=null;
        CommonToken ID6=null;
        Expr ie =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:311:2: ( (ie= includeExpr ) (p= '.' ID )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:311:4: (ie= includeExpr ) (p= '.' ID )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:311:4: (ie= includeExpr )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:311:5: ie= includeExpr
            {
            pushFollow(FOLLOW_includeExpr_in_memberExpr1317);
            ie=includeExpr();

            state._fsp--;


            v=ie;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:312:3: (p= '.' ID )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==DOT) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:312:5: p= '.' ID
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_memberExpr1329); 

            	    ID6=(CommonToken)match(input,ID,FOLLOW_ID_in_memberExpr1331); 

            	    v=c.opFieldOf(v,(ID6!=null?ID6.getText():null));

            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);


            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "memberExpr"



    // $ANTLR start "includeExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:317:1: includeExpr returns [Expr v] options {k=2; } : ({...}? ID '(' ( expr )? ')' | ID '(' as= args ')' |p= primary );
    public final Expr includeExpr() throws RecognitionException {
        Expr v = null;


        CommonToken ID7=null;
        List<Argument> as =null;

        Expr p =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:319:2: ({...}? ID '(' ( expr )? ')' | ID '(' as= args ')' |p= primary )
            int alt40=3;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==ID) ) {
                int LA40_1 = input.LA(2);

                if ( (LA40_1==LPAREN) ) {
                    int LA40_8 = input.LA(3);

                    if ( ((Compiler.funcs.containsKey(input.LT(1).getText()))) ) {
                        alt40=1;
                    }
                    else if ( (true) ) {
                        alt40=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 40, 8, input);

                        throw nvae;

                    }
                }
                else if ( (LA40_1==SEMI||LA40_1==COLON||LA40_1==RPAREN||(LA40_1 >= RBRACK && LA40_1 <= DOT)||LA40_1==RDELIM||(LA40_1 >= OR && LA40_1 <= AND)) ) {
                    alt40=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA40_0==LBRACK||LA40_0==LCURLY||LA40_0==STRING||(LA40_0 >= TRUE && LA40_0 <= FALSE)) ) {
                alt40=3;
            }
            else if ( (LA40_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt40=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;

            }
            switch (alt40) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:319:4: {...}? ID '(' ( expr )? ')'
                    {
                    if ( !((Compiler.funcs.containsKey(input.LT(1).getText()))) ) {
                        throw new FailedPredicateException(input, "includeExpr", "Compiler.funcs.containsKey(input.LT(1).getText())");
                    }

                    match(input,ID,FOLLOW_ID_in_includeExpr1374); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1376); 

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:320:10: ( expr )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==LBRACK||LA39_0==LCURLY||(LA39_0 >= ID && LA39_0 <= STRING)||(LA39_0 >= TRUE && LA39_0 <= FALSE)) ) {
                        alt39=1;
                    }
                    else if ( (LA39_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:320:10: expr
                            {
                            pushFollow(FOLLOW_expr_in_includeExpr1378);
                            expr();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1381); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:322:4: ID '(' as= args ')'
                    {
                    ID7=(CommonToken)match(input,ID,FOLLOW_ID_in_includeExpr1393); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1395); 

                    pushFollow(FOLLOW_args_in_includeExpr1399);
                    as=args();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1401); 

                    v=c.opInclude(c.opLocal(v("group")),c.opName((ID7!=null?ID7.getText():null)),as);

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:325:4: p= primary
                    {
                    pushFollow(FOLLOW_primary_in_includeExpr1418);
                    p=primary();

                    state._fsp--;


                    v=p;

                    }
                    break;

            }
        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "includeExpr"



    // $ANTLR start "primary"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:328:1: primary returns [Expr v] : ( ID | STRING | TRUE | FALSE |st= subtemplate | list |{...}? => '(' cd= conditional ')' |{...}? =>lp= '(' name= expr ')' ( '(' (as= argExprList )? ')' |) );
    public final Expr primary() throws RecognitionException {
        Expr v = null;


        CommonToken lp=null;
        CommonToken ID8=null;
        CommonToken STRING9=null;
        int st =0;

        Expr cd =null;

        Expr name =null;

        List<Argument> as =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:329:2: ( ID | STRING | TRUE | FALSE |st= subtemplate | list |{...}? => '(' cd= conditional ')' |{...}? =>lp= '(' name= expr ')' ( '(' (as= argExprList )? ')' |) )
            int alt43=8;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==ID) ) {
                alt43=1;
            }
            else if ( (LA43_0==STRING) ) {
                alt43=2;
            }
            else if ( (LA43_0==TRUE) ) {
                alt43=3;
            }
            else if ( (LA43_0==FALSE) ) {
                alt43=4;
            }
            else if ( (LA43_0==LCURLY) ) {
                alt43=5;
            }
            else if ( (LA43_0==LBRACK) ) {
                alt43=6;
            }
            else if ( (LA43_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                int LA43_7 = input.LA(2);

                if ( ((conditional_stack.size()>0)) ) {
                    alt43=7;
                }
                else if ( ((conditional_stack.size()==0)) ) {
                    alt43=8;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 43, 7, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;

            }
            switch (alt43) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:329:4: ID
                    {
                    ID8=(CommonToken)match(input,ID,FOLLOW_ID_in_primary1435); 

                    v=c.opArg(v("argv"),arg((ID8!=null?ID8.getText():null)));

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:330:4: STRING
                    {
                    STRING9=(CommonToken)match(input,STRING,FOLLOW_STRING_in_primary1452); 

                    v=c.opStringCst((STRING9!=null?STRING9.getText():null));

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:331:4: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_primary1461); 

                    v=c.opYesnoCst(true);

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:332:4: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_primary1476); 

                    v=c.opYesnoCst(false);

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:333:4: st= subtemplate
                    {
                    pushFollow(FOLLOW_subtemplate_in_primary1490);
                    st=subtemplate();

                    state._fsp--;


                    v= c.opInclude(c.opLocal(v("template")),st);

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:334:4: list
                    {
                    pushFollow(FOLLOW_list_in_primary1497);
                    list();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:335:4: {...}? => '(' cd= conditional ')'
                    {
                    if ( !((conditional_stack.size()>0)) ) {
                        throw new FailedPredicateException(input, "primary", "$conditional.size()>0");
                    }

                    match(input,LPAREN,FOLLOW_LPAREN_in_primary1506); 

                    pushFollow(FOLLOW_conditional_in_primary1510);
                    cd=conditional();

                    state._fsp--;


                    v=cd;

                    match(input,RPAREN,FOLLOW_RPAREN_in_primary1513); 

                    }
                    break;
                case 8 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:336:4: {...}? =>lp= '(' name= expr ')' ( '(' (as= argExprList )? ')' |)
                    {
                    if ( !((conditional_stack.size()==0)) ) {
                        throw new FailedPredicateException(input, "primary", "$conditional.size()==0");
                    }

                    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_primary1523); 

                    pushFollow(FOLLOW_expr_in_primary1527);
                    name=expr();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_primary1529); 

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:336:54: ( '(' (as= argExprList )? ')' |)
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==LPAREN) ) {
                        alt42=1;
                    }
                    else if ( (LA42_0==SEMI||LA42_0==COLON||LA42_0==RPAREN||(LA42_0 >= RBRACK && LA42_0 <= DOT)||LA42_0==RDELIM||(LA42_0 >= OR && LA42_0 <= AND)) ) {
                        alt42=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 42, 0, input);

                        throw nvae;

                    }
                    switch (alt42) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:336:56: '(' (as= argExprList )? ')'
                            {
                            match(input,LPAREN,FOLLOW_LPAREN_in_primary1534); 

                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:336:62: (as= argExprList )?
                            int alt41=2;
                            int LA41_0 = input.LA(1);

                            if ( (LA41_0==LBRACK||LA41_0==LCURLY||(LA41_0 >= ID && LA41_0 <= STRING)||(LA41_0 >= TRUE && LA41_0 <= FALSE)) ) {
                                alt41=1;
                            }
                            else if ( (LA41_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                                alt41=1;
                            }
                            switch (alt41) {
                                case 1 :
                                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:336:62: as= argExprList
                                    {
                                    pushFollow(FOLLOW_argExprList_in_primary1538);
                                    as=argExprList();

                                    state._fsp--;


                                    }
                                    break;

                            }


                            match(input,RPAREN,FOLLOW_RPAREN_in_primary1541); 

                            v=c.opInclude(c.opLocal(v("group")),name,as);

                            }
                            break;
                        case 2 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:338:3: 
                            {
                            }
                            break;

                    }


                    }
                    break;

            }
        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "primary"



    // $ANTLR start "args"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:341:1: args returns [List<Argument> args] : (as= argExprList |a= namedArg ( ',' a= namedArg )* ( ',' '...' )? | '...' |);
    public final List<Argument> args() throws RecognitionException {
        List<Argument> args = null;


        List<Argument> as =null;

        Argument a =null;



          args = new ArrayList();

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:345:3: (as= argExprList |a= namedArg ( ',' a= namedArg )* ( ',' '...' )? | '...' |)
            int alt46=4;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==ID) ) {
                int LA46_1 = input.LA(2);

                if ( ((LA46_1 >= COLON && LA46_1 <= RPAREN)||(LA46_1 >= COMMA && LA46_1 <= DOT)) ) {
                    alt46=1;
                }
                else if ( (LA46_1==EQUALS) ) {
                    alt46=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 46, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA46_0==LBRACK||LA46_0==LCURLY||LA46_0==STRING||(LA46_0 >= TRUE && LA46_0 <= FALSE)) ) {
                alt46=1;
            }
            else if ( (LA46_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt46=1;
            }
            else if ( (LA46_0==ELLIPSIS) ) {
                alt46=3;
            }
            else if ( (LA46_0==RPAREN) ) {
                alt46=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }
            switch (alt46) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:345:5: as= argExprList
                    {
                    pushFollow(FOLLOW_argExprList_in_args1594);
                    as=argExprList();

                    state._fsp--;


                    args=as;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:346:4: a= namedArg ( ',' a= namedArg )* ( ',' '...' )?
                    {
                    pushFollow(FOLLOW_namedArg_in_args1603);
                    a=namedArg();

                    state._fsp--;


                    c.opAddArgument(args,a);

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:346:41: ( ',' a= namedArg )*
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==COMMA) ) {
                            int LA44_1 = input.LA(2);

                            if ( (LA44_1==ID) ) {
                                alt44=1;
                            }


                        }


                        switch (alt44) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:346:43: ',' a= namedArg
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_args1608); 

                    	    pushFollow(FOLLOW_namedArg_in_args1612);
                    	    a=namedArg();

                    	    state._fsp--;


                    	    c.opAddArgument(args,a);

                    	    }
                    	    break;

                    	default :
                    	    break loop44;
                        }
                    } while (true);


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:346:87: ( ',' '...' )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==COMMA) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:346:88: ',' '...'
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_args1619); 

                            match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_args1621); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:347:9: '...'
                    {
                    match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_args1634); 

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:349:2: 
                    {
                    }
                    break;

            }
        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return args;
    }
    // $ANTLR end "args"



    // $ANTLR start "argExprList"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:351:1: argExprList returns [List<Argument> args] : a= arg ( ',' a= arg )* ;
    public final List<Argument> argExprList() throws RecognitionException {
        List<Argument> args = null;


        Argument a =null;



          args = new ArrayList();

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:355:3: (a= arg ( ',' a= arg )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:355:3: a= arg ( ',' a= arg )*
            {
            pushFollow(FOLLOW_arg_in_argExprList1658);
            a=arg();

            state._fsp--;


            c.opAddArgument(args,a);

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:355:35: ( ',' a= arg )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==COMMA) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:355:37: ',' a= arg
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_argExprList1663); 

            	    pushFollow(FOLLOW_arg_in_argExprList1667);
            	    a=arg();

            	    state._fsp--;


            	    c.opAddArgument(args,a);

            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);


            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return args;
    }
    // $ANTLR end "argExprList"



    // $ANTLR start "arg"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:357:1: arg returns [Argument v] : e= exprNoComma ;
    public final Argument arg() throws RecognitionException {
        Argument v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:357:27: (e= exprNoComma )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:357:29: e= exprNoComma
            {
            pushFollow(FOLLOW_exprNoComma_in_arg1688);
            e=exprNoComma();

            state._fsp--;


            v=c.opArgument(e);

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "arg"



    // $ANTLR start "namedArg"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:359:1: namedArg returns [Argument v] : ID '=' a= arg ;
    public final Argument namedArg() throws RecognitionException {
        Argument v = null;


        CommonToken ID10=null;
        Argument a =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:359:32: ( ID '=' a= arg )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:359:34: ID '=' a= arg
            {
            ID10=(CommonToken)match(input,ID,FOLLOW_ID_in_namedArg1704); 

            match(input,EQUALS,FOLLOW_EQUALS_in_namedArg1706); 

            pushFollow(FOLLOW_arg_in_namedArg1710);
            a=arg();

            state._fsp--;


            v=c.opArgument((ID10!=null?ID10.getText():null),a);

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "namedArg"



    // $ANTLR start "list"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:361:1: list : ({...}?lb= '[' ']' |lb= '[' listElement ( ',' listElement )* ']' );
    public final void list() throws RecognitionException {
        CommonToken lb=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:361:5: ({...}?lb= '[' ']' |lb= '[' listElement ( ',' listElement )* ']' )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==LBRACK) ) {
                int LA49_1 = input.LA(2);

                if ( (LA49_1==RBRACK) ) {
                    int LA49_2 = input.LA(3);

                    if ( ((input.LA(2)==RBRACK)) ) {
                        alt49=1;
                    }
                    else if ( (true) ) {
                        alt49=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 49, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA49_1==LPAREN||LA49_1==LBRACK||LA49_1==COMMA||LA49_1==LCURLY||(LA49_1 >= ID && LA49_1 <= STRING)||(LA49_1 >= TRUE && LA49_1 <= FALSE)) ) {
                    alt49=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 49, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;

            }
            switch (alt49) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:361:7: {...}?lb= '[' ']'
                    {
                    if ( !((input.LA(2)==RBRACK)) ) {
                        throw new FailedPredicateException(input, "list", "input.LA(2)==RBRACK");
                    }

                    lb=(CommonToken)match(input,LBRACK,FOLLOW_LBRACK_in_list1727); 

                    match(input,RBRACK,FOLLOW_RBRACK_in_list1729); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:363:4: lb= '[' listElement ( ',' listElement )* ']'
                    {
                    lb=(CommonToken)match(input,LBRACK,FOLLOW_LBRACK_in_list1737); 

                    pushFollow(FOLLOW_listElement_in_list1739);
                    listElement();

                    state._fsp--;


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:363:23: ( ',' listElement )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==COMMA) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:363:25: ',' listElement
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_list1743); 

                    	    pushFollow(FOLLOW_listElement_in_list1745);
                    	    listElement();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop48;
                        }
                    } while (true);


                    match(input,RBRACK,FOLLOW_RBRACK_in_list1750); 

                    }
                    break;

            }
        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "list"



    // $ANTLR start "listElement"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:366:1: listElement returns [Expr v] : (e= exprNoComma |);
    public final Expr listElement() throws RecognitionException {
        Expr v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:366:29: (e= exprNoComma |)
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==LBRACK||LA50_0==LCURLY||(LA50_0 >= ID && LA50_0 <= STRING)||(LA50_0 >= TRUE && LA50_0 <= FALSE)) ) {
                alt50=1;
            }
            else if ( (LA50_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt50=1;
            }
            else if ( ((LA50_0 >= RBRACK && LA50_0 <= COMMA)) ) {
                alt50=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;

            }
            switch (alt50) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:366:31: e= exprNoComma
                    {
                    pushFollow(FOLLOW_exprNoComma_in_listElement1766);
                    e=exprNoComma();

                    state._fsp--;


                    v=e;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:366:66: 
                    {
                    }
                    break;

            }
        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "listElement"

    // Delegated rules


 

    public static final BitSet FOLLOW_templateGroupDef_in_templateGroupFile128 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_templateGroupFile130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_templateGroupDef163 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_templateGroupDef165 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_templateGroupDef166 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ID_in_templateGroupDef176 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_templateGroupDef178 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_templateGroupDef182 = new BitSet(new long[]{0x0000000000048000L});
    public static final BitSet FOLLOW_COMMA_in_templateGroupDef188 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_templateGroupDef192 = new BitSet(new long[]{0x0000000000048000L});
    public static final BitSet FOLLOW_RPAREN_in_templateGroupDef199 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COLON_in_templateGroupDef214 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COLON_in_templateGroupDef215 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_EQUALS_in_templateGroupDef216 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_templateGroupDef220 = new BitSet(new long[]{0x0000002181C00000L});
    public static final BitSet FOLLOW_template_in_templateGroupDef224 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_templateGroupDef226 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_INDENT_in_templateGroupDef229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_template_in_templateAndEOF258 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_templateAndEOF261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_template285 = new BitSet(new long[]{0x0000002180C00002L});
    public static final BitSet FOLLOW_element_in_block313 = new BitSet(new long[]{0x0000002180C00002L});
    public static final BitSet FOLLOW_INDENT_in_element335 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_COMMENT_in_element338 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_NEWLINE_in_element340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_element346 = new BitSet(new long[]{0x0000002100C00000L});
    public static final BitSet FOLLOW_singleElement_in_element350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_singleElement_in_element359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compoundElement_in_element367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprTag_in_singleElement384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_singleElement392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_singleElement409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMENT_in_singleElement419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifstat_in_compoundElement436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_region_in_compoundElement444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LDELIM_in_exprTag458 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_expr_in_exprTag462 = new BitSet(new long[]{0x0000000001000200L});
    public static final BitSet FOLLOW_SEMI_in_exprTag466 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_exprOptions_in_exprTag468 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_exprTag473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_region496 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_region501 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_AT_in_region503 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_region505 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_region507 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_region513 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_region517 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_region520 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_END_in_region522 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_region524 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_NEWLINE_in_region535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_subtemplate563 = new BitSet(new long[]{0x0000002182E00000L});
    public static final BitSet FOLLOW_ID_in_subtemplate568 = new BitSet(new long[]{0x0000000010040000L});
    public static final BitSet FOLLOW_COMMA_in_subtemplate574 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_subtemplate578 = new BitSet(new long[]{0x0000000010040000L});
    public static final BitSet FOLLOW_PIPE_in_subtemplate584 = new BitSet(new long[]{0x0000002180E00000L});
    public static final BitSet FOLLOW_template_in_subtemplate591 = new BitSet(new long[]{0x0000000080200000L});
    public static final BitSet FOLLOW_INDENT_in_subtemplate593 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_RCURLY_in_subtemplate601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_ifstat633 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat636 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IF_in_ifstat638 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_ifstat640 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_conditional_in_ifstat644 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_ifstat645 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat647 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_block_in_ifstat656 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat665 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat668 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ELSEIF_in_ifstat670 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_ifstat672 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_conditional_in_ifstat676 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_ifstat678 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat680 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_block_in_ifstat684 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat695 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat698 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ELSE_in_ifstat700 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat702 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_block_in_ifstat706 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat713 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat719 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ENDIF_in_ifstat721 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat725 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ifstat736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andConditional_in_conditional764 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_OR_in_conditional769 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_andConditional_in_conditional773 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_notConditional_in_andConditional793 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_AND_in_andConditional798 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_notConditional_in_andConditional802 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_BANG_in_notConditional821 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_notConditional_in_notConditional825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_notConditional834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_option_in_exprOptions852 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_exprOptions856 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_option_in_exprOptions858 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ID_in_option877 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_EQUALS_in_option887 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_exprNoComma_in_option889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_exprNoComma948 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COLON_in_exprNoComma956 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_exprNoComma960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mapExpr_in_expr1001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_mapExpr1025 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_COMMA_in_mapExpr1036 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_memberExpr_in_mapExpr1042 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_COLON_in_mapExpr1051 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRefListParams_in_mapExpr1055 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COLON_in_mapExpr1101 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr1105 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_COMMA_in_mapExpr1113 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr1117 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_ID_in_mapTemplateRef1160 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef1162 = new BitSet(new long[]{0x000000180611C800L});
    public static final BitSet FOLLOW_args_in_mapTemplateRef1166 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef1168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subtemplate_in_mapTemplateRef1183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef1196 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_mapExpr_in_mapTemplateRef1200 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef1204 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef1206 = new BitSet(new long[]{0x000000180611C000L});
    public static final BitSet FOLLOW_argExprList_in_mapTemplateRef1210 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef1213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_mapTemplateRefListParams1233 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRefListParams1235 = new BitSet(new long[]{0x000000180611C800L});
    public static final BitSet FOLLOW_args_in_mapTemplateRefListParams1239 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRefListParams1241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subtemplate_in_mapTemplateRefListParams1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRefListParams1278 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_mapExpr_in_mapTemplateRefListParams1282 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRefListParams1286 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRefListParams1288 = new BitSet(new long[]{0x000000180611C000L});
    public static final BitSet FOLLOW_argExprList_in_mapTemplateRefListParams1292 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRefListParams1295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_includeExpr_in_memberExpr1317 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_memberExpr1329 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_memberExpr1331 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_ID_in_includeExpr1374 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1376 = new BitSet(new long[]{0x000000180611C000L});
    public static final BitSet FOLLOW_expr_in_includeExpr1378 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_includeExpr1393 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1395 = new BitSet(new long[]{0x000000180611C800L});
    public static final BitSet FOLLOW_args_in_includeExpr1399 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_includeExpr1418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_primary1452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_primary1461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_primary1476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subtemplate_in_primary1490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_primary1497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1506 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_conditional_in_primary1510 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1523 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_expr_in_primary1527 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1529 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1534 = new BitSet(new long[]{0x000000180611C000L});
    public static final BitSet FOLLOW_argExprList_in_primary1538 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_argExprList_in_args1594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_namedArg_in_args1603 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_args1608 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_namedArg_in_args1612 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_args1619 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ELLIPSIS_in_args1621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELLIPSIS_in_args1634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_in_argExprList1658 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_argExprList1663 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_arg_in_argExprList1667 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_exprNoComma_in_arg1688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_namedArg1704 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_EQUALS_in_namedArg1706 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_arg_in_namedArg1710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_list1727 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACK_in_list1729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_list1737 = new BitSet(new long[]{0x0000001806174000L});
    public static final BitSet FOLLOW_listElement_in_list1739 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_COMMA_in_list1743 = new BitSet(new long[]{0x0000001806174000L});
    public static final BitSet FOLLOW_listElement_in_list1745 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_RBRACK_in_list1750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprNoComma_in_listElement1766 = new BitSet(new long[]{0x0000000000000002L});

}