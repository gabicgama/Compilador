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
public class Identifier extends Sintatico {

    public Identifier(Sintatico head) {
        super(head);
    }

    @Override
    public void analise() {

        switch (token.tag) {

            case Tag.IDF:

//                Token tok = tabelaSimbolos.get(token.lexema);

                if (isDecl) {

                    this.tipo = head.tipo;

//                    if (tok != null) {
//
//                        tok = new Token(token.lexema, Tag.ID);
//                        tok.tipo = this.tipo;
//                        tabelaSimbolos.put(tok);
//                        idList.add(tok);
//
//                    }

                } else {

//                    if (!tok.declaracao) {
//
//                        System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Utilização de identificador não definido '" + token.lexema + "'.");
//                        erro();
//                    }

//                    this.tipo = tok.tipo;

                }

                 {
                    try {
                        eat(Tag.IDF);
                    } catch (IOException ex) {
                        Logger.getLogger(Identifier.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            default:
                System.out.println("Erro semântico na linha " + Lexer.line + " próximo ao token '" + token.toString() + "'");
                System.exit(0);

        }
    }

}
