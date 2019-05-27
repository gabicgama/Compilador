
package parser;


import java.io.IOException;
import java.util.*;
import lexer.Tag;
import lexer.*;

public class Parser {

    private Lexer lexer;
    private Token token;
    private Tag tag;
    //Hashtable<String, Token> sintatico;

    public Parser(Lexer lexer) throws IOException {
        this.lexer = lexer;
        next();
    }

    public void next() throws IOException {
        token = lexer.scan();
        System.out.println();
    }

    private void erro() {
        System.out.println("Erro  na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
        System.exit(0);
    }

    private void eat(int tag) throws IOException {
        if (token.tag == tag) {
            System.out.print("eat: " + token);
            next();
        } else {
            erro();
        }
    }

    //program ::= app identifier body
    public void programa() throws IOException {
        switch (token.tag) {
            case Tag.APP:
                eat(Tag.APP);
                identifier();
                body();
                break;
            default:
                erro();
        }
    }

    //rever
    //body ::= [ decl-list] start stmt-list stop
    public void body() throws IOException {
        switch (token.tag) {
            case Tag.INT:
            case Tag.REAL:
            case Tag.FLOAT:
                decList();
                if (token.tag == Tag.START) {
                    eat(Tag.START);
                } else {
                    System.out.println("Erro ;");
                    erro();
                }
                stmtList();
                eat(Tag.STOP);
                break;
            default:
                erro();
        }
    }

    //decl-list ::= decl {";" decl}
    public void decList() throws IOException {
        decl();
        while (token.tag == ';') {
            eat(';');
            decl();
        }
    }

    //rever
    //decl ::= type ident-list
    public void decl() throws IOException {
        switch (token.tag) {
            case Tag.INT:
            case Tag.REAL:
            case Tag.FLOAT:
                type();
                identList();
                break;
            default:
                erro();
        }
    }

    //rever
    public void type() throws IOException {
        if (token.tag == Tag.INT) {
            eat(Tag.INT);
        } else if (token.tag == Tag.REAL) {
            eat(Tag.REAL);
        } else {
            System.out.println("Necessário definir o tipo na linha " + Lexer.line);
        }
    }

    //ident-list ::= identifier {"," identifier}
    public void identList() throws IOException {
        switch (token.tag) {
            case Tag.IDF:
                identifier();
                while (token.tag == ',') {
                    eat(',');
                    identifier();
                }
                break;
            default:
                erro();
        }
    }

    // stmt-list ::= stmt {";" stmt}
    public void stmtList() throws IOException {
        switch (token.tag) {
            case Tag.IF:
            case Tag.IDF:
            case Tag.REPEAT:
            case Tag.READ:
            case Tag.WHILE:
            case Tag.WRITE:
                stmt();

                while (token.tag == ';') {
                    eat(';');
                    stmt();
                }
                break;

            default:
                erro();
        }
    }

    //stmt ::= assign-stmt | if-stmt | while-stmt | repeat-stmt| read-stmt | write-stmt
    public void stmt() throws IOException {
        switch (token.tag) {
            case Tag.IDF:
                assignStmt();
                break;
            case Tag.IF:
                ifStmt();
                break;
            case Tag.WHILE:
                WHILEstmt();
                break;
            case Tag.REPEAT:
                repeatStmt();
                break;
            case Tag.READ:
                readStmt();
                break;
            case Tag.WRITE:
                writeStmt();
                break;
            default:
                erro();

        }
    }

    //rever
    //assign-stmt ::= identifier ":=" simple_expr 
    public void assignStmt() throws IOException {
        switch (token.tag) {
            case Tag.IDF:
//                eat(Tag.IDF);
                identifier();
                eat(Tag.ATB);
                simpleExpr();
                break;
            default:
                erro();

        }
    }

    //if-stmt ::= if condition then stmt-list else-stmt
    public void ifStmt() throws IOException {
        switch (token.tag) {
            case Tag.IF:
                eat(Tag.IF);
                condition();
                eat(Tag.THEN);
                stmtList();
                elseStmt();
                break;
            default:
                erro();

        }
    }

    //else-stmt ::= end | else stmt-list end
    public void elseStmt() throws IOException {
        switch (token.tag) {
            case Tag.END:
                eat(Tag.END);
                break;
            case Tag.ELSE:
                eat(Tag.ELSE);
                stmtList();
                eat(Tag.END);
                break;
            default:
                erro();
        }
    }

    //condition ::= expression 
    public void condition() throws IOException {
        expression();
    }

    //repeat-stmt ::= repeat stmt-list stmt-suffix
    public void repeatStmt() throws IOException {
        switch (token.tag) {
            case Tag.REPEAT:
                eat(Tag.REPEAT);
                stmtList();
                stmtSuffix();
                break;
            default:
                erro();
        }
    }

    //stmt-suffix ::= until condition 
    public void stmtSuffix() throws IOException {
        switch (token.tag) {
            case Tag.UNTIL:
                eat(Tag.UNTIL);
                condition();
                break;
            default:
                erro();
        }
    }

    //while-stmt ::= stmt-prefix stmt-list end 
    public void WHILEstmt() throws IOException {
        switch (token.tag) {
            case Tag.WHILE:
                stmtPrefix();
                stmtList();
                eat(Tag.END);
                break;
            default:
                erro();
        }
    }

    //stmt-prefix ::= while condition do 
    public void stmtPrefix() throws IOException {
        switch (token.tag) {
            case Tag.WHILE:
                eat(Tag.WHILE);
                condition();
                eat(Tag.DO);
                break;
            default:
                erro();
        }
    }

    //read-stmt ::= read "(" identifier ")" 
    public void readStmt() throws IOException {
        switch (token.tag) {
            case Tag.READ:
                eat(Tag.READ);
                eat('(');
                identifier();
                eat(')');
                break;
            default:
                erro();
        }
    }

    //write-stmt ::= write "(" writable ")" 
    public void writeStmt() throws IOException {
        switch (token.tag) {
            case Tag.WRITE:
                eat(Tag.WRITE);
                eat('(');
                WRITABLE();
                eat(')');
                break;
            default:
                erro();
        }
    }

    //rever
    //writable ::= simple-expr | literal 
    public void WRITABLE() throws IOException {
        switch (token.tag) {
            case Tag.LIT:
                eat(Tag.LIT);
                break;
            case Tag.IDF:
            case Tag.INT:
            case Tag.REAL:
            case Tag.FLOAT:
            case '(':
            case '{':
            case '!':
            case '-':
                simpleExpr();
                break;
            default:
                erro();
        }
    }

    //rever
    //expression ::= simple-expr | simple-expr relop simple-expr 
    public void expression() throws IOException {
        switch (token.tag) {
            case Tag.IDF:
            case Tag.INT:
            case Tag.REAL:
            case Tag.FLOAT:
            case '(':
            case '{':
            case '!':
            case '-':
                simpleExpr();
                if (token.tag == '=' || token.tag == Tag.NE || token.tag == Tag.GE
                        || token.tag == Tag.LE || token.tag == '>' || token.tag == '<') {
                    relop();
                    simpleExpr();
                }
                break;
            default:
                erro();
        }
    }

    //rever
    //simple-expr ::= term simple-expr’
    public void simpleExpr() throws IOException {
        switch (token.tag) {
            case Tag.IDF:
            case Tag.INT:
            case Tag.REAL:
            case Tag.FLOAT:
            case '(':
            case '{':
            case '!':
            case '-':
                term();
                simpleExpr2();
                break;
            default:
                erro();
        }
    }

    //simple-expr’ ::= λ | addop term simple-expr’ 
    public void simpleExpr2() throws IOException {
        if (token.tag == Tag.OR || token.tag == '+' || token.tag == '-') {
            addop();
            term();
            simpleExpr2();
        }
    }

    //rever
    //term ::= factor-a term’
    public void term() throws IOException {
        switch (token.tag) {
            case Tag.IDF:
            case Tag.INT:
            case Tag.REAL:
            case Tag.FLOAT:
            case '(':
            case '{':
            case '!':
            case '-':
                factorA();
                term2();
                break;
            default:
                erro();
        }

    }

    //term’ ::= λ | mulop factor-a term’  
    public void term2() throws IOException {
        switch (token.tag) {
            case '*':
                eat('*');
                factorA();
                term2();
                break;
            case ('/'):
                eat('/');
                factorA();
                term2();
                break;
            case Tag.AND:
                eat(Tag.AND);
                factorA();
                term2();
                break;
            default:
                break;
        }
    }

    //rever
    //fator-a ::= factor | "!" factor | "-" factor 
    public void factorA() throws IOException {
        switch (token.tag) {
            case '!':
                eat('!');
                factor();
                break;
            case '-':
                eat('-');
                factor();
                break;
            case '(':
                eat('(');//eat 
                factor();
                break;
            case Tag.IDF:
            case Tag.INT:
            case Tag.REAL:
            case Tag.FLOAT:
                factor();
                break;
            default:
                erro();
        }
    }

    //rever
    //factor ::= identifier | constant | "(" expression ")" 
    public void factor() throws IOException {
        switch (token.tag) {
            case Tag.IDF:
                identifier();
                break;
            case Tag.FLOAT:
            case Tag.INT:
                constant();
                break;
            case ('('):
                eat('(');
                expression();
                eat(')');
                break;
            default:
                erro();

        }
    }

    //relop ::= "=" | ">" | ">=" | "<" | "<=" | "!=" 
    public void relop() throws IOException {
        switch (token.tag) {
            case '=':
                eat('=');
                break;
            case '>':
                eat('>');
                break;
            case Tag.GE:
                eat(Tag.GE);
                break;
            case '<':
                eat('<');
                break;
            case Tag.LE:
                eat(Tag.LE);
                break;
            case Tag.NE:
                eat(Tag.NE);
                break;
            default:
                erro();
        }
    }

    // addop ::= "+" | "-" | "||" 
    public void addop() throws IOException {
        switch (token.tag) {
            case ('+'):
                eat('+');
                break;
            case ('-'):
                eat('-');
                break;
            case Tag.OR:
                eat(Tag.OR);
                break;
            default:
                erro();
        }
    }

    //mulop ::= "*" | "/" | "&&" 
    public void mulop() throws IOException {
        switch (token.tag) {
            case ('*'):
                eat('*');
                break;
            case ('/'):
                eat('/');
                break;
            case Tag.AND:
                eat(Tag.AND);
                break;
            default:
                erro();
        }
    }

    //constant ::= integer_const | float_const 
    public void constant() throws IOException {
        switch (token.tag) {
            case Tag.INT:
                integerConst();
                break;
            case Tag.FLOAT:
                floatConst();
                break;
            default:
                erro();

        }
    }

    //rever
    // integer_const ::= digit {digit} 
    public void integerConst() throws IOException {
        switch (token.tag) {
            case Tag.INT:
                eat(Tag.INT);
                break;
            default:
                erro();
        }
    }

    //rever
    // float_const ::= digit {digit} “.” digit {digit} 
    public void floatConst() throws IOException {
        switch (token.tag) {
            case Tag.FLOAT:
                eat(Tag.FLOAT);
                break;
            default:
                erro();
        }
    }

    //rever
    //literal ::= " {" {caractere} "}" 
    public void literal() throws IOException {
        switch (token.tag) {
            case ('{'):
                eat('{');
                caracter();
                eat('}');
                break;
            default:
                erro();
        }
    }

    //identifier ::= letter |“_” {letter | digit | “_”} 
    public void identifier() throws IOException {
        //DUVIDA
        switch (token.tag) {
            case (Tag.IDF):
                eat(Tag.IDF);
                break;
            default:
                erro();
        }
    }

    // letter ::= [A-Za-z] 
    public void letter() throws IOException {
    }

    //digit ::= [0-9]
    public void digit() throws IOException {
        if (token.tag == Tag.FLOAT) {
            eat(Tag.FLOAT);
        } else if (token.tag == Tag.REAL) {
            eat(Tag.REAL);
        } else if (token.tag == Tag.INT) {
            eat(Tag.INT);
        }
    }

    //caractere ::= um dos 256 caracteres
    public void caracter() throws IOException {
        switch (token.tag) {
            case '{':
            case '\n':
                erro();
            default:
                eat(token.tag);
                break;
        }
    }
}
