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
public class Constant extends Sintatico {

    public Constant(Sintatico head) {
        super(head);
    }

    @Override
    public void analise() {

        switch (token.tag) {
            case Tag.INT_CONST: {

                try {
                    eat(Tag.INT_CONST);
                } catch (IOException ex) {
                    Logger.getLogger(Constant.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = "inteiro";
            break;

            case Tag.LIT: {

                try {
                    eat(Tag.LIT);
                } catch (IOException ex) {
                    Logger.getLogger(Constant.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = "literal";
            break;

            default:
                System.out.println("Erro semântico na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
                System.exit(0);
        }
    }

}
