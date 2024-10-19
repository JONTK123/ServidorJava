package org.example.servidor;

import org.example.Parceiro;
import org.example.cliente.PedidoParaSair;

import java.net.ServerSocket;

public class TratadorDePedidoDesligarServidor extends Thread {
    private Parceiro parceiro;
    private ServerSocket servidor;

    public TratadorDePedidoDesligarServidor(Parceiro parceiro, ServerSocket servidor) {
        this.parceiro = parceiro;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            Object pedido = parceiro.envie(); // Recebe do cliente
            if (pedido instanceof PedidoParaSair) {
                System.out.println("Pedido de desligamento recebido. Desligando servidor...");
                servidor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}