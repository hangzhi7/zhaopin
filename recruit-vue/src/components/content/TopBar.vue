<template>
  <main>
    <div id="container">
      <div id="topBar">
        <div id="topBar-container">
          <div id="top-left">
            <div id="logo">
              <h2>毕业生招聘平台</h2>
            </div>
            <div id="tab">
              <div class="tab-child" v-for="item in tabList" @click="$router.push(item.path)" :key="item.name">{{item.name}}</div>
            </div>
          </div>
          <div id="top-right">
            <div id="tab-two">
              <div id="avatar" v-if="!isCompany">
                <img :src="icon">
              </div>
              <el-dropdown @command="handleCommand" placement="top">
              <span class="el-dropdown-link" style="font-size:18px">{{ userName }}<i style="font-size:18px" class="el-icon-user"></i></span>
                <el-dropdown-menu slot="dropdown" >
                  <el-dropdown-item v-if="!isCompany" command="personal">个人中心</el-dropdown-item>
                  <el-dropdown-item v-if="isCompany" command="company">企业信息</el-dropdown-item>
                  <el-dropdown-item v-if="isCompany" command="password">修改密码</el-dropdown-item>
                  <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </div>
        </div>

      </div>
    </div>
  </main>
</template>

<script>
export default {
  name: "TopBar",
  created() {
    this.isCompany = JSON.parse(sessionStorage.getItem('isCompany'))
    
    // 公司
    if(this.isCompany){
      let hr = JSON.parse(sessionStorage.getItem('hr'));
      this.userName = hr.hrName?hr.hrName:'暂无昵称'
      this.tabList=[
        {name:'招聘',icon:'el-icon-position',path:'/company/jobManage'},
        {name:'企业信息',icon:'el-icon-position',path:'/company/info'},
      ]
      return
    }
    let user = JSON.parse(sessionStorage.getItem('user'));
    this.userName = user.nickname?user.nickname:'暂无昵称'
    this.icon = 'http://121.4.140.112:8080/api/user/' + (user.avatar?user.avatar:'default')
  },
  data(){
    return{
      tabList:[
        {name:'首页',icon:'el-icon-s-opportunity',path:'/user/index'},
        {name:'招聘',icon:'el-icon-position',path:'/user/recruitment'},
        {name:'个人中心',icon:'el-icon-position',path:'/personal'},
      ],
      userName: "未登录",
      isCompany: false,
      icon: require("@/assets/img/adminAvatar.jpg")
    }
  },
  methods:{
    handleCommand(command){
      const _this = this
      if(command=='logout'){
        sessionStorage.removeItem("user");
        sessionStorage.removeItem("hr");
        _this.$router.push("/login");
      }else if(command=='company'){
        this.$router.push("/company/info")
      }else if(command=='password'){
        this.$router.push("/company/password")
      }else if(command=='personal'){
        this.$router.push("/personal")
      }
    },
  }
}
</script>

<style lang="stylus" scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@1,300&display=swap');
dip=@block {
  display flex
  justify-content center
}
*{
  margin 0
}
main
  z-index 10
  width 100%
  height 4rem
  //display flex
  //background-image url("https://source.unsplash.com/2250x120/?city")
  //background url("../../assets/img/topbar2.jpg")
  background #454242
  background-size 100% 100%
#container
  height 100%
  //background-image: linear-gradient(rgba(0,0,0,.5),rgba(0,0,0,0));
#topBar
  height 4rem
  //background-color #1991FA
  {dip}
#topBar-container
  width 70%
  height 100%
  //background-color #45494c
  display flex
  align-items center
  justify-content space-between
#top-left,#top-right
  width 50%
  height 100%
  display flex
  align-items center
#logo
  font-family: 'Roboto Condensed', sans-serif
  display flex
  align-items center
  width 230px;
  height 60px;
  color white
  //background-image -webkit-linear-gradient(left, #ff7878, #ffa93f 10%, #fff8a2 20%, #9aff5b 30%, #cfffcf 40%, #a6ffff 50%, #CCCCFF 60%, #c6f7ff 70%, #a2ffd8 80%, #67ffb2 90%, #ffa85c 100%)
  //-webkit-text-fill-color transparent /* 将字体设置成透明色 */
  //-webkit-background-clip text /* 裁剪背景图，使文字作为裁剪区域向外裁剪 */
  //-webkit-background-size 200% 100%;
  //-webkit-animation masked-animation 12s linear infinite
  & h2
    //font-style oblique
    cursor pointer

@keyframes masked-animation {
  0% {
    background-position: 0 0;
  }
  100% {
    background-position: -100% 0;
  }
}
#tab
  height 100%
  display flex
  width 300px
//background-color #1991FA
.tab-child
  font-size 14px
  height 100%
  transition all .3s
  width 60px
  cursor pointer
  color white
  {dip}
  align-items center
  &:hover
    background-color rgba(0,0,0,.2)
#search
  width 35%
  height 100%
  display flex
  align-items center
  justify-content space-between
  position relative
  & input
    height 20px
    width 180px
    outline none
    border none
    padding-left 7px
    border-radius 15px
    background-color rgba(255,255,255,.3)


  & button
    height 23px
    width 23px
    border-radius 50%
    {dip}
    align-items center
    &:hover
      cursor pointer
      background-color #52CCBA
#top-right
  display flex
  justify-content flex-end
#tab-two
  width 70%
  height 100%
  display flex
  justify-content flex-end
  align-items center
  & i
    color white
    font-size 14px
    cursor pointer
#avatar
  cursor pointer
  height 40px
  width 40px
  margin-right 30px
  border-radius 50%
  transition all .4s
  transform-origin top center
  &:hover
    transform scale(1.5,1.5)
    transform-origin top center
  & img
    width 100%
    height 100%
    border-radius 50%
.el-dropdown-link
  cursor pointer
  color white

.el-icon-arrow-down
  font-size: 12px
#search-list
  transition all .4s
  position absolute
  z-index 5
  height auto
  min-height 20px
  width 190px
  top 100%
  border-radius 10px
  background white
  box-shadow 0px 0px 4px #6d6666
  box-sizing border-box
  padding 8px
  display flex
  flex-direction column
  & .keywords-list
    width 100%
    height 24px
    font-size 13px
    border-radius 5px
    box-sizing border-box
    padding 2px
    cursor pointer
    transition all .2s
    &:hover
      background #d5dfe2
</style>
