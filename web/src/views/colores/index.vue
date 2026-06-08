<template>
<div>
        <h3>Rojo</h3>
        <input type="range" id="rojo" value="30" min="0" max="255" oninput="actualizar()"></input>
        <input type="text" id="txtrojo" value="30"></input>
        <h3>Verde</h3>
        <input type="range" id="verde" value="100" min="0" max="255" oninput="actualizar()"></input>
        <input type="text" id="txtverde" value="100"></input>
        <h3>Azul</h3>
        <input type="range" id="azul" value="40" min="0" max="255" oninput="actualizar()"></input>
        <input type="text" id="txtazul" value="40"></input>
    </div>
</template>

<script>
import '@/assets/js/colores.js'

export default {
    name: 'Colores'
}
</script>
<style scoped>
</style>