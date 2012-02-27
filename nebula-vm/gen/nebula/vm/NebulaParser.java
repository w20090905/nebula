// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-02-26 13:09:51

package nebula.vm;
import nebula.vm.VariableSymbol;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class NebulaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "INT", "NEWLINE", "WS", "'('", "')'", "'*'", "'+'", "'-'", "'='"
    };

    public static final int EOF=-1;
    public static final int T__8=8;
    public static final int T__9=9;
    public static final int T__10=10;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int ID=4;
    public static final int INT=5;
    public static final int NEWLINE=6;
    public static final int WS=7;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public NebulaParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public NebulaParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return NebulaParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\Projects\\nebula\\nebula-vm\\Nebula.g"; }


      /** Map variable name to Integer object holding value */

    	protected void gen(short op) {;}
    	protected void gen(short op, short a) {;}
    	protected void gen(short op, short a, short b) {;}
    	protected void gen(short op, short a, short b, short c) {;}
    	protected void gen(short op, short a, int bx) {;}
    	
      protected VariableSymbol resolve(String name) {return null;};
      protected VariableSymbol defineVariable(String name) {return null;};
      protected VariableSymbol defineInt(String name) {return null;};
        
      protected VariableSymbol eval(VariableSymbol a) {return null;};
      protected VariableSymbol evalSet(String id,VariableSymbol b) {return null;};

      protected VariableSymbol add(VariableSymbol a, VariableSymbol b) {return null;};
      protected VariableSymbol sub(VariableSymbol a, VariableSymbol b) {return null;};
      protected VariableSymbol mul(VariableSymbol a, VariableSymbol b) {return null;};  
      protected VariableSymbol load(VariableSymbol a, VariableSymbol b) {return null;};




    // $ANTLR start "program"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:36:1: program : ( stat )+ ;
    public final void program() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:36:8: ( ( stat )+ )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:36:12: ( stat )+
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:36:12: ( stat )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= ID && LA1_0 <= NEWLINE)||LA1_0==8) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:36:12: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_program35);
            	    stat();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "program"



    // $ANTLR start "stat"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:38:1: stat : ( expr NEWLINE | ID '=' expr NEWLINE | NEWLINE );
    public final void stat() throws RecognitionException {
        Token ID2=null;
        VariableSymbol expr1 =null;

        VariableSymbol expr3 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:38:5: ( expr NEWLINE | ID '=' expr NEWLINE | NEWLINE )
            int alt2=3;
            switch ( input.LA(1) ) {
            case INT:
            case 8:
                {
                alt2=1;
                }
                break;
            case ID:
                {
                int LA2_2 = input.LA(2);

                if ( (LA2_2==13) ) {
                    alt2=2;
                }
                else if ( (LA2_2==NEWLINE||(LA2_2 >= 10 && LA2_2 <= 12)) ) {
                    alt2=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 2, input);

                    throw nvae;

                }
                }
                break;
            case NEWLINE:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:40:9: expr NEWLINE
                    {
                    pushFollow(FOLLOW_expr_in_stat80);
                    expr1=expr();

                    state._fsp--;


                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat82); 

                     eval(expr1);

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:44:9: ID '=' expr NEWLINE
                    {
                    ID2=(Token)match(input,ID,FOLLOW_ID_in_stat115); 

                    match(input,13,FOLLOW_13_in_stat117); 

                    pushFollow(FOLLOW_expr_in_stat119);
                    expr3=expr();

                    state._fsp--;


                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat121); 

                    evalSet((ID2!=null?ID2.getText():null),expr3);

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:48:9: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat153); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "stat"



    // $ANTLR start "expr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:56:1: expr returns [VariableSymbol value] : e= multExpr ( '+' e= multExpr | '-' e= multExpr )* ;
    public final VariableSymbol expr() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:57:5: (e= multExpr ( '+' e= multExpr | '-' e= multExpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:57:9: e= multExpr ( '+' e= multExpr | '-' e= multExpr )*
            {
            pushFollow(FOLLOW_multExpr_in_expr182);
            e=multExpr();

            state._fsp--;


            value = e;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:58:9: ( '+' e= multExpr | '-' e= multExpr )*
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==11) ) {
                    alt3=1;
                }
                else if ( (LA3_0==12) ) {
                    alt3=2;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:58:13: '+' e= multExpr
            	    {
            	    match(input,11,FOLLOW_11_in_expr198); 

            	    pushFollow(FOLLOW_multExpr_in_expr202);
            	    e=multExpr();

            	    state._fsp--;


            	    value = add(value,e);

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:59:13: '-' e= multExpr
            	    {
            	    match(input,12,FOLLOW_12_in_expr218); 

            	    pushFollow(FOLLOW_multExpr_in_expr222);
            	    e=multExpr();

            	    state._fsp--;


            	    value = sub(value,e);

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "expr"



    // $ANTLR start "multExpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:70:1: multExpr returns [VariableSymbol value] : e= atom ( '*' e= atom )* ;
    public final VariableSymbol multExpr() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:71:5: (e= atom ( '*' e= atom )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:71:9: e= atom ( '*' e= atom )*
            {
            pushFollow(FOLLOW_atom_in_multExpr264);
            e=atom();

            state._fsp--;


            value = e;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:72:5: ( '*' e= atom )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==10) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:72:6: '*' e= atom
            	    {
            	    match(input,10,FOLLOW_10_in_multExpr274); 

            	    pushFollow(FOLLOW_atom_in_multExpr278);
            	    e=atom();

            	    state._fsp--;


            	    value = mul(value,e);

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "multExpr"



    // $ANTLR start "atom"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:78:1: atom returns [VariableSymbol value] : ( INT | ID | '(' expr ')' );
    public final VariableSymbol atom() throws RecognitionException {
        VariableSymbol value = null;


        Token INT4=null;
        Token ID5=null;
        VariableSymbol expr6 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:5: ( INT | ID | '(' expr ')' )
            int alt5=3;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt5=1;
                }
                break;
            case ID:
                {
                alt5=2;
                }
                break;
            case 8:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:80:9: INT
                    {
                    INT4=(Token)match(input,INT,FOLLOW_INT_in_atom323); 

                    value = defineInt((INT4!=null?INT4.getText():null));

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:82:9: ID
                    {
                    ID5=(Token)match(input,ID,FOLLOW_ID_in_atom336); 

                    value = resolve((ID5!=null?ID5.getText():null));

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:92:9: '(' expr ')'
                    {
                    match(input,8,FOLLOW_8_in_atom365); 

                    pushFollow(FOLLOW_expr_in_atom367);
                    expr6=expr();

                    state._fsp--;


                    match(input,9,FOLLOW_9_in_atom369); 

                    value = expr6;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "atom"

    // Delegated rules


 

    public static final BitSet FOLLOW_stat_in_program35 = new BitSet(new long[]{0x0000000000000172L});
    public static final BitSet FOLLOW_expr_in_stat80 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_stat82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_stat115 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_stat117 = new BitSet(new long[]{0x0000000000000130L});
    public static final BitSet FOLLOW_expr_in_stat119 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_stat121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_stat153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpr_in_expr182 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_11_in_expr198 = new BitSet(new long[]{0x0000000000000130L});
    public static final BitSet FOLLOW_multExpr_in_expr202 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_12_in_expr218 = new BitSet(new long[]{0x0000000000000130L});
    public static final BitSet FOLLOW_multExpr_in_expr222 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_atom_in_multExpr264 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_10_in_multExpr274 = new BitSet(new long[]{0x0000000000000130L});
    public static final BitSet FOLLOW_atom_in_multExpr278 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_INT_in_atom323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_atom365 = new BitSet(new long[]{0x0000000000000130L});
    public static final BitSet FOLLOW_expr_in_atom367 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_atom369 = new BitSet(new long[]{0x0000000000000002L});

}