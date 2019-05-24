package parser;

import inter.Stmt;
import java.io.IOException;
import lexer.Lexer;
import lexer.Token;

/**
 *
 * @author kvnet
 */

/*
** A classe Parser deve ter um procedimento p/ cada não terminal.
** É necessário retirar a recursão a esquerda!!
*/
public class Parser {
    private Lexer lex; // Analisador lexico para este analisador sintático
    private Token look; // lookahead tagem
    
    public Parser (Lexer l) throws IOException{
        lex = l;
        move();
    }

    void move() throws IOException{
        look = lex.scan();
    }
    
    void error(String s){
        throw new Error("near line " + lex.line + " +s");
    }
    
    void match(int t) throws IOException{
      if (look.tag == t) move();  
      else error("syntax error");
    }
    
    // A analise começa com a chamada de program()
//    public void program() throws Exception {
//        Stmt s = block();
//        int begin = s.newlabel();
//        int after = s.newlabel();
//        s.emitlabel(begin);
//        s.gen(begin,after);
//        s.emitlabel(after);
//        
//    }
    
//    Stmt block() throws IOException{
//        match('{');
//        Stmt s = stmts();
//        
//    }
    
   
}
