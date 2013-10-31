parser grammar STParser;

options {
  language=Java;
  tokenVocab=STLexer;
  TokenLabelType=CommonToken;
  output=AST;
  ASTLabelType=CommonTree;
}

tokens {
  EXPR; OPTIONS; PROP; PROP_IND; INCLUDE; INCLUDE_IND; EXEC_FUNC; INCLUDE_SUPER;
  INCLUDE_SUPER_REGION; INCLUDE_REGION; TO_STR; LIST; MAP; ZIP; SUBTEMPLATE; ARGS;
  ELEMENTS; REGION; NULL; INDENTED_EXPR;
  }

@header {
    package nebula.simpletemplate;
}

rule: (TEXT | EXPR ) *;
