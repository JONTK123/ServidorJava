package org.example.models;

public class Usuario  {
    private String nome;
    private String email;
    private String dataNascimento;
    private boolean isPhysicalPerson;

    public Usuario(String nome, String email, String dataNascimento, boolean isPhysicalPerson) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.isPhysicalPerson = isPhysicalPerson;
    }

    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getDataNascimento() {return this.dataNascimento;}
    public boolean getIsPhysicalPerson() {return this.isPhysicalPerson;}
}