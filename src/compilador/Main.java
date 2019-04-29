
package compilador;

import java.io.*;
import java.util.*;
import lexer.*;

/**
 *
 * @author gabyc
 * @author kiqnetto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        File arq = new File("");
        System.out.println("Escolha: Teste 1 | Teste 2 | Teste 3 | Teste 4 | Teste 5 | Teste 6 \n");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 1) {
            arq = new File("Testes/teste1.txt");
        } else if (n == 2) {
            arq = new File("Testes/teste2.txt");
        } else if (n == 3) {
            arq = new File("Testes/teste3.txt");
        } else if (n == 4) {
            arq = new File("Testes/teste4.txt");
        } else if (n == 5) {
            arq = new File("Testes/teste5.txt");
        } else if (n == 6) {
            arq = new File("Testes/teste6.txt");
        } else {
            System.out.println("Entrada inválida!");
        }
        
        Lexer lexer= new Lexer(arq);

    }

}
