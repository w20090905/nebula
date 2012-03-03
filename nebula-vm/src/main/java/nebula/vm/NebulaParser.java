// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-03 11:57:58

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:44:1: compilationUnit : ( classDefinition | fieldDeclaration | methodDeclaration )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:45:5: ( ( classDefinition | fieldDeclaration | methodDeclaration )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:45:9: ( classDefinition | fieldDeclaration | methodDeclaration )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:45:9: ( classDefinition | fieldDeclaration | methodDeclaration )+
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:45:29: fieldDeclaration
            	    {
            	    pushFollow(FOLLOW_fieldDeclaration_in_compilationUnit59);
            	    fieldDeclaration();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:45:48: methodDeclaration
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:49:1: classDefinition : 'class' Identifier ( superClass )? '{' ( classMember )+ '}' ;
    public final void classDefinition() throws RecognitionException {
        Token Identifier1=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:50:5: ( 'class' Identifier ( superClass )? '{' ( classMember )+ '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:50:9: 'class' Identifier ( superClass )? '{' ( classMember )+ '}'
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
            match(input,19,FOLLOW_19_in_superClass167); if (state.failed) return ;

            match(input,27,FOLLOW_27_in_superClass169); if (state.failed) return ;

            match(input,Identifier,FOLLOW_Identifier_in_superClass171); if (state.failed) return ;

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
                    pushFollow(FOLLOW_type_in_classMember186);
                    type2=type();

                    state._fsp--;
                    if (state.failed) return ;

                    e=(Token)match(input,Identifier,FOLLOW_Identifier_in_classMember190); if (state.failed) return ;

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
                            match(input,21,FOLLOW_21_in_classMember193); if (state.failed) return ;

                            pushFollow(FOLLOW_expression_in_classMember195);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,20,FOLLOW_20_in_classMember199); if (state.failed) return ;

                    if ( state.backtracking==0 ) {defineField((e!=null?e.getText():null),type2);}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:63:5: methodDeclaration
                    {
                    pushFollow(FOLLOW_methodDeclaration_in_classMember207);
                    methodDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:64:5: 'public' ':'
                    {
                    match(input,27,FOLLOW_27_in_classMember213); if (state.failed) return ;

                    match(input,19,FOLLOW_19_in_classMember215); if (state.failed) return ;

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
            pushFollow(FOLLOW_type_in_methodDeclaration236);
            type4=type();

            state._fsp--;
            if (state.failed) return ;

            Identifier3=(Token)match(input,Identifier,FOLLOW_Identifier_in_methodDeclaration238); if (state.failed) return ;

            match(input,12,FOLLOW_12_in_methodDeclaration240); if (state.failed) return ;

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
                    pushFollow(FOLLOW_formalParameters_in_methodDeclaration242);
                    formalParameters();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,13,FOLLOW_13_in_methodDeclaration245); if (state.failed) return ;

            if ( state.backtracking==0 ) {enterFunction((Identifier3!=null?Identifier3.getText():null),type4);}

            pushFollow(FOLLOW_block_in_methodDeclaration268);
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
            pushFollow(FOLLOW_type_in_formalParameters309);
            type();

            state._fsp--;
            if (state.failed) return ;

            match(input,Identifier,FOLLOW_Identifier_in_formalParameters311); if (state.failed) return ;

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
            	    match(input,16,FOLLOW_16_in_formalParameters314); if (state.failed) return ;

            	    pushFollow(FOLLOW_type_in_formalParameters316);
            	    type();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    match(input,Identifier,FOLLOW_Identifier_in_formalParameters318); if (state.failed) return ;

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
                    match(input,25,FOLLOW_25_in_type344); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:83:9: 'int'
                    {
                    match(input,26,FOLLOW_26_in_type356); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:84:9: 'void'
                    {
                    match(input,31,FOLLOW_31_in_type368); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:85:9: ID= Identifier
                    {
                    ID=(Token)match(input,Identifier,FOLLOW_Identifier_in_type382); if (state.failed) return type;

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
            match(input,32,FOLLOW_32_in_block404); if (state.failed) return ;

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
            	    pushFollow(FOLLOW_statement_in_block406);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            match(input,33,FOLLOW_33_in_block409); if (state.failed) return ;

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



    // $ANTLR start "fieldDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:93:1: fieldDeclaration : type ID= Identifier ( '=' expression )? ';' ;
    public final void fieldDeclaration() throws RecognitionException {
        Token ID=null;
        Type type5 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:94:5: ( type ID= Identifier ( '=' expression )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:94:9: type ID= Identifier ( '=' expression )? ';'
            {
            pushFollow(FOLLOW_type_in_fieldDeclaration425);
            type5=type();

            state._fsp--;
            if (state.failed) return ;

            ID=(Token)match(input,Identifier,FOLLOW_Identifier_in_fieldDeclaration429); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:94:28: ( '=' expression )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==21) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:94:29: '=' expression
                    {
                    match(input,21,FOLLOW_21_in_fieldDeclaration432); if (state.failed) return ;

                    pushFollow(FOLLOW_expression_in_fieldDeclaration434);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,20,FOLLOW_20_in_fieldDeclaration438); if (state.failed) return ;

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
    // $ANTLR end "fieldDeclaration"



    // $ANTLR start "varDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:98:1: varDeclaration : type ID= Identifier ( '=' e= expression )? ';' ;
    public final void varDeclaration() throws RecognitionException {
        Token ID=null;
        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:99:5: ( type ID= Identifier ( '=' e= expression )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:99:9: type ID= Identifier ( '=' e= expression )? ';'
            {
            pushFollow(FOLLOW_type_in_varDeclaration464);
            type();

            state._fsp--;
            if (state.failed) return ;

            ID=(Token)match(input,Identifier,FOLLOW_Identifier_in_varDeclaration468); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:99:28: ( '=' e= expression )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==21) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:99:29: '=' e= expression
                    {
                    match(input,21,FOLLOW_21_in_varDeclaration471); if (state.failed) return ;

                    pushFollow(FOLLOW_expression_in_varDeclaration475);
                    e=expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,20,FOLLOW_20_in_varDeclaration479); if (state.failed) return ;

            if ( state.backtracking==0 ) {evalSet((ID!=null?ID.getText():null),e);}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:104:1: statement : ( block | varDeclaration | 'return' ( expression )? ';' | postfixExpression ( '=' expression )? ';' | ';' );
    public final void statement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:105:5: ( block | varDeclaration | 'return' ( expression )? ';' | postfixExpression ( '=' expression )? ';' | ';' )
            int alt14=5;
            switch ( input.LA(1) ) {
            case 32:
                {
                alt14=1;
                }
                break;
            case 25:
            case 26:
            case 31:
                {
                alt14=2;
                }
                break;
            case Identifier:
                {
                int LA14_3 = input.LA(2);

                if ( (LA14_3==Identifier) ) {
                    alt14=2;
                }
                else if ( (LA14_3==12||LA14_3==18||(LA14_3 >= 20 && LA14_3 <= 22)) ) {
                    alt14=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 3, input);

                    throw nvae;

                }
                }
                break;
            case 28:
                {
                alt14=3;
                }
                break;
            case Integer:
            case 12:
            case 29:
            case 30:
                {
                alt14=4;
                }
                break;
            case 20:
                {
                alt14=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }

            switch (alt14) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:105:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement502);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:106:9: varDeclaration
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement512);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:9: 'return' ( expression )? ';'
                    {
                    match(input,28,FOLLOW_28_in_statement522); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:18: ( expression )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( ((LA12_0 >= Identifier && LA12_0 <= Integer)||LA12_0==12||(LA12_0 >= 29 && LA12_0 <= 30)) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:18: expression
                            {
                            pushFollow(FOLLOW_expression_in_statement524);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,20,FOLLOW_20_in_statement527); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:108:9: postfixExpression ( '=' expression )? ';'
                    {
                    pushFollow(FOLLOW_postfixExpression_in_statement538);
                    postfixExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:109:9: ( '=' expression )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==21) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:109:13: '=' expression
                            {
                            match(input,21,FOLLOW_21_in_statement553); if (state.failed) return ;

                            pushFollow(FOLLOW_expression_in_statement555);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,20,FOLLOW_20_in_statement569); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:111:7: ';'
                    {
                    match(input,20,FOLLOW_20_in_statement578); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:115:1: expressionList : ( expression ( ',' expression )* |);
    public final void expressionList() throws RecognitionException {

                  ArrayList list = new ArrayList();
            
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:5: ( expression ( ',' expression )* |)
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0 >= Identifier && LA16_0 <= Integer)||LA16_0==12||(LA16_0 >= 29 && LA16_0 <= 30)) ) {
                alt16=1;
            }
            else if ( (LA16_0==13||LA16_0==23) ) {
                alt16=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:9: expression ( ',' expression )*
                    {
                    pushFollow(FOLLOW_expression_in_expressionList607);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:20: ( ',' expression )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==16) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:21: ',' expression
                    	    {
                    	    match(input,16,FOLLOW_16_in_expressionList610); if (state.failed) return ;

                    	    pushFollow(FOLLOW_expression_in_expressionList612);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:40: 
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:123:1: expression returns [VariableSymbol value] : e= addExpression ;
    public final VariableSymbol expression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:124:5: (e= addExpression )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:124:9: e= addExpression
            {
            pushFollow(FOLLOW_addExpression_in_expression639);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:128:1: addExpression returns [VariableSymbol value] : e= multExpression ( '+' e= multExpression | '-' e= multExpression )* ;
    public final VariableSymbol addExpression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:129:5: (e= multExpression ( '+' e= multExpression | '-' e= multExpression )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:129:9: e= multExpression ( '+' e= multExpression | '-' e= multExpression )*
            {
            pushFollow(FOLLOW_multExpression_in_addExpression668);
            e=multExpression();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:130:9: ( '+' e= multExpression | '-' e= multExpression )*
            loop17:
            do {
                int alt17=3;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==15) ) {
                    alt17=1;
                }
                else if ( (LA17_0==17) ) {
                    alt17=2;
                }


                switch (alt17) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:130:13: '+' e= multExpression
            	    {
            	    match(input,15,FOLLOW_15_in_addExpression684); if (state.failed) return value;

            	    pushFollow(FOLLOW_multExpression_in_addExpression688);
            	    e=multExpression();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = add(value,e);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:131:13: '-' e= multExpression
            	    {
            	    match(input,17,FOLLOW_17_in_addExpression704); if (state.failed) return value;

            	    pushFollow(FOLLOW_multExpression_in_addExpression708);
            	    e=multExpression();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = sub(value,e);}

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
    // $ANTLR end "addExpression"



    // $ANTLR start "multExpression"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:1: multExpression returns [VariableSymbol value] : e= postfixExpression ( '*' e= postfixExpression )* ;
    public final VariableSymbol multExpression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:138:5: (e= postfixExpression ( '*' e= postfixExpression )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:138:9: e= postfixExpression ( '*' e= postfixExpression )*
            {
            pushFollow(FOLLOW_postfixExpression_in_multExpression748);
            e=postfixExpression();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:139:9: ( '*' e= postfixExpression )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==14) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:140:11: '*' e= postfixExpression
            	    {
            	    match(input,14,FOLLOW_14_in_multExpression777); if (state.failed) return value;

            	    pushFollow(FOLLOW_postfixExpression_in_multExpression781);
            	    e=postfixExpression();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = mul(value,e);}

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
    // $ANTLR end "multExpression"



    // $ANTLR start "postfixExpression"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:147:1: postfixExpression returns [VariableSymbol value] : (e= primary ) ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' | '[' Integer ']' | '[' expressionList ']' )* ;
    public final VariableSymbol postfixExpression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:148:5: ( (e= primary ) ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' | '[' Integer ']' | '[' expressionList ']' )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:148:9: (e= primary ) ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' | '[' Integer ']' | '[' expressionList ']' )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:148:9: (e= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:148:10: e= primary
            {
            pushFollow(FOLLOW_primary_in_postfixExpression826);
            e=primary();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:149:9: ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' | '[' Integer ']' | '[' expressionList ']' )*
            loop19:
            do {
                int alt19=6;
                switch ( input.LA(1) ) {
                case 18:
                    {
                    int LA19_9 = input.LA(2);

                    if ( (synpred1_Nebula()) ) {
                        alt19=1;
                    }
                    else if ( (synpred2_Nebula()) ) {
                        alt19=2;
                    }


                    }
                    break;
                case 12:
                    {
                    alt19=3;
                    }
                    break;
                case 22:
                    {
                    int LA19_11 = input.LA(2);

                    if ( (synpred4_Nebula()) ) {
                        alt19=4;
                    }
                    else if ( (synpred5_Nebula()) ) {
                        alt19=5;
                    }


                    }
                    break;

                }

                switch (alt19) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:150:12: '.' Identifier '(' expressionList ')'
            	    {
            	    match(input,18,FOLLOW_18_in_postfixExpression858); if (state.failed) return value;

            	    match(input,Identifier,FOLLOW_Identifier_in_postfixExpression860); if (state.failed) return value;

            	    match(input,12,FOLLOW_12_in_postfixExpression862); if (state.failed) return value;

            	    pushFollow(FOLLOW_expressionList_in_postfixExpression864);
            	    expressionList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixExpression866); if (state.failed) return value;

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:151:12: '.' Identifier
            	    {
            	    match(input,18,FOLLOW_18_in_postfixExpression879); if (state.failed) return value;

            	    match(input,Identifier,FOLLOW_Identifier_in_postfixExpression881); if (state.failed) return value;

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:12: '(' expressionList ')'
            	    {
            	    match(input,12,FOLLOW_12_in_postfixExpression907); if (state.failed) return value;

            	    pushFollow(FOLLOW_expressionList_in_postfixExpression909);
            	    expressionList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixExpression911); if (state.failed) return value;

            	    }
            	    break;
            	case 4 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:153:12: '[' Integer ']'
            	    {
            	    match(input,22,FOLLOW_22_in_postfixExpression925); if (state.failed) return value;

            	    match(input,Integer,FOLLOW_Integer_in_postfixExpression927); if (state.failed) return value;

            	    match(input,23,FOLLOW_23_in_postfixExpression929); if (state.failed) return value;

            	    }
            	    break;
            	case 5 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:154:12: '[' expressionList ']'
            	    {
            	    match(input,22,FOLLOW_22_in_postfixExpression942); if (state.failed) return value;

            	    pushFollow(FOLLOW_expressionList_in_postfixExpression944);
            	    expressionList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,23,FOLLOW_23_in_postfixExpression946); if (state.failed) return value;

            	    }
            	    break;

            	default :
            	    break loop19;
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:1: primary returns [VariableSymbol value] : ( 'this' | 'super' | Integer | Identifier | '(' expression ')' );
    public final VariableSymbol primary() throws RecognitionException {
        VariableSymbol value = null;


        Token Integer6=null;
        Token Identifier7=null;
        VariableSymbol expression8 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:5: ( 'this' | 'super' | Integer | Identifier | '(' expression ')' )
            int alt20=5;
            switch ( input.LA(1) ) {
            case 30:
                {
                alt20=1;
                }
                break;
            case 29:
                {
                alt20=2;
                }
                break;
            case Integer:
                {
                alt20=3;
                }
                break;
            case Identifier:
                {
                alt20=4;
                }
                break;
            case 12:
                {
                alt20=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }

            switch (alt20) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:9: 'this'
                    {
                    match(input,30,FOLLOW_30_in_primary982); if (state.failed) return value;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:162:9: 'super'
                    {
                    match(input,29,FOLLOW_29_in_primary992); if (state.failed) return value;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:163:9: Integer
                    {
                    Integer6=(Token)match(input,Integer,FOLLOW_Integer_in_primary1002); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = defineInt((Integer6!=null?Integer6.getText():null));}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:164:9: Identifier
                    {
                    Identifier7=(Token)match(input,Identifier,FOLLOW_Identifier_in_primary1014); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = resolve((Identifier7!=null?Identifier7.getText():null));}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:165:9: '(' expression ')'
                    {
                    match(input,12,FOLLOW_12_in_primary1026); if (state.failed) return value;

                    pushFollow(FOLLOW_expression_in_primary1028);
                    expression8=expression();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_primary1030); if (state.failed) return value;

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
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:150:12: ( '.' Identifier '(' expressionList ')' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:150:12: '.' Identifier '(' expressionList ')'
        {
        match(input,18,FOLLOW_18_in_synpred1_Nebula858); if (state.failed) return ;

        match(input,Identifier,FOLLOW_Identifier_in_synpred1_Nebula860); if (state.failed) return ;

        match(input,12,FOLLOW_12_in_synpred1_Nebula862); if (state.failed) return ;

        pushFollow(FOLLOW_expressionList_in_synpred1_Nebula864);
        expressionList();

        state._fsp--;
        if (state.failed) return ;

        match(input,13,FOLLOW_13_in_synpred1_Nebula866); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_Nebula

    // $ANTLR start synpred2_Nebula
    public final void synpred2_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:151:12: ( '.' Identifier )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:151:12: '.' Identifier
        {
        match(input,18,FOLLOW_18_in_synpred2_Nebula879); if (state.failed) return ;

        match(input,Identifier,FOLLOW_Identifier_in_synpred2_Nebula881); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_Nebula

    // $ANTLR start synpred4_Nebula
    public final void synpred4_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:153:12: ( '[' Integer ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:153:12: '[' Integer ']'
        {
        match(input,22,FOLLOW_22_in_synpred4_Nebula925); if (state.failed) return ;

        match(input,Integer,FOLLOW_Integer_in_synpred4_Nebula927); if (state.failed) return ;

        match(input,23,FOLLOW_23_in_synpred4_Nebula929); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred4_Nebula

    // $ANTLR start synpred5_Nebula
    public final void synpred5_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:154:12: ( '[' expressionList ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:154:12: '[' expressionList ']'
        {
        match(input,22,FOLLOW_22_in_synpred5_Nebula942); if (state.failed) return ;

        pushFollow(FOLLOW_expressionList_in_synpred5_Nebula944);
        expressionList();

        state._fsp--;
        if (state.failed) return ;

        match(input,23,FOLLOW_23_in_synpred5_Nebula946); if (state.failed) return ;

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
    public static final BitSet FOLLOW_fieldDeclaration_in_compilationUnit59 = new BitSet(new long[]{0x0000000087000020L});
    public static final BitSet FOLLOW_methodDeclaration_in_compilationUnit63 = new BitSet(new long[]{0x0000000087000020L});
    public static final BitSet FOLLOW_EOF_in_compilationUnit68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_classDefinition88 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_classDefinition90 = new BitSet(new long[]{0x0000000100080000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition92 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_classDefinition120 = new BitSet(new long[]{0x000000008E000020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition122 = new BitSet(new long[]{0x000000028E000020L});
    public static final BitSet FOLLOW_33_in_classDefinition125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_superClass167 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_superClass169 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_superClass171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_classMember186 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_classMember190 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_classMember193 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_expression_in_classMember195 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_classMember199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_classMember213 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_classMember215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration236 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_methodDeclaration238 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration240 = new BitSet(new long[]{0x0000000086002020L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration242 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration245 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameters309 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_formalParameters311 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_formalParameters314 = new BitSet(new long[]{0x0000000086000020L});
    public static final BitSet FOLLOW_type_in_formalParameters316 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_formalParameters318 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_25_in_type344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_type356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_type368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_type382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_block404 = new BitSet(new long[]{0x00000003F6101060L});
    public static final BitSet FOLLOW_statement_in_block406 = new BitSet(new long[]{0x00000003F6101060L});
    public static final BitSet FOLLOW_33_in_block409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_fieldDeclaration425 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_fieldDeclaration429 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_fieldDeclaration432 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_expression_in_fieldDeclaration434 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_fieldDeclaration438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration464 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_varDeclaration468 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_varDeclaration471 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_expression_in_varDeclaration475 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_varDeclaration479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_statement522 = new BitSet(new long[]{0x0000000060101060L});
    public static final BitSet FOLLOW_expression_in_statement524 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixExpression_in_statement538 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_statement553 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_expression_in_statement555 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_statement578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList607 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_expressionList610 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_expression_in_expressionList612 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_addExpression_in_expression639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpression_in_addExpression668 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_15_in_addExpression684 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_multExpression_in_addExpression688 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_17_in_addExpression704 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_multExpression_in_addExpression708 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_postfixExpression_in_multExpression748 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_multExpression777 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_postfixExpression_in_multExpression781 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_primary_in_postfixExpression826 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_18_in_postfixExpression858 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_postfixExpression860 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixExpression862 = new BitSet(new long[]{0x0000000060003060L});
    public static final BitSet FOLLOW_expressionList_in_postfixExpression864 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixExpression866 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_18_in_postfixExpression879 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_postfixExpression881 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_12_in_postfixExpression907 = new BitSet(new long[]{0x0000000060003060L});
    public static final BitSet FOLLOW_expressionList_in_postfixExpression909 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixExpression911 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_22_in_postfixExpression925 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_Integer_in_postfixExpression927 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_postfixExpression929 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_22_in_postfixExpression942 = new BitSet(new long[]{0x0000000060801060L});
    public static final BitSet FOLLOW_expressionList_in_postfixExpression944 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_postfixExpression946 = new BitSet(new long[]{0x0000000000441002L});
    public static final BitSet FOLLOW_30_in_primary982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_primary992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Integer_in_primary1002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_primary1014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary1026 = new BitSet(new long[]{0x0000000060001060L});
    public static final BitSet FOLLOW_expression_in_primary1028 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred1_Nebula858 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_synpred1_Nebula860 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_synpred1_Nebula862 = new BitSet(new long[]{0x0000000060003060L});
    public static final BitSet FOLLOW_expressionList_in_synpred1_Nebula864 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_synpred1_Nebula866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred2_Nebula879 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_synpred2_Nebula881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_synpred4_Nebula925 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_Integer_in_synpred4_Nebula927 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_synpred4_Nebula929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_synpred5_Nebula942 = new BitSet(new long[]{0x0000000060801060L});
    public static final BitSet FOLLOW_expressionList_in_synpred5_Nebula944 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_synpred5_Nebula946 = new BitSet(new long[]{0x0000000000000002L});

}