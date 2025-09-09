<template>
  <main>
    <RouterView />
  </main>
</template>

<style>
/* Estilos globais para a aplicação */
body {
  margin: 0;
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  background-color: #FFFBF5;
  color: #4E4E4E;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

* {
  box-sizing: border-box;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  font-family: inherit;
  color: #3C2A21;
  /* Marrom para todos os títulos */
  margin-top: 0;
}
</style>