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
public class Term extends Sintatico {

    FactorA factorA;
    Term2 term2;

    public Term(Sintatico head) {
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
                factorA = new FactorA(this);
                factorA.analise();
                this.tipo = factorA.tipo;
                term2 = new Term2(this);
                term2.analise();

                if (!term2.tipo.equals("void")) {

                    if (!Util.isNumeric(factorA.tipo) || !Util.isNumeric(term2.tipo)) {

                        if (!factorA.tipo.equals(term2.tipo)) {

                            System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Operador incompaível com o tipo do operando.");
                            erro();
                        }
                    }
                }

                if (Util.isNumeric(term2.tipo) && Util.isNumeric(factorA.tipo)) {
                    this.tipo = Util.getNumericType(factorA.tipo, term2.tipo);
                } else {
                    this.tipo = term2.tipo;
                }

                break;

            default:
                System.out.println("Erro semântico na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
                System.exit(0);
        }
    }

}
