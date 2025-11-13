package com.projetinho.api_linguagens.controllers;

import com.projetinho.api_linguagens.model.Linguagem;
import com.projetinho.api_linguagens.services.LinguagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController //Define como controller
@RequestMapping("api/linguagem") //Define a rota matriz
@RequiredArgsConstructor //Cria construtor
public class LinguagemController {
    private final LinguagemService linguagemService;

    @GetMapping     //Defino que esse método vai ser Map
    public List<Linguagem> listarTodasLinguagem(){
        return linguagemService.listarTodasLinguagens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Linguagem> buscarPorId(@PathVariable("id") Integer idLinguagem) {
        Linguagem linguagem = linguagemService.buscarLinguagemPorId(idLinguagem);
        return ResponseEntity.ok(linguagem);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Linguagem> buscarPorNome(@RequestParam String nome){
        Linguagem linguagem =  linguagemService.buscarLinguagemPorNome(nome);
        return ResponseEntity.ok(linguagem);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Linguagem> adicionarLinguagem(
            @RequestParam("nome") String nome,
            @RequestParam("criador") String criador,
            @RequestParam("anoCriacao") int anoCriacao,
            @RequestParam("paradigma") String paradigma,
            @RequestParam("foto") MultipartFile foto
    ) throws IOException {
        // Define o diretório de upload (ex: dentro de /static/uploads)
        String uploadDir = "src/main/resources/static/uploads/";

        // Cria pasta se não existir
        File diretorio = new File(uploadDir);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        // Salva o arquivo com nome único
        String nomeArquivo = System.currentTimeMillis() + "_" + foto.getOriginalFilename();
        Path caminho = Paths.get(uploadDir + nomeArquivo);
        Files.write(caminho, foto.getBytes());

        // Salva a URL relativa para exibir no HTML
        String urlImagem = "/uploads/" + nomeArquivo;

        Linguagem nova = new Linguagem();
        nova.setNome(nome);
        nova.setCriador(criador);
        nova.setAnoCriacao(anoCriacao);
        nova.setParadigma(paradigma);
        nova.setFoto(urlImagem);

        linguagemService.salvarLinguagem(nova);
        return ResponseEntity.ok(nova);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPorId(@PathVariable("id") Integer idLinguagem){
        linguagemService.deletarLinguagemPorId(idLinguagem);
        return ResponseEntity.ok().build();
    }
}
