<template>
  <el-dialog title="Cambiar contraseña" v-model="visible" :close-on-click-modal="false">
    <el-form :model="passwordForm" :rules="rules" ref="passwordRef" label-width="120px">
      <el-form-item label="Contraseña actual" prop="password">
        <el-input type="password" v-model="passwordForm.password" placeholder="Introduce la contraseña actual" clearable></el-input>
      </el-form-item>
      <el-form-item label="Nueva contraseña" prop="newPassword">
        <el-input type="password" v-model="passwordForm.newPassword" placeholder="Introduce la nueva contraseña" clearable></el-input>
      </el-form-item>
      <el-form-item label="Confirmar contraseña" prop="confirmPassword">
        <el-input type="password" v-model="passwordForm.confirmPassword" placeholder="Confirma la contraseña" clearable></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span>
      <el-button @click="resetForm(passwordRef)">Restablecer</el-button>
      <el-button type="primary" @click="submitPassword">Confirmar</el-button>
    </span>
    </template>
  </el-dialog>
</template>

<script setup>
import {updatePwd} from "@/api/user/sysUser.js";
import {errorMsg, successMsg} from "@/utils/message.js";
import {computed, ref, reactive} from "vue";
import {resetForm} from "@/utils/common.js";
const props = defineProps({
  dialogVisible: {
    type: Boolean,
    require: true,
    default: false
  }
})

const emit = defineEmits(['update:dialog-visible'])

const visible = computed({
  get: () => props.dialogVisible,
  set: (value) => emit('update:dialog-visible', value)
})

const passwordForm = reactive({
  password: '',
  newPassword: '',
  confirmPassword: '',
})

const passwordRef = ref()

const validateNew = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('Introduce la contraseña'));
  } else {
    if (passwordForm.confirmPassword !== '') {
      passwordRef.value.validateField('confirmPassword');
    }
    callback();
  }
}

const validateConfirm = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('Confirma la contraseña'));
  } else if (value !== passwordForm.newPassword) {
    callback(new Error('Las contraseñas no coinciden'));
  } else {
    callback();
  }
}

const rules = reactive( {
  password: [{required: true, message: 'La contraseña actual es obligatoria', trigger: 'blur'}],
  newPassword: [
      {required: true, message: 'La nueva contraseña es obligatoria', trigger: 'blur'},
      {validator: validateNew, trigger: 'blur'}
  ],
  confirmPassword: [
      {required: true, message: 'La confirmación de contraseña es obligatoria', trigger: 'blur'},
      {validator: validateConfirm, trigger: 'blur'}
  ]
})
// Comentario en espanol
const submitPassword = () => {
  passwordRef.value.validate((valid) => {
    if (valid){
      updatePwd(passwordForm).then(res => {
        if (res.success){
          successMsg(res.data)
          visible.value = false
          emit('logout')
        } else {
          errorMsg(res.msg)
        }
      })
    }
  })
}
</script>

<style scoped>
 :deep(.vue-treeselect__control){
  height: 28px;
}
 :deep(.el-form-item__content){
   line-height: 28px;
   font-size: 12px;
 }
</style>
