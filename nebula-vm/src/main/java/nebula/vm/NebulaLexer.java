// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-05 15:49:03
package nebula.vm;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class NebulaLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int Digit=4;
    public static final int ID=5;
    public static final int INT=6;
    public static final int Letter=7;
    public static final int MultiLineComment=8;
    public static final int NEWLINE=9;
    public static final int SingleLineComment=10;
    public static final int Whitespace=11;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public NebulaLexer() {} 
    public NebulaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public NebulaLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "D:\\Projects\\nebula\\nebula-vm\\Nebula.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:9:7: ( '(' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:9:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:10:7: ( ')' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:10:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:11:7: ( '*' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:11:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:12:7: ( '+' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:12:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:13:7: ( ',' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:13:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:14:7: ( '-' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:14:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:15:7: ( '.' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:15:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:16:7: ( ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:16:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:17:7: ( '=' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:17:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:18:7: ( '[' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:18:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:19:7: ( ']' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:19:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:20:7: ( 'class' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:20:9: 'class'
            {
            match("class"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:21:7: ( 'decimal' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:21:9: 'decimal'
            {
            match("decimal"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:22:7: ( 'extends' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:22:9: 'extends'
            {
            match("extends"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:23:7: ( 'int' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:23:9: 'int'
            {
            match("int"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:24:7: ( 'return' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:24:9: 'return'
            {
            match("return"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:25:7: ( 'super' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:25:9: 'super'
            {
            match("super"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:26:7: ( 'this' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:26:9: 'this'
            {
            match("this"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:27:7: ( 'void' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:27:9: 'void'
            {
            match("void"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:28:7: ( '{' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:28:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:29:7: ( '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:29:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:4: ( Letter ( Letter | Digit )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:7: Letter ( Letter | Digit )*
            {
            mLetter(); 


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:14: ( Letter | Digit )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
    // $ANTLR end "ID"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:153:5: ( Digit ( Digit )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:153:8: Digit ( Digit )*
            {
            mDigit(); 


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:153:14: ( Digit )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:
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
            	    break;

            	default :
            	    break loop2;
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
    // $ANTLR end "INT"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:154:16: ( '0' .. '9' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:
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
    // $ANTLR end "Digit"

    // $ANTLR start "Letter"
    public final void mLetter() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:17: ( 'a' .. 'z' | 'A' .. 'Z' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:
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
    // $ANTLR end "Letter"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:8: ( ( '\\r' )? '\\n' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:9: ( '\\r' )? '\\n'
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:9: ( '\\r' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:9: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "Whitespace"
    public final void mWhitespace() throws RecognitionException {
        try {
            int _type = Whitespace;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:12: ( ( ' ' | '\\t' | '\\f' )+ )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:15: ( ' ' | '\\t' | '\\f' )+
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:15: ( ' ' | '\\t' | '\\f' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='\t'||LA4_0=='\f'||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)=='\f'||input.LA(1)==' ' ) {
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
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Whitespace"

    // $ANTLR start "SingleLineComment"
    public final void mSingleLineComment() throws RecognitionException {
        try {
            int _type = SingleLineComment;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:159:19: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\n' | '\\r' ( '\\n' )? )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:3: '//' (~ ( '\\n' | '\\r' ) )* ( '\\n' | '\\r' ( '\\n' )? )?
            {
            match("//"); 



            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:8: (~ ( '\\n' | '\\r' ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
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
            	    break loop5;
                }
            } while (true);


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:24: ( '\\n' | '\\r' ( '\\n' )? )?
            int alt7=3;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\n') ) {
                alt7=1;
            }
            else if ( (LA7_0=='\r') ) {
                alt7=2;
            }
            switch (alt7) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:25: '\\n'
                    {
                    match('\n'); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:30: '\\r' ( '\\n' )?
                    {
                    match('\r'); 

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:34: ( '\\n' )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0=='\n') ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:35: '\\n'
                            {
                            match('\n'); 

                            }
                            break;

                    }


                    }
                    break;

            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SingleLineComment"

    // $ANTLR start "MultiLineComment"
    public final void mMultiLineComment() throws RecognitionException {
        try {
            int _type = MultiLineComment;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:18: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:162:5: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 



            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:162:10: ( options {greedy=false; } : . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='*') ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1=='/') ) {
                        alt8=2;
                    }
                    else if ( ((LA8_1 >= '\u0000' && LA8_1 <= '.')||(LA8_1 >= '0' && LA8_1 <= '\uFFFF')) ) {
                        alt8=1;
                    }


                }
                else if ( ((LA8_0 >= '\u0000' && LA8_0 <= ')')||(LA8_0 >= '+' && LA8_0 <= '\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:162:38: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            match("*/"); 



            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MultiLineComment"

    public void mTokens() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | ID | INT | NEWLINE | Whitespace | SingleLineComment | MultiLineComment )
        int alt9=27;
        alt9 = dfa9.predict(input);
        switch (alt9) {
            case 1 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:10: T__12
                {
                mT__12(); 


                }
                break;
            case 2 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:16: T__13
                {
                mT__13(); 


                }
                break;
            case 3 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:22: T__14
                {
                mT__14(); 


                }
                break;
            case 4 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:28: T__15
                {
                mT__15(); 


                }
                break;
            case 5 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:34: T__16
                {
                mT__16(); 


                }
                break;
            case 6 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:40: T__17
                {
                mT__17(); 


                }
                break;
            case 7 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:46: T__18
                {
                mT__18(); 


                }
                break;
            case 8 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:52: T__19
                {
                mT__19(); 


                }
                break;
            case 9 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:58: T__20
                {
                mT__20(); 


                }
                break;
            case 10 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:64: T__21
                {
                mT__21(); 


                }
                break;
            case 11 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:70: T__22
                {
                mT__22(); 


                }
                break;
            case 12 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:76: T__23
                {
                mT__23(); 


                }
                break;
            case 13 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:82: T__24
                {
                mT__24(); 


                }
                break;
            case 14 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:88: T__25
                {
                mT__25(); 


                }
                break;
            case 15 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:94: T__26
                {
                mT__26(); 


                }
                break;
            case 16 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:100: T__27
                {
                mT__27(); 


                }
                break;
            case 17 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:106: T__28
                {
                mT__28(); 


                }
                break;
            case 18 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:112: T__29
                {
                mT__29(); 


                }
                break;
            case 19 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:118: T__30
                {
                mT__30(); 


                }
                break;
            case 20 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:124: T__31
                {
                mT__31(); 


                }
                break;
            case 21 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:130: T__32
                {
                mT__32(); 


                }
                break;
            case 22 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:136: ID
                {
                mID(); 


                }
                break;
            case 23 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:139: INT
                {
                mINT(); 


                }
                break;
            case 24 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:143: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 25 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:151: Whitespace
                {
                mWhitespace(); 


                }
                break;
            case 26 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:162: SingleLineComment
                {
                mSingleLineComment(); 


                }
                break;
            case 27 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:180: MultiLineComment
                {
                mMultiLineComment(); 


                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA9_eotS =
        "\14\uffff\10\26\7\uffff\10\26\2\uffff\3\26\1\60\7\26\1\uffff\2\26"+
        "\1\72\1\73\1\74\3\26\1\100\3\uffff\2\26\1\103\1\uffff\1\104\1\105"+
        "\3\uffff";
    static final String DFA9_eofS =
        "\106\uffff";
    static final String DFA9_minS =
        "\1\11\13\uffff\1\154\1\145\1\170\1\156\1\145\1\165\1\150\1\157\6"+
        "\uffff\1\52\1\141\1\143\3\164\1\160\2\151\2\uffff\1\163\1\151\1"+
        "\145\1\60\1\165\1\145\1\163\1\144\1\163\1\155\1\156\1\uffff\2\162"+
        "\3\60\1\141\1\144\1\156\1\60\3\uffff\1\154\1\163\1\60\1\uffff\2"+
        "\60\3\uffff";
    static final String DFA9_maxS =
        "\1\175\13\uffff\1\154\1\145\1\170\1\156\1\145\1\165\1\150\1\157"+
        "\6\uffff\1\57\1\141\1\143\3\164\1\160\2\151\2\uffff\1\163\1\151"+
        "\1\145\1\172\1\165\1\145\1\163\1\144\1\163\1\155\1\156\1\uffff\2"+
        "\162\3\172\1\141\1\144\1\156\1\172\3\uffff\1\154\1\163\1\172\1\uffff"+
        "\2\172\3\uffff";
    static final String DFA9_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\10\uffff"+
        "\1\24\1\25\1\26\1\27\1\30\1\31\11\uffff\1\32\1\33\13\uffff\1\17"+
        "\11\uffff\1\22\1\23\1\14\3\uffff\1\21\2\uffff\1\20\1\15\1\16";
    static final String DFA9_specialS =
        "\106\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\31\1\30\1\uffff\1\31\1\30\22\uffff\1\31\7\uffff\1\1\1\2\1"+
            "\3\1\4\1\5\1\6\1\7\1\32\12\27\1\uffff\1\10\1\uffff\1\11\3\uffff"+
            "\32\26\1\12\1\uffff\1\13\3\uffff\2\26\1\14\1\15\1\16\3\26\1"+
            "\17\10\26\1\20\1\21\1\22\1\26\1\23\4\26\1\24\1\uffff\1\25",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "\1\42",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\44\4\uffff\1\43",
            "\1\45",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "",
            "",
            "\1\55",
            "\1\56",
            "\1\57",
            "\12\26\7\uffff\32\26\6\uffff\32\26",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "",
            "\1\70",
            "\1\71",
            "\12\26\7\uffff\32\26\6\uffff\32\26",
            "\12\26\7\uffff\32\26\6\uffff\32\26",
            "\12\26\7\uffff\32\26\6\uffff\32\26",
            "\1\75",
            "\1\76",
            "\1\77",
            "\12\26\7\uffff\32\26\6\uffff\32\26",
            "",
            "",
            "",
            "\1\101",
            "\1\102",
            "\12\26\7\uffff\32\26\6\uffff\32\26",
            "",
            "\12\26\7\uffff\32\26\6\uffff\32\26",
            "\12\26\7\uffff\32\26\6\uffff\32\26",
            "",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | ID | INT | NEWLINE | Whitespace | SingleLineComment | MultiLineComment );";
        }
    }
 

}