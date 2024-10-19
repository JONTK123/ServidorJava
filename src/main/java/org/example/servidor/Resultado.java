package org.example.servidor;

import org.example.Comunicado;

public class Resultado extends Comunicado
{
    private boolean statusDaOperacao;

    public Resultado (boolean statusDaOperacao)
    {
        this.statusDaOperacao = statusDaOperacao;
    }

    public boolean getStatus ()
    {
        return this.statusDaOperacao;
    }

    public String toString ()
    {
        return (""+this.statusDaOperacao);
    }

}
