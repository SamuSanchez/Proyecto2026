import {ElMessage} from "element-plus";

/**
 * Comentario en espanol
 * @param msg
 */
export function successMsg(msg){
    ElMessage.success(msg)
}

/**
 * Comentario en espanol
 * @param msg
 */
export function errorMsg(msg){
    ElMessage.error(msg)
}

/**
 * Comentario en espanol
 * @param msg
 */
export function warningMsg(msg){
    ElMessage.warning(msg)
}

/**
 * Comentario en espanol
 * @param msg
 */
export function infoMsg(msg){
    ElMessage.info(msg)
}
