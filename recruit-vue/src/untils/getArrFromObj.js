

export function getArrFromObj(arr){
    let newArr=[]
    for (let item of arr){
        newArr.push(item.studentName||item.companyName||item.universityName)
    }

    return newArr
}
