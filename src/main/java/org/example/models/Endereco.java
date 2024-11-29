package org.example.models;

public class Endereco {
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String cep, String logradouro, String numero, String bairro, String cidade, String estado)
    {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {return this.cep;}
    public String getLogradouro() {return this.logradouro;}
    public String getNumero() {return this.numero;}
    public String getBairro() {return this.bairro;}
    public String getCidade() {return this.cidade;}
    public String getEstado() {return this.estado;}


    @Override

    public String toString()
    {
        return "CEP: " + this.getCep() +
                "\nLogradouro: " + this.getLogradouro() +
                "\nNumero: " + this.getNumero() +
                "\nBairro: " + this.getBairro() +
                "\nCidade: " + this.getCidade() +
                "\nEstado: " + this.getEstado();
    }

    @Override

    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(obj==null) return false;
        if(this.getClass()!=obj.getClass()) return false;

        Endereco e = (Endereco)obj;

        if(!this.cep.equals(e.cep)) return false;
        if(!this.logradouro.equals(e.logradouro)) return false;
        if(!this.numero.equals(e.numero)) return false;
        if(!this.bairro.equals(e.bairro)) return false;
        if(!this.cidade.equals(e.cidade)) return false;
        if(!this.estado.equals(e.estado)) return false;

        return true;
    }


    @Override

    public int hashCode()
    {
        int ret = 1;

        ret += ret * 3 + this.cep.hashCode();
        ret += ret * 2 + this.logradouro.hashCode();
        ret += ret * 7 + this.numero.hashCode();
        ret += ret * 13 + this.bairro.hashCode();
        ret += ret * 15 + this.cidade.hashCode();
        ret += ret * 2 + this.estado.hashCode();

        return ret;
    }
}
