/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastropoo;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.PessoaFisicaRepo;
import model.PessoaJuridicaRepo;
import java.io.IOException;

/**
 *
 * @author Nanda
 */
public class CadastroPOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();

        PessoaFisica pessoa1 = new PessoaFisica(1, "Ana", "11111111111", 25);
        PessoaFisica pessoa2 = new PessoaFisica(2, "Carlos", "22222222222", 52);
        repo1.inserir(pessoa1);
        repo1.inserir(pessoa2);

        try {
            repo1.persistir("pessoas_fisicas.dat");
            System.out.println("Pessoas Fisicas Armazenadas.");

            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();

            repo2.recuperar("pessoas_fisicas.dat");

            System.out.println("Pessoas Fisicas Recuperadas.");
            for (PessoaFisica pessoa : repo2.obterTodos()) {
                pessoa.exibir();                
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();

        PessoaJuridica pessoaJuridica1 = new PessoaJuridica(3, "XPTO Sales", "33333333333333");
        PessoaJuridica pessoaJuridica2 = new PessoaJuridica(4, "XPTO Solutions", "44444444444444");
        repo3.inserir(pessoaJuridica1);
        repo3.inserir(pessoaJuridica2);

        try {
            repo3.persistir("pessoas_juridicas.dat");
            System.out.println("Pessoas Juridicas Armazenadas.");

            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();

            repo4.recuperar("pessoas_juridicas.dat");

            System.out.println("Pessoas Juridicas Recuperadas.");
            for (PessoaJuridica pessoaJuridica : repo4.obterTodos()) {
                pessoaJuridica.exibir();                
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
