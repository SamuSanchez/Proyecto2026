<template>
  <el-menu :default-active="defaultActive" :unique-opened="true" router :collapse="isCollapse"
           background-color="#545c64" text-color="#fff">
    <div class="logo" @click="changeCollapse">
      <el-image :src="emsPath" style="width: 40px;"></el-image>
    </div>
    <!-- Comentario en espanol -->
    <el-menu-item route="/home" index="Inicio" @click="openTab('Inicio', '/home')">
      <i class="iconfont icon-home"></i>
      <template #title>
        Inicio
      </template>
    </el-menu-item>
    <menu-tree :menu-data="menuList"></menu-tree>
  </el-menu>
</template>

<script setup>
import {useStore} from "@/store/index.js";
import {getMenuTree, getPermission} from "@/api/menu/sysMenu.js";
import {errorMsg} from "@/utils/message.js";
import {computed, onMounted, ref} from "vue";
import MenuTree from "@/components/MenuTree.vue";
import emsPath from "@/assets/image/ems.png";

const store = useStore()

const emit = defineEmits(['update:width'])

const menuList = ref([])

const isCollapse = ref(false)

const defaultActive = computed(() => {
  return store.activeIndex
})

onMounted(() => {
  // Comentario en espanol
  getMenuTree().then(res => {
    if (res.success){
      menuList.value = res.data
    }
  })
// Comentario en espanol
  getPermission().then(res => {
    if (res.success){
      store.permissionAction(res.data)
    } else {
      errorMsg(res.msg)
    }
  })
})
// Comentario en espanol
const openTab = (name, path) => {
  console.log(name, path)
  // Comentario en espanol
  store.addTabAction({name: name, path: path})
  // Comentario en espanol
  store.activeIndex = name
}
// Comentario en espanol
const changeCollapse = () => {
  isCollapse.value = !isCollapse.value
  if (isCollapse.value){
    emit('update:width', '64px')
  } else {
    emit('update:width', '230px')
  }
}
</script>

<style scoped>
  .el-menu{
    height: 100%;
  }
  .logo{
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    font-size: 20px;
    height: 60px;
    cursor: pointer;
  }
</style>
