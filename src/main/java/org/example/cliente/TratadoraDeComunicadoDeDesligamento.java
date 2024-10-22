package org.example.cliente;

import org.example.Comunicado;
import org.example.Parceiro;
import org.example.servidor.ComunicadoDeDesligamento;

public class TratadoraDeComunicadoDeDesligamento extends Thread {
    private Parceiro servidor;

    public TratadoraDeComunicadoDeDesligamento(Parceiro servidor) throws Exception {
        if (servidor == null) throw new Exception("Porta inválida");

        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Comunicado comunicado = servidor.espie();
                if (comunicado == null) {
                    System.err.println("Erro de recepção: comunicado nulo");
                    continue;
                }
                if (comunicado instanceof ComunicadoDeDesligamento) {
                    servidor.adeus();
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}