import request from "@/utils/request";

/**
 * Comentario en espanol
 * @returns {AxiosPromise}
 */
export function queryAllMenu(){
    return request({
        url: '/api/sys/menu/all',
        method: 'get'
    })
}

/**
 * Comentario en espanol
 * @returns {AxiosPromise}
 */
export function getMenuTree(){
    return request({
        url: '/api/sys/menu/tree',
        method: 'get'
    })
}

/**
 * Comentario en espanol
 * @param params
 * @returns {AxiosPromise}
 */
export function getMenuTable(params){
    return request({
        url: '/api/sys/menu/table',
        method: 'get',
        params
    })
}

/**
 * EditarMenu
 * @param data
 * @returns {AxiosPromise}
 */
export function editMenu(data){
    return request({
        url: '/api/sys/menu/edit',
        method: 'post',
        data
    })
}

/**
 * Comentario en espanol
 * @param params
 * @returns {AxiosPromise}
 */
export function delMenu(params){
    return request({
        url: '/api/sys/menu/del',
        method: 'delete',
        params
    })
}

/**
 * Comentario en espanol
 * @returns {AxiosPromise}
 */
export function getPermission(){
    return request({
        url: '/api/sys/menu/permission',
        method: 'get'
    })
}


export function getMenuTreeSelect(){
    return request({
        url: '/api/sys/menu/select',
        method: 'get'
    })
}
