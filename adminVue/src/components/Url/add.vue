<template>
  <el-card class="app-container" shadow="always">
    <div slot="header">
      <span>添加友情链接</span>
    </div>
    <el-form ref="urlForm" :model="urlForm" :rules="rules2" label-width="120px">
      <el-form-item
        prop="name"
        label="名称">
        <el-input v-model="urlForm.name"  placeholder="名称"/>
      </el-form-item>
      <el-form-item
        prop="url"
        label="友情链接">
        <el-input v-model="urlForm.url"  placeholder="请输入友情链接地址"/>
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
  import { createFriendUrl } from "@/api/manager";

  export default {
    components: {ElCard},
    data() {
      return {
        urlForm: {
          name: "",
          url: ""
        }
      }
    },
    methods: {
      onSubmit() {
        if(this.urlForm.name === '' || this.urlForm.url === '') return
        createFriendUrl(this.urlForm.name, this.urlForm.url)
        .then(res=>{
          if(res.data) {
            this.$message({
              type: 'success',
              message: '添加成功!'
            });
            this.$router.back()
            return
          } 
          this.$message({
            type: 'error',
            message: '添加失败!'
          });
        })
      }
    }
  }
</script>

<style scoped>
  .line{
    text-align: center;
  }
</style>
