tree grammar NebulaTree;

options {
	tokenVocab=Nebula;  
	ASTLabelType=CommonTree;
}

@header {package nebula.compiler;}

typeDefinition scope { String name;} :
	^(TYPE n=NAME{$typeDefinition::name = $n.text; System.out.println("type: " + n);} fieldDefinition*);
	
fieldDefinition : 
	^(FIELD n=NAME IMPORTANCE? CARDINALITY?){System.out.println("field: " + n + " in Type " + $typeDefinition::name);};

prog : 
	^(PROG typeDefinition*) {System.out.println("RET");}
  	|^(PROG stat* ) {System.out.println("RET");};


stat 
: ^(STAT e=expr)  
;

expr 
:     	  ^('+' e1=expr e2=expr) {System.out.println("ADD");}
	| ^('-' e1=expr e2=expr) {System.out.println("SUB");}
	| ^('*' e1=expr e2=expr) {System.out.println("MUL");}
	| ^('/' e1=expr e2=expr) {System.out.println("DIV");}
	| a=atom  
;

atom  
:      ^(NUM i=NUMBER) {System.out.println("LDC "+i.getText());}
 	|  ^(VAR v=NAME)  {System.out.println("LDV "+v.getText());}
;