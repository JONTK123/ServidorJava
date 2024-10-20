package org.example.database;

import org.example.cliente.PedidoDeOperacao;

import javax.xml.crypto.Data;

public class DELETERequisition implements DatabaseExecutor {

    private BancoDados db;

    public DELETERequisition(BancoDados db) throws Exception
    {
        if(db==null) throw new Exception("Conexão com o Banco não encontrada");

        this.db = db;
    }

    @Override

    public Object execute(PedidoDeOperacao request)
    {
        try
        {
            this.db.delete(request.getColecao(), request.getParametros());
            return "Registro deletado com sucesso.";
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return "Falha ao deletar registro";
        }
    }
}
