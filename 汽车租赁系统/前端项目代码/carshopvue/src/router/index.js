import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login'
import adminIndex from '../views/adminIndex'
import CarType from '../views/car/CarType'

import Register from '../views/Register'
import Home from '../views/Home'
import UserReportForm from '../views/user/UserReportForm'
import CarList from '../views/car/CarList'
import UserListShow from '../views/user/UserListShow'


import CarReportForm from '../views/car/CarReportForm'



import OrderItem from '../views/order/OrderItem'


import UserIntegral from '../views/UserIntegral'

import ScoreExchangeShow from '../views/ScoreExchangeShow'


//订单管理
import OrderList from '../views/order/OrderList'
import OrderListLog from '../views/order/OrderListLog'

//优惠券管理
import DiscountCouponAdd from '../views/discount/DiscountCouponAdd'

import DiscountCouponShow from '../views/discount/DiscountCouponShow'

import DiscountCouponUpdate from '../views/discount/DiscountCouponUpdate'




Vue.use(Router)


let routes=[


//   =======================================首页============================================
  {
    path:'/index',
    name:'index',
    component:Home,
    redirect:'/index/adminIndex',
    meta:{
      title:'首页',
      icon: 'el-icon-user-solid',  // 图标
      hasSubMenu: true,   // 是否包含子菜单
      isHide:false
    },
    children:[
      //用户列表
      {
        path:'/index/adminIndex',
        name:'userlistshow',
        meta:{
          title:'系统首页',
          hasSubMenu:false,
          isHide:false
        },
        component:adminIndex
      },

    ]
  },
  // ====================================用户信息管理=======================================================
  {
    path:'/usermanage',
    name:'usermanage',
    component:Home,
    redirect:'/usermanage/userlistshow',
    meta:{
      title:'用户管理',
      icon: 'el-icon-user-solid',  // 图标
      hasSubMenu: true,   // 是否包含子菜单
    isHide:false
    },
    children:[
      //用户列表
      {
        path:'/usermanage/userlistshow',
        name:'userlistshow',
        meta:{
          title:'用户列表',
          hasSubMenu:false,
          isHide:false
        },
        component:UserListShow
      },



    ]
  },

  // ======================================= 汽车管理====================================================
  {
    path: '/car',
    name: 'carmanage',
    component: Home,
    redirect: '/car/carlist',
    meta: {
      title: '汽车管理',    // 菜单标题
      icon: 'el-icon-user-solid',  // 图标
      hasSubMenu: true,   // 是否包含子菜单,
    isHide:false
    },
    children: [
      // 汽车清单
      {
        path: '/car/carlist',
        name: 'carlist',
        meta: {
          title: '汽车清单',    // 菜单标题,
          hasSubMenu: false   , // 是否包含子菜单,
          isHide:false
        },
        component: CarList,
      },
      //汽车类型
      {
        path:'/cartype',
        name:'cartype',
        meta:{
          title:'汽车类型',
          hasSubMenu:false,
          isHide:false
        },
        component:CarType

      },

      //汽车报表
      {
        path:'/carreportform',
        name:'carreportform',
        meta:{
          title:'汽车报表', //菜单标题
          hasSubMenu:false,  //是否包含子菜单,
          isHide:false
        },
        component:CarReportForm
      }

    ]
  },

// ======================================= 订单管理====================================================

  {
    path: '/order',
    name: 'order',
    component: Home,
    redirect: '/order/orderlist',
    meta: {
      title: '订单管理',    // 菜单标题
      icon: 'el-icon-user-solid',  // 图标
      hasSubMenu: true,   // 是否包含子菜单
      isHide:false
    },
    children: [
      // 订单列表
      {
        path: '/order/orderlist',
        name: 'orderlist',
        meta: {
          title: '订单列表',    // 菜单标题,
          hasSubMenu: false,    // 是否包含子菜单
          isHide:false
        },
        component: OrderList,
      },

      // 订单详情
      {
        path: '/orderitem',
        name: 'orderitem',
        meta: {
          title: '订单详情',    // 菜单标题
          hasSubMenu: false   , // 是否包含子菜单
          isHide:false
        },
        component: OrderItem
      },
      //完成的订单
      {
        path: '/orderlistLog',
        name: 'orderlistLog',
        meta: {
          title: '完结订单',    // 菜单标题,
          hasSubMenu: false,    // 是否包含子菜单
          isHide:false
        },
        component: OrderListLog,
      }
    ]
  },
// ======================================= 积分管理====================================================
  {
    path: '/integral',
    name: 'integral',
    component: Home,
    redirect: '/integral/userintegral',
    meta: {
      title: '积分管理',    // 菜单标题
      icon: 'el-icon-user-solid',  // 图标
      hasSubMenu: true,   // 是否包含子菜单
      isHide:false
    },
    children: [
      // 用户积分列表
      {
        path: '/integral/userintegral',
        name: 'userintegral',
        meta: {
          title: '积分列表',    // 菜单标题,
          hasSubMenu: false ,   // 是否包含子菜单
          isHide:false
        },
        component: UserIntegral,
      },
      // 积分兑换记录

      {
        path: '/scoreexchangeshow',
        name: 'scoreexchangeshow',
        meta: {
          title: '积分兑换记录',    // 菜单标题
          hasSubMenu: false,    // 是否包含子菜单
          isHide:false
        },
        component: ScoreExchangeShow
      }
    ]
  },
// ======================================= 优惠券管理====================================================
  {
    path: '/discount',
    name: 'discount',
    component: Home,
    redirect: '/discount/discountcouponshow',
    meta: {
      title: '优惠券列表',    // 菜单标题
      icon: 'el-icon-user-solid',  // 图标
      hasSubMenu: true,   // 是否包含子菜单
      isHide:false
    },
    children: [
      // 优惠券列表展示
      {
        path: '/discount/discountcouponshow',
        name: 'discountcouponshow',
        meta: {
          title: '优惠券列表',    // 菜单标题
          hasSubMenu: false   , // 是否包含子菜单
          isHide:false
        },
        component: DiscountCouponShow
      },
      // 优惠券更新
      {
        path: '/discountcouponupdate',
        name: 'discountcouponupdate',
        meta: {
          title: '优惠券更新',    // 菜单标题,
          hasSubMenu: false ,   // 是否包含子菜单
          isHide:false
        },
        component: DiscountCouponUpdate,
      },
      //增加优惠券
      {
        path:'/discountcouponadd',
        name:'discountcouponadd',
        meta:{
          title:'增加优惠券' ,  // 菜单标题,
          hasSubMenu:false ,     // 是否包含子菜单
          isHide:false,
        },
        component:DiscountCouponAdd
      }

    ]
  },



  {
    path:'/register',
    name:'注册页',
    component:Register,
    meta: {
      hasSubMenu:false
    }
  },
  {
    path:'/',
    component: Login,
    meta: {
      hasSubMenu:false
    }
  },

  {
    path:'/home',
    name:'主界面',
    redirect: '/index/adminIndex',
    component:Home,
    meta: {
      hasSubMenu:false,
      requireAuth: true
    }
  }

];

export default new Router({
  routes
})





