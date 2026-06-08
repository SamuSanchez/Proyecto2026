import router from "./routers.js";
import {useStore} from "@/store/index.js";
import {queryAllMenu} from "@/api/menu/sysMenu.js";
import {errorMsg} from "@/utils/message.js";

// Comentario en espanol
const whiteList = ['/login', '/401', '/404']
const modules = import.meta.glob('@/views/**/*.vue')
// Comentario en espanol
router.beforeEach((to, from, next) => {
    console.log('Ruta solicitada: ' + to.path)
    const store = useStore()
    // Comentario en espanol
    if (store.token){
        // Comentario en espanol
        // Comentario en espanol
        if (to.path === '/'){
            next({path: '/login'})
        }
        // Comentario en espanol
        if (to.path === '/login'){
            // Comentario en espanol
            next({path: '/Layout'})
        } else {
            // Comentario en espanol
            if (!store.isLoadMenu){
                loadMenus(next, to)
            // Comentario en espanol
            } else {
                // Comentario en espanol
                if (!hashRoute(to)){
                    // Comentario en espanol
                    addRoute()
                    // Comentario en espanol
                    // Comentario en espanol
                    if (hashRoute(to)){
                        next(to.fullPath)
                        // Comentario en espanol
                    } else {
                        next({path: '/404'})
                    }
                // Comentario en espanol
                } else {
                    // Comentario en espanol
                    next()
                }
            }
        }
    } else {
        // Comentario en espanol
        if (whiteList.indexOf(to.path) !== -1){
            // Comentario en espanol
            next()
        // Comentario en espanol
        } else {
            next({path: '/login'})
        }
    }
})

/**
 * Comentario en espanol
 * @param next
 * @param to
 */
export function loadMenus(next, to){
    const store = useStore()
    queryAllMenu().then(res => {
        if (res.success){
            if (res.data.length > 0){
                // Comentario en espanol
                store.routerAction(res.data)
                // Comentario en espanol
                store.loadMenuAction(true)
                // Comentario en espanol
                next({...to, replace: true})
            }
        } else {
            errorMsg(res.msg)
        }
    })
}

/**
 * Comentario en espanol
 * @param to
 */
export function hashRoute(to){
    let find = router.getRoutes().find(item => item.path === to.path)
    return !!find
}

/**
 * Comentario en espanol
 */
export function addRoute(){
    const store = useStore()
    let routerList = store.routers
    if (routerList && routerList.length > 0){
        routerList.forEach(item => {
            if (item.path){
                const componentPath = `/src/views/${item.component}`
                router.addRoute('Layout', {
                    path: item.path,
                    name: item.name,
                    component: componentPath && modules[componentPath] ? modules[componentPath] : null
                })
            }
        })
    }
}
