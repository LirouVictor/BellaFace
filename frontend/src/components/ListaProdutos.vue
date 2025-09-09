<template>
    <div class="product-grid">
        <div v-for="produto in produtos" :key="produto.id" class="product-card">
            <img src="/produto-placeholder.png" alt="Foto do Produto" class="product-image">
            <div class="product-content">
                <h3>{{ produto.nome }}</h3>
                <p class="description">{{ produto.descricao }}</p>
                <p class="price">R$ {{ produto.preco.toFixed(2) }}</p>
                <button @click="solicitarAdicao(produto)">Adicionar ao Carrinho</button>
            </div>
        </div>
    </div>
</template>

<script setup>
// Este componente recebe a lista de produtos como uma "prop" (propriedade)
defineProps({
    produtos: {
        type: Array,
        required: true
    }
});

// Define que este componente pode emitir um evento
const emit = defineEmits(['abrir-modal-quantidade']);

function solicitarAdicao(produto) {
    // Emite o evento para o componente pai (LojaView), passando o produto clicado
    emit('abrir-modal-quantidade', produto);
}
</script>

<style scoped>
.product-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
    gap: 35px;
}

.product-card {
    background: #FFFFFF;
    border: 1px solid #EAE2D9;
    border-radius: 12px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);
    display: flex;
    flex-direction: column;
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.product-card:hover {
    transform: translateY(-8px);
    box-shadow: 0 12px 25px rgba(0, 0, 0, 0.1);
}

.product-image {
    width: 100%;
    height: 300px;
    object-fit: cover;
}

.product-content {
    padding: 25px;
    display: flex;
    flex-direction: column;
    flex-grow: 1;
}

h3 {
    margin-bottom: 10px;
    font-size: 1.25em;
}

.description {
    color: #555;
    flex-grow: 1;
    margin-bottom: 20px;
    line-height: 1.5;
}

.price {
    font-size: 1.4em;
    font-weight: 700;
    color: #6B4F4B;
    margin-bottom: 20px;
}

button {
    padding: 14px;
    background-color: #6B4F4B;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    margin-top: auto;
    font-weight: 700;
    font-size: 1em;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: #3C2A21;
}
</style>