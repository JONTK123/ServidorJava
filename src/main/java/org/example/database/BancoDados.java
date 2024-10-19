package org.example.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.example.models.Empresa;
import org.example.models.Usuario;

public class BancoDados {
    private MongoDatabase database;
    public static final Dotenv dotenv = Dotenv.configure().directory("src").load();
    private static final String mongoURI = dotenv.get("MONGO_URI");
    private static final String databaseName = "Usuario";

    public BancoDados() {
        MongoClient mongoClient = MongoClients.create(BancoDados.mongoURI);
        this.database = mongoClient.getDatabase(BancoDados.databaseName);
    }

    public void get(String collection)
    {
        MongoCollection<Document> usuarios = this.database.getCollection(collection);
        System.out.println(usuarios);
    }

    public void addUsuario(Usuario user) {
        MongoCollection<Document> collection = database.getCollection("Usuarios");

        Document doc = new Document("nome", user.getName())
                .append("email", user.getEmail())
                .append("birthday", user.getData())
                .append("password", user.getPassword());
        collection.insertOne(doc);
    }

    public void addEmpresa(Empresa company) {
        MongoCollection<Document> collection = database.getCollection("Empresas");

        Document doc = new Document("nome", company.getName())
                .append("cnpj", company.getEmail())
                .append("email", company.getCNPJ())
                .append("password", company.getPassword())
                .append("cep", company.getCEP())
                .append("address", company.getAddress())
                .append("phoneNumber", company.getPhone())
                .append("qtd_reviews", company.getQtdeReviews())
                .append("av_grade", company.getAvGrade());
        collection.insertOne(doc);
    }

    //lOGICA PARA ATUALIZAR OS CAMPOS QTD AVALIACOES E NOTA MEDIA DA COLECAO EMPRESAS

    //ARRUMAR ESSE MÉTODO - ANTES DE CONTINUAR
//    public void avaliarEmpresa(String nomeUsuario, String nomeEmpresa, int nota, String comentario) {
//        MongoCollection<Document> avaliacoesCollection = database.getCollection("Avaliacoes");
//        MongoCollection<Document> empresasCollection = database.getCollection("Empresas");
//
//        Document avaliacaoDoc = new Document("nomeUsuario", nomeUsuario)
//                .append("nomeEmpresa", nomeEmpresa)
//                .append("nota", nota)
//                .append("comentario", comentario);
//        avaliacoesCollection.insertOne(avaliacaoDoc);
//
//        Document empresaDoc = empresasCollection.find(new Document("nome", nomeEmpresa)).first();
//        if (empresaDoc != null) {
//            int qtdAvaliacoes = empresaDoc.getInteger("qtdAvaliacoes", 0);
//            double notaMedia = empresaDoc.getDouble("notaMedia");
//
//            qtdAvaliacoes += 1;
//            notaMedia = ((notaMedia * (qtdAvaliacoes - 1)) + nota) / qtdAvaliacoes;
//
//            Document update = new Document("$set", new Document("qtdAvaliacoes", qtdAvaliacoes)
//                    .append("notaMedia", notaMedia));
//            empresasCollection.updateOne(new Document("nome", nomeEmpresa), update);
//        }
//    }
}
