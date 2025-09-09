<template>
    <header class="app-header">
        <div class="header-content">
            <img src="/logo-placeholder.png" alt="Logo" class="header-logo">
            <div class="user-info" v-if="authStore.estaLogado">
                <img src="/avatar-placeholder.png" alt="Avatar do usuário" class="user-avatar">
                <span>Olá, {{ authStore.cliente.nome }}</span>
                <button @click="handleLogout" class="logout-button">Sair</button>

                <div class="cart-icon-wrapper" @click="toggleCarrinho">
                    <span class="cart-icon"><img src="/cart-placeholder.png" alt="cart-icon" class="cart-icon"></span>
                    <span v-if="authStore.quantidadeTotalItens > 0" class="cart-badge">
                        {{ authStore.quantidadeTotalItens }}
                    </span>
                </div>

            </div>
        </div>
    </header>
</template>

<script setup>
import { useAuthStore } from '@/stores/useAuthStore';
// Define que este componente pode emitir um evento chamado 'toggle-carrinho'
const emit = defineEmits(['toggle-carrinho']);

const authStore = useAuthStore();

function handleLogout() {
    authStore.logout();
}

function toggleCarrinho() {
    // Emite o evento para o componente pai (LojaView)
    emit('toggle-carrinho');
}
</script>

<style scoped>
    .app-header {
        background-color: #FFFFFF;
        padding: 1px 40px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
        border-bottom: 1px solid #EAE2D9;
        position: sticky;
        top: 0;
        z-index: 900;
    }

.header-content {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.header-logo {
    height: 100px;
    width: auto;
    object-fit: contain;
}

.user-info {
    display: flex;
    align-items: center;
    gap: 20px;
}

span {
    font-weight: 500;
}

.user-avatar {
    width: 45px;
    height: 45px;
    border-radius: 50%;
    object-fit: cover;
    border: 2px solid #EAE2D9;
}

.cart-icon-wrapper {
    position: relative;
    cursor: pointer;
    padding: 5px;
    transition: transform 0.2s ease;
}

.cart-icon-wrapper:hover {
    transform: scale(1.1);
}

.cart-icon {
    height: 28px;
    width: auto;
}

.cart-badge {
    position: absolute;
    top: -2px;
    right: -8px;
    background-color: #6B4F4B;
    color: white;
    border-radius: 50%;
    padding: 2px 6px;
    font-size: 12px;
    font-weight: 700;
    border: 2px solid #FFFFFF;
}

.logout-button {
    padding: 9px 18px;
    background-color: #A95C5C;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-weight: 500;
    transition: background-color 0.3s ease;
}

.logout-button:hover {
    background-color: #8c4a4a;
}
</style>