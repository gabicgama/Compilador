/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

import lexer.Tag;
import lexer.Token;

/**
 *
 * @author Aluno
 */
public class Real extends Token {
    public final float value;

    // Construtor
    public Real(float v) {
        super(Tag.FLOAT_CONST);
        value = v;
    }
    
    public String toString(){
        return "" + value; 
    }
}
