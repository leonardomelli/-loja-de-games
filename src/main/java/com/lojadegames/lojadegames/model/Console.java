package com.lojadegames.lojadegames.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_console")
public class Console {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @NotBlank
    @Size (min = 1 , max = 1000)
    private String nome;

    @OneToMany(mappedBy = "console", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("console")
    private List<Jogo> jogo;


    public List<Jogo> getJogo() {
        return jogo;
    }

    public void setJogo(List<Jogo> jogo) {
        this.jogo = jogo;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
