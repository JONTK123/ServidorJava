package org.example.cliente;

import org.example.BancoDados;
import org.example.Comunicado;
import org.example.models.Data;
import org.example.models.Usuario;

public class PedidoAddUsuario extends Comunicado {
    private String nome;
    private String email;
    private Data dataNascimento;
    //Eu tentei implementar banco de dados aqui tambem mas eh um problema e deu bug... Nao eha conselhavel passar operacoes de BD na rede

    public PedidoAddUsuario( String nome, String email, Data dataNascimento) {
        super();
        this.nome = nome;
        this.email = email;
        this.dataNascimento = (Data) dataNascimento.clone();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }
}
