<template>
  <main>
    <div id="container">
      <!-- 左边标语 -->
      <div id="c-1">
        <p style="font-size: 60px;font-weight: 500;">高收入&nbsp;&nbsp;好平台&nbsp;&nbsp;新机遇</p>
        <p style="font-size: 32px;">来这里,你值得更好的</p>
        <img style="height: 350px;" src="../../assets/img/content1.png" alt />
      </div>
      <!-- 登录界面 -->
      <div id="c-2">
        <button :class="[ isCompany?'bt1':'bt2' ]" @click="isCompany = false">个人用户</button>
        <button :class="[ isCompany?'bt2':'bt1' ]" @click="isCompany = true">企业用户</button>

        <br />
        <br />

        <div id="msg">
          <input
              v-model="user.userName"
              :placeholder="userPlace"
              id="userName"
              name="userName"
              type="text"
          />
          <input v-model="user.passWord" :placeholder="passPlace" style="margin-top: 30px" name="pwd" type="password" />



          <br>
          <br><br>
          <div id="remember">
            <!-- <span id="forget" @click="$router.push('rePwd')">忘记密码?</span> -->
            <span id="forget" @click="toRegister">注册</span>
          </div>

          <br>
          <button @click="login()" id="loginBt">登录<i v-if="loading" class="el-icon-loading"></i></button>
          <el-dialog
            title="用户注册"
            :visible.sync="register"
            width="30%"
            center>
            <input
              v-model="newUser.userName"
              :placeholder="userPlace"
              id="userName"
              name="userName"
              type="text"
            />
            <input v-model="newUser.passWord" :placeholder="passPlace" style="margin-top: 30px" name="pwd" type="password" />
            <input v-model="newUser.repeat" placeholder="重复密码" style="margin-top: 30px" name="pwd" type="password" />
            <span slot="footer" class="dialog-footer">
              <el-button @click="register = false">取 消</el-button>
              <el-button type="primary" @click="registerUser()">确 定</el-button>
            </span>
          </el-dialog>
        </div>
      </div>
    </div>
  </main>
</template>


<script>
import dragVerifyImgChip from 'vue-drag-verify-img-chip'
import slideVerify from "@/components/conmmon/slideVerify";
import Captcha from "@/components/conmmon/Captcha";
import {userRegister, userLogin, userInfoShow} from '@/api/user'
import {hrRegister, hrLogin, hrInfoShow} from '@/api/hr'
export default {
  name: "App",
  components: {
    Captcha,
    slideVerify,
    dragVerifyImgChip
  },
  data() {
    return {
      checked:false,
      loading:false,
      uuid:'',
      user: {
        userName: "", //用户名
        passWord: "" //密码
      },
      newUser: {
        userName: "", //用户名
        passWord: "", //密码
        repeat: ""
      },
      yanzheng:false,
      token:'',
      userPlace: "输入用户名",
      passPlace: "输入密码",
      submitTxt: "登录",
      isCompany: false,
      register: false,
      msg:'',
      captchaShow:false,
      captchaImgShow:false,
      captcha:''
    };
  },
  mounted() {
    this.isCompany = false
  },
  methods: {
    toRegister() {
      this.register = true
      this.newUser = {
        userName: "", //用户名
        passWord: "", //密码
        repeat: ""
      }
    },
    registerUser() {
      if (!this.newUser.userName) {
        this.$message.error("用户名不能为空!")
        return
      }
      if (!this.newUser.passWord) {
        this.$message.error("请填写密码~")
        return
      }
      if (this.newUser.passWord!==this.newUser.repeat) {
        this.$message.error("两次密码输入不一致")
        return
      }
      if(this.isCompany) {
        hrRegister(this.newUser.userName, this.newUser.passWord)
          .then(res=>{
            if(res.data) {
              this.$message.success("注册成功")
              this.register = false
            } else {
              this.$message.error("该账号已注册")
            }
          })
        return
      } 
      userRegister(this.newUser.userName, this.newUser.passWord)
        .then(res=>{
          if(res.data) {
            this.$message.success("注册成功")
            this.register = false
          } else {
            this.$message.error("该账号已注册")
          }
        })
    },
    login() {
      if (!this.user.userName) {
        this.$message.error("用户名不能为空!")
        return
      }
      if (!this.user.passWord) {
        this.$message.error("请填写密码~")
        return
      }
      if(this.isCompany) {
        hrLogin(this.user.userName, this.user.passWord)
        .then(res=>{
          if(res.data.indexOf('success')>-1) {
            this.$message.success("登录成功")
            hrInfoShow()
            .then(res=> {
              sessionStorage.setItem("isCompany",this.isCompany)
              sessionStorage.setItem("hr", JSON.stringify(res.data.hr))
              this.$router.push('/company/jobManage')
            })
          } else {
            this.$message.error("登录失败")
          }
        })
        return
      } 
      userLogin(this.user.userName, this.user.passWord)
        .then(res=>{
          if(res.data) {
            this.$message.success("登录成功")
            userInfoShow()
            .then(res=> {
              sessionStorage.setItem("isCompany",this.isCompany)
              sessionStorage.setItem("resume", JSON.stringify(res.data.resume))
              sessionStorage.setItem("user", JSON.stringify(res.data.user))
              this.$router.push('/user/index')
            })
          } else {
            this.$message.error("登录失败")
          }
        })
    }
  }
}
</script>

<style lang="stylus" scoped>
color=rgb(187,187,187)
*
  padding 0
  margin 0
main
  width 100%
  height 93vh
  display flex
  background-color #f6f6f8
  justify-content center
#container
  width 90%
  display flex
  justify-content space-between
  box-sizing border-box
  padding 60px 0
#c-2
  position relative
  width 388px
  height 430px
  padding 60px
  box-sizing border-box
  background-color #fff
  box-shadow 0px 0px 16px rgb(180, 166, 166)
  & input
    border none
    background-color #fff
    width 100%
    height 40px
    margin-top 30px
    outline none
    border-bottom 0.5px rgb(216, 204, 204) solid
.bt1
  width 48%
  background-color #fff
  border none
  outline none
  cursor pointer
  font-size 20px
  color rgb(184, 177, 166)


.bt2
  width 48%
  background-color #fff
  border none
  outline none
  cursor pointer
  font-size 20px
  border-bottom orange 2px solid
  color black

#loginBt
  margin-top 25px
  width 103%
  height 40px
  background-color rgb(255, 102, 68)
  border none
  outline none
  color white
  cursor pointer


#forget
  color blue
  font-size 14px
  cursor pointer

#captcha
  z-index 3
  width 72%
  height 70%
  background #edf392
  box-shadow 0 0 2px black
  position absolute
  top 100px
  display flex
  flex-direction column
  align-items center
  justify-content center
#remember
  display flex
  justify-content space-between
  &>p
    font-size 13px
    color color
</style>
