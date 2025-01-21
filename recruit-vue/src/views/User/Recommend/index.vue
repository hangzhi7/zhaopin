<template>
  <div id="recommend">
    <div id="recommend-1">
      <home-aside></home-aside>
      <div id="recommend-card">
        <div id="search">
          <input @keyup.enter="userSearch" @input="showSearch=true" @blur="closeShow" v-model="keyWords" placeholder="搜索职位、企业" type="text">
          <el-button type="danger" icon="el-icon-search" @click="workSearch"></el-button>
          <!-- <div v-show="showSearch" class="re-search-tip">
            <div @click="workSearch" class="keywords-list">
              搜索职位:{{keyWords}}
            </div>
            <div @click="userSearch" class="keywords-list">
              搜索企业:{{keyWords}}
            </div>
          </div> -->
        </div>
        <div id="hot-job">
          <h4>热门职位:</h4>
          <span style="cursor: pointer" @click="$router.push({name:'recruitment',params:{categoryId:item.id}})" class="hot-jobs" v-for="item in hotJobs" :key="item.id">{{item.name}}</span>
        </div>
        <div class="recommend-cards">
          <div class="left">
            <div @click="$router.push({name:'recruitment',params:{categoryId: 83}})" class="recommend-cards-1">
              <img src="../../../assets/img/card1.png" alt="">
              <div class="mask"></div>
            </div>
            <div @click="$router.push({name:'recruitment',params:{categoryId: 6}})" class="recommend-cards-1">
              <img src="../../../assets/img/card3.png" alt="">
              <div class="mask"></div>
            </div>
          </div>
          <div class="right">
            <div @click="$router.push({name:'recruitment',params:{categoryId: 3}})" class="recommend-cards-2">
<!--              <img src="https://source.unsplash.com/250x250/?advertisement" alt="">-->
              <img src="../../../assets/img/card2.png" alt="">
              <div class="mask"></div>
            </div>
            <div class="recommend-cards-2">
              <img src="../../../assets/img/card4.png" alt="">
              <div class="mask"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <br>
    <div id="recommend-2">
      <h4>热招岗位</h4>
      <div id="work-list">
        <!-- <rec-box1 :jobs="item" v-for="item in jobList"></rec-box1> -->
        <img @click="$router.push('recruitment')" width="auto" height="140" style="cursor: pointer" src="../../../assets/img/morejobs.png" alt="">
      </div>
    </div>
  </div>
</template>

<script>
import HomeAside from "@/components/content/Aside";
export default {
  name: "index",
  components:{
    HomeAside
  },
  created() {
    this.init()
  },
  data(){
    return{
      keyWords:'',
      // showSearch:false,
      jobList:[],
      hotJobs:[
        {
          name: "服务员",
          id: 3,
        },
        {
          name: "营业员",
          id: 79,
        },
        {
          name: "导购",
          id: 90,
        },
        {
          name: "收银",
          id: 7,
        },
        {
          name: "快递员",
          id: 161,
        },
        {
          name: "移动App开发",
          id: 108,
        },
        {
          name: "Java开发",
          id: 99,
        },
        {
          name: "运维",
          id: 101,
        }
      ]
    }
  },
  methods:{
    init(){
      // searchRec().then(res=>{
      //   console.log(res)
      //   let arr=res.data.extend.recruitmentList
      //   for (let i in arr){
      //     if(i<=14){
      //       this.jobList.push(arr[i])
      //     }
      //   }
      // })
    },
    closeShow(){
      setTimeout(()=>{
        this.showSearch=false
      },200)
    },
    workSearch(){
      this.$router.push({
        name:'recruitment',
        params:{
          keyWords:this.keyWords
        }
      })
    },
    // userSearch(){
    //   this.$router.push({
    //     path:'userList',
    //     query:{
    //       searchName:this.keyWords
    //     }
    //   })
    // }
  }
}
</script>

<style lang="stylus" scoped>
--color1=rgb(153,153,153)   //灰色
--color2=rgb(255,92,91)     //红色  悬浮变色
#recommend
  width 100%
  margin 10px 0
  display flex
  flex-direction column
  background rgb(246,246,248)
#recommend-1
  width 100%
  height 560px
  display flex
  justify-content space-between
#recommend-card
  height 100%
  width 75%
  display flex
  flex-direction column
  justify-content space-between
#hot-job
  display flex
  color rgb(102,102,102)
.hot-jobs
  margin 0 7px
.recommend-cards
  width 100%
  display flex
  justify-content space-between
  height 82%
.right,.left
  height 100%
  display flex
  flex-direction column
  justify-content space-between
.left
  width 70.5%
.right
  width 29%
.recommend-cards-1,.recommend-cards-2

  height 49.5%
  position relative
  &:hover .mask
    background rgba(0,0,0,.2)
  & img
    width 100%
    height 100%
.recommend-cards-1
  height 49.5%
.mask
  transition all .2s
  width 100%
  height 100%
  position absolute
  top 0
  background rgba(0,0,0,0)
#carousel
  width 100%
#rec-left
  width 72%
#rec-right
  width 25%
  height 300px
  position sticky
  top 10px
#rec-content
  display flex
  justify-content space-between
  //height 800px
  overflow hidden
#rec-content-dynamic
  width 60%
#rec-content-video
  width 39%
#recommend-2
  width 100%
  &>h4
    color #2b2d2d
    margin 10px 0
#work-list
  width 100%
  display grid
  grid-template-columns repeat(4, 1fr)
  grid-gap 5px
#search
  position relative
  background white
  padding-left 10px
  width 100%
  height 40px
  box-shadow -1px 2px 3px rgb(240,240,242)
  display flex
  justify-content space-between
  & input
    width 80%
    border none
    outline none
  & button
    width 10%
.re-search-tip
  position absolute
  transition all .4s
  z-index 20
  height auto
  min-height 80px
  width 100%
  top 100%
  border-top 1px solid #efe1e1
  //border-radius 10px
  background white
  //box-shadow 0px 0px 4px #6d6666
  box-sizing border-box
  padding 8px
  display flex
  left 0px
  flex-direction column
  & .keywords-list
    color #474343
    width 100%
    height 28px
    font-weight 600
    font-size 15px
    border-radius 5px
    box-sizing border-box
    padding 2px
    cursor pointer
    transition all .2s
    &:hover
      background #d5dfe2
</style>
