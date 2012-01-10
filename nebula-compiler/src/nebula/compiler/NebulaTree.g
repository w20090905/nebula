tree grammar NebulaTree;

options {
	tokenVocab=Nebula;  
	ASTLabelType=CommonTree;
}

@header {
	package nebula.compiler;
	import java.util.HashMap;
	import java.util.Map;
	
	import nebula.compiler.Type;
	import nebula.compiler.Field;
}

@members {
	Map<String,Type> types = new HashMap();
}

typeDefinition scope { Type type;} :
	^(TYPE 
		n=NAME		{$typeDefinition::type = new Type($n.text);}		
		fieldDefinition*
		)	
		{
			types.put($typeDefinition::type.getName(),$typeDefinition::type);
			System.out.println("type: " + $typeDefinition::type);
		};
	
fieldDefinition @init{ Field field;} : 
	^(FIELD 
		n=NAME		{field = new Field($typeDefinition::type,$n.text);}	
		i=IMPORTANCE?	
		{	
			String t = $i.text;
			char c;
			if(t!=null) c = t.charAt(0);
			else  c = '#';

			
			switch(c){
				case '!':
					field.importance = Field.KEY;
					break;
				case '*':
					field.importance = Field.IMPORTANT;
					break;
				case '#':
				case 0:  
					field.importance = Field.REQUIRE;
					break;
				case '?': 
					field.importance = Field.UNIMPORTANT;
					break;     
			}
		} 
		cardinaltiry[field]? 
		reference?
	);

reference :	^(BYREF NAME)|
		^(BYVAL NAME);

cardinaltiry [Field field]: ^(RANGE from=INTEGER? s='..'? to=INTEGER?) 
	{
		field.setArray(true);
		if(from==null && s==null){	//[]
			field.setFrom(0);
			field.setTo(Integer.MAX_VALUE);
		} else if(from!=null && s==null){ //[3]
			field.setFrom(Integer.parseInt($from.text));
			field.setTo(Integer.parseInt($from.text));
		} else if(from==null && to!=null){//[..8]
			field.setFrom(0);
			field.setTo(Integer.parseInt($to.text));
		} else if(from!=null && to==null){//[3..]
			field.setFrom(Integer.parseInt($from.text));
			field.setTo(Integer.MAX_VALUE);	
		} else if(from!=null && to!=null){//[3..8]
			field.setFrom(Integer.parseInt($from.text));
			field.setTo(Integer.parseInt($to.text));		
		} else {
			field.setFrom(0);
			field.setTo(Integer.MAX_VALUE);	
		}
	};

/*
==============================================================
==============================================================
==============================================================
==============================================================
==============================================================
*/
prog : 
	^(PROG typeDefinition*) {System.out.println("RET");}|
	^(PROG stat* ) {System.out.println("RET");};


stat 
: ^(STAT e=expr)  
;

expr 
:	^('+' e1=expr e2=expr) {System.out.println("ADD");}| 
	^('-' e1=expr e2=expr) {System.out.println("SUB");}| 
	^('*' e1=expr e2=expr) {System.out.println("MUL");}| 
	^('/' e1=expr e2=expr) {System.out.println("DIV");}| 
	a=atom  
;

atom  
:	^(NUM i=INTEGER) {System.out.println("LDC "+i.getText());}| 
	^(VAR v=NAME)  {System.out.println("LDV "+v.getText());}
;