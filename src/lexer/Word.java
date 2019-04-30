
package lexer;

import lexer.Token;

/**
 *
 * @author kvnet
 * @author gabyc
 */
public class Word extends Token {

    public String lexeme = "";

    // Construtor
    public Word(String s, int tag) {
        super(tag);
        lexeme = s;
    }

    public String toString() {
        return lexeme;
    }

    public static final Word 
            // Operadores e pontuações
            and = new Word("&&", Tag.AND), or = new Word("||", Tag.OR), 
            atb = new Word(":=", Tag.ATB), ge = new Word(">=", Tag.GE), 
            le = new Word("<=", Tag.LE),   ne = new Word("!=", Tag.NE),
            
            // Palavras reservadas
            app = new Word("app", Tag.APP),         start = new Word("start", Tag.START), 
            stop = new Word("stop", Tag.STOP),      
            real = new Word("real", Tag.REAL),      end = new Word("end", Tag.END),
            then = new Word("then",Tag.THEN),       repeat = new Word("repeat",Tag.REPEAT),
            until = new Word("until", Tag.UNTIL),   read = new Word("read", Tag.READ), 
            write = new Word("write", Tag.WRITE);
}
