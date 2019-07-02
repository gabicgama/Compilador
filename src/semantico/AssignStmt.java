/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;

import lexer.Lexer;
import lexer.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.rmi.CORBA.Util;

/**
 *
 * @author gabyc
 */
public class AssignStmt extends Sintatico {

    Identifier identifier;
    SimpleExpr simpleExpr;

    public AssignStmt(Sintatico head) {
        super(head);
    }

    @Override
    public void analise() {

        switch (token.tag) {

            case Tag.IDF:

                identifier = new Identifier(this);
                identifier.analise();

                 {
                    try {
                        eat(Tag.ATB);
                    } catch (IOException ex) {
                        Logger.getLogger(AssignStmt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                simpleExpr = new SimpleExpr(this);
                simpleExpr.analise();

                if (identifier.tipo != simpleExpr.tipo) {
                    System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Tipos incompatíveis.");
                    erro();
                }
               

                break;

            default:
                System.out.println("Erro semântico na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
                System.exit(0);

        }
    }
}
