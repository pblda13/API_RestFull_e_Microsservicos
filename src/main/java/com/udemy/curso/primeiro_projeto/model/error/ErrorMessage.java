package com.udemy.curso.primeiro_projeto.model.error;

public class ErrorMessage{

     private String titulo;

    private Integer status;

    private String descricao;

    public ErrorMessage(String string, int value, String message) {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    
}