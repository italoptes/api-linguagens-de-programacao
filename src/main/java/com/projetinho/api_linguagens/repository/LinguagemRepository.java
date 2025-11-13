package com.projetinho.api_linguagens.repository;

import com.projetinho.api_linguagens.model.Linguagem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.Transient;
import java.util.Optional;

public interface LinguagemRepository extends JpaRepository<Linguagem, Integer> {
    //JpaRepository<Linguagem, Integer> --> Primeiro o nome da tabela, depois o tipo do id

    //A única função de buscar por Id

    //É necessário a criação de outros métodos

    Optional<Linguagem> findByNome(String nome);

    @Transactional //Pois caso dê algum erro ele nao deleta
    void deleteByNome(String nome);
}
