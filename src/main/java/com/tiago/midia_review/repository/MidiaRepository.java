package com.tiago.midia_review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tiago.midia_review.model.Midia;

public interface MidiaRepository extends JpaRepository<Midia, Long>{
    
}