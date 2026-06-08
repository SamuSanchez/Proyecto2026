<template>
  <div style="width: 100%;">
    <el-row>
      <el-col :span="12">
        <span>Sistema de administración EMS</span>
      </el-col>
      <el-col :span="12">
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link">{{username}}<i class="el-icon-arrow-down el-icon--right"></i></span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="pwd">Cambiar contraseña</el-dropdown-item>
              <el-dropdown-item command="logout">Cerrar sesión</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
    </el-row>
    <update-password v-model:dialog-visible="dialogVisible" @logout="clearToken"></update-password>
  </div>
</template>

<script setup>
import updatePassword from "@/views/user/updatePassword.vue";
import {useStore} from "@/store/index.js";
import routers from "@/router/routers.js";
import {infoMsg} from "@/utils/message.js";
import {ref} from "vue";
import {ElMessageBox} from "element-plus";

const store = useStore()

const username = ref(store.userInfo.nickName)

const dialogVisible = ref(false)

const handleCommand = (command) => {
  if (command === 'logout'){
    logout()
  } else if (command === 'pwd'){
    dialogVisible.value = true
  }
}
// Comentario en espanol
const logout = () => {
  ElMessageBox.confirm('¿Confirmas cerrar la sesión actual?', 'Aviso', {
    confirmButtonText: 'Confirmar',
    cancelButtonText: 'Cancelar',
    type: 'warning'
  }).then(() => {
    clearToken()
  }).catch(() => {
    infoMsg('Operación cancelada')
  })
}
// Comentario en espanol
const clearToken = () => {
  // Comentario en espanol
  store.token = null
  // Comentario en espanol
  store.refreshToken = null
  // Comentario en espanol
  routers.push({path: '/login'})
}
</script>

<style scoped>
  .el-row{
    width: 100%;
  }
  .el-dropdown{
    float: right;
  }
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
