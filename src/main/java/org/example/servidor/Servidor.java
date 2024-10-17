package org.example.servidor;

import org.example.Parceiro;
import org.example.BancoDados;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    public static final int PORTA_PADRAO = 3000;
    private static final Dotenv dotenv = Dotenv.configure().directory("src").load();
    private static final String mongoURI = dotenv.get("MONGO_URI");
    private static boolean running = true;
    private static List<Parceiro> clientes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        if (mongoURI == null) {
            throw new IllegalArgumentException("MONGO_URI is not set in the .env file");
        }

        ServerSocket servidor = new ServerSocket(PORTA_PADRAO);
        System.out.println("Servidor iniciado na porta " + PORTA_PADRAO);

        BancoDados bancoDados = new BancoDados(mongoURI, "Usuarios");

        while (running) {
            try {
                Socket conexao = servidor.accept();
                ObjectOutputStream transmissor = new ObjectOutputStream(conexao.getOutputStream());
                ObjectInputStream receptor = new ObjectInputStream(conexao.getInputStream());

                Parceiro parceiro = new Parceiro(conexao, receptor, transmissor);
                clientes.add(parceiro);
                System.out.println("Conexão estabelecida com " + conexao.getInetAddress().getHostAddress());

                // Tratando req do cliente addUsuario
                TratadoraDePedidoAddUsuario tratadorAddUsuario = new TratadoraDePedidoAddUsuario(parceiro, bancoDados);
                tratadorAddUsuario.start();

                // Tratando req do cliente desligar
                TratadorDePedidoDesligarServidor tratadorDesligarServidor = new TratadorDePedidoDesligarServidor(parceiro, servidor);
                tratadorDesligarServidor.start();

                // Tratando req do cliente addEmpresa
                TratadorDePedidoAddEmpresa tratadorAddEmpresa = new TratadorDePedidoAddEmpresa(parceiro, bancoDados);
                tratadorAddEmpresa.start();

                // Tratando req do cliente avaliarEmpresa
                TratadorDePedidoAvaliarEmpresa tratadorAvaliarEmpresa = new TratadorDePedidoAvaliarEmpresa(parceiro, bancoDados);
                tratadorAvaliarEmpresa.start();

            } catch (Exception e) {
                if (!running) {
                    System.out.println("Servidor desligado.");
                } else {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void shutdown() {
        running = false;
        for (Parceiro cliente : clientes) {
            try {
                cliente.receba(new ComunicadoDeDesligamento());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
