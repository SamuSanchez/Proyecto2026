<template>
    <q-input type="text" label="mensaje de inicio" v-model="mensaje" q-input />
    <h3>{{ mensaje }}</h3>
    <h3>{{ reverse }}</h3>
  </template>
  
  <script setup>
  import { computed, ref } from "vue";
  let mensaje = ref("");
  const reverse = computed(() => {
    return mensaje.value.split("").reverse().join("");
  });
  </script>