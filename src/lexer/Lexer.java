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
    char peek =' ';//peek = proximo caracter
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
    
    void readch() throws IOException {
        peek = (char) System.in.read();
    }
    
    boolean readch(char c) throws IOException{
        readch();
        if(peek != c) return false;
        peek = ' ';
        return true;
    }
    
    public Token scan() throws IOException{
        for( ; ; readch()){
            if(peek == ' ' || peek == '\t') continue;
            else if(peek == '\n') line += 1;
            else break;
        }
        
        switch(peek){
            case '&':
                if(readch('&')) return Word.and; else return new Token('&');
            case '|':
                if(readch('|')) return Word.or; else return new Token('|');
            case ':':
                if(readch('=')) return Word.atb; else return new Token(':');
            case '!':
                if(readch('=')) return Word.ne; else return new Token('!');
            case '<':
                if(readch('=')) return Word.le; else return new Token('<');
            case '>':
                if(readch('=')) return Word.ge; else return new Token('>');
        }
        //Reconhecedor de números inteiros ou decimais
        if(Character.isDigit(peek)){
            int v = 0;
            do{
                v = 10*v + Character.digit(peek,10); 
                readch();
            }while(Character.isDigit(peek));     
            if(peek != '.') return new Num(v);//se não ler '.' é inteiro
            
            float x = v; float d = 10;
            for(;;){
                readch();
                if(!Character.isDigit(peek)) break;//se ler letra sai do bloco
                x  = x + Character.digit(peek, 10) / d;
                d = d*10;
            }
            return new Real(x);
        }
        //Reconhecedor de identificadores 
        
        //OBS Modificar para reconhecer identificadores que começam com '_'
        //Character.isUnicodeIdentifierPart(char) ou Character.isJavaIdentifierPart(char) identifica '_'
        if(Character.isLetter(peek)){
            StringBuffer b = new StringBuffer();
            do{
                b.append(peek);
                readch();
            }while(Character.isLetterOrDigit(peek));
            String s = b.toString();
            Word w = (Word)words.get(s);
            if(w != null) return w;
            w =  new Word(s, Tag.IDF);
            words.put(s, w);
            return w;
        }
        
        
        //Implementar trecho de código que reconhece literais do tipo "{as1344sda%$%$}"
        //Character.isValidCodePoint(char) reconhecer todos os caracteres
        
        
        Token tok = new Token(peek);
        peek = ' ';
        return tok;
        
    }
}
