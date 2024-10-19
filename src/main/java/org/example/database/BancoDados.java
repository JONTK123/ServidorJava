package org.example.database;

import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.example.models.Empresa;
import org.example.models.Usuario;
import static com.mongodb.client.model.Filters.eq; // Importa o método 'eq'

import java.util.concurrent.Flow;

import static javax.management.Query.eq;

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

    public void get(String collection)
    {

        try {

            MongoCollection<Document> colecao = this.database.getCollection(collection);

            Bson projectionFields = Projections.fields(
                    Projections.include("name", "email", "birthday", "password"),
                    Projections.excludeId());

            for (Document doc : colecao.find().projection(projectionFields)) {
                System.out.println(doc.toJson());
            }
        }
        catch (Exception e)
        {
            System.err.println("Erro ao buscar docs no banco:" + e.getMessage());
        }

    }


}
