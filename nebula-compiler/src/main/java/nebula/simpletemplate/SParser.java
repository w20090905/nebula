// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g 2013-11-05 09:56:06

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
            pushLocal("group",Type.getType(StringBuilder.class));
            pushLocal("template",Type.getType(StringBuilder.class));
            pushLocal("sb",Type.getType(StringBuilder.class));
    //				pushLocal("object",Type.getType(Object.class));
            pushLocal("argv",Type.getType(Object.class));
          }
    			
    			private List<Var> arges = new ArrayList<Var>();
    			
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:98:1: templateAndEOF returns [TemplateImpl temp] : t= template EOF ;
    public final TemplateImpl templateAndEOF() throws RecognitionException {
        TemplateImpl temp = null;


        TemplateImpl t =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:99:6: (t= template EOF )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:99:8: t= template EOF
            {
            pushFollow(FOLLOW_template_in_templateAndEOF133);
            t=template();

            state._fsp--;


            temp=t;

            match(input,EOF,FOLLOW_EOF_in_templateAndEOF136); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:101:1: template returns [TemplateImpl temp] : (e= element )* ;
    public final TemplateImpl template() throws RecognitionException {
        TemplateImpl temp = null;


        Statement e =null;



              initLocals();
              List<Statement> statments = new ArrayList<Statement>();
            
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:106:3: ( (e= element )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:106:5: (e= element )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:106:5: (e= element )*
            loop1:
            do {
                int alt1=2;
                switch ( input.LA(1) ) {
                case INDENT:
                    {
                    int LA1_2 = input.LA(2);

                    if ( (LA1_2==LDELIM) ) {
                        int LA1_5 = input.LA(3);

                        if ( (LA1_5==IF||LA1_5==LPAREN||LA1_5==LBRACK||LA1_5==LCURLY||(LA1_5 >= ID && LA1_5 <= STRING)||LA1_5==AT||(LA1_5 >= TRUE && LA1_5 <= FALSE)) ) {
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

                    if ( (LA1_3==IF||LA1_3==LPAREN||LA1_3==LBRACK||LA1_3==LCURLY||(LA1_3 >= ID && LA1_3 <= STRING)||LA1_3==AT||(LA1_3 >= TRUE && LA1_3 <= FALSE)) ) {
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
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:106:6: e= element
            	    {
            	    pushFollow(FOLLOW_element_in_template160);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:108:1: element returns [Statement s] : ({...}? ( INDENT )? COMMENT NEWLINE | INDENT se= singleElement |se= singleElement | compoundElement );
    public final Statement element() throws RecognitionException {
        Statement s = null;


        Statement se =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:109:2: ({...}? ( INDENT )? COMMENT NEWLINE | INDENT se= singleElement |se= singleElement | compoundElement )
            int alt3=4;
            switch ( input.LA(1) ) {
            case INDENT:
                {
                switch ( input.LA(2) ) {
                case COMMENT:
                    {
                    int LA3_5 = input.LA(3);

                    if ( (LA3_5==NEWLINE) ) {
                        int LA3_10 = input.LA(4);

                        if ( ((input.LT(1).getCharPositionInLine()==0)) ) {
                            alt3=1;
                        }
                        else if ( (true) ) {
                            alt3=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 10, input);

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
                    int LA3_6 = input.LA(3);

                    if ( (LA3_6==IF||LA3_6==AT) ) {
                        alt3=4;
                    }
                    else if ( (LA3_6==LPAREN||LA3_6==LBRACK||LA3_6==LCURLY||(LA3_6 >= ID && LA3_6 <= STRING)||(LA3_6 >= TRUE && LA3_6 <= FALSE)) ) {
                        alt3=2;
                    }
                    else {
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
                int LA3_3 = input.LA(2);

                if ( (LA3_3==IF||LA3_3==AT) ) {
                    alt3=4;
                }
                else if ( (LA3_3==LPAREN||LA3_3==LBRACK||LA3_3==LCURLY||(LA3_3 >= ID && LA3_3 <= STRING)||(LA3_3 >= TRUE && LA3_3 <= FALSE)) ) {
                    alt3=3;
                }
                else {
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
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:109:4: {...}? ( INDENT )? COMMENT NEWLINE
                    {
                    if ( !((input.LT(1).getCharPositionInLine()==0)) ) {
                        throw new FailedPredicateException(input, "element", "input.LT(1).getCharPositionInLine()==0");
                    }

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:109:46: ( INDENT )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==INDENT) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:109:46: INDENT
                            {
                            match(input,INDENT,FOLLOW_INDENT_in_element181); 

                            }
                            break;

                    }


                    match(input,COMMENT,FOLLOW_COMMENT_in_element184); 

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_element186); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:110:4: INDENT se= singleElement
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_element192); 

                    pushFollow(FOLLOW_singleElement_in_element196);
                    se=singleElement();

                    state._fsp--;


                    s=se;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:111:4: se= singleElement
                    {
                    pushFollow(FOLLOW_singleElement_in_element205);
                    se=singleElement();

                    state._fsp--;


                    s=se;

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:112:4: compoundElement
                    {
                    pushFollow(FOLLOW_compoundElement_in_element211);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:115:1: singleElement returns [Statement s] : (e= exprTag | TEXT | NEWLINE | COMMENT );
    public final Statement singleElement() throws RecognitionException {
        Statement s = null;


        CommonToken TEXT1=null;
        CommonToken NEWLINE2=null;
        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:116:2: (e= exprTag | TEXT | NEWLINE | COMMENT )
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
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:116:4: e= exprTag
                    {
                    pushFollow(FOLLOW_exprTag_in_singleElement227);
                    e=exprTag();

                    state._fsp--;


                    s=c.stOutput(c.opLocal(v("sb")),e);

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:117:4: TEXT
                    {
                    TEXT1=(CommonToken)match(input,TEXT,FOLLOW_TEXT_in_singleElement235); 

                    s=c.stOutput(c.opLocal(v("sb")),c.opStringCst((TEXT1!=null?TEXT1.getText():null)));

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:118:4: NEWLINE
                    {
                    NEWLINE2=(CommonToken)match(input,NEWLINE,FOLLOW_NEWLINE_in_singleElement252); 

                    s=c.stOutput(c.opLocal(v("sb")),c.opStringCst((NEWLINE2!=null?NEWLINE2.getText():null)));

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:119:4: COMMENT
                    {
                    match(input,COMMENT,FOLLOW_COMMENT_in_singleElement262); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:122:1: compoundElement : ( ifstat | region );
    public final void compoundElement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:123:2: ( ifstat | region )
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
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:123:4: ifstat
                    {
                    pushFollow(FOLLOW_ifstat_in_compoundElement274);
                    ifstat();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:124:4: region
                    {
                    pushFollow(FOLLOW_region_in_compoundElement279);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:127:1: exprTag returns [Expr v] : LDELIM e= expr ( ';' exprOptions )? RDELIM ;
    public final Expr exprTag() throws RecognitionException {
        Expr v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:128:2: ( LDELIM e= expr ( ';' exprOptions )? RDELIM )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:128:4: LDELIM e= expr ( ';' exprOptions )? RDELIM
            {
            match(input,LDELIM,FOLLOW_LDELIM_in_exprTag293); 

            pushFollow(FOLLOW_expr_in_exprTag297);
            e=expr();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:128:18: ( ';' exprOptions )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==SEMI) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:128:20: ';' exprOptions
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_exprTag301); 

                    pushFollow(FOLLOW_exprOptions_in_exprTag303);
                    exprOptions();

                    state._fsp--;


                    }
                    break;

            }


            match(input,RDELIM,FOLLOW_RDELIM_in_exprTag308); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:132:1: region : (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )? ;
    public final SParser.region_return region() throws RecognitionException {
        SParser.region_return retval = new SParser.region_return();
        retval.start = input.LT(1);


        CommonToken i=null;
        CommonToken x=null;

        Token indent=null;
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:134:2: ( (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )? )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:134:4: (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )?
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:134:5: (i= INDENT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==INDENT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:134:5: i= INDENT
                    {
                    i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_region331); 

                    }
                    break;

            }


            x=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_region336); 

            match(input,AT,FOLLOW_AT_in_region338); 

            match(input,ID,FOLLOW_ID_in_region340); 

            match(input,RDELIM,FOLLOW_RDELIM_in_region342); 

            if (input.LA(1)!=NEWLINE) indent=i;

            pushFollow(FOLLOW_template_in_region348);
            template();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:136:3: ( INDENT )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==INDENT) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:136:3: INDENT
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_region352); 

                    }
                    break;

            }


            match(input,LDELIM,FOLLOW_LDELIM_in_region355); 

            match(input,END,FOLLOW_END_in_region357); 

            match(input,RDELIM,FOLLOW_RDELIM_in_region359); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:138:3: ({...}? => NEWLINE )?
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
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:138:4: {...}? => NEWLINE
                    {
                    if ( !((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                        throw new FailedPredicateException(input, "region", "$region.start.getLine()!=input.LT(1).getLine()");
                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_region370); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:144:1: subtemplate returns [TemplateImpl temp] : lc= '{' (id= ID ( ',' id= ID )* '|' )? t= template ( INDENT )? '}' ;
    public final TemplateImpl subtemplate() throws RecognitionException {
        TemplateImpl temp = null;


        CommonToken lc=null;
        CommonToken id=null;
        TemplateImpl t =null;



            List<Var> outterArges =arges;
            arges= new ArrayList<Var>();
         
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:149:2: (lc= '{' (id= ID ( ',' id= ID )* '|' )? t= template ( INDENT )? '}' )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:149:4: lc= '{' (id= ID ( ',' id= ID )* '|' )? t= template ( INDENT )? '}'
            {
            lc=(CommonToken)match(input,LCURLY,FOLLOW_LCURLY_in_subtemplate398); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:149:11: (id= ID ( ',' id= ID )* '|' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==ID) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:149:12: id= ID ( ',' id= ID )* '|'
                    {
                    id=(CommonToken)match(input,ID,FOLLOW_ID_in_subtemplate403); 

                    arg((id!=null?id.getText():null));

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:149:35: ( ',' id= ID )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==COMMA) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:149:37: ',' id= ID
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_subtemplate409); 

                    	    id=(CommonToken)match(input,ID,FOLLOW_ID_in_subtemplate413); 

                    	    arg((id!=null?id.getText():null));

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    match(input,PIPE,FOLLOW_PIPE_in_subtemplate419); 

                    }
                    break;

            }


            pushFollow(FOLLOW_template_in_subtemplate426);
            t=template();

            state._fsp--;


            temp=t;

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:149:94: ( INDENT )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==INDENT) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:149:94: INDENT
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_subtemplate430); 

                    }
                    break;

            }


            	
            	  arges = outterArges;
            	

            match(input,RCURLY,FOLLOW_RCURLY_in_subtemplate438); 

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return temp;
    }
    // $ANTLR end "subtemplate"


    public static class ifstat_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "ifstat"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:158:1: ifstat : (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= template ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )* ( ( INDENT )? LDELIM 'else' RDELIM t3= template )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )? ;
    public final SParser.ifstat_return ifstat() throws RecognitionException {
        SParser.ifstat_return retval = new SParser.ifstat_return();
        retval.start = input.LT(1);


        CommonToken i=null;
        CommonToken endif=null;
        TemplateImpl t1 =null;

        TemplateImpl t3 =null;


        Token indent=null;
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:160:2: ( (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= template ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )* ( ( INDENT )? LDELIM 'else' RDELIM t3= template )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )? )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:160:4: (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= template ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )* ( ( INDENT )? LDELIM 'else' RDELIM t3= template )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )?
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:160:5: (i= INDENT )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==INDENT) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:160:5: i= INDENT
                    {
                    i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_ifstat462); 

                    }
                    break;

            }


            match(input,LDELIM,FOLLOW_LDELIM_in_ifstat465); 

            match(input,IF,FOLLOW_IF_in_ifstat467); 

            match(input,LPAREN,FOLLOW_LPAREN_in_ifstat469); 

            pushFollow(FOLLOW_conditional_in_ifstat473);
            conditional();

            state._fsp--;


            match(input,RPAREN,FOLLOW_RPAREN_in_ifstat475); 

            match(input,RDELIM,FOLLOW_RDELIM_in_ifstat477); 

            if (input.LA(1)!=NEWLINE) indent=i;

            pushFollow(FOLLOW_template_in_ifstat486);
            t1=template();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:162:4: ( ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template )*
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
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:162:6: ( INDENT )? LDELIM 'elseif' '(' conditional ')' RDELIM template
            	    {
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:162:6: ( INDENT )?
            	    int alt14=2;
            	    int LA14_0 = input.LA(1);

            	    if ( (LA14_0==INDENT) ) {
            	        alt14=1;
            	    }
            	    switch (alt14) {
            	        case 1 :
            	            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:162:6: INDENT
            	            {
            	            match(input,INDENT,FOLLOW_INDENT_in_ifstat493); 

            	            }
            	            break;

            	    }


            	    match(input,LDELIM,FOLLOW_LDELIM_in_ifstat496); 

            	    match(input,ELSEIF,FOLLOW_ELSEIF_in_ifstat498); 

            	    match(input,LPAREN,FOLLOW_LPAREN_in_ifstat500); 

            	    pushFollow(FOLLOW_conditional_in_ifstat502);
            	    conditional();

            	    state._fsp--;


            	    match(input,RPAREN,FOLLOW_RPAREN_in_ifstat504); 

            	    match(input,RDELIM,FOLLOW_RDELIM_in_ifstat506); 

            	    pushFollow(FOLLOW_template_in_ifstat508);
            	    template();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:163:4: ( ( INDENT )? LDELIM 'else' RDELIM t3= template )?
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
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:163:6: ( INDENT )? LDELIM 'else' RDELIM t3= template
                    {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:163:6: ( INDENT )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==INDENT) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:163:6: INDENT
                            {
                            match(input,INDENT,FOLLOW_INDENT_in_ifstat518); 

                            }
                            break;

                    }


                    match(input,LDELIM,FOLLOW_LDELIM_in_ifstat521); 

                    match(input,ELSE,FOLLOW_ELSE_in_ifstat523); 

                    match(input,RDELIM,FOLLOW_RDELIM_in_ifstat525); 

                    pushFollow(FOLLOW_template_in_ifstat529);
                    t3=template();

                    state._fsp--;


                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:164:4: ( INDENT )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==INDENT) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:164:4: INDENT
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_ifstat537); 

                    }
                    break;

            }


            endif=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_ifstat543); 

            match(input,ENDIF,FOLLOW_ENDIF_in_ifstat545); 

            match(input,RDELIM,FOLLOW_RDELIM_in_ifstat549); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:167:3: ({...}? => NEWLINE )?
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
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:167:4: {...}? => NEWLINE
                    {
                    if ( !((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                        throw new FailedPredicateException(input, "ifstat", "$ifstat.start.getLine()!=input.LT(1).getLine()");
                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_ifstat560); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:173:1: conditional : andConditional ( '||' andConditional )* ;
    public final void conditional() throws RecognitionException {
        conditional_stack.push(new conditional_scope());
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:177:2: ( andConditional ( '||' andConditional )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:177:4: andConditional ( '||' andConditional )*
            {
            pushFollow(FOLLOW_andConditional_in_conditional581);
            andConditional();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:177:19: ( '||' andConditional )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==OR) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:177:21: '||' andConditional
            	    {
            	    match(input,OR,FOLLOW_OR_in_conditional585); 

            	    pushFollow(FOLLOW_andConditional_in_conditional587);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:180:1: andConditional : notConditional ( '&&' notConditional )* ;
    public final void andConditional() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:180:16: ( notConditional ( '&&' notConditional )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:180:18: notConditional ( '&&' notConditional )*
            {
            pushFollow(FOLLOW_notConditional_in_andConditional600);
            notConditional();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:180:33: ( '&&' notConditional )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==AND) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:180:35: '&&' notConditional
            	    {
            	    match(input,AND,FOLLOW_AND_in_andConditional604); 

            	    pushFollow(FOLLOW_notConditional_in_andConditional606);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:182:1: notConditional : ( '!' notConditional | memberExpr );
    public final void notConditional() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:183:2: ( '!' notConditional | memberExpr )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==BANG) ) {
                alt22=1;
            }
            else if ( (LA22_0==LBRACK||LA22_0==LCURLY||(LA22_0 >= ID && LA22_0 <= STRING)||(LA22_0 >= TRUE && LA22_0 <= FALSE)) ) {
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
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:183:4: '!' notConditional
                    {
                    match(input,BANG,FOLLOW_BANG_in_notConditional619); 

                    pushFollow(FOLLOW_notConditional_in_notConditional621);
                    notConditional();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:184:4: memberExpr
                    {
                    pushFollow(FOLLOW_memberExpr_in_notConditional626);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:187:1: notConditionalExpr : ( ID ) (p= '.' prop= ID |p= '.' '(' mapExpr ')' )* ;
    public final void notConditionalExpr() throws RecognitionException {
        CommonToken p=null;
        CommonToken prop=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:188:2: ( ( ID ) (p= '.' prop= ID |p= '.' '(' mapExpr ')' )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:188:4: ( ID ) (p= '.' prop= ID |p= '.' '(' mapExpr ')' )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:188:4: ( ID )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:188:5: ID
            {
            match(input,ID,FOLLOW_ID_in_notConditionalExpr638); 

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:189:3: (p= '.' prop= ID |p= '.' '(' mapExpr ')' )*
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
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:189:5: p= '.' prop= ID
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_notConditionalExpr648); 

            	    prop=(CommonToken)match(input,ID,FOLLOW_ID_in_notConditionalExpr652); 

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:190:5: p= '.' '(' mapExpr ')'
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_notConditionalExpr666); 

            	    match(input,LPAREN,FOLLOW_LPAREN_in_notConditionalExpr668); 

            	    pushFollow(FOLLOW_mapExpr_in_notConditionalExpr670);
            	    mapExpr();

            	    state._fsp--;


            	    match(input,RPAREN,FOLLOW_RPAREN_in_notConditionalExpr672); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:194:1: exprOptions : option ( ',' option )* ;
    public final void exprOptions() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:194:13: ( option ( ',' option )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:194:15: option ( ',' option )*
            {
            pushFollow(FOLLOW_option_in_exprOptions691);
            option();

            state._fsp--;


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:194:22: ( ',' option )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==COMMA) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:194:24: ',' option
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_exprOptions695); 

            	    pushFollow(FOLLOW_option_in_exprOptions697);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:196:1: option : ID ( '=' exprNoComma |) ;
    public final void option() throws RecognitionException {
        CommonToken ID3=null;


        	//String id = input.LT(1).getText();
        	//String defVal = Compiler.defaultOptionValues.get(id);
        	//boolean validOption = Compiler.supportedOptions.get(id)!=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:202:2: ( ID ( '=' exprNoComma |) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:202:4: ID ( '=' exprNoComma |)
            {
            ID3=(CommonToken)match(input,ID,FOLLOW_ID_in_option716); 


            		//if ( !validOption ) {
                        //errMgr.compileTimeError(ErrorType.NO_SUCH_OPTION, templateToken, ID3, (ID3!=null?ID3.getText():null));
            		//}
            		

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:208:3: ( '=' exprNoComma |)
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
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:208:5: '=' exprNoComma
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_option726); 

                    pushFollow(FOLLOW_exprNoComma_in_option728);
                    exprNoComma();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:210:5: 
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:221:1: exprNoComma returns [Expr v] : m= memberExpr ( ':' mt= mapTemplateRef[v] |) ;
    public final Expr exprNoComma() throws RecognitionException {
        Expr v = null;


        Expr m =null;

        Expr mt =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:222:2: (m= memberExpr ( ':' mt= mapTemplateRef[v] |) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:222:4: m= memberExpr ( ':' mt= mapTemplateRef[v] |)
            {
            pushFollow(FOLLOW_memberExpr_in_exprNoComma787);
            m=memberExpr();

            state._fsp--;


            v=m;

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:223:3: ( ':' mt= mapTemplateRef[v] |)
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
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:223:5: ':' mt= mapTemplateRef[v]
                    {
                    match(input,COLON,FOLLOW_COLON_in_exprNoComma795); 

                    pushFollow(FOLLOW_mapTemplateRef_in_exprNoComma799);
                    mt=mapTemplateRef(v);

                    state._fsp--;


                    v=mt;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:225:3: 
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:228:1: expr returns [Expr v] : e= mapExpr ;
    public final Expr expr() throws RecognitionException {
        Expr v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:228:23: (e= mapExpr )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:228:25: e= mapExpr
            {
            pushFollow(FOLLOW_mapExpr_in_expr840);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:232:1: mapExpr returns [Expr v] : me= memberExpr (col= ':' mt= mapTemplateRef[v] )* ;
    public final Expr mapExpr() throws RecognitionException {
        Expr v = null;


        CommonToken col=null;
        Expr me =null;

        Expr mt =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:233:2: (me= memberExpr (col= ':' mt= mapTemplateRef[v] )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:233:4: me= memberExpr (col= ':' mt= mapTemplateRef[v] )*
            {
            pushFollow(FOLLOW_memberExpr_in_mapExpr860);
            me=memberExpr();

            state._fsp--;


            v=me;

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:238:3: (col= ':' mt= mapTemplateRef[v] )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==COLON) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:239:4: col= ':' mt= mapTemplateRef[v]
            	    {
            	    col=(CommonToken)match(input,COLON,FOLLOW_COLON_in_mapExpr878); 

            	    pushFollow(FOLLOW_mapTemplateRef_in_mapExpr882);
            	    mt=mapTemplateRef(v);

            	    state._fsp--;


            	    v=mt;

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
        return v;
    }
    // $ANTLR end "mapExpr"



    // $ANTLR start "mapTemplateRef"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:249:1: mapTemplateRef[ Expr data] returns [Expr v] : ( ID '(' as= args ')' | subtemplate |lp= '(' me= mapExpr rp= ')' '(' (as= argExprList )? ')' );
    public final Expr mapTemplateRef(Expr data) throws RecognitionException {
        Expr v = null;


        CommonToken lp=null;
        CommonToken rp=null;
        CommonToken ID4=null;
        List<Argument> as =null;

        Expr me =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:250:2: ( ID '(' as= args ')' | subtemplate |lp= '(' me= mapExpr rp= ')' '(' (as= argExprList )? ')' )
            int alt29=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt29=1;
                }
                break;
            case LCURLY:
                {
                alt29=2;
                }
                break;
            case LPAREN:
                {
                alt29=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }

            switch (alt29) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:250:4: ID '(' as= args ')'
                    {
                    ID4=(CommonToken)match(input,ID,FOLLOW_ID_in_mapTemplateRef923); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef925); 

                    pushFollow(FOLLOW_args_in_mapTemplateRef929);
                    as=args();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef931); 

                    v=c.opInclude(c.opLocal(v("group")),c.opStringCst((ID4!=null?ID4.getText():null)),data,as);

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:251:4: subtemplate
                    {
                    pushFollow(FOLLOW_subtemplate_in_mapTemplateRef944);
                    subtemplate();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:252:4: lp= '(' me= mapExpr rp= ')' '(' (as= argExprList )? ')'
                    {
                    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef951); 

                    pushFollow(FOLLOW_mapExpr_in_mapTemplateRef955);
                    me=mapExpr();

                    state._fsp--;


                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef959); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef961); 

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:252:35: (as= argExprList )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==LBRACK||LA28_0==LCURLY||(LA28_0 >= ID && LA28_0 <= STRING)||(LA28_0 >= TRUE && LA28_0 <= FALSE)) ) {
                        alt28=1;
                    }
                    else if ( (LA28_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:252:35: as= argExprList
                            {
                            pushFollow(FOLLOW_argExprList_in_mapTemplateRef965);
                            as=argExprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef968); 

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



    // $ANTLR start "memberExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:255:1: memberExpr returns [Expr v] : (ie= includeExpr ) (p= '.' ID |p= '.' '(' mapExpr ')' )* ;
    public final Expr memberExpr() throws RecognitionException {
        Expr v = null;


        CommonToken p=null;
        CommonToken ID5=null;
        Expr ie =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:256:2: ( (ie= includeExpr ) (p= '.' ID |p= '.' '(' mapExpr ')' )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:256:4: (ie= includeExpr ) (p= '.' ID |p= '.' '(' mapExpr ')' )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:256:4: (ie= includeExpr )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:256:5: ie= includeExpr
            {
            pushFollow(FOLLOW_includeExpr_in_memberExpr989);
            ie=includeExpr();

            state._fsp--;


            v=ie;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:257:3: (p= '.' ID |p= '.' '(' mapExpr ')' )*
            loop30:
            do {
                int alt30=3;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==DOT) ) {
                    int LA30_2 = input.LA(2);

                    if ( (LA30_2==ID) ) {
                        alt30=1;
                    }
                    else if ( (LA30_2==LPAREN) ) {
                        alt30=2;
                    }


                }


                switch (alt30) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:257:5: p= '.' ID
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_memberExpr1001); 

            	    ID5=(CommonToken)match(input,ID,FOLLOW_ID_in_memberExpr1003); 

            	    v=c.opFieldOf(v,(ID5!=null?ID5.getText():null));

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:258:5: p= '.' '(' mapExpr ')'
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_memberExpr1019); 

            	    match(input,LPAREN,FOLLOW_LPAREN_in_memberExpr1021); 

            	    pushFollow(FOLLOW_mapExpr_in_memberExpr1023);
            	    mapExpr();

            	    state._fsp--;


            	    match(input,RPAREN,FOLLOW_RPAREN_in_memberExpr1025); 

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
    // $ANTLR end "memberExpr"



    // $ANTLR start "includeExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:262:1: includeExpr returns [Expr v] options {k=2; } : ({...}? ID '(' ( expr )? ')' | ID '(' as= args ')' |p= primary );
    public final Expr includeExpr() throws RecognitionException {
        Expr v = null;


        CommonToken ID6=null;
        List<Argument> as =null;

        Expr p =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:264:2: ({...}? ID '(' ( expr )? ')' | ID '(' as= args ')' |p= primary )
            int alt32=3;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==ID) ) {
                int LA32_1 = input.LA(2);

                if ( (LA32_1==LPAREN) ) {
                    int LA32_8 = input.LA(3);

                    if ( ((Compiler.funcs.containsKey(input.LT(1).getText()))) ) {
                        alt32=1;
                    }
                    else if ( (true) ) {
                        alt32=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 32, 8, input);

                        throw nvae;

                    }
                }
                else if ( (LA32_1==SEMI||LA32_1==COLON||LA32_1==RPAREN||(LA32_1 >= RBRACK && LA32_1 <= DOT)||LA32_1==RDELIM||(LA32_1 >= OR && LA32_1 <= AND)) ) {
                    alt32=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 32, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA32_0==LBRACK||LA32_0==LCURLY||LA32_0==STRING||(LA32_0 >= TRUE && LA32_0 <= FALSE)) ) {
                alt32=3;
            }
            else if ( (LA32_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt32=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }
            switch (alt32) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:264:4: {...}? ID '(' ( expr )? ')'
                    {
                    if ( !((Compiler.funcs.containsKey(input.LT(1).getText()))) ) {
                        throw new FailedPredicateException(input, "includeExpr", "Compiler.funcs.containsKey(input.LT(1).getText())");
                    }

                    match(input,ID,FOLLOW_ID_in_includeExpr1063); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1065); 

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:265:10: ( expr )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==LBRACK||LA31_0==LCURLY||(LA31_0 >= ID && LA31_0 <= STRING)||(LA31_0 >= TRUE && LA31_0 <= FALSE)) ) {
                        alt31=1;
                    }
                    else if ( (LA31_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:265:10: expr
                            {
                            pushFollow(FOLLOW_expr_in_includeExpr1067);
                            expr();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1070); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:267:4: ID '(' as= args ')'
                    {
                    ID6=(CommonToken)match(input,ID,FOLLOW_ID_in_includeExpr1082); 

                    match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1084); 

                    pushFollow(FOLLOW_args_in_includeExpr1088);
                    as=args();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1090); 

                    v=c.opInclude(c.opLocal(v("group")),c.opStringCst((ID6!=null?ID6.getText():null)),as);

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:270:4: p= primary
                    {
                    pushFollow(FOLLOW_primary_in_includeExpr1107);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:273:1: primary returns [Expr v] : ( ID | STRING | TRUE | FALSE | subtemplate | list |{...}? => '(' conditional ')' |{...}? =>lp= '(' expr ')' ( '(' ( argExprList )? ')' |) );
    public final Expr primary() throws RecognitionException {
        Expr v = null;


        CommonToken lp=null;
        CommonToken ID7=null;
        CommonToken STRING8=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:274:2: ( ID | STRING | TRUE | FALSE | subtemplate | list |{...}? => '(' conditional ')' |{...}? =>lp= '(' expr ')' ( '(' ( argExprList )? ')' |) )
            int alt35=8;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==ID) ) {
                alt35=1;
            }
            else if ( (LA35_0==STRING) ) {
                alt35=2;
            }
            else if ( (LA35_0==TRUE) ) {
                alt35=3;
            }
            else if ( (LA35_0==FALSE) ) {
                alt35=4;
            }
            else if ( (LA35_0==LCURLY) ) {
                alt35=5;
            }
            else if ( (LA35_0==LBRACK) ) {
                alt35=6;
            }
            else if ( (LA35_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                int LA35_7 = input.LA(2);

                if ( ((conditional_stack.size()>0)) ) {
                    alt35=7;
                }
                else if ( ((conditional_stack.size()==0)) ) {
                    alt35=8;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 7, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }
            switch (alt35) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:274:4: ID
                    {
                    ID7=(CommonToken)match(input,ID,FOLLOW_ID_in_primary1124); 

                    v=c.opArg(v("argv"),arg((ID7!=null?ID7.getText():null)));

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:275:4: STRING
                    {
                    STRING8=(CommonToken)match(input,STRING,FOLLOW_STRING_in_primary1141); 

                    v=c.opStringCst((STRING8!=null?STRING8.getText():null));

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:276:4: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_primary1150); 

                    v=c.opYesnoCst(true);

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:277:4: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_primary1165); 

                    v=c.opYesnoCst(false);

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:278:4: subtemplate
                    {
                    pushFollow(FOLLOW_subtemplate_in_primary1177);
                    subtemplate();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:279:4: list
                    {
                    pushFollow(FOLLOW_list_in_primary1182);
                    list();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:280:4: {...}? => '(' conditional ')'
                    {
                    if ( !((conditional_stack.size()>0)) ) {
                        throw new FailedPredicateException(input, "primary", "$conditional.size()>0");
                    }

                    match(input,LPAREN,FOLLOW_LPAREN_in_primary1191); 

                    pushFollow(FOLLOW_conditional_in_primary1193);
                    conditional();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_primary1195); 

                    }
                    break;
                case 8 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:281:4: {...}? =>lp= '(' expr ')' ( '(' ( argExprList )? ')' |)
                    {
                    if ( !((conditional_stack.size()==0)) ) {
                        throw new FailedPredicateException(input, "primary", "$conditional.size()==0");
                    }

                    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_primary1205); 

                    pushFollow(FOLLOW_expr_in_primary1207);
                    expr();

                    state._fsp--;


                    match(input,RPAREN,FOLLOW_RPAREN_in_primary1209); 

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:282:3: ( '(' ( argExprList )? ')' |)
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==LPAREN) ) {
                        alt34=1;
                    }
                    else if ( (LA34_0==SEMI||LA34_0==COLON||LA34_0==RPAREN||(LA34_0 >= RBRACK && LA34_0 <= DOT)||LA34_0==RDELIM||(LA34_0 >= OR && LA34_0 <= AND)) ) {
                        alt34=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 34, 0, input);

                        throw nvae;

                    }
                    switch (alt34) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:282:5: '(' ( argExprList )? ')'
                            {
                            match(input,LPAREN,FOLLOW_LPAREN_in_primary1215); 

                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:282:9: ( argExprList )?
                            int alt33=2;
                            int LA33_0 = input.LA(1);

                            if ( (LA33_0==LBRACK||LA33_0==LCURLY||(LA33_0 >= ID && LA33_0 <= STRING)||(LA33_0 >= TRUE && LA33_0 <= FALSE)) ) {
                                alt33=1;
                            }
                            else if ( (LA33_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                                alt33=1;
                            }
                            switch (alt33) {
                                case 1 :
                                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:282:9: argExprList
                                    {
                                    pushFollow(FOLLOW_argExprList_in_primary1217);
                                    argExprList();

                                    state._fsp--;


                                    }
                                    break;

                            }


                            match(input,RPAREN,FOLLOW_RPAREN_in_primary1220); 

                            }
                            break;
                        case 2 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:284:3: 
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:287:1: args returns [List<Argument> args] : (as= argExprList |a= namedArg ( ',' a= namedArg )* ( ',' '...' )? | '...' |);
    public final List<Argument> args() throws RecognitionException {
        List<Argument> args = null;


        List<Argument> as =null;

        Argument a =null;



          args = new ArrayList();

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:291:3: (as= argExprList |a= namedArg ( ',' a= namedArg )* ( ',' '...' )? | '...' |)
            int alt38=4;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==ID) ) {
                int LA38_1 = input.LA(2);

                if ( ((LA38_1 >= COLON && LA38_1 <= RPAREN)||(LA38_1 >= COMMA && LA38_1 <= DOT)) ) {
                    alt38=1;
                }
                else if ( (LA38_1==EQUALS) ) {
                    alt38=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA38_0==LBRACK||LA38_0==LCURLY||LA38_0==STRING||(LA38_0 >= TRUE && LA38_0 <= FALSE)) ) {
                alt38=1;
            }
            else if ( (LA38_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt38=1;
            }
            else if ( (LA38_0==ELLIPSIS) ) {
                alt38=3;
            }
            else if ( (LA38_0==RPAREN) ) {
                alt38=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:291:5: as= argExprList
                    {
                    pushFollow(FOLLOW_argExprList_in_args1269);
                    as=argExprList();

                    state._fsp--;


                    args=as;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:292:4: a= namedArg ( ',' a= namedArg )* ( ',' '...' )?
                    {
                    pushFollow(FOLLOW_namedArg_in_args1278);
                    a=namedArg();

                    state._fsp--;


                    c.opAddArgument(args,a);

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:292:41: ( ',' a= namedArg )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==COMMA) ) {
                            int LA36_1 = input.LA(2);

                            if ( (LA36_1==ID) ) {
                                alt36=1;
                            }


                        }


                        switch (alt36) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:292:43: ',' a= namedArg
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_args1283); 

                    	    pushFollow(FOLLOW_namedArg_in_args1287);
                    	    a=namedArg();

                    	    state._fsp--;


                    	    c.opAddArgument(args,a);

                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:292:87: ( ',' '...' )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==COMMA) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:292:88: ',' '...'
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_args1294); 

                            match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_args1296); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:293:9: '...'
                    {
                    match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_args1309); 

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:295:2: 
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:297:1: argExprList returns [List<Argument> args] : a= arg ( ',' a= arg )* ;
    public final List<Argument> argExprList() throws RecognitionException {
        List<Argument> args = null;


        Argument a =null;



          args = new ArrayList();

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:301:3: (a= arg ( ',' a= arg )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:301:3: a= arg ( ',' a= arg )*
            {
            pushFollow(FOLLOW_arg_in_argExprList1333);
            a=arg();

            state._fsp--;


            c.opAddArgument(args,a);

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:301:35: ( ',' a= arg )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==COMMA) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:301:37: ',' a= arg
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_argExprList1338); 

            	    pushFollow(FOLLOW_arg_in_argExprList1342);
            	    a=arg();

            	    state._fsp--;


            	    c.opAddArgument(args,a);

            	    }
            	    break;

            	default :
            	    break loop39;
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:303:1: arg returns [Argument v] : e= exprNoComma ;
    public final Argument arg() throws RecognitionException {
        Argument v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:303:27: (e= exprNoComma )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:303:29: e= exprNoComma
            {
            pushFollow(FOLLOW_exprNoComma_in_arg1363);
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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:305:1: namedArg returns [Argument v] : ID '=' a= arg ;
    public final Argument namedArg() throws RecognitionException {
        Argument v = null;


        CommonToken ID9=null;
        Argument a =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:305:32: ( ID '=' a= arg )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:305:34: ID '=' a= arg
            {
            ID9=(CommonToken)match(input,ID,FOLLOW_ID_in_namedArg1379); 

            match(input,EQUALS,FOLLOW_EQUALS_in_namedArg1381); 

            pushFollow(FOLLOW_arg_in_namedArg1385);
            a=arg();

            state._fsp--;


            v=c.opArgument((ID9!=null?ID9.getText():null),a);

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:307:1: list : ({...}?lb= '[' ']' |lb= '[' listElement ( ',' listElement )* ']' );
    public final void list() throws RecognitionException {
        CommonToken lb=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:307:5: ({...}?lb= '[' ']' |lb= '[' listElement ( ',' listElement )* ']' )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==LBRACK) ) {
                int LA41_1 = input.LA(2);

                if ( (LA41_1==RBRACK) ) {
                    int LA41_2 = input.LA(3);

                    if ( ((input.LA(2)==RBRACK)) ) {
                        alt41=1;
                    }
                    else if ( (true) ) {
                        alt41=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA41_1==LPAREN||LA41_1==LBRACK||LA41_1==COMMA||LA41_1==LCURLY||(LA41_1 >= ID && LA41_1 <= STRING)||(LA41_1 >= TRUE && LA41_1 <= FALSE)) ) {
                    alt41=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 41, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }
            switch (alt41) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:307:7: {...}?lb= '[' ']'
                    {
                    if ( !((input.LA(2)==RBRACK)) ) {
                        throw new FailedPredicateException(input, "list", "input.LA(2)==RBRACK");
                    }

                    lb=(CommonToken)match(input,LBRACK,FOLLOW_LBRACK_in_list1402); 

                    match(input,RBRACK,FOLLOW_RBRACK_in_list1404); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:309:4: lb= '[' listElement ( ',' listElement )* ']'
                    {
                    lb=(CommonToken)match(input,LBRACK,FOLLOW_LBRACK_in_list1412); 

                    pushFollow(FOLLOW_listElement_in_list1414);
                    listElement();

                    state._fsp--;


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:309:23: ( ',' listElement )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==COMMA) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:309:25: ',' listElement
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_list1418); 

                    	    pushFollow(FOLLOW_listElement_in_list1420);
                    	    listElement();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop40;
                        }
                    } while (true);


                    match(input,RBRACK,FOLLOW_RBRACK_in_list1425); 

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:312:1: listElement returns [Expr v] : (e= exprNoComma |);
    public final Expr listElement() throws RecognitionException {
        Expr v = null;


        Expr e =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:312:29: (e= exprNoComma |)
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==LBRACK||LA42_0==LCURLY||(LA42_0 >= ID && LA42_0 <= STRING)||(LA42_0 >= TRUE && LA42_0 <= FALSE)) ) {
                alt42=1;
            }
            else if ( (LA42_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt42=1;
            }
            else if ( ((LA42_0 >= RBRACK && LA42_0 <= COMMA)) ) {
                alt42=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }
            switch (alt42) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:312:31: e= exprNoComma
                    {
                    pushFollow(FOLLOW_exprNoComma_in_listElement1441);
                    e=exprNoComma();

                    state._fsp--;


                    v=e;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:312:66: 
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


 

    public static final BitSet FOLLOW_template_in_templateAndEOF133 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_templateAndEOF136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_template160 = new BitSet(new long[]{0x0000002180C00002L});
    public static final BitSet FOLLOW_INDENT_in_element181 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_COMMENT_in_element184 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_NEWLINE_in_element186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_element192 = new BitSet(new long[]{0x0000002100C00000L});
    public static final BitSet FOLLOW_singleElement_in_element196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_singleElement_in_element205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compoundElement_in_element211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprTag_in_singleElement227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_singleElement235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_singleElement252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMENT_in_singleElement262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifstat_in_compoundElement274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_region_in_compoundElement279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LDELIM_in_exprTag293 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_expr_in_exprTag297 = new BitSet(new long[]{0x0000000001000200L});
    public static final BitSet FOLLOW_SEMI_in_exprTag301 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_exprOptions_in_exprTag303 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_exprTag308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_region331 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_region336 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_AT_in_region338 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_region340 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_region342 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_region348 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_region352 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_region355 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_END_in_region357 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_region359 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_NEWLINE_in_region370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_subtemplate398 = new BitSet(new long[]{0x0000002182E00000L});
    public static final BitSet FOLLOW_ID_in_subtemplate403 = new BitSet(new long[]{0x0000000010040000L});
    public static final BitSet FOLLOW_COMMA_in_subtemplate409 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_subtemplate413 = new BitSet(new long[]{0x0000000010040000L});
    public static final BitSet FOLLOW_PIPE_in_subtemplate419 = new BitSet(new long[]{0x0000002180E00000L});
    public static final BitSet FOLLOW_template_in_subtemplate426 = new BitSet(new long[]{0x0000000080200000L});
    public static final BitSet FOLLOW_INDENT_in_subtemplate430 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_RCURLY_in_subtemplate438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_ifstat462 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat465 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IF_in_ifstat467 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_ifstat469 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_conditional_in_ifstat473 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_ifstat475 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat477 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat486 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat493 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat496 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ELSEIF_in_ifstat498 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_ifstat500 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_conditional_in_ifstat502 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_ifstat504 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat506 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat508 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat518 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat521 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ELSE_in_ifstat523 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat525 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat529 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat537 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat543 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ENDIF_in_ifstat545 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat549 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ifstat560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andConditional_in_conditional581 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_OR_in_conditional585 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_andConditional_in_conditional587 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_notConditional_in_andConditional600 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_AND_in_andConditional604 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_notConditional_in_andConditional606 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_BANG_in_notConditional619 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_notConditional_in_notConditional621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_notConditional626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_notConditionalExpr638 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_notConditionalExpr648 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_notConditionalExpr652 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_notConditionalExpr666 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_notConditionalExpr668 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_mapExpr_in_notConditionalExpr670 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_notConditionalExpr672 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_option_in_exprOptions691 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_exprOptions695 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_option_in_exprOptions697 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ID_in_option716 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_EQUALS_in_option726 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_exprNoComma_in_option728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_exprNoComma787 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COLON_in_exprNoComma795 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_exprNoComma799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mapExpr_in_expr840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_mapExpr860 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COLON_in_mapExpr878 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr882 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_ID_in_mapTemplateRef923 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef925 = new BitSet(new long[]{0x000000180611C800L});
    public static final BitSet FOLLOW_args_in_mapTemplateRef929 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subtemplate_in_mapTemplateRef944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef951 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_mapExpr_in_mapTemplateRef955 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef959 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef961 = new BitSet(new long[]{0x000000180611C000L});
    public static final BitSet FOLLOW_argExprList_in_mapTemplateRef965 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_includeExpr_in_memberExpr989 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_memberExpr1001 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_memberExpr1003 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_memberExpr1019 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_memberExpr1021 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_mapExpr_in_memberExpr1023 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_memberExpr1025 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_ID_in_includeExpr1063 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1065 = new BitSet(new long[]{0x000000180611C000L});
    public static final BitSet FOLLOW_expr_in_includeExpr1067 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_includeExpr1082 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1084 = new BitSet(new long[]{0x000000180611C800L});
    public static final BitSet FOLLOW_args_in_includeExpr1088 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_includeExpr1107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_primary1141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_primary1150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_primary1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subtemplate_in_primary1177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_primary1182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1191 = new BitSet(new long[]{0x0000001806114400L});
    public static final BitSet FOLLOW_conditional_in_primary1193 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1205 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_expr_in_primary1207 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1209 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1215 = new BitSet(new long[]{0x000000180611C000L});
    public static final BitSet FOLLOW_argExprList_in_primary1217 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_argExprList_in_args1269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_namedArg_in_args1278 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_args1283 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_namedArg_in_args1287 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_args1294 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ELLIPSIS_in_args1296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELLIPSIS_in_args1309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_in_argExprList1333 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_argExprList1338 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_arg_in_argExprList1342 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_exprNoComma_in_arg1363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_namedArg1379 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_EQUALS_in_namedArg1381 = new BitSet(new long[]{0x0000001806114000L});
    public static final BitSet FOLLOW_arg_in_namedArg1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_list1402 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACK_in_list1404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_list1412 = new BitSet(new long[]{0x0000001806174000L});
    public static final BitSet FOLLOW_listElement_in_list1414 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_COMMA_in_list1418 = new BitSet(new long[]{0x0000001806174000L});
    public static final BitSet FOLLOW_listElement_in_list1420 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_RBRACK_in_list1425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprNoComma_in_listElement1441 = new BitSet(new long[]{0x0000000000000002L});

}