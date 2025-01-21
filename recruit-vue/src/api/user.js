import request from './request'

// by zxk
// userRegister
export function userRegister(user, password){
    return request({url: '/user/register', method: 'post', params:{user, password}})
}

//userLogin
export function userLogin(user, password){
    return request({url: '/user/login', method: 'post', params:{user, password}})
}

//userInfoShow
export function userInfoShow(){
    return request({url: '/user/info/show', method: 'get'})
}

//userInfoUpdate
export function userInfoUpdate(user){
    const data = user
    return request({url: '/user/info/update', method: 'post',data})
}

//userLogout
export function userLogout(){
    return request({url: '/user/logout', method: 'get'})
}

//positionApply
export function positionApply(id){
    return request({url: '/user/apply/'+id, method: 'get'})
}

//favorOrNot 职位收藏与否
export function  favorOrNot(id){
    return request({url: '/user/favorOrNot/'+id, method: 'get'})
}

//positionFavor 职位收藏
export function favor(id){
    return request({url: '/user/favor/'+id, method: 'get'})
}

//positionDisFavor 职位取消收藏
export  function  disFavor(id){
    return request({url: 'user/disfavor/'+id, method: 'get'})
}

//listFavor 
export  function  listFav(id){
    return request({url: 'user/fav/list', method: 'get'})
}

//comment 评论
export  function  comment(posId, type,content){
    return request({url: '/user/comment', method: 'post', params: {posId, type,content}})
}

//reply 回复
export  function  reply(commentId, replyUserId,content){
    return request({url: '/user/reply', method: 'post', params: {commentId, replyUserId,content}})
}

//getcomment 评论
export  function  getComment(posId){
    return request({url: `/user/comment/${posId}/`, method: 'get'})
}

//resumeShow 简历信息
export  function  showResume(){
    return request({url: '/user/resume/show', method: 'get'})
}

//resumeUpdate 简历更新
export  function  updateResume(resume){
    const data = resume
    return request({url: '/user/resume/update', method: 'post', data})
}

//positionShow 职位信息展示
export  function  positionShow(id, pageNum, limit){
    return request({url: '/position/' + id + '/' + pageNum, method: 'get', params: {limit}})
}

//index
export  function  index(pageNum, limit){
    return request({url: '/page/'+pageNum, method: 'get', params: {limit}})
}

//categoryShow 按职位分类分页输出
export  function  listByCategory(id,pageNum, limit){
    return request({url: '/category/'+id+'/'+pageNum, method: 'post', params: {limit}})
}

//positionList
export  function  listPosition(){
    return request({url: '/hr/position/list', method: 'get'})
}

// categoryId:100
// workProvince:广东省
// workCity:广州市
// educationId:1
//keyword:腾讯
//positionSearch
export  function  searchPosition(keyword,categoryId,educationId,workProvince,workCity,page,limit){
    const params = {}
    if (keyword) {
        params['keyword'] = keyword
    }
    if (categoryId) {
        params['categoryId'] = categoryId
    }
    if (educationId) {
        params['educationId'] = educationId
    }
    if (workProvince) {
        params['workProvince'] = workProvince
    }
    if (workCity) {
        params['workCity'] = workCity
    }
    if (page) {
        params['page'] = page
    }
    if (limit) {
        params['limit'] = limit
    }
    return request({url: '/position/search', method: 'get', params})
}

//friendUrlList
export  function listFriendUrl(){
    return request({url: '/user/friendUrl/list', method: 'get'})
}

//friendUrlList
export  function passwordUpdate(oldPassword, newPassword){
    return request({url: '/user/password/update', method: 'post', params: {oldPassword,newPassword}})
}

//listApp
export  function listApp(id){
    return request({url: `/user/app/${id}/list`, method: 'get'})
}

//
export function getCompanyInfo(id){
    return request({url: `/user/company/${id}`})
}