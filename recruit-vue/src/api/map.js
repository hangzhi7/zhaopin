//高德api
import axios from "axios";
//开发者密钥
const mapKey='7d77df4562932651ba9897b00c235bfb'
const service=axios.create({
    baseURL:`https://restapi.amap.com/v3/config`,
    timeout:50000
})

service.interceptors.response.use(res=>{
    //整理拿到需要的子级数据
    return res.data.districts[0].districts;
})

//根据关键词查询省市区
export function getProvinces(keywords){
    return service({
        url:`district?key=${mapKey}&keywords=${keywords}`
    })
}



