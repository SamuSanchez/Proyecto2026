<template>
    <el-card>
        <h3>{{ Titulo }}</h3>
        <p>{{ descripcion }}</p>

        <el-bootom type="primary">
        mostrar informacion
    </el-bootom>
    </el-card>

</template>

<script setup>
defineProps({
    Titulo: String,
    descripcion: String
})
</script>