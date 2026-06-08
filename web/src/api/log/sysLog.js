import request from "@/utils/request.js";

/**
 * Comentario en espanol
 * @param params
 * @returns {AxiosPromise}
 */
export function getLogList(params){
    return request({
        url: '/api/sys/log/list',
        method: 'get',
        params
    })
}
