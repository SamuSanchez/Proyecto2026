import request from '@/utils/request'

export function getMiFacturaList(params) {
  return request({
    url: '/api/sys/factura/table',
    method: 'get',
    params
  })
}

export function getMiFacturaInfo (params) {
  return request({
    url: '/api/sys/factura/info',
    method: 'get',
    params
  })
}
export function editMiFactura(data) {
  return request({ url: '/api/sys/factura/edit', method: 'post', data })
}
export function delMiFactura(params) {
  return request({ url: '/api/sys/factura/del', method: 'delete', params })
}