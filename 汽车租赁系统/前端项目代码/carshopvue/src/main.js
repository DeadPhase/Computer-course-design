// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import './plugins/request'

// import store from './store/index.js'
import store from './store/index1'

import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'


Vue.use(ElementUI);
Vue.config.productionTip = false

// 引入echarts
import * as echarts from 'echarts'

Vue.prototype.$echarts = echarts


/*路由守卫   根据登录获得token*/
router.beforeEach((to,from,next) =>{

  if (to.path === '/login' || to.path ==="/" ||to.path ==="/register" ) {
    next();
  } else {
    let token = localStorage.getItem('Authorization');

    if (token === null || token === '') {
      next('/login');
    } else {
      next();
    }
  }


})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  // store1,

  render: h => h(App)
})
