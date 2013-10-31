// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g 2013-10-31 11:07:03

    package nebula.simpletemplate;
    
    
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.RuleReturnScope;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.RewriteEarlyExitException;
import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
import org.antlr.runtime.tree.RewriteRuleTokenStream;
import org.antlr.runtime.tree.TreeAdaptor;
import org.stringtemplate.v4.misc.ErrorManager;
import org.stringtemplate.v4.misc.ErrorType;

import org.stringtemplate.v4.compiler.Compiler;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class STParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IF", "ELSE", "ELSEIF", "ENDIF", "SUPER", "SEMI", "BANG", "ELLIPSIS", "EQUALS", "COLON", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "COMMA", "DOT", "LCURLY", "RCURLY", "TEXT", "LDELIM", "RDELIM", "ID", "STRING", "WS", "PIPE", "OR", "AND", "INDENT", "NEWLINE", "AT", "END", "TRUE", "FALSE", "COMMENT", "ARGS", "ELEMENTS", "EXEC_FUNC", "EXPR", "INCLUDE", "INCLUDE_IND", "INCLUDE_REGION", "INCLUDE_SUPER", "INCLUDE_SUPER_REGION", "INDENTED_EXPR", "LIST", "MAP", "NULL", "OPTIONS", "PROP", "PROP_IND", "REGION", "SUBTEMPLATE", "TO_STR", "ZIP"
    };

    public static final int EOF=-1;
    public static final int RBRACK=17;
    public static final int LBRACK=16;
    public static final int ELSE=5;
    public static final int ELLIPSIS=11;
    public static final int LCURLY=20;
    public static final int BANG=10;
    public static final int EQUALS=12;
    public static final int TEXT=22;
    public static final int ID=25;
    public static final int SEMI=9;
    public static final int LPAREN=14;
    public static final int IF=4;
    public static final int ELSEIF=6;
    public static final int COLON=13;
    public static final int RPAREN=15;
    public static final int WS=27;
    public static final int COMMA=18;
    public static final int RCURLY=21;
    public static final int ENDIF=7;
    public static final int RDELIM=24;
    public static final int SUPER=8;
    public static final int DOT=19;
    public static final int LDELIM=23;
    public static final int STRING=26;
    public static final int PIPE=28;
    public static final int OR=29;
    public static final int AND=30;
    public static final int INDENT=31;
    public static final int NEWLINE=32;
    public static final int AT=33;
    public static final int END=34;
    public static final int TRUE=35;
    public static final int FALSE=36;
    public static final int COMMENT=37;
    public static final int ARGS=38;
    public static final int ELEMENTS=39;
    public static final int EXEC_FUNC=40;
    public static final int EXPR=41;
    public static final int INCLUDE=42;
    public static final int INCLUDE_IND=43;
    public static final int INCLUDE_REGION=44;
    public static final int INCLUDE_SUPER=45;
    public static final int INCLUDE_SUPER_REGION=46;
    public static final int INDENTED_EXPR=47;
    public static final int LIST=48;
    public static final int MAP=49;
    public static final int NULL=50;
    public static final int OPTIONS=51;
    public static final int PROP=52;
    public static final int PROP_IND=53;
    public static final int REGION=54;
    public static final int SUBTEMPLATE=55;
    public static final int TO_STR=56;
    public static final int ZIP=57;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public STParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public STParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return STParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g"; }


    ErrorManager errMgr;
    Token templateToken;
    public STParser(TokenStream input, ErrorManager errMgr, Token templateToken) {
      this(input);
      this.errMgr = errMgr;
      this.templateToken = templateToken;
    }
    @Override
    protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
      throws RecognitionException
    {
      throw new MismatchedTokenException(ttype, input);
    }


    public static class templateAndEOF_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "templateAndEOF"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:67:1: templateAndEOF : template EOF -> ( template )? ;
    public final STParser.templateAndEOF_return templateAndEOF() throws RecognitionException {
        STParser.templateAndEOF_return retval = new STParser.templateAndEOF_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken EOF2=null;
        STParser.template_return template1 =null;


        CommonTree EOF2_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_template=new RewriteRuleSubtreeStream(adaptor,"rule template");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:67:16: ( template EOF -> ( template )? )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:67:18: template EOF
            {
            pushFollow(FOLLOW_template_in_templateAndEOF143);
            template1=template();

            state._fsp--;

            stream_template.add(template1.getTree());

            EOF2=(CommonToken)match(input,EOF,FOLLOW_EOF_in_templateAndEOF145);  
            stream_EOF.add(EOF2);


            // AST REWRITE
            // elements: template
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 67:31: -> ( template )?
            {
                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:67:34: ( template )?
                if ( stream_template.hasNext() ) {
                    adaptor.addChild(root_0, stream_template.nextTree());

                }
                stream_template.reset();

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "templateAndEOF"


    public static class template_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "template"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:69:1: template : ( element )* ;
    public final STParser.template_return template() throws RecognitionException {
        STParser.template_return retval = new STParser.template_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        STParser.element_return element3 =null;



        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:69:10: ( ( element )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:69:12: ( element )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:69:12: ( element )*
            loop1:
            do {
                int alt1=2;
                switch ( input.LA(1) ) {
                case INDENT:
                    {
                    int LA1_2 = input.LA(2);

                    if ( (LA1_2==LDELIM) ) {
                        int LA1_5 = input.LA(3);

                        if ( (LA1_5==IF||LA1_5==SUPER||LA1_5==LPAREN||LA1_5==LBRACK||LA1_5==LCURLY||(LA1_5 >= ID && LA1_5 <= STRING)||LA1_5==AT||(LA1_5 >= TRUE && LA1_5 <= FALSE)) ) {
                            alt1=1;
                        }


                    }
                    else if ( (LA1_2==TEXT||LA1_2==NEWLINE||LA1_2==COMMENT) ) {
                        alt1=1;
                    }


                    }
                    break;
                case LDELIM:
                    {
                    int LA1_3 = input.LA(2);

                    if ( (LA1_3==IF||LA1_3==SUPER||LA1_3==LPAREN||LA1_3==LBRACK||LA1_3==LCURLY||(LA1_3 >= ID && LA1_3 <= STRING)||LA1_3==AT||(LA1_3 >= TRUE && LA1_3 <= FALSE)) ) {
                        alt1=1;
                    }


                    }
                    break;
                case TEXT:
                case NEWLINE:
                case COMMENT:
                    {
                    alt1=1;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:69:12: element
            	    {
            	    pushFollow(FOLLOW_element_in_template159);
            	    element3=element();

            	    state._fsp--;

            	    adaptor.addChild(root_0, element3.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "template"


    public static class element_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "element"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:71:1: element : ({...}? ( INDENT )? COMMENT NEWLINE ->| INDENT singleElement -> ^( INDENTED_EXPR INDENT ( singleElement )? ) | singleElement | compoundElement );
    public final STParser.element_return element() throws RecognitionException {
        STParser.element_return retval = new STParser.element_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken INDENT4=null;
        CommonToken COMMENT5=null;
        CommonToken NEWLINE6=null;
        CommonToken INDENT7=null;
        STParser.singleElement_return singleElement8 =null;

        STParser.singleElement_return singleElement9 =null;

        STParser.compoundElement_return compoundElement10 =null;


        CommonTree INDENT4_tree=null;
        CommonTree COMMENT5_tree=null;
        CommonTree NEWLINE6_tree=null;
        CommonTree INDENT7_tree=null;
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_COMMENT=new RewriteRuleTokenStream(adaptor,"token COMMENT");
        RewriteRuleTokenStream stream_INDENT=new RewriteRuleTokenStream(adaptor,"token INDENT");
        RewriteRuleSubtreeStream stream_singleElement=new RewriteRuleSubtreeStream(adaptor,"rule singleElement");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:72:3: ({...}? ( INDENT )? COMMENT NEWLINE ->| INDENT singleElement -> ^( INDENTED_EXPR INDENT ( singleElement )? ) | singleElement | compoundElement )
            int alt3=4;
            switch ( input.LA(1) ) {
            case INDENT:
                {
                switch ( input.LA(2) ) {
                case COMMENT:
                    {
                    int LA3_5 = input.LA(3);

                    if ( (LA3_5==NEWLINE) ) {
                        int LA3_11 = input.LA(4);

                        if ( ((input.LT(1).getCharPositionInLine()==0)) ) {
                            alt3=1;
                        }
                        else if ( (true) ) {
                            alt3=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 11, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA3_5==EOF||(LA3_5 >= RCURLY && LA3_5 <= LDELIM)||LA3_5==INDENT||LA3_5==COMMENT) ) {
                        alt3=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 5, input);

                        throw nvae;

                    }
                    }
                    break;
                case LDELIM:
                    {
                    switch ( input.LA(3) ) {
                    case IF:
                        {
                        alt3=4;
                        }
                        break;
                    case AT:
                        {
                        int LA3_12 = input.LA(4);

                        if ( (LA3_12==ID) ) {
                            int LA3_15 = input.LA(5);

                            if ( (LA3_15==RDELIM) ) {
                                alt3=4;
                            }
                            else if ( (LA3_15==LPAREN) ) {
                                alt3=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 3, 15, input);

                                throw nvae;

                            }
                        }
                        else if ( (LA3_12==SUPER) ) {
                            alt3=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 12, input);

                            throw nvae;

                        }
                        }
                        break;
                    case SUPER:
                    case LPAREN:
                    case LBRACK:
                    case LCURLY:
                    case ID:
                    case STRING:
                    case TRUE:
                    case FALSE:
                        {
                        alt3=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 6, input);

                        throw nvae;

                    }

                    }
                    break;
                case TEXT:
                case NEWLINE:
                    {
                    alt3=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;

                }

                }
                break;
            case COMMENT:
                {
                int LA3_2 = input.LA(2);

                if ( (LA3_2==NEWLINE) ) {
                    int LA3_8 = input.LA(3);

                    if ( ((input.LT(1).getCharPositionInLine()==0)) ) {
                        alt3=1;
                    }
                    else if ( (true) ) {
                        alt3=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 8, input);

                        throw nvae;

                    }
                }
                else if ( (LA3_2==EOF||(LA3_2 >= RCURLY && LA3_2 <= LDELIM)||LA3_2==INDENT||LA3_2==COMMENT) ) {
                    alt3=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

                    throw nvae;

                }
                }
                break;
            case LDELIM:
                {
                switch ( input.LA(2) ) {
                case IF:
                    {
                    alt3=4;
                    }
                    break;
                case AT:
                    {
                    int LA3_10 = input.LA(3);

                    if ( (LA3_10==ID) ) {
                        int LA3_14 = input.LA(4);

                        if ( (LA3_14==RDELIM) ) {
                            alt3=4;
                        }
                        else if ( (LA3_14==LPAREN) ) {
                            alt3=3;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 14, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA3_10==SUPER) ) {
                        alt3=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 10, input);

                        throw nvae;

                    }
                    }
                    break;
                case SUPER:
                case LPAREN:
                case LBRACK:
                case LCURLY:
                case ID:
                case STRING:
                case TRUE:
                case FALSE:
                    {
                    alt3=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 3, input);

                    throw nvae;

                }

                }
                break;
            case TEXT:
            case NEWLINE:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:72:5: {...}? ( INDENT )? COMMENT NEWLINE
                    {
                    if ( !((input.LT(1).getCharPositionInLine()==0)) ) {
                        throw new FailedPredicateException(input, "element", "input.LT(1).getCharPositionInLine()==0");
                    }

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:72:47: ( INDENT )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==INDENT) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:72:47: INDENT
                            {
                            INDENT4=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_element173);  
                            stream_INDENT.add(INDENT4);


                            }
                            break;

                    }


                    COMMENT5=(CommonToken)match(input,COMMENT,FOLLOW_COMMENT_in_element176);  
                    stream_COMMENT.add(COMMENT5);


                    NEWLINE6=(CommonToken)match(input,NEWLINE,FOLLOW_NEWLINE_in_element178);  
                    stream_NEWLINE.add(NEWLINE6);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 72:71: ->
                    {
                        root_0 = null;
                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:73:5: INDENT singleElement
                    {
                    INDENT7=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_element187);  
                    stream_INDENT.add(INDENT7);


                    pushFollow(FOLLOW_singleElement_in_element189);
                    singleElement8=singleElement();

                    state._fsp--;

                    stream_singleElement.add(singleElement8.getTree());

                    // AST REWRITE
                    // elements: INDENT, singleElement
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 73:26: -> ^( INDENTED_EXPR INDENT ( singleElement )? )
                    {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:73:29: ^( INDENTED_EXPR INDENT ( singleElement )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(INDENTED_EXPR, "INDENTED_EXPR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_INDENT.nextNode()
                        );

                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:73:52: ( singleElement )?
                        if ( stream_singleElement.hasNext() ) {
                            adaptor.addChild(root_1, stream_singleElement.nextTree());

                        }
                        stream_singleElement.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:74:5: singleElement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_singleElement_in_element207);
                    singleElement9=singleElement();

                    state._fsp--;

                    adaptor.addChild(root_0, singleElement9.getTree());

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:75:5: compoundElement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_compoundElement_in_element213);
                    compoundElement10=compoundElement();

                    state._fsp--;

                    adaptor.addChild(root_0, compoundElement10.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "element"


    public static class singleElement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "singleElement"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:78:1: singleElement : ( exprTag | TEXT | NEWLINE | COMMENT !);
    public final STParser.singleElement_return singleElement() throws RecognitionException {
        STParser.singleElement_return retval = new STParser.singleElement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken TEXT12=null;
        CommonToken NEWLINE13=null;
        CommonToken COMMENT14=null;
        STParser.exprTag_return exprTag11 =null;


        CommonTree TEXT12_tree=null;
        CommonTree NEWLINE13_tree=null;
        CommonTree COMMENT14_tree=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:79:3: ( exprTag | TEXT | NEWLINE | COMMENT !)
            int alt4=4;
            switch ( input.LA(1) ) {
            case LDELIM:
                {
                alt4=1;
                }
                break;
            case TEXT:
                {
                alt4=2;
                }
                break;
            case NEWLINE:
                {
                alt4=3;
                }
                break;
            case COMMENT:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:79:5: exprTag
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_exprTag_in_singleElement226);
                    exprTag11=exprTag();

                    state._fsp--;

                    adaptor.addChild(root_0, exprTag11.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:80:5: TEXT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    TEXT12=(CommonToken)match(input,TEXT,FOLLOW_TEXT_in_singleElement232); 
                    TEXT12_tree = 
                    (CommonTree)adaptor.create(TEXT12)
                    ;
                    adaptor.addChild(root_0, TEXT12_tree);


                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:81:5: NEWLINE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    NEWLINE13=(CommonToken)match(input,NEWLINE,FOLLOW_NEWLINE_in_singleElement238); 
                    NEWLINE13_tree = 
                    (CommonTree)adaptor.create(NEWLINE13)
                    ;
                    adaptor.addChild(root_0, NEWLINE13_tree);


                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:82:5: COMMENT !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    COMMENT14=(CommonToken)match(input,COMMENT,FOLLOW_COMMENT_in_singleElement244); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "singleElement"


    public static class compoundElement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "compoundElement"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:85:1: compoundElement : ( ifstat | region );
    public final STParser.compoundElement_return compoundElement() throws RecognitionException {
        STParser.compoundElement_return retval = new STParser.compoundElement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        STParser.ifstat_return ifstat15 =null;

        STParser.region_return region16 =null;



        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:86:3: ( ifstat | region )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==INDENT) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==LDELIM) ) {
                    int LA5_2 = input.LA(3);

                    if ( (LA5_2==IF) ) {
                        alt5=1;
                    }
                    else if ( (LA5_2==AT) ) {
                        alt5=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA5_0==LDELIM) ) {
                int LA5_2 = input.LA(2);

                if ( (LA5_2==IF) ) {
                    alt5=1;
                }
                else if ( (LA5_2==AT) ) {
                    alt5=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:86:5: ifstat
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_ifstat_in_compoundElement259);
                    ifstat15=ifstat();

                    state._fsp--;

                    adaptor.addChild(root_0, ifstat15.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:87:5: region
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_region_in_compoundElement265);
                    region16=region();

                    state._fsp--;

                    adaptor.addChild(root_0, region16.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "compoundElement"


    public static class exprTag_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "exprTag"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:90:1: exprTag : LDELIM expr ( ';' exprOptions )? RDELIM -> ^( EXPR[$LDELIM,\"EXPR\"] expr ( exprOptions )? ) ;
    public final STParser.exprTag_return exprTag() throws RecognitionException {
        STParser.exprTag_return retval = new STParser.exprTag_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken LDELIM17=null;
        CommonToken char_literal19=null;
        CommonToken RDELIM21=null;
        STParser.expr_return expr18 =null;

        STParser.exprOptions_return exprOptions20 =null;


        CommonTree LDELIM17_tree=null;
        CommonTree char_literal19_tree=null;
        CommonTree RDELIM21_tree=null;
        RewriteRuleTokenStream stream_RDELIM=new RewriteRuleTokenStream(adaptor,"token RDELIM");
        RewriteRuleTokenStream stream_LDELIM=new RewriteRuleTokenStream(adaptor,"token LDELIM");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_exprOptions=new RewriteRuleSubtreeStream(adaptor,"rule exprOptions");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:91:3: ( LDELIM expr ( ';' exprOptions )? RDELIM -> ^( EXPR[$LDELIM,\"EXPR\"] expr ( exprOptions )? ) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:91:5: LDELIM expr ( ';' exprOptions )? RDELIM
            {
            LDELIM17=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_exprTag278);  
            stream_LDELIM.add(LDELIM17);


            pushFollow(FOLLOW_expr_in_exprTag280);
            expr18=expr();

            state._fsp--;

            stream_expr.add(expr18.getTree());

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:91:17: ( ';' exprOptions )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==SEMI) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:91:19: ';' exprOptions
                    {
                    char_literal19=(CommonToken)match(input,SEMI,FOLLOW_SEMI_in_exprTag284);  
                    stream_SEMI.add(char_literal19);


                    pushFollow(FOLLOW_exprOptions_in_exprTag286);
                    exprOptions20=exprOptions();

                    state._fsp--;

                    stream_exprOptions.add(exprOptions20.getTree());

                    }
                    break;

            }


            RDELIM21=(CommonToken)match(input,RDELIM,FOLLOW_RDELIM_in_exprTag291);  
            stream_RDELIM.add(RDELIM21);


            // AST REWRITE
            // elements: exprOptions, expr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 92:5: -> ^( EXPR[$LDELIM,\"EXPR\"] expr ( exprOptions )? )
            {
                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:92:8: ^( EXPR[$LDELIM,\"EXPR\"] expr ( exprOptions )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(EXPR, LDELIM17, "EXPR")
                , root_1);

                adaptor.addChild(root_1, stream_expr.nextTree());

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:92:36: ( exprOptions )?
                if ( stream_exprOptions.hasNext() ) {
                    adaptor.addChild(root_1, stream_exprOptions.nextTree());

                }
                stream_exprOptions.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "exprTag"


    public static class region_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "region"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:95:1: region : (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )? -> {indent!=null}? ^( INDENTED_EXPR $i ^( REGION[$x] ID ( template )? ) ) -> ^( REGION[$x] ID ( template )? ) ;
    public final STParser.region_return region() throws RecognitionException {
        STParser.region_return retval = new STParser.region_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken i=null;
        CommonToken x=null;
        CommonToken char_literal22=null;
        CommonToken ID23=null;
        CommonToken RDELIM24=null;
        CommonToken INDENT26=null;
        CommonToken LDELIM27=null;
        CommonToken string_literal28=null;
        CommonToken RDELIM29=null;
        CommonToken NEWLINE30=null;
        STParser.template_return template25 =null;


        CommonTree i_tree=null;
        CommonTree x_tree=null;
        CommonTree char_literal22_tree=null;
        CommonTree ID23_tree=null;
        CommonTree RDELIM24_tree=null;
        CommonTree INDENT26_tree=null;
        CommonTree LDELIM27_tree=null;
        CommonTree string_literal28_tree=null;
        CommonTree RDELIM29_tree=null;
        CommonTree NEWLINE30_tree=null;
        RewriteRuleTokenStream stream_AT=new RewriteRuleTokenStream(adaptor,"token AT");
        RewriteRuleTokenStream stream_RDELIM=new RewriteRuleTokenStream(adaptor,"token RDELIM");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_LDELIM=new RewriteRuleTokenStream(adaptor,"token LDELIM");
        RewriteRuleTokenStream stream_INDENT=new RewriteRuleTokenStream(adaptor,"token INDENT");
        RewriteRuleSubtreeStream stream_template=new RewriteRuleSubtreeStream(adaptor,"rule template");
        Token indent=null;
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:97:3: ( (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )? -> {indent!=null}? ^( INDENTED_EXPR $i ^( REGION[$x] ID ( template )? ) ) -> ^( REGION[$x] ID ( template )? ) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:97:5: (i= INDENT )? x= LDELIM '@' ID RDELIM template ( INDENT )? LDELIM '@end' RDELIM ({...}? => NEWLINE )?
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:97:6: (i= INDENT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==INDENT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:97:6: i= INDENT
                    {
                    i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_region327);  
                    stream_INDENT.add(i);


                    }
                    break;

            }


            x=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_region332);  
            stream_LDELIM.add(x);


            char_literal22=(CommonToken)match(input,AT,FOLLOW_AT_in_region334);  
            stream_AT.add(char_literal22);


            ID23=(CommonToken)match(input,ID,FOLLOW_ID_in_region336);  
            stream_ID.add(ID23);


            RDELIM24=(CommonToken)match(input,RDELIM,FOLLOW_RDELIM_in_region338);  
            stream_RDELIM.add(RDELIM24);


            if (input.LA(1)!=NEWLINE) indent=i;

            pushFollow(FOLLOW_template_in_region346);
            template25=template();

            state._fsp--;

            stream_template.add(template25.getTree());

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:99:5: ( INDENT )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==INDENT) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:99:5: INDENT
                    {
                    INDENT26=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_region352);  
                    stream_INDENT.add(INDENT26);


                    }
                    break;

            }


            LDELIM27=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_region355);  
            stream_LDELIM.add(LDELIM27);


            string_literal28=(CommonToken)match(input,END,FOLLOW_END_in_region357);  
            stream_END.add(string_literal28);


            RDELIM29=(CommonToken)match(input,RDELIM,FOLLOW_RDELIM_in_region359);  
            stream_RDELIM.add(RDELIM29);


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:101:5: ({...}? => NEWLINE )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NEWLINE) ) {
                int LA9_1 = input.LA(2);

                if ( ((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                    alt9=1;
                }
            }
            switch (alt9) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:101:6: {...}? => NEWLINE
                    {
                    if ( !((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                        throw new FailedPredicateException(input, "region", "$region.start.getLine()!=input.LT(1).getLine()");
                    }

                    NEWLINE30=(CommonToken)match(input,NEWLINE,FOLLOW_NEWLINE_in_region374);  
                    stream_NEWLINE.add(NEWLINE30);


                    }
                    break;

            }


            // AST REWRITE
            // elements: ID, ID, template, template, i
            // token labels: i
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 102:5: -> {indent!=null}? ^( INDENTED_EXPR $i ^( REGION[$x] ID ( template )? ) )
            if (indent!=null) {
                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:103:8: ^( INDENTED_EXPR $i ^( REGION[$x] ID ( template )? ) )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(INDENTED_EXPR, "INDENTED_EXPR")
                , root_1);

                adaptor.addChild(root_1, stream_i.nextNode());

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:103:27: ^( REGION[$x] ID ( template )? )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(REGION, x)
                , root_2);

                adaptor.addChild(root_2, 
                stream_ID.nextNode()
                );

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:103:43: ( template )?
                if ( stream_template.hasNext() ) {
                    adaptor.addChild(root_2, stream_template.nextTree());

                }
                stream_template.reset();

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }

            else // 104:5: -> ^( REGION[$x] ID ( template )? )
            {
                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:104:27: ^( REGION[$x] ID ( template )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(REGION, x)
                , root_1);

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:104:43: ( template )?
                if ( stream_template.hasNext() ) {
                    adaptor.addChild(root_1, stream_template.nextTree());

                }
                stream_template.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "region"


    public static class subtemplate_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "subtemplate"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:107:1: subtemplate : lc= '{' (ids+= ID ( ',' ids+= ID )* '|' )? template ( INDENT )? '}' -> ^( SUBTEMPLATE[$lc,\"SUBTEMPLATE\"] ( ^( ARGS $ids) )* ( template )? ) ;
    public final STParser.subtemplate_return subtemplate() throws RecognitionException {
        STParser.subtemplate_return retval = new STParser.subtemplate_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken lc=null;
        CommonToken char_literal31=null;
        CommonToken char_literal32=null;
        CommonToken INDENT34=null;
        CommonToken char_literal35=null;
        CommonToken ids=null;
        List list_ids=null;
        STParser.template_return template33 =null;


        CommonTree lc_tree=null;
        CommonTree char_literal31_tree=null;
        CommonTree char_literal32_tree=null;
        CommonTree INDENT34_tree=null;
        CommonTree char_literal35_tree=null;
        CommonTree ids_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_PIPE=new RewriteRuleTokenStream(adaptor,"token PIPE");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_INDENT=new RewriteRuleTokenStream(adaptor,"token INDENT");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_template=new RewriteRuleSubtreeStream(adaptor,"rule template");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:108:3: (lc= '{' (ids+= ID ( ',' ids+= ID )* '|' )? template ( INDENT )? '}' -> ^( SUBTEMPLATE[$lc,\"SUBTEMPLATE\"] ( ^( ARGS $ids) )* ( template )? ) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:108:5: lc= '{' (ids+= ID ( ',' ids+= ID )* '|' )? template ( INDENT )? '}'
            {
            lc=(CommonToken)match(input,LCURLY,FOLLOW_LCURLY_in_subtemplate458);  
            stream_LCURLY.add(lc);


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:108:12: (ids+= ID ( ',' ids+= ID )* '|' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==ID) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:108:13: ids+= ID ( ',' ids+= ID )* '|'
                    {
                    ids=(CommonToken)match(input,ID,FOLLOW_ID_in_subtemplate464);  
                    stream_ID.add(ids);

                    if (list_ids==null) list_ids=new ArrayList();
                    list_ids.add(ids);


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:108:22: ( ',' ids+= ID )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==COMMA) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:108:24: ',' ids+= ID
                    	    {
                    	    char_literal31=(CommonToken)match(input,COMMA,FOLLOW_COMMA_in_subtemplate468);  
                    	    stream_COMMA.add(char_literal31);


                    	    ids=(CommonToken)match(input,ID,FOLLOW_ID_in_subtemplate473);  
                    	    stream_ID.add(ids);

                    	    if (list_ids==null) list_ids=new ArrayList();
                    	    list_ids.add(ids);


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    char_literal32=(CommonToken)match(input,PIPE,FOLLOW_PIPE_in_subtemplate478);  
                    stream_PIPE.add(char_literal32);


                    }
                    break;

            }


            pushFollow(FOLLOW_template_in_subtemplate483);
            template33=template();

            state._fsp--;

            stream_template.add(template33.getTree());

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:108:56: ( INDENT )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==INDENT) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:108:56: INDENT
                    {
                    INDENT34=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_subtemplate485);  
                    stream_INDENT.add(INDENT34);


                    }
                    break;

            }


            char_literal35=(CommonToken)match(input,RCURLY,FOLLOW_RCURLY_in_subtemplate488);  
            stream_RCURLY.add(char_literal35);


            // AST REWRITE
            // elements: ids, template
            // token labels: 
            // rule labels: retval
            // token list labels: ids
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_ids=new RewriteRuleTokenStream(adaptor,"token ids", list_ids);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 110:5: -> ^( SUBTEMPLATE[$lc,\"SUBTEMPLATE\"] ( ^( ARGS $ids) )* ( template )? )
            {
                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:110:8: ^( SUBTEMPLATE[$lc,\"SUBTEMPLATE\"] ( ^( ARGS $ids) )* ( template )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(SUBTEMPLATE, lc, "SUBTEMPLATE")
                , root_1);

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:110:41: ( ^( ARGS $ids) )*
                while ( stream_ids.hasNext() ) {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:110:41: ^( ARGS $ids)
                    {
                    CommonTree root_2 = (CommonTree)adaptor.nil();
                    root_2 = (CommonTree)adaptor.becomeRoot(
                    (CommonTree)adaptor.create(ARGS, "ARGS")
                    , root_2);

                    adaptor.addChild(root_2, stream_ids.nextNode());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_ids.reset();

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:110:55: ( template )?
                if ( stream_template.hasNext() ) {
                    adaptor.addChild(root_1, stream_template.nextTree());

                }
                stream_template.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "subtemplate"


    public static class ifstat_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ifstat"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:113:1: ifstat : (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= template ( ( INDENT )? LDELIM 'elseif' '(' c2+= conditional ')' RDELIM t2+= template )* ( ( INDENT )? LDELIM 'else' RDELIM t3= template )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )? -> {indent!=null}? ^( INDENTED_EXPR $i ^( 'if' $c1 ( $t1)? ( ^( 'elseif' $c2 $t2) )* ( ^( 'else' ( $t3)? ) )? ) ) -> ^( 'if' $c1 ( $t1)? ( ^( 'elseif' $c2 $t2) )* ( ^( 'else' ( $t3)? ) )? ) ;
    public final STParser.ifstat_return ifstat() throws RecognitionException {
        STParser.ifstat_return retval = new STParser.ifstat_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken i=null;
        CommonToken endif=null;
        CommonToken LDELIM36=null;
        CommonToken string_literal37=null;
        CommonToken char_literal38=null;
        CommonToken char_literal39=null;
        CommonToken RDELIM40=null;
        CommonToken INDENT41=null;
        CommonToken LDELIM42=null;
        CommonToken string_literal43=null;
        CommonToken char_literal44=null;
        CommonToken char_literal45=null;
        CommonToken RDELIM46=null;
        CommonToken INDENT47=null;
        CommonToken LDELIM48=null;
        CommonToken string_literal49=null;
        CommonToken RDELIM50=null;
        CommonToken INDENT51=null;
        CommonToken string_literal52=null;
        CommonToken RDELIM53=null;
        CommonToken NEWLINE54=null;
        List list_c2=null;
        List list_t2=null;
        STParser.conditional_return c1 =null;

        STParser.template_return t1 =null;

        STParser.template_return t3 =null;

        RuleReturnScope c2 = null;
        RuleReturnScope t2 = null;
        CommonTree i_tree=null;
        CommonTree endif_tree=null;
        CommonTree LDELIM36_tree=null;
        CommonTree string_literal37_tree=null;
        CommonTree char_literal38_tree=null;
        CommonTree char_literal39_tree=null;
        CommonTree RDELIM40_tree=null;
        CommonTree INDENT41_tree=null;
        CommonTree LDELIM42_tree=null;
        CommonTree string_literal43_tree=null;
        CommonTree char_literal44_tree=null;
        CommonTree char_literal45_tree=null;
        CommonTree RDELIM46_tree=null;
        CommonTree INDENT47_tree=null;
        CommonTree LDELIM48_tree=null;
        CommonTree string_literal49_tree=null;
        CommonTree RDELIM50_tree=null;
        CommonTree INDENT51_tree=null;
        CommonTree string_literal52_tree=null;
        CommonTree RDELIM53_tree=null;
        CommonTree NEWLINE54_tree=null;
        RewriteRuleTokenStream stream_ENDIF=new RewriteRuleTokenStream(adaptor,"token ENDIF");
        RewriteRuleTokenStream stream_RDELIM=new RewriteRuleTokenStream(adaptor,"token RDELIM");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_LDELIM=new RewriteRuleTokenStream(adaptor,"token LDELIM");
        RewriteRuleTokenStream stream_INDENT=new RewriteRuleTokenStream(adaptor,"token INDENT");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleTokenStream stream_ELSE=new RewriteRuleTokenStream(adaptor,"token ELSE");
        RewriteRuleTokenStream stream_ELSEIF=new RewriteRuleTokenStream(adaptor,"token ELSEIF");
        RewriteRuleSubtreeStream stream_template=new RewriteRuleSubtreeStream(adaptor,"rule template");
        RewriteRuleSubtreeStream stream_conditional=new RewriteRuleSubtreeStream(adaptor,"rule conditional");
        Token indent=null;
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:115:3: ( (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= template ( ( INDENT )? LDELIM 'elseif' '(' c2+= conditional ')' RDELIM t2+= template )* ( ( INDENT )? LDELIM 'else' RDELIM t3= template )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )? -> {indent!=null}? ^( INDENTED_EXPR $i ^( 'if' $c1 ( $t1)? ( ^( 'elseif' $c2 $t2) )* ( ^( 'else' ( $t3)? ) )? ) ) -> ^( 'if' $c1 ( $t1)? ( ^( 'elseif' $c2 $t2) )* ( ^( 'else' ( $t3)? ) )? ) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:115:5: (i= INDENT )? LDELIM 'if' '(' c1= conditional ')' RDELIM t1= template ( ( INDENT )? LDELIM 'elseif' '(' c2+= conditional ')' RDELIM t2+= template )* ( ( INDENT )? LDELIM 'else' RDELIM t3= template )? ( INDENT )? endif= LDELIM 'endif' RDELIM ({...}? => NEWLINE )?
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:115:6: (i= INDENT )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==INDENT) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:115:6: i= INDENT
                    {
                    i=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_ifstat535);  
                    stream_INDENT.add(i);


                    }
                    break;

            }


            LDELIM36=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_ifstat538);  
            stream_LDELIM.add(LDELIM36);


            string_literal37=(CommonToken)match(input,IF,FOLLOW_IF_in_ifstat540);  
            stream_IF.add(string_literal37);


            char_literal38=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_ifstat542);  
            stream_LPAREN.add(char_literal38);


            pushFollow(FOLLOW_conditional_in_ifstat546);
            c1=conditional();

            state._fsp--;

            stream_conditional.add(c1.getTree());

            char_literal39=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_ifstat548);  
            stream_RPAREN.add(char_literal39);


            RDELIM40=(CommonToken)match(input,RDELIM,FOLLOW_RDELIM_in_ifstat550);  
            stream_RDELIM.add(RDELIM40);


            if (input.LA(1)!=NEWLINE) indent=i;

            pushFollow(FOLLOW_template_in_ifstat562);
            t1=template();

            state._fsp--;

            stream_template.add(t1.getTree());

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:117:7: ( ( INDENT )? LDELIM 'elseif' '(' c2+= conditional ')' RDELIM t2+= template )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==INDENT) ) {
                    int LA15_1 = input.LA(2);

                    if ( (LA15_1==LDELIM) ) {
                        int LA15_2 = input.LA(3);

                        if ( (LA15_2==ELSEIF) ) {
                            alt15=1;
                        }


                    }


                }
                else if ( (LA15_0==LDELIM) ) {
                    int LA15_2 = input.LA(2);

                    if ( (LA15_2==ELSEIF) ) {
                        alt15=1;
                    }


                }


                switch (alt15) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:117:9: ( INDENT )? LDELIM 'elseif' '(' c2+= conditional ')' RDELIM t2+= template
            	    {
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:117:9: ( INDENT )?
            	    int alt14=2;
            	    int LA14_0 = input.LA(1);

            	    if ( (LA14_0==INDENT) ) {
            	        alt14=1;
            	    }
            	    switch (alt14) {
            	        case 1 :
            	            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:117:9: INDENT
            	            {
            	            INDENT41=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_ifstat572);  
            	            stream_INDENT.add(INDENT41);


            	            }
            	            break;

            	    }


            	    LDELIM42=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_ifstat575);  
            	    stream_LDELIM.add(LDELIM42);


            	    string_literal43=(CommonToken)match(input,ELSEIF,FOLLOW_ELSEIF_in_ifstat577);  
            	    stream_ELSEIF.add(string_literal43);


            	    char_literal44=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_ifstat579);  
            	    stream_LPAREN.add(char_literal44);


            	    pushFollow(FOLLOW_conditional_in_ifstat583);
            	    c2=conditional();

            	    state._fsp--;

            	    stream_conditional.add(c2.getTree());
            	    if (list_c2==null) list_c2=new ArrayList();
            	    list_c2.add(c2.getTree());


            	    char_literal45=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_ifstat585);  
            	    stream_RPAREN.add(char_literal45);


            	    RDELIM46=(CommonToken)match(input,RDELIM,FOLLOW_RDELIM_in_ifstat587);  
            	    stream_RDELIM.add(RDELIM46);


            	    pushFollow(FOLLOW_template_in_ifstat591);
            	    t2=template();

            	    state._fsp--;

            	    stream_template.add(t2.getTree());
            	    if (list_t2==null) list_t2=new ArrayList();
            	    list_t2.add(t2.getTree());


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:118:7: ( ( INDENT )? LDELIM 'else' RDELIM t3= template )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==INDENT) ) {
                int LA17_1 = input.LA(2);

                if ( (LA17_1==LDELIM) ) {
                    int LA17_2 = input.LA(3);

                    if ( (LA17_2==ELSE) ) {
                        alt17=1;
                    }
                }
            }
            else if ( (LA17_0==LDELIM) ) {
                int LA17_2 = input.LA(2);

                if ( (LA17_2==ELSE) ) {
                    alt17=1;
                }
            }
            switch (alt17) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:118:9: ( INDENT )? LDELIM 'else' RDELIM t3= template
                    {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:118:9: ( INDENT )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==INDENT) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:118:9: INDENT
                            {
                            INDENT47=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_ifstat604);  
                            stream_INDENT.add(INDENT47);


                            }
                            break;

                    }


                    LDELIM48=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_ifstat607);  
                    stream_LDELIM.add(LDELIM48);


                    string_literal49=(CommonToken)match(input,ELSE,FOLLOW_ELSE_in_ifstat609);  
                    stream_ELSE.add(string_literal49);


                    RDELIM50=(CommonToken)match(input,RDELIM,FOLLOW_RDELIM_in_ifstat611);  
                    stream_RDELIM.add(RDELIM50);


                    pushFollow(FOLLOW_template_in_ifstat615);
                    t3=template();

                    state._fsp--;

                    stream_template.add(t3.getTree());

                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:119:7: ( INDENT )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==INDENT) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:119:7: INDENT
                    {
                    INDENT51=(CommonToken)match(input,INDENT,FOLLOW_INDENT_in_ifstat626);  
                    stream_INDENT.add(INDENT51);


                    }
                    break;

            }


            endif=(CommonToken)match(input,LDELIM,FOLLOW_LDELIM_in_ifstat632);  
            stream_LDELIM.add(endif);


            string_literal52=(CommonToken)match(input,ENDIF,FOLLOW_ENDIF_in_ifstat634);  
            stream_ENDIF.add(string_literal52);


            RDELIM53=(CommonToken)match(input,RDELIM,FOLLOW_RDELIM_in_ifstat640);  
            stream_RDELIM.add(RDELIM53);


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:122:5: ({...}? => NEWLINE )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==NEWLINE) ) {
                int LA19_1 = input.LA(2);

                if ( ((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                    alt19=1;
                }
            }
            switch (alt19) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:122:6: {...}? => NEWLINE
                    {
                    if ( !((((CommonToken)retval.start).getLine()!=input.LT(1).getLine())) ) {
                        throw new FailedPredicateException(input, "ifstat", "$ifstat.start.getLine()!=input.LT(1).getLine()");
                    }

                    NEWLINE54=(CommonToken)match(input,NEWLINE,FOLLOW_NEWLINE_in_ifstat655);  
                    stream_NEWLINE.add(NEWLINE54);


                    }
                    break;

            }


            // AST REWRITE
            // elements: c2, t1, t1, ELSEIF, ELSE, t3, t2, t2, ELSE, t3, IF, i, IF, c2, c1, c1, ELSEIF
            // token labels: i
            // rule labels: t3, retval, t1, c1
            // token list labels: 
            // rule list labels: t2, c2
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_t3=new RewriteRuleSubtreeStream(adaptor,"rule t3",t3!=null?t3.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_t1=new RewriteRuleSubtreeStream(adaptor,"rule t1",t1!=null?t1.tree:null);
            RewriteRuleSubtreeStream stream_c1=new RewriteRuleSubtreeStream(adaptor,"rule c1",c1!=null?c1.tree:null);
            RewriteRuleSubtreeStream stream_t2=new RewriteRuleSubtreeStream(adaptor,"token t2",list_t2);
            RewriteRuleSubtreeStream stream_c2=new RewriteRuleSubtreeStream(adaptor,"token c2",list_c2);
            root_0 = (CommonTree)adaptor.nil();
            // 123:5: -> {indent!=null}? ^( INDENTED_EXPR $i ^( 'if' $c1 ( $t1)? ( ^( 'elseif' $c2 $t2) )* ( ^( 'else' ( $t3)? ) )? ) )
            if (indent!=null) {
                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:124:8: ^( INDENTED_EXPR $i ^( 'if' $c1 ( $t1)? ( ^( 'elseif' $c2 $t2) )* ( ^( 'else' ( $t3)? ) )? ) )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(INDENTED_EXPR, "INDENTED_EXPR")
                , root_1);

                adaptor.addChild(root_1, stream_i.nextNode());

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:124:27: ^( 'if' $c1 ( $t1)? ( ^( 'elseif' $c2 $t2) )* ( ^( 'else' ( $t3)? ) )? )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(
                stream_IF.nextNode()
                , root_2);

                adaptor.addChild(root_2, stream_c1.nextTree());

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:124:39: ( $t1)?
                if ( stream_t1.hasNext() ) {
                    adaptor.addChild(root_2, stream_t1.nextTree());

                }
                stream_t1.reset();

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:124:43: ( ^( 'elseif' $c2 $t2) )*
                while ( stream_c2.hasNext()||stream_ELSEIF.hasNext()||stream_t2.hasNext() ) {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:124:43: ^( 'elseif' $c2 $t2)
                    {
                    CommonTree root_3 = (CommonTree)adaptor.nil();
                    root_3 = (CommonTree)adaptor.becomeRoot(
                    stream_ELSEIF.nextNode()
                    , root_3);

                    adaptor.addChild(root_3, stream_c2.nextTree());

                    adaptor.addChild(root_3, stream_t2.nextTree());

                    adaptor.addChild(root_2, root_3);
                    }

                }
                stream_c2.reset();
                stream_ELSEIF.reset();
                stream_t2.reset();

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:124:64: ( ^( 'else' ( $t3)? ) )?
                if ( stream_ELSE.hasNext()||stream_t3.hasNext() ) {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:124:64: ^( 'else' ( $t3)? )
                    {
                    CommonTree root_3 = (CommonTree)adaptor.nil();
                    root_3 = (CommonTree)adaptor.becomeRoot(
                    stream_ELSE.nextNode()
                    , root_3);

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:124:74: ( $t3)?
                    if ( stream_t3.hasNext() ) {
                        adaptor.addChild(root_3, stream_t3.nextTree());

                    }
                    stream_t3.reset();

                    adaptor.addChild(root_2, root_3);
                    }

                }
                stream_ELSE.reset();
                stream_t3.reset();

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }

            else // 125:5: -> ^( 'if' $c1 ( $t1)? ( ^( 'elseif' $c2 $t2) )* ( ^( 'else' ( $t3)? ) )? )
            {
                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:125:27: ^( 'if' $c1 ( $t1)? ( ^( 'elseif' $c2 $t2) )* ( ^( 'else' ( $t3)? ) )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_IF.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_c1.nextTree());

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:125:39: ( $t1)?
                if ( stream_t1.hasNext() ) {
                    adaptor.addChild(root_1, stream_t1.nextTree());

                }
                stream_t1.reset();

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:125:43: ( ^( 'elseif' $c2 $t2) )*
                while ( stream_c2.hasNext()||stream_t2.hasNext()||stream_ELSEIF.hasNext() ) {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:125:43: ^( 'elseif' $c2 $t2)
                    {
                    CommonTree root_2 = (CommonTree)adaptor.nil();
                    root_2 = (CommonTree)adaptor.becomeRoot(
                    stream_ELSEIF.nextNode()
                    , root_2);

                    adaptor.addChild(root_2, stream_c2.nextTree());

                    adaptor.addChild(root_2, stream_t2.nextTree());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_c2.reset();
                stream_t2.reset();
                stream_ELSEIF.reset();

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:125:64: ( ^( 'else' ( $t3)? ) )?
                if ( stream_ELSE.hasNext()||stream_t3.hasNext() ) {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:125:64: ^( 'else' ( $t3)? )
                    {
                    CommonTree root_2 = (CommonTree)adaptor.nil();
                    root_2 = (CommonTree)adaptor.becomeRoot(
                    stream_ELSE.nextNode()
                    , root_2);

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:125:74: ( $t3)?
                    if ( stream_t3.hasNext() ) {
                        adaptor.addChild(root_2, stream_t3.nextTree());

                    }
                    stream_t3.reset();

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_ELSE.reset();
                stream_t3.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "ifstat"


    protected static class conditional_scope {
        boolean inside;
    }
    protected Stack conditional_stack = new Stack();


    public static class conditional_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "conditional"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:128:1: conditional : andConditional ( '||' ^ andConditional )* ;
    public final STParser.conditional_return conditional() throws RecognitionException {
        conditional_stack.push(new conditional_scope());
        STParser.conditional_return retval = new STParser.conditional_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken string_literal56=null;
        STParser.andConditional_return andConditional55 =null;

        STParser.andConditional_return andConditional57 =null;


        CommonTree string_literal56_tree=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:132:3: ( andConditional ( '||' ^ andConditional )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:132:5: andConditional ( '||' ^ andConditional )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_andConditional_in_conditional783);
            andConditional55=andConditional();

            state._fsp--;

            adaptor.addChild(root_0, andConditional55.getTree());

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:132:20: ( '||' ^ andConditional )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==OR) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:132:22: '||' ^ andConditional
            	    {
            	    string_literal56=(CommonToken)match(input,OR,FOLLOW_OR_in_conditional787); 
            	    string_literal56_tree = 
            	    (CommonTree)adaptor.create(string_literal56)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal56_tree, root_0);


            	    pushFollow(FOLLOW_andConditional_in_conditional790);
            	    andConditional57=andConditional();

            	    state._fsp--;

            	    adaptor.addChild(root_0, andConditional57.getTree());

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
            conditional_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "conditional"


    public static class andConditional_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "andConditional"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:135:1: andConditional : notConditional ( '&&' ^ notConditional )* ;
    public final STParser.andConditional_return andConditional() throws RecognitionException {
        STParser.andConditional_return retval = new STParser.andConditional_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken string_literal59=null;
        STParser.notConditional_return notConditional58 =null;

        STParser.notConditional_return notConditional60 =null;


        CommonTree string_literal59_tree=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:135:16: ( notConditional ( '&&' ^ notConditional )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:135:18: notConditional ( '&&' ^ notConditional )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_notConditional_in_andConditional804);
            notConditional58=notConditional();

            state._fsp--;

            adaptor.addChild(root_0, notConditional58.getTree());

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:135:33: ( '&&' ^ notConditional )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==AND) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:135:35: '&&' ^ notConditional
            	    {
            	    string_literal59=(CommonToken)match(input,AND,FOLLOW_AND_in_andConditional808); 
            	    string_literal59_tree = 
            	    (CommonTree)adaptor.create(string_literal59)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal59_tree, root_0);


            	    pushFollow(FOLLOW_notConditional_in_andConditional811);
            	    notConditional60=notConditional();

            	    state._fsp--;

            	    adaptor.addChild(root_0, notConditional60.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "andConditional"


    public static class notConditional_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "notConditional"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:137:1: notConditional : ( '!' ^ notConditional | memberExpr );
    public final STParser.notConditional_return notConditional() throws RecognitionException {
        STParser.notConditional_return retval = new STParser.notConditional_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken char_literal61=null;
        STParser.notConditional_return notConditional62 =null;

        STParser.memberExpr_return memberExpr63 =null;


        CommonTree char_literal61_tree=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:138:3: ( '!' ^ notConditional | memberExpr )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==BANG) ) {
                alt22=1;
            }
            else if ( (LA22_0==SUPER||LA22_0==LBRACK||LA22_0==LCURLY||(LA22_0 >= ID && LA22_0 <= STRING)||LA22_0==AT||(LA22_0 >= TRUE && LA22_0 <= FALSE)) ) {
                alt22=2;
            }
            else if ( (LA22_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }
            switch (alt22) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:138:5: '!' ^ notConditional
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal61=(CommonToken)match(input,BANG,FOLLOW_BANG_in_notConditional825); 
                    char_literal61_tree = 
                    (CommonTree)adaptor.create(char_literal61)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal61_tree, root_0);


                    pushFollow(FOLLOW_notConditional_in_notConditional828);
                    notConditional62=notConditional();

                    state._fsp--;

                    adaptor.addChild(root_0, notConditional62.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:139:5: memberExpr
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_memberExpr_in_notConditional834);
                    memberExpr63=memberExpr();

                    state._fsp--;

                    adaptor.addChild(root_0, memberExpr63.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "notConditional"


    public static class notConditionalExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "notConditionalExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:142:1: notConditionalExpr : ( ID -> ID ) (p= '.' prop= ID -> ^( PROP[$p,\"PROP\"] $notConditionalExpr $prop) |p= '.' '(' mapExpr ')' -> ^( PROP_IND[$p,\"PROP_IND\"] $notConditionalExpr mapExpr ) )* ;
    public final STParser.notConditionalExpr_return notConditionalExpr() throws RecognitionException {
        STParser.notConditionalExpr_return retval = new STParser.notConditionalExpr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken p=null;
        CommonToken prop=null;
        CommonToken ID64=null;
        CommonToken char_literal65=null;
        CommonToken char_literal67=null;
        STParser.mapExpr_return mapExpr66 =null;


        CommonTree p_tree=null;
        CommonTree prop_tree=null;
        CommonTree ID64_tree=null;
        CommonTree char_literal65_tree=null;
        CommonTree char_literal67_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_mapExpr=new RewriteRuleSubtreeStream(adaptor,"rule mapExpr");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:143:3: ( ( ID -> ID ) (p= '.' prop= ID -> ^( PROP[$p,\"PROP\"] $notConditionalExpr $prop) |p= '.' '(' mapExpr ')' -> ^( PROP_IND[$p,\"PROP_IND\"] $notConditionalExpr mapExpr ) )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:143:5: ( ID -> ID ) (p= '.' prop= ID -> ^( PROP[$p,\"PROP\"] $notConditionalExpr $prop) |p= '.' '(' mapExpr ')' -> ^( PROP_IND[$p,\"PROP_IND\"] $notConditionalExpr mapExpr ) )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:143:5: ( ID -> ID )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:143:6: ID
            {
            ID64=(CommonToken)match(input,ID,FOLLOW_ID_in_notConditionalExpr848);  
            stream_ID.add(ID64);


            // AST REWRITE
            // elements: ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 143:8: -> ID
            {
                adaptor.addChild(root_0, 
                stream_ID.nextNode()
                );

            }


            retval.tree = root_0;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:144:5: (p= '.' prop= ID -> ^( PROP[$p,\"PROP\"] $notConditionalExpr $prop) |p= '.' '(' mapExpr ')' -> ^( PROP_IND[$p,\"PROP_IND\"] $notConditionalExpr mapExpr ) )*
            loop23:
            do {
                int alt23=3;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==DOT) ) {
                    int LA23_2 = input.LA(2);

                    if ( (LA23_2==ID) ) {
                        alt23=1;
                    }
                    else if ( (LA23_2==LPAREN) ) {
                        alt23=2;
                    }


                }


                switch (alt23) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:144:7: p= '.' prop= ID
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_notConditionalExpr861);  
            	    stream_DOT.add(p);


            	    prop=(CommonToken)match(input,ID,FOLLOW_ID_in_notConditionalExpr865);  
            	    stream_ID.add(prop);


            	    // AST REWRITE
            	    // elements: notConditionalExpr, prop
            	    // token labels: prop
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    retval.tree = root_0;
            	    RewriteRuleTokenStream stream_prop=new RewriteRuleTokenStream(adaptor,"token prop",prop);
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (CommonTree)adaptor.nil();
            	    // 144:31: -> ^( PROP[$p,\"PROP\"] $notConditionalExpr $prop)
            	    {
            	        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:144:34: ^( PROP[$p,\"PROP\"] $notConditionalExpr $prop)
            	        {
            	        CommonTree root_1 = (CommonTree)adaptor.nil();
            	        root_1 = (CommonTree)adaptor.becomeRoot(
            	        (CommonTree)adaptor.create(PROP, p, "PROP")
            	        , root_1);

            	        adaptor.addChild(root_1, stream_retval.nextTree());

            	        adaptor.addChild(root_1, stream_prop.nextNode());

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:145:7: p= '.' '(' mapExpr ')'
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_notConditionalExpr898);  
            	    stream_DOT.add(p);


            	    char_literal65=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_notConditionalExpr900);  
            	    stream_LPAREN.add(char_literal65);


            	    pushFollow(FOLLOW_mapExpr_in_notConditionalExpr902);
            	    mapExpr66=mapExpr();

            	    state._fsp--;

            	    stream_mapExpr.add(mapExpr66.getTree());

            	    char_literal67=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_notConditionalExpr904);  
            	    stream_RPAREN.add(char_literal67);


            	    // AST REWRITE
            	    // elements: mapExpr, notConditionalExpr
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (CommonTree)adaptor.nil();
            	    // 145:35: -> ^( PROP_IND[$p,\"PROP_IND\"] $notConditionalExpr mapExpr )
            	    {
            	        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:145:38: ^( PROP_IND[$p,\"PROP_IND\"] $notConditionalExpr mapExpr )
            	        {
            	        CommonTree root_1 = (CommonTree)adaptor.nil();
            	        root_1 = (CommonTree)adaptor.becomeRoot(
            	        (CommonTree)adaptor.create(PROP_IND, p, "PROP_IND")
            	        , root_1);

            	        adaptor.addChild(root_1, stream_retval.nextTree());

            	        adaptor.addChild(root_1, stream_mapExpr.nextTree());

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "notConditionalExpr"


    public static class exprOptions_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "exprOptions"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:149:1: exprOptions : option ( ',' option )* -> ^( OPTIONS ( option )* ) ;
    public final STParser.exprOptions_return exprOptions() throws RecognitionException {
        STParser.exprOptions_return retval = new STParser.exprOptions_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken char_literal69=null;
        STParser.option_return option68 =null;

        STParser.option_return option70 =null;


        CommonTree char_literal69_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_option=new RewriteRuleSubtreeStream(adaptor,"rule option");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:149:13: ( option ( ',' option )* -> ^( OPTIONS ( option )* ) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:149:15: option ( ',' option )*
            {
            pushFollow(FOLLOW_option_in_exprOptions940);
            option68=option();

            state._fsp--;

            stream_option.add(option68.getTree());

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:149:22: ( ',' option )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==COMMA) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:149:24: ',' option
            	    {
            	    char_literal69=(CommonToken)match(input,COMMA,FOLLOW_COMMA_in_exprOptions944);  
            	    stream_COMMA.add(char_literal69);


            	    pushFollow(FOLLOW_option_in_exprOptions946);
            	    option70=option();

            	    state._fsp--;

            	    stream_option.add(option70.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            // AST REWRITE
            // elements: option
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 149:38: -> ^( OPTIONS ( option )* )
            {
                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:149:41: ^( OPTIONS ( option )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(OPTIONS, "OPTIONS")
                , root_1);

                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:149:51: ( option )*
                while ( stream_option.hasNext() ) {
                    adaptor.addChild(root_1, stream_option.nextTree());

                }
                stream_option.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "exprOptions"


    public static class option_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "option"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:151:1: option : ID ( '=' exprNoComma -> {validOption}? ^( '=' ID exprNoComma ) ->| -> {validOption&&defVal!=null}? ^( EQUALS[\"=\"] ID STRING[$ID,'\"'+defVal+'\"'] ) ->) ;
    public final STParser.option_return option() throws RecognitionException {
        STParser.option_return retval = new STParser.option_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken ID71=null;
        CommonToken char_literal72=null;
        STParser.exprNoComma_return exprNoComma73 =null;


        CommonTree ID71_tree=null;
        CommonTree char_literal72_tree=null;
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_exprNoComma=new RewriteRuleSubtreeStream(adaptor,"rule exprNoComma");

          String id = input.LT(1).getText();
          String defVal = Compiler.defaultOptionValues.get(id);
          boolean validOption = Compiler.supportedOptions.get(id)!=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:157:3: ( ID ( '=' exprNoComma -> {validOption}? ^( '=' ID exprNoComma ) ->| -> {validOption&&defVal!=null}? ^( EQUALS[\"=\"] ID STRING[$ID,'\"'+defVal+'\"'] ) ->) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:157:5: ID ( '=' exprNoComma -> {validOption}? ^( '=' ID exprNoComma ) ->| -> {validOption&&defVal!=null}? ^( EQUALS[\"=\"] ID STRING[$ID,'\"'+defVal+'\"'] ) ->)
            {
            ID71=(CommonToken)match(input,ID,FOLLOW_ID_in_option974);  
            stream_ID.add(ID71);



                if ( !validOption ) {
                        errMgr.compileTimeError(ErrorType.NO_SUCH_OPTION, templateToken, ID71, (ID71!=null?ID71.getText():null));
                }
                

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:163:5: ( '=' exprNoComma -> {validOption}? ^( '=' ID exprNoComma ) ->| -> {validOption&&defVal!=null}? ^( EQUALS[\"=\"] ID STRING[$ID,'\"'+defVal+'\"'] ) ->)
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==EQUALS) ) {
                alt25=1;
            }
            else if ( (LA25_0==COMMA||LA25_0==RDELIM) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }
            switch (alt25) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:163:7: '=' exprNoComma
                    {
                    char_literal72=(CommonToken)match(input,EQUALS,FOLLOW_EQUALS_in_option988);  
                    stream_EQUALS.add(char_literal72);


                    pushFollow(FOLLOW_exprNoComma_in_option990);
                    exprNoComma73=exprNoComma();

                    state._fsp--;

                    stream_exprNoComma.add(exprNoComma73.getTree());

                    // AST REWRITE
                    // elements: exprNoComma, EQUALS, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 163:33: -> {validOption}? ^( '=' ID exprNoComma )
                    if (validOption) {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:163:51: ^( '=' ID exprNoComma )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_EQUALS.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_1, stream_exprNoComma.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    else // 164:25: ->
                    {
                        root_0 = null;
                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:165:7: 
                    {

                          if ( defVal==null ) {
                            errMgr.compileTimeError(ErrorType.NO_DEFAULT_VALUE, templateToken, ID71);
                          }
                          

                    // AST REWRITE
                    // elements: ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 170:25: -> {validOption&&defVal!=null}? ^( EQUALS[\"=\"] ID STRING[$ID,'\"'+defVal+'\"'] )
                    if (validOption&&defVal!=null) {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:171:28: ^( EQUALS[\"=\"] ID STRING[$ID,'\"'+defVal+'\"'] )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(EQUALS, "=")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        (CommonTree)adaptor.create(STRING, ID71, '"'+defVal+'"')
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    else // 172:25: ->
                    {
                        root_0 = null;
                    }


                    retval.tree = root_0;

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "option"


    public static class exprNoComma_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "exprNoComma"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:176:1: exprNoComma : memberExpr ( ':' mapTemplateRef -> ^( MAP memberExpr mapTemplateRef ) | -> memberExpr ) ;
    public final STParser.exprNoComma_return exprNoComma() throws RecognitionException {
        STParser.exprNoComma_return retval = new STParser.exprNoComma_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken char_literal75=null;
        STParser.memberExpr_return memberExpr74 =null;

        STParser.mapTemplateRef_return mapTemplateRef76 =null;


        CommonTree char_literal75_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_memberExpr=new RewriteRuleSubtreeStream(adaptor,"rule memberExpr");
        RewriteRuleSubtreeStream stream_mapTemplateRef=new RewriteRuleSubtreeStream(adaptor,"rule mapTemplateRef");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:177:3: ( memberExpr ( ':' mapTemplateRef -> ^( MAP memberExpr mapTemplateRef ) | -> memberExpr ) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:177:5: memberExpr ( ':' mapTemplateRef -> ^( MAP memberExpr mapTemplateRef ) | -> memberExpr )
            {
            pushFollow(FOLLOW_memberExpr_in_exprNoComma1156);
            memberExpr74=memberExpr();

            state._fsp--;

            stream_memberExpr.add(memberExpr74.getTree());

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:178:5: ( ':' mapTemplateRef -> ^( MAP memberExpr mapTemplateRef ) | -> memberExpr )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==COLON) ) {
                alt26=1;
            }
            else if ( (LA26_0==RPAREN||(LA26_0 >= RBRACK && LA26_0 <= COMMA)||LA26_0==RDELIM) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }
            switch (alt26) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:178:7: ':' mapTemplateRef
                    {
                    char_literal75=(CommonToken)match(input,COLON,FOLLOW_COLON_in_exprNoComma1164);  
                    stream_COLON.add(char_literal75);


                    pushFollow(FOLLOW_mapTemplateRef_in_exprNoComma1166);
                    mapTemplateRef76=mapTemplateRef();

                    state._fsp--;

                    stream_mapTemplateRef.add(mapTemplateRef76.getTree());

                    // AST REWRITE
                    // elements: memberExpr, mapTemplateRef
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 178:35: -> ^( MAP memberExpr mapTemplateRef )
                    {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:178:38: ^( MAP memberExpr mapTemplateRef )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(MAP, "MAP")
                        , root_1);

                        adaptor.addChild(root_1, stream_memberExpr.nextTree());

                        adaptor.addChild(root_1, stream_mapTemplateRef.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:179:25: 
                    {
                    // AST REWRITE
                    // elements: memberExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 179:25: -> memberExpr
                    {
                        adaptor.addChild(root_0, stream_memberExpr.nextTree());

                    }


                    retval.tree = root_0;

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "exprNoComma"


    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:183:1: expr : mapExpr ;
    public final STParser.expr_return expr() throws RecognitionException {
        STParser.expr_return retval = new STParser.expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        STParser.mapExpr_return mapExpr77 =null;



        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:183:6: ( mapExpr )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:183:8: mapExpr
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_mapExpr_in_expr1230);
            mapExpr77=mapExpr();

            state._fsp--;

            adaptor.addChild(root_0, mapExpr77.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr"


    public static class mapExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mapExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:187:1: mapExpr : memberExpr ( (c= ',' memberExpr )+ col= ':' mapTemplateRef -> ^( ZIP[$col] ^( ELEMENTS ( memberExpr )+ ) mapTemplateRef ) | -> memberExpr ) (col= ':' x+= mapTemplateRef ({...}? => ',' x+= mapTemplateRef )* -> ^( MAP[$col] $mapExpr ( $x)+ ) )* ;
    public final STParser.mapExpr_return mapExpr() throws RecognitionException {
        STParser.mapExpr_return retval = new STParser.mapExpr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken c=null;
        CommonToken col=null;
        CommonToken char_literal81=null;
        List list_x=null;
        STParser.memberExpr_return memberExpr78 =null;

        STParser.memberExpr_return memberExpr79 =null;

        STParser.mapTemplateRef_return mapTemplateRef80 =null;

        RuleReturnScope x = null;
        CommonTree c_tree=null;
        CommonTree col_tree=null;
        CommonTree char_literal81_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_memberExpr=new RewriteRuleSubtreeStream(adaptor,"rule memberExpr");
        RewriteRuleSubtreeStream stream_mapTemplateRef=new RewriteRuleSubtreeStream(adaptor,"rule mapTemplateRef");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:188:3: ( memberExpr ( (c= ',' memberExpr )+ col= ':' mapTemplateRef -> ^( ZIP[$col] ^( ELEMENTS ( memberExpr )+ ) mapTemplateRef ) | -> memberExpr ) (col= ':' x+= mapTemplateRef ({...}? => ',' x+= mapTemplateRef )* -> ^( MAP[$col] $mapExpr ( $x)+ ) )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:188:5: memberExpr ( (c= ',' memberExpr )+ col= ':' mapTemplateRef -> ^( ZIP[$col] ^( ELEMENTS ( memberExpr )+ ) mapTemplateRef ) | -> memberExpr ) (col= ':' x+= mapTemplateRef ({...}? => ',' x+= mapTemplateRef )* -> ^( MAP[$col] $mapExpr ( $x)+ ) )*
            {
            pushFollow(FOLLOW_memberExpr_in_mapExpr1243);
            memberExpr78=memberExpr();

            state._fsp--;

            stream_memberExpr.add(memberExpr78.getTree());

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:189:5: ( (c= ',' memberExpr )+ col= ':' mapTemplateRef -> ^( ZIP[$col] ^( ELEMENTS ( memberExpr )+ ) mapTemplateRef ) | -> memberExpr )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==COMMA) ) {
                alt28=1;
            }
            else if ( (LA28_0==SEMI||LA28_0==COLON||LA28_0==RPAREN||LA28_0==RDELIM) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;

            }
            switch (alt28) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:189:7: (c= ',' memberExpr )+ col= ':' mapTemplateRef
                    {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:189:7: (c= ',' memberExpr )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==COMMA) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:189:8: c= ',' memberExpr
                    	    {
                    	    c=(CommonToken)match(input,COMMA,FOLLOW_COMMA_in_mapExpr1254);  
                    	    stream_COMMA.add(c);


                    	    pushFollow(FOLLOW_memberExpr_in_mapExpr1256);
                    	    memberExpr79=memberExpr();

                    	    state._fsp--;

                    	    stream_memberExpr.add(memberExpr79.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt27 >= 1 ) break loop27;
                                EarlyExitException eee =
                                    new EarlyExitException(27, input);
                                throw eee;
                        }
                        cnt27++;
                    } while (true);


                    col=(CommonToken)match(input,COLON,FOLLOW_COLON_in_mapExpr1262);  
                    stream_COLON.add(col);


                    pushFollow(FOLLOW_mapTemplateRef_in_mapExpr1264);
                    mapTemplateRef80=mapTemplateRef();

                    state._fsp--;

                    stream_mapTemplateRef.add(mapTemplateRef80.getTree());

                    // AST REWRITE
                    // elements: memberExpr, mapTemplateRef
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 190:25: -> ^( ZIP[$col] ^( ELEMENTS ( memberExpr )+ ) mapTemplateRef )
                    {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:190:28: ^( ZIP[$col] ^( ELEMENTS ( memberExpr )+ ) mapTemplateRef )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(ZIP, col)
                        , root_1);

                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:190:40: ^( ELEMENTS ( memberExpr )+ )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(ELEMENTS, "ELEMENTS")
                        , root_2);

                        if ( !(stream_memberExpr.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_memberExpr.hasNext() ) {
                            adaptor.addChild(root_2, stream_memberExpr.nextTree());

                        }
                        stream_memberExpr.reset();

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_1, stream_mapTemplateRef.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:191:25: 
                    {
                    // AST REWRITE
                    // elements: memberExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 191:25: -> memberExpr
                    {
                        adaptor.addChild(root_0, stream_memberExpr.nextTree());

                    }


                    retval.tree = root_0;

                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:193:5: (col= ':' x+= mapTemplateRef ({...}? => ',' x+= mapTemplateRef )* -> ^( MAP[$col] $mapExpr ( $x)+ ) )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==COLON) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:193:7: col= ':' x+= mapTemplateRef ({...}? => ',' x+= mapTemplateRef )*
            	    {
            	    if (list_x!=null) list_x.clear();

            	    col=(CommonToken)match(input,COLON,FOLLOW_COLON_in_mapExpr1357);  
            	    stream_COLON.add(col);


            	    pushFollow(FOLLOW_mapTemplateRef_in_mapExpr1361);
            	    x=mapTemplateRef();

            	    state._fsp--;

            	    stream_mapTemplateRef.add(x.getTree());
            	    if (list_x==null) list_x=new ArrayList();
            	    list_x.add(x.getTree());


            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:194:33: ({...}? => ',' x+= mapTemplateRef )*
            	    loop29:
            	    do {
            	        int alt29=2;
            	        int LA29_0 = input.LA(1);

            	        if ( (LA29_0==COMMA) && ((c==null))) {
            	            alt29=1;
            	        }


            	        switch (alt29) {
            	    	case 1 :
            	    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:194:34: {...}? => ',' x+= mapTemplateRef
            	    	    {
            	    	    if ( !((c==null)) ) {
            	    	        throw new FailedPredicateException(input, "mapExpr", "$c==null");
            	    	    }

            	    	    char_literal81=(CommonToken)match(input,COMMA,FOLLOW_COMMA_in_mapExpr1367);  
            	    	    stream_COMMA.add(char_literal81);


            	    	    pushFollow(FOLLOW_mapTemplateRef_in_mapExpr1371);
            	    	    x=mapTemplateRef();

            	    	    state._fsp--;

            	    	    stream_mapTemplateRef.add(x.getTree());
            	    	    if (list_x==null) list_x=new ArrayList();
            	    	    list_x.add(x.getTree());


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop29;
            	        }
            	    } while (true);


            	    // AST REWRITE
            	    // elements: mapExpr, x
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: x
            	    // wildcard labels: 
            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_x=new RewriteRuleSubtreeStream(adaptor,"token x",list_x);
            	    root_0 = (CommonTree)adaptor.nil();
            	    // 195:25: -> ^( MAP[$col] $mapExpr ( $x)+ )
            	    {
            	        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:195:28: ^( MAP[$col] $mapExpr ( $x)+ )
            	        {
            	        CommonTree root_1 = (CommonTree)adaptor.nil();
            	        root_1 = (CommonTree)adaptor.becomeRoot(
            	        (CommonTree)adaptor.create(MAP, col)
            	        , root_1);

            	        adaptor.addChild(root_1, stream_retval.nextTree());

            	        if ( !(stream_x.hasNext()) ) {
            	            throw new RewriteEarlyExitException();
            	        }
            	        while ( stream_x.hasNext() ) {
            	            adaptor.addChild(root_1, stream_x.nextTree());

            	        }
            	        stream_x.reset();

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mapExpr"


    public static class mapTemplateRef_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mapTemplateRef"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:204:1: mapTemplateRef : ( ID '(' args ')' -> ^( INCLUDE ID ( args )? ) | subtemplate |lp= '(' mapExpr rp= ')' '(' ( argExprList )? ')' -> ^( INCLUDE_IND mapExpr ( argExprList )? ) );
    public final STParser.mapTemplateRef_return mapTemplateRef() throws RecognitionException {
        STParser.mapTemplateRef_return retval = new STParser.mapTemplateRef_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken lp=null;
        CommonToken rp=null;
        CommonToken ID82=null;
        CommonToken char_literal83=null;
        CommonToken char_literal85=null;
        CommonToken char_literal88=null;
        CommonToken char_literal90=null;
        STParser.args_return args84 =null;

        STParser.subtemplate_return subtemplate86 =null;

        STParser.mapExpr_return mapExpr87 =null;

        STParser.argExprList_return argExprList89 =null;


        CommonTree lp_tree=null;
        CommonTree rp_tree=null;
        CommonTree ID82_tree=null;
        CommonTree char_literal83_tree=null;
        CommonTree char_literal85_tree=null;
        CommonTree char_literal88_tree=null;
        CommonTree char_literal90_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_argExprList=new RewriteRuleSubtreeStream(adaptor,"rule argExprList");
        RewriteRuleSubtreeStream stream_args=new RewriteRuleSubtreeStream(adaptor,"rule args");
        RewriteRuleSubtreeStream stream_mapExpr=new RewriteRuleSubtreeStream(adaptor,"rule mapExpr");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:205:3: ( ID '(' args ')' -> ^( INCLUDE ID ( args )? ) | subtemplate |lp= '(' mapExpr rp= ')' '(' ( argExprList )? ')' -> ^( INCLUDE_IND mapExpr ( argExprList )? ) )
            int alt32=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt32=1;
                }
                break;
            case LCURLY:
                {
                alt32=2;
                }
                break;
            case LPAREN:
                {
                alt32=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }

            switch (alt32) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:205:5: ID '(' args ')'
                    {
                    ID82=(CommonToken)match(input,ID,FOLLOW_ID_in_mapTemplateRef1434);  
                    stream_ID.add(ID82);


                    char_literal83=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef1436);  
                    stream_LPAREN.add(char_literal83);


                    pushFollow(FOLLOW_args_in_mapTemplateRef1438);
                    args84=args();

                    state._fsp--;

                    stream_args.add(args84.getTree());

                    char_literal85=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef1440);  
                    stream_RPAREN.add(char_literal85);


                    // AST REWRITE
                    // elements: args, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 205:33: -> ^( INCLUDE ID ( args )? )
                    {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:205:36: ^( INCLUDE ID ( args )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(INCLUDE, "INCLUDE")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:205:49: ( args )?
                        if ( stream_args.hasNext() ) {
                            adaptor.addChild(root_1, stream_args.nextTree());

                        }
                        stream_args.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:206:5: subtemplate
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_subtemplate_in_mapTemplateRef1469);
                    subtemplate86=subtemplate();

                    state._fsp--;

                    adaptor.addChild(root_0, subtemplate86.getTree());

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:207:5: lp= '(' mapExpr rp= ')' '(' ( argExprList )? ')'
                    {
                    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef1477);  
                    stream_LPAREN.add(lp);


                    pushFollow(FOLLOW_mapExpr_in_mapTemplateRef1479);
                    mapExpr87=mapExpr();

                    state._fsp--;

                    stream_mapExpr.add(mapExpr87.getTree());

                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef1483);  
                    stream_RPAREN.add(rp);


                    char_literal88=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_mapTemplateRef1485);  
                    stream_LPAREN.add(char_literal88);


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:207:31: ( argExprList )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==SUPER||LA31_0==LBRACK||LA31_0==LCURLY||(LA31_0 >= ID && LA31_0 <= STRING)||LA31_0==AT||(LA31_0 >= TRUE && LA31_0 <= FALSE)) ) {
                        alt31=1;
                    }
                    else if ( (LA31_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:207:31: argExprList
                            {
                            pushFollow(FOLLOW_argExprList_in_mapTemplateRef1487);
                            argExprList89=argExprList();

                            state._fsp--;

                            stream_argExprList.add(argExprList89.getTree());

                            }
                            break;

                    }


                    char_literal90=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_mapTemplateRef1490);  
                    stream_RPAREN.add(char_literal90);


                    // AST REWRITE
                    // elements: mapExpr, argExprList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 207:48: -> ^( INCLUDE_IND mapExpr ( argExprList )? )
                    {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:207:51: ^( INCLUDE_IND mapExpr ( argExprList )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(INCLUDE_IND, "INCLUDE_IND")
                        , root_1);

                        adaptor.addChild(root_1, stream_mapExpr.nextTree());

                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:207:73: ( argExprList )?
                        if ( stream_argExprList.hasNext() ) {
                            adaptor.addChild(root_1, stream_argExprList.nextTree());

                        }
                        stream_argExprList.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mapTemplateRef"


    public static class memberExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "memberExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:210:1: memberExpr : ( includeExpr -> includeExpr ) (p= '.' ID -> ^( PROP[$p,\"PROP\"] $memberExpr ID ) |p= '.' '(' mapExpr ')' -> ^( PROP_IND[$p,\"PROP_IND\"] $memberExpr mapExpr ) )* ;
    public final STParser.memberExpr_return memberExpr() throws RecognitionException {
        STParser.memberExpr_return retval = new STParser.memberExpr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken p=null;
        CommonToken ID92=null;
        CommonToken char_literal93=null;
        CommonToken char_literal95=null;
        STParser.includeExpr_return includeExpr91 =null;

        STParser.mapExpr_return mapExpr94 =null;


        CommonTree p_tree=null;
        CommonTree ID92_tree=null;
        CommonTree char_literal93_tree=null;
        CommonTree char_literal95_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_includeExpr=new RewriteRuleSubtreeStream(adaptor,"rule includeExpr");
        RewriteRuleSubtreeStream stream_mapExpr=new RewriteRuleSubtreeStream(adaptor,"rule mapExpr");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:211:3: ( ( includeExpr -> includeExpr ) (p= '.' ID -> ^( PROP[$p,\"PROP\"] $memberExpr ID ) |p= '.' '(' mapExpr ')' -> ^( PROP_IND[$p,\"PROP_IND\"] $memberExpr mapExpr ) )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:211:5: ( includeExpr -> includeExpr ) (p= '.' ID -> ^( PROP[$p,\"PROP\"] $memberExpr ID ) |p= '.' '(' mapExpr ')' -> ^( PROP_IND[$p,\"PROP_IND\"] $memberExpr mapExpr ) )*
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:211:5: ( includeExpr -> includeExpr )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:211:6: includeExpr
            {
            pushFollow(FOLLOW_includeExpr_in_memberExpr1515);
            includeExpr91=includeExpr();

            state._fsp--;

            stream_includeExpr.add(includeExpr91.getTree());

            // AST REWRITE
            // elements: includeExpr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 211:17: -> includeExpr
            {
                adaptor.addChild(root_0, stream_includeExpr.nextTree());

            }


            retval.tree = root_0;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:212:5: (p= '.' ID -> ^( PROP[$p,\"PROP\"] $memberExpr ID ) |p= '.' '(' mapExpr ')' -> ^( PROP_IND[$p,\"PROP_IND\"] $memberExpr mapExpr ) )*
            loop33:
            do {
                int alt33=3;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==DOT) ) {
                    int LA33_2 = input.LA(2);

                    if ( (LA33_2==ID) ) {
                        alt33=1;
                    }
                    else if ( (LA33_2==LPAREN) ) {
                        alt33=2;
                    }


                }


                switch (alt33) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:212:7: p= '.' ID
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_memberExpr1528);  
            	    stream_DOT.add(p);


            	    ID92=(CommonToken)match(input,ID,FOLLOW_ID_in_memberExpr1530);  
            	    stream_ID.add(ID92);


            	    // AST REWRITE
            	    // elements: ID, memberExpr
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (CommonTree)adaptor.nil();
            	    // 212:29: -> ^( PROP[$p,\"PROP\"] $memberExpr ID )
            	    {
            	        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:212:32: ^( PROP[$p,\"PROP\"] $memberExpr ID )
            	        {
            	        CommonTree root_1 = (CommonTree)adaptor.nil();
            	        root_1 = (CommonTree)adaptor.becomeRoot(
            	        (CommonTree)adaptor.create(PROP, p, "PROP")
            	        , root_1);

            	        adaptor.addChild(root_1, stream_retval.nextTree());

            	        adaptor.addChild(root_1, 
            	        stream_ID.nextNode()
            	        );

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;

            	    }
            	    break;
            	case 2 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:213:7: p= '.' '(' mapExpr ')'
            	    {
            	    p=(CommonToken)match(input,DOT,FOLLOW_DOT_in_memberExpr1565);  
            	    stream_DOT.add(p);


            	    char_literal93=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_memberExpr1567);  
            	    stream_LPAREN.add(char_literal93);


            	    pushFollow(FOLLOW_mapExpr_in_memberExpr1569);
            	    mapExpr94=mapExpr();

            	    state._fsp--;

            	    stream_mapExpr.add(mapExpr94.getTree());

            	    char_literal95=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_memberExpr1571);  
            	    stream_RPAREN.add(char_literal95);


            	    // AST REWRITE
            	    // elements: memberExpr, mapExpr
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (CommonTree)adaptor.nil();
            	    // 213:35: -> ^( PROP_IND[$p,\"PROP_IND\"] $memberExpr mapExpr )
            	    {
            	        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:213:38: ^( PROP_IND[$p,\"PROP_IND\"] $memberExpr mapExpr )
            	        {
            	        CommonTree root_1 = (CommonTree)adaptor.nil();
            	        root_1 = (CommonTree)adaptor.becomeRoot(
            	        (CommonTree)adaptor.create(PROP_IND, p, "PROP_IND")
            	        , root_1);

            	        adaptor.addChild(root_1, stream_retval.nextTree());

            	        adaptor.addChild(root_1, stream_mapExpr.nextTree());

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "memberExpr"


    public static class includeExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "includeExpr"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:217:1: includeExpr options {k=2; } : ({...}? ID '(' ( expr )? ')' -> ^( EXEC_FUNC ID ( expr )? ) | 'super' '.' ID '(' args ')' -> ^( INCLUDE_SUPER ID ( args )? ) | ID '(' args ')' -> ^( INCLUDE ID ( args )? ) | '@' 'super' '.' ID '(' rp= ')' -> ^( INCLUDE_SUPER_REGION ID ) | '@' ID '(' rp= ')' -> ^( INCLUDE_REGION ID ) | primary );
    public final STParser.includeExpr_return includeExpr() throws RecognitionException {
        STParser.includeExpr_return retval = new STParser.includeExpr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken rp=null;
        CommonToken ID96=null;
        CommonToken char_literal97=null;
        CommonToken char_literal99=null;
        CommonToken string_literal100=null;
        CommonToken char_literal101=null;
        CommonToken ID102=null;
        CommonToken char_literal103=null;
        CommonToken char_literal105=null;
        CommonToken ID106=null;
        CommonToken char_literal107=null;
        CommonToken char_literal109=null;
        CommonToken char_literal110=null;
        CommonToken string_literal111=null;
        CommonToken char_literal112=null;
        CommonToken ID113=null;
        CommonToken char_literal114=null;
        CommonToken char_literal115=null;
        CommonToken ID116=null;
        CommonToken char_literal117=null;
        STParser.expr_return expr98 =null;

        STParser.args_return args104 =null;

        STParser.args_return args108 =null;

        STParser.primary_return primary118 =null;


        CommonTree rp_tree=null;
        CommonTree ID96_tree=null;
        CommonTree char_literal97_tree=null;
        CommonTree char_literal99_tree=null;
        CommonTree string_literal100_tree=null;
        CommonTree char_literal101_tree=null;
        CommonTree ID102_tree=null;
        CommonTree char_literal103_tree=null;
        CommonTree char_literal105_tree=null;
        CommonTree ID106_tree=null;
        CommonTree char_literal107_tree=null;
        CommonTree char_literal109_tree=null;
        CommonTree char_literal110_tree=null;
        CommonTree string_literal111_tree=null;
        CommonTree char_literal112_tree=null;
        CommonTree ID113_tree=null;
        CommonTree char_literal114_tree=null;
        CommonTree char_literal115_tree=null;
        CommonTree ID116_tree=null;
        CommonTree char_literal117_tree=null;
        RewriteRuleTokenStream stream_AT=new RewriteRuleTokenStream(adaptor,"token AT");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_SUPER=new RewriteRuleTokenStream(adaptor,"token SUPER");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_args=new RewriteRuleSubtreeStream(adaptor,"rule args");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:219:3: ({...}? ID '(' ( expr )? ')' -> ^( EXEC_FUNC ID ( expr )? ) | 'super' '.' ID '(' args ')' -> ^( INCLUDE_SUPER ID ( args )? ) | ID '(' args ')' -> ^( INCLUDE ID ( args )? ) | '@' 'super' '.' ID '(' rp= ')' -> ^( INCLUDE_SUPER_REGION ID ) | '@' ID '(' rp= ')' -> ^( INCLUDE_REGION ID ) | primary )
            int alt35=6;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==ID) ) {
                int LA35_1 = input.LA(2);

                if ( (LA35_1==LPAREN) ) {
                    int LA35_10 = input.LA(3);

                    if ( ((Compiler.funcs.containsKey(input.LT(1).getText()))) ) {
                        alt35=1;
                    }
                    else if ( (true) ) {
                        alt35=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 35, 10, input);

                        throw nvae;

                    }
                }
                else if ( (LA35_1==SEMI||LA35_1==COLON||LA35_1==RPAREN||(LA35_1 >= RBRACK && LA35_1 <= DOT)||LA35_1==RDELIM||(LA35_1 >= OR && LA35_1 <= AND)) ) {
                    alt35=6;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA35_0==SUPER) ) {
                alt35=2;
            }
            else if ( (LA35_0==AT) ) {
                int LA35_3 = input.LA(2);

                if ( (LA35_3==SUPER) ) {
                    alt35=4;
                }
                else if ( (LA35_3==ID) ) {
                    alt35=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA35_0==LBRACK||LA35_0==LCURLY||LA35_0==STRING||(LA35_0 >= TRUE && LA35_0 <= FALSE)) ) {
                alt35=6;
            }
            else if ( (LA35_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt35=6;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }
            switch (alt35) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:219:5: {...}? ID '(' ( expr )? ')'
                    {
                    if ( !((Compiler.funcs.containsKey(input.LT(1).getText()))) ) {
                        throw new FailedPredicateException(input, "includeExpr", "Compiler.funcs.containsKey(input.LT(1).getText())");
                    }

                    ID96=(CommonToken)match(input,ID,FOLLOW_ID_in_includeExpr1624);  
                    stream_ID.add(ID96);


                    char_literal97=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1626);  
                    stream_LPAREN.add(char_literal97);


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:220:12: ( expr )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==SUPER||LA34_0==LBRACK||LA34_0==LCURLY||(LA34_0 >= ID && LA34_0 <= STRING)||LA34_0==AT||(LA34_0 >= TRUE && LA34_0 <= FALSE)) ) {
                        alt34=1;
                    }
                    else if ( (LA34_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:220:12: expr
                            {
                            pushFollow(FOLLOW_expr_in_includeExpr1628);
                            expr98=expr();

                            state._fsp--;

                            stream_expr.add(expr98.getTree());

                            }
                            break;

                    }


                    char_literal99=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1631);  
                    stream_RPAREN.add(char_literal99);


                    // AST REWRITE
                    // elements: expr, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 220:33: -> ^( EXEC_FUNC ID ( expr )? )
                    {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:220:36: ^( EXEC_FUNC ID ( expr )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(EXEC_FUNC, "EXEC_FUNC")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:220:51: ( expr )?
                        if ( stream_expr.hasNext() ) {
                            adaptor.addChild(root_1, stream_expr.nextTree());

                        }
                        stream_expr.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:221:5: 'super' '.' ID '(' args ')'
                    {
                    string_literal100=(CommonToken)match(input,SUPER,FOLLOW_SUPER_in_includeExpr1659);  
                    stream_SUPER.add(string_literal100);


                    char_literal101=(CommonToken)match(input,DOT,FOLLOW_DOT_in_includeExpr1661);  
                    stream_DOT.add(char_literal101);


                    ID102=(CommonToken)match(input,ID,FOLLOW_ID_in_includeExpr1663);  
                    stream_ID.add(ID102);


                    char_literal103=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1665);  
                    stream_LPAREN.add(char_literal103);


                    pushFollow(FOLLOW_args_in_includeExpr1667);
                    args104=args();

                    state._fsp--;

                    stream_args.add(args104.getTree());

                    char_literal105=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1669);  
                    stream_RPAREN.add(char_literal105);


                    // AST REWRITE
                    // elements: args, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 221:39: -> ^( INCLUDE_SUPER ID ( args )? )
                    {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:221:42: ^( INCLUDE_SUPER ID ( args )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(INCLUDE_SUPER, "INCLUDE_SUPER")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:221:61: ( args )?
                        if ( stream_args.hasNext() ) {
                            adaptor.addChild(root_1, stream_args.nextTree());

                        }
                        stream_args.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:222:5: ID '(' args ')'
                    {
                    ID106=(CommonToken)match(input,ID,FOLLOW_ID_in_includeExpr1692);  
                    stream_ID.add(ID106);


                    char_literal107=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1694);  
                    stream_LPAREN.add(char_literal107);


                    pushFollow(FOLLOW_args_in_includeExpr1696);
                    args108=args();

                    state._fsp--;

                    stream_args.add(args108.getTree());

                    char_literal109=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1698);  
                    stream_RPAREN.add(char_literal109);


                    // AST REWRITE
                    // elements: ID, args
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 222:33: -> ^( INCLUDE ID ( args )? )
                    {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:222:36: ^( INCLUDE ID ( args )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(INCLUDE, "INCLUDE")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:222:49: ( args )?
                        if ( stream_args.hasNext() ) {
                            adaptor.addChild(root_1, stream_args.nextTree());

                        }
                        stream_args.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:223:5: '@' 'super' '.' ID '(' rp= ')'
                    {
                    char_literal110=(CommonToken)match(input,AT,FOLLOW_AT_in_includeExpr1727);  
                    stream_AT.add(char_literal110);


                    string_literal111=(CommonToken)match(input,SUPER,FOLLOW_SUPER_in_includeExpr1729);  
                    stream_SUPER.add(string_literal111);


                    char_literal112=(CommonToken)match(input,DOT,FOLLOW_DOT_in_includeExpr1731);  
                    stream_DOT.add(char_literal112);


                    ID113=(CommonToken)match(input,ID,FOLLOW_ID_in_includeExpr1733);  
                    stream_ID.add(ID113);


                    char_literal114=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1735);  
                    stream_LPAREN.add(char_literal114);


                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1739);  
                    stream_RPAREN.add(rp);


                    // AST REWRITE
                    // elements: ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 223:39: -> ^( INCLUDE_SUPER_REGION ID )
                    {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:223:42: ^( INCLUDE_SUPER_REGION ID )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(INCLUDE_SUPER_REGION, "INCLUDE_SUPER_REGION")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:224:5: '@' ID '(' rp= ')'
                    {
                    char_literal115=(CommonToken)match(input,AT,FOLLOW_AT_in_includeExpr1757);  
                    stream_AT.add(char_literal115);


                    ID116=(CommonToken)match(input,ID,FOLLOW_ID_in_includeExpr1759);  
                    stream_ID.add(ID116);


                    char_literal117=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_includeExpr1761);  
                    stream_LPAREN.add(char_literal117);


                    rp=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_includeExpr1765);  
                    stream_RPAREN.add(rp);


                    // AST REWRITE
                    // elements: ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 224:33: -> ^( INCLUDE_REGION ID )
                    {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:224:36: ^( INCLUDE_REGION ID )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(INCLUDE_REGION, "INCLUDE_REGION")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:225:5: primary
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_primary_in_includeExpr1789);
                    primary118=primary();

                    state._fsp--;

                    adaptor.addChild(root_0, primary118.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "includeExpr"


    public static class primary_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "primary"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:228:1: primary : ( ID | STRING | TRUE | FALSE | subtemplate | list |{...}? => '(' ! conditional ')' !|{...}? =>lp= '(' expr ')' ( '(' ( argExprList )? ')' -> ^( INCLUDE_IND[$lp] expr ( argExprList )? ) | -> ^( TO_STR[$lp] expr ) ) );
    public final STParser.primary_return primary() throws RecognitionException {
        STParser.primary_return retval = new STParser.primary_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken lp=null;
        CommonToken ID119=null;
        CommonToken STRING120=null;
        CommonToken TRUE121=null;
        CommonToken FALSE122=null;
        CommonToken char_literal125=null;
        CommonToken char_literal127=null;
        CommonToken char_literal129=null;
        CommonToken char_literal130=null;
        CommonToken char_literal132=null;
        STParser.subtemplate_return subtemplate123 =null;

        STParser.list_return list124 =null;

        STParser.conditional_return conditional126 =null;

        STParser.expr_return expr128 =null;

        STParser.argExprList_return argExprList131 =null;


        CommonTree lp_tree=null;
        CommonTree ID119_tree=null;
        CommonTree STRING120_tree=null;
        CommonTree TRUE121_tree=null;
        CommonTree FALSE122_tree=null;
        CommonTree char_literal125_tree=null;
        CommonTree char_literal127_tree=null;
        CommonTree char_literal129_tree=null;
        CommonTree char_literal130_tree=null;
        CommonTree char_literal132_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_argExprList=new RewriteRuleSubtreeStream(adaptor,"rule argExprList");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:229:3: ( ID | STRING | TRUE | FALSE | subtemplate | list |{...}? => '(' ! conditional ')' !|{...}? =>lp= '(' expr ')' ( '(' ( argExprList )? ')' -> ^( INCLUDE_IND[$lp] expr ( argExprList )? ) | -> ^( TO_STR[$lp] expr ) ) )
            int alt38=8;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==ID) ) {
                alt38=1;
            }
            else if ( (LA38_0==STRING) ) {
                alt38=2;
            }
            else if ( (LA38_0==TRUE) ) {
                alt38=3;
            }
            else if ( (LA38_0==FALSE) ) {
                alt38=4;
            }
            else if ( (LA38_0==LCURLY) ) {
                alt38=5;
            }
            else if ( (LA38_0==LBRACK) ) {
                alt38=6;
            }
            else if ( (LA38_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                int LA38_7 = input.LA(2);

                if ( ((conditional_stack.size()>0)) ) {
                    alt38=7;
                }
                else if ( ((conditional_stack.size()==0)) ) {
                    alt38=8;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 7, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:229:5: ID
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ID119=(CommonToken)match(input,ID,FOLLOW_ID_in_primary1802); 
                    ID119_tree = 
                    (CommonTree)adaptor.create(ID119)
                    ;
                    adaptor.addChild(root_0, ID119_tree);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:230:5: STRING
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    STRING120=(CommonToken)match(input,STRING,FOLLOW_STRING_in_primary1808); 
                    STRING120_tree = 
                    (CommonTree)adaptor.create(STRING120)
                    ;
                    adaptor.addChild(root_0, STRING120_tree);


                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:231:5: TRUE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    TRUE121=(CommonToken)match(input,TRUE,FOLLOW_TRUE_in_primary1814); 
                    TRUE121_tree = 
                    (CommonTree)adaptor.create(TRUE121)
                    ;
                    adaptor.addChild(root_0, TRUE121_tree);


                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:232:5: FALSE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    FALSE122=(CommonToken)match(input,FALSE,FOLLOW_FALSE_in_primary1820); 
                    FALSE122_tree = 
                    (CommonTree)adaptor.create(FALSE122)
                    ;
                    adaptor.addChild(root_0, FALSE122_tree);


                    }
                    break;
                case 5 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:233:5: subtemplate
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_subtemplate_in_primary1826);
                    subtemplate123=subtemplate();

                    state._fsp--;

                    adaptor.addChild(root_0, subtemplate123.getTree());

                    }
                    break;
                case 6 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:234:5: list
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_list_in_primary1832);
                    list124=list();

                    state._fsp--;

                    adaptor.addChild(root_0, list124.getTree());

                    }
                    break;
                case 7 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:235:5: {...}? => '(' ! conditional ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    if ( !((conditional_stack.size()>0)) ) {
                        throw new FailedPredicateException(input, "primary", "$conditional.size()>0");
                    }

                    char_literal125=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_primary1842); 

                    pushFollow(FOLLOW_conditional_in_primary1845);
                    conditional126=conditional();

                    state._fsp--;

                    adaptor.addChild(root_0, conditional126.getTree());

                    char_literal127=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_primary1847); 

                    }
                    break;
                case 8 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:236:5: {...}? =>lp= '(' expr ')' ( '(' ( argExprList )? ')' -> ^( INCLUDE_IND[$lp] expr ( argExprList )? ) | -> ^( TO_STR[$lp] expr ) )
                    {
                    if ( !((conditional_stack.size()==0)) ) {
                        throw new FailedPredicateException(input, "primary", "$conditional.size()==0");
                    }

                    lp=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_primary1859);  
                    stream_LPAREN.add(lp);


                    pushFollow(FOLLOW_expr_in_primary1861);
                    expr128=expr();

                    state._fsp--;

                    stream_expr.add(expr128.getTree());

                    char_literal129=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_primary1863);  
                    stream_RPAREN.add(char_literal129);


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:237:5: ( '(' ( argExprList )? ')' -> ^( INCLUDE_IND[$lp] expr ( argExprList )? ) | -> ^( TO_STR[$lp] expr ) )
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==LPAREN) ) {
                        alt37=1;
                    }
                    else if ( (LA37_0==SEMI||LA37_0==COLON||LA37_0==RPAREN||(LA37_0 >= RBRACK && LA37_0 <= DOT)||LA37_0==RDELIM||(LA37_0 >= OR && LA37_0 <= AND)) ) {
                        alt37=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 0, input);

                        throw nvae;

                    }
                    switch (alt37) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:237:7: '(' ( argExprList )? ')'
                            {
                            char_literal130=(CommonToken)match(input,LPAREN,FOLLOW_LPAREN_in_primary1871);  
                            stream_LPAREN.add(char_literal130);


                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:237:11: ( argExprList )?
                            int alt36=2;
                            int LA36_0 = input.LA(1);

                            if ( (LA36_0==SUPER||LA36_0==LBRACK||LA36_0==LCURLY||(LA36_0 >= ID && LA36_0 <= STRING)||LA36_0==AT||(LA36_0 >= TRUE && LA36_0 <= FALSE)) ) {
                                alt36=1;
                            }
                            else if ( (LA36_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                                alt36=1;
                            }
                            switch (alt36) {
                                case 1 :
                                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:237:11: argExprList
                                    {
                                    pushFollow(FOLLOW_argExprList_in_primary1873);
                                    argExprList131=argExprList();

                                    state._fsp--;

                                    stream_argExprList.add(argExprList131.getTree());

                                    }
                                    break;

                            }


                            char_literal132=(CommonToken)match(input,RPAREN,FOLLOW_RPAREN_in_primary1876);  
                            stream_RPAREN.add(char_literal132);


                            // AST REWRITE
                            // elements: argExprList, expr
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 237:39: -> ^( INCLUDE_IND[$lp] expr ( argExprList )? )
                            {
                                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:237:42: ^( INCLUDE_IND[$lp] expr ( argExprList )? )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(
                                (CommonTree)adaptor.create(INCLUDE_IND, lp)
                                , root_1);

                                adaptor.addChild(root_1, stream_expr.nextTree());

                                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:237:66: ( argExprList )?
                                if ( stream_argExprList.hasNext() ) {
                                    adaptor.addChild(root_1, stream_argExprList.nextTree());

                                }
                                stream_argExprList.reset();

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                            }
                            break;
                        case 2 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:238:25: 
                            {
                            // AST REWRITE
                            // elements: expr
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 238:25: -> ^( TO_STR[$lp] expr )
                            {
                                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:238:28: ^( TO_STR[$lp] expr )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(
                                (CommonTree)adaptor.create(TO_STR, lp)
                                , root_1);

                                adaptor.addChild(root_1, stream_expr.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "primary"


    public static class args_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "args"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:242:1: args : ( argExprList | namedArg ( ',' namedArg )* ( ',' '...' )? -> ( namedArg )+ ( '...' )? | '...' |);
    public final STParser.args_return args() throws RecognitionException {
        STParser.args_return retval = new STParser.args_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken char_literal135=null;
        CommonToken char_literal137=null;
        CommonToken string_literal138=null;
        CommonToken string_literal139=null;
        STParser.argExprList_return argExprList133 =null;

        STParser.namedArg_return namedArg134 =null;

        STParser.namedArg_return namedArg136 =null;


        CommonTree char_literal135_tree=null;
        CommonTree char_literal137_tree=null;
        CommonTree string_literal138_tree=null;
        CommonTree string_literal139_tree=null;
        RewriteRuleTokenStream stream_ELLIPSIS=new RewriteRuleTokenStream(adaptor,"token ELLIPSIS");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_namedArg=new RewriteRuleSubtreeStream(adaptor,"rule namedArg");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:242:5: ( argExprList | namedArg ( ',' namedArg )* ( ',' '...' )? -> ( namedArg )+ ( '...' )? | '...' |)
            int alt41=4;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==ID) ) {
                int LA41_1 = input.LA(2);

                if ( ((LA41_1 >= COLON && LA41_1 <= RPAREN)||(LA41_1 >= COMMA && LA41_1 <= DOT)) ) {
                    alt41=1;
                }
                else if ( (LA41_1==EQUALS) ) {
                    alt41=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 41, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA41_0==SUPER||LA41_0==LBRACK||LA41_0==LCURLY||LA41_0==STRING||LA41_0==AT||(LA41_0 >= TRUE && LA41_0 <= FALSE)) ) {
                alt41=1;
            }
            else if ( (LA41_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt41=1;
            }
            else if ( (LA41_0==ELLIPSIS) ) {
                alt41=3;
            }
            else if ( (LA41_0==RPAREN) ) {
                alt41=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }
            switch (alt41) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:242:7: argExprList
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_argExprList_in_args1948);
                    argExprList133=argExprList();

                    state._fsp--;

                    adaptor.addChild(root_0, argExprList133.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:243:5: namedArg ( ',' namedArg )* ( ',' '...' )?
                    {
                    pushFollow(FOLLOW_namedArg_in_args1954);
                    namedArg134=namedArg();

                    state._fsp--;

                    stream_namedArg.add(namedArg134.getTree());

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:243:14: ( ',' namedArg )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==COMMA) ) {
                            int LA39_1 = input.LA(2);

                            if ( (LA39_1==ID) ) {
                                alt39=1;
                            }


                        }


                        switch (alt39) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:243:16: ',' namedArg
                    	    {
                    	    char_literal135=(CommonToken)match(input,COMMA,FOLLOW_COMMA_in_args1958);  
                    	    stream_COMMA.add(char_literal135);


                    	    pushFollow(FOLLOW_namedArg_in_args1960);
                    	    namedArg136=namedArg();

                    	    state._fsp--;

                    	    stream_namedArg.add(namedArg136.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:243:32: ( ',' '...' )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==COMMA) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:243:33: ',' '...'
                            {
                            char_literal137=(CommonToken)match(input,COMMA,FOLLOW_COMMA_in_args1966);  
                            stream_COMMA.add(char_literal137);


                            string_literal138=(CommonToken)match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_args1968);  
                            stream_ELLIPSIS.add(string_literal138);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: ELLIPSIS, namedArg
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 243:45: -> ( namedArg )+ ( '...' )?
                    {
                        if ( !(stream_namedArg.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_namedArg.hasNext() ) {
                            adaptor.addChild(root_0, stream_namedArg.nextTree());

                        }
                        stream_namedArg.reset();

                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:243:58: ( '...' )?
                        if ( stream_ELLIPSIS.hasNext() ) {
                            adaptor.addChild(root_0, 
                            stream_ELLIPSIS.nextNode()
                            );

                        }
                        stream_ELLIPSIS.reset();

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:244:9: '...'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal139=(CommonToken)match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_args1988); 
                    string_literal139_tree = 
                    (CommonTree)adaptor.create(string_literal139)
                    ;
                    adaptor.addChild(root_0, string_literal139_tree);


                    }
                    break;
                case 4 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:246:3: 
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "args"


    public static class argExprList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "argExprList"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:248:1: argExprList : arg ( ',' arg )* -> ( arg )+ ;
    public final STParser.argExprList_return argExprList() throws RecognitionException {
        STParser.argExprList_return retval = new STParser.argExprList_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken char_literal141=null;
        STParser.arg_return arg140 =null;

        STParser.arg_return arg142 =null;


        CommonTree char_literal141_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_arg=new RewriteRuleSubtreeStream(adaptor,"rule arg");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:248:13: ( arg ( ',' arg )* -> ( arg )+ )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:248:15: arg ( ',' arg )*
            {
            pushFollow(FOLLOW_arg_in_argExprList2003);
            arg140=arg();

            state._fsp--;

            stream_arg.add(arg140.getTree());

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:248:19: ( ',' arg )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==COMMA) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:248:21: ',' arg
            	    {
            	    char_literal141=(CommonToken)match(input,COMMA,FOLLOW_COMMA_in_argExprList2007);  
            	    stream_COMMA.add(char_literal141);


            	    pushFollow(FOLLOW_arg_in_argExprList2009);
            	    arg142=arg();

            	    state._fsp--;

            	    stream_arg.add(arg142.getTree());

            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);


            // AST REWRITE
            // elements: arg
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 248:32: -> ( arg )+
            {
                if ( !(stream_arg.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_arg.hasNext() ) {
                    adaptor.addChild(root_0, stream_arg.nextTree());

                }
                stream_arg.reset();

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "argExprList"


    public static class arg_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arg"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:250:1: arg : exprNoComma ;
    public final STParser.arg_return arg() throws RecognitionException {
        STParser.arg_return retval = new STParser.arg_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        STParser.exprNoComma_return exprNoComma143 =null;



        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:250:5: ( exprNoComma )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:250:7: exprNoComma
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_exprNoComma_in_arg2026);
            exprNoComma143=exprNoComma();

            state._fsp--;

            adaptor.addChild(root_0, exprNoComma143.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arg"


    public static class namedArg_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "namedArg"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:252:1: namedArg : ID '=' arg -> ^( '=' ID arg ) ;
    public final STParser.namedArg_return namedArg() throws RecognitionException {
        STParser.namedArg_return retval = new STParser.namedArg_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken ID144=null;
        CommonToken char_literal145=null;
        STParser.arg_return arg146 =null;


        CommonTree ID144_tree=null;
        CommonTree char_literal145_tree=null;
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_arg=new RewriteRuleSubtreeStream(adaptor,"rule arg");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:252:10: ( ID '=' arg -> ^( '=' ID arg ) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:252:12: ID '=' arg
            {
            ID144=(CommonToken)match(input,ID,FOLLOW_ID_in_namedArg2035);  
            stream_ID.add(ID144);


            char_literal145=(CommonToken)match(input,EQUALS,FOLLOW_EQUALS_in_namedArg2037);  
            stream_EQUALS.add(char_literal145);


            pushFollow(FOLLOW_arg_in_namedArg2039);
            arg146=arg();

            state._fsp--;

            stream_arg.add(arg146.getTree());

            // AST REWRITE
            // elements: EQUALS, arg, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 252:23: -> ^( '=' ID arg )
            {
                // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:252:26: ^( '=' ID arg )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_EQUALS.nextNode()
                , root_1);

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                adaptor.addChild(root_1, stream_arg.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "namedArg"


    public static class list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "list"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:254:1: list : ({...}?lb= '[' ']' -> LIST[$lb] |lb= '[' listElement ( ',' listElement )* ']' -> ^( LIST[$lb] ( listElement )* ) );
    public final STParser.list_return list() throws RecognitionException {
        STParser.list_return retval = new STParser.list_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonToken lb=null;
        CommonToken char_literal147=null;
        CommonToken char_literal149=null;
        CommonToken char_literal151=null;
        STParser.listElement_return listElement148 =null;

        STParser.listElement_return listElement150 =null;


        CommonTree lb_tree=null;
        CommonTree char_literal147_tree=null;
        CommonTree char_literal149_tree=null;
        CommonTree char_literal151_tree=null;
        RewriteRuleTokenStream stream_RBRACK=new RewriteRuleTokenStream(adaptor,"token RBRACK");
        RewriteRuleTokenStream stream_LBRACK=new RewriteRuleTokenStream(adaptor,"token LBRACK");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_listElement=new RewriteRuleSubtreeStream(adaptor,"rule listElement");
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:254:5: ({...}?lb= '[' ']' -> LIST[$lb] |lb= '[' listElement ( ',' listElement )* ']' -> ^( LIST[$lb] ( listElement )* ) )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==LBRACK) ) {
                int LA44_1 = input.LA(2);

                if ( (LA44_1==RBRACK) ) {
                    int LA44_2 = input.LA(3);

                    if ( ((input.LA(2)==RBRACK)) ) {
                        alt44=1;
                    }
                    else if ( (true) ) {
                        alt44=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 44, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA44_1==SUPER||LA44_1==LPAREN||LA44_1==LBRACK||LA44_1==COMMA||LA44_1==LCURLY||(LA44_1 >= ID && LA44_1 <= STRING)||LA44_1==AT||(LA44_1 >= TRUE && LA44_1 <= FALSE)) ) {
                    alt44=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 44, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;

            }
            switch (alt44) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:254:7: {...}?lb= '[' ']'
                    {
                    if ( !((input.LA(2)==RBRACK)) ) {
                        throw new FailedPredicateException(input, "list", "input.LA(2)==RBRACK");
                    }

                    lb=(CommonToken)match(input,LBRACK,FOLLOW_LBRACK_in_list2066);  
                    stream_LBRACK.add(lb);


                    char_literal147=(CommonToken)match(input,RBRACK,FOLLOW_RBRACK_in_list2068);  
                    stream_RBRACK.add(char_literal147);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 255:16: -> LIST[$lb]
                    {
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(LIST, lb)
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:256:5: lb= '[' listElement ( ',' listElement )* ']'
                    {
                    lb=(CommonToken)match(input,LBRACK,FOLLOW_LBRACK_in_list2081);  
                    stream_LBRACK.add(lb);


                    pushFollow(FOLLOW_listElement_in_list2083);
                    listElement148=listElement();

                    state._fsp--;

                    stream_listElement.add(listElement148.getTree());

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:256:24: ( ',' listElement )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==COMMA) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:256:26: ',' listElement
                    	    {
                    	    char_literal149=(CommonToken)match(input,COMMA,FOLLOW_COMMA_in_list2087);  
                    	    stream_COMMA.add(char_literal149);


                    	    pushFollow(FOLLOW_listElement_in_list2089);
                    	    listElement150=listElement();

                    	    state._fsp--;

                    	    stream_listElement.add(listElement150.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);


                    char_literal151=(CommonToken)match(input,RBRACK,FOLLOW_RBRACK_in_list2094);  
                    stream_RBRACK.add(char_literal151);


                    // AST REWRITE
                    // elements: listElement
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 256:49: -> ^( LIST[$lb] ( listElement )* )
                    {
                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:256:52: ^( LIST[$lb] ( listElement )* )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(LIST, lb)
                        , root_1);

                        // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:256:64: ( listElement )*
                        while ( stream_listElement.hasNext() ) {
                            adaptor.addChild(root_1, stream_listElement.nextTree());

                        }
                        stream_listElement.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "list"


    public static class listElement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listElement"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:259:1: listElement : ( exprNoComma | -> NULL );
    public final STParser.listElement_return listElement() throws RecognitionException {
        STParser.listElement_return retval = new STParser.listElement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        STParser.exprNoComma_return exprNoComma152 =null;



        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:259:13: ( exprNoComma | -> NULL )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==SUPER||LA45_0==LBRACK||LA45_0==LCURLY||(LA45_0 >= ID && LA45_0 <= STRING)||LA45_0==AT||(LA45_0 >= TRUE && LA45_0 <= FALSE)) ) {
                alt45=1;
            }
            else if ( (LA45_0==LPAREN) && (((conditional_stack.size()==0)||(conditional_stack.size()>0)))) {
                alt45=1;
            }
            else if ( ((LA45_0 >= RBRACK && LA45_0 <= COMMA)) ) {
                alt45=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }
            switch (alt45) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:259:15: exprNoComma
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_exprNoComma_in_listElement2115);
                    exprNoComma152=exprNoComma();

                    state._fsp--;

                    adaptor.addChild(root_0, exprNoComma152.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\STParser.g:259:29: 
                    {
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 259:29: -> NULL
                    {
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(NULL, "NULL")
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

           catch (RecognitionException re) { throw re; }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "listElement"

    // Delegated rules


 

    public static final BitSet FOLLOW_template_in_templateAndEOF143 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_templateAndEOF145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_template159 = new BitSet(new long[]{0x0000002180C00002L});
    public static final BitSet FOLLOW_INDENT_in_element173 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_COMMENT_in_element176 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_NEWLINE_in_element178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_element187 = new BitSet(new long[]{0x0000002100C00000L});
    public static final BitSet FOLLOW_singleElement_in_element189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_singleElement_in_element207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compoundElement_in_element213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprTag_in_singleElement226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_singleElement232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_singleElement238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMENT_in_singleElement244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifstat_in_compoundElement259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_region_in_compoundElement265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LDELIM_in_exprTag278 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_expr_in_exprTag280 = new BitSet(new long[]{0x0000000001000200L});
    public static final BitSet FOLLOW_SEMI_in_exprTag284 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_exprOptions_in_exprTag286 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_exprTag291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_region327 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_region332 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_AT_in_region334 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_region336 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_region338 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_region346 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_region352 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_region355 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_END_in_region357 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_region359 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_NEWLINE_in_region374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_subtemplate458 = new BitSet(new long[]{0x0000002182E00000L});
    public static final BitSet FOLLOW_ID_in_subtemplate464 = new BitSet(new long[]{0x0000000010040000L});
    public static final BitSet FOLLOW_COMMA_in_subtemplate468 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_subtemplate473 = new BitSet(new long[]{0x0000000010040000L});
    public static final BitSet FOLLOW_PIPE_in_subtemplate478 = new BitSet(new long[]{0x0000002180E00000L});
    public static final BitSet FOLLOW_template_in_subtemplate483 = new BitSet(new long[]{0x0000000080200000L});
    public static final BitSet FOLLOW_INDENT_in_subtemplate485 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_RCURLY_in_subtemplate488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDENT_in_ifstat535 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat538 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IF_in_ifstat540 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_ifstat542 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_conditional_in_ifstat546 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_ifstat548 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat550 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat562 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat572 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat575 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ELSEIF_in_ifstat577 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_ifstat579 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_conditional_in_ifstat583 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_ifstat585 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat587 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat591 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat604 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat607 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ELSE_in_ifstat609 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat611 = new BitSet(new long[]{0x0000002180C00000L});
    public static final BitSet FOLLOW_template_in_ifstat615 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_INDENT_in_ifstat626 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LDELIM_in_ifstat632 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ENDIF_in_ifstat634 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RDELIM_in_ifstat640 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ifstat655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andConditional_in_conditional783 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_OR_in_conditional787 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_andConditional_in_conditional790 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_notConditional_in_andConditional804 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_AND_in_andConditional808 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_notConditional_in_andConditional811 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_BANG_in_notConditional825 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_notConditional_in_notConditional828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_notConditional834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_notConditionalExpr848 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_notConditionalExpr861 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_notConditionalExpr865 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_notConditionalExpr898 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_notConditionalExpr900 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_mapExpr_in_notConditionalExpr902 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_notConditionalExpr904 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_option_in_exprOptions940 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_exprOptions944 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_option_in_exprOptions946 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ID_in_option974 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_EQUALS_in_option988 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_exprNoComma_in_option990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_exprNoComma1156 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COLON_in_exprNoComma1164 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_exprNoComma1166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mapExpr_in_expr1230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpr_in_mapExpr1243 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_COMMA_in_mapExpr1254 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_memberExpr_in_mapExpr1256 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_COLON_in_mapExpr1262 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr1264 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COLON_in_mapExpr1357 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr1361 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_COMMA_in_mapExpr1367 = new BitSet(new long[]{0x0000000002104000L});
    public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr1371 = new BitSet(new long[]{0x0000000000042002L});
    public static final BitSet FOLLOW_ID_in_mapTemplateRef1434 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef1436 = new BitSet(new long[]{0x0000001A0611C900L});
    public static final BitSet FOLLOW_args_in_mapTemplateRef1438 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef1440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subtemplate_in_mapTemplateRef1469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef1477 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_mapExpr_in_mapTemplateRef1479 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef1483 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef1485 = new BitSet(new long[]{0x0000001A0611C100L});
    public static final BitSet FOLLOW_argExprList_in_mapTemplateRef1487 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef1490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_includeExpr_in_memberExpr1515 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_memberExpr1528 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_memberExpr1530 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_DOT_in_memberExpr1565 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_memberExpr1567 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_mapExpr_in_memberExpr1569 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_memberExpr1571 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_ID_in_includeExpr1624 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1626 = new BitSet(new long[]{0x0000001A0611C100L});
    public static final BitSet FOLLOW_expr_in_includeExpr1628 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_includeExpr1659 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_includeExpr1661 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_includeExpr1663 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1665 = new BitSet(new long[]{0x0000001A0611C900L});
    public static final BitSet FOLLOW_args_in_includeExpr1667 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_includeExpr1692 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1694 = new BitSet(new long[]{0x0000001A0611C900L});
    public static final BitSet FOLLOW_args_in_includeExpr1696 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_includeExpr1727 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_SUPER_in_includeExpr1729 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_includeExpr1731 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_includeExpr1733 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1735 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_includeExpr1757 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ID_in_includeExpr1759 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_includeExpr1761 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_includeExpr1765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_includeExpr1789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_primary1808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_primary1814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_primary1820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subtemplate_in_primary1826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_primary1832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1842 = new BitSet(new long[]{0x0000001A06114500L});
    public static final BitSet FOLLOW_conditional_in_primary1845 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1859 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_expr_in_primary1861 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1863 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_LPAREN_in_primary1871 = new BitSet(new long[]{0x0000001A0611C100L});
    public static final BitSet FOLLOW_argExprList_in_primary1873 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_RPAREN_in_primary1876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_argExprList_in_args1948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_namedArg_in_args1954 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_args1958 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_namedArg_in_args1960 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_args1966 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ELLIPSIS_in_args1968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELLIPSIS_in_args1988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_in_argExprList2003 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_COMMA_in_argExprList2007 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_arg_in_argExprList2009 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_exprNoComma_in_arg2026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_namedArg2035 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_EQUALS_in_namedArg2037 = new BitSet(new long[]{0x0000001A06114100L});
    public static final BitSet FOLLOW_arg_in_namedArg2039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_list2066 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACK_in_list2068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_list2081 = new BitSet(new long[]{0x0000001A06174100L});
    public static final BitSet FOLLOW_listElement_in_list2083 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_COMMA_in_list2087 = new BitSet(new long[]{0x0000001A06174100L});
    public static final BitSet FOLLOW_listElement_in_list2089 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_RBRACK_in_list2094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprNoComma_in_listElement2115 = new BitSet(new long[]{0x0000000000000002L});

}