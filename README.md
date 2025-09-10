# Sistema de Vendas - Avaliação WMW

Este projeto é uma aplicação Full-Stack desenvolvida como parte de uma atividade avaliativa. O sistema simula uma plataforma web de vendas B2B (jurídico) simplificada para a empresa de cosméticos "Bella Face", construído com Java/Spring Boot no backend e Vue.js no frontend.

## 📜 Visão Geral

A aplicação permite que clientes autenticados visualizem uma lista de produtos, adicionem itens a um carrinho de compras e finalizem um pedido. O sistema é dividido em duas partes principais:
*   **Backend:** Uma API RESTful robusta construída com Java e Spring Boot, responsável por toda a lógica de negócio, persistência de dados e segurança.
*   **Frontend:** Uma Aplicação de Página Única (SPA) reativa e moderna, construída com Vue.js, que consome a API do backend para fornecer a interface do usuário.

---

## ✨ Funcionalidades Implementadas

-   **Autenticação de Cliente:** Endpoint seguro para validação de login e senha.
-   **Listagem de Produtos:** Exibição dos produtos disponíveis para compra após a autenticação.
-   **Carrinho de Compras (Gerenciado no Frontend):**
    -   Adição e remoção de produtos de forma instantânea.
    -   Cálculo de subtotais e total em tempo real na interface.
    -   O estado do carrinho e o login do usuário persistem no navegador (usando `localStorage`).
-   **Finalização de Pedido:**
    -   Envio do carrinho para o backend para criar um pedido formal em uma única transação.
    -   Capacidade de adicionar comentários ao pedido.
-   **Validações de Regras de Negócio:**
    -   O sistema impede a criação de pedidos aos sábados e domingos.
    -   A integridade dos preços é garantida, com todos os cálculos sendo realizados e validados no backend.
-   **Tratamento de Erros Centralizado:** Respostas de API claras para diferentes cenários de erro (ex: credenciais inválidas, produto não encontrado, violação de regras de negócio).

---

## 🛠️ Tech Stack (Tecnologias Utilizadas)

| Backend                                | Frontend                       |
| -------------------------------------- | ------------------------------ |
| **Java 17**                            | **Vue.js 3** (Composition API) |
| **Spring Boot 3.x**                    | **Vite** (Build Tool)          |
| Spring Web                             | **Pinia** (Gerenciamento de Estado) |
| Spring Data JPA                        | **Vue Router** (Roteamento)    |
| **PostgreSQL** (Banco de Dados)        | **Axios** (Cliente HTTP)       |
| **Maven** (Gerenciador de Dependências) | **JavaScript ES6+**            |
|                                        | HTML5 & CSS3                   |

---

## 🏗️ Arquitetura do Projeto

### Backend
A API foi desenvolvida seguindo uma arquitetura em camadas para garantir a separação de responsabilidades:
-   **`Controllers`**: Responsáveis por expor os endpoints REST e traduzir o mundo HTTP para o Java. Contém um `ControllerExceptionHandler` para tratamento global de erros.
-   **`Services`**: Onde reside toda a lógica de negócio da aplicação.
-   **`Repositories`**: Camada de acesso a dados, utilizando Spring Data JPA para interagir com o banco de dados.
-   **`Entities`**: Objetos que mapeiam as tabelas do banco de dados (ex: `Cliente`, `Pedido`, `PedidoProduto`).
-   **`DTOs`**: Objetos para transferência de dados, com uma clara separação entre `RequestDTOs` (entrada) e `ResponseDTOs` (saída).
-   **`Config`**: Configurações globais da aplicação, como as permissões de CORS para o frontend.

### Frontend
A interface foi construída como uma SPA (Single Page Application) com uma estrutura organizada:
-   **`views`**: Componentes que representam as "páginas" principais da aplicação (`LoginView`, `LojaView`).
-   **`components`**: Peças de UI reutilizáveis (`CabecalhoAplicativo`, `ListaProdutos`, `CarrinhoCompras`).
-   **`router`**: Gerencia a navegação entre as views e protege rotas que exigem autenticação através de uma guarda de rota.
-   **`stores`**: Centraliza o estado global da aplicação com o Pinia (dados do usuário, itens do carrinho).
-   **`services`**: Encapsula a lógica de comunicação com a API backend em um `apiService` central.

---

## 🚀 Como Executar o Projeto

Siga os passos abaixo para configurar e executar a aplicação em seu ambiente local.

### Pré-requisitos
-   **Java JDK 17** ou superior.
-   **Maven 3.x**
-   **Node.js 18.x** ou superior (com npm).
-   Uma instância do **PostgreSQL** em execução.
-   Um editor de código, como **VS Code** (recomendado com o "Extension Pack for Java").

### 1. Configuração do Backend
1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/LirouVictor/BellaFace.git
    cd BellaFace/backend
2.  **Crie o Banco de Dados:** Crie uma base de dados no PostgreSQL (ex: `wmw_vendas_db`).

3.  **Configure a Conexão e Inicialização:**
    -   Abra o arquivo `backend/src/main/resources/application.properties`.
    -   Altere as seguintes propriedades com as suas credenciais do PostgreSQL:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/wmw_vendas_db
        spring.datasource.username=seu_usuario_postgres
        spring.datasource.password=sua_senha_postgres
        ```
    -   Este projeto já está configurado para **criar as tabelas e popular o banco de dados automaticamente** ao iniciar, utilizando o arquivo `data.sql`. As seguintes propriedades garantem esse comportamento:
        ```properties
        spring.jpa.hibernate.ddl-auto=create-drop
        spring.sql.init.mode=always
        spring.jpa.defer-datasource-initialization=true
        ```
4.  **Execute a Aplicação:**
    Dentro da pasta `backend`, execute o comando:
    ```bash
    ./mvnw spring-boot:run
    ```
    O backend estará em execução em `http://localhost:8080`.

### 2. Configuração do Frontend
1.  **Abra um novo terminal** e navegue até a pasta do frontend:
    ```bash
    cd ../frontend 
    ```
2.  **Instale as dependências:**
    ```bash
    npm install
    ```
3.  **Execute o Servidor de Desenvolvimento:**
    ```bash
    npm run dev
    ```
    O frontend estará acessível em `http://localhost:5173`.

### 3. Acessando a Aplicação
Abra seu navegador em **`http://localhost:5173`**. Você será direcionado para a tela de login. Use as credenciais definidas no arquivo `data.sql` para acessar (ex: `cliente1` / `senha123`).
