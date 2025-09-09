<template>
    <!-- O @click.self no overlay fecha o modal apenas se o clique for no fundo, não nos filhos -->
    <div class="modal-overlay" @click.self="fechar">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Adicionar Produto ao Carrinho</h3>
                <button @click="fechar" class="close-btn">✖</button>
            </div>

            <div class="modal-body">
                <div class="product-info">
                    <img src="/produto-placeholder.png" alt="Produto" class="product-image-small">
                    <h4>{{ produto.nome }}</h4>
                </div>

                <div class="quantity-control">
                    <label for="quantity">Quantidade:</label>
                    <input type="number" id="quantity" v-model.number="quantidade" min="1">
                </div>
            </div>

            <div class="modal-footer">
                <button @click="fechar" class="btn-cancel">Cancelar</button>
                <button @click="confirmar" class="btn-confirm">Adicionar ao Carrinho</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

// Recebe o produto selecionado como uma propriedade
const props = defineProps({
    produto: {
        type: Object,
        required: true
    }
});

// Define os eventos que este componente pode emitir
const emit = defineEmits(['close', 'confirm']);

// Estado interno para a quantidade, começando em 1
const quantidade = ref(1);

// Funções para emitir os eventos para o componente pai
function fechar() {
    emit('close');
}

function confirmar() {
    // Garante que a quantidade seja pelo menos 1
    if (quantidade.value > 0) {
        emit('confirm', { produto: props.produto, quantidade: quantidade.value });
    }
}
</script>

<style scoped>
.modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(60, 42, 33, 0.6);
    backdrop-filter: blur(5px);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 2000;
}

.modal-content {
    background: #FFFFFF;
    padding: 30px;
    border-radius: 12px;
    width: 100%;
    max-width: 480px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
    border: 1px solid #EAE2D9;
}

.modal-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #EAE2D9;
    padding-bottom: 20px;
    margin-bottom: 25px;
}

.close-btn {
    background: none;
    border: none;
    font-size: 24px;
    cursor: pointer;
    color: #888;
}

.close-btn:hover {
    color: #3C2A21;
}

.modal-body {
    padding-bottom: 25px;
}

.product-info {
    display: flex;
    align-items: center;
    gap: 20px;
    margin-bottom: 30px;
}

.product-image-small {
    width: 70px;
    height: 70px;
    border-radius: 8px;
    object-fit: cover;
}

.quantity-control {
    display: flex;
    align-items: center;
    gap: 15px;
}

.quantity-control label {
    font-weight: 700;
    font-size: 1.1em;
}

.quantity-control input {
    width: 100px;
    padding: 12px;
    border: 1px solid #EAE2D9;
    border-radius: 8px;
    font-size: 1.2em;
    text-align: center;
    font-weight: 700;
}

.quantity-control input:focus {
    outline: none;
    border-color: #6B4F4B;
}

/* Remover setas do campo de quantidade para Chrome, Safari, Edge, Opera */
.quantity-control input::-webkit-outer-spin-button,
.quantity-control input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

/* Remover setas do campo de quantidade para Firefox */
.quantity-control input[type=number] {
    -moz-appearance: textfield;
}

.modal-footer {
    display: flex;
    justify-content: flex-end;
    gap: 15px;
    border-top: 1px solid #EAE2D9;
    padding-top: 25px;
}

.btn-cancel,
.btn-confirm {
    padding: 12px 25px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-weight: 700;
    font-size: 1em;
    transition: all 0.3s;
}

.btn-cancel {
    background-color: #F8F8F8;
    border: 1px solid #EAE2D9;
    color: #555;
}

.btn-cancel:hover {
    background-color: #eee;
}

.btn-confirm {
    background-color: #6B4F4B;
    color: white;
}

.btn-confirm:hover {
    background-color: #3C2A21;
}
</style>