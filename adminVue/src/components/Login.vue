<template>
  <div class="login-view">
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm"  class="demo-ruleForm">
        <h3 class="title">招聘后台登录</h3>
        <el-form-item prop="account">
          <el-input type="text" v-model="ruleForm.account" auto-complete="off" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" auto-complete="off" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item style="width:100%;">
        <el-button type="primary"  @click.native.prevent="handleSubmit" :loading="logining">登录</el-button>
        <el-button @click.native.prevent="handleReset">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { managerLogin } from '@/api/manager'

export default {
  data() {
    return {
      logining: false,
      ruleForm: {
        account: "admin",
        checkPass: "admin"
      },
      options: [],
        value: '',
      userinfo:{},
      rules: {
        account: [
          { required: true, message: "请输入账号", trigger: "blur" },
          { type: "string", message: "账号必须为字符和数字" },
          { min: 2, max: 15, message: "长度在 2 到 15个字符", trigger: "blur" }
          //{ validator: validaePass }
        ],
        checkPass: [
          { required: true, message: "请输入密码", trigger: "blur" }
          //{ validator: validaePass2 }
        ]
      },
      checked: true
    };
  },
  mounted(){
    let historyuser = localStorage.getItem("history")
    if(historyuser){
      historyuser = JSON.parse(localStorage.getItem("history"))
      historyuser.forEach(element => {
        this.options.push(element)
      });
      this.value = historyuser[0].value
    }
  },
  methods: {
    handleReset() {
      this.$refs.ruleForm.resetFields();
    },
    handleSubmit() {
      this.$refs.ruleForm.validate(valid => {
        if (valid) {
          // this.logining = true;
          var loginParams = {
            username: this.ruleForm.account,
            password: this.ruleForm.checkPass
          };
          this.logining = false;
          managerLogin(loginParams.username, loginParams.password)
          .then(res => {
            if (res.data) {
              sessionStorage.setItem("user", JSON.stringify(res.data));
              this.$router.push({ path: "/position/list" });
            } else {
              this.$message.warning("用户名或密码错误！请检查后再试");
            }
          });
        }
      });
    }
  },
  watch:{
    value(newval,oldval){
      console.log(newval,oldval)
      this.ruleForm.account = newval
    }
  }
};
</script>

<style scoped lang="scss">
.login-view
{
  background-image:url('../assets/zhaopin.jpeg');
  background-repeat: repeat;
  height: 100vh;
  width: 100vw;
  display: flex;
  justify-content: center;
  align-items: center;
}
.demo-ruleForm {
  /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  /* margin: 80px auto; */
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  position: relative;
}
.el-form-item {
  margin-left: 0;
}
.title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
.remember {
  margin: 0px 0px 35px 0px;
}
</style>
