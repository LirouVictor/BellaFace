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
Para garantir a compatibilidade, certifique-se de que seu ambiente atende aos seguintes requisitos:

-   **Java JDK:** Versão **17** ou superior. O projeto está configurado para compilar com a versão 17. Você pode verificar sua versão com `java -version`.
-   **Node.js:** Versão **LTS** (Long-Term Support) recomendada, como **18.x** ou **20.x**. Você pode verificar sua versão com `node -v`.
    -   *Dica: Use uma ferramenta como o [nvm](https://github.com/nvm-sh/nvm) (Node Version Manager) para gerenciar facilmente múltiplas versões do Node.js.*
-   **Banco de Dados:** Uma instância do **PostgreSQL** em execução.
-   **Git:** Para clonar o repositório.

*(**Observação:** Não é necessário ter o Maven instalado globalmente, pois o projeto utiliza o Maven Wrapper (`mvnw`), que baixará a versão correta automaticamente.)*

### 1. Configuração do Backend
1.  **Clone o repositório e navegue até a pasta do backend:**
    ```bash
    git clone https://github.com/LirouVictor/BellaFace.git
    cd nome-do-repositorio/backend
    ```
2.  **Crie o Banco de Dados:** Crie uma base de dados no PostgreSQL (ex: `wmw_vendas_db`).

3.  **Configure a Conexão:**
    -   Abra o arquivo `backend/src/main/resources/application.properties`.
    -   Altere as propriedades `spring.datasource.url`, `spring.datasource.username`, e `spring.datasource.password` com suas credenciais do PostgreSQL.

4.  **Execute a Aplicação:**
    Use o Maven Wrapper para compilar e rodar o projeto. Ele cuidará de tudo para você.
    
    *   No Linux/macOS:
        ```bash
        ./mvnw spring-boot:run
        ```
    *   No Windows (CMD ou PowerShell):
        ```bash
        .\mvnw.cmd spring-boot:run
        ```
    O backend estará em execução em `http://localhost:8080`. O banco de dados será criado e populado automaticamente na primeira inicialização.

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
Abra seu navegador em **`http://localhost:5173`**. Use as credenciais definidas no arquivo `data.sql` para acessar (ex: `cliente1` / `senha123`).

---
