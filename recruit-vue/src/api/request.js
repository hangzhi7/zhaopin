import axios from 'axios'
import {baseUrl} from "@/api/baseUrl";
import store from "@/store/index"
import router from "@/router";
import {Message} from "element-ui";
const service=axios.create({
    baseURL:baseUrl,
    timeout:50000
})

export default service
