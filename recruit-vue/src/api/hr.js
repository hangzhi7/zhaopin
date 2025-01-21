import request  from './request'

//hrRegister
export function hrRegister(hr, password, name, email, companyCode){
    return request({url: '/hr/register', method: 'post', params:{hr, password, name, email, companyCode}})
}

//hrLogin
export function hrLogin(hr, password){
    return request({url: '/hr/login', method: 'post', params:{hr, password}})
}

//hrInfoShow
export function hrInfoShow(){
    return request({url: '/hr/info/show', method: 'get'})
}

//hrPasswordUpdate
export function hrPasswordUpdate(oldPassword,newPassword){
    return request({url: '/hr/password/update', method: 'post', params:{oldPassword,newPassword}})
}

//hrInfoUpdate
export function hrInfoUpdate(hr){
    return request({url: '/hr/info/update', method: 'post', data: hr})
}

//hrLogout
export function hrLogout(){
    return request({url: '/hr/logout', method: 'get'})
}

//hrReceiveApplication
export function receiveApplication(){
    return request({url: '/hr/application/receive', method: 'post'})
}

//allowApplication
export function allowApplication(application){
    return request({url: '/hr/application/allow', method: 'post', data: application})
}

//refuseApplication
export function refuseApplication(application){
    return request({url: '/hr/application/refuse', method: 'post', data: application})
}

//publishPosition
export function publishPosition(positionModel){
    return request({url: '/hr/position/publish', method: 'post', data: positionModel})
}

//modifyPosition
export function modifyPosition(positionModel){
    return request({url: '/hr/position/update', method: 'post', data: positionModel})
}

//delPosition
export function delPosition(positionModel){
    return request({url: '/hr/position/del', method: 'post', data: positionModel})
}

//showPosition
export function showPosition(){
    return request({url: '/hr/position/show', method: 'get'})
}

//friendUrlList
export  function listFriendUrl(){
    return request({url: '/hr/friendUrl/list', method: 'get'})
}

//listCategory
export  function listCategory(){
    return request({url: '/manager/category/list', method: 'get'})
}

//listEducation
export function listEducation(){
    return request({url: '/manager/education/list', method:'get'})
}

//
export function getApplicMsg(application){
    const data = application
    return request({url: '/hr/resumeAndUser/show', method:'post', data})
}
