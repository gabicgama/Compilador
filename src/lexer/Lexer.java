/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

import java.util.Hashtable;

/**
 *
 * @author gabyc
 * @author kvnet
 */
public class Lexer {   
    public static int line = 1;
    //char peek = '';
    Hashtable words = new Hashtable();
    void reserve(Word w){
        words.put(w.lexeme, w);
    }
    
    // Reserva palavras chaves selecionadas e objetos definidos em outras partes
    public Lexer(){
        
        // Palavras chaves selecionadas
        reserve(new Word ("if", Tag.IF));
        reserve(new Word ("else", Tag.ELSE));
        reserve(new Word ("then", Tag.THEN));
        reserve(new Word ("end", Tag.END));
        reserve(new Word ("repeat", Tag.REPEAT));
        reserve(new Word ("until", Tag.UNTIL));
        reserve(new Word ("while", Tag.WHILE));
        reserve(new Word ("do", Tag.DO));
        reserve(new Word ("read", Tag.READ));
        reserve(new Word ("write", Tag.WRITE));
        reserve(new Word ("app", Tag.APP));
        reserve(new Word ("start", Tag.START));
        reserve(new Word ("stop", Tag.STOP));
        reserve(new Word ("integer", Tag.INTEGER));
        
        // Objetos definidos em outras classes. Entra aqui: Types e objetos criados na classe Word
        // TODO
        
        
    }
    
    /*
    ** TODO
    public Token scan(){
       
    }
    */
}
