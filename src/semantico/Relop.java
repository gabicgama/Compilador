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
public class Relop extends Sintatico {

    public Relop(Sintatico head) {
        super(head);
    }

    @Override
    public void analise() {

        switch (token.tag) {
            case '=': {

                try {
                    eat('=');
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = "bool";
            break;

            case '>': {

                try {
                    eat('>');
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = "bool";
            break;

            case Tag.GE: {

                try {
                    eat(Tag.GE);
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = "bool";
            break;

            case '<': {

                try {
                    eat('<');
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = "bool";
            break;

            case Tag.LE: {

                try {
                    eat(Tag.LE);
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = "bool";
            break;

            case Tag.NE: {

                try {
                    eat(Tag.NE);
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
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
