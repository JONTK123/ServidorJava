# Servidor Java - Sistema Cliente-Servidor com MongoDB

## 📋 Descrição do Projeto

Este é um sistema cliente-servidor desenvolvido em Java que implementa uma arquitetura de comunicação via sockets para gerenciar dados de empresas, usuários, trajetos e avaliações. O sistema utiliza MongoDB como banco de dados e oferece suporte para operações CRUD (Create, Read, Update, Delete).

O projeto foi desenvolvido como parte do PI4 (Projeto Integrador 4) e demonstra conceitos de programação distribuída, comunicação em rede, e integração com banco de dados NoSQL.

## 🚀 Tecnologias Utilizadas

- **Java 17** - Linguagem de programação principal
- **Maven** - Gerenciamento de dependências e build
- **MongoDB** - Banco de dados NoSQL
- **Socket Programming** - Comunicação cliente-servidor
- **Gson** - Serialização/deserialização JSON
- **Dotenv** - Gerenciamento de variáveis de ambiente
- **JUnit & Mockito** - Framework de testes unitários
- **SLF4J** - Framework de logging

## 📦 Dependências Principais

```xml
- MongoDB Driver (5.2.0)
- Gson (2.11.0)
- Dotenv Java (2.2.4)
- JUnit Jupiter (testes)
- Mockito (mocks para testes)
- SLF4J (logging)
```

## 🔧 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

1. **Java JDK 17** ou superior
   ```bash
   java -version
   ```

2. **Maven** (versão 3.6 ou superior)
   ```bash
   mvn -version
   ```

3. **MongoDB** - Acesso a uma instância MongoDB (local ou MongoDB Atlas)

4. **Git** (para clonar o repositório)

## 📥 Instalação

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/JONTK123/ServidorJava.git
   cd ServidorJava
   ```

2. **Instale as dependências:**
   ```bash
   mvn clean install
   ```

3. **Configure as variáveis de ambiente:**
   
   Crie um arquivo `.env` no diretório `src/` com a seguinte estrutura:
   ```properties
   MONGO_URI=mongodb+srv://seu_usuario:sua_senha@seu_cluster.mongodb.net/
   ```
   
   ⚠️ **Importante:** Substitua `seu_usuario`, `sua_senha` e `seu_cluster` pelas suas credenciais do MongoDB.

## ⚙️ Configuração

### Configuração do Banco de Dados

O sistema utiliza um banco de dados MongoDB chamado `PI4` com as seguintes collections:

- **Usuario** - Armazena informações de usuários
- **Empresa** - Armazena informações de empresas
- **Avaliações** - Avaliações feitas por usuários
- **Trajetos** - Rotas/trajetos das empresas

### Porta do Servidor

A porta padrão do servidor é `3000`. Você pode alterá-la ao iniciar o servidor passando a porta como argumento.

## 🎯 Como Executar

### 1. Executar o Servidor

Para iniciar o servidor na porta padrão (3000):

```bash
mvn exec:java -Dexec.mainClass="org.example.servidor.Servidor"
```

Para iniciar o servidor em uma porta específica:

```bash
mvn exec:java -Dexec.mainClass="org.example.servidor.Servidor" -Dexec.args="8080"
```

Após iniciar, você verá a mensagem:
```
STARTOU ACEITADORA DE CONEXAO
O servidor esta ativo! Para desativa-lo,
use o comando "desativar"
>
```

Para desligar o servidor, digite `desativar` no console.

### 2. Executar o Cliente

Em outro terminal, execute o cliente:

```bash
mvn exec:java -Dexec.mainClass="org.example.cliente.Cliente"
```

Para conectar a um servidor específico:

```bash
mvn exec:java -Dexec.mainClass="org.example.cliente.Cliente" -Dexec.args="localhost 8080"
```

O cliente irá:
1. Conectar-se ao servidor
2. Enviar uma requisição GET para buscar dados de usuários
3. Receber e exibir os resultados
4. Aguardar o comando "desligar" para desconectar

## 🏗️ Arquitetura do Sistema

### Estrutura de Pacotes

```
org.example/
├── cliente/                      # Componentes do cliente
│   ├── Cliente.java             # Classe principal do cliente
│   ├── PedidoDeOperacao.java    # Requisições de operações CRUD
│   ├── PedidoDeResultado.java   # Requisições de resultados
│   ├── PedidoParaSair.java      # Requisição de desconexão
│   └── TratadorDeComunicadoResultado.java
├── servidor/                     # Componentes do servidor
│   ├── Servidor.java            # Classe principal do servidor
│   ├── AceitadoraDeConexao.java # Aceita conexões de clientes
│   ├── SupervisoraDeConexao.java # Supervisiona conexões ativas
│   ├── ComunicadoDeResultado.java
│   └── ComunicadoDeDesligamento.java
├── database/                     # Camada de acesso ao banco
│   └── BancoDados.java          # Operações no MongoDB
├── models/                       # Modelos de dados
│   ├── Usuario.java
│   ├── Empresa.java
│   ├── Avaliacao.java
│   ├── Trajeto.java
│   ├── Endereco.java
│   └── Data.java
├── Comunicado.java              # Classe base para comunicação
├── Parceiro.java                # Gerencia comunicação cliente-servidor
└── Teclado.java                 # Utilitário para entrada de teclado
```

### Componentes Principais

#### Servidor
- **Servidor.java**: Ponto de entrada do servidor, gerencia o ciclo de vida
- **AceitadoraDeConexao.java**: Thread que aceita novas conexões de clientes
- **SupervisoraDeConexao.java**: Monitora e gerencia conexões ativas
- **Parceiro.java**: Representa uma conexão com um cliente

#### Cliente
- **Cliente.java**: Ponto de entrada do cliente, estabelece conexão
- **PedidoDeOperacao.java**: Envia requisições CRUD ao servidor
- **TratadorDeComunicado*.java**: Threads que processam respostas do servidor

#### Database
- **BancoDados.java**: Implementa operações CRUD no MongoDB
  - `getUser()` - Busca usuário por email
  - `getAll()` - Busca todos os documentos de uma collection
  - Suporte para operações em múltiplas collections

## 📊 Modelos de Dados

### Usuario
```java
{
    name: String,
    email: String,
    birthday: String,
    cpf: String
}
```

### Empresa
```java
{
    name: String,
    email: String,
    cnpj: String,
    telefone: String,
    endereco: Endereco,
    mediaAvl: Double,
    avaliacoes: ArrayList<Object>,
    trajetos: ArrayList<Object>,
    tipoUsuario: String
}
```

### Avaliacao
```java
{
    cnpj: String,           // CNPJ da empresa avaliada
    nomeUsuario: String,    // Nome do usuário que avaliou
    comentario: String,     // Comentário da avaliação
    nota: Double           // Nota (deve ser > 0)
}
```

### Trajeto
```java
{
    companyCNPJ: String,    // CNPJ da empresa
    origin: String,         // Cidade de partida
    destination: String     // Instituição de destino
}
```

### Endereco
```java
{
    logradouro: String,
    numero: String,
    cidade: String,
    estado: String,
    cep: String
}
```

## 🔌 Operações Disponíveis

### Operações CRUD

O cliente pode enviar requisições usando `PedidoDeOperacao`:

```java
// GET - Buscar todos os usuários
servidor.receba(new PedidoDeOperacao("GET", "Usuario"));

// GET - Buscar usuário específico (com parâmetros)
Map<String, Object> params = new HashMap<>();
params.put("email", "usuario@example.com");
servidor.receba(new PedidoDeOperacao("GET", "Usuario", params));

// Outros métodos disponíveis: POST, PUT, DELETE
```

### Operações de Avaliação

```java
Avaliacao avaliacao = new Avaliacao(cnpj, nomeUsuario, comentario, nota);
avaliacao.adicionarAvl(collection);  // Adiciona avaliação
avaliacao.mediaAvaliacoes(collection, cnpj);  // Calcula média
```

### Operações de Trajeto

```java
Trajeto trajeto = new Trajeto(companyCNPJ, origin, destination);
trajeto.addTrajeto(collection);  // Adiciona trajeto
```

## 🧪 Executar Testes

Para executar os testes unitários:

```bash
mvn test
```

Para executar testes com relatório detalhado:

```bash
mvn test -Dtest=AvaliacaoTest
```

## 📝 Exemplos de Uso

### Exemplo 1: Cliente Simples

```java
// Conectar ao servidor
Socket conexao = new Socket("localhost", 3000);
ObjectOutputStream transmissor = new ObjectOutputStream(conexao.getOutputStream());
ObjectInputStream receptor = new ObjectInputStream(conexao.getInputStream());
Parceiro servidor = new Parceiro(conexao, receptor, transmissor);

// Buscar usuários
servidor.receba(new PedidoDeOperacao("GET", "Usuario"));
servidor.receba(new PedidoDeResultado());

// Aguardar resposta...
```

### Exemplo 2: Adicionar Avaliação

```java
MongoCollection<Document> empresas = database.getCollection("Empresa");
Avaliacao avaliacao = new Avaliacao(
    "12.345.678/0001-90",  // CNPJ
    "João Silva",           // Nome do usuário
    "Excelente serviço!",   // Comentário
    4.5                     // Nota
);
avaliacao.adicionarAvl(empresas);
avaliacao.mediaAvaliacoes(empresas, "12.345.678/0001-90");
```

## 🛠️ Compilação e Build

Para compilar o projeto:

```bash
mvn compile
```

Para criar um JAR executável:

```bash
mvn package
```

O arquivo JAR será gerado em `target/SERVIDORMAVEN-1.0-SNAPSHOT.jar`

## 🔐 Segurança

⚠️ **Observações Importantes:**

1. Nunca commite o arquivo `.env` com credenciais reais
2. Use variáveis de ambiente para produção
3. O arquivo `.env` está no `.gitignore` para evitar commits acidentais
4. Para produção, considere implementar autenticação e criptografia SSL/TLS

## 🐛 Troubleshooting

### Problema: Erro ao conectar ao MongoDB
```
Solução: Verifique se:
- As credenciais no arquivo .env estão corretas
- Seu IP está na whitelist do MongoDB Atlas
- A string de conexão está no formato correto
```

### Problema: Porta já em uso
```
Solução: 
- Use uma porta diferente ao iniciar o servidor
- Ou libere a porta 3000: lsof -ti:3000 | xargs kill -9
```

### Problema: Cliente não consegue conectar
```
Solução:
- Verifique se o servidor está rodando
- Confirme se está usando a mesma porta
- Verifique configurações de firewall
```

## 📚 Estrutura do Banco de Dados

### Database: PI4

#### Collection: Usuario
```javascript
{
  name: "João Silva",
  email: "joao@example.com",
  birthday: "01/01/1990",
  cpf: "123.456.789-00"
}
```

#### Collection: Empresa
```javascript
{
  name: "Empresa Exemplo",
  email: "contato@empresa.com",
  cnpj: "12.345.678/0001-90",
  telefone: "(11) 98765-4321",
  endereco: {
    logradouro: "Rua Exemplo",
    numero: "123",
    cidade: "São Paulo",
    estado: "SP",
    cep: "01234-567"
  },
  mediaAvl: 4.5,
  avaliacoes: [...],
  trajetos: [...],
  tipoUsuario: "empresa"
}
```

## 🤝 Contribuindo

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto é parte de um trabalho acadêmico (PI4).

## 👥 Autores

- Equipe do Projeto PI4

## 📧 Contato

Para dúvidas ou sugestões, abra uma issue no repositório.

---

**Nota:** Este é um projeto acadêmico desenvolvido para fins educacionais.
