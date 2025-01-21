<template>
<div id="card-user">
  <div id="user-center" @click="$router.push('/personal')">
    <i class="el-icon-user" ><span>个人中心</span></i>
    <i class="el-icon-d-arrow-right"></i>
  </div>

  <div  id="user-number">
    <div style="border-right: 1px solid rgb(133,144,166)">
      <p>今日收到关注</p>
      <span>{{myFansCount}}</span>
      <div class="up-number">
        <span>较前日</span>
        <img src="../../../assets/svg/up.svg" alt="">
        <span>{{fansUpgrade=='-'?'-':fansUpgrade*100}}%</span>
      </div>
    </div>
    <div>
      <p>今日收到的赞</p>
      <span>{{likesReceived}}</span>
      <div class="up-number">
        <span>较前日</span>
        <img src="../../../assets/svg/up.svg" alt="">
        <span>{{likesReceivedUpgrade=='-'?'-':likesReceivedUpgrade*100}}%</span>
      </div>
    </div>
  </div>
  <div id="user-tip">
    <img src="../../../assets/svg/defend.svg" alt="">
    <p>请注意防范论坛信息诈骗,及时举报不良内容</p>

  </div>

</div>
</template>

<script>
import {getConcernMine, getHotProportion} from "@/api/user"
export default {
name: "CardUser",
  data(){
  return{
    type:this.$store.getters["user/getUser"].uType,
    uid:this.$store.getters["user/getUser"].uid,
    myFansCount:0,
    fansUpgrade:0,
    likesReceived:0,
    likesReceivedUpgrade:0
  }
  },
  created() {
    this.init()
  },
  methods:{
    init(){
      // getConcernMine(this.type,this.uid).then(res=>{
      //   console.log(res)
      //   this.myFansCount=res.data.extend.count
      // })
      getHotProportion(this.uid,this.type).then(res=>{
        this.likesReceivedUpgrade=res.data.extend.likeProportion
        this.fansUpgrade=res.data.extend.concernHotProportion
        let {countConcernToday,countLikeToday}=res.data.extend
        this.boostAnimation(countConcernToday,countLikeToday)
        // this.boostAnimation(160,456)
      })
    },
    boostAnimation(fansNumber,likesNumber){
      let fans=setInterval(()=>{
        if(this.myFansCount>=fansNumber){
          clearInterval(fans)
        }else {
          this.myFansCount++
        }
      },5)
      let myLikes=setInterval(()=>{
        if(this.likesReceived>=likesNumber){
          clearInterval(myLikes)
        }else {
          this.likesReceived++
        }
      },5)
    }
  }
}
</script>

<style lang="stylus" scoped>
#card-user
  width 100%
  display flex
  flex-direction column
  justify-content space-between
  background white
  height 220px
  margin-top 10px
  padding 10px 20px
#user-center
  display flex
  justify-content space-between
  cursor pointer
#user-number
  padding-top 15px
  width 100%
  height 42%
  display flex
  &>div
    width 50%
    display flex
    flex-direction column
    justify-content space-between
    align-items center
    &>span
      font-size 20px
      font-weight 600
    &>p
      color rgb(133,144,166)
.up-number
  font-size 13px
  display flex
  justify-content center
  align-items center
  width 100%
  height 30%
  &>img
    height 100%
#user-tip
  width 100%
  height 20%
  display flex
  align-items center
  font-size 14px
  color rgb(133,144,166)
  &>img
    height 70%
</style>
