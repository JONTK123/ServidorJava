package org.example;

import org.example.cliente.PedidoAddUsuario;
import org.example.cliente.PedidoAtualizarSenha;


public class Main {
    public static void main(String[] args) {
        BancoDados bancoDados = new BancoDados("mongodb+srv://thiagofossa433:XZmYXY2p4vlqXTzO@aulasbd.p8nc1.mongodb.net/\n", "Usuarios");
        PedidoAddUsuario addUsuario = new PedidoAddUsuario(bancoDados);
        PedidoAtualizarSenha atualizarSenha = new PedidoAtualizarSenha(bancoDados);

        addUsuario.cadastrar();
    }
}
