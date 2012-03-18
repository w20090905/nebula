grammar Nebula;

options {
  language = Java;
}
@lexer::header {package nebula.vm;}
@header {
package nebula.vm;
import nebula.vm.Var;
import nebula.vm.Type;
}

@members {  // START:members
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
  :   type name=ID '(' params=formalParameters? ')' 
        {enterFunction($name.text,$type.type,$params.list);}
      block
        {exitFunction();}
  ; // END: method

formalParameters returns [List<Var> list]
  @init{$list = new ArrayList<>(); }    
  : t=type id=ID{$list.add(new Param($id.text,$t.type));}
    ( ',' t=type id=ID{$list.add(new Param($id.text,$t.type));} 
     )* 
    ;
  
// *************   END  :  Class   *************


// *************   START:  BLOCK   *************
block // START: block
    @init{info("Block{\n");}
    @after{info("}Block\n");}
    :   '{' statement* '}' 
    ; // END: block
  
statement
    :   block
    |   varDeclaration  ';' {info("\n");}
    |   'return' expr?  ';' {info("\n");}
    |   exprStatement   ';' {info("\n");}
    |   ';' 
    ;   

varDeclaration
    :   type ID
        ('=' v=expr  {
              v.name = $ID.text;
              v.type = $type.type; 
              push(v);
            }
         )? 
    ; 
    
exprStatement
    options {backtrack=true;}
    :   (to=postfixexpr '=' from=expr  )
        | to=postfixexpr
    ;
    
expr returns [Var value]
    :   e=addexpr {$value = $e.value;}
    ;
    
addexpr returns [Var value]
    :   a=multexpr {$value = a;}
        (   '+' b=multexpr  {$value=add(a,b);}
        |   '-' c=multexpr  {$value=sub(a,c);}
        )*
    ; // END:addexpr
    
multexpr returns [Var value]
    :   a=postfixexpr {$value=a;} 
        (   '*' b=postfixexpr {$value=mul(a,b);} 
        )*
    ;

postfixexpr returns [Var value] // START: call
    :   (e=primary{$value = $e.value;})
        ( options {backtrack=true;}
         : '.' method=refMethod[$value.type] '(' params=exprList ')' {$value = invoke($value,method,params);}
         | '.' field=refField[$value.type] { $value = getField($value,field); }
        )*
    ; // END: call
    
refMethod[Type objType] returns [MethodSymbol value]
    : ID {$value =new MethodSymbol((ClassSymbol)objType,$ID.text);}
    ;
    
refField[Type objType] returns [FieldSymbol value]
    : ID{$value = new FieldSymbol((ClassSymbol)objType,$ID.text);}
    ;

exprList returns [List<Var> list]
    @init{$list = new ArrayList<>(); }    
    :   e=expr{list.add(e);} 
        (',' expr{list.add(e);})* | 
    ; 

primary returns [Var value] // START:atom
    :   ('new' type '(' ps=exprList ')'){;} // TODO new object
    |   ref=ref_this      {$value = ref;}
    |   ref=ref_super     {$value = ref;}
    |   ref=ref_const     {$value = ref;}
    |   ID                {$value = locals.get($ID.text);}
    |   '(' v=expr ')'    {$value = v;}
    ; // END:atom
    
// *************   END  :  BLOCK   *************

// *************   START:  BASIC   *************

ref_this returns [Var value]
    :   'this'{$value = locals.get("this");}
    ;

ref_super returns [Var value]
    :   'super'{$value = locals.get("super");}
    ;
    
ref_const returns [Var value]
    :   INT {$value = loadI($INT.text);}
    ;
    
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