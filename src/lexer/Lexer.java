/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;
import java.io.*; 
import java.util.*;

/**
 *
 * @author kvnet
 * @author gabyc
 */
public class Lexer {   
    
    public static int line = 1;
    char peek =' ';//peek = proximo
    Hashtable words = new Hashtable();
    
    void reserve(Word w){
        words.put(w.lexeme, w);
    }
    
    public Lexer(){
        //Palavras-chave
        reserve(new Word("if", Tag.IF));    reserve(new Word("else", Tag.ELSE));
        reserve(new Word("do", Tag.DO));    reserve(new Word("while", Tag.WHILE));
        //Demais palavras reservadas
        reserve(Word.app);      reserve(Word.start);    reserve(Word.stop);
        reserve(Word.integer);  reserve(Word.real);     reserve(Word.then);
        reserve(Word.end);      reserve(Word.repeat);   reserve(Word.until);
        reserve(Word.read);     reserve(Word.write);   
    }
 
}
