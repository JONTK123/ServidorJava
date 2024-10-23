package org.example.servidor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Comunicado;
import org.example.Parceiro;
import org.example.cliente.PedidoDeOperacao;
import org.example.cliente.PedidoDeResultado;
import org.example.cliente.PedidoParaSair;
import org.example.database.*;
import org.example.models.Avaliacao;
import org.example.models.Empresa;
import org.example.models.Trajeto;
import org.example.models.Usuario;

import java.net.Socket;

import java.util.*;

import java.net.*;

import java.io.*;

public class SupervisoraDeConexao extends Thread{

    private boolean status = false;
    private String res;
    private Parceiro usuario;
    private Socket conexao;
    private ArrayList<Parceiro> usuarios;


    public SupervisoraDeConexao(Socket conexao, ArrayList<Parceiro> usuarios) throws Exception
    {
        if(conexao==null) throw new Exception("Não há conexão");

        if(usuarios==null) throw new Exception ("Não há usuários");

        this.conexao = conexao;

        this.usuarios = usuarios;
    }


    public void run()
    {
        PrintWriter transmissor = null;

        try
        {
            transmissor = new PrintWriter(this.conexao.getOutputStream());
        }
        catch(Exception e) {return;}


       BufferedReader receptor = null;

        try
        {
            receptor = new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));
        }
        catch(Exception e)
        {
            try
            {
                transmissor.close();
            }
            catch(Exception error){};
            return;
        }

        try
        {
            this.usuario = new Parceiro (this.conexao, receptor, transmissor);
        }
        catch (Exception erro)
        {} // nao vai dar erro

        try
        {
            synchronized (this.usuarios)
            {
                this.usuarios.add (this.usuario);
            }


            for(;;)
            {
//                Comunicado comunicado = this.usuario.envie ();

                 String JASON = this.usuario.envie ();
                 Comunicado comunicado = new PedidoDeOperacao("POST", "Usuario", JASON);

                if (comunicado==null)
                    return;
                else if (comunicado instanceof PedidoDeOperacao)
                {
                    PedidoDeOperacao pedidoDeOperacao = (PedidoDeOperacao)comunicado;
                    String colecao = pedidoDeOperacao.getColecao ();
                    String parametros= pedidoDeOperacao.getParametros();

                    switch (pedidoDeOperacao.getOperacao())
                    {
                        case "POST":

                            try
                            {
                                BancoDados db = new BancoDados();

                                this.res = db.post(colecao, parametros);
                                usuario.receba(this.res);
                            }
                            catch (Exception erro)
                            {
                                System.err.println(erro.getMessage());
                            }
                            break;


                    }
                }
                else if (comunicado instanceof PedidoDeResultado)
                {
                    this.usuario.receba (String.valueOf(new Resultado(this.res)));
                }
                else if (comunicado instanceof PedidoParaSair)
                {
                    synchronized (this.usuarios)
                    {
                        this.usuarios.remove (this.usuario);
                    }
                    this.usuario.adeus();
                }
            }
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close ();
                receptor.close ();
            }
            catch (Exception falha)
            {} //

            return;
        }
    }
}
