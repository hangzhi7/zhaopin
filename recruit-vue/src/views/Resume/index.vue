<template>
  <div id="resume">
    <top-bar></top-bar>

  
    </div>

  </div>
</template>

<script>
import TopBar from "@/components/content/TopBar";

export default {
  name: "index",
  components: {
    TopBar
  },
  data(){
    return{
    }
  },
  mounted() {
    this.init()
  },
  methods:{
    upDataResume(){
      putResume({id:this.activeResumeId,name:this.upName,sex:this.upSex,age:this.upAge,degree:this.upDegree,email:this.upEmail,city:this.upAddress}).then(res=>{
        this.$message.success("更新成功")
        this.init()
        this.editShow=false
      })
    },
    toEdit(item){
      this.editShow=true
      this.upName=item.name
      this.upSex=item.sex
      this.upAge=item.age
      this.upDegree=item.degree
      this.upEmail=item.email
      this.upAddress=item.city
    },
    addResume(){
      addResume({}).then(res=>{
        this.init()
      })

    },
    init(){
      getResume(this.sid).then(res=>{
        console.log(res)
        this.resumes=res.data.extend.resumes
        this.activeResumeId=this.resumes[0].id
      })
    },
    delResume(){
      delResume(this.activeResumeId).then(res=>{
        this.$message.success(res.data.msg)
        this.init()
      })
    }
  }
}
</script>

<style lang="stylus" scoped>
.resume-fade-enter-active {
  transition: all .2s ease;
  transition-delay: .2s;
}

.resume-fade-leave-active {
  transition: all .2s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}

.resume-fade-enter
{
  transform: translateY(30px);
  opacity: 0;
}
.resume-fade-leave-to{
  transform: translateY(-30px);
  opacity: 0;
}
#resume
  width 100%
  height 100vh
#resume-main
  width 100%
  background rgb(237,237,237)
  display flex
  flex-direction column
  justify-content center
  align-items center
.resume-container
  width 72%
  box-shadow 0px 0px 2px gray
  display flex
  &>.left
    padding 30px 40px
    width 70%
    height 100%
    background rgb(254,254,254)
    color #5f1010
  &>.right
    width 30%
    height 100%
    display flex
    justify-content center
    align-items center
    flex-direction column
    padding 10px 12px
.resume-avatar
  width 100%
  height 200px
  display flex
  flex-direction column
  justify-content space-around
  align-items center
  color #3b8076
  &>.position
    color rgb(7,180,141)
  &>img
    height 80px
    width 80px
    border-radius 50%
.resume-msg
  color #d29251
  width 100%
  height 140px
  margin-top 20px
  display flex
  flex-direction column
  justify-content space-around
  align-items center
.resume-tag
  display flex
  flex-direction column
  height 200px
  width 100%
  align-items center
.rec-type
  width 100px
  display flex
  justify-content center
  align-items center
  height 30px
  background rgb(237,237,237)
  border-radius 40px
#resume-topBar
  padding 15px 0
  width 72%
  height 60px
  display flex
  align-items center
.resume-tab
  background orange
  height 100%
  display flex
  justify-content center
  align-items center
  margin-right 20px
  border-radius 20px
  cursor pointer
.resume-msg-edit
  width 100%
</style>
