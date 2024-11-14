package org.example.models;

import com.mongodb.CursorType;
import com.mongodb.ExplainVerbosity;
import com.mongodb.Function;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.cursor.TimeoutMode;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class AvaliacaoTest {

    private MongoCollection<Document> mockCollection;
    private Avaliacao avaliacao;

    @BeforeEach
    public void setup() {
        // Criação do mock da coleção MongoDB
        mockCollection = Mockito.mock(MongoCollection.class);
        // Inicializa um objeto Avaliacao com valores fictícios
        avaliacao = new Avaliacao("123456789", "usuario1", "comentário teste", 5.0);
    }

    @Test
    public void testMediaAvaliacoes() {
        // Criação de avaliações mockadas
        Document avaliacao1 = new Document("nota", 4.0);
        Document avaliacao2 = new Document("nota", 5.0);
        List<Document> avaliacoesList = Arrays.asList(avaliacao1, avaliacao2);

        // Documento mockado da empresa com o array de avaliações
        Document empresaMock = new Document("cnpj", "123456789").append("avaliacoes", avaliacoesList);

        // Mock do comportamento do método find().first() para retornar empresaMock
        when(mockCollection.find(eq("cnpj", "123456789"))).thenReturn(new FindIterableMock(empresaMock));

        // Executa o método mediaAvaliacoes
        assertDoesNotThrow(() -> avaliacao.mediaAvaliacoes(mockCollection, "123456789"));

        // Captura os parâmetros passados para updateOne
        ArgumentCaptor<Bson> filterCaptor = ArgumentCaptor.forClass(Bson.class);
        ArgumentCaptor<Bson> updateCaptor = ArgumentCaptor.forClass(Bson.class);
        verify(mockCollection).updateOne(filterCaptor.capture(), updateCaptor.capture());

        // Verifica os filtros passados para o updateOne
        Bson capturedFilter = filterCaptor.getValue();
        Bson capturedUpdate = updateCaptor.getValue();

        // Verifica se o filtro está correto (espera um filtro pelo cnpj)
        assertTrue(capturedFilter.toString().contains("cnpj"));

        // Verifica se a atualização inclui o valor esperado para a média
        assertTrue(capturedUpdate.toString().contains("mediaAvl"));
        assertTrue(capturedUpdate.toString().contains("4.5"));
    }


    // Classe auxiliar para simular o FindIterable
    private static class FindIterableMock implements FindIterable<Document> {
        private final Document document;

        public FindIterableMock(Document document) {
            this.document = document;
        }

        @Override
        public Document first() {
            return document;
        }

        @Override
        public <U> MongoIterable<U> map(Function<Document, U> function) {
            return null;
        }

        @Override
        public <A extends Collection<? super Document>> A into(A objects) {
            return null;
        }

        // Implementação de métodos obrigatórios, retornando `this` ou valores padrão

        @Override
        public FindIterable<Document> filter(Bson filter) {
            return this;
        }

        @Override
        public FindIterable<Document> limit(int limit) {
            return this;
        }

        @Override
        public FindIterable<Document> skip(int skip) {
            return this;
        }

        @Override
        public FindIterable<Document> maxTime(long maxTime, java.util.concurrent.TimeUnit timeUnit) {
            return this;
        }

        @Override
        public FindIterable<Document> maxAwaitTime(long maxAwaitTime, java.util.concurrent.TimeUnit timeUnit) {
            return this;
        }

        @Override
        public FindIterable<Document> projection(Bson bson) {
            return null;
        }

        @Override
        public FindIterable<Document> sort(Bson bson) {
            return null;
        }

        @Override
        public FindIterable<Document> noCursorTimeout(boolean b) {
            return null;
        }

        @Override
        public FindIterable<Document> partial(boolean b) {
            return null;
        }

        @Override
        public FindIterable<Document> cursorType(CursorType cursorType) {
            return null;
        }

        @Override
        public FindIterable<Document> batchSize(int batchSize) {
            return this;
        }

        @Override
        public FindIterable<Document> collation(com.mongodb.client.model.Collation collation) {
            return this;
        }

        @Override
        public FindIterable<Document> comment(String s) {
            return null;
        }

        @Override
        public FindIterable<Document> comment(BsonValue bsonValue) {
            return null;
        }

        @Override
        public FindIterable<Document> hint(Bson bson) {
            return null;
        }

        @Override
        public FindIterable<Document> hintString(String s) {
            return null;
        }

        @Override
        public FindIterable<Document> let(Bson bson) {
            return null;
        }

        @Override
        public FindIterable<Document> max(Bson bson) {
            return null;
        }

        @Override
        public FindIterable<Document> min(Bson bson) {
            return null;
        }

        @Override
        public FindIterable<Document> returnKey(boolean b) {
            return null;
        }

        @Override
        public FindIterable<Document> showRecordId(boolean b) {
            return null;
        }

        @Override
        public FindIterable<Document> allowDiskUse(Boolean aBoolean) {
            return null;
        }

        @Override
        public FindIterable<Document> timeoutMode(TimeoutMode timeoutMode) {
            return null;
        }

        @Override
        public Document explain() {
            return null;
        }

        @Override
        public Document explain(ExplainVerbosity explainVerbosity) {
            return null;
        }

        @Override
        public <E> E explain(Class<E> aClass) {
            return null;
        }

        @Override
        public <E> E explain(Class<E> aClass, ExplainVerbosity explainVerbosity) {
            return null;
        }

        @Override
        public MongoCursor<Document> iterator() {
            return (MongoCursor<Document>) Arrays.asList(document).iterator();
        }

        @Override
        public MongoCursor<Document> cursor() {
            return null;  // Retorna null se não precisar de cursor
        }
    }
}
