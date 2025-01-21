<template>
  <div id="card">
    <div id="card-first">
      <div id="avatar">

      </div>
      <div id="myName">
        <h4>打工个人</h4>
        <p>这个热很烂,什么都没说.</p>
      </div>
    </div>

    <div id="number">
      <li @click="$router.push('/setting/myConcern')"><h3>123</h3><span>关注</span></li>
      <li @click="$router.push('/setting/myCollection')"><h3>123</h3><span>收藏</span></li>
      <li @click="$router.push('/setting/myDynamic')" style="border-right: none"><h3>123</h3><span>动态</span></li>
    </div>
    <div id="lineData">
<!--      <hr>-->
      <p>大学:湖南工业职业技术学院</p>
      <p>毕业年份:2019届</p>
    </div>
<!--    <div id="card-first">-->
<!--      <div id="avatar">-->
<!--        <img src="../../assets/img/Avatar.jpg" alt="">-->
<!--        <p>{{userName||'打工人'}}[{{getType(userType)}}]</p>-->
<!--      </div>-->


<!--    </div>-->


    <div id="expand">
      <div class="form"><div><img src="../../assets/svg/form.svg" alt=""></div><span>看动态</span></div>
      <div class="edit"><div><img src="../../assets/svg/edit.svg" alt=""></div><span>发动态</span></div>
      <div class="setting"><div><img src="../../assets/svg/setting.svg" alt=""></div><span>设置</span></div>

    </div>
    <ranking></ranking>
  </div>

</template>

<script>
import {getUnitCount} from "@/api/user"
import Ranking from "@/components/content/Ranking";
export default {
  name: "Card",
  components: {Ranking},
  data(){
    return{
      num:{
        collectionNum:0,
        dynamicNum:0,
        focusNum:0
      },
      userName:localStorage.getItem('userName'),
      userType:localStorage.getItem('userType'),
      userId:localStorage.getItem('userId')

    }
  },
  created() {
    this.getNum()
  },
  methods:{
    getNum(){
      getUnitCount(this.userType,this.userId)
          .then(res=>{
            console.log(res)
            let data=res.data.extend
            this.num.collectionNum=data.collectionCount
            this.num.focusNum=data.concernCount
            this.num.dynamicNum=data.dynamicCount
            this.$store.state.number=this.num
          })
    },

    getType(userType){
      if(userType=='university'){
        return '高校'
      }else if(userType=='company'){
        return '企业'
      }else {
        return '学生'
      }
    }
  }
}
</script>

<style scoped>
*{
  margin: 0;
}
.ret_mask{

  width: 100%;
  height: 100%;
  top: 0;
  position: absolute;
  background :linear-gradient(rgba(0,0,0,0),rgba(0,0,0,.4));
}
#myName{
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  z-index: 5;
  height: 60px;
  margin: 5px;
}
#myName p{
  font-size: 12px;
}
#card{
  box-shadow: 0 0 2px #d5d3d3;
  width: 100%;
  /*height: 590px;*/
  background-color: #fff;
  color: rgb(51,51,51);
  /*border: 1px solid gray;*/
  display: flex;
  flex-direction: column;
  /*justify-content: space-between;*/
  align-items: center;
}
#card-first{
  box-shadow: 0 0 2px #d5d3d3;
  position: relative;
  width: 100%;
  height: 120px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  margin-bottom: 5px;
  color: white;
  background:url("../../assets/img/pen-4337521_1280.jpg");
  background-size: 100% 100%;
}

#avatar{
  margin: 5px;
  z-index: 5;
  border: 1px solid #c4b8b8;
  height: 60px;
  width: 60px;
  background: url("../../assets/img/Avatar.jpg");
  background-size: 100% 100%;
  border-radius: 50%;
}
#avatar *{
  margin: 0;
}
#avatar img{
  width: 25%;
  border-radius: 50%;
}
#avatar img,p:hover{
  transition: all .3s;
  cursor: pointer;
  color: red;
}
#avatar p{
  font-size: 14px;
  font-weight: bold;
}
#number{
  /*background-color: #aa9f9f;*/
  width: 100%;
  height: 10%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
#number *{
  margin: 0;
}
#number li{
  list-style: none;
  width: 30%;
  border-right: 1px solid #ded1d1;
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
}
#number li:hover{
  color: red;
}
#number li span{
  font-size: 14px;
}
#lineData{
  width: 80%;
  height: 60px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  line-height: 28px;
  /*background: blue;*/
}
#expand{
  width: 100%;
  height: 60px;
  box-shadow: 0 0 2px #d5d3d3;
  margin:5px 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
#expand>div{
  height: 100%;
  width: 20%;
  font-size: 14px;
  border: 1px solid white;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
  transition: all .1s ease-in;
}
#expand>div>div{
  width: 45px;
  height: 45px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  background: #d5d5f1;
}
#expand>div>div>img{
  width: 30px;
  height: 30px;
}

.ex-icon{
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
