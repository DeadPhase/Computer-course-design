// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// import './plugins/request'
Vue.use(ElementUI);
// 引入
import Avue from '@smallwei/avue';
import '@smallwei/avue/lib/index.css';
Vue.use(Avue)

// 引入echarts
import *as echarts from 'echarts'
Vue.prototype.$echarts = echarts

//引入store
import store from './store/index.js'


import Api from './api/index'

Vue.config.productionTip = false
Vue.prototype.$axios = Api




new Vue({
  el: '#app',
  store,
  router,

  render: h => h(App)
})
