<template>
  <div class="video-box" >
<!--    <img class="cover-admin" src="../../assets/img/tab1.jpg" alt="">-->
    <img class="cover" :src="myImg" alt="">
    <!--  遮罩层    -->
    <div class="ret_mask"></div>
    <div class="video-box-details">
      <div class="first">
        <i class="el-icon-video-play"><span>{{item.accessNum}}</span></i>
      </div>
      <div class="second">
        <p ref="det1" style="width: 65%" class="title"><a :title="item.title" href="">{{item.title}}</a></p>
        <p ref="det2" style="width: 35%" class="userName">{{item.userName}}</p>
      </div>
      <div class="third">
        <p>2012/12/31</p>
        <img  style="margin-right: 6px" src="../../assets/svg/player.svg" alt="">
      </div>
    </div>

  </div>
</template>

<script>
import {openVideo} from "@/api/data";
import {baseUrl} from "@/api/baseUrl";

export default {
  name: "Video-1",
  props:["item","img"],
  computed:{
    myImg(){
      let url=this.img
      if(url==0||url==2||url==1){
        if (url==0)
          return require('@/assets/img/tab1.jpg')
        else if(url==1)
          return require('@/assets/img/tab2.jpg')
        else
          return require('@/assets/img/tab3.jpg')
      }else{
        return 'data:image/png;base64,'+url
      }
    }
  },
  methods:{
    openPlayer(id){
      openVideo(id)
          .then(res=>{
            this.movie=baseUrl+'/data/openData?str='+res.data.extend.address
            this.movieShow=true
            this.listShow=false
          })

    },
  }
}
</script>

<style lang="stylus" scoped>

.title,.userName
  overflow: hidden;
  text-overflow: ellipsis;
  white-space nowrap
.second
  display flex
  justify-content space-between
.third
  height 45%
  display flex
  align-items flex-end
  justify-content space-between
  &>img
    height 95%
.video-box
  border-radius 5px
  overflow hidden
  cursor pointer
  position relative
  width 100%
  height 135px
  background linear-gradient(rgba(0,0,0,0))
  &>img
    width 100%
    height 100%

.ret_mask
  width 100%
  height 100%
  top 0
  position absolute
  background linear-gradient(rgba(255,255,255,0),rgba(255,255,255,0),rgba(255,255,255,0),rgba(0,0,0,.2),rgba(0,0,0,.5))

.video-box-details
  position absolute
  box-sizing border-box
  display flex
  flex-direction column
  padding 5px
  width 100%
  top 64%
  height 100%
  color white
  transition all .2s ease-out
.video-box:hover .video-box-details
  top 20%
.video-box:hover .userName
  margin-top -80px
  transition all .2s ease-out
.video-box:hover .ret_mask
  background rgba(0,0,0,.7)
.video-box:hover .cover
  height 120%
</style>
