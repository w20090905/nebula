grammar Nebula;

options{
	output = AST;
	k = 5;
	ASTLabelType = CommonTree; 
}

tokens{
	PROG; STAT; NUM; VAR; FIELD; RANGE;
}

@header {package nebula.compiler;}
@lexer::header{package nebula.compiler;}


typeDefinition : TYPE NAME '{' NEWLINE? (fieldDefinition NEWLINE*)* '}' terminator  -> ^(TYPE NAME fieldDefinition*);

fieldDefinition : NAME IMPORTANCE? cardinaltiry? reference? ';' -> ^(FIELD NAME IMPORTANCE? cardinaltiry? NAME?);

reference 	:BYREF NAME  -> ^(BYREF NAME)|
		 BYVAL NAME  -> ^(BYVAL NAME)|
		 NAME -> ^(BYVAL NAME);
//Sample 
prog : typeDefinition* ->  ^(PROG typeDefinition*)
	| stat -> ^(PROG stat);

fragment stat : expr EOF  -> ^(STAT expr);
expr : multExpr (('+'|'-')^ multExpr )*;
multExpr : atom (('*'|'/')^ atom )*;
atom :   '(' expr ')' -> expr
	   | INTEGER -> ^(NUM INTEGER)
	   | NAME  -> ^(VAR NAME)	
;

terminator: NEWLINE | EOF;

cardinaltiry: '[]' -> ^(RANGE) |
	'[' '..' INTEGER ']' -> ^(RANGE '..' INTEGER) |
	'[' INTEGER ('..' INTEGER?)?  ']' -> ^(RANGE INTEGER '..'? INTEGER?);

/*

cardinaltiry : '[]' -> ^(RANGE)| 
	'[' integer_range (',' integer_range)* ']' -> integer_range*;

integer_range: '..' INTEGER -> ^(RANGE '..' INTEGER) |
	INTEGER ('..' INTEGER?)? -> ^(RANGE INTEGER '..'? INTEGER?);

    '[]' |                          // 0~*
    '[..' INTEGER ']' |             // 0~m
//    '[' INTEGER '..]'|// n~m
    '[' INTEGER ('..' INTEGER?)  ']'|   // n~m
    '[' INTEGER (','  INTEGER)+  ']' |
    '[' INTEGER ']'                 // n
    ;
  */  
/*CARDINALITY : ONE_OR_MORE | ANY | OPTIONALLY;
//===============================================
//===============================================
//===============================================
//===============================================
//===============================================
//===============================================*/

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
//SIGN: '+' | '-';

//VARIABLES: 'variables'; // for list command


//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
TYPE : 'type';
BYREF : 'byref';
BYVAL :	'byval';
IMPORTANCE : KEY | IMPORTANT | REQUIRE | UNIMPORTANT;

fragment KEY         : '!';
fragment IMPORTANT   : '*';
fragment REQUIRE     : '#';
fragment UNIMPORTANT : '?';


//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

//number: INTEGER | flort;
//flort: INTEGER '.' '0'..'9'+;
INTEGER: '0' | ('+'|'-')? '1'..'9' '0'..'9'*;
NAME: LETTER (LETTER | DIGIT | '_')*;
STRING_LITERAL: '"' NONCONTROL_CHAR* '"';

// Note that NONCONTROL_CHAR does not include the double-quote character.
fragment NONCONTROL_CHAR: LETTER | DIGIT | SPACE; //SYMBOL
fragment LETTER: LOWER | UPPER;
fragment LOWER: 'a'..'z';
fragment UPPER: 'A'..'Z';
fragment DIGIT: '0'..'9';
fragment SPACE: ' ' | '\t';

// Note that SYMBOL omits the double-quote character,
// digits, uppercase letters and lowercase letters.
//fragment SYMBOL: '!'; | '#'..'/' | ':'..'@' | '['..'`' | '{'..'~';

// Windows uses \r\n. UNIX and Mac OS X use \n.
// To use newlines as a terminator,
// they can't be written to the hidden channel!
NEWLINE: ('\r'? '\n');
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
