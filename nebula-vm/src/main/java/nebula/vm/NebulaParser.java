// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-02-29 10:14:45

package nebula.vm;
import nebula.vm.VariableSymbol;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class NebulaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Digit", "Identifier", "Integer", "Letter", "MultiLineComment", "NEWLINE", "SingleLineComment", "Whitespace", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "':'", "';'", "'='", "'class'", "'float'", "'int'", "'public'", "'return'", "'void'", "'{'", "'}'"
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
      
      protected void enterFunction(String name,String typeName) {;};
      protected void exitFunction() {;};
      
      protected void defineField(String name,String typeName){;};
      
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:41:1: compilationUnit : ( classDefinition | varDeclaration | methodDeclaration )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:42:5: ( ( classDefinition | varDeclaration | methodDeclaration )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:42:9: ( classDefinition | varDeclaration | methodDeclaration )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:42:9: ( classDefinition | varDeclaration | methodDeclaration )+
            int cnt1=0;
            loop1:
            do {
                int alt1=4;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==22) ) {
                    alt1=1;
                }
                else if ( (LA1_0==Identifier||(LA1_0 >= 23 && LA1_0 <= 24)||LA1_0==27) ) {
                    int LA1_3 = input.LA(2);

                    if ( (LA1_3==Identifier) ) {
                        int LA1_4 = input.LA(3);

                        if ( (LA1_4==12) ) {
                            alt1=3;
                        }
                        else if ( ((LA1_4 >= 20 && LA1_4 <= 21)) ) {
                            alt1=2;
                        }


                    }


                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:42:11: classDefinition
            	    {
            	    pushFollow(FOLLOW_classDefinition_in_compilationUnit55);
            	    classDefinition();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:42:29: varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_compilationUnit59);
            	    varDeclaration();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:42:46: methodDeclaration
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:46:1: classDefinition : 'class' Identifier ( superClass )? '{' ( classMember )+ '}' ';' ;
    public final void classDefinition() throws RecognitionException {
        Token Identifier1=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:47:5: ( 'class' Identifier ( superClass )? '{' ( classMember )+ '}' ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:47:9: 'class' Identifier ( superClass )? '{' ( classMember )+ '}' ';'
            {
            match(input,22,FOLLOW_22_in_classDefinition88); if (state.failed) return ;

            Identifier1=(Token)match(input,Identifier,FOLLOW_Identifier_in_classDefinition90); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:47:28: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==19) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:47:28: superClass
                    {
                    pushFollow(FOLLOW_superClass_in_classDefinition92);
                    superClass();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {enterClass((Identifier1!=null?Identifier1.getText():null));}

            match(input,28,FOLLOW_28_in_classDefinition120); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:49:14: ( classMember )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==Identifier||(LA3_0 >= 23 && LA3_0 <= 25)||LA3_0==27) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:49:14: classMember
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


            match(input,29,FOLLOW_29_in_classDefinition125); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:53:1: superClass : ':' 'public' Identifier ;
    public final void superClass() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:54:3: ( ':' 'public' Identifier )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:54:5: ':' 'public' Identifier
            {
            match(input,19,FOLLOW_19_in_superClass169); if (state.failed) return ;

            match(input,25,FOLLOW_25_in_superClass171); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:58:1: classMember : ( type Identifier ( '=' expression )? ';' | methodDeclaration | 'public' ':' );
    public final void classMember() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:59:3: ( type Identifier ( '=' expression )? ';' | methodDeclaration | 'public' ':' )
            int alt5=3;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==Identifier||(LA5_0 >= 23 && LA5_0 <= 24)||LA5_0==27) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==Identifier) ) {
                    int LA5_3 = input.LA(3);

                    if ( (LA5_3==12) ) {
                        alt5=2;
                    }
                    else if ( ((LA5_3 >= 20 && LA5_3 <= 21)) ) {
                        alt5=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 3, input);

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
            else if ( (LA5_0==25) ) {
                alt5=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:59:5: type Identifier ( '=' expression )? ';'
                    {
                    pushFollow(FOLLOW_type_in_classMember188);
                    type();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,Identifier,FOLLOW_Identifier_in_classMember190); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:59:21: ( '=' expression )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==21) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:59:22: '=' expression
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

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:60:5: methodDeclaration
                    {
                    pushFollow(FOLLOW_methodDeclaration_in_classMember206);
                    methodDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:61:5: 'public' ':'
                    {
                    match(input,25,FOLLOW_25_in_classMember212); if (state.failed) return ;

                    match(input,19,FOLLOW_19_in_classMember214); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:65:1: methodDeclaration : type Identifier '(' ( formalParameters )? ')' block ;
    public final void methodDeclaration() throws RecognitionException {
        Token Identifier2=null;
        NebulaParser.type_return type3 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:66:5: ( type Identifier '(' ( formalParameters )? ')' block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:66:9: type Identifier '(' ( formalParameters )? ')' block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration235);
            type3=type();

            state._fsp--;
            if (state.failed) return ;

            Identifier2=(Token)match(input,Identifier,FOLLOW_Identifier_in_methodDeclaration237); if (state.failed) return ;

            match(input,12,FOLLOW_12_in_methodDeclaration239); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:66:29: ( formalParameters )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==Identifier||(LA6_0 >= 23 && LA6_0 <= 24)||LA6_0==27) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:66:29: formalParameters
                    {
                    pushFollow(FOLLOW_formalParameters_in_methodDeclaration241);
                    formalParameters();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,13,FOLLOW_13_in_methodDeclaration244); if (state.failed) return ;

            if ( state.backtracking==0 ) {enterFunction((Identifier2!=null?Identifier2.getText():null),(type3!=null?input.toString(type3.start,type3.stop):null));}

            pushFollow(FOLLOW_block_in_methodDeclaration267);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:1: formalParameters : type Identifier ( ',' type Identifier )* ;
    public final void formalParameters() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:75:5: ( type Identifier ( ',' type Identifier )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:75:9: type Identifier ( ',' type Identifier )*
            {
            pushFollow(FOLLOW_type_in_formalParameters308);
            type();

            state._fsp--;
            if (state.failed) return ;

            match(input,Identifier,FOLLOW_Identifier_in_formalParameters310); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:75:25: ( ',' type Identifier )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:75:26: ',' type Identifier
            	    {
            	    match(input,16,FOLLOW_16_in_formalParameters313); if (state.failed) return ;

            	    pushFollow(FOLLOW_type_in_formalParameters315);
            	    type();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    match(input,Identifier,FOLLOW_Identifier_in_formalParameters317); if (state.failed) return ;

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


    public static class type_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "type"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:78:1: type : ( 'float' | 'int' | 'void' | Identifier );
    public final NebulaParser.type_return type() throws RecognitionException {
        NebulaParser.type_return retval = new NebulaParser.type_return();
        retval.start = input.LT(1);


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:78:5: ( 'float' | 'int' | 'void' | Identifier )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:
            {
            if ( input.LA(1)==Identifier||(input.LA(1) >= 23 && input.LA(1) <= 24)||input.LA(1)==27 ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
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
    // $ANTLR end "type"



    // $ANTLR start "block"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:85:1: block : '{' ( statement )* '}' ;
    public final void block() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:86:5: ( '{' ( statement )* '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:86:9: '{' ( statement )* '}'
            {
            match(input,28,FOLLOW_28_in_block381); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:86:13: ( statement )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= Identifier && LA8_0 <= Integer)||LA8_0==12||LA8_0==20||(LA8_0 >= 23 && LA8_0 <= 24)||(LA8_0 >= 26 && LA8_0 <= 28)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:86:13: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block383);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            match(input,29,FOLLOW_29_in_block386); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:90:1: varDeclaration : type Identifier ( '=' expression )? ';' ;
    public final void varDeclaration() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:91:5: ( type Identifier ( '=' expression )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:91:9: type Identifier ( '=' expression )? ';'
            {
            pushFollow(FOLLOW_type_in_varDeclaration403);
            type();

            state._fsp--;
            if (state.failed) return ;

            match(input,Identifier,FOLLOW_Identifier_in_varDeclaration405); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:91:25: ( '=' expression )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==21) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:91:26: '=' expression
                    {
                    match(input,21,FOLLOW_21_in_varDeclaration408); if (state.failed) return ;

                    pushFollow(FOLLOW_expression_in_varDeclaration410);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,20,FOLLOW_20_in_varDeclaration414); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:96:1: statement : ( block | varDeclaration | 'return' ( expression )? ';' | postfixExpression ( '=' expression )? ';' | ';' );
    public final void statement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:97:5: ( block | varDeclaration | 'return' ( expression )? ';' | postfixExpression ( '=' expression )? ';' | ';' )
            int alt12=5;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt12=1;
                }
                break;
            case Identifier:
                {
                int LA12_2 = input.LA(2);

                if ( (LA12_2==Identifier) ) {
                    alt12=2;
                }
                else if ( (LA12_2==12||LA12_2==18||(LA12_2 >= 20 && LA12_2 <= 21)) ) {
                    alt12=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 2, input);

                    throw nvae;

                }
                }
                break;
            case 26:
                {
                alt12=3;
                }
                break;
            case Integer:
            case 12:
                {
                alt12=4;
                }
                break;
            case 23:
            case 24:
            case 27:
                {
                alt12=2;
                }
                break;
            case 20:
                {
                alt12=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }

            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:97:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement436);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:98:7: varDeclaration
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement444);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:99:9: 'return' ( expression )? ';'
                    {
                    match(input,26,FOLLOW_26_in_statement454); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:99:18: ( expression )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( ((LA10_0 >= Identifier && LA10_0 <= Integer)||LA10_0==12) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:99:18: expression
                            {
                            pushFollow(FOLLOW_expression_in_statement456);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,20,FOLLOW_20_in_statement459); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:100:9: postfixExpression ( '=' expression )? ';'
                    {
                    pushFollow(FOLLOW_postfixExpression_in_statement470);
                    postfixExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:101:9: ( '=' expression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==21) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:101:13: '=' expression
                            {
                            match(input,21,FOLLOW_21_in_statement485); if (state.failed) return ;

                            pushFollow(FOLLOW_expression_in_statement487);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,20,FOLLOW_20_in_statement501); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:103:7: ';'
                    {
                    match(input,20,FOLLOW_20_in_statement510); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:1: expressionList : ( expression ( ',' expression )* |);
    public final void expressionList() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:108:5: ( expression ( ',' expression )* |)
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0 >= Identifier && LA14_0 <= Integer)||LA14_0==12) ) {
                alt14=1;
            }
            else if ( (LA14_0==13) ) {
                alt14=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:108:9: expression ( ',' expression )*
                    {
                    pushFollow(FOLLOW_expression_in_expressionList531);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:108:20: ( ',' expression )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==16) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:108:21: ',' expression
                    	    {
                    	    match(input,16,FOLLOW_16_in_expressionList534); if (state.failed) return ;

                    	    pushFollow(FOLLOW_expression_in_expressionList536);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:108:40: 
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:112:1: expression returns [VariableSymbol value] : addExpression ;
    public final VariableSymbol expression() throws RecognitionException {
        VariableSymbol value = null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:113:5: ( addExpression )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:113:9: addExpression
            {
            pushFollow(FOLLOW_addExpression_in_expression561);
            addExpression();

            state._fsp--;
            if (state.failed) return value;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:118:1: addExpression returns [VariableSymbol value] : e= multExpression ( '+' e= multExpression | '-' e= multExpression )* ;
    public final VariableSymbol addExpression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:5: (e= multExpression ( '+' e= multExpression | '-' e= multExpression )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:9: e= multExpression ( '+' e= multExpression | '-' e= multExpression )*
            {
            pushFollow(FOLLOW_multExpression_in_addExpression589);
            e=multExpression();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:120:9: ( '+' e= multExpression | '-' e= multExpression )*
            loop15:
            do {
                int alt15=3;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==15) ) {
                    alt15=1;
                }
                else if ( (LA15_0==17) ) {
                    alt15=2;
                }


                switch (alt15) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:120:13: '+' e= multExpression
            	    {
            	    match(input,15,FOLLOW_15_in_addExpression605); if (state.failed) return value;

            	    pushFollow(FOLLOW_multExpression_in_addExpression609);
            	    e=multExpression();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = add(value,e);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:121:13: '-' e= multExpression
            	    {
            	    match(input,17,FOLLOW_17_in_addExpression625); if (state.failed) return value;

            	    pushFollow(FOLLOW_multExpression_in_addExpression629);
            	    e=multExpression();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = sub(value,e);}

            	    }
            	    break;

            	default :
            	    break loop15;
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:1: multExpression returns [VariableSymbol value] : e= postfixExpression ( '*' e= postfixExpression )* ;
    public final VariableSymbol multExpression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:128:5: (e= postfixExpression ( '*' e= postfixExpression )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:128:9: e= postfixExpression ( '*' e= postfixExpression )*
            {
            pushFollow(FOLLOW_postfixExpression_in_multExpression669);
            e=postfixExpression();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:129:5: ( '*' e= postfixExpression )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==14) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:129:6: '*' e= postfixExpression
            	    {
            	    match(input,14,FOLLOW_14_in_multExpression679); if (state.failed) return value;

            	    pushFollow(FOLLOW_postfixExpression_in_multExpression683);
            	    e=postfixExpression();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = mul(value,e);}

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
    // $ANTLR end "multExpression"



    // $ANTLR start "postfixExpression"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:136:1: postfixExpression returns [VariableSymbol value] : ( primary ) ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' )* ;
    public final VariableSymbol postfixExpression() throws RecognitionException {
        VariableSymbol value = null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:5: ( ( primary ) ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:9: ( primary ) ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:9: ( primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:10: primary
            {
            pushFollow(FOLLOW_primary_in_postfixExpression721);
            primary();

            state._fsp--;
            if (state.failed) return value;

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:138:7: ( options {backtrack=true; } : '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' )*
            loop17:
            do {
                int alt17=4;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==18) ) {
                    int LA17_8 = input.LA(2);

                    if ( (synpred1_Nebula()) ) {
                        alt17=1;
                    }
                    else if ( (synpred2_Nebula()) ) {
                        alt17=2;
                    }


                }
                else if ( (LA17_0==12) ) {
                    alt17=3;
                }


                switch (alt17) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:139:7: '.' Identifier '(' expressionList ')'
            	    {
            	    match(input,18,FOLLOW_18_in_postfixExpression745); if (state.failed) return value;

            	    match(input,Identifier,FOLLOW_Identifier_in_postfixExpression747); if (state.failed) return value;

            	    match(input,12,FOLLOW_12_in_postfixExpression749); if (state.failed) return value;

            	    pushFollow(FOLLOW_expressionList_in_postfixExpression751);
            	    expressionList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixExpression753); if (state.failed) return value;

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:140:7: '.' Identifier
            	    {
            	    match(input,18,FOLLOW_18_in_postfixExpression761); if (state.failed) return value;

            	    match(input,Identifier,FOLLOW_Identifier_in_postfixExpression763); if (state.failed) return value;

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:7: '(' expressionList ')'
            	    {
            	    match(input,12,FOLLOW_12_in_postfixExpression784); if (state.failed) return value;

            	    pushFollow(FOLLOW_expressionList_in_postfixExpression786);
            	    expressionList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixExpression788); if (state.failed) return value;

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
    // $ANTLR end "postfixExpression"



    // $ANTLR start "suffix"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:147:1: suffix[VariableSymbol expr] returns [VariableSymbol value] options {backtrack=true; } : ( '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' );
    public final VariableSymbol suffix(VariableSymbol expr) throws RecognitionException {
        VariableSymbol value = null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:149:3: ( '.' Identifier '(' expressionList ')' | '.' Identifier | '(' expressionList ')' )
            int alt18=3;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==18) ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1==Identifier) ) {
                    int LA18_3 = input.LA(3);

                    if ( (LA18_3==12) ) {
                        alt18=1;
                    }
                    else if ( (LA18_3==EOF) ) {
                        alt18=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return value;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 18, 3, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA18_0==12) ) {
                alt18=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }
            switch (alt18) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:149:5: '.' Identifier '(' expressionList ')'
                    {
                    match(input,18,FOLLOW_18_in_suffix825); if (state.failed) return value;

                    match(input,Identifier,FOLLOW_Identifier_in_suffix827); if (state.failed) return value;

                    match(input,12,FOLLOW_12_in_suffix829); if (state.failed) return value;

                    pushFollow(FOLLOW_expressionList_in_suffix831);
                    expressionList();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_suffix833); if (state.failed) return value;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:150:5: '.' Identifier
                    {
                    match(input,18,FOLLOW_18_in_suffix839); if (state.failed) return value;

                    match(input,Identifier,FOLLOW_Identifier_in_suffix841); if (state.failed) return value;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:151:5: '(' expressionList ')'
                    {
                    match(input,12,FOLLOW_12_in_suffix861); if (state.failed) return value;

                    pushFollow(FOLLOW_expressionList_in_suffix863);
                    expressionList();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_suffix865); if (state.failed) return value;

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
    // $ANTLR end "suffix"



    // $ANTLR start "primary"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:1: primary returns [VariableSymbol value] : ( Integer | Identifier | '(' expression ')' );
    public final VariableSymbol primary() throws RecognitionException {
        VariableSymbol value = null;


        Token Integer4=null;
        Token Identifier5=null;
        VariableSymbol expression6 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:5: ( Integer | Identifier | '(' expression ')' )
            int alt19=3;
            switch ( input.LA(1) ) {
            case Integer:
                {
                alt19=1;
                }
                break;
            case Identifier:
                {
                alt19=2;
                }
                break;
            case 12:
                {
                alt19=3;
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:9: Integer
                    {
                    Integer4=(Token)match(input,Integer,FOLLOW_Integer_in_primary907); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = defineInt((Integer4!=null?Integer4.getText():null));}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:159:9: Identifier
                    {
                    Identifier5=(Token)match(input,Identifier,FOLLOW_Identifier_in_primary919); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = resolve((Identifier5!=null?Identifier5.getText():null));}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:9: '(' expression ')'
                    {
                    match(input,12,FOLLOW_12_in_primary940); if (state.failed) return value;

                    pushFollow(FOLLOW_expression_in_primary942);
                    expression6=expression();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_primary944); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = expression6;}

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
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:139:7: ( '.' Identifier '(' expressionList ')' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:139:7: '.' Identifier '(' expressionList ')'
        {
        match(input,18,FOLLOW_18_in_synpred1_Nebula745); if (state.failed) return ;

        match(input,Identifier,FOLLOW_Identifier_in_synpred1_Nebula747); if (state.failed) return ;

        match(input,12,FOLLOW_12_in_synpred1_Nebula749); if (state.failed) return ;

        pushFollow(FOLLOW_expressionList_in_synpred1_Nebula751);
        expressionList();

        state._fsp--;
        if (state.failed) return ;

        match(input,13,FOLLOW_13_in_synpred1_Nebula753); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_Nebula

    // $ANTLR start synpred2_Nebula
    public final void synpred2_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:140:7: ( '.' Identifier )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:140:7: '.' Identifier
        {
        match(input,18,FOLLOW_18_in_synpred2_Nebula761); if (state.failed) return ;

        match(input,Identifier,FOLLOW_Identifier_in_synpred2_Nebula763); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_Nebula

    // Delegated rules

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


 

    public static final BitSet FOLLOW_classDefinition_in_compilationUnit55 = new BitSet(new long[]{0x0000000009C00020L});
    public static final BitSet FOLLOW_varDeclaration_in_compilationUnit59 = new BitSet(new long[]{0x0000000009C00020L});
    public static final BitSet FOLLOW_methodDeclaration_in_compilationUnit63 = new BitSet(new long[]{0x0000000009C00020L});
    public static final BitSet FOLLOW_EOF_in_compilationUnit68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_classDefinition88 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_classDefinition90 = new BitSet(new long[]{0x0000000010080000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition92 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_classDefinition120 = new BitSet(new long[]{0x000000000B800020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition122 = new BitSet(new long[]{0x000000002B800020L});
    public static final BitSet FOLLOW_29_in_classDefinition125 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_classDefinition127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_superClass169 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_superClass171 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_superClass173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_classMember188 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_classMember190 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_classMember193 = new BitSet(new long[]{0x0000000000001060L});
    public static final BitSet FOLLOW_expression_in_classMember195 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_classMember199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_classMember212 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_classMember214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration235 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_methodDeclaration237 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration239 = new BitSet(new long[]{0x0000000009802020L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration241 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration244 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameters308 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_formalParameters310 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_formalParameters313 = new BitSet(new long[]{0x0000000009800020L});
    public static final BitSet FOLLOW_type_in_formalParameters315 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_formalParameters317 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_28_in_block381 = new BitSet(new long[]{0x000000003D901060L});
    public static final BitSet FOLLOW_statement_in_block383 = new BitSet(new long[]{0x000000003D901060L});
    public static final BitSet FOLLOW_29_in_block386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration403 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_varDeclaration405 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_varDeclaration408 = new BitSet(new long[]{0x0000000000001060L});
    public static final BitSet FOLLOW_expression_in_varDeclaration410 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_varDeclaration414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_statement454 = new BitSet(new long[]{0x0000000000101060L});
    public static final BitSet FOLLOW_expression_in_statement456 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixExpression_in_statement470 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_statement485 = new BitSet(new long[]{0x0000000000001060L});
    public static final BitSet FOLLOW_expression_in_statement487 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_statement510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList531 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_expressionList534 = new BitSet(new long[]{0x0000000000001060L});
    public static final BitSet FOLLOW_expression_in_expressionList536 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_addExpression_in_expression561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpression_in_addExpression589 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_15_in_addExpression605 = new BitSet(new long[]{0x0000000000001060L});
    public static final BitSet FOLLOW_multExpression_in_addExpression609 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_17_in_addExpression625 = new BitSet(new long[]{0x0000000000001060L});
    public static final BitSet FOLLOW_multExpression_in_addExpression629 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_postfixExpression_in_multExpression669 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_multExpression679 = new BitSet(new long[]{0x0000000000001060L});
    public static final BitSet FOLLOW_postfixExpression_in_multExpression683 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_primary_in_postfixExpression721 = new BitSet(new long[]{0x0000000000041002L});
    public static final BitSet FOLLOW_18_in_postfixExpression745 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_postfixExpression747 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixExpression749 = new BitSet(new long[]{0x0000000000003060L});
    public static final BitSet FOLLOW_expressionList_in_postfixExpression751 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixExpression753 = new BitSet(new long[]{0x0000000000041002L});
    public static final BitSet FOLLOW_18_in_postfixExpression761 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_postfixExpression763 = new BitSet(new long[]{0x0000000000041002L});
    public static final BitSet FOLLOW_12_in_postfixExpression784 = new BitSet(new long[]{0x0000000000003060L});
    public static final BitSet FOLLOW_expressionList_in_postfixExpression786 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixExpression788 = new BitSet(new long[]{0x0000000000041002L});
    public static final BitSet FOLLOW_18_in_suffix825 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_suffix827 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_suffix829 = new BitSet(new long[]{0x0000000000003060L});
    public static final BitSet FOLLOW_expressionList_in_suffix831 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_suffix833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_suffix839 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_suffix841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_suffix861 = new BitSet(new long[]{0x0000000000003060L});
    public static final BitSet FOLLOW_expressionList_in_suffix863 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_suffix865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Integer_in_primary907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_primary919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary940 = new BitSet(new long[]{0x0000000000001060L});
    public static final BitSet FOLLOW_expression_in_primary942 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred1_Nebula745 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_synpred1_Nebula747 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_synpred1_Nebula749 = new BitSet(new long[]{0x0000000000003060L});
    public static final BitSet FOLLOW_expressionList_in_synpred1_Nebula751 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_synpred1_Nebula753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred2_Nebula761 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_synpred2_Nebula763 = new BitSet(new long[]{0x0000000000000002L});

}