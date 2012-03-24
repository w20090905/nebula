grammar Nebula;

options {
  language = Java;
}
@lexer::header {package nebula.vm;}
@header {
package nebula.vm;
}

@members {  // START:members
  /** Method info */  
  private Map<String,Var> locals = new HashMap<>();
  private List<Var> params = null;
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
  protected TempVar popTmp(Type type){
      TempVar v = null;
      for(TempVar tv:tmps){
          if(tv.applied){
              v = tv;
              v.type = type;
              break;
          }
      }
      if(v ==null){
          int index = locals.size() + tmps.size();
          v = new TempVar("tmp"+index,(short)index,type);
          tmps.push(v);
      }
      v.applied = false;
      return v;
  }
  
  protected void resolveTemp(Var v){
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
    
  protected TempVar pick(Var a,Var b){
      assert a.type.equals(b.type); 
      if(!a.applied)resolveTemp(a);
      if(!b.applied)resolveTemp(b);
      return popTmp(a.type);
  }
  
  protected ClassSymbol resolveType(String name){return new ClassSymbol(name);};
    
  protected ClassSymbol enterClass(String name,Type superType)  {return new ClassSymbol(name);/*TODO add super class support*/};
  protected ClassSymbol exitClass(ClassSymbol clz) {return clz;};
    
  protected FieldSymbol defineField(ClassSymbol clz,String name,Type type){
      info("define field " + name + "\n");
      return new FieldSymbol(clz,name,type);
  };
  
  protected MethodSymbol enterMethod(ClassSymbol clz, String name,Type returnType,List<Var> params) {
      MethodSymbol m = new MethodSymbol(clz, name,returnType);
      maxLocals = 0;
      
      String str = "";
      for(Var v : params){
          pushLocal(v);
          str += v.name + " ";
      }
      
      Var varThis = new Var("this",clz);
      pushLocal(varThis);
      
      info("FUNC " + name + "() {\n");
      
      return m;
  };
  protected MethodSymbol exitMethod(MethodSymbol method) {      
      info("}\n");
      return method;
  };

  protected Var add(Var a, Var b) {Var v=pick(a,b);info("ADD  : " + v.getName() +  " = " + a.getName() + " + " + b.getName() + ";\n");return v;};
  protected Var sub(Var a, Var b) {Var v=pick(a,b);info("SUB  : " + v.getName() +  " = " + a.getName() + " - " + b.getName() + ";\n");return v;};
  protected Var mul(Var a, Var b) {Var v=pick(a,b);info("MUL  : " + v.getName() +  " = " + a.getName() + " * " + b.getName() + ";\n");return v;};

  protected Var getField(Var obj,FieldSymbol field) {
      Var var = popTmp(BuiltInTypeSymbol.FLEX);
      info("GETF : " + var.getName() +  " = " + obj.getName() + "." + field.name + "\n");
      return var;
  };
  
  protected Var setField(Var obj, FieldSymbol field, Var v) {
      resolveTemp(v);
      info("SETF : " + obj.getName() + "." + field.name + " = " + v.getName() + "\n");  
      return v;
  };  
    
  protected Var invoke(Var obj,MethodSymbol method,List<Var> params) {
	       resolveTemp(obj);
	       String txtParams = "";
	       for(Var v:params){
	           txtParams += v.getName() + " ";
	           resolveTemp(v);
	       }
	       String str = " = " + obj.getName() + "." + method.definedClass.getName() + "_" +  method.name + "("  + txtParams +  ");\n";
	       Var var = popTmp(BuiltInTypeSymbol.FLEX);
	       info("CALL : " + var.getName() + str); 
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
		        resolveTemp(from);
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
	        resolveTemp(v);
	        pushLocal(to);        
        }
        return v;
  };
  
  protected Var move(Var to,Var from){
        info("MOVE : " + to.getName() +  " = " + from.getName() + ";\n");
        return to;
  }

  protected Var createObject(ClassSymbol clz){
        Var var = popTmp(clz);
        info("NEWO : " + var.getName()  + " = new " + clz.getName() + ";\n");
        return var;
  }
  
  protected Var loadI(String text){
      Var v=popTmp(BuiltInTypeSymbol.INT);
      info("LOADI: " + v.getName() + " = " + Integer.parseInt(text) + ";\n");
      return v;
  };
    
  protected void info(String str){
      if(str.charAt(str.length()-1) == '\n'){
		      String txtTemps = "";      
		      for(TempVar v : tmps){
		        txtTemps += "" + (v.applied?" ":v.reg) + " ";
		      }
		      str ="|" +  txtTemps  + "|" + "\t\t" + str;
		      
      }
      System.out.print(str);
  }
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
  :   t=type id=ID{$list.add(new Var($id.text,$t.type));}
    ( ',' t=type id=ID{$list.add(new Var($id.text,$t.type));} 
     )* 
    ;
  
// *************   END  :  Class   *************


// *************   START:  BLOCK   *************
block
    :   '{' statement* '}' 
    ;
  
statement
    @init{tmps.clear();}
    @after{clearTmp();}
    :   block
    |   varDeclaration  ';'
    |   'return' expr?  ';'
    |   exprStatement   ';'
    |   ';' 
    ;   

varDeclaration
    :   type ID ('=' expr {set($ID.text,$type.type,$expr.v);} )? 
    ; 
        
exprStatement
    options {backtrack=true;}
    :   to=postfixexpr 
        ('=' from=expr  )?
        {   if(from==null){
                assert to.field == null;
                resolveTemp(to.v);
            } else if (to.field==null){
                set(to.v,from);
            } else {
                setField(to.v,to.field,from);
            }
        }
    ;
    
expr returns [Var v]
    :   e=addexpr {v = $e.v;}
    ;
    
addexpr returns [Var v]
    :   a=multexpr {v = a;}
        (   '+' b=multexpr  {v=add(v,b);}
        |   '-' c=multexpr  {v=sub(v,c);}
        )*
    ; // END:addexpr
    
multexpr returns [Var v]
    :   a=postfixExprValue {v=a;} 
        (   '*' b=postfixExprValue {v=mul(v,b);} 
        )*
    ;
    
postfixExprValue returns [Var v]
    :   p=postfixexpr {v=$p.v; if($p.field!=null){v=getField(v,$p.field);} }
    ;

postfixexpr returns [Var v,FieldSymbol field]
    :   (p=primary {$v = p;})
        ( options {backtrack=true;}
         : '.' mID=ID '()' {
	              if($field!=null){$v=getField($v,$field);$field=null;} 
	              if($v.type == BuiltInTypeSymbol.FLEX){
	                  $v.type = BuiltInTypeSymbol.FLEXCLASS;
	              }
	              if($v.applied)$v=move(popTmp($v.type),$v); 
                List<Var> list = new ArrayList<>();
	              list.add($v);
	              MethodSymbol m = new MethodSymbol((ClassSymbol)$v.type,$mID.text,BuiltInTypeSymbol.FLEX);  
	              $v = invoke($v,m,list);
	           }
         | '.' mID=ID '(' {if($field!=null){$v=getField($v,$field);$field=null;}} params=exprList[$v] ')' {                 
	                MethodSymbol m = new MethodSymbol((ClassSymbol)$v.type,$mID.text,BuiltInTypeSymbol.FLEX);
	                $v = invoke($v,m,params);
	              }
         | '.' fID=ID { 
                if($field!=null){$v=getField($v,$field);$field=null;} 
                $field=new FieldSymbol((ClassSymbol)$v.type,$fID.text,BuiltInTypeSymbol.FLEX); }
        )*
    ;
    
exprList[Var inst] returns [List<Var> list]
    @init{$list = new ArrayList<>(); if(inst.applied)inst=move(popTmp(inst.type),inst); list.add(inst);}    
    :   e=expr{if(e.applied)e=move(popTmp(e.type),e); list.add(e);} 
        (',' e=expr{if(e.applied)e=move(popTmp(e.type),e);list.add(e);})* | 
    ; 

primary returns [Var v] // START:atom
    :   ('new' type '()') {v = createObject((ClassSymbol)$type.type);}
    |   'this'          {v = v("this");}
    |   'super'         {v = v("super");} 
    |   INT             {v = loadI($INT.text);}
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