package org.example.models;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.example.database.BancoDados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Avaliacao implements Serializable {
    private String user;
    private String company;
    private String comment;
    private  int grade;

    public Avaliacao(String username, String company, String comment, int grade){
        this.user = username;
        this.company = company;
        this.comment = comment;
        this.grade = grade;
    }

    public String getUser(){
        return this.user;
    }
    public String getCompany(){
        return this.company;
    }
    public String getComment(){
        return this.comment;
    }
    public int getGrade(){
        return this.grade;
    }

    @Override
    public boolean equals(Object b){
        if(b == null) return false;
        if(b == this) return true;
        if(b.getClass()!= this.getClass()) return false;

        Avaliacao other = (Avaliacao) b;
        if(!this.user.equals(other.user)) return false;
        if(!this.company.equals(other.company)) return false;
        if(!this.comment.equals(other.comment)) return false;
        if(this.grade != other.grade) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 1;
        ret = ret * 7 + this.user.hashCode();
        ret = ret * 7 + this.company.hashCode();
        ret = ret * 7 + this.comment.hashCode();
        ret = ret * 7 + Integer.valueOf(this.grade).hashCode();
        return ret;
    }

    @Override
    public String toString(){
        return(this.user+"/"+this.company+"/"+this.comment+"/"+this.grade);
    }

    public void insereAvaliacao(Map<String, Object> avaliacao) throws Exception{
        BancoDados db = new BancoDados();
        try{
            String id = avaliacao.get("id").toString();
            Map<String, Object> doc = new HashMap<>();
            doc.put("chave", id);
            Map<String, Object> parametros = new HashMap<>();
            ArrayList<Document> docs = (ArrayList<Document>) db.get("Empresa", parametros);
            for(Document documents: docs){
                int i = 0;
                Document x = docs.get(i);
                String id_x = x.getString("id");
                if(documents.getString(id).equals(id_x)){
                    Document avac = (Document) avaliacao;
                    docs.add(avac);
                }
            }

            Map<String, Object> novo = new HashMap<>();
            novo.put("campo", "avaliacoes");
            novo.put("chave", id);
            novo.put("novoValor", docs);
            db.put("Empresa", novo);

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        //ACHO QUE É ISSO PESSOAL... REVISAR
    }

    public void mediaAvaliacoes(Map<String, Object> avaliacao) throws Exception{
        BancoDados db = new BancoDados();
        try{
            ArrayList<String> avaliacoes = (ArrayList<String>) avaliacao.get("avaliacoes");
            String id = avaliacao.get("id").toString();
            int totalNotas = 0;
            for(String avac : avaliacoes){
                String nota = avac.split(",")[0];
                int n = Integer.parseInt(nota);
                totalNotas += n;
            }

            int media = (totalNotas / avaliacoes.size());

            Map<String, Object> novo = new HashMap<>();
            novo.put("campo", "mediaAvl");
            novo.put("chave", id);
            novo.put("novoValor", media);
            db.put("Empresa", novo);

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        //ACHO QUE É ISSO PESSOAL... REVISAR
    }

}
