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
public class SimpleExpr extends Sintatico {

    Term term;
    SimpleExpr2 simpleExpr2;

    public SimpleExpr(Sintatico head) {
        super(head);
    }

    @Override
    public void analise() {

        switch (token.tag) {

            case Tag.IDF:
            case Tag.INT:
            case Tag.REAL:
            case Tag.FLOAT:
            case '(':
            case '{':
            case '!':
            case '-':
                term = new Term(this);
                term.analise();
                this.tipo = term.tipo;
                simpleExpr2 = new SimpleExpr2(this);
                simpleExpr2.analise();

                if (!simpleExpr2.tipo.equals("void")) {

                    if (!Util.isNumeric(term.tipo) || !Util.isNumeric(simpleExpr2.tipo)) {

                        if (!term.tipo.equals(simpleExpr2.tipo)) {
                            System.out.println("Erro semantico na linha " + Lexer.line + ":\n" + "Operador incompatível com tipo do operando.");
                            erro();
                        }
                    }
                }

                if (Util.isNumeric(term.tipo) && Util.isNumeric(simpleExpr2.tipo)) {
                    this.tipo = Util.getNumericType(term.tipo, simpleExpr2.tipo);
                } else {
                    this.tipo = simpleExpr2.tipo;
                }
                
                break;

            default:
                System.out.println("Erro semântico na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
                System.exit(0);
        }
    }

}
