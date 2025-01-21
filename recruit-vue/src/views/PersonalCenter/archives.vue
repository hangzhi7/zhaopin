<template>
  <div class="personal-info">
    <el-form
      style="width: 600px"
      ref="userForm"
      :model="userForm"
      label-width="200px"
    >
      <el-form-item> </el-form-item>
      <el-form-item style="text-align: center">
        <div id="bg">
          <el-upload
            class="avatar-uploader"
            action="http://121.4.140.112:8080/api/user/avatar/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <el-avatar
              id="avatar"
              :shape="'square'"
              :size="120"
              :src="avatar"
            ></el-avatar>
          </el-upload>
        </div>
      </el-form-item>
      <el-form-item prop="nickname" label="姓名">
        <el-input v-model="userForm.nickname" />
      </el-form-item>
      <el-form-item prop="gender" label="性别" style="text-align: left">
        <el-select v-model="userForm.gender">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="birthYear" label="出生年份" style="text-align: left">
        <el-input-number v-model="userForm.birthYear" />(年)
      </el-form-item>
      <el-form-item prop="graduation" label="毕业院校">
        <el-input v-model="userForm.graduation" />
      </el-form-item>
      <el-form-item prop="eduDegree" label="学历">
        <el-input v-model="userForm.eduDegree" />
      </el-form-item>
      <el-form-item
        prop="graduateYear"
        label="毕业年份"
        style="text-align: left"
      >
        <el-input-number v-model="userForm.graduateYear" />(年)
      </el-form-item>
      <el-form-item prop="mobile" label="联系电话">
        <el-input v-model="userForm.mobile" />
      </el-form-item>
      <el-form-item prop="email" label="邮箱地址">
        <el-input v-model="userForm.email" />
      </el-form-item>
      <el-form-item prop="major" label="应聘职位">
        <el-input v-model="userForm.major" />
      </el-form-item>
      <el-form-item prop="dirDesire" label="期望薪资" style="text-align: left">
        <el-input-number v-model="userForm.dirDesire" />(元)
      </el-form-item>
      <el-form-item prop="province" label="省份">
        <el-input v-model="userForm.province" />
      </el-form-item>
      <el-form-item prop="city" label="城市">
        <el-input v-model="userForm.city" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { userInfoShow, userInfoUpdate } from '@/api/user'
export default {
  name: 'archives',
  data() {
    return {
      user: JSON.parse(sessionStorage.getItem('user')),
      avatar: 'http://121.4.140.112:8080/api/user/default',
      userForm: {},
      options: [
        {
          value: 0,
          label: '女',
        },
        {
          value: 1,
          label: '男',
        },
      ],
    }
  },
  mounted() {
    this.getData()
    this.avatar = 'http://121.4.140.112:8080/api/user/' + this.user.userId
  },
  methods: {
    handleAvatarSuccess(res, file) {
      console.log(res)
      this.avatar = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload(file) {
      const isImg = file.type.indexOf('image') > -1
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isImg) {
        this.$message.error('上传头像图片只能是图片格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isImg && isLt2M
    },
    getData() {
      userInfoShow().then((res) => {
        this.userForm = res.data.user
        sessionStorage.setItem("user", JSON.stringify(res.data.user))
      })
    },
    onSubmit() {
      userInfoUpdate(this.userForm).then((res) => {
        if (res.data) {
          this.$message({
            type: 'success',
            message: '修改成功!',
          })
          this.getData()
          return
        }
        this.$message({
          type: 'error',
          message: '修改失败!',
        })
      })
    },
  },
}
</script>

<style lang="stylus" scoped>
/deep/.el-form-item__label{
  color: #ff6000;
}
.personal-info {
  width: 70%;
  margin-top: 30px;
  margin-bottom: 30px;
  margin-left: 15%;
  display: flex;
  box-shadow: 0 2px 4px #e0e0e0;
}
.big {
  width: 70%;
  min-height: 500px;
  box-sizing: border-box;
  padding: 2% 10%;
  display: flex;
  align-items: center;
  flex-direction: column;
  background-color: #fff;
  border-bottom: 1px solid grey;
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
.baseMsg {
  width: 90%;
  height: 170px;
  font-size: 15px;
  color: rgb(128, 128, 128);
  display: flex;
  justify-content: space-between;
}

.contactMsg {
  width: 90%;
  height: 70px;
  font-size: 15px;
  color: rgb(128, 128, 128);
  display: flex;
  justify-content: space-between;
}
.options {
  width: 60px;
  height: 100%;
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  align-items: flex-end;
}
.options > p {
  line-height: 30px;
}
.msg {
  color: black;
  justify-content: space-between;
  width: 85%;
  height: 100%;
  display: flex;
  flex-direction: column;
}
.msg > p {
  line-height: 30px;
}
</style>
