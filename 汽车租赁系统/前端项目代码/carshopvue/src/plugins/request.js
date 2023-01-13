"use strict";

import Vue from 'vue';
import axios from "axios";

// 常规配置
axios.defaults.baseURL = 'http://localhost:8282';

// axios.defaults.withCredentials  =true; //  在跨域中允许携带凭证
// axios.defaults.header['Content-Type'] = 'application/x-www-form-urlencoded';//  声明传给服务器的数据，通过请求传给服务器的数据application/x-www-form-urlencoded格式
// axios.defaults.headers.common["token"] = window.sessionStorage.getItem('token'); //  携带token请求头
// axios.defaults.headers.common["token"] = localStorage.getItem('Authorization'); //  携带token请求头
// axios.defaults.headers.common['Authorization'] = window.sessionStorage.getItem('token');
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

let config = {
  // baseURL: 'http://localhost:8282',
  // timeout: 60 * 1000, // Timeout
  // withCredentials: true, // Check cross-site Access-Control
};

const _axios = axios.create(config);

_axios.interceptors.request.use(
  function(config) {
    // config.headers.Authorization=window.sessionStorage.getItem('token')
    // config.headers.Authorization=localStorage.getItem('Authorization')
    if (localStorage.getItem('Authorization')) {
      config.headers.Authorization = localStorage.getItem('Authorization');
    }
    return config;
  },
  function(error) {
    // Do something with request error
    return Promise.reject(error);
  }
);

// Add a response interceptor
_axios.interceptors.response.use(
  function(response) {
    // Do something with response data
    return response;
  },
  function(error) {
    // Do something with response error
    return Promise.reject(error);
  }
);

Plugin.install = function(Vue, options) {
  Vue.axios = _axios;
  window.axios = _axios;
  Object.defineProperties(Vue.prototype, {
    axios: {
      get() {
        return _axios;
      }
    },
    $axios: {
      get() {
        return _axios;
      }
    },
  });
};

Vue.use(Plugin)

export default Plugin;
