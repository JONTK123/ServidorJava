package org.example.database;

import org.example.cliente.PedidoDeOperacao;

public interface DatabaseExecutor {
    Object execute(PedidoDeOperacao request);
}

