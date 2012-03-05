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

  protected void enterClass(String name,Type superType) {};
  protected void exitClass() {;};
  
  protected void enterFunction(String name,Type returnType,List<Var> list) {;};
  protected void exitFunction() {;};
  
  protected void defField(String name,Type type){;};
  
  protected Type resolveType(String name){return null;};
    
  protected Var resolve(String name) {return null;};
  protected Var defVariable(String name,Type type) {return null;};
  protected Var defInt(String name) {return null;};
  
  protected Var invoke(Var name,String funcName,List<Var> list){return null;};
  protected Var invokeStatic(String name,List<Var> list){return null;};
  protected Var refField(Var obj,String text){return null;};  
  protected Var index(Var obj,Var i){return null;};
  protected Var index(Var obj,List<Var> cause){return null;};
  protected void ret(Var a) {;};
      
  protected Var eval(Var a) {return null;};
  protected Var evalSet(String id,Var b) {return null;};

  protected Var add(Var a, Var b) {return null;};
  protected Var sub(Var a, Var b) {return null;};
  protected Var mul(Var a, Var b) {return null;};  
  protected Var load(Var a, Var b) {return null;}; 
}// END:members

compilationUnit
    :   ( classDefinition )+ EOF
    ;

classDefinition // START: class
    :   'class' ID superClass? 
            {enterClass($ID.text,$superClass.type);} 
         '{' classMember+ '}'
            {exitClass();}         
    ;
    
superClass returns[Type type]
  : 'extends' id=ID {$type=resolveType($id.text);}
  ;

classMember
  : fieldDeclaration
  | methodDeclaration
  ; // END: class

fieldDeclaration  //TODO fieldDeclaration
    :   type ID ('=' e=expr)? ';' {defField($ID.text,$type.type);}
    ;

methodDeclaration // START: method
    :   type name=ID '(' params=formalParameters? ')' 
          {enterFunction($name.text,$type.type,$params.list);}
        block
          {exitFunction();}
    ; // END: method

formalParameters returns [List<Var> list]
    @init{$list = new ArrayList<>(); }    
    :   t=type id=ID{$list.add(defVariable($id.text,$t.type));}
        ( ',' t=type id=ID{$list.add(defVariable($id.text,$t.type));} 
        )* 
    ;

type returns [Type type]
    :   'decimal' {$type = BuiltInTypeSymbol.DECIMAL;}
    |   'int' {$type = BuiltInTypeSymbol.INT;}
    |   'void' {$type = BuiltInTypeSymbol.VOID;}
    |   ID {$type = resolveType($ID.text);}
    ;

block // START: block
    :   '{' statement* '}' 
    ; // END: block
    
varDeclaration  // START: var
    :   type ID ('=' e=expr)? ';' {
          defVariable($ID.text,$type.type);  
          evalSet($ID.text,$e.value);
        }
    ; // END: var

statement
    :   block
    |   varDeclaration
    |   'return' e=expr? ';' {ret($e.value);}
    |   postfixexpr
        (   '=' expr  )?
        ';' 
    | ';' 
    ;

exprList returns [List<Var> list] // START: exprList
    @init{$list = new ArrayList<>(); }    
    :   e=expr{list.add(e);} (',' expr{list.add(e);})* | 
    ; // END: exprList

expr returns [Var value]
    :   e=addexpr {$value = $e.value;} //-> ^(EXPR addexpr)
    ; // START:addexpr
    
addexpr returns [Var value]
    :   e=multexpr {$value = $e.value;}
        (   '+' e=multexpr {$value = add($value,$e.value);}
        |   '-' e=multexpr {$value = sub($value,$e.value);}
        )*
    ; // END:addexpr

multexpr returns [Var value]  // START:multexpr
    :   e=postfixexpr {$value = $e.value;} 
        (   '*' e=postfixexpr  {$value = mul($value,$e.value);} 
        )*
    ; // END:multexpr

postfixexpr returns [Var value] // START: call
    :   (e=primary{$value = $e.value;})
        ( options {backtrack=true;}
         : '.' name=ID '(' exprList ')' {$value = invoke($value,$name.text,$params.list);}
         | '.' name=ID            { $value = refField($value,$name.text);}
         | '(' params=exprList ')'  { $value = invokeStatic($e.text,$params.list);}
         | '[' INT ']'                { $value = index($value,defInt($INT.text));}
         | '[' cause=exprList ']'   { $value = index($value,$cause.list);}
        )*
    ; // END: call

primary returns [Var value] // START:atom
    :   id='this'{$value = resolve($id.text);}
    |   id='super'{$value = resolve($id.text);}
    |   INT {$value = defInt($INT.text);}
    |   ID {$value = resolve($ID.text);}
    |   '(' expr ')' {$value = $expr.value;}
    ; // END:atom

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