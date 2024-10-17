package org.example.servidor;

import org.example.Parceiro;
import org.example.BancoDados;
import org.example.cliente.PedidoAddEmpresa;

public class TratadorDePedidoAddEmpresa extends Thread {
    private Parceiro parceiro;
    private BancoDados bancoDados;

    public TratadorDePedidoAddEmpresa(Parceiro parceiro, BancoDados bancoDados) {
        this.parceiro = parceiro;
        this.bancoDados = bancoDados;
    }

    @Override
    public void run() {
        try {
            Object pedido = parceiro.envie(); //Recebe do cliente
            if (pedido instanceof PedidoAddEmpresa) {
                PedidoAddEmpresa pedidoAddEmpresa = (PedidoAddEmpresa) pedido;
                bancoDados.addEmpresa(pedidoAddEmpresa.getNome(), pedidoAddEmpresa.getCnpj(), pedidoAddEmpresa.getEmail(), pedidoAddEmpresa.getCep(), pedidoAddEmpresa.getEndereco(), pedidoAddEmpresa.getTelefone());
                System.out.println("Empresa cadastrada com sucesso no servidor!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
