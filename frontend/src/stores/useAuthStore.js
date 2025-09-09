import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import apiService from '../services/apiService';
import router from '../router';

export const useAuthStore = defineStore('auth', () => {
    const cliente = ref(JSON.parse(localStorage.getItem('cliente')) || null);
    const carrinho = ref(JSON.parse(localStorage.getItem('carrinho')) || []);

    const estaLogado = computed(() => !!cliente.value);
    const clienteId = computed(() => cliente.value ? cliente.value.id : null);

    const totalCarrinho = computed(() => {
        return carrinho.value.reduce((total, item) => {
            return total + (item.preco * item.quantidade);
        }, 0);
    });

    const quantidadeTotalItens = computed(() => {
        return carrinho.value.reduce((total, item) => total + item.quantidade, 0);
    });

    /**
     * @description Tenta autenticar o cliente e armazena os dados em caso de sucesso.
     * @param {string} login 
     * @param {string} senha 
     */
    async function login(login, senha) {
        const dadosCliente = await apiService.login(login, senha);
        cliente.value = dadosCliente;
        localStorage.setItem('cliente', JSON.stringify(dadosCliente));
        router.push({ name: 'loja' }); // Redireciona para a página principal após o login
    }

    function logout() {
        cliente.value = null;
        carrinho.value = [];
        localStorage.removeItem('cliente');
        localStorage.removeItem('carrinho');
        router.push('/login'); // Redireciona para a página de login
    }

    /**
     * @description Adiciona um produto ao carrinho ou incrementa sua quantidade.
     * @param {object} produto 
     */
    function adicionarAoCarrinho(produto) {
        adicionarItemComQuantidade(produto, 1); 
    }

    /**
     * @description Adiciona um produto com uma quantidade específica.
     * @param {object} produto 
     * @param {number} quantidade 
     */
    function adicionarItemComQuantidade(produto, quantidade) {
        if (quantidade <= 0) return; // Validação de segurança  
        const itemExistente = carrinho.value.find(item => item.id === produto.id);
        if (itemExistente) {
            itemExistente.quantidade += quantidade;
        } else {
            carrinho.value.push({ ...produto, quantidade: quantidade });
        }
        localStorage.setItem('carrinho', JSON.stringify(carrinho.value));
    }

    /**
     * @description Remove um produto completamente do carrinho.
     * @param {number} produtoId 
     */
    function removerDoCarrinho(produtoId) {
        carrinho.value = carrinho.value.filter(item => item.id !== produtoId);
        localStorage.setItem('carrinho', JSON.stringify(carrinho.value));
    }

    /**
     * @description Limpa todos os itens do carrinho (usado após finalizar um pedido).
     */
    function limparCarrinho() {
        carrinho.value = [];
        localStorage.removeItem('carrinho');
    }

    return {
        // State
        cliente,
        carrinho,
        // Getters
        estaLogado,
        clienteId,
        totalCarrinho,
        quantidadeTotalItens,
        // Actions
        login,
        logout,
        adicionarAoCarrinho,
        adicionarItemComQuantidade,
        removerDoCarrinho,
        limparCarrinho
    };
});