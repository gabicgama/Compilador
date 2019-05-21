/*
 * Node possui duas subclasses: Expr para nós de expressão e Stmt para nós de comando.
 */
package inter;

import lexer.Lexer;

/**
 *
 * @author kvnet
 */
public class Node {
    int lexline = 0;
    Node(){
        lexline = Lexer.line;
    }
    void error(String s){
        throw new Error ("near line " + lexline + " +s");
    }    
    
    static int labels = 0;
    public int newlabel() {
        return ++labels;
    }
    public void emitlabel (int i){
        System.out.println("L" + i + ": ");
    }
    public void emit(String s){
        System.out.println("\t" + s);
    }
}
