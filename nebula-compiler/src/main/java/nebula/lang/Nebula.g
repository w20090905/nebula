grammar Nebula;

options {
  language = Java;
  backtrack=true;
}

@header {
	package nebula.lang;
	import java.math.BigDecimal;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import util.InheritHashMap;
	
  import static nebula.lang.Reference.*;  
  import static nebula.lang.Modifier.*;
  
  import nebula.lang.Compiler.VarRefer;
}
@lexer::header{
    package nebula.lang;
}

@members {
//    Map<String,Type> typesMap = new HashMap<String,Type>();
    List<Type> types = new ArrayList<Type>();
    
    Map<Field, Expr> derivedFields = new HashMap<Field, Expr>();
    Map<Field, Statement> actionFields = new HashMap<Field, Statement>();
  
    Compiler op = new Compiler(new Context() {
    
      @Override
      public Type resolveType(String name) {
              return NebulaParser.this.resolveType(name);
      }
    });

    InheritHashMap attrsBuffer = new InheritHashMap();
    
    TypeLoader loader;
    
    Map<String, Type> typesLoading;
    public NebulaParser(TokenStream input,TypeLoader loader) {
        this(input);
        this.loader = loader;
        this.typesLoading = loader.typesLoading;
    }
    
    Type currentType;
    
    protected Type resolveType(String name){
        if("this".equals(name)){
            return currentType;
        }
        
        Type type = typesLoading.get(name);
        if(type!=null)
            return type;
        type = loader.findType(name);
        if(type!=null){
            return type;
        } else {
            throw new RuntimeException("Can't find type :" + name);
        }
    }
    
    protected void loading(Type type){
        typesLoading.put(type.name,type);
        types.add(type);
        currentType = type;
    }
    
     protected void makeSureHasKey(Type type){
            Field firstKey = null;
            Field firstUnique = null;
            for(Field f : type.fields){
                if(f.isKey()){firstKey = f;break;}
                if(f.isUnique()){firstUnique = f;break;}
            }
            
            if(firstKey==null && firstUnique!=null){
              firstUnique.modifiers |= Key;
            }
        }
    
    
    protected void loadingFinish(Type resideType, Type type){
        typesLoading.remove(type.name);
        
        makeSureHasKey(type);
        currentType = resideType;
    }
    protected void loadingFinish(Type type){
        typesLoading.remove(type.name);        
        makeSureHasKey(type);
        currentType = null;
    }
    
    protected void exitTopType(){
    for (Map.Entry<Field, Expr> e : derivedFields.entrySet()) {
      e.getKey().expr = op.compile(e.getValue(), e.getKey().getResideType());
    }
    for (Map.Entry<Field, Statement> e : actionFields.entrySet()) {
      e.getKey().code = op.compile(e.getValue(), e.getKey().getResideType());
    }
    }
    protected void info(String str) {
    if (str.charAt(str.length() - 1) == '\n') {
    ;
    }
    System.out.print(str);
  }
  
  protected void enterMethod(Type type, String name) {
    locals.clear();
    pushLocal("nop", (Type)null);
    pushLocal("this", type);
    pushLocal("repos", "Type");
  };

  protected void exitMethod() {
    locals.clear();
  };

  private Map<String, Var> locals = new HashMap<String, Var>();
  protected int maxLocals = 0;

  protected void initLocals() {
    locals.clear();
  }

      protected Var pushLocal(String name, String typeName) {
        Var var = new Var(name, resolveType(typeName),locals.size());
        locals.put(var.name, var);
        return var;
      }
      protected Var pushLocal(String name, Type type) {
          Var var = new Var(name, type, locals.size());
          locals.put(var.name, var);
          return var;
        }

  protected Var v(String name) {
    Var var = locals.get(name);
    return var;
  };
}

flowDefinition returns[Flow flow]
  @init{
      Type superType = null;
    }
    : 
      'flow'
      typeID=ID
      ('extends' superTypeID=ID)?
      {
            if($superTypeID==null){
                superType = resolveType(TypeStandalone.Flow.name());
            }else{
                superType = resolveType($superTypeID.text);                
            }
       } 
      { flow = new Flow(loader,superType,$typeID.text);  loading(flow); }
      '{' NEWLINE?
          stepDefinition[flow]* 
      '}' {loadingFinish(flow);exitTopType();}  (';' NEWLINE?| NEWLINE)
    
    ;

stepDefinition[Flow resideFlow]  returns[Flow.Step step]
  @init{
      Type stepType = null;
      Type superType = null;
    }
    :
      actorQuery = queryLiteral?
      stepID = ID
        (('extends' | ':') superTypeID=ID )?    
        {
            if($superTypeID==null){
                superType = resolveType($stepID.text);
                if(superType==null){
                    superType = resolveType(Flow.APPROVE);
                 } 
            }else{
                superType = resolveType($superTypeID.text);                
            }
        } 
        (
            '{'  NEWLINE?   
                { 
                    String name = resideFlow.name + "$" + $stepID.text + ( superTypeID==null?(resideFlow.steps.size()+1) : "");
                    stepType = new Type(loader,resideFlow,name, superType); 
                    loading(stepType);
                }
                fieldDefinition[stepType]* 
            '}'     {loadingFinish(stepType);} 
        )?
        {          
            if(stepType==null){
                stepType = superType;
            }
            step = resideFlow.addStep($actorQuery.text,$stepID.text,stepType);            
        }
         (';' NEWLINE?| NEWLINE)
    ;

queryLiteral
    : ('[' ((~(']'))*) ']')  ;

programDefinition returns[List<Type> retTypes]
    : typeDefinition NEWLINE*
      {retTypes = this.types;}
    ;
    
typeDefinition returns[Type type]
    :   (annotations = annotationListDefinition)?
        typeType=typeDefineKeyword 
        typeID=ID 
        // Aliases
         ('|' aliases=aliasesLiteral[$typeID.text]
         | {aliases = new Aliases($typeID.text);}
         )
        ('extends' superTypeID=ID)? { 
            if($superTypeID==null){
                switch(typeType){
                  case Transaction:
                    type = new Type(loader,$typeID.text,resolveType(TypeStandalone.Transaction.name()),typeType);
                    break;
                  case Master:
                  default:
                    type = new Type(loader,$typeID.text,resolveType(TypeStandalone.Master.name()),typeType);
                    break;
                }
            }else{
                Type superType = resolveType($superTypeID.text);
                if(typeType != superType.standalone){
                  throw new RuntimeException("Type's standalone [" + typeType + "] not match super type's standalone [" + superType.standalone + "]");
                }
                type = new Type(loader,$typeID.text,superType);
            } 
           
            if(annotations != null){
                type.attrs.putAll(annotations);
            }
            
            loading(type);
            type.nameAlias.extend(aliases);
        }
        (
          '{' NEWLINE? (fieldDefinition[type])*  '}' (';'|NEWLINE)
        )
                
        // Finish
        {loadingFinish(type);exitTopType();}
        ;

typeDefineKeyword returns[TypeStandalone typeType]
    :   'define'                  {typeType = TypeStandalone.Basic;} 
      | ('type' | 'master' ) {typeType = TypeStandalone.Master;} 
      | ('tx'|'transaction')    {typeType = TypeStandalone.Transaction;}
      | ('flow')    {typeType = TypeStandalone.Flow;}
      | ('action')    {typeType = TypeStandalone.Action;};

nestedTypeDefinition[Type resideType,String name,Aliases nameAlias] returns[Type type]
    :  
            {
              String typeName = resideType.name + "$" + name;
              type = new Type(loader,resideType,typeName,resolveType(TypeStandalone.Mixin.name()));
              if(nameAlias!=null)type.setNameAlias(nameAlias);
              loading(type);
            }
            fieldDefinition[type]* 
        ;

fieldDefinition[Type resideType] returns[Field field]
    :   (annotations = annotationListDefinition)?  
        modifiers=fieldImportance
        inline=inlineDefinition
        name=ID ('-' qtype=ID)?  
        /* Aliases */
         ('|' aliases=aliasesLiteral[$name.text])?
       
          { 
            if($qtype!=null){
              field = new Field(resideType,$name.text + $qtype.text);
              field.type = resolveType($qtype.text);            

            } else {
              field = new Field(resideType,$name.text);
           }
          }       
                
        /* Actions */
        ( '()' { enterMethod(currentType,"");} action=block { exitMethod();} {actionFields.put(field, action);} )?
        /* Array? */
        range=arrayDefinition
        
        /* Field Type */
        (
            typeText=ID { field.type = resolveType($typeText.text);}
            |   '{'     NEWLINE ? { if(aliases==null) aliases = new Aliases(field.name); } 
                    nestedType = nestedTypeDefinition[resideType,$name.text,aliases] 
                '}'   { field.type = nestedType; loadingFinish(resideType,nestedType);}
           |            {
                            if(field.type==null && action==null)  {
                                field.type = resolveType(field.name);  
                                if(aliases==null && field.type.getNameAlias() !=null) aliases = field.type.getNameAlias();
                             }
                        } 
        )
        
        /* Default value */
        (':=' { enterMethod(currentType,"");} defaultExpr=expression  { exitMethod();}    {   field.modifiers |=DefaultValue;   derivedFields.put(field, defaultExpr); })?
        
        /* Derived expr */
        ('='  { enterMethod(currentType,"");} derivedExpr=expression  { exitMethod();}    {   field.modifiers |=Derived;            derivedFields.put(field, derivedExpr); } )?
        
          (';' NEWLINE?| NEWLINE)

        {
            if(field.type!=null){
              field.attrs.setDefaults(field.type.attrs);
            field.modifiers |= modifiers;
            if(field.type.standalone == TypeStandalone.Basic){
              field.refer = ByVal;
            }else if(field.type.standalone == TypeStandalone.Mixin){
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
            
            }
            
            if(annotations != null){
                field.attrs.putAll(annotations);
            }
           if(aliases!=null)field.setNameAlias(aliases);
           else field.setNameAlias(new Aliases(field.name));
            
            if(action==null){            
              resideType.fields.add(field);
            }else{
              resideType.actions.add(field);            
            }
            
          }
        ;



fieldImportance returns[int v] 
  @init{v=0;}
    :   ('!!' {v|=Key;v|=Unique;} )?
        ('!'  {v|=Unique;})?
        ('*' {v|=Core;} )?
        ('#' {v&=Required;} )?/* TODO */
        ('?' {v|=Nullable;})?
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


annotationListDefinition returns[InheritHashMap annotations]
    @init{annotations = new InheritHashMap();}
    :('@' annotationItemDefinition[annotations])+
;

annotationItemDefinition[InheritHashMap annotations]
    :   ID ('(' v=constValueDefinition ')')? NEWLINE?
    {      
        if(v!=null)annotations.put($ID.text,v);
        else annotations.put($ID.text,$ID.text);
      }
    ;

constValueDefinition returns [Object v]
    : string=stringLiteral {v=string;}
      | decimal {v=new BigDecimal($decimal.text);}
      | INT {v=new Long($INT.text);}  
    ;
    
decimal
    :   INT '.' INT*
        | '.' INT+
    ;
    
aliasesLiteral[String name] returns[Aliases aliases]
    :   text = flexID (':'  value=flexID)? {
            if(value!=null){
                aliases = new Aliases(name);
                aliases.add(text,value);
            }else{
                aliases = new Aliases(text);
            }
        }
        ('|' language=flexID ':' value=flexID {aliases.add(language,value);} )*
    ;

flexID returns[String text]
    :   string=stringLiteral {text = string;}
      | pre=ID  ('-' post=ID)?{text = $pre.text; if($post!=null) text = text + "-" + $post.text;}
    ;
    
block returns [Statement s]
    :   '{' NEWLINE? ss=statements {s=op.stBlock(ss);} '}'
    ;

statements returns [List<Statement> statments]   
@init{statments = new ArrayList<Statement>();}
:
  (s=statement {statments.add(s);})* 
;

statement returns [Statement s]:
  stBlock = block   {s = stBlock;}
  | stVar=varDeclaration {s = stVar;}
  | stExpr=exprStatement {s = stExpr;}
  ;
  
varDeclaration returns [Statement s]:
  type=ID name=ID ('=' from=expression)? (';' (NEWLINE)?|NEWLINE) 
    {
      Var var = pushLocal($name.text,$type.text);
      if(from!=null) s=op.stPutVar(var,from);      
    }
  ;

exprStatement returns [Statement s]
    :   expr=postfixExpr
        ('=' from=expression  )? (';' NEWLINE?|NEWLINE) {if(from!=null) s=op.stPut(expr,from);else op.stCall(expr);}
    ;

paramList:
  expression ( ',' expression)*
;

expression returns [Expr v]
    :   e=conditionalExpr {v = $e.v;}
;

conditionalExpr returns [Expr v]
    :   a=logicalNotExpr {v = a;}
        (   operator=conditionalOperators b=logicalNotExpr  {v=op.opConditional(operator,v,b);}
        )*
    ;

logicalNotExpr returns [Expr v]
    :   '!' b=relationalExpr {v=op.opNot(b);} 
        | 'not'  b=relationalExpr {v=op.opNot(b);} 
        | b=relationalExpr {v=b;} 
    ;
    
relationalExpr returns [Expr v]
    :   a=additiveExpr {v = a;} //equalityAndRelationalOperators
        (    operator=relationalOperators  c=additiveExpr  {v=op.opRelational(operator,v,c);}
        )*
    ;
    
additiveExpr returns [Expr v]
    :   a=multiplicativeExpr {v = a;}
        (   operator=additiveOperators  c=multiplicativeExpr  {v=op.opArithmetic(operator,v,c);}
        )*
    ;
multiplicativeExpr returns [Expr v]
    :   a=unary {v=a;}
        (  operator=multiplicativeOperators  c=unary  {v=op.opArithmetic(operator,v,c);} 
        )*
    ;

unary returns [Expr v]
    :  INC b=postfixExpr {v=op.opIncrement(b);} 
        | DEC  b=postfixExpr {v=op.opDecrement(b);} 
//        | ADD  b=postfixExpr {v=op.opPositive(b);} 
//        | SUB  b=postfixExpr {v=op.opNegates(b);} 
        | b=postfixExpr {v=b;} 
    ;

postfixExpr returns [Expr v]
    :   p=primaryExpr {v = p;}
        (
         '[' (
          e=datalistexpr[v] {v=e;}
         ) ']'
         | '.' m=ID '()' {v = op.opMethodCall(v,$m.text);}
         |  '.' mp=ID '(' paramList ')'{v = op.opMethodCall(v,$mp.text);}
         |  '.'  f=ID {v = op.opFieldOf(v,$f.text);}
        )*
    ;
primaryExpr returns [Expr v]
  :  c=constExpr  {v=c;} 
  | 'this' {v=op.opLocal(v("this"));}  // this
  | '$' entityName=ID { v=op.entities(op.opLocal(v("repos")),$entityName.text); }    //  实体对象
  | ID  {v=op.opLocal(v($ID.text));}         // 
  | '(' expr=expression ')'   {v = expr;}
;


datalistexpr[Expr list]  returns [Expr v] 
  @init{List<Expr<Object>> ranges = new ArrayList<Expr<Object>>();Expr<Boolean> b = null;} 
  :              (decimal {v=op.opDecimalCst($decimal.text);} | INT{v=op.opLongCst($INT.text);}) unit=ID{v=op.opUnit(v,$unit.text);} 
              |  
              (  f=ID rop=relationalOperators   c=additiveExpr  { v=op.opRelational(rop,(Expr)op.opFieldInList(list,$f.text),(Expr)op.opParamsl(c,ranges.size())); ranges.add(c); } )
              (              
                   cop=conditionalOperators
                   
                  ( f=ID rop=relationalOperators   c=additiveExpr  { b=op.opRelational(rop,(Expr)op.opFieldInList(list,$f.text),(Expr)op.opParamsl(c,ranges.size())); ranges.add(c); } )
                  {v = op.opConditional(cop,v,b);}
              )*
              { v=op.list(list,v,ranges);}
              | r=singleRange{ranges.add(r);}  (',' r=singleRange{ranges.add(r);})* { v=op.list(list,ranges);}
;

singleRange returns [Expr v]:
    (r='..' to=additiveExpr
    | from=additiveExpr (r='..' to=additiveExpr? )?)
    {
        if($r!=null){// Range
            v=op.range(from,to);
        } else {//Single
            v=op.index(from);          
        }    
    }
;

   /*
clauseOr returns [Expr v]
  :
a=clauseAnd {v = a;}
        (
          (  '||' |   'or') 
          b=clauseAnd {v=op.opOr(v,b);}
        )*
    ;

clauseAnd returns [Expr v]
  :
     a=clausePrimary {v = a;}
       (
          ( '&&' | 'and') 
          b=clausePrimary  {v=op.opAnd(v,b);}
      )*
    ;

clausePrimary returns [Expr v] options  {k=1;}
  :
    s=scalar{v = s;}
    | c=clauseRelationalExpr {v = c;}
    ;
clauseRelationalExpr returns [Expr v] options {backtrack=true;}
    :   ID{v=op.opFieldInList($ID.text);}
        operator=relationalOperators  
        c=additiveExpr  {v=op.opRelational(operator,v,c);}
    ;
*/
scalar returns [Expr v] options {k=1;}
  :
  (decimal{v=op.opDecimalCst($decimal.text);} | INT{v=op.opLongCst($INT.text);}) unit=ID{v=op.opUnit(v,$unit.text);};


constExpr returns [Expr v]
    : string=stringLiteral {v=op.opStringCst(string);}
      | decimal {v=op.opDecimalCst($decimal.text);}
      | INT {v=op.opLongCst($INT.text);}  
      | TimestampLiteral {v=op.opTimestampCst($TimestampLiteral.text);}  
      | DateTimeLiteral {v=op.opDatetimeCst($DateTimeLiteral.text);}  
      | DateLiteral {v=op.opDateCst($DateLiteral.text);}  
      | TimeLiteral {v=op.opTimeCst($TimeLiteral.text);}  
      | Yes {v=op.opYesnoCst(true);}  
      | No {v=op.opYesnoCst(false);}  
    ;

unaryOperators returns [Operator op]:
    INC     {op = Operator.INC; }
  | DEC     {op = Operator.DEC; }
  | NOT    {op = Operator.NOT; }
  | ADD    {op = Operator.ADD; }
  | SUB     {op = Operator.SUB; }
;

relationalOperators returns [Operator op]:
      EQ      {op = Operator.EQ; }
    | NE      {op = Operator.NE; }
    | GE      {op = Operator.GE; }
    | GT      {op = Operator.GT; }
    | LE       {op = Operator.LE; }
    | LT       {op = Operator.LT; }
;

conditionalOperators returns [Operator op]:
    AND     {op = Operator.AND; }
  | 'and'     {op = Operator.AND; }
  | OR        {op = Operator.OR; }
  | 'or'        {op = Operator.OR; }
;

multiplicativeOperators returns [Operator op]:
  | MUL     {op = Operator.MUL; }
  | DIV       {op = Operator.DIV; }
  | REM     {op = Operator.REM; }
;

additiveOperators returns [Operator op]:
    ADD     {op = Operator.ADD; }
  | SUB      {op = Operator.SUB; }
;

// *************   START  :  BASIC   *************

stringLiteral returns[String text]:
    blockString=BlockStringLiteral {text=$blockString.text.substring(3,$blockString.text.length()-3);if(text.length()>0){int start=0;if(text.charAt(start)=='\r')start++;if(text.charAt(start)=='\n')start++;text=text.substring(start,text.length());}}
    | singleString=SingleQuotationStringLiteral  {text=$singleString.text.substring(1,$singleString.text.length()-1);}
    | doubleString=DoubleQuotationStringLiteral  {text=$doubleString.text.substring(1,$doubleString.text.length()-1);}
; 
 
Yes: 'Yes' | 'yes' | 'Y' | 'y' | 'true';
No: 'No' | 'no' | 'N' | 'n' | 'false';

SingleQuotationStringLiteral :
  '"' (~('"'|'\n'|'\r'))* '"';

DoubleQuotationStringLiteral:
    '\'' (~('\''|'\n'|'\r'))* '\'';

BlockStringLiteral :
  '```' ( options {greedy=false;} : . )* '```';
  

TimestampLiteral :
  Date ' ' Time '.' MillSecond
;
DateTimeLiteral :
   Date ' ' Time 
;
DateLiteral :
  Date
;
TimeLiteral :
  Time
;

fragment Date: Digit Digit Digit Digit '-' Digit? Digit '-' Digit? Digit;
fragment Time: Digit? Digit ':' Digit? Digit ':' Digit? Digit;
fragment MillSecond:  ((Digit Digit) | Digit | ) Digit;
/*ID :  Letter (Letter | Digit | '-' | '_')*;*/  
INT :  Digit Digit*;
fragment Digit :  '0'..'9';
fragment Letter : 'a'..'z' | 'A'..'Z';



INIT  : ':=';


/* Unary Operators */
INC : '++';
DEC : '--'; 
NOT : '!';


/* Equality and Relational Operators */
EQ  : '==';
NE  : '!=';
GE  : '>=';
GT  : '>';
LE  : '<=';
LT  : '<';


/* Conditional Operators  */
AND   : '&&';
OR     : '||';


/* Arithmetic Operators  */
ADD : '+';
SUB : '-';
MUL : '*';
DIV  : '/';
REM: '%';

/* Simple Assignment Operator */
ASSIGN : '=';

NEWLINE:'\r'? '\n';   
Whitespace :  (' ' | '\t' | '\f')+ {$channel=HIDDEN;};   
SingleLineComment :
  '//' (~('\n'|'\r'))*  {$channel=HIDDEN;};
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