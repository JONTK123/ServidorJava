package org.example.cliente;

import org.example.BancoDados;
import org.example.Comunicado;
import org.example.Data;

import java.util.Scanner;

public class PedidoAddUsuario extends Comunicado {
    private BancoDados bancoDados;

    public PedidoAddUsuario(BancoDados bancoDados) {
        this.bancoDados = bancoDados;
    }

    public void cadastrar() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Dia, mês e ano de nascimento: ");
        String i = scanner.nextLine(); byte dia = Byte.parseByte(i);
        String j = scanner.nextLine(); byte mes = Byte.parseByte(j);
        String t = scanner.nextLine(); short ano = Short.parseShort(t);
        Data dataNascimento = new Data(dia,mes,ano);

        Usuario usuario = new Usuario(nome, email, dataNascimento);
        bancoDados.addUsuario(usuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }
}
