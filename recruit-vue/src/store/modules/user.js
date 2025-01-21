import { login } from "@/api/main";
import { Message } from "element-ui";
import router from "@/router";
import { logout } from "@/api/user";

const state = function () {
    return {
        token: '',
        user: {
            uid: '1',
            uType: 'student',
            userName: '',
            avatar:''
        },
        otherUser:{
            uid:1,
            type:'student'
        },
        concernList:[]
    }
}
const getters = {
    getToken: state => state.token,
    getUser: state => state.user,
    getOther:state=>state.otherUser,
    getConcernList:state=>state.concernList
}

const mutations = {
    SET_TOKEN: (state, token) => {
        state.token = token
    },
    CLEAN_TOKEN: (state) => {
        state.token = null
    },
    SET_USER: (state, user) => {
        state.user = user
    },
    CLEAN_USER: (state) => {
        state.user = null
    },
    SET_OTHER:(state,user)=>{
        state.otherUser=user
    },
    SET_CONCERNLIST:(state,arr)=>{
        state.concernList=arr
    }
}

const actions = {
    //登录
    login({ commit }, loginInfo) {
        console.log(loginInfo)
        const { userName, passWord, captcha, uuid } = loginInfo
        login(userName.trim(), passWord, captcha, uuid).then(res => {
            const data = res.data.extend
            commit('SET_TOKEN', data.token)
            let {msg}=res.data.extend
            localStorage.setItem("TOKEN", data.token)
            if (res.data.code === 100) {
                let user = {
                    uid: data.user.id,
                    uType: data.user.role.toLowerCase(),
                    userName: data.user.userName,
                    avatar:data.user.image
                }

                commit("SET_USER", user)
                router.push("/home").then(() => {
                    Message.success("登陆成功!")
                })
            }
        })
    },
    //退出登录
    logOut({ commit, state }) {
        logout(state.token).then(res => {
            localStorage.removeItem("TOKEN")
            commit("CLEAN_TOKEN")
            commit("CLEAN_USER")
            router.push("/main/login").then(() => {
                Message.success("已退出登录!")
            })

        })
    }
}

export default {
    namespaced: true,
    state,
    getters,
    mutations,
    actions
}

