<template>
  <div>
    <div class="asideBar" >
      <div class="tap" @click="clickHandle(index,item.path)" v-for="(item,index) in aside">
        <i :class="item.icon"> {{item.name}}</i>
      </div>
    </div>
  </div>



</template>

<script>
import { hrInfoShow } from '@/api/hr'
export default {
  name: "AdminAside",
  data(){
    return{
      aside:[
        {name:'岗位管理',icon:'el-icon-notebook-1',path:'/company/jobManage'},
        {name:'投递管理',icon:'el-icon-setting',path:'/company/postResume'}
      ],
    }
  },
  mounted() {
    this.checkInfo()
  },
  methods:{
    clickHandle(index,path){
      this.$router.push(path)
    },
    checkInfo() {
      hrInfoShow()
      .then(res=>{
        if(res.data) {
          const info = res.data.hr
          if(info.companyAddress ===''|| info.companyName ==='' || info.description==='' || info.hrEmail===''|| info.hrName==='') {
            this.$message.info('请完善企业信息后使用招聘功能')
            this.$router.push('/company/info')
          }
        }
      })
      // .catch(err => {
      //   console.log()
      // })
    }
  }
}
</script>

<style scoped>
.asideBar{
  height: 100%;
  width:100%;
  background-color: rgba(24, 26, 26, 0.8);
}
.tap{
  /*transition: all .2s;*/
  cursor: pointer;
  height: 40px;
  width: 60%;
  line-height: 40px;
  box-sizing: border-box;
  padding-left: 20px;
  margin-bottom: 8px;
  color: white;
}
.active{
  width: 98%;
  transition: .3s;
  position: relative;
  background-color: rgba(255,255,255,.8);
  color: rgb(0,136,245);;
}

.tap:hover{
  color: rgb(0,136,245);
}

</style>
