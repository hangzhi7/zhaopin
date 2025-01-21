<template>
  <div id="card">
    <header>
      <div id="userMsg">
        <div v-if="user.studentName!=undefined">
          <p @click="toOther('student')">{{user.studentName}}</p>
          <p class="detail">{{user.address}}-学生-{{user.yearNum}}</p>
        </div>
      </div>
      <div id="concernBtn">
        <el-button @click="concernBtn()" ><i :class="isConcern?'el-icon-check':'el-icon-plus'">{{isConcern?'已关注':'关注'}}</i></el-button>
      </div>
    </header>
    <footer>
      <li><h3>{{this.num.focusNum}}</h3><span>关注</span></li>
      <li><h3>{{this.num.collectionNum}}</h3><span>收藏</span></li>
      <li style="border-right: none"><h3>{{this.num.dynamicNum}}</h3><span>动态</span></li>
    </footer>
  </div>
</template>

<script>
import {getUnitCount,getArchives,getConcernList,addConcern,cancelConcern} from "@/api/user"
export default {
  name: "UserCard",
  data() {
    return {
      isConcern:false,
      userProfileShow: true,
      num: {
        collectionNum: 0,
        dynamicNum: 0,
        focusNum: 0
      },
      user: {
        studentName: '',
      },
      concern: {
        sid:0,
        uid:0,
        cid:0,
        type:'',
        tid:0

      },
      userType: this.$store.getters["user/getUser"].uType,
      userId: this.$store.getters["user/getUser"].uid,
      concernList: [],

    }
  },
  props: ['users'],
  created() {
    this.getUser(this.users)
    let type=this.$store.getters["user/getUser"].uType
    let id=this.$store.getters["user/getUser"].uid
    if(type=='student'){
      this.concern.sid=id
    }else if(type=='company'){
      this.concern.cid=id
    }else {
      this.concern.uid=id
    }

  },
  computed: {

  },
  methods: {
    switchConcern(){
      let {uid,uType}=this.$store.getters["user/getUser"]
      let concernList=this.$store.getters["user/getConcernList"]
      let newArr=[]
      for (let item of concernList){
        if (item.studentName){
          newArr.push(`${item.sid}_student`)
        }else if(item.universityName){
          newArr.push(`${item.uid}_university`)
        }else {
          newArr.push(`${item.cid}_company`)
        }
      }
      let obj=uid+'_'+uType

      this.isConcern= newArr.includes(obj)
    },
    toOther(type){
      let u=this.user
      let user={
        uid:u.sid||u.uid||u.cid,
        type:type,
        userName:u.companyName||u.studentName||u.universityName,
        archivesVisible:u.archivesVisible,
        visible:u.visible
      }
      if(user.userName==this.$store.getters["user/getUser"].userName){
        this.$router.push('/personal')
      }else {
        this.$store.commit("user/SET_OTHER",user)
        this.$router.push('/other')
      }

    },
    addCon() {
      addConcern(this.concern)
          .then(res => {
            this.$message.warning(res.data.extend.msg)
            this.isConcern=true
          })
    },
    cancelCon(){
      let type=this.$store.getters["user/getUser"].uType
      let uid=this.$store.getters["user/getUser"].uid
      this.concern.tid=this.users.uid
      this.concern.type=this.users.type
      if(this.concern.tid==uid&&this.concern.type==type){
        this.$message.warning("不能取消关注自己!")
        return
      }
      cancelConcern(this.concern)
      .then(res=>{
        this.$message.warning(res.data.extend.msg)
       this.isConcern=false
      })
    },
    concernBtn(){
      this.concern.tid=this.users.uid       //获取被关注人id
      this.concern.type=this.users.type      //获取被关注人的类型
      if(this.isConcern){
        this.cancelCon()
      }else {
        this.addCon()
      }
    },
    getUser(item) {
      this.userProfileShow = true
      this.userType = item.type
      this.userId = item.uid
      this.switchConcern()
      getArchives(item.type,item.uid)
          .then(res => {
            if (item.type == 'student') {
              this.user = res.data.extend.student
            } else if (item.type == 'university') {
              this.user = res.data.extend.university
            } else {
              this.user = res.data.extend.company
            }
            this.getNums(item.type,item.uid)
          })
    },
    getNums(type, uid) {
      getUnitCount(type, uid)
          .then(res => {
            // console.log(res)
            let data = res.data.extend
            this.num.collectionNum = data.collectionCount
            this.num.focusNum = data.concernCount
            this.num.dynamicNum = data.dynamicCount
          })
    },

    getType(userType) {
      if (userType == 'university') {
        return '高校'
      } else if (userType == 'company') {
        return '企业'
      } else {
        return '学生'
      }
    }
  }
}


</script>

<style lang="stylus" scoped>
#card
  box-shadow 0 2px 2px darkgrey
  width 400px
  height 170px
  background-color #fff
  color rgb(51,51,51)

header{
  width: 100%;
  height: 70%;
  /*background: url("../../assets/img/perbg.jpg") no-repeat;*/
  /*background-size: 100% 65%;*/
  background-color: rgb(244,247,252);
  display: flex;
  box-sizing: border-box;
  padding:10px 45px;
}
header *{
  margin: 0;
}
.detail{
  font-size: 13px;
  color: #8d9192;
}
header #userMsg{
  width: 60%;
  font-size: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
#concernBtn{
  width: 40%;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
#concern{
  display: flex;
  justify-content: center;
  align-items: flex-end;
}
#avatar{
  width: 70%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: flex-start;
  box-sizing: border-box;
  padding-left: 30px;
}
#avatar *{
  margin: 0;
}
#avatar img{
  width: 20%;
  border-radius: 50%;
}
#avatar img,p:hover{
  transition: all .3s;
  cursor: pointer;
  color: red;
}
#avatar p{
  font-size: 14px;
  font-weight: bold;
}
footer{
  background-color: #fff;
  width: 100%;
  height: 30%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
footer *{
  margin: 0;
}
footer li{
  list-style: none;
  width: 30%;
  border-right: 1px solid #ded1d1;
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
}
footer li:hover{
  color: red;
}
footer li span{
  font-size: 14px;
}
</style>
