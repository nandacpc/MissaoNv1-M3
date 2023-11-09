/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nanda
 */
public class PessoaFisicaRepo implements Serializable {
    private List<PessoaFisica> pessoasFisicas = new ArrayList<>();
    private String arquivo;
    
    public PessoaFisicaRepo(String arquivo) {
        this.arquivo = arquivo;
        
        recuperar();
    }

    public void inserir(PessoaFisica pessoa) {
        pessoasFisicas.add(pessoa);
        persistir();
    }

    public void alterar(PessoaFisica pessoa) throws IllegalArgumentException {
        int id = pessoa.getId();
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            PessoaFisica existente = pessoasFisicas.get(i);
            if (existente.getId() == id) {
                pessoasFisicas.set(i,pessoa);
                persistir();
                return;
            }
        }
        throw new IllegalArgumentException("Pessoa nao encontrada" + id);                     
    }

    public void excluir(int id) throws IllegalArgumentException {
        PessoaFisica pessoaExcluir = null;
        for (PessoaFisica pessoa : pessoasFisicas) {
            if (pessoa.getId() == id) {
                pessoaExcluir = pessoa;
                break;
            }
        }
        if (pessoaExcluir != null) {
            pessoasFisicas.remove(pessoaExcluir);
            persistir();
        } else{
            throw new IllegalArgumentException ("Pessoa nao encontrada" + id);
        }
    }

    public PessoaFisica obter(int id) throws IllegalArgumentException {
        for (PessoaFisica pessoa : pessoasFisicas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        throw new IllegalArgumentException ("Pessoa nao encontrada" + id);
    }

    public List<PessoaFisica> obterTodos() {
        return pessoasFisicas;
    }

    public void persistir() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            outputStream.writeObject(pessoasFisicas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recuperar() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivo))) {
            List<PessoaFisica> dados = (List<PessoaFisica>) inputStream.readObject();
            pessoasFisicas.clear();
            pessoasFisicas.addAll(dados);
        } catch (IOException | ClassNotFoundException e) {            
            e.printStackTrace();
        }
    }
    
    public void substituir(PessoaFisicaRepo novoRepo) {
        this.pessoasFisicas.clear(); 
        this.pessoasFisicas.addAll(novoRepo.pessoasFisicas);
    }
}
