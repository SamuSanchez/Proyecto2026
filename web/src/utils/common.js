import {useStore} from "@/store/index.js";
/**
 * Comentario en espanol
 * @param ref
 */

export function resetForm(ref){
    ref.resetFields()
}

/**
 * Comentario en espanol
 * @param per
 * @returns {boolean}
 */
export function hasPer(per){
    const store = useStore()
    const permission = store.permission
    return permission.indexOf(per) > -1
}
