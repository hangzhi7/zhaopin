import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  // 管理员：
  // 1.	修改密码
  // 2.	管理用户
  // 3.	设置职业分类
  // 4.	设置学历参数
  // 5.	管理网站友情链接信息
  // 6.	所有的招聘信息
  // 7.	所有的应聘记录

  routes: [
    {
      path: '/login',
      name: 'Login',
      component:()=>import("@/components/Login"),
    },
    {
      path: '/position',
      component:()=>import("@/components/Home"),
      children: [
        { 
          path: 'list',
          component:()=>import("@/components/Position/list"),
          name: '岗位列表 ' 
        },
      ]
    },
    {
      path: '/position',
      component:()=>import("@/components/Home"),
      children: [
        { 
          path: 'list',
          component:()=>import("@/components/Position/list"),
          name: '岗位列表 ' 
        },
      ]
    },
    {
      path: '/application',
      component:()=>import("@/components/Home"),
      children: [
        { 
          path: 'list',
          component:()=>import("@/components/Application/list"),
          name: '应聘记录' 
        },
      ]
    },
    {
      path: '/url',
      component:()=>import("@/components/Home"),
      children: [
        { 
          path: 'list',
          component:()=>import("@/components/Url/list"),
          name: '友情链接列表 ' 
        },
        { 
          path: 'edit',
          component:()=>import("@/components/Url/edit"),
          name: '友情链接编辑 '
        },
        { 
          path: 'add',
          component:()=>import("@/components/Url/add"),
          name: '友情链接添加 '
        }
      ]
    },
    {
      path: '/user',
      component:()=>import("@/components/Home"),
      children: [
        { 
          path: 'list',
          component:()=>import("@/components/User/list"),
          name: '求职用户列表 ' 
        },
        { 
          path: 'edit',
          component:()=>import("@/components/User/edit"),
          name: '求职用户编辑 '
        }
      ]
    },
    {
      path: '/company',
      component:()=>import("@/components/Home"),
      children: [
        { 
          path: 'list',
          component:()=>import("@/components/Company/list"),
          name: '企业用户列表 ' 
        },
        { 
          path: 'edit',
          component:()=>import("@/components/Company/edit"),
          name: '企业用户编辑 '
        }
      ]
    },
    {
      path: '/',
      component:()=>import("@/components/Home"),
      children: [
        { 
          path: 'category',
          component:()=>import("@/components/Category/index"),
          name: '职业分类管理' 
        }
      ]
    },
    {
      path: '/',
      component:()=>import("@/components/Home"),
      children: [
        { 
          path: 'education',
          component:()=>import("@/components/Education/index"),
          name: '学历参数' 
        }
      ]
    },
    {
      path: '/',
      component:()=>import("@/components/Home"),
      children: [
        {
          path: '/user',
          component:()=>import("@/components/nav3/User"),
          name: '求职用户'
        },
        {
          path: '/company',
          component:()=>import("@/components/nav3/User"),
          name: '企业用户'
        }
      ]
    },
    {
      path: '/',
      component:()=>import("@/components/Home"),
      children: [
        {
          path: '/reset',
          component:()=>import("@/components/nav4/reset"),
          name: '修改密码'
        }
      ]
    },
   ]
})
