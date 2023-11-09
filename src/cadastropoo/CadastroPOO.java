/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastropoo;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.PessoaFisicaRepo;
import model.PessoaJuridicaRepo;
import java.util.Scanner;
import java.util.List;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author Nanda
 */
public class CadastroPOO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int escolha;

        PessoaFisicaRepo repoPessoasFisicas = new PessoaFisicaRepo("pessoasFisicas.dat");
        PessoaJuridicaRepo repoPessoasJuridicas = new PessoaJuridicaRepo("pessoasJuridicas.dat");

        do {
            System.out.println("Menu:");
            System.out.println("1. Incluir");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Buscar pelo ID");
            System.out.println("5. Exibir todos");
            System.out.println("6. Salvar dados");
            System.out.println("7. Recuperar dados");
            System.out.println("0. Finalizar programa");
            System.out.print("Escolha uma opcao: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    System.out.println("Escolha o tipo (F - Pessoa Fisica, J - Pessoa Juridica): ");
                    String tipo = scanner.nextLine().toUpperCase();

                    if (tipo.equals("F")) {
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine(); 

                        PessoaFisica pessoaFisica = new PessoaFisica(id, nome, cpf, idade);
                        repoPessoasFisicas.inserir(pessoaFisica);
                        repoPessoasFisicas.persistir();
                    } else if (tipo.equals("J")) {
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();

                        PessoaJuridica pessoaJuridica = new PessoaJuridica(id, nome, cnpj);
                        repoPessoasJuridicas.inserir(pessoaJuridica);
                        repoPessoasJuridicas.persistir();
                    } else {
                        System.out.println("Opcao invalida. Use F para Pessoa Fisica ou J para Pessoa Juridica.");
                    }
                    break;
                case 2:
                    System.out.println("Escolha o tipo (F - Pessoa Fisica, J - Pessoa Juridica): ");
                    String tipoAlterar = scanner.nextLine().toUpperCase();

                    if (tipoAlterar.equals("F")) {
                        System.out.print("ID da Pessoa Fisica a ser alterada: ");
                        int idAlterar = scanner.nextInt();
                        scanner.nextLine();

                        PessoaFisica pessoaExistente = repoPessoasFisicas.obter(idAlterar);

                        if (pessoaExistente != null) {
                            System.out.println("Dados atuais:");
                            pessoaExistente.exibir();

                            System.out.print("Novo nome: ");
                            String novoNome = scanner.nextLine();
                            System.out.print("Novo CPF: ");
                            String novoCpf = scanner.nextLine();
                            System.out.print("Nova idade: ");
                            int novaIdade = scanner.nextInt();
                            scanner.nextLine();

                            pessoaExistente.setNome(novoNome);
                            pessoaExistente.setCpf(novoCpf);
                            pessoaExistente.setIdade(novaIdade);

                            repoPessoasFisicas.persistir();
                            System.out.println("Dados alterados com sucesso.");
                        } else {
                            System.out.println("Pessoa Fisica nao encontrada.");
                        }
                    } else if (tipoAlterar.equals("J")) {
                        System.out.print("ID da Pessoa Juridica a ser alterada: ");
                        int idAlterar = scanner.nextInt();
                        scanner.nextLine();

                        PessoaJuridica empresaExistente = repoPessoasJuridicas.obter(idAlterar);

                        if (empresaExistente != null) {
                            System.out.println("Dados atuais:");
                            empresaExistente.exibir();

                            System.out.print("Novo nome: ");
                            String novoNome = scanner.nextLine();
                            System.out.print("Novo CNPJ: ");
                            String novoCnpj = scanner.nextLine();

                            empresaExistente.setNome(novoNome);
                            empresaExistente.setCnpj(novoCnpj);

                            repoPessoasJuridicas.persistir();
                            System.out.println("Dados alterados com sucesso.");
                        } else {
                            System.out.println("Pessoa Juridica nao encontrada.");
                        }
                    } else {
                        System.out.println("Opcao invalida. Use F para Pessoa Fisica ou J para Pessoa Juridica.");
                    }
                    break;
                    
                    case 3:
                    System.out.println("Escolha o tipo (F - Pessoa Fisica, J - Pessoa Juridica): ");
                    String tipoExcluir = scanner.nextLine().toUpperCase();

                    if (tipoExcluir.equals("F")) {
                        System.out.print("ID da Pessoa Fisica a ser excluida: ");
                        int idExcluir = scanner.nextInt();
                        scanner.nextLine(); 

                        repoPessoasFisicas.excluir(idExcluir);
                        repoPessoasFisicas.persistir();
                        System.out.println("Pessoa Fisica removida com sucesso.");
                    } else if (tipoExcluir.equals("J")) {
                        System.out.print("ID da Pessoa Juridica a ser excluida: ");
                        int idExcluir = scanner.nextInt();
                        scanner.nextLine(); 

                        repoPessoasJuridicas.excluir(idExcluir);
                        repoPessoasJuridicas.persistir();
                        System.out.println("Pessoa Juridica removida com sucesso.");
                    } else {
                        System.out.println("Opcao invalida. Use F para Pessoa Fisica ou J para Pessoa Juridica.");
                    }
                    break;
                    
                    case 4:
                    System.out.println("Escolha o tipo (F - Pessoa Fisica, J - Pessoa Juridica): ");
                    String tipoExibir = scanner.nextLine().toUpperCase();

                    if (tipoExibir.equals("F")) {
                        System.out.print("ID da Pessoa Fisica a ser exibida: ");
                        int idExibir = scanner.nextInt();
                        scanner.nextLine();

                        PessoaFisica pessoaFisica = repoPessoasFisicas.obter(idExibir);

                        if (pessoaFisica != null) {
                            System.out.println("Dados da Pessoa Fisica com ID " + idExibir + ":");
                            pessoaFisica.exibir();
                        } else {
                            System.out.println("Pessoa Fisica nao encontrada.");
                        }
                    } else if (tipoExibir.equals("J")) {
                        System.out.print("ID da Pessoa Juridica a ser exibida: ");
                        int idExibir = scanner.nextInt();
                        scanner.nextLine();

                        PessoaJuridica pessoaJuridica = repoPessoasJuridicas.obter(idExibir);

                        if (pessoaJuridica != null) {
                            System.out.println("Dados da Pessoa Juridica com ID " + idExibir + ":");
                            pessoaJuridica.exibir();
                        } else {
                            System.out.println("Pessoa Juridica nao encontrada.");
                        }
                    } else {
                        System.out.println("Opcao invalida. Use F para Pessoa Fisica ou J para Pessoa Juridica.");
                    }
                    break;
                    
                    case 5:
                    System.out.println("Escolha o tipo (F - Pessoa Fisica, J - Pessoa Juridica): ");
                    String tipoExibirTodos = scanner.nextLine().toUpperCase();

                    if (tipoExibirTodos.equals("F")) {
                        List<PessoaFisica> pessoasFisicas = repoPessoasFisicas.obterTodos();

                        if (!pessoasFisicas.isEmpty()) {
                            System.out.println("Pessoas Fisicas cadastradas:");
                            for (PessoaFisica pessoaFisica : pessoasFisicas) {
                                pessoaFisica.exibir();
                            }
                        } else {
                            System.out.println("Nao ha Pessoas Fisicas cadastradas.");
                        }
                    } else if (tipoExibirTodos.equals("J")) {
                        List<PessoaJuridica> pessoasJuridicas = repoPessoasJuridicas.obterTodos();

                        if (!pessoasJuridicas.isEmpty()) {
                            System.out.println("Pessoas Juridicas cadastradas:");
                            for (PessoaJuridica pessoaJuridica : pessoasJuridicas) {
                                pessoaJuridica.exibir();
                            }
                        } else {
                            System.out.println("Nao ha Pessoas Juridicas cadastradas.");
                        }
                    } else {
                        System.out.println("Opcao invalida. Use F para Pessoa Fisica ou J para Pessoa Juridica.");
                    }
                    break;
                    
                    case 6:                    
                    System.out.println("Escolha o tipo (F - Pessoa Fisica, J - Pessoa Juridica): ");
                    String tipoSalvar = scanner.nextLine().toUpperCase();

                    if (tipoSalvar.equals("F")) {
                        System.out.print("Digite o nome do arquivo a ser salvo: ");
                        String prefixoFisica = scanner.nextLine();

                        salvarDados(repoPessoasFisicas, prefixoFisica + ".fisica.bin");
                    } else if (tipoSalvar.equals("J")) {
                        System.out.print("Digite o nome do arquivo a ser salvo: ");
                        String prefixoJuridica = scanner.nextLine();

                        salvarDados(repoPessoasJuridicas, prefixoJuridica + ".juridica.bin");
                    } else {
                        System.out.println("Opcao invalida. Use F para Pessoa Fisica ou J para Pessoa Juridica.");
                    }
                    break;
                    
                    case 7:                    
                    System.out.println("Escolha o tipo (F - Pessoa Fisica, J - Pessoa Juridica): ");
                    String tipoRecuperar = scanner.nextLine().toUpperCase();

                    if (tipoRecuperar.equals("F")) {
                        System.out.print("Digite o nome do arquivo a ser recuperado: ");
                        String prefixoFisica = scanner.nextLine();

                        recuperarDados(repoPessoasFisicas, prefixoFisica + ".fisica.bin");
                    } else if (tipoRecuperar.equals("J")) {
                        System.out.print("Digite o nome do arquivo a ser recuperado: ");
                        String prefixoJuridica = scanner.nextLine();

                        recuperarDados(repoPessoasJuridicas, prefixoJuridica + ".juridica.bin");
                    } else {
                        System.out.println("Opcao invalida. Use F para Pessoa Fisica ou J para Pessoa Juridica.");
                    }
                    break;
            }

        } while (escolha != 0);

        scanner.close();
    }
    
    private static void salvarDados(PessoaFisicaRepo repo, String nomeArquivo) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(nomeArquivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(repo);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Dados salvos com sucesso em: " + nomeArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar os dados. " + e.getMessage());
        }
    }
    
        private static void salvarDados(PessoaJuridicaRepo repo, String nomeArquivo) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(nomeArquivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(repo);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Dados salvos com sucesso em: " + nomeArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar os dados. " + e.getMessage());
        }
    }
    
    private static void recuperarDados(PessoaFisicaRepo repo, String nomeArquivo) {
        try {
            FileInputStream fileInputStream = new FileInputStream(nomeArquivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            PessoaFisicaRepo novoRepo = (PessoaFisicaRepo) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            
            repo.substituir(novoRepo);
            
            System.out.println("Dados recuperados com sucesso do arquivo: " + nomeArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao recuperar os dados. " + e.getMessage());
        }
    }
    
        private static void recuperarDados(PessoaJuridicaRepo repo, String nomeArquivo) {
        try {
            FileInputStream fileInputStream = new FileInputStream(nomeArquivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            PessoaJuridicaRepo novoRepo = (PessoaJuridicaRepo) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            
            repo.substituir(novoRepo);
            
            System.out.println("Dados recuperados com sucesso do arquivo: " + nomeArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao recuperar os dados. " + e.getMessage());
        }
    }
}
