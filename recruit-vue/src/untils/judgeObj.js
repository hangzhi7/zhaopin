//判断对象是否相等

export function judgeObj(arr,obj){
    for(let item of arr){
        let flag=true                       //如果falg没被置为false,说明此轮属性全等,返回true,如果整个大循环都执行完,说明没返回true,为false
        for(let key in item){
            if(item[key]!=obj[key])
                flag=false
        }
        if(flag)
            return true
    }
    return false
}
