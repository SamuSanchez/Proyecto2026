<template>
    <el-card>
    <h2>Ciclo de Vida</h2>
    <p>Hola Mundo</p>
    </el-card>
    4
    </template>
    <script setup>
    import { onMounted, onUpdated } from 'vue'
    onMounted(() => {
    console.log('Se ejecutó onMounted')
    })
    onUpdated(() => {
    console.log('Se ejecutó onUpdated')
    })
    </script>