<template>
    <div>  
        <el-card>
            <h1>Mi Reporte</h1>
            <ol type="a">
                <li v-for="item in state.tableData" :key="item.id">Factura {{item.id}}
                <br>
                ID:{{ item.id }}
                <br>
                nombre:{{ item.nombre }}
                <br>
                NIT:{{ item.nit }}
                <ol>
                    <li v-for="detalle in item.detalles">
                        ID: {{ detalle.id }}, Factuara ID: {{ detalle.id }}, Producto: {{ detalle.producto }}
                    </li>
                </ol>
                </li>
               
            </ol>
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
            state.tableData.forEach((item)=>
        {item.detalles = []
        seleccionarFactura(item)
        })
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
            //detalles.value = res.data.detalles
            row.detalles = res.data.detalles || []
        }
    })

}
</script>