import axios from 'axios';

// Cria uma instância do Axios com configurações padrão
const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api', // URL base da API
    headers: {
        'Content-Type': 'application/json'
    }
});

// Exporta um objeto com todas as funções que nossa aplicação precisará
export default {
    
    /**
     * Envia as credenciais para o endpoint de login.
     * @param {string} login 
     * @param {string} senha 
     * @returns {Promise<Object>} Os dados do cliente logado (id, nome).
     */
    async login(login, senha) {
        try {
            const response = await apiClient.post('/clientes/login', { login, senha });
            return response.data;
        } catch (error) {
            console.error("Erro na autenticação:", error.response.data);
            throw error; // Re-lança o erro para o componente que chamou poder tratar
        }
    },

    /**
     * Busca a lista completa de produtos.
     * @returns {Promise<Array>} Uma lista de produtos.
     */
    async buscarProdutos() {
        try {
            const response = await apiClient.get('/produtos');
            return response.data;
        } catch (error) {
            console.error("Erro ao buscar produtos:", error.response.data);
            throw error;
        }
    },

    /**
     * Envia um novo pedido para ser criado.
     * @param {Object} pedidoRequest O corpo da requisição (contém itens e comentários).
     * @param {number} clienteId O ID do cliente que está fazendo o pedido.
     * @returns {Promise<Object>} A resposta do pedido criado (o "recibo").
     */
    async criarPedido(pedidoRequest, clienteId) {
        try {
            // Adiciona o clienteId como um parâmetro de query na URL
            const response = await apiClient.post(`/pedidos?clienteId=${clienteId}`, pedidoRequest);
            return response.data;
        } catch (error) {
            console.error("Erro ao criar pedido:", error.response.data);
            throw error;
        }
    }
};