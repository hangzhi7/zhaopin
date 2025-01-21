<template>
  <div id="rank">
    <div id="top">
      <img src="../../assets/img/fire.png" alt="">IT热点
    </div>

    <div style="padding-left: 8px">
      <div class="bigSerial" v-for="(item,index) in rankingData">
        <a :href="item.url" target="view_window" >
          <span class="serial">{{index+1}}</span>{{item.title}}
        </a>

      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "Ranking",
  data(){
    return{
      rankingData:[
        {title:'Vue 3.0 来了，我们该做些什么？',url:'https://juejin.im/post/6874604408030789640?utm_source=gold_browser_extension'},
        {title:'算法与数据结构 带你看哈希算法之美',url:'https://juejin.im/post/6874708801208254478?utm_source=gold_browser_extension'},
        {title:'使用 SVG 制作加载动画',url:'https://juejin.im/post/6874846460261695502?utm_source=gold_browser_extension'},
        {title:'Vue3.0 不畏惧祖传代码的 Composition API',url:'https://juejin.im/post/6874748339246776327?utm_source=gold_browser_extension'},
        {title:'一年前端面试分享',url:'https://juejin.im/post/6873444336059711495?utm_source=gold_browser_extension'},
        {title:'尤大 3 天前发在 GitHub 上的 vue-lit 是啥？',url:'https://juejin.im/post/6874383765070675976?utm_source=gold_browser_extension'},
        {title:'尤大 3 天前发在 GitHub 上的 vue-lit 是啥？',url:'https://juejin.im/post/6874383765070675976?utm_source=gold_browser_extension'},
        {title:'尤大 3 天前发在 GitHub 上的 vue-lit 是啥？',url:'https://juejin.im/post/6874383765070675976?utm_source=gold_browser_extension'},
        {title:'尤大 3 天前发在 GitHub 上的 vue-lit 是啥？',url:'https://juejin.im/post/6874383765070675976?utm_source=gold_browser_extension'},
        {title:'尤大 3 天前发在 GitHub 上的 vue-lit 是啥？',url:'https://juejin.im/post/6874383765070675976?utm_source=gold_browser_extension'},


      ]
    }
  }
}
</script>

<style scoped>
a{
  text-decoration: none;
  color: black;
}
#rank{
  background-color: rgba(255,255,255,.7);
  height: 100%;
  width: 100%;
}
#top{
  width: 100%;
  height: 40px;
  line-height: 40px;
  background-color: rgb(248,248,248);
  color: rgb(86,86,86);
  font-weight: 600;
}
#top img{
  margin-top: 7px;
  float: left;
}
.serial{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 20px;
  height: 20px;
  background-color: rgb(145,204,147);
  color: #fff;

  font-size: 12px;
  float: left;
  margin-right: 10px;
}
.bigSerial{
  width: 100%;
  height: 40px;
  box-sizing: border-box;
  padding: 15px 0;
  font-size: 14px;
  color: rgb(85,85,85);
  cursor: pointer;
  overflow: hidden;
}
.bigSerial a{
  width: 100%;
  height: 20px;
  display:block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.bigSerial:hover{
  text-decoration: underline;
}
</style>
