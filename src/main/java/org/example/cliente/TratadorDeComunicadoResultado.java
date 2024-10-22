package org.example.cliente;

import com.google.gson.Gson;
import org.example.Comunicado;
import org.example.servidor.ComunicadoDeResultado;
import org.example.Parceiro;
import java.util.List;

public class TratadorDeComunicadoResultado extends Thread {
    private Parceiro servidor;

    public TratadorDeComunicadoResultado(Parceiro servidor) throws Exception {
        if (servidor == null) throw new Exception("Porta inválida");

        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Comunicado comunicado = servidor.espie();
                if (comunicado instanceof ComunicadoDeResultado) {
                    ComunicadoDeResultado resultado = (ComunicadoDeResultado) servidor.envie();
                    List<?> docList = (List<?>) resultado.getResultado();
                    Gson gson = new Gson();

                    for (Object doc : docList) {
                        System.out.println(gson.toJson(doc)); //Printando em JSON
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}