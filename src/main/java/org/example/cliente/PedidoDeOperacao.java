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
    private Map<String, Object> parametros;



    public PedidoDeOperacao(String operacao, String colecao) //construtor para GET all
    {
        this.operacao = operacao;
        this.colecao = colecao;
    }

    public PedidoDeOperacao(String operacao, String colecao, Map<String, Object> parametros) throws Exception
    {   System.out.println("Entrou no construtor");
        if(parametros==null) throw new Exception("Parâmetros não encontrado");

        this.operacao = operacao;
        this.colecao = colecao;
        this.parametros = parametros;

        System.out.println("Objeto contruido");

    }



    public String getOperacao() {return this.operacao;}

    public String getColecao() {return this.colecao;}

    public Map<String, Object> getParametros() {return this.parametros;}
    
    
    @Override
    
    public String toString()
    {
        return "Tipo de operação: " + this.operacao +
                "\nColeção: " + this.colecao +
                ((this.parametros==null)?"":"\nParâmetros: " + this.parametros);
    }


    public static void main(String[] args) throws Exception {

        // TESTANDO AS INSTANCIAS

        //REQUISIÇÃO GET ALL
        PedidoDeOperacao getAll = new PedidoDeOperacao("GET", "usuarios");
        System.out.println(getAll.toString());
        System.out.println("");

        //REQUISIÇÃO PUT
        Map<String, Object> parametrosPUT = new HashMap<String, Object>();
        Avaliacao avaliacao = new Avaliacao("teste", "empresa", "uma bosta", 5);
        parametrosPUT.put("docNovo", avaliacao);
        PedidoDeOperacao put = new PedidoDeOperacao("PUT", "usuarios", parametrosPUT);
        System.out.println(put.toString());
        System.out.println("");

        //REQUISIÇÃO DELETE
        Map<String, Object> parametrosDelete = new HashMap<String, Object>();
        parametrosDelete.put("campoChave", "nome");
        parametrosDelete.put("nomeCampoChave", "Joao");
        PedidoDeOperacao delete = new PedidoDeOperacao("DELETE", "usuarios", parametrosDelete);

        System.out.println(delete.toString());



    }
}
