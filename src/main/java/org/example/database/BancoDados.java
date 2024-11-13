package org.example.database;

import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.example.models.Avaliacao;

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
        try {
            this.mongoClient = MongoClients.create(BancoDados.mongoURI);
            this.database = mongoClient.getDatabase(BancoDados.databaseName);
        }
        catch (Exception e) {
            System.err.println("Erro ao instanciar o banco:" + e.getMessage());
        }
    }

    public String getUserName(String collection, Map<String, Object> parametros) {
        try{
            MongoCollection<Document> colecao = this.database.getCollection(collection);
            String email = (String) parametros.get("email");
            System.out.println(email);
            Document doc = colecao.find(eq("email", email)).first();

            if (doc != null) {
                String nome = (String) doc.get("name");
                System.out.println(nome);
                return nome;
            } else {
                return "Usuário não encontrado";
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar nome do usuário: " + e.getMessage());
            return "Erro ao buscar nome do usuário";
        }
    }

    public Object getEmpresas(String collection, Map<String, Object> parametros) {
        try {
            MongoCollection<Document> colecao = this.database.getCollection(collection);
            FindIterable<Document> docList;

            if(parametros==null || parametros.isEmpty()) docList = colecao.find();

            else {
                // Extrai os parâmetros 'cidadePartida' e 'instituicaoDestino'
                String cidadePartida = (String) parametros.get("cidadePartida");
                System.out.println(cidadePartida);
                String instituicaoDestino = (String) parametros.get("instituicaoDestino");
                System.out.println(instituicaoDestino);
                // Cria expressões regex que permitem variantes de capitalização e hífen
                String regexCidadePartida = "(?i)" + cidadePartida.replaceAll("[- ]", "[- ]?");
                String regexInstituicaoDestino = "(?i)" + instituicaoDestino.replaceAll("[- ]", "[- ]?");

                // Cria filtros regex para buscar nos objetos dentro do array `trajetos`
                Bson filtro = Filters.elemMatch("trajetos", Filters.and(
                        Filters.regex("cidadePartida", regexCidadePartida),
                        Filters.regex("instituicaoDestino", regexInstituicaoDestino)
                ));

                // Aplica o filtro à consulta
                docList = colecao.find(filtro);
            }

            ArrayList<Object> lista = new ArrayList<Object>();
            for (Document doc : docList) {
                lista.add(doc.toJson());
                System.out.println(doc.toJson());  //Remover isso depois
            }
            this.mongoClient.close();
            System.out.println(lista);
            return lista; //está retornando formato de array list Object mesmo tendo o conteúdo em json.
        }
        catch (Exception e) {
            System.err.println("Erro ao buscar docs no banco:" + e.getMessage());
            this.mongoClient.close();
            return null;
        }
    }

    public String inserirUsuarioEmpresa (String collection, Map<String, Object> parametros) {
        try {
            Gson gson = new Gson();

            MongoCollection<Document> colecao = this.database.getCollection(collection);

            String jsonString = gson.toJson(parametros.get("docNovo"));

            Document doc = Document.parse(jsonString);

            colecao.insertOne(doc);

            System.out.println("Documento inserido com sucesso");

            this.mongoClient.close();
            return("Documento inserido com sucesso");
        }
        catch (Exception e) {
            System.err.println("Erro ao inserir documento:" + e.getMessage());
            this.mongoClient.close();
            return("Erro ao inserir o documento");
        }
    }

    public String addAvl(String collection, Map<String, Object> parametros) {
        try {
            Gson gson = new Gson();

            MongoCollection<Document> colecao = this.database.getCollection(collection);

            // Cria o objeto Avaliacao
            Map<String, Object> docNovo = (Map<String, Object>) parametros.get("docNovo");
            Avaliacao avaliacao = new Avaliacao(
                    (String) docNovo.get("cnpj"),
                    (String) docNovo.get("nomeUsuario"),
                    (String) docNovo.get("comentario"),
                    (double) docNovo.get("nota")
            );

            // Adiciona a avaliação ao array de avaliações da empresa
            avaliacao.adicionarAvl(colecao);

            // Após adicionar, calcula a média das avaliações
            avaliacao.mediaAvaliacoes(colecao, avaliacao.getCnpj());

            return "Avaliação adicionada e média atualizada com sucesso.";
        } catch (Exception e) {
            System.err.println("Erro ao adicionar avaliação: " + e.getMessage());
            return "Erro ao adicionar a avaliação.";
        } finally {
            this.mongoClient.close();
        }
    }



    public String delete(String collection, Map<String, Object> parametros) {
        try {
            MongoCollection<Document> colecao = this.database.getCollection(collection);
            String campo = parametros.get("campo").toString();
            String chave = parametros.get("chave").toString();

            colecao.deleteOne(eq(campo, chave));

            System.out.println("Documento deletado com sucesso");
            this.mongoClient.close();
            return("Documento deletado com sucesso");
        }
        catch (Exception e) {
            System.err.println("Erro ao deletar documento:" + e.getMessage());
            this.mongoClient.close();
            return("Erro ao deletar o documento");
        }
    }
}
