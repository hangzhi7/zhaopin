<template>
  <div style="width:60%;margin-left:20%;margin-top:50px">
    <el-form
      :model="ruleForm"
      status-icon
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="旧密码" prop="oldPassword">
        <el-input type="password" v-model="ruleForm.oldPassword" ></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input type="password" v-model="ruleForm.newPassword" ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">提交</el-button>
        <el-button @click="resetForm()">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { managerUpdate } from '@/api/manager'
export default {
  data() {
 
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入旧密码"));
      }
      callback()
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入新密码"));
      }
      callback()
    };
    return {
      ruleForm: {
        oldPassword: "",
        newPassword: "",
      },
      userinfo:{},
      rules: {
        oldPassword: [{ validator: validatePass, trigger: "blur" }],
        newPassword: [{ validator: validatePass2, trigger: "blur" }]
      }
    };
  },
mounted(){
    let userinfo = JSON.parse(sessionStorage.getItem("user"))
    this.userinfo = userinfo
  },  
  methods: {
    submitForm() {
      var that = this
      this.$refs.ruleForm.validate(valid => {
        if (valid) {
            managerUpdate(that.ruleForm.oldPassword,that.ruleForm.newPassword)
            .then(res => {
              if(res.data) {
                alert("修改成功!");
              } else {
                alert("修改失败!");
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    }
  }
};
</script>