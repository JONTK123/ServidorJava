package org.example.models;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.example.Comunicado;

@BsonDiscriminator
public class Usuario extends Comunicado {
    @BsonProperty("_id")
    private ObjectId id;
    private String nome;
    private String email;
    private Data dataNascimento;

    public Usuario() {}

    public Usuario(String nome, String email, Data dataNascimento) {
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

    public Data getDataNascimento() {
        return (Data) dataNascimento.clone();
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = (Data) dataNascimento.clone();
    }
}