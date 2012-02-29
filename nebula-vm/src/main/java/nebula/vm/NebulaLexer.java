// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-02-29 10:14:45
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
    public static final int Digit=4;
    public static final int Identifier=5;
    public static final int Integer=6;
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
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:16:7: ( ':' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:16:9: ':'
            {
            match(':'); 

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
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:17:7: ( ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:17:9: ';'
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
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:18:7: ( '=' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:18:9: '='
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
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:19:7: ( 'class' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:19:9: 'class'
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
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:20:7: ( 'float' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:20:9: 'float'
            {
            match("float"); 



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
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:21:7: ( 'int' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:21:9: 'int'
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
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:22:7: ( 'public' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:22:9: 'public'
            {
            match("public"); 



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
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:23:7: ( 'return' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:23:9: 'return'
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
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:24:7: ( 'void' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:24:9: 'void'
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
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:25:7: ( '{' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:25:9: '{'
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
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:26:7: ( '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:26:9: '}'
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
    // $ANTLR end "T__29"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:166:12: ( Letter ( Letter | Digit )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:166:15: Letter ( Letter | Digit )*
            {
            mLetter(); 


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:166:22: ( Letter | Digit )*
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
    // $ANTLR end "Identifier"

    // $ANTLR start "Integer"
    public final void mInteger() throws RecognitionException {
        try {
            int _type = Integer;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:167:9: ( Digit ( Digit )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:167:12: Digit ( Digit )*
            {
            mDigit(); 


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:167:18: ( Digit )*
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
    // $ANTLR end "Integer"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:168:16: ( '0' .. '9' )
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
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:169:17: ( 'a' .. 'z' | 'A' .. 'Z' )
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
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:171:8: ( ( '\\r' )? '\\n' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:171:9: ( '\\r' )? '\\n'
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:171:9: ( '\\r' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:171:9: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

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
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:172:12: ( ( ' ' | '\\t' | '\\f' )+ )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:172:15: ( ' ' | '\\t' | '\\f' )+
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:172:15: ( ' ' | '\\t' | '\\f' )+
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
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:173:19: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\n' | '\\r' ( '\\n' )? )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:3: '//' (~ ( '\\n' | '\\r' ) )* ( '\\n' | '\\r' ( '\\n' )? )?
            {
            match("//"); 



            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:8: (~ ( '\\n' | '\\r' ) )*
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


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:24: ( '\\n' | '\\r' ( '\\n' )? )?
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:25: '\\n'
                    {
                    match('\n'); 

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:30: '\\r' ( '\\n' )?
                    {
                    match('\r'); 

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:34: ( '\\n' )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0=='\n') ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:35: '\\n'
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
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:175:18: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:176:5: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 



            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:176:10: ( options {greedy=false; } : . )*
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:176:38: .
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
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | Identifier | Integer | NEWLINE | Whitespace | SingleLineComment | MultiLineComment )
        int alt9=24;
        switch ( input.LA(1) ) {
        case '(':
            {
            alt9=1;
            }
            break;
        case ')':
            {
            alt9=2;
            }
            break;
        case '*':
            {
            alt9=3;
            }
            break;
        case '+':
            {
            alt9=4;
            }
            break;
        case ',':
            {
            alt9=5;
            }
            break;
        case '-':
            {
            alt9=6;
            }
            break;
        case '.':
            {
            alt9=7;
            }
            break;
        case ':':
            {
            alt9=8;
            }
            break;
        case ';':
            {
            alt9=9;
            }
            break;
        case '=':
            {
            alt9=10;
            }
            break;
        case 'c':
            {
            int LA9_11 = input.LA(2);

            if ( (LA9_11=='l') ) {
                int LA9_24 = input.LA(3);

                if ( (LA9_24=='a') ) {
                    int LA9_32 = input.LA(4);

                    if ( (LA9_32=='s') ) {
                        int LA9_38 = input.LA(5);

                        if ( (LA9_38=='s') ) {
                            int LA9_44 = input.LA(6);

                            if ( ((LA9_44 >= '0' && LA9_44 <= '9')||(LA9_44 >= 'A' && LA9_44 <= 'Z')||(LA9_44 >= 'a' && LA9_44 <= 'z')) ) {
                                alt9=19;
                            }
                            else {
                                alt9=11;
                            }
                        }
                        else {
                            alt9=19;
                        }
                    }
                    else {
                        alt9=19;
                    }
                }
                else {
                    alt9=19;
                }
            }
            else {
                alt9=19;
            }
            }
            break;
        case 'f':
            {
            int LA9_12 = input.LA(2);

            if ( (LA9_12=='l') ) {
                int LA9_25 = input.LA(3);

                if ( (LA9_25=='o') ) {
                    int LA9_33 = input.LA(4);

                    if ( (LA9_33=='a') ) {
                        int LA9_39 = input.LA(5);

                        if ( (LA9_39=='t') ) {
                            int LA9_45 = input.LA(6);

                            if ( ((LA9_45 >= '0' && LA9_45 <= '9')||(LA9_45 >= 'A' && LA9_45 <= 'Z')||(LA9_45 >= 'a' && LA9_45 <= 'z')) ) {
                                alt9=19;
                            }
                            else {
                                alt9=12;
                            }
                        }
                        else {
                            alt9=19;
                        }
                    }
                    else {
                        alt9=19;
                    }
                }
                else {
                    alt9=19;
                }
            }
            else {
                alt9=19;
            }
            }
            break;
        case 'i':
            {
            int LA9_13 = input.LA(2);

            if ( (LA9_13=='n') ) {
                int LA9_26 = input.LA(3);

                if ( (LA9_26=='t') ) {
                    int LA9_34 = input.LA(4);

                    if ( ((LA9_34 >= '0' && LA9_34 <= '9')||(LA9_34 >= 'A' && LA9_34 <= 'Z')||(LA9_34 >= 'a' && LA9_34 <= 'z')) ) {
                        alt9=19;
                    }
                    else {
                        alt9=13;
                    }
                }
                else {
                    alt9=19;
                }
            }
            else {
                alt9=19;
            }
            }
            break;
        case 'p':
            {
            int LA9_14 = input.LA(2);

            if ( (LA9_14=='u') ) {
                int LA9_27 = input.LA(3);

                if ( (LA9_27=='b') ) {
                    int LA9_35 = input.LA(4);

                    if ( (LA9_35=='l') ) {
                        int LA9_41 = input.LA(5);

                        if ( (LA9_41=='i') ) {
                            int LA9_46 = input.LA(6);

                            if ( (LA9_46=='c') ) {
                                int LA9_51 = input.LA(7);

                                if ( ((LA9_51 >= '0' && LA9_51 <= '9')||(LA9_51 >= 'A' && LA9_51 <= 'Z')||(LA9_51 >= 'a' && LA9_51 <= 'z')) ) {
                                    alt9=19;
                                }
                                else {
                                    alt9=14;
                                }
                            }
                            else {
                                alt9=19;
                            }
                        }
                        else {
                            alt9=19;
                        }
                    }
                    else {
                        alt9=19;
                    }
                }
                else {
                    alt9=19;
                }
            }
            else {
                alt9=19;
            }
            }
            break;
        case 'r':
            {
            int LA9_15 = input.LA(2);

            if ( (LA9_15=='e') ) {
                int LA9_28 = input.LA(3);

                if ( (LA9_28=='t') ) {
                    int LA9_36 = input.LA(4);

                    if ( (LA9_36=='u') ) {
                        int LA9_42 = input.LA(5);

                        if ( (LA9_42=='r') ) {
                            int LA9_47 = input.LA(6);

                            if ( (LA9_47=='n') ) {
                                int LA9_52 = input.LA(7);

                                if ( ((LA9_52 >= '0' && LA9_52 <= '9')||(LA9_52 >= 'A' && LA9_52 <= 'Z')||(LA9_52 >= 'a' && LA9_52 <= 'z')) ) {
                                    alt9=19;
                                }
                                else {
                                    alt9=15;
                                }
                            }
                            else {
                                alt9=19;
                            }
                        }
                        else {
                            alt9=19;
                        }
                    }
                    else {
                        alt9=19;
                    }
                }
                else {
                    alt9=19;
                }
            }
            else {
                alt9=19;
            }
            }
            break;
        case 'v':
            {
            int LA9_16 = input.LA(2);

            if ( (LA9_16=='o') ) {
                int LA9_29 = input.LA(3);

                if ( (LA9_29=='i') ) {
                    int LA9_37 = input.LA(4);

                    if ( (LA9_37=='d') ) {
                        int LA9_43 = input.LA(5);

                        if ( ((LA9_43 >= '0' && LA9_43 <= '9')||(LA9_43 >= 'A' && LA9_43 <= 'Z')||(LA9_43 >= 'a' && LA9_43 <= 'z')) ) {
                            alt9=19;
                        }
                        else {
                            alt9=16;
                        }
                    }
                    else {
                        alt9=19;
                    }
                }
                else {
                    alt9=19;
                }
            }
            else {
                alt9=19;
            }
            }
            break;
        case '{':
            {
            alt9=17;
            }
            break;
        case '}':
            {
            alt9=18;
            }
            break;
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
        case 'd':
        case 'e':
        case 'g':
        case 'h':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'q':
        case 's':
        case 't':
        case 'u':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt9=19;
            }
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt9=20;
            }
            break;
        case '\n':
        case '\r':
            {
            alt9=21;
            }
            break;
        case '\t':
        case '\f':
        case ' ':
            {
            alt9=22;
            }
            break;
        case '/':
            {
            int LA9_23 = input.LA(2);

            if ( (LA9_23=='/') ) {
                alt9=23;
            }
            else if ( (LA9_23=='*') ) {
                alt9=24;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 23, input);

                throw nvae;

            }
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 9, 0, input);

            throw nvae;

        }

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
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:118: Identifier
                {
                mIdentifier(); 


                }
                break;
            case 20 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:129: Integer
                {
                mInteger(); 


                }
                break;
            case 21 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:137: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 22 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:145: Whitespace
                {
                mWhitespace(); 


                }
                break;
            case 23 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:156: SingleLineComment
                {
                mSingleLineComment(); 


                }
                break;
            case 24 :
                // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:1:174: MultiLineComment
                {
                mMultiLineComment(); 


                }
                break;

        }

    }


 

}