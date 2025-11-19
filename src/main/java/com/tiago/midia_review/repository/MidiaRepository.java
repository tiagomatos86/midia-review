package com.tiago.midia_review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tiago.midia_review.model.Midia;

public interface MidiaRepository extends JpaRepository<Midia, Long>{
    List<Midia> findByTituloContainingIgnoreCase(String titulo);
    List<Midia> findByTipoIgnoreCase(String tipo);
    List<Midia> findByAnoLancamento(int anoLancamento);
}