grammar Nebula;

options {
  language = Java;
}

@header {
	package nebula.lang;
	import java.math.BigDecimal;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import util.InheritHashMap;
	
  import static nebula.lang.Importance.*;
  import static nebula.lang.Reference.*;	
	
}
@lexer::header{
    package nebula.lang;
}

@members {
    Map<String,Type> typesMap = new HashMap<String,Type>();
    List<Type> types = new ArrayList<Type>();

    InheritHashMap attrsBuffer = new InheritHashMap();
    
    TypeLoader loader;
    public NebulaParser(TokenStream input,TypeLoader loader) {
        this(input);
        this.loader = loader;
    }
    
    protected Type resolveType(String name){
        Type type = typesMap.get(name);
        if(type!=null)
            return type;
        type = loader.findType(name);
        if(type!=null){
            return type;
        } else {
            throw new RuntimeException("Can't find type :" + name);
        }
    }
    
    protected void addType(Type type){
        typesMap.put(type.name,type);   
        types.add(type);     
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

programDefinition returns[List<Type> retTypes]
    : typeDefinition
      {retTypes = this.types;}
    ;
    
typeDefinition returns[Type type]
    :   (attrs = attrListDefinition)?
        'type' typeID=ID ('|' alias=aliasLiteral[$typeID.text])?
        ('extends' superTypeID=ID)? { 
            if($superTypeID==null){
                type = new Type(loader,$typeID.text,resolveType(Type.ENTITY));
                type.standalone = TypeStandalone.Master;
            }else{
                type = new Type(loader,$typeID.text,resolveType($superTypeID.text)); 
            } 
           
            if(attrs != null){
                type.attrs.putAll(attrs);
            }
            
            addType(type);
           
            if(alias!=null){
                type.nameAlias = alias;
            }
        }
        '{' 
            fieldDefinition[type]* 
        '}' ';';

nestedTypeDefinition[Type resideType,String name,Alias nameAlias] returns[Type type]
    :   '{' 
            {
              String typeName = resideType.name + "$" + name;
              type = new Type(loader,resideType,typeName,resolveType(Type.ENTITY));
              type.standalone = TypeStandalone.Eembedded;
              if(nameAlias!=null)type.nameAlias=nameAlias;
              addType(type);
            }
            fieldDefinition[type]* 
        '}';

fieldDefinition[Type resideType] returns[Field field]
    :   (attrs = attrListDefinition)?  
        imp=fieldImportance
        inline=inlineDefinition
        name=ID ('-' qtype=ID)?  { 
            if($qtype!=null){
              field = new Field(resideType,$name.text + $qtype.text); 
              field.type = resolveType($qtype.text);
            } else {
              field = new Field(resideType,$name.text); 
           }
          }
        ('|' alias=aliasLiteral[$name.text] {field.nameAlias =alias; })?
        range=arrayDefinition
        (INIT 
        |)
        (
            type=ID { field.type = resolveType($type.text); }
            | nestedType = nestedTypeDefinition[resideType,$name.text,alias] {field.type = nestedType;}
           | {if(field.type==null)field.type = resolveType(field.name);} 
        )
        
        ';'{
            field.attrs.setDefaults(field.type.attrs);
            field.importance = imp;
            if(field.type.standalone == TypeStandalone.Basic){
              field.refer = ByVal;
            }else if(field.type.standalone == TypeStandalone.Eembedded){
              field.refer = Inline;
            }else{
              field.refer = ByRef;             
            }
            if(inline==Inline || inline == Cascade){
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
            
            if(attrs != null){
                field.attrs.putAll(attrs);
            }
            
            resideType.fields.add(field);
          }
        ;



fieldImportance returns[Importance v] 
    :   '!' {v=Key;} 
      | '*' {v=Core;} 
      | '#' {v=Require;} 
      | '?' {v=Unimportant;}
      |     {v=Require;}
    ;

inlineDefinition returns[Reference v] 
    :   '&' {v=Inline;} 
      | '%' {v=Cascade;} 
      | 
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


attrListDefinition returns[InheritHashMap attrs]
    @init{if(attrs==null)attrs = new InheritHashMap();else attrs.clear();}
    :('@' attrItemDefinition[attrs] ';')+
;

attrItemDefinition[InheritHashMap attrs]
    :   ID '=' v=constValueDefinition {attrs.put($ID.text,v);}
    ;

constValueDefinition returns [Object v]
    : StringLiteral {v=$StringLiteral.text.substring(1,$StringLiteral.text.length()-1);}
      | decimal {v=new BigDecimal($decimal.text);}
      | INT {v=new Integer($INT.text);}  
    ;
    
decimal
    :   INT '.' INT*
        | '.' INT+
    ;
    
aliasLiteral[String defaultName] returns[Alias alias]
    @init{alias = new Alias(defaultName);}
    :   language=flexID ':' value=flexID {alias.add(language,value);}
        ('|' language=flexID ':' value=flexID {alias.add(language,value);} )*
    ;

flexID returns[String text]
    :   StringLiteral {text = $StringLiteral.text.substring(1,$StringLiteral.text.length()-1);}
      | ID  {text = $ID.text;}
    ;




// *************   START  :  BASIC   *************

StringLiteral :
  '"' (~('"'|'\n'|'\r'))* '"';

/*ID :  Letter (Letter | Digit | '-' | '_')*;*/  
INT :  Digit Digit*;
fragment Digit :  '0'..'9';
fragment Letter : 'a'..'z' | 'A'..'Z';

INIT  : ':=';

NEWLINE:'\r'? '\n'  {$channel=HIDDEN;};    
Whitespace :  (' ' | '\t' | '\f')+ {$channel=HIDDEN;};    
SingleLineComment :
  '//' (~('\n'|'\r'))* ('\n'|'\r'('\n')?)? {$channel=HIDDEN;};
MultiLineComment :
    '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;};
    
ID:
  JavaIdentifierStart
  JavaIdentifierPart*;
  fragment JavaIdentifierPart :
    '\u0000'..'\u0008' | '\u000e'..'\u001b' |
    '\u0030'..'\u0039' | '\u0041'..'\u005a' | '\u005f' |
    '\u0061'..'\u007a' | '\u007f'..'\u009f' | '\u00aa' | '\u00ad' |
    '\u00b5' | '\u00ba' | '\u00c0'..'\u00d6' | '\u00d8'..'\u00f6' |
    '\u00f8'..'\u02c1' | '\u02c6'..'\u02d1' | '\u02e0'..'\u02e4' |
    '\u02ee' | '\u0300'..'\u036f' | '\u037a'..'\u037d' | '\u0386' |
    '\u0388'..'\u038a' | '\u038c' | '\u038e'..'\u03a1' |
    '\u03a3'..'\u03ce' | '\u03d0'..'\u03f5' | '\u03f7'..'\u0481' |
    '\u0483'..'\u0486' | '\u048a'..'\u0513' | '\u0531'..'\u0556' |
    '\u0559' | '\u0561'..'\u0587' | '\u0591'..'\u05bd' | '\u05bf' |
    '\u05c1'..'\u05c2' | '\u05c4'..'\u05c5' | '\u05c7' |
    '\u05d0'..'\u05ea' | '\u05f0'..'\u05f2' | '\u0600'..'\u0603' |
    '\u0610'..'\u0615' | '\u0621'..'\u063a' | '\u0640'..'\u065e' |
    '\u0660'..'\u0669' | '\u066e'..'\u06d3' | '\u06d5'..'\u06dd' |
    '\u06df'..'\u06e8' | '\u06ea'..'\u06fc' | '\u06ff' |
    '\u070f'..'\u074a' | '\u074d'..'\u076d' | '\u0780'..'\u07b1' |
    '\u07c0'..'\u07f5' | '\u07fa' | '\u0901'..'\u0939' |
    '\u093c'..'\u094d' | '\u0950'..'\u0954' | '\u0958'..'\u0963' |
    '\u0966'..'\u096f' | '\u097b'..'\u097f' | '\u0981'..'\u0983' |
    '\u0985'..'\u098c' | '\u098f'..'\u0990' | '\u0993'..'\u09a8' |
    '\u09aa'..'\u09b0' | '\u09b2' | '\u09b6'..'\u09b9' |
    '\u09bc'..'\u09c4' | '\u09c7'..'\u09c8' | '\u09cb'..'\u09ce' |
    '\u09d7' | '\u09dc'..'\u09dd' | '\u09df'..'\u09e3' |
    '\u09e6'..'\u09f1' | '\u0a01'..'\u0a03' | '\u0a05'..'\u0a0a' |
    '\u0a0f'..'\u0a10' | '\u0a13'..'\u0a28' | '\u0a2a'..'\u0a30' |
    '\u0a32'..'\u0a33' | '\u0a35'..'\u0a36' | '\u0a38'..'\u0a39' |
    '\u0a3c' | '\u0a3e'..'\u0a42' | '\u0a47'..'\u0a48' |
    '\u0a4b'..'\u0a4d' | '\u0a59'..'\u0a5c' | '\u0a5e' |
    '\u0a66'..'\u0a74' | '\u0a81'..'\u0a83' | '\u0a85'..'\u0a8d' |
    '\u0a8f'..'\u0a91' | '\u0a93'..'\u0aa8' | '\u0aaa'..'\u0ab0' |
    '\u0ab2'..'\u0ab3' | '\u0ab5'..'\u0ab9' | '\u0abc'..'\u0ac5' |
    '\u0ac7'..'\u0ac9' | '\u0acb'..'\u0acd' | '\u0ad0' |
    '\u0ae0'..'\u0ae3' | '\u0ae6'..'\u0aef' | '\u0b01'..'\u0b03' |
    '\u0b05'..'\u0b0c' | '\u0b0f'..'\u0b10' | '\u0b13'..'\u0b28' |
    '\u0b2a'..'\u0b30' | '\u0b32'..'\u0b33' | '\u0b35'..'\u0b39' |
    '\u0b3c'..'\u0b43' | '\u0b47'..'\u0b48' | '\u0b4b'..'\u0b4d' |
    '\u0b56'..'\u0b57' | '\u0b5c'..'\u0b5d' | '\u0b5f'..'\u0b61' |
    '\u0b66'..'\u0b6f' | '\u0b71' | '\u0b82'..'\u0b83' |
    '\u0b85'..'\u0b8a' | '\u0b8e'..'\u0b90' | '\u0b92'..'\u0b95' |
    '\u0b99'..'\u0b9a' | '\u0b9c' | '\u0b9e'..'\u0b9f' |
    '\u0ba3'..'\u0ba4' | '\u0ba8'..'\u0baa' | '\u0bae'..'\u0bb9' |
    '\u0bbe'..'\u0bc2' | '\u0bc6'..'\u0bc8' | '\u0bca'..'\u0bcd' |
    '\u0bd7' | '\u0be6'..'\u0bef' | '\u0c01'..'\u0c03' |
    '\u0c05'..'\u0c0c' | '\u0c0e'..'\u0c10' | '\u0c12'..'\u0c28' |
    '\u0c2a'..'\u0c33' | '\u0c35'..'\u0c39' | '\u0c3e'..'\u0c44' |
    '\u0c46'..'\u0c48' | '\u0c4a'..'\u0c4d' | '\u0c55'..'\u0c56' |
    '\u0c60'..'\u0c61' | '\u0c66'..'\u0c6f' | '\u0c82'..'\u0c83' |
    '\u0c85'..'\u0c8c' | '\u0c8e'..'\u0c90' | '\u0c92'..'\u0ca8' |
    '\u0caa'..'\u0cb3' | '\u0cb5'..'\u0cb9' | '\u0cbc'..'\u0cc4' |
    '\u0cc6'..'\u0cc8' | '\u0cca'..'\u0ccd' | '\u0cd5'..'\u0cd6' |
    '\u0cde' | '\u0ce0'..'\u0ce3' | '\u0ce6'..'\u0cef' |
    '\u0d02'..'\u0d03' | '\u0d05'..'\u0d0c' | '\u0d0e'..'\u0d10' |
    '\u0d12'..'\u0d28' | '\u0d2a'..'\u0d39' | '\u0d3e'..'\u0d43' |
    '\u0d46'..'\u0d48' | '\u0d4a'..'\u0d4d' | '\u0d57' |
    '\u0d60'..'\u0d61' | '\u0d66'..'\u0d6f' | '\u0d82'..'\u0d83' |
    '\u0d85'..'\u0d96' | '\u0d9a'..'\u0db1' | '\u0db3'..'\u0dbb' |
    '\u0dbd' | '\u0dc0'..'\u0dc6' | '\u0dca' | '\u0dcf'..'\u0dd4' |
    '\u0dd6' | '\u0dd8'..'\u0ddf' | '\u0df2'..'\u0df3' |
    '\u0e01'..'\u0e3a' | '\u0e40'..'\u0e4e' | '\u0e50'..'\u0e59' |
    '\u0e81'..'\u0e82' | '\u0e84' | '\u0e87'..'\u0e88' | '\u0e8a' |
    '\u0e8d' | '\u0e94'..'\u0e97' | '\u0e99'..'\u0e9f' |
    '\u0ea1'..'\u0ea3' | '\u0ea5' | '\u0ea7' | '\u0eaa'..'\u0eab' |
    '\u0ead'..'\u0eb9' | '\u0ebb'..'\u0ebd' | '\u0ec0'..'\u0ec4' |
    '\u0ec6' | '\u0ec8'..'\u0ecd' | '\u0ed0'..'\u0ed9' |
    '\u0edc'..'\u0edd' | '\u0f00' | '\u0f18'..'\u0f19' |
    '\u0f20'..'\u0f29' | '\u0f35' | '\u0f37' | '\u0f39' |
    '\u0f3e'..'\u0f47' | '\u0f49'..'\u0f6a' | '\u0f71'..'\u0f84' |
    '\u0f86'..'\u0f8b' | '\u0f90'..'\u0f97' | '\u0f99'..'\u0fbc' |
    '\u0fc6' | '\u1000'..'\u1021' | '\u1023'..'\u1027' |
    '\u1029'..'\u102a' | '\u102c'..'\u1032' | '\u1036'..'\u1039' |
    '\u1040'..'\u1049' | '\u1050'..'\u1059' | '\u10a0'..'\u10c5' |
    '\u10d0'..'\u10fa' | '\u10fc' | '\u1100'..'\u1159' |
    '\u115f'..'\u11a2' | '\u11a8'..'\u11f9' | '\u1200'..'\u1248' |
    '\u124a'..'\u124d' | '\u1250'..'\u1256' | '\u1258' |
    '\u125a'..'\u125d' | '\u1260'..'\u1288' | '\u128a'..'\u128d' |
    '\u1290'..'\u12b0' | '\u12b2'..'\u12b5' | '\u12b8'..'\u12be' |
    '\u12c0' | '\u12c2'..'\u12c5' | '\u12c8'..'\u12d6' |
    '\u12d8'..'\u1310' | '\u1312'..'\u1315' | '\u1318'..'\u135a' |
    '\u135f' | '\u1380'..'\u138f' | '\u13a0'..'\u13f4' |
    '\u1401'..'\u166c' | '\u166f'..'\u1676' | '\u1681'..'\u169a' |
    '\u16a0'..'\u16ea' | '\u16ee'..'\u16f0' | '\u1700'..'\u170c' |
    '\u170e'..'\u1714' | '\u1720'..'\u1734' | '\u1740'..'\u1753' |
    '\u1760'..'\u176c' | '\u176e'..'\u1770' | '\u1772'..'\u1773' |
    '\u1780'..'\u17d3' | '\u17d7' | '\u17dc'..'\u17dd' |
    '\u17e0'..'\u17e9' | '\u180b'..'\u180d' | '\u1810'..'\u1819' |
    '\u1820'..'\u1877' | '\u1880'..'\u18a9' | '\u1900'..'\u191c' |
    '\u1920'..'\u192b' | '\u1930'..'\u193b' | '\u1946'..'\u196d' |
    '\u1970'..'\u1974' | '\u1980'..'\u19a9' | '\u19b0'..'\u19c9' |
    '\u19d0'..'\u19d9' | '\u1a00'..'\u1a1b' | '\u1b00'..'\u1b4b' |
    '\u1b50'..'\u1b59' | '\u1b6b'..'\u1b73' | '\u1d00'..'\u1dca' |
    '\u1dfe'..'\u1e9b' | '\u1ea0'..'\u1ef9' | '\u1f00'..'\u1f15' |
    '\u1f18'..'\u1f1d' | '\u1f20'..'\u1f45' | '\u1f48'..'\u1f4d' |
    '\u1f50'..'\u1f57' | '\u1f59' | '\u1f5b' | '\u1f5d' |
    '\u1f5f'..'\u1f7d' | '\u1f80'..'\u1fb4' | '\u1fb6'..'\u1fbc' |
    '\u1fbe' | '\u1fc2'..'\u1fc4' | '\u1fc6'..'\u1fcc' |
    '\u1fd0'..'\u1fd3' | '\u1fd6'..'\u1fdb' | '\u1fe0'..'\u1fec' |
    '\u1ff2'..'\u1ff4' | '\u1ff6'..'\u1ffc' | '\u200b'..'\u200f' |
    '\u202a'..'\u202e' | '\u203f'..'\u2040' | '\u2054' |
    '\u2060'..'\u2063' | '\u206a'..'\u206f' | '\u2071' | '\u207f' |
    '\u2090'..'\u2094' | '\u20d0'..'\u20dc' | '\u20e1' |
    '\u20e5'..'\u20ef' | '\u2102' | '\u2107' | '\u210a'..'\u2113' |
    '\u2115' | '\u2119'..'\u211d' | '\u2124' | '\u2126' | '\u2128' |
    '\u212a'..'\u212d' | '\u212f'..'\u2139' | '\u213c'..'\u213f' |
    '\u2145'..'\u2149' | '\u214e' | '\u2160'..'\u2184' |
    '\u2c00'..'\u2c2e' | '\u2c30'..'\u2c5e' | '\u2c60'..'\u2c6c' |
    '\u2c74'..'\u2c77' | '\u2c80'..'\u2ce4' | '\u2d00'..'\u2d25' |
    '\u2d30'..'\u2d65' | '\u2d6f' | '\u2d80'..'\u2d96' |
    '\u2da0'..'\u2da6' | '\u2da8'..'\u2dae' | '\u2db0'..'\u2db6' |
    '\u2db8'..'\u2dbe' | '\u2dc0'..'\u2dc6' | '\u2dc8'..'\u2dce' |
    '\u2dd0'..'\u2dd6' | '\u2dd8'..'\u2dde' | '\u3005'..'\u3007' |
    '\u3021'..'\u302f' | '\u3031'..'\u3035' | '\u3038'..'\u303c' |
    '\u3041'..'\u3096' | '\u3099'..'\u309a' | '\u309d'..'\u309f' |
    '\u30a1'..'\u30fa' | '\u30fc'..'\u30ff' | '\u3105'..'\u312c' |
    '\u3131'..'\u318e' | '\u31a0'..'\u31b7' | '\u31f0'..'\u31ff' |
    '\u3400'..'\u4db5' | '\u4e00'..'\u9fbb' | '\ua000'..'\ua48c' |
    '\ua717'..'\ua71a' | '\ua800'..'\ua827' | '\ua840'..'\ua873' |
    '\uac00'..'\ud7a3' | '\uf900'..'\ufa2d' | '\ufa30'..'\ufa6a' |
    '\ufa70'..'\ufad9' | '\ufb00'..'\ufb06' | '\ufb13'..'\ufb17' |
    '\ufb1d'..'\ufb28' | '\ufb2a'..'\ufb36' | '\ufb38'..'\ufb3c' |
    '\ufb3e' | '\ufb40'..'\ufb41' | '\ufb43'..'\ufb44' |
    '\ufb46'..'\ufbb1' | '\ufbd3'..'\ufd3d' | '\ufd50'..'\ufd8f' |
    '\ufd92'..'\ufdc7' | '\ufdf0'..'\ufdfb' | '\ufe00'..'\ufe0f' |
    '\ufe20'..'\ufe23' | '\ufe33'..'\ufe34' | '\ufe4d'..'\ufe4f' |
    '\ufe70'..'\ufe74' | '\ufe76'..'\ufefc' | '\ufeff' |
    '\uff10'..'\uff19' | '\uff21'..'\uff3a' | '\uff3f' |
    '\uff41'..'\uff5a' | '\uff66'..'\uffbe' | '\uffc2'..'\uffc7' |
    '\uffca'..'\uffcf' | '\uffd2'..'\uffd7' | '\uffda'..'\uffdc' |
    '\ufff9'..'\ufffb';
    
    fragment JavaIdentifierStart:
      '\u0041'..'\u005a' | '\u005f' | '\u0061'..'\u007a' |
      '\u00aa' | '\u00b5' | '\u00ba' | '\u00c0'..'\u00d6' |
      '\u00d8'..'\u00f6' | '\u00f8'..'\u02c1' | '\u02c6'..'\u02d1' |
      '\u02e0'..'\u02e4' | '\u02ee' | '\u037a'..'\u037d' | '\u0386' |
      '\u0388'..'\u038a' | '\u038c' | '\u038e'..'\u03a1' |
      '\u03a3'..'\u03ce' | '\u03d0'..'\u03f5' | '\u03f7'..'\u0481' |
      '\u048a'..'\u0513' | '\u0531'..'\u0556' | '\u0559' |
      '\u0561'..'\u0587' | '\u05d0'..'\u05ea' | '\u05f0'..'\u05f2' |
      '\u0621'..'\u063a' | '\u0640'..'\u064a' | '\u066e'..'\u066f' |
      '\u0671'..'\u06d3' | '\u06d5' | '\u06e5'..'\u06e6' |
      '\u06ee'..'\u06ef' | '\u06fa'..'\u06fc' | '\u06ff' | '\u0710' |
      '\u0712'..'\u072f' | '\u074d'..'\u076d' | '\u0780'..'\u07a5' |
      '\u07b1' | '\u07ca'..'\u07ea' | '\u07f4'..'\u07f5' | '\u07fa' |
      '\u0904'..'\u0939' | '\u093d' | '\u0950' | '\u0958'..'\u0961' |
      '\u097b'..'\u097f' | '\u0985'..'\u098c' | '\u098f'..'\u0990' |
      '\u0993'..'\u09a8' | '\u09aa'..'\u09b0' | '\u09b2' |
      '\u09b6'..'\u09b9' | '\u09bd' | '\u09ce' | '\u09dc'..'\u09dd' |
      '\u09df'..'\u09e1' | '\u09f0'..'\u09f1' | '\u0a05'..'\u0a0a' |
      '\u0a0f'..'\u0a10' | '\u0a13'..'\u0a28' | '\u0a2a'..'\u0a30' |
      '\u0a32'..'\u0a33' | '\u0a35'..'\u0a36' | '\u0a38'..'\u0a39' |
      '\u0a59'..'\u0a5c' | '\u0a5e' | '\u0a72'..'\u0a74' |
      '\u0a85'..'\u0a8d' | '\u0a8f'..'\u0a91' | '\u0a93'..'\u0aa8' |
      '\u0aaa'..'\u0ab0' | '\u0ab2'..'\u0ab3' | '\u0ab5'..'\u0ab9' |
      '\u0abd' | '\u0ad0' | '\u0ae0'..'\u0ae1' | '\u0b05'..'\u0b0c' |
      '\u0b0f'..'\u0b10' | '\u0b13'..'\u0b28' | '\u0b2a'..'\u0b30' |
      '\u0b32'..'\u0b33' | '\u0b35'..'\u0b39' | '\u0b3d' |
      '\u0b5c'..'\u0b5d' | '\u0b5f'..'\u0b61' | '\u0b71' | '\u0b83' |
      '\u0b85'..'\u0b8a' | '\u0b8e'..'\u0b90' | '\u0b92'..'\u0b95' |
      '\u0b99'..'\u0b9a' | '\u0b9c' | '\u0b9e'..'\u0b9f' |
      '\u0ba3'..'\u0ba4' | '\u0ba8'..'\u0baa' | '\u0bae'..'\u0bb9' |
      '\u0c05'..'\u0c0c' | '\u0c0e'..'\u0c10' | '\u0c12'..'\u0c28' |
      '\u0c2a'..'\u0c33' | '\u0c35'..'\u0c39' | '\u0c60'..'\u0c61' |
      '\u0c85'..'\u0c8c' | '\u0c8e'..'\u0c90' | '\u0c92'..'\u0ca8' |
      '\u0caa'..'\u0cb3' | '\u0cb5'..'\u0cb9' | '\u0cbd' | '\u0cde' |
      '\u0ce0'..'\u0ce1' | '\u0d05'..'\u0d0c' | '\u0d0e'..'\u0d10' |
      '\u0d12'..'\u0d28' | '\u0d2a'..'\u0d39' | '\u0d60'..'\u0d61' |
      '\u0d85'..'\u0d96' | '\u0d9a'..'\u0db1' | '\u0db3'..'\u0dbb' |
      '\u0dbd' | '\u0dc0'..'\u0dc6' | '\u0e01'..'\u0e30' |
      '\u0e32'..'\u0e33' | '\u0e40'..'\u0e46' | '\u0e81'..'\u0e82' |
      '\u0e84' | '\u0e87'..'\u0e88' | '\u0e8a' | '\u0e8d' |
      '\u0e94'..'\u0e97' | '\u0e99'..'\u0e9f' | '\u0ea1'..'\u0ea3' |
      '\u0ea5' | '\u0ea7' | '\u0eaa'..'\u0eab' | '\u0ead'..'\u0eb0' |
      '\u0eb2'..'\u0eb3' | '\u0ebd' | '\u0ec0'..'\u0ec4' | '\u0ec6' |
      '\u0edc'..'\u0edd' | '\u0f00' | '\u0f40'..'\u0f47' |
      '\u0f49'..'\u0f6a' | '\u0f88'..'\u0f8b' | '\u1000'..'\u1021' |
      '\u1023'..'\u1027' | '\u1029'..'\u102a' | '\u1050'..'\u1055' |
      '\u10a0'..'\u10c5' | '\u10d0'..'\u10fa' | '\u10fc' |
      '\u1100'..'\u1159' | '\u115f'..'\u11a2' | '\u11a8'..'\u11f9' |
      '\u1200'..'\u1248' | '\u124a'..'\u124d' | '\u1250'..'\u1256' |
      '\u1258' | '\u125a'..'\u125d' | '\u1260'..'\u1288' |
      '\u128a'..'\u128d' | '\u1290'..'\u12b0' | '\u12b2'..'\u12b5' |
      '\u12b8'..'\u12be' | '\u12c0' | '\u12c2'..'\u12c5' |
      '\u12c8'..'\u12d6' | '\u12d8'..'\u1310' | '\u1312'..'\u1315' |
      '\u1318'..'\u135a' | '\u1380'..'\u138f' | '\u13a0'..'\u13f4' |
      '\u1401'..'\u166c' | '\u166f'..'\u1676' | '\u1681'..'\u169a' |
      '\u16a0'..'\u16ea' | '\u16ee'..'\u16f0' | '\u1700'..'\u170c' |
      '\u170e'..'\u1711' | '\u1720'..'\u1731' | '\u1740'..'\u1751' |
      '\u1760'..'\u176c' | '\u176e'..'\u1770' | '\u1780'..'\u17b3' |
      '\u17d7' | '\u17dc' | '\u1820'..'\u1877' | '\u1880'..'\u18a8' |
      '\u1900'..'\u191c' | '\u1950'..'\u196d' | '\u1970'..'\u1974' |
      '\u1980'..'\u19a9' | '\u19c1'..'\u19c7' | '\u1a00'..'\u1a16' |
      '\u1b05'..'\u1b33' | '\u1b45'..'\u1b4b' | '\u1d00'..'\u1dbf' |
      '\u1e00'..'\u1e9b' | '\u1ea0'..'\u1ef9' | '\u1f00'..'\u1f15' |
      '\u1f18'..'\u1f1d' | '\u1f20'..'\u1f45' | '\u1f48'..'\u1f4d' |
      '\u1f50'..'\u1f57' | '\u1f59' | '\u1f5b' | '\u1f5d' |
      '\u1f5f'..'\u1f7d' | '\u1f80'..'\u1fb4' | '\u1fb6'..'\u1fbc' |
      '\u1fbe' | '\u1fc2'..'\u1fc4' | '\u1fc6'..'\u1fcc' |
      '\u1fd0'..'\u1fd3' | '\u1fd6'..'\u1fdb' | '\u1fe0'..'\u1fec' |
      '\u1ff2'..'\u1ff4' | '\u1ff6'..'\u1ffc' | '\u2071' | '\u207f' |
      '\u2090'..'\u2094' | '\u2102' | '\u2107' | '\u210a'..'\u2113' |
      '\u2115' | '\u2119'..'\u211d' | '\u2124' | '\u2126' | '\u2128' |
      '\u212a'..'\u212d' | '\u212f'..'\u2139' | '\u213c'..'\u213f' |
      '\u2145'..'\u2149' | '\u214e' | '\u2160'..'\u2184' |
      '\u2c00'..'\u2c2e' | '\u2c30'..'\u2c5e' | '\u2c60'..'\u2c6c' |
      '\u2c74'..'\u2c77' | '\u2c80'..'\u2ce4' | '\u2d00'..'\u2d25' |
      '\u2d30'..'\u2d65' | '\u2d6f' | '\u2d80'..'\u2d96' |
      '\u2da0'..'\u2da6' | '\u2da8'..'\u2dae' | '\u2db0'..'\u2db6' |
      '\u2db8'..'\u2dbe' | '\u2dc0'..'\u2dc6' | '\u2dc8'..'\u2dce' |
      '\u2dd0'..'\u2dd6' | '\u2dd8'..'\u2dde' | '\u3005'..'\u3007' |
      '\u3021'..'\u3029' | '\u3031'..'\u3035' | '\u3038'..'\u303c' |
      '\u3041'..'\u3096' | '\u309d'..'\u309f' | '\u30a1'..'\u30fa' |
      '\u30fc'..'\u30ff' | '\u3105'..'\u312c' | '\u3131'..'\u318e' |
      '\u31a0'..'\u31b7' | '\u31f0'..'\u31ff' | '\u3400'..'\u4db5' |
      '\u4e00'..'\u9fbb' | '\ua000'..'\ua48c' | '\ua717'..'\ua71a' |
      '\ua800'..'\ua801' | '\ua803'..'\ua805' | '\ua807'..'\ua80a' |
      '\ua80c'..'\ua822' | '\ua840'..'\ua873' | '\uac00'..'\ud7a3' |
      '\uf900'..'\ufa2d' | '\ufa30'..'\ufa6a' | '\ufa70'..'\ufad9' |
      '\ufb00'..'\ufb06' | '\ufb13'..'\ufb17' | '\ufb1d' |
      '\ufb1f'..'\ufb28' | '\ufb2a'..'\ufb36' | '\ufb38'..'\ufb3c' |
      '\ufb3e' | '\ufb40'..'\ufb41' | '\ufb43'..'\ufb44' |
      '\ufb46'..'\ufbb1' | '\ufbd3'..'\ufd3d' | '\ufd50'..'\ufd8f' |
      '\ufd92'..'\ufdc7' | '\ufdf0'..'\ufdfb' | '\ufe70'..'\ufe74' |
      '\ufe76'..'\ufefc' | '\uff21'..'\uff3a' | '\uff41'..'\uff5a' |
      '\uff66'..'\uffbe' | '\uffc2'..'\uffc7' | '\uffca'..'\uffcf' |
      '\uffd2'..'\uffd7' | '\uffda'..'\uffdc';