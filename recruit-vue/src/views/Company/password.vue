<template>
  <div id="form-container">
    <h1>修改密码</h1>
    <br />
    <br />
    <transition name="slide-fade">
      <el-form
        :model="company"
        style="width: 100%; height: 80%"
        ref="ruleForm"
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
            v-model="company.oldpassword"
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
            v-model="company.newpassword"
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
            v-model="company.repassword"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-form-item>
            <!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
            <el-button @click="save()" type="primary">保存</el-button>
          </el-form-item>
        </el-form-item>
      </el-form>
    </transition>
  </div>
</template>

<script>
import { hrPasswordUpdate } from '@/api/hr'
export default {
  name: 'password',
  data() {
    return {
      company: {
        oldpassword: '',
        newpassword: '',
        repassword: ''
      },
    }
  },
  methods: {
    save() {
      if(this.company.oldpassword === '' || this.company.newpassword === '' || this.company.repassword === '' ) {
        this.$message.error("请完成表单")
        return
      }
      if(this.company.newpassword !== this.company.repassword) {
        this.$message.error("两次密码输入不一致")
        return
      }
      hrPasswordUpdate(this.company.oldpassword, this.company.newpassword).then((res) => {
        if (res.data.indexOf('success') > -1) {
          this.$message.success('保存成功')
          sessionStorage.removeItem("hr")
          this.$router.push('/login')
          return
        }
        this.$message.error('保存失败')
      })
    },
  },
}
</script>

<style scoped>
#form-container {
  overflow: hidden;
  width: 100%;
  height: 60%;
  box-shadow: 0 2px 4px gray;
  background-color: #fff;
  box-sizing: border-box;
  margin-top: 30px;
  padding: 30px 30px 300px 30px;
}
p {
  line-height: 30px;
}
#submit {
  position: fixed;
  left: 21%;
  bottom: 15%;
  width: 80px;
}
#success {
  width: 100%;
  height: 80%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
#material {
  padding: 10px;
  box-sizing: border-box;
  width: 100%;
  height: 80%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: gainsboro 1px dotted;
}
.slide-fade-enter-active {
  transition: all 0.2s ease;
  transition-delay: 0.2s;
}

.slide-fade-leave-active {
  transition: all 0.2s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter, .slide-fade-leave-to
  /* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateX(20px);
  opacity: 0;
}
</style>
