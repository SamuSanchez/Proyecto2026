<template>
    <div class="container">
      <PostDetalle
        v-for="ve in informacion"
        :key="ve.titulo"
        :titulo="ve.titulo"
        :detalle="ve.detalle"
        @enviarPadre="mostrarAlerta"
      />
    </div>
  </template>
  
  <script setup>
  import PostDetalle from "../ciclo/PostDetalle.vue";
  import { ref } from "vue";
  
  let informacion = ref([
    { titulo: "TITULO 1", detalle: "detalle del título 1 VD" },
    { titulo: "t.- 2", detalle: "detalle del título 2 VD" },
    { titulo: "T.- 3", detalle: "detalle del título 3" },
    { titulo: "t.- 4", detalle: "detalle del título 4" },
    { titulo: "t.-", detalle: "detalle del título 5" },
  ]);
  
  function mostrarAlerta(cadena) {
    alert(cadena);
  }
  </script>