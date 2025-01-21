<template>
  <div>
    <img id="captchaimg" @click="refresh()" :src="capSrc" alt="验证码">
  </div>
</template>


<script>
import {baseUrl} from "@/api/baseUrl"

export default {
  name: "captcha",
  props:["uuid"],
  data(){
    return{
      capSrc: baseUrl+'/captcha/'+this.uuid,
    }
  },
  created() {
    this.refresh()
  },
  methods:{
    refresh() { //验证码刷新
      const time=new Date()   //以当前时间为唯一值,防止缓存
      // let num = Math.ceil(Math.random() * 100000); //生成一个随机数（防止缓存）
      const url = baseUrl+"/captcha"
      console.log(this.uuid)
      this.capSrc=`${url}/${this.uuid}?${time}`
      console.log("验证码刷新成功!");
    },

  }
}
</script>

<style scoped>
#captchaimg{
  cursor: pointer;
}
</style>
