grammar Ne;

options {
  language = Java;
}

@header {
    package ne;

    import java.util.HashMap;
    import java.util.Map;
}
@lexer::header{
    package ne;
}

@members {
    Map<String,Type> types = new HashMap<>();
    
    protected Type resolveType(String name){
        Type type =  types.get(name);
        if(type==null){
            types.put(name,new Type(name));
            type =  types.get(name);
        }
        return type;
    }

    protected void info(String str) {
    if (str.charAt(str.length() - 1) == '\n') {
//      String txtTemps = "";
//      for (TempVar v : tmps) {
//        txtTemps += "" + (v.applied ? " " : v.reg) + " ";
//      }
//      str = "|" + txtTemps + "|" + "\t\t" + str;
    ;
    }
    System.out.print(str);
  }
}

typeDefinition returns[Type type]: 'type' ID{type = new Type($ID.text);} '{' fieldDefinition[type]* '}' ';';
fieldDefinition[Type resideType] returns[Field field]
    :   imp=fieldImportance
        inline=inlineDefinition
        name=ID  { field = new Field(resideType,$name.text); }
        (type=ID { field.type = resolveType($type.text); } 
           | {field.type = resolveType(field.name);} )
        ';'{
            field.importance = imp;
            if(inline!=""){
                field.inline = inline;
            }
            resideType.fields.add(field);
          }
        ;

fieldImportance returns[String v] 
    :   '!' {v=Field.KEY;} 
      | '*' {v=Field.CORE;} 
      | '#' {v=Field.REQUIRE;} 
      | '?' {v=Field.UNIMPORTANT;}
      |     {v=Field.REQUIRE;}
    ;

inlineDefinition returns[String v] 
    :   '&' {v=Field.INLINE;} 
      | '%' {v=Field.CASCADE;} 
      |     {v="";}
    ;
    
arrayDefinition
    :  ('[' (
            (INT ('..' INT)?)
            | ('..' INT?)
        ) ']')
        |
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