<template>
  <el-card class="app-container" shadow="always">
    <div slot="header">
      <span>编辑企业用户</span>
    </div>
  <el-form ref="companyForm" :model="companyForm" label-width="120px">
      <el-form-item
        prop="hr">
        用户：
        {{companyForm.hr}}
      </el-form-item>
      <el-form-item
        prop="companyName"
        label="公司名">
        <el-input v-model="companyForm.companyName" />
      </el-form-item>
      <el-form-item
        prop="hrName"
        label="负责人">
        <el-input v-model="companyForm.hrName" />
      </el-form-item>
      <el-form-item
        prop="hrMobile"
        label="联系电话">
        <el-input v-model="companyForm.hrMobile" />
      </el-form-item>
      <el-form-item
        prop="hrEmail"
        label="邮箱地址">
        <el-input v-model="companyForm.hrEmail" />
      </el-form-item>
      <el-form-item
        prop="description"
        label="简介">
        <el-input v-model="companyForm.description" />
      </el-form-item>
      <el-form-item
        prop="companyAddress"
        label="地址">
        <el-input v-model="companyForm.companyAddress" />
      </el-form-item>
      <el-form-item
        prop="createTime"
        style="text-align:left"
        label="注册时间">
        <el-date-picker
            size="small"
            type="date"
            placeholder="选择日期"
            value-format="yyyy-M-d"
            v-model="companyForm.createTime"
          >
        </el-date-picker>
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
  import { updateCompany } from "@/api/manager";

  export default {
    components: {ElCard},
    data() {
      return {
        companyForm: {}
      }
    },
    mounted(){
      this.companyForm =JSON.parse(localStorage.getItem("companyInfo"))
      console.log(this.companyForm)
    },
    methods: {
      onSubmit() {
        updateCompany(this.companyForm)
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
