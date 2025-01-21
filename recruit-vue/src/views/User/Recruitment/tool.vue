<template>
  <div id="tool">
    <div @click="$router.push('/personal/myArchives')" class="form">
      <div style="background: rgb(225,241,255)" class="circle">
        <img src="../../../assets/svg/form.svg" alt="">
      </div>
      <span>个人信息</span>
    </div>
    <div @click="$router.push('/personal/resume')" class="edit">
      <div style="background: rgb(254,249,226)" class="circle">
        <img src="../../../assets/svg/edit.svg" alt="">
      </div>
      <span>编辑简历</span>
    </div>
    <div @click="$router.push('/personal/setting')" class="setting">
      <div style="background: rgb(229,247,247)" class="circle">
        <img src="../../../assets/svg/setting.svg" alt="">
      </div>
      <span>设置</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "CardTab"
}
</script>

<style lang="stylus" scoped>
#tool
  margin-top 10px
  width 100%
  height 120px
  background white
  display flex
  align-items center
  justify-content space-around
  &>div
    cursor pointer
    display flex
    flex-direction column
    justify-content space-between
    align-items center
    height 60%
    width auto
    font-size 14px
    &>div
      width 40px
      height 40px
      display flex
      justify-content center
      align-items center
      background blue

    &>div>img
      width 65%
      height 65%
.circle
  border-radius 50%
</style>
