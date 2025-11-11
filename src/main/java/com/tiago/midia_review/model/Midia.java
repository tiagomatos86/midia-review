package com.tiago.midia_review.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "midias")
public class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O tipo é obrigatório")
    private String tipo;

    @NotNull(message = "O ano de lançamento é obrigatório")
    private int anoLancamento;
    private String imageUrl;

    @Column(length = 2000)
    @Size(max = 2000, message = "A resenha deve ter no máximo 2000 caracteres")
    private String resenha;
    
    @Min(value = 0, message = "A nota mínima é 0")
    @Max(value = 10, message = "A nota máxima é 10")
    private Double nota;

    private LocalDate dataRegistro;

    public Midia() {}

    public Midia(Long id, String titulo, String tipo, String imageUrl, String resenha, Double nota, LocalDate dataRegistro, int anoLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.imageUrl = imageUrl;
        this.resenha = resenha;
        this.nota = nota;
        this.dataRegistro = dataRegistro;
        this.anoLancamento = anoLancamento;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getResenha() {
        return resenha;
    }

    public Double getNota() {
        return nota;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }


    @Override
    public String toString() {
        return "Midia [id=" + id + ", titulo=" + titulo + ", tipo=" + tipo + ", anoLancamento=" + anoLancamento
                + ", imageUrl=" + imageUrl + ", resenha=" + resenha + ", nota=" + nota + ", dataRegistro="
                + dataRegistro + "]";
    }
}
