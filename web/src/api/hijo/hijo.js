import request from '@/utils/request'

/**
 * Lista los hijos de una madre.
 */
export function getHijoList(params) {
  return request({
    url: '/api/sys/hijo/list',
    method: 'get',
    params
  })
}

/**
 * Registra o actualiza un hijo.
 */
export function editHijo(data) {
  return request({
    url: '/api/sys/hijo/edit',
    method: 'post',
    data
  })
}

/**
 * Elimina un hijo por CIH.
 */
export function delHijo(params) {
  return request({
    url: '/api/sys/hijo/del',
    method: 'delete',
    params
  })
}
