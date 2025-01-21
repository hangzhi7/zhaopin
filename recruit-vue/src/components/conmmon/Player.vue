<template>
  <div id="player">
    <div id="video-msg">
      <h3>Java入门</h3>
    </div>
    <el-button @click="$store.commit('CLOSE_PLAYER')" id="el-btn" icon="el-icon-circle-close">关闭</el-button>
    <video autoplay id="video" style="width: 100%"  width="90%" height="440" controls>
      <source :src="movie" type="video/mp4">
      <source :src="movie" type="video/flv">
    </video>
  </div>
</template>

<script>
export default {
  name: "Player",
  props:['movie'],
}
</script>

<style lang="stylus" scoped>
#player
  width 100%
  height 100%
#video
  width 100%
  height 100%
#el-btn
  z-index 10
  position absolute
  background rgba(0,0,0,0.4)
  color white
  border none
  cursor pointer
  &:hover
    background white
    color black
#video-msg
  height 60px
  display flex
  justify-content center
  align-items center
</style>
