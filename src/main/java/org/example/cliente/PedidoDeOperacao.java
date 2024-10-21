package org.example.cliente;

import org.example.Comunicado;
import org.example.models.Avaliacao;
import org.example.models.Data;
import org.example.models.Empresa;
import org.example.models.Usuario;

import java.util.HashMap;
import java.util.Map;

public class PedidoDeOperacao extends Comunicado {


    private String operacao;
    private String colecao;
    private String parametros;



    public PedidoDeOperacao(String operacao, String colecao) //construtor para GET all
    {
        this.operacao = operacao;
        this.colecao = colecao;
    }

    public PedidoDeOperacao(String operacao, String colecao, String parametros)

    {
        this.operacao = operacao;
        this.colecao = colecao;
        this.parametros = parametros;

        System.out.println("Objeto contruido");

    }



    public String getOperacao() {return this.operacao;}

    public String getColecao() {return this.colecao;}

    public String getParametros() {return this.parametros;}
    
    
    @Override
    
    public String toString()
    {
        return "Tipo de operação: " + this.operacao +
                "\nColeção: " + this.colecao +
                ((this.parametros==null)?"":"\nParâmetros: " + this.parametros);
    }


}
