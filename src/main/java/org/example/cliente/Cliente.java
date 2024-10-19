package org.example.cliente;

import org.example.Parceiro;
import org.example.Teclado;
import org.example.models.Data;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

    public static final String HOST_PADRAO = "localhost";
    public static final int PORTA_PADRAO = 3000;

    public static void main(String[] args) throws Exception {
        String host = HOST_PADRAO;
        int porta = PORTA_PADRAO;

        if (args.length > 0) {
            host = args[0];
        }

        if (args.length > 1) {
            porta = Integer.parseInt(args[1]);
        }

        Socket conexao = null;
        try {
            conexao = new Socket(host, porta);

        } catch (Exception erro) {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return;
        }

        ObjectOutputStream transmissor = null;
        try {
            transmissor = new ObjectOutputStream(conexao.getOutputStream());
        } catch (Exception erro) {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return;
        }

        ObjectInputStream receptor = null;
        try {
            receptor = new ObjectInputStream(conexao.getInputStream());

        } catch (Exception erro) {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return;
        }

        Parceiro servidor = null;
        try {
            servidor = new Parceiro(conexao, receptor, transmissor);

        } catch (Exception erro) {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return;
        }

        //Falta implementar TratadoraDeComunicadoDeDesligamento

            servidor.receba(new PedidoDeOperacao("GET", "Usuario"));
            System.out.println("-----------------------------------");
        }
}
