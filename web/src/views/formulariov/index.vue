<template>
    <el-form :model="form" :rules="rules" ref="formRef">  
        <el-form-item label="Inrodusca Nombre" prop="nombre">
            <el-input v-model="form.nombre" placeholder="sin nombre"></el-input>
        </el-form-item>

        <el-form-item label="Inrodusca Correo" prop="correo">
            <el-input v-model="form.correo" placeholder="ejemplo@correo.com"></el-input>
        </el-form-item>

        <el-form-item label="Inrodusca Edad" prop="edad">
            <el-input v-model.number="form.edad" placeholder="sin edad"></el-input>
        </el-form-item>

        <el-form-item label="contraseña" prop="contrasena">
            <el-input v-model="form.contrasena" type="password" placeholder="ingrese su contraseña" show-password></el-input>
        </el-form-item>

        <el-form-item prop="aceptaTerminos">
            <el-checkbox v-model="form.aceptaTerminos">Acepto los terminos y condiciones</el-checkbox>
        </el-form-item>    

        <el-form-item>
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
    contrasena: '',
    aceptaTerminos: false,
});

const validarTerminos = (rule, value, callback) => {
    if (!value) {
        callback(new Error('Debe aceptar los terminos y condiciones para poder enviar el formulario'));
    } else {
        callback();
    }
};

const enviarFormulario = async() => {
    if (!formRef.value) return
     await 
     formRef.value.validate((valid) => {
        if (valid) {
            ElMessage.success('Formulario enviado Correctamente');
        }else {
            ElMessage.error('Revise los campos del formulario');
     }
     });
};
/*const enviarFormulario = () => {
    console.log( form);
};*/
const rules = {
    nombre: [{required:true, message:'El nombre es un campo obligatorio', trigger:'blur'},{min:3, message:'El nombre debe tener al menos 3 caracteres', trigger:'blur'}],
    correo: [{type: 'email', message:'Correo Invalido', trigger:['blur','change']}],
    edad: [{required:true, message:'La edad es un campo obligatorio', trigger:'blur'}],
    contrasena: [{required:true, message:'La contraseña es obligatoria', trigger: 'blur'}, {min:6, message:'La contraseña debe tener al menos 6 caracteres', trigger:'blur'}],
    aceptaTerminos: [{validator: validarTerminos, trigger:'change'}],

}

</script>