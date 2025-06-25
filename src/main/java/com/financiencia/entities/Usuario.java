package com.financiencia.entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.stereotype.Service;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String nome;

    @Column(nullable=false)
    private String senha;

    public Usuario (){
    }

    public Usuario (Long id, String email, String nome, String senha){
        this.id=id;
        this.email=email;
        this.nome=nome;
        this.senha=senha;
    }

    public Long getId (){
        return id;
    }

    public void setId (Long id){
        this.id=id;
    }

    public String getEmail (){
        return email;
    }

    public void setEmail (String email){
        this.email=email;
    }

    public String getNome (){
        return nome;
    }

    public void setNome (String nome){
        this.nome=nome;
    }

    public String getSenha (){
        return senha;
    }

    public void setSenha (String senha){
        this.senha=senha;
    }
}
