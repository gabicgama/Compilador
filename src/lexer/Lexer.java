package lexer;

import java.io.*;
import java.util.*;

/**
 *
 * @author kvnet
 * @author gabyc
 */
public class Lexer {

    private FileReader file;
    public static int line = 1;
    private char ch = ' ';//peek = proximo caracter
    Hashtable words = new Hashtable();

    void reserve(Word w) {
        words.put(w.lexeme, w);
    }

    public Lexer(File arquivo) throws FileNotFoundException {

        //Arquivo de entrada
        try {
            file = new FileReader(arquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não Encontrado");
            throw e;
        }

        //Palavras-chave
        reserve(new Word("if", Tag.IF));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("int", Tag.INT));
        //Demais palavras reservadas
        reserve(Word.app);
        reserve(Word.start);
        reserve(Word.stop);
        reserve(Word.real);
        reserve(Word.then);
        reserve(Word.end);
        reserve(Word.repeat);
        reserve(Word.until);
        reserve(Word.read);
        reserve(Word.write);
    }

    void readch() throws IOException {
        ch = (char) file.read();
    }

    boolean readch(char c) throws IOException {
        readch();
        if (ch != c) {
            return false;
        }
        ch = ' ';
        return true;
    }

    public Token scan() throws IOException {
        for (;; readch()) {
            if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\b') {
                continue;
            } else if (ch == '\n') {
                line++;
            } else if (ch == '%') { //comentários começam com % e terminam até \n
                while (ch != '\n') {
                    readch();
                }
                continue;
            } else {
                break;
            }
        }

        switch (ch) {
            case '&':
                if (readch('&')) {
                    return Word.and;
                } else {
                    return new Token('&');
                }
            case '|':
                if (readch('|')) {
                    return Word.or;
                } else {
                    return new Token('|');
                }
            case ':':
                if (readch('=')) {
                    return Word.atb;
                } else {
                    return new Token(':');
                }
            case '!':
                if (readch('=')) {
                    return Word.ne;
                } else {
                    return new Token('!');
                }
            case '<':
                if (readch('=')) {
                    return Word.le;
                } else {
                    return new Token('<');
                }
            case '>':
                if (readch('=')) {
                    return Word.ge;
                } else {
                    return new Token('>');
                }
            case '=':
                ch = ' ';
                return new Token('=');
            case '*':
                ch = ' ';
                return new Token('*');
            case '+':
                ch = ' ';
                return new Token('+');
            case '-':
                ch = ' ';
                return new Token('-');
            case '(':
                ch = ' ';
                return new Token('(');
            case ')':
                ch = ' ';
                return new Token(')');
            case ',':
                ch = ' ';
                return new Token(',');
            case ';':
                ch = ' ';
                return new Token(';');
        }
        //Reconhecedor de números inteiros ou decimais
        if (Character.isDigit(ch)) {
            int v = 0;
            do {
                v = 10 * v + Character.digit(ch, 10);
                readch();
            } while (Character.isDigit(ch));
            if (ch != '.') {
                return new Num(v);//se não ler '.' é inteiro
            }
            float x = v;
            float d = 10;
            for (;;) {
                readch();
                if (!Character.isDigit(ch)) {
                    break;//se for diferente de número sai do bloco
                }
                x = x + Character.digit(ch, 10) / d;
                d = d * 10;
            }
            return new NumFloat(x);
        }
        //Reconhecedor de identificadores 
        //OBS Deve reconhecer idf que começam com '_'
        if (Character.isLetter(ch) || ch == '_') {
            StringBuffer b = new StringBuffer();
            do {
                b.append(ch);
                readch();
            } while (Character.isLetterOrDigit(ch) || ch == '_');
            String s = b.toString();
            s.toLowerCase();//converte toda a string pra minúsculo já que a gramatica não é case sensitive
            Word w = (Word) words.get(s);
            if (w != null) {//verifica se w já está na tabela
                return w;
            }

            w = new Word(s, Tag.IDF);
            words.put(s, w);
            //Exibindo símbolos inseridos na tabela de símbolos
            System.out.println("=>Inserindo:" + "'" + w.toString() + "' na tabela.");
            return w;
        }
        //Reconhece literais do tipo "{as1344sda%$%$}"
        if (ch == '{') {
            StringBuffer b = new StringBuffer();
            do {
                b.append(ch);
                readch();
                if (ch == '\n') {
                    System.out.println("** Erro na linha " + line + ".");
                    break;
                }
            } while (ch != '}');
            if (ch == '}') {
                b.append(ch);
                ch = ' ';
            }
            String s = b.toString();
//            Word w = (Word) words.get(s);
//            if (w != null) {
//                return w;
//            }
            Word w = new Word(s, Tag.LIT);
            //acho que literal não entra na tabela de simbolos
            //words.put(s, w);
            return w;
        }

        if (ch != '+' && ch != '-' && ch != '=' && ch != '<' && ch != '>' && ch != '&' && ch != '|' && ch != '{' && ch != '}'
                && ch != ')' && ch != ')' && ch != ',' && ch != '.' && ch != ';' && ch != ':' && ch != ' ' && ch != '\t' && ch != '\n' && ch != '\r' && ch != '\b'
                && ch != '/' && ch != '*' && ch != 65535) {
            System.out.println("** Erro na linha " + (line - 1) + ".");
        }

        Token tok = new Token(this.ch);
        this.ch = ' ';
        return tok;

    }
}
