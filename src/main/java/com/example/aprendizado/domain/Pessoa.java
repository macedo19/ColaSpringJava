package com.example.aprendizado.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pessoa{

    // Aqui precisa adicionar a dependencia na pom.xml
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private Integer idade;

    /*
     * Metodos construtores
     */
    public Pessoa(){}

    public Pessoa(String nome){
        this.nome = nome;
    }

    public Pessoa( String nome, Integer idade){
        this.nome = nome;
        this.idade = idade;
        
    }
    public Pessoa(Long id, String nome, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    /*
     * GETTERS AND SETTERS ABAIXO
     */
    public long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public Integer getIdade() {
        return idade;
    }

    public void setNome( String nome){
        this.nome = nome;
    }

    
    public void setId( Long id){
        this.id = id;
    }
    
    public void setIdade( Integer idade){
        this.idade = idade;
    }

}