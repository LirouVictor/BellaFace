<template>
    <div class="cart-sidebar">
        <div class="cart-header">
            <h2>Carrinho de Compras</h2>
            <!-- Botão para fechar o carrinho -->
            <button @click="fecharCarrinho" class="close-btn">✖</button>
        </div>
        <div v-if="authStore.carrinho.length === 0" class="empty-cart">
            Seu carrinho está vazio.
        </div>
        <div v-else class="cart-content">
            <ul>
                <li v-for="item in authStore.carrinho" :key="item.id" class="cart-item">
                    <button @click="remover(item.id)" class="remove-btn">✖</button>
                    <img src="/produto-placeholder.png" alt="Miniatura do produto" class="cart-item-image">
                    <div class="cart-item-details">
                        <span class="item-name">{{ item.nome }}</span>
                        <span class="item-quantity">Quantidade: {{ item.quantidade }}</span>
                        <span class="item-price">R$ {{ (item.preco * item.quantidade).toFixed(2) }}</span>
                    </div>
                </li>
            </ul>
        </div>

        <!-- Rodapé do Carrinho -->
        <div class="cart-footer">
            <hr>
            <div class="cart-total">
                <strong>Total: R$ {{ authStore.totalCarrinho.toFixed(2) }}</strong>
            </div>
            <div class="comments-section">
                <label for="comments">Adicionar Comentário:</label>
                <textarea id="comments" v-model="comentarios"></textarea>
            </div>
            <button @click="finalizar" class="checkout-btn" :disabled="loading">
                {{ loading ? 'Finalizando...' : 'Finalizar Pedido' }}
            </button>
            <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/useAuthStore';
import apiService from '@/services/apiService';

const authStore = useAuthStore();
const comentarios = ref('');
const loading = ref(false);
const errorMessage = ref('');
const emit = defineEmits(['fechar']);

function fecharCarrinho() {
    emit('fechar');
}

async function finalizar() {
    loading.value = true;
    errorMessage.value = '';

    const pedidoRequest = {
        comentarios: comentarios.value,
        itens: authStore.carrinho.map(item => ({
            produtoId: item.id,
            quantidade: item.quantidade
        }))
    };

    try {
        await apiService.criarPedido(pedidoRequest, authStore.clienteId);
        alert('Pedido realizado com sucesso!'); // Feedback para o usuário
        authStore.limparCarrinho();
        comentarios.value = ''; // Limpa o campo de comentários
    } catch (error) {
        errorMessage.value = error.response?.data?.message || 'Ocorreu um erro ao finalizar o pedido.';
    } finally {
        loading.value = false;
    }
}

function remover(produtoId) {
    authStore.removerDoCarrinho(produtoId);
}
</script>

<style scoped>
.cart-sidebar {
    position: fixed;
    top: 0;
    right: 0;
    width: 420px;
    height: 100%;
    background: #FFFBF5;
    box-shadow: -5px 0 25px rgba(0, 0, 0, 0.1);
    z-index: 1000;
    display: flex;
    flex-direction: column;
}

.cart-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px 25px;
    border-bottom: 1px solid #EAE2D9;
    background: #FFFBF5;
}

.close-btn {
    background: none;
    border: none;
    font-size: 24px;
    cursor: pointer;
    color: #888;
    transition: color 0.3s;
}

.close-btn:hover {
    color: #3C2A21;
}

.cart-content {
    flex-grow: 1;
    overflow-y: auto;
    padding: 10px 25px;
}

.empty-cart {
    padding: 25px;
    text-align: center;
    margin-top: 40px;
    color: #777;
}

ul {
    list-style: none;
    padding: 0;
}

.cart-item {
    position: relative;
    display: flex;
    align-items: center;
    gap: 15px;
    padding: 20px 15px;
    border-bottom: 1px solid #EAE2D9;
    border-radius: 8px;
    transition: background-color 0.2s;
}

.cart-item:hover {
    background-color: #FAFAFA;
}

.cart-item-image {
    width: 60px;
    height: 60px;
    object-fit: cover;
    border-radius: 8px;
    flex-shrink: 0;
}

.cart-item-details {
    display: flex;
    flex-direction: column;
    flex-grow: 1;
    gap: 4px;
}

.item-name {
    font-weight: 600;
    color: #333;
    font-size: 1em;
}

.item-quantity {
    color: #666;
    font-size: 0.9em;
}

.item-price {
    color: #6B4F4B;
    font-weight: 600;
    font-size: 1em;
}

.remove-btn {
    position: absolute;
    top: 8px;
    right: 8px;
    background: #F8F8F8;
    border: 1px solid #EAE2D9;
    color: #A95C5C;
    cursor: pointer;
    width: 28px;
    height: 28px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 14px;
    transition: all 0.3s;
    z-index: 10;
}

.remove-btn:hover {
    background-color: #A95C5C;
    color: white;
    transform: scale(1.1);
}

.cart-footer {
    padding: 25px;
    border-top: 1px solid #EAE2D9;
    background: #FFFBF5;
}

.cart-total {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
    font-size: 1.3em;
    font-weight: 700;
}

.comments-section label {
    font-weight: 500;
    font-size: 0.9em;
}

textarea {
    width: 100%;
    height: 70px;
    padding: 12px;
    border: 1px solid #EAE2D9;
    border-radius: 8px;
    margin-top: 8px;
    resize: vertical;
}

textarea:focus {
    outline: none;
    border-color: #6B4F4B;
}

.checkout-btn {
    width: 100%;
    padding: 15px;
    margin-top: 20px;
    background: #6B4F4B;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-size: 1.1em;
    font-weight: 700;
    transition: background-color 0.3s;
}

.checkout-btn:hover {
    background-color: #3C2A21;
}

.checkout-btn:disabled {
    background-color: #C8B6B1;
    cursor: not-allowed;
}

.error-message {
    color: #A95C5C;
    margin-top: 15px;
    font-weight: 500;
}
</style>