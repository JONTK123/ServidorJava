package org.example.cliente;

import org.example.Comunicado;
import org.example.models.Data;

public class PedidoDeOperacao extends Comunicado {


    private String operacao;
    private String colecao;
    private String campoChave;
    private String valorChave;
    private String novoDocumento;
    private String campoAlterado;
    private String novoValor;


    public PedidoDeOperacao(String operacao, String colecao) //construtor para GET all
    {
        this.operacao = operacao;
        this.colecao = colecao;
    }

    public PedidoDeOperacao(String operacao, String colecao, String novoDocumento) throws Exception // construtor para PUT
    {
        if(novoDocumento==null) throw new Exception("Documento não encontrado");

        this.operacao = operacao;
        this.colecao = colecao;
        this.novoDocumento = novoDocumento;
    }


    public PedidoDeOperacao(String operacao, String colecao, String campoChave, String valorChave, String novoValor, String campoAlterado) throws Exception // construtor para PATCH
    {

        if(campoChave == null) throw new Exception("campoChave não encontrado");

        if(valorChave == null) throw new Exception ("valorChave não encontrada");

        if(novoValor == null) throw new Exception("Novo valor do campoChave não encontrado");

        if(campoAlterado == null) throw new Exception("Campo a ser alterado não encontrado");

        this.operacao = operacao;
        this.colecao = colecao;
        this.campoChave = campoChave;
        this.valorChave = valorChave;
        this.novoValor = novoValor;
        this.campoAlterado = campoAlterado;
    }

    public PedidoDeOperacao(String operacao, String colecao, String campoChave, String valorChave) throws Exception // construtor para DELETE ou GET baseado em determinado campo e valor
    {

        if(campoChave == null) throw new Exception("campoChave não encontrado");

        if(valorChave == null) throw new Exception ("valorChave não encontrada");

        this.operacao = operacao;
        this.colecao = colecao;
        this.campoChave = campoChave;
        this.valorChave = valorChave;
    }


    public String getOperacao() {return this.operacao;}

    public String getColecao() {return this.colecao;}

    public String getcampoChave() {return this.campoChave;}

    public String getvalorChave() {return this.valorChave;}

    public String getNovoDocumento() {return this.novoDocumento;}

    public String getNovoValor() {return this.novoValor;}

    public String getCampoAlterado() {return this.campoAlterado;}
    
    
    @Override
    
    public String toString()
    {
        return "Tipo de operação: " + this.operacao +
                "\nColeção: " + this.colecao +
                ((this.campoChave==null)?"": "\nCampo chave: " + this.campoChave) +
                ((this.valorChave==null)? "": "\nValor do campo chave: " + this.valorChave) +
                ((this.novoDocumento==null)?"": "\n Novo documento: " + this.novoDocumento) +
                ((this.campoAlterado==null)?"": "\nCampo a ser alterado: " + this.campoAlterado) +
                ((this.novoValor==null)?"":"\nNovo valor: " + this.novoValor);
    }


    public static void main(String[] args) throws Exception {

        Data d1 = new Data((byte) 10, (byte) 10, (byte) 2024);

        PedidoDeOperacao getAll = new PedidoDeOperacao("GET", "usuarios");

        PedidoDeOperacao put = new PedidoDeOperacao("PUT", "trajetos", d1.toString());

        System.out.println(getAll.toString());
        System.out.println(put.toString());
    }
}
