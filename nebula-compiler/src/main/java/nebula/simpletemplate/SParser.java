// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g 2013-10-31 18:26:53

package nebula.simpletemplate;

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
        
       Compiler c = new Compiler();
      
      private Map<String, Var> locals = new HashMap<String, Var>();
      protected int maxLocals = 0;

      protected void initLocals() {
        locals.clear();
        pushLocal("this");
        pushLocal("out");
        pushLocal("object");
        root = pushLocal("root");
      }
      
      Var root;
      
      protected Var pushLocal(String name, String typeName) {
        Var var = new Var(name,locals.size());
        locals.put(var.name, var);
        return var;
      }
      
      protected Var pushLocal(String name) {
        Var var = new Var(name,locals.size());
        locals.put(var.name, var);
        return var;
      }
      protected Var v(String name) {
        Var var = locals.get(name);
        return var;
      };



    // $ANTLR start "templateAndEOF"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:73:1: templateAndEOF returns [Statement s] : t= template EOF ;
    public final Statement templateAndEOF() throws RecognitionException {
        Statement s = null;


        Statement t =null;


        initLocals();
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:75:3: (t= template EOF )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:75:3: t= template EOF
            {
            pushFollow(FOLLOW_template_in_templateAndEOF139);
            t=template();

            state._fsp--;


            s=t;

            match(input,EOF,FOLLOW_EOF_in_templateAndEOF142); 

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return s;
    }
    // $ANTLR end "templateAndEOF"



    // $ANTLR start "template"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:77:1: template returns [Statement s] : (e= element )* ;
    public final Statement template() throws RecognitionException {
        Statement s = null;


        Statement e =null;


        List<Statement> statments = new ArrayList<Statement>();
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:79:2: ( (e= element )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:79:4: (e= element )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:79:4: (e= element )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= TEXT && LA1_0 <= LDELIM)||(LA1_0 >= INDENT && LA1_0 <= NEWLINE)||LA1_0==COMMENT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:79:5: e= element
            	    {
            	    pushFollow(FOLLOW_element_in_template162);
            	    e=element();

            	    state._fsp--;


            	    if(e!=null)statments.add(e);

            	    }
            	    break;

            	default :
            	    break loop1;
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
    // $ANTLR end "template"



    // $ANTLR start "element"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:82:1: element returns [Statement s] : ({...}? ( INDENT )? COMMENT NEWLINE | INDENT p= singleElement |p= singleElement );
    public final Statement element() throws RecognitionException {
        Statement s = null;


        CommonToken NEWLINE1=null;
        Statement p =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:83:3: ({...}? ( INDENT )? COMMENT NEWLINE | INDENT p= singleElement |p= singleElement )
            int alt3=3;
            switch ( input.LA(1) ) {
            case INDENT:
                {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==COMMENT) ) {
                    int LA3_4 = input.LA(3);

                    if ( (LA3_4==NEWLINE) ) {
                        int LA3_7 = input.LA(4);

                        if ( ((input.LT(1).getCharPositionInLine()==0)) ) {
                            alt3=1;
                        }
                        else if ( (true) ) {
                            alt3=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 7, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA3_4==EOF||(LA3_4 >= TEXT && LA3_4 <= LDELIM)||LA3_4==INDENT||LA3_4==COMMENT) ) {
                        alt3=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 4, input);

                        throw nvae;

                    }
                }
                else if ( ((LA3_1 >= TEXT && LA3_1 <= LDELIM)||LA3_1==NEWLINE) ) {
                    alt3=2;
                }
                else {
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
                    int LA3_6 = input.LA(3);

                    if ( ((input.LT(1).getCharPositionInLine()==0)) ) {
                        alt3=1;
                    }
                    else if ( (true) ) {
                        alt3=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 6, input);

                        throw nvae;

                    }
                }
                else if ( (LA3_2==EOF||(LA3_2 >= TEXT && LA3_2 <= LDELIM)||LA3_2==INDENT||LA3_2==COMMENT) ) {
                    alt3=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

                    throw nvae;

                }
                }
                break;
            case TEXT:
            case LDELIM:
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
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:83:5: {...}? ( INDENT )? COMMENT NEWLINE
                    {
                    if ( !((input.LT(1).getCharPositionInLine()==0)) ) {
                        throw new FailedPredicateException(input, "element", "input.LT(1).getCharPositionInLine()==0");
                    }

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:83:47: ( INDENT )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==INDENT) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:83:47: INDENT
                            {
                            match(input,INDENT,FOLLOW_INDENT_in_element185); 

                            }
                            break;

                    }


                    match(input,COMMENT,FOLLOW_COMMENT_in_element189); 

                    NEWLINE1=(CommonToken)match(input,NEWLINE,FOLLOW_NEWLINE_in_element191); 

                    s=c.stOutput(c.opStringCst((NEWLINE1!=null?NEWLINE1.getText():null)));

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:84:5: INDENT p= singleElement
                    {
                    match(input,INDENT,FOLLOW_INDENT_in_element199); 

                    pushFollow(FOLLOW_singleElement_in_element203);
                    p=singleElement();

                    state._fsp--;


                    s=p;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:85:5: p= singleElement
                    {
                    pushFollow(FOLLOW_singleElement_in_element212);
                    p=singleElement();

                    state._fsp--;


                    s=p;

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
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:88:1: singleElement returns [Statement v] : (p= exprTag | TEXT | NEWLINE | COMMENT );
    public final Statement singleElement() throws RecognitionException {
        Statement v = null;


        CommonToken TEXT2=null;
        CommonToken NEWLINE3=null;
        Expr p =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:89:3: (p= exprTag | TEXT | NEWLINE | COMMENT )
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
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:89:5: p= exprTag
                    {
                    pushFollow(FOLLOW_exprTag_in_singleElement232);
                    p=exprTag();

                    state._fsp--;


                    v=c.stOutput(p);

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:90:5: TEXT
                    {
                    TEXT2=(CommonToken)match(input,TEXT,FOLLOW_TEXT_in_singleElement239); 

                    v=c.stOutput(c.opStringCst((TEXT2!=null?TEXT2.getText():null)));

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:91:5: NEWLINE
                    {
                    NEWLINE3=(CommonToken)match(input,NEWLINE,FOLLOW_NEWLINE_in_singleElement247); 

                    v=c.stOutput(c.opStringCst((NEWLINE3!=null?NEWLINE3.getText():null)));

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:92:5: COMMENT
                    {
                    match(input,COMMENT,FOLLOW_COMMENT_in_singleElement255); 

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
    // $ANTLR end "singleElement"



    // $ANTLR start "exprTag"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:95:1: exprTag returns [Expr v] : LDELIM p= expr RDELIM ;
    public final Expr exprTag() throws RecognitionException {
        Expr v = null;


        Expr p =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:96:3: ( LDELIM p= expr RDELIM )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:96:5: LDELIM p= expr RDELIM
            {
            match(input,LDELIM,FOLLOW_LDELIM_in_exprTag273); 

            pushFollow(FOLLOW_expr_in_exprTag277);
            p=expr();

            state._fsp--;


            match(input,RDELIM,FOLLOW_RDELIM_in_exprTag279); 

            v=p;

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "exprTag"



    // $ANTLR start "expr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:99:1: expr returns [Expr v] : p= primary ;
    public final Expr expr() throws RecognitionException {
        Expr v = null;


        Expr p =null;


        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:99:23: (p= primary )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:99:25: p= primary
            {
            pushFollow(FOLLOW_primary_in_expr298);
            p=primary();

            state._fsp--;


            v=p;

            }

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "expr"



    // $ANTLR start "primary"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:101:1: primary returns [Expr v] : ( ID | STRING | TRUE | FALSE );
    public final Expr primary() throws RecognitionException {
        Expr v = null;


        CommonToken ID4=null;
        CommonToken STRING5=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:102:3: ( ID | STRING | TRUE | FALSE )
            int alt5=4;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt5=1;
                }
                break;
            case STRING:
                {
                alt5=2;
                }
                break;
            case TRUE:
                {
                alt5=3;
                }
                break;
            case FALSE:
                {
                alt5=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:102:5: ID
                    {
                    ID4=(CommonToken)match(input,ID,FOLLOW_ID_in_primary314); 

                    v = c.opFieldOf(v=c.opLocal(root),(ID4!=null?ID4.getText():null));

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:103:5: STRING
                    {
                    STRING5=(CommonToken)match(input,STRING,FOLLOW_STRING_in_primary322); 

                    v=c.opStringCst((STRING5!=null?STRING5.getText():null));

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:104:5: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_primary331); 

                    v=c.opYesnoCst(true);

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\SParser.g:105:5: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_primary342); 

                    v=c.opYesnoCst(false);

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

    // Delegated rules


 

    public static final BitSet FOLLOW_template_in_templateAndEOF139 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_templateAndEOF142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_template162 = new BitSet(new long[]{0x0000002180C00002L});
    public static final BitSet FOLLOW_INDENT_in_element185 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_COMMENT_in_element189 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_NEWLINE_in_element191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_element199 = new BitSet(new long[]{0x0000002100C00000L});
    public static final BitSet FOLLOW_singleElement_in_element203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_singleElement_in_element212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprTag_in_singleElement232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_singleElement239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_singleElement247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMENT_in_singleElement255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LDELIM_in_exprTag273 = new BitSet(new long[]{0x0000001806000000L});
    public static final BitSet FOLLOW_expr_in_exprTag277 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_exprTag279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_expr298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_primary322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_primary331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_primary342 = new BitSet(new long[]{0x0000000000000002L});

}