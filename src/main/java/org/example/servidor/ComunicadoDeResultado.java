package org.example.servidor;

import org.example.Comunicado;

public class ComunicadoDeResultado extends Comunicado
{
    private Object resultado;

    public ComunicadoDeResultado (Object resultado) {
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
