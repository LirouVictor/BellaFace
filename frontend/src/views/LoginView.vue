<template>
    <div class="login-container">
        <div class="login-box">
            <img src="/logo-placeholder.png" alt="Bella Face Logo" class="logo">
            <h2>Acesso ao Sistema</h2>
            <form @submit.prevent="handleSubmit">
                <div class="input-group">
                    <label for="login">Login</label>
                    <input type="text" id="login" v-model="loginField" required>
                </div>
                <div class="input-group">
                    <label for="password">Senha</label>
                    <input type="password" id="password" v-model="senhaField" required>
                </div>
                <button type="submit" :disabled="loading">
                    {{ loading ? 'Entrando...' : 'Entrar' }}
                </button>
                <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
            </form>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/useAuthStore';

const authStore = useAuthStore();

const loginField = ref('');
const senhaField = ref('');
const loading = ref(false);
const errorMessage = ref('');

async function handleSubmit() {
    loading.value = true;
    errorMessage.value = '';
    try {
        await authStore.login(loginField.value, senhaField.value);
        // O redirecionamento é feito automaticamente pelo store
    } catch (error) {
        errorMessage.value = error.response?.data?.message || 'Erro ao tentar fazer login.';
    } finally {
        loading.value = false;
    }
}
</script>

<style scoped>
.login-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    padding: 20px;
    background-color: #FFFBF5;
}

.logo {
    height: 120px;
    width: auto;
    margin-bottom: 40px;
    filter: drop-shadow(0 4px 8px rgba(0, 0, 0, 0.05));
}

.login-box {
    background: #FFFFFF;
    padding: 40px 50px;
    border-radius: 12px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
    text-align: center;
    width: 100%;
    max-width: 420px;
    border: 1px solid #EAE2D9;
}

.input-group {
    margin-bottom: 25px;
    text-align: left;
}

label {
    display: block;
    margin-bottom: 8px;
    color: #6B4F4B;
    font-weight: 500;
    font-size: 0.9em;
}

input {
    width: 100%;
    padding: 14px;
    border: 1px solid #EAE2D9;
    border-radius: 8px;
    background-color: #FFFBF5;
    font-size: 1em;
    transition: all 0.3s ease;
}

input:focus {
    outline: none;
    border-color: #6B4F4B;
    box-shadow: 0 0 0 3px rgba(107, 79, 75, 0.1);
}

button {
    width: 100%;
    padding: 15px;
    background: linear-gradient(90deg, #6B4F4B 0%, #8a6f6b 100%);
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-size: 1.1em;
    font-weight: bold;
    letter-spacing: 0.5px;
    transition: all 0.3s ease;
    box-shadow: 0 4px 15px rgba(107, 79, 75, 0.2);
}

button:hover {
    transform: translateY(-2px);
    box-shadow: 0 6px 20px rgba(107, 79, 75, 0.3);
}

button:disabled {
    background: #C8B6B1;
    cursor: not-allowed;
    transform: none;
    box-shadow: none;
}

.error-message {
    color: #A95C5C;
    margin-top: 20px;
    font-weight: 500;
}
</style>