import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getCallingPackageOne = (params) => {
    return getRequest('/callingPackage/getOne', params)
}
export const getCallingPackageList = (params) => {
    return getRequest('/callingPackage/getByPage', params)
}
export const getCallingPackageCount = (params) => {
    return getRequest('/callingPackage/count', params)
}
export const addCallingPackage = (params) => {
    return postRequest('/callingPackage/insert', params)
}
export const editCallingPackage = (params) => {
    return postRequest('/callingPackage/update', params)
}
export const addOrEditCallingPackage = (params) => {
    return postRequest('/callingPackage/insertOrUpdate', params)
}
export const deleteCallingPackage = (params) => {
    return postRequest('/callingPackage/delByIds', params)
}