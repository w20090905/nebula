// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-20 09:30:29

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
      
      Stack<Var> tmp = new Stack<Var>();
      int tmpCount = 0;
      protected Var popTmp(Type type){      
          Var v = tmp.empty()?new TempVar("tmp" + (++tmpCount),type):tmp.pop();
          v.applied = false;
          return v;
      }
      
      protected Var pick(Var a,Var b){
          assert a.type.equals(b.type); 
          if(!b.applied)resolveTmp(b);
          if(!a.applied)resolveTmp(a);
          return popTmp(a.type);
      }
      
      protected void resolveTmp(Var v){
            assert !v.applied;
            //Resolved
            tmp.push(v);
            v.applied = true;
      }
      
      protected void clearTmp(){
          if(!tmp.empty()){
          }
          for(Var v : tmp){
            if(!v.applied){
                info("TMP VAR NOT CLEANED!!!");
            }
          }
          tmp.clear();
          tmpCount = 0;
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
     
      protected Var add(Var a, Var b) {Var v=pick(a,b);info(v.name +  " = " + a.name + " + " + b.name + ";\n ");return v;};
      protected Var sub(Var a, Var b) {Var v=pick(a,b);info(v.name +  " = " + a.name + " - " + b.name + ";\n ");return v;};
      protected Var mul(Var a, Var b) {Var v=pick(a,b);info(v.name +  " = " + a.name + " * " + b.name + ";\n ");return v;};
      
      
      protected Var getField(Var obj,FieldSymbol field) {
          info(" (" + obj.name + "." + field.name + ") ");
          return popTmp(null);
      };
      protected Var setField(Var obj, FieldSymbol field, Var value) {
          return value;
      };  
        
      protected Var invoke(Var obj,MethodSymbol field,List<Var> params) {
          info(" " + obj.name + "." + field.name + "(" + params + ")) "); 
          return popTmp(null);
      };
        
      protected Var set(Var to,Var from){
            info("HIDE " + to.name + " = " + from.name + ";\n");
            from.name = to.name;
            assert from.type.equals(to.type); 
            from.reg = to.reg;
            resolveTmp(from);        
            return to;
      };
      protected Var set(String text,Type type,Var v){
            info("HIDE " + text + " = " + v.name + ";\n");
            v.name = text;
            v.type = type; 
            resolveTmp(v);
            push(v);
            return v;
      };


      
      protected Var loadI(String text){
          Var v=popTmp(BuiltInTypeSymbol.INT);
          info(v.name + " = " + Integer.parseInt(text) + ";\n ");
          return v;
      };
      
      protected Var top=null;
      
      protected void info(String str){
          System.out.print(str);
      }



    // $ANTLR start "compilationUnit"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:127:1: compilationUnit : ( classDefinition )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:128:5: ( ( classDefinition )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:128:9: ( classDefinition )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:128:9: ( classDefinition )+
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:128:11: classDefinition
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:132:1: classDefinition : 'class' ID ( superClass )? '{' ( classMember )+ '}' ;
    public final void classDefinition() throws RecognitionException {
        Token ID1=null;
        Type superClass2 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:133:5: ( 'class' ID ( superClass )? '{' ( classMember )+ '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:133:9: 'class' ID ( superClass )? '{' ( classMember )+ '}'
            {
            match(input,21,FOLLOW_21_in_classDefinition80); if (state.failed) return ;

            ID1=(Token)match(input,ID,FOLLOW_ID_in_classDefinition82); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:133:20: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==23) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:133:20: superClass
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

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:135:14: ( classMember )+
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:135:14: classMember
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:139:1: superClass returns [Type type] : 'extends' ID ;
    public final Type superClass() throws RecognitionException {
        Type type = null;


        Token ID3=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:140:3: ( 'extends' ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:140:5: 'extends' ID
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:143:1: classMember : ( fieldDeclaration | methodDeclaration );
    public final void classMember() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:144:3: ( fieldDeclaration | methodDeclaration )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:144:5: fieldDeclaration
                    {
                    pushFollow(FOLLOW_fieldDeclaration_in_classMember177);
                    fieldDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:144:24: methodDeclaration
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:147:1: fieldDeclaration : type ID ( '=' e= expr )? ';' ;
    public final void fieldDeclaration() throws RecognitionException {
        Token ID4=null;
        Var e =null;

        Type type5 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:148:3: ( type ID ( '=' e= expr )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:148:7: type ID ( '=' e= expr )? ';'
            {
            pushFollow(FOLLOW_type_in_fieldDeclaration199);
            type5=type();

            state._fsp--;
            if (state.failed) return ;

            ID4=(Token)match(input,ID,FOLLOW_ID_in_fieldDeclaration201); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:148:15: ( '=' e= expr )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==20) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:148:16: '=' e= expr
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:151:1: methodDeclaration : type name= ID '(' (params= formalParameters )? ')' block ;
    public final void methodDeclaration() throws RecognitionException {
        Token name=null;
        List<Var> params =null;

        Type type6 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:3: ( type name= ID '(' (params= formalParameters )? ')' block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:7: type name= ID '(' (params= formalParameters )? ')' block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration233);
            type6=type();

            state._fsp--;
            if (state.failed) return ;

            name=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration237); if (state.failed) return ;

            match(input,12,FOLLOW_12_in_methodDeclaration239); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:30: (params= formalParameters )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ID||LA6_0==22||LA6_0==24||LA6_0==29) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:152:30: params= formalParameters
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:1: formalParameters returns [List<Var> list] : t= type id= ID ( ',' t= type id= ID )* ;
    public final List<Var> formalParameters() throws RecognitionException {
        List<Var> list = null;


        Token id=null;
        Type t =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:3: (t= type id= ID ( ',' t= type id= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:160:5: t= type id= ID ( ',' t= type id= ID )*
            {
            pushFollow(FOLLOW_type_in_formalParameters305);
            t=type();

            state._fsp--;
            if (state.failed) return list;

            id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters309); if (state.failed) return list;

            if ( state.backtracking==0 ) {list.add(new Param((id!=null?id.getText():null),t));}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:5: ( ',' t= type id= ID )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:161:7: ',' t= type id= ID
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:169:1: block : '{' ( statement )* '}' ;
    public final void block() throws RecognitionException {
        info("Block{\n");
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:172:5: ( '{' ( statement )* '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:172:9: '{' ( statement )* '}'
            {
            match(input,30,FOLLOW_30_in_block379); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:172:13: ( statement )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= ID && LA8_0 <= INT)||LA8_0==12||LA8_0==19||LA8_0==22||(LA8_0 >= 24 && LA8_0 <= 30)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:172:13: statement
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:175:1: statement : ( block | varDeclaration ';' | 'return' ( expr )? ';' | exprStatement ';' | ';' );
    public final void statement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:177:5: ( block | varDeclaration ';' | 'return' ( expr )? ';' | exprStatement ';' | ';' )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:177:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement415);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:178:9: varDeclaration ';'
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement425);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,19,FOLLOW_19_in_statement428); if (state.failed) return ;

                    if ( state.backtracking==0 ) {clearTmp();}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:179:9: 'return' ( expr )? ';'
                    {
                    match(input,26,FOLLOW_26_in_statement440); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:179:18: ( expr )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0 >= ID && LA9_0 <= INT)||LA9_0==12||LA9_0==25||(LA9_0 >= 27 && LA9_0 <= 28)) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:179:18: expr
                            {
                            pushFollow(FOLLOW_expr_in_statement442);
                            expr();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,19,FOLLOW_19_in_statement446); if (state.failed) return ;

                    if ( state.backtracking==0 ) {clearTmp();}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:180:9: exprStatement ';'
                    {
                    pushFollow(FOLLOW_exprStatement_in_statement458);
                    exprStatement();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,19,FOLLOW_19_in_statement462); if (state.failed) return ;

                    if ( state.backtracking==0 ) {clearTmp();}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:181:9: ';'
                    {
                    match(input,19,FOLLOW_19_in_statement474); if (state.failed) return ;

                    }
                    break;

            }
            if ( state.backtracking==0 ) {clearTmp();}
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:184:1: varDeclaration : type ID ( '=' v= expr )? ;
    public final void varDeclaration() throws RecognitionException {
        Token ID7=null;
        Var v =null;

        Type type8 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:185:5: ( type ID ( '=' v= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:185:9: type ID ( '=' v= expr )?
            {
            pushFollow(FOLLOW_type_in_varDeclaration497);
            type8=type();

            state._fsp--;
            if (state.failed) return ;

            ID7=(Token)match(input,ID,FOLLOW_ID_in_varDeclaration499); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:186:9: ( '=' v= expr )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==20) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:186:10: '=' v= expr
                    {
                    match(input,20,FOLLOW_20_in_varDeclaration510); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_varDeclaration514);
                    v=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {set((ID7!=null?ID7.getText():null),type8,v);}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:190:1: exprStatement options {backtrack=true; } : to= postfixexpr ( '=' from= expr )? ;
    public final void exprStatement() throws RecognitionException {
        NebulaParser.postfixexpr_return to =null;

        Var from =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:192:5: (to= postfixexpr ( '=' from= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:192:9: to= postfixexpr ( '=' from= expr )?
            {
            pushFollow(FOLLOW_postfixexpr_in_exprStatement567);
            to=postfixexpr();

            state._fsp--;
            if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:193:9: ( '=' from= expr )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==20) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:193:10: '=' from= expr
                    {
                    match(input,20,FOLLOW_20_in_exprStatement579); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_exprStatement583);
                    from=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {   if(from==null){
                            assert to.field == null;
                        } else if (to.field==null){
                            set(to.value,from);
                        } else {
                            setField(to.value,to.field,from);
                        }
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:204:1: expr returns [Var value] : e= addexpr ;
    public final Var expr() throws RecognitionException {
        Var value = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:205:5: (e= addexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:205:9: e= addexpr
            {
            pushFollow(FOLLOW_addexpr_in_expr626);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:208:1: addexpr returns [Var value] : a= multexpr ( '+' b= multexpr | '-' c= multexpr )* ;
    public final Var addexpr() throws RecognitionException {
        Var value = null;


        Var a =null;

        Var b =null;

        Var c =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:209:5: (a= multexpr ( '+' b= multexpr | '-' c= multexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:209:9: a= multexpr ( '+' b= multexpr | '-' c= multexpr )*
            {
            pushFollow(FOLLOW_multexpr_in_addexpr657);
            a=multexpr();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value = a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:210:9: ( '+' b= multexpr | '-' c= multexpr )*
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:210:13: '+' b= multexpr
            	    {
            	    match(input,15,FOLLOW_15_in_addexpr673); if (state.failed) return value;

            	    pushFollow(FOLLOW_multexpr_in_addexpr677);
            	    b=multexpr();

            	    state._fsp--;
            	    if (state.failed) return value;

            	    if ( state.backtracking==0 ) {value =add(a,b);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:211:13: '-' c= multexpr
            	    {
            	    match(input,17,FOLLOW_17_in_addexpr694); if (state.failed) return value;

            	    pushFollow(FOLLOW_multexpr_in_addexpr698);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:215:1: multexpr returns [Var value] : a= postfixExprValue ( '*' b= postfixExprValue )* ;
    public final Var multexpr() throws RecognitionException {
        Var value = null;


        Var a =null;

        Var b =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:216:5: (a= postfixExprValue ( '*' b= postfixExprValue )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:216:9: a= postfixExprValue ( '*' b= postfixExprValue )*
            {
            pushFollow(FOLLOW_postfixExprValue_in_multexpr742);
            a=postfixExprValue();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value =a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:217:9: ( '*' b= postfixExprValue )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==14) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:217:13: '*' b= postfixExprValue
            	    {
            	    match(input,14,FOLLOW_14_in_multexpr759); if (state.failed) return value;

            	    pushFollow(FOLLOW_postfixExprValue_in_multexpr763);
            	    b=postfixExprValue();

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



    // $ANTLR start "postfixExprValue"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:221:1: postfixExprValue returns [Var value] : p= postfixexpr ;
    public final Var postfixExprValue() throws RecognitionException {
        Var value = null;


        NebulaParser.postfixexpr_return p =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:222:5: (p= postfixexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:222:6: p= postfixexpr
            {
            pushFollow(FOLLOW_postfixexpr_in_postfixExprValue803);
            p=postfixexpr();

            state._fsp--;
            if (state.failed) return value;

            if ( state.backtracking==0 ) {value =(p!=null?p.value:null); if((p!=null?p.field:null)!=null){value =getField(value,(p!=null?p.field:null));} }

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
    // $ANTLR end "postfixExprValue"


    public static class postfixexpr_return extends ParserRuleReturnScope {
        public Var value;
        public FieldSymbol field;
    };


    // $ANTLR start "postfixexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:225:1: postfixexpr returns [Var value,FieldSymbol field] : (e= primary ) ( options {backtrack=true; } : '.' m= refMethod[$value.type] '(' params= exprList ')' | '.' f= refField[$value.type] )* ;
    public final NebulaParser.postfixexpr_return postfixexpr() throws RecognitionException {
        NebulaParser.postfixexpr_return retval = new NebulaParser.postfixexpr_return();
        retval.start = input.LT(1);


        Var e =null;

        MethodSymbol m =null;

        List<Var> params =null;

        FieldSymbol f =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:226:5: ( (e= primary ) ( options {backtrack=true; } : '.' m= refMethod[$value.type] '(' params= exprList ')' | '.' f= refField[$value.type] )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:226:9: (e= primary ) ( options {backtrack=true; } : '.' m= refMethod[$value.type] '(' params= exprList ')' | '.' f= refField[$value.type] )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:226:9: (e= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:226:10: e= primary
            {
            pushFollow(FOLLOW_primary_in_postfixexpr830);
            e=primary();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {retval.value = e;}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:227:9: ( options {backtrack=true; } : '.' m= refMethod[$value.type] '(' params= exprList ')' | '.' f= refField[$value.type] )*
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
                        else if ( ((LA15_3 >= 13 && LA15_3 <= 20)) ) {
                            alt15=2;
                        }


                    }


                }


                switch (alt15) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:228:12: '.' m= refMethod[$value.type] '(' params= exprList ')'
            	    {
            	    match(input,18,FOLLOW_18_in_postfixexpr862); if (state.failed) return retval;

            	    pushFollow(FOLLOW_refMethod_in_postfixexpr866);
            	    m=refMethod(retval.value.type);

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    match(input,12,FOLLOW_12_in_postfixexpr869); if (state.failed) return retval;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr873);
            	    params=exprList();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    match(input,13,FOLLOW_13_in_postfixexpr875); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {if(retval.field!=null){retval.value =getField(retval.value,retval.field);retval.field =null;} retval.value = invoke(retval.value,m,params);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:229:12: '.' f= refField[$value.type]
            	    {
            	    match(input,18,FOLLOW_18_in_postfixexpr890); if (state.failed) return retval;

            	    pushFollow(FOLLOW_refField_in_postfixexpr894);
            	    f=refField(retval.value.type);

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) { if(retval.field!=null){retval.value =getField(retval.value,retval.field);retval.field =null;}retval.field =f; }

            	    }
            	    break;

            	default :
            	    break loop15;
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



    // $ANTLR start "refMethod"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:233:1: refMethod[Type objType] returns [MethodSymbol value] : ID ;
    public final MethodSymbol refMethod(Type objType) throws RecognitionException {
        MethodSymbol value = null;


        Token ID9=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:234:5: ( ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:234:7: ID
            {
            ID9=(Token)match(input,ID,FOLLOW_ID_in_refMethod934); if (state.failed) return value;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:237:1: refField[Type objType] returns [FieldSymbol value] : ID ;
    public final FieldSymbol refField(Type objType) throws RecognitionException {
        FieldSymbol value = null;


        Token ID10=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:238:5: ( ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:238:7: ID
            {
            ID10=(Token)match(input,ID,FOLLOW_ID_in_refField962); if (state.failed) return value;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:241:1: exprList returns [List<Var> list] : (e= expr ( ',' expr )* |);
    public final List<Var> exprList() throws RecognitionException {
        List<Var> list = null;


        Var e =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:243:5: (e= expr ( ',' expr )* |)
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:243:9: e= expr ( ',' expr )*
                    {
                    pushFollow(FOLLOW_expr_in_exprList1000);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return list;

                    if ( state.backtracking==0 ) {list.add(e);}

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:244:9: ( ',' expr )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==16) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:244:10: ',' expr
                    	    {
                    	    match(input,16,FOLLOW_16_in_exprList1013); if (state.failed) return list;

                    	    pushFollow(FOLLOW_expr_in_exprList1015);
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:245:5: 
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:247:1: primary returns [Var value] : ( ( 'new' type '(' ps= exprList ')' ) |ref= ref_this |ref= ref_super |ref= ref_const | ID | '(' v= expr ')' );
    public final Var primary() throws RecognitionException {
        Var value = null;


        Token ID11=null;
        List<Var> ps =null;

        Var ref =null;

        Var v =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:248:5: ( ( 'new' type '(' ps= exprList ')' ) |ref= ref_this |ref= ref_super |ref= ref_const | ID | '(' v= expr ')' )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:248:9: ( 'new' type '(' ps= exprList ')' )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:248:9: ( 'new' type '(' ps= exprList ')' )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:248:10: 'new' type '(' ps= exprList ')'
                    {
                    match(input,25,FOLLOW_25_in_primary1047); if (state.failed) return value;

                    pushFollow(FOLLOW_type_in_primary1049);
                    type();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,12,FOLLOW_12_in_primary1051); if (state.failed) return value;

                    pushFollow(FOLLOW_exprList_in_primary1055);
                    ps=exprList();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_primary1057); if (state.failed) return value;

                    }


                    if ( state.backtracking==0 ) {;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:249:9: ref= ref_this
                    {
                    pushFollow(FOLLOW_ref_this_in_primary1072);
                    ref=ref_this();

                    state._fsp--;
                    if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = ref;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:250:9: ref= ref_super
                    {
                    pushFollow(FOLLOW_ref_super_in_primary1091);
                    ref=ref_super();

                    state._fsp--;
                    if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = ref;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:251:9: ref= ref_const
                    {
                    pushFollow(FOLLOW_ref_const_in_primary1109);
                    ref=ref_const();

                    state._fsp--;
                    if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = ref;}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:252:9: ID
                    {
                    ID11=(Token)match(input,ID,FOLLOW_ID_in_primary1125); if (state.failed) return value;

                    if ( state.backtracking==0 ) {value = locals.get((ID11!=null?ID11.getText():null));}

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:253:9: '(' v= expr ')'
                    {
                    match(input,12,FOLLOW_12_in_primary1152); if (state.failed) return value;

                    pushFollow(FOLLOW_expr_in_primary1156);
                    v=expr();

                    state._fsp--;
                    if (state.failed) return value;

                    match(input,13,FOLLOW_13_in_primary1158); if (state.failed) return value;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:260:1: ref_this returns [Var value] : 'this' ;
    public final Var ref_this() throws RecognitionException {
        Var value = null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:261:5: ( 'this' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:261:9: 'this'
            {
            match(input,28,FOLLOW_28_in_ref_this1195); if (state.failed) return value;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:264:1: ref_super returns [Var value] : 'super' ;
    public final Var ref_super() throws RecognitionException {
        Var value = null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:265:5: ( 'super' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:265:9: 'super'
            {
            match(input,27,FOLLOW_27_in_ref_super1219); if (state.failed) return value;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:268:1: ref_const returns [Var value] : INT ;
    public final Var ref_const() throws RecognitionException {
        Var value = null;


        Token INT12=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:269:5: ( INT )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:269:9: INT
            {
            INT12=(Token)match(input,INT,FOLLOW_INT_in_ref_const1247); if (state.failed) return value;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:272:1: type returns [Type type] : ( 'decimal' | 'int' | 'void' | ID );
    public final Type type() throws RecognitionException {
        Type type = null;


        Token ID13=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:273:5: ( 'decimal' | 'int' | 'void' | ID )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:273:9: 'decimal'
                    {
                    match(input,22,FOLLOW_22_in_type1276); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:274:9: 'int'
                    {
                    match(input,24,FOLLOW_24_in_type1288); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:275:9: 'void'
                    {
                    match(input,29,FOLLOW_29_in_type1304); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:276:9: ID
                    {
                    ID13=(Token)match(input,ID,FOLLOW_ID_in_type1319); if (state.failed) return type;

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

    // Delegated rules


 

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
    public static final BitSet FOLLOW_block_in_statement415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement425 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_statement440 = new BitSet(new long[]{0x000000001A081060L});
    public static final BitSet FOLLOW_expr_in_statement442 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprStatement_in_statement458 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_statement474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration497 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_varDeclaration499 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_20_in_varDeclaration510 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_expr_in_varDeclaration514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixexpr_in_exprStatement567 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_20_in_exprStatement579 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_expr_in_exprStatement583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addexpr_in_expr626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multexpr_in_addexpr657 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_15_in_addexpr673 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr677 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_17_in_addexpr694 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr698 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_postfixExprValue_in_multexpr742 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_multexpr759 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_postfixExprValue_in_multexpr763 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_postfixexpr_in_postfixExprValue803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_postfixexpr830 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_postfixexpr862 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_refMethod_in_postfixexpr866 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixexpr869 = new BitSet(new long[]{0x000000001A003060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr873 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixexpr875 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_postfixexpr890 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_refField_in_postfixexpr894 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ID_in_refMethod934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_refField962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprList1000 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_exprList1013 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_expr_in_exprList1015 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_25_in_primary1047 = new BitSet(new long[]{0x0000000021400020L});
    public static final BitSet FOLLOW_type_in_primary1049 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_primary1051 = new BitSet(new long[]{0x000000001A003060L});
    public static final BitSet FOLLOW_exprList_in_primary1055 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ref_this_in_primary1072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ref_super_in_primary1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ref_const_in_primary1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary1152 = new BitSet(new long[]{0x000000001A001060L});
    public static final BitSet FOLLOW_expr_in_primary1156 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ref_this1195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ref_super1219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_ref_const1247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_type1276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_type1288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_type1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type1319 = new BitSet(new long[]{0x0000000000000002L});

}