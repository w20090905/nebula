// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-05 14:04:06

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

      // START:members
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
      
      protected Var invoke(Var name,String funcName,List<Var> list){return null;};
      protected Var invokeStatic(String name,List<Var> list){return null;};
      protected Var refField(Var obj,String text){return null;};  
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:46:1: compilationUnit : ( classDefinition )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:47:5: ( ( classDefinition )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:47:9: ( classDefinition )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:47:9: ( classDefinition )+
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:47:11: classDefinition
            	    {
            	    pushFollow(FOLLOW_classDefinition_in_compilationUnit51);
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


            match(input,EOF,FOLLOW_EOF_in_compilationUnit56); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:50:1: classDefinition : 'class' ID ( superClass )? '{' ( classMember )+ '}' ;
    public final void classDefinition() throws RecognitionException {
        Token ID1=null;
        Type superClass2 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:51:5: ( 'class' ID ( superClass )? '{' ( classMember )+ '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:51:9: 'class' ID ( superClass )? '{' ( classMember )+ '}'
            {
            match(input,23,FOLLOW_23_in_classDefinition76); if (state.failed) return ;

            ID1=(Token)match(input,ID,FOLLOW_ID_in_classDefinition78); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:51:20: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==25) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:51:20: superClass
                    {
                    pushFollow(FOLLOW_superClass_in_classDefinition80);
                    superClass2=superClass();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {enterClass((ID1!=null?ID1.getText():null),superClass2);}

            match(input,31,FOLLOW_31_in_classDefinition108); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:53:14: ( classMember )+
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:53:14: classMember
            	    {
            	    pushFollow(FOLLOW_classMember_in_classDefinition110);
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


            match(input,32,FOLLOW_32_in_classDefinition113); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:57:1: superClass returns [Type type] : 'extends' id= ID ;
    public final Type superClass() throws RecognitionException {
        Type type = null;


        Token id=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:58:3: ( 'extends' id= ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:58:5: 'extends' id= ID
            {
            match(input,25,FOLLOW_25_in_superClass158); if (state.failed) return type;

            id=(Token)match(input,ID,FOLLOW_ID_in_superClass162); if (state.failed) return type;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:61:1: classMember : ( fieldDeclaration | methodDeclaration );
    public final void classMember() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:62:3: ( fieldDeclaration | methodDeclaration )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:62:5: fieldDeclaration
                    {
                    pushFollow(FOLLOW_fieldDeclaration_in_classMember177);
                    fieldDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:63:5: methodDeclaration
                    {
                    pushFollow(FOLLOW_methodDeclaration_in_classMember183);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:66:1: fieldDeclaration : type ID ( '=' e= expr )? ';' ;
    public final void fieldDeclaration() throws RecognitionException {
        Token ID3=null;
        Var e =null;

        Type type4 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:5: ( type ID ( '=' e= expr )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:9: type ID ( '=' e= expr )? ';'
            {
            pushFollow(FOLLOW_type_in_fieldDeclaration203);
            type4=type();

            state._fsp--;
            if (state.failed) return ;

            ID3=(Token)match(input,ID,FOLLOW_ID_in_fieldDeclaration205); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:17: ( '=' e= expr )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==20) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:18: '=' e= expr
                    {
                    match(input,20,FOLLOW_20_in_fieldDeclaration208); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_fieldDeclaration212);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,19,FOLLOW_19_in_fieldDeclaration216); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:70:1: methodDeclaration : type name= ID '(' (params= formalParameters )? ')' block ;
    public final void methodDeclaration() throws RecognitionException {
        Token name=null;
        List<Var> params =null;

        Type type5 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:71:5: ( type name= ID '(' (params= formalParameters )? ')' block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:71:9: type name= ID '(' (params= formalParameters )? ')' block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration238);
            type5=type();

            state._fsp--;
            if (state.failed) return ;

            name=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration242); if (state.failed) return ;

            match(input,12,FOLLOW_12_in_methodDeclaration244); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:71:32: (params= formalParameters )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ID||LA6_0==24||LA6_0==26||LA6_0==30) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:71:32: params= formalParameters
                    {
                    pushFollow(FOLLOW_formalParameters_in_methodDeclaration248);
                    params=formalParameters();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,13,FOLLOW_13_in_methodDeclaration251); if (state.failed) return ;

            if ( state.backtracking==0 ) {enterFunction((name!=null?name.getText():null),type5,params);}

            pushFollow(FOLLOW_block_in_methodDeclaration274);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:77:1: formalParameters returns [List<Var> list] : t= type id= ID ( ',' t= type id= ID )* ;
    public final List<Var> formalParameters() throws RecognitionException {
        List<Var> list = null;


        Token id=null;
        Type t =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:5: (t= type id= ID ( ',' t= type id= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:9: t= type id= ID ( ',' t= type id= ID )*
            {
            pushFollow(FOLLOW_type_in_formalParameters324);
            t=type();

            state._fsp--;
            if (state.failed) return list;

            id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters328); if (state.failed) return list;

            if ( state.backtracking==0 ) {list.add(defVariable((id!=null?id.getText():null),t));}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:80:9: ( ',' t= type id= ID )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:80:11: ',' t= type id= ID
            	    {
            	    match(input,16,FOLLOW_16_in_formalParameters341); if (state.failed) return list;

            	    pushFollow(FOLLOW_type_in_formalParameters345);
            	    t=type();

            	    state._fsp--;
            	    if (state.failed) return list;

            	    id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters349); if (state.failed) return list;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:84:1: type returns [Type type] : ( 'decimal' | 'int' | 'void' | ID );
    public final Type type() throws RecognitionException {
        Type type = null;


        Token ID6=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:85:5: ( 'decimal' | 'int' | 'void' | ID )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:85:9: 'decimal'
                    {
                    match(input,24,FOLLOW_24_in_type386); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:86:9: 'int'
                    {
                    match(input,26,FOLLOW_26_in_type398); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:87:9: 'void'
                    {
                    match(input,30,FOLLOW_30_in_type410); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:88:9: ID
                    {
                    ID6=(Token)match(input,ID,FOLLOW_ID_in_type422); if (state.failed) return type;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:91:1: block : '{' ( statement )* '}' ;
    public final void block() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:92:5: ( '{' ( statement )* '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:92:9: '{' ( statement )* '}'
            {
            match(input,31,FOLLOW_31_in_block444); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:92:13: ( statement )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= ID && LA9_0 <= INT)||LA9_0==12||LA9_0==19||LA9_0==24||(LA9_0 >= 26 && LA9_0 <= 31)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:92:13: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block446);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            match(input,32,FOLLOW_32_in_block449); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:95:1: varDeclaration : type ID ( '=' e= expr )? ';' ;
    public final void varDeclaration() throws RecognitionException {
        Token ID7=null;
        Var e =null;

        Type type8 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:96:5: ( type ID ( '=' e= expr )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:96:9: type ID ( '=' e= expr )? ';'
            {
            pushFollow(FOLLOW_type_in_varDeclaration476);
            type8=type();

            state._fsp--;
            if (state.failed) return ;

            ID7=(Token)match(input,ID,FOLLOW_ID_in_varDeclaration478); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:96:17: ( '=' e= expr )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==20) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:96:18: '=' e= expr
                    {
                    match(input,20,FOLLOW_20_in_varDeclaration481); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_varDeclaration485);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,19,FOLLOW_19_in_varDeclaration489); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:102:1: statement : ( block | varDeclaration | 'return' (e= expr )? ';' | postfixexpr ( '=' expr )? ';' | ';' );
    public final void statement() throws RecognitionException {
        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:103:5: ( block | varDeclaration | 'return' (e= expr )? ';' | postfixexpr ( '=' expr )? ';' | ';' )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:103:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement511);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:104:9: varDeclaration
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement521);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:105:9: 'return' (e= expr )? ';'
                    {
                    match(input,27,FOLLOW_27_in_statement531); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:105:19: (e= expr )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0 >= ID && LA11_0 <= INT)||LA11_0==12||(LA11_0 >= 28 && LA11_0 <= 29)) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:105:19: e= expr
                            {
                            pushFollow(FOLLOW_expr_in_statement535);
                            e=expr();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,19,FOLLOW_19_in_statement538); if (state.failed) return ;

                    if ( state.backtracking==0 ) {ret(e);}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:106:9: postfixexpr ( '=' expr )? ';'
                    {
                    pushFollow(FOLLOW_postfixexpr_in_statement550);
                    postfixexpr();

                    state._fsp--;
                    if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:9: ( '=' expr )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==20) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:107:13: '=' expr
                            {
                            match(input,20,FOLLOW_20_in_statement564); if (state.failed) return ;

                            pushFollow(FOLLOW_expr_in_statement566);
                            expr();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,19,FOLLOW_19_in_statement580); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:109:7: ';'
                    {
                    match(input,19,FOLLOW_19_in_statement589); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:112:1: exprList returns [List<Var> list] : (e= expr ( ',' expr )* |);
    public final List<Var> exprList() throws RecognitionException {
        List<Var> list = null;


        Var e =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:114:5: (e= expr ( ',' expr )* |)
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:114:9: e= expr ( ',' expr )*
                    {
                    pushFollow(FOLLOW_expr_in_exprList628);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return list;

                    if ( state.backtracking==0 ) {list.add(e);}

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:114:30: ( ',' expr )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==16) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:114:31: ',' expr
                    	    {
                    	    match(input,16,FOLLOW_16_in_exprList632); if (state.failed) return list;

                    	    pushFollow(FOLLOW_expr_in_exprList634);
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:115:5: 
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:1: expr returns [Var value] : e= addexpr ;
    public final Var expr() throws RecognitionException {
        Var value = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:118:5: (e= addexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:118:9: e= addexpr
            {
            pushFollow(FOLLOW_addexpr_in_expr666);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:121:1: addexpr returns [Var value] : e= multexpr ( '+' e= multexpr | '-' e= multexpr )* ;
    public final Var addexpr() throws RecognitionException {
        Var value = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:122:5: (e= multexpr ( '+' e= multexpr | '-' e= multexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:122:9: e= multexpr ( '+' e= multexpr | '-' e= multexpr )*
            {
            pushFollow(FOLLOW_multexpr_in_addexpr699);
            e=multexpr();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:123:9: ( '+' e= multexpr | '-' e= multexpr )*
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:123:13: '+' e= multexpr
            	    {
            	    match(input,15,FOLLOW_15_in_addexpr715); if (state.failed) return value;

            	    pushFollow(FOLLOW_multexpr_in_addexpr719);
            	    e=multexpr();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = add(value,e);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:124:13: '-' e= multexpr
            	    {
            	    match(input,17,FOLLOW_17_in_addexpr735); if (state.failed) return value;

            	    pushFollow(FOLLOW_multexpr_in_addexpr739);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:128:1: multexpr returns [Var value] : e= postfixexpr ( '*' e= postfixexpr )* ;
    public final Var multexpr() throws RecognitionException {
        Var value = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:129:5: (e= postfixexpr ( '*' e= postfixexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:129:9: e= postfixexpr ( '*' e= postfixexpr )*
            {
            pushFollow(FOLLOW_postfixexpr_in_multexpr780);
            e=postfixexpr();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:130:9: ( '*' e= postfixexpr )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==14) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:130:13: '*' e= postfixexpr
            	    {
            	    match(input,14,FOLLOW_14_in_multexpr797); if (state.failed) return value;

            	    pushFollow(FOLLOW_postfixexpr_in_multexpr801);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:134:1: postfixexpr returns [Var value] : (e= primary ) ( options {backtrack=true; } : '.' name= ID '(' exprList ')' | '.' name= ID | '(' params= exprList ')' | '[' INT ']' | '[' cause= exprList ']' )* ;
    public final Var postfixexpr() throws RecognitionException {
        Var value = null;


        Token name=null;
        Token INT9=null;
        NebulaParser.primary_return e =null;

        List<Var> params =null;

        List<Var> cause =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:135:5: ( (e= primary ) ( options {backtrack=true; } : '.' name= ID '(' exprList ')' | '.' name= ID | '(' params= exprList ')' | '[' INT ']' | '[' cause= exprList ']' )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:135:9: (e= primary ) ( options {backtrack=true; } : '.' name= ID '(' exprList ')' | '.' name= ID | '(' params= exprList ')' | '[' INT ']' | '[' cause= exprList ']' )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:135:9: (e= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:135:10: e= primary
            {
            pushFollow(FOLLOW_primary_in_postfixexpr844);
            e=primary();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = (e!=null?e.value:null);}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:136:9: ( options {backtrack=true; } : '.' name= ID '(' exprList ')' | '.' name= ID | '(' params= exprList ')' | '[' INT ']' | '[' cause= exprList ']' )*
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:12: '.' name= ID '(' exprList ')'
            	    {
            	    match(input,18,FOLLOW_18_in_postfixexpr876); if (state.failed) return value;

            	    name=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr880); if (state.failed) return value;

            	    match(input,12,FOLLOW_12_in_postfixexpr882); if (state.failed) return value;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr884);
            	    exprList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixexpr886); if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = invoke(value,(name!=null?name.getText():null),params);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:138:12: '.' name= ID
            	    {
            	    match(input,18,FOLLOW_18_in_postfixexpr901); if (state.failed) return value;

            	    name=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr905); if (state.failed) return value;

            	    if ( state.backtracking==0 ) { value = refField(value,(name!=null?name.getText():null));}

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:139:12: '(' params= exprList ')'
            	    {
            	    match(input,12,FOLLOW_12_in_postfixexpr931); if (state.failed) return value;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr935);
            	    params=exprList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixexpr937); if (state.failed) return value;

            	    if ( state.backtracking==0 ) { value = invokeStatic((e!=null?input.toString(e.start,e.stop):null),params);}

            	    }
            	    break;
            	case 4 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:140:12: '[' INT ']'
            	    {
            	    match(input,21,FOLLOW_21_in_postfixexpr953); if (state.failed) return value;

            	    INT9=(Token)match(input,INT,FOLLOW_INT_in_postfixexpr955); if (state.failed) return value;

            	    match(input,22,FOLLOW_22_in_postfixexpr957); if (state.failed) return value;

            	    if ( state.backtracking==0 ) { value = index(value,defInt((INT9!=null?INT9.getText():null)));}

            	    }
            	    break;
            	case 5 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:12: '[' cause= exprList ']'
            	    {
            	    match(input,21,FOLLOW_21_in_postfixexpr987); if (state.failed) return value;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr991);
            	    cause=exprList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,22,FOLLOW_22_in_postfixexpr993); if (state.failed) return value;

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


    public static class primary_return extends ParserRuleReturnScope {
        public Var value;
    };


    // $ANTLR start "primary"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:145:1: primary returns [Var value] : (id= 'this' |id= 'super' | INT | ID | '(' expr ')' );
    public final NebulaParser.primary_return primary() throws RecognitionException {
        NebulaParser.primary_return retval = new NebulaParser.primary_return();
        retval.start = input.LT(1);


        Token id=null;
        Token INT10=null;
        Token ID11=null;
        Var expr12 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:5: (id= 'this' |id= 'super' | INT | ID | '(' expr ')' )
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
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:9: id= 'this'
                    {
                    id=(Token)match(input,29,FOLLOW_29_in_primary1035); if (state.failed) return retval;

                    if ( state.backtracking==0 ) {retval.value = resolve((id!=null?id.getText():null));}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:147:9: id= 'super'
                    {
                    id=(Token)match(input,28,FOLLOW_28_in_primary1048); if (state.failed) return retval;

                    if ( state.backtracking==0 ) {retval.value = resolve((id!=null?id.getText():null));}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:148:9: INT
                    {
                    INT10=(Token)match(input,INT,FOLLOW_INT_in_primary1059); if (state.failed) return retval;

                    if ( state.backtracking==0 ) {retval.value = defInt((INT10!=null?INT10.getText():null));}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:149:9: ID
                    {
                    ID11=(Token)match(input,ID,FOLLOW_ID_in_primary1071); if (state.failed) return retval;

                    if ( state.backtracking==0 ) {retval.value = resolve((ID11!=null?ID11.getText():null));}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:150:9: '(' expr ')'
                    {
                    match(input,12,FOLLOW_12_in_primary1083); if (state.failed) return retval;

                    pushFollow(FOLLOW_expr_in_primary1085);
                    expr12=expr();

                    state._fsp--;
                    if (state.failed) return retval;

                    match(input,13,FOLLOW_13_in_primary1087); if (state.failed) return retval;

                    if ( state.backtracking==0 ) {retval.value = expr12;}

                    }
                    break;

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
    // $ANTLR end "primary"

    // $ANTLR start synpred1_Nebula
    public final void synpred1_Nebula_fragment() throws RecognitionException {
        Token name=null;

        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:12: ( '.' name= ID '(' exprList ')' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:137:12: '.' name= ID '(' exprList ')'
        {
        match(input,18,FOLLOW_18_in_synpred1_Nebula876); if (state.failed) return ;

        name=(Token)match(input,ID,FOLLOW_ID_in_synpred1_Nebula880); if (state.failed) return ;

        match(input,12,FOLLOW_12_in_synpred1_Nebula882); if (state.failed) return ;

        pushFollow(FOLLOW_exprList_in_synpred1_Nebula884);
        exprList();

        state._fsp--;
        if (state.failed) return ;

        match(input,13,FOLLOW_13_in_synpred1_Nebula886); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_Nebula

    // $ANTLR start synpred2_Nebula
    public final void synpred2_Nebula_fragment() throws RecognitionException {
        Token name=null;

        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:138:12: ( '.' name= ID )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:138:12: '.' name= ID
        {
        match(input,18,FOLLOW_18_in_synpred2_Nebula901); if (state.failed) return ;

        name=(Token)match(input,ID,FOLLOW_ID_in_synpred2_Nebula905); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_Nebula

    // $ANTLR start synpred4_Nebula
    public final void synpred4_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:140:12: ( '[' INT ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:140:12: '[' INT ']'
        {
        match(input,21,FOLLOW_21_in_synpred4_Nebula953); if (state.failed) return ;

        match(input,INT,FOLLOW_INT_in_synpred4_Nebula955); if (state.failed) return ;

        match(input,22,FOLLOW_22_in_synpred4_Nebula957); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred4_Nebula

    // $ANTLR start synpred5_Nebula
    public final void synpred5_Nebula_fragment() throws RecognitionException {
        List<Var> cause =null;


        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:12: ( '[' cause= exprList ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:12: '[' cause= exprList ']'
        {
        match(input,21,FOLLOW_21_in_synpred5_Nebula987); if (state.failed) return ;

        pushFollow(FOLLOW_exprList_in_synpred5_Nebula991);
        cause=exprList();

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


 

    public static final BitSet FOLLOW_classDefinition_in_compilationUnit51 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_EOF_in_compilationUnit56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_classDefinition76 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_classDefinition78 = new BitSet(new long[]{0x0000000082000000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition80 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_classDefinition108 = new BitSet(new long[]{0x0000000045000020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition110 = new BitSet(new long[]{0x0000000145000020L});
    public static final BitSet FOLLOW_32_in_classDefinition113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_superClass158 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_superClass162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fieldDeclaration_in_classMember177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_fieldDeclaration203 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_fieldDeclaration205 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_fieldDeclaration208 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expr_in_fieldDeclaration212 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_fieldDeclaration216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration238 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration242 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration244 = new BitSet(new long[]{0x0000000045002020L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration248 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration251 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameters324 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters328 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_formalParameters341 = new BitSet(new long[]{0x0000000045000020L});
    public static final BitSet FOLLOW_type_in_formalParameters345 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters349 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_24_in_type386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_type398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_type410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_block444 = new BitSet(new long[]{0x00000001FD081060L});
    public static final BitSet FOLLOW_statement_in_block446 = new BitSet(new long[]{0x00000001FD081060L});
    public static final BitSet FOLLOW_32_in_block449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration476 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_varDeclaration478 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_varDeclaration481 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expr_in_varDeclaration485 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_varDeclaration489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_statement531 = new BitSet(new long[]{0x0000000030081060L});
    public static final BitSet FOLLOW_expr_in_statement535 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixexpr_in_statement550 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_statement564 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expr_in_statement566 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_statement589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprList628 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_exprList632 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expr_in_exprList634 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_addexpr_in_expr666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multexpr_in_addexpr699 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_15_in_addexpr715 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr719 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_17_in_addexpr735 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr739 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_postfixexpr_in_multexpr780 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_multexpr797 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_postfixexpr_in_multexpr801 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_primary_in_postfixexpr844 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_18_in_postfixexpr876 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr880 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixexpr882 = new BitSet(new long[]{0x0000000030003060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr884 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixexpr886 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_18_in_postfixexpr901 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr905 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_12_in_postfixexpr931 = new BitSet(new long[]{0x0000000030003060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr935 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixexpr937 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_21_in_postfixexpr953 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_INT_in_postfixexpr955 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_postfixexpr957 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_21_in_postfixexpr987 = new BitSet(new long[]{0x0000000030401060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr991 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_postfixexpr993 = new BitSet(new long[]{0x0000000000241002L});
    public static final BitSet FOLLOW_29_in_primary1035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_primary1048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_primary1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary1083 = new BitSet(new long[]{0x0000000030001060L});
    public static final BitSet FOLLOW_expr_in_primary1085 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred1_Nebula876 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_synpred1_Nebula880 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_synpred1_Nebula882 = new BitSet(new long[]{0x0000000030003060L});
    public static final BitSet FOLLOW_exprList_in_synpred1_Nebula884 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_synpred1_Nebula886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_synpred2_Nebula901 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_synpred2_Nebula905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_synpred4_Nebula953 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_INT_in_synpred4_Nebula955 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_synpred4_Nebula957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_synpred5_Nebula987 = new BitSet(new long[]{0x0000000030401060L});
    public static final BitSet FOLLOW_exprList_in_synpred5_Nebula991 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_synpred5_Nebula993 = new BitSet(new long[]{0x0000000000000002L});

}