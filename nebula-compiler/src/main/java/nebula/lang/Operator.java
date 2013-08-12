package nebula.lang;

public enum Operator {
	INIT,

	/* Unary Operators */
	INC, // '++';
	DEC, // '--';
	NOT, // '!';

	/* Equality and Relational Operators */
	EQ, // '==';
	NE, // '!=';
	GE, // '>=';
	GT, // '>';
	LE, // '<=';
	LT, // '<';

	/* Conditional Operators */
	AND, // '&&';
	OR, // '||';

	/* Arithmetic Operators */
	ADD, // '+';
	SUB, // '-';
	MUL, // '*';
	DIV, // '/';
	REM, // '%';

	/* Simple Assignment Operator */
	ASSIGN // '=';
}
