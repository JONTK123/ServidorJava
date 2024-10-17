package org.example.cliente;

import org.example.Comunicado;

public class PedidoAvaliarEmpresa extends Comunicado {
    private String nomeUsuario;
    private String nomeEmpresa;
    private int nota;
    private String comentario;

    public PedidoAvaliarEmpresa(String nomeUsuario, String nomeEmpresa, int nota, String comentario) {
        this.nomeUsuario = nomeUsuario;
        this.nomeEmpresa = nomeEmpresa;
        this.nota = nota;
        this.comentario = comentario;
    }

    public String getNomeUsuario() {return this.nomeUsuario;}
    public String getNomeEmpresa() {return this.nomeEmpresa;}
    public int getNota() {return this.nota;}
    public String getComentario() {return this.comentario;}
}
