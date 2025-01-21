<template>
  <div style="display: flex;flex-direction: column;align-items: center">
    <div class="dynamicBox-2">
      <div class="title">
        <div class="person">
                    <img :src="$store.getters['comment/getCoMap'][dynamic.uid+'_'+dynamic.type].image|setAvatar" alt="">
<!--          <img :src="'https://source.unsplash.com/30x30/?avatar'" alt="">-->
          <div class="userName123">
            <h3>{{getName(dynamic.uid,dynamic.type)}}</h3>
            <user-card class="userCard"  :users="dynamic"></user-card>
          </div>
        </div>
        <div class="time">
          {{getTime(dynamic.createTime)}}
        </div>

      </div>
      <div class="content">

        <div class="content-main">
          {{thisContent}}<i v-if="dynamic.context.length>192" class="el-icon-arrow-down more">查看更多</i>
        </div>
                <div v-if="dynamicMap.file.length" class="img-con">
                  <div v-for="imgs in dynamicMap.file"  class="imgBox">
                    <img :src="getImg(imgs)" alt="">
                  </div>
                </div>
<!--        <div class="demo-image__preview">-->
<!--          <el-image-->
<!--              style="width: 100px; height: 100px"-->
<!--              :src=""-->
<!--              :preview-src-list="srcList">-->
<!--          </el-image>-->
<!--        </div>-->
      </div>

      <div class="footer flex">
        <div class="footer-left">
          <i @click="likeIt()" class="el-icon-caret-top flex" :style="isLike?'background:rgb(0,132,255);color:white':''">{{isLike?'已赞同':'赞同'}} {{dynamic.likeNum}}</i>
        </div>
        <div class="footer-right">
          <i @click="$store.commit('comment/SET_ACTIVECOMMENT',dynamic.id)" class="el-icon-s-comment flex">{{dynamicMap.count}} 条评论</i>
          <i @click="collectIt" class="el-icon-star-on flex">{{isCollection?'已收藏':'收藏'}}</i>
          <div class="flex" style="margin-left: 200px">
            <report :msg="dynamic" :type="'动态'"></report>
          </div>

        </div>
      </div>
    </div>
    <comment-2 v-if="dynamic.id==$store.getters['comment/getActiveComment']" :dynamic="dynamic" :commentNum="dynamicMap.count"></comment-2>
  </div>

</template>

<script>
import {getUserName} from "@/untils/getUserName";
import {getTimeBefore} from "@/untils/timeBefore";
import {favorite,unFavorite} from "@/api/dynamic";
import {like,cancelLike} from "@/api/dynamic";
import Report from "@/components/content/Report";
import UserCard from "@/components/content/UserCard";

export default {
  name: "DynamicBox-2",
  components: {UserCard, Report, Comment2},
  props:['dynamic','dynamicMap','collectionList','likeLists'],

  data(){
    return{
      commentShow:false,
      isUnlike:false,
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      srcList: [
        'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
        'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
      ],
      collection:{
        did:'',
        uid:this.$store.getters["user/getUser"].uid,
        type:this.$store.getters["user/getUser"].uType
      },
      likeList:{
        did:'',
        uid:this.$store.getters["user/getUser"].uid,
        type:this.$store.getters["user/getUser"].uType
      }
    }
  },
  computed:{
    dynamicImg(){
      return this.dynamicMap[this.dynamic.id].file
    },
    thisContent(){
      let str=this.dynamic.context
      if (str.length>192){
        let newStr=str.substring(0,192)+'...'
        return newStr
      }else {
        return str
      }

    },
    isLike(){
      return this.likeLists.includes(this.dynamic.id)
    },
    isCollection(){
      return this.collectionList.includes(this.dynamic.id)
    },
  },
  methods:{
    getImg(url){
      return 'data:image/png;base64,'+url
    },
    likeIt(){
      this.likeList.did=this.dynamic.id
      let _index=this.likeLists.indexOf(this.dynamic.id)
      if(this.isLike){
        cancelLike(this.likeList.did,this.likeList.type,this.likeList.uid)
            .then(res=>{
              console.log(res)
              this.likeLists.splice(_index,1);
              this.dynamic.likeNum--
            })
      }else {
        this.likeLists.push(this.dynamic.id)
        like(this.likeList)
            .then(res=>{
              console.log(res)
              this.$message.success("点赞成功~")
              this.dynamic.likeNum++
            })
      }
    },
    unLikeIt(){
      this.isUnlike=!this.isUnlike
    },
    getName(uid,type){
      return getUserName(uid,type)
    },
    getTime(time){
      return getTimeBefore(time)
    },
    collectIt(){
      let id=this.dynamic.id
      this.collection.did=id
      let index=this.collectionList.indexOf(id)
      if(index==-1){
        this.collectionList.push(id)
        favorite(this.collection)
            .then(res=>{
              console.log(res)
              this.$message.success("收藏成功!")
            })
      }else {
        unFavorite(this.collection.did,this.collection.type,this.collection.uid)
            .then(res=>{
              this.collectionList.splice(index,1)
              console.log(res)
            })
      }

    },
  }
}
</script>

<style lang="stylus" scoped>
.flex
  display flex
  align-items center
.dynamicBox-2
  padding 2%
  width 100%
  background white
  display flex
  flex-direction column
  justify-content space-between
  transition all .2s
  border-top 1.5px solid rgb(246,246,246)
.title
  height 18%
  display flex
  justify-content space-between
  & .person
    display flex
    align-items center
  & img
    height 30px
    width 30px
    border-radius 50%
  & .time
    display flex
    align-items center
    color grey
.content
  display flex
  flex-direction column
  height auto
  min-height 120px
  padding 10px 0
  letter-spacing 1px
.footer
  height 30px
  display flex
  align-items center
  justify-content space-between
  font-size 15px

//background #8d9192
.footer-left
  height 100%
  width 28%
  display flex
  justify-content space-between
.footer-right
  color rgb(133,144,166)
  height 100%
  width 72%
  display flex
  & i
    cursor pointer
    margin 0 15px
.el-icon-caret-top,.el-icon-caret-bottom
  cursor pointer
  justify-content center
  width 73%
  border-radius 2px
  height 100%
  background rgb(229,242,255)
  color rgb(0,132,255)
  &:hover
    background rgb(217,237,255)
.el-icon-caret-bottom
  width 25%
.img-con
  display flex
  border-radius 3px
  height 120px
  cursor pointer
  margin 3px
  &>.imgBox
    width 150px
    height 120px
    margin-right 5px
    overflow hidden
    &>img
      width 100%
      height 100%
      transition all .3s
      &:hover
        transform scale(1.3,1.3)
.content-main
  font-size 15px
  min-width 73%
  width auto
  height 120px
  overflow hidden
  text-overflow ellipsis
  word-wrap break-word
.more
  font-size 14px
  color rgb(64,158,255)
  cursor pointer
.userCard
  position absolute
  display none
.userName123
  position relative
  cursor pointer
  &:hover .userCard
    display block
    z-index 9999
  &:hover h3
    color red
</style>
