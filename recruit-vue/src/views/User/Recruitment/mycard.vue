<template>
  <div id="myCard">
    <div id="recAvatar">
      <img :src="avatar">
    </div>
    <div class="userName">
      <span>{{user.userName}}</span>
    </div>
    <div v-if="type=='student'" class="tab">
      <el-button style="width: 100%" type="success" size="small" @click="$router.push('/user/deliveryList')">查看已投递的岗位</el-button>
    </div>

  </div>
</template>

<script>
export default {
  name: "mycard",
  data(){
    return{
      avatar: require("@/assets/img/adminAvatar.jpg"),
      user
    }
  },
  created() {
    this.init()
  },
  methods:{
    init(){
      this.user = JSON.parse(sessionStorage.getItem('user'))
    }
  }
}
</script>

<style lang="stylus" scoped>
#myCard
  background white
  height 200px
  display flex
  flex-direction column
  justify-content space-between
  padding 10px
#recAvatar
  height 60px
  display flex
  justify-content center
  &>img
    height 100%
    width 60px
    border-radius 50%
.userName,.userMsg
  height 24px
  font-weight 200
  width 100%
  display flex
  justify-content center
  align-items center
  &>span
    margin 0 2px
.boy,.girl
  height 100%
  display flex
  align-items center
  &>img
    height 100%

.tab
  display flex
  justify-content space-around
  &>div
    cursor pointer
    display flex
    flex-direction column
    align-items center
</style>
