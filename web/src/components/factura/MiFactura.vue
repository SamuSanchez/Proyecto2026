<template>
    <el-card>
        <h1>Datos de la Factura</h1>
        <el-form label-width="140px|">
            <el-form-item label="nombre">
                <el-input v-model="nombre" placeholder="Nombre de la persona" />
                </el-form-item>
            <el-form-item label="nit">
                <el-input v-model="nit" placeholder="Nit de la persona" />
            </el-form-item>
            <el-form-item label="Numero de Items">
                <el-input-number v-model="numeroItems" :min="1" :max="8" />
            </el-form-item>
                <MiFacturaDetalle
                :numero-items="numeroItems"
                @enviar-total="recibirTotal"
                />
                <h2>Total a pagar: {{ totalFactura }} Bs. </h2>
        </el-form>
    </el-card>
</template>

<script setup>
import { ref, computed } from 'vue';
import MiFacturaDetalle from './MiFacturaDetalle.vue';
const nombre = ref('');
const nit = ref('');
const numeroItems = ref(1);
const total = ref(0);
const recibirTotal = (monto) => {
    total.value = monto;
}
const totalFactura = computed(() => {
    return total.value.toFixed(2);
});
</script>