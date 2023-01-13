<template>
  <div>
  <el-container class="home_container" style="height:800px">
    <el-header>
      <div>
        <img src="../assets/car.jpg" width="80" height="60">
        <span>"红灯笼"网上汽车租赁系统</span>
      </div>
      <div>
        <span>用户姓名：{{ShowUserName}}</span>

        <el-popconfirm
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="确定退出账号吗？"
          @confirm="loginOut()">
          <el-button type="primary"  slot="reference">退出</el-button>
        </el-popconfirm>

      </div>
    </el-header>

    <el-container>
      <el-aside width="200px" :width="isCollapse? '45px':'200px'">
        <div style="color:#fff;background-color:black;cursor:pointer; "  @click="isCollapse=!isCollapse">|||</div>

        <el-menu
          :default-active="$route.path"
          class="el-menu-vertical-demo"
          router
          :collapse="false">
          <!--  一级菜单 -->
          <!--  循环路由数据  -->
          <!--  判断当前路由route是否包含子菜单  -->
          <el-submenu
            v-for="(route,index) in this.$router.options.routes"
            :key="index"
            v-if="route.meta.hasSubMenu"
            :index="route.path">
            <template slot="title">
              <i :class="route.meta.icon"></i>
              <span slot="title" style="color: white">{{route.meta.title}}</span>
            </template>
            <!-- 二级菜单 -->
            <!-- 循环子路由`route.children` -->
            <!-- 循环的时候判断子路由`childRoute`是否包含子菜单 -->
            <el-submenu

              v-for="(childRoute,index2) in route.children"
              :key="index2"
              v-if="childRoute.meta.hasSubMenu"
              :index="childRoute.path">
              <template slot="title">
                <i :class="childRoute.meta.icon"></i>
                <span slot="title" >{{childRoute.meta.title}}|{{childRoute.path}}</span>
              </template>
            </el-submenu>
            <el-menu-item
              :index="childRoute.path" v-else>
              <i :class="childRoute.meta.icon"></i>
              <span slot="title" style="color: white">{{childRoute.meta.title}}</span>
            </el-menu-item>
          </el-submenu>
<!--          <el-menu-item-->
<!--            :index="route.path" v-else>-->
<!--            <i :class="route.meta.icon"></i>-->
<!--            <span slot="title" >{{route.meta.title}}</span>-->
<!--          </el-menu-item>-->
        </el-menu>


      </el-aside>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>


</div>
</template>

<script>

  import Cookies from 'js-cookie'
  import store1 from '../store/index1'
  export default {
    data(){
      return{
        menuList:[],
        isCollapse:false
      }


    },
    methods:{

      loginOut(){

        const _this = this;
        localStorage.removeItem('Authorization')
        _this.$router.push('/')
      },

      // getMenuList(){
      //   //请求权限
      // },


    },
    // mounted() {
    //   console.log(this.$router.options.routes)
    // }


    computed: {
      ShowUserName(){
        const vip = Cookies.get('vip')
          return Cookies.get('name')
      },

      show:function(){
      const vip = Cookies.get('vip')
          return this.$store.state.adminList

      }

    }
  }
</script>

<style scoped>

  .home_container{
    height: 100%;
  }
  .el-header{
    background-color: black;
    display: flex;
    justify-content: space-between;
    line-height: 60px;
    color:#fff;
    font-size: 22px;
  }
  .el-header img{
    vertical-align: middle;
  }

  .el-aside{
    background-color: black;
  }
  .el-menu{
    background-color: black;
  }
  .el-menu-item{
    background-color: black;
  }

  .el-main{
    background: rgb(234,237,241);
  }


</style>


<!--        <el-menu  default-active="1"-->
<!--                  class="el-menu-vertical-demo"-->
<!--                  background-color="rgb(51,55,68)"-->
<!--                  text-color="#fff"-->
<!--                  :collapse="isCollapse"-->
<!--                  :collapse-transition="false"-->
<!--        >-->
<!--          <el-submenu index="1">-->
<!--            <template slot="title"><i class="el-icon-user"></i>-->
<!--              <router-link to="/usermanage" style="color: white">用户管理</router-link>-->
<!--              </template>-->


<!--            <el-menu-item index="1-1">-->
<!--              <router-link to="/usermanage/userlistshow" style="color: white">用户列表</router-link>-->
<!--            </el-menu-item>-->
<!--            <el-menu-item index="1-2">-->
<!--            <router-link to="/userAdd" style="color: white">增加用户</router-link>-->
<!--          </el-menu-item>-->

<!--            <el-menu-item index="1-3">-->
<!--              <router-link to="/userreportform" style="color: white">用户分布</router-link>-->
<!--            </el-menu-item>-->


<!--            <el-menu-item index="1-4">-->
<!--              <router-link to="/register" style="color: white">注册页面</router-link>-->
<!--            </el-menu-item>-->

<!--            <el-menu-item index="1-5">-->
<!--              <router-link to="/userlogin" style="color: white">用户登录页面</router-link>-->
<!--            </el-menu-item>-->

<!--            <el-menu-item index="1-6">-->
<!--              <router-link to="/loginchange" style="color: white">改变登录</router-link>-->
<!--            </el-menu-item>-->

<!--            <el-menu-item index="1-7">-->
<!--              <router-link to="/reportforms" style="color: white">报表</router-link>-->
<!--            </el-menu-item>-->



<!--          </el-submenu>-->


<!--          <el-submenu index="2">-->
<!--            <template slot="title"><i class="el-icon-truck"></i>-->
<!--              <router-link to="/car" style="color: white">汽车管理</router-link>-->
<!--            </template>-->
<!--            <el-menu-item index="2-1" >-->
<!--              <router-link to="/car/carlist" style="color: white">汽车清单</router-link>-->
<!--            </el-menu-item>-->

<!--            <el-menu-item index="2-3" style="color: white">-->
<!--              <router-link to="/caradd" style="color: white">增加汽车</router-link>-->
<!--            </el-menu-item>-->

<!--          </el-submenu>-->

<!--          <el-submenu index="3">-->
<!--            <template slot="title"><i class="el-icon-truck"></i>-->
<!--              <router-link to="/order/orderlist" style="color: white">订单信息管理</router-link>-->
<!--            </template>-->

<!--            <el-menu-item index="3-1">-->
<!--              <router-link to="/order/orderlist" style="color: white">订单列表</router-link>-->
<!--            </el-menu-item>-->

<!--            <el-menu-item index="3-2">-->
<!--              <router-link to="/orderitem" style="color: white">订单商品详情</router-link>-->
<!--            </el-menu-item>-->

<!--          </el-submenu>-->

<!--          <el-submenu index="4">-->
<!--            <template slot="title"><i class="el-icon-truck" ></i>-->
<!--              <router-link to="/integral" style="color: white">积分管理</router-link>-->
<!--            </template>-->

<!--            <el-menu-item index="4-1">-->
<!--              <router-link to="/integral/userintegral" style="color: white">用户积分列表</router-link>-->
<!--            </el-menu-item>-->

<!--          </el-submenu>-->

<!--          <el-submenu index="5">-->
<!--            <template slot="title"><i class="el-icon-truck"></i>-->
<!--              <router-link to="/discount" style="color: white">优惠券管理</router-link>-->
<!--            </template>-->

<!--            <el-menu-item index="5-2">-->
<!--              <router-link to="/discountcouponadd" style="color: white">新增优惠券</router-link>-->
<!--            </el-menu-item>-->

<!--            <el-menu-item index="5-1">-->
<!--              <router-link to="/discount/discountcouponshow" style="color: white">优惠券展示</router-link>-->
<!--            </el-menu-item>-->


<!--            <el-menu-item index="5-3">-->
<!--              <router-link to="/reportforms" style="color: white">图形报表展示</router-link>-->
<!--            </el-menu-item>-->

<!--          </el-submenu>-->



<!--          <el-submenu index="7">-->
<!--            <template slot="title"><i class="el-icon-truck"></i>-->
<!--              <router-link to="/myinfo" style="color: white">个人信息管理</router-link>-->
<!--              </template>-->

<!--            <el-menu-item index="7-1">-->
<!--              <router-link to="/myinfo/userinfo" style="color: white">个人信息</router-link>-->
<!--            </el-menu-item>-->

<!--            <el-menu-item index="7-2" style="color: white">-->
<!--              <router-link to="/updatecustomer" style="color: white">修改信息</router-link>-->
<!--            </el-menu-item>-->

<!--            <el-menu-item index="7-3" style="color: white">-->
<!--              <router-link to="/updatepassword" style="color: white">修改密码</router-link>-->
<!--            </el-menu-item>-->

<!--          </el-submenu>-->



<!--          <el-submenu index="8">-->
<!--            <template slot="title"><i class="el-icon-truck"></i>-->
<!--              <router-link to="/carshow" style="color: white">租赁汽车展示</router-link>-->
<!--            </template>-->

<!--            <el-menu-item index="8-1">-->
<!--              <router-link to="/carshow/carshowlist" style="color: white">汽车展示</router-link>-->
<!--            </el-menu-item>-->

<!--          </el-submenu>-->

<!--          <el-submenu index="6">-->
<!--            <template slot="title"><i class="el-icon-truck"></i>-->
<!--              <router-link to="/mycar" style="color: white">购物车管理</router-link>-->
<!--            </template>-->

<!--            <el-menu-item index="6-1">-->
<!--              <router-link to="/mycar/shoppingcarlist" style="color: white">购物车商品</router-link>-->
<!--            </el-menu-item>-->

<!--          </el-submenu>-->





<!--        </el-menu>-->
