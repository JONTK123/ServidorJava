package org.example.cliente;

import org.example.Comunicado;
import java.util.Map;

public class PedidoDeOperacao extends Comunicado {

    private String operacao;
    private String colecao;
    private Map<String, Object> parametros;

    public PedidoDeOperacao(String operacao, String colecao){ //construtor para GET all
        this.operacao = operacao;
        this.colecao = colecao;
    }

    public PedidoDeOperacao(String operacao, String colecao, Map<String, Object> parametros) {
        this.operacao = operacao;
        this.colecao = colecao;
        this.parametros = parametros;

        System.out.println("Objeto contruido");
    }

    public String getOperacao() {return this.operacao;}
    public String getColecao() {return this.colecao;}
    public Map<String, Object> getParametros() {return this.parametros;}

    @Override
    public String toString() {
        return "Tipo de operação: " + this.operacao +
                "\nColeção: " + this.colecao +
                ((this.parametros==null)?"":"\nParâmetros: " + this.parametros);
    }
}
