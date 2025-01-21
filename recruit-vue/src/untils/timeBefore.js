export function getTimeBefore(time){
    const dt=new Date(time)
    const nowDt=new Date()
    const dtPoor=nowDt.getTime()-dt.getTime()
    //计算相差天数
    let days=Math.floor(dtPoor/(24*3600*1000))

    //计算小时数
    let level1=dtPoor%(24*3600*1000)            //计算天数后剩余的毫秒数
    let hours=Math.floor(level1/(3600*1000))

    //计算相差分钟数
    let level2=level1%(3600*1000)           //计算小时后剩余秒数
    let minutes=Math.floor(level2/(60*1000))

    if(days>=7) {
        let date = dt
        let y = date.getFullYear();
        let m = date.getMonth() + 1;
        m = m < 10 ? '0' + m : m;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
    }
    else
        if(days<=7&&days>=1)
            return `${days}天前`
        else if(days<1&&hours>=1)
            return `${hours}小时前`
        else if(hours<1&&minutes>=1)
            return `${minutes}分钟前`
        else
            return `刚刚`

}




