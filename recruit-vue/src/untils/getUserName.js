import store from "@/store/index"
export function getUserName(uid,type){
    const unitMap=store.getters["comment/getCoMap"]
    if(type=='university'){
        return unitMap[uid+'_'+type].object.universityName
    }else if(type=='student'){
        return unitMap[uid+'_'+type].object.studentName
    }else if(type=='company') {
        return unitMap[uid+'_'+type].object.companyName
    }
}
