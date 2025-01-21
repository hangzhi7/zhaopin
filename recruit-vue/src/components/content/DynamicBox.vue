<template>
  <div  class="dynamic">
    <div style="box-sizing: border-box;padding:10px"  >
      <header>
        <div id="avatar">
          <img src="../../assets/img/tab1.jpg" alt="">
        </div>
        <div id="user">
          <div   style="display: flex;flex-direction: column;justify-content: space-around">
            <div class="userName" >
              <h3 class="userName2" @click="toArchive(1,'student')">{{'拜拜'}}</h3>
<!--              <user-card :users="item" class="userCard"  style="position: absolute;left: 80%;top:-60px;z-index: 5"></user-card>-->
            </div>

            <p style="color: rgb(135,137,135)">{{'2022/12'||getTime(item.createTime)}}</p>
          </div>
          <div style="display: flex;justify-content: flex-end;width: 40%">
<!--            <report :msg="item"></report>-->
          </div>

        </div>
      </header>
      <article>
        <div id="blank">

        </div>
        <div style="display: flex;width:89%;flex-direction: column;justify-content: space-between">
          <div style="word-break: break-all">测试数据从恩特content</div>

<!--          <div v-if="item.imageId!=0" class="demo-image__preview">-->
<!--            <el-image-->
<!--                v-for="(imgs,index) in map2[item.id].file"-->
<!--                :key="index"-->
<!--                style="width: 100px; height: 100px"-->
<!--                :src="getImage(imgs)"-->
<!--                fit="scale-down"-->
<!--            ></el-image>-->
<!--          </div>-->

        </div>

      </article>
    </div>
    <footer>
      <div @click="collectIt(item.id,item.type)" class="fBtn"><i :class="isCollection(item.id)?'el-icon-star-on':'el-icon-star-off'">
      </i>{{isCollection(item.id)?'已收藏':'收藏'}}</div>
      <div @click="getComment(item.id)" class="fBtn"><i class="el-icon-s-comment"> </i> {{1||getCommentNum(item.id)}}
        <i v-if="commentShow[item.id]" style="color: rgb(242,242,245);position: absolute;bottom: -8px;font-size: 24px" class="el-icon-caret-top"></i>
      </div>
      <div @click="likeIt(item.id,index)" class="fBtn"><i :class="isLike(item.id)?'el-icon-caret-top':'el-icon-s-opportunity gray'">
      </i> {{item.likeNum+'人觉得很赞'}}</div>



    </footer>
    <transition name="commentFade">
      <Comment v-if="commentShow[item.id]" :dynamic="item" ></Comment>
    </transition>

  </div>

</template>


<script>
import Comment from "./Comment";
import UserCard from "./UserCard";
import {getConcernDynamic,getComments,favorite,unFavorite,like,cancelLike} from "@/api/dynamic"
import {getTimeBefore} from "../../untils/timeBefore";
import Report from "./Report";

export default {
  name: "index",
  components:{
    Report,
    Comment,UserCard
  },
  data(){
    return{
      item:{
        id:1,
        type:'student',
        likeNum:8
      },
      reportVisible:false,
      userProfile:{
        id:'',
        type:''
      },
      userProfileShow:false,
      currentPage:1,
      page:{

      },
      likeLists:[],
      likeList:{
        did:'',
        type:localStorage.getItem('userType'),
        uid:localStorage.getItem('userId')
      },
      collectionList:[],
      collection:{
        did:'',
        type:localStorage.getItem('userType'),
        uid:localStorage.getItem('userId')
      },
      addView:false,
      btnShow:true,
      dynamicData:[],
      map:'',
      map2:'',
      commentShow:{

      },

    }
  },
  created() {
    this.getData()

  },
  handleClose(done) {
    this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
  },
  methods:{
    load(){
      this.getMore()
      // alert("加载")
    },

    toArchive(uid,type){
      let userName=this.getName(uid,type)
      if(uid==localStorage.getItem("userId")&&type==localStorage.getItem("userType")){
        this.$router.push('/setting')
      }else {
        this.$store.commit('setOther',{uid,type,userName})
        this.$router.push('/other')
      }

    },
    getMore(){
      this.currentPage++
      this.isMore=false
      if(this.currentPage>this.page.pages){
        this.$message.warning("没有更多了噢~")
        this.isMore=true
        return ;
      }
      setTimeout(()=>{
        getConcernDynamic(localStorage.getItem('userId'),localStorage.getItem('userType'),this.currentPage)
            .then(res=>{
              console.log(res)
              for (let item of res.data.extend.page.records){
                this.$set(this.commentShow,item.id,false)

              }
              const {extend} =res.data
              //合并两个数组
              this.collectionList=[...this.collectionList,...extend.collections]               //新语法...,展开语法

              this.page.records=[...this.page.records,...extend.page.records]
              this.page.pages=extend.page.pages
              //由于请求新的内容,需要合并map
              this.map={...this.map,...extend.map}
              this.$store.commit("setUserList",this.map)

              this.likeLists=[...this.likeLists,...extend.likeLists]

              this.map2={...this.map2,...extend.dynamicMap}

              this.dynamicShow=true
              this.isMore=true
            }).catch(err=>{
          console.log(err);
        })
      },300)

    },
    collectIt(id){
      this.collection.did=id
      let index=this.collectionList.indexOf(id)
      if(index==-1){
        this.collectionList.push(id)
        favorite(this.collection)
            .then(res=>{

              console.log(res)
              this.$message.success("收藏成功!")
              this.$store.state.number.collectionNum++
            })
      }else {
        unFavorite(this.collection.did,this.collection.type,this.collection.uid)
            .then(res=>{
              this.collectionList.splice(index,1)
              console.log(res)
              this.$store.state.number.collectionNum--
            })
      }

    },
    likeIt(id,index){
      this.likeList.did=id
      let _index=this.likeLists.indexOf(id)
      if(_index!=-1){
        cancelLike(this.likeList.did,this.likeList.type,this.likeList.uid)
            .then(res=>{
              console.log(res)
              this.likeLists.splice(_index,1)
              this.page.records[index].likeNum--
            })
      }else {
        like(this.likeList)
            .then(res=>{
              console.log(res)
              this.likeLists.push(id)
              this.page.records[index].likeNum++
            })
      }
    },
    getCommentShow(id){
      return this.commentShow[id]
    },
    getTime(time){
      return getTimeBefore(time)
    },
    getData(){
      getConcernDynamic(1,'student',1)
          .then(res=>{
            console.log(res)
            for (let item of res.data.extend.page.records){
              this.$set(this.commentShow,item.id,false)
            }
            this.collectionList=res.data.extend.collections
            this.page=res.data.extend.page
            this.currentPage=res.data.extend.page.current
            this.map=res.data.extend.map
            this.$store.commit("setUserList",this.map)
            this.likeLists=res.data.extend.likeLists
            this.map2=res.data.extend.dynamicMap
            // this.page.records.reverse()
            this.dynamicShow=true
          }).catch(err=>{
        console.log(err);
      })
    },
    isCollection(id){
      if((this.collectionList).indexOf(id)!=-1){
        return true
      }else {
        return false
      }
    },
    isLike(id){
      return this.likeLists.includes(id)
    },
    getName(uid,type){
      if(type=='company'){
        console.log(this.map['1_company'].companyName)
        return this.map[uid+'_'+type].companyName;
      }else if(type=='university'){
        console.log(this.map[uid+'_'+type].universityName)
        return this.map[uid+'_'+type].universityName
      }else {
        console.log(this.map[uid+'_'+type].studentName)
        return this.map[uid+'_'+type].studentName
      }

    },
    getImage(url){
      return 'data:image/png;base64,'+url
    },
    getCommentNum(id){
      return this.map2[id].count
    },
    addSwitch(){
      this.addView=!this.addView
      this.btnShow=!this.btnShow
    },
    getComment(id){

      //对象属性是非响应式的,可以用vue.$set解决
      for (let key in this.commentShow){
        if(key==id){
          this.$set(this.commentShow,id,!this.commentShow[id])
        }else {
          this.$set(this.commentShow,key,false);
        }


      }


    }
  },

}
</script>

<style scoped>

.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.black{
  color: black;
}
.gray{
  color: gainsboro;
}
.fade2-enter-active, .fade2-leave-active {
  transition: all .5s;
  transition-delay: 0s;
}
.fade2-enter, .fade2-leave-to /* .fade-leave-active below version 2.1.8 */ {
  transform: translate(0px,-300px);
}
.fade-enter-active, .fade-leave-active {
  transition: all .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  transform: translate(0px,-300px);
}


.commentFade-enter, .commentFade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  /*transform: translate(-300px,0px);*/
  opacity: 0;
}
.commentFade-enter-active {
  transition: all .8s;
}
.commentFade-enter, .commentFade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  /*transform: translate(0px,-300px);*/
  opacity: 0;
}

.dynamicMove-enter, .dynamicMove-leave-to /* .fade-leave-active below version 2.1.8 */ {
  transform: translate(-300px,0px);
  opacity: 0;
}
.dynamicMove-enter-active {
  transition: all .2s;
}
.dynamicMove-enter, .dynamicMove-leave-to /* .fade-leave-active below version 2.1.8 */ {
  transform: translate(0px,-300px);
  opacity: 0;
}

.dynamic{
  margin-bottom: 20px;
  width: 100%;
  border: 1px solid #d5d0d0;
  transition: all .2s;
  background-color: #fff;
}
.dynamic:hover{
  box-shadow: 0 0px 3px #c9b953;

}
header{
  height: 50px;
  width: 100%;
  display: flex;
  background-color: #fff;
}
#avatar{
  height:100%;
  width: 11%;
  /*border-radius: 50%;*/
  display: flex;
  justify-content: center;
  align-items: center;
  /*background-color: #000;*/
}
#avatar img{
  width: 45px;
  height: 45px;
  border-radius: 50%;
}
#user{
  color: rgb(51,51,51);
  width: 89%;
  height: 100%;
  display: flex;
  justify-content:space-between;
}
#user h3:hover{
  color: #f55151;
  cursor: pointer;
}
#user *{
  padding: 0;
  margin: 0;
}
#user p{
  font-size: 13px;
}
article{
  display: flex;
  flex-direction: row;
}
#blank{
  display: flex;
  width: 11%;

  /*background-color: #000;*/
}
footer{
  height: 35px;
  width: 100%;
  border-top: 1px solid #e7d7d7;
  display: flex;
  justify-content: space-between;
  position: relative;
}

.fBtn{
  cursor:pointer;
  width: 25%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: rgb(128,128,128);
  font-size: 15px;
}
.fBtn i{
  font-size: 18px;
}
.userCard{
  display: none;
  opacity: 0;
  transition: opacity 1s;
}
.userName{
  position: relative;
}
.userName:hover .userCard{
  display: block;
  opacity: 1;
}
.userName2:hover{
  transform: translateX(5px) ;
  transition: all .1s ease-in;
}
#content{
  width: 80%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

@media screen and (max-width: 500px){
  .dynamic{
    height: 100px;
    width: 95vw;
    box-sizing: border-box;
    display: flex;
    padding: 10px;
  }
  .dynamic h3{
    font-size: 16px;
  }
  .dynamic p{
    font-size: 8px;
  }
  footer{
    font-size: 8px;
  }
  #add{
    width: 130vw;
  }
}
</style>
