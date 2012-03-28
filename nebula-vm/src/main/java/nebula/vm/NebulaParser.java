// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-vm\\Nebula.g 2012-03-28 23:03:37

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Digit", "ID", "INT", "Letter", "MultiLineComment", "NEWLINE", "SingleLineComment", "Whitespace", "'('", "'()'", "')'", "'*'", "'+'", "','", "'-'", "'.'", "';'", "'='", "'=='", "'class'", "'decimal'", "'else'", "'extends'", "'if'", "'int'", "'new'", "'return'", "'super'", "'then'", "'this'", "'void'", "'{'", "'}'"
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
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
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
        clz.fields = this.fields.toArray(new FieldSymbol[0]);
        clz.methods = this.methods.toArray(new MethodSymbol[0]);
        
        return clz;
      };

      protected FieldSymbol defineField(ClassSymbol clz, String name, Type type) {
        FieldSymbol f = new FieldSymbol(clz, name, type);
        info("FIELD : " + f.name + "\n");
        
        fields.add(f);
        return f;
      };

      protected MethodSymbol enterMethod(ClassSymbol clz, String name, Type returnType, List<Var> params) {
        MethodSymbol m = new MethodSymbol(clz, name, returnType);
        this.maxLocals = 0;

        pushLocal("this", clz);

        for (Var v : params) {
          pushLocal(v.name, v.type);
        }
        m.nargs = params.size();
        this.methods.add(m);

        String str = "";
        for (Var v : params) {
          str += v.name + " ";
        }
        info("FUNC  : " + name + "() {\n");

        return m;
      };

      protected MethodSymbol exitMethod(MethodSymbol method) {
        method.nlocals = maxLocals - method.nargs;
        
        info("}\n");
        return method;
      };
      
      protected Var opIEq(Var a, Var b) {
        if (!a.applied) resolveTemp(a);
        if (!b.applied) resolveTemp(b);
        TempVar v = popTmp(a.type);

        info("IEQ  : " + v.getName() + " = " + a.getName() + " + " + b.getName() + ";\n");

        return v;
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

      protected List<MethodSymbol> methods = new ArrayList<>();
      protected List<FieldSymbol> fields = new ArrayList<>();
      
      private Map<String, Var> locals = new HashMap<>();
      protected int maxLocals = 0;
      
      protected void initLocals(){
        locals.clear();
      }
      
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:259:1: compilationUnit : ( classDefinition )+ EOF ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:260:5: ( ( classDefinition )+ EOF )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:260:9: ( classDefinition )+ EOF
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:260:9: ( classDefinition )+
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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:260:11: classDefinition
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:264:1: classDefinition returns [ClassSymbol clz] : 'class' ID ( superClass )? '{' ( classMember[clz] )+ '}' ;
    public final ClassSymbol classDefinition() throws RecognitionException {
        ClassSymbol clz = null;


        Token ID1=null;
        Type superClass2 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:265:5: ( 'class' ID ( superClass )? '{' ( classMember[clz] )+ '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:265:9: 'class' ID ( superClass )? '{' ( classMember[clz] )+ '}'
            {
            match(input,23,FOLLOW_23_in_classDefinition83); if (state.failed) return clz;

            ID1=(Token)match(input,ID,FOLLOW_ID_in_classDefinition85); if (state.failed) return clz;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:265:20: ( superClass )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==26) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:265:20: superClass
                    {
                    pushFollow(FOLLOW_superClass_in_classDefinition87);
                    superClass2=superClass();

                    state._fsp--;
                    if (state.failed) return clz;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {clz=enterClass((ID1!=null?ID1.getText():null),superClass2);}

            match(input,35,FOLLOW_35_in_classDefinition115); if (state.failed) return clz;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:267:14: ( classMember[clz] )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID||LA3_0==24||LA3_0==28||LA3_0==34) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:267:14: classMember[clz]
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


            match(input,36,FOLLOW_36_in_classDefinition121); if (state.failed) return clz;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:271:1: superClass returns [Type type] : 'extends' ID ;
    public final Type superClass() throws RecognitionException {
        Type type = null;


        Token ID3=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:272:3: ( 'extends' ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:272:5: 'extends' ID
            {
            match(input,26,FOLLOW_26_in_superClass162); if (state.failed) return type;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:275:1: classMember[ClassSymbol clz] : ( fieldDeclaration[clz] | methodDeclaration[clz] );
    public final void classMember(ClassSymbol clz) throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:276:3: ( fieldDeclaration[clz] | methodDeclaration[clz] )
            int alt4=2;
            switch ( input.LA(1) ) {
            case 24:
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
            case 28:
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
            case 34:
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:276:7: fieldDeclaration[clz]
                    {
                    pushFollow(FOLLOW_fieldDeclaration_in_classMember184);
                    fieldDeclaration(clz);

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:277:9: methodDeclaration[clz]
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:280:1: fieldDeclaration[ClassSymbol clz] returns [FieldSymbol field] : type ID ( '=' e= expr )? ';' ;
    public final FieldSymbol fieldDeclaration(ClassSymbol clz) throws RecognitionException {
        FieldSymbol field = null;


        Token ID4=null;
        Var e =null;

        Type type5 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:281:3: ( type ID ( '=' e= expr )? ';' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:281:7: type ID ( '=' e= expr )? ';'
            {
            pushFollow(FOLLOW_type_in_fieldDeclaration217);
            type5=type();

            state._fsp--;
            if (state.failed) return field;

            ID4=(Token)match(input,ID,FOLLOW_ID_in_fieldDeclaration219); if (state.failed) return field;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:281:15: ( '=' e= expr )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==21) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:281:16: '=' e= expr
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:284:1: methodDeclaration[ClassSymbol clz] returns [MethodSymbol m] : type name= ID ( '()' | ( '(' params= paramDeclarationList ')' ) ) block ;
    public final MethodSymbol methodDeclaration(ClassSymbol clz) throws RecognitionException {
        MethodSymbol m = null;


        Token name=null;
        List<Var> params =null;

        Type type6 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:285:3: ( type name= ID ( '()' | ( '(' params= paramDeclarationList ')' ) ) block )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:285:7: type name= ID ( '()' | ( '(' params= paramDeclarationList ')' ) ) block
            {
            pushFollow(FOLLOW_type_in_methodDeclaration253);
            type6=type();

            state._fsp--;
            if (state.failed) return m;

            name=(Token)match(input,ID,FOLLOW_ID_in_methodDeclaration257); if (state.failed) return m;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:286:7: ( '()' | ( '(' params= paramDeclarationList ')' ) )
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:286:8: '()'
                    {
                    match(input,13,FOLLOW_13_in_methodDeclaration267); if (state.failed) return m;

                    if ( state.backtracking==0 ) {m=enterMethod(clz, (name!=null?name.getText():null), type6, new ArrayList<Var>());}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:287:10: ( '(' params= paramDeclarationList ')' )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:287:10: ( '(' params= paramDeclarationList ')' )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:287:11: '(' params= paramDeclarationList ')'
                    {
                    match(input,12,FOLLOW_12_in_methodDeclaration281); if (state.failed) return m;

                    pushFollow(FOLLOW_paramDeclarationList_in_methodDeclaration285);
                    params=paramDeclarationList();

                    state._fsp--;
                    if (state.failed) return m;

                    match(input,14,FOLLOW_14_in_methodDeclaration287); if (state.failed) return m;

                    }


                    if ( state.backtracking==0 ) {m=enterMethod(clz, (name!=null?name.getText():null), type6, params);}

                    }
                    break;

            }


            pushFollow(FOLLOW_block_in_methodDeclaration314);
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



    // $ANTLR start "paramDeclarationList"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:293:1: paramDeclarationList returns [List<Var> list] : v= paramDeclaration ( ',' v= paramDeclaration )* ;
    public final List<Var> paramDeclarationList() throws RecognitionException {
        List<Var> list = null;


        Var v =null;


        list = new ArrayList<>(); 
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:295:3: (v= paramDeclaration ( ',' v= paramDeclaration )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:295:7: v= paramDeclaration ( ',' v= paramDeclaration )*
            {
            pushFollow(FOLLOW_paramDeclaration_in_paramDeclarationList356);
            v=paramDeclaration();

            state._fsp--;
            if (state.failed) return list;

            if ( state.backtracking==0 ) {list.add(v);}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:296:5: ( ',' v= paramDeclaration )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==17) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:296:7: ',' v= paramDeclaration
            	    {
            	    match(input,17,FOLLOW_17_in_paramDeclarationList365); if (state.failed) return list;

            	    pushFollow(FOLLOW_paramDeclaration_in_paramDeclarationList369);
            	    v=paramDeclaration();

            	    state._fsp--;
            	    if (state.failed) return list;

            	    if ( state.backtracking==0 ) {list.add(v);}

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
    // $ANTLR end "paramDeclarationList"



    // $ANTLR start "paramDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:300:1: paramDeclaration returns [Var v] : t= type id= ID ;
    public final Var paramDeclaration() throws RecognitionException {
        Var v = null;


        Token id=null;
        Type t =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:301:3: (t= type id= ID )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:301:5: t= type id= ID
            {
            pushFollow(FOLLOW_type_in_paramDeclaration404);
            t=type();

            state._fsp--;
            if (state.failed) return v;

            id=(Token)match(input,ID,FOLLOW_ID_in_paramDeclaration408); if (state.failed) return v;

            if ( state.backtracking==0 ) {v=new Var((id!=null?id.getText():null),t,0);}

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
    // $ANTLR end "paramDeclaration"



    // $ANTLR start "block"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:309:1: block : '{' ( statement )* '}' ;
    public final void block() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:310:5: ( '{' ( statement )* '}' )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:310:9: '{' ( statement )* '}'
            {
            match(input,35,FOLLOW_35_in_block432); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:310:13: ( statement )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= ID && LA8_0 <= INT)||LA8_0==12||LA8_0==20||LA8_0==24||(LA8_0 >= 27 && LA8_0 <= 31)||(LA8_0 >= 33 && LA8_0 <= 35)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:310:13: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block434);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            match(input,36,FOLLOW_36_in_block437); if (state.failed) return ;

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:313:1: statement : ( block | varDeclaration ';' | returnStatement ';' | exprStatement ';' | ifStatement | ';' );
    public final void statement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:315:5: ( block | varDeclaration ';' | returnStatement ';' | exprStatement ';' | ifStatement | ';' )
            int alt9=6;
            switch ( input.LA(1) ) {
            case 35:
                {
                alt9=1;
                }
                break;
            case 24:
            case 28:
            case 34:
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
            case 30:
                {
                alt9=3;
                }
                break;
            case INT:
            case 12:
            case 29:
            case 31:
            case 33:
                {
                alt9=4;
                }
                break;
            case 27:
                {
                alt9=5;
                }
                break;
            case 20:
                {
                alt9=6;
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
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:315:9: block
                    {
                    pushFollow(FOLLOW_block_in_statement467);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:316:9: varDeclaration ';'
                    {
                    pushFollow(FOLLOW_varDeclaration_in_statement477);
                    varDeclaration();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,20,FOLLOW_20_in_statement480); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:317:9: returnStatement ';'
                    {
                    pushFollow(FOLLOW_returnStatement_in_statement490);
                    returnStatement();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,20,FOLLOW_20_in_statement492); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:318:9: exprStatement ';'
                    {
                    pushFollow(FOLLOW_exprStatement_in_statement502);
                    exprStatement();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input,20,FOLLOW_20_in_statement506); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:319:9: ifStatement
                    {
                    pushFollow(FOLLOW_ifStatement_in_statement516);
                    ifStatement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:320:9: ';'
                    {
                    match(input,20,FOLLOW_20_in_statement526); if (state.failed) return ;

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



    // $ANTLR start "ifStatement"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:323:1: ifStatement : 'if' '(' expr ')' 'then' t= statement ( 'else' e= statement )? ;
    public final void ifStatement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:324:5: ( 'if' '(' expr ')' 'then' t= statement ( 'else' e= statement )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:324:7: 'if' '(' expr ')' 'then' t= statement ( 'else' e= statement )?
            {
            match(input,27,FOLLOW_27_in_ifStatement550); if (state.failed) return ;

            match(input,12,FOLLOW_12_in_ifStatement552); if (state.failed) return ;

            pushFollow(FOLLOW_expr_in_ifStatement554);
            expr();

            state._fsp--;
            if (state.failed) return ;

            match(input,14,FOLLOW_14_in_ifStatement556); if (state.failed) return ;

            match(input,32,FOLLOW_32_in_ifStatement565); if (state.failed) return ;

            pushFollow(FOLLOW_statement_in_ifStatement569);
            statement();

            state._fsp--;
            if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:326:7: ( 'else' e= statement )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==25) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:326:8: 'else' e= statement
                    {
                    match(input,25,FOLLOW_25_in_ifStatement578); if (state.failed) return ;

                    pushFollow(FOLLOW_statement_in_ifStatement582);
                    statement();

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
    // $ANTLR end "ifStatement"



    // $ANTLR start "forStatement"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:329:1: forStatement :;
    public final void forStatement() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:330:5: ()
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:331:5: 
            {
            }

        }
        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "forStatement"



    // $ANTLR start "varDeclaration"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:333:1: varDeclaration returns [Var v] : type ID ( '=' from= expr )? ;
    public final Var varDeclaration() throws RecognitionException {
        Var v = null;


        Token ID7=null;
        Var from =null;

        Type type8 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:334:5: ( type ID ( '=' from= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:334:9: type ID ( '=' from= expr )?
            {
            pushFollow(FOLLOW_type_in_varDeclaration625);
            type8=type();

            state._fsp--;
            if (state.failed) return v;

            ID7=(Token)match(input,ID,FOLLOW_ID_in_varDeclaration627); if (state.failed) return v;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:334:17: ( '=' from= expr )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==21) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:334:18: '=' from= expr
                    {
                    match(input,21,FOLLOW_21_in_varDeclaration630); if (state.failed) return v;

                    pushFollow(FOLLOW_expr_in_varDeclaration634);
                    from=expr();

                    state._fsp--;
                    if (state.failed) return v;

                    }
                    break;

            }


            if ( state.backtracking==0 ) { 
                        v = pushLocal((ID7!=null?ID7.getText():null),type8);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:350:1: returnStatement : 'return' (v= expr )? ;
    public final void returnStatement() throws RecognitionException {
        Var v =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:351:5: ( 'return' (v= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:351:6: 'return' (v= expr )?
            {
            match(input,30,FOLLOW_30_in_returnStatement670); if (state.failed) return ;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:351:16: (v= expr )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0 >= ID && LA12_0 <= INT)||LA12_0==12||LA12_0==29||LA12_0==31||LA12_0==33) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:351:16: v= expr
                    {
                    pushFollow(FOLLOW_expr_in_returnStatement674);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:354:1: exprStatement returns [Var v] options {backtrack=true; } : to= postfixexpr ( '=' from= expr )? ;
    public final Var exprStatement() throws RecognitionException {
        Var v = null;


        NebulaParser.postfixexpr_return to =null;

        Var from =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:356:5: (to= postfixexpr ( '=' from= expr )? )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:356:9: to= postfixexpr ( '=' from= expr )?
            {
            pushFollow(FOLLOW_postfixexpr_in_exprStatement720);
            to=postfixexpr();

            state._fsp--;
            if (state.failed) return v;

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:357:9: ( '=' from= expr )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==21) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:357:10: '=' from= expr
                    {
                    match(input,21,FOLLOW_21_in_exprStatement732); if (state.failed) return v;

                    pushFollow(FOLLOW_expr_in_exprStatement736);
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
                          if(!to.v.applied)resolveTemp(to.v);               
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:374:1: expr returns [Var v] : e= eqexpr ;
    public final Var expr() throws RecognitionException {
        Var v = null;


        Var e =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:375:5: (e= eqexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:375:9: e= eqexpr
            {
            pushFollow(FOLLOW_eqexpr_in_expr779);
            e=eqexpr();

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



    // $ANTLR start "eqexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:378:1: eqexpr returns [Var v] : a= addexpr ( '==' b= addexpr )* ;
    public final Var eqexpr() throws RecognitionException {
        Var v = null;


        Var a =null;

        Var b =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:379:5: (a= addexpr ( '==' b= addexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:379:9: a= addexpr ( '==' b= addexpr )*
            {
            pushFollow(FOLLOW_addexpr_in_eqexpr810);
            a=addexpr();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v = a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:380:9: ( '==' b= addexpr )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==22) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:380:13: '==' b= addexpr
            	    {
            	    match(input,22,FOLLOW_22_in_eqexpr826); if (state.failed) return v;

            	    pushFollow(FOLLOW_addexpr_in_eqexpr830);
            	    b=addexpr();

            	    state._fsp--;
            	    if (state.failed) return v;

            	    if ( state.backtracking==0 ) {v=opIEq(v,b);}

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
    // $ANTLR end "eqexpr"



    // $ANTLR start "addexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:384:1: addexpr returns [Var v] : a= multexpr ( '+' b= multexpr | '-' c= multexpr )* ;
    public final Var addexpr() throws RecognitionException {
        Var v = null;


        Var a =null;

        Var b =null;

        Var c =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:385:5: (a= multexpr ( '+' b= multexpr | '-' c= multexpr )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:385:9: a= multexpr ( '+' b= multexpr | '-' c= multexpr )*
            {
            pushFollow(FOLLOW_multexpr_in_addexpr873);
            a=multexpr();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v = a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:386:9: ( '+' b= multexpr | '-' c= multexpr )*
            loop15:
            do {
                int alt15=3;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==16) ) {
                    alt15=1;
                }
                else if ( (LA15_0==18) ) {
                    alt15=2;
                }


                switch (alt15) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:386:13: '+' b= multexpr
            	    {
            	    match(input,16,FOLLOW_16_in_addexpr889); if (state.failed) return v;

            	    pushFollow(FOLLOW_multexpr_in_addexpr893);
            	    b=multexpr();

            	    state._fsp--;
            	    if (state.failed) return v;

            	    if ( state.backtracking==0 ) {v=opIAdd(v,b);}

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:387:13: '-' c= multexpr
            	    {
            	    match(input,18,FOLLOW_18_in_addexpr910); if (state.failed) return v;

            	    pushFollow(FOLLOW_multexpr_in_addexpr914);
            	    c=multexpr();

            	    state._fsp--;
            	    if (state.failed) return v;

            	    if ( state.backtracking==0 ) {v=opISub(v,c);}

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
    // $ANTLR end "addexpr"



    // $ANTLR start "multexpr"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:391:1: multexpr returns [Var v] : a= postfixExprValue ( '*' b= postfixExprValue )* ;
    public final Var multexpr() throws RecognitionException {
        Var v = null;


        Var a =null;

        Var b =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:392:5: (a= postfixExprValue ( '*' b= postfixExprValue )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:392:9: a= postfixExprValue ( '*' b= postfixExprValue )*
            {
            pushFollow(FOLLOW_postfixExprValue_in_multexpr958);
            a=postfixExprValue();

            state._fsp--;
            if (state.failed) return v;

            if ( state.backtracking==0 ) {v=a;}

            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:393:9: ( '*' b= postfixExprValue )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==15) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:393:13: '*' b= postfixExprValue
            	    {
            	    match(input,15,FOLLOW_15_in_multexpr975); if (state.failed) return v;

            	    pushFollow(FOLLOW_postfixExprValue_in_multexpr979);
            	    b=postfixExprValue();

            	    state._fsp--;
            	    if (state.failed) return v;

            	    if ( state.backtracking==0 ) {v=opIMul(v,b);}

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
        return v;
    }
    // $ANTLR end "multexpr"



    // $ANTLR start "postfixExprValue"
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:397:1: postfixExprValue returns [Var v] : p= postfixexpr ;
    public final Var postfixExprValue() throws RecognitionException {
        Var v = null;


        NebulaParser.postfixexpr_return p =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:398:5: (p= postfixexpr )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:398:9: p= postfixexpr
            {
            pushFollow(FOLLOW_postfixexpr_in_postfixExprValue1022);
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:401:1: postfixexpr returns [Var v,FieldSymbol field] : (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )* ;
    public final NebulaParser.postfixexpr_return postfixexpr() throws RecognitionException {
        NebulaParser.postfixexpr_return retval = new NebulaParser.postfixexpr_return();
        retval.start = input.LT(1);


        Token mID=null;
        Token fID=null;
        Var p =null;

        List<Var> params =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:402:5: ( (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )* )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:402:9: (p= primary ) ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )*
            {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:402:9: (p= primary )
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:402:10: p= primary
            {
            pushFollow(FOLLOW_primary_in_postfixexpr1050);
            p=primary();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {retval.v = p;}

            }


            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:403:9: ( options {backtrack=true; } : '.' mID= ID '()' | '.' mID= ID '(' params= exprList ')' | '.' fID= ID )*
            loop17:
            do {
                int alt17=4;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==19) ) {
                    int LA17_2 = input.LA(2);

                    if ( (LA17_2==ID) ) {
                        switch ( input.LA(3) ) {
                        case 13:
                            {
                            alt17=1;
                            }
                            break;
                        case 12:
                            {
                            alt17=2;
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
                        case 22:
                            {
                            alt17=3;
                            }
                            break;

                        }

                    }


                }


                switch (alt17) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:404:12: '.' mID= ID '()'
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr1083); if (state.failed) return retval;

            	    mID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr1087); if (state.failed) return retval;

            	    match(input,13,FOLLOW_13_in_postfixexpr1089); if (state.failed) return retval;

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
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:412:12: '.' mID= ID '(' params= exprList ')'
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr1104); if (state.failed) return retval;

            	    mID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr1108); if (state.failed) return retval;

            	    match(input,12,FOLLOW_12_in_postfixexpr1110); if (state.failed) return retval;

            	    pushFollow(FOLLOW_exprList_in_postfixexpr1114);
            	    params=exprList();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    match(input,14,FOLLOW_14_in_postfixexpr1116); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {                 
            	                      if(retval.field!=null){retval.v =opFLoad(retval.v,retval.field);retval.field =null;}
            	    	                MethodSymbol m = new MethodSymbol((ClassSymbol)retval.v.type,(mID!=null?mID.getText():null),BuiltInTypeSymbol.FLEX);
            	    	                retval.v = opInvoke(retval.v,m,params);
            	    	              }

            	    }
            	    break;
            	case 3 :
            	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:417:12: '.' fID= ID
            	    {
            	    match(input,19,FOLLOW_19_in_postfixexpr1131); if (state.failed) return retval;

            	    fID=(Token)match(input,ID,FOLLOW_ID_in_postfixexpr1135); if (state.failed) return retval;

            	    if ( state.backtracking==0 ) { 
            	                    if(retval.field!=null){retval.v =opFLoad(retval.v,retval.field);retval.field =null;} 
            	                    retval.field =new FieldSymbol((ClassSymbol)retval.v.type,(fID!=null?fID.getText():null),BuiltInTypeSymbol.FLEX); }

            	    }
            	    break;

            	default :
            	    break loop17;
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:423:1: exprList returns [List<Var> list] : (e= expr ( ',' e= expr )* |);
    public final List<Var> exprList() throws RecognitionException {
        List<Var> list = null;


        Var e =null;


        list = new ArrayList<>();
        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:425:5: (e= expr ( ',' e= expr )* |)
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0 >= ID && LA19_0 <= INT)||LA19_0==12||LA19_0==29||LA19_0==31||LA19_0==33) ) {
                alt19=1;
            }
            else if ( (LA19_0==14) ) {
                alt19=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return list;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }
            switch (alt19) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:425:9: e= expr ( ',' e= expr )*
                    {
                    pushFollow(FOLLOW_expr_in_exprList1189);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return list;

                    if ( state.backtracking==0 ) {if(e.applied)e=opMove(popTmp(e.type),e); list.add(e);}

                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:426:9: ( ',' e= expr )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==17) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:426:10: ',' e= expr
                    	    {
                    	    match(input,17,FOLLOW_17_in_exprList1202); if (state.failed) return list;

                    	    pushFollow(FOLLOW_expr_in_exprList1206);
                    	    e=expr();

                    	    state._fsp--;
                    	    if (state.failed) return list;

                    	    if ( state.backtracking==0 ) {if(e.applied)e=opMove(popTmp(e.type),e); list.add(e);}

                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:427:5: 
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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:429:1: primary returns [Var v] : ( ( 'new' type '()' ) | 'this' | 'super' | INT | ID | '(' expr ')' );
    public final Var primary() throws RecognitionException {
        Var v = null;


        Token INT10=null;
        Token ID11=null;
        Type type9 =null;

        Var expr12 =null;


        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:430:5: ( ( 'new' type '()' ) | 'this' | 'super' | INT | ID | '(' expr ')' )
            int alt20=6;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt20=1;
                }
                break;
            case 33:
                {
                alt20=2;
                }
                break;
            case 31:
                {
                alt20=3;
                }
                break;
            case INT:
                {
                alt20=4;
                }
                break;
            case ID:
                {
                alt20=5;
                }
                break;
            case 12:
                {
                alt20=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return v;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }

            switch (alt20) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:430:9: ( 'new' type '()' )
                    {
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:430:9: ( 'new' type '()' )
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:430:10: 'new' type '()'
                    {
                    match(input,29,FOLLOW_29_in_primary1238); if (state.failed) return v;

                    pushFollow(FOLLOW_type_in_primary1240);
                    type9=type();

                    state._fsp--;
                    if (state.failed) return v;

                    match(input,13,FOLLOW_13_in_primary1242); if (state.failed) return v;

                    }


                    if ( state.backtracking==0 ) {v = opNew((ClassSymbol)type9);}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:431:9: 'this'
                    {
                    match(input,33,FOLLOW_33_in_primary1255); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = v("this");}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:432:9: 'super'
                    {
                    match(input,31,FOLLOW_31_in_primary1276); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = v("super");}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:433:9: INT
                    {
                    INT10=(Token)match(input,INT,FOLLOW_INT_in_primary1297); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = opILoad((INT10!=null?INT10.getText():null));}

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:434:9: ID
                    {
                    ID11=(Token)match(input,ID,FOLLOW_ID_in_primary1321); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = v((ID11!=null?ID11.getText():null));}

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:435:9: '(' expr ')'
                    {
                    match(input,12,FOLLOW_12_in_primary1346); if (state.failed) return v;

                    pushFollow(FOLLOW_expr_in_primary1348);
                    expr12=expr();

                    state._fsp--;
                    if (state.failed) return v;

                    match(input,14,FOLLOW_14_in_primary1350); if (state.failed) return v;

                    if ( state.backtracking==0 ) {v = expr12;}

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
    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:442:1: type returns [Type type] : ( 'decimal' | 'int' | 'void' | ID );
    public final Type type() throws RecognitionException {
        Type type = null;


        Token ID13=null;

        try {
            // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:443:5: ( 'decimal' | 'int' | 'void' | ID )
            int alt21=4;
            switch ( input.LA(1) ) {
            case 24:
                {
                alt21=1;
                }
                break;
            case 28:
                {
                alt21=2;
                }
                break;
            case 34:
                {
                alt21=3;
                }
                break;
            case ID:
                {
                alt21=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }

            switch (alt21) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:443:9: 'decimal'
                    {
                    match(input,24,FOLLOW_24_in_type1387); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.DECIMAL;}

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:444:9: 'int'
                    {
                    match(input,28,FOLLOW_28_in_type1399); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.INT;}

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:445:9: 'void'
                    {
                    match(input,34,FOLLOW_34_in_type1415); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = BuiltInTypeSymbol.VOID;}

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-vm\\Nebula.g:446:9: ID
                    {
                    ID13=(Token)match(input,ID,FOLLOW_ID_in_type1430); if (state.failed) return type;

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


 

    public static final BitSet FOLLOW_classDefinition_in_compilationUnit51 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_EOF_in_compilationUnit56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_classDefinition83 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_classDefinition85 = new BitSet(new long[]{0x0000000804000000L});
    public static final BitSet FOLLOW_superClass_in_classDefinition87 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_classDefinition115 = new BitSet(new long[]{0x0000000411000020L});
    public static final BitSet FOLLOW_classMember_in_classDefinition117 = new BitSet(new long[]{0x0000001411000020L});
    public static final BitSet FOLLOW_36_in_classDefinition121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_superClass162 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_superClass164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fieldDeclaration_in_classMember184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_classMember196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_fieldDeclaration217 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_fieldDeclaration219 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_fieldDeclaration222 = new BitSet(new long[]{0x00000002A0001060L});
    public static final BitSet FOLLOW_expr_in_fieldDeclaration226 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_fieldDeclaration230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodDeclaration253 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration257 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_13_in_methodDeclaration267 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_12_in_methodDeclaration281 = new BitSet(new long[]{0x0000000411000020L});
    public static final BitSet FOLLOW_paramDeclarationList_in_methodDeclaration285 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_methodDeclaration287 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paramDeclaration_in_paramDeclarationList356 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_paramDeclarationList365 = new BitSet(new long[]{0x0000000411000020L});
    public static final BitSet FOLLOW_paramDeclaration_in_paramDeclarationList369 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_type_in_paramDeclaration404 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_paramDeclaration408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_block432 = new BitSet(new long[]{0x0000001EF9101060L});
    public static final BitSet FOLLOW_statement_in_block434 = new BitSet(new long[]{0x0000001EF9101060L});
    public static final BitSet FOLLOW_36_in_block437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_statement477 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnStatement_in_statement490 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprStatement_in_statement502 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_statement526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ifStatement550 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ifStatement552 = new BitSet(new long[]{0x00000002A0001060L});
    public static final BitSet FOLLOW_expr_in_ifStatement554 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ifStatement556 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ifStatement565 = new BitSet(new long[]{0x0000000EF9101060L});
    public static final BitSet FOLLOW_statement_in_ifStatement569 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ifStatement578 = new BitSet(new long[]{0x0000000EF9101060L});
    public static final BitSet FOLLOW_statement_in_ifStatement582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration625 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_varDeclaration627 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_varDeclaration630 = new BitSet(new long[]{0x00000002A0001060L});
    public static final BitSet FOLLOW_expr_in_varDeclaration634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_returnStatement670 = new BitSet(new long[]{0x00000002A0001062L});
    public static final BitSet FOLLOW_expr_in_returnStatement674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postfixexpr_in_exprStatement720 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_exprStatement732 = new BitSet(new long[]{0x00000002A0001060L});
    public static final BitSet FOLLOW_expr_in_exprStatement736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_eqexpr_in_expr779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addexpr_in_eqexpr810 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_eqexpr826 = new BitSet(new long[]{0x00000002A0001060L});
    public static final BitSet FOLLOW_addexpr_in_eqexpr830 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_multexpr_in_addexpr873 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_16_in_addexpr889 = new BitSet(new long[]{0x00000002A0001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr893 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_18_in_addexpr910 = new BitSet(new long[]{0x00000002A0001060L});
    public static final BitSet FOLLOW_multexpr_in_addexpr914 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_postfixExprValue_in_multexpr958 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_multexpr975 = new BitSet(new long[]{0x00000002A0001060L});
    public static final BitSet FOLLOW_postfixExprValue_in_multexpr979 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_postfixexpr_in_postfixExprValue1022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_postfixexpr1050 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr1083 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr1087 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_postfixexpr1089 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr1104 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr1108 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_postfixexpr1110 = new BitSet(new long[]{0x00000002A0005060L});
    public static final BitSet FOLLOW_exprList_in_postfixexpr1114 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_postfixexpr1116 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_postfixexpr1131 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_postfixexpr1135 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_expr_in_exprList1189 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_exprList1202 = new BitSet(new long[]{0x00000002A0001060L});
    public static final BitSet FOLLOW_expr_in_exprList1206 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_29_in_primary1238 = new BitSet(new long[]{0x0000000411000020L});
    public static final BitSet FOLLOW_type_in_primary1240 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_primary1242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_primary1255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_primary1276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_primary1297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_primary1346 = new BitSet(new long[]{0x00000002A0001060L});
    public static final BitSet FOLLOW_expr_in_primary1348 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_primary1350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_type1387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_type1399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_type1415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type1430 = new BitSet(new long[]{0x0000000000000002L});

}