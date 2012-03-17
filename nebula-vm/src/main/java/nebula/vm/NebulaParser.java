// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-17 22:04:56

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Digit", "ID", "INT", "Letter", "MultiLineComment", "NEWLINE", "SingleLineComment", "Whitespace", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "';'", "'='", "'['", "']'", "'class'", "'decimal'", "'extends'", "'int'", "'new'", "'return'", "'super'", "'this'", "'void'", "'{'", "'}'"
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
      Map<String,Var> locals = new HashMap<>();
      short maxLocals = 0;

      protected void push(Var var) {
        locals.put(var.name,var);
        var.reg = (short) (locals.size() - 1);
        maxLocals = maxLocals > (short) locals.size() ? maxLocals : (short) locals.size();
      } 
      protected Var v(String name) {
        for (int i = 0; i < locals.size(); i++) {
          if (locals.get(i).getName().equals(name)) {
            return locals.get(i);
          }
        }
        return null;
      };

      protected void enterClass(String name,Type superType) {};
      protected void exitClass() {;};
      
      protected void enterFunction(String name,Type returnType,List<Var> list) {;};
      protected void exitFunction() {;};
        
      protected void defField(String name,Type type){;};
      
      protected Type resolveType(String name){return null;};
     
      protected Var add(Var a, Var b) {return null;};
      protected Var sub(Var a, Var b) {return null;};
      protected Var mul(Var a, Var b) {return null;};  
      protected Var load(Var a, Var b) {return null;}; 
      
      protected Var top=null;
      
      protected void info(String str){
          System.out.println(str);
      }



    // $ANTLR start "compilationUnit"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:54:1: compilationUnit : ( classDefinition )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:55:5: ( ( classDefinition )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:55:9: ( classDefinition )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:55:9: ( classDefinition )+
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:55:11: classDefinition
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:59:1: classDefinition : 'class' ID ( superClass )? '{' ( classMember )+ '}' ;
    public final void classDefinition() throws RecognitionException {
        Token ID1=null;
        Type superClass2 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:60:5: ( 'class' ID ( superClass )? '{' ( classMember )+ '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:60:9: 'class' ID ( superClass )? '{' ( classMember )+ '}'
            {
            match(input,23,FOLLOW_23_in_classDefinition80); if (state.failed) return ;

            ID1=(Token)match(input,ID,FOLLOW_ID_in_classDefinition82); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:60:20: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==25) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:60:20: superClass
                    {
                    pushFollow(FOLLOW_superClass_in_classDefinition84);
                    superClass2=superClass();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {enterClass((ID1!=null?ID1.getText():null),superClass2);}

            match(input,32,FOLLOW_32_in_classDefinition112); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:62:14: ( classMember )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID||LA3_0==24||LA3_0==26||LA3_0==31) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:62:14: classMember
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


            match(input,33,FOLLOW_33_in_classDefinition117); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:66:1: superClass returns [Type type] : 'extends' ID ;
    public final Type superClass() throws RecognitionException {
        Type type = null;


        Token ID3=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:3: ( 'extends' ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:5: 'extends' ID
            {
            match(input,25,FOLLOW_25_in_superClass158); if (state.failed) return type;

            ID3=(Token)match(input,ID,FOLLOW_ID_in_superClass160); if (state.failed) return type;

            if ( state.backtracking==0 ) {type =resolveType((ID3!=null?ID3.getText():null));}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:70:1: classMember : ( fieldDeclaration | methodDeclaration );
    public final void classMember() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:71:3: ( fieldDeclaration | methodDeclaration )
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
            case 31:
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:71:5: fieldDeclaration
                    {
                    pushFollow(FOLLOW_fieldDeclaration_in_classMember177);
                    fieldDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:71:24: methodDeclaration
                    {
                    pushFollow(FOLLOW_methodDeclaration_in_classMember181);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:1: fieldDeclaration : type ID ( '=' e= expr )? ';' ;
    public final void fieldDeclaration() throws RecognitionException {
        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:75:3: ( type ID ( '=' e= expr )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:75:7: type ID ( '=' e= expr )? ';'
            {
            pushFollow(FOLLOW_type_in_fieldDeclaration199);
            type();

            state._fsp--;
            if (state.failed) return ;

            match(input,ID,FOLLOW_ID_in_fieldDeclaration201); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:75:15: ( '=' e= expr )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==20) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:75:16: '=' e= expr
                    {
                    match(input,20,FOLLOW_20_in_fieldDeclaration204); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_fieldDeclaration208);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,19,FOLLOW_19_in_fieldDeclaration212); if (state.failed) return ;

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

        Type type4 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:3: ( type name= ID '(' (params= formalParameters )? ')' block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:7: type name= ID '(' (params= formalParameters )? ')' block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration231);
            type4=type();

            state._fsp--;
            if (state.failed) return ;

            name=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration235); if (state.failed) return ;

            match(input,12,FOLLOW_12_in_methodDeclaration237); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:30: (params= formalParameters )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ID||LA6_0==24||LA6_0==26||LA6_0==31) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:30: params= formalParameters
                    {
                    pushFollow(FOLLOW_formalParameters_in_methodDeclaration241);
                    params=formalParameters();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,13,FOLLOW_13_in_methodDeclaration244); if (state.failed) return ;

            if ( state.backtracking==0 ) {enterFunction((name!=null?name.getText():null),type4,params);}

            pushFollow(FOLLOW_block_in_methodDeclaration263);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:85:1: formalParameters returns [List<Var> list] : t= type id= ID ( ',' t= type id= ID )* ;
    public final List<Var> formalParameters() throws RecognitionException {
        List<Var> list = null;


        Token id=null;
        Type t =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:87:3: (t= type id= ID ( ',' t= type id= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:87:5: t= type id= ID ( ',' t= type id= ID )*
            {
            pushFollow(FOLLOW_type_in_formalParameters303);
            t=type();

            state._fsp--;
            if (state.failed) return list;

            id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters307); if (state.failed) return list;

            if ( state.backtracking==0 ) {list.add(new Var(Var.PARAM,(id!=null?id.getText():null),t));}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:88:5: ( ',' t= type id= ID )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:88:7: ',' t= type id= ID
            	    {
            	    match(input,16,FOLLOW_16_in_formalParameters316); if (state.failed) return list;

            	    pushFollow(FOLLOW_type_in_formalParameters320);
            	    t=type();

            	    state._fsp--;
            	    if (state.failed) return list;

            	    id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters324); if (state.failed) return list;

            	    if ( state.backtracking==0 ) {list.add(new Var(Var.PARAM,(id!=null?id.getText():null),t));}

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



    // $ANTLR start "block"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:96:1: block : '{' ( statement )* '}' ;
    public final void block() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:97:5: ( '{' ( statement )* '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:97:9: '{' ( statement )* '}'
            {
            match(input,32,FOLLOW_32_in_block364); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:97:13: ( statement )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= ID && LA8_0 <= INT)||LA8_0==12||LA8_0==19||LA8_0==24||(LA8_0 >= 26 && LA8_0 <= 32)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:97:13: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block366);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            match(input,33,FOLLOW_33_in_block369); if (state.failed) return ;

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



    // $ANTLR start "statement"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:100:1: statement : ( block | varDeclaration | 'return' ( expr )? ';' | exprStatement ';' | ';' );
    public final void statement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:101:5: ( block | varDeclaration | 'return' ( expr )? ';' | exprStatement ';' | ';' )
            int alt10=5;
            switch ( input.LA(1) ) {
            case 32:
                {
                alt10=1;
                }
                break;
            case 24:
            case 26:
            case 31:
                {
                alt10=2;
                }
                break;
            case ID:
                {
                int LA10_3 = input.LA(2);

                if ( (LA10_3==ID) ) {
                    alt10=2;
                }
                else if ( ((LA10_3 >= 18 && LA10_3 <= 21)) ) {
                    alt10=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 3, input);

                    throw nvae;

                }
                }
                break;
            case 28:
                {
                alt10=3;
                }
                break;
            case INT:
            case 12:
            case 27:
            case 29:
            case 30:
                {
                alt10=4;
                }
                break;
            case 19:
                {
                alt10=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:101:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement392);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:102:9: varDeclaration
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement402);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:103:9: 'return' ( expr )? ';'
                    {
                    match(input,28,FOLLOW_28_in_statement412); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:103:18: ( expr )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0 >= ID && LA9_0 <= INT)||LA9_0==12||LA9_0==27||(LA9_0 >= 29 && LA9_0 <= 30)) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:103:18: expr
                            {
                            pushFollow(FOLLOW_expr_in_statement414);
                            expr();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,19,FOLLOW_19_in_statement417); if (state.failed) return ;

                    if ( state.backtracking==0 ) {;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:104:9: exprStatement ';'
                    {
                    pushFollow(FOLLOW_exprStatement_in_statement429);
                    exprStatement();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,19,FOLLOW_19_in_statement431); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:105:9: ';'
                    {
                    match(input,19,FOLLOW_19_in_statement442); if (state.failed) return ;

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



    // $ANTLR start "varDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:108:1: varDeclaration : type ID ( '=' (f= ref_const |f= expr ) )? ';' ;
    public final void varDeclaration() throws RecognitionException {
        Var f =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:109:5: ( type ID ( '=' (f= ref_const |f= expr ) )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:109:9: type ID ( '=' (f= ref_const |f= expr ) )? ';'
            {
            pushFollow(FOLLOW_type_in_varDeclaration468);
            type();

            state._fsp--;
            if (state.failed) return ;

            match(input,ID,FOLLOW_ID_in_varDeclaration470); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:110:9: ( '=' (f= ref_const |f= expr ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==20) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:110:10: '=' (f= ref_const |f= expr )
                    {
                    match(input,20,FOLLOW_20_in_varDeclaration481); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:110:14: (f= ref_const |f= expr )
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==INT) ) {
                        alt11=1;
                    }
                    else if ( (LA11_0==ID||LA11_0==12||LA11_0==27||(LA11_0 >= 29 && LA11_0 <= 30)) ) {
                        alt11=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;

                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:111:13: f= ref_const
                            {
                            pushFollow(FOLLOW_ref_const_in_varDeclaration499);
                            f=ref_const();

                            state._fsp--;
                            if (state.failed) return ;

                            if ( state.backtracking==0 ) {info("const " + f); }

                            }
                            break;
                        case 2 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:112:15: f= expr
                            {
                            pushFollow(FOLLOW_expr_in_varDeclaration519);
                            f=expr();

                            state._fsp--;
                            if (state.failed) return ;

                            if ( state.backtracking==0 ) {info("expr " + f); }

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,19,FOLLOW_19_in_varDeclaration550); if (state.failed) return ;

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



    // $ANTLR start "exprStatement"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:1: exprStatement : to= postfixexpr ( '=' from= expr )? ;
    public final void exprStatement() throws RecognitionException {
        NebulaParser.postfixexpr_return to =null;

        Var from =null;


        ;
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:5: (to= postfixexpr ( '=' from= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:9: to= postfixexpr ( '=' from= expr )?
            {
            pushFollow(FOLLOW_postfixexpr_in_exprStatement577);
            to=postfixexpr();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:120:9: ( '=' from= expr )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==20) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:120:13: '=' from= expr
                    {
                    match(input,20,FOLLOW_20_in_exprStatement592); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_exprStatement596);
                    from=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

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
    // $ANTLR end "exprStatement"



    // $ANTLR start "expr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:122:1: expr returns [Var value] : e= addexpr ;
    public final Var expr() throws RecognitionException {
        Var value = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:123:5: (e= addexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:123:9: e= addexpr
            {
            pushFollow(FOLLOW_addexpr_in_expr628);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:126:1: addexpr returns [Var value] : e= multexpr ( '+' e= multexpr | '-' e= multexpr )* ;
    public final Var addexpr() throws RecognitionException {
        Var value = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:5: (e= multexpr ( '+' e= multexpr | '-' e= multexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:9: e= multexpr ( '+' e= multexpr | '-' e= multexpr )*
            {
            pushFollow(FOLLOW_multexpr_in_addexpr661);
            e=multexpr();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:128:9: ( '+' e= multexpr | '-' e= multexpr )*
            loop14:
            do {
                int alt14=3;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==15) ) {
                    alt14=1;
                }
                else if ( (LA14_0==17) ) {
                    alt14=2;
                }


                switch (alt14) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:128:13: '+' e= multexpr
            	    {
            	    match(input,15,FOLLOW_15_in_addexpr677); if (state.failed) return value;

            	    pushFollow(FOLLOW_multexpr_in_addexpr681);
            	    e=multexpr();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = add(value,e);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:129:13: '-' e= multexpr
            	    {
            	    match(input,17,FOLLOW_17_in_addexpr697); if (state.failed) return value;

            	    pushFollow(FOLLOW_multexpr_in_addexpr701);
            	    e=multexpr();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = sub(value,e);}

            	    }
            	    break;

            	default :
            	    break loop14;
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:133:1: multexpr returns [Var value] : e= postfixexpr ( '*' e= postfixexpr )* ;
    public final Var multexpr() throws RecognitionException {
        Var value = null;


        NebulaParser.postfixexpr_return e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:134:5: (e= postfixexpr ( '*' e= postfixexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:134:9: e= postfixexpr ( '*' e= postfixexpr )*
            {
            pushFollow(FOLLOW_postfixexpr_in_multexpr742);
            e=postfixexpr();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:135:9: ( '*' e= postfixexpr )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==14) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:135:13: '*' e= postfixexpr
            	    {
            	    match(input,14,FOLLOW_14_in_multexpr759); if (state.failed) return value;

            	    pushFollow(FOLLOW_postfixexpr_in_multexpr763);
            	    e=postfixexpr();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {;}

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
    // $ANTLR end "multexpr"


    public static class postfixexpr_return extends ParserRuleReturnScope {
        public Var value;
        public boolean isDone;
        public String refItem;
    };


    // $ANTLR start "postfixexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:139:1: postfixexpr returns [Var value,boolean isDone,String refItem] : (e= primary ) ( options {backtrack=true; } : '.' name= ID '(' params= exprList ')' | '.' name= ID | '[' INT ']' | '[' cause= exprList ']' )* ;
    public final NebulaParser.postfixexpr_return postfixexpr() throws RecognitionException {
        NebulaParser.postfixexpr_return retval = new NebulaParser.postfixexpr_return();
        retval.start = input.LT(1);


        Token name=null;
        Var e =null;

        List<Var> params =null;

        List<Var> cause =null;


        retval.isDone = true;
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:5: ( (e= primary ) ( options {backtrack=true; } : '.' name= ID '(' params= exprList ')' | '.' name= ID | '[' INT ']' | '[' cause= exprList ']' )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:8: (e= primary ) ( options {backtrack=true; } : '.' name= ID '(' params= exprList ')' | '.' name= ID | '[' INT ']' | '[' cause= exprList ']' )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:8: (e= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:141:9: e= primary
            {
            pushFollow(FOLLOW_primary_in_postfixexpr817);
            e=primary();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {retval.value = e;}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:142:9: ( options {backtrack=true; } : '.' name= ID '(' params= exprList ')' | '.' name= ID | '[' INT ']' | '[' cause= exprList ']' )*
            loop16:
            do {
                int alt16=5;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==18) ) {
                    int LA16_2 = input.LA(2);

                    if ( (LA16_2==ID) ) {
                        int LA16_4 = input.LA(3);

                        if ( (LA16_4==12) ) {
                            alt16=1;
                        }
                        else if ( ((LA16_4 >= 13 && LA16_4 <= 22)) ) {
                            alt16=2;
                        }


                    }


                }
                else if ( (LA16_0==21) ) {
                    int LA16_3 = input.LA(2);

                    if ( (LA16_3==INT) ) {
                        int LA16_5 = input.LA(3);

                        if ( (LA16_5==22) ) {
                            int LA16_9 = input.LA(4);

                            if ( (synpred3_Nebula()) ) {
                                alt16=3;
                            }
                            else if ( (synpred4_Nebula()) ) {
                                alt16=4;
                            }


                        }
                        else if ( ((LA16_5 >= 14 && LA16_5 <= 18)||LA16_5==21) ) {
                            alt16=4;
                        }


                    }
                    else if ( (LA16_3==ID||LA16_3==12||LA16_3==22||LA16_3==27||(LA16_3 >= 29 && LA16_3 <= 30)) ) {
                        alt16=4;
                    }


                }


                switch (alt16) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:143:12: '.' name= ID '(' params= exprList ')'
            	    {
            	    match(input,18,FOLLOW_18_in_postfixexpr849); if (state.failed) return retval;

            	    name=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr853); if (state.failed) return retval;

            	    match(input,12,FOLLOW_12_in_postfixexpr855); if (state.failed) return retval;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr859);
            	    params=exprList();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    match(input,13,FOLLOW_13_in_postfixexpr861); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {;}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:144:12: '.' name= ID
            	    {
            	    match(input,18,FOLLOW_18_in_postfixexpr876); if (state.failed) return retval;

            	    name=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr880); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {;}

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:145:12: '[' INT ']'
            	    {
            	    match(input,21,FOLLOW_21_in_postfixexpr912); if (state.failed) return retval;

            	    match(input,INT,FOLLOW_INT_in_postfixexpr914); if (state.failed) return retval;

            	    match(input,22,FOLLOW_22_in_postfixexpr916); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {;}

            	    }
            	    break;
            	case 4 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:12: '[' cause= exprList ']'
            	    {
            	    match(input,21,FOLLOW_21_in_postfixexpr948); if (state.failed) return retval;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr952);
            	    cause=exprList();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    match(input,22,FOLLOW_22_in_postfixexpr954); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {;}

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


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
    // $ANTLR end "postfixexpr"



    // $ANTLR start "exprList"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:150:1: exprList returns [List<Var> list] : (e= expr ( ',' expr )* |);
    public final List<Var> exprList() throws RecognitionException {
        List<Var> list = null;


        Var e =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:5: (e= expr ( ',' expr )* |)
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0 >= ID && LA18_0 <= INT)||LA18_0==12||LA18_0==27||(LA18_0 >= 29 && LA18_0 <= 30)) ) {
                alt18=1;
            }
            else if ( (LA18_0==13||LA18_0==22) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return list;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }
            switch (alt18) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:9: e= expr ( ',' expr )*
                    {
                    pushFollow(FOLLOW_expr_in_exprList1011);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return list;

                    if ( state.backtracking==0 ) {list.add(e);}

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:30: ( ',' expr )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==16) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:31: ',' expr
                    	    {
                    	    match(input,16,FOLLOW_16_in_exprList1015); if (state.failed) return list;

                    	    pushFollow(FOLLOW_expr_in_exprList1017);
                    	    expr();

                    	    state._fsp--;
                    	    if (state.failed) return list;

                    	    if ( state.backtracking==0 ) {list.add(e);}

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:153:5: 
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



    // $ANTLR start "primary"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:1: primary returns [Var value] : ( ( 'new' type '(' ps= exprList ')' ) |ref= ref_this |ref= ref_super |ref= ref_const | ID | '(' v= expr ')' );
    public final Var primary() throws RecognitionException {
        Var value = null;


        Token ID5=null;
        List<Var> ps =null;

        Var ref =null;

        Var v =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:5: ( ( 'new' type '(' ps= exprList ')' ) |ref= ref_this |ref= ref_super |ref= ref_const | ID | '(' v= expr ')' )
            int alt19=6;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt19=1;
                }
                break;
            case 30:
                {
                alt19=2;
                }
                break;
            case 29:
                {
                alt19=3;
                }
                break;
            case INT:
                {
                alt19=4;
                }
                break;
            case ID:
                {
                alt19=5;
                }
                break;
            case 12:
                {
                alt19=6;
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:9: ( 'new' type '(' ps= exprList ')' )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:9: ( 'new' type '(' ps= exprList ')' )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:10: 'new' type '(' ps= exprList ')'
                    {
                    match(input,27,FOLLOW_27_in_primary1053); if (state.failed) return value;

                    pushFollow(FOLLOW_type_in_primary1055);
                    type();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,12,FOLLOW_12_in_primary1057); if (state.failed) return value;

                    pushFollow(FOLLOW_exprList_in_primary1061);
                    ps=exprList();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_primary1063); if (state.failed) return value;

                    }


                    if ( state.backtracking==0 ) {;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:9: ref= ref_this
                    {
                    pushFollow(FOLLOW_ref_this_in_primary1077);
                    ref=ref_this();

                    state._fsp--;
                    if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = ref;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:9: ref= ref_super
                    {
                    pushFollow(FOLLOW_ref_super_in_primary1096);
                    ref=ref_super();

                    state._fsp--;
                    if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = ref;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:159:9: ref= ref_const
                    {
                    pushFollow(FOLLOW_ref_const_in_primary1114);
                    ref=ref_const();

                    state._fsp--;
                    if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = ref;}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:9: ID
                    {
                    ID5=(Token)match(input,ID,FOLLOW_ID_in_primary1130); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value =locals.get((ID5!=null?ID5.getText():null));}

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:9: '(' v= expr ')'
                    {
                    match(input,12,FOLLOW_12_in_primary1151); if (state.failed) return value;

                    pushFollow(FOLLOW_expr_in_primary1155);
                    v=expr();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_primary1157); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = v;}

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



    // $ANTLR start "ref_this"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:165:1: ref_this returns [Var value] : 'this' ;
    public final Var ref_this() throws RecognitionException {
        Var value = null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:166:5: ( 'this' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:166:9: 'this'
            {
            match(input,30,FOLLOW_30_in_ref_this1187); if (state.failed) return value;

            if ( state.backtracking==0 ) {value = locals.get("this");}

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
    // $ANTLR end "ref_this"



    // $ANTLR start "ref_super"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:169:1: ref_super returns [Var value] : 'super' ;
    public final Var ref_super() throws RecognitionException {
        Var value = null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:170:5: ( 'super' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:170:9: 'super'
            {
            match(input,29,FOLLOW_29_in_ref_super1211); if (state.failed) return value;

            if ( state.backtracking==0 ) {value = locals.get("super");}

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
    // $ANTLR end "ref_super"



    // $ANTLR start "ref_const"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:173:1: ref_const returns [Var value] : INT ;
    public final Var ref_const() throws RecognitionException {
        Var value = null;


        Token INT6=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:5: ( INT )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:9: INT
            {
            INT6=(Token)match(input,INT,FOLLOW_INT_in_ref_const1239); if (state.failed) return value;

            if ( state.backtracking==0 ) {value = new IConst((INT6!=null?INT6.getText():null));}

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
    // $ANTLR end "ref_const"



    // $ANTLR start "type"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:181:1: type returns [Type type] : ( 'decimal' | 'int' | 'void' | ID );
    public final Type type() throws RecognitionException {
        Type type = null;


        Token ID7=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:182:5: ( 'decimal' | 'int' | 'void' | ID )
            int alt20=4;
            switch ( input.LA(1) ) {
            case 24:
                {
                alt20=1;
                }
                break;
            case 26:
                {
                alt20=2;
                }
                break;
            case 31:
                {
                alt20=3;
                }
                break;
            case ID:
                {
                alt20=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }

            switch (alt20) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:182:9: 'decimal'
                    {
                    match(input,24,FOLLOW_24_in_type1276); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:183:9: 'int'
                    {
                    match(input,26,FOLLOW_26_in_type1288); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:184:9: 'void'
                    {
                    match(input,31,FOLLOW_31_in_type1304); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:185:9: ID
                    {
                    ID7=(Token)match(input,ID,FOLLOW_ID_in_type1319); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = resolveType((ID7!=null?ID7.getText():null));}

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

    // $ANTLR start synpred3_Nebula
    public final void synpred3_Nebula_fragment() throws RecognitionException {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:145:12: ( '[' INT ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:145:12: '[' INT ']'
        {
        match(input,21,FOLLOW_21_in_synpred3_Nebula912); if (state.failed) return ;

        match(input,INT,FOLLOW_INT_in_synpred3_Nebula914); if (state.failed) return ;

        match(input,22,FOLLOW_22_in_synpred3_Nebula916); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred3_Nebula

    // $ANTLR start synpred4_Nebula
    public final void synpred4_Nebula_fragment() throws RecognitionException {
        List<Var> cause =null;


        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:12: ( '[' cause= exprList ']' )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:146:12: '[' cause= exprList ']'
        {
        match(input,21,FOLLOW_21_in_synpred4_Nebula948); if (state.failed) return ;

        pushFollow(FOLLOW_exprList_in_synpred4_Nebula952);
        cause=exprList();

        state._fsp--;
        if (state.failed) return ;

        match(input,22,FOLLOW_22_in_synpred4_Nebula954); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred4_Nebula

    // Delegated rules

    public final boolean synpred3_Nebula() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_Nebula_fragment(); // can never throw exception
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
    public static final BitSet FOLLOW_23_in_classDefinition80 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_classDefinition82 = new BitSet(new long[]{0x0000000102000000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition84 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_classDefinition112 = new BitSet(new long[]{0x0000000085000020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition114 = new BitSet(new long[]{0x0000000285000020L});
    public static final BitSet FOLLOW_33_in_classDefinition117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_superClass158 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_superClass160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fieldDeclaration_in_classMember177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_fieldDeclaration199 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_fieldDeclaration201 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_fieldDeclaration204 = new BitSet(new long[]{0x0000000068001060L});
    public static final BitSet FOLLOW_expr_in_fieldDeclaration208 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_fieldDeclaration212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration231 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration235 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration237 = new BitSet(new long[]{0x0000000085002020L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration241 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration244 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameters303 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters307 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_formalParameters316 = new BitSet(new long[]{0x0000000085000020L});
    public static final BitSet FOLLOW_type_in_formalParameters320 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters324 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_32_in_block364 = new BitSet(new long[]{0x00000003FD081060L});
    public static final BitSet FOLLOW_statement_in_block366 = new BitSet(new long[]{0x00000003FD081060L});
    public static final BitSet FOLLOW_33_in_block369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_statement412 = new BitSet(new long[]{0x0000000068081060L});
    public static final BitSet FOLLOW_expr_in_statement414 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprStatement_in_statement429 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_statement442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration468 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_varDeclaration470 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_varDeclaration481 = new BitSet(new long[]{0x0000000068001060L});
    public static final BitSet FOLLOW_ref_const_in_varDeclaration499 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_expr_in_varDeclaration519 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_varDeclaration550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixexpr_in_exprStatement577 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_20_in_exprStatement592 = new BitSet(new long[]{0x0000000068001060L});
    public static final BitSet FOLLOW_expr_in_exprStatement596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addexpr_in_expr628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multexpr_in_addexpr661 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_15_in_addexpr677 = new BitSet(new long[]{0x0000000068001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr681 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_17_in_addexpr697 = new BitSet(new long[]{0x0000000068001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr701 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_postfixexpr_in_multexpr742 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_multexpr759 = new BitSet(new long[]{0x0000000068001060L});
    public static final BitSet FOLLOW_postfixexpr_in_multexpr763 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_primary_in_postfixexpr817 = new BitSet(new long[]{0x0000000000240002L});
    public static final BitSet FOLLOW_18_in_postfixexpr849 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr853 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixexpr855 = new BitSet(new long[]{0x0000000068003060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr859 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixexpr861 = new BitSet(new long[]{0x0000000000240002L});
    public static final BitSet FOLLOW_18_in_postfixexpr876 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr880 = new BitSet(new long[]{0x0000000000240002L});
    public static final BitSet FOLLOW_21_in_postfixexpr912 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_INT_in_postfixexpr914 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_postfixexpr916 = new BitSet(new long[]{0x0000000000240002L});
    public static final BitSet FOLLOW_21_in_postfixexpr948 = new BitSet(new long[]{0x0000000068401060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr952 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_postfixexpr954 = new BitSet(new long[]{0x0000000000240002L});
    public static final BitSet FOLLOW_expr_in_exprList1011 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_exprList1015 = new BitSet(new long[]{0x0000000068001060L});
    public static final BitSet FOLLOW_expr_in_exprList1017 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_27_in_primary1053 = new BitSet(new long[]{0x0000000085000020L});
    public static final BitSet FOLLOW_type_in_primary1055 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_primary1057 = new BitSet(new long[]{0x0000000068003060L});
    public static final BitSet FOLLOW_exprList_in_primary1061 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ref_this_in_primary1077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ref_super_in_primary1096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ref_const_in_primary1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary1151 = new BitSet(new long[]{0x0000000068001060L});
    public static final BitSet FOLLOW_expr_in_primary1155 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ref_this1187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ref_super1211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_ref_const1239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_type1276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_type1288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_type1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_synpred3_Nebula912 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_INT_in_synpred3_Nebula914 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_synpred3_Nebula916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_synpred4_Nebula948 = new BitSet(new long[]{0x0000000068401060L});
    public static final BitSet FOLLOW_exprList_in_synpred4_Nebula952 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_synpred4_Nebula954 = new BitSet(new long[]{0x0000000000000002L});

}