<template>
  <div>
    <div class="searchDiv">
      <el-input class="searchInput" v-model="state.blurry" placeholder="Introduce operador o descripción" clearable></el-input>
      <el-select class="searchInput" v-model="state.logType" placeholder="Selecciona el tipo de registro" clearable>
        <el-option value="1" label="Éxito"></el-option>
        <el-option value="2" label="Error"></el-option>
      </el-select>
      <el-button type="primary" @click="getLogs">Buscar</el-button>
    </div>
    <el-table :data="state.tableData" row-key="id" border>
      <el-table-column label="Nº" type="index" width="60">
        <template #default="scope">
          <span>{{(state.current - 1) * state.size + 1 + scope.$index}}</span>
        </template>
      </el-table-column>
      <el-table-column label="Operador" prop="username" width="100"></el-table-column>
      <el-table-column label="Descripción de operación" prop="description" show-overflow-tooltip width="150"></el-table-column>
      <el-table-column label="Método" prop="method" show-overflow-tooltip></el-table-column>
      <el-table-column label="Parámetros" prop="params" show-overflow-tooltip></el-table-column>
      <el-table-column label="IP" prop="ip" width="120"></el-table-column>
      <el-table-column label="Tipo de registro" prop="logType" width="100">
        <template #default="scope">
          <span>{{scope.row.logType === '1' ? 'Éxito' : 'Error'}}</span>
        </template>
      </el-table-column>
      <el-table-column label="Detalle del error" prop="exceptionDetail" show-overflow-tooltip>
        <!-- Comentario en espanol -->
        <template #default="scope">
          <span>{{scope.row.exceptionDetail ? scope.row.exceptionDetail.substring(0, 100) : ''}}</span>
        </template>
      </el-table-column>
      <el-table-column label="Tiempo de respuesta" prop="time" width="100"></el-table-column>
      <el-table-column label="Fecha de operación" prop="createTime" width="150"></el-table-column>
      <el-table-column label="Acciones" prop="option" width="120" align="center">
        <template #default="scope">
          <el-button type="primary" :disabled="scope.row.logType === '1'" @click="showErrorDetails(scope.row.exceptionDetail)">Detalle del error</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- Comentario en espanol -->
    <pagination v-model:current="state.current" v-model:size="state.size" v-model:total="state.total" @get-list="getLogs"></pagination>
    <error-detail v-model:dialog-visible="dialogVisible" :msg="state.msg"></error-detail>
  </div>
</template>

<script setup>
import {getLogList} from "@/api/log/sysLog.js";
import {errorMsg} from "@/utils/message.js";
import Pagination from "@/components/Pagination.vue";
import ErrorDetail from "./ErrorDetail.vue";
import {onMounted, reactive, ref} from "vue";

const state = reactive({
  blurry: '',
  msg: null,
  tableData: [],
  logType: '',
  current: 1,
  size: 10,
  total: 0
})

onMounted(() => {
  getLogs()
})

const dialogVisible = ref(false)

const getLogs = () => {
  const params = {
    blurry: state.blurry,
    currentPage: state.current,
    size: state.size,
    logType: state.logType
  }
  getLogList(params).then(res => {
    if (res.success){
      state.tableData = res.data.records
      state.total = res.data.total
    } else {
      errorMsg(res.msg)
    }
  })
}
// Comentario en espanol
const showErrorDetails = (msg) => {
  dialogVisible.value = true
  state.msg = msg
}
</script>

<style scoped>
.el-tooltip__popper{
  margin-left: 20px;
}
</style>
