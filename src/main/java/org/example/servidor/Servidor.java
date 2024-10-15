package org.example.servidor;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.cliente.PedidoAddUsuario;
import org.example.Parceiro;
import org.example.BancoDados;
import org.example.models.Usuario;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static final int PORTA_PADRAO = 3000;
    private static final Dotenv dotenv = Dotenv.configure().directory("src").load();
    private static final String mongoURI = dotenv.get("MONGO_URI");

    public static void main(String[] args) throws Exception {
        if (mongoURI == null) {
            throw new IllegalArgumentException("MONGO_URI is not set in the .env file");
        }

        ServerSocket servidor = new ServerSocket(PORTA_PADRAO);
        System.out.println("Servidor iniciado na porta " + PORTA_PADRAO);

        BancoDados bancoDados = new BancoDados(mongoURI, "Usuarios");

        while (true) {
            Socket conexao = servidor.accept();
            ObjectOutputStream transmissor = new ObjectOutputStream(conexao.getOutputStream());
            ObjectInputStream receptor = new ObjectInputStream(conexao.getInputStream());

            Parceiro parceiro = new Parceiro(conexao, receptor, transmissor);

            Object pedido = receptor.readObject();
            if (pedido instanceof PedidoAddUsuario) {
                PedidoAddUsuario pedidoAddUsuario = (PedidoAddUsuario) pedido;
                bancoDados.addUsuario(new Usuario(pedidoAddUsuario.getNome(), pedidoAddUsuario.getEmail(), pedidoAddUsuario.getDataNascimento()));
                System.out.println("Usuário cadastrado com sucesso no servidor!");
            }
        }
    }
}