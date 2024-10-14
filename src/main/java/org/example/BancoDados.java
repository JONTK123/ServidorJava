package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.example.cliente.Usuario;

public class BancoDados {
    private MongoDatabase database;

    public BancoDados(String uri, String dbName) {
        MongoClient mongoClient = MongoClients.create(uri);
        this.database = mongoClient.getDatabase(dbName);
    }

    public void addUsuario(Usuario usuario) {
        MongoCollection<Document> collection = database.getCollection("Usuarios");

        Document doc = new Document("nome", usuario.getNome())
                .append("email", usuario.getEmail())
                .append("dataNascimento", usuario.getDataNascimento())
                .append("senha", usuario.getSenha());

        collection.insertOne(doc);
    }

    public boolean atualizarSenha(String email, String novaSenha) {
        MongoCollection<Document> collection = database.getCollection("Usuarios");
        Bson filtro = Filters.eq("email", email);
        Bson atualizacao = Updates.set("senha", novaSenha);
        Document resultado = collection.findOneAndUpdate(filtro, atualizacao);
        return resultado != null;
    }

}