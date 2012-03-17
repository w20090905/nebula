grammar Nebula;

options {
  language = Java;
}

// START:members
@lexer::header {package nebula.vm;}
@header {
package nebula.vm;
import nebula.vm.VariableSymbol;
import nebula.vm.Type;
}

<<<<<<< HEAD
@members {  // START:members
  /** Map variable name to INT object holding value */
  Map<String,Var> locals = new HashMap<>();
  short maxLocals = 0;

  protected void push(Var var) {
    locals.put(var.name,var);
    var.reg = (short) (locals.size() - 1);
    maxLocals = maxLocals > (short) locals.size() ? maxLocals : (short) locals.size();
  } 
  protected Var v(String name) {
    for (int i = 0; i < locals.size(); i++) {
      if (locals.get(i).getName().equals(name)) {
        return locals.get(i);
      }
    }
    return null;
  };
=======
@members {
  /** Map variable name to Integer object holding value */
>>>>>>> 4f361c897c997c9b3a550342d78c0a89882573c9

  protected void enterClass(String name) {};
  protected void exitClass() {};
  
  protected void enterFunction(String name,Type type) {;};
  protected void exitFunction() {;};
<<<<<<< HEAD
    
  protected void defField(String name,Type type){;};
  
  protected Type resolveType(String name){return null;};
 
  protected Var add(Var a, Var b) {return null;};
  protected Var sub(Var a, Var b) {return null;};
  protected Var mul(Var a, Var b) {return null;};  
  protected Var load(Var a, Var b) {return null;}; 
  
  protected Var top=null;
  
  protected void info(String str){
      System.out.println(str);
  }
}// END:members
=======
  
  protected void defineField(String name,Type type){;};
  
  protected Type resolveType(String name){return null;};
    
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
>>>>>>> 4f361c897c997c9b3a550342d78c0a89882573c9

compilationUnit
    :   ( classDefinition | fieldDeclaration | methodDeclaration )+ EOF
    ;
<<<<<<< HEAD
    
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
  :   type ID ('=' e=expr)? ';'
  ;
   
methodDeclaration // START: method
  :   type name=ID '(' params=formalParameters? ')' 
        {enterFunction($name.text,$type.type,$params.list);}
      block
        {exitFunction();}
  ; // END: method

formalParameters returns [List<Var> list]
  @init{$list = new ArrayList<>(); }    
  : t=type id=ID{$list.add(new Var(Var.PARAM,$id.text,$t.type));}
    ( ',' t=type id=ID{$list.add(new Var(Var.PARAM,$id.text,$t.type));} 
     )* 
=======

// START: class
classDefinition
    :   'class' Identifier superClass? 
            {enterClass($Identifier.text);} 
         '{' classMember+ '}'
            {exitClass();}         
    ;
    
superClass
  : ':' 'public' Identifier //-> ^(EXTENDS ID)
  ;
// END: class

classMember
  : type e=Identifier ('=' expression)? ';' {defineField($e.text,$type.type);}
  | methodDeclaration
  | 'public' ':' //-> // throw away; just making input valid C++
  ;
  
// START: method
methodDeclaration
    :   type Identifier '(' formalParameters? ')' 
          {enterFunction($Identifier.text,$type.type);}
        block
          {exitFunction();}
        //-> ^(METHOD_DECL type ID formalParameters? block)
    ;
// END: method

formalParameters
    :   type Identifier (',' type Identifier)* //-> ^(ARG_DECL type ID)+
    ;

type returns [Type type]
    :   'decimal' {$type = BuiltInTypeSymbol.DECIMAL;}
    |   'int' {$type = BuiltInTypeSymbol.INT;}
    |   'void' {$type = BuiltInTypeSymbol.VOID;}
    |   ID=Identifier {$type = resolveType($ID.text);}
>>>>>>> 4f361c897c997c9b3a550342d78c0a89882573c9
    ;
  
// *************   END  :  Class   *************
   

<<<<<<< HEAD
// *************   START:  BLOCK   *************
block // START: block
    :   '{' statement* '}' 
    ; // END: block
  
statement
    :   block
    |   varDeclaration
    |   'return' expr? ';' {;}
    |   exprStatement ';' 
    |   ';' 
=======
// START: block
block
    :   '{' statement* '}' ;
// END: block

fieldDeclaration
    :   type ID=Identifier ('=' expression)? ';' {defineField($ID.text,$type.type);}//-> ^(VAR_DECL type ID expression?)
    ;
    
// START: var
varDeclaration
    :   type ID=Identifier ('=' e=expression)? ';' {evalSet($ID.text,$e.value);}//-> ^(VAR_DECL type ID expression?)
    ;
// END: var


statement
    :   block
    |   varDeclaration
    |   'return' expression? ';' 
    |   postfixExpression // handles function calls like f(i);
        (   '=' expression  )?
        ';' 
    | ';' 
>>>>>>> 4f361c897c997c9b3a550342d78c0a89882573c9
    ;
    
varDeclaration  // START: var
    :   type ID
        ('=' (
            f=ref_const {info("const " + f); }
            | f=expr  {info("expr " + f); }
            )
         )? ';' 
    ; // END: var

<<<<<<< HEAD
exprStatement
@init{;}
    :   to=postfixexpr{}
        (   '=' from=expr  )?;
        
expr returns [Var value]
    :   e=addexpr {$value = $e.value;} //-> ^(EXPR addexpr)
    ; // START:addexpr
    
addexpr returns [Var value]
    :   e=multexpr {$value = e;}
        (   '+' e=multexpr {$value = add($value,$e.value);}
        |   '-' e=multexpr {$value = sub($value,$e.value);}
=======

expressionList
    @init{
          ArrayList list = new ArrayList();
    }
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
>>>>>>> 4f361c897c997c9b3a550342d78c0a89882573c9
        )*
    ;
// END:addExpression

<<<<<<< HEAD
multexpr returns [Var value]  // START:multexpr
    :   e=postfixexpr {;} 
        (   '*' e=postfixexpr  {;} 
        )*
    ; // END:multexpr
    
postfixexpr returns [Var value,boolean isDone,String refItem] // START: call
    @init{$isDone = true;}
    :  (e=primary{$value = $e.value;})
        ( options {backtrack=true;}
         : '.' name=ID '(' params=exprList ')' {;}
         | '.' name=ID                  {;}
         | '[' INT ']'                  {;}
         | '[' cause=exprList ']'       {;}
        )*
    ; // END: call

exprList returns [List<Var> list]
    @init{$list = new ArrayList<>(); }    
    :   e=expr{list.add(e);} (',' expr{list.add(e);})* | 
    ; 
    
primary returns [Var value] // START:atom
    :   ('new' type '(' ps=exprList ')'){;}
    |   ref=ref_this      {$value = ref;}
    |   ref=ref_super     {$value = ref;}
    |   ref=ref_const     {$value = ref;}
    |   ID          {$value =locals.get($ID.text);}
    |   '(' v=expr ')'{$value = v;}
    ; // END:atom
    

ref_this returns [Var value]
    :   'this'{$value = locals.get("this");}
    ;

ref_super returns [Var value]
    :   'super'{$value = locals.get("super");}
    ;
    
ref_const returns [Var value]
    :   INT {$value = new IConst($INT.text);}
    ;
    
    
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
=======
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

// START:atom
primary returns [VariableSymbol value]
    :   'this'
    |   'super'
    |   Integer {$value = defineInt($Integer.text);}
    |   Identifier {$value = resolve($Identifier.text);}
    |   '(' expression ')' {$value = $expression.value;}
    ;
// END:atom


Identifier :  Letter (Letter | Digit)*;  
Integer :  Digit Digit*;
>>>>>>> 4f361c897c997c9b3a550342d78c0a89882573c9
fragment Digit :  '0'..'9';
fragment Letter : 'a'..'z' | 'A'..'Z';

NEWLINE:'\r'? '\n'  {$channel=HIDDEN;};    
Whitespace :  (' ' | '\t' | '\f')+ {$channel=HIDDEN;};    
SingleLineComment :
  '//' (~('\n'|'\r'))* ('\n'|'\r'('\n')?)? {$channel=HIDDEN;};
MultiLineComment :
    '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;};