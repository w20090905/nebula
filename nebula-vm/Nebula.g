grammar Nebula;

options {
  language = Java;
}
@lexer::header {package nebula.vm;}
@header {
package nebula.vm;
import nebula.vm.Var;
import nebula.vm.Type;
}

@members {  // START:members
  /** Map variable name to INT object holding value */
  Map<String,Var> locals = new HashMap<>();
  short maxLocals = 0;

  protected void push(Var var) {
    locals.put(var.name,var);
    var.reg = (short) (locals.size() - 1);
    maxLocals = maxLocals > (short) locals.size() ? maxLocals : (short) locals.size();
  } 
  
  Stack<Var> tmp = new Stack<Var>();
  int tmpCount = 0;
  protected Var popTmp(Type type){      
      Var v = tmp.empty()?new TempVar("tmp" + (++tmpCount),type):tmp.pop();
      v.applied = false;
      return v;
  }
  
  protected Var pick(Var a,Var b){
      assert a.type.equals(b.type); 
      if(!b.applied)resolveTmp(b);
      if(!a.applied)resolveTmp(a);
      return popTmp(a.type);
  }
  
  protected void resolveTmp(Var v){
        assert !v.applied;
        //Resolved
        tmp.push(v);
        v.applied = true;
  }
  
  protected void clearTmp(){
      if(!tmp.empty()){
      }
      for(Var v : tmp){
        if(!v.applied){
            info("TMP VAR NOT CLEANED!!!");
        }
      }
      tmp.clear();
      tmpCount = 0;
  }
  
  protected Var v(String name) {
    for (int i = 0; i < locals.size(); i++) {
      if (locals.get(i).getName().equals(name)) {
        return locals.get(i);
      }
    }
    return null;
  };

  protected void enterClass(String name,Type superType) {};
  protected void exitClass() {;};
  
  protected void enterFunction(String name,Type returnType,List<Var> list) {;};
  protected void exitFunction() {;};

  protected void defineField(String name,Type type){info("define field " + name);};
  
  protected Type resolveType(String name){return null;};
 
  protected Var add(Var a, Var b) {Var v=pick(a,b);info(v.name +  " = " + a.name + " + " + b.name + ";\n ");return v;};
  protected Var sub(Var a, Var b) {Var v=pick(a,b);info(v.name +  " = " + a.name + " - " + b.name + ";\n ");return v;};
  protected Var mul(Var a, Var b) {Var v=pick(a,b);info(v.name +  " = " + a.name + " * " + b.name + ";\n ");return v;};
  
  
  protected Var getField(Var obj,FieldSymbol field) {
      info(" (" + obj.name + "." + field.name + ") ");
      return popTmp(null);
  };
  protected Var setField(Var obj, FieldSymbol field, Var value) {
      return value;
  };  
    
  protected Var invoke(Var obj,MethodSymbol field,List<Var> params) {
      info(" " + obj.name + "." + field.name + "(" + params + ")) "); 
      return popTmp(null);
  };
    
  protected Var set(Var to,Var from){
        info("HIDE " + to.name + " = " + from.name + ";\n");
        from.name = to.name;
        assert from.type.equals(to.type); 
        from.reg = to.reg;
        resolveTmp(from);        
        return to;
  };
  protected Var set(String text,Type type,Var v){
        info("HIDE " + text + " = " + v.name + ";\n");
        v.name = text;
        v.type = type; 
        resolveTmp(v);
        push(v);
        return v;
  };


  
  protected Var loadI(String text){
      Var v=popTmp(BuiltInTypeSymbol.INT);
      info(v.name + " = " + Integer.parseInt(text) + ";\n ");
      return v;
  };
  
  protected Var top=null;
  
  protected void info(String str){
      System.out.print(str);
  }
}// END:members

compilationUnit
    :   ( classDefinition )+ EOF
    ;
    
// *************   START:  Class   *************
classDefinition
    :   'class' ID superClass? 
            {enterClass($ID.text,$superClass.type);} 
         '{' classMember+ '}'
            {exitClass();}         
    ;

superClass returns[Type type]
  : 'extends' ID {$type=resolveType($ID.text);}
  ;
  
classMember
  : fieldDeclaration | methodDeclaration
  ; 
  
fieldDeclaration
  :   type ID ('=' e=expr)? ';' {defineField($ID.text,$type.type);}
  ;
   
methodDeclaration // START: method
  :   type name=ID '(' params=formalParameters? ')' 
        {enterFunction($name.text,$type.type,$params.list);}
      block
        {exitFunction();}
  ; // END: method

formalParameters returns [List<Var> list]
  @init{$list = new ArrayList<>(); }    
  : t=type id=ID{$list.add(new Param($id.text,$t.type));}
    ( ',' t=type id=ID{$list.add(new Param($id.text,$t.type));} 
     )* 
    ;
  
// *************   END  :  Class   *************


// *************   START:  BLOCK   *************
block // START: block
    @init{info("Block{\n");}
    @after{info("}Block\n");}
    :   '{' statement* '}' 
    ; // END: block
  
statement
    @after{clearTmp();}
    :   block
    |   varDeclaration  ';' {clearTmp();}
    |   'return' expr?  ';' {clearTmp();}
    |   exprStatement   ';' {clearTmp();}
    |   ';' 
    ;   

varDeclaration
    :   type ID
        ('=' v=expr  {set($ID.text,$type.type,v);} 
        )? 
    ; 
    
exprStatement
    options {backtrack=true;}
    :   to=postfixexpr 
        ('=' from=expr  )?
        {   if(from==null){
                assert to.field == null;
            } else if (to.field==null){
                set(to.value,from);
            } else {
                setField(to.value,to.field,from);
            }
        }
    ;
    
expr returns [Var value]
    :   e=addexpr {$value = $e.value;}
    ;
    
addexpr returns [Var value]
    :   a=multexpr {$value = a;}
        (   '+' b=multexpr  {$value=add(a,b);}
        |   '-' c=multexpr  {$value=sub(a,c);}
        )*
    ; // END:addexpr
    
multexpr returns [Var value]
    :   a=postfixExprValue {$value=a;} 
        (   '*' b=postfixExprValue {$value=mul(a,b);} 
        )*
    ;
    
postfixExprValue returns [Var value]
    :p=postfixexpr{$value=$p.value; if($p.field!=null){$value=getField($value,$p.field);} }
    ;

postfixexpr returns [Var value,FieldSymbol field]
    :   (e=primary{$value = $e.value;})
        ( options {backtrack=true;}
         : '.' m=refMethod[$value.type] '(' params=exprList ')' {if($field!=null){$value=getField($value,$field);$field=null;} $value = invoke($value,m,params);}
         | '.' f=refField[$value.type] { if($field!=null){$value=getField($value,$field);$field=null;}$field=f; }
        )*
    ;
    
refMethod[Type objType] returns [MethodSymbol value]
    : ID {$value =new MethodSymbol((ClassSymbol)objType,$ID.text);}
    ;
    
refField[Type objType] returns [FieldSymbol value]
    : ID{$value = new FieldSymbol((ClassSymbol)objType,$ID.text);}
    ;

exprList returns [List<Var> list]
    @init{$list = new ArrayList<>(); }    
    :   e=expr{list.add(e);} 
        (',' expr{list.add(e);})* | 
    ; 

primary returns [Var value] // START:atom
    :   ('new' type '(' ps=exprList ')'){;} // TODO new object
    |   ref=ref_this      {$value = ref;}
    |   ref=ref_super     {$value = ref;}
    |   ref=ref_const     {$value = ref;}
    |   ID                {$value = locals.get($ID.text);}
    |   '(' v=expr ')'    {$value = v;}
    ; // END:atom
    
// *************   END  :  BLOCK   *************

// *************   START:  BASIC   *************

ref_this returns [Var value]
    :   'this'{$value = locals.get("this");}
    ;

ref_super returns [Var value]
    :   'super'{$value = locals.get("super");}
    ;
    
ref_const returns [Var value]
    :   INT {$value = loadI($INT.text);}
    ;
    
type returns [Type type]
    :   'decimal' {$type = BuiltInTypeSymbol.DECIMAL;}
    |   'int'     {$type = BuiltInTypeSymbol.INT;}
    |   'void'    {$type = BuiltInTypeSymbol.VOID;}
    |   ID        {$type = resolveType($ID.text);}
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