// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-18 18:32:34

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Digit", "ID", "INT", "Letter", "MultiLineComment", "NEWLINE", "SingleLineComment", "Whitespace", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "';'", "'='", "'class'", "'decimal'", "'extends'", "'int'", "'new'", "'return'", "'super'", "'this'", "'void'", "'{'", "'}'"
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
      
      protected Var popTmp(){
          return new LocalVar("tmp",BuiltInTypeSymbol.INT);
      }
      
      protected void clearTmp(){
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

      protected void defineField(String name,Type type){info("define field " + name);};
      
      protected Type resolveType(String name){return null;};
     
      protected Var add(Var a, Var b) {info(" " + a.name + " + " + b.name + " "); return a;};
      protected Var sub(Var a, Var b) {info(" " + a.name + " - " + b.name + " ");return a;};
      protected Var mul(Var a, Var b) {info(" " + a.name + " * " + b.name + " ");return a;};  
      protected Var getField(Var obj,FieldSymbol field) {info(" " + obj.name + "." + field.name + " ");return obj;};  
      protected Var invoke(Var obj,MethodSymbol field,List<Var> params) {info(" " + obj.name + "." + field.name + "(" + params + ") "); return obj;};  
      protected Var setField(Var obj, Var field, Var value) {return value;};  
      
      protected Var loadI(String text){info("loadI " + text + " ");return new IConst(text);};
      
      protected Var top=null;
      
      protected void info(String str){
          System.out.print(str);
      }



    // $ANTLR start "compilationUnit"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:66:1: compilationUnit : ( classDefinition )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:5: ( ( classDefinition )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:9: ( classDefinition )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:9: ( classDefinition )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==21) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:67:11: classDefinition
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:71:1: classDefinition : 'class' ID ( superClass )? '{' ( classMember )+ '}' ;
    public final void classDefinition() throws RecognitionException {
        Token ID1=null;
        Type superClass2 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:72:5: ( 'class' ID ( superClass )? '{' ( classMember )+ '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:72:9: 'class' ID ( superClass )? '{' ( classMember )+ '}'
            {
            match(input,21,FOLLOW_21_in_classDefinition80); if (state.failed) return ;

            ID1=(Token)match(input,ID,FOLLOW_ID_in_classDefinition82); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:72:20: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==23) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:72:20: superClass
                    {
                    pushFollow(FOLLOW_superClass_in_classDefinition84);
                    superClass2=superClass();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {enterClass((ID1!=null?ID1.getText():null),superClass2);}

            match(input,30,FOLLOW_30_in_classDefinition112); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:14: ( classMember )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID||LA3_0==22||LA3_0==24||LA3_0==29) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:74:14: classMember
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


            match(input,31,FOLLOW_31_in_classDefinition117); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:78:1: superClass returns [Type type] : 'extends' ID ;
    public final Type superClass() throws RecognitionException {
        Type type = null;


        Token ID3=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:3: ( 'extends' ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:79:5: 'extends' ID
            {
            match(input,23,FOLLOW_23_in_superClass158); if (state.failed) return type;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:82:1: classMember : ( fieldDeclaration | methodDeclaration );
    public final void classMember() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:83:3: ( fieldDeclaration | methodDeclaration )
            int alt4=2;
            switch ( input.LA(1) ) {
            case 22:
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
            case 24:
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
            case 29:
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:83:5: fieldDeclaration
                    {
                    pushFollow(FOLLOW_fieldDeclaration_in_classMember177);
                    fieldDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:83:24: methodDeclaration
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:86:1: fieldDeclaration : type ID ( '=' e= expr )? ';' ;
    public final void fieldDeclaration() throws RecognitionException {
        Token ID4=null;
        Var e =null;

        Type type5 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:87:3: ( type ID ( '=' e= expr )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:87:7: type ID ( '=' e= expr )? ';'
            {
            pushFollow(FOLLOW_type_in_fieldDeclaration199);
            type5=type();

            state._fsp--;
            if (state.failed) return ;

            ID4=(Token)match(input,ID,FOLLOW_ID_in_fieldDeclaration201); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:87:15: ( '=' e= expr )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==20) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:87:16: '=' e= expr
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

            if ( state.backtracking==0 ) {defineField((ID4!=null?ID4.getText():null),type5);}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:90:1: methodDeclaration : type name= ID '(' (params= formalParameters )? ')' block ;
    public final void methodDeclaration() throws RecognitionException {
        Token name=null;
        List<Var> params =null;

        Type type6 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:91:3: ( type name= ID '(' (params= formalParameters )? ')' block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:91:7: type name= ID '(' (params= formalParameters )? ')' block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration233);
            type6=type();

            state._fsp--;
            if (state.failed) return ;

            name=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration237); if (state.failed) return ;

            match(input,12,FOLLOW_12_in_methodDeclaration239); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:91:30: (params= formalParameters )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ID||LA6_0==22||LA6_0==24||LA6_0==29) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:91:30: params= formalParameters
                    {
                    pushFollow(FOLLOW_formalParameters_in_methodDeclaration243);
                    params=formalParameters();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,13,FOLLOW_13_in_methodDeclaration246); if (state.failed) return ;

            if ( state.backtracking==0 ) {enterFunction((name!=null?name.getText():null),type6,params);}

            pushFollow(FOLLOW_block_in_methodDeclaration265);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:97:1: formalParameters returns [List<Var> list] : t= type id= ID ( ',' t= type id= ID )* ;
    public final List<Var> formalParameters() throws RecognitionException {
        List<Var> list = null;


        Token id=null;
        Type t =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:99:3: (t= type id= ID ( ',' t= type id= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:99:5: t= type id= ID ( ',' t= type id= ID )*
            {
            pushFollow(FOLLOW_type_in_formalParameters305);
            t=type();

            state._fsp--;
            if (state.failed) return list;

            id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters309); if (state.failed) return list;

            if ( state.backtracking==0 ) {list.add(new Param((id!=null?id.getText():null),t));}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:100:5: ( ',' t= type id= ID )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:100:7: ',' t= type id= ID
            	    {
            	    match(input,16,FOLLOW_16_in_formalParameters318); if (state.failed) return list;

            	    pushFollow(FOLLOW_type_in_formalParameters322);
            	    t=type();

            	    state._fsp--;
            	    if (state.failed) return list;

            	    id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters326); if (state.failed) return list;

            	    if ( state.backtracking==0 ) {list.add(new Param((id!=null?id.getText():null),t));}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:108:1: block : '{' ( statement )* '}' ;
    public final void block() throws RecognitionException {
        info("Block{\n");
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:111:5: ( '{' ( statement )* '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:111:9: '{' ( statement )* '}'
            {
            match(input,30,FOLLOW_30_in_block379); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:111:13: ( statement )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= ID && LA8_0 <= INT)||LA8_0==12||LA8_0==19||LA8_0==22||(LA8_0 >= 24 && LA8_0 <= 30)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:111:13: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block381);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            match(input,31,FOLLOW_31_in_block384); if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {info("}Block\n");}
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:114:1: statement : ( block | varDeclaration ';' | 'return' ( expr )? ';' | exprStatement ';' | ';' );
    public final void statement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:115:5: ( block | varDeclaration ';' | 'return' ( expr )? ';' | exprStatement ';' | ';' )
            int alt10=5;
            switch ( input.LA(1) ) {
            case 30:
                {
                alt10=1;
                }
                break;
            case 22:
            case 24:
            case 29:
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
                else if ( ((LA10_3 >= 18 && LA10_3 <= 20)) ) {
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
            case 26:
                {
                alt10=3;
                }
                break;
            case INT:
            case 12:
            case 25:
            case 27:
            case 28:
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:115:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement407);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:116:9: varDeclaration ';'
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement417);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,19,FOLLOW_19_in_statement420); if (state.failed) return ;

                    if ( state.backtracking==0 ) {info("\n");}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:9: 'return' ( expr )? ';'
                    {
                    match(input,26,FOLLOW_26_in_statement432); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:18: ( expr )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0 >= ID && LA9_0 <= INT)||LA9_0==12||LA9_0==25||(LA9_0 >= 27 && LA9_0 <= 28)) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:117:18: expr
                            {
                            pushFollow(FOLLOW_expr_in_statement434);
                            expr();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,19,FOLLOW_19_in_statement438); if (state.failed) return ;

                    if ( state.backtracking==0 ) {info("\n");}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:118:9: exprStatement ';'
                    {
                    pushFollow(FOLLOW_exprStatement_in_statement450);
                    exprStatement();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,19,FOLLOW_19_in_statement454); if (state.failed) return ;

                    if ( state.backtracking==0 ) {info("\n");}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:119:9: ';'
                    {
                    match(input,19,FOLLOW_19_in_statement466); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:122:1: varDeclaration : type ID ( '=' v= expr )? ;
    public final void varDeclaration() throws RecognitionException {
        Token ID7=null;
        Var v =null;

        Type type8 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:123:5: ( type ID ( '=' v= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:123:9: type ID ( '=' v= expr )?
            {
            pushFollow(FOLLOW_type_in_varDeclaration489);
            type8=type();

            state._fsp--;
            if (state.failed) return ;

            ID7=(Token)match(input,ID,FOLLOW_ID_in_varDeclaration491); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:124:9: ( '=' v= expr )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==20) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:124:10: '=' v= expr
                    {
                    match(input,20,FOLLOW_20_in_varDeclaration502); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_varDeclaration506);
                    v=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                                  v.name = (ID7!=null?ID7.getText():null);
                                  v.type = type8; 
                                  push(v);
                                }

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
    // $ANTLR end "varDeclaration"



    // $ANTLR start "exprStatement"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:132:1: exprStatement options {backtrack=true; } : ( (to= postfixexpr '=' from= expr ) |to= postfixexpr );
    public final void exprStatement() throws RecognitionException {
        Var to =null;

        Var from =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:134:5: ( (to= postfixexpr '=' from= expr ) |to= postfixexpr )
            int alt12=2;
            switch ( input.LA(1) ) {
            case 25:
                {
                int LA12_1 = input.LA(2);

                if ( (synpred1_Nebula()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;

                }
                }
                break;
            case 28:
                {
                int LA12_2 = input.LA(2);

                if ( (synpred1_Nebula()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 2, input);

                    throw nvae;

                }
                }
                break;
            case 27:
                {
                int LA12_3 = input.LA(2);

                if ( (synpred1_Nebula()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 3, input);

                    throw nvae;

                }
                }
                break;
            case INT:
                {
                int LA12_4 = input.LA(2);

                if ( (synpred1_Nebula()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 4, input);

                    throw nvae;

                }
                }
                break;
            case ID:
                {
                int LA12_5 = input.LA(2);

                if ( (synpred1_Nebula()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 5, input);

                    throw nvae;

                }
                }
                break;
            case 12:
                {
                int LA12_6 = input.LA(2);

                if ( (synpred1_Nebula()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 6, input);

                    throw nvae;

                }
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:134:9: (to= postfixexpr '=' from= expr )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:134:9: (to= postfixexpr '=' from= expr )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:134:10: to= postfixexpr '=' from= expr
                    {
                    pushFollow(FOLLOW_postfixexpr_in_exprStatement560);
                    to=postfixexpr();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,20,FOLLOW_20_in_exprStatement562); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_exprStatement566);
                    from=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:135:11: to= postfixexpr
                    {
                    pushFollow(FOLLOW_postfixexpr_in_exprStatement583);
                    to=postfixexpr();

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
    // $ANTLR end "exprStatement"



    // $ANTLR start "expr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:138:1: expr returns [Var value] : e= addexpr ;
    public final Var expr() throws RecognitionException {
        Var value = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:139:5: (e= addexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:139:9: e= addexpr
            {
            pushFollow(FOLLOW_addexpr_in_expr612);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:142:1: addexpr returns [Var value] : a= multexpr ( '+' b= multexpr | '-' c= multexpr )* ;
    public final Var addexpr() throws RecognitionException {
        Var value = null;


        Var a =null;

        Var b =null;

        Var c =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:143:5: (a= multexpr ( '+' b= multexpr | '-' c= multexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:143:9: a= multexpr ( '+' b= multexpr | '-' c= multexpr )*
            {
            pushFollow(FOLLOW_multexpr_in_addexpr643);
            a=multexpr();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:144:9: ( '+' b= multexpr | '-' c= multexpr )*
            loop13:
            do {
                int alt13=3;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==15) ) {
                    alt13=1;
                }
                else if ( (LA13_0==17) ) {
                    alt13=2;
                }


                switch (alt13) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:144:13: '+' b= multexpr
            	    {
            	    match(input,15,FOLLOW_15_in_addexpr659); if (state.failed) return value;

            	    pushFollow(FOLLOW_multexpr_in_addexpr663);
            	    b=multexpr();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value =add(a,b);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:145:13: '-' c= multexpr
            	    {
            	    match(input,17,FOLLOW_17_in_addexpr680); if (state.failed) return value;

            	    pushFollow(FOLLOW_multexpr_in_addexpr684);
            	    c=multexpr();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value =sub(a,c);}

            	    }
            	    break;

            	default :
            	    break loop13;
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:149:1: multexpr returns [Var value] : a= postfixexpr ( '*' b= postfixexpr )* ;
    public final Var multexpr() throws RecognitionException {
        Var value = null;


        Var a =null;

        Var b =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:150:5: (a= postfixexpr ( '*' b= postfixexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:150:9: a= postfixexpr ( '*' b= postfixexpr )*
            {
            pushFollow(FOLLOW_postfixexpr_in_multexpr728);
            a=postfixexpr();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value =a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:151:9: ( '*' b= postfixexpr )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==14) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:151:13: '*' b= postfixexpr
            	    {
            	    match(input,14,FOLLOW_14_in_multexpr745); if (state.failed) return value;

            	    pushFollow(FOLLOW_postfixexpr_in_multexpr749);
            	    b=postfixexpr();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value =mul(a,b);}

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
    // $ANTLR end "multexpr"



    // $ANTLR start "postfixexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:155:1: postfixexpr returns [Var value] : (e= primary ) ( options {backtrack=true; } : '.' method= refMethod[$value.type] '(' params= exprList ')' | '.' field= refField[$value.type] )* ;
    public final Var postfixexpr() throws RecognitionException {
        Var value = null;


        Var e =null;

        MethodSymbol method =null;

        List<Var> params =null;

        FieldSymbol field =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:5: ( (e= primary ) ( options {backtrack=true; } : '.' method= refMethod[$value.type] '(' params= exprList ')' | '.' field= refField[$value.type] )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:9: (e= primary ) ( options {backtrack=true; } : '.' method= refMethod[$value.type] '(' params= exprList ')' | '.' field= refField[$value.type] )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:9: (e= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:156:10: e= primary
            {
            pushFollow(FOLLOW_primary_in_postfixexpr790);
            e=primary();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = e;}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:157:9: ( options {backtrack=true; } : '.' method= refMethod[$value.type] '(' params= exprList ')' | '.' field= refField[$value.type] )*
            loop15:
            do {
                int alt15=3;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==18) ) {
                    int LA15_2 = input.LA(2);

                    if ( (LA15_2==ID) ) {
                        int LA15_3 = input.LA(3);

                        if ( (LA15_3==12) ) {
                            alt15=1;
                        }
                        else if ( (LA15_3==EOF||(LA15_3 >= 13 && LA15_3 <= 20)) ) {
                            alt15=2;
                        }


                    }


                }


                switch (alt15) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:12: '.' method= refMethod[$value.type] '(' params= exprList ')'
            	    {
            	    match(input,18,FOLLOW_18_in_postfixexpr822); if (state.failed) return value;

            	    pushFollow(FOLLOW_refMethod_in_postfixexpr826);
            	    method=refMethod(value.type);

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,12,FOLLOW_12_in_postfixexpr829); if (state.failed) return value;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr833);
            	    params=exprList();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    match(input,13,FOLLOW_13_in_postfixexpr835); if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value = invoke(value,method,params);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:159:12: '.' field= refField[$value.type]
            	    {
            	    match(input,18,FOLLOW_18_in_postfixexpr850); if (state.failed) return value;

            	    pushFollow(FOLLOW_refField_in_postfixexpr854);
            	    field=refField(value.type);

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) { value = getField(value,field); }

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
    // $ANTLR end "postfixexpr"



    // $ANTLR start "refMethod"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:163:1: refMethod[Type objType] returns [MethodSymbol value] : ID ;
    public final MethodSymbol refMethod(Type objType) throws RecognitionException {
        MethodSymbol value = null;


        Token ID9=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:164:5: ( ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:164:7: ID
            {
            ID9=(Token)match(input,ID,FOLLOW_ID_in_refMethod895); if (state.failed) return value;

            if ( state.backtracking==0 ) {value =new MethodSymbol((ClassSymbol)objType,(ID9!=null?ID9.getText():null));}

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
    // $ANTLR end "refMethod"



    // $ANTLR start "refField"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:167:1: refField[Type objType] returns [FieldSymbol value] : ID ;
    public final FieldSymbol refField(Type objType) throws RecognitionException {
        FieldSymbol value = null;


        Token ID10=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:168:5: ( ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:168:7: ID
            {
            ID10=(Token)match(input,ID,FOLLOW_ID_in_refField923); if (state.failed) return value;

            if ( state.backtracking==0 ) {value = new FieldSymbol((ClassSymbol)objType,(ID10!=null?ID10.getText():null));}

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
    // $ANTLR end "refField"



    // $ANTLR start "exprList"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:171:1: exprList returns [List<Var> list] : (e= expr ( ',' expr )* |);
    public final List<Var> exprList() throws RecognitionException {
        List<Var> list = null;


        Var e =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:173:5: (e= expr ( ',' expr )* |)
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0 >= ID && LA17_0 <= INT)||LA17_0==12||LA17_0==25||(LA17_0 >= 27 && LA17_0 <= 28)) ) {
                alt17=1;
            }
            else if ( (LA17_0==13) ) {
                alt17=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return list;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:173:9: e= expr ( ',' expr )*
                    {
                    pushFollow(FOLLOW_expr_in_exprList961);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return list;

                    if ( state.backtracking==0 ) {list.add(e);}

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:9: ( ',' expr )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==16) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:10: ',' expr
                    	    {
                    	    match(input,16,FOLLOW_16_in_exprList974); if (state.failed) return list;

                    	    pushFollow(FOLLOW_expr_in_exprList976);
                    	    expr();

                    	    state._fsp--;
                    	    if (state.failed) return list;

                    	    if ( state.backtracking==0 ) {list.add(e);}

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:175:5: 
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:177:1: primary returns [Var value] : ( ( 'new' type '(' ps= exprList ')' ) |ref= ref_this |ref= ref_super |ref= ref_const | ID | '(' v= expr ')' );
    public final Var primary() throws RecognitionException {
        Var value = null;


        Token ID11=null;
        List<Var> ps =null;

        Var ref =null;

        Var v =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:178:5: ( ( 'new' type '(' ps= exprList ')' ) |ref= ref_this |ref= ref_super |ref= ref_const | ID | '(' v= expr ')' )
            int alt18=6;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt18=1;
                }
                break;
            case 28:
                {
                alt18=2;
                }
                break;
            case 27:
                {
                alt18=3;
                }
                break;
            case INT:
                {
                alt18=4;
                }
                break;
            case ID:
                {
                alt18=5;
                }
                break;
            case 12:
                {
                alt18=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }

            switch (alt18) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:178:9: ( 'new' type '(' ps= exprList ')' )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:178:9: ( 'new' type '(' ps= exprList ')' )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:178:10: 'new' type '(' ps= exprList ')'
                    {
                    match(input,25,FOLLOW_25_in_primary1008); if (state.failed) return value;

                    pushFollow(FOLLOW_type_in_primary1010);
                    type();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,12,FOLLOW_12_in_primary1012); if (state.failed) return value;

                    pushFollow(FOLLOW_exprList_in_primary1016);
                    ps=exprList();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_primary1018); if (state.failed) return value;

                    }


                    if ( state.backtracking==0 ) {;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:179:9: ref= ref_this
                    {
                    pushFollow(FOLLOW_ref_this_in_primary1033);
                    ref=ref_this();

                    state._fsp--;
                    if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = ref;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:180:9: ref= ref_super
                    {
                    pushFollow(FOLLOW_ref_super_in_primary1052);
                    ref=ref_super();

                    state._fsp--;
                    if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = ref;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:181:9: ref= ref_const
                    {
                    pushFollow(FOLLOW_ref_const_in_primary1070);
                    ref=ref_const();

                    state._fsp--;
                    if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = ref;}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:182:9: ID
                    {
                    ID11=(Token)match(input,ID,FOLLOW_ID_in_primary1086); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = locals.get((ID11!=null?ID11.getText():null));}

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:183:9: '(' v= expr ')'
                    {
                    match(input,12,FOLLOW_12_in_primary1113); if (state.failed) return value;

                    pushFollow(FOLLOW_expr_in_primary1117);
                    v=expr();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_primary1119); if (state.failed) return value;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:190:1: ref_this returns [Var value] : 'this' ;
    public final Var ref_this() throws RecognitionException {
        Var value = null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:191:5: ( 'this' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:191:9: 'this'
            {
            match(input,28,FOLLOW_28_in_ref_this1156); if (state.failed) return value;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:194:1: ref_super returns [Var value] : 'super' ;
    public final Var ref_super() throws RecognitionException {
        Var value = null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:195:5: ( 'super' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:195:9: 'super'
            {
            match(input,27,FOLLOW_27_in_ref_super1180); if (state.failed) return value;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:198:1: ref_const returns [Var value] : INT ;
    public final Var ref_const() throws RecognitionException {
        Var value = null;


        Token INT12=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:199:5: ( INT )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:199:9: INT
            {
            INT12=(Token)match(input,INT,FOLLOW_INT_in_ref_const1208); if (state.failed) return value;

            if ( state.backtracking==0 ) {value = loadI((INT12!=null?INT12.getText():null));}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:202:1: type returns [Type type] : ( 'decimal' | 'int' | 'void' | ID );
    public final Type type() throws RecognitionException {
        Type type = null;


        Token ID13=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:203:5: ( 'decimal' | 'int' | 'void' | ID )
            int alt19=4;
            switch ( input.LA(1) ) {
            case 22:
                {
                alt19=1;
                }
                break;
            case 24:
                {
                alt19=2;
                }
                break;
            case 29:
                {
                alt19=3;
                }
                break;
            case ID:
                {
                alt19=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:203:9: 'decimal'
                    {
                    match(input,22,FOLLOW_22_in_type1237); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:204:9: 'int'
                    {
                    match(input,24,FOLLOW_24_in_type1249); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:205:9: 'void'
                    {
                    match(input,29,FOLLOW_29_in_type1265); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:206:9: ID
                    {
                    ID13=(Token)match(input,ID,FOLLOW_ID_in_type1280); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = resolveType((ID13!=null?ID13.getText():null));}

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

    // $ANTLR start synpred1_Nebula
    public final void synpred1_Nebula_fragment() throws RecognitionException {
        Var to =null;

        Var from =null;


        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:134:9: ( (to= postfixexpr '=' from= expr ) )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:134:9: (to= postfixexpr '=' from= expr )
        {
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:134:9: (to= postfixexpr '=' from= expr )
        // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:134:10: to= postfixexpr '=' from= expr
        {
        pushFollow(FOLLOW_postfixexpr_in_synpred1_Nebula560);
        to=postfixexpr();

        state._fsp--;
        if (state.failed) return ;

        match(input,20,FOLLOW_20_in_synpred1_Nebula562); if (state.failed) return ;

        pushFollow(FOLLOW_expr_in_synpred1_Nebula566);
        from=expr();

        state._fsp--;
        if (state.failed) return ;

        }


        }

    }
    // $ANTLR end synpred1_Nebula

    // Delegated rules

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


 

    public static final BitSet FOLLOW_classDefinition_in_compilationUnit51 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_EOF_in_compilationUnit56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_classDefinition80 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_classDefinition82 = new BitSet(new long[]{0x0000000040800000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition84 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_classDefinition112 = new BitSet(new long[]{0x0000000021400020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition114 = new BitSet(new long[]{0x00000000A1400020L});
    public static final BitSet FOLLOW_31_in_classDefinition117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_superClass158 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_superClass160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fieldDeclaration_in_classMember177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_fieldDeclaration199 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_fieldDeclaration201 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_fieldDeclaration204 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_expr_in_fieldDeclaration208 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_fieldDeclaration212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration233 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration237 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration239 = new BitSet(new long[]{0x0000000021402020L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration243 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration246 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameters305 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters309 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_formalParameters318 = new BitSet(new long[]{0x0000000021400020L});
    public static final BitSet FOLLOW_type_in_formalParameters322 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters326 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_30_in_block379 = new BitSet(new long[]{0x00000000FF481060L});
    public static final BitSet FOLLOW_statement_in_block381 = new BitSet(new long[]{0x00000000FF481060L});
    public static final BitSet FOLLOW_31_in_block384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement417 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_statement432 = new BitSet(new long[]{0x000000001A081060L});
    public static final BitSet FOLLOW_expr_in_statement434 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprStatement_in_statement450 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_statement466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration489 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_varDeclaration491 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_20_in_varDeclaration502 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_expr_in_varDeclaration506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixexpr_in_exprStatement560 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_exprStatement562 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_expr_in_exprStatement566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixexpr_in_exprStatement583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addexpr_in_expr612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multexpr_in_addexpr643 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_15_in_addexpr659 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr663 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_17_in_addexpr680 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr684 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_postfixexpr_in_multexpr728 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_multexpr745 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_postfixexpr_in_multexpr749 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_primary_in_postfixexpr790 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_postfixexpr822 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_refMethod_in_postfixexpr826 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixexpr829 = new BitSet(new long[]{0x000000001A003060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr833 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixexpr835 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_postfixexpr850 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_refField_in_postfixexpr854 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ID_in_refMethod895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_refField923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprList961 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_exprList974 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_expr_in_exprList976 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_25_in_primary1008 = new BitSet(new long[]{0x0000000021400020L});
    public static final BitSet FOLLOW_type_in_primary1010 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_primary1012 = new BitSet(new long[]{0x000000001A003060L});
    public static final BitSet FOLLOW_exprList_in_primary1016 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ref_this_in_primary1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ref_super_in_primary1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ref_const_in_primary1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary1113 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_expr_in_primary1117 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ref_this1156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ref_super1180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_ref_const1208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_type1237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_type1249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_type1265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type1280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixexpr_in_synpred1_Nebula560 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_synpred1_Nebula562 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_expr_in_synpred1_Nebula566 = new BitSet(new long[]{0x0000000000000002L});

}