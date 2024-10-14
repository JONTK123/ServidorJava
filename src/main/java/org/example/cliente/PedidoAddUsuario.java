package org.example.cliente;

import org.example.BancoDados;
import org.example.Comunicado;

import java.util.Scanner;

public class PedidoAddUsuario extends Comunicado {
    private BancoDados bancoDados;

    public PedidoAddUsuario(BancoDados bancoDados) {
        this.bancoDados = bancoDados;
    }

    public void cadastrar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Data de Nascimento: ");
        String dataNascimento = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = new Usuario(nome, email, dataNascimento, senha);
        bancoDados.addUsuario(usuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }
}
