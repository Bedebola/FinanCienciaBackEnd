package com.financiencia.dto;

public class UsuarioRequest {

    private String nome;
    private String email;
    private String senha;

    public UsuarioRequest (){
    }

    public UsuarioRequest (String email, String senha, String nome){
        this.nome=nome;
        this.email=email;
        this.senha=senha;
    }

    public String getNome (){
        return nome;
    }

    public void setNome (String nome){
        this.nome=nome;
    }

    public String getEmail (){
        return email;
    }

    public void setEmail (String email){
        this.email=email;
    }

    public String getSenha (){
        return senha;
    }

    public void setSenha (String senha){
        this.senha=senha;
    }

}
