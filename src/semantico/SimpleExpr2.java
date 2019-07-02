/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;

import lexer.*;

/**
 *
 * @author gabyc
 */
public class SimpleExpr2 extends Sintatico {

    Addop addop;
    Term term;
    SimpleExpr2 simpleExpr2;

    public SimpleExpr2(Sintatico head) {

        super(head);
        this.tipo = head.tipo;
    }

    @Override
    public void analise() {

        if (token.tag == Tag.OR || token.tag == '+' || token.tag == '-') {

            addop = new Addop(this);
            addop.analise();
            this.tipo = addop.tipo;
            term = new Term(this);
            term.analise();

            if (!Util.isNumeric(addop.tipo) || !Util.isNumeric(term.tipo)) {

                if (!addop.tipo.equals(term.tipo)) {

                    System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Operador incompatível com o tipo do operando.");
                    erro();

                }
            }

            this.tipo = Util.getNumericType(addop.tipo, term.tipo);
            simpleExpr2 = new SimpleExpr2(this);
            simpleExpr2.analise();

            if (!simpleExpr2.tipo.equals("void")) {

                if (!Util.isNumeric(addop.tipo) || !Util.isNumeric(simpleExpr2.tipo)) {
                    System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Operador incompatível com o tipo do operando.");

                    System.exit(0);

                }

            }

            if (Util.isNumeric(addop.tipo)) {
                this.tipo = Util.getNumericType(term.tipo, simpleExpr2.tipo);
            } else {
                this.tipo = addop.tipo;
            }

        }
    }

}
