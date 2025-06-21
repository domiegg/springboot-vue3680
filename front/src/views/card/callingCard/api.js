import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getCallingCardOne = (params) => {
    return getRequest('/callingCard/getOne', params)
}
export const getCallingCardList = (params) => {
    return getRequest('/callingCard/getByPage', params)
}
export const getCallingCardCount = (params) => {
    return getRequest('/callingCard/count', params)
}
export const addCallingCard = (params) => {
    return postRequest('/callingCard/insert', params)
}
export const editCallingCard = (params) => {
    return postRequest('/callingCard/update', params)
}
export const addOrEditCallingCard = (params) => {
    return postRequest('/callingCard/insertOrUpdate', params)
}
export const deleteCallingCard = (params) => {
    return postRequest('/callingCard/delByIds', params)
}
export const getCallingPackageList = (params) => {
    return getRequest('/callingPackage/getAll', params)
}
export const initCard = (params) => {
    return getRequest('/callingCard/init', params)
}
export const addOrder = (params) => {
    return getRequest('/callingOrder/addOne', params)
}
export const raCaard = (params) => {
    return getRequest('/callingCard/ra', params)
}