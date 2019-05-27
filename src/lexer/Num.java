
package lexer;

import lexer.Tag;
import lexer.Token;

/**
 *
 * @author kvnet
 * @author gabycgama
 */
public class Num extends Token {

    public final int value;

    // Construtor
    public Num(int v) {
        super(Tag.INT);
        value = v;
    }
    
    public String toString(){
        return "" + value; 
    }
    
    
}
