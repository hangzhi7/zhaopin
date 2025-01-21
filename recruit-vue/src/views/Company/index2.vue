<template>
  <div>
    <top-bar></top-bar>
    <div id="container">
      <router-view></router-view>
    </div>

  </div>

</template>

<script>
import TopBar from "../../components/content/TopBar";
import AdminAside from "../../components/content/AdminAside";
export default {
  name: "index",
  components:{
    TopBar,AdminAside
  }
}
</script>

<style lang="stylus" scoped>
#container{
  width: 100%;
  height: 90vh;
  display: flex;
  justify-content: center;
  align-items: flex-end;
  background-color: #ece0e0;
}

</style>
