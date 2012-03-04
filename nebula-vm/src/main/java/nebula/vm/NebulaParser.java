// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-04 22:15:17

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Digit", "Identifier", "Integer", "Letter", "MultiLineComment", "NEWLINE", "SingleLineComment", "Whitespace", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "';'", "'='", "'['", "']'", "'class'", "'decimal'", "'extends'", "'int'", "'return'", "'super'", "'this'", "'void'", "'{'", "'}'"
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

      protected void enterClass(String name,Type superType) {};
      protected void exitClass() {;};
      
      protected void enterFunction(String name,Type returnType,List<VariableSymbol> list) {;};
      protected void exitFunction() {;};
      
      protected void defineField(String name,Type type){;};
      
      protected Type resolveType(String name){return null;};
        
      protected VariableSymbol resolve(String name) {return null;};
      protected VariableSymbol defineVariable(String name,Type type) {return null;};
      protected VariableSymbol defineInt(String name) {return null;};
      
      protected VariableSymbol call(VariableSymbol name,List<VariableSymbol> list){return null;};
      protected VariableSymbol getField(VariableSymbol obj,String text){return null;};  
      protected VariableSymbol index(VariableSymbol obj,VariableSymbol i){return null;};
      protected VariableSymbol index(VariableSymbol obj,List<VariableSymbol> cause){return null;};
      protected void ret(VariableSymbol a) {;};
      
        
      protected VariableSymbol eval(VariableSymbol a) {return null;};
      protected VariableSymbol evalSet(String id,VariableSymbol b) {return null;};

      protected VariableSymbol add(VariableSymbol a, VariableSymbol b) {return null;};
      protected VariableSymbol sub(VariableSymbol a, VariableSymbol b) {return null;};
      protected VariableSymbol mul(VariableSymbol a, VariableSymbol b) {return null;};  
      protected VariableSymbol load(VariableSymbol a, VariableSymbol b) {return null;}; 



    // $ANTLR start "compilationUnit"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:50:1: compilationUnit : ( classDefinition )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:51:5: ( ( classDefinition )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:51:9: ( classDefinition )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:51:9: ( classDefinition )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==23) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:51:11: classDefinition
            	    {
            	    pushFollow(FOLLOW_classDefinition_in_compilationUnit55);
            	    classDefinition();

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


            match(input,EOF,FOLLOW_EOF_in_compilationUnit60); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:55:1: classDefinition : 'class' Identifier ( superClass )? '{' ( classMember )+ '}' ;
    public final void classDefinition() throws RecognitionException {
        Token Identifier1=null;
        Type superClass2 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:56:5: ( 'class' Identifier ( superClass )? '{' ( classMember )+ '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:56:9: 'class' Identifier ( superClass )? '{' ( classMember )+ '}'
            {
            match(input,23,FOLLOW_23_in_classDefinition80); if (state.failed) return ;

            Identifier1=(Token)match(input,Identifier,FOLLOW_Identifier_in_classDefinition82); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:56:28: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==25) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:56:28: superClass
                    {
                    pushFollow(FOLLOW_superClass_in_classDefinition84);
                    superClass2=superClass();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {enterClass((Identifier1!=null?Identifier1.getText():null),superClass2);}

            match(input,31,FOLLOW_31_in_classDefinition112); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:58:14: ( classMember )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==Identifier||LA3_0==24||LA3_0==26||LA3_0==30) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:58:14: classMember
            	    {
            	    pushFollow(FOLLOW_classMember_in_classDefinition114);
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


            match(input,32,FOLLOW_32_in_classDefinition117); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:62:1: superClass returns [Type type] : 'extends' id= Identifier ;
    public final Type superClass() throws RecognitionException {
        Type type = null;


        Token id=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:63:3: ( 'extends' id= Identifier )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:63:5: 'extends' id= Identifier
            {
            match(input,25,FOLLOW_25_in_superClass162); if (state.failed) return type;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_superClass166); if (state.failed) return type;

            if ( state.backtracking==0 ) {type =resolveType((id!=null?id.getText():null));}

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
    // $ANTLR end "superClass"



    // $ANTLR start "classMember"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:66:1: classMember : ( fieldDeclaration | methodDeclaration );
    public final void classMember() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:3: ( fieldDeclaration | methodDeclaration )
            int alt4=2;
            switch ( input.LA(1) ) {
            case 24:
                {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==Identifier) ) {
                    int LA4_5 = input.LA(3);

                    if ( (LA4_5==12) ) {
                        alt4=2;
                    }
                    else if ( ((LA4_5 >= 19 && LA4_5 <= 20)) ) {
                        alt4=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 5, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;

                }
                }
                break;
            case 26:
                {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==Identifier) ) {
                    int LA4_5 = input.LA(3);

                    if ( (LA4_5==12) ) {
                        alt4=2;
                    }
                    else if ( ((LA4_5 >= 19 && LA4_5 <= 20)) ) {
                        alt4=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 5, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;

                }
                }
                break;
            case 30:
                {
                int LA4_3 = input.LA(2);

                if ( (LA4_3==Identifier) ) {
                    int LA4_5 = input.LA(3);

                    if ( (LA4_5==12) ) {
                        alt4=2;
                    }
                    else if ( ((LA4_5 >= 19 && LA4_5 <= 20)) ) {
                        alt4=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 5, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 3, input);

                    throw nvae;

                }
                }
                break;
            case Identifier:
                {
                int LA4_4 = input.LA(2);

                if ( (LA4_4==Identifier) ) {
                    int LA4_5 = input.LA(3);

                    if ( (LA4_5==12) ) {
                        alt4=2;
                    }
                    else if ( ((LA4_5 >= 19 && LA4_5 <= 20)) ) {
                        alt4=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 5, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 4, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:5: fieldDeclaration
                    {
                    pushFollow(FOLLOW_fieldDeclaration_in_classMember181);
                    fieldDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:68:5: methodDeclaration
                    {
                    pushFollow(FOLLOW_methodDeclaration_in_classMember187);
                    methodDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

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



    // $ANTLR start "fieldDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:73:1: fieldDeclaration : type ID= Identifier ( '=' e= expression )? ';' ;
    public final void fieldDeclaration() throws RecognitionException {
        Token ID=null;
        VariableSymbol e =null;

        Type type3 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:5: ( type ID= Identifier ( '=' e= expression )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:9: type ID= Identifier ( '=' e= expression )? ';'
            {
            pushFollow(FOLLOW_type_in_fieldDeclaration208);
            type3=type();

            state._fsp--;
            if (state.failed) return ;

            ID=(Token)match(input,Identifier,FOLLOW_Identifier_in_fieldDeclaration212); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:28: ( '=' e= expression )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==20) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:29: '=' e= expression
                    {
                    match(input,20,FOLLOW_20_in_fieldDeclaration215); if (state.failed) return ;

                    pushFollow(FOLLOW_expression_in_fieldDeclaration219);
                    e=expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,19,FOLLOW_19_in_fieldDeclaration223); if (state.failed) return ;

            if ( state.backtracking==0 ) {defineField((ID!=null?ID.getText():null),type3);}

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



    // $ANTLR start "methodDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:78:1: methodDeclaration : type name= Identifier '(' (params= formalParameters )? ')' block ;
    public final void methodDeclaration() throws RecognitionException {
        Token name=null;
        List<VariableSymbol> params =null;

        Type type4 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:5: ( type name= Identifier '(' (params= formalParameters )? ')' block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:9: type name= Identifier '(' (params= formalParameters )? ')' block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration247);
            type4=type();

            state._fsp--;
            if (state.failed) return ;

            name=(Token)match(input,Identifier,FOLLOW_Identifier_in_methodDeclaration251); if (state.failed) return ;

            match(input,12,FOLLOW_12_in_methodDeclaration253); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:40: (params= formalParameters )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==Identifier||LA6_0==24||LA6_0==26||LA6_0==30) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:40: params= formalParameters
                    {
                    pushFollow(FOLLOW_formalParameters_in_methodDeclaration257);
                    params=formalParameters();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,13,FOLLOW_13_in_methodDeclaration260); if (state.failed) return ;

            if ( state.backtracking==0 ) {enterFunction((name!=null?name.getText():null),type4,params);}

            pushFollow(FOLLOW_block_in_methodDeclaration283);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:86:1: formalParameters returns [List<VariableSymbol> list] : t= type ID= Identifier ( ',' t= type ID= Identifier )* ;
    public final List<VariableSymbol> formalParameters() throws RecognitionException {
        List<VariableSymbol> list = null;


        Token ID=null;
        Type t =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:88:5: (t= type ID= Identifier ( ',' t= type ID= Identifier )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:88:9: t= type ID= Identifier ( ',' t= type ID= Identifier )*
            {
            pushFollow(FOLLOW_type_in_formalParameters333);
            t=type();

            state._fsp--;
            if (state.failed) return list;

            ID=(Token)match(input,Identifier,FOLLOW_Identifier_in_formalParameters337); if (state.failed) return list;

            if ( state.backtracking==0 ) {list.add(defineVariable((ID!=null?ID.getText():null),t));}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:89:9: ( ',' t= type ID= Identifier )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:89:11: ',' t= type ID= Identifier
            	    {
            	    match(input,16,FOLLOW_16_in_formalParameters350); if (state.failed) return list;

            	    pushFollow(FOLLOW_type_in_formalParameters354);
            	    t=type();

            	    state._fsp--;
            	    if (state.failed) return list;

            	    ID=(Token)match(input,Identifier,FOLLOW_Identifier_in_formalParameters358); if (state.failed) return list;

            	    if ( state.backtracking==0 ) {list.add(defineVariable((ID!=null?ID.getText():null),t));}

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
        return list;
    }
    // $ANTLR end "formalParameters"



    // $ANTLR start "type"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:93:1: type returns [Type type] : ( 'decimal' | 'int' | 'void' |ID= Identifier );
    public final Type type() throws RecognitionException {
        Type type = null;


        Token ID=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:94:5: ( 'decimal' | 'int' | 'void' |ID= Identifier )
            int alt8=4;
            switch ( input.LA(1) ) {
            case 24:
                {
                alt8=1;
                }
                break;
            case 26:
                {
                alt8=2;
                }
                break;
            case 30:
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:94:9: 'decimal'
                    {
                    match(input,24,FOLLOW_24_in_type395); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:95:9: 'int'
                    {
                    match(input,26,FOLLOW_26_in_type407); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:96:9: 'void'
                    {
                    match(input,30,FOLLOW_30_in_type419); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:97:9: ID= Identifier
                    {
                    ID=(Token)match(input,Identifier,FOLLOW_Identifier_in_type433); if (state.failed) return type;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:101:1: block : '{' ( statement )* '}' ;
    public final void block() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:102:5: ( '{' ( statement )* '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:102:9: '{' ( statement )* '}'
            {
            match(input,31,FOLLOW_31_in_block455); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:102:13: ( statement )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= Identifier && LA9_0 <= Integer)||LA9_0==12||LA9_0==19||LA9_0==24||(LA9_0 >= 26 && LA9_0 <= 31)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:102:13: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block457);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            match(input,32,FOLLOW_32_in_block460); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:106:1: varDeclaration : type ID= Identifier ( '=' e= expression )? ';' ;
    public final void varDeclaration() throws RecognitionException {
        Token ID=null;
        VariableSymbol e =null;

        Type type5 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:5: ( type ID= Identifier ( '=' e= expression )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:9: type ID= Identifier ( '=' e= expression )? ';'
            {
            pushFollow(FOLLOW_type_in_varDeclaration481);
            type5=type();

            state._fsp--;
            if (state.failed) return ;

            ID=(Token)match(input,Identifier,FOLLOW_Identifier_in_varDeclaration485); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:28: ( '=' e= expression )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==20) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:29: '=' e= expression
                    {
                    match(input,20,FOLLOW_20_in_varDeclaration488); if (state.failed) return ;

                    pushFollow(FOLLOW_expression_in_varDeclaration492);
                    e=expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,19,FOLLOW_19_in_varDeclaration496); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                      defineVariable((ID!=null?ID.getText():null),type5);  
                      evalSet((ID!=null?ID.getText():null),e);
                    }

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:114:1: statement : ( block | varDeclaration | 'return' (e= expression )? ';' | postfixExpression ( '=' expression )? ';' | ';' );
    public final void statement() throws RecognitionException {
        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:115:5: ( block | varDeclaration | 'return' (e= expression )? ';' | postfixExpression ( '=' expression )? ';' | ';' )
            int alt13=5;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt13=1;
                }
                break;
            case 24:
            case 26:
            case 30:
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
                else if ( (LA13_3==12||(LA13_3 >= 18 && LA13_3 <= 21)) ) {
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
            case 27:
                {
                alt13=3;
                }
                break;
            case Integer:
            case 12:
            case 28:
            case 29:
                {
                alt13=4;
                }
                break;
            case 19:
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:115:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement518);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:116:9: varDeclaration
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement528);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:9: 'return' (e= expression )? ';'
                    {
                    match(input,27,FOLLOW_27_in_statement538); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:19: (e= expression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0 >= Identifier && LA11_0 <= Integer)||LA11_0==12||(LA11_0 >= 28 && LA11_0 <= 29)) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:19: e= expression
                            {
                            pushFollow(FOLLOW_expression_in_statement542);
                            e=expression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,19,FOLLOW_19_in_statement545); if (state.failed) return ;

                    if ( state.backtracking==0 ) {ret(e);}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:118:9: postfixExpression ( '=' expression )? ';'
                    {
                    pushFollow(FOLLOW_postfixExpression_in_statement557);
                    postfixExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:9: ( '=' expression )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==20) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:13: '=' expression
                            {
                            match(input,20,FOLLOW_20_in_statement571); if (state.failed) return ;

                            pushFollow(FOLLOW_expression_in_statement573);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,19,FOLLOW_19_in_statement587); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:121:7: ';'
                    {
                    match(input,19,FOLLOW_19_in_statement596); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:125:1: expressionList returns [List<VariableSymbol> list] : (e= expression ( ',' expression )* |);
    public final List<VariableSymbol> expressionList() throws RecognitionException {
        List<VariableSymbol> list = null;


        VariableSymbol e =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:5: (e= expression ( ',' expression )* |)
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0 >= Identifier && LA15_0 <= Integer)||LA15_0==12||(LA15_0 >= 28 && LA15_0 <= 29)) ) {
                alt15=1;
            }
            else if ( (LA15_0==13||LA15_0==22) ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return list;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }
            switch (alt15) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:9: e= expression ( ',' expression )*
                    {
                    pushFollow(FOLLOW_expression_in_expressionList635);
                    e=expression();

                    state._fsp--;
                    if (state.failed) return list;

                    if ( state.backtracking==0 ) {list.add(e);}

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:36: ( ',' expression )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==16) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:37: ',' expression
                    	    {
                    	    match(input,16,FOLLOW_16_in_expressionList639); if (state.failed) return list;

                    	    pushFollow(FOLLOW_expression_in_expressionList641);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return list;

                    	    if ( state.backtracking==0 ) {list.add(e);}

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:70: 
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
        return list;
    }
    // $ANTLR end "expressionList"



    // $ANTLR start "expression"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:131:1: expression returns [VariableSymbol value] : e= addExpression ;
    public final VariableSymbol expression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:132:5: (e= addExpression )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:132:9: e= addExpression
            {
            pushFollow(FOLLOW_addExpression_in_expression669);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:136:1: addExpression returns [VariableSymbol value] : e= multExpression ( '+' e= multExpression | '-' e= multExpression )* ;
    public final VariableSymbol addExpression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:5: (e= multExpression ( '+' e= multExpression | '-' e= multExpression )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:9: e= multExpression ( '+' e= multExpression | '-' e= multExpression )*
            {
            pushFollow(FOLLOW_multExpression_in_addExpression698);
            e=multExpression();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:138:9: ( '+' e= multExpression | '-' e= multExpression )*
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:138:13: '+' e= multExpression
            	    {
            	    match(input,15,FOLLOW_15_in_addExpression714); if (state.failed) return value;

            	    pushFollow(FOLLOW_multExpression_in_addExpression718);
            	    e=multExpression();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = add(value,e);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:139:13: '-' e= multExpression
            	    {
            	    match(input,17,FOLLOW_17_in_addExpression734); if (state.failed) return value;

            	    pushFollow(FOLLOW_multExpression_in_addExpression738);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:145:1: multExpression returns [VariableSymbol value] : e= postfixExpression ( '*' e= postfixExpression )* ;
    public final VariableSymbol multExpression() throws RecognitionException {
        VariableSymbol value = null;


        VariableSymbol e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:5: (e= postfixExpression ( '*' e= postfixExpression )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:9: e= postfixExpression ( '*' e= postfixExpression )*
            {
            pushFollow(FOLLOW_postfixExpression_in_multExpression778);
            e=postfixExpression();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:147:9: ( '*' e= postfixExpression )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==14) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:147:13: '*' e= postfixExpression
            	    {
            	    match(input,14,FOLLOW_14_in_multExpression795); if (state.failed) return value;

            	    pushFollow(FOLLOW_postfixExpression_in_multExpression799);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:154:1: postfixExpression returns [VariableSymbol value] : (e= primary ) ( options {backtrack=true; } : '.' name= Identifier '(' expressionList ')' | '.' name= Identifier | '(' params= expressionList ')' | '[' Integer ']' | '[' cause= expressionList ']' )* ;
    public final VariableSymbol postfixExpression() throws RecognitionException {
        VariableSymbol value = null;


        Token name=null;
        Token Integer6=null;
        VariableSymbol e =null;

        List<VariableSymbol> params =null;

        List<VariableSymbol> cause =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:5: ( (e= primary ) ( options {backtrack=true; } : '.' name= Identifier '(' expressionList ')' | '.' name= Identifier | '(' params= expressionList ')' | '[' Integer ']' | '[' cause= expressionList ']' )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:9: (e= primary ) ( options {backtrack=true; } : '.' name= Identifier '(' expressionList ')' | '.' name= Identifier | '(' params= expressionList ')' | '[' Integer ']' | '[' cause= expressionList ']' )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:9: (e= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:10: e= primary
            {
            pushFollow(FOLLOW_primary_in_postfixExpression844);
            e=primary();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:9: ( options {backtrack=true; } : '.' name= Identifier '(' expressionList ')' | '.' name= Identifier | '(' params= expressionList ')' | '[' Integer ']' | '[' cause= expressionList ']' )*
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
                case 21:
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:12: '.' name= Identifier '(' expressionList ')'
            	    {
            	    match(input,18,FOLLOW_18_in_postfixExpression876); if (state.failed) return value;

            	    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_postfixExpression880); if (state.failed) return value;

            	    match(input,12,FOLLOW_12_in_postfixExpression882); if (state.failed) return value;

            	    pushFollow(FOLLOW_expressionList_in_postfixExpression884);
            	    expressionList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixExpression886); if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = call(getField(value,(name!=null?name.getText():null)),params);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:12: '.' name= Identifier
            	    {
            	    match(input,18,FOLLOW_18_in_postfixExpression901); if (state.failed) return value;

            	    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_postfixExpression905); if (state.failed) return value;

            	    if ( state.backtracking==0 ) { value = getField(value,(name!=null?name.getText():null));}

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:159:12: '(' params= expressionList ')'
            	    {
            	    match(input,12,FOLLOW_12_in_postfixExpression931); if (state.failed) return value;

            	    pushFollow(FOLLOW_expressionList_in_postfixExpression935);
            	    params=expressionList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixExpression937); if (state.failed) return value;

            	    if ( state.backtracking==0 ) { value = call(value,params);}

            	    }
            	    break;
            	case 4 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:12: '[' Integer ']'
            	    {
            	    match(input,21,FOLLOW_21_in_postfixExpression953); if (state.failed) return value;

            	    Integer6=(Token)match(input,Integer,FOLLOW_Integer_in_postfixExpression955); if (state.failed) return value;

            	    match(input,22,FOLLOW_22_in_postfixExpression957); if (state.failed) return value;

            	    if ( state.backtracking==0 ) { value = index(value,defineInt((Integer6!=null?Integer6.getText():null)));}

            	    }
            	    break;
            	case 5 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:12: '[' cause= expressionList ']'
            	    {
            	    match(input,21,FOLLOW_21_in_postfixExpression987); if (state.failed) return value;

            	    pushFollow(FOLLOW_expressionList_in_postfixExpression991);
            	    cause=expressionList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,22,FOLLOW_22_in_postfixExpression993); if (state.failed) return value;

            	    if ( state.backtracking==0 ) { value = index(value,cause);}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:167:1: primary returns [VariableSymbol value] : (ID= 'this' |ID= 'super' | Integer | Identifier | '(' expression ')' );
    public final VariableSymbol primary() throws RecognitionException {
        VariableSymbol value = null;


        Token ID=null;
        Token Integer7=null;
        Token Identifier8=null;
        VariableSymbol expression9 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:168:5: (ID= 'this' |ID= 'super' | Integer | Identifier | '(' expression ')' )
            int alt19=5;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt19=1;
                }
                break;
            case 28:
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:168:9: ID= 'this'
                    {
                    ID=(Token)match(input,29,FOLLOW_29_in_primary1035); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = resolve((ID!=null?ID.getText():null));}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:169:9: ID= 'super'
                    {
                    ID=(Token)match(input,28,FOLLOW_28_in_primary1048); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = resolve((ID!=null?ID.getText():null));}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:170:9: Integer
                    {
                    Integer7=(Token)match(input,Integer,FOLLOW_Integer_in_primary1059); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = defineInt((Integer7!=null?Integer7.getText():null));}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:171:9: Identifier
                    {
                    Identifier8=(Token)match(input,Identifier,FOLLOW_Identifier_in_primary1071); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = resolve((Identifier8!=null?Identifier8.getText():null));}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:172:9: '(' expression ')'
                    {
                    match(input,12,FOLLOW_12_in_primary1083); if (state.failed) return value;

                    pushFollow(FOLLOW_expression_in_primary1085);
                    expression9=expression();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_primary1087); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = expression9;}

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
        Token name=null;

        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:12: ( '.' name= Identifier '(' expressionList ')' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:12: '.' name= Identifier '(' expressionList ')'
        {
        match(input,18,FOLLOW_18_in_synpred1_Nebula876); if (state.failed) return ;

        name=(Token)match(input,Identifier,FOLLOW_Identifier_in_synpred1_Nebula880); if (state.failed) return ;

        match(input,12,FOLLOW_12_in_synpred1_Nebula882); if (state.failed) return ;

        pushFollow(FOLLOW_expressionList_in_synpred1_Nebula884);
        expressionList();

        state._fsp--;
        if (state.failed) return ;

        match(input,13,FOLLOW_13_in_synpred1_Nebula886); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_Nebula

    // $ANTLR start synpred2_Nebula
    public final void synpred2_Nebula_fragment() throws RecognitionException {
        Token name=null;

        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:12: ( '.' name= Identifier )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:12: '.' name= Identifier
        {
        match(input,18,FOLLOW_18_in_synpred2_Nebula901); if (state.failed) return ;

        name=(Token)match(input,Identifier,FOLLOW_Identifier_in_synpred2_Nebula905); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_Nebula

    // $ANTLR start synpred4_Nebula
    public final void synpred4_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:12: ( '[' Integer ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:12: '[' Integer ']'
        {
        match(input,21,FOLLOW_21_in_synpred4_Nebula953); if (state.failed) return ;

        match(input,Integer,FOLLOW_Integer_in_synpred4_Nebula955); if (state.failed) return ;

        match(input,22,FOLLOW_22_in_synpred4_Nebula957); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred4_Nebula

    // $ANTLR start synpred5_Nebula
    public final void synpred5_Nebula_fragment() throws RecognitionException {
        List<VariableSymbol> cause =null;


        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:12: ( '[' cause= expressionList ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:12: '[' cause= expressionList ']'
        {
        match(input,21,FOLLOW_21_in_synpred5_Nebula987); if (state.failed) return ;

        pushFollow(FOLLOW_expressionList_in_synpred5_Nebula991);
        cause=expressionList();

        state._fsp--;
        if (state.failed) return ;

        match(input,22,FOLLOW_22_in_synpred5_Nebula993); if (state.failed) return ;

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


 

    public static final BitSet FOLLOW_classDefinition_in_compilationUnit55 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_EOF_in_compilationUnit60 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_classDefinition80 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_classDefinition82 = new BitSet(new long[]{0x0000000082000000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition84 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_classDefinition112 = new BitSet(new long[]{0x0000000045000020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition114 = new BitSet(new long[]{0x0000000145000020L});
    public static final BitSet FOLLOW_32_in_classDefinition117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_superClass162 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_superClass166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fieldDeclaration_in_classMember181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_fieldDeclaration208 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_fieldDeclaration212 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_fieldDeclaration215 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expression_in_fieldDeclaration219 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_fieldDeclaration223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration247 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_methodDeclaration251 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration253 = new BitSet(new long[]{0x0000000045002020L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration257 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration260 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameters333 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_formalParameters337 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_formalParameters350 = new BitSet(new long[]{0x0000000045000020L});
    public static final BitSet FOLLOW_type_in_formalParameters354 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_formalParameters358 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_24_in_type395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_type407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_type419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_type433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_block455 = new BitSet(new long[]{0x00000001FD081060L});
    public static final BitSet FOLLOW_statement_in_block457 = new BitSet(new long[]{0x00000001FD081060L});
    public static final BitSet FOLLOW_32_in_block460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration481 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_varDeclaration485 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_varDeclaration488 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expression_in_varDeclaration492 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_varDeclaration496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_statement538 = new BitSet(new long[]{0x0000000030081060L});
    public static final BitSet FOLLOW_expression_in_statement542 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixExpression_in_statement557 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_statement571 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expression_in_statement573 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_statement596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList635 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_expressionList639 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expression_in_expressionList641 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_addExpression_in_expression669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpression_in_addExpression698 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_15_in_addExpression714 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_multExpression_in_addExpression718 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_17_in_addExpression734 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_multExpression_in_addExpression738 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_postfixExpression_in_multExpression778 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_multExpression795 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_postfixExpression_in_multExpression799 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_primary_in_postfixExpression844 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_18_in_postfixExpression876 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_postfixExpression880 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixExpression882 = new BitSet(new long[]{0x0000000030003060L});
    public static final BitSet FOLLOW_expressionList_in_postfixExpression884 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixExpression886 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_18_in_postfixExpression901 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_postfixExpression905 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_12_in_postfixExpression931 = new BitSet(new long[]{0x0000000030003060L});
    public static final BitSet FOLLOW_expressionList_in_postfixExpression935 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixExpression937 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_21_in_postfixExpression953 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_Integer_in_postfixExpression955 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_postfixExpression957 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_21_in_postfixExpression987 = new BitSet(new long[]{0x0000000030401060L});
    public static final BitSet FOLLOW_expressionList_in_postfixExpression991 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_postfixExpression993 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_29_in_primary1035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_primary1048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Integer_in_primary1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_primary1071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary1083 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expression_in_primary1085 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred1_Nebula876 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_synpred1_Nebula880 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_synpred1_Nebula882 = new BitSet(new long[]{0x0000000030003060L});
    public static final BitSet FOLLOW_expressionList_in_synpred1_Nebula884 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_synpred1_Nebula886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred2_Nebula901 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Identifier_in_synpred2_Nebula905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_synpred4_Nebula953 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_Integer_in_synpred4_Nebula955 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_synpred4_Nebula957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_synpred5_Nebula987 = new BitSet(new long[]{0x0000000030401060L});
    public static final BitSet FOLLOW_expressionList_in_synpred5_Nebula991 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_synpred5_Nebula993 = new BitSet(new long[]{0x0000000000000002L});

}