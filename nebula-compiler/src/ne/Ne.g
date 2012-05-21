grammar Ne;

options {
  language = Java;
}

@header {
    package ne;
    import java.math.BigDecimal;
}
@lexer::header{
    package ne;
}

@members {
    TypeLoader loader;
    public NeParser(TokenStream input,TypeLoader loader) {
        this(input);
        this.loader = loader;
    }
    protected Type resolveType(String name){
        return loader.findType(name);
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

typeDefinition returns[Type type]
    :   'type' typeID=ID 
        (':' superTypeID=ID)? { 
            if($superTypeID==null){
                type = new Type($typeID.text);
            }else{
                type = new Type($typeID.text,resolveType($superTypeID.text)); 
           } 
        }
        '{' 
            ('@' attrDefinition[type] ';')* 
            fieldDefinition[type]* 
        '}' ';';

fieldDefinition[Type resideType] returns[Field field]
    :   imp=fieldImportance
        inline=inlineDefinition
        name=ID  { field = new Field(resideType,$name.text); }
        range=arrayDefinition
        (type=ID { field.type = resolveType($type.text); } 
           | {field.type = resolveType(field.name);} )
        ';'{
            field.importance = imp;
            if(inline!=""){
                field.refer = inline;
            }
            if(range.from=="f"){
            }else if(range.from!=null){
                field.rangeFrom = Integer.parseInt(range.from);
                field.array = true;                
            }else{
                field.array = true;
            }
            
            if(range.to=="f"){
            }else if(range.to!=null){
                field.rangeTo = Integer.parseInt(range.to);
                field.array = true;                
            }else{
                field.array = true;
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
    
arrayDefinition returns[String from,String to]
    :  '[]'
        |  ( '[' (
            ('..' rangeTo=INT? {$to=$rangeTo.text;})
            | (rangeFrom=INT ('..' rangeTo=INT?)? {$from=$rangeFrom.text;$to=$rangeTo.text;})
            |
        ) ']')
        | {$from = "f";$to = "f";}
    ;
    
attrDefinition[Type resideType]
    :   ID '=' (
        INT         {resideType.attrs.put($ID.text,new Integer($INT.text));} 
        | STRING    {resideType.attrs.put($ID.text,$STRING.text.substring(1,$STRING.text.length()-1));} 
        | decimal   {resideType.attrs.put($ID.text,new BigDecimal($decimal.text));}
        )
    ;
    
decimal
    :   INT '.' INT*
        | '.' INT+
    ;
    
    
    
// *************   END  :  BASIC   *************

STRING : CHAR_ | STRING_; 

fragment CHAR_:   '\'' d=. '\'' ;

fragment STRING_: '\"' .* '\"' ;
    
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