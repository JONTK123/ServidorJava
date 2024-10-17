package org.example.cliente;

import org.example.Comunicado;

public class PedidoAddEmpresa extends Comunicado {
    private String nome;
    private String email;
    private String cep;
    private String endereco;
    private String cnpj;
    private String telefone;
    private int qtdAvaliacoes;
    private double notaMedia;

    public PedidoAddEmpresa(String nome, String email, String cep, String endereco, String cnpj, String telefone) {
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.qtdAvaliacoes = 0; //Vai ser atribuido dps
        this.notaMedia = 0; //Vai ser atribuido dps
    }

    public PedidoAddEmpresa(int qtdAvaliacoes, double notaMedia) { //Para atualizarmos o valor no futuro, imagino q seja assim
        this.qtdAvaliacoes = qtdAvaliacoes;
        this.notaMedia = notaMedia;
    }

    public String getNome() {return this.nome;}
    public String getEmail() {return this.email;}
    public String getCep() {return this.cep;}
    public String getEndereco() {return this.endereco;}
    public String getCnpj() {return this.cnpj;}
    public String getTelefone() {return this.telefone;}
    public int getQtdAvaliacoes() {return this.qtdAvaliacoes;}
    public double getNotaMedia() {return this.notaMedia;}
}
