grammar Nebula;

options{
	output = AST;
	ASTLabelType = CommonTree; 
}

tokens{
	PROG; STAT; NUM; VAR; FIELD;
}

@header {package nebula.compiler;}
@lexer::header{package nebula.compiler;}

typeDef : TYPE NAME '{' NEWLINE? typeBody '}' terminator  -> ^(TYPE NAME typeBody);

typeBody : field* ;

field : NAME importance? multiple? ';' NEWLINE? -> ^(FIELD NAME importance? multiple?);

//Sample 
prog : typeDef ->  ^(PROG typeDef)
	| stat -> ^(PROG stat);

fragment stat : expr EOF  -> ^(STAT expr);
expr : multExpr (('+'|'-')^ multExpr )*;
multExpr : atom (('*'|'/')^ atom )*;
atom :   '(' expr ')' -> expr
	   | NUMBER -> ^(NUM NUMBER)
	   | NAME  -> ^(VAR NAME)	
;

importance : IMPORTANCE;
multiple : MULTIPLE;

terminator: NEWLINE | EOF;

/*
APOSTROPHE: '\''; // for derivative
ASSERT: 'assert';
ASSIGN: '=';
BACKSLASH: '\\'; // fon line continuation
CARET: '^'; // for exponentiation
FUNCTIONS: 'functions'; // for list command
HELP: '?' | 'help';
LEFT_PAREN: '(';
LIST: 'list';
PRINT: 'print';
RELATION: '<' | '<=' | '=' | '>=' | '>';
RIGHT_PAREN: ')';
*/
SIGN: '+' | '-';

//VARIABLES: 'variables'; // for list command


//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
TYPE : 'type';
IMPORTANCE : PIVOTAL | IMPORTANT | UNIMPORTANT;
MULTIPLE : ONE_OR_MORE | ANY | OPTIONALLY;

fragment PIVOTAL     : '!';
fragment IMPORTANT   : '#';
fragment UNIMPORTANT : '~';

fragment ONE_OR_MORE : '+';  // 1 | n
fragment ANY: '*';           // 0 | n
fragment OPTIONALLY : '?';   // 0 | 1

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

NUMBER: INTEGER | FLOAT;
fragment FLOAT: INTEGER '.' '0'..'9'+;
fragment INTEGER: '0' | SIGN? '1'..'9' '0'..'9'*;
NAME: LETTER (LETTER | DIGIT | '_')*;
STRING_LITERAL: '"' NONCONTROL_CHAR* '"';

// Note that NONCONTROL_CHAR does not include the double-quote character.
fragment NONCONTROL_CHAR: LETTER | DIGIT | SPACE ;//| SYMBOL | SPACE;
fragment LETTER: LOWER | UPPER;
fragment LOWER: 'a'..'z';
fragment UPPER: 'A'..'Z';
fragment DIGIT: '0'..'9';
fragment SPACE: ' ' | '\t';

// Note that SYMBOL omits the double-quote character,
// digits, uppercase letters and lowercase letters.
fragment SYMBOL: '!' | '#'..'/' | ':'..'@' | '['..'`' | '{'..'~';

// Windows uses \r\n. UNIX and Mac OS X use \n.
// To use newlines as a terminator,
// they can't be written to the hidden channel!
NEWLINE: ('\r'? '\n')+;
WHITESPACE: SPACE+ { $channel = HIDDEN; };

// Single-line comments begin with //, are followed by any characters
// other than those in a newline, and are terminated by new characters.
SINGLE_COMMENT: '//' ~('\r' | '\n')* NEWLINE { skip(); };

// Multi-line comments are delimited by /* and */
// and are optionally followed by newline characters.
MULTI_COMMENT
// The greedy option defaults to false for the .* and .+ patterns.
//options { greedy = false; }
  : '/*' .* '*/' NEWLINE? { skip(); };
