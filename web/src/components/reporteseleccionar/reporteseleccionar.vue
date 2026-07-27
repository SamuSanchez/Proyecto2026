<template>
    <div>
        <el-card>
            <h1>Seleccionar una factura</h1>
            <el-form label="Factura">
                <el-form-item>
                <el-select v-model="facturaIdSeleccionada" placeHolder="Seleccione la Factura" @change="cargarDetalleFactura">
                    <el-option v-for="factura in state.tableData" :key="factura.id" :label="'Factura'+ factura.id + '-' + factura.nombre + '-' + 'NIT:' + factura.nit" :value="factura.id"></el-option>
                </el-select>
                </el-form-item>
                <el-form-item label="Detalle de Factura">
                    <el-select v-model="detalleIdSeleccionado" placeholder="Seleccione un producto" :disabled="detalles.length==0">
                        <el-option v-for="detalles in detalles" key="id" :label="'ID:' + detalles.id + '- Producto:' + detalles.producto + '- Precio:' + detalles.precio + 'Bs. Cantidad:' + detalles.cantidad" :value="detalles.id"></el-option>
                        
                    </el-select>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>
<script setup>
import { onMounted, reactive, ref } from 'vue';
import { getMiFacturaList, getMiFacturaInfo} from '../../api/mifactura/mifactura.js';
const state=reactive({
    tableData: [],
});
const facturaIdSeleccionada=ref(null);
const detalleIdSeleccionado=ref(null);
const detalles=ref([]);
onMounted(() => {
    listarFacturas();
});
const listarFacturas =() =>{
    const params={
        currentPage: 1,
        size: 100
    }
    getMiFacturaList (params).then((res)=>{
        if (res.success) {
            state.tableData = res.data.records || [];
        } 
    })
}

const cargarDetalleFactura =(idfactura)=>{
    detalleIdSeleccionado.value=null;
    detalles.value=[];
    getMiFacturaInfo({id:idfactura}).then((res)=>{
        if (res.success) {
            detalles.value = res.data.detalles || [];
            if (detalles.value.length>0) {
                detalleIdSeleccionado.value=detalles.value[0].id;
            }
        } 
    })
}
</script>