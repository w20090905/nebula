// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-02 22:07:06

package nebula.vm;
import nebula.vm.VariableSymbol;
import nebula.vm.Type;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class NebulaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Digit", "Identifier", "Integer", "Letter", "MultiLineComment", "NEWLINE", "SingleLineComment", "Whitespace", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "':'", "';'", "'='", "'['", "']'", "'class'", "'decimal'", "'int'", "'public'", "'return'", "'super'", "'this'", "'void'", "'{'", "'}'"
    };

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
    public static final int T__33=33;
    public static final int Digit=4;
    public static final int Identifier=5;
    public static final int Integer=6;
    public static final int Letter=7;
    public static final int MultiLineComment=8;
    public static final int NEWLINE=9;
    public static final int SingleLineComment=10;
    public static final int Whitespace=11;

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

      protected void enterClass(String name) {};
      protected void exitClass() {};
      
      protected void enterFunction(String name,Type type) {;};
      protected void exitFunction() {;};
      
      protected void defineField(String name,Type type){;};
      
      protected Type resolveType(String name){return null;};
        
      protected VariableSymbol resolve(String name) {return null;};
      protected VariableSymbol defineVariable(String name) {return null;};
      protected VariableSymbol defineInt(String name) {return null;};
        
      protected VariableSymbol eval(VariableSymbol a) {return null;};
      protected VariableSymbol evalSet(String id,VariableSymbol b) {return null;};

      protected VariableSymbol add(VariableSymbol a, VariableSymbol b) {return null;};
      protected VariableSymbol sub(VariableSymbol a, VariableSymbol b) {return null;};
      protected VariableSymbol mul(VariableSymbol a, VariableSymbol b) {return null;};  
      protected VariableSymbol load(VariableSymbol a, VariableSymbol b) {return null;};




    // $ANTLR start "compilationUnit"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:44:1: compilationUnit : ( classDefinition | varDeclaration | methodDeclaration )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:45:5: ( ( classDefinition | varDeclaration | methodDeclaration )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:45:9: ( classDefinition | varDeclaration | methodDeclaration )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:45:9: ( classDefinition | varDeclaration | methodDeclaration )+
            int cnt1=0;
            loop1:
            do {
                int alt1=4;
                switch ( input.LA(1) ) {
                case 24:
                    {
                    alt1=1;
                    }
                    break;
                case 25:
                    {
                    int LA1_3 = input.LA(2);

                    if ( (LA1_3==Identifier) ) {
                        int LA1_7 = input.LA(3);

                        if ( (LA1_7==12) ) {
                            alt1=3;
                        }
                        else if ( ((LA1_7 >= 20 && LA1_7 <= 21)) ) {
                            alt1=2;
                        }


                    }


                    }
                    break;
                case 26:
                    {
                    int LA1_4 = input.LA(2);

                    if ( (LA1_4==Identifier) ) {
                        int LA1_7 = input.LA(3);

                        if ( (LA1_7==12) ) {
                            alt1=3;
                        }
                        else if ( ((LA1_7 >= 20 && LA1_7 <= 21)) ) {
                            alt1=2;
                        }


                    }


                    }
                    break;
                case 31:
                    {
                    int LA1_5 = input.LA(2);

                    if ( (LA1_5==Identifier) ) {
                        int LA1_7 = input.LA(3);

                        if ( (LA1_7==12) ) {
                            alt1=3;
                        }
                        else if ( ((LA1_7 >= 20 && LA1_7 <= 21)) ) {
                            alt1=2;
                        }


                    }


                    }
                    break;
                case Identifier:
                    {
                    int LA1_6 = input.LA(2);

                    if ( (LA1_6==Identifier) ) {
                        int LA1_7 = input.LA(3);

                        if ( (LA1_7==12) ) {
                            alt1=3;
                        }
                        else if ( ((LA1_7 >= 20 && LA1_7 <= 21)) ) {
                            alt1=2;
                        }


                    }


                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:45:11: classDefinition
            	    {
            	    pushFollow(FOLLOW_classDefinition_in_compilationUnit55);
            	    classDefinition();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:45:29: varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_compilationUnit59);
            	    varDeclaration();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:45:46: methodDeclaration
            	    {
            	    pushFollow(FOLLOW_methodDeclaration_in_compilationUnit63);
            	    methodDeclaration();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            match(input,EOF,FOLLOW_EOF_in_compilationUnit68); if (state.failed) return ;

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
    // $ANTLR end "compilationUnit"



    // $ANTLR start "classDefinition"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:49:1: classDefinition : 'class' Identifier ( superClass )? '{' ( classMember )+ '}' ';' ;
    public final void classDefinition() throws RecognitionException {
        Token Identifier1=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:50:5: ( 'class' Identifier ( superClass )? '{' ( classMember )+ '}' ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:50:9: 'class' Identifier ( superClass )? '{' ( classMember )+ '}' ';'
            {
            match(input,24,FOLLOW_24_in_classDefinition88); if (state.failed) return ;

            Identifier1=(Token)match(input,Identifier,FOLLOW_Identifier_in_classDefinition90); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:50:28: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==19) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:50:28: superClass
                    {
                    pushFollow(FOLLOW_superClass_in_classDefinition92);
                    superClass();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {enterClass((Identifier1!=null?Identifier1.getText():null));}

            match(input,32,FOLLOW_32_in_classDefinition120); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:52:14: ( classMember )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==Identifier||(LA3_0 >= 25 && LA3_0 <= 27)||LA3_0==31) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:52:14: classMember
            	    {
            	    pushFollow(FOLLOW_classMember_in_classDefinition122);
            	    classMember();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            match(input,33,FOLLOW_33_in_classDefinition125); if (state.failed) return ;

            match(input,20,FOLLOW_20_in_classDefinition127); if (state.failed) return ;

            if ( state.backtracking==0 ) {exitClass();}

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
    // $ANTLR end "classDefinition"



    // $ANTLR start "superClass"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:56:1: superClass : ':' 'public' Identifier ;
    public final void superClass() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:57:3: ( ':' 'public' Identifier )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:57:5: ':' 'public' Identifier
            {
            match(input,19,FOLLOW_19_in_superClass169); if (state.failed) return ;

            match(input,27,FOLLOW_27_in_superClass171); if (state.failed) return ;

            match(input,Identifier,FOLLOW_Identifier_in_superClass173); if (state.failed) return ;

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
    // $ANTLR end "superClass"



    // $ANTLR start "classMember"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:61:1: classMember : ( type e= Identifier ( '=' expression )? ';' | methodDeclaration | 'public' ':' );
    public final void classMember() throws RecognitionException {
        Token e=null;
        Type type2 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:62:3: ( type e= Identifier ( '=' expression )? ';' | methodDeclaration | 'public' ':' )
            int alt5=3;
            switch ( input.LA(1) ) {
            case 25:
                {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==Identifier) ) {
                    int LA5_6 = input.LA(3);

                    if ( (LA5_6==12) ) {
                        alt5=2;
                    }
                    else if ( ((LA5_6 >= 20 && LA5_6 <= 21)) ) {
                        alt5=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 6, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;

                }
                }
                break;
            case 26:
                {
                int LA5_2 = input.LA(2);

                if ( (LA5_2==Identifier) ) {
                    int LA5_6 = input.LA(3);

                    if ( (LA5_6==12) ) {
                        alt5=2;
                    }
                    else if ( ((LA5_6 >= 20 && LA5_6 <= 21)) ) {
                        alt5=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 6, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;

                }
                }
                break;
            case 31:
                {
                int LA5_3 = input.LA(2);

                if ( (LA5_3==Identifier) ) {
                    int LA5_6 = input.LA(3);

                    if ( (LA5_6==12) ) {
                        alt5=2;
                    }
                    else if ( ((LA5_6 >= 20 && LA5_6 <= 21)) ) {
                        alt5=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 6, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 3, input);

                    throw nvae;

                }
                }
                break;
            case Identifier:
                {
                int LA5_4 = input.LA(2);

                if ( (LA5_4==Identifier) ) {
                    int LA5_6 = input.LA(3);

                    if ( (LA5_6==12) ) {
                        alt5=2;
                    }
                    else if ( ((LA5_6 >= 20 && LA5_6 <= 21)) ) {
                        alt5=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 6, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 4, input);

                    throw nvae;

                }
                }
                break;
            case 27:
                {
                alt5=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:62:5: type e= Identifier ( '=' expression )? ';'
                    {
                    pushFollow(FOLLOW_type_in_classMember188);
                    type2=type();

                    state._fsp--;
                    if (state.failed) return ;

                    e=(Token)match(input,Identifier,FOLLOW_Identifier_in_classMember192); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:62:23: ( '=' expression )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==21) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:62:24: '=' expression
                            {
                            match(input,21,FOLLOW_21_in_classMember195); if (state.failed) return ;

                            pushFollow(FOLLOW_expression_in_classMember197);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,20,FOLLOW_20_in_classMember201); if (state.failed) return ;

                    if ( state.backtracking==0 ) {defineField((e!=null?e.getText():null),type2);}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:63:5: methodDeclaration
                    {
                    pushFollow(FOLLOW_methodDeclaration_in_classMember209);
                    methodDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:64:5: 'public' ':'
                    {
                    match(input,27,FOLLOW_27_in_classMember215); if (state.failed) return ;

                    match(input,19,FOLLOW_19_in_classMember217); if (state.failed) return ;

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
    // $ANTLR end "classMember"



    // $ANTLR start "methodDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:68:1: methodDeclaration : type Identifier '(' ( formalParameters )? ')' block ;
    public final void methodDeclaration() throws RecognitionException {
        Token Identifier3=null;
        Type type4 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:69:5: ( type Identifier '(' ( formalParameters )? ')' block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:69:9: type Identifier '(' ( formalParameters )? ')' block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration238);
            type4=type();

            state._fsp--;
            if (state.failed) return ;

            Identifier3=(Token)match(input,Identifier,FOLLOW_Identifier_in_methodDeclaration240); if (state.failed) return ;

            match(input,12,FOLLOW_12_in_methodDeclaration242); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:69:29: ( formalParameters )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==Identifier||(LA6_0 >= 25 && LA6_0 <= 26)||LA6_0==31) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:69:29: formalParameters
                    {
                    pushFollow(FOLLOW_formalParameters_in_methodDeclaration244);
                    formalParameters();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,13,FOLLOW_13_in_methodDeclaration247); if (state.failed) return ;

            if ( state.backtracking==0 ) {enterFunction((Identifier3!=null?Identifier3.getText():null),type4);}

            pushFollow(FOLLOW_block_in_methodDeclaration270);
            block();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {exitFunction();}

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
    // $ANTLR end "methodDeclaration"



    // $ANTLR start "formalParameters"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:77:1: formalParameters : type Identifier ( ',' type Identifier )* ;
    public final void formalParameters() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:78:5: ( type Identifier ( ',' type Identifier )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:78:9: type Identifier ( ',' type Identifier )*
            {
            pushFollow(FOLLOW_type_in_formalParameters311);
            type();

            state._fsp--;
            if (state.failed) return ;

            match(input,Identifier,FOLLOW_Identifier_in_formalParameters313); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:78:25: ( ',' type Identifier )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:78:26: ',' type Identifier
            	    {
            	    match(input,16,FOLLOW_16_in_formalParameters316); if (state.failed) return ;

            	    pushFollow(FOLLOW_type_in_formalParameters318);
            	    type();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    match(input,Identifier,FOLLOW_Identifier_in_formalParameters320); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop7;
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
    // $ANTLR end "formalParameters"



    // $ANTLR start "type"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:81:1: type returns [Type type] : ( 'decimal' | 'int' | 'void' |ID= Identifier );
    public final Type type() throws RecognitionException {
        Type type = null;


        Token ID=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:82:5: ( 'decimal' | 'int' | 'void' |ID= Identifier )
            int alt8=4;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt8=1;
                }
                break;
            case 26:
                {
                alt8=2;
                }
                break;
            case 31:
                {
                alt8=3;
                }
                break;
            case Identifier:
                {
                alt8=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }

            switch (alt8) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:82:9: 'decimal'
                    {
                    match(input,25,FOLLOW_25_in_type346); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:83:9: 'int'
                    {
                    match(input,26,FOLLOW_26_in_type358); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:84:9: 'void'
                    {
                    match(input,31,FOLLOW_31_in_type370); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:85:9: ID= Identifier
                    {
                    ID=(Token)match(input,Identifier,FOLLOW_Identifier_in_type384); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = resolveType((ID!=null?ID.getText():null));}

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
        return type;
    }
    // $ANTLR end "type"



    // $ANTLR start "block"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:89:1: block : '{' ( statement )* '}' ;
    public final void block() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:90:5: ( '{' ( statement )* '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:90:9: '{' ( statement )* '}'
            {
            match(input,32,FOLLOW_32_in_block406); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:90:13: ( statement )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= Identifier && LA9_0 <= Integer)||LA9_0==12||LA9_0==20||(LA9_0 >= 25 && LA9_0 <= 26)||(LA9_0 >= 28 && LA9_0 <= 32)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:90:13: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block408);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            match(input,33,FOLLOW_33_in_block411); if (state.failed) return ;

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
    // $ANTLR end "block"



    // $ANTLR start "varDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:94:1: varDeclaration : type ID= Identifier ( '=' expression )? ';' ;
    public final void varDeclaration() throws RecognitionException {
        Token ID=null;
        Type type5 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:95:5: ( type ID= Identifier ( '=' expression )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:95:9: type ID= Identifier ( '=' expression )? ';'
            {
            pushFollow(FOLLOW_type_in_varDeclaration428);
            type5=type();

            state._fsp--;
            if (state.failed) return ;

            ID=(Token)match(input,Identifier,FOLLOW_Identifier_in_varDeclaration432); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:95:28: ( '=' expression )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==21) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:95:29: '=' expression
                    {
                    match(input,21,FOLLOW_21_in_varDeclaration435); if (state.failed) return ;

                    pushFollow(FOLLOW_expression_in_varDeclaration437);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,20,FOLLOW_20_in_varDeclaration441); if (state.failed) return ;

            if ( state.backtracking==0 ) {defineField((ID!=null?ID.getText():null),type5);}

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
    // $ANTLR end "varDeclaration"



    // $ANTLR start "statement"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:100:1: statement : ( block | varDeclaration | 'return' ( expression )? ';' | postfixExpression ( '=' expression )? ';' | ';' );
    public final void statement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:101:5: ( block | varDeclaration | 'return' ( expression )? ';' | postfixExpression ( '=' expression )? ';' | ';' )
            int alt13=5;
            switch ( input.LA(1) ) {
            case 32:
                {
                alt13=1;
                }
                break;
            case 25:
            case 26:
            case 31:
                {
                alt13=2;
                }
                break;
            case Identifier:
                {
                int LA13_3 = input.LA(2);

                if ( (LA13_3==Identifier) ) {
                    alt13=2;
                }
                else if ( (LA13_3==12||LA13_3==18||(LA13_3 >= 20 && LA13_3 <= 22)) ) {
                    alt13=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 3, input);

                    throw nvae;

                }
                }
                break;
            case 28:
                {
                alt13=3;
                }
                break;
            case Integer:
            case 12:
            case 29:
            case 30:
                {
                alt13=4;
                }
                break;
            case 20:
                {
                alt13=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:101:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement464);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:102:7: varDeclaration
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement472);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:103:9: 'return' ( expression )? ';'
                    {
                    match(input,28,FOLLOW_28_in_statement482); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:103:18: ( expression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0 >= Identifier && LA11_0 <= Integer)||LA11_0==12||(LA11_0 >= 29 && LA11_0 <= 30)) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:103:18: expression
                            {
                            pushFollow(FOLLOW_expression_in_statement484);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,20,FOLLOW_20_in_statement487); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:104:9: postfixExpression ( '=' expression )? ';'
                    {
                    pushFollow(FOLLOW_postfixExpression_in_statement498);
                    postfixExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:105:9: ( '=' expression )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==21) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:105:13: '=' expression
                            {
                            match(input,21,FOLLOW_21_in_statement513); if (state.failed) return ;

                            pushFollow(FOLLOW_expression_in_statement515);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,20,FOLLOW_20_in_statement529); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:7: ';'
                    {
                    match(input,20,FOLLOW_20_in_statement538); if (state.failed) return ;

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
    // $ANTLR end "statement"



    // $ANTLR start "expressionList"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:111:1: expressionList : ( expression ( ',' expression )* |);
    public final void expressionList() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:112:5: ( expression ( ',' expression )* |)
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0 >= Identifier && LA15_0 <= Integer)||LA15_0==12||(LA15_0 >= 29 && LA15_0 <= 30)) ) {
                alt15=1;
            }
            else if ( (LA15_0==13||LA15_0==23) ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }
            switch (alt15) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:112:9: expression ( ',' expression )*
                    {
                    pushFollow(FOLLOW_expression_in_expressionList559);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:112:20: ( ',' expression )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==16) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:112:21: ',' expression
                    	    {
                    	    match(input,16,FOLLOW_16_in_expressionList562); if (state.failed) return ;

                    	    pushFollow(FOLLOW_expression_in_expressionList564);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:112:40: 
                    {
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
    // $ANTLR end "expressionList"



    // $ANTLR start "expression"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:116:1: expression returns [VariableSymbol value] : e= addExpression ;
    public final VariableSymbol expression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:5: (e= addExpression )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:9: e= addExpression
            {
            pushFollow(FOLLOW_addExpression_in_expression591);
            e=addExpression();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

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
    // $ANTLR end "expression"



    // $ANTLR start "addExpression"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:121:1: addExpression returns [VariableSymbol value] : e= multExpression ( '+' e= multExpression | '-' e= multExpression )* ;
    public final VariableSymbol addExpression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:122:5: (e= multExpression ( '+' e= multExpression | '-' e= multExpression )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:122:9: e= multExpression ( '+' e= multExpression | '-' e= multExpression )*
            {
            pushFollow(FOLLOW_multExpression_in_addExpression620);
            e=multExpression();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:123:9: ( '+' e= multExpression | '-' e= multExpression )*
            loop16:
            do {
                int alt16=3;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==15) ) {
                    alt16=1;
                }
                else if ( (LA16_0==17) ) {
                    alt16=2;
                }


                switch (alt16) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:123:13: '+' e= multExpression
            	    {
            	    match(input,15,FOLLOW_15_in_addExpression636); if (state.failed) return value;

            	    pushFollow(FOLLOW_multExpression_in_addExpression640);
            	    e=multExpression();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = add(value,e);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:124:13: '-' e= multExpression
            	    {
            	    match(input,17,FOLLOW_17_in_addExpression656); if (state.failed) return value;

            	    pushFollow(FOLLOW_multExpression_in_addExpression660);
            	    e=multExpression();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = sub(value,e);}

            	    }
            	    break;

            	default :
            	    break loop16;
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
    // $ANTLR end "addExpression"



    // $ANTLR start "multExpression"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:130:1: multExpression returns [VariableSymbol value] : e= postfixExpression ( '*' e= postfixExpression )* ;
    public final VariableSymbol multExpression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:131:5: (e= postfixExpression ( '*' e= postfixExpression )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:131:9: e= postfixExpression ( '*' e= postfixExpression )*
            {
            pushFollow(FOLLOW_postfixExpression_in_multExpression700);
            e=postfixExpression();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:132:9: ( '*' e= postfixExpression )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==14) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:133:11: '*' e= postfixExpression
            	    {
            	    match(input,14,FOLLOW_14_in_multExpression729); if (state.failed) return value;

            	    pushFollow(FOLLOW_postfixExpression_in_multExpression733);
            	    e=postfixExpression();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = mul(value,e);}

            	    }
            	    break;

            	default :
            	    break loop17;
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
    // $ANTLR end "multExpression"



    // $ANTLR start "postfixExpression"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:140:1: postfixExpression returns [VariableSymbol value] : (e= primary ) ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' | '[' Integer ']' | '[' expressionList ']' )* ;
    public final VariableSymbol postfixExpression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:5: ( (e= primary ) ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' | '[' Integer ']' | '[' expressionList ']' )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:9: (e= primary ) ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' | '[' Integer ']' | '[' expressionList ']' )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:9: (e= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:10: e= primary
            {
            pushFollow(FOLLOW_primary_in_postfixExpression778);
            e=primary();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:142:9: ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' | '[' Integer ']' | '[' expressionList ']' )*
            loop18:
            do {
                int alt18=6;
                switch ( input.LA(1) ) {
                case 18:
                    {
                    int LA18_9 = input.LA(2);

                    if ( (synpred1_Nebula()) ) {
                        alt18=1;
                    }
                    else if ( (synpred2_Nebula()) ) {
                        alt18=2;
                    }


                    }
                    break;
                case 12:
                    {
                    alt18=3;
                    }
                    break;
                case 22:
                    {
                    int LA18_11 = input.LA(2);

                    if ( (synpred4_Nebula()) ) {
                        alt18=4;
                    }
                    else if ( (synpred5_Nebula()) ) {
                        alt18=5;
                    }


                    }
                    break;

                }

                switch (alt18) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:143:12: '.' Identifier '(' expressionList ')'
            	    {
            	    match(input,18,FOLLOW_18_in_postfixExpression810); if (state.failed) return value;

            	    match(input,Identifier,FOLLOW_Identifier_in_postfixExpression812); if (state.failed) return value;

            	    match(input,12,FOLLOW_12_in_postfixExpression814); if (state.failed) return value;

            	    pushFollow(FOLLOW_expressionList_in_postfixExpression816);
            	    expressionList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixExpression818); if (state.failed) return value;

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:144:12: '.' Identifier
            	    {
            	    match(input,18,FOLLOW_18_in_postfixExpression831); if (state.failed) return value;

            	    match(input,Identifier,FOLLOW_Identifier_in_postfixExpression833); if (state.failed) return value;

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:145:12: '(' expressionList ')'
            	    {
            	    match(input,12,FOLLOW_12_in_postfixExpression859); if (state.failed) return value;

            	    pushFollow(FOLLOW_expressionList_in_postfixExpression861);
            	    expressionList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixExpression863); if (state.failed) return value;

            	    }
            	    break;
            	case 4 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:12: '[' Integer ']'
            	    {
            	    match(input,22,FOLLOW_22_in_postfixExpression877); if (state.failed) return value;

            	    match(input,Integer,FOLLOW_Integer_in_postfixExpression879); if (state.failed) return value;

            	    match(input,23,FOLLOW_23_in_postfixExpression881); if (state.failed) return value;

            	    }
            	    break;
            	case 5 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:147:12: '[' expressionList ']'
            	    {
            	    match(input,22,FOLLOW_22_in_postfixExpression894); if (state.failed) return value;

            	    pushFollow(FOLLOW_expressionList_in_postfixExpression896);
            	    expressionList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,23,FOLLOW_23_in_postfixExpression898); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop18;
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
    // $ANTLR end "postfixExpression"



    // $ANTLR start "primary"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:153:1: primary returns [VariableSymbol value] : ( 'this' | 'super' | Integer | Identifier | '(' expression ')' );
    public final VariableSymbol primary() throws RecognitionException {
        VariableSymbol value = null;


        Token Integer6=null;
        Token Identifier7=null;
        VariableSymbol expression8 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:154:5: ( 'this' | 'super' | Integer | Identifier | '(' expression ')' )
            int alt19=5;
            switch ( input.LA(1) ) {
            case 30:
                {
                alt19=1;
                }
                break;
            case 29:
                {
                alt19=2;
                }
                break;
            case Integer:
                {
                alt19=3;
                }
                break;
            case Identifier:
                {
                alt19=4;
                }
                break;
            case 12:
                {
                alt19=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:154:9: 'this'
                    {
                    match(input,30,FOLLOW_30_in_primary934); if (state.failed) return value;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:9: 'super'
                    {
                    match(input,29,FOLLOW_29_in_primary944); if (state.failed) return value;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:9: Integer
                    {
                    Integer6=(Token)match(input,Integer,FOLLOW_Integer_in_primary954); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = defineInt((Integer6!=null?Integer6.getText():null));}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:9: Identifier
                    {
                    Identifier7=(Token)match(input,Identifier,FOLLOW_Identifier_in_primary966); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = resolve((Identifier7!=null?Identifier7.getText():null));}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:9: '(' expression ')'
                    {
                    match(input,12,FOLLOW_12_in_primary978); if (state.failed) return value;

                    pushFollow(FOLLOW_expression_in_primary980);
                    expression8=expression();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_primary982); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = expression8;}

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
    // $ANTLR end "primary"

    // $ANTLR start synpred1_Nebula
    public final void synpred1_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:143:12: ( '.' Identifier '(' expressionList ')' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:143:12: '.' Identifier '(' expressionList ')'
        {
        match(input,18,FOLLOW_18_in_synpred1_Nebula810); if (state.failed) return ;

        match(input,Identifier,FOLLOW_Identifier_in_synpred1_Nebula812); if (state.failed) return ;

        match(input,12,FOLLOW_12_in_synpred1_Nebula814); if (state.failed) return ;

        pushFollow(FOLLOW_expressionList_in_synpred1_Nebula816);
        expressionList();

        state._fsp--;
        if (state.failed) return ;

        match(input,13,FOLLOW_13_in_synpred1_Nebula818); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_Nebula

    // $ANTLR start synpred2_Nebula
    public final void synpred2_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:144:12: ( '.' Identifier )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:144:12: '.' Identifier
        {
        match(input,18,FOLLOW_18_in_synpred2_Nebula831); if (state.failed) return ;

        match(input,Identifier,FOLLOW_Identifier_in_synpred2_Nebula833); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_Nebula

    // $ANTLR start synpred4_Nebula
    public final void synpred4_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:12: ( '[' Integer ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:12: '[' Integer ']'
        {
        match(input,22,FOLLOW_22_in_synpred4_Nebula877); if (state.failed) return ;

        match(input,Integer,FOLLOW_Integer_in_synpred4_Nebula879); if (state.failed) return ;

        match(input,23,FOLLOW_23_in_synpred4_Nebula881); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred4_Nebula

    // $ANTLR start synpred5_Nebula
    public final void synpred5_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:147:12: ( '[' expressionList ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:147:12: '[' expressionList ']'
        {
        match(input,22,FOLLOW_22_in_synpred5_Nebula894); if (state.failed) return ;

        pushFollow(FOLLOW_expressionList_in_synpred5_Nebula896);
        expressionList();

        state._fsp--;
        if (state.failed) return ;

        match(input,23,FOLLOW_23_in_synpred5_Nebula898); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred5_Nebula

    // Delegated rules

    public final boolean synpred5_Nebula() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_Nebula_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_Nebula() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_Nebula_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_Nebula() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Nebula_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_Nebula() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_Nebula_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_classDefinition_in_compilationUnit55 = new BitSet(new long[]{0x0000000087000020L});
    public static final BitSet FOLLOW_varDeclaration_in_compilationUnit59 = new BitSet(new long[]{0x0000000087000020L});
    public static final BitSet FOLLOW_methodDeclaration_in_compilationUnit63 = new BitSet(new long[]{0x0000000087000020L});
    public static final BitSet FOLLOW_EOF_in_compilationUnit68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_classDefinition88 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_classDefinition90 = new BitSet(new long[]{0x0000000100080000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition92 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_classDefinition120 = new BitSet(new long[]{0x000000008E000020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition122 = new BitSet(new long[]{0x000000028E000020L});
    public static final BitSet FOLLOW_33_in_classDefinition125 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_classDefinition127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_superClass169 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_superClass171 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_superClass173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_classMember188 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_classMember192 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_classMember195 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_expression_in_classMember197 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_classMember201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_classMember215 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_classMember217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration238 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_methodDeclaration240 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration242 = new BitSet(new long[]{0x0000000086002020L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration244 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration247 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameters311 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_formalParameters313 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_formalParameters316 = new BitSet(new long[]{0x0000000086000020L});
    public static final BitSet FOLLOW_type_in_formalParameters318 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_formalParameters320 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_25_in_type346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_type358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_type370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_type384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_block406 = new BitSet(new long[]{0x00000003F6101060L});
    public static final BitSet FOLLOW_statement_in_block408 = new BitSet(new long[]{0x00000003F6101060L});
    public static final BitSet FOLLOW_33_in_block411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration428 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_varDeclaration432 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_varDeclaration435 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_expression_in_varDeclaration437 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_varDeclaration441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_statement482 = new BitSet(new long[]{0x0000000060101060L});
    public static final BitSet FOLLOW_expression_in_statement484 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixExpression_in_statement498 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_statement513 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_expression_in_statement515 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_statement538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList559 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_expressionList562 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_expression_in_expressionList564 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_addExpression_in_expression591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpression_in_addExpression620 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_15_in_addExpression636 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_multExpression_in_addExpression640 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_17_in_addExpression656 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_multExpression_in_addExpression660 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_postfixExpression_in_multExpression700 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_multExpression729 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_postfixExpression_in_multExpression733 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_primary_in_postfixExpression778 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_18_in_postfixExpression810 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_postfixExpression812 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixExpression814 = new BitSet(new long[]{0x0000000060003060L});
    public static final BitSet FOLLOW_expressionList_in_postfixExpression816 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixExpression818 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_18_in_postfixExpression831 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_postfixExpression833 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_12_in_postfixExpression859 = new BitSet(new long[]{0x0000000060003060L});
    public static final BitSet FOLLOW_expressionList_in_postfixExpression861 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixExpression863 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_22_in_postfixExpression877 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_Integer_in_postfixExpression879 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_postfixExpression881 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_22_in_postfixExpression894 = new BitSet(new long[]{0x0000000060801060L});
    public static final BitSet FOLLOW_expressionList_in_postfixExpression896 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_postfixExpression898 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_30_in_primary934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_primary944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Integer_in_primary954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_primary966 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary978 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_expression_in_primary980 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred1_Nebula810 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_synpred1_Nebula812 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_synpred1_Nebula814 = new BitSet(new long[]{0x0000000060003060L});
    public static final BitSet FOLLOW_expressionList_in_synpred1_Nebula816 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_synpred1_Nebula818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred2_Nebula831 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_synpred2_Nebula833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_synpred4_Nebula877 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_Integer_in_synpred4_Nebula879 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_synpred4_Nebula881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_synpred5_Nebula894 = new BitSet(new long[]{0x0000000060801060L});
    public static final BitSet FOLLOW_expressionList_in_synpred5_Nebula896 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_synpred5_Nebula898 = new BitSet(new long[]{0x0000000000000002L});

}