package org.example.database;

import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.example.models.Empresa;
import org.example.models.Usuario;

import static com.mongodb.client.model.Filters.eq;

import java.util.Map;
import java.util.concurrent.Flow;


import static com.mongodb.client.model.Updates.set;
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
        //TODO ADD RETURN NA FUNÇAO

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

    public void post (String collection, Map<String, Object> parametros)

    {
        try

        {
            Gson gson = new Gson();

            MongoCollection<Document> colecao = this.database.getCollection(collection);

            String jsonString = gson.toJson(parametros.get("docNovo"));

            Document doc = Document.parse(jsonString);

            colecao.insertOne(doc);

            System.out.println("Documento inserido com sucesso");



        }
        catch (Exception e)
        {
            System.err.println("Erro ao inserir documento:" + e.getMessage());
        }
    }


    public void put(String collection, Map<String, Object> parametros)

    {
        try
        {

            MongoCollection<Document> colecao = this.database.getCollection(collection);

            String campo = parametros.get("campo").toString();
            String chave = parametros.get("chave").toString();
            Object novoValor = parametros.get("novoValor");

            colecao.updateOne(eq(campo, chave), set(campo, novoValor));

            System.out.println("Documento atualizado com sucesso");

        }
        catch (Exception e)
        {
            System.err.println("Erro ao atualizar documento:" + e.getMessage());
        }
    }


    public void delete(String collection, Map<String, Object> parametros)
    {
        try
        {
            MongoCollection<Document> colecao = this.database.getCollection(collection);
            String campo = parametros.get("campo").toString();
            String chave = parametros.get("chave").toString();

            colecao.deleteOne(eq(campo, chave));

            System.out.println("Documento deletado com sucesso");
        }
        catch (Exception e)
        {
            System.err.println("Erro ao deletar documento:" + e.getMessage());
        }
    }
}
