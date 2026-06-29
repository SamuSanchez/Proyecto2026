<template>
    <div>
        <el-card>
            <template #header>
                <div>
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
                />

                <el-table-column label="Total">
                    <template #default="fila">
                        {{ Number(fila.row.total || 0).toFixed(2) }} Bs.
                    </template>
                </el-table-column>

                <el-table-column label="Acción">
                    <template #default="fila">
                        <el-button
                            type="primary"
                            @click.stop="seleccionarFactura(fila.row)"
                        >
                            Ver Detalle 
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>

        <el-card 
            v-if="facturaSeleccionada.id"
            style="margin-top: 15px;"
        >
            <template #header>
                <div>
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
                    {{ Number(facturaSeleccionada.total || 0).toFixed(2) }} Bs.
                </el-descriptions-item>
            </el-descriptions>

            <MiFacturaDetalleN :detalles="detalles" />
        </el-card>
    </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import MiFacturaDetalleN from './MiFacturaDetalle.vue'
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

onMounted(() => {
    listarFacturas()
})

const listarFacturas = () => {
    const params = {
        currentPage: 1,
        size: 100
    }

    getFacturaList(params).then((res) => {
        console.log(res.data.records)

        if (res.success) {
            state.tableData = res.data.records || []
        } else {
            errorMsg(res.msg)
        }
    })
}

const seleccionarFactura = (row) => {
    console.log('Factura seleccionada:', row)

    if (!row || !row.id) {
        return
    }

    getFacturaInfo({ id: row.id }).then((res) => {
        console.log('Detalle de factura:', res)

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