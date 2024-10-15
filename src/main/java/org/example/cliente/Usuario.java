package org.example.cliente;

import org.bson.types.ObjectId;
import org.example.Data;

public class Usuario {
    private ObjectId id;
    private String nome;
    private String email;
    private Data dataNascimento;

    public Usuario(String nome, String email, Data dataNascimento ) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = (Data) dataNascimento.clone();
    }

    // Getters e Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
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

    public Data getDataNascimento() { return (Data) dataNascimento.clone();}

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = (Data) dataNascimento.clone();
    }

}
