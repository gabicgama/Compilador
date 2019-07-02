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
public class Term2 extends Sintatico {

    Mulop mulop;
    FactorA factorA;
    Term2 term2;

    public Term2(Sintatico head) {

        super(head);
        this.tipo = head.tipo;

    }

    @Override
    public void analise() {

        if (token.tag == Tag.AND || token.tag == '*' || token.tag == '/') {

            mulop = new Mulop(this);
            mulop.analise();
            this.tipo = mulop.tipo;

            factorA = new FactorA(this);
            factorA.analise();

            if (!Util.isNumeric(mulop.tipo) || !Util.isNumeric(factorA.tipo)) {

                if (!mulop.tipo.equals(factorA.tipo)) {

                    System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Operador incompatível com o tipo do operando.");
                    erro();
                }
            }

            term2 = new Term2(this);
            term2.analise();

            if (!term2.tipo.equals("void")) {

                if (!mulop.tipo.equals(term2.tipo)) {
                    System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Operador incompaível com o tipo do operando.");
                    erro();
                }

            }

            if (Util.isNumeric(mulop.tipo)) {
                this.tipo = Util.getNumericType(factorA.tipo, term2.tipo);
            } else {
                this.tipo = mulop.tipo;
            }
        }
    }

}
