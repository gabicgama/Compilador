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
public class Type extends Sintatico {

    public Type(Sintatico head) {
        super(head);
    }

    @Override
    public void analise() {

        switch (token.tag) {

            case Tag.INT: {
                try {
                    eat(Tag.INT);
                } catch (IOException ex) {
                    Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = "inteiro";
            break;

            case Tag.REAL: {
                try {
                    eat(Tag.REAL);
                } catch (IOException ex) {
                    Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = "real";
            break;

            default:
                System.out.println("Erro semântico na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
                System.exit(0);
        }
    }
}
