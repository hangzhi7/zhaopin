<template>
  <div class="block">
    <el-carousel trigger="click" height="280px">
      <el-carousel-item v-for="item in 6" :key="item">
        <img style="width: 100%;height: 100%" :src="'https://source.unsplash.com/1100x340/?nature,'+item" alt="">
<!--        <img width="100%" height="100%" src="http://localhost:8081/img/montain.jpg" alt="">-->
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  name: "Carousel",
  data(){
    return{
      carousel:[
        {src:require('../../assets/img/car1.jpg')},
        {src:require('../../assets/img/car2.jpg')},
        {src:require('../../assets/img/car3.jpg')}
      ]
    }
  }
}
</script>

<style scoped>

</style>
