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
public class PessoaJuridicaRepo implements Serializable {
    private List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();
    private String arquivo;
    
    public PessoaJuridicaRepo(String arquivo) {
        this.arquivo = arquivo;
        
        recuperar();
    }

    public void inserir(PessoaJuridica empresa) {
        pessoasJuridicas.add(empresa);
        persistir();
    }

    public void alterar(PessoaJuridica empresa) throws IllegalArgumentException {
        int id = empresa.getId();
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            PessoaJuridica existente = pessoasJuridicas.get(i);
            if (existente.getId() == id) {
                pessoasJuridicas.set(i,empresa);
                persistir();
                return;
            }
        }                     
    }

    public void excluir(int id) throws IllegalArgumentException {
        PessoaJuridica empresaExcluir = null;
        for (PessoaJuridica empresa : pessoasJuridicas) {
            if (empresa.getId() == id) {
                empresaExcluir = empresa;
                break;
            }
        }
        if (empresaExcluir != null) {
            pessoasJuridicas.remove(empresaExcluir);
            persistir();
        } 
    }

    public PessoaJuridica obter(int id) throws IllegalArgumentException {
        for (PessoaJuridica empresa : pessoasJuridicas) {
            if (empresa.getId() == id) {
                return empresa;
            }
        }
        return null;
    }

    public List<PessoaJuridica> obterTodos() {
        return pessoasJuridicas;
    }

    public void persistir() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            outputStream.writeObject(pessoasJuridicas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recuperar() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivo))) {
            List<PessoaJuridica> dados = (List<PessoaJuridica>) inputStream.readObject();
            pessoasJuridicas.clear();
            pessoasJuridicas.addAll(dados);
        } catch (IOException | ClassNotFoundException e) {            
            e.printStackTrace();
        }
    }
    
    public void substituir(PessoaJuridicaRepo novoRepo) {
        this.pessoasJuridicas.clear();
        this.pessoasJuridicas.addAll(novoRepo.pessoasJuridicas);
    }
}
