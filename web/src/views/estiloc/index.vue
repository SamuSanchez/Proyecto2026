<template>
    <div>
        <h1>Contador con option API</h1>
    <h2>{{ contador }}</h2>
    
    </div>
    <br>
    <el-button type="primary" @click="incrementar">Incrementar</el-button>
    <el-button type="danger" @click="disminuar">Decrementar</el-button>
    <el-button @click="reiniciar">reiniciar</el-button>
</template>
<script>
//como usar el option API <script>
//como usar el option API <script setup>
export default {
    contador: 0,
    
}
</script>
<style>
</style>