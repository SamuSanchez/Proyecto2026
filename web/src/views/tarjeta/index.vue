<template>
    <el-container>
      <el-main>
        <h1>Taller 11</h1>
        
        <div style="display: flex; gap: 10px; align-items: center;">
          <el-input 
            v-model="inputCantidad" 
            type="number" 
            placeholder="Ingrese un número"
            style="width: 200px;"
          />
          <el-button type="primary" @click="ejecutar">Ejecutar</el-button>
        </div>
  
        <el-divider />
        <Contenedor :cantidad="cantidad" />
      </el-main>
    </el-container>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import Contenedor from '../../components/tarjeta/Contenedor.vue'
  
  const inputCantidad = ref('')
  const cantidad = ref(0)
  
  function ejecutar() {
    cantidad.value = parseInt(inputCantidad.value) || 0
  }
  </script>