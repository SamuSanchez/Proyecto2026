<template>
  <el-container>
    <el-aside v-model:width="width">
      <Aside v-model:width="width"></Aside>
    </el-aside>
    <el-container>
      <el-header>
        <Header></Header>
      </el-header>
      <el-main>
        <Main></Main>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import Header from "./modules/Header.vue";
import Main from "./modules/Main.vue";
import Aside from "./modules/Aside.vue";
import {useStore} from "@/store/index.js";
import routers from "@/router/routers.js";
import {onBeforeMount, ref} from "vue";

const store = useStore()

onBeforeMount(() => {
      // Comentario en espanol
    store.clearTabAction()
    // Comentario en espanol
    store.addTabAction({path: '/home', name: 'Inicio'})
    // Comentario en espanol
    store.activeIndexAction('Inicio')
    // Comentario en espanol
    routers.push({path: '/home'})
})

const width = ref('230px')
</script>

<style scoped>
  .el-aside{
    height: calc(100vh);
  }
  .el-header{
    display: flex;
    align-items: center;
  }
  .el-main{
    padding: 0;
  }
</style>
