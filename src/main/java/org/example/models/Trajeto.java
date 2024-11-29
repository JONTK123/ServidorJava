package org.example.models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.io.Serializable;

public class Trajeto implements Serializable {

    private String companyCNPJ;
    private String origin;
    private String destination;

    public Trajeto(String companyCNPJ, String origin, String destination){
        this.companyCNPJ = companyCNPJ;
        this.origin = origin;
        this.destination = destination;
    }

    public String getCompanyCNPJ(){
        return this.companyCNPJ;
    }
    public String getOrigin(){
        return this.origin;
    }
    public String getDestination(){
        return this.destination;
    }

    public void addTrajeto(MongoCollection<Document> collection) throws Exception{
        Document novoTrajeto = new Document("cidadePartida", this.origin)
                                    .append("instituicaoDestino", this.destination);

        UpdateResult resultado = collection.updateOne(
                Filters.eq("cnpj", this.companyCNPJ),
                Updates.push("trajetos", novoTrajeto)
        );

        if (resultado.getMatchedCount() > 0) {
            if (resultado.getModifiedCount() > 0) {
                System.out.println("Trajeto adicionado com sucesso.");
            } else {
                System.out.println("Documento encontrado, mas nenhuma alteração foi feita.");
            }
        } else {
            System.out.println("Documento não encontrado.");
            throw new Exception("Documento não encontrado.");
        }
    }

    @Override
    public boolean equals(Object b){
        if(b == null) return false;
        if(b == this) return true;
        if(b.getClass()!= this.getClass()) return false;

        Trajeto other = (Trajeto) b;
        if(!this.companyCNPJ.equals(other.companyCNPJ)) return false;
        if(!this.origin.equals(other.origin)) return false;
        if(!this.destination.equals(other.destination)) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 1;
        ret = ret * 7 + this.companyCNPJ.hashCode();
        ret = ret * 7 + this.origin.hashCode();
        ret = ret * 7 + this.destination.hashCode();
        return ret;
    }

    @Override
    public String toString(){
        return(this.companyCNPJ+"/"+this.origin+"/"+this.destination);
    }

}
