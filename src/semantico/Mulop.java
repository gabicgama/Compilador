/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;

import lexer.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabyc
 */
public class Mulop extends Sintatico {

    public Mulop(Sintatico head) {
        super(head);
    }

    @Override
    public void analise() {

        switch (token.tag) {

            case '*': {

                try {
                    eat('*');
                } catch (IOException ex) {
                    Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = head.tipo;
            break;

            case '/': {

                try {
                    eat('/');
                } catch (IOException ex) {
                    Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = head.tipo;
            break;

            case Tag.AND: {

                try {
                    eat(Tag.AND);
                } catch (IOException ex) {
                    Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, ex);
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
