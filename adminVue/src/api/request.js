import axios from 'axios'
const service=axios.create({
    baseURL: '/api',
    timeout:50000
})

export default service
