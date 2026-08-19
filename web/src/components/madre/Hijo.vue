<template>
    <el-dialog
        v-model="dialogVisible"
  :title="esNuevo ? 'Adicionar Madre' : 'Modificar Madre'"
  width="900px"
>
  <el-form label-width="120px">
    <el-form-item label="CIM">
      <el-input
        v-model="madreForm.cim"
        placeholder="CIM de la madre"
        :disabled="!esNuevo"
      />
    </el-form-item>
    <el-form-item label="Nombre">
      <el-input
        v-model="madreForm.nombre"
        placeholder="Nombre de la madre"
      />
    </el-form-item>
    <el-form-item label="Apellido Paterno">
      <el-input
        v-model="madreForm.apellidoPaterno"
        placeholder="Apellido paterno"
      />
    </el-form-item>
    <el-form-item label="Apellido Materno">
      <el-input
        v-model="madreForm.apellidoMaterno"
        placeholder="Apellido materno"
      />
    </el-form-item>
    <el-form-item label="Detalle">
      <el-button type="primary" @click="agregarHijo">
        Agregar hijo
      </el-button>
    </el-form-item>
    <el-table
      :data="hijoForm"
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
      <el-table-column
        label="CIH"
        width="140"
        align="center"
      >
        <template #default="fila">
          <el-input
            v-model="hijoForm[fila.$index].cih"
            placeholder="CIH"
            :disabled="hijoForm[fila.$index].existe"
          />
        </template>
      </el-table-column>
      <el-table-column label="Nombre">
        <template #default="fila">
          <el-input
            v-model="hijoForm[fila.$index].nombre"
            placeholder="Nombre"
          />
        </template>
      </el-table-column>
      <el-table-column label="Apellido Paterno">
        <template #default="fila">
          <el-input
            v-model="hijoForm[fila.$index].ap"
            placeholder="Apellido Paterno"
          />
        </template>
      </el-table-column>
      <el-table-column label="Apellido Materno">
        <template #default="fila">
          <el-input
            v-model="hijoForm[fila.$index].am"
            placeholder="Apellido Materno"
          />
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
            @click="quitarHijo(fila.$index)"
          >
            Quitar
          </el-button>
        </template>
      </el-table-column>
      
    </el-table>
    </el-form>
    <template #footer>
    <el-button @click="dialogVisible = false">
      Cancelar
    </el-button>
    <el-button type="primary" @click="guardarMadre">
      Guardar
    </el-button>
  </template>
    </el-dialog>
</template>

<script setup>
import { reactive, ref } from 'vue'
import {
  getMadreInfo,
  editMadre
} from '../../api/madre/madre.js'
import {
  getHijoList,
  editHijo,
  delHijo
} from '../../api/hijo/hijo.js'
const emit=defineEmits(['get-list'])
const dialogVisible = ref(false)
const esNuevo = ref(true)
const madreForm = reactive({
  cim: '',
  nombre: '',
  apellidoPaterno: '',
  apellidoMaterno: ''
})
const hijoForm = ref([])
const crearHijoVacio = () => {
  return {
    cih: '',
    nombre: '',
    ap: '',
    am: '',
    existe: false
  }
}
const limpiarFormulario = () => {
  madreForm.cim = ''
  madreForm.nombre = ''
  madreForm.apellidoPaterno = ''
  madreForm.apellidoMaterno = ''
  hijoForm.value = [
    crearHijoVacio()
  ]
}
const cargarHijos = (cim) => {
  getHijoList({ cim: cim }).then((res) => {
    if (res.success) {
      const lista = (res.data || []).map((item) => {
        return { ...item, existe: true }
      })
      hijoForm.value = lista.length > 0 ? lista : [crearHijoVacio()]
    }
  })
}
const open = (row) => {
  limpiarFormulario()
  if (row && row.cim) {
    esNuevo.value = false
    getMadreInfo({ cim: row.cim }).then((res) => {
      if (res.success) {
        const data = res.data

        madreForm.cim = data.cim
        madreForm.nombre = data.nombre
        madreForm.apellidoPaterno = data.apellidoPaterno
        madreForm.apellidoMaterno = data.apellidoMaterno
      }
    })
    cargarHijos(row.cim)
    dialogVisible.value = true
  } else {
    esNuevo.value = true
    dialogVisible.value = true
  }
  }
  const agregarHijo = () => {
  hijoForm.value.push(crearHijoVacio())
}
const quitarHijo = (index) => {
  const hijo = hijoForm.value[index]
  if (hijo.existe) {
    delHijo({ cih: hijo.cih }).then((res) => {
      if (res.success) {
        hijoForm.value.splice(index, 1)
        if (hijoForm.value.length === 0) {
          hijoForm.value.push(crearHijoVacio())
        }
      } else {
        alert(res.msg)
      }
    })
  } else {
    hijoForm.value.splice(index, 1)
    if (hijoForm.value.length === 0) {
      hijoForm.value.push(crearHijoVacio())
    }
  }
}
const validarFormulario = () => {
  if (!madreForm.cim) {
    alert('Debe ingresar el CIM')
    return false
  }
  if (!madreForm.nombre) {
    alert('Debe ingresar el nombre')
    return false
  }
  const hijoIncorrecto = hijoForm.value.some((item) => {
    return !item.cih || !item.nombre
  })
  if (hijoIncorrecto) {
    alert('Todos los hijos deben tener CIH y nombre')
    return false
  }
  return true
}
const guardarHijos = () => {
  const promesas = hijoForm.value
    .filter((item) => !item.existe)
    .map((item) => {
      const data = {
        cih: item.cih,
        nombre: item.nombre,
        ap: item.ap,
        am: item.am,
        cim: madreForm.cim
      }
      return editHijo(data)
    })
  Promise.all(promesas).then(() => {
    dialogVisible.value = false
    emit('get-list')
  })
}
const guardarMadre = () => {
  if (!validarFormulario()) {
    return
  }
  const dataMadre = {
    cim: madreForm.cim,
    nombre: madreForm.nombre,
    apellidoPaterno: madreForm.apellidoPaterno,
    apellidoMaterno: madreForm.apellidoMaterno
  }
  editMadre(dataMadre).then((res) => {
    if (res.success) {
      guardarHijos()
    } else {
      alert(res.msg)
    }
  })
}
defineExpose({
  open
})
</script>