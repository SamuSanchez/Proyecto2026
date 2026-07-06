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