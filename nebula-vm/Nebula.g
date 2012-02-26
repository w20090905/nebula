grammar Nebula;

// START:members
@lexer::header {package nebula.vm;}
@header {
package nebula.vm;
import nebula.vm.VariableSymbol;
}

@members {
  /** Map variable name to Integer object holding value */

	protected void gen(short op) {;}
	protected void gen(short op, short a) {;}
	protected void gen(short op, short a, short b) {;}
	protected void gen(short op, short a, short b, short c) {;}
	protected void gen(short op, short a, int bx) {;}
	
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

// START:stat
program:   stat+ ;
                
stat:   // evaluate expr and emit result
        // $expr.value is return attribute 'value' from expr call
        expr NEWLINE   { eval($expr.value);}

        // match assignment and stored value
        // $ID.text is text property of token matched for ID reference
    |   ID '=' expr NEWLINE  {evalSet($ID.text,$expr.value);}
        

        // do nothing: empty statement
    |   NEWLINE
    ;
// END:stat

// START:expr
/** return value of multExpr or, if '+'|'-' present, return
 *  the addition or subtraction of results from both multExpr references.
 */
expr returns [VariableSymbol value]
    :   e=multExpr {$value = $e.value;}
        (   '+' e=multExpr {$value = add($value,$e.value);}
        |   '-' e=multExpr {$value = sub($value,$e.value);}
        )*
    ;
// END:expr

// START:multExpr
/** return the value of an atom or, if '*' present, return
 *  multiplication of results from both atom references.
 *  $value is the return value of this method, $e.value
 *  is the return value of the rule labeled with e.
 */
multExpr returns [VariableSymbol value]
    :   e=atom {$value = $e.value;} 
    ('*' e=atom  {$value = mul($value,$e.value);}
    )*
    ; 
// END:multExpr

// START:atom
atom returns [VariableSymbol value]
    :   // value of an INT is the int computed from char sequence
        INT {$value = defineInt($INT.text);}

    |   ID {$value = resolve($ID.text);}// variable reference
//        {
//        // look up value of variable
//        Integer v = (Integer)memory.get($ID.text);
//        // if found, set return value else error
//        if ( v!=null ) $value = v.intValue();
//        else System.err.println("undefined variable "+$ID.text);
//        }

        // value of parenthesized expression is just the expr value
    |   '(' expr ')' {$value = $expr.value;}
    ;
// END:atom

// START:tokens
ID  :   ('a'..'z'|'A'..'Z')+ ;
INT :   '0'..'9'+ ;
NEWLINE:'\r'? '\n' ;
WS  :   (' '|'\t')+ {skip();} ;
// END:tokens
