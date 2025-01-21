<template>
  <main>
    <div id="applications">
      <div class="dynamic" >
        <div v-show="appList.length===0" class="empty-tip">暂无数据</div>
        <div class="notNull" v-for="job in appList" :key="job.positionId">
          <header>
            <h3>{{job.title}}</h3>
            <p style="color:red">{{job.salaryDown}}~{{job.salaryUp}}（元/月）</p>
          </header>
          <article>
            {{job.requirement}}
          </article>
          <footer>
            <i style="justify-content:center" class="el-icon-thumb" v-show="job.statePub===1" @click="getDetail(job)">查看详情</i>
            <i style="justify-content:center" v-show="job.statePub!==1"></i>
            <div style="justify-content:flex-end;color:red;" v-if="job.statePub!==1">
              <i style="font-size:26px;" class="el-icon-error"></i>
              已下架
            </div>
            <div style="justify-content:flex-end;color:green;" v-if="job.applicationState===1&&job.statePub===1">
              <i style="font-size:26px;" class="el-icon-success"></i>
              已通过
            </div>
            <div style="justify-content:flex-end;color:red;" v-if="job.applicationState===2&&job.statePub===1">
              <i style="font-size:26px;" class="el-icon-error"></i>
              未通过
            </div>
            <div style="justify-content:flex-end;color:blue;" v-if="job.applicationState===0&&job.statePub===1">
              <i style="font-size:26px;" class="el-icon-question"></i>
              请等待
            </div>
          </footer>
          <el-row v-if="job.reply" class="job-reply">
            <el-col :span="4">
              人事回复：
            </el-col>
            <el-col :span="20">
              {{job.reply}}
            </el-col>
          </el-row>
        </div>
      </div>

    </div>
  </main>
</template>

<script>
import {listApp} from "@/api/user";
export default {
  name: "myConcern",
  data(){
    return{
      appList:[],
      resume: JSON.parse(sessionStorage.getItem('resume'))
    }
  },
  created() {
    this.getData()
  },
  methods:{
    getData() {
      listApp(this.resume.resumeId).then(res=>{
        this.appList = res.data
      })
    },
    getDetail(job) {
      localStorage.setItem('job',JSON.stringify(job))
      this.$router.push('/user/position')
    }
  }
}
</script>

<style lang="stylus" scoped>
fontColor=rgb(109,109,109)
.job-reply
  margin-top 5px
  font-size 14px
  color #ff6000
.empty-tip
  width 100%
  height 200px
  display flex
  justify-content center
  align-items center
  font-size 30px
  color #bc9c9c
#applications{
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
  margin-bottom 15px
  padding 10px
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
