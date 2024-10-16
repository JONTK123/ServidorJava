package org.example.cliente;

import org.example.Comunicado;
import org.example.Parceiro;
import org.example.servidor.ComunicadoDeDesligamento;

public class TratadoraDeComunicadoDeDesligamento extends Thread {
    private Parceiro servidor;

    public TratadoraDeComunicadoDeDesligamento(Parceiro servidor) {
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Comunicado comunicado = servidor.espie();
                if (comunicado instanceof ComunicadoDeDesligamento) {
                    System.out.println("Servidor solicitou desligamento. Encerrando...");
                    servidor.adeus();
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}