import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store/index"
import {Message} from "element-ui";

Vue.use(VueRouter)

//解决当前位置的冗余导航报错
const originalPush = VueRouter.prototype.push

VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)

}

const routes = [
  // public
  {
    path:'/',
    component:()=>import("../views/Index/Main"),
    children:[
      {
        path:'',
        redirect:'login'
      },
      // 注册登陆
      {
        path:'login',
        component:()=>import("../views/Index/Login")
      }
    ]
  },
  // 企业用户功能：
  // 1.	修改企业信息
  // 2.	发布职位信息
  // 3.	处理简历
  // 4.	注册登陆
  {
    path:'/company',
    component:()=>import("../views/Company/index"),
    children:[
      // 1.	修改企业信息
      {
        path:'info',
        component:()=>import("../views/Company/Info")
      },
      {
        path:'password',
        component:()=>import("../views/Company/password")
      }
    ]
  },
      // 2. 招聘管理
  {
    path:'/company',
    component:()=>import("../views/Company/index2"),
    children: [
      {
        path:'',
        component:()=>import("../views/Company/Recruitment/index"),
        children: [
          {
            path:'jobManage',
            component:()=>import("../views/Company/Recruitment/jobManagement")
          },
          {
            path:'postResume',
            component:()=>import("../views/Company/Recruitment/postResume")
          }
        ]
      }
    ]
  },
  // 求职者功能：
  // 1.	注册登陆
  // 2.	查询企业招聘信息
  // 3.	投递简历
  // 4.	收藏职位
  // 5.	评论职位
  // 6.	查看其他求职用户
  // 7.	修改个人资料
  // 8.	查看自己得投递是否通过
  {
    path:'/user',
    component:()=>import("../views/User/index"),
    children:[
      // 查询企业招聘信息
      {
        path:'index',
        component:()=>import("../views/User/Recommend/index")
      },
      // 投递简历
      {
        path:'recruitment',
        name: 'recruitment',
        component:()=>import("../views/User/Recruitment/index")
      },
      {
        path:'position',
        component:()=>import("../views/User/Recruitment/Detail")
      }
    ]
  },
  {
    path:'/myResume',
    component:()=>import("../views/Resume/index")
  },
  {
    path:'/personal',
    component:()=>import("../views/PersonalCenter/index"),
    children:[
      {
        path:'',
        redirect:'myArchives'
      },
      {
        path:'myArchives',
        component: ()=>import("../views/PersonalCenter/archives")
      },
      {
        path:'applications',
        component: ()=>import("../views/PersonalCenter/applications")
      },
      {
        path: 'setting',
        component: ()=>import("../views/PersonalCenter/setting")
      },
      {
        path: 'resume',
        component: ()=>import("../views/PersonalCenter/resume/index")
      },
      {
        path:'myCollection',
        component: ()=>import("../views/PersonalCenter/myCollection")
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next)=> {
  if (to.path == '/login') {
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('user');
  }
  let isCompany = JSON.parse(sessionStorage.getItem('isCompany'))
  let user = false
  if (isCompany) {
    user = JSON.parse(sessionStorage.getItem('hr'))
  } else {
    user = JSON.parse(sessionStorage.getItem('user'))
  }
 
  if (!user && to.path != '/login') {
    next({ path: '/login' })
  } else {
    next()
  }
})

export default router
