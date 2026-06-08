<template>
    <div class="post">
      {{ props.titulo }}
      <br />
      <q-btn @click="handleClick">Di Hola</q-btn>
      {{ props.detalle }}
    </div>
  </template>
  
  <script setup>
  import { defineProps, defineEmits } from "vue";
  const emits = defineEmits(["enviarPadre"]);
  const props = defineProps({
    titulo: {
      type: String,
      required: true,
    },
    detalle: {
      type: String,
      required: true,
    },
  });
  
  const handleClick = () => {
    //alert("Hola desde el hijo");
    emits("enviarPadre", "TE ENVIO UN MENSAJE PADRE");
  };
  // el hijo notificara al padre
  </script>