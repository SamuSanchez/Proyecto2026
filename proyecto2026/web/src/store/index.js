import {defineStore} from "pinia";

export const useStore = defineStore('ems-vue3', {
    persist: true,
    state: () => {
        return {
            token: null,
            // Comentario en espanol
            refreshToken: null,
            // Comentario en espanol
            userInfo: null,
            // Comentario en espanol
            isLoadMenu: false,
            // Comentario en espanol
            routers: null,
            // Comentario en espanol
            permission: null,
            // Comentario en espanol
            activeIndex: 'Inicio',
            // Comentario en espanol
            openTabs: []
        }
    },
    actions: {
        // Comentario en espanol
        loadMenuAction(payload){
            this.isLoadMenu = payload
        },
        // Comentario en espanol
        routerAction(payload){
            this.routers = payload
        },
        // Comentario en espanol
        permissionAction(payload){
            this.permission = payload
        },
        // Comentario en espanol
        tokenAction(payload){
            this.token = payload
        },
        // Comentario en espanol
        activeIndexAction(payload){
            this.activeIndex = payload
        },
        // Comentario en espanol
        addTabAction(payload){
            // Comentario en espanol
            if (this.openTabs.filter(tab => tab.name === payload.name).length === 0){
                payload.isClose = payload.name !== 'Inicio'
                this.openTabs.push(payload)
            }
        },
        // Comentario en espanol
        removeTabAction(payload){
            this.openTabs = this.openTabs.filter((tab) => tab.name !== payload)
        },
        // Comentario en espanol
        clearTabAction(){
            this.openTabs = []
        }
    }
})
