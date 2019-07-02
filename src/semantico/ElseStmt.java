/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lexer.*;
/**
 *
 * @author gabyc
 */
public class ElseStmt extends Sintatico {
    
    
    public ElseStmt(Sintatico head) {
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
    	
    		case Tag.END: 
    			{
		            try {
		                eat(Tag.IF);
		            } catch (IOException ex) {
		                Logger.getLogger(ElseStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                
		        break;
    		
    		case Tag.ELSE:
	    		{
		            try {
		                eat(Tag.ELSE);
		            } catch (IOException ex) {
		                Logger.getLogger(ElseStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }   
	    		
		        
	            {
		            try {
		                eat(Tag.END);
		            } catch (IOException ex) {
		                Logger.getLogger(ElseStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                break;
                
            default:
                System.out.println("Erro semântico na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
                System.exit(0);
        }
    }
    
}
