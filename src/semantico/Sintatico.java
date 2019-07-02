package semantico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lexer.*;

public abstract class Sintatico {

    public Sintatico head;
    public String tipo;
    public boolean declaracao;
    public static Lexer lexico;
    public static Token token;
//    public static TabelaSimbolos tabelaSimbolos = TabelaSimbolos.getInstance();
    public static List<Token> idList = new ArrayList<Token>();
    public static boolean isDecl = false;

    protected Sintatico(Sintatico head) {
        this.head = head;
        this.tipo = "void";
        this.declaracao = false;
    }

    protected void erro() {
        System.exit(0);
    }

    protected void eat(int tag) throws IOException {

        if (token.tag == tag) {
            System.out.println("eat " + token);
            token = lexico.scan();

        } else {
            System.out.println("Erro  na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
            System.exit(0);
        }
    }

    public abstract void analise();

}
