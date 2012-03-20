// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-20 23:11:21

package nebula.vm;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class NebulaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Digit", "ID", "INT", "Letter", "MultiLineComment", "NEWLINE", "SingleLineComment", "Whitespace", "'('", "'()'", "')'", "'*'", "'+'", "','", "'-'", "'.'", "';'", "'='", "'class'", "'decimal'", "'extends'", "'int'", "'new'", "'return'", "'super'", "'this'", "'void'", "'{'", "'}'"
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
      /** Map variable name to INT object holding v */
      Map<String,Var> locals = new HashMap<>();
      short maxLocals = 0;

      protected void push(Var var) {
        locals.put(var.name,var);
        var.reg = (short) (locals.size() - 1);
        maxLocals = maxLocals > (short) locals.size() ? maxLocals : (short) locals.size();
      } 
      
      Stack<TempVar> tmps = new Stack<TempVar>();
      int tmpCount = 0;
      protected Var popTmp(Type type){
          TempVar v = null;
          for(TempVar tv:tmps){
              if(tv.applied){
                  v = tv;
                  break;
              }
          }
          if(v ==null){
              int index = locals.size() + tmps.size() + 1;
              v = new TempVar("tmp"+index,index,type);
              tmps.push(v);
          }
          v.applied = false;
          return v;
      }
      
      protected Var pick(Var a,Var b){
          assert a.type.equals(b.type); 
          if(!a.applied)releaseTmp(a);
          if(!b.applied)releaseTmp(b);
          return popTmp(a.type);
      }

      protected void releaseTmp(Var v){
    			assert !v.applied;
    			//Resolved
    			//        tmps.push(v);
    			v.applied = true;
      }
      
      protected void clearTmp(){
          if(!tmps.empty()){
          }
          for(Var v : tmps){
            if(!v.applied){
                info("TMP VAR NOT CLEANED!!!");
            }
          }
          tmps.clear();
          tmpCount = 0;
          info("\n");
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

      protected void defineField(String name,Type type){
          info("define field " + name + "\n");
      };
      
      protected Type resolveType(String name){return null;};
     
      protected Var add(Var a, Var b) {Var v=pick(a,b);info(v.name +  " = " + a.name + " + " + b.name + ";\n");return v;};
      protected Var sub(Var a, Var b) {Var v=pick(a,b);info(v.name +  " = " + a.name + " - " + b.name + ";\n");return v;};
      protected Var mul(Var a, Var b) {Var v=pick(a,b);info(v.name +  " = " + a.name + " * " + b.name + ";\n");return v;};

      protected Var getField(Var obj,FieldSymbol field) {
          Var var = popTmp(BuiltInTypeSymbol.FLEX);
          info(var.name +  " = " + obj.name + "." + field.name + "\n");
          return var;
      };
      
      protected Var setField(Var obj, FieldSymbol field, Var v) {
          info(" " + obj.name + "." + field.name + " = " + v.name + "\n");  
          return v;
      };  
        
      protected Var invoke(Var obj,MethodSymbol method,List<Var> params) {
          String txtParams = "";
          for(Var v:params){
              txtParams += v.name + " ";
              releaseTmp(v);
          }
          Var var = popTmp(BuiltInTypeSymbol.FLEX);
          info(var.name + " =  " + obj.name + "." + method.definedClass.name + "_" +  method.name + "("  + txtParams +  ")\n"); 
          return var;
      };
        
      protected Var set(Var to,Var from){
            info("HIDE " + to.name + " = " + from.name + ";\n");
            from.name = to.name;
            assert from.type.equals(to.type); 
            from.reg = to.reg;
            releaseTmp(from);        
            return to;
      };
      protected Var set(String text,Type type,Var v){
            info("HIDE " + text + " = " + v.name + ";\n");
            v.name = text;
            v.type = type;
            releaseTmp(v);
            push(v);
            return v;
      };

      protected Var createObject(Type type){
            Var var = popTmp(type);
            info(var.name  + " = new " + type.getName() + ";\n");
            return var;
      }
      
      protected Var loadI(String text){
          Var v=popTmp(BuiltInTypeSymbol.INT);
          info(v.name + " = " + Integer.parseInt(text) + ";\n");
          return v;
      };
      
      protected Var top=null;
      
      protected void info(String str){
          if(str.length() > 2){
    		      String txtTemps = "";      
    		      for(TempVar v : tmps){
    		        txtTemps += "" + (v.applied?" ":v.index) + " ";
    		      }
    		      str = "tmps["+ txtTemps +"]\t" + str;
          }
          System.out.print(str);
      }



    // $ANTLR start "compilationUnit"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:158:1: compilationUnit : ( classDefinition )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:159:5: ( ( classDefinition )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:159:9: ( classDefinition )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:159:9: ( classDefinition )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==22) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:159:11: classDefinition
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:163:1: classDefinition : 'class' ID ( superClass )? '{' ( classMember )+ '}' ;
    public final void classDefinition() throws RecognitionException {
        Token ID1=null;
        Type superClass2 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:164:5: ( 'class' ID ( superClass )? '{' ( classMember )+ '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:164:9: 'class' ID ( superClass )? '{' ( classMember )+ '}'
            {
            match(input,22,FOLLOW_22_in_classDefinition80); if (state.failed) return ;

            ID1=(Token)match(input,ID,FOLLOW_ID_in_classDefinition82); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:164:20: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==24) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:164:20: superClass
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

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:166:14: ( classMember )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID||LA3_0==23||LA3_0==25||LA3_0==30) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:166:14: classMember
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:170:1: superClass returns [Type type] : 'extends' ID ;
    public final Type superClass() throws RecognitionException {
        Type type = null;


        Token ID3=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:171:3: ( 'extends' ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:171:5: 'extends' ID
            {
            match(input,24,FOLLOW_24_in_superClass158); if (state.failed) return type;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:174:1: classMember : ( fieldDeclaration | methodDeclaration );
    public final void classMember() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:175:3: ( fieldDeclaration | methodDeclaration )
            int alt4=2;
            switch ( input.LA(1) ) {
            case 23:
                {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==ID) ) {
                    int LA4_5 = input.LA(3);

                    if ( ((LA4_5 >= 20 && LA4_5 <= 21)) ) {
                        alt4=1;
                    }
                    else if ( ((LA4_5 >= 12 && LA4_5 <= 13)) ) {
                        alt4=2;
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
            case 25:
                {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==ID) ) {
                    int LA4_5 = input.LA(3);

                    if ( ((LA4_5 >= 20 && LA4_5 <= 21)) ) {
                        alt4=1;
                    }
                    else if ( ((LA4_5 >= 12 && LA4_5 <= 13)) ) {
                        alt4=2;
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

                    if ( ((LA4_5 >= 20 && LA4_5 <= 21)) ) {
                        alt4=1;
                    }
                    else if ( ((LA4_5 >= 12 && LA4_5 <= 13)) ) {
                        alt4=2;
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

                    if ( ((LA4_5 >= 20 && LA4_5 <= 21)) ) {
                        alt4=1;
                    }
                    else if ( ((LA4_5 >= 12 && LA4_5 <= 13)) ) {
                        alt4=2;
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:175:5: fieldDeclaration
                    {
                    pushFollow(FOLLOW_fieldDeclaration_in_classMember177);
                    fieldDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:175:24: methodDeclaration
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:178:1: fieldDeclaration : type ID ( '=' e= expr )? ';' ;
    public final void fieldDeclaration() throws RecognitionException {
        Token ID4=null;
        Var e =null;

        Type type5 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:179:3: ( type ID ( '=' e= expr )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:179:7: type ID ( '=' e= expr )? ';'
            {
            pushFollow(FOLLOW_type_in_fieldDeclaration197);
            type5=type();

            state._fsp--;
            if (state.failed) return ;

            ID4=(Token)match(input,ID,FOLLOW_ID_in_fieldDeclaration199); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:179:15: ( '=' e= expr )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==21) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:179:16: '=' e= expr
                    {
                    match(input,21,FOLLOW_21_in_fieldDeclaration202); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_fieldDeclaration206);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,20,FOLLOW_20_in_fieldDeclaration210); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:182:1: methodDeclaration : type name= ID ( '()' | ( '(' ( formalParameters )? ')' ) ) block ;
    public final void methodDeclaration() throws RecognitionException {
        Token name=null;
        Type type6 =null;

        List<Var> formalParameters7 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:183:3: ( type name= ID ( '()' | ( '(' ( formalParameters )? ')' ) ) block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:183:7: type name= ID ( '()' | ( '(' ( formalParameters )? ')' ) ) block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration230);
            type6=type();

            state._fsp--;
            if (state.failed) return ;

            name=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration234); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:184:7: ( '()' | ( '(' ( formalParameters )? ')' ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==13) ) {
                alt7=1;
            }
            else if ( (LA7_0==12) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:184:8: '()'
                    {
                    match(input,13,FOLLOW_13_in_methodDeclaration244); if (state.failed) return ;

                    if ( state.backtracking==0 ) {enterFunction((name!=null?name.getText():null),type6,new ArrayList<Var>());}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:185:10: ( '(' ( formalParameters )? ')' )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:185:10: ( '(' ( formalParameters )? ')' )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:185:11: '(' ( formalParameters )? ')'
                    {
                    match(input,12,FOLLOW_12_in_methodDeclaration258); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:185:15: ( formalParameters )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==ID||LA6_0==23||LA6_0==25||LA6_0==30) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:185:15: formalParameters
                            {
                            pushFollow(FOLLOW_formalParameters_in_methodDeclaration260);
                            formalParameters7=formalParameters();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,14,FOLLOW_14_in_methodDeclaration263); if (state.failed) return ;

                    }


                    if ( state.backtracking==0 ) {enterFunction((name!=null?name.getText():null),type6,formalParameters7);}

                    }
                    break;

            }


            pushFollow(FOLLOW_block_in_methodDeclaration290);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:191:1: formalParameters returns [List<Var> list] : t= type id= ID ( ',' t= type id= ID )* ;
    public final List<Var> formalParameters() throws RecognitionException {
        List<Var> list = null;


        Token id=null;
        Type t =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:193:3: (t= type id= ID ( ',' t= type id= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:193:7: t= type id= ID ( ',' t= type id= ID )*
            {
            pushFollow(FOLLOW_type_in_formalParameters332);
            t=type();

            state._fsp--;
            if (state.failed) return list;

            id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters336); if (state.failed) return list;

            if ( state.backtracking==0 ) {list.add(new Param((id!=null?id.getText():null),t));}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:194:5: ( ',' t= type id= ID )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==17) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:194:7: ',' t= type id= ID
            	    {
            	    match(input,17,FOLLOW_17_in_formalParameters345); if (state.failed) return list;

            	    pushFollow(FOLLOW_type_in_formalParameters349);
            	    t=type();

            	    state._fsp--;
            	    if (state.failed) return list;

            	    id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters353); if (state.failed) return list;

            	    if ( state.backtracking==0 ) {list.add(new Param((id!=null?id.getText():null),t));}

            	    }
            	    break;

            	default :
            	    break loop8;
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:202:1: block : '{' ( statement )* '}' ;
    public final void block() throws RecognitionException {
        info("Block{\n");
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:205:5: ( '{' ( statement )* '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:205:9: '{' ( statement )* '}'
            {
            match(input,31,FOLLOW_31_in_block405); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:205:13: ( statement )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= ID && LA9_0 <= INT)||LA9_0==12||LA9_0==20||LA9_0==23||(LA9_0 >= 25 && LA9_0 <= 31)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:205:13: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block407);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            match(input,32,FOLLOW_32_in_block410); if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {info("\n}Block\n");}
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:208:1: statement : ( block | varDeclaration ';' | 'return' ( expr )? ';' | exprStatement ';' | ';' );
    public final void statement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:210:5: ( block | varDeclaration ';' | 'return' ( expr )? ';' | exprStatement ';' | ';' )
            int alt11=5;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt11=1;
                }
                break;
            case 23:
            case 25:
            case 30:
                {
                alt11=2;
                }
                break;
            case ID:
                {
                int LA11_3 = input.LA(2);

                if ( (LA11_3==ID) ) {
                    alt11=2;
                }
                else if ( ((LA11_3 >= 19 && LA11_3 <= 21)) ) {
                    alt11=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 3, input);

                    throw nvae;

                }
                }
                break;
            case 27:
                {
                alt11=3;
                }
                break;
            case INT:
            case 12:
            case 26:
            case 28:
            case 29:
                {
                alt11=4;
                }
                break;
            case 20:
                {
                alt11=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }

            switch (alt11) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:210:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement440);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:211:9: varDeclaration ';'
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement450);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,20,FOLLOW_20_in_statement453); if (state.failed) return ;

                    if ( state.backtracking==0 ) {clearTmp();}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:212:9: 'return' ( expr )? ';'
                    {
                    match(input,27,FOLLOW_27_in_statement465); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:212:18: ( expr )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( ((LA10_0 >= ID && LA10_0 <= INT)||LA10_0==12||LA10_0==26||(LA10_0 >= 28 && LA10_0 <= 29)) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:212:18: expr
                            {
                            pushFollow(FOLLOW_expr_in_statement467);
                            expr();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,20,FOLLOW_20_in_statement471); if (state.failed) return ;

                    if ( state.backtracking==0 ) {clearTmp();}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:213:9: exprStatement ';'
                    {
                    pushFollow(FOLLOW_exprStatement_in_statement483);
                    exprStatement();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,20,FOLLOW_20_in_statement487); if (state.failed) return ;

                    if ( state.backtracking==0 ) {clearTmp();}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:214:9: ';'
                    {
                    match(input,20,FOLLOW_20_in_statement499); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:217:1: varDeclaration : type ID ( '=' expr )? ;
    public final void varDeclaration() throws RecognitionException {
        Token ID8=null;
        Type type9 =null;

        Var expr10 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:218:5: ( type ID ( '=' expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:218:9: type ID ( '=' expr )?
            {
            pushFollow(FOLLOW_type_in_varDeclaration522);
            type9=type();

            state._fsp--;
            if (state.failed) return ;

            ID8=(Token)match(input,ID,FOLLOW_ID_in_varDeclaration524); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:218:17: ( '=' expr )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==21) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:218:18: '=' expr
                    {
                    match(input,21,FOLLOW_21_in_varDeclaration527); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_varDeclaration529);
                    expr10=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {set((ID8!=null?ID8.getText():null),type9,expr10);}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:221:1: exprStatement options {backtrack=true; } : to= postfixexpr ( '=' from= expr )? ;
    public final void exprStatement() throws RecognitionException {
        NebulaParser.postfixexpr_return to =null;

        Var from =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:223:5: (to= postfixexpr ( '=' from= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:223:9: to= postfixexpr ( '=' from= expr )?
            {
            pushFollow(FOLLOW_postfixexpr_in_exprStatement576);
            to=postfixexpr();

            state._fsp--;
            if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:224:9: ( '=' from= expr )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==21) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:224:10: '=' from= expr
                    {
                    match(input,21,FOLLOW_21_in_exprStatement588); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_exprStatement592);
                    from=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {   if(from==null){
                            assert to.field == null;
                            releaseTmp(to.v);
                        } else if (to.field==null){
                            set(to.v,from);
                        } else {
                            setField(to.v,to.field,from);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:236:1: expr returns [Var v] : e= addexpr ;
    public final Var expr() throws RecognitionException {
        Var v = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:237:5: (e= addexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:237:9: e= addexpr
            {
            pushFollow(FOLLOW_addexpr_in_expr635);
            e=addexpr();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v = e;}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "expr"



    // $ANTLR start "addexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:240:1: addexpr returns [Var v] : a= multexpr ( '+' b= multexpr | '-' c= multexpr )* ;
    public final Var addexpr() throws RecognitionException {
        Var v = null;


        Var a =null;

        Var b =null;

        Var c =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:241:5: (a= multexpr ( '+' b= multexpr | '-' c= multexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:241:9: a= multexpr ( '+' b= multexpr | '-' c= multexpr )*
            {
            pushFollow(FOLLOW_multexpr_in_addexpr666);
            a=multexpr();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v = a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:242:9: ( '+' b= multexpr | '-' c= multexpr )*
            loop14:
            do {
                int alt14=3;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==16) ) {
                    alt14=1;
                }
                else if ( (LA14_0==18) ) {
                    alt14=2;
                }


                switch (alt14) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:242:13: '+' b= multexpr
            	    {
            	    match(input,16,FOLLOW_16_in_addexpr682); if (state.failed) return v;

            	    pushFollow(FOLLOW_multexpr_in_addexpr686);
            	    b=multexpr();

            	    state._fsp--;
            	    if (state.failed) return v;

            	    if ( state.backtracking==0 ) {v=add(v,b);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:243:13: '-' c= multexpr
            	    {
            	    match(input,18,FOLLOW_18_in_addexpr703); if (state.failed) return v;

            	    pushFollow(FOLLOW_multexpr_in_addexpr707);
            	    c=multexpr();

            	    state._fsp--;
            	    if (state.failed) return v;

            	    if ( state.backtracking==0 ) {v=sub(v,c);}

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
        return v;
    }
    // $ANTLR end "addexpr"



    // $ANTLR start "multexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:247:1: multexpr returns [Var v] : a= postfixExprValue ( '*' b= postfixExprValue )* ;
    public final Var multexpr() throws RecognitionException {
        Var v = null;


        Var a =null;

        Var b =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:248:5: (a= postfixExprValue ( '*' b= postfixExprValue )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:248:9: a= postfixExprValue ( '*' b= postfixExprValue )*
            {
            pushFollow(FOLLOW_postfixExprValue_in_multexpr751);
            a=postfixExprValue();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v=a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:249:9: ( '*' b= postfixExprValue )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==15) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:249:13: '*' b= postfixExprValue
            	    {
            	    match(input,15,FOLLOW_15_in_multexpr768); if (state.failed) return v;

            	    pushFollow(FOLLOW_postfixExprValue_in_multexpr772);
            	    b=postfixExprValue();

            	    state._fsp--;
            	    if (state.failed) return v;

            	    if ( state.backtracking==0 ) {v=mul(v,b);}

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
        return v;
    }
    // $ANTLR end "multexpr"



    // $ANTLR start "postfixExprValue"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:253:1: postfixExprValue returns [Var v] : p= postfixexpr ;
    public final Var postfixExprValue() throws RecognitionException {
        Var v = null;


        NebulaParser.postfixexpr_return p =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:254:5: (p= postfixexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:254:9: p= postfixexpr
            {
            pushFollow(FOLLOW_postfixexpr_in_postfixExprValue815);
            p=postfixexpr();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v=(p!=null?p.v:null); if((p!=null?p.field:null)!=null){v=getField(v,(p!=null?p.field:null));} }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "postfixExprValue"


    public static class postfixexpr_return extends ParserRuleReturnScope {
        public Var v;
        public FieldSymbol field;
    };


    // $ANTLR start "postfixexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:257:1: postfixexpr returns [Var v,FieldSymbol field] : (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )* ;
    public final NebulaParser.postfixexpr_return postfixexpr() throws RecognitionException {
        NebulaParser.postfixexpr_return retval = new NebulaParser.postfixexpr_return();
        retval.start = input.LT(1);


        Token mID=null;
        Token fID=null;
        Var p =null;

        List<Var> params =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:258:5: ( (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:258:9: (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:258:9: (p= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:258:10: p= primary
            {
            pushFollow(FOLLOW_primary_in_postfixexpr843);
            p=primary();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {retval.v = p;}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:259:9: ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )*
            loop16:
            do {
                int alt16=4;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==19) ) {
                    int LA16_2 = input.LA(2);

                    if ( (LA16_2==ID) ) {
                        switch ( input.LA(3) ) {
                        case 13:
                            {
                            alt16=1;
                            }
                            break;
                        case 12:
                            {
                            alt16=2;
                            }
                            break;
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                            {
                            alt16=3;
                            }
                            break;

                        }

                    }


                }


                switch (alt16) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:260:12: '.' mID= ID '()'
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr876); if (state.failed) return retval;

            	    mID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr880); if (state.failed) return retval;

            	    match(input,13,FOLLOW_13_in_postfixexpr882); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {if(retval.field!=null){retval.v =getField(retval.v,retval.field);retval.field =null;} MethodSymbol m = new MethodSymbol((ClassSymbol)retval.v.type,(mID!=null?mID.getText():null));  retval.v = invoke(retval.v,m,new ArrayList<Var>());}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:261:12: '.' mID= ID '(' params= exprList ')'
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr897); if (state.failed) return retval;

            	    mID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr901); if (state.failed) return retval;

            	    match(input,12,FOLLOW_12_in_postfixexpr903); if (state.failed) return retval;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr907);
            	    params=exprList();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    match(input,14,FOLLOW_14_in_postfixexpr909); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {if(retval.field!=null){retval.v =getField(retval.v,retval.field);retval.field =null;} MethodSymbol m = new MethodSymbol((ClassSymbol)retval.v.type,(mID!=null?mID.getText():null));  retval.v = invoke(retval.v,m,params);}

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:262:12: '.' fID= ID
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr924); if (state.failed) return retval;

            	    fID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr928); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) { if(retval.field!=null){retval.v =getField(retval.v,retval.field);retval.field =null;} retval.field =new FieldSymbol((ClassSymbol)retval.v.type,(fID!=null?fID.getText():null)); }

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:266:1: exprList returns [List<Var> list] : (e= expr ( ',' e= expr )* |);
    public final List<Var> exprList() throws RecognitionException {
        List<Var> list = null;


        Var e =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:268:5: (e= expr ( ',' e= expr )* |)
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0 >= ID && LA18_0 <= INT)||LA18_0==12||LA18_0==26||(LA18_0 >= 28 && LA18_0 <= 29)) ) {
                alt18=1;
            }
            else if ( (LA18_0==14) ) {
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:268:9: e= expr ( ',' e= expr )*
                    {
                    pushFollow(FOLLOW_expr_in_exprList982);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return list;

                    if ( state.backtracking==0 ) {list.add(e);}

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:269:9: ( ',' e= expr )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==17) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:269:10: ',' e= expr
                    	    {
                    	    match(input,17,FOLLOW_17_in_exprList995); if (state.failed) return list;

                    	    pushFollow(FOLLOW_expr_in_exprList999);
                    	    e=expr();

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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:270:5: 
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:272:1: primary returns [Var v] : ( ( 'new' type '()' ) | 'this' | 'super' | INT | ID | '(' expr ')' );
    public final Var primary() throws RecognitionException {
        Var v = null;


        Token INT12=null;
        Token ID13=null;
        Type type11 =null;

        Var expr14 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:273:5: ( ( 'new' type '()' ) | 'this' | 'super' | INT | ID | '(' expr ')' )
            int alt19=6;
            switch ( input.LA(1) ) {
            case 26:
                {
                alt19=1;
                }
                break;
            case 29:
                {
                alt19=2;
                }
                break;
            case 28:
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
                if (state.backtracking>0) {state.failed=true; return v;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:273:9: ( 'new' type '()' )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:273:9: ( 'new' type '()' )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:273:10: 'new' type '()'
                    {
                    match(input,26,FOLLOW_26_in_primary1031); if (state.failed) return v;

                    pushFollow(FOLLOW_type_in_primary1033);
                    type11=type();

                    state._fsp--;
                    if (state.failed) return v;

                    match(input,13,FOLLOW_13_in_primary1035); if (state.failed) return v;

                    }


                    if ( state.backtracking==0 ) {v = createObject(type11);}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:274:9: 'this'
                    {
                    match(input,29,FOLLOW_29_in_primary1048); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = locals.get("this");}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:275:9: 'super'
                    {
                    match(input,28,FOLLOW_28_in_primary1069); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = locals.get("super");}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:276:9: INT
                    {
                    INT12=(Token)match(input,INT,FOLLOW_INT_in_primary1090); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = loadI((INT12!=null?INT12.getText():null));}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:277:9: ID
                    {
                    ID13=(Token)match(input,ID,FOLLOW_ID_in_primary1114); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = locals.get((ID13!=null?ID13.getText():null));}

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:278:9: '(' expr ')'
                    {
                    match(input,12,FOLLOW_12_in_primary1139); if (state.failed) return v;

                    pushFollow(FOLLOW_expr_in_primary1141);
                    expr14=expr();

                    state._fsp--;
                    if (state.failed) return v;

                    match(input,14,FOLLOW_14_in_primary1143); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = expr14;}

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
        return v;
    }
    // $ANTLR end "primary"



    // $ANTLR start "type"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:285:1: type returns [Type type] : ( 'decimal' | 'int' | 'void' | ID );
    public final Type type() throws RecognitionException {
        Type type = null;


        Token ID15=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:286:5: ( 'decimal' | 'int' | 'void' | ID )
            int alt20=4;
            switch ( input.LA(1) ) {
            case 23:
                {
                alt20=1;
                }
                break;
            case 25:
                {
                alt20=2;
                }
                break;
            case 30:
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:286:9: 'decimal'
                    {
                    match(input,23,FOLLOW_23_in_type1180); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:287:9: 'int'
                    {
                    match(input,25,FOLLOW_25_in_type1192); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:288:9: 'void'
                    {
                    match(input,30,FOLLOW_30_in_type1208); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:289:9: ID
                    {
                    ID15=(Token)match(input,ID,FOLLOW_ID_in_type1223); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = resolveType((ID15!=null?ID15.getText():null));}

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


 

    public static final BitSet FOLLOW_classDefinition_in_compilationUnit51 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EOF_in_compilationUnit56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_classDefinition80 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_classDefinition82 = new BitSet(new long[]{0x0000000081000000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition84 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_classDefinition112 = new BitSet(new long[]{0x0000000042800020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition114 = new BitSet(new long[]{0x0000000142800020L});
    public static final BitSet FOLLOW_32_in_classDefinition117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_superClass158 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_superClass160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fieldDeclaration_in_classMember177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_fieldDeclaration197 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_fieldDeclaration199 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_fieldDeclaration202 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_fieldDeclaration206 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_fieldDeclaration210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration230 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration234 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration244 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration258 = new BitSet(new long[]{0x0000000042804020L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration260 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_methodDeclaration263 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameters332 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters336 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_formalParameters345 = new BitSet(new long[]{0x0000000042800020L});
    public static final BitSet FOLLOW_type_in_formalParameters349 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters353 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_31_in_block405 = new BitSet(new long[]{0x00000001FE901060L});
    public static final BitSet FOLLOW_statement_in_block407 = new BitSet(new long[]{0x00000001FE901060L});
    public static final BitSet FOLLOW_32_in_block410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement450 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_statement465 = new BitSet(new long[]{0x0000000034101060L});
    public static final BitSet FOLLOW_expr_in_statement467 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprStatement_in_statement483 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_statement499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration522 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_varDeclaration524 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_varDeclaration527 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_varDeclaration529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixexpr_in_exprStatement576 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_exprStatement588 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_exprStatement592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addexpr_in_expr635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multexpr_in_addexpr666 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_16_in_addexpr682 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr686 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_18_in_addexpr703 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr707 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_postfixExprValue_in_multexpr751 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_multexpr768 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_postfixExprValue_in_multexpr772 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_postfixexpr_in_postfixExprValue815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_postfixexpr843 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr876 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr880 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixexpr882 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr897 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr901 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixexpr903 = new BitSet(new long[]{0x0000000034005060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr907 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_postfixexpr909 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr924 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr928 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_expr_in_exprList982 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_exprList995 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_exprList999 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_26_in_primary1031 = new BitSet(new long[]{0x0000000042800020L});
    public static final BitSet FOLLOW_type_in_primary1033 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_primary1048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_primary1069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_primary1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary1139 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_primary1141 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_primary1143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_type1180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_type1192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_type1208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type1223 = new BitSet(new long[]{0x0000000000000002L});

}