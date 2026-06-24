<template>
  <el-card class="detalle-card" shadow="never">
    <template #header>
      <strong>Detalle de la Factura</strong>
    </template>

    <el-table :data="detalleConSubtotal" border max-height="300">
      <el-table-column label="Item" width="80" align="center">
        <template #default="fila">
          {{ fila.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column label="Producto" prop="producto" />

      <el-table-column label="Cantidad" prop="cantidad" width="140" align="center" />

      <el-table-column label="Precio" width="140" align="right">
        <template #default="fila">
          {{ Number(fila.row.precio || 0).toFixed(2) }} Bs.
        </template>
      </el-table-column>

      <el-table-column label="Subtotal" width="160" align="right">
        <template #default="fila">
          {{ fila.row.subtotal.toFixed(2) }} Bs.
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  detalles: {
    type: Array,
    default: () => []
  }
})

const detalleConSubtotal = computed(() => {
  return props.detalles.map((item) => {
    const cantidad = Number(item.cantidad || 0)
    const precio = Number(item.precio || 0)

    return {
      ...item,
      subtotal: cantidad * precio
    }
  })
})
</script>

<style scoped>
.detalle-card {
  margin-top: 15px;
}
</style>
