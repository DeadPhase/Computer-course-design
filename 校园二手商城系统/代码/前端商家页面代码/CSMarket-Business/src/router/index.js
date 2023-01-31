import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Register from '../views/main/Register'
import Login from '@/views/main/Login'
import Home from '@/views/main/Home'
import HomePage from '@/views/end/HomePage'
import AdminInfo from '@/views/end/AdminInfo'
import UserInfo from '@/views/end/UserInfo'
import TypeInfo from '@/views/end/TypeInfo'
import GoodsInfo from '@/views/end/GoodsInfo'
import OrderInfo from '@/views/end/OrderInfo'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: '注册页',
      component: Register
    },
    {
      path: '/login',
      name: '登录页',
      component: Login
    },
    //菜单栏设置
    {
      path: '/Home',
      name: "主界面",
      component: Home,
      redirect: '/HomePage',
      children: [
        {
          path: '/HomePage',
          name: '后台首页',
          component: HomePage
        },
        {
          path: '/AdminInfo',
          name: '管理员信息',
          component: AdminInfo
        }, {
          path: '/UserInfo',
          name: "用户信息",
          component: UserInfo
        }, {
          path: '/TypeInfo',
          name: "化妆品种类",
          component: TypeInfo
        }, {
          path: '/GoodsInfo',
          name: "商品详情",
          component: GoodsInfo       
        },
        {
          path: '/OrderInfo',
          name: "订单详情",
          component: OrderInfo
        }
      ]

    },


  ]
})


//全局前置守卫
router.beforeEach((to, from, next) => {
  // to and from are both route objects. must call `next`.
  if (to.path === '/register' || to.path === '/login' || to.path === '/') {
    next();//直接放行
  } else {
    const token = localStorage.getItem('Authorization');
    if (token === null || token === '') {
      next('/login')
    } else {

      next()
    }
  }
})


export default router
