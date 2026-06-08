<template>
    <div>
      <h1>Elecciones de la UAJMS</h1>
  
      <div>
  
        <div>
          <span>Candidato - Carlos Salinas (Tapitas) &nbsp;</span>
          <span>{{ votosSalinas }} &nbsp;&nbsp;</span>
          <el-button type="primary" @click="votarS">Votar</el-button>
        </div>
        <br>
        <div>
          <span>Candidato - Eduardo Cortez (Lalo) &nbsp;</span>
          <span>{{ votosCortez }} &nbsp;&nbsp;</span>
          <el-button type="primary" @click="votarC">Votar</el-button>
        </div>
  
      </div>
  
      <div>
        <p>{{ mensajeGanador }}</p>
      </div>
  
      <el-button type="danger" @click="reiniciar">Reiniciar</el-button>
    </div>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue'
  
  const votosSalinas = ref(0)
  const votosCortez  = ref(0)
  
  const mensajeGanador = computed(() => {
    if (votosSalinas.value === 0 && votosCortez.value === 0) {
      return 'Aún no hay votos.'
    } else if (votosSalinas.value > votosCortez.value) {
      return 'El ganador es Carlos Salinas (Tapitas)'
    } else if (votosCortez.value > votosSalinas.value) {
      return 'El ganador es Eduardo Cortez (Lalo)'
    } else {
      return 'Existe empate'
    }
  })
  
  const votarS = () =>{
    votosSalinas.value++
  }
  
  const votarC = () => {
    votosCortez.value++
  }
  
  const reiniciar =() => {
    votosSalinas.value = 0
    votosCortez.value  = 0
  }
  </script>
  
  <style>
  </style>