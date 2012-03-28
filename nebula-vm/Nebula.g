grammar Nebula;

options {
  language = Java;
}
@lexer::header {package nebula.vm;}
@header {
package nebula.vm;
}

@members {  
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
}// END:members

compilationUnit
    :   ( classDefinition )+ EOF
    ;
    
// *************   START:  Class   *************
classDefinition returns[ClassSymbol clz]
    :   'class' ID superClass? 
            {clz=enterClass($ID.text,$superClass.type);} 
         '{' classMember[clz]+ '}'
            {clz=exitClass(clz);}         
    ;

superClass returns[Type type]
  : 'extends' ID {$type=resolveType($ID.text);}
  ;
  
classMember[ClassSymbol clz]
  :   fieldDeclaration[clz] 
      | methodDeclaration[clz]
  ; 

fieldDeclaration[ClassSymbol clz] returns[FieldSymbol field]
  :   type ID ('=' e=expr)? ';' {field = defineField(clz, $ID.text,$type.type);}
  ;
  
methodDeclaration[ClassSymbol clz] returns[MethodSymbol m]
  :   type name=ID 
      ('()' {m=enterMethod(clz, $name.text, $type.type, new ArrayList<Var>());}
       | ('(' formalParameters ')') {m=enterMethod(clz, $name.text, $type.type, $formalParameters.list);}
      )        
      block
        {m=exitMethod(m);}
  ; // END: method

formalParameters returns [List<Var> list]
  @init{$list = new ArrayList<>(); }    
  :   t=type id=ID{$list.add(new Var($id.text,$t.type,0));}
    ( ',' t=type id=ID{$list.add(new Var($id.text,$t.type,0));} 
     )* 
    ;
  
// *************   END  :  Class   *************


// *************   START:  BLOCK   *************
block
    :   '{' statement* '}' 
    ;
  
statement
    @after{clearTmp();}
    :   block
    |   varDeclaration  ';'
    |   returnStatement ';'
    |   exprStatement   ';'
    |   ';' 
    ;   

varDeclaration returns[Var v]
    :   type ID ('=' from=expr  )? 
        { 
            v = pushLocal($ID.text,$type.type);
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
    ; 
    
returnStatement
    :'return' v=expr? {opReturn(v);}
    ;
        
exprStatement returns[Var v]
    options {backtrack=true;}
    :   to=postfixexpr 
        ('=' from=expr  )?
        { 
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
    ;
    
expr returns [Var v]
    :   e=addexpr {v = $e.v;}
    ;
    
addexpr returns [Var v]
    :   a=multexpr {v = a;}
        (   '+' b=multexpr  {v=opIAdd(v,b);}
        |   '-' c=multexpr  {v=opISub(v,c);}
        )*
    ; // END:addexpr
    
multexpr returns [Var v]
    :   a=postfixExprValue {v=a;} 
        (   '*' b=postfixExprValue {v=opIMul(v,b);} 
        )*
    ;
    
postfixExprValue returns [Var v]
    :   p=postfixexpr {v=$p.v; if($p.field!=null){v=opFLoad(v,$p.field);} }
    ;

postfixexpr returns [Var v,FieldSymbol field]
    :   (p=primary {$v = p;})
        ( options {backtrack=true;}
         : '.' mID=ID '()' {
	              if($field!=null){$v=opFLoad($v,$field);$field=null;} 
	              if($v.type == BuiltInTypeSymbol.FLEX){
	                  $v.type = BuiltInTypeSymbol.FLEXCLASS;
	              }
	              MethodSymbol m = new MethodSymbol((ClassSymbol)$v.type,$mID.text,BuiltInTypeSymbol.FLEX);  
	              $v = opInvoke($v,m,new ArrayList<Var>());
	           }
         | '.' mID=ID '(' params=exprList ')' {                 
                  if($field!=null){$v=opFLoad($v,$field);$field=null;}
	                MethodSymbol m = new MethodSymbol((ClassSymbol)$v.type,$mID.text,BuiltInTypeSymbol.FLEX);
	                $v = opInvoke($v,m,params);
	              }
         | '.' fID=ID { 
                if($field!=null){$v=opFLoad($v,$field);$field=null;} 
                $field=new FieldSymbol((ClassSymbol)$v.type,$fID.text,BuiltInTypeSymbol.FLEX); }
        )*
    ;
    
exprList returns [List<Var> list]
    @init{$list = new ArrayList<>();}    
    :   e=expr{if(e.applied)e=opMove(popTmp(e.type),e); list.add(e);} 
        (',' e=expr{if(e.applied)e=opMove(popTmp(e.type),e); list.add(e);})* | 
    ; 

primary returns [Var v] // START:atom
    :   ('new' type '()') {v = opNew((ClassSymbol)$type.type);}
    |   'this'          {v = v("this");}
    |   'super'         {v = v("super");} 
    |   INT             {v = opILoad($INT.text);}
    |   ID              {v = v($ID.text);}
    |   '(' expr ')'    {v = $expr.v;}
    ; // END:atom
    
// *************   END  :  BLOCK   *************

// *************   START:  BASIC   *************

type returns [Type type]
    :   'decimal' {$type = BuiltInTypeSymbol.DECIMAL;}
    |   'int'     {$type = BuiltInTypeSymbol.INT;}
    |   'void'    {$type = BuiltInTypeSymbol.VOID;}
    |   ID        {$type = resolveType($ID.text);}
    ;    
    
// *************   END  :  BASIC   *************

ID :  Letter (Letter | Digit)*;  
INT :  Digit Digit*;
fragment Digit :  '0'..'9';
fragment Letter : 'a'..'z' | 'A'..'Z';

NEWLINE:'\r'? '\n'  {$channel=HIDDEN;};    
Whitespace :  (' ' | '\t' | '\f')+ {$channel=HIDDEN;};    
SingleLineComment :
  '//' (~('\n'|'\r'))* ('\n'|'\r'('\n')?)? {$channel=HIDDEN;};
MultiLineComment :
    '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;};