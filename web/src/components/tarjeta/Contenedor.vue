<template>
    <div>
      <template v-for="n in cantidad" :key="n">
        <Tarjeta />
        <el-divider />
      </template>
    </div>
  </template>
  <script setup>
  import Tarjeta from './Tarjeta.vue'
  
  defineProps({
    cantidad: {
      type: Number,
      default: 1
    }
  })
  </script>