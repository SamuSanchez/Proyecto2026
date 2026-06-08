<template>
  <div class="login">
    <el-form class="form" :model="loginForm" :rules="rules" ref="loginRef">
      <h1>Inicio de sesion</h1>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" placeholder="Introduce el usuario" @keyup.enter="submitLogin(loginRef)">
          <template #prefix>
            <i class="iconfont icon-username"></i>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" placeholder="Introduce la contrasena" @keyup.enter="submitLogin(loginRef)">
          <template #prefix>
            <i class="iconfont icon-password"></i>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <div style="display: inline-flex;justify-content: space-between;width: 100%;">
          <el-input type="text" v-model="loginForm.code" style="width: 170px;" placeholder="Introduce el codigo de verificacion" @keyup.enter="submitLogin(loginRef)">
            <template #prefix>
              <i class="iconfont icon-captcha"></i>
            </template>
          </el-input>
          <img :src="state.image" @click="getCode">
        </div>
      </el-form-item>
      <el-form-item>
        <el-button class="button" type="primary" :loading="isLoading" @click="submitLogin(loginRef)">Iniciar sesion</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import '@/assets/css/login.css'
import {useStore} from "@/store/index.js";
import routers from "@/router/routers.js";
import {login, getVerifyCode} from "@/api/login/login.js";
import {errorMsg} from "@/utils/message.js";
import {ref, reactive, onMounted} from 'vue'

  const store = useStore()

  const loginRef = ref(null)

  const isLoading = ref(false)

  const state = reactive({
    image: ''
  })

  const loginForm = reactive({
    username: '',
    password: '',
    code: '',
    uuid: ''
  })

  const rules = reactive({
    username: [{ required: true, message: 'El usuario es obligatorio', trigger: 'blur' }],
    password: [{ required: true, message: 'La contrasena es obligatoria', trigger: 'blur' }],
    code: [{ required: true, message: 'El codigo de verificacion es obligatorio', trigger: 'blur' }]
  })

  onMounted(() => {
    getCode()
  })

  // Comentario en espanol
  const getCode = () =>{
    getVerifyCode().then(res => {
      state.image = res.img
      loginForm.uuid = res.uuid
    })
  }

  const submitLogin = (loginRef) => {
    loginRef.validate((valid) => {
      if (valid){
        isLoading.value = true
        login(loginForm).then(res => {
          if (res.success){
            // Comentario en espanol
            store.token = res.data.token
            // Comentario en espanol
            store.refreshToken = res.data.refreshToken
            // Comentario en espanol
            store.userInfo = res.data.userDto
            // Comentario en espanol
            routers.push({path: '/Layout'})
          } else {
            errorMsg(res.msg)
          }
          isLoading.value = false
        })
      }
    })
  }
</script>

<style scoped>

</style>
