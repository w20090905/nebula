grammar Nebula;

options {
  language = Java;
}
@lexer::header {package nebula.vm;}
@header {
package nebula.vm;
}

@members {  // START:members
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
  
  
  protected ClassSymbol enterClass(String name,Type superType)  {return new ClassSymbol(name);/*TODO add super class support*/};
  protected void exitClass() {;};
  
  protected MethodSymbol enterFunction(ClassSymbol clz, String name,Type returnType,List<Var> list) {
      MethodSymbol m = new MethodSymbol(clz, name,returnType);
      maxLocals = 0;
      
      Var varThis = new Var("this",clz);
      pushLocal(varThis);
      return m;
  };
  protected void exitFunction() {;};

  protected FieldSymbol defineField(ClassSymbol clz,String name,Type type){
      info("define field " + name + "\n");
      return new FieldSymbol(clz,name,type);
  };
  
  protected Type resolveType(String name){return new ClassSymbol(name);};
 
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
      info("CALL : " + var.getName() + " = " + obj.getName() + "." + method.definedClass.getName() + "_" +  method.name + "("  + txtParams +  ");\n"); 
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
}// END:members

compilationUnit
    :   ( classDefinition )+ EOF
    ;
    
// *************   START:  Class   *************
classDefinition returns[ClassSymbol clz]
    :   'class' ID superClass? 
            {clz=enterClass($ID.text,$superClass.type);} 
         '{' classMember[clz]+ '}'
            {exitClass();}         
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
      ('()' {m=enterFunction(clz, $name.text, $type.type, new ArrayList<Var>());}
       | ('(' formalParameters? ')') {m=enterFunction(clz, $name.text, $type.type, $formalParameters.list);}
      )        
      block
        {exitFunction();}
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
    @init{info("Block{\n");}
    @after{info("\n}Block\n");}
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
                releaseTmp(to.v);
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
	              MethodSymbol m = new MethodSymbol((ClassSymbol)$v.type,$mID.text,BuiltInTypeSymbol.FLEX);  
	              $v = invoke($v,m,new ArrayList<Var>());
	           }
         | '.' mID=ID '(' params=exprList ')' {
                if($field!=null){$v=getField($v,$field);$field=null;} 
                MethodSymbol m = new MethodSymbol((ClassSymbol)$v.type,$mID.text,BuiltInTypeSymbol.FLEX);
                $v = invoke($v,m,params);
             }
         | '.' fID=ID { 
                if($field!=null){$v=getField($v,$field);$field=null;} 
                $field=new FieldSymbol((ClassSymbol)$v.type,$fID.text,BuiltInTypeSymbol.FLEX); }
        )*
    ;
    
exprList returns [List<Var> list]
    @init{$list = new ArrayList<>(); }    
    :   e=expr{if(e.applied)e=move(popTmp(e.type),e); list.add(e);} 
        (',' e=expr{if(e.applied)e=move(popTmp(e.type),e);list.add(e);})* | 
    ; 

primary returns [Var v] // START:atom
    :   ('new' type '()') {v = createObject($type.type);}
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