package org.example.database;

import org.example.cliente.PedidoDeOperacao;

public class GETRequisition implements DatabaseExecutor{
    private BancoDados db;

    public GETRequisition(BancoDados db)throws Exception
    {
        if(db==null) throw new Exception("Conexão com o banco não encontrada");

        this.db = db;
    }

    @Override

    public void execute(PedidoDeOperacao request)
    {
        // METODO GENERICO DO BANCO PARA GET
    }
}
