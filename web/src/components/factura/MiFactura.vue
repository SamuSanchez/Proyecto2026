<template>
  <div>
    <el-card class="mb-15">
      <template #header>
        <div class="card-header">
          <strong>Listado de Facturas</strong>
        </div>
      </template>
      <el-table
        :data="state.tableData"
        border
        row-key="id"
        highlight-current-row
        @row-click="seleccionarFactura"
      >
        <el-table-column 
          label="ID" 
          prop="id" 
          width="80" 
          align="center" 
        />
        <el-table-column 
          label="Nombre" 
          prop="nombre" 
        />
        <el-table-column 
          label="NIT" 
          prop="nit" 
          width="180" 
        />
        <el-table-column 
          label="Total" 
          width="160" 
          align="right"
        >
          <template #default="fila">
            {{ Number(fila.row.total || 0).toFixed(2) }} Bs.
          </template>
        </el-table-column>
        <el-table-column 
          label="Acción" 
          width="140" 
          align="center"
        >
          <template #default="fila">
            <el-button 
              type="primary" 
              @click.stop="seleccionarFactura(fila.row)"
            >
              Ver detalle
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-card v-if="facturaSeleccionada.id">
      <template #header>
        <div class="card-header">
          <strong>Factura seleccionada</strong>
        </div>
      </template>
      <el-descriptions :column="3" border>
        <el-descriptions-item label="ID">
          {{ facturaSeleccionada.id }}
        </el-descriptions-item>
        <el-descriptions-item label="Nombre">
          {{ facturaSeleccionada.nombre }}
        </el-descriptions-item>
        <el-descriptions-item label="NIT">
          {{ facturaSeleccionada.nit }}
        </el-descriptions-item>
        <el-descriptions-item label="Total">
          {{ totalFactura }} Bs.
        </el-descriptions-item>
      </el-descriptions>
      <MiFacturaDetalle :detalles="detalles" />
    </el-card>
    <el-empty
      v-else
      description="Seleccione una factura para visualizar su detalle"
    />
  </div>
</template>
<script setup>
import { computed, onMounted, reactive, ref } from 'vue'
import MiFacturaDetalle from './MiFacturaDetalle.vue'
import { getFacturaInfo, getFacturaList } from '@/api/factura/factura.js'
import { errorMsg } from '@/utils/message.js'
const state = reactive({
  tableData: []
})
const facturaSeleccionada = reactive({
  id: null,
  nombre: '',
  nit: '',
  total: 0
})
const detalles = ref([])
const totalFactura = computed(() => {
  return Number(facturaSeleccionada.total || 0).toFixed(2)
})
onMounted(() => {
  listarFacturas()
})
const listarFacturas = () => {
  const params = {
    currentPage: 1,
    size: 100
  }
  getFacturaList(params).then((res) => {
    if (res.success) {
      state.tableData = res.data.records || []
    } else {
      errorMsg(res.msg)
    }
  })
}
const seleccionarFactura = (row) => {
  if (!row || !row.id) {
    return
  }
  getFacturaInfo({ id: row.id }).then((res) => {
    if (res.success) {
      const data = res.data
      facturaSeleccionada.id = data.id
      facturaSeleccionada.nombre = data.nombre
      facturaSeleccionada.nit = data.nit
      facturaSeleccionada.total = data.total
      detalles.value = data.detalles || []
    } else {
      errorMsg(res.msg)
    }
  })
}
</script>
<style scoped>
.mb-15 {
  margin-bottom: 15px;
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>