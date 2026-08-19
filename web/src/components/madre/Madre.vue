<template>
    <div>
        <el-car>
            <h1>ABM Madre</h1>
        <el-button type="primary" @click="abrirAdicionar">
  Adicionar
</el-button>
<el-table
  :data="state.tableData"
  border
  row-key="cim"
>
  <el-table-column
    label="CIM"
    prop="cim"
    width="120"
    align="center"
  />
  <el-table-column
    label="Nombre"
    prop="nombre"
  />
  <el-table-column
    label="Apellido Paterno"
    prop="apellidoPaterno"
  />
  <el-table-column
    label="Apellido Materno"
    prop="apellidoMaterno"
  />
<el-table-column
  label="Acciones"
  width="220"
  align="center"
>
  <template #default="fila">
    <el-button
      type="primary"
      @click="abrirModificar(fila.row)"
    >
      Modificar
    </el-button>
    <el-button
      type="danger"
      @click="eliminarMadre(fila.row)"
    >
    Eliminar
    </el-button>
    </template>
</el-table-column>
</el-table>
    </el-car>
    <Hijo ref="hijoRef" @get-list="listarMadres" />
    </div>
</template>
<script setup>
import { onMounted, reactive, ref } from 'vue'
import { ElMessageBox } from 'element-plus'
import Hijo from './Hijo.vue'
import {
  getMadreList,
  delMadre
} from '../../api/madre/madre.js'
const state = reactive({
  tableData: []
})
const hijoRef = ref(null)
onMounted(() => {
  listarMadres()
})
const listarMadres = () => {
  getMadreList().then((res) => {
    if (res.success) {
      state.tableData = res.data || []
    }
  })
}
const abrirAdicionar = () => {
  hijoRef.value.open()
}
const abrirModificar = (row) => {
  hijoRef.value.open(row)
}
const eliminarMadre = (row) => {
  ElMessageBox.confirm(
    '¿Está seguro de eliminar la madre ' + row.cim + '?',
    'Confirmar',
    {
      confirmButtonText: 'Sí',
      cancelButtonText: 'No',
      type: 'warning'
    }
  ).then(() => {
    delMadre({ cim: row.cim }).then((res) => {
      if (res.success) {
        listarMadres()
      } else {
        alert(res.msg)
      }
    })
  })
}
</script>