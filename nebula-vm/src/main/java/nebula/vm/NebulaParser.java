// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-25 21:58:21

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

      
      // ************** START:members **************
      protected ClassSymbol resolveType(String name) {
        ClassSymbol clz = new ClassSymbol(name);
        return clz;
      };

      protected ClassSymbol enterClass(String name, Type superType) {
        ClassSymbol v = new ClassSymbol(name);
        return v;/* TODO add super class support */
      };

      protected ClassSymbol exitClass(ClassSymbol clz) {
        return clz;
      };

      protected FieldSymbol defineField(ClassSymbol clz, String name, Type type) {
        FieldSymbol f = new FieldSymbol(clz, name, type);
        info("FIELD : " + f.name + "\n");
        return f;
      };

      protected MethodSymbol enterMethod(ClassSymbol clz, String name, Type returnType, List<Var> params) {
        MethodSymbol m = new MethodSymbol(clz, name, returnType);
        this.maxLocals = 0;

        pushLocal("this", clz);

        for (Var v : params) {
          pushLocal(v.name, v.type);
        }

        String str = "";
        for (Var v : params) {
          str += v.name + " ";
        }
        info("FUNC  : " + name + "() {\n");

        return m;
      };

      protected MethodSymbol exitMethod(MethodSymbol method) {
        info("}\n");
        return method;
      };

      protected Var opIAdd(Var a, Var b) {
        if (!a.applied) resolveTemp(a);
        if (!b.applied) resolveTemp(b);
        TempVar v = popTmp(a.type);

        info("IADD  : " + v.getName() + " = " + a.getName() + " + " + b.getName() + ";\n");

        return v;
      };

      protected Var opISub(Var a, Var b) {
        if (!a.applied) resolveTemp(a);
        if (!b.applied) resolveTemp(b);
        TempVar v = popTmp(a.type);

        info("ISUB  : " + v.getName() + " = " + a.getName() + " - " + b.getName() + ";\n");
        return v;
      };

      protected Var opIMul(Var a, Var b) {
        if (!a.applied) resolveTemp(a);
        if (!b.applied) resolveTemp(b);
        TempVar v = popTmp(a.type);

        info("IMUL  : " + v.getName() + " = " + a.getName() + " * " + b.getName() + ";\n");
        return v;
      };

      protected Var opFLoad(Var obj, FieldSymbol field) {
        if (!obj.applied) resolveTemp(obj);
        TempVar v = popTmp(BuiltInTypeSymbol.FLEX);

        info("FLOAD : " + v.getName() + " = " + obj.getName() + "." + field.name + "\n");
        return v;
      };

      protected Var opFStore(Var obj, FieldSymbol field, Var v) {
        if (!obj.applied) resolveTemp(obj);
        if (!v.applied) resolveTemp(v);

        info("FSTORE: " + obj.getName() + "." + field.name + " = " + v.getName() + "\n");
        return v;
      };
      protected Var opInvoke(Var obj, MethodSymbol method, List<Var> params) {
        if (!obj.applied) resolveTemp(obj);
        for (Var vp : params) {
          if (!vp.applied) resolveTemp(vp);
        }
        TempVar v = popTmp(BuiltInTypeSymbol.FLEX);
        if (params.size() > 0) {
          TempVar ov = (TempVar)params.get(0);
          assert v.reg == ov.reg;
          v.applied = true;
        }

        String txtParams = "";
        for (Var vp : params) {
          txtParams += vp.getName() + " ";
        }
        String str = " = " + obj.getName() + "." + method.definedClass.getName() + "_" + method.name + "(" + txtParams
            + ");\n";
        info("CALL  : " + v.getName() + str);
        return v;
      };

      protected Var opMove(Var to, Var from) {
        if (!from.applied) resolveTemp(from);

        info("MOVE  : " + to.getName() + " = " + from.getName() + ";\n");

        return to;
      }

      protected Var opNew(ClassSymbol clz) {
        TempVar v = popTmp(clz);

        info("STRUCT: " + v.getName() + " = new " + clz.getName() + ";\n");
        return v;
      }

      protected Var opILoad(String text) {
        TempVar v = popTmp(BuiltInTypeSymbol.INT);

        info("ICONST: " + v.getName() + " = " + Integer.parseInt(text) + ";\n");
        return v;
      };
        
      protected Var opReturn(Var v) {
        if(v==null){
            info("RET   : " + v.getName() + ";\n");
        }else{
            info("RET   : ;\n");
        }
        return v;
      };

      // ************** END :members **************

      // ************** START: Deal local **************

      protected void resolveTemp(Var v) {
        assert !v.applied;
        v.applied = true;
      }

      protected void resolveTemp(Var v, short reg) {
        assert !v.applied || v.reg == reg;
        Var to = null;
        for(Var tv : locals.values()){
            if(tv.reg == reg){
                to = tv;
                break;
            }
        }
        info("HIDE  : " + to.getName() + " = " + v.getName() + ";\n");
        v.applied = true;
      }

      private Map<String, Var> locals = new HashMap<>();
      private List<Var> params = null;
      protected int maxLocals = 0;

      protected Var pushLocal(String name, Type type) {
        Var var = new Var(name, type, locals.size());
        locals.put(var.name, var);

        maxLocals = maxLocals > (short) locals.size() ? maxLocals : (short) locals.size();
        return var;
      }

      protected Var v(String name) {
        Var var = locals.get(name);
        return var;
      };

      Stack<TempVar> tmps = new Stack<TempVar>();

      protected TempVar popTmp(Type type) {
        TempVar nv = null;
        for (int i = 0; i < tmps.size(); i++) {
          TempVar ov = tmps.get(i);
          if (ov.applied) {
            nv = new TempVar("tmp" + ov.reg, ov.reg, type);
            tmps.set(i, nv);
            break;
          }
        }
        if (nv == null) {
          int index = locals.size() + tmps.size();
          nv = new TempVar("tmp" + index, (short) index, type);
          tmps.add(nv);
        }
        nv.applied = false;
        return nv;
      }

      protected void clearTmp() {
    		maxLocals =  Math.max(maxLocals,locals.size());
    		if(tmps.size()>0) maxLocals =  Math.max(maxLocals,tmps.get(tmps.size()-1).reg+1);
    		
    		tmps.clear();
      }

      protected void info(String str) {
        if (str.charAt(str.length() - 1) == '\n') {
          String txtTemps = "";
          for (TempVar v : tmps) {
            txtTemps += "" + (v.applied ? " " : v.reg) + " ";
          }
          str = "|" + txtTemps + "|" + "\t\t" + str;

        }
        System.out.print(str);
      }
      // ************** END : Deal local **************



    // $ANTLR start "compilationUnit"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:234:1: compilationUnit : ( classDefinition )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:235:5: ( ( classDefinition )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:235:9: ( classDefinition )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:235:9: ( classDefinition )+
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:235:11: classDefinition
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:239:1: classDefinition returns [ClassSymbol clz] : 'class' ID ( superClass )? '{' ( classMember[clz] )+ '}' ;
    public final ClassSymbol classDefinition() throws RecognitionException {
        ClassSymbol clz = null;


        Token ID1=null;
        Type superClass2 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:240:5: ( 'class' ID ( superClass )? '{' ( classMember[clz] )+ '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:240:9: 'class' ID ( superClass )? '{' ( classMember[clz] )+ '}'
            {
            match(input,22,FOLLOW_22_in_classDefinition83); if (state.failed) return clz;

            ID1=(Token)match(input,ID,FOLLOW_ID_in_classDefinition85); if (state.failed) return clz;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:240:20: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==24) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:240:20: superClass
                    {
                    pushFollow(FOLLOW_superClass_in_classDefinition87);
                    superClass2=superClass();

                    state._fsp--;
                    if (state.failed) return clz;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {clz=enterClass((ID1!=null?ID1.getText():null),superClass2);}

            match(input,31,FOLLOW_31_in_classDefinition115); if (state.failed) return clz;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:242:14: ( classMember[clz] )+
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:242:14: classMember[clz]
            	    {
            	    pushFollow(FOLLOW_classMember_in_classDefinition117);
            	    classMember(clz);

            	    state._fsp--;
            	    if (state.failed) return clz;

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
            	    if (state.backtracking>0) {state.failed=true; return clz;}
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            match(input,32,FOLLOW_32_in_classDefinition121); if (state.failed) return clz;

            if ( state.backtracking==0 ) {clz=exitClass(clz);}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return clz;
    }
    // $ANTLR end "classDefinition"



    // $ANTLR start "superClass"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:246:1: superClass returns [Type type] : 'extends' ID ;
    public final Type superClass() throws RecognitionException {
        Type type = null;


        Token ID3=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:247:3: ( 'extends' ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:247:5: 'extends' ID
            {
            match(input,24,FOLLOW_24_in_superClass162); if (state.failed) return type;

            ID3=(Token)match(input,ID,FOLLOW_ID_in_superClass164); if (state.failed) return type;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:250:1: classMember[ClassSymbol clz] : ( fieldDeclaration[clz] | methodDeclaration[clz] );
    public final void classMember(ClassSymbol clz) throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:251:3: ( fieldDeclaration[clz] | methodDeclaration[clz] )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:251:7: fieldDeclaration[clz]
                    {
                    pushFollow(FOLLOW_fieldDeclaration_in_classMember184);
                    fieldDeclaration(clz);

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:252:9: methodDeclaration[clz]
                    {
                    pushFollow(FOLLOW_methodDeclaration_in_classMember196);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:255:1: fieldDeclaration[ClassSymbol clz] returns [FieldSymbol field] : type ID ( '=' e= expr )? ';' ;
    public final FieldSymbol fieldDeclaration(ClassSymbol clz) throws RecognitionException {
        FieldSymbol field = null;


        Token ID4=null;
        Var e =null;

        Type type5 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:256:3: ( type ID ( '=' e= expr )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:256:7: type ID ( '=' e= expr )? ';'
            {
            pushFollow(FOLLOW_type_in_fieldDeclaration217);
            type5=type();

            state._fsp--;
            if (state.failed) return field;

            ID4=(Token)match(input,ID,FOLLOW_ID_in_fieldDeclaration219); if (state.failed) return field;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:256:15: ( '=' e= expr )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==21) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:256:16: '=' e= expr
                    {
                    match(input,21,FOLLOW_21_in_fieldDeclaration222); if (state.failed) return field;

                    pushFollow(FOLLOW_expr_in_fieldDeclaration226);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return field;

                    }
                    break;

            }


            match(input,20,FOLLOW_20_in_fieldDeclaration230); if (state.failed) return field;

            if ( state.backtracking==0 ) {field = defineField(clz, (ID4!=null?ID4.getText():null),type5);}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return field;
    }
    // $ANTLR end "fieldDeclaration"



    // $ANTLR start "methodDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:259:1: methodDeclaration[ClassSymbol clz] returns [MethodSymbol m] : type name= ID ( '()' | ( '(' formalParameters ')' ) ) block ;
    public final MethodSymbol methodDeclaration(ClassSymbol clz) throws RecognitionException {
        MethodSymbol m = null;


        Token name=null;
        Type type6 =null;

        List<Var> formalParameters7 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:260:3: ( type name= ID ( '()' | ( '(' formalParameters ')' ) ) block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:260:7: type name= ID ( '()' | ( '(' formalParameters ')' ) ) block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration253);
            type6=type();

            state._fsp--;
            if (state.failed) return m;

            name=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration257); if (state.failed) return m;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:261:7: ( '()' | ( '(' formalParameters ')' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==13) ) {
                alt6=1;
            }
            else if ( (LA6_0==12) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return m;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:261:8: '()'
                    {
                    match(input,13,FOLLOW_13_in_methodDeclaration267); if (state.failed) return m;

                    if ( state.backtracking==0 ) {m=enterMethod(clz, (name!=null?name.getText():null), type6, new ArrayList<Var>());}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:262:10: ( '(' formalParameters ')' )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:262:10: ( '(' formalParameters ')' )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:262:11: '(' formalParameters ')'
                    {
                    match(input,12,FOLLOW_12_in_methodDeclaration281); if (state.failed) return m;

                    pushFollow(FOLLOW_formalParameters_in_methodDeclaration283);
                    formalParameters7=formalParameters();

                    state._fsp--;
                    if (state.failed) return m;

                    match(input,14,FOLLOW_14_in_methodDeclaration285); if (state.failed) return m;

                    }


                    if ( state.backtracking==0 ) {m=enterMethod(clz, (name!=null?name.getText():null), type6, formalParameters7);}

                    }
                    break;

            }


            pushFollow(FOLLOW_block_in_methodDeclaration312);
            block();

            state._fsp--;
            if (state.failed) return m;

            if ( state.backtracking==0 ) {m=exitMethod(m);}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return m;
    }
    // $ANTLR end "methodDeclaration"



    // $ANTLR start "formalParameters"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:268:1: formalParameters returns [List<Var> list] : t= type id= ID ( ',' t= type id= ID )* ;
    public final List<Var> formalParameters() throws RecognitionException {
        List<Var> list = null;


        Token id=null;
        Type t =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:270:3: (t= type id= ID ( ',' t= type id= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:270:7: t= type id= ID ( ',' t= type id= ID )*
            {
            pushFollow(FOLLOW_type_in_formalParameters354);
            t=type();

            state._fsp--;
            if (state.failed) return list;

            id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters358); if (state.failed) return list;

            if ( state.backtracking==0 ) {list.add(new Var((id!=null?id.getText():null),t,0));}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:271:5: ( ',' t= type id= ID )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==17) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:271:7: ',' t= type id= ID
            	    {
            	    match(input,17,FOLLOW_17_in_formalParameters367); if (state.failed) return list;

            	    pushFollow(FOLLOW_type_in_formalParameters371);
            	    t=type();

            	    state._fsp--;
            	    if (state.failed) return list;

            	    id=(Token)match(input,ID,FOLLOW_ID_in_formalParameters375); if (state.failed) return list;

            	    if ( state.backtracking==0 ) {list.add(new Var((id!=null?id.getText():null),t,0));}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:279:1: block : '{' ( statement )* '}' ;
    public final void block() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:280:5: ( '{' ( statement )* '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:280:9: '{' ( statement )* '}'
            {
            match(input,31,FOLLOW_31_in_block411); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:280:13: ( statement )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= ID && LA8_0 <= INT)||LA8_0==12||LA8_0==20||LA8_0==23||(LA8_0 >= 25 && LA8_0 <= 31)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:280:13: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block413);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            match(input,32,FOLLOW_32_in_block416); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:283:1: statement : ( block | varDeclaration ';' | returnStatement ';' | exprStatement ';' | ';' );
    public final void statement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:285:5: ( block | varDeclaration ';' | returnStatement ';' | exprStatement ';' | ';' )
            int alt9=5;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt9=1;
                }
                break;
            case 23:
            case 25:
            case 30:
                {
                alt9=2;
                }
                break;
            case ID:
                {
                int LA9_3 = input.LA(2);

                if ( (LA9_3==ID) ) {
                    alt9=2;
                }
                else if ( ((LA9_3 >= 19 && LA9_3 <= 21)) ) {
                    alt9=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 3, input);

                    throw nvae;

                }
                }
                break;
            case 27:
                {
                alt9=3;
                }
                break;
            case INT:
            case 12:
            case 26:
            case 28:
            case 29:
                {
                alt9=4;
                }
                break;
            case 20:
                {
                alt9=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:285:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement446);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:286:9: varDeclaration ';'
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement456);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,20,FOLLOW_20_in_statement459); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:287:9: returnStatement ';'
                    {
                    pushFollow(FOLLOW_returnStatement_in_statement469);
                    returnStatement();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,20,FOLLOW_20_in_statement471); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:288:9: exprStatement ';'
                    {
                    pushFollow(FOLLOW_exprStatement_in_statement481);
                    exprStatement();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,20,FOLLOW_20_in_statement485); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:289:9: ';'
                    {
                    match(input,20,FOLLOW_20_in_statement495); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:292:1: varDeclaration returns [Var v] : type ID ( '=' from= expr )? ;
    public final Var varDeclaration() throws RecognitionException {
        Var v = null;


        Token ID8=null;
        Var from =null;

        Type type9 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:293:5: ( type ID ( '=' from= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:293:9: type ID ( '=' from= expr )?
            {
            pushFollow(FOLLOW_type_in_varDeclaration521);
            type9=type();

            state._fsp--;
            if (state.failed) return v;

            ID8=(Token)match(input,ID,FOLLOW_ID_in_varDeclaration523); if (state.failed) return v;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:293:17: ( '=' from= expr )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==21) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:293:18: '=' from= expr
                    {
                    match(input,21,FOLLOW_21_in_varDeclaration526); if (state.failed) return v;

                    pushFollow(FOLLOW_expr_in_varDeclaration530);
                    from=expr();

                    state._fsp--;
                    if (state.failed) return v;

                    }
                    break;

            }


            if ( state.backtracking==0 ) { 
                        v = pushLocal((ID8!=null?ID8.getText():null),type9);
                        if(from!=null) {
                            if(!from.applied ){
            			              resolveTemp(from, v.reg);
            			          } else {
            			              if(v.reg != from.reg)
            			                  v = opMove(v, from);
            			              else 
            			                  resolveTemp(from, v.reg);
            			          }
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
        return v;
    }
    // $ANTLR end "varDeclaration"



    // $ANTLR start "returnStatement"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:309:1: returnStatement : 'return' (v= expr )? ;
    public final void returnStatement() throws RecognitionException {
        Var v =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:310:5: ( 'return' (v= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:310:6: 'return' (v= expr )?
            {
            match(input,27,FOLLOW_27_in_returnStatement566); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:310:16: (v= expr )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0 >= ID && LA11_0 <= INT)||LA11_0==12||LA11_0==26||(LA11_0 >= 28 && LA11_0 <= 29)) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:310:16: v= expr
                    {
                    pushFollow(FOLLOW_expr_in_returnStatement570);
                    v=expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {opReturn(v);}

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
    // $ANTLR end "returnStatement"



    // $ANTLR start "exprStatement"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:313:1: exprStatement returns [Var v] options {backtrack=true; } : to= postfixexpr ( '=' from= expr )? ;
    public final Var exprStatement() throws RecognitionException {
        Var v = null;


        NebulaParser.postfixexpr_return to =null;

        Var from =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:315:5: (to= postfixexpr ( '=' from= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:315:9: to= postfixexpr ( '=' from= expr )?
            {
            pushFollow(FOLLOW_postfixexpr_in_exprStatement616);
            to=postfixexpr();

            state._fsp--;
            if (state.failed) return v;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:316:9: ( '=' from= expr )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==21) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:316:10: '=' from= expr
                    {
                    match(input,21,FOLLOW_21_in_exprStatement628); if (state.failed) return v;

                    pushFollow(FOLLOW_expr_in_exprStatement632);
                    from=expr();

                    state._fsp--;
                    if (state.failed) return v;

                    }
                    break;

            }


            if ( state.backtracking==0 ) { 
                      if(to.field != null){
                          assert from!=null;
                          v = opFStore(to.v,to.field,from);              
                      } else if( from == null ){
                          resolveTemp(to.v);               
                          v = to.v;     
                      } else if(!from.applied ){
                          resolveTemp(from, to.v.reg);
                          v = to.v;
                      } else {
                          v = opMove(to.v, from);              
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
        return v;
    }
    // $ANTLR end "exprStatement"



    // $ANTLR start "expr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:333:1: expr returns [Var v] : e= addexpr ;
    public final Var expr() throws RecognitionException {
        Var v = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:334:5: (e= addexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:334:9: e= addexpr
            {
            pushFollow(FOLLOW_addexpr_in_expr675);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:337:1: addexpr returns [Var v] : a= multexpr ( '+' b= multexpr | '-' c= multexpr )* ;
    public final Var addexpr() throws RecognitionException {
        Var v = null;


        Var a =null;

        Var b =null;

        Var c =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:338:5: (a= multexpr ( '+' b= multexpr | '-' c= multexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:338:9: a= multexpr ( '+' b= multexpr | '-' c= multexpr )*
            {
            pushFollow(FOLLOW_multexpr_in_addexpr706);
            a=multexpr();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v = a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:339:9: ( '+' b= multexpr | '-' c= multexpr )*
            loop13:
            do {
                int alt13=3;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==16) ) {
                    alt13=1;
                }
                else if ( (LA13_0==18) ) {
                    alt13=2;
                }


                switch (alt13) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:339:13: '+' b= multexpr
            	    {
            	    match(input,16,FOLLOW_16_in_addexpr722); if (state.failed) return v;

            	    pushFollow(FOLLOW_multexpr_in_addexpr726);
            	    b=multexpr();

            	    state._fsp--;
            	    if (state.failed) return v;

            	    if ( state.backtracking==0 ) {v=opIAdd(v,b);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:340:13: '-' c= multexpr
            	    {
            	    match(input,18,FOLLOW_18_in_addexpr743); if (state.failed) return v;

            	    pushFollow(FOLLOW_multexpr_in_addexpr747);
            	    c=multexpr();

            	    state._fsp--;
            	    if (state.failed) return v;

            	    if ( state.backtracking==0 ) {v=opISub(v,c);}

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
        return v;
    }
    // $ANTLR end "addexpr"



    // $ANTLR start "multexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:344:1: multexpr returns [Var v] : a= postfixExprValue ( '*' b= postfixExprValue )* ;
    public final Var multexpr() throws RecognitionException {
        Var v = null;


        Var a =null;

        Var b =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:345:5: (a= postfixExprValue ( '*' b= postfixExprValue )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:345:9: a= postfixExprValue ( '*' b= postfixExprValue )*
            {
            pushFollow(FOLLOW_postfixExprValue_in_multexpr791);
            a=postfixExprValue();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v=a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:346:9: ( '*' b= postfixExprValue )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==15) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:346:13: '*' b= postfixExprValue
            	    {
            	    match(input,15,FOLLOW_15_in_multexpr808); if (state.failed) return v;

            	    pushFollow(FOLLOW_postfixExprValue_in_multexpr812);
            	    b=postfixExprValue();

            	    state._fsp--;
            	    if (state.failed) return v;

            	    if ( state.backtracking==0 ) {v=opIMul(v,b);}

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
    // $ANTLR end "multexpr"



    // $ANTLR start "postfixExprValue"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:350:1: postfixExprValue returns [Var v] : p= postfixexpr ;
    public final Var postfixExprValue() throws RecognitionException {
        Var v = null;


        NebulaParser.postfixexpr_return p =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:351:5: (p= postfixexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:351:9: p= postfixexpr
            {
            pushFollow(FOLLOW_postfixexpr_in_postfixExprValue855);
            p=postfixexpr();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v=(p!=null?p.v:null); if((p!=null?p.field:null)!=null){v=opFLoad(v,(p!=null?p.field:null));} }

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:354:1: postfixexpr returns [Var v,FieldSymbol field] : (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )* ;
    public final NebulaParser.postfixexpr_return postfixexpr() throws RecognitionException {
        NebulaParser.postfixexpr_return retval = new NebulaParser.postfixexpr_return();
        retval.start = input.LT(1);


        Token mID=null;
        Token fID=null;
        Var p =null;

        List<Var> params =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:355:5: ( (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:355:9: (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:355:9: (p= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:355:10: p= primary
            {
            pushFollow(FOLLOW_primary_in_postfixexpr883);
            p=primary();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {retval.v = p;}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:356:9: ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )*
            loop15:
            do {
                int alt15=4;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==19) ) {
                    int LA15_2 = input.LA(2);

                    if ( (LA15_2==ID) ) {
                        switch ( input.LA(3) ) {
                        case 13:
                            {
                            alt15=1;
                            }
                            break;
                        case 12:
                            {
                            alt15=2;
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
                            alt15=3;
                            }
                            break;

                        }

                    }


                }


                switch (alt15) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:357:12: '.' mID= ID '()'
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr916); if (state.failed) return retval;

            	    mID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr920); if (state.failed) return retval;

            	    match(input,13,FOLLOW_13_in_postfixexpr922); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	    	              if(retval.field!=null){retval.v =opFLoad(retval.v,retval.field);retval.field =null;} 
            	    	              if(retval.v.type == BuiltInTypeSymbol.FLEX){
            	    	                  retval.v.type = BuiltInTypeSymbol.FLEXCLASS;
            	    	              }
            	    	              MethodSymbol m = new MethodSymbol((ClassSymbol)retval.v.type,(mID!=null?mID.getText():null),BuiltInTypeSymbol.FLEX);  
            	    	              retval.v = opInvoke(retval.v,m,new ArrayList<Var>());
            	    	           }

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:365:12: '.' mID= ID '(' params= exprList ')'
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr937); if (state.failed) return retval;

            	    mID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr941); if (state.failed) return retval;

            	    match(input,12,FOLLOW_12_in_postfixexpr943); if (state.failed) return retval;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr947);
            	    params=exprList();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    match(input,14,FOLLOW_14_in_postfixexpr949); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {                 
            	                      if(retval.field!=null){retval.v =opFLoad(retval.v,retval.field);retval.field =null;}
            	    	                MethodSymbol m = new MethodSymbol((ClassSymbol)retval.v.type,(mID!=null?mID.getText():null),BuiltInTypeSymbol.FLEX);
            	    	                retval.v = opInvoke(retval.v,m,params);
            	    	              }

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:370:12: '.' fID= ID
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr964); if (state.failed) return retval;

            	    fID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr968); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) { 
            	                    if(retval.field!=null){retval.v =opFLoad(retval.v,retval.field);retval.field =null;} 
            	                    retval.field =new FieldSymbol((ClassSymbol)retval.v.type,(fID!=null?fID.getText():null),BuiltInTypeSymbol.FLEX); }

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



    // $ANTLR start "exprList"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:376:1: exprList returns [List<Var> list] : (e= expr ( ',' e= expr )* |);
    public final List<Var> exprList() throws RecognitionException {
        List<Var> list = null;


        Var e =null;


        list = new ArrayList<>();
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:378:5: (e= expr ( ',' e= expr )* |)
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0 >= ID && LA17_0 <= INT)||LA17_0==12||LA17_0==26||(LA17_0 >= 28 && LA17_0 <= 29)) ) {
                alt17=1;
            }
            else if ( (LA17_0==14) ) {
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:378:9: e= expr ( ',' e= expr )*
                    {
                    pushFollow(FOLLOW_expr_in_exprList1022);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return list;

                    if ( state.backtracking==0 ) {if(e.applied)e=opMove(popTmp(e.type),e); list.add(e);}

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:379:9: ( ',' e= expr )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==17) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:379:10: ',' e= expr
                    	    {
                    	    match(input,17,FOLLOW_17_in_exprList1035); if (state.failed) return list;

                    	    pushFollow(FOLLOW_expr_in_exprList1039);
                    	    e=expr();

                    	    state._fsp--;
                    	    if (state.failed) return list;

                    	    if ( state.backtracking==0 ) {if(e.applied)e=opMove(popTmp(e.type),e); list.add(e);}

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:380:5: 
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:382:1: primary returns [Var v] : ( ( 'new' type '()' ) | 'this' | 'super' | INT | ID | '(' expr ')' );
    public final Var primary() throws RecognitionException {
        Var v = null;


        Token INT11=null;
        Token ID12=null;
        Type type10 =null;

        Var expr13 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:383:5: ( ( 'new' type '()' ) | 'this' | 'super' | INT | ID | '(' expr ')' )
            int alt18=6;
            switch ( input.LA(1) ) {
            case 26:
                {
                alt18=1;
                }
                break;
            case 29:
                {
                alt18=2;
                }
                break;
            case 28:
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
                if (state.backtracking>0) {state.failed=true; return v;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }

            switch (alt18) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:383:9: ( 'new' type '()' )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:383:9: ( 'new' type '()' )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:383:10: 'new' type '()'
                    {
                    match(input,26,FOLLOW_26_in_primary1071); if (state.failed) return v;

                    pushFollow(FOLLOW_type_in_primary1073);
                    type10=type();

                    state._fsp--;
                    if (state.failed) return v;

                    match(input,13,FOLLOW_13_in_primary1075); if (state.failed) return v;

                    }


                    if ( state.backtracking==0 ) {v = opNew((ClassSymbol)type10);}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:384:9: 'this'
                    {
                    match(input,29,FOLLOW_29_in_primary1088); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = v("this");}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:385:9: 'super'
                    {
                    match(input,28,FOLLOW_28_in_primary1109); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = v("super");}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:386:9: INT
                    {
                    INT11=(Token)match(input,INT,FOLLOW_INT_in_primary1130); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = opILoad((INT11!=null?INT11.getText():null));}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:387:9: ID
                    {
                    ID12=(Token)match(input,ID,FOLLOW_ID_in_primary1154); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = v((ID12!=null?ID12.getText():null));}

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:388:9: '(' expr ')'
                    {
                    match(input,12,FOLLOW_12_in_primary1179); if (state.failed) return v;

                    pushFollow(FOLLOW_expr_in_primary1181);
                    expr13=expr();

                    state._fsp--;
                    if (state.failed) return v;

                    match(input,14,FOLLOW_14_in_primary1183); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = expr13;}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:395:1: type returns [Type type] : ( 'decimal' | 'int' | 'void' | ID );
    public final Type type() throws RecognitionException {
        Type type = null;


        Token ID14=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:396:5: ( 'decimal' | 'int' | 'void' | ID )
            int alt19=4;
            switch ( input.LA(1) ) {
            case 23:
                {
                alt19=1;
                }
                break;
            case 25:
                {
                alt19=2;
                }
                break;
            case 30:
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:396:9: 'decimal'
                    {
                    match(input,23,FOLLOW_23_in_type1220); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:397:9: 'int'
                    {
                    match(input,25,FOLLOW_25_in_type1232); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:398:9: 'void'
                    {
                    match(input,30,FOLLOW_30_in_type1248); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:399:9: ID
                    {
                    ID14=(Token)match(input,ID,FOLLOW_ID_in_type1263); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = resolveType((ID14!=null?ID14.getText():null));}

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
    public static final BitSet FOLLOW_22_in_classDefinition83 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_classDefinition85 = new BitSet(new long[]{0x0000000081000000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition87 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_classDefinition115 = new BitSet(new long[]{0x0000000042800020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition117 = new BitSet(new long[]{0x0000000142800020L});
    public static final BitSet FOLLOW_32_in_classDefinition121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_superClass162 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_superClass164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fieldDeclaration_in_classMember184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_fieldDeclaration217 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_fieldDeclaration219 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_fieldDeclaration222 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_fieldDeclaration226 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_fieldDeclaration230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration253 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration257 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration267 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration281 = new BitSet(new long[]{0x0000000042800020L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration283 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_methodDeclaration285 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameters354 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters358 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_formalParameters367 = new BitSet(new long[]{0x0000000042800020L});
    public static final BitSet FOLLOW_type_in_formalParameters371 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_formalParameters375 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_31_in_block411 = new BitSet(new long[]{0x00000001FE901060L});
    public static final BitSet FOLLOW_statement_in_block413 = new BitSet(new long[]{0x00000001FE901060L});
    public static final BitSet FOLLOW_32_in_block416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement456 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnStatement_in_statement469 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprStatement_in_statement481 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_statement495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration521 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_varDeclaration523 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_varDeclaration526 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_varDeclaration530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_returnStatement566 = new BitSet(new long[]{0x0000000034001062L});
    public static final BitSet FOLLOW_expr_in_returnStatement570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixexpr_in_exprStatement616 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_exprStatement628 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_exprStatement632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addexpr_in_expr675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multexpr_in_addexpr706 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_16_in_addexpr722 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr726 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_18_in_addexpr743 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr747 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_postfixExprValue_in_multexpr791 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_multexpr808 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_postfixExprValue_in_multexpr812 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_postfixexpr_in_postfixExprValue855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_postfixexpr883 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr916 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr920 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixexpr922 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr937 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr941 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixexpr943 = new BitSet(new long[]{0x0000000034005060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr947 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_postfixexpr949 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr964 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr968 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_expr_in_exprList1022 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_exprList1035 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_exprList1039 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_26_in_primary1071 = new BitSet(new long[]{0x0000000042800020L});
    public static final BitSet FOLLOW_type_in_primary1073 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_primary1088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_primary1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_primary1130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary1179 = new BitSet(new long[]{0x0000000034001060L});
    public static final BitSet FOLLOW_expr_in_primary1181 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_primary1183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_type1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_type1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_type1248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type1263 = new BitSet(new long[]{0x0000000000000002L});

}