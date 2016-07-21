package com.ronan.persistenciaapplication.model;

public class Usuario {
    private Integer _id;
    private String login;
    private String senha;
    private String marcado;

    public Usuario(){}

    public Usuario(Integer id,String login, String senha, String marcado){
        this._id = id;
        this.login = login;
        this.senha = senha;
        this.marcado = marcado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getMarcado() {
        return marcado;
    }

    public void setMarcado(String marcado) {
        this.marcado = marcado;
    }

}
