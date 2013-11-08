// $ANTLR 3.4 D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g 2013-11-08 09:57:04


package nebula.simpletemplate;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

import org.stringtemplate.v4.misc.ErrorType;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class GroupParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ANONYMOUS_TEMPLATE", "BIGSTRING", "BIGSTRING_NO_NL", "COMMENT", "FALSE", "ID", "LBRACK", "LINE_COMMENT", "RBRACK", "STRING", "TRUE", "WS", "'('", "')'", "','", "'.'", "':'", "'::='", "';'", "'='", "'@'", "'delimiters'", "'group'", "'implements'"
    };

    public static final int EOF=-1;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int ANONYMOUS_TEMPLATE=4;
    public static final int BIGSTRING=5;
    public static final int BIGSTRING_NO_NL=6;
    public static final int COMMENT=7;
    public static final int FALSE=8;
    public static final int ID=9;
    public static final int LBRACK=10;
    public static final int LINE_COMMENT=11;
    public static final int RBRACK=12;
    public static final int STRING=13;
    public static final int TRUE=14;
    public static final int WS=15;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public GroupParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public GroupParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return GroupParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g"; }


    public STGroup group;

    @Override
    public void displayRecognitionError(String[] tokenNames,
                                        RecognitionException e)
    {
        String msg = getErrorMessage(e, tokenNames);
        group.errMgr.groupSyntaxError(ErrorType.SYNTAX_ERROR, getSourceName(), e, msg);
    }
    @Override
    public String getSourceName() {
        String fullFileName = super.getSourceName();
        File f = new File(fullFileName); // strip to simple name
        return f.getName();
    }
    public void error(String msg) {
        NoViableAltException e = new NoViableAltException("", 0, 0, input);
        group.errMgr.groupSyntaxError(ErrorType.SYNTAX_ERROR, getSourceName(), e, msg);
        recover(input, null);
    }



    // $ANTLR start "group"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:109:1: group[STGroup group, String prefix] : ( oldStyleHeader )? ( delimiters )? ( def[prefix] )* EOF ;
    public final void group(STGroup group, String prefix) throws RecognitionException {

        GroupLexer lexer = (GroupLexer)input.getTokenSource();
        this.group = lexer.group = group;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:114:2: ( ( oldStyleHeader )? ( delimiters )? ( def[prefix] )* EOF )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:114:4: ( oldStyleHeader )? ( delimiters )? ( def[prefix] )* EOF
            {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:114:4: ( oldStyleHeader )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==26) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:114:4: oldStyleHeader
                    {
                    pushFollow(FOLLOW_oldStyleHeader_in_group86);
                    oldStyleHeader();

                    state._fsp--;


                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:115:3: ( delimiters )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==25) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:115:3: delimiters
                    {
                    pushFollow(FOLLOW_delimiters_in_group91);
                    delimiters();

                    state._fsp--;


                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:124:3: ( def[prefix] )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID||LA3_0==24) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:124:3: def[prefix]
            	    {
            	    pushFollow(FOLLOW_def_in_group98);
            	    def(prefix);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            match(input,EOF,FOLLOW_EOF_in_group104); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "group"



    // $ANTLR start "oldStyleHeader"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:128:1: oldStyleHeader : 'group' ID ( ':' ID )? ( 'implements' ID ( ',' ID )* )? ';' ;
    public final void oldStyleHeader() throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:129:5: ( 'group' ID ( ':' ID )? ( 'implements' ID ( ',' ID )* )? ';' )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:129:9: 'group' ID ( ':' ID )? ( 'implements' ID ( ',' ID )* )? ';'
            {
            match(input,26,FOLLOW_26_in_oldStyleHeader121); 

            match(input,ID,FOLLOW_ID_in_oldStyleHeader123); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:129:20: ( ':' ID )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==20) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:129:22: ':' ID
                    {
                    match(input,20,FOLLOW_20_in_oldStyleHeader127); 

                    match(input,ID,FOLLOW_ID_in_oldStyleHeader129); 

                    }
                    break;

            }


            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:130:6: ( 'implements' ID ( ',' ID )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==27) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:130:8: 'implements' ID ( ',' ID )*
                    {
                    match(input,27,FOLLOW_27_in_oldStyleHeader141); 

                    match(input,ID,FOLLOW_ID_in_oldStyleHeader143); 

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:130:24: ( ',' ID )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==18) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:130:25: ',' ID
                    	    {
                    	    match(input,18,FOLLOW_18_in_oldStyleHeader146); 

                    	    match(input,ID,FOLLOW_ID_in_oldStyleHeader148); 

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,22,FOLLOW_22_in_oldStyleHeader160); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "oldStyleHeader"



    // $ANTLR start "groupName"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:134:1: groupName returns [String name] : a= ID ( '.' a= ID )* ;
    public final String groupName() throws RecognitionException {
        String name = null;


        Token a=null;

        StringBuilder buf = new StringBuilder();
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:136:2: (a= ID ( '.' a= ID )* )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:136:4: a= ID ( '.' a= ID )*
            {
            a=(Token)match(input,ID,FOLLOW_ID_in_groupName182); 

            buf.append((a!=null?a.getText():null));

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:136:32: ( '.' a= ID )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==19) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:136:33: '.' a= ID
            	    {
            	    match(input,19,FOLLOW_19_in_groupName187); 

            	    a=(Token)match(input,ID,FOLLOW_ID_in_groupName191); 

            	    buf.append((a!=null?a.getText():null));

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return name;
    }
    // $ANTLR end "groupName"



    // $ANTLR start "delimiters"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:139:1: delimiters : 'delimiters' a= STRING ',' b= STRING ;
    public final void delimiters() throws RecognitionException {
        Token a=null;
        Token b=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:140:5: ( 'delimiters' a= STRING ',' b= STRING )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:140:7: 'delimiters' a= STRING ',' b= STRING
            {
            match(input,25,FOLLOW_25_in_delimiters209); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_delimiters213); 

            match(input,18,FOLLOW_18_in_delimiters215); 

            b=(Token)match(input,STRING,FOLLOW_STRING_in_delimiters219); 


                 	group.delimiterStartChar=a.getText().charAt(1);
                    group.delimiterStopChar=b.getText().charAt(1);
                    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "delimiters"



    // $ANTLR start "def"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:151:1: def[String prefix] : templateDef[prefix] ;
    public final void def(String prefix) throws RecognitionException {
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:151:20: ( templateDef[prefix] )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:151:22: templateDef[prefix]
            {
            pushFollow(FOLLOW_templateDef_in_def243);
            templateDef(prefix);

            state._fsp--;


            }

        }
        catch (RecognitionException re) {

            		// pretend we already saw an error here
            		state.lastErrorIndex = input.index();
            		error("garbled template definition starting at '"+input.LT(1).getText()+"'");
            	
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "def"



    // $ANTLR start "templateDef"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:158:1: templateDef[String prefix] : ( ( '@' enclosing= ID '.' name= ID '(' ')' |name= ID '(' formalArgs ')' ) '::=' ( STRING | BIGSTRING | BIGSTRING_NO_NL |) |alias= ID '::=' target= ID );
    public final void templateDef(String prefix) throws RecognitionException {
        Token enclosing=null;
        Token name=null;
        Token alias=null;
        Token target=null;
        Token STRING1=null;
        Token BIGSTRING2=null;
        Token BIGSTRING_NO_NL3=null;
        List<FormalArgument> formalArgs4 =null;



            String template=null;
            int n=0; // num char to strip from left, right of template def

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:163:2: ( ( '@' enclosing= ID '.' name= ID '(' ')' |name= ID '(' formalArgs ')' ) '::=' ( STRING | BIGSTRING | BIGSTRING_NO_NL |) |alias= ID '::=' target= ID )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==24) ) {
                alt10=1;
            }
            else if ( (LA10_0==ID) ) {
                int LA10_2 = input.LA(2);

                if ( (LA10_2==16) ) {
                    alt10=1;
                }
                else if ( (LA10_2==21) ) {
                    alt10=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:163:4: ( '@' enclosing= ID '.' name= ID '(' ')' |name= ID '(' formalArgs ')' ) '::=' ( STRING | BIGSTRING | BIGSTRING_NO_NL |)
                    {
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:163:4: ( '@' enclosing= ID '.' name= ID '(' ')' |name= ID '(' formalArgs ')' )
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==24) ) {
                        alt8=1;
                    }
                    else if ( (LA8_0==ID) ) {
                        alt8=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 0, input);

                        throw nvae;

                    }
                    switch (alt8) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:163:6: '@' enclosing= ID '.' name= ID '(' ')'
                            {
                            match(input,24,FOLLOW_24_in_templateDef267); 

                            enclosing=(Token)match(input,ID,FOLLOW_ID_in_templateDef271); 

                            match(input,19,FOLLOW_19_in_templateDef273); 

                            name=(Token)match(input,ID,FOLLOW_ID_in_templateDef277); 

                            match(input,16,FOLLOW_16_in_templateDef279); 

                            match(input,17,FOLLOW_17_in_templateDef281); 

                            }
                            break;
                        case 2 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:164:5: name= ID '(' formalArgs ')'
                            {
                            name=(Token)match(input,ID,FOLLOW_ID_in_templateDef289); 

                            match(input,16,FOLLOW_16_in_templateDef291); 

                            pushFollow(FOLLOW_formalArgs_in_templateDef293);
                            formalArgs4=formalArgs();

                            state._fsp--;


                            match(input,17,FOLLOW_17_in_templateDef295); 

                            }
                            break;

                    }


                    match(input,21,FOLLOW_21_in_templateDef306); 

                    Token templateToken = input.LT(1);

                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:168:6: ( STRING | BIGSTRING | BIGSTRING_NO_NL |)
                    int alt9=4;
                    switch ( input.LA(1) ) {
                    case STRING:
                        {
                        alt9=1;
                        }
                        break;
                    case BIGSTRING:
                        {
                        alt9=2;
                        }
                        break;
                    case BIGSTRING_NO_NL:
                        {
                        alt9=3;
                        }
                        break;
                    case EOF:
                    case ID:
                    case 24:
                        {
                        alt9=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 0, input);

                        throw nvae;

                    }

                    switch (alt9) {
                        case 1 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:168:8: STRING
                            {
                            STRING1=(Token)match(input,STRING,FOLLOW_STRING_in_templateDef322); 

                            template=(STRING1!=null?STRING1.getText():null); n=1;

                            }
                            break;
                        case 2 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:169:8: BIGSTRING
                            {
                            BIGSTRING2=(Token)match(input,BIGSTRING,FOLLOW_BIGSTRING_in_templateDef337); 

                            template=(BIGSTRING2!=null?BIGSTRING2.getText():null); n=2;

                            }
                            break;
                        case 3 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:170:8: BIGSTRING_NO_NL
                            {
                            BIGSTRING_NO_NL3=(Token)match(input,BIGSTRING_NO_NL,FOLLOW_BIGSTRING_NO_NL_in_templateDef349); 

                            template=(BIGSTRING_NO_NL3!=null?BIGSTRING_NO_NL3.getText():null); n=2;

                            }
                            break;
                        case 4 :
                            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:171:8: 
                            {

                            	    	template = "";
                            	    	String msg = "missing template at '"+input.LT(1).getText()+"'";
                                        NoViableAltException e = new NoViableAltException("", 0, 0, input);
                                	    group.errMgr.groupSyntaxError(ErrorType.SYNTAX_ERROR, getSourceName(), e, msg);
                                	    

                            }
                            break;

                    }



                    	    if ( (name!=null?name.getTokenIndex():0) >= 0 ) { // if ID missing
                    			template = Misc.strip(template, n);
                    			String templateName = (name!=null?name.getText():null);
                    			if ( prefix.length()>0 ) templateName = prefix+(name!=null?name.getText():null);
                    			String enclosingTemplateName = (enclosing!=null?enclosing.getText():null);
                    			if (enclosingTemplateName != null && enclosingTemplateName.length()>0 && prefix.length()>0) {
                    				enclosingTemplateName = prefix + enclosingTemplateName;
                    			}
                    			group.defineTemplateOrRegion(templateName, enclosingTemplateName, templateToken,
                    										 template, name, formalArgs4);
                    		}
                    	    

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:191:6: alias= ID '::=' target= ID
                    {
                    alias=(Token)match(input,ID,FOLLOW_ID_in_templateDef384); 

                    match(input,21,FOLLOW_21_in_templateDef386); 

                    target=(Token)match(input,ID,FOLLOW_ID_in_templateDef390); 

                    group.defineTemplateAlias((alias!=null?alias.getText():null), (target!=null?target.getText():null));

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "templateDef"


    protected static class formalArgs_scope {
        boolean hasOptionalParameter;
    }
    protected Stack formalArgs_stack = new Stack();



    // $ANTLR start "formalArgs"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:194:1: formalArgs returns [List<FormalArgument> args = new ArrayList<FormalArgument>()] : ( formalArg[$args] ( ',' formalArg[$args] )* |);
    public final List<FormalArgument> formalArgs() throws RecognitionException {
        formalArgs_stack.push(new formalArgs_scope());
        List<FormalArgument> args =  new ArrayList<FormalArgument>();


         ((formalArgs_scope)formalArgs_stack.peek()).hasOptionalParameter = false; 
        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:199:2: ( formalArg[$args] ( ',' formalArg[$args] )* |)
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ID) ) {
                alt12=1;
            }
            else if ( (LA12_0==17) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }
            switch (alt12) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:199:4: formalArg[$args] ( ',' formalArg[$args] )*
                    {
                    pushFollow(FOLLOW_formalArg_in_formalArgs416);
                    formalArg(args);

                    state._fsp--;


                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:199:21: ( ',' formalArg[$args] )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==18) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:199:22: ',' formalArg[$args]
                    	    {
                    	    match(input,18,FOLLOW_18_in_formalArgs420); 

                    	    pushFollow(FOLLOW_formalArg_in_formalArgs422);
                    	    formalArg(args);

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:201:2: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            formalArgs_stack.pop();
        }
        return args;
    }
    // $ANTLR end "formalArgs"



    // $ANTLR start "formalArg"
    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:203:1: formalArg[List<FormalArgument> args] : ID ( '=' a= ( STRING | ANONYMOUS_TEMPLATE | 'true' | 'false' ) | '=' a= '[' ']' |) ;
    public final void formalArg(List<FormalArgument> args) throws RecognitionException {
        Token a=null;
        Token ID5=null;

        try {
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:204:2: ( ID ( '=' a= ( STRING | ANONYMOUS_TEMPLATE | 'true' | 'false' ) | '=' a= '[' ']' |) )
            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:204:4: ID ( '=' a= ( STRING | ANONYMOUS_TEMPLATE | 'true' | 'false' ) | '=' a= '[' ']' |)
            {
            ID5=(Token)match(input,ID,FOLLOW_ID_in_formalArg440); 

            // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:205:3: ( '=' a= ( STRING | ANONYMOUS_TEMPLATE | 'true' | 'false' ) | '=' a= '[' ']' |)
            int alt13=3;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==23) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==ANONYMOUS_TEMPLATE||LA13_1==FALSE||(LA13_1 >= STRING && LA13_1 <= TRUE)) ) {
                    alt13=1;
                }
                else if ( (LA13_1==LBRACK) ) {
                    alt13=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;

                }
            }
            else if ( ((LA13_0 >= 17 && LA13_0 <= 18)) ) {
                alt13=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }
            switch (alt13) {
                case 1 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:205:5: '=' a= ( STRING | ANONYMOUS_TEMPLATE | 'true' | 'false' )
                    {
                    match(input,23,FOLLOW_23_in_formalArg446); 

                    a=(Token)input.LT(1);

                    if ( input.LA(1)==ANONYMOUS_TEMPLATE||input.LA(1)==FALSE||(input.LA(1) >= STRING && input.LA(1) <= TRUE) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    ((formalArgs_scope)formalArgs_stack.peek()).hasOptionalParameter = true;

                    }
                    break;
                case 2 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:206:5: '=' a= '[' ']'
                    {
                    match(input,23,FOLLOW_23_in_formalArg466); 

                    a=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_formalArg470); 

                    match(input,RBRACK,FOLLOW_RBRACK_in_formalArg472); 

                    ((formalArgs_scope)formalArgs_stack.peek()).hasOptionalParameter = true;

                    }
                    break;
                case 3 :
                    // D:\\Projects\\nebula\\nebula-compiler\\src\\main\\java\\nebula\\simpletemplate\\Group.g:207:5: 
                    {

                    			if (((formalArgs_scope)formalArgs_stack.peek()).hasOptionalParameter) {
                    				group.errMgr.compileTimeError(ErrorType.REQUIRED_PARAMETER_AFTER_OPTIONAL,
                    				 							  null, ID5);
                    			}
                    			

                    }
                    break;

            }


            args.add(new FormalArgument((ID5!=null?ID5.getText():null), a));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "formalArg"

    // Delegated rules


 

    public static final BitSet FOLLOW_oldStyleHeader_in_group86 = new BitSet(new long[]{0x0000000003000200L});
    public static final BitSet FOLLOW_delimiters_in_group91 = new BitSet(new long[]{0x0000000001000200L});
    public static final BitSet FOLLOW_def_in_group98 = new BitSet(new long[]{0x0000000001000200L});
    public static final BitSet FOLLOW_EOF_in_group104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_oldStyleHeader121 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_oldStyleHeader123 = new BitSet(new long[]{0x0000000008500000L});
    public static final BitSet FOLLOW_20_in_oldStyleHeader127 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_oldStyleHeader129 = new BitSet(new long[]{0x0000000008400000L});
    public static final BitSet FOLLOW_27_in_oldStyleHeader141 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_oldStyleHeader143 = new BitSet(new long[]{0x0000000000440000L});
    public static final BitSet FOLLOW_18_in_oldStyleHeader146 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_oldStyleHeader148 = new BitSet(new long[]{0x0000000000440000L});
    public static final BitSet FOLLOW_22_in_oldStyleHeader160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_groupName182 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_groupName187 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_groupName191 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_25_in_delimiters209 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_STRING_in_delimiters213 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_delimiters215 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_STRING_in_delimiters219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_templateDef_in_def243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_templateDef267 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_templateDef271 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_templateDef273 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_templateDef277 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_templateDef279 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_templateDef281 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_ID_in_templateDef289 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_templateDef291 = new BitSet(new long[]{0x0000000000020200L});
    public static final BitSet FOLLOW_formalArgs_in_templateDef293 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_templateDef295 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_templateDef306 = new BitSet(new long[]{0x0000000000002062L});
    public static final BitSet FOLLOW_STRING_in_templateDef322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIGSTRING_in_templateDef337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIGSTRING_NO_NL_in_templateDef349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_templateDef384 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_templateDef386 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_templateDef390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_formalArg_in_formalArgs416 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_formalArgs420 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_formalArg_in_formalArgs422 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ID_in_formalArg440 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_23_in_formalArg446 = new BitSet(new long[]{0x0000000000006110L});
    public static final BitSet FOLLOW_set_in_formalArg450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_formalArg466 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_LBRACK_in_formalArg470 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_RBRACK_in_formalArg472 = new BitSet(new long[]{0x0000000000000002L});

}