<template>
  <el-tabs v-model="activeIndex" type="card" @tab-click="clickTab" @tab-remove="removeTab">
    <el-tab-pane
        v-for="(item, index) in tabs"
        :closable="item.isClose"
        :key="index"
        :label="item.name"
        :name="item.name">
      <router-view></router-view>
    </el-tab-pane>
  </el-tabs>
</template>

<script setup>
import {storeToRefs} from 'pinia'
import {useStore} from "@/store";
import {useRouter} from 'vue-router'

import {computed} from "vue";

const router = useRouter()

const store = useStore()

const { activeIndex } = storeToRefs(store)

const tabs = computed(() => {
  return store.openTabs
})
  // Comentario en espanol
const clickTab = (tab) => {
  store.activeIndex = tab.paneName
  // Comentario en espanol
  router.push({name: tab.paneName})
}
  // Comentario en espanol
const removeTab = (name) => {
    // Comentario en espanol
    tabs.value.forEach((tab, index) => {
      // Comentario en espanol
      if (tab.name === name){
        // Comentario en espanol
        // Comentario en espanol
        const nextTab = tabs.value[index + 1] || tabs.value[index - 1]
        if (nextTab){
          store.activeIndex = nextTab.name
          // Comentario en espanol
          router.push({path: nextTab.path})
        }
      }
    })
    // Comentario en espanol
    store.removeTabAction(name)
}
</script>

<style scoped>
 :deep(.el-tabs__content){
  height: calc(100vh - 120px);
  padding-left: 30px;
  padding-right: 30px;
}
 :deep(.el-tabs__nav-wrap){
  height: 40px;
  border-top: 1px solid #d8dce5;
  border-bottom: 1px solid #d8dce5;
  background-color: rgb(247, 247, 247);
}

:deep(.el-tabs__nav){
  border: none!important;
  height: 40px;
  display: flex;
  align-items: center;
}

:deep(.el-tabs__item.is-active){
  height: 30px;
  line-height: 30px;
  background-color: #42b983;
  border-color: #42b983;
  color: white;
}

:deep(.el-tabs__item.is-active::before) {
  content: "";
  background-color: #fff;
  display: inline-block;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  margin-right: 4px;
}
</style>
