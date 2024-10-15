package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.models.Usuario;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class BancoDados extends Comunicado {
    private MongoCollection<Usuario> collection;

    public BancoDados(String uri, String dbName) {
        // Configuração do CodecRegistry para suportar POJOs
        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        // Configuração das definições do MongoClient
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();

        // Criação do MongoClient com as configurações acima
        MongoClient mongoClient = MongoClients.create(settings);

        // Selecionar o banco de dados
        MongoDatabase database = mongoClient.getDatabase(dbName);

        // Selecionar a coleção e informar a classe POJO
        collection = database.getCollection("Usuarios", Usuario.class);
    }

    // Método para adicionar um usuário
    public void addUsuario(Usuario usuario) {
        collection.insertOne(usuario);
    }
}