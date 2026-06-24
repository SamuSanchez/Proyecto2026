import request from "@/utils/request";

/**
 * Consulta paginada de facturas.
 * @param params { blurry, currentPage, size }
 */
export function getFacturaList(params) {
  return request({
    url: '/api/sys/factura/table',
    method: 'get',
    params
  })
}

/**
 * Consulta una factura completa con su detalle.
 * @param params { id }
 */
export function getFacturaInfo(params) {
  return request({
    url: '/api/sys/factura/info',
    method: 'get',
    params
  })
}

/**
 * Crea o actualiza una factura.
 * @param data { id, nombre, nit, detalles }
 */
export function editFactura(data) {
  return request({
    url: '/api/sys/factura/edit',
    method: 'post',
    data
  })
}

/**
 * Elimina una factura por ID.
 * @param params { id }
 */
export function delFactura(params) {
  return request({
    url: '/api/sys/factura/del',
    method: 'delete',
    params
  })
}
