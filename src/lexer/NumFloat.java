
package lexer;

import lexer.Tag;
import lexer.Token;

/**
 *
 * @author Aluno
 */
public class NumFloat extends Token {
    public final float value;

    // Construtor
    public NumFloat(float v) {
        super(Tag.FLOAT);
        value = v;
    }
    
    public String toString(){
        return "" + value; 
    }
}
