<template>
  <el-card class="app-container" shadow="always">
    <div slot="header">
      <span>编辑友情链接</span>
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
  import { updateFriendUrl } from "@/api/manager";

  export default {
    components: {ElCard},
    data() {
      return {
        urlForm: {
          urlId: "",
          name: "",
          url: ""
        }
      }
    },
    mounted(){
      this.urlForm =JSON.parse(localStorage.getItem("urlInfo"))
    },
    methods: {
      onSubmit() {
        updateFriendUrl(this.urlForm.urlId, this.urlForm.name, this.urlForm.url)
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
