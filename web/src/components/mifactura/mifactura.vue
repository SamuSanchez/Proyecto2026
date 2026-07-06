<template>
    <div>
        <el-card>
            <el-table :data="state.tableData" row-key="id">
                <el-table-column label="nit" prop="nit"></el-table-column>
                <el-table-column label="nombre" prop="nombre"></el-table-column>
                <el-table-column label="seleccionar">
                    <template #default="mifila">
                        <el-button type="primary" @click="seleccionarFactura(mifila.row)">Seleccionar</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <el-card>
            <label v-if="facturaSeleccionada.id">
                {{ facturaSeleccionada.nombre }} - {{ facturaSeleccionada.nit }} - {{ detalles }}
              
            </label>
        </el-card>
    </div>
</template>
<script setup>
import { onMounted, reactive, ref } from 'vue';
import { getMiFacturaList, getMiFacturaInfo } from '../../api/mifactura/mifactura.js';
const state = reactive({
    tableData: []
})

const facturaSeleccionada = reactive({
    id: null,
    nit: '',
    nombre: '',
    total: 0
})

const detalles = ref ([])

onMounted (() => {
    listarFacturas ()
})

const listarFacturas = () => {
    const params = {
        currentPage: 1,
        size: 100
    }
    getMiFacturaList(params).then((res) => {
        if (res.success) {
            state.tableData = res.data.records || [];
            console.log('state.tableData')
        }
    })
}

const seleccionarFactura = (row) => {
    console.log(row)
    getMiFacturaInfo({id: row.id}).then((res) => {
        if (res.success) {
            facturaSeleccionada.id = res.data.id
            facturaSeleccionada.nombre = res.data.nombre
            facturaSeleccionada.nit = res.data.nit
            facturaSeleccionada.total = res.data.total
            detalles.value = res.data.detalles
        }
    })

}
</script>