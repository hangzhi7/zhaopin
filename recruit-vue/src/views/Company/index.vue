<template>
  <main id="bigMain">
    <top-bar></top-bar>
    <article>
      <transition name="fade">
        <router-view></router-view>
      </transition>

    </article>
    <footer>
      <p>CopyRight © 招聘平台</p>
      <p>2020-2021</p>
    </footer>

  </main>
</template>

<script>
import TopBar from "@/components/content/TopBar";
import Carousel from "@/components/conmmon/Carousel";
export default {
  name: "index",
  components:{
    TopBar,Carousel
  }
}
</script>

<style lang="stylus" scoped>
.fade-enter-active, .fade-leave-active {
  transition: all .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
#bigMain
  display flex
  flex-wrap wrap
  justify-content center
  background rgb(246,246,248)
article
  width 70%
  height auto
footer
  width 100%
  height 100px
  background #4b4b50
  color #52CCBA
  display flex
  justify-content center
  align-items center
  flex-direction column
  & p
    margin 0
    font-size 13px
</style>
