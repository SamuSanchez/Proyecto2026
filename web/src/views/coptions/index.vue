<template>
    <div>
      <h1>Elecciones de la UAJMS</h1>
  
      <div>
  
        <div>
          <span>Candidato - Carlos Salinas (Tapitas) &nbsp;</span>
          <span>{{ votosSalinas }} &nbsp;&nbsp;</span>
          <el-button type="primary" @click="votarS('salinas')">Votar</el-button>
        </div>
        <br>
        <div>
          <span>Candidato - Eduardo Cortez (Lalo) &nbsp;</span>
          <span>{{ votosCortez }} &nbsp;&nbsp;</span>
          <el-button type="primary" @click="votarC('cortez')">Votar</el-button>
        </div>
  
      </div>
  
      <div>
        <p>{{ mensajeGanador }}</p>
      </div>
  
      <el-button type="danger" @click="reiniciar">Reiniciar</el-button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'EleccionesUAJMS',
    data() {
      return {
        votosSalinas: 0,
        votosCortez: 0,
      }
    },
    computed: {
      mensajeGanador() {
        if (this.votosSalinas === 0 && this.votosCortez === 0) {
          return 'Aún no hay votos.'
        } else if (this.votosSalinas > this.votosCortez) {
          return 'El ganador es Carlos Salinas (tapitas)'
        } else if (this.votosCortez > this.votosSalinas) {
          return 'El ganador es Eduardo Cortez (Lalo)'
        } else {
          return 'Existe empate'
        }
      }
    },

    methods: {
      votarS(candidato) {
       this.votosSalinas++
      },
      votarC(candidato) {
       this.votosCortez++ 
      },
      reiniciar() {
        this.votosSalinas = 0
        this.votosCortez = 0
      }
    }
  }
  </script>
  
  <style>
  </style>