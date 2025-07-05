package com.financiencia.dto;

public class UsuarioResponse {

    private Long id;
    private String email;
    private String token;

    public UsuarioResponse (){
    }

    public UsuarioResponse (Long id, String email, String token){
        this.id=id;
        this.email=email;
        this.token=token;
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

    public String getToken (){
        return token;
    }

    public void setToken (String token){
        this.token=token;
    }
}
