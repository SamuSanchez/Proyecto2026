<template>
    <div>
        <h1>Elecciones de la UAJMS</h1>
    <h2>{{ contador }}</h2>  

    </div>
    <br>
    <el-button type="primary" @click="aumentar">Aumentar</el-button> 
    <el-button type="danger" @click="disminuir">Disminuir</el-button>
    <el-button @click="reiniciar">reiniciar</el-button>
</template>
<script>
//como usar el option API <script>
//como usar el option API nestro <script setup> no debe ir con setup
export default {
    name: 'ContadorOptionAPI',
    data() {
        return {
             contador: 0,
        }
    },
    methods: {
        aumentar() {
            this.contador++; /**aqui aumenta el contador */
        },
        disminuir() {
            this.contador--;
        },
        reiniciar() {
            this.contador = 0;
        }
    }
}
</script>
<style>
</style>