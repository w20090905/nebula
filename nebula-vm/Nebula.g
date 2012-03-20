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
}// END:members

compilationUnit
    :   ( classDefinition )+ EOF
    ;
    
// *************   START:  Class   *************
classDefinition
    :   'class' ID superClass? 
            {enterClass($ID.text,$superClass.type);} 
         '{' classMember+ '}'
            {exitClass();}         
    ;

superClass returns[Type type]
  : 'extends' ID {$type=resolveType($ID.text);}
  ;
  
classMember
  : fieldDeclaration | methodDeclaration
  ; 

fieldDeclaration
  :   type ID ('=' e=expr)? ';' {defineField($ID.text,$type.type);}
  ;
  
methodDeclaration // START: method
  :   type name=ID 
      ('()' {enterFunction($name.text,$type.type,new ArrayList<Var>());}
       | ('(' formalParameters? ')') {enterFunction($name.text,$type.type,$formalParameters.list);}
      )        
      block
        {exitFunction();}
  ; // END: method

formalParameters returns [List<Var> list]
  @init{$list = new ArrayList<>(); }    
  :   t=type id=ID{$list.add(new Param($id.text,$t.type));}
    ( ',' t=type id=ID{$list.add(new Param($id.text,$t.type));} 
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
    @after{clearTmp();}
    :   block
    |   varDeclaration  ';' {clearTmp();}
    |   'return' expr?  ';' {clearTmp();}
    |   exprStatement   ';' {clearTmp();}
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
         : '.' mID=ID '()' {if($field!=null){$v=getField($v,$field);$field=null;} MethodSymbol m = new MethodSymbol((ClassSymbol)$v.type,$mID.text);  $v = invoke($v,m,new ArrayList<Var>());}
         | '.' mID=ID '(' params=exprList ')' {if($field!=null){$v=getField($v,$field);$field=null;} MethodSymbol m = new MethodSymbol((ClassSymbol)$v.type,$mID.text);  $v = invoke($v,m,params);}
         | '.' fID=ID { if($field!=null){$v=getField($v,$field);$field=null;} $field=new FieldSymbol((ClassSymbol)$v.type,$fID.text); }
        )*
    ;
    
exprList returns [List<Var> list]
    @init{$list = new ArrayList<>(); }    
    :   e=expr{list.add(e);} 
        (',' e=expr{list.add(e);})* | 
    ; 

primary returns [Var v] // START:atom
    :   ('new' type '()') {v = createObject($type.type);}
    |   'this'          {v = locals.get("this");}
    |   'super'         {v = locals.get("super");} 
    |   INT             {v = loadI($INT.text);}
    |   ID              {v = locals.get($ID.text);}
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