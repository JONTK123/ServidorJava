package org.example.cliente;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.BancoDados;


public class Main {
    public static void main(String[] args) throws Exception {
        Dotenv dotenv = Dotenv.load();
        String mongoURI = dotenv.get("DATABASE_URL");
        BancoDados bancoDados = new BancoDados(mongoURI, "Usuarios");
        PedidoAddUsuario addUsuario = new PedidoAddUsuario(bancoDados);

        addUsuario.cadastrar();
    }
}
