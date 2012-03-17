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
    ;
  
// *************   END  :  Class   *************
   

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
    ;
    
varDeclaration  // START: var
    :   type ID
        ('=' (
            f=ref_const {info("const " + f); }
            | f=expr  {info("expr " + f); }
            )
         )? ';' 
    ; // END: var

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
        )*
    ; // END:addexpr

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
fragment Digit :  '0'..'9';
fragment Letter : 'a'..'z' | 'A'..'Z';

NEWLINE:'\r'? '\n'  {$channel=HIDDEN;};    
Whitespace :  (' ' | '\t' | '\f')+ {$channel=HIDDEN;};    
SingleLineComment :
  '//' (~('\n'|'\r'))* ('\n'|'\r'('\n')?)? {$channel=HIDDEN;};
MultiLineComment :
    '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;};