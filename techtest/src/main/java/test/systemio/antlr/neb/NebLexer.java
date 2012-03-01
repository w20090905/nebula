// $ANTLR 3.4 D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g 2012-03-01 14:22:30
package test.systemio.antlr.neb;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class NebLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public NebLexer() {} 
    public NebLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public NebLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g"; }

    // $ANTLR start "NAME"
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:20:5: ( LETTER ( LETTER | DIGIT | '_' )* )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:20:7: LETTER ( LETTER | DIGIT | '_' )*
            {
            mLETTER(); 


            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:20:14: ( LETTER | DIGIT | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NAME"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:22:16: ( LOWER | UPPER )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "LOWER"
    public final void mLOWER() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:23:15: ( 'a' .. 'z' )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:
            {
            if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LOWER"

    // $ANTLR start "UPPER"
    public final void mUPPER() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:24:15: ( 'A' .. 'Z' )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UPPER"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:25:15: ( '0' .. '9' )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "SPACE"
    public final void mSPACE() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:26:15: ( ' ' | '\\t' )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:
            {
            if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SPACE"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:28:8: ( ( ( '\\r' )? '\\n' ) )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:28:10: ( ( '\\r' )? '\\n' )
            {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:28:10: ( ( '\\r' )? '\\n' )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:28:11: ( '\\r' )? '\\n'
            {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:28:11: ( '\\r' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='\r') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:28:11: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:29:11: ( ( SPACE )+ )
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:29:13: ( SPACE )+
            {
            // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:29:13: ( SPACE )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='\t'||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHITESPACE"

    public void mTokens() throws RecognitionException {
        // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:1:8: ( NAME | NEWLINE | WHITESPACE )
        int alt4=3;
        switch ( input.LA(1) ) {
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'f':
        case 'g':
        case 'h':
        case 'i':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'p':
        case 'q':
        case 'r':
        case 's':
        case 't':
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt4=1;
            }
            break;
        case '\n':
        case '\r':
            {
            alt4=2;
            }
            break;
        case '\t':
        case ' ':
            {
            alt4=3;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 4, 0, input);

            throw nvae;

        }

        switch (alt4) {
            case 1 :
                // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:1:10: NAME
                {
                mNAME(); 


                }
                break;
            case 2 :
                // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:1:15: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 3 :
                // D:\\Projects\\nebula\\techtest\\target\\test-classes\\test\\systemio\\antlr\\neb\\Neb.g:1:23: WHITESPACE
                {
                mWHITESPACE(); 


                }
                break;

        }

    }


 

}