<template>
    <div>
        <AppHeader />
        <!-- Ouve o evento do cabeçalho para chamar nossa função -->
        <CabecalhoAplicativo @toggle-carrinho="toggleCarrinho" />
        <div class="loja-layout">
            <div class="produtos-section">
                <h1>Nossos Produtos</h1>
                <p v-if="loading">Carregando produtos...</p>
                <p v-if="error">{{ error }}</p>
                <ListaProdutos v-if="produtos.length > 0" :produtos="produtos"
                    @abrir-modal-quantidade="abrirModalQuantidade" />
            </div>
        </div>

        <!-- Overlay que escurece o fundo -->
        <div v-if="isCartVisible" class="overlay" @click="toggleCarrinho"></div>

        <!-- Animação de transição para o carrinho -->
        <Transition name="slide-fade">
            <!-- Ouve o evento do carrinho para fechá-lo -->
            <CarrinhoCompras v-if="isCartVisible" @fechar="toggleCarrinho" />
        </Transition>

        <!-- Gerenciamento do Modal de Quantidade -->
        <Transition name="fade">
            <QuantidadeModal v-if="isModalVisible" :produto="produtoSelecionado" @close="fecharModalQuantidade"
                @confirm="handleConfirmarAdicao" />
        </Transition>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import apiService from '@/services/apiService';
import CabecalhoAplicativo from '@/components/CabecalhoAplicativo.vue';
import ListaProdutos from '@/components/ListaProdutos.vue';
import CarrinhoCompras from '@/components/CarrinhoCompras.vue';
import { useAuthStore } from '@/stores/useAuthStore';
import QuantidadeModal from '@/components/QuantidadeModal.vue';

const produtos = ref([]);
const loading = ref(true);
const error = ref('');
const isCartVisible = ref(false);
const authStore = useAuthStore();
const isModalVisible = ref(false);
const produtoSelecionado = ref(null);

function abrirModalQuantidade(produto) {
    produtoSelecionado.value = produto;
    isModalVisible.value = true;
}

function fecharModalQuantidade() {
    isModalVisible.value = false;
    produtoSelecionado.value = null;
}

function handleConfirmarAdicao({ produto, quantidade }) {
    authStore.adicionarItemComQuantidade(produto, quantidade);
    fecharModalQuantidade();
}

function toggleCarrinho() {
    isCartVisible.value = !isCartVisible.value;
}

// onMounted é um "gancho de ciclo de vida" do Vue.
// O código aqui dentro é executado assim que o componente é montado na tela.
onMounted(async () => {
    try {
        produtos.value = await apiService.buscarProdutos();
    } catch (err) {
        error.value = 'Não foi possível carregar os produtos.';
    } finally {
        loading.value = false;
    }
});
</script>

<style scoped>
.loja-layout {
    max-width: 1200px;
    margin: 30px auto;
    padding: 0 20px;
}

.overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 999;
}

.slide-fade-enter-active {
    transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
    transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
    transform: translateX(100%);
    opacity: 0;
}

.produtos-section {
    grid-column: 1 / 2;
}

.carrinho-section {
    grid-column: 2 / 3;
}

.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
    opacity: 0;
}
</style>