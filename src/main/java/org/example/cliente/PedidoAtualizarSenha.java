package org.example.cliente;

import org.example.BancoDados;
import org.example.Comunicado;

import java.util.Scanner;

public class PedidoAtualizarSenha extends Comunicado {
    private BancoDados bancoDados;

    public PedidoAtualizarSenha(BancoDados bancoDados) {
        this.bancoDados = bancoDados;
    }

    public void alterar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Nova Senha: ");
        String novaSenha = scanner.nextLine();

        boolean sucesso = bancoDados.atualizarSenha(email, novaSenha);

        if (sucesso) {
            System.out.println("Senha atualizada com sucesso!");
        } else {
            System.out.println("Email não encontrado.");
        }
    }
}