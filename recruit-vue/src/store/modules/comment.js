const state=function (){
    return{
        commentMap:{},          //评论者信息
        activeComment:-1        //当前激活动态评论区
    }
}

const mutations={
    SET_COMMENTMAP:(state,unitMap)=>{
        const oldMap=state.commentMap
        state.commentMap={...oldMap,...unitMap}             //用来合并动态和评论的Map
    },
    SET_ACTIVECOMMENT:(state,id)=>{
        if(state.activeComment===id){
            state.activeComment=-1
        }else {
            state.activeComment=id
        }

    },
    CLEAN_ACTIVE:(state)=>{
        state.activeComment=-1
    }
}

const getters={
    getCoMap:state=>state.commentMap,
    getActiveComment:state=>state.activeComment
}


export default{
    namespaced:true,
    state,
    getters,
    mutations,
}
