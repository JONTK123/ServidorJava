package org.example.database;

import com.google.gson.Gson;
import com.mongodb.client.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Map;


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

    public Object get(String collection, Map<String, Object> parametros)
    {

        try {

            MongoCollection<Document> colecao = this.database.getCollection(collection);

            FindIterable<Document> docList;

            if(parametros==null) docList = colecao.find();

            else
            {
                String chave = (String) parametros.get("chave");
                String valor = (String) parametros.get("valor");
                docList = colecao.find(eq(chave, valor));
            }
            ArrayList<Object> ret = new ArrayList<>();
            for (Document doc : docList) {
                ret.add(doc.toJson());
                System.out.println(doc.toJson());
            }
            this.mongoClient.close();
            return ret;
        }
        catch (Exception e)
        {
            System.err.println("Erro ao buscar docs no banco:" + e.getMessage());
            this.mongoClient.close();
            return "Falha ao recuperar os dados.";
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

            this.mongoClient.close();



        }
        catch (Exception e)
        {
            System.err.println("Erro ao inserir documento:" + e.getMessage());
            this.mongoClient.close();
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

            this.mongoClient.close();

        }
        catch (Exception e)
        {
            System.err.println("Erro ao atualizar documento:" + e.getMessage());
            this.mongoClient.close();
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
            this.mongoClient.close();
        }
        catch (Exception e)
        {
            System.err.println("Erro ao deletar documento:" + e.getMessage());
            this.mongoClient.close();
        }
    }
}
