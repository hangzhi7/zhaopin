<template>
  <div class="a-comment">
    <!--  个人信息部分  -->
    <div class="personal" >
      <div class="avatar">
        <img :src="$store.getters['comment/getCoMap'][comment.uid+'_'+comment.type].image|setAvatar" alt="">
<!--        <img src="https://source.unsplash.com/30x30/?avatar" alt="">-->
      </div>

      <!--      时间-->
      <div class="reTime">
        <div class="userName">
          <p>{{getName(comment.uid,comment.type)}}</p>
          <user-card class="comment-user-card" :users="comment"></user-card>
          <!--  这里放悬浮名片-->
        </div>
        {{getTime(comment.createTime)}}
      </div>
    </div>


    <!-- 回复内容部分   -->
    <div class="content">
      <div  style="width: 95%">
        {{comment.context}}
        <div :class="{'handle':true,'handle-bottom':secondReplyShow}">
          <div @click="greatIt" class="great">
            <img v-if="!isLike" src="../../assets/svg/great.svg" alt="">
            <img v-else src="../../assets/svg/great-fill.svg" alt="">
            <span :style="isGreat?'color:rgb(0,132,255)':''">{{comment.likeNum}}</span>
          </div>
          <div class="child-comment" @click="secondReplyHandle()">
            <img src="../../assets/svg/comment.svg" alt="">
            <span>{{secondComment[comment.id].length}}条</span>
          </div>
          <div class="reply-tab" >

            <div class="reply" @click="inputSwitch(comment.id,comment.uid,comment.type)">
              <img src="../../assets/svg/reply.svg" alt="">
              <span>{{reInput?'取消回复':'回复'}}</span>
            </div>

            <div class="delete" @click="deleteComment(comment.id)" v-show="isMe(comment.uid,comment.type)">
              <img src="../../assets/svg/delete.svg" alt="">
              <span>删除</span>
            </div>
          </div>

        </div>
        <div  v-if="reInput" class="re-input">
          <el-input   v-model="context"  :placeholder="`@${getName(comment.uid,comment.type)}`" style="width: 90%" size="small"></el-input>
          <el-button @click="sendComment" size="small" type="primary">发送</el-button>
        </div>

    <!--   二级回复    -->
        <div v-if="secondReplyShow">
          <div class="secondReply" v-for="(item,index) in secondComments"  v-if="$store.getters['comment/getCoMap'][item.uid+'_'+item.type]"   :key="item.id">
            <div class="personal">
              <div class="avatar">
                <img :src="$store.getters['comment/getCoMap'][item.uid+'_'+item.type].image|setAvatar" alt="">
                <!--              <img :src="'https://source.unsplash.com/30x30/?avatar'+Math.random()" alt="">-->
              </div>

              <!--      时间-->
              <div class="reTime">
                <div class="userName" style="display: flex;align-items: center">
                  <div class="comment2-userName">
                    <p>{{getName(item.uid,item.type)}}</p>
                    <user-card class="comment2-user-card" :users="{uid:item.uid,type:item.type}" ></user-card>
                  </div>
                   <span><span style="color: #8a8a8a">回复</span></span>
                  <div class="comment2-userName">
                    <p>{{getName(item.tuid,item.ttype)}}</p>
                    <user-card class="comment2-user-card" :users="{uid:item.tuid,type:item.ttype}"></user-card>
                  </div>
                  <!--  这里放悬浮名片-->
                </div>
                {{getTime(item.createTime)}}
              </div>
            </div>
            <div class="content2">
              <div  style="width: 95%">
                {{item.context}}
                <div :class="{'handle':true,'handle-bottom':secondReplyShow}">
                  <div class="reply-tab2"  style="margin: 0">
                    <div class="reply" @click="inputSwitch2(comment.id,item.uid,item.type,index)">
                      <img src="../../assets/svg/reply.svg" alt="">
                      <span>{{activeInput==index?'取消回复':'回复'}}</span>
                    </div>
<!--                    <div class="report">-->
<!--                      <img src="../../assets/svg/report.svg" alt="">-->
<!--                      <span>举报</span>-->
<!--                    </div>-->
                    <div @click="deleteComment(item.id)" class="delete" v-show="isMe(item.uid,item.type)">
                      <img src="../../assets/svg/delete.svg" alt="">
                      <span>删除</span>
                    </div>
                  </div>

                </div>
                <div  v-if="activeInput==index" class="re-input">
                  <el-input @keyup.enter.native="sendComment" v-model="context"  :placeholder="`@${getName(item.uid,item.type)}`"  style="width: 90%" size="small"></el-input>
                  <el-button size="small" @click="sendComment(index)" type="primary">发送</el-button>
                </div>
              </div>
            </div>

          </div>
        </div>

      </div>

    </div>

  </div>
</template>

<script>
import {getTimeBefore} from "@/untils/timeBefore";
import {getUserName} from "@/untils/getUserName";
import {addComment, delComment, likeComment} from "@/api/dynamic";
import UserCard from "@/components/content/UserCard";
import comment from "@/store/modules/comment";

export default {
  name: "Acomment",
  components: {UserCard},
  props:['comment','secondComment','dynamic','likeList'],
  data(){
    return{
      likeComments:{
        commentId:this.comment.id,
        type:this.$store.getters["user/getUser"].uType,
        uid:this.$store.getters["user/getUser"].uid
      },
      secondReplyShow:false,
      activeInput:-1,
      isGreat:false,
      reInput:false,
      reInput2:false,
      context:'',
      newComment:{
        cid:0,
        id:'',
        context:'',
        did:'',
        type:this.$store.getters["user/getUser"].uType,
        ttype:'',
        tuid:'',
        uid:this.$store.getters["user/getUser"].uid
      },
    }
  },
  computed:{
    secondComments(){
      return this.secondComment[this.comment.id].reverse()
    },
    isLike(){
      return this.likeList.includes(this.comment.id)
    }
  },
  methods:{
    likeHandle(){
      likeComment(this.likeComments).then(res=>{
        this.$message.success(res.data.msg)
        this.$parent.getData()
      })
    },
    secondReplyHandle(){
      if(this.secondComment[this.comment.id].length){
        this.secondReplyShow=!this.secondReplyShow
      }else {
        this.$message.warning("没有评论呢~")
      }
    },
    isMe(uid1,uType1){
      let {uType,uid}=this.$store.getters["user/getUser"]
      return uid1===uid&&uType1===uType
    },
    deleteComment(id){
      delComment(id).then(res=>{
        this.$message.warning(res.data.extend.msg)
        this.$parent.getData()
      })
    },
    change(e){
      this.$forceUpdate()
    },
    greatIt(){
      this.likeHandle()
    },
    getTime(time){
     return getTimeBefore(time)
    },
    inputSwitch(cid,tuid,tType){
      this.reInput=!this.reInput
      this.newComment.cid=cid
      this.newComment.tuid=tuid
      this.newComment.ttype=tType
      this.newComment.did=this.dynamic.id
    },
    inputSwitch2(id,uid,type,index){
      console.log(uid,type,index)
      if(this.activeInput==index){
        this.activeInput=-1
        return
      }
      this.activeInput=index
      this.newComment.tuid=uid
      this.newComment.ttype=type
      this.newComment.did=this.dynamic.id
      this.newComment.cid=id
    },
    getName(uid,type){
      return getUserName(uid,type)
    },
    sendComment(index){
      async function start(_this){
        _this.newComment.context=_this.context
        if(!_this.context){
          _this.activeInput=-1
          _this.reInput=false
          return
        }
        const res=await addComment(_this.newComment)
        console.log(res)
        _this.$parent.getData()
        _this.activeInput=-1
        _this.secondReplyShow=true
        _this.context=''
      }
      start(this)
    }
  }
}
</script>

<style lang="stylus" scoped>
.a-comment
  display flex
  flex-direction column
  width 100%
  padding 10px 0
  height auto
  min-height 80px
  border-bottom 1px solid rgb(247,247,247)
.personal
  display flex
  justify-content space-between
  height 30px
.userName
  color black
.avatar
  height 100%
  display flex
  align-items center
  padding-left 1px
  & img
    height 28px
    width 28px
    border-radius 4px
.reTime
  height 100%
  width 95%
  display flex
  align-items center
  justify-content space-between
  color rgb(133,144,166)
  & img
    height 100%
.content
  height auto
  display flex
  justify-content flex-end
  margin-top 2px
  &:hover
    .reply-tab
      opacity 1
.content2
  height auto
  display flex
  justify-content flex-end
  margin-top 2px
  &:hover
    .reply-tab2
      opacity 1
.handle
  height 30px
  width 100%
  display flex
  align-items center
.handle-bottom
  border-bottom 1px solid rgb(247,247,247)

.great,.reply,.report,.delete,.child-comment
  cursor pointer
  width 35px
  height 50%
  display flex
  font-size 14px
  justify-content space-between
  align-items center
  color #8a8a8a
  & img
    height 100%
.reply-tab,.reply-tab2
  transition all .2s
  margin-left 30px
  height 30px
  display flex
  align-items center
  opacity 0
.reply
  width auto
  margin-right 25px
.report
  width 45px
  margin-right 25px
.child-comment
  width 40px
  margin-left  20px
.delete
  width 45px
.replies
  width 100%
.re-input
  display flex
  justify-content space-between
.secondReply
  padding 10px 0
.comment-user-card
  position absolute
  display none
.userName
  position relative
  cursor pointer
  &:hover .comment-user-card
    display block
    z-index 10
    &:hover
      color red
.comment2-user-card
  display none
  position absolute
.comment2-userName
  display flex
  align-items center
  cursor pointer
  &:hover .comment2-user-card
    display block
</style>
