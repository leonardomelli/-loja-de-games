package com.lojadegames.lojadegames.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table (name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size (min = 5,max = 1000)
    private String nome;

    @NotBlank
    @Size (min = 5,max = 100)
    private String email;

    @NotBlank
    @Size (min = 5,max = 255)
    private String cpf;

    @NotBlank
    @Size (min = 5,max = 255)
    private String telefone;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("cliente")
    private List<Jogo> jogo;

    public List<Jogo> getJogo() {
        return jogo;
    }

    public void setJogo(List<Jogo> jogo) {
        this.jogo = jogo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
