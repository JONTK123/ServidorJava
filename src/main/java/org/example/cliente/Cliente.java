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

        char opcao = 0;
        do {
            System.out.println("1. Cadastrar Usuario");
            System.out.println("2. Cadastrar Empresa");
            System.out.println("3. Avaliar Empresa");
            System.out.println("T. Desligar");
            System.out.print("Opcao: ");
            opcao = Teclado.getUmChar();
        } while (opcao != '1' && opcao != '2' && opcao != '3' && opcao != 'T');

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

            case '2':
                System.out.print("Nome? ");
                String nomeEmpresa = Teclado.getUmString();
                System.out.print("Email? ");
                String emailEmpresa = Teclado.getUmString();
                System.out.print("CEP? ");
                String cep = Teclado.getUmString();
                System.out.print("Endereço? ");
                String endereco = Teclado.getUmString();
                System.out.print("CNPJ? ");
                String cnpj = Teclado.getUmString();
                System.out.print("Telefone? ");
                String telefone = Teclado.getUmString();

                PedidoAddEmpresa pedidoAddEmpresa = new PedidoAddEmpresa(nomeEmpresa, emailEmpresa, cep, endereco, cnpj, telefone);
                servidor.receba(pedidoAddEmpresa); // Envia para o servidor
                System.out.println("Pedido enviado para o servidor");
                break;

            case '3':
                char avaliarEmpresaOption;
                boolean validOption2;
                do {
                    System.out.print("Avaliar empresa (S/N)? ");
                    avaliarEmpresaOption = Teclado.getUmChar();
                    validOption2 = (avaliarEmpresaOption == 'S' || avaliarEmpresaOption == 'N');
                    if (!validOption2) {
                        System.out.println("Opção inválida");
                    }

                    if(avaliarEmpresaOption == 'S'){
                        System.out.print("Nome do usuário? ");
                        String nomeUsuario = Teclado.getUmString();
                        System.out.print("Nome da empresa? ");
                        String nomeEmpresaAvaliada = Teclado.getUmString();
                        System.out.print("Nota? ");
                        int nota = Teclado.getUmInt();
                        System.out.print("Comentário? ");
                        String comentario = Teclado.getUmString();

                        PedidoAvaliarEmpresa pedidoAvaliarEmpresa = new PedidoAvaliarEmpresa(nomeUsuario, nomeEmpresaAvaliada, nota, comentario);
                        servidor.receba(pedidoAvaliarEmpresa); // Envia para o servidor
                        System.out.println("Pedido enviado para o servidor");
                    }
                } while (!validOption2);




            case 'T':
                servidor.receba(new PedidoParaSair());
                System.out.println("Pedido de desligamento enviado para o servidor");
                break;
            }
        }
}
