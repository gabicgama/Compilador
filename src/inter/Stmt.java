/*
 * Cada construção de comando é implementada por uma subclasse de Stmt.
 */
package inter;

/**
 *
 * @author kvnet
 */
public class Stmt extends Node{
    public Stmt (){
        // Não faz nada porque o trabalho é feito nas subclasses. 
    }
    public static Stmt Null = new Stmt();
    
    public void gen(int b, int a){
        // Chamado com rótulos begin e after
    }
    
    int after = 0; // guarda rótulo after
    
    public static Stmt Enclosing = Stmt.Null; // Usado para comandos break
}
