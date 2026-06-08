import axios from "axios";
import routers from '@/router/routers.js'
import {useStore} from "@/store/index.js";
import {errorMsg} from "./message.js";

// Comentario en espanol
const instance = axios.create({
    baseURL: process.env.NODE_ENV === 'production' ? process.env.VUE_APP_BASE_URL : '/',
    timeout: 60000   // Tiempo de espera de la solicitud (ms)
})

// Comentario en espanol
instance.interceptors.request.use(
    config => {
        const store = useStore()
        // Comentario en espanol
        if (store.token){
            // Comentario en espanol
            config.headers['Authorization'] = 'Bearer ' + store.token
        }
        // Comentario en espanol
        config.headers['Content-Type'] = 'application/json'
        return config
    },
    error => {
        Promise.reject(error)
    }
)

// Comentario en espanol
instance.interceptors.response.use(
    response => {
        // Comentario en espanol
        return response.data
    },
    error => {
        const store = useStore()
        if (!error.response){
            errorMsg(error.message)
        } else {
            // Comentario en espanol
            let code;
            if (error.response){
                code = error.response.status
            }
            // Comentario en espanol
            const data = error.response.data
            if (code){
                // Comentario en espanol
                if (code === 401){
                    // Comentario en espanol
                    const refresh = store.refreshToken
                    // Comentario en espanol
                    if (refresh){
                        return againRequest(refresh, error)
                    // Comentario en espanol
                    } else {
                        // Comentario en espanol
                        store.tokenAction(null)
                        // Comentario en espanol
                        routers.push({
                            path: '/login',
                            query: {
                                backto: routers.currentRoute.fullPath
                            }
                        })
                    }
                    // Comentario en espanol
                } else if (code === 403){
                    // Comentario en espanol
                    routers.replace({path: '/401'})
                    // Comentario en espanol
                } else {
                    // Comentario en espanol
                    if (data){
                        errorMsg(data.detail)
                    }
                }
            } else {
                errorMsg('Error en la solicitud de la API')
            }
        }
        return Promise.reject(error)
    }
)

/**
 * Comentario en espanol
 * @param error
 * @returns {Promise<void>}
 */
async function againRequest(refresh, error){
    const store = useStore()
    await refreshToken(refresh)
    const config = error.response.config
    config.headers['Authorization'] = 'Bearer ' + store.token
    const res = await axios.request(config)
    return res.data
}

/**
 * Comentario en espanol
 * @param refresh
 * @param config
 */
export function refreshToken(refresh){
    const store = useStore()
    // Comentario en espanol
    return axios({
        url: '/auth/refresh',
        method: 'put',
        headers: {
            Authorization: `Bearer ${refresh}`
        }
    }).then(res => {
        if (res.data.success){
            // Comentario en espanol
            store.tokenAction(res.data.data)
        } else {
            errorMsg(res.msg)
            // Comentario en espanol
            store.tokenAction(null)
        }
    })
}

export default instance
