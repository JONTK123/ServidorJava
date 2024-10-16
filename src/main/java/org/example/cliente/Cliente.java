package org.example.cliente;

import org.example.Parceiro;
import org.example.Teclado;
import org.example.models.Data;
import org.example.servidor.ComunicadoDeDesligamento;
import org.example.cliente.PedidoAddUsuario;
import org.example.cliente.PedidoDesligarServidor;

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

        char opcao = 0;
        do {
            System.out.println("1. Cadastrar");
            System.out.println("T. Desligar");
            System.out.print("Opcao: ");
            opcao = Teclado.getUmChar();
        } while (opcao != '1' && opcao != 'T');

        switch (opcao) {
            case '1':
                System.out.print("Nome? ");
                String nome = Teclado.getUmString();
                System.out.print("Email? ");
                String email = Teclado.getUmString();

                System.out.print("Data de nascimento (DD/MM/AAAA)? ");
                String dataNascimentoStr = Teclado.getUmString();
                String[] dataParts = dataNascimentoStr.split("/");
                byte dia = Byte.parseByte(dataParts[0]);
                byte mes = Byte.parseByte(dataParts[1]);
                short ano = Short.parseShort(dataParts[2]);
                Data dataNascimento = new Data(dia, mes, ano);

                char isPhysicalPersonOption;
                boolean validOption;
                do {
                    System.out.print("Pessoa física (S/N)? ");
                    isPhysicalPersonOption = Teclado.getUmChar();
                    validOption = (isPhysicalPersonOption == 'S' || isPhysicalPersonOption == 'N');
                    if (!validOption) {
                        System.out.println("Opção inválida");
                    }
                } while (!validOption);

                boolean isPhysicalPerson = (isPhysicalPersonOption == 'S');

                PedidoAddUsuario pedidoAddUsuario = new PedidoAddUsuario(nome, email, dataNascimento.toString(), isPhysicalPerson);
                servidor.receba(pedidoAddUsuario); // Envia para o servidor
                System.out.println("Pedido enviado para o servidor");
                break;
            case 'T':
                servidor.receba(new PedidoDesligarServidor());
                System.out.println("Pedido de desligamento enviado para o servidor");
                break;
        }
    }
}