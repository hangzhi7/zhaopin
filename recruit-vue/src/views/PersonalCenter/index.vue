<template>
  <div>
    <top-bar></top-bar>
    <!-- <div id="container">
      <div id="bg">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/api/user/avatar/upload"
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
      <p style="font-size: 24px">{{ userName }}</p>
    </div> -->
    <div id="content">
      <div id="tab">
        <button
          @click="tabClick(item.path)"
          :key="item.id"
          v-for="item in tabList"
          :class="
            $route.fullPath == `/personal/${item.path}` ? 'btn active' : 'btn'
          "
        >
          {{ item.name }}
        </button>
      </div>
    </div>
    <transition name="fade">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
import TopBar from '../../components/content/TopBar'
export default {
  name: 'index',
  components: {
    TopBar,
  },
  data() {
    return {
      tabList: [
        { id: 1, path: 'myArchives', name: '个人信息' },
        { id: 2, path: 'resume', name: '我的简历' },
        { id: 3, path: 'myCollection', name: '我的收藏' },
        { id: 4, path: 'applications', name: '我的投递' },
        { id: 5, path: 'setting', name: '安全设置' },
      ],
      avatar: 'http://121.4.140.112:8080/api/user/default',
      user: JSON.parse(sessionStorage.getItem('user')),
    }
  },
  computed: {
    userName() {
      return this.user.nickname
    },
  },
  mounted() {
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
    tabClick(path) {
      this.$router.push(path)
    },
  },
}
</script>

<style scoped>
.fade-enter-active {
  transition: all 0.2s ease;
  transition-delay: 0.2s;
}

.fade-leave-active {
  transition: all 0.2s cubic-bezier(1, 0.5, 0.8, 1);
}

.fade-enter {
  transform: translateY(30px);
  opacity: 0;
}
.fade-leave-to {
  transform: translateY(-30px);
  opacity: 0;
}
#container {
  width: 100%;
  /*height: 100vh;*/
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #ece0e0;
}
#content {
  box-shadow: 0 2px 4px #e0e0e0;
  /* margin-top: 20px; */
  margin-left: 15%;
  width: 70%;
  height: 60px;
  background-color: #fff;
}
#bg {
  overflow: hidden;
  width: 100%;
  height: 90%;
  background-size: 150% 150%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: rgba(255, 255, 255, 0.8);
  animation: archive 6s linear forwards;
  transform-origin: center center;
}
@keyframes archive {
  0% {
    background-size: 150% 150%;
  }
  100% {
    background-size: 100% 100%;
  }
}
#bg * {
  margin: 0;
}
#tab {
  width: 100%;
  height: 100%;
  background-color: #fff;
  /*border-bottom: 1px solid blue;*/
  display: flex;
  justify-content: center;
  box-shadow: 0 1px 2px rgb(232, 232, 232);
}
.btn {
  cursor: pointer;
  margin: 0 50px;
  outline: none;
  border: none;
  background-color: #fff;
}
.active {
  font-weight: 600;
  border-bottom: 2px solid orange;
}
.avatar-risk {
  background: rgba(0, 0, 0, 0.6);
  position: absolute;
  width: 80px;
  height: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  top: 100%;
  transition: all 0.1s;
  cursor: pointer;
}
#avatar-con:hover .avatar-risk {
  top: 0;
}
</style>
