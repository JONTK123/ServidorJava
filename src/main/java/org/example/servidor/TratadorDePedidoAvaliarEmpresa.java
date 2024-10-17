package org.example.servidor;

import org.example.BancoDados;
import org.example.Parceiro;
import org.example.cliente.PedidoAvaliarEmpresa;

public class TratadorDePedidoAvaliarEmpresa extends Thread {
    private Parceiro parceiro;
    private BancoDados bancoDados;

    public TratadorDePedidoAvaliarEmpresa(Parceiro parceiro, BancoDados bancoDados) {
        this.parceiro = parceiro;
        this.bancoDados = bancoDados;
    }

    @Override
    public void run() {
        try {
            Object pedido = parceiro.envie(); //Recebe do cliente
            if (pedido instanceof PedidoAvaliarEmpresa) {
                PedidoAvaliarEmpresa pedidoAvaliarEmpresa = (PedidoAvaliarEmpresa) pedido;
                bancoDados.avaliarEmpresa(pedidoAvaliarEmpresa.getNomeUsuario(), pedidoAvaliarEmpresa.getNomeEmpresa(), pedidoAvaliarEmpresa.getNota(), pedidoAvaliarEmpresa.getComentario());
                System.out.println("Empresa avaliada com sucesso no servidor!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
