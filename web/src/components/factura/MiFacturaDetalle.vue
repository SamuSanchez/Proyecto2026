<template>
    <el-card>
    <h1>Detalle de la Factura</h1>
    <el-table :data="detalleConSubtotal" border max-height="300px">
        <el-table-column label="Item" width="80">
            <template #default ="fila">
                {{ fila.$index + 1 }}
            </template>
        </el-table-column>

        <el-table-column label="producto">
            <template #default="fila">
                <el-input v-model="items[fila.$index].producto" placeholder="Nombre del producto"></el-input>
            </template>
        </el-table-column>

        <el-table-column label="cantidad">
            <template #default="fila">
                <el-input-number v-model="items[fila.$index].cantidad" placeholder="Cantidad del producto" :min="0" :step="1"></el-input-number>
            </template>
        </el-table-column>

        <el-table-column label="precio">
            <template #default="fila">
                <el-input-number v-model="items[fila.$index].precio" placeholder="Precio del producto" :min="0" :step="1"></el-input-number>
            </template>
        </el-table-column>
        <el-table-column>
            <template #default="fila">
                {{ fila.row.subtotal.toFixed(2) }} Bs.
            </template>
        </el-table-column> 

    </el-table>
    </el-card>
</template>
<script setup>
import { ref, computed, watch } from 'vue';
const props = defineProps({
    
    numeroItems: {
        type: Number,
        required: true
    }
});
const emit = defineEmits(['enviar-total']);
const items = ref([]);
const crearItems = (cantidad) => {
const nuevosItems = [];
for (let i = 0; i < cantidad; i++) {
    nuevosItems.push(
        items.value[i] || {
            producto: '',
            cantidad: 0,
            precio: 0,
        }
    );
}
items.value = nuevosItems;
}
watch (
    () => props.numeroItems,
    (nuevoValor) => {
        crearItems(nuevoValor);
    },
    { immediate: true }
)
const detalleConSubtotal = computed(()=>{
    return items.value.map((item)=>{
        const cantidad = Number(item.cantidad)
        const precio = Number(item.precio)
        return {
            ...item,
            subtotal: cantidad * precio
        };
    })
})

const totalDetalle = computed(()=>{
    return detalleConSubtotal.value.reduce((suma,item)=> {
        return suma + item.subtotal;
    }, 0);
})

watch(
    totalDetalle,
    (nuevoTotal) => {
        emit('enviar-total', nuevoTotal);
    },
    {immediate: true}
)
</script>