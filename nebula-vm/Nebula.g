grammar Nebula;

options {
  language = Java;
}

@lexer::header {package nebula.vm;}
@header {
package nebula.vm;
import nebula.vm.VariableSymbol;
import nebula.vm.Type;
}

// START:members
@members {
  /** Map variable name to Integer object holding value */

  protected void enterClass(String name,Type superType) {};
  protected void exitClass() {};
  
  protected void enterFunction(String name,Type returnType,List<VariableSymbol> list) {;};
  protected void exitFunction() {;};
  
  protected void defineField(String name,Type type){;};
  
  protected Type resolveType(String name){return null;};
    
  protected VariableSymbol resolve(String name) {return null;};
  protected VariableSymbol defineVariable(String name,Type type) {return null;};
  protected VariableSymbol defineInt(String name) {return null;};
  
  protected VariableSymbol call(VariableSymbol name,List<VariableSymbol> list){return null;};
  protected VariableSymbol getField(VariableSymbol obj,String text){return null;};  
  protected VariableSymbol index(VariableSymbol obj,VariableSymbol i){return null;};
  protected VariableSymbol index(VariableSymbol obj,List<VariableSymbol> cause){return null;};
  protected void ret(VariableSymbol a) {;};
  
    
  protected VariableSymbol eval(VariableSymbol a) {return null;};
  protected VariableSymbol evalSet(String id,VariableSymbol b) {return null;};

  protected VariableSymbol add(VariableSymbol a, VariableSymbol b) {return null;};
  protected VariableSymbol sub(VariableSymbol a, VariableSymbol b) {return null;};
  protected VariableSymbol mul(VariableSymbol a, VariableSymbol b) {return null;};  
  protected VariableSymbol load(VariableSymbol a, VariableSymbol b) {return null;}; 
}

// END:members

compilationUnit
    :   ( classDefinition )+ EOF
    ;

// START: class
classDefinition
    :   'class' Identifier superClass? 
            {enterClass($Identifier.text,$superClass.type);} 
         '{' classMember+ '}'
            {exitClass();}         
    ;
    
superClass returns[Type type]
  : 'extends' id=Identifier {$type=resolveType($id.text);}
  ;

classMember
  : fieldDeclaration
  | methodDeclaration
  ;  
// END: class

//TODO fieldDeclaration
fieldDeclaration
    :   type ID=Identifier ('=' expression)? ';' {defineField($ID.text,$type.type);}
    ;
  
// START: method
methodDeclaration
    :   type name=Identifier '(' params=formalParameters? ')' 
          {enterFunction($name.text,$type.type,$params.list);}
        block
          {exitFunction();}
    ;
// END: method

formalParameters returns [List<VariableSymbol> list]
    @init{$list = new ArrayList<>(); }    
    :   t=type ID=Identifier{$list.add(defineVariable($ID.text,$t.type));}
        ( ',' t=type ID=Identifier{$list.add(defineVariable($ID.text),$t.type);} 
        )* 
    ;

type returns [Type type]
    :   'decimal' {$type = BuiltInTypeSymbol.DECIMAL;}
    |   'int' {$type = BuiltInTypeSymbol.INT;}
    |   'void' {$type = BuiltInTypeSymbol.VOID;}
    |   ID=Identifier {$type = resolveType($ID.text);}
    ;

// START: block
block
    :   '{' statement* '}' ;
// END: block

    
// START: var
varDeclaration
    :   type ID=Identifier ('=' e=expression)? ';' {evalSet($ID.text,$e.value);}
    ;
// END: var


statement
    :   block
    |   varDeclaration
    |   'return' e=expression? ';' {ret($e.value);}
    |   postfixExpression // handles function calls like f(i);
        (   '=' expression  )?
        ';' 
    | ';' 
    ;

// START: expressionList
expressionList returns [List<VariableSymbol> list]
    @init{$list = new ArrayList<>(); }    
    :   e=expression{list.add(e);} (',' expression{list.add(e);})* | ;
// END: expressionList


expression returns [VariableSymbol value]
    :   e=addExpression {$value = $e.value;} //-> ^(EXPR addExpression)
    ;

// START:addExpression
addExpression returns [VariableSymbol value]
    :   e=multExpression {$value = $e.value;}
        (   '+' e=multExpression {$value = add($value,$e.value);}
        |   '-' e=multExpression {$value = sub($value,$e.value);}
        )*
    ;
// END:addExpression

// START:multExpression
multExpression returns [VariableSymbol value]
    :   e=postfixExpression {$value = $e.value;} 
        (   '*' e=postfixExpression  {$value = mul($value,$e.value);} 
        )*
    ; 
// END:multExpression


// START: call
postfixExpression returns [VariableSymbol value]
    :   (e=primary{$value = $e.value;})
        ( options {backtrack=true;}
         : '.' name=Identifier '(' expressionList ')' {$value = call(getField($value,$name.text),$params.list);}
         | '.' name=Identifier            { $value = getField($value,$name.text);}
         | '(' params=expressionList ')'  { $value = call($value,$params.list);}
         | '[' Integer ']'                { $value = index($value,defineInt($Integer.text));}
         | '[' cause=expressionList ']'   { $value = index($value,$cause.list);}
        )*
    ;
// END: call

// START:atom
primary returns [VariableSymbol value]
    :   ID='this'{$value = resolve($ID.text);}
    |   ID='super'{$value = resolve($ID.text);}
    |   Integer {$value = defineInt($Integer.text);}
    |   Identifier {$value = resolve($Identifier.text);}
    |   '(' expression ')' {$value = $expression.value;}
    ;
// END:atom

Identifier :  Letter (Letter | Digit)*;  
Integer :  Digit Digit*;
fragment Digit :  '0'..'9';
fragment Letter : 'a'..'z' | 'A'..'Z';

NEWLINE:'\r'? '\n'  {$channel=HIDDEN;};    
Whitespace :  (' ' | '\t' | '\f')+ {$channel=HIDDEN;};    
SingleLineComment :
  '//' (~('\n'|'\r'))* ('\n'|'\r'('\n')?)? {$channel=HIDDEN;};
MultiLineComment :
    '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;};