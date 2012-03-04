// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-04 22:28:40

package nebula.vm;
import nebula.vm.Var;
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Digit", "ID", "INT", "Letter", "MultiLineComment", "NEWLINE", "SingleLineComment", "Whitespace", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "';'", "'='", "'['", "']'", "'class'", "'decimal'", "'extends'", "'int'", "'return'", "'super'", "'this'", "'void'", "'{'", "'}'"
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
    public static final int ID=5;
    public static final int INT=6;
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


      /** Map variable name to INT object holding value */

      protected void enterClass(String name,Type superType) {};
      protected void exitClass() {;};
      
      protected void enterFunction(String name,Type returnType,List<Var> list) {;};
      protected void exitFunction() {;};
      
      protected void defField(String name,Type type){;};
      
      protected Type resolveType(String name){return null;};
        
      protected Var resolve(String name) {return null;};
      protected Var defVariable(String name,Type type) {return null;};
      protected Var defInt(String name) {return null;};
      
      protected Var call(Var name,List<Var> list){return null;};
      protected Var getField(Var obj,String text){return null;};  
      protected Var index(Var obj,Var i){return null;};
      protected Var index(Var obj,List<Var> cause){return null;};
      protected void ret(Var a) {;};
      
        
      protected Var eval(Var a) {return null;};
      protected Var evalSet(String id,Var b) {return null;};

      protected Var add(Var a, Var b) {return null;};
      protected Var sub(Var a, Var b) {return null;};
      protected Var mul(Var a, Var b) {return null;};  
      protected Var load(Var a, Var b) {return null;}; 



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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:55:1: classDefinition : 'class' ID ( superClass )? '{' ( classMember )+ '}' ;
    public final void classDefinition() throws RecognitionException {
        Token ID1=null;
        Type superClass2 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:56:5: ( 'class' ID ( superClass )? '{' ( classMember )+ '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:56:9: 'class' ID ( superClass )? '{' ( classMember )+ '}'
            {
            match(input,23,FOLLOW_23_in_classDefinition80); if (state.failed) return ;

            ID1=(Token)match(input,ID,FOLLOW_ID_in_classDefinition82); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:56:20: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==25) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:56:20: superClass
                    {
                    pushFollow(FOLLOW_superClass_in_classDefinition84);
                    superClass2=superClass();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {enterClass((ID1!=null?ID1.getText():null),superClass2);}

            match(input,31,FOLLOW_31_in_classDefinition112); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:58:14: ( classMember )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID||LA3_0==24||LA3_0==26||LA3_0==30) ) {
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:62:1: superClass returns [Type type] : 'extends' id= ID ;
    public final Type superClass() throws RecognitionException {
        Type type = null;


        Token id=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:63:3: ( 'extends' id= ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:63:5: 'extends' id= ID
            {
            match(input,25,FOLLOW_25_in_superClass162); if (state.failed) return type;

            id=(Token)match(input,ID,FOLLOW_ID_in_superClass166); if (state.failed) return type;

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

                if ( (LA4_1==ID) ) {
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

                if ( (LA4_2==ID) ) {
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

                if ( (LA4_3==ID) ) {
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
            case ID:
                {
                int LA4_4 = input.LA(2);

                if ( (LA4_4==ID) ) {
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:73:1: fieldDeclaration : type ID ( '=' e= expr )? ';' ;
    public final void fieldDeclaration() throws RecognitionException {
        Token ID3=null;
        Var e =null;

        Type type4 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:5: ( type ID ( '=' e= expr )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:9: type ID ( '=' e= expr )? ';'
            {
            pushFollow(FOLLOW_type_in_fieldDeclaration208);
            type4=type();

            state._fsp--;
            if (state.failed) return ;

            ID3=(Token)match(input,ID,FOLLOW_ID_in_fieldDeclaration210); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:17: ( '=' e= expr )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==20) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:18: '=' e= expr
                    {
                    match(input,20,FOLLOW_20_in_fieldDeclaration213); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_fieldDeclaration217);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,19,FOLLOW_19_in_fieldDeclaration221); if (state.failed) return ;

            if ( state.backtracking==0 ) {defField((ID3!=null?ID3.getText():null),type4);}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:78:1: methodDeclaration : type name= ID '(' (params= formalParameters )? ')' block ;
    public final void methodDeclaration() throws RecognitionException {
        Token name=null;
        List<Var> params =null;

        Type type5 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:5: ( type name= ID '(' (params= formalParameters )? ')' block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:9: type name= ID '(' (params= formalParameters )? ')' block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration245);
            type5=type();

            state._fsp--;
            if (state.failed) return ;

            name=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration249); if (state.failed) return ;

            match(input,12,FOLLOW_12_in_methodDeclaration251); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:32: (params= formalParameters )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ID||LA6_0==24||LA6_0==26||LA6_0==30) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:32: params= formalParameters
                    {
                    pushFollow(FOLLOW_formalParameters_in_methodDeclaration255);
                    params=formalParameters();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,13,FOLLOW_13_in_methodDeclaration258); if (state.failed) return ;

            if ( state.backtracking==0 ) {enterFunction((name!=null?name.getText():null),type5,params);}

            pushFollow(FOLLOW_block_in_methodDeclaration281);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:86:1: formalParameters returns [List<Var> list] : t= type id= ID ( ',' t= type id= ID )* ;
    public final List<Var> formalParameters() throws RecognitionException {
        List<Var> list = null;


        Token id=null;
        Type t =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:88:5: (t= type id= ID ( ',' t= type id= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:88:9: t= type id= ID ( ',' t= type id= ID )*
            {
            pushFollow(FOLLOW_type_in_formalParameters331);
            t=type();

            state._fsp--;
            if (state.failed) return list;

            id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters335); if (state.failed) return list;

            if ( state.backtracking==0 ) {list.add(defVariable((id!=null?id.getText():null),t));}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:89:9: ( ',' t= type id= ID )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:89:11: ',' t= type id= ID
            	    {
            	    match(input,16,FOLLOW_16_in_formalParameters348); if (state.failed) return list;

            	    pushFollow(FOLLOW_type_in_formalParameters352);
            	    t=type();

            	    state._fsp--;
            	    if (state.failed) return list;

            	    id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters356); if (state.failed) return list;

            	    if ( state.backtracking==0 ) {list.add(defVariable((id!=null?id.getText():null),t));}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:93:1: type returns [Type type] : ( 'decimal' | 'int' | 'void' | ID );
    public final Type type() throws RecognitionException {
        Type type = null;


        Token ID6=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:94:5: ( 'decimal' | 'int' | 'void' | ID )
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
            case ID:
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
                    match(input,24,FOLLOW_24_in_type393); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:95:9: 'int'
                    {
                    match(input,26,FOLLOW_26_in_type405); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:96:9: 'void'
                    {
                    match(input,30,FOLLOW_30_in_type417); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:97:9: ID
                    {
                    ID6=(Token)match(input,ID,FOLLOW_ID_in_type429); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = resolveType((ID6!=null?ID6.getText():null));}

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
            match(input,31,FOLLOW_31_in_block451); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:102:13: ( statement )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= ID && LA9_0 <= INT)||LA9_0==12||LA9_0==19||LA9_0==24||(LA9_0 >= 26 && LA9_0 <= 31)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:102:13: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block453);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            match(input,32,FOLLOW_32_in_block456); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:106:1: varDeclaration : type ID ( '=' e= expr )? ';' ;
    public final void varDeclaration() throws RecognitionException {
        Token ID7=null;
        Var e =null;

        Type type8 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:5: ( type ID ( '=' e= expr )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:9: type ID ( '=' e= expr )? ';'
            {
            pushFollow(FOLLOW_type_in_varDeclaration477);
            type8=type();

            state._fsp--;
            if (state.failed) return ;

            ID7=(Token)match(input,ID,FOLLOW_ID_in_varDeclaration479); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:17: ( '=' e= expr )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==20) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:18: '=' e= expr
                    {
                    match(input,20,FOLLOW_20_in_varDeclaration482); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_varDeclaration486);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,19,FOLLOW_19_in_varDeclaration490); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                      defVariable((ID7!=null?ID7.getText():null),type8);  
                      evalSet((ID7!=null?ID7.getText():null),e);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:114:1: statement : ( block | varDeclaration | 'return' (e= expr )? ';' | postfixexpr ( '=' expr )? ';' | ';' );
    public final void statement() throws RecognitionException {
        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:115:5: ( block | varDeclaration | 'return' (e= expr )? ';' | postfixexpr ( '=' expr )? ';' | ';' )
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
            case ID:
                {
                int LA13_3 = input.LA(2);

                if ( (LA13_3==ID) ) {
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
            case INT:
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
                    pushFollow(FOLLOW_block_in_statement512);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:116:9: varDeclaration
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement522);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:9: 'return' (e= expr )? ';'
                    {
                    match(input,27,FOLLOW_27_in_statement532); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:19: (e= expr )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0 >= ID && LA11_0 <= INT)||LA11_0==12||(LA11_0 >= 28 && LA11_0 <= 29)) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:19: e= expr
                            {
                            pushFollow(FOLLOW_expr_in_statement536);
                            e=expr();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,19,FOLLOW_19_in_statement539); if (state.failed) return ;

                    if ( state.backtracking==0 ) {ret(e);}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:118:9: postfixexpr ( '=' expr )? ';'
                    {
                    pushFollow(FOLLOW_postfixexpr_in_statement551);
                    postfixexpr();

                    state._fsp--;
                    if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:9: ( '=' expr )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==20) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:13: '=' expr
                            {
                            match(input,20,FOLLOW_20_in_statement565); if (state.failed) return ;

                            pushFollow(FOLLOW_expr_in_statement567);
                            expr();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,19,FOLLOW_19_in_statement581); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:121:7: ';'
                    {
                    match(input,19,FOLLOW_19_in_statement590); if (state.failed) return ;

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



    // $ANTLR start "exprList"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:125:1: exprList returns [List<Var> list] : (e= expr ( ',' expr )* |);
    public final List<Var> exprList() throws RecognitionException {
        List<Var> list = null;


        Var e =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:5: (e= expr ( ',' expr )* |)
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0 >= ID && LA15_0 <= INT)||LA15_0==12||(LA15_0 >= 28 && LA15_0 <= 29)) ) {
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:9: e= expr ( ',' expr )*
                    {
                    pushFollow(FOLLOW_expr_in_exprList629);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return list;

                    if ( state.backtracking==0 ) {list.add(e);}

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:30: ( ',' expr )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==16) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:31: ',' expr
                    	    {
                    	    match(input,16,FOLLOW_16_in_exprList633); if (state.failed) return list;

                    	    pushFollow(FOLLOW_expr_in_exprList635);
                    	    expr();

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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:58: 
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
    // $ANTLR end "exprList"



    // $ANTLR start "expr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:131:1: expr returns [Var value] : e= addexpr ;
    public final Var expr() throws RecognitionException {
        Var value = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:132:5: (e= addexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:132:9: e= addexpr
            {
            pushFollow(FOLLOW_addexpr_in_expr663);
            e=addexpr();

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
    // $ANTLR end "expr"



    // $ANTLR start "addexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:136:1: addexpr returns [Var value] : e= multexpr ( '+' e= multexpr | '-' e= multexpr )* ;
    public final Var addexpr() throws RecognitionException {
        Var value = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:5: (e= multexpr ( '+' e= multexpr | '-' e= multexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:9: e= multexpr ( '+' e= multexpr | '-' e= multexpr )*
            {
            pushFollow(FOLLOW_multexpr_in_addexpr692);
            e=multexpr();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:138:9: ( '+' e= multexpr | '-' e= multexpr )*
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:138:13: '+' e= multexpr
            	    {
            	    match(input,15,FOLLOW_15_in_addexpr708); if (state.failed) return value;

            	    pushFollow(FOLLOW_multexpr_in_addexpr712);
            	    e=multexpr();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = add(value,e);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:139:13: '-' e= multexpr
            	    {
            	    match(input,17,FOLLOW_17_in_addexpr728); if (state.failed) return value;

            	    pushFollow(FOLLOW_multexpr_in_addexpr732);
            	    e=multexpr();

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
    // $ANTLR end "addexpr"



    // $ANTLR start "multexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:145:1: multexpr returns [Var value] : e= postfixexpr ( '*' e= postfixexpr )* ;
    public final Var multexpr() throws RecognitionException {
        Var value = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:5: (e= postfixexpr ( '*' e= postfixexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:9: e= postfixexpr ( '*' e= postfixexpr )*
            {
            pushFollow(FOLLOW_postfixexpr_in_multexpr772);
            e=postfixexpr();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:147:9: ( '*' e= postfixexpr )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==14) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:147:13: '*' e= postfixexpr
            	    {
            	    match(input,14,FOLLOW_14_in_multexpr789); if (state.failed) return value;

            	    pushFollow(FOLLOW_postfixexpr_in_multexpr793);
            	    e=postfixexpr();

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
    // $ANTLR end "multexpr"



    // $ANTLR start "postfixexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:154:1: postfixexpr returns [Var value] : (e= primary ) ( options {backtrack=true; } : '.' name= ID '(' exprList ')' | '.' name= ID | '(' params= exprList ')' | '[' INT ']' | '[' cause= exprList ']' )* ;
    public final Var postfixexpr() throws RecognitionException {
        Var value = null;


        Token name=null;
        Token INT9=null;
        Var e =null;

        List<Var> params =null;

        List<Var> cause =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:5: ( (e= primary ) ( options {backtrack=true; } : '.' name= ID '(' exprList ')' | '.' name= ID | '(' params= exprList ')' | '[' INT ']' | '[' cause= exprList ']' )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:9: (e= primary ) ( options {backtrack=true; } : '.' name= ID '(' exprList ')' | '.' name= ID | '(' params= exprList ')' | '[' INT ']' | '[' cause= exprList ']' )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:9: (e= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:10: e= primary
            {
            pushFollow(FOLLOW_primary_in_postfixexpr838);
            e=primary();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:9: ( options {backtrack=true; } : '.' name= ID '(' exprList ')' | '.' name= ID | '(' params= exprList ')' | '[' INT ']' | '[' cause= exprList ']' )*
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:12: '.' name= ID '(' exprList ')'
            	    {
            	    match(input,18,FOLLOW_18_in_postfixexpr870); if (state.failed) return value;

            	    name=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr874); if (state.failed) return value;

            	    match(input,12,FOLLOW_12_in_postfixexpr876); if (state.failed) return value;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr878);
            	    exprList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixexpr880); if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = call(getField(value,(name!=null?name.getText():null)),params);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:12: '.' name= ID
            	    {
            	    match(input,18,FOLLOW_18_in_postfixexpr895); if (state.failed) return value;

            	    name=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr899); if (state.failed) return value;

            	    if ( state.backtracking==0 ) { value = getField(value,(name!=null?name.getText():null));}

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:159:12: '(' params= exprList ')'
            	    {
            	    match(input,12,FOLLOW_12_in_postfixexpr925); if (state.failed) return value;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr929);
            	    params=exprList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixexpr931); if (state.failed) return value;

            	    if ( state.backtracking==0 ) { value = call(value,params);}

            	    }
            	    break;
            	case 4 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:12: '[' INT ']'
            	    {
            	    match(input,21,FOLLOW_21_in_postfixexpr947); if (state.failed) return value;

            	    INT9=(Token)match(input,INT,FOLLOW_INT_in_postfixexpr949); if (state.failed) return value;

            	    match(input,22,FOLLOW_22_in_postfixexpr951); if (state.failed) return value;

            	    if ( state.backtracking==0 ) { value = index(value,defInt((INT9!=null?INT9.getText():null)));}

            	    }
            	    break;
            	case 5 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:12: '[' cause= exprList ']'
            	    {
            	    match(input,21,FOLLOW_21_in_postfixexpr981); if (state.failed) return value;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr985);
            	    cause=exprList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,22,FOLLOW_22_in_postfixexpr987); if (state.failed) return value;

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
    // $ANTLR end "postfixexpr"



    // $ANTLR start "primary"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:167:1: primary returns [Var value] : (id= 'this' |id= 'super' | INT | ID | '(' expr ')' );
    public final Var primary() throws RecognitionException {
        Var value = null;


        Token id=null;
        Token INT10=null;
        Token ID11=null;
        Var expr12 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:168:5: (id= 'this' |id= 'super' | INT | ID | '(' expr ')' )
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
            case INT:
                {
                alt19=3;
                }
                break;
            case ID:
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:168:9: id= 'this'
                    {
                    id=(Token)match(input,29,FOLLOW_29_in_primary1029); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = resolve((id!=null?id.getText():null));}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:169:9: id= 'super'
                    {
                    id=(Token)match(input,28,FOLLOW_28_in_primary1042); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = resolve((id!=null?id.getText():null));}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:170:9: INT
                    {
                    INT10=(Token)match(input,INT,FOLLOW_INT_in_primary1053); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = defInt((INT10!=null?INT10.getText():null));}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:171:9: ID
                    {
                    ID11=(Token)match(input,ID,FOLLOW_ID_in_primary1065); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = resolve((ID11!=null?ID11.getText():null));}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:172:9: '(' expr ')'
                    {
                    match(input,12,FOLLOW_12_in_primary1077); if (state.failed) return value;

                    pushFollow(FOLLOW_expr_in_primary1079);
                    expr12=expr();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_primary1081); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = expr12;}

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

        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:12: ( '.' name= ID '(' exprList ')' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:12: '.' name= ID '(' exprList ')'
        {
        match(input,18,FOLLOW_18_in_synpred1_Nebula870); if (state.failed) return ;

        name=(Token)match(input,ID,FOLLOW_ID_in_synpred1_Nebula874); if (state.failed) return ;

        match(input,12,FOLLOW_12_in_synpred1_Nebula876); if (state.failed) return ;

        pushFollow(FOLLOW_exprList_in_synpred1_Nebula878);
        exprList();

        state._fsp--;
        if (state.failed) return ;

        match(input,13,FOLLOW_13_in_synpred1_Nebula880); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_Nebula

    // $ANTLR start synpred2_Nebula
    public final void synpred2_Nebula_fragment() throws RecognitionException {
        Token name=null;

        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:12: ( '.' name= ID )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:12: '.' name= ID
        {
        match(input,18,FOLLOW_18_in_synpred2_Nebula895); if (state.failed) return ;

        name=(Token)match(input,ID,FOLLOW_ID_in_synpred2_Nebula899); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_Nebula

    // $ANTLR start synpred4_Nebula
    public final void synpred4_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:12: ( '[' INT ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:12: '[' INT ']'
        {
        match(input,21,FOLLOW_21_in_synpred4_Nebula947); if (state.failed) return ;

        match(input,INT,FOLLOW_INT_in_synpred4_Nebula949); if (state.failed) return ;

        match(input,22,FOLLOW_22_in_synpred4_Nebula951); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred4_Nebula

    // $ANTLR start synpred5_Nebula
    public final void synpred5_Nebula_fragment() throws RecognitionException {
        List<Var> cause =null;


        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:12: ( '[' cause= exprList ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:12: '[' cause= exprList ']'
        {
        match(input,21,FOLLOW_21_in_synpred5_Nebula981); if (state.failed) return ;

        pushFollow(FOLLOW_exprList_in_synpred5_Nebula985);
        cause=exprList();

        state._fsp--;
        if (state.failed) return ;

        match(input,22,FOLLOW_22_in_synpred5_Nebula987); if (state.failed) return ;

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
    public static final BitSet FOLLOW_ID_in_classDefinition82 = new BitSet(new long[]{0x0000000082000000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition84 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_classDefinition112 = new BitSet(new long[]{0x0000000045000020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition114 = new BitSet(new long[]{0x0000000145000020L});
    public static final BitSet FOLLOW_32_in_classDefinition117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_superClass162 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_superClass166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fieldDeclaration_in_classMember181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_fieldDeclaration208 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_fieldDeclaration210 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_fieldDeclaration213 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expr_in_fieldDeclaration217 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_fieldDeclaration221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration245 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration249 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration251 = new BitSet(new long[]{0x0000000045002020L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration255 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration258 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameters331 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters335 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_formalParameters348 = new BitSet(new long[]{0x0000000045000020L});
    public static final BitSet FOLLOW_type_in_formalParameters352 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters356 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_24_in_type393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_type405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_type417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_block451 = new BitSet(new long[]{0x00000001FD081060L});
    public static final BitSet FOLLOW_statement_in_block453 = new BitSet(new long[]{0x00000001FD081060L});
    public static final BitSet FOLLOW_32_in_block456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration477 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_varDeclaration479 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_varDeclaration482 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expr_in_varDeclaration486 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_varDeclaration490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_statement532 = new BitSet(new long[]{0x0000000030081060L});
    public static final BitSet FOLLOW_expr_in_statement536 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixexpr_in_statement551 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_statement565 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expr_in_statement567 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_statement590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprList629 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_exprList633 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expr_in_exprList635 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_addexpr_in_expr663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multexpr_in_addexpr692 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_15_in_addexpr708 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr712 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_17_in_addexpr728 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr732 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_postfixexpr_in_multexpr772 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_multexpr789 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_postfixexpr_in_multexpr793 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_primary_in_postfixexpr838 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_18_in_postfixexpr870 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr874 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixexpr876 = new BitSet(new long[]{0x0000000030003060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr878 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixexpr880 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_18_in_postfixexpr895 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr899 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_12_in_postfixexpr925 = new BitSet(new long[]{0x0000000030003060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr929 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixexpr931 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_21_in_postfixexpr947 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_INT_in_postfixexpr949 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_postfixexpr951 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_21_in_postfixexpr981 = new BitSet(new long[]{0x0000000030401060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr985 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_postfixexpr987 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_29_in_primary1029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_primary1042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_primary1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary1077 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expr_in_primary1079 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred1_Nebula870 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_synpred1_Nebula874 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_synpred1_Nebula876 = new BitSet(new long[]{0x0000000030003060L});
    public static final BitSet FOLLOW_exprList_in_synpred1_Nebula878 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_synpred1_Nebula880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred2_Nebula895 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_synpred2_Nebula899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_synpred4_Nebula947 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_INT_in_synpred4_Nebula949 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_synpred4_Nebula951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_synpred5_Nebula981 = new BitSet(new long[]{0x0000000030401060L});
    public static final BitSet FOLLOW_exprList_in_synpred5_Nebula985 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_synpred5_Nebula987 = new BitSet(new long[]{0x0000000000000002L});

}