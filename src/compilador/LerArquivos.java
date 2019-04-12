/*
** Classe implementada para ler os arquivos testes do compilador.
** Fica mais fácil manusear assim na classe principal.
 */
package compilador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kvnet
 */
public class LerArquivos {

    Scanner leitura = new Scanner(System.in);
    FileReader arquivo;

    public LerArquivos(String nome) throws IOException {
        try {
            this.arquivo = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arquivo);
            String linha = lerArq.readLine(); // lê a primeira linha

            while (linha != null) {
                System.out.println(linha);

                linha = lerArq.readLine(); // lê da segunda até a última linha
            }

            arquivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LerArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
