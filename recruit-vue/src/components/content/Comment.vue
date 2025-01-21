<template>
  <main>
    <div v-if="loading">
      <i class="el-icon-loading"></i><span style="color: #07c160;font-size: 15px">加载中</span>
    </div>
    <div v-if="!loading" id="myComment">
      <div style="display: flex;justify-content: space-between">
        <el-avatar style="width: 32px;height: 32px" shape="square" :size="100" fit="fill" :src="require('../../assets/img/tab1.jpg')"></el-avatar>
        <input v-model="comment.context" placeholder="说出你的想法" style="height:30px;width: 92%;border:1px solid rgb(250,125,60);border-radius:3px;outline: none" type="text">
      </div>
      <div  style=";display: flex;justify-content: flex-end">
        <button @click="postComment">评论</button>
      </div>
    </div>
    <div v-if="otherComment&&!loading">
      <h5 style="color: rgb(128,128,128)">空空如也~~,等你的评论</h5>
    </div>
    <div v-if="!loading" class="otherComment" v-for="(item,index) in comments" :key="item">
      <div id="top">
        <el-avatar style="width: 32px;height: 32px" shape="square" :size="100" fit="fill" :src="require('../../assets/img/tab1.jpg')"></el-avatar>
        <div style="position: relative;height:100%;display: flex;flex-direction: column;justify-content: space-between;font-size: 13px;margin-left: 10px">
          <div class="userName">
            <p  @click="toArchive(item.uid,item.type)" style="color: red;cursor:pointer;">{{getName(item.uid,item.type)}}：</p>
            <!--            鼠标悬浮显示名片-->
            <user-card :users="item" class="userCard"  style="position: absolute;z-index: 5"></user-card>
          </div>

          <p style="font-size: 12px;color: gray">{{getTime(item.createTime)}}</p>
        </div>
        <div style="height: 100%;font-size: 13px;display: flex;width: auto;max-width: 70%;word-break: break-word">
          {{item.context }}
        </div>
        <div class="moreTab" style="width: 10%">
          <span v-if="isMe(item.uid,item.type)" @click="deleteComment(item.id)" style="margin-left: 5px" class="recall" >删除</span>
          <span class="recall" @click="openReply(item.id,item.uid,item.type,item.cid,index)">回复</span>

        </div>
      </div>
      <div id="reply" v-if="getReplyNum(item.id)!=0||isReply==index"  >
        <div style="width: 93%;float: right;background-color:rgb(234,234,236);font-size: 13px;box-sizing: border-box;padding: 10px">
          <div>
            <div v-if="isReply==index"  @blur="isReply=-1;replyContext=''"  style="display: flex">
              <el-input id="replyInput" v-model="replyContext" :placeholder="'@'+replyName" size="mini"></el-input><el-button @click="reply(index)" size="mini">回复</el-button>
            </div>
            <div >
              共<span style="color: orange">{{getReplyNum(item.id)}}</span>条回复
              <i style="color: #07c160;cursor:pointer;" @click="openSwitch(index)"
                 :class="isOpen==index?'el-icon-arrow-up':'el-icon-arrow-down'">{{isOpen==index?'收起':'展开'}}</i>
            </div>

          </div>
          <div v-if="isOpen==index" class="replies" v-for="(replies,index2) in secondComments[item.id]">
            <el-avatar style="width: 32px;height: 32px" shape="square" :size="100" fit="fill" :src="require('../../assets/img/tab1.jpg')"></el-avatar>
            <div style="height:100%;display: flex;flex-direction: column;justify-content: space-between;font-size: 13px;margin-left: 10px">
              <p style="color: red">
                <span class="userName">
                  <span @click="toArchive(replies.uid,replies.type)">{{getName(replies.uid,replies.type)}}</span>
                  <user-card class="userCard" :users="replies" style="position: absolute;z-index: 5"></user-card>
                </span>
                <span style="color: black">回复</span>
                <span class="userName">
                  <span @click="toArchive(replies.tuid,replies.ttype)">{{getName(replies.tuid,replies.ttype)}}:</span>
                  <user-card class="userCard" :users="getMsg(replies)" style="position: absolute;z-index: 5"></user-card>
                </span>
              </p>
              <p style="font-size: 12px;color: gray">{{getTime(replies.createTime)}}</p>
            </div>
            <div style="height: 100%;font-size: 13px;display: flex;width: auto;max-width: 60%">
              {{replies.context }}
            </div>
            <div style="width: 15%">
              <span  class="recall" style="margin-left: 5px" @click="openReply(item.id,replies.uid,replies.type,replies.cid,index)" >回复</span>
              <span v-if="isMe(replies.uid,replies.type)" @click="deleteComment(replies.id)"  class="recall" >删除</span>

            </div>
          </div>

        </div>
      </div>

    </div>
  </main>
</template>

<script>
import {addComment,delComment,getComments} from "@/api/dynamic";
import {getTimeBefore} from "../../untils/timeBefore";
import UserCard from "./UserCard";

export default {
  name: "Comment",
  components:{
    UserCard
  },
  data(){
    return{
      loading:true,
      otherComment:false,
      userProfileShow:false,
      unitMap:[],   //评论者信息
      isReply:-1,     //通过index的值来确定哪个的回复框的h展开的
      isOpen:-1,      //通过index确定展开回复对象
      replyName:'',
      replyContext:'',
      comment:{
        cid:'',
        id:'',
        context:'',
        did:'',
        type:localStorage.getItem("userType"),
        ttype:'',
        tuid:'',
        uid:localStorage.getItem("userId"),

      },
      comments:[],
      secondComments:{},

    }
  },
  props:["dynamic"],
  created() {
    this.getData()
  },
  methods:{
    toArchive(uid,type){
      let userName=this.getName(uid,type)
      if(uid==localStorage.getItem("userId")&&type==localStorage.getItem("userType")){
        this.$router.push('/setting')
      }else {
        this.$store.commit('setOther',{uid,type,userName})
        this.$router.push('/other')
      }

    },
    isMe(uid,type){
      let userId=localStorage.getItem('userId')
      let userType=localStorage.getItem('userType')
      if (uid==userId&&type==userType){
        return true
      }
      return false
    },
    getData(){
      getComments(1)
          .then(res=>{
            let {extend}=res.data
            this.comments=extend.firstComments
            if(this.comments.length==0){
              this.otherComment=true
            }else {
              this.otherComment=false
              this.secondComments=extend.secondComments
              this.unitMap=extend.unitMap
            }
            setTimeout(()=>{
              this.loading=false
            },200)
          })
    },
    getReplyNum(id){
      if(!this.secondComments[id]){
        return 0
      }
      return this.secondComments[id].length
    },
    deleteComment(cid){
      console.log(cid)
      delComment(cid)
          .then(res=>{
            console.log(res)
            this.$message.success(res.data.extend.msg)
            this.getData()
          })
    },
    openReply(id,tuid,ttype,cid,index){       //
      this.isReply=index
      this.replyName=this.getName(tuid,ttype)
      this.comment.cid=id
      this.comment.tuid=tuid
      this.comment.ttype=ttype
      setTimeout(()=>{
        document.getElementById('replyInput').focus()
        document.getElementById('replyInput').onblur=()=>{
          setTimeout(()=>{
            this.isReply=-1
          },300)

        }
      },0)

    },
    openSwitch(index){

      if(this.isOpen==index){
        this.isOpen=-1
      }else {
        this.isOpen=index
      }

    },
    reply(index){
      let dynamic=this.dynamic
      this.comment.did=dynamic.id
      if(!this.replyContext){
        this.isReply=-1
        return 0;           //如果未填写内容,结束进程
      }
      this.comment.context=this.replyContext
      addComment(this.comment)
          .then(res=>{
            console.log(res)
            this.replyContext=''
            this.getData()
            this.isReply=-1
            this.comment.context=''
            this.isOpen=index
          })
    },
    getName(uid,type){
      if(type=='university'){
        return this.unitMap[uid+'_'+type].universityName
      }else if(type=='student'){
        return this.unitMap[uid+'_'+type].studentName
      }else if(type=='company') {
        return this.unitMap[uid+'_'+type].companyName
      }

    },
    getMsg(replies){
      let obj={
        uid:replies.tuid,
        type:replies.ttype
      }
      return obj
    },
    getTime(time){
      return  getTimeBefore(time)     //调用封装方法计算多少时间之前
    },
    postComment(){
      let dynamic=this.dynamic
      this.comment.did=dynamic.id
      this.comment.ttype=''
      this.comment.tuid=''
      this.comment.cid=''
      if (this.comment.context){
        addComment(this.comment)
            .then(res=>{
              console.log(res)
              this.getData()
              this.comment.context=''
            })
      }else {
        this.$message.warning("不能发布空白评论~")
      }

    }
  }
}
</script>

<style scoped>
main{
  margin: 5px;
  box-sizing: border-box;
  padding: 10px 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  z-index: 5;
  width: 95%;
  box-shadow:0 0 2px rgb(222, 218, 218);
  background-color: white;
}
#myComment{
  display: flex;
  width: 95%;
  height: 90px;
  /*background-color: #fff;*/
  box-sizing: border-box;
  padding-bottom: 10px;
  flex-direction: column;
  justify-content:space-between;
  border-bottom: 1px solid darkgray;
}
#myComment button{
  width: 100px;
  height: 30px;
  color: white;
  outline: none;
  border: none;
  background-color: orange;
}
.otherComment{
  width: 95%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  margin: 10px 0;
  border-bottom: 1px solid rgb(242,242,245);
}
.otherComment *{
  margin: 0;
}
.otherComment img{
  height: 100%;
  width: 40px;
}
.recall{
  font-size: 13px;
  float: right;
  cursor:pointer;
  color: #d58a4e;
}
.recall:hover{
  color: red;
}
#reply,#top{
  width: 100%;
}
#top{
  height: 40px;
  display: flex;
  justify-content: flex-start;
}
#reply{

}
.replies{
  height: 40px;
  display: flex;
  justify-content: flex-start;
  margin: 10px 0;
  border-bottom: 1px solid #dbc8c8;
}
.userCard{
  display: none;
  transition: all .3s;
}
.userName{
  cursor: pointer;
}
.userName:hover .userCard{
  display: block;
}
.moreTab{
  display: none;
}
.replies:hover .moreTab{
  display: block;
}
#top:hover .moreTab{
  display: block;
}
</style>
