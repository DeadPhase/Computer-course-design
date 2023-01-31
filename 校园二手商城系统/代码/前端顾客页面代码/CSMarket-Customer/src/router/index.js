import Vue from 'vue'
import Router from 'vue-router'

import Index from '@/views/Index'
//用户信息
import UserInfo from '@/views/UserInfo';
import UserInfoShow from '@/views/UserInfoShow';
import PersonalData from '@/views/userInfo/PersonalData'
import PersonInfo from '@/views/userInfo/PersonInfo'
import PassWord from '@/views/userInfo/PassWord';
import MailBox from '@/views/userInfo/MailBox'
import Phone from '@/views/userInfo/Phone'
import NewPhone from '@/views/userInfo/NewPhone'
import NewMaibox from '@/views/userInfo/NewMaibox'
import AccountSettings from '@/views/userInfo/AccountSettings'
import HomePage from '@/views/HomePage'
import GoodsDetail from '@/views/GoodsDetail'
import OrderInfo from '@/views/OrderInfo'
import ShopCarInfo from '@/views/ShopCarInfo'
import ReviewInfo from '@/views/ReviewInfo'
import CommunicateOnline from '@/views/CommunicateOnline';
import AnnouncementInfo from '@/views/AnnouncementInfo'
import ConfirmTheOrder from '@/views/ConfirmTheOrder'
import UserLogin from '@/views/UserLogin'
// import { component } from 'vue/types/umd';
Vue.use(Router)

export default new Router({
  routes: [


    {
      path: '/UserLogin',
      name: '用户登录',
      component: UserLogin

    },
    {
      path: '/',
      name: 'index',
      component: Index,
      redirect: '/HomePage',
      children: [
        {
          path: '/HomePage',
          name: '商城首页',
          component: HomePage

        },

        {
          path: '/GoodsDetail',
          name: '商品详情',
          component: GoodsDetail
        },
        {
          path: '/ConfirmTheOrder',
          name: '确认订单',
          component: ConfirmTheOrder
        },
        {
          path: '/AnnouncementInfo',
          name: '公告信息',
          component: AnnouncementInfo
        },

        {
          path: '/CommunicateOnline',
          name: '在线交流',
          component: CommunicateOnline

        },
        {
          path: '/ShopCarInfo',
          name: '购物车信息',
          component: ShopCarInfo
        }
        , {
          path: '/OrderInfo',
          name: '订单信息',
          component: OrderInfo
        },
        {
          path: '/ReviewInfo',
          name: '评价信息',
          component: ReviewInfo

        },
        {
          path: '/userInfo',
          name: '个人信息',
          component: UserInfo
        },
        {
          path: '/UserInfoShow',
          name: '用户信息',
          component: UserInfoShow,
          redirect: '/UserInfoShow/PersonInfo',
          children: [
            {
              path: '/UserInfoShow/PersonalData',
              name: '个人资料',
              component: PersonalData
            },
            {
              path: '/UserInfoShow/PersonInfo',
              name: '个人资料2',
              component: PersonInfo
            },
            {
              path: '/UserInfoShow/AccountSettings',
              name: '账号设置',
              component: AccountSettings
            }, {
              path: '/UserInfoShow/PassWord',
              name: '修改密码',
              component: PassWord
            },
            {
              path: '/UserInfoShow/MailBox',
              name: '修改邮箱',
              component: MailBox
            }, {
              path: '/UserInfoShow/Phone',
              name: '修改号码',
              component: Phone
            }, {
              path: '/UserInfoShow/NewPhone',
              name: '修改新号码',
              component: NewPhone
            }, {
              path: '/UserInfoShow/NewMaibox',
              name: '修改新邮箱',
              component: NewMaibox

            }
          ]

        },

      ]
    }
  ]
})
