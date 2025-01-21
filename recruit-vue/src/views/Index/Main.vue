<template>
  <div id="app">
    <header>
      <div id="header-con">
<!--        <img src="@/assets/img/topbar1.jpg" alt="logo">-->
        <div style="color: white;display: flex;align-items: center">
          <h2>毕业生招聘平台</h2>
        </div>
        <ul>
          <!-- <li @click="$router.push('/main/login')">登入</li>
          <li @click="$router.push('/main/register')">注册</li> -->
        </ul>
      </div>
    </header>

    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',

}
</script>
<style lang="stylus" scoped>
main
  background rgb(246,246,248)
  width 100%
  height 100vh
header
  background rgb(48,58,64)
  box-shadow 0px 1px 1px
  width 100%
  height 7vh
  display flex
  justify-content center
  align-items center
  & #header-con
    height 100%
    width 80%
    display flex
    justify-content space-between
    & img
      height 100%
  & ul
    width 120px
    list-style none
    color white
    cursor pointer
    display flex
    align-items center
    justify-content space-between

</style>
