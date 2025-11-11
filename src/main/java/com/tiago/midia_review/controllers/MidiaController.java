package com.tiago.midia_review.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiago.midia_review.model.Midia;
import com.tiago.midia_review.repository.MidiaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/midias")
public class MidiaController { 
    
    @Autowired
    private MidiaRepository midiaRepository;

    //GET /midias -> lista todas as mídias
    @GetMapping
    public List<Midia> listarTodas() {
        return midiaRepository.findAll();
    }

    // POST /midias -> Cadastra uma nova mídia
    @PostMapping
    public Midia salvarMidia(@RequestBody Midia midia) {
        return midiaRepository.save(midia);
    }
}
