import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getCallingOrderOne = (params) => {
    return getRequest('/callingOrder/getOne', params)
}
export const getCallingOrderList = (params) => {
    return getRequest('/callingOrder/getByPage', params)
}
export const getCallingOrderCount = (params) => {
    return getRequest('/callingOrder/count', params)
}
export const addCallingOrder = (params) => {
    return postRequest('/callingOrder/insert', params)
}
export const editCallingOrder = (params) => {
    return postRequest('/callingOrder/update', params)
}
export const addOrEditCallingOrder = (params) => {
    return postRequest('/callingOrder/insertOrUpdate', params)
}
export const deleteCallingOrder = (params) => {
    return postRequest('/callingOrder/delByIds', params)
}