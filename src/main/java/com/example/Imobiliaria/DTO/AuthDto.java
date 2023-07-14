package com.example.Imobiliaria.DTO;

public class AuthDto {
    private String nome;
    private String email;
    private String token;
    private String tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public AuthDto(String nome, String email, String token, String tipo) {
        this.nome = nome;
        this.email = email;
        this.token = token;
        this.tipo = tipo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
