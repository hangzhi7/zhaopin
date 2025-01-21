<template>
  <div id="Aside">
    <div  v-for="item in asideList" :key="item.id" class="rec-type">
      <p class="rec-type-title">{{item.name}}<i class="el-icon-caret-right"></i></p>
      <p class="rec-type-exa">{{getThree(item.child)}}</p>
      <div class="rec-type-child" >
        <p >{{item.name}}</p>
        <div style="display: flex;flex-wrap: wrap">
          <span @click="$router.push({name:'recruitment',params:{categoryId:typeChild.id}})" v-for="typeChild in item.child">{{typeChild.name}}</span>
        </div>

      </div>
    </div>


  </div>
</template>

<script>
// import {T1, T2, T3, T4, T5,T6,T7,T8} from "@/data/jobs";
import { listCategory} from '@/api/hr'
export default {
  name: "Aside",
  data(){
    return{
      asideList:[
        {
          id:1,name:'餐饮服务',
          child:[]
        },
        {
          id:2,name:'人事/财政/财务',
          child:[]
        },
        {
          id:3,name:'超市/零售',
          child:[]
        },
        {
          id:4,name:'互联网/IT',
          child:[]
        },
        {
          id:5,name:'销售/采购',
          child:[]
        },
        {
          id:6,name:'家政/保洁',
          child:[]
        },
        {
          id:7,name:'物流/仓储/司机',
          child:[]
        },
        {
          id:8,name:'运动/健身',
          child:[]
        },
      ],
      categorys: []
    }
  },
  created() {
    this.listCategory()
  },
  methods:{
    listCategory() {
      listCategory().then(res=> {
        this.categorys = res.data
        this.categorys.forEach(item=>{
          if(item.firstCategoryName === '餐饮服务') this.asideList[0].child.push({
            name: item.secondCategoryName,
            id: item.categoryId
          })
          if(item.firstCategoryName === '人事/财政/财务') this.asideList[1].child.push({
            name: item.secondCategoryName,
            id: item.categoryId
          })
          if(item.firstCategoryName === '超市/零售') this.asideList[2].child.push({
            name: item.secondCategoryName,
            id: item.categoryId
          })
          if(item.firstCategoryName === '互联网/IT') this.asideList[3].child.push({
            name: item.secondCategoryName,
            id: item.categoryId
          })
          if(item.firstCategoryName === '销售/采购') this.asideList[4].child.push({
            name: item.secondCategoryName,
            id: item.categoryId
          })
          if(item.firstCategoryName === '家政/保洁') this.asideList[5].child.push({
            name: item.secondCategoryName,
            id: item.categoryId
          })
          if(item.firstCategoryName === '物流/仓储/司机') this.asideList[6].child.push({
            name: item.secondCategoryName,
            id: item.categoryId
          })
          if(item.firstCategoryName === '运动/健身') this.asideList[7].child.push({
            name: item.secondCategoryName,
            id: item.categoryId
          })
        })
        // console.log(this.asideList)
      })
    },
    getThree(item){
      return `${item[0].name}/${item[1].name}/${item[2].name}`
    }
  }
}
</script>

<style lang="stylus" scoped>
--color1=rgb(153,153,153)   //灰色
--color2=rgb(255,92,91)     //红色  悬浮变色
#Aside
  width 260px
  height 560px
  background white
  box-shadow 0 0 3px rgb(248,247,247)
  display flex
  flex-direction column
  justify-content space-between

.rec-type
  transition all .2s
  padding 0 20px
  height 70px
  width 100%
  display flex
  flex-direction column
  justify-content center
  position relative
  cursor pointer
  line-height 24px
  &:hover
    background rgb(253,248,248)
  &:hover .rec-type-child
    display flex
  &:first-child .rec-type-child
    top 0
  &:last-child .rec-type-child
    top -150%
  &:hover
    .rec-type-title,.rec-type-exa
      color --color2

.rec-type-title
  display flex
  font-weight 100
  justify-content space-between
.rec-type-exa
  letter-spacing 3px
  color --color1
  font-size 15px
  overflow hidden
  white-space nowrap
  text-overflow ellipsis
.rec-type-child
  transition all .8s
  display none
  padding 20px
  width 500px
  height auto
  min-height 160px
  background white
  top -80%
  position absolute
  left 100%
  flex-direction column
  flex-wrap wrap
  box-shadow 0 0 3px rgb(241,236,236)
  z-index 10
  & span
    display block
    font-size 14px
    margin-right 15px
    color --color1
    cursor pointer
    &:hover
      color --color2
.el-icon-caret-right
  color --color1
</style>
