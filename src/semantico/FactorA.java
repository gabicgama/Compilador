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
public class FactorA extends Sintatico {

    Factor factor;

    public FactorA(Sintatico head) {
        super(head);
    }

    @Override
    public void analise() {

        switch (token.tag) {

            case Tag.IDF:
            case Tag.INT_CONST:
            case Tag.LIT:
            case '(':
                factor = new Factor(this);
                factor.analise();
                this.tipo = factor.tipo;
                break;

            case '!': {
                try {
                    eat('!');
                } catch (IOException ex) {
                    Logger.getLogger(FactorA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            factor = new Factor(this);
            factor.analise();
            if (!factor.tipo.equals("bool")) {
                System.out.println("Erro Semântico na linha " + Lexer.line + ":\n" + "Operador booleano esperado.");
                erro();
            }
            this.tipo = factor.tipo;
            break;

            case '-': {
                try {
                    eat('-');
                } catch (IOException ex) {
                    Logger.getLogger(FactorA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            factor = new Factor(this);
            factor.analise();
            if ((!factor.tipo.equals("inteiro")) && (!factor.tipo.equals("literal"))) {
                System.out.println("Erro Semântico na linha " + Lexer.line + ":\n" + "Operador númerico esperado.");
                erro();
            }
            this.tipo = factor.tipo;
            break;

            default:
                System.out.println("Erro semântico na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
                System.exit(0);
        }
    }

}
