<template>
    <el-dialog
        v-model="dialogVisible"
  :title="facturaForm.id ? 'Modificar Factura' : 'Adicionar Factura'"
  width="900px"
>
  <el-form label-width="120px">
    <el-form-item label="Nombre">
      <el-input
        v-model="facturaForm.nombre"
        placeholder="Nombre de la persona"
      />
    </el-form-item>
    <el-form-item label="NIT">
      <el-input
        v-model="facturaForm.nit"
        placeholder="Número de NIT"
      />
    </el-form-item>
    <el-form-item label="Detalle">
      <el-button type="primary" @click="agregarDetalle">
        Agregar producto
      </el-button>
    </el-form-item>
    <el-table
      :data="detalleForm"
      border
    >
    <el-table-column
        label="Item"
        width="80"
        align="center"
      >
        <template #default="fila">
          {{ fila.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="Producto">
        <template #default="fila">
          <el-input
            v-model="detalleForm[fila.$index].producto"
            placeholder="Producto"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="Cantidad"
        width="160"
        align="center"
      >
        <template #default="fila">
          <el-input-number
            v-model="detalleForm[fila.$index].cantidad"
            :min="1"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="Precio"
        width="160"
        align="center"
      >
        <template #default="fila">
          <el-input-number
            v-model="detalleForm[fila.$index].precio"
            :min="0"
            :step="1"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="Subtotal"
        width="140"
        align="right"
      >
        <template #default="fila">
          {{ calcularSubtotal(fila.row).toFixed(2) }} Bs.
        </template>
      </el-table-column>
      <el-table-column
        label="Acción"
        width="120"
        align="center"
      >
        <template #default="fila">
          <el-button
            type="danger"
            @click="quitarDetalle(fila.$index)"
          >
            Quitar
          </el-button>
        </template>
      </el-table-column>
      
    </el-table>
    <h2>Total: {{ toalFactura }} Bs.</h2>
    </el-form>
    <template #footer>
    <el-button @click="dialogVisible = false">
      Cancelar
    </el-button>
    <el-button type="primary" @click="guardarFactura">
      Guardar
    </el-button>
  </template>
    </el-dialog>
</template>

<script setup>
import { computed, reactive, ref } from 'vue'
import {
  getMiFacturaInfo,
  editMiFactura
} from '../../api/mifactura/mifactura.js'
const emit=defineEmits(['get-list'])
const dialogVisible = ref(false)
const facturaForm = reactive({
  id: null,
  nombre: '',
  nit: '',
  total: 0
})
const detalleForm = ref([])
const crearDetalleVacio = () => {
  return {
    id: null,
    facturaId: null,
    producto: '',
    cantidad: 1,
    precio: 0
  }
}
const limpiarFormulario = () => {
  facturaForm.id = null
  facturaForm.nombre = ''
  facturaForm.nit = ''
  facturaForm.total = 0
  detalleForm.value = [
    crearDetalleVacio()
  ]
}
const open = (row) => {
  limpiarFormulario()
  if (row && row.id) {
    getMiFacturaInfo({ id: row.id }).then((res) => {
      if (res.success) {
        const data = res.data

        facturaForm.id = data.id
        facturaForm.nombre = data.nombre
        facturaForm.nit = data.nit
        facturaForm.total = data.total
        detalleForm.value = data.detalles || []
        if (detalleForm.value.length === 0) {
          detalleForm.value = [
            crearDetalleVacio()
          ]
        }
        dialogVisible.value = true
      }
    })
  } else {
    dialogVisible.value = true
  }
  }
  const agregarDetalle = () => {
  detalleForm.value.push(crearDetalleVacio())
}
const quitarDetalle = (index) => {
  detalleForm.value.splice(index, 1)
  if (detalleForm.value.length === 0) {
    detalleForm.value.push(crearDetalleVacio())
  }
}
const calcularSubtotal = (detalle) => {
  const cantidad = Number(detalle.cantidad || 0)
  const precio = Number(detalle.precio || 0)
  return cantidad * precio
}

const totalFactura = computed(() => {
  const total = detalleForm.value.reduce((suma, item) => {
    return suma + calcularSubtotal(item)
  }, 0)
  return total.toFixed(2)
})
const validarFormulario = () => {
  if (!facturaForm.nombre) {
    alert('Debe ingresar el nombre')
    return false
  }
  if (!facturaForm.nit) {
    alert('Debe ingresar el NIT')
    return false
  }
  const detalleIncorrecto = detalleForm.value.some((item) => {
    return !item.producto || Number(item.cantidad) <= 0 || Number(item.precio) <= 0
  })
  if (detalleIncorrecto) {
    alert('Todos los productos deben tener producto, cantidad y precio')
    return false
  }
  return true
}
const guardarFactura = () => {
  if (!validarFormulario()) {
    return
  }
  const data = {
    id: facturaForm.id,
    nombre: facturaForm.nombre,
    nit: facturaForm.nit,
    detalles: detalleForm.value.map((item) => {
      return {
        id: item.id,
        facturaId: item.facturaId,
        producto: item.producto,
        cantidad: Number(item.cantidad),
        precio: Number(item.precio)
      }
    })
  }
  editMiFactura(data).then((res) => {
    if (res.success) {
      dialogVisible.value = false
      emit('get-list')
    } else {
      alert(res.msg)
    }
  })
}
defineExpose({
  open
})
</script>