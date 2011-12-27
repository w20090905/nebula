grammar Expr;

options{
	output = AST;
	ASTLabelType = CommonTree;
}

tokens{
	PROG; STAT; NUM; VAR; TYPE; FIELD;
}

@header {package compiler;}
@lexer::header{package compiler;}

type : KEY_TYPE ID '{' terminator typebody '}' terminator  -> ^(TYPE ID typebody);

typebody : field ( ',' (terminator)? field )*  (terminator)? ;

field : ID modifier? multiple?  -> ^(FIELD ID);



prog : type ->  ^(PROG type)
	| stat -> ^(PROG stat);

fragment stat : expr EOF  -> ^(STAT expr)
;

expr : multExpr (('+'|'-')^ multExpr )*
;

multExpr : atom (('*'|'/')^ atom )*
;

atom :   '(' expr ')' -> expr
	   | INT -> ^(NUM INT)
	   | ID  -> ^(VAR ID)	
;

modifier : PIVOTAL | IMPORTANT | UNIMPORTANT;
multiple : ONE-OR-MORE | ANY | OPTIONALLY;

terminator: NEWLINE | EOF;
PIVOTAL     : '!';
IMPORTANT   : '#';
UNIMPORTANT : '-';

ONE-OR-MORE : '+';  // 1 | n
ANY: '*';           // 0 | n
OPTIONALLY : '?';   // 0 | 1

fragment SPACE: ' ' | '\t';
WHITESPACE: SPACE+ { $channel = HIDDEN; };

KEY_TYPE : 'type';
ID : ('a'..'z' |'A'..'Z' | '-' | '_')+ ;

INT : '0'..'9' + ;
NEWLINE:'\r' ? '\n' ;

