<template>
  <div id="content">
    <AdminAside style="width: 15%"></AdminAside>
    <div style="overflow: hidden;width: 85%;height: 100%">
      <transition name="slide-fade">
        <router-view style="height: 100%"></router-view>
      </transition>
    </div>

  </div>
</template>

<script>
import AdminAside from "@/components/content/AdminAside";
export default {
  name: "index",
  components:{
    AdminAside
  }
}
</script>

<style lang="stylus" scoped>
#content{
  box-shadow: 0 2px 4px grey;
  //margin-bottom: 1%;
  width: 100%;
  height: 100%;
  background-color: #fff;
  display: flex;
  justify-content: space-between;

}
.slide-fade-enter-active {
  transition: all .3s ease;
  transition-delay: .3s;
}
.slide-fade-leave-active {
  transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-fade-enter, .slide-fade-leave-to
{
  transform: translateX(10px);
  opacity: 0;
}

</style>
