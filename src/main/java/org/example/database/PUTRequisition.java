package org.example.database;

import org.example.cliente.PedidoDeOperacao;

import javax.xml.crypto.Data;

public class PUTRequisition implements DatabaseExecutor {

    private BancoDados db;

    public PUTRequisition(BancoDados db) throws Exception
    {
        if(db==null) throw new Exception("Conexão com o Banco não encontrada");

        this.db = db;
    }

    @Override

    public void execute(PedidoDeOperacao request)
    {
        try
        {
            this.db.put(request.getColecao(), request.getParametros());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
