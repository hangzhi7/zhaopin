export function identification(item){
    let uid
    let type
    if(item.universityName){
        uid=item.uid
        type='university'
    }else if(item.companyName){
        uid=item.cid
        type='company'
    }else {
        uid=item.sid
        type='student'
    }
    return {uid,type}
}


