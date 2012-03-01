grammar Nebula;

options {
  language = Java;
}

// START:members
@lexer::header {package nebula.vm;}
@header {
package nebula.vm;
import nebula.vm.VariableSymbol;
}

@members {
  /** Map variable name to Integer object holding value */

  protected void enterClass(String name) {};
  protected void exitClass() {};
  
  protected void enterFunction(String name,String typeName) {;};
  protected void exitFunction() {;};
  
  protected void defineField(String name,String typeName){;};
  
  protected VariableSymbol resolve(String name) {return null;};
  protected VariableSymbol defineVariable(String name) {return null;};
  protected VariableSymbol defineInt(String name) {return null;};
    
  protected VariableSymbol eval(VariableSymbol a) {return null;};
  protected VariableSymbol evalSet(String id,VariableSymbol b) {return null;};

  protected VariableSymbol add(VariableSymbol a, VariableSymbol b) {return null;};
  protected VariableSymbol sub(VariableSymbol a, VariableSymbol b) {return null;};
  protected VariableSymbol mul(VariableSymbol a, VariableSymbol b) {return null;};  
  protected VariableSymbol load(VariableSymbol a, VariableSymbol b) {return null;};

}

// END:members

compilationUnit
    :   ( classDefinition | varDeclaration | methodDeclaration )+ EOF
    ;

// START: class
classDefinition
    :   'class' Identifier superClass? 
            {enterClass($Identifier.text);} 
         '{' classMember+ '}' ';'
            {exitClass();}         
    ;
    
superClass
  : ':' 'public' Identifier //-> ^(EXTENDS ID)
  ;
// END: class

classMember
  : type Identifier ('=' expression)? ';' {defineField($Identifier.text,$type.text);}
  | methodDeclaration
  | 'public' ':' //-> // throw away; just making input valid C++
  ;
  
// START: method
methodDeclaration
    :   type Identifier '(' formalParameters? ')' 
          {enterFunction($Identifier.text,$type.text);}
        block
          {exitFunction();}
        //-> ^(METHOD_DECL type ID formalParameters? block)
    ;
// END: method

formalParameters
    :   type Identifier (',' type Identifier)* //-> ^(ARG_DECL type ID)+
    ;

type:   'float'
    |   'int'
    | 'void'
    | Identifier // class type name
    ;

// START: block
block
    :   '{' statement* '}' ;
// END: block

// START: var
varDeclaration
    :   type Identifier ('=' expression)? ';' //-> ^(VAR_DECL type ID expression?)
    ;
// END: var


statement
    :   block
    | varDeclaration
    |   'return' expression? ';' 
    |   postfixExpression // handles function calls like f(i);
        (   '=' expression  )?
        ';' 
    | ';' 
    ;


expressionList
    :   expression (',' expression)* | ;
//END: expressionList


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
        (    
          '*' e=postfixExpression  {$value = mul($value,$e.value);} 
        )*
    ; 
// END:multExpression


// START: call
postfixExpression returns [VariableSymbol value]
    :   (e=primary{$value = $e.value;})
        ( options {backtrack=true;}
         : '.' Identifier '(' expressionList ')'// -> ^(CALL ^('.' $postfixExpression ID))
         | '.' Identifier             // -> ^('.' $postfixExpression ID)
         | '(' expressionList ')' //       -> ^(CALL $postfixExpression)
         | '[' Integer ']'
         | '[' expressionList ']'
        )*
    ;
// END: call

/*
suffix[VariableSymbol expr] returns [VariableSymbol value]
options {backtrack=true;}
  : '.' Identifier '(' expressionList ')'// -> ^(CALL ^('.' {$expr} ID))
  | '.' Identifier              //-> ^('.' {$expr} Identifier)
  | '(' expressionList ')'        //-> ^(CALL {$expr})
  ;
*/  

// START:atom
primary returns [VariableSymbol value]
    :   'this'
    |   'super'
        Integer {$value = defineInt($Integer.text);}
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