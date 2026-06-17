<template>
    <el-form :model="form" :rules="rules" ref="formRef">  
        <el-form-item label="Inrodusca Nombre" prop="nombre">
            <el-input v-model="form.nombre" placeholder="sin nombre"></el-input>
        </el-form-item>

        <el-form-item label="Inrodusca Correo" prop="correo">
            <el-input v-model="form.correo" placeholder="ejemplo@correo.com"></el-input>
        </el-form-item>

        <el-form-item label="Inrodusca Edad">
            <el-input v-model.number="form.edad" placeholder="sin edad"></el-input>
        </el-form-item>

        <el-form-item label="Inrodusca Edad">
            <el-button @click="enviarFormulario" type="primary">Enviar</el-button>
        </el-form-item>
    </el-form>
</template>
<script setup>
import { reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
const formRef = ref(null);
const form = reactive({
    nombre: 'sin nombre',
    correo: 'sin correo',
    edad: 0,
});
const EnviarFormulario = async() => {
    if (!formRef.value) return
     await 
     formRef.value.velidate((valid) => {
        if (valid) {
            ElMessage.success('Formulario enviado Correctamente');
        }else {
            ElMessage.error('Revise los campos del formulario');
     }
     })
};
/*const enviarFormulario = () => {
    console.log( form);
};*/
const rules = {
    nombre: [{required:true, message:'El nombre es un campo obligatorio', trigger:'blur'},{min:3, message:'El nombre debe tener al menos 3 caracteres', trigger:'blur'}],
    correo: [{type: 'email', message:'Correo Invalido', trigger:['blur','change']}],
    edad: [{required:true, message:'La edad es un campo obligatorio', trigger:'blur'}]
}

</script>