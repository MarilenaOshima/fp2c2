/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.model.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author aluno
 */
//arquitetura:
// view (.xhtml) --> controller (managed bean) --> model (entity)

// pra determinar uma classe managed bean
// precisa ter o @Named, o escopo e um contrutor sem parametro
// import biblioteca java ee 7
@Named
// determina o escopo

//@RequestScoped
// escopo do tipo request, a cada requisição faz uma nova instância.
// cria toda vez uma nova instância
//toda vez que fizer uma chamada, vai criar uma nova instância praquela classe
//no caso quem cria é o glassfish
//chamada de container...o servidor é chamada de container
//quem gerencia o managed bean é o container
//se você quiser mandar informções..não pode ser do tipo @RequestScoped
//pq toda vez instância um novo objeo...aí zer a lista e cria uma lista vazia toda vez
//por isso não dá para guardar informações

@ViewScoped
/**
precisa implementar na classe Serializable
continua precisando do @Named e construtor sem parametro
a instância será a mesma enquanto estiver na mesma página
esse escopo serve qnd vc precisa por exemplo add itens e exibir na mesma página

*/
public class ExemploController implements Serializable {

    private Pessoa pessoa;   
    private Pessoa pessoaSelecionada;
    private List<Pessoa> pessoas = new ArrayList<>();

    //instância o atributo
    public ExemploController() {
        System.out.println("Construtor.");
        this.pessoa = new Pessoa();
    }

    public void adicionar() {
        System.out.println("Executou o método teste.");
        System.out.println("Nome: " + this.pessoa.getNome());
        this.pessoas.add(pessoa);
        this.pessoa = new Pessoa();
        addMessage(FacesMessage.SEVERITY_INFO, "Informação", "Cadastro realizado.");
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void remover() {
        System.out.println("metodo remover");
        //System.out.println(this.pessoaSelecionada.getNome());

        if (pessoaSelecionada != null && !pessoaSelecionada.getNome().isEmpty()) {
            for (Pessoa p : pessoas) {
                if (p.getNome().equals(this.pessoaSelecionada.getNome())) {
                    System.out.println("achou na lista");
                    this.pessoas.remove(p);
                    addMessage(FacesMessage.SEVERITY_INFO, "Informação", "Pessoa excluída com sucesso.");
                    return;
                }
            }
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Selecione uma pessoa.");
        }
    }


    public void exibir() {
        for (Pessoa p : pessoas) {
            System.out.println(p.getNome());
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }


    

}
