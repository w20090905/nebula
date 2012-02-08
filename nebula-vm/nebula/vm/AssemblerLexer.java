package nebula.vm;
// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Assembler.g 2012-02-08 10:44:42

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class AssemblerLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int CHAR=4;
    public static final int CLASS=5;
    public static final int FIELD=6;
    public static final int FLOAT=7;
    public static final int FUNC=8;
    public static final int ID=9;
    public static final int INT=10;
    public static final int LETTER=11;
    public static final int LOWER=12;
    public static final int NEWLINE=13;
    public static final int REG=14;
    public static final int STRING=15;
    public static final int STR_CHARS=16;
    public static final int UPPER=17;
    public static final int WS=18;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public AssemblerLexer() {} 
    public AssemblerLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public AssemblerLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "D:\\Projects\\nebula\\nebula-vm\\Assembler.g"; }

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:2:7: ( ',' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:2:9: ','
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
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:3:7: ( '.class' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:3:9: '.class'
            {
            match(".class"); 



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
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:4:7: ( '.def' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:4:9: '.def'
            {
            match(".def"); 



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
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:5:7: ( '.field' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:5:9: '.field'
            {
            match(".field"); 



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
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:6:7: ( '.globals' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:6:9: '.globals'
            {
            match(".globals"); 



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
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:7:7: ( ':' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:7:9: ':'
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
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:8:7: ( '=' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:8:9: '='
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
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:9:7: ( 'args' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:9:9: 'args'
            {
            match("args"); 



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
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:10:7: ( 'locals' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:10:9: 'locals'
            {
            match("locals"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "FUNC"
    public final void mFUNC() throws RecognitionException {
        try {
            int _type = FUNC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken CLASS1=null;
            CommonToken ID2=null;

            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:82:6: ( CLASS '.' ID '()' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:82:8: CLASS '.' ID '()'
            {
            int CLASS1Start85 = getCharIndex();
            int CLASS1StartLine85 = getLine();
            int CLASS1StartCharPos85 = getCharPositionInLine();
            mCLASS(); 
            CLASS1 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, CLASS1Start85, getCharIndex()-1);
            CLASS1.setLine(CLASS1StartLine85);
            CLASS1.setCharPositionInLine(CLASS1StartCharPos85);


            match('.'); 

            int ID2Start89 = getCharIndex();
            int ID2StartLine89 = getLine();
            int ID2StartCharPos89 = getCharPositionInLine();
            mID(); 
            ID2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ID2Start89, getCharIndex()-1);
            ID2.setLine(ID2StartLine89);
            ID2.setCharPositionInLine(ID2StartCharPos89);


            match("()"); 



            setText((CLASS1!=null?CLASS1.getText():null) + "." + (ID2!=null?ID2.getText():null));

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FUNC"

    // $ANTLR start "FIELD"
    public final void mFIELD() throws RecognitionException {
        try {
            int _type = FIELD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken CLASS3=null;
            CommonToken ID4=null;

            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:84:7: ( CLASS '.' ID )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:84:9: CLASS '.' ID
            {
            int CLASS3Start101 = getCharIndex();
            int CLASS3StartLine101 = getLine();
            int CLASS3StartCharPos101 = getCharPositionInLine();
            mCLASS(); 
            CLASS3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, CLASS3Start101, getCharIndex()-1);
            CLASS3.setLine(CLASS3StartLine101);
            CLASS3.setCharPositionInLine(CLASS3StartCharPos101);


            match('.'); 

            int ID4Start105 = getCharIndex();
            int ID4StartLine105 = getLine();
            int ID4StartCharPos105 = getCharPositionInLine();
            mID(); 
            ID4 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ID4Start105, getCharIndex()-1);
            ID4.setLine(ID4StartLine105);
            ID4.setCharPositionInLine(ID4StartCharPos105);


            setText((CLASS3!=null?CLASS3.getText():null) + "." + (ID4!=null?ID4.getText():null));

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FIELD"

    // $ANTLR start "CLASS"
    public final void mCLASS() throws RecognitionException {
        try {
            int _type = CLASS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken ID5=null;

            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:86:7: ( '@' ID )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:86:9: '@' ID
            {
            match('@'); 

            int ID5Start117 = getCharIndex();
            int ID5StartLine117 = getLine();
            int ID5StartCharPos117 = getCharPositionInLine();
            mID(); 
            ID5 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ID5Start117, getCharIndex()-1);
            ID5.setLine(ID5StartLine117);
            ID5.setCharPositionInLine(ID5StartCharPos117);


            setText((ID5!=null?ID5.getText():null));

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CLASS"

    // $ANTLR start "REG"
    public final void mREG() throws RecognitionException {
        try {
            int _type = REG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken INT6=null;

            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:88:5: ( 'r' INT )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:88:9: 'r' INT
            {
            match('r'); 

            int INT6Start131 = getCharIndex();
            int INT6StartLine131 = getLine();
            int INT6StartCharPos131 = getCharPositionInLine();
            mINT(); 
            INT6 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, INT6Start131, getCharIndex()-1);
            INT6.setLine(INT6StartLine131);
            INT6.setCharPositionInLine(INT6StartCharPos131);


            setText((INT6!=null?INT6.getText():null));

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REG"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:91:5: ( LETTER ( LETTER | '0' .. '9' )* )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:91:9: LETTER ( LETTER | '0' .. '9' )*
            {
            mLETTER(); 


            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:91:16: ( LETTER | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:
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

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:96:5: ( LOWER | UPPER )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:
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
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:97:15: ( 'a' .. 'z' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:
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
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:98:15: ( 'A' .. 'Z' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:
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

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:100:5: ( ( '-' )? ( '0' .. '9' )+ )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:100:9: ( '-' )? ( '0' .. '9' )+
            {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:100:9: ( '-' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='-') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:100:9: '-'
                    {
                    match('-'); 

                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:100:14: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:
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
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
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

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:102:5: ( '\\'' . '\\'' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:102:9: '\\'' . '\\''
            {
            match('\''); 

            matchAny(); 

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken STR_CHARS7=null;

            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:104:7: ( '\\\"' STR_CHARS '\\\"' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:104:9: '\\\"' STR_CHARS '\\\"'
            {
            match('\"'); 

            int STR_CHARS7Start236 = getCharIndex();
            int STR_CHARS7StartLine236 = getLine();
            int STR_CHARS7StartCharPos236 = getCharPositionInLine();
            mSTR_CHARS(); 
            STR_CHARS7 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, STR_CHARS7Start236, getCharIndex()-1);
            STR_CHARS7.setLine(STR_CHARS7StartLine236);
            STR_CHARS7.setCharPositionInLine(STR_CHARS7StartCharPos236);


            match('\"'); 

            setText((STR_CHARS7!=null?STR_CHARS7.getText():null));

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "STR_CHARS"
    public final void mSTR_CHARS() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:106:20: ( (~ '\"' )* )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:106:22: (~ '\"' )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:106:22: (~ '\"' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '\u0000' && LA4_0 <= '!')||(LA4_0 >= '#' && LA4_0 <= '\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
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
            	    break loop4;
                }
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STR_CHARS"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:109:5: ( INT '.' ( INT )* | '.' ( INT )+ )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='-'||(LA7_0 >= '0' && LA7_0 <= '9')) ) {
                alt7=1;
            }
            else if ( (LA7_0=='.') ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:109:9: INT '.' ( INT )*
                    {
                    mINT(); 


                    match('.'); 

                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:109:17: ( INT )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='-'||(LA5_0 >= '0' && LA5_0 <= '9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:109:17: INT
                    	    {
                    	    mINT(); 


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:110:9: '.' ( INT )+
                    {
                    match('.'); 

                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:110:13: ( INT )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='-'||(LA6_0 >= '0' && LA6_0 <= '9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:110:13: INT
                    	    {
                    	    mINT(); 


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:113:5: ( ( ' ' | '\\t' )+ )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:113:9: ( ' ' | '\\t' )+
            {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:113:9: ( ' ' | '\\t' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='\t'||LA8_0==' ') ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:
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
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:116:5: ( ( ';' ( . )* )? ( '\\r' )? '\\n' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:116:9: ( ';' ( . )* )? ( '\\r' )? '\\n'
            {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:116:9: ( ';' ( . )* )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==';') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:116:10: ';' ( . )*
                    {
                    match(';'); 

                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:116:14: ( . )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0=='\r') ) {
                            alt9=2;
                        }
                        else if ( (LA9_0=='\n') ) {
                            alt9=2;
                        }
                        else if ( ((LA9_0 >= '\u0000' && LA9_0 <= '\t')||(LA9_0 >= '\u000B' && LA9_0 <= '\f')||(LA9_0 >= '\u000E' && LA9_0 <= '\uFFFF')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:116:14: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:116:19: ( '\\r' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:116:19: '\\r'
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

    public void mTokens() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:8: ( T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | FUNC | FIELD | CLASS | REG | ID | INT | CHAR | STRING | FLOAT | WS | NEWLINE )
        int alt12=20;
        alt12 = dfa12.predict(input);
        switch (alt12) {
            case 1 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:10: T__19
                {
                mT__19(); 


                }
                break;
            case 2 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:16: T__20
                {
                mT__20(); 


                }
                break;
            case 3 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:22: T__21
                {
                mT__21(); 


                }
                break;
            case 4 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:28: T__22
                {
                mT__22(); 


                }
                break;
            case 5 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:34: T__23
                {
                mT__23(); 


                }
                break;
            case 6 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:40: T__24
                {
                mT__24(); 


                }
                break;
            case 7 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:46: T__25
                {
                mT__25(); 


                }
                break;
            case 8 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:52: T__26
                {
                mT__26(); 


                }
                break;
            case 9 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:58: T__27
                {
                mT__27(); 


                }
                break;
            case 10 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:64: FUNC
                {
                mFUNC(); 


                }
                break;
            case 11 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:69: FIELD
                {
                mFIELD(); 


                }
                break;
            case 12 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:75: CLASS
                {
                mCLASS(); 


                }
                break;
            case 13 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:81: REG
                {
                mREG(); 


                }
                break;
            case 14 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:85: ID
                {
                mID(); 


                }
                break;
            case 15 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:88: INT
                {
                mINT(); 


                }
                break;
            case 16 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:92: CHAR
                {
                mCHAR(); 


                }
                break;
            case 17 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:97: STRING
                {
                mSTRING(); 


                }
                break;
            case 18 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:104: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 19 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:110: WS
                {
                mWS(); 


                }
                break;
            case 20 :
                // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:1:113: NEWLINE
                {
                mNEWLINE(); 


                }
                break;

        }

    }


    protected DFA12 dfa12 = new DFA12(this);
    static final String DFA12_eotS =
        "\5\uffff\2\11\1\uffff\1\11\2\uffff\1\32\11\uffff\2\11\1\35\1\uffff"+
        "\1\30\1\uffff\2\11\1\uffff\1\35\1\uffff\1\43\1\11\1\45\1\uffff\1"+
        "\11\1\uffff\1\45\1\uffff\1\51\1\uffff";
    static final String DFA12_eofS =
        "\52\uffff";
    static final String DFA12_minS =
        "\1\11\1\uffff\1\55\2\uffff\1\162\1\157\1\101\1\55\1\uffff\1\60\1"+
        "\56\11\uffff\1\147\1\143\1\56\1\uffff\1\60\1\uffff\1\163\1\141\1"+
        "\uffff\1\56\1\101\1\60\1\154\1\50\1\uffff\1\163\1\uffff\1\50\1\uffff"+
        "\1\60\1\uffff";
    static final String DFA12_maxS =
        "\1\172\1\uffff\1\147\2\uffff\1\162\1\157\1\172\1\71\1\uffff\2\71"+
        "\11\uffff\1\147\1\143\1\172\1\uffff\1\172\1\uffff\1\163\1\141\1"+
        "\uffff\3\172\1\154\1\172\1\uffff\1\163\1\uffff\1\172\1\uffff\1\172"+
        "\1\uffff";
    static final String DFA12_acceptS =
        "\1\uffff\1\1\1\uffff\1\6\1\7\4\uffff\1\16\2\uffff\1\20\1\21\1\23"+
        "\1\24\1\2\1\3\1\4\1\5\1\22\3\uffff\1\15\1\uffff\1\17\2\uffff\1\14"+
        "\5\uffff\1\10\1\uffff\1\13\1\uffff\1\12\1\uffff\1\11";
    static final String DFA12_specialS =
        "\52\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\16\1\17\2\uffff\1\17\22\uffff\1\16\1\uffff\1\15\4\uffff\1"+
            "\14\4\uffff\1\1\1\12\1\2\1\uffff\12\13\1\3\1\17\1\uffff\1\4"+
            "\2\uffff\1\7\32\11\6\uffff\1\5\12\11\1\6\5\11\1\10\10\11",
            "",
            "\1\24\2\uffff\12\24\51\uffff\1\20\1\21\1\uffff\1\22\1\23",
            "",
            "",
            "\1\25",
            "\1\26",
            "\32\27\6\uffff\32\27",
            "\1\30\2\uffff\12\31",
            "",
            "\12\13",
            "\1\24\1\uffff\12\13",
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
            "\1\37\1\uffff\12\36\7\uffff\32\36\6\uffff\32\36",
            "",
            "\12\31\7\uffff\32\11\6\uffff\32\11",
            "",
            "\1\40",
            "\1\41",
            "",
            "\1\37\1\uffff\12\36\7\uffff\32\36\6\uffff\32\36",
            "\32\42\6\uffff\32\42",
            "\12\11\7\uffff\32\11\6\uffff\32\11",
            "\1\44",
            "\1\47\7\uffff\12\46\7\uffff\32\46\6\uffff\32\46",
            "",
            "\1\50",
            "",
            "\1\47\7\uffff\12\46\7\uffff\32\46\6\uffff\32\46",
            "",
            "\12\11\7\uffff\32\11\6\uffff\32\11",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | FUNC | FIELD | CLASS | REG | ID | INT | CHAR | STRING | FLOAT | WS | NEWLINE );";
        }
    }
 

}