package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.example.models.Usuario;

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
                .append("isPhysicalPerson", usuario.getIsPhysicalPerson());
        collection.insertOne(doc);
    }
}