import request from "@/utils/request";

//  Iniciar sesion
export function login(data){
    return request({
        url: '/api/auth/login',
        method: 'post',
        data
    })
}

// Comentario en espanol
export function getVerifyCode(params){
    return request({
        url: '/api/auth/code',
        method: 'get',
        params
    })
}
