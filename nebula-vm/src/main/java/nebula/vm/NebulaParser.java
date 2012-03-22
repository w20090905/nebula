// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-22 23:07:24

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
      protected Map<String,Var> locals = new HashMap<>();
      protected short maxLocals = 0;

      protected void pushLocal(Var var) {
        locals.put(var.name,var);
        var.reg = (short) (locals.size() - 1);
        maxLocals = maxLocals > (short) locals.size() ? maxLocals : (short) locals.size();
      } 
      
      protected Var v(String name) {
          Var var = locals.get(name);
    	    return var;
      };
      
      Stack<TempVar> tmps = new Stack<TempVar>();
      protected Var popTmp(Type type){
          TempVar v = null;
          for(TempVar tv:tmps){
              if(tv.applied){
                  v = tv;
                  v.type = type;
                  break;
              }
          }
          if(v ==null){
              int index = locals.size() + tmps.size() + 1;
              v = new TempVar("tmp"+index,(short)index,type);
              tmps.push(v);
          }
          v.applied = false;
          return v;
      }
      
      protected void releaseTmp(Var v){
    			assert !v.applied;
    			//Resolved
    			//        tmps.push(v);
    			v.applied = true;
      }
      
      protected void clearTmp(){
          for(Var v : tmps){
            if(!v.applied){
                info("TMP VAR NOT CLEANED!!!");
            }
          }
          short max = (short)(locals.size() + tmps.size() + 1);
          maxLocals = maxLocals > max ? maxLocals : max ;
          tmps.clear();
          info("\n");
      }
        
      protected Var pick(Var a,Var b){
          assert a.type.equals(b.type); 
          if(!a.applied)releaseTmp(a);
          if(!b.applied)releaseTmp(b);
          return popTmp(a.type);
      }
      
      
      protected ClassSymbol enterClass(String name,Type superType) {return null;};
      protected void exitClass() {;};
      
      protected MethodSymbol enterFunction(ClassSymbol clz, String name,Type returnType,List<Var> list) {
          MethodSymbol m = new MethodSymbol(clz, name);
          maxLocals = 0;
          
          Var varThis = new Var("this",clz);
          pushLocal(varThis);
          return m;
      };
      protected void exitFunction() {;};

      protected void defineField(ClassSymbol clz,String name,Type type){
          info("define field " + name + "\n");
      };
      
      protected Type resolveType(String name){return null;};
     
      protected Var add(Var a, Var b) {Var v=pick(a,b);info("ADD  : " + v.getName() +  " = " + a.getName() + " + " + b.getName() + ";\n");return v;};
      protected Var sub(Var a, Var b) {Var v=pick(a,b);info("SUB  : " + v.getName() +  " = " + a.getName() + " - " + b.getName() + ";\n");return v;};
      protected Var mul(Var a, Var b) {Var v=pick(a,b);info("MUL  : " + v.getName() +  " = " + a.getName() + " * " + b.getName() + ";\n");return v;};

      protected Var getField(Var obj,FieldSymbol field) {
          Var var = popTmp(BuiltInTypeSymbol.FLEX);
          info("GETF : " + var.getName() +  " = " + obj.getName() + "." + field.name + "\n");
          return var;
      };
      
      protected Var setField(Var obj, FieldSymbol field, Var v) {
          releaseTmp(v);
          info("SETF : " + obj.getName() + "." + field.name + " = " + v.getName() + "\n");  
          return v;
      };  
        
      protected Var invoke(Var obj,MethodSymbol method,List<Var> params) {
          releaseTmp(obj);
          String txtParams = "";
          for(Var v:params){
              txtParams += v.getName() + " ";
              releaseTmp(v);
          }
          Var var = popTmp(BuiltInTypeSymbol.FLEX);
          info("CALL : " + var.getName() + " =  " + obj.getName() + "." + method.definedClass.getName() + "_" +  method.name + "("  + txtParams +  ")\n"); 
          return var;
      };
        
      protected Var set(Var to,Var from){
            if(from.applied){
              to = move(to,from);        
            } else {
    		        info("HIDE : " + to.getName() + " = " + from.getName() + ";\n");
    		        from.name = to.getName();
    		        assert from.type.equals(to.type); 
    		        from.reg = to.reg;
    		        releaseTmp(from);
            }        
            return to;
      };
      protected Var set(String text,Type type,Var v){
            Var to = null;
            if(v.applied){
              to = new Var(text,type);
              pushLocal(to);
              to = move(to,v);
            } else {
    	        info("HIDE : " + text + " = " + v.getName() + ";\n");
    	        to = v;
    	        to.name = text;
    	        to.type = type;
    	        releaseTmp(v);
    	        pushLocal(to);        
            }
            return v;
      };
      
      protected Var move(Var to,Var from){
            info("MOVE : " + to.getName() +  " = " + from.getName() + ";\n");
            return to;
      }

      protected Var createObject(Type type){
            Var var = popTmp(type);
            info("NEWO : " + var.getName()  + " = new " + type.getName() + ";\n");
            return var;
      }
      
      protected Var loadI(String text){
          Var v=popTmp(BuiltInTypeSymbol.INT);
          info("LOADI: " + v.getName() + " = " + Integer.parseInt(text) + ";\n");
          return v;
      };
      
      protected Var top=null;
      
      protected void info(String str){
          if(str.length() > 2){
    		      String txtTemps = "";      
    		      for(TempVar v : tmps){
    		        txtTemps += "" + (v.applied?" ":v.reg) + " ";
    		      }
    		      str = "tmps["+ txtTemps +"]\t" + str;
          }
          System.out.print(str);
      }



    // $ANTLR start "compilationUnit"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:180:1: compilationUnit : ( classDefinition )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:181:5: ( ( classDefinition )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:181:9: ( classDefinition )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:181:9: ( classDefinition )+
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:181:11: classDefinition
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:185:1: classDefinition : 'class' ID ( superClass )? '{' ( classMember[clz] )+ '}' ;
    public final void classDefinition() throws RecognitionException {
        Token ID1=null;
        Type superClass2 =null;


        ClassSymbol clz = null;
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:187:5: ( 'class' ID ( superClass )? '{' ( classMember[clz] )+ '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:187:9: 'class' ID ( superClass )? '{' ( classMember[clz] )+ '}'
            {
            match(input,22,FOLLOW_22_in_classDefinition88); if (state.failed) return ;

            ID1=(Token)match(input,ID,FOLLOW_ID_in_classDefinition90); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:187:20: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==24) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:187:20: superClass
                    {
                    pushFollow(FOLLOW_superClass_in_classDefinition92);
                    superClass2=superClass();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {clz=enterClass((ID1!=null?ID1.getText():null),superClass2);}

            match(input,31,FOLLOW_31_in_classDefinition120); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:189:14: ( classMember[clz] )+
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:189:14: classMember[clz]
            	    {
            	    pushFollow(FOLLOW_classMember_in_classDefinition122);
            	    classMember(clz);

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


            match(input,32,FOLLOW_32_in_classDefinition126); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:193:1: superClass returns [Type type] : 'extends' ID ;
    public final Type superClass() throws RecognitionException {
        Type type = null;


        Token ID3=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:194:3: ( 'extends' ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:194:5: 'extends' ID
            {
            match(input,24,FOLLOW_24_in_superClass167); if (state.failed) return type;

            ID3=(Token)match(input,ID,FOLLOW_ID_in_superClass169); if (state.failed) return type;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:197:1: classMember[ClassSymbol clz] : ( fieldDeclaration[clz] | methodDeclaration[clz] );
    public final void classMember(ClassSymbol clz) throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:198:3: ( fieldDeclaration[clz] | methodDeclaration[clz] )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:198:7: fieldDeclaration[clz]
                    {
                    pushFollow(FOLLOW_fieldDeclaration_in_classMember189);
                    fieldDeclaration(clz);

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:199:9: methodDeclaration[clz]
                    {
                    pushFollow(FOLLOW_methodDeclaration_in_classMember201);
                    methodDeclaration(clz);

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:202:1: fieldDeclaration[ClassSymbol clz] : type ID ( '=' e= expr )? ';' ;
    public final void fieldDeclaration(ClassSymbol clz) throws RecognitionException {
        Token ID4=null;
        Var e =null;

        Type type5 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:203:3: ( type ID ( '=' e= expr )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:203:7: type ID ( '=' e= expr )? ';'
            {
            pushFollow(FOLLOW_type_in_fieldDeclaration219);
            type5=type();

            state._fsp--;
            if (state.failed) return ;

            ID4=(Token)match(input,ID,FOLLOW_ID_in_fieldDeclaration221); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:203:15: ( '=' e= expr )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==21) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:203:16: '=' e= expr
                    {
                    match(input,21,FOLLOW_21_in_fieldDeclaration224); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_fieldDeclaration228);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,20,FOLLOW_20_in_fieldDeclaration232); if (state.failed) return ;

            if ( state.backtracking==0 ) {defineField(clz, (ID4!=null?ID4.getText():null),type5);}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:206:1: methodDeclaration[ClassSymbol clz] : type name= ID ( '()' | ( '(' ( formalParameters )? ')' ) ) block ;
    public final void methodDeclaration(ClassSymbol clz) throws RecognitionException {
        Token name=null;
        Type type6 =null;

        List<Var> formalParameters7 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:207:3: ( type name= ID ( '()' | ( '(' ( formalParameters )? ')' ) ) block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:207:7: type name= ID ( '()' | ( '(' ( formalParameters )? ')' ) ) block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration252);
            type6=type();

            state._fsp--;
            if (state.failed) return ;

            name=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration256); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:208:7: ( '()' | ( '(' ( formalParameters )? ')' ) )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:208:8: '()'
                    {
                    match(input,13,FOLLOW_13_in_methodDeclaration266); if (state.failed) return ;

                    if ( state.backtracking==0 ) {enterFunction(clz, (name!=null?name.getText():null), type6, new ArrayList<Var>());}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:209:10: ( '(' ( formalParameters )? ')' )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:209:10: ( '(' ( formalParameters )? ')' )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:209:11: '(' ( formalParameters )? ')'
                    {
                    match(input,12,FOLLOW_12_in_methodDeclaration280); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:209:15: ( formalParameters )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==ID||LA6_0==23||LA6_0==25||LA6_0==30) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:209:15: formalParameters
                            {
                            pushFollow(FOLLOW_formalParameters_in_methodDeclaration282);
                            formalParameters7=formalParameters();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,14,FOLLOW_14_in_methodDeclaration285); if (state.failed) return ;

                    }


                    if ( state.backtracking==0 ) {enterFunction(clz, (name!=null?name.getText():null), type6, formalParameters7);}

                    }
                    break;

            }


            pushFollow(FOLLOW_block_in_methodDeclaration312);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:215:1: formalParameters returns [List<Var> list] : t= type id= ID ( ',' t= type id= ID )* ;
    public final List<Var> formalParameters() throws RecognitionException {
        List<Var> list = null;


        Token id=null;
        Type t =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:217:3: (t= type id= ID ( ',' t= type id= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:217:7: t= type id= ID ( ',' t= type id= ID )*
            {
            pushFollow(FOLLOW_type_in_formalParameters354);
            t=type();

            state._fsp--;
            if (state.failed) return list;

            id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters358); if (state.failed) return list;

            if ( state.backtracking==0 ) {list.add(new Var((id!=null?id.getText():null),t));}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:218:5: ( ',' t= type id= ID )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==17) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:218:7: ',' t= type id= ID
            	    {
            	    match(input,17,FOLLOW_17_in_formalParameters367); if (state.failed) return list;

            	    pushFollow(FOLLOW_type_in_formalParameters371);
            	    t=type();

            	    state._fsp--;
            	    if (state.failed) return list;

            	    id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters375); if (state.failed) return list;

            	    if ( state.backtracking==0 ) {list.add(new Var((id!=null?id.getText():null),t));}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:226:1: block : '{' ( statement )* '}' ;
    public final void block() throws RecognitionException {
        info("Block{\n");
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:229:5: ( '{' ( statement )* '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:229:9: '{' ( statement )* '}'
            {
            match(input,31,FOLLOW_31_in_block427); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:229:13: ( statement )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= ID && LA9_0 <= INT)||LA9_0==12||LA9_0==20||LA9_0==23||(LA9_0 >= 25 && LA9_0 <= 31)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:229:13: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block429);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            match(input,32,FOLLOW_32_in_block432); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:232:1: statement : ( block | varDeclaration ';' | 'return' ( expr )? ';' | exprStatement ';' | ';' );
    public final void statement() throws RecognitionException {
        tmps.clear();
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:235:5: ( block | varDeclaration ';' | 'return' ( expr )? ';' | exprStatement ';' | ';' )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:235:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement470);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:236:9: varDeclaration ';'
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement480);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,20,FOLLOW_20_in_statement483); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:237:9: 'return' ( expr )? ';'
                    {
                    match(input,27,FOLLOW_27_in_statement493); if (state.failed) return ;

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:237:18: ( expr )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( ((LA10_0 >= ID && LA10_0 <= INT)||LA10_0==12||LA10_0==26||(LA10_0 >= 28 && LA10_0 <= 29)) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:237:18: expr
                            {
                            pushFollow(FOLLOW_expr_in_statement495);
                            expr();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,20,FOLLOW_20_in_statement499); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:238:9: exprStatement ';'
                    {
                    pushFollow(FOLLOW_exprStatement_in_statement509);
                    exprStatement();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,20,FOLLOW_20_in_statement513); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:239:9: ';'
                    {
                    match(input,20,FOLLOW_20_in_statement523); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:242:1: varDeclaration : type ID ( '=' expr )? ;
    public final void varDeclaration() throws RecognitionException {
        Token ID8=null;
        Type type9 =null;

        Var expr10 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:243:5: ( type ID ( '=' expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:243:9: type ID ( '=' expr )?
            {
            pushFollow(FOLLOW_type_in_varDeclaration546);
            type9=type();

            state._fsp--;
            if (state.failed) return ;

            ID8=(Token)match(input,ID,FOLLOW_ID_in_varDeclaration548); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:243:17: ( '=' expr )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==21) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:243:18: '=' expr
                    {
                    match(input,21,FOLLOW_21_in_varDeclaration551); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_varDeclaration553);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:246:1: exprStatement options {backtrack=true; } : to= postfixexpr ( '=' from= expr )? ;
    public final void exprStatement() throws RecognitionException {
        NebulaParser.postfixexpr_return to =null;

        Var from =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:248:5: (to= postfixexpr ( '=' from= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:248:9: to= postfixexpr ( '=' from= expr )?
            {
            pushFollow(FOLLOW_postfixexpr_in_exprStatement600);
            to=postfixexpr();

            state._fsp--;
            if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:249:9: ( '=' from= expr )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==21) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:249:10: '=' from= expr
                    {
                    match(input,21,FOLLOW_21_in_exprStatement612); if (state.failed) return ;

                    pushFollow(FOLLOW_expr_in_exprStatement616);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:261:1: expr returns [Var v] : e= addexpr ;
    public final Var expr() throws RecognitionException {
        Var v = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:262:5: (e= addexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:262:9: e= addexpr
            {
            pushFollow(FOLLOW_addexpr_in_expr659);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:265:1: addexpr returns [Var v] : a= multexpr ( '+' b= multexpr | '-' c= multexpr )* ;
    public final Var addexpr() throws RecognitionException {
        Var v = null;


        Var a =null;

        Var b =null;

        Var c =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:266:5: (a= multexpr ( '+' b= multexpr | '-' c= multexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:266:9: a= multexpr ( '+' b= multexpr | '-' c= multexpr )*
            {
            pushFollow(FOLLOW_multexpr_in_addexpr690);
            a=multexpr();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v = a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:267:9: ( '+' b= multexpr | '-' c= multexpr )*
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:267:13: '+' b= multexpr
            	    {
            	    match(input,16,FOLLOW_16_in_addexpr706); if (state.failed) return v;

            	    pushFollow(FOLLOW_multexpr_in_addexpr710);
            	    b=multexpr();

            	    state._fsp--;
            	    if (state.failed) return v;

            	    if ( state.backtracking==0 ) {v=add(v,b);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:268:13: '-' c= multexpr
            	    {
            	    match(input,18,FOLLOW_18_in_addexpr727); if (state.failed) return v;

            	    pushFollow(FOLLOW_multexpr_in_addexpr731);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:272:1: multexpr returns [Var v] : a= postfixExprValue ( '*' b= postfixExprValue )* ;
    public final Var multexpr() throws RecognitionException {
        Var v = null;


        Var a =null;

        Var b =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:273:5: (a= postfixExprValue ( '*' b= postfixExprValue )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:273:9: a= postfixExprValue ( '*' b= postfixExprValue )*
            {
            pushFollow(FOLLOW_postfixExprValue_in_multexpr775);
            a=postfixExprValue();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v=a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:274:9: ( '*' b= postfixExprValue )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==15) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:274:13: '*' b= postfixExprValue
            	    {
            	    match(input,15,FOLLOW_15_in_multexpr792); if (state.failed) return v;

            	    pushFollow(FOLLOW_postfixExprValue_in_multexpr796);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:278:1: postfixExprValue returns [Var v] : p= postfixexpr ;
    public final Var postfixExprValue() throws RecognitionException {
        Var v = null;


        NebulaParser.postfixexpr_return p =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:279:5: (p= postfixexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:279:9: p= postfixexpr
            {
            pushFollow(FOLLOW_postfixexpr_in_postfixExprValue839);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:282:1: postfixexpr returns [Var v,FieldSymbol field] : (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )* ;
    public final NebulaParser.postfixexpr_return postfixexpr() throws RecognitionException {
        NebulaParser.postfixexpr_return retval = new NebulaParser.postfixexpr_return();
        retval.start = input.LT(1);


        Token mID=null;
        Token fID=null;
        Var p =null;

        List<Var> params =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:283:5: ( (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:283:9: (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:283:9: (p= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:283:10: p= primary
            {
            pushFollow(FOLLOW_primary_in_postfixexpr867);
            p=primary();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {retval.v = p;}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:284:9: ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )*
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:285:12: '.' mID= ID '()'
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr900); if (state.failed) return retval;

            	    mID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr904); if (state.failed) return retval;

            	    match(input,13,FOLLOW_13_in_postfixexpr906); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	    	              if(retval.field!=null){retval.v =getField(retval.v,retval.field);retval.field =null;} 
            	    	              if(retval.v.type == BuiltInTypeSymbol.FLEX){
            	    	                  retval.v.type = BuiltInTypeSymbol.FLEXCLASS;
            	    	              }
            	    	              MethodSymbol m = new MethodSymbol((ClassSymbol)retval.v.type,(mID!=null?mID.getText():null));  
            	    	              retval.v = invoke(retval.v,m,new ArrayList<Var>());
            	    	           }

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:293:12: '.' mID= ID '(' params= exprList ')'
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr921); if (state.failed) return retval;

            	    mID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr925); if (state.failed) return retval;

            	    match(input,12,FOLLOW_12_in_postfixexpr927); if (state.failed) return retval;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr931);
            	    params=exprList();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    match(input,14,FOLLOW_14_in_postfixexpr933); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    if(retval.field!=null){retval.v =getField(retval.v,retval.field);retval.field =null;} 
            	                    MethodSymbol m = new MethodSymbol((ClassSymbol)retval.v.type,(mID!=null?mID.getText():null));
            	                    retval.v = invoke(retval.v,m,params);
            	                 }

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:298:12: '.' fID= ID
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr948); if (state.failed) return retval;

            	    fID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr952); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) { 
            	                    if(retval.field!=null){retval.v =getField(retval.v,retval.field);retval.field =null;} 
            	                    retval.field =new FieldSymbol((ClassSymbol)retval.v.type,(fID!=null?fID.getText():null)); }

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:304:1: exprList returns [List<Var> list] : (e= expr ( ',' e= expr )* |);
    public final List<Var> exprList() throws RecognitionException {
        List<Var> list = null;


        Var e =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:306:5: (e= expr ( ',' e= expr )* |)
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:306:9: e= expr ( ',' e= expr )*
                    {
                    pushFollow(FOLLOW_expr_in_exprList1006);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return list;

                    if ( state.backtracking==0 ) {if(e.applied)e=move(popTmp(e.type),e); list.add(e);}

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:307:9: ( ',' e= expr )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==17) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:307:10: ',' e= expr
                    	    {
                    	    match(input,17,FOLLOW_17_in_exprList1019); if (state.failed) return list;

                    	    pushFollow(FOLLOW_expr_in_exprList1023);
                    	    e=expr();

                    	    state._fsp--;
                    	    if (state.failed) return list;

                    	    if ( state.backtracking==0 ) {if(e.applied)e=move(popTmp(e.type),e);list.add(e);}

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:308:5: 
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:310:1: primary returns [Var v] : ( ( 'new' type '()' ) | 'this' | 'super' | INT | ID | '(' expr ')' );
    public final Var primary() throws RecognitionException {
        Var v = null;


        Token INT12=null;
        Token ID13=null;
        Type type11 =null;

        Var expr14 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:311:5: ( ( 'new' type '()' ) | 'this' | 'super' | INT | ID | '(' expr ')' )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:311:9: ( 'new' type '()' )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:311:9: ( 'new' type '()' )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:311:10: 'new' type '()'
                    {
                    match(input,26,FOLLOW_26_in_primary1055); if (state.failed) return v;

                    pushFollow(FOLLOW_type_in_primary1057);
                    type11=type();

                    state._fsp--;
                    if (state.failed) return v;

                    match(input,13,FOLLOW_13_in_primary1059); if (state.failed) return v;

                    }


                    if ( state.backtracking==0 ) {v = createObject(type11);}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:312:9: 'this'
                    {
                    match(input,29,FOLLOW_29_in_primary1072); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = v("this");}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:313:9: 'super'
                    {
                    match(input,28,FOLLOW_28_in_primary1093); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = v("super");}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:314:9: INT
                    {
                    INT12=(Token)match(input,INT,FOLLOW_INT_in_primary1114); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = loadI((INT12!=null?INT12.getText():null));}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:315:9: ID
                    {
                    ID13=(Token)match(input,ID,FOLLOW_ID_in_primary1138); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = v((ID13!=null?ID13.getText():null));}

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:316:9: '(' expr ')'
                    {
                    match(input,12,FOLLOW_12_in_primary1163); if (state.failed) return v;

                    pushFollow(FOLLOW_expr_in_primary1165);
                    expr14=expr();

                    state._fsp--;
                    if (state.failed) return v;

                    match(input,14,FOLLOW_14_in_primary1167); if (state.failed) return v;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:323:1: type returns [Type type] : ( 'decimal' | 'int' | 'void' | ID );
    public final Type type() throws RecognitionException {
        Type type = null;


        Token ID15=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:324:5: ( 'decimal' | 'int' | 'void' | ID )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:324:9: 'decimal'
                    {
                    match(input,23,FOLLOW_23_in_type1204); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:325:9: 'int'
                    {
                    match(input,25,FOLLOW_25_in_type1216); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:326:9: 'void'
                    {
                    match(input,30,FOLLOW_30_in_type1232); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:327:9: ID
                    {
                    ID15=(Token)match(input,ID,FOLLOW_ID_in_type1247); if (state.failed) return type;

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
    public static final BitSet FOLLOW_22_in_classDefinition88 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_classDefinition90 = new BitSet(new long[]{0x0000000081000000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition92 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_classDefinition120 = new BitSet(new long[]{0x0000000042800020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition122 = new BitSet(new long[]{0x0000000142800020L});
    public static final BitSet FOLLOW_32_in_classDefinition126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_superClass167 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_superClass169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fieldDeclaration_in_classMember189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_fieldDeclaration219 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_fieldDeclaration221 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_fieldDeclaration224 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_fieldDeclaration228 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_fieldDeclaration232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration252 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration256 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration266 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration280 = new BitSet(new long[]{0x0000000042804020L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration282 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_methodDeclaration285 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameters354 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters358 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_formalParameters367 = new BitSet(new long[]{0x0000000042800020L});
    public static final BitSet FOLLOW_type_in_formalParameters371 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters375 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_31_in_block427 = new BitSet(new long[]{0x00000001FE901060L});
    public static final BitSet FOLLOW_statement_in_block429 = new BitSet(new long[]{0x00000001FE901060L});
    public static final BitSet FOLLOW_32_in_block432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement480 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_statement493 = new BitSet(new long[]{0x0000000034101060L});
    public static final BitSet FOLLOW_expr_in_statement495 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprStatement_in_statement509 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_statement523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration546 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_varDeclaration548 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_varDeclaration551 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_varDeclaration553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixexpr_in_exprStatement600 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_exprStatement612 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_exprStatement616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addexpr_in_expr659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multexpr_in_addexpr690 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_16_in_addexpr706 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr710 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_18_in_addexpr727 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr731 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_postfixExprValue_in_multexpr775 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_multexpr792 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_postfixExprValue_in_multexpr796 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_postfixexpr_in_postfixExprValue839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_postfixexpr867 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr900 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr904 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixexpr906 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr921 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr925 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixexpr927 = new BitSet(new long[]{0x0000000034005060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr931 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_postfixexpr933 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr948 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr952 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_expr_in_exprList1006 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_exprList1019 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_exprList1023 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_26_in_primary1055 = new BitSet(new long[]{0x0000000042800020L});
    public static final BitSet FOLLOW_type_in_primary1057 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_primary1072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_primary1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_primary1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary1163 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_primary1165 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_primary1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_type1204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_type1216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_type1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type1247 = new BitSet(new long[]{0x0000000000000002L});

}