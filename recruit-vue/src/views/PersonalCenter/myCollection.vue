<template>
  <div id="collections">
    <div class="dynamic" >
      <div v-show="favList.length===0" class="empty-tip">暂无数据</div>
      <div class="notNull" v-for="job in favList" :key="job.positionId">
        <header>
          <h3>{{job.title}}</h3>
          <p style="color:red">{{job.salaryDown}}~{{job.salaryUp}}（元/月）</p>
        </header>
        <article>
          {{job.requirement}}
        </article>
        <footer>
          <i style="justify-content:center" class="el-icon-thumb" v-show="job.statePub===1" @click="getDetail(job)">查看详情</i>
          <i style="justify-content:center;color: red;" class="el-icon-error" v-show="job.statePub!==1">已下架</i>
          <i style="justify-content:flex-end" class="el-icon-delete" @click="notFav(job)">取消收藏</i>
        </footer>
      </div>
    </div>

  </div>
</template>

<script>
import {listFav, disFavor} from "@/api/user";
export default {
  name: "myConcern",
  data(){
    return{
      favList:[]
    }
  },

  created() {
    this.getData()
  },
  methods:{
    getData() {
      listFav().then(res=>{
        this.favList = res.data
      })
    },
    getDetail(job) {
      localStorage.setItem('job',JSON.stringify(job))
      this.$router.push('/user/position')
    },
    notFav(job) {
      disFavor(job.positionId).then(res=>{
        if(res.data === 'success') {
            this.$message.success('已取消收藏')
            this.getData()
            return
          }
          this.$message.error('操作失败')
      })
    }
  }
}
</script>

<style lang="stylus" scoped>
fontColor=rgb(109,109,109)
.empty-tip
  width 100%
  height 200px
  display flex
  justify-content center
  align-items center
  font-size 30px
  color #bc9c9c
#collections{
  width: 70%;
  margin-top: 30px;
  margin-bottom: 30px;
  min-height: 300px;
  margin-left: 15%;
  display: flex;
  box-shadow: 0 2px 4px #e0e0e0;
}
#noBody
  display flex
  justify-content center
  align-items center
  color fontColor
.isNull
  box-shadow 0 0 1px gray
  width 100%
  height 120px
  display flex
  flex-direction column
  justify-content center
  align-items center
  color fontColor
  &:hover
    box-shadow 0 0 2px gray
.dynamic
  width 100%
  margin 10px
  box-sizing border-box
  padding 10px
.notNull
  box-sizing border-box
  padding 15px
  margin-bottom 15px
  width 100%
  display flex
  box-shadow 0 0 1px gray
  flex-direction column
  justify-content space-between
  word-break break-all
  &:hover
    box-shadow 0 0 2px gray
  & header
    width 100%
    display flex
    justify-content space-between
  & article
    margin-top 10px
    font-size 14px
    width 100%
    color fontColor
  & footer
    width 100%
    height 24px
    display flex
    align-items flex-end
    justify-content space-between
    & i
      width 80px
      cursor pointer
      display flex

</style>
