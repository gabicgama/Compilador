
package lexer;

/**
 *
 * @author kvnet
 */
public class Token {
    public final int tag;
    
    // Construtor da classe
    public Token(int t){
        tag = t;
    }
    
    public String toString(){
        return "" + (char)tag;
    }
}
