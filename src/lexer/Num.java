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
 * @author kvnet
 * @author gabycgama
 */
public class Num extends Token {

    public final int value;

    // Construtor
    public Num(int v) {
        super(Tag.INT_CONST);
        value = v;
    }
    
    public String toString(){
        return "" + value; 
    }
    
    
}
