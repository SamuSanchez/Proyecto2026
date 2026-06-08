<template>
  <div class="home">
    <img alt="EMS logo" src="@/assets/image/ems.png">
    <HelloWorld :msg="msg"/>
  </div>
</template>

<script setup>
  import HelloWorld from '@/components/HelloWorld.vue'
  import {ref} from 'vue'
  const msg = ref('Bienvenidos a Taller 1 de Ing. Informatica UAJMS  Grupo 2 y Grupo 4')
</script>
<style scoped>
  .home{
    text-align: center;
  }
</style>