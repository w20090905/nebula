grammar Neb;

options{	
	ASTLabelType = CommonTree; 
}
@header {package test.systemio.antlr.neb;}
@lexer::header{package test.systemio.antlr.neb;}


echo	: (state|emptyLine)*;

state	: id{System.out.println("==>" + $id.text);} NEWLINE;

emptyLine
	:NEWLINE;

id 	: NAME;


NAME: LETTER (LETTER | DIGIT | '_')* ;

fragment LETTER: LOWER | UPPER;
fragment LOWER: 'a'..'z';
fragment UPPER: 'A'..'Z';
fragment DIGIT: '0'..'9';
fragment SPACE: ' ' | '\t';

NEWLINE: ('\r'? '\n');
WHITESPACE: SPACE+ { $channel = HIDDEN; };
