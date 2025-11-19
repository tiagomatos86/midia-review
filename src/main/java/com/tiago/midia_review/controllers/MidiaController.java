package com.tiago.midia_review.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiago.midia_review.model.Midia;
import com.tiago.midia_review.repository.MidiaRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/midias")
public class MidiaController {

    @Autowired
    private MidiaRepository midiaRepository;

    // GET /midias -> lista todas as mídias
    @GetMapping
    public List<Midia> listarTodas() {
        return midiaRepository.findAll();
    }

    // POST /midias -> Cadastra uma nova mídia
    @PostMapping
    public Midia salvarMidia(@RequestBody Midia midia) {
        return midiaRepository.save(midia);
    }

    // @GetMapping("/{id}")
    // public Midia econtrarMidiaPorId(@PathVariable Long id) {
    // Midia midia = midiaRepository.findById(id).orElse(null);
    // return midia;
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Midia> encontrarMidiaPorId(@PathVariable Long id) {
        Optional<Midia> midiaExiste = midiaRepository.findById(id);

        if (midiaExiste.isPresent()) {
            return ResponseEntity.ok(midiaExiste.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/titulo")
    public List<Midia> listarPorTitulo(@RequestParam String titulo) {
        return midiaRepository.findByTituloContainingIgnoreCase(titulo);
    }

    @GetMapping("/tipo")
    public List<Midia> listarPorTipo(@RequestParam String tipo) {
        return midiaRepository.findByTipoIgnoreCase(tipo);
    }

    @GetMapping("/ano")
    public List<Midia> listarPorAno(@RequestParam Integer anoLancamento) {
        return midiaRepository.findByAnoLancamento(anoLancamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Midia> atualizarMidia(@PathVariable Long id, @RequestBody Midia midiaAtualizada) {
        Optional<Midia> midiaExistente = midiaRepository.findById(id);

        if (midiaExistente.isPresent()) {
            Midia midia = midiaExistente.get();

            midia.setTitulo(midiaAtualizada.getTitulo());
            midia.setTipo(midiaAtualizada.getTipo());
            midia.setAnoLancamento(midiaAtualizada.getAnoLancamento());
            midia.setImageUrl(midiaAtualizada.getImageUrl());
            midia.setResenha(midiaAtualizada.getResenha());
            midia.setNota(midiaAtualizada.getNota());

            Midia midiaSalva = midiaRepository.save(midia);
            return ResponseEntity.ok(midiaSalva); // retorna 200 ok
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Sem response entity, retorna sempre 200
    // @DeleteMapping("/{id}")
    // public void deletarMidia(@PathVariable Long id) {
    // Midia midiaBanco = midiaRepository.findById(id).orElse(null);
    // if(midiaBanco != null) {
    // midiaRepository.delete(midiaBanco);
    // }
    // }

    // obedece a semântica REST e retorna 204 se o delete for bem sucedido e 404 se
    // inexistente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMidia(@PathVariable Long id) {
        Optional<Midia> midiaExistente = midiaRepository.findById(id);

        if (midiaExistente.isPresent()) {
            midiaRepository.delete(midiaExistente.get());
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
