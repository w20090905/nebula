parser grammar SParser;

options {
  language=Java;
  tokenVocab=STLexer;
  TokenLabelType=CommonToken;
}

tokens {
  EXPR; OPTIONS; PROP; PROP_IND; INCLUDE; INCLUDE_IND; EXEC_FUNC; INCLUDE_SUPER;
  INCLUDE_SUPER_REGION; INCLUDE_REGION; TO_STR; LIST; MAP; ZIP; SUBTEMPLATE; ARGS;
  ELEMENTS; REGION; NULL; INDENTED_EXPR;
  }
  
@header {
package nebula.simpletemplate;

import org.objectweb.asm.Type;

import org.antlr.runtime.*;
import org.stringtemplate.v4.misc.ErrorManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
}

@rulecatch {
   catch (RecognitionException re) { throw re; }
}

@members {
  ErrorManager errMgr;
  Token templateToken;

  @Override
  protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
    throws RecognitionException
  {
    throw new MismatchedTokenException(ttype, input);
  }
    
   Compiler c = new Compiler();
  
  private Map<String, Var> locals = new HashMap<String, Var>();
  protected int maxLocals = 0;

      protected void initLocals() {
        locals.clear();
        pushLocal("this",Type.getType(Action.class));
        pushLocal("out",Type.getType(StringBuilder.class));
        pushLocal("object",Type.getType(Object.class));
        root = pushLocal("root",Type.getType(Object.class));
      }
      
      Var root;
      
      protected Var pushLocal(String name, Type type) {
        Var var = new Var(name,type,locals.size());
        locals.put(var.name, var);
        return var;
      }
      
      protected Var v(String name) {
        Var var = locals.get(name);
        return var;
      };
}
templateAndEOF returns[Statement s] 
@init{initLocals();}
: t=template{s=t;} EOF ;

template returns[Statement s]
@init{List<Statement> statments = new ArrayList<Statement>();}
 : (e=element{if(e!=null)statments.add(e);})* {s=c.stBlock(statments);}
 ;

element returns[Statement s] 
  : {input.LT(1).getCharPositionInLine()==0}? INDENT ? COMMENT NEWLINE {s=c.stOutput(c.opStringCst($NEWLINE.text));}
  | INDENT p=singleElement{s=p;}
  | p=singleElement{s=p;}
  ;

singleElement returns[Statement v] 
  : p=exprTag{v=c.stOutput(p);}
  | TEXT {v=c.stOutput(c.opStringCst($TEXT.text));}
  | NEWLINE {v=c.stOutput(c.opStringCst($NEWLINE.text));}
  | COMMENT // throw away
  ;

exprTag returns[Expr v] 
  : LDELIM p=expr RDELIM {v=p;}
  ;

expr  returns[Expr v] : p=primary {v=p;};

primary returns[Expr v] 
  : ID {v = c.opFieldOf(v=c.opLocal(root),$ID.text);}
  | STRING  {v=c.opStringCst($STRING.text);}
  | TRUE  {v=c.opYesnoCst(true);}  
  | FALSE {v=c.opYesnoCst(false);}  
  ;