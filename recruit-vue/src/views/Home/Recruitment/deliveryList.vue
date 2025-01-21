<template>
  <div id="deliveryList">
    <div id="main-list">
      <rec-box1 style="margin-bottom: 5px" v-for="item of deList" :jobs="item.recruitment"></rec-box1>
    </div>
  </div>
</template>

<script>
import {getDelivers} from "@/api/recruitment";
import RecBox1 from "@/components/content/RecBox1";
export default {
  name: "deliveryList",
  components:{
    RecBox1
  },
  data(){
    return{
      deList:[]
    }
  },
  created() {
    this.init()
  },
  methods:{
    init(){
      getDelivers(this.$store.getters["user/getUser"].uid).then(res=>{
        this.deList=res.data.extend.delivers
        console.log(res)
      })
    }
  }
}
</script>

<style lang="stylus" scoped>
#deliveryList
  margin-top 10px
  min-height 500px
  height auto
  display flex
  justify-content space-between
#main-list
  height 600px
  overflow-y scroll
  width 70%
  background white
.resume
  width 27%
  background white
  height 400px
</style>
