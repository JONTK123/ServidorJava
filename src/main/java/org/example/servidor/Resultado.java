package org.example.servidor;

import org.example.Comunicado;

public class Resultado extends Comunicado
{
    private Object resultado;

    public Resultado (boolean resultado)
    {
        this.resultado = resultado;
    }

    public Object getResultado()
    {
        return this.resultado;
    }

    public String toString ()
    {
        return (""+this.resultado);
    }

}
