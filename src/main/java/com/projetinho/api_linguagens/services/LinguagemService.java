package com.projetinho.api_linguagens.services;

import com.projetinho.api_linguagens.model.Linguagem;
import com.projetinho.api_linguagens.repository.LinguagemRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Indica para o spring q essa classe é service, É OBRIGATORIO!!
@RequiredArgsConstructor
public class LinguagemService {

    private final LinguagemRepository repository;

    public Linguagem salvarLinguagem(Linguagem linguagem){
        return repository.saveAndFlush(linguagem); //Salva e fecha a conexão com o banco de dados
    }

    public List<Linguagem> listarTodasLinguagens(){
        return repository.findAll();
    }

    //  Métodos de busca
    public Linguagem buscarLinguagemPorId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Id não encontrado")
        );
    }
    public Linguagem buscarLinguagemPorNome(String nome){
        return repository.findByNome(nome).orElseThrow(
                () -> new RuntimeException("Nome não encontrado")
        );
    }

    // Deleta linguagem
    public void deletarLinguagemPorNome(String nome){
        repository.deleteByNome(nome);
    }
    public void deletarLinguagemPorId(Integer id){
        repository.deleteById(id);
    }


}
