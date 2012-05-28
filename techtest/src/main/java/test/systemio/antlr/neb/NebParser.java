// $ANTLR 3.4 D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g 2012-05-26 15:27:35
package test.systemio.antlr.neb;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class NebParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DIGIT", "LETTER", "LOWER", "NAME", "NEWLINE", "SPACE", "UPPER", "WHITESPACE"
    };

    public static final int EOF=-1;
    public static final int DIGIT=4;
    public static final int LETTER=5;
    public static final int LOWER=6;
    public static final int NAME=7;
    public static final int NEWLINE=8;
    public static final int SPACE=9;
    public static final int UPPER=10;
    public static final int WHITESPACE=11;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public NebParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public NebParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return NebParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g"; }



    // $ANTLR start "echo"
    // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:10:1: echo : ( state | emptyLine )* ;
    public final void echo() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:10:6: ( ( state | emptyLine )* )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:10:8: ( state | emptyLine )*
            {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:10:8: ( state | emptyLine )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==NAME) ) {
                    alt1=1;
                }
                else if ( (LA1_0==NEWLINE) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:10:9: state
            	    {
            	    pushFollow(FOLLOW_state_in_echo39);
            	    state();

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:10:15: emptyLine
            	    {
            	    pushFollow(FOLLOW_emptyLine_in_echo41);
            	    emptyLine();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
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
        return ;
    }
    // $ANTLR end "echo"



    // $ANTLR start "state"
    // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:12:1: state : id NEWLINE ;
    public final void state() throws RecognitionException {
        NebParser.id_return id1 =null;


        try {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:12:7: ( id NEWLINE )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:12:9: id NEWLINE
            {
            pushFollow(FOLLOW_id_in_state51);
            id1=id();

            state._fsp--;


            System.out.println("==>" + (id1!=null?input.toString(id1.start,id1.stop):null));

            match(input,NEWLINE,FOLLOW_NEWLINE_in_state54); 

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
    // $ANTLR end "state"



    // $ANTLR start "emptyLine"
    // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:14:1: emptyLine : NEWLINE ;
    public final void emptyLine() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:15:2: ( NEWLINE )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:15:3: NEWLINE
            {
            match(input,NEWLINE,FOLLOW_NEWLINE_in_emptyLine62); 

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
    // $ANTLR end "emptyLine"


    public static class id_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "id"
    // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:17:1: id : NAME ;
    public final NebParser.id_return id() throws RecognitionException {
        NebParser.id_return retval = new NebParser.id_return();
        retval.start = input.LT(1);


        try {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:17:5: ( NAME )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:17:7: NAME
            {
            match(input,NAME,FOLLOW_NAME_in_id71); 

            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "id"

    // Delegated rules


 

    public static final BitSet FOLLOW_state_in_echo39 = new BitSet(new long[]{0x0000000000000182L});
    public static final BitSet FOLLOW_emptyLine_in_echo41 = new BitSet(new long[]{0x0000000000000182L});
    public static final BitSet FOLLOW_id_in_state51 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_NEWLINE_in_state54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_emptyLine62 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_id71 = new BitSet(new long[]{0x0000000000000002L});

}