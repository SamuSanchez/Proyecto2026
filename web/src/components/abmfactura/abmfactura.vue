<template>
    <div>
        <el-card>
        <h1>ABM Facturas</h1>
        <el-button type="primary" @click="abrirAdicionar">Adicionar</el-button>
        <el-table
            :data="state.tableData"
            border
            row-key="id">

            <el-table-column
                label="ID"
                prop="id">
            </el-table-column>

            <el-table-column
                label="Nombre"
                prop="nombre">
            </el-table-column>

            <el-table-column
                label="Nit"
                prop="nit">
            </el-table-column>

             <el-table-column
                label="Total"
                align="right">
                <template #default="fila">
                    {{ Number(fila.row.total || 0).toFixed(2) }} Bs. 
                </template>
            </el-table-column>

            <el-table-column
                label="acciones"
                width="220"
                align="center"
                >
                <template #default="fila">
                    <el-button type="primary" @click="abrirModificar(fila.row)">Modificar</el-button>
                    <el-button type="danger" size="small" @click="eliminarFactura(fila.row)">Eliminar</el-button>
                </template>
            </el-table-column>

        </el-table>
        </el-card>

        <el-dialog
            v-model="dialogVisible"
            :title="facturaForm.id ? 'Modificar Factura' : 'Adicionar Factura'"
            width="900px">

            <!---vamos a realizar el formulario-->
            <el-form label-width="120px">

                <el-form-item label="Nombre">
                    <el-input v-model="facturaForm.nombre" placeHolder="Nombre de la persona"></el-input>
                </el-form-item>

                <el-form-item label="NIT">
                    <el-input v-model="facturaForm.nit" placeHolder="Nombre del NIT"></el-input>
                </el-form-item>

                <el-form-item label="Detalle de la Factura">
                    <el-button type="primary" @click="agregarDetalle">Adicionar Producto</el-button>
                </el-form-item>

            <el-table :data="detalleForm" border >
                <el-table-column label="item" width="80" aling="center">
                    <template #default="fila">
                        {{ fila.$index + 1 }}
                    </template>
                </el-table-column>
            
            <el-table-column label="Producto">
                <template #default="fila">
                    <el-input v-model="detalleForm[fila.$index].producto" placeHolder="Nombre del producto"></el-input>
                </template>
            </el-table-column>

            <el-table-column label="Cantidad">
                <template #default="fila">
                    <el-input-number v-model="detalleForm[fila.$index].cantidad" :min="1"></el-input-number>
                </template>
            </el-table-column>

            <el-table-column label="Precio">
                <template #default="fila">
                    <el-input-number v-model="detalleForm[fila.$index].precio" :min="0" :step="1"></el-input-number>
                </template>
            </el-table-column>

            <el-table-column label="Subtotal" width="140" aling="right">
                <template #default="fila">
                    {{ calcularSubtotal(fila.row).toFixed(2) }} Bs.
                </template>
            </el-table-column>

            <el-table-column label="accion" width="120" align="center">
                <template #default="fila">
                    <el-button type="danger" @click="quitarDetalle(fila.$index)">Quitar</el-button>
                </template>
            </el-table-column>

            </el-table>
            <h2>Total: {{ totalFactura }} Bs.</h2>

        </el-form>
            <template #footer>
                <el-button @click="dialogVisible=false">Cancelar</el-button>
                <el-button type="primary" @click="guardarFactura">Guardar</el-button>
            </template>
        </el-dialog>
    </div>
</template>
<script setup>
import {onMounted} from 'vue';
import {editMiFactura, getMiFacturaList, getMiFacturaInfo, delMiFactura} from '../../api/mifactura/mifactura';
import {reactive, ref, computed} from 'vue';
import { ElMessageBox, ElMessage } from 'element-plus';
const state = reactive ({
    tableData: []
})

const detalleForm = ref([])
const dialogVisible = ref(false)
const facturaForm = reactive({
    id: null,
    nombre: '',
    nit: '',
    total: 0,
})

onMounted (()=>{
    listarFacturas()
})
const listarFacturas = () =>{
    const params = {
        currentPage:1,
        size: 100
    }
    getMiFacturaList (params).then ((res)=>{
        if (res.success){
            state.tableData = res.data.records || []
        }
    })
}

const crearDetalleVacio= () =>{
    return {
        id: null,
        facturaId: null,
        producto:'',
        cantidad: 1,
        precio: 0,
    }
}

const abrirAdicionar = () =>{
   facturaForm.id = null
   facturaForm.nombre = ''
   facturaForm.nit = ''
   facturaForm.total = 0
   detalleForm.value=[crearDetalleVacio()]
   dialogVisible.value = true

}

const abrirModificar = (row) =>{
    console.log('modificar')
    getMiFacturaInfo({id: row.id}).then((res)=>{
        if (res.success){
            const data = res.data
            facturaForm.id = data.id
            facturaForm.nombre = data.nombre
            facturaForm.nit = data.nit
            facturaForm.total = data.total
            detalleForm.value = data.detalle || []
        }
    })
    dialogVisible.value = true
}

const calcularSubtotal = (detalle) =>{
    const cantidad = Number(detalle.cantidad || 0)
    const precio = Number(detalle.precio || 0)
    return cantidad * precio
}

const quitarDetalle = (index) =>{
    detalleForm.value.splice(index, 1)
    if (detalleForm.value.length === 0){
        detalleForm.value.push(crearDetalleVacio())
    }
}

const agregarDetalle = () =>{
    detalleForm.value.push(crearDetalleVacio())
}

const totalFactura = computed(()=>{
    const total = detalleForm.value.reduce((suma,item)=>{
        return suma + calcularSubtotal(item)
    }, 0)
    return total.toFixed(2)
})

const guardarFactura =()=>{
ElMessageBox.confirm(
    'Está seguro de eliminar la factura?'+ row.id + '?',
    'Confirmar', {
    confirmButtonText: 'Sí',
    cancelButtonText: 'No',
    type: 'warning',
}

).then(()=>{
    delMiFactura({id: row.id}).then((res)=>{
        if (res.success){
            listarFacturas()
        }else{
            alert(res.msg)
        }
    }) 
})
}

</script>