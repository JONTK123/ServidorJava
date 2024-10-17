package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.cliente.PedidoAddEmpresa;

public class BancoDados {
    private MongoDatabase database;

    public BancoDados(String uri, String dbName) {
        MongoClient mongoClient = MongoClients.create(uri);
        this.database = mongoClient.getDatabase(dbName);
    }

    public void addUsuario(String nome, String email, String dataNascimento, boolean isPhysicalPerson) {
        MongoCollection<Document> collection = database.getCollection("Usuarios");

        Document doc = new Document("nome", nome)
                .append("email", email)
                .append("dataNascimento", dataNascimento)
                .append("isPhysicalPerson", isPhysicalPerson);
        collection.insertOne(doc);
    }

    public void addEmpresa(String nome, String cnpj, String email, String cep, String endereco, String telefone) {
        MongoCollection<Document> collection = database.getCollection("Empresas");

        Document doc = new Document("nome", nome)
                .append("cnpj", cnpj)
                .append("email", email)
                .append("cep", cep)
                .append("endereco", endereco)
                .append("telefone", telefone);
        collection.insertOne(doc);
    }

    //lOGICA PARA ATUALIZAR OS CAMPOS QTD AVALIACOES E NOTA MEDIA DA COLECAO EMPRESAS
    public void avaliarEmpresa(String nomeUsuario, String nomeEmpresa, int nota, String comentario) {
        MongoCollection<Document> avaliacoesCollection = database.getCollection("Avaliacoes");
        MongoCollection<Document> empresasCollection = database.getCollection("Empresas");

        Document avaliacaoDoc = new Document("nomeUsuario", nomeUsuario)
                .append("nomeEmpresa", nomeEmpresa)
                .append("nota", nota)
                .append("comentario", comentario);
        avaliacoesCollection.insertOne(avaliacaoDoc);

        Document empresaDoc = empresasCollection.find(new Document("nome", nomeEmpresa)).first();
        if (empresaDoc != null) {
            int qtdAvaliacoes = empresaDoc.getInteger("qtdAvaliacoes", 0);
            double notaMedia = empresaDoc.getDouble("notaMedia");

            qtdAvaliacoes += 1;
            notaMedia = ((notaMedia * (qtdAvaliacoes - 1)) + nota) / qtdAvaliacoes;

            Document update = new Document("$set", new Document("qtdAvaliacoes", qtdAvaliacoes)
                    .append("notaMedia", notaMedia));
            empresasCollection.updateOne(new Document("nome", nomeEmpresa), update);
        }
    }
}
