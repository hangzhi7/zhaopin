<template>
  <div>
      <!-- <span id="user">{{$store.getters.userName}}</span> -->
      <!-- <el-avatar shape="square" :size="30" :fit="fit" :src="require('@/assets/img/Avatar.jpg') "></el-avatar> -->
     
  
    <el-dropdown>
      <span class="el-dropdown-link">
        <i class="el-icon-user"></i>{{$store.getters.userName?$store.getters.userName:'未登录'}}<i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item    icon="el-icon-remove"><span @click="logout()">退出登录</span></el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

  </div>
</template>

<script>
export default {
  name:'Avatar',
  methods:{
    logout(){
      this.$store.dispatch("user/logout")
      console.log("退出登录");
    }
  }
}
</script>


</style>