package com.projetinho.api_linguagens.controllers;

import com.projetinho.api_linguagens.services.LinguagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LinguagemService linguagemService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("linguagens", linguagemService.listarTodasLinguagens());
        return "index";
    }
}