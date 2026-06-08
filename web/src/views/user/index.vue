<template>
  <div>
    <div class="searchDiv">
      <el-input class="searchInput" v-model="state.blurry" placeholder="Introduce usuario o nombre" clearable></el-input>
      <el-button type="primary" @click="getUserListFun">Buscar</el-button>
      <el-button v-if="hasPer('user:add')" @click="editUserFun" style="float: right;">Nuevo</el-button>
    </div>
    <el-table :data="state.tableData" row-key="id" border>
      <el-table-column label="Usuario" prop="username"></el-table-column>
      <el-table-column label="Nombre" prop="nickName"></el-table-column>
      <el-table-column label="Rol" prop="roles"></el-table-column>
      <el-table-column label="Estado" prop="enabled" width="100">
        <template #default="scope">
          <el-tag v-if="scope.row.enabled" type="success">Activo</el-tag>
          <el-tag v-else type="danger">Inactivo</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Acciones" prop="option" width="220px" align="center">
        <template #default="scope">
          <span v-if="scope.row.username !== state.username">
            <el-button v-if="hasPer('user:enabled')" :type="buttonType(scope.row.enabled)"
                       @click="enabledUserFun(JSON.parse(JSON.stringify(scope.row)))">
              {{scope.row.enabled ? 'Inactivo' : 'Activo'}}</el-button>
            <el-button v-if="hasPer('user:edit')" type="primary" @click="editUserFun(JSON.parse(JSON.stringify(scope.row)))">Editar</el-button>
            <el-button v-if="hasPer('user:del')" type="danger" @click="deleteUser(scope.row.id, scope.row.username)">Eliminar</el-button>
          </span>
        </template>
      </el-table-column>
    </el-table>
    <!-- Comentario en espanol -->
    <pagination v-model:current="state.current" v-model:size="state.size" v-model:total="state.total" @get-list="getUserListFun"></pagination>
    <!--Editar-->
    <EditUser v-model:dialog-visible="dialogVisible" :user-obj="state.userObj" @get-list="getUserListFun"></EditUser>
  </div>
</template>

<script setup>
import EditUser from "./editUser.vue";
import Pagination from "@/components/Pagination.vue";
import {getUserList, delUser, enabledUser} from "@/api/user/sysUser.js";
import {errorMsg, infoMsg, successMsg} from "@/utils/message.js";
import {hasPer} from "@/utils/common.js";
import {useStore} from "@/store/index.js";
import {onMounted, reactive, ref} from "vue";
import {ElMessageBox} from "element-plus";

const store = useStore()

const state = reactive({
  blurry: '',
  username: store.userInfo.username,
  tableData: [],
  userObj: {},
  current: 1,
  size: 10,
  total: 0
})

const dialogVisible = ref(false)

onMounted(() => {
  getUserListFun()
})

const getUserListFun = () => {
  const params = {
    blurry: state.blurry,
    size: state.size,
    currentPage: state.current
  }
  getUserList(params).then(res => {
    if (res.success){
      state.tableData = res.data.records
      state.total = res.data.total
    }
  })
}

const editUserFun = (row) => {
  state.userObj = row?.id ? row : {}
  dialogVisible.value = true
}

const deleteUser = (id, username) => {
  ElMessageBox.confirm('¿Confirmas eliminar el usuario [' + username + ']?', 'Aviso', {
    confirmButtonText: 'Confirmar',
    cancelButtonText: 'Cancelar',
    type: 'warning'
  }).then(() => {
    delUser({id: id}).then(res => {
      if (res.success){
        successMsg(res.data)
        getUserListFun()
      } else {
        errorMsg(res.msg)
      }
    })
  }).catch(() => {
    infoMsg('Operación cancelada')
  })
}
// Comentario en espanol
const enabledUserFun = (row) => {
  row.enabled = !row.enabled
  const str = row.enabled ? 'Activo' : 'Inactivo'
  const color = row.enabled ? '#67C23A' : '#F56C6C'
  ElMessageBox.confirm('¿Confirmas <span style="color: '+color+'">' + str + '</span> del usuario [' + row.nickName + ']?', 'Aviso', {
    confirmButtonText: 'Confirmar',
    cancelButtonText: 'Cancelar',
    type: 'warning',
    dangerouslyUseHTMLString: true
  }).then(() => {
    enabledUser(row).then(res => {
      if (res.success){
        successMsg(res.data)
        getUserListFun()
      } else {
        errorMsg(res.msg)
      }
    })
  }).catch(() => {
    infoMsg('Operación cancelada')
  })
}

const buttonType = (type) => {
  if (type){
    return 'warning'
  } else {
    return 'success'
  }
}
</script>

<style scoped>

</style>
