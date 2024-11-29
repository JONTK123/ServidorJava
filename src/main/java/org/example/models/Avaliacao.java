package org.example.models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.io.Serializable;
import java.util.List;

public class Avaliacao implements Serializable {
    private String cnpj;
    private String nomeUsuario;
    private String comentario;
    private double nota;

    public Avaliacao(String cnpj, String nomeUsuario, String comentario, double nota) throws Exception {
        this.cnpj = cnpj;
        this.nomeUsuario = nomeUsuario;
        this.comentario = comentario;
        if (nota <= 0)
            throw new Exception("Avaliacao deve ser maior que 0");
        this.nota = nota;
    }

    public String getCnpj(){
        return this.cnpj;
    }
    public String getNomeUsuario(){
        return this.nomeUsuario;
    }
    public String getComentario(){
        return this.comentario;
    }
    public double getNota(){
        return this.nota;
    }

    @Override
    public boolean equals(Object b){
        if(b == null) return false;
        if(b == this) return true;
        if(b.getClass()!= this.getClass()) return false;

        Avaliacao other = (Avaliacao) b;
        if(!this.cnpj.equals(other.cnpj)) return false;
        if(!this.nomeUsuario.equals(other.nomeUsuario)) return false;
        if(!this.comentario.equals(other.comentario)) return false;
        if(this.nota != other.nota) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 1;
        ret = ret * 7 + this.cnpj.hashCode();
        ret = ret * 7 + this.nomeUsuario.hashCode();
        ret = ret * 7 + this.comentario.hashCode();
        ret = ret * 7 + Double.valueOf(this.nota).hashCode();
        return ret;
    }

    @Override
    public String toString(){
        return(this.cnpj+"/"+this.nomeUsuario+"/"+this.comentario+"/"+this.nota);
    }

    // Adiciona uma avaliação ao array de avaliações
    public void adicionarAvl(MongoCollection<Document> colecao) throws Exception {

        Document novaAvaliacao = new Document("nomeUsuario", this.nomeUsuario)
                                        .append("comentario", this.comentario)
                                        .append("nota", this.nota);

        // Atualiza o documento da empresa e adiciona a avaliação
        UpdateResult resultado = colecao.updateOne(
                Filters.eq("cnpj", this.cnpj), // Filtro pelo cnpj da empresa
                Updates.push("avaliacoes", novaAvaliacao) // Adiciona a avaliação no array "avaliacoes"
        );

        if (resultado.getMatchedCount() > 0) {
            if (resultado.getModifiedCount() > 0) {
                System.out.println("Avaliação adicionada com sucesso.");
            } else {
                System.out.println("Documento encontrado, mas nenhuma alteração foi feita.");
            }
        } else {
            System.out.println("Documento não encontrado.");
            throw new Exception("Documento não encontrado.");
        }
    }

    // Calcula a média das avaliações
    public void mediaAvaliacoes(MongoCollection<Document> colecao, String cnpj) throws Exception {
        Document empresa = colecao.find(Filters.eq("cnpj", cnpj)).first();

        if (empresa != null) {
            List<Document> avaliacoes = empresa.getList("avaliacoes", Document.class);
            if (avaliacoes != null && !avaliacoes.isEmpty()) {
                double somaNotas = 0;
                for (Document avaliacao : avaliacoes) {
                    somaNotas += avaliacao.getDouble("nota");
                }
                double media = somaNotas / avaliacoes.size();
                // Atualiza o campo "mediaAvl" com a nova média
                colecao.updateOne(Filters.eq("cnpj", cnpj), Updates.set("mediaAvl", media));
                System.out.println("Média de avaliações atualizada: " + media);
            } else {
                System.out.println("Não há avaliações para calcular a média.");
            }
        } else {
            System.out.println("Empresa não encontrada.");
            throw new Exception("Empresa não encontrada.");
        }
    }

}
