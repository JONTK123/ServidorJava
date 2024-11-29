package org.example.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Empresa implements Serializable {

    private String name;
    private String email;
    private String cnpj;
    private String telefone;
    private Endereco endereco;
    private double mediaAvl;
    private ArrayList<Object> avaliacoes;
    private ArrayList<Object> trajetos;
    private String tipoUsuario;



    public Empresa(String name, String email, String cnpj, String telefone, Endereco endereco, String tipoUsuario) {
        this.name = name;
        this.email = email;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.mediaAvl = 0d;
        this.avaliacoes = new ArrayList<Object>();
        this.trajetos = new ArrayList<Object>();
        this.tipoUsuario = tipoUsuario;
    }

    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getCnpj(){
        return this.cnpj;
    }
    public String getTelefone(){
        return this.telefone;
    }
    public Endereco getEndereco(){
        return this.endereco;
    }
    public Double getMediaAvl(){
        return this.mediaAvl;
    }
    public String getTipoUsuario(){return this.tipoUsuario;}
    public ArrayList<Object> getAvaliacoes(){ return this.avaliacoes;}
    public ArrayList<Object> getTrajetos() {return this.trajetos;}

    @Override
    public boolean equals(Object b){
        if(b == null) return false;
        if(b == this) return true;
        if(b.getClass()!= this.getClass()) return false;

        Empresa other = (Empresa) b;

        if(!this.name.equals(other.name)) return false;
        if(!this.email.equals(other.email)) return false;
        if(!this.cnpj.equals(other.cnpj)) return false;
        if(!this.telefone.equals(other.telefone)) return false;
        if(!this.endereco.equals(other.endereco)) return false;
        if(!(this.mediaAvl ==other.mediaAvl)) return false;
        if(this.avaliacoes.size()!=other.avaliacoes.size()) return false;
        if(this.trajetos.size()!=other.trajetos.size()) return false;
        if(!this.tipoUsuario.equals(other.tipoUsuario)) return false;

        //TODO: COMPARAÇÃO ENTRE CADA ELEMENTO DOS VETORES DE TRAJETO E DE AVALIACOES
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 1;
        ret += ret * 7 + this.name.hashCode();
        ret += ret * 7 + this.email.hashCode();
        ret += ret * 7 + this.cnpj.hashCode();
        ret += ret * 7 + this.telefone.hashCode();
        ret += ret * 7 + this.endereco.hashCode();
        ret += ret * 7 + Double.valueOf(this.mediaAvl).hashCode();
        ret += ret * 7 + this.tipoUsuario.hashCode();

        for(int i=0;i<this.avaliacoes.size();i++)
        {
            ret += ret * 2 + this.avaliacoes.get(i).hashCode();
        }

        for(int i=0;i<this.trajetos.size();i++)
        {
            ret += ret * 2 + this.trajetos.get(i).hashCode();
        }
        return ret;
    }

    @Override
    public String toString(){
        return(this.name+"/"
                +this.email+"/"
                +this.cnpj+"/"
                +this.telefone+"/"
                +this.endereco.toString()+"/"
                +this.mediaAvl+"/"
                +this.avaliacoes.toString()+"/"
                +this.trajetos.toString()
                +this.tipoUsuario.toString());
    }

}