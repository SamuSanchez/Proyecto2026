import request from "@/utils/request";

/**
 * Comentario en espanol
 * @param params
 * @returns {AxiosPromise}
 */
export function getUserList(params){
    return request({
        url: '/api/sys/user/table',
        method: 'get',
        params
    })
}

/**
 * Comentario en espanol
 * @param data
 * @returns {AxiosPromise}
 */
export function editUser(data){
    return request({
        url: '/api/sys/user/edit',
        method: 'post',
        data
    })
}

/**
 * Comentario en espanol
 * @param params
 * @returns {AxiosPromise}
 */
export function delUser(params){
    return request({
        url: '/api/sys/user/del',
        method: 'delete',
        params
    })
}

/**
 * Comentario en espanol
 * @param data
 * @returns {AxiosPromise}
 */
export function enabledUser(data){
    return request({
        url: '/api/sys/user/enabled',
        method: 'put',
        data
    })
}

/**
 * Comentario en espanol
 * @param data
 * @returns {AxiosPromise}
 */
export function updatePwd(data){
    return request({
        url: '/api/sys/user/password',
        method: 'put',
        data
    })
}
