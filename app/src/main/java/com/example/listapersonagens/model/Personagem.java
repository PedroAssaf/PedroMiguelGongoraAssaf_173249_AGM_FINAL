package com.example.listapersonagens.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
    //Variaveis para guardar a informação de nome, altura e nascimento para manipulação do get e set
    private String nome;
    private String altura;
    private String nascimento;
    private String telefone;
    private String endereco;
    private String cep;
    private String rg;
    private String genero;

    private int id = 0;


    public Personagem(String nome, String altura, String nascimento) { //Função para obter as informações do personagem
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cep = cep;
        this.rg = rg;
        this.genero = genero;
    }

    public Personagem() {

    }

    //Metodo para retornar o nome guardado
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Metodo para retornar o altura guardado
    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    //Metodo para retornar o nascimento guardado
    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    //Metodo para retornar o Telefone guardado
    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone;}

    //Metodo para retornar o Endereço guardado
    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco;}

    //Metodo para retornar o Cep guardado
    public String getCep() { return cep; }

    public void setCep(String cep) { this.cep = cep;}

    //Metodo para retornar o RG guardado
    public String getRg() { return rg; }

    public void setRg(String rg) { this.rg = rg;}

    //Metodo para retornar o Genero guardado
    public String getGenero() { return genero; }

    public void setGenero(String genero) { this.genero = genero;}

    //Metodo para exibir o nome do personagem (String)
    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    //metodo para setar o id do item
    public void setId(int id) {
        this.id = id;
    }

    //Metodo para receber o valor do id
    public int getId() {
        return id;
    }

    public boolean IdValido() {
        return id > 0;
    }
}
