import axios from "axios";


export async function getPosition(){
    let info=await axios.get('https://restapi.amap.com/v3/ip?key=7d77df4562932651ba9897b00c235bfb')
    console.log(info)
    return info
}
