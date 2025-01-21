<template>
  <div id="app" >
      <router-view></router-view>
  </div>
</template>

<script>
export default {
  components: {
    // Aside,TopBar
  },
}
</script>

<style lang="stylus">
@import "assets/css/base.styl"
#app
  height 100vh
  width 100vw
  transition all .3s
  overflow-y scroll
  overflow-x hidden
  &::-webkit-scrollbar
    width 6px
    background-color #F5F5F5
  &::-webkit-scrollbar-track
    background-color: #F5F5F5;
  &::-webkit-scrollbar-thumb
    background-color: #F90;
    background-image: -webkit-linear-gradient(45deg, rgba(255, 255, 255, .2) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, .2) 50%, rgba(255, 255, 255, .2) 75%, transparent 75%, transparent);
.night
  background-color black
</style>
