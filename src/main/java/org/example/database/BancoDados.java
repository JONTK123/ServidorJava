package org.example.database;

import com.mongodb.client.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;


import static com.mongodb.client.model.Updates.set;

public class BancoDados {
    private MongoClient mongoClient;
    private MongoDatabase database;
    public static final Dotenv dotenv = Dotenv.configure().directory("src").load();
    private static final String mongoURI = dotenv.get("MONGO_URI");
    private static final String databaseName = "PI4";

    public BancoDados() {
        try
        {
            this.mongoClient = MongoClients.create(BancoDados.mongoURI);
            this.database = mongoClient.getDatabase(BancoDados.databaseName);
        }
        catch (Exception e) {
            System.err.println("Erro ao instanciar o banco:" + e.getMessage());
        }
    }


    public String post (String collection, String parametros)

    {
        try

        {

            MongoCollection<Document> colecao = this.database.getCollection(collection);

            Document doc = Document.parse(parametros);

            colecao.insertOne(doc);

            System.out.println("Documento inserido com sucesso");

            this.mongoClient.close();
            return("Documento inserido com sucesso");


        }
        catch (Exception e)
        {
            System.err.println("Erro ao inserir documento:" + e.getMessage());
            this.mongoClient.close();
            return("Erro ao inserir o documento");
        }
    }



}
