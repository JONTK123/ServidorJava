package org.example.cliente;

import org.example.Comunicado;

public class PedidoAddUsuario extends Comunicado {
    private String nome;
    private String email;
    private String dataNascimento;
    private boolean isPhysicalPerson;

    public PedidoAddUsuario(String nome, String email, String dataNascimento, boolean isPhysicalPerson) {
        super();
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
    public String getDataNascimento() { return dataNascimento; }
    public boolean getIsPhysicalPerson() { return isPhysicalPerson; }
}
