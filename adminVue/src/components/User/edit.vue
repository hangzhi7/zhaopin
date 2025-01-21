<template>
  <el-card class="app-container" shadow="always">
    <div slot="header">
      <span>编辑求职用户</span>
    </div>
  <el-form ref="userForm" :model="userForm" label-width="120px">
      <el-form-item
        prop="user">
        用户：
        {{userForm.user}}
      </el-form-item>
      <el-form-item
        prop="mobile"
        label="联系电话">
        <el-input v-model="userForm.mobile"/>
      </el-form-item>
      <el-form-item
        prop="email"
        label="邮箱地址">
        <el-input v-model="userForm.email"/>
      </el-form-item>
      <el-form-item
        prop="gender"
        label="性别"
        style="text-align: left">
        <el-select v-model="userForm.gender">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item
        prop="birthYear"
        label="出生年份"
        style="text-align: left">
        <el-input-number v-model="userForm.birthYear"/>(年)
      </el-form-item>
      <el-form-item
        prop="graduation"
        label="毕业院校">
        <el-input v-model="userForm.graduation"/>
      </el-form-item>
      <el-form-item
        prop="eduDegree"
        label="学历">
        <el-input v-model="userForm.eduDegree"/>
      </el-form-item>
      <el-form-item
        prop="graduateYear"
        label="毕业年份"
        style="text-align: left">
        <el-input-number v-model="userForm.graduateYear"/>(年)
      </el-form-item>
      <el-form-item
        prop="major"
        label="应聘职位">
        <el-input v-model="userForm.major"/>
      </el-form-item>
      <el-form-item
        prop="dirDesire"
        label="期望薪资"
        style="text-align: left">
        <el-input-number v-model="userForm.dirDesire"/>(元)
      </el-form-item>
      <el-form-item
        prop="province"
        label="省份">
        <el-input v-model="userForm.province"/>
      </el-form-item>
      <el-form-item
        prop="city"
        label="城市">
        <el-input v-model="userForm.city"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">提交</el-button>
        <el-button @click="$router.back()">取消</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
  import ElCard from "element-ui/packages/card/src/main";
  import { updateUser } from "@/api/manager";

  export default {
    components: {ElCard},
    data() {
      return {
        userForm: {},
        options: [
            {
              value: 0,
              label: '女'
            },
            {
              value: 1,
              label: '男'
            }
          ]
      }
    },
    mounted(){
      this.userForm =JSON.parse(localStorage.getItem("userInfo"))
      console.log(this.userForm)
    },
    methods: {
      onSubmit() {
        updateUser(this.userForm)
        .then(res=>{
          if(res.data) {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            this.$router.back()
            return
          } 
          this.$message({
            type: 'error',
            message: '修改失败!'
          });
        })
      },
      onCancel() {
        this.$router.back()
      }
    }
  }
</script>

<style scoped>
  .line{
    text-align: center;
  }
</style>
