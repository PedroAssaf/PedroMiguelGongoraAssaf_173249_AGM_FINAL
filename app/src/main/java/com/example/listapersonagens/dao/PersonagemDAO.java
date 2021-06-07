package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>(); //Lista de personagens para guardar informações
    private static int contadorId = 1;  // Id para lista


    public void salva(Personagem personagemSalvo) { //Função para salvar os conteudos da lista Personagem
        personagemSalvo.setId(contadorId);
        personagens.add(personagemSalvo);
        contadorId++; // incremento no id apos salvar
    }


    public void edita(Personagem personagem) {  //Função para editar as informações da lista
        Personagem personagemEscolhido = null;
        for (Personagem p: personagens) {
            if(p.getId() == personagem.getId()) {
                personagemEscolhido = p;
            }
        }
        if(personagemEscolhido != null) {
            int posicaoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoPersonagem, personagem);
        }

    }


    private Personagem buscarPersonagemId(Personagem personagem) { //Função para buscar um personagem pelo seu id
        for (Personagem p : personagens) {
            if(p.getId() == personagem.getId()) {
                return p;
            }
        }
        return null;
    }


    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    } //Função para mostrar todos os personagens salvos


    public void remove(Personagem personagem) { //Função para deletar um personagem da lista já salvo
        Personagem personagemDevolvido = buscarPersonagemId(personagem);
        if(personagemDevolvido != null) {
            personagens.remove(personagemDevolvido);
        }
    }
}
