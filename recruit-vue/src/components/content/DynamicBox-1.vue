<template>
  <div class="dynamicBox-1">
    <div class="title"><h3>学会打扮之后，同一个男生的颜值差异能有多大？</h3></div>
    <div class="content">
<!--      {{dynamic.context}}-->
      大明： 喜欢过度思考。 事实上，过度思考是抑郁和焦虑的重要原因之一。 过度思考在心理学上被称为反刍思维。反刍思维指人们在经历了负面事件后，对事件、自身消极情绪、以及事件的原因和可能产生的后果进行反复思考
    </div>

    <div class="footer flex">
      <div class="footer-left">
        <i class="el-icon-caret-top flex" :style="isLike?'background:rgb(0,132,255);color:white':''">{{isLike?'已赞同':'赞同'}} {{dynamic.likeNum}}</i>
        <i class="el-icon-caret-bottom flex"></i>
      </div>
      <div class="footer-right">
        <i class="el-icon-s-comment flex">{{comment}} 条评论</i>
        <i class="el-icon-star-on flex">收藏</i>
        <i class="el-icon-more flex"></i>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: "DynamicBox-1",
  props:['dynamic','dynamicMap'],
  created() {

  },
  computed:{
    isLike(){
      return this.dynamicMap.likeLists.includes(this.dynamic.id)
    },
    comment(){
      return this.dynamicMap.dynamicMap[this.dynamic.id].count
    }
  },
  methods:{
  }
}
</script>

<style lang="stylus" scoped>
.flex
  display flex
  align-items center
.dynamicBox-1
  padding 15px
  width auto
  height 200px
  background white
  display flex
  flex-direction column
  justify-content space-between
  transition all .2s
  &:hover
    box-shadow 0 0 3px #bab3b3
.title
  height 18%
.content
  height 54%
.footer
  height 18%
  display flex
  align-items center
  justify-content space-between
  font-size 15px
//background #8d9192
.footer-left
  height 100%
  width 28%
  display flex
  justify-content space-between
.footer-right
  color rgb(133,144,166)
  height 100%
  width 72%
  display flex
  & i
    cursor pointer
    margin 0 15px
.el-icon-caret-top,.el-icon-caret-bottom
  cursor pointer
  justify-content center
  width 73%
  border-radius 2px
  height 100%
  background rgb(229,242,255)
  color rgb(0,132,255)
  &:hover
    background rgb(217,237,255)
.el-icon-caret-bottom
  width 25%
</style>
