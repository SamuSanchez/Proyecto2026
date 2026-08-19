import request from '@/utils/request'

/**
 * Lista todas las madres registradas.
 */
export function getMadreList() {
  return request({
    url: '/api/sys/madre/list',
    method: 'get'
  })
}

/**
 * Obtiene una madre por CIM.
 */
export function getMadreInfo(params) {
  return request({
    url: '/api/sys/madre/info',
    method: 'get',
    params
  })
}

/**
 * Registra o actualiza una madre.
 */
export function editMadre(data) {
  return request({
    url: '/api/sys/madre/edit',
    method: 'post',
    data
  })
}

/**
 * Elimina una madre por CIM.
 */
export function delMadre(params) {
  return request({
    url: '/api/sys/madre/del',
    method: 'delete',
    params
  })
}
