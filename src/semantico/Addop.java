/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import lexer.Lexer;
import lexer.Tag;
import static semantico.Sintatico.token;
/**
 *
 * @author gabyc
 */
public class Addop extends Sintatico {
    
    
    
    public Addop(Sintatico head){
        super(head);
    }
    
    @Override
    public void analise()  {
    	
        switch (token.tag) {
	    
        	case '+': {
        		try {
        			eat('+');
        		} catch (IOException ex) {
        			Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
        		}
        	}
            
            this.tipo = head.tipo;
            break;
	    
        	case '-': {
	            try {
	                eat('-');
	            } catch (IOException ex) {
	                Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            this.tipo = head.tipo;
            break;
            
		    case Tag.OR: {
	            try {
	                eat(Tag.OR);
	            } catch (IOException ex) {
	                Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            this.tipo = "bool";
            break;
		    
		    default:
                        System.out.println("Erro semântico na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
                        System.exit(0);
		}
    }
    
}
