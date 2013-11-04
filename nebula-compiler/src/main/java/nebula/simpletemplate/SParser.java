// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g 2013-11-04 14:51:29

package nebula.simpletemplate;

import org.objectweb.asm.Type;

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



    // $ANTLR start "templateAndEOF"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:97:1: templateAndEOF returns [TemplateImpl temp] : t= template EOF ;
    public final TemplateImpl templateAndEOF() throws RecognitionException {
        TemplateImpl temp = null;


        TemplateImpl t =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:97:44: (t= template EOF )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:97:46: t= template EOF
            {
            pushFollow(FOLLOW_template_in_templateAndEOF128);
            t=template();

            state._fsp--;


            temp=t;

            match(input,EOF,FOLLOW_EOF_in_templateAndEOF131); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:99:1: template returns [TemplateImpl temp] : (e= element )* ;
    public final TemplateImpl template() throws RecognitionException {
        TemplateImpl temp = null;


        Statement e =null;



              initLocals();
              List<Statement> statments = new ArrayList<Statement>();
              arges = new ArrayList<Var>();
            
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:105:3: ( (e= element )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:105:5: (e= element )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:105:5: (e= element )*
            loop1:
            do {
                int alt1=2;
                switch ( input.LA(1) ) {
                case INDENT:
                    {
                    int LA1_2 = input.LA(2);

                    if ( (LA1_2==LDELIM) ) {
                        int LA1_5 = input.LA(3);

                        if ( (LA1_5==IF||LA1_5==SUPER||LA1_5==LPAREN||LA1_5==LBRACK||LA1_5==LCURLY||(LA1_5 >= ID && LA1_5 <= STRING)||LA1_5==AT||(LA1_5 >= TRUE && LA1_5 <= FALSE)) ) {
                            alt1=1;
                        }


                    }
                    else if ( (LA1_2==TEXT||LA1_2==NEWLINE||LA1_2==COMMENT) ) {
                        alt1=1;
                    }


                    }
                    break;
                case LDELIM:
                    {
                    int LA1_3 = input.LA(2);

                    if ( (LA1_3==IF||LA1_3==SUPER||LA1_3==LPAREN||LA1_3==LBRACK||LA1_3==LCURLY||(LA1_3 >= ID && LA1_3 <= STRING)||LA1_3==AT||(LA1_3 >= TRUE && LA1_3 <= FALSE)) ) {
                        alt1=1;
                    }


                    }
                    break;
                case TEXT:
                case NEWLINE:
                case COMMENT:
                    {
                    alt1=1;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:105:6: e= element
            	    {
            	    pushFollow(FOLLOW_element_in_template155);
            	    e=element();

            	    state._fsp--;


            	    if(e!=null)statments.add(e);

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            Statement s=c.stBlock(statments);temp=c.tpTemplate(group,s,arges);

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return temp;
    }
    // $ANTLR end "template"



    // $ANTLR start "element"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:107:1: element returns [Statement s] : ({...}? ( INDENT )? COMMENT NEWLINE | INDENT se= singleElement |se= singleElement | compoundElement );
    public final Statement element() throws RecognitionException {
        Statement s = null;


        Statement se =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:108:2: ({...}? ( INDENT )? COMMENT NEWLINE | INDENT se= singleElement |se= singleElement | compoundElement )
            int alt3=4;
            switch ( input.LA(1) ) {
            case INDENT:
                {
                switch ( input.LA(2) ) {
                case COMMENT:
                    {
                    int LA3_5 = input.LA(3);

                    if ( (LA3_5==NEWLINE) ) {
                        int LA3_11 = input.LA(4);

                        if ( ((input.LT(1).getCharPositionInLine()==0)) ) {
                            alt3=1;
                        }
                        else if ( (true) ) {
                            alt3=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 11, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA3_5==EOF||(LA3_5 >= RCURLY && LA3_5 <= LDELIM)||LA3_5==INDENT||LA3_5==COMMENT) ) {
                        alt3=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 5, input);

                        throw nvae;

                    }
                    }
                    break;
                case LDELIM:
                    {
                    switch ( input.LA(3) ) {
                    case IF:
                        {
                        alt3=4;
                        }
                        break;
                    case AT:
                        {
                        int LA3_12 = input.LA(4);

                        if ( (LA3_12==ID) ) {
                            int LA3_15 = input.LA(5);

                            if ( (LA3_15==RDELIM) ) {
                                alt3=4;
                            }
                            else if ( (LA3_15==LPAREN) ) {
                                alt3=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 3, 15, input);

                                throw nvae;

                            }
                        }
                        else if ( (LA3_12==SUPER) ) {
                            alt3=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 12, input);

                            throw nvae;

                        }
                        }
                        break;
                    case SUPER:
                    case LPAREN:
                    case LBRACK:
                    case LCURLY:
                    case ID:
                    case STRING:
                    case TRUE:
                    case FALSE:
                        {
                        alt3=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 6, input);

                        throw nvae;

                    }

                    }
                    break;
                case TEXT:
                case NEWLINE:
                    {
                    alt3=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;

                }

                }
                break;
            case COMMENT:
                {
                int LA3_2 = input.LA(2);

                if ( (LA3_2==NEWLINE) ) {
                    int LA3_8 = input.LA(3);

                    if ( ((input.LT(1).getCharPositionInLine()==0)) ) {
                        alt3=1;
                    }
                    else if ( (true) ) {
                        alt3=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 8, input);

                        throw nvae;

                    }
                }
                else if ( (LA3_2==EOF||(LA3_2 >= RCURLY && LA3_2 <= LDELIM)||LA3_2==INDENT||LA3_2==COMMENT) ) {
                    alt3=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

                    throw nvae;

                }
                }
                break;
            case LDELIM:
                {
                switch ( input.LA(2) ) {
                case IF:
                    {
                    alt3=4;
                    }
                    break;
                case AT:
                    {
                    int LA3_10 = input.LA(3);

                    if ( (LA3_10==ID) ) {
                        int LA3_14 = input.LA(4);

                        if ( (LA3_14==RDELIM) ) {
                            alt3=4;
                        }
                        else if ( (LA3_14==LPAREN) ) {
                            alt3=3;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 14, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA3_10==SUPER) ) {
                        alt3=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 10, input);

                        throw nvae;

                    }
                    }
                    break;
                case SUPER:
                case LPAREN:
                case LBRACK:
                case LCURLY:
                case ID:
                case STRING:
                case TRUE:
                case FALSE:
                    {
                    alt3=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 3, input);

                    throw nvae;

                }

                }
                break;
            case TEXT:
            case NEWLINE:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:108:4: {...}? ( INDENT )? COMMENT NEWLINE
                    {
                    if ( !((input.LT(1).getCharPositionInLine()==0)) ) {
                        throw new FailedPredicateException(input, "element", "input.LT(1).getCharPositionInLine()==0");
                    }

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:108:46: ( INDENT )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==INDENT) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:108:46: INDENT
                            {
                            match(input,INDENT,FOLLOW_INDENT_in_element176); 

                            }
                            break;

                    }


                    match(input,COMMENT,FOLLOW_COMMENT_in_element179); 

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_element181); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:109:4: INDENT se= singleElement
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_element187); 

                    pushFollow(FOLLOW_singleElement_in_element191);
                    se=singleElement();

                    state._fsp--;


                    s=se;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:110:4: se= singleElement
                    {
                    pushFollow(FOLLOW_singleElement_in_element200);
                    se=singleElement();

                    state._fsp--;


                    s=se;

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:111:4: compoundElement
                    {
                    pushFollow(FOLLOW_compoundElement_in_element206);
                    compoundElement();

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
    // $ANTLR end "element"



    // $ANTLR start "singleElement"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:114:1: singleElement returns [Statement s] : (e= exprTag | TEXT | NEWLINE | COMMENT );
    public final Statement singleElement() throws RecognitionException {
        Statement s = null;


        CommonToken TEXT1=null;
        CommonToken NEWLINE2=null;
        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:115:2: (e= exprTag | TEXT | NEWLINE | COMMENT )
            int alt4=4;
            switch ( input.LA(1) ) {
            case LDELIM:
                {
                alt4=1;
                }
                break;
            case TEXT:
                {
                alt4=2;
                }
                break;
            case NEWLINE:
                {
                alt4=3;
                }
                break;
            case COMMENT:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:115:4: e= exprTag
                    {
                    pushFollow(FOLLOW_exprTag_in_singleElement222);
                    e=exprTag();

                    state._fsp--;


                    s=c.stOutput(e);

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:116:4: TEXT
                    {
                    TEXT1=(CommonToken)match(input,TEXT,FOLLOW_TEXT_in_singleElement230); 

                    s=c.stOutput(c.opStringCst((TEXT1!=null?TEXT1.getText():null)));

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:117:4: NEWLINE
                    {
                    NEWLINE2=(CommonToken)match(input,NEWLINE,FOLLOW_NEWLINE_in_singleElement247); 

                    s=c.stOutput(c.opStringCst((NEWLINE2!=null?NEWLINE2.getText():null)));

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:118:4: COMMENT
                    {
                    match(input,COMMENT,FOLLOW_COMMENT_in_singleElement257); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:121:1: compoundElement : ( ifstat | region );
    public final void compoundElement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:122:2: ( ifstat | region )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==INDENT) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==LDELIM) ) {
                    int LA5_2 = input.LA(3);

                    if ( (LA5_2==IF) ) {
                        alt5=1;
                    }
                    else if ( (LA5_2==AT) ) {
                        alt5=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA5_0==LDELIM) ) {
                int LA5_2 = input.LA(2);

                if ( (LA5_2==IF) ) {
                    alt5=1;
                }
                else if ( (LA5_2==AT) ) {
                    alt5=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:122:4: ifstat
                    {
                    pushFollow(FOLLOW_ifstat_in_compoundElement269);
                    ifstat();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:123:4: region
                    {
                    pushFollow(FOLLOW_region_in_compoundElement274);
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
        return ;
    }
    // $ANTLR end "compoundElement"



    // $ANTLR start "exprTag"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:126:1: exprTag returns [Expr v] : LDELIM e= expr ( ';' exprOptions )? RDELIM ;
    public final Expr exprTag() throws RecognitionException {
        Expr v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:127:2: ( LDELIM e= expr ( ';' exprOptions )? RDELIM )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:127:4: LDELIM e= expr ( ';' exprOptions )? RDELIM
            {
            match(input,LDELIM,FOLLOW_LDELIM_in_exprTag288); 

            pushFollow(FOLLOW_expr_in_exprTag292);
            e=expr();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:127:18: ( ';' exprOptions )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==SEMI) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:127:20: ';' exprOptions
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_exprTag296); 

                    pushFollow(FOLLOW_exprOptions_in_exprTag298);
                    exprOptions();

                    state._fsp--;


                    }
                    break;

            }


            match(input,RDELIM,FOLLOW_RDELIM_in_exprTag303); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:131:1: region : (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )? ;
    public final SParser.region_return region() throws RecognitionException {
        SParser.region_return retval = new SParser.region_return();
        retval.start = input.LT(1);


        CommonToken i=null;
        CommonToken x=null;

        Token indent=null;
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:133:2: ( (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )? )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:133:4: (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )?
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:133:5: (i= INDENT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==INDENT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:133:5: i= INDENT
                    {
                    i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_region326); 

                    }
                    break;

            }


            x=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_region331); 

            match(input,AT,FOLLOW_AT_in_region333); 

            match(input,ID,FOLLOW_ID_in_region335); 

            match(input,RDELIM,FOLLOW_RDELIM_in_region337); 

            if (input.LA(1)!=NEWLINE) indent=i;

            pushFollow(FOLLOW_template_in_region343);
            template();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:135:3: ( INDENT )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==INDENT) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:135:3: INDENT
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_region347); 

                    }
                    break;

            }


            match(input,LDELIM,FOLLOW_LDELIM_in_region350); 

            match(input,END,FOLLOW_END_in_region352); 

            match(input,RDELIM,FOLLOW_RDELIM_in_region354); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:137:3: ({...}? => NEWLINE )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NEWLINE) ) {
                int LA9_1 = input.LA(2);

                if ( ((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                    alt9=1;
                }
            }
            switch (alt9) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:137:4: {...}? => NEWLINE
                    {
                    if ( !((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                        throw new FailedPredicateException(input, "region", "$region.start.getLine()!=input.LT(1).getLine()");
                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_region365); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:143:1: subtemplate : lc= '{' (ids+= ID ( ',' ids+= ID )* '|' )? template ( INDENT )? '}' ;
    public final void subtemplate() throws RecognitionException {
        CommonToken lc=null;
        CommonToken ids=null;
        List list_ids=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:144:2: (lc= '{' (ids+= ID ( ',' ids+= ID )* '|' )? template ( INDENT )? '}' )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:144:4: lc= '{' (ids+= ID ( ',' ids+= ID )* '|' )? template ( INDENT )? '}'
            {
            lc=(CommonToken)match(input,LCURLY,FOLLOW_LCURLY_in_subtemplate384); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:144:11: (ids+= ID ( ',' ids+= ID )* '|' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==ID) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:144:12: ids+= ID ( ',' ids+= ID )* '|'
                    {
                    ids=(CommonToken)match(input,ID,FOLLOW_ID_in_subtemplate390); 
                    if (list_ids==null) list_ids=new ArrayList();
                    list_ids.add(ids);


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:144:21: ( ',' ids+= ID )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==COMMA) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:144:23: ',' ids+= ID
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_subtemplate394); 

                    	    ids=(CommonToken)match(input,ID,FOLLOW_ID_in_subtemplate399); 
                    	    if (list_ids==null) list_ids=new ArrayList();
                    	    list_ids.add(ids);


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    match(input,PIPE,FOLLOW_PIPE_in_subtemplate404); 

                    }
                    break;

            }


            pushFollow(FOLLOW_template_in_subtemplate409);
            template();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:144:55: ( INDENT )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==INDENT) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:144:55: INDENT
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_subtemplate411); 

                    }
                    break;

            }


            match(input,RCURLY,FOLLOW_RCURLY_in_subtemplate414); 

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "subtemplate"


    public static class ifstat_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "ifstat"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:149:1: ifstat : (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= template ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )* ( ( INDENT )? LDELIM 'else' RDELIM t3= template )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )? ;
    public final SParser.ifstat_return ifstat() throws RecognitionException {
        SParser.ifstat_return retval = new SParser.ifstat_return();
        retval.start = input.LT(1);


        CommonToken i=null;
        CommonToken endif=null;
        TemplateImpl t1 =null;

        TemplateImpl t3 =null;


        Token indent=null;
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:151:2: ( (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= template ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )* ( ( INDENT )? LDELIM 'else' RDELIM t3= template )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )? )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:151:4: (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= template ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )* ( ( INDENT )? LDELIM 'else' RDELIM t3= template )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )?
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:151:5: (i= INDENT )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==INDENT) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:151:5: i= INDENT
                    {
                    i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_ifstat438); 

                    }
                    break;

            }


            match(input,LDELIM,FOLLOW_LDELIM_in_ifstat441); 

            match(input,IF,FOLLOW_IF_in_ifstat443); 

            match(input,LPAREN,FOLLOW_LPAREN_in_ifstat445); 

            pushFollow(FOLLOW_conditional_in_ifstat449);
            conditional();

            state._fsp--;


            match(input,RPAREN,FOLLOW_RPAREN_in_ifstat451); 

            match(input,RDELIM,FOLLOW_RDELIM_in_ifstat453); 

            if (input.LA(1)!=NEWLINE) indent=i;

            pushFollow(FOLLOW_template_in_ifstat462);
            t1=template();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:153:4: ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==INDENT) ) {
                    int LA15_1 = input.LA(2);

                    if ( (LA15_1==LDELIM) ) {
                        int LA15_2 = input.LA(3);

                        if ( (LA15_2==ELSEIF) ) {
                            alt15=1;
                        }


                    }


                }
                else if ( (LA15_0==LDELIM) ) {
                    int LA15_2 = input.LA(2);

                    if ( (LA15_2==ELSEIF) ) {
                        alt15=1;
                    }


                }


                switch (alt15) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:153:6: ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template
            	    {
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:153:6: ( INDENT )?
            	    int alt14=2;
            	    int LA14_0 = input.LA(1);

            	    if ( (LA14_0==INDENT) ) {
            	        alt14=1;
            	    }
            	    switch (alt14) {
            	        case 1 :
            	            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:153:6: INDENT
            	            {
            	            match(input,INDENT,FOLLOW_INDENT_in_ifstat469); 

            	            }
            	            break;

            	    }


            	    match(input,LDELIM,FOLLOW_LDELIM_in_ifstat472); 

            	    match(input,ELSEIF,FOLLOW_ELSEIF_in_ifstat474); 

            	    match(input,LPAREN,FOLLOW_LPAREN_in_ifstat476); 

            	    pushFollow(FOLLOW_conditional_in_ifstat478);
            	    conditional();

            	    state._fsp--;


            	    match(input,RPAREN,FOLLOW_RPAREN_in_ifstat480); 

            	    match(input,RDELIM,FOLLOW_RDELIM_in_ifstat482); 

            	    pushFollow(FOLLOW_template_in_ifstat484);
            	    template();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:154:4: ( ( INDENT )? LDELIM 'else' RDELIM t3= template )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==INDENT) ) {
                int LA17_1 = input.LA(2);

                if ( (LA17_1==LDELIM) ) {
                    int LA17_2 = input.LA(3);

                    if ( (LA17_2==ELSE) ) {
                        alt17=1;
                    }
                }
            }
            else if ( (LA17_0==LDELIM) ) {
                int LA17_2 = input.LA(2);

                if ( (LA17_2==ELSE) ) {
                    alt17=1;
                }
            }
            switch (alt17) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:154:6: ( INDENT )? LDELIM 'else' RDELIM t3= template
                    {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:154:6: ( INDENT )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==INDENT) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:154:6: INDENT
                            {
                            match(input,INDENT,FOLLOW_INDENT_in_ifstat494); 

                            }
                            break;

                    }


                    match(input,LDELIM,FOLLOW_LDELIM_in_ifstat497); 

                    match(input,ELSE,FOLLOW_ELSE_in_ifstat499); 

                    match(input,RDELIM,FOLLOW_RDELIM_in_ifstat501); 

                    pushFollow(FOLLOW_template_in_ifstat505);
                    t3=template();

                    state._fsp--;


                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:155:4: ( INDENT )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==INDENT) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:155:4: INDENT
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_ifstat513); 

                    }
                    break;

            }


            endif=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_ifstat519); 

            match(input,ENDIF,FOLLOW_ENDIF_in_ifstat521); 

            match(input,RDELIM,FOLLOW_RDELIM_in_ifstat525); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:158:3: ({...}? => NEWLINE )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==NEWLINE) ) {
                int LA19_1 = input.LA(2);

                if ( ((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                    alt19=1;
                }
            }
            switch (alt19) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:158:4: {...}? => NEWLINE
                    {
                    if ( !((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                        throw new FailedPredicateException(input, "ifstat", "$ifstat.start.getLine()!=input.LT(1).getLine()");
                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_ifstat536); 

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
    // $ANTLR end "ifstat"


    protected static class conditional_scope {
        boolean inside;
    }
    protected Stack conditional_stack = new Stack();



    // $ANTLR start "conditional"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:164:1: conditional : andConditional ( '||' andConditional )* ;
    public final void conditional() throws RecognitionException {
        conditional_stack.push(new conditional_scope());
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:168:2: ( andConditional ( '||' andConditional )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:168:4: andConditional ( '||' andConditional )*
            {
            pushFollow(FOLLOW_andConditional_in_conditional557);
            andConditional();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:168:19: ( '||' andConditional )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==OR) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:168:21: '||' andConditional
            	    {
            	    match(input,OR,FOLLOW_OR_in_conditional561); 

            	    pushFollow(FOLLOW_andConditional_in_conditional563);
            	    andConditional();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
            conditional_stack.pop();
        }
        return ;
    }
    // $ANTLR end "conditional"



    // $ANTLR start "andConditional"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:171:1: andConditional : notConditional ( '&&' notConditional )* ;
    public final void andConditional() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:171:16: ( notConditional ( '&&' notConditional )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:171:18: notConditional ( '&&' notConditional )*
            {
            pushFollow(FOLLOW_notConditional_in_andConditional576);
            notConditional();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:171:33: ( '&&' notConditional )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==AND) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:171:35: '&&' notConditional
            	    {
            	    match(input,AND,FOLLOW_AND_in_andConditional580); 

            	    pushFollow(FOLLOW_notConditional_in_andConditional582);
            	    notConditional();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
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
    // $ANTLR end "andConditional"



    // $ANTLR start "notConditional"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:173:1: notConditional : ( '!' notConditional | memberExpr );
    public final void notConditional() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:174:2: ( '!' notConditional | memberExpr )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==BANG) ) {
                alt22=1;
            }
            else if ( (LA22_0==SUPER||LA22_0==LBRACK||LA22_0==LCURLY||(LA22_0 >= ID && LA22_0 <= STRING)||LA22_0==AT||(LA22_0 >= TRUE && LA22_0 <= FALSE)) ) {
                alt22=2;
            }
            else if ( (LA22_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }
            switch (alt22) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:174:4: '!' notConditional
                    {
                    match(input,BANG,FOLLOW_BANG_in_notConditional595); 

                    pushFollow(FOLLOW_notConditional_in_notConditional597);
                    notConditional();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:175:4: memberExpr
                    {
                    pushFollow(FOLLOW_memberExpr_in_notConditional602);
                    memberExpr();

                    state._fsp--;


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
    // $ANTLR end "notConditional"



    // $ANTLR start "notConditionalExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:178:1: notConditionalExpr : ( ID ) (p= '.' prop= ID |p= '.' '(' mapExpr ')' )* ;
    public final void notConditionalExpr() throws RecognitionException {
        CommonToken p=null;
        CommonToken prop=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:179:2: ( ( ID ) (p= '.' prop= ID |p= '.' '(' mapExpr ')' )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:179:4: ( ID ) (p= '.' prop= ID |p= '.' '(' mapExpr ')' )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:179:4: ( ID )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:179:5: ID
            {
            match(input,ID,FOLLOW_ID_in_notConditionalExpr614); 

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:180:3: (p= '.' prop= ID |p= '.' '(' mapExpr ')' )*
            loop23:
            do {
                int alt23=3;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==DOT) ) {
                    int LA23_2 = input.LA(2);

                    if ( (LA23_2==ID) ) {
                        alt23=1;
                    }
                    else if ( (LA23_2==LPAREN) ) {
                        alt23=2;
                    }


                }


                switch (alt23) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:180:5: p= '.' prop= ID
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_notConditionalExpr624); 

            	    prop=(CommonToken)match(input,ID,FOLLOW_ID_in_notConditionalExpr628); 

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:181:5: p= '.' '(' mapExpr ')'
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_notConditionalExpr642); 

            	    match(input,LPAREN,FOLLOW_LPAREN_in_notConditionalExpr644); 

            	    pushFollow(FOLLOW_mapExpr_in_notConditionalExpr646);
            	    mapExpr();

            	    state._fsp--;


            	    match(input,RPAREN,FOLLOW_RPAREN_in_notConditionalExpr648); 

            	    }
            	    break;

            	default :
            	    break loop23;
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
    // $ANTLR end "notConditionalExpr"



    // $ANTLR start "exprOptions"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:185:1: exprOptions : option ( ',' option )* ;
    public final void exprOptions() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:185:13: ( option ( ',' option )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:185:15: option ( ',' option )*
            {
            pushFollow(FOLLOW_option_in_exprOptions667);
            option();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:185:22: ( ',' option )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==COMMA) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:185:24: ',' option
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_exprOptions671); 

            	    pushFollow(FOLLOW_option_in_exprOptions673);
            	    option();

            	    state._fsp--;


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
        }
        return ;
    }
    // $ANTLR end "exprOptions"



    // $ANTLR start "option"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:187:1: option : ID ( '=' exprNoComma |) ;
    public final void option() throws RecognitionException {
        CommonToken ID3=null;


        	//String id = input.LT(1).getText();
        	//String defVal = Compiler.defaultOptionValues.get(id);
        	//boolean validOption = Compiler.supportedOptions.get(id)!=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:193:2: ( ID ( '=' exprNoComma |) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:193:4: ID ( '=' exprNoComma |)
            {
            ID3=(CommonToken)match(input,ID,FOLLOW_ID_in_option692); 


            		//if ( !validOption ) {
                        //errMgr.compileTimeError(ErrorType.NO_SUCH_OPTION, templateToken, ID3, (ID3!=null?ID3.getText():null));
            		//}
            		

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:199:3: ( '=' exprNoComma |)
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==EQUALS) ) {
                alt25=1;
            }
            else if ( (LA25_0==COMMA||LA25_0==RDELIM) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }
            switch (alt25) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:199:5: '=' exprNoComma
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_option702); 

                    pushFollow(FOLLOW_exprNoComma_in_option704);
                    exprNoComma();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:201:5: 
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:212:1: exprNoComma returns [Expr v] : memberExpr ( ':' mapTemplateRef |) ;
    public final Expr exprNoComma() throws RecognitionException {
        Expr v = null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:213:2: ( memberExpr ( ':' mapTemplateRef |) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:213:4: memberExpr ( ':' mapTemplateRef |)
            {
            pushFollow(FOLLOW_memberExpr_in_exprNoComma761);
            memberExpr();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:214:3: ( ':' mapTemplateRef |)
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==COLON) ) {
                alt26=1;
            }
            else if ( (LA26_0==RPAREN||(LA26_0 >= RBRACK && LA26_0 <= COMMA)||LA26_0==RDELIM) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }
            switch (alt26) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:214:5: ':' mapTemplateRef
                    {
                    match(input,COLON,FOLLOW_COLON_in_exprNoComma767); 

                    pushFollow(FOLLOW_mapTemplateRef_in_exprNoComma769);
                    mapTemplateRef();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:216:3: 
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:219:1: expr returns [Expr v] : e= mapExpr ;
    public final Expr expr() throws RecognitionException {
        Expr v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:219:23: (e= mapExpr )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:219:25: e= mapExpr
            {
            pushFollow(FOLLOW_mapExpr_in_expr808);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:223:1: mapExpr returns [Expr v] : me= memberExpr ( (c= ',' memberExpr )+ col= ':' mapTemplateRef |) (col= ':' mapTemplateRef ({...}? => ',' mapTemplateRef )* )* ;
    public final Expr mapExpr() throws RecognitionException {
        Expr v = null;


        CommonToken c=null;
        CommonToken col=null;
        Expr me =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:224:2: (me= memberExpr ( (c= ',' memberExpr )+ col= ':' mapTemplateRef |) (col= ':' mapTemplateRef ({...}? => ',' mapTemplateRef )* )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:224:4: me= memberExpr ( (c= ',' memberExpr )+ col= ':' mapTemplateRef |) (col= ':' mapTemplateRef ({...}? => ',' mapTemplateRef )* )*
            {
            pushFollow(FOLLOW_memberExpr_in_mapExpr828);
            me=memberExpr();

            state._fsp--;


            v=me;

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:225:3: ( (c= ',' memberExpr )+ col= ':' mapTemplateRef |)
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==COMMA) ) {
                alt28=1;
            }
            else if ( (LA28_0==SEMI||LA28_0==COLON||LA28_0==RPAREN||LA28_0==RDELIM) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;

            }
            switch (alt28) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:225:5: (c= ',' memberExpr )+ col= ':' mapTemplateRef
                    {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:225:5: (c= ',' memberExpr )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==COMMA) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:225:6: c= ',' memberExpr
                    	    {
                    	    c=(CommonToken)match(input,COMMA,FOLLOW_COMMA_in_mapExpr839); 

                    	    pushFollow(FOLLOW_memberExpr_in_mapExpr841);
                    	    memberExpr();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt27 >= 1 ) break loop27;
                                EarlyExitException eee =
                                    new EarlyExitException(27, input);
                                throw eee;
                        }
                        cnt27++;
                    } while (true);


                    col=(CommonToken)match(input,COLON,FOLLOW_COLON_in_mapExpr847); 

                    pushFollow(FOLLOW_mapTemplateRef_in_mapExpr849);
                    mapTemplateRef();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:228:3: 
                    {
                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:229:3: (col= ':' mapTemplateRef ({...}? => ',' mapTemplateRef )* )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==COLON) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:230:4: col= ':' mapTemplateRef ({...}? => ',' mapTemplateRef )*
            	    {
            	    col=(CommonToken)match(input,COLON,FOLLOW_COLON_in_mapExpr892); 

            	    pushFollow(FOLLOW_mapTemplateRef_in_mapExpr894);
            	    mapTemplateRef();

            	    state._fsp--;


            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:230:27: ({...}? => ',' mapTemplateRef )*
            	    loop29:
            	    do {
            	        int alt29=2;
            	        int LA29_0 = input.LA(1);

            	        if ( (LA29_0==COMMA) && ((c==null))) {
            	            alt29=1;
            	        }


            	        switch (alt29) {
            	    	case 1 :
            	    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:230:28: {...}? => ',' mapTemplateRef
            	    	    {
            	    	    if ( !((c==null)) ) {
            	    	        throw new FailedPredicateException(input, "mapExpr", "$c==null");
            	    	    }

            	    	    match(input,COMMA,FOLLOW_COMMA_in_mapExpr900); 

            	    	    pushFollow(FOLLOW_mapTemplateRef_in_mapExpr902);
            	    	    mapTemplateRef();

            	    	    state._fsp--;


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop29;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop30;
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:240:1: mapTemplateRef : ( ID '(' args ')' | subtemplate |lp= '(' mapExpr rp= ')' '(' ( argExprList )? ')' );
    public final void mapTemplateRef() throws RecognitionException {
        CommonToken lp=null;
        CommonToken rp=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:241:2: ( ID '(' args ')' | subtemplate |lp= '(' mapExpr rp= ')' '(' ( argExprList )? ')' )
            int alt32=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt32=1;
                }
                break;
            case LCURLY:
                {
                alt32=2;
                }
                break;
            case LPAREN:
                {
                alt32=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }

            switch (alt32) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:241:4: ID '(' args ')'
                    {
                    match(input,ID,FOLLOW_ID_in_mapTemplateRef936); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef938); 

                    pushFollow(FOLLOW_args_in_mapTemplateRef940);
                    args();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef942); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:242:4: subtemplate
                    {
                    pushFollow(FOLLOW_subtemplate_in_mapTemplateRef954);
                    subtemplate();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:243:4: lp= '(' mapExpr rp= ')' '(' ( argExprList )? ')'
                    {
                    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef961); 

                    pushFollow(FOLLOW_mapExpr_in_mapTemplateRef963);
                    mapExpr();

                    state._fsp--;


                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef967); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef969); 

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:243:30: ( argExprList )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==SUPER||LA31_0==LBRACK||LA31_0==LCURLY||(LA31_0 >= ID && LA31_0 <= STRING)||LA31_0==AT||(LA31_0 >= TRUE && LA31_0 <= FALSE)) ) {
                        alt31=1;
                    }
                    else if ( (LA31_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:243:30: argExprList
                            {
                            pushFollow(FOLLOW_argExprList_in_mapTemplateRef971);
                            argExprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef974); 

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
    // $ANTLR end "mapTemplateRef"



    // $ANTLR start "memberExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:246:1: memberExpr returns [Expr v] : (ie= includeExpr ) (p= '.' ID |p= '.' '(' mapExpr ')' )* ;
    public final Expr memberExpr() throws RecognitionException {
        Expr v = null;


        CommonToken p=null;
        CommonToken ID4=null;
        Expr ie =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:247:2: ( (ie= includeExpr ) (p= '.' ID |p= '.' '(' mapExpr ')' )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:247:4: (ie= includeExpr ) (p= '.' ID |p= '.' '(' mapExpr ')' )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:247:4: (ie= includeExpr )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:247:5: ie= includeExpr
            {
            pushFollow(FOLLOW_includeExpr_in_memberExpr994);
            ie=includeExpr();

            state._fsp--;


            v=ie;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:248:3: (p= '.' ID |p= '.' '(' mapExpr ')' )*
            loop33:
            do {
                int alt33=3;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==DOT) ) {
                    int LA33_2 = input.LA(2);

                    if ( (LA33_2==ID) ) {
                        alt33=1;
                    }
                    else if ( (LA33_2==LPAREN) ) {
                        alt33=2;
                    }


                }


                switch (alt33) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:248:5: p= '.' ID
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_memberExpr1006); 

            	    ID4=(CommonToken)match(input,ID,FOLLOW_ID_in_memberExpr1008); 

            	    v=c.opFieldOf(v,(ID4!=null?ID4.getText():null));

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:249:5: p= '.' '(' mapExpr ')'
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_memberExpr1024); 

            	    match(input,LPAREN,FOLLOW_LPAREN_in_memberExpr1026); 

            	    pushFollow(FOLLOW_mapExpr_in_memberExpr1028);
            	    mapExpr();

            	    state._fsp--;


            	    match(input,RPAREN,FOLLOW_RPAREN_in_memberExpr1030); 

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
    // $ANTLR end "memberExpr"



    // $ANTLR start "includeExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:253:1: includeExpr returns [Expr v] options {k=2; } : ({...}? ID '(' ( expr )? ')' | 'super' '.' ID '(' args ')' | ID '(' as= args ')' | '@' 'super' '.' ID '(' rp= ')' | '@' ID '(' rp= ')' |p= primary );
    public final Expr includeExpr() throws RecognitionException {
        Expr v = null;


        CommonToken rp=null;
        CommonToken ID5=null;
        List<Argument> as =null;

        Expr p =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:255:2: ({...}? ID '(' ( expr )? ')' | 'super' '.' ID '(' args ')' | ID '(' as= args ')' | '@' 'super' '.' ID '(' rp= ')' | '@' ID '(' rp= ')' |p= primary )
            int alt35=6;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==ID) ) {
                int LA35_1 = input.LA(2);

                if ( (LA35_1==LPAREN) ) {
                    int LA35_10 = input.LA(3);

                    if ( ((Compiler.funcs.containsKey(input.LT(1).getText()))) ) {
                        alt35=1;
                    }
                    else if ( (true) ) {
                        alt35=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 35, 10, input);

                        throw nvae;

                    }
                }
                else if ( (LA35_1==SEMI||LA35_1==COLON||LA35_1==RPAREN||(LA35_1 >= RBRACK && LA35_1 <= DOT)||LA35_1==RDELIM||(LA35_1 >= OR && LA35_1 <= AND)) ) {
                    alt35=6;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA35_0==SUPER) ) {
                alt35=2;
            }
            else if ( (LA35_0==AT) ) {
                int LA35_3 = input.LA(2);

                if ( (LA35_3==SUPER) ) {
                    alt35=4;
                }
                else if ( (LA35_3==ID) ) {
                    alt35=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA35_0==LBRACK||LA35_0==LCURLY||LA35_0==STRING||(LA35_0 >= TRUE && LA35_0 <= FALSE)) ) {
                alt35=6;
            }
            else if ( (LA35_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt35=6;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }
            switch (alt35) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:255:4: {...}? ID '(' ( expr )? ')'
                    {
                    if ( !((Compiler.funcs.containsKey(input.LT(1).getText()))) ) {
                        throw new FailedPredicateException(input, "includeExpr", "Compiler.funcs.containsKey(input.LT(1).getText())");
                    }

                    match(input,ID,FOLLOW_ID_in_includeExpr1068); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1070); 

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:256:10: ( expr )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==SUPER||LA34_0==LBRACK||LA34_0==LCURLY||(LA34_0 >= ID && LA34_0 <= STRING)||LA34_0==AT||(LA34_0 >= TRUE && LA34_0 <= FALSE)) ) {
                        alt34=1;
                    }
                    else if ( (LA34_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:256:10: expr
                            {
                            pushFollow(FOLLOW_expr_in_includeExpr1072);
                            expr();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1075); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:257:4: 'super' '.' ID '(' args ')'
                    {
                    match(input,SUPER,FOLLOW_SUPER_in_includeExpr1086); 

                    match(input,DOT,FOLLOW_DOT_in_includeExpr1088); 

                    match(input,ID,FOLLOW_ID_in_includeExpr1090); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1092); 

                    pushFollow(FOLLOW_args_in_includeExpr1094);
                    args();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1096); 

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:258:4: ID '(' as= args ')'
                    {
                    ID5=(CommonToken)match(input,ID,FOLLOW_ID_in_includeExpr1105); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1107); 

                    pushFollow(FOLLOW_args_in_includeExpr1111);
                    as=args();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1113); 

                    v=c.opInclude((ID5!=null?ID5.getText():null),as);

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:259:4: '@' 'super' '.' ID '(' rp= ')'
                    {
                    match(input,AT,FOLLOW_AT_in_includeExpr1126); 

                    match(input,SUPER,FOLLOW_SUPER_in_includeExpr1128); 

                    match(input,DOT,FOLLOW_DOT_in_includeExpr1130); 

                    match(input,ID,FOLLOW_ID_in_includeExpr1132); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1134); 

                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1138); 

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:260:4: '@' ID '(' rp= ')'
                    {
                    match(input,AT,FOLLOW_AT_in_includeExpr1146); 

                    match(input,ID,FOLLOW_ID_in_includeExpr1148); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1150); 

                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1154); 

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:261:4: p= primary
                    {
                    pushFollow(FOLLOW_primary_in_includeExpr1167);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:264:1: primary returns [Expr v] : ( ID | STRING | TRUE | FALSE | subtemplate | list |{...}? => '(' conditional ')' |{...}? =>lp= '(' expr ')' ( '(' ( argExprList )? ')' |) );
    public final Expr primary() throws RecognitionException {
        Expr v = null;


        CommonToken lp=null;
        CommonToken ID6=null;
        CommonToken STRING7=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:265:2: ( ID | STRING | TRUE | FALSE | subtemplate | list |{...}? => '(' conditional ')' |{...}? =>lp= '(' expr ')' ( '(' ( argExprList )? ')' |) )
            int alt38=8;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==ID) ) {
                alt38=1;
            }
            else if ( (LA38_0==STRING) ) {
                alt38=2;
            }
            else if ( (LA38_0==TRUE) ) {
                alt38=3;
            }
            else if ( (LA38_0==FALSE) ) {
                alt38=4;
            }
            else if ( (LA38_0==LCURLY) ) {
                alt38=5;
            }
            else if ( (LA38_0==LBRACK) ) {
                alt38=6;
            }
            else if ( (LA38_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                int LA38_7 = input.LA(2);

                if ( ((conditional_stack.size()>0)) ) {
                    alt38=7;
                }
                else if ( ((conditional_stack.size()==0)) ) {
                    alt38=8;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 7, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:265:4: ID
                    {
                    ID6=(CommonToken)match(input,ID,FOLLOW_ID_in_primary1184); 

                    v=c.opArg(argv,arg((ID6!=null?ID6.getText():null)));

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:266:4: STRING
                    {
                    STRING7=(CommonToken)match(input,STRING,FOLLOW_STRING_in_primary1201); 

                    v=c.opStringCst((STRING7!=null?STRING7.getText():null));

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:267:4: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_primary1210); 

                    v=c.opYesnoCst(true);

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:268:4: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_primary1225); 

                    v=c.opYesnoCst(false);

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:269:4: subtemplate
                    {
                    pushFollow(FOLLOW_subtemplate_in_primary1237);
                    subtemplate();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:270:4: list
                    {
                    pushFollow(FOLLOW_list_in_primary1242);
                    list();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:271:4: {...}? => '(' conditional ')'
                    {
                    if ( !((conditional_stack.size()>0)) ) {
                        throw new FailedPredicateException(input, "primary", "$conditional.size()>0");
                    }

                    match(input,LPAREN,FOLLOW_LPAREN_in_primary1251); 

                    pushFollow(FOLLOW_conditional_in_primary1253);
                    conditional();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_primary1255); 

                    }
                    break;
                case 8 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:272:4: {...}? =>lp= '(' expr ')' ( '(' ( argExprList )? ')' |)
                    {
                    if ( !((conditional_stack.size()==0)) ) {
                        throw new FailedPredicateException(input, "primary", "$conditional.size()==0");
                    }

                    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_primary1265); 

                    pushFollow(FOLLOW_expr_in_primary1267);
                    expr();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_primary1269); 

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:273:3: ( '(' ( argExprList )? ')' |)
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==LPAREN) ) {
                        alt37=1;
                    }
                    else if ( (LA37_0==SEMI||LA37_0==COLON||LA37_0==RPAREN||(LA37_0 >= RBRACK && LA37_0 <= DOT)||LA37_0==RDELIM||(LA37_0 >= OR && LA37_0 <= AND)) ) {
                        alt37=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 0, input);

                        throw nvae;

                    }
                    switch (alt37) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:273:5: '(' ( argExprList )? ')'
                            {
                            match(input,LPAREN,FOLLOW_LPAREN_in_primary1275); 

                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:273:9: ( argExprList )?
                            int alt36=2;
                            int LA36_0 = input.LA(1);

                            if ( (LA36_0==SUPER||LA36_0==LBRACK||LA36_0==LCURLY||(LA36_0 >= ID && LA36_0 <= STRING)||LA36_0==AT||(LA36_0 >= TRUE && LA36_0 <= FALSE)) ) {
                                alt36=1;
                            }
                            else if ( (LA36_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                                alt36=1;
                            }
                            switch (alt36) {
                                case 1 :
                                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:273:9: argExprList
                                    {
                                    pushFollow(FOLLOW_argExprList_in_primary1277);
                                    argExprList();

                                    state._fsp--;


                                    }
                                    break;

                            }


                            match(input,RPAREN,FOLLOW_RPAREN_in_primary1280); 

                            }
                            break;
                        case 2 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:275:3: 
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:278:1: args returns [List<Argument> args] : (as= argExprList |a= namedArg ( ',' a= namedArg )* ( ',' '...' )? | '...' |);
    public final List<Argument> args() throws RecognitionException {
        List<Argument> args = null;


        List<Argument> as =null;

        Argument a =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:279:3: (as= argExprList |a= namedArg ( ',' a= namedArg )* ( ',' '...' )? | '...' |)
            int alt41=4;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==ID) ) {
                int LA41_1 = input.LA(2);

                if ( ((LA41_1 >= COLON && LA41_1 <= RPAREN)||(LA41_1 >= COMMA && LA41_1 <= DOT)) ) {
                    alt41=1;
                }
                else if ( (LA41_1==EQUALS) ) {
                    alt41=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 41, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA41_0==SUPER||LA41_0==LBRACK||LA41_0==LCURLY||LA41_0==STRING||LA41_0==AT||(LA41_0 >= TRUE && LA41_0 <= FALSE)) ) {
                alt41=1;
            }
            else if ( (LA41_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt41=1;
            }
            else if ( (LA41_0==ELLIPSIS) ) {
                alt41=3;
            }
            else if ( (LA41_0==RPAREN) ) {
                alt41=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }
            switch (alt41) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:279:5: as= argExprList
                    {
                    pushFollow(FOLLOW_argExprList_in_args1325);
                    as=argExprList();

                    state._fsp--;


                    args=as;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:280:4: a= namedArg ( ',' a= namedArg )* ( ',' '...' )?
                    {
                    pushFollow(FOLLOW_namedArg_in_args1334);
                    a=namedArg();

                    state._fsp--;


                    c.opAddArgument(args,a);

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:280:41: ( ',' a= namedArg )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==COMMA) ) {
                            int LA39_1 = input.LA(2);

                            if ( (LA39_1==ID) ) {
                                alt39=1;
                            }


                        }


                        switch (alt39) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:280:43: ',' a= namedArg
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_args1339); 

                    	    pushFollow(FOLLOW_namedArg_in_args1343);
                    	    a=namedArg();

                    	    state._fsp--;


                    	    c.opAddArgument(args,a);

                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:280:87: ( ',' '...' )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==COMMA) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:280:88: ',' '...'
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_args1350); 

                            match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_args1352); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:281:9: '...'
                    {
                    match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_args1365); 

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:283:2: 
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:285:1: argExprList returns [List<Argument> args] : a= arg ( ',' a= arg )* ;
    public final List<Argument> argExprList() throws RecognitionException {
        List<Argument> args = null;


        Argument a =null;



          args = new ArrayList();

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:289:3: (a= arg ( ',' a= arg )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:289:3: a= arg ( ',' a= arg )*
            {
            pushFollow(FOLLOW_arg_in_argExprList1389);
            a=arg();

            state._fsp--;


            c.opAddArgument(args,a);

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:289:35: ( ',' a= arg )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==COMMA) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:289:37: ',' a= arg
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_argExprList1394); 

            	    pushFollow(FOLLOW_arg_in_argExprList1398);
            	    a=arg();

            	    state._fsp--;


            	    c.opAddArgument(args,a);

            	    }
            	    break;

            	default :
            	    break loop42;
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:291:1: arg returns [Argument v] : e= exprNoComma ;
    public final Argument arg() throws RecognitionException {
        Argument v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:291:27: (e= exprNoComma )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:291:29: e= exprNoComma
            {
            pushFollow(FOLLOW_exprNoComma_in_arg1419);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:293:1: namedArg returns [Argument v] : ID '=' a= arg ;
    public final Argument namedArg() throws RecognitionException {
        Argument v = null;


        CommonToken ID8=null;
        Argument a =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:293:32: ( ID '=' a= arg )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:293:34: ID '=' a= arg
            {
            ID8=(CommonToken)match(input,ID,FOLLOW_ID_in_namedArg1435); 

            match(input,EQUALS,FOLLOW_EQUALS_in_namedArg1437); 

            pushFollow(FOLLOW_arg_in_namedArg1441);
            a=arg();

            state._fsp--;


            v=c.opArgument((ID8!=null?ID8.getText():null),a);

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:295:1: list : ({...}?lb= '[' ']' |lb= '[' listElement ( ',' listElement )* ']' );
    public final void list() throws RecognitionException {
        CommonToken lb=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:295:5: ({...}?lb= '[' ']' |lb= '[' listElement ( ',' listElement )* ']' )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==LBRACK) ) {
                int LA44_1 = input.LA(2);

                if ( (LA44_1==RBRACK) ) {
                    int LA44_2 = input.LA(3);

                    if ( ((input.LA(2)==RBRACK)) ) {
                        alt44=1;
                    }
                    else if ( (true) ) {
                        alt44=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 44, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA44_1==SUPER||LA44_1==LPAREN||LA44_1==LBRACK||LA44_1==COMMA||LA44_1==LCURLY||(LA44_1 >= ID && LA44_1 <= STRING)||LA44_1==AT||(LA44_1 >= TRUE && LA44_1 <= FALSE)) ) {
                    alt44=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 44, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;

            }
            switch (alt44) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:295:7: {...}?lb= '[' ']'
                    {
                    if ( !((input.LA(2)==RBRACK)) ) {
                        throw new FailedPredicateException(input, "list", "input.LA(2)==RBRACK");
                    }

                    lb=(CommonToken)match(input,LBRACK,FOLLOW_LBRACK_in_list1458); 

                    match(input,RBRACK,FOLLOW_RBRACK_in_list1460); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:297:4: lb= '[' listElement ( ',' listElement )* ']'
                    {
                    lb=(CommonToken)match(input,LBRACK,FOLLOW_LBRACK_in_list1468); 

                    pushFollow(FOLLOW_listElement_in_list1470);
                    listElement();

                    state._fsp--;


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:297:23: ( ',' listElement )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==COMMA) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:297:25: ',' listElement
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_list1474); 

                    	    pushFollow(FOLLOW_listElement_in_list1476);
                    	    listElement();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);


                    match(input,RBRACK,FOLLOW_RBRACK_in_list1481); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:300:1: listElement returns [Expr v] : (e= exprNoComma |);
    public final Expr listElement() throws RecognitionException {
        Expr v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:300:29: (e= exprNoComma |)
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==SUPER||LA45_0==LBRACK||LA45_0==LCURLY||(LA45_0 >= ID && LA45_0 <= STRING)||LA45_0==AT||(LA45_0 >= TRUE && LA45_0 <= FALSE)) ) {
                alt45=1;
            }
            else if ( (LA45_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt45=1;
            }
            else if ( ((LA45_0 >= RBRACK && LA45_0 <= COMMA)) ) {
                alt45=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }
            switch (alt45) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:300:31: e= exprNoComma
                    {
                    pushFollow(FOLLOW_exprNoComma_in_listElement1497);
                    e=exprNoComma();

                    state._fsp--;


                    v=e;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:300:66: 
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


 

    public static final BitSet FOLLOW_template_in_templateAndEOF128 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_templateAndEOF131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_template155 = new BitSet(new long[]{0x0000002180C00002L});
    public static final BitSet FOLLOW_INDENT_in_element176 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_COMMENT_in_element179 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_NEWLINE_in_element181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_element187 = new BitSet(new long[]{0x0000002100C00000L});
    public static final BitSet FOLLOW_singleElement_in_element191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_singleElement_in_element200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compoundElement_in_element206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprTag_in_singleElement222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_singleElement230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_singleElement247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMENT_in_singleElement257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifstat_in_compoundElement269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_region_in_compoundElement274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LDELIM_in_exprTag288 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_expr_in_exprTag292 = new BitSet(new long[]{0x0000000001000200L});
    public static final BitSet FOLLOW_SEMI_in_exprTag296 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_exprOptions_in_exprTag298 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_exprTag303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_region326 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_region331 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_AT_in_region333 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_region335 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_region337 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_region343 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_region347 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_region350 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_END_in_region352 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_region354 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_NEWLINE_in_region365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_subtemplate384 = new BitSet(new long[]{0x0000002182E00000L});
    public static final BitSet FOLLOW_ID_in_subtemplate390 = new BitSet(new long[]{0x0000000010040000L});
    public static final BitSet FOLLOW_COMMA_in_subtemplate394 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_subtemplate399 = new BitSet(new long[]{0x0000000010040000L});
    public static final BitSet FOLLOW_PIPE_in_subtemplate404 = new BitSet(new long[]{0x0000002180E00000L});
    public static final BitSet FOLLOW_template_in_subtemplate409 = new BitSet(new long[]{0x0000000080200000L});
    public static final BitSet FOLLOW_INDENT_in_subtemplate411 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_RCURLY_in_subtemplate414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_ifstat438 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat441 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IF_in_ifstat443 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_ifstat445 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_conditional_in_ifstat449 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_ifstat451 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat453 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat462 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat469 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat472 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ELSEIF_in_ifstat474 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_ifstat476 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_conditional_in_ifstat478 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_ifstat480 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat482 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat484 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat494 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat497 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ELSE_in_ifstat499 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat501 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat505 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat513 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat519 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ENDIF_in_ifstat521 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat525 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ifstat536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andConditional_in_conditional557 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_OR_in_conditional561 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_andConditional_in_conditional563 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_notConditional_in_andConditional576 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_AND_in_andConditional580 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_notConditional_in_andConditional582 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_BANG_in_notConditional595 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_notConditional_in_notConditional597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_notConditional602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_notConditionalExpr614 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_notConditionalExpr624 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_notConditionalExpr628 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_notConditionalExpr642 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_notConditionalExpr644 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_mapExpr_in_notConditionalExpr646 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_notConditionalExpr648 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_option_in_exprOptions667 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_exprOptions671 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_option_in_exprOptions673 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ID_in_option692 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_EQUALS_in_option702 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_exprNoComma_in_option704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_exprNoComma761 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COLON_in_exprNoComma767 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_exprNoComma769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mapExpr_in_expr808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_mapExpr828 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_COMMA_in_mapExpr839 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_memberExpr_in_mapExpr841 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_COLON_in_mapExpr847 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr849 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COLON_in_mapExpr892 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr894 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_COMMA_in_mapExpr900 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr902 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_ID_in_mapTemplateRef936 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef938 = new BitSet(new long[]{0x0000001A0611C900L});
    public static final BitSet FOLLOW_args_in_mapTemplateRef940 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subtemplate_in_mapTemplateRef954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef961 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_mapExpr_in_mapTemplateRef963 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef967 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef969 = new BitSet(new long[]{0x0000001A0611C100L});
    public static final BitSet FOLLOW_argExprList_in_mapTemplateRef971 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_includeExpr_in_memberExpr994 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_memberExpr1006 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_memberExpr1008 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_memberExpr1024 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_memberExpr1026 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_mapExpr_in_memberExpr1028 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_memberExpr1030 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_ID_in_includeExpr1068 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1070 = new BitSet(new long[]{0x0000001A0611C100L});
    public static final BitSet FOLLOW_expr_in_includeExpr1072 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_includeExpr1086 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_includeExpr1088 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_includeExpr1090 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1092 = new BitSet(new long[]{0x0000001A0611C900L});
    public static final BitSet FOLLOW_args_in_includeExpr1094 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_includeExpr1105 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1107 = new BitSet(new long[]{0x0000001A0611C900L});
    public static final BitSet FOLLOW_args_in_includeExpr1111 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_includeExpr1126 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_SUPER_in_includeExpr1128 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_includeExpr1130 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_includeExpr1132 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1134 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_includeExpr1146 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_includeExpr1148 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1150 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_includeExpr1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_primary1201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_primary1210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_primary1225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subtemplate_in_primary1237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_primary1242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1251 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_conditional_in_primary1253 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1265 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_expr_in_primary1267 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1269 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1275 = new BitSet(new long[]{0x0000001A0611C100L});
    public static final BitSet FOLLOW_argExprList_in_primary1277 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_argExprList_in_args1325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_namedArg_in_args1334 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_args1339 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_namedArg_in_args1343 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_args1350 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ELLIPSIS_in_args1352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELLIPSIS_in_args1365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_in_argExprList1389 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_argExprList1394 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_arg_in_argExprList1398 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_exprNoComma_in_arg1419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_namedArg1435 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_EQUALS_in_namedArg1437 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_arg_in_namedArg1441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_list1458 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACK_in_list1460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_list1468 = new BitSet(new long[]{0x0000001A06174100L});
    public static final BitSet FOLLOW_listElement_in_list1470 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_COMMA_in_list1474 = new BitSet(new long[]{0x0000001A06174100L});
    public static final BitSet FOLLOW_listElement_in_list1476 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_RBRACK_in_list1481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprNoComma_in_listElement1497 = new BitSet(new long[]{0x0000000000000002L});

}