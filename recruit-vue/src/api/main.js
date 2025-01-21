import request from "./request"


//登录
export function login(userName,password,captcha,uuid){
  return request({
    url:'/login',
    method:'post',
    params:{
      userName,password,captcha,uuid
    }
  })
}

//拿到登录的唯一uuid
export function getUuid(){
  return request({
    url:'/uuid',
  })
}
//企业注册
export function companyRegister(registerMsg) {
  return request({
    url:'/company/register',
    method:'post',
    data:registerMsg

  })
}

//学校注册
export function universityRegister(registerMsg) {
  return request({
    url:'/university/register',
    method:'post',
    data: registerMsg
  })
}

//学校取消注册
export function upCancel(ip){
  return request({
    url:'/university/cancel',
    params:{
      ip
    }
  })
}

//企业取消注册
export function upCancelC(ip){
  return request({
    url:'/company/cancel',
    params:{
      ip
    }
  })
}

//企业注册判断是否重名
export function  isRename(name,type){
  return request({
    url:'/unRename',
    method:'post',
    params:{
      name,type
    }
  })
}
