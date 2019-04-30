package lexer;

/**
 *
 * @author kvnet
 */
public class Token {

    public final int tag;

    // Construtor da classe
    public Token(int t) {
        tag = t;
    }

    public String toString() {
        return "" + (char) tag;
    }

    //Só para retornar o nome de token pra etapa 1
    public String getNomeToken() {
        if (tag == Tag.AND) {
            return "AND";
        } else if (tag == Tag.APP) {
            return "APP";
        } else if (tag == Tag.ATB) {
            return "ATB";
        } else if (tag == Tag.DO) {
            return "DO";
        } else if (tag == Tag.ELSE) {
            return "ELSE";
        } else if (tag == Tag.END) {
            return "END";
        } else if (tag == Tag.FLOAT_CONST) {
            return "NUMBER";
        } else if (tag == Tag.GE) {
            return "RELOP";
        } else if (tag == Tag.IDF) {
            return "IDF";
        } else if (tag == Tag.IF) {
            return "IF";
        } else if (tag == Tag.INT) {
            return "INT";
        } else if (tag == Tag.INT_CONST) {
            return "NUMBER";
        } else if (tag == Tag.LE) {
            return "RELOP";
        } else if (tag == Tag.LIT) {
            return "LIT";
        } else if (tag == Tag.NE) {
            return "RELOP";
        } else if (tag == Tag.OR) {
            return "RELOP";
        } else if (tag == Tag.READ) {
            return "READ";
        } else if (tag == Tag.REAL) {
            return "REAL";
        } else if (tag == Tag.REPEAT) {
            return "REPEAT";
        } else if (tag == Tag.START) {
            return "START";
        } else if (tag == Tag.STOP) {
            return "STOP";
        } else if (tag == Tag.THEN) {
            return "THEN";
        } else if (tag == Tag.UNTIL) {
            return "UNTIL";
        } else if (tag == Tag.WHILE) {
            return "WHILE";
        } else if (tag == Tag.WRITE) {
            return "WRITE";

        } else {
            return toString();
        }

    }
}
