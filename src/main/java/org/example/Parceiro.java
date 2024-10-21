package org.example;

import java.io.*;
import java.util.concurrent.Semaphore;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//Classe Parceiro, responsável por estabelecer a comunicação entre o servidor e o cliente
public class Parceiro {
    private Socket conexao; //Socket para estabelecer a conexão
    private ObjectInputStream receptor; //Objeto para receber a mensagem
    private ObjectOutputStream transmissor; //Objeto para enviar a mensagem

    private String proximoComunicado = null; //Espiar comunicado que esta vindo sem consumir o comunicado presente

    private Semaphore mutEx = new Semaphore(1, true); //Semáforo para exclusão mútua, 1 recurso apenas para alocacao

    public Parceiro (Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor) throws Exception {
        if (conexao == null)
            throw new Exception("Conexao ausente");

        if (receptor == null)
            throw new Exception("Receptor ausente");

        if (transmissor == null)
            throw new Exception("Transmissor ausente");

        this.conexao = conexao;
        this.receptor = receptor;
        this.transmissor = transmissor;
    }

    public void receba (String x) throws Exception
    {
        try
        {
            this.transmissor.writeObject (x);
            this.transmissor.flush       ();
        }
        catch (IOException erro)
        {
            throw new Exception ("Erro de transmissao");
        }
    }

    public String espie () throws Exception
    {
        try
        {
            this.mutEx.acquireUninterruptibly();
            if (this.proximoComunicado==null) this.proximoComunicado = (String)this.receptor.readObject();
            this.mutEx.release();
            return this.proximoComunicado;
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao: " + erro.getMessage());
        }
    }

    public String envie () throws Exception
    {
        try
        {
            if (this.proximoComunicado==null) this.proximoComunicado = (String)this.receptor.readObject();
            String ret         = this.proximoComunicado;
            this.proximoComunicado = null;
            return ret;
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao");
        }
    }

    public void adeus () throws Exception
    {
        try
        {
            this.transmissor.close();
            this.receptor   .close();
            this.conexao    .close();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de desconexao");
        }
    }

}
