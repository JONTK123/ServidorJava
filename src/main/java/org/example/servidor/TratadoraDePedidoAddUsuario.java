package org.example.servidor;

import org.example.BancoDados;
import org.example.Parceiro;
import org.example.cliente.PedidoAddUsuario;

public class TratadoraDePedidoAddUsuario extends Thread {
    private Parceiro parceiro;
    private BancoDados bancoDados;

    public TratadoraDePedidoAddUsuario(Parceiro parceiro, BancoDados bancoDados) {
        this.parceiro = parceiro;
        this.bancoDados = bancoDados;
    }

    @Override
    public void run() {
        try {
            Object pedido = parceiro.envie(); //Recebe do cliente
            if (pedido instanceof PedidoAddUsuario) {
                PedidoAddUsuario pedidoAddUsuario = (PedidoAddUsuario) pedido;
                //bancoDados.addUsuario(pedidoAddUsuario.getNome(), pedidoAddUsuario.getEmail(), pedidoAddUsuario.getDataNascimento(), pedidoAddUsuario.getIsPhysicalPerson());
                System.out.println("Usuário cadastrado com sucesso no servidor!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}