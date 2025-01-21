import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import comment from "@/store/modules/comment";
import createPersistedState from "vuex-persistedstate"


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isSun:true,
    isPlayer:false,
    isAdd:false
  },
  mutations: {
    SET_PLAYER:(state)=>{
      state.isPlayer=true
    },
    CLOSE_PLAYER:(state)=>{
      state.isPlayer=false
    },
    OPEN_ADD:(state)=>{
      state.isAdd=true
    },
    CLOSE_ADD:(state)=>{
      state.isAdd=false
    }
  },
  actions: {
  },
  getters:{
    getPlayerState:(state)=>{
      return state.isPlayer
    },
    getAddState:(state)=>{
      return state.isAdd
    }
  }
  ,
  modules: {
    comment,user
  },
  plugins: [createPersistedState()]
})
