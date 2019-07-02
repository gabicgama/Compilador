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
public class Factor extends Sintatico {

    Identifier identifier;
    Constant constant;
    Expression expression;

    public Factor(Sintatico head) {
        super(head);
    }

    @Override
    public void analise() {

        switch (token.tag) {
            case Tag.IDF:
                identifier = new Identifier(this);
                identifier.analise();
                this.tipo = identifier.tipo;
                break;

            case Tag.INT_CONST:
            case Tag.LIT:
                constant = new Constant(this);
                constant.analise();
                this.tipo = constant.tipo;
                break;

            case '(': {

                try {
                    eat('(');
                } catch (IOException ex) {
                    Logger.getLogger(Factor.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            expression = new Expression(this);
            expression.analise();
            this.tipo = expression.tipo;

             {
                try {
                    eat(')');
                } catch (IOException ex) {
                    Logger.getLogger(Factor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            default:
                System.out.println("Erro semântico na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
                System.exit(0);
        }
    }

}
