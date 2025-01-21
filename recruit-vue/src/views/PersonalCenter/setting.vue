<template>
  <div id="setting">
    <div class="msg">
      <br />
      <h2>密码重置</h2>
      <br />
      <el-form
        style="width: 100%; height: 80%"
        ref="ruleForm"
        :model="user"
        label-width="100px"
      >
        <el-form-item
          prop="oldpassword"
          status-icon
          label="旧密码"
          :rules="[
            {
              required: true,
              message: '请输入旧密码',
              trigger: ['blur', 'change'],
            },
          ]"
        >
          <el-input
            size="small"
            type="password"
            v-model="user.oldpassword"
            placeholder="请输入旧密码"
          ></el-input>
        </el-form-item>

        <el-form-item
          status-icon
          prop="newpassword"
          label="新密码"
          :rules="[
            {
              required: true,
              message: '请输入新密码',
              trigger: ['blur', 'change'],
            },
          ]"
        >
          <el-input
            size="small"
            type="password"
            placeholder="请输入新密码"
            v-model="user.newpassword"
          ></el-input>
        </el-form-item>

        <el-form-item
          status-icon
          prop="repassword"
          label="重复密码"
          :rules="[
            {
              required: true,
              message: '请重复输入新密码',
              trigger: ['blur', 'change'],
            },
          ]"
        >
          <el-input
            size="small"
            type="password"
            placeholder="请重复输入新密码"
            v-model="user.repassword"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-form-item>
            <!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
            <el-button @click="changePwd()" type="primary">保存</el-button>
          </el-form-item>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { passwordUpdate } from '@/api/user'
export default {
  name: 'setting',
  data() {
    return {
      user: {
        oldpassword: '',
        newpassword: '',
        repassword: ''
      }
    }
  },
  methods: {
    changePwd() {
      if (this.user.oldpassword === '' || this.user.newpassword === '') {
        this.$message.error('请输入密码')
        return
      }
      if (this.user.repassword !== this.user.newpassword) {
        this.$message.error('密码输入不一致')
        return
      }
      passwordUpdate(this.user.oldpassword,this.user.newpassword).then((res) => {
        if (res.data.indexOf('success')>-1) {
          this.$message.success('修改成功')
          sessionStorage.removeItem('user')
          this.$router.push('/login')
          return
        }
        this.$message.error('修改失败')
      })
    },
  },
}
</script>

<style scoped>
#setting {
  width: 70%;
  margin-top: 30px;
  margin-bottom: 30px;
  min-height: 300px;
  margin-left: 15%;
  display: flex;
  justify-content: center;
  box-shadow: 0 2px 4px #e0e0e0;
}
.base {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.contact {
  width: 100%;
}
#baseMsg {
  width: 90%;
  height: 180px;
  font-size: 15px;
  color: rgb(128, 128, 128);
  display: flex;
  justify-content: space-between;
}
#contactMsg {
  width: 90%;
  height: 220px;
  font-size: 15px;
  color: rgb(128, 128, 128);
  display: flex;
  justify-content: space-between;
}
.options {
  width: 18%;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}
.msg {
  color: black;
  width: 76%;
  display: flex;
  flex-direction: column;
}
</style>
