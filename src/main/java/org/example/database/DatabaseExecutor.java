package org.example.database;

import org.example.cliente.PedidoDeOperacao;

public interface DatabaseExecutor {
    void execute(PedidoDeOperacao request);
}

