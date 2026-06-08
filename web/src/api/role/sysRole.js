import request from "@/utils/request";

/**
 * Comentario en espanol
 * @param params
 * @returns {AxiosPromise}
 */
export function getRoleList(params){
    return request({
        url: '/api/sys/role/table',
        method: 'get',
        params
    })
}

/**
 * EditarRol
 * @param data
 * @returns {AxiosPromise}
 */
export function editRole(data){
    return request({
        url: '/api/sys/role/edit',
        method: 'post',
        data
    })
}

/**
 * RolAutorizar
 * @param data
 * @returns {AxiosPromise}
 */
export function authorizeRole(data){
    return request({
        url: '/api/sys/role/menu/edit',
        method: 'post',
        data
    })
}

/**
 * EliminarRol
 * @param params
 * @returns {AxiosPromise}
 */
export function delRole(params){
    return request({
        url: '/api/sys/role/del',
        method: 'delete',
        params
    })
}

/**
 * Comentario en espanol
 * @param params
 * @returns {AxiosPromise}
 */
export function getMenusByRoleId(params){
    return request({
        url: '/api/sys/role/menu/list',
        method: 'get',
        params
    })
}
