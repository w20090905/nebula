package nebula.vm;
// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Assembler.g 2012-02-08 10:44:41

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/** A generic bytecode assembler whose instructions take 0..3 operands.
 *  Instruction set is dictated externally with a String[].  Implement
 *  specifics by subclassing and defining gen() methods. Comments start
 *  with ';' and all instructions end with '\n'.  Handles both register (rN)
 *  and stack-based assembly instructions.  Labels are "ID:".  "main:" label
 *  is where we start execution.  Use .globals and .def for global data
 *  and function definitions, respectively.
 */
@SuppressWarnings({"all", "warnings", "unchecked"})
public class AssemblerParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CHAR", "CLASS", "FIELD", "FLOAT", "FUNC", "ID", "INT", "LETTER", "LOWER", "NEWLINE", "REG", "STRING", "STR_CHARS", "UPPER", "WS", "','", "'.class'", "'.def'", "'.field'", "'.globals'", "':'", "'='", "'args'", "'locals'"
    };

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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public AssemblerParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public AssemblerParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return AssemblerParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\Projects\\nebula\\nebula-vm\\Assembler.g"; }


        // Define the functionality required by the parser for code generation
        protected void gen(Token instrToken) {;}
        protected void gen(Token instrToken, Token operandToken) {;}
        protected void gen(Token instrToken, Token oToken1, Token oToken2) {;}
        protected void gen(Token instrToken, Token oToken1, Token oToken2, Token oToken3) {;}
        protected void checkForUnresolvedReferences() {;}
        protected void defineFunction(Token idToken, int nargs, int nlocals) {;}
        protected void finishFunction(){};
        protected void defineDataSize(int n) {;}
        protected void defineLabel(Token idToken) {;}
        
        protected void defineClass(Token idToken){;}
        protected void defineField(Token idToken){;}
        



    // $ANTLR start "program"
    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:31:1: program : ( globals )? ( classDeclaration )? ( fieldDeclaration )* ( functionDeclaration | instr | label | ( NEWLINE )+ )+ ;
    public final void program() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:32:5: ( ( globals )? ( classDeclaration )? ( fieldDeclaration )* ( functionDeclaration | instr | label | ( NEWLINE )+ )+ )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:32:9: ( globals )? ( classDeclaration )? ( fieldDeclaration )* ( functionDeclaration | instr | label | ( NEWLINE )+ )+
            {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:32:9: ( globals )?
            int alt1=2;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:32:9: globals
                    {
                    pushFollow(FOLLOW_globals_in_program27);
                    globals();

                    state._fsp--;


                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:32:18: ( classDeclaration )?
            int alt2=2;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:32:18: classDeclaration
                    {
                    pushFollow(FOLLOW_classDeclaration_in_program30);
                    classDeclaration();

                    state._fsp--;


                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:32:36: ( fieldDeclaration )*
            loop3:
            do {
                int alt3=2;
                alt3 = dfa3.predict(input);
                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:32:36: fieldDeclaration
            	    {
            	    pushFollow(FOLLOW_fieldDeclaration_in_program33);
            	    fieldDeclaration();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:33:9: ( functionDeclaration | instr | label | ( NEWLINE )+ )+
            int cnt5=0;
            loop5:
            do {
                int alt5=5;
                switch ( input.LA(1) ) {
                case 21:
                    {
                    alt5=1;
                    }
                    break;
                case ID:
                    {
                    int LA5_3 = input.LA(2);

                    if ( ((LA5_3 >= CHAR && LA5_3 <= INT)||(LA5_3 >= NEWLINE && LA5_3 <= STRING)) ) {
                        alt5=2;
                    }
                    else if ( (LA5_3==24) ) {
                        alt5=3;
                    }


                    }
                    break;
                case NEWLINE:
                    {
                    alt5=4;
                    }
                    break;

                }

                switch (alt5) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:33:11: functionDeclaration
            	    {
            	    pushFollow(FOLLOW_functionDeclaration_in_program46);
            	    functionDeclaration();

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:33:33: instr
            	    {
            	    pushFollow(FOLLOW_instr_in_program50);
            	    instr();

            	    state._fsp--;


            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:33:41: label
            	    {
            	    pushFollow(FOLLOW_label_in_program54);
            	    label();

            	    state._fsp--;


            	    }
            	    break;
            	case 4 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:33:49: ( NEWLINE )+
            	    {
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:33:49: ( NEWLINE )+
            	    int cnt4=0;
            	    loop4:
            	    do {
            	        int alt4=2;
            	        int LA4_0 = input.LA(1);

            	        if ( (LA4_0==NEWLINE) ) {
            	            alt4=1;
            	        }


            	        switch (alt4) {
            	    	case 1 :
            	    	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:33:49: NEWLINE
            	    	    {
            	    	    match(input,NEWLINE,FOLLOW_NEWLINE_in_program58); 

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


            	    finishFunction();

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            checkForUnresolvedReferences();

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



    // $ANTLR start "globals"
    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:39:1: globals : ( NEWLINE )* '.globals' INT NEWLINE ;
    public final void globals() throws RecognitionException {
        Token INT1=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:39:9: ( ( NEWLINE )* '.globals' INT NEWLINE )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:39:11: ( NEWLINE )* '.globals' INT NEWLINE
            {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:39:11: ( NEWLINE )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==NEWLINE) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:39:11: NEWLINE
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_globals91); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            match(input,23,FOLLOW_23_in_globals94); 

            INT1=(Token)match(input,INT,FOLLOW_INT_in_globals96); 

            match(input,NEWLINE,FOLLOW_NEWLINE_in_globals98); 

            defineDataSize((INT1!=null?Integer.valueOf(INT1.getText()):0));

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
    // $ANTLR end "globals"



    // $ANTLR start "classDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:42:1: classDeclaration : ( NEWLINE )* '.class' ID NEWLINE ;
    public final void classDeclaration() throws RecognitionException {
        Token ID2=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:42:18: ( ( NEWLINE )* '.class' ID NEWLINE )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:42:20: ( NEWLINE )* '.class' ID NEWLINE
            {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:42:20: ( NEWLINE )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==NEWLINE) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:42:20: NEWLINE
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_classDeclaration110); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            match(input,20,FOLLOW_20_in_classDeclaration113); 

            ID2=(Token)match(input,ID,FOLLOW_ID_in_classDeclaration115); 

            match(input,NEWLINE,FOLLOW_NEWLINE_in_classDeclaration117); 

            defineClass(ID2);

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
    // $ANTLR end "classDeclaration"



    // $ANTLR start "fieldDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:43:1: fieldDeclaration : ( NEWLINE )* '.field' ID NEWLINE ;
    public final void fieldDeclaration() throws RecognitionException {
        Token ID3=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:43:18: ( ( NEWLINE )* '.field' ID NEWLINE )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:43:20: ( NEWLINE )* '.field' ID NEWLINE
            {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:43:20: ( NEWLINE )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==NEWLINE) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:43:20: NEWLINE
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_fieldDeclaration127); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            match(input,22,FOLLOW_22_in_fieldDeclaration130); 

            ID3=(Token)match(input,ID,FOLLOW_ID_in_fieldDeclaration132); 

            match(input,NEWLINE,FOLLOW_NEWLINE_in_fieldDeclaration134); 

            defineField(ID3);

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
    // $ANTLR end "fieldDeclaration"



    // $ANTLR start "functionDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:47:1: functionDeclaration : '.def' name= ID ':' 'args' '=' a= INT ',' 'locals' '=' lo= INT NEWLINE ;
    public final void functionDeclaration() throws RecognitionException {
        Token name=null;
        Token a=null;
        Token lo=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:48:5: ( '.def' name= ID ':' 'args' '=' a= INT ',' 'locals' '=' lo= INT NEWLINE )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:48:9: '.def' name= ID ':' 'args' '=' a= INT ',' 'locals' '=' lo= INT NEWLINE
            {
            match(input,21,FOLLOW_21_in_functionDeclaration153); 

            name=(Token)match(input,ID,FOLLOW_ID_in_functionDeclaration157); 

            match(input,24,FOLLOW_24_in_functionDeclaration159); 

            match(input,26,FOLLOW_26_in_functionDeclaration161); 

            match(input,25,FOLLOW_25_in_functionDeclaration163); 

            a=(Token)match(input,INT,FOLLOW_INT_in_functionDeclaration167); 

            match(input,19,FOLLOW_19_in_functionDeclaration169); 

            match(input,27,FOLLOW_27_in_functionDeclaration171); 

            match(input,25,FOLLOW_25_in_functionDeclaration173); 

            lo=(Token)match(input,INT,FOLLOW_INT_in_functionDeclaration177); 

            match(input,NEWLINE,FOLLOW_NEWLINE_in_functionDeclaration179); 

            defineFunction(name, (a!=null?Integer.valueOf(a.getText()):0), (lo!=null?Integer.valueOf(lo.getText()):0));

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
    // $ANTLR end "functionDeclaration"



    // $ANTLR start "instr"
    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:54:1: instr : ( ID NEWLINE | ID operand NEWLINE | ID a= operand ',' b= operand NEWLINE | ID a= operand ',' b= operand ',' c= operand NEWLINE );
    public final void instr() throws RecognitionException {
        Token ID4=null;
        Token ID5=null;
        Token ID7=null;
        Token ID8=null;
        AssemblerParser.operand_return a =null;

        AssemblerParser.operand_return b =null;

        AssemblerParser.operand_return c =null;

        AssemblerParser.operand_return operand6 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:55:5: ( ID NEWLINE | ID operand NEWLINE | ID a= operand ',' b= operand NEWLINE | ID a= operand ',' b= operand ',' c= operand NEWLINE )
            int alt9=4;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==ID) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==NEWLINE) ) {
                    alt9=1;
                }
                else if ( ((LA9_1 >= CHAR && LA9_1 <= INT)||(LA9_1 >= REG && LA9_1 <= STRING)) ) {
                    int LA9_3 = input.LA(3);

                    if ( (LA9_3==NEWLINE) ) {
                        alt9=2;
                    }
                    else if ( (LA9_3==19) ) {
                        int LA9_5 = input.LA(4);

                        if ( ((LA9_5 >= CHAR && LA9_5 <= INT)||(LA9_5 >= REG && LA9_5 <= STRING)) ) {
                            int LA9_6 = input.LA(5);

                            if ( (LA9_6==NEWLINE) ) {
                                alt9=3;
                            }
                            else if ( (LA9_6==19) ) {
                                alt9=4;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 9, 6, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 9, 5, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 3, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

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
                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:55:9: ID NEWLINE
                    {
                    ID4=(Token)match(input,ID,FOLLOW_ID_in_instr210); 

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_instr212); 

                    gen(ID4);

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:56:9: ID operand NEWLINE
                    {
                    ID5=(Token)match(input,ID,FOLLOW_ID_in_instr248); 

                    pushFollow(FOLLOW_operand_in_instr250);
                    operand6=operand();

                    state._fsp--;


                    match(input,NEWLINE,FOLLOW_NEWLINE_in_instr252); 

                    gen(ID5,(operand6!=null?((Token)operand6.start):null));

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:57:9: ID a= operand ',' b= operand NEWLINE
                    {
                    ID7=(Token)match(input,ID,FOLLOW_ID_in_instr280); 

                    pushFollow(FOLLOW_operand_in_instr284);
                    a=operand();

                    state._fsp--;


                    match(input,19,FOLLOW_19_in_instr286); 

                    pushFollow(FOLLOW_operand_in_instr290);
                    b=operand();

                    state._fsp--;


                    match(input,NEWLINE,FOLLOW_NEWLINE_in_instr292); 

                    gen(ID7,(a!=null?((Token)a.start):null),(b!=null?((Token)b.start):null));

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:58:9: ID a= operand ',' b= operand ',' c= operand NEWLINE
                    {
                    ID8=(Token)match(input,ID,FOLLOW_ID_in_instr304); 

                    pushFollow(FOLLOW_operand_in_instr308);
                    a=operand();

                    state._fsp--;


                    match(input,19,FOLLOW_19_in_instr310); 

                    pushFollow(FOLLOW_operand_in_instr314);
                    b=operand();

                    state._fsp--;


                    match(input,19,FOLLOW_19_in_instr316); 

                    pushFollow(FOLLOW_operand_in_instr320);
                    c=operand();

                    state._fsp--;


                    match(input,NEWLINE,FOLLOW_NEWLINE_in_instr322); 

                    gen(ID8,(a!=null?((Token)a.start):null),(b!=null?((Token)b.start):null),(c!=null?((Token)c.start):null));

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
    // $ANTLR end "instr"


    public static class operand_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "operand"
    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:64:1: operand : ( FUNC | FIELD | CLASS | ID | REG | INT | CHAR | STRING | FLOAT );
    public final AssemblerParser.operand_return operand() throws RecognitionException {
        AssemblerParser.operand_return retval = new AssemblerParser.operand_return();
        retval.start = input.LT(1);


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:65:5: ( FUNC | FIELD | CLASS | ID | REG | INT | CHAR | STRING | FLOAT )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:
            {
            if ( (input.LA(1) >= CHAR && input.LA(1) <= INT)||(input.LA(1) >= REG && input.LA(1) <= STRING) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


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
    // $ANTLR end "operand"



    // $ANTLR start "label"
    // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:78:1: label : ID ':' ;
    public final void label() throws RecognitionException {
        Token ID9=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:79:5: ( ID ':' )
            // D:\\Projects\\nebula\\nebula-vm\\Assembler.g:79:9: ID ':'
            {
            ID9=(Token)match(input,ID,FOLLOW_ID_in_label462); 

            match(input,24,FOLLOW_24_in_label464); 

            defineLabel(ID9);

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
    // $ANTLR end "label"

    // Delegated rules


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA2 dfa2 = new DFA2(this);
    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA1_eotS =
        "\4\uffff";
    static final String DFA1_eofS =
        "\1\uffff\1\3\2\uffff";
    static final String DFA1_minS =
        "\2\11\2\uffff";
    static final String DFA1_maxS =
        "\2\27\2\uffff";
    static final String DFA1_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA1_specialS =
        "\4\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\3\3\uffff\1\1\6\uffff\3\3\1\2",
            "\1\3\3\uffff\1\1\6\uffff\3\3\1\2",
            "",
            ""
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "32:9: ( globals )?";
        }
    }
    static final String DFA2_eotS =
        "\4\uffff";
    static final String DFA2_eofS =
        "\1\uffff\1\3\2\uffff";
    static final String DFA2_minS =
        "\2\11\2\uffff";
    static final String DFA2_maxS =
        "\2\26\2\uffff";
    static final String DFA2_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA2_specialS =
        "\4\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\3\3\uffff\1\1\6\uffff\1\2\2\3",
            "\1\3\3\uffff\1\1\6\uffff\1\2\2\3",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "32:18: ( classDeclaration )?";
        }
    }
    static final String DFA3_eotS =
        "\4\uffff";
    static final String DFA3_eofS =
        "\2\uffff\1\1\1\uffff";
    static final String DFA3_minS =
        "\1\11\1\uffff\1\11\1\uffff";
    static final String DFA3_maxS =
        "\1\26\1\uffff\1\26\1\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\2\1\uffff\1\1";
    static final String DFA3_specialS =
        "\4\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\3\uffff\1\2\7\uffff\1\1\1\3",
            "",
            "\1\1\3\uffff\1\2\7\uffff\1\1\1\3",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "()* loopback of 32:36: ( fieldDeclaration )*";
        }
    }
 

    public static final BitSet FOLLOW_globals_in_program27 = new BitSet(new long[]{0x0000000000702200L});
    public static final BitSet FOLLOW_classDeclaration_in_program30 = new BitSet(new long[]{0x0000000000602200L});
    public static final BitSet FOLLOW_fieldDeclaration_in_program33 = new BitSet(new long[]{0x0000000000602200L});
    public static final BitSet FOLLOW_functionDeclaration_in_program46 = new BitSet(new long[]{0x0000000000202202L});
    public static final BitSet FOLLOW_instr_in_program50 = new BitSet(new long[]{0x0000000000202202L});
    public static final BitSet FOLLOW_label_in_program54 = new BitSet(new long[]{0x0000000000202202L});
    public static final BitSet FOLLOW_NEWLINE_in_program58 = new BitSet(new long[]{0x0000000000202202L});
    public static final BitSet FOLLOW_NEWLINE_in_globals91 = new BitSet(new long[]{0x0000000000802000L});
    public static final BitSet FOLLOW_23_in_globals94 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_INT_in_globals96 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_NEWLINE_in_globals98 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_classDeclaration110 = new BitSet(new long[]{0x0000000000102000L});
    public static final BitSet FOLLOW_20_in_classDeclaration113 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_classDeclaration115 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_NEWLINE_in_classDeclaration117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_fieldDeclaration127 = new BitSet(new long[]{0x0000000000402000L});
    public static final BitSet FOLLOW_22_in_fieldDeclaration130 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_fieldDeclaration132 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_NEWLINE_in_fieldDeclaration134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_functionDeclaration153 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_functionDeclaration157 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_functionDeclaration159 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_functionDeclaration161 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_functionDeclaration163 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_INT_in_functionDeclaration167 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_functionDeclaration169 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_functionDeclaration171 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_functionDeclaration173 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_INT_in_functionDeclaration177 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_NEWLINE_in_functionDeclaration179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_instr210 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_NEWLINE_in_instr212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_instr248 = new BitSet(new long[]{0x000000000000C7F0L});
    public static final BitSet FOLLOW_operand_in_instr250 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_NEWLINE_in_instr252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_instr280 = new BitSet(new long[]{0x000000000000C7F0L});
    public static final BitSet FOLLOW_operand_in_instr284 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_instr286 = new BitSet(new long[]{0x000000000000C7F0L});
    public static final BitSet FOLLOW_operand_in_instr290 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_NEWLINE_in_instr292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_instr304 = new BitSet(new long[]{0x000000000000C7F0L});
    public static final BitSet FOLLOW_operand_in_instr308 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_instr310 = new BitSet(new long[]{0x000000000000C7F0L});
    public static final BitSet FOLLOW_operand_in_instr314 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_instr316 = new BitSet(new long[]{0x000000000000C7F0L});
    public static final BitSet FOLLOW_operand_in_instr320 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_NEWLINE_in_instr322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_label462 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_label464 = new BitSet(new long[]{0x0000000000000002L});

}