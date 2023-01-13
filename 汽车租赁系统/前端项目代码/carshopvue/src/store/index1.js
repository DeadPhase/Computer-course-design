
import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);
const state = {

  userLogin:false,//用户登陆状态

  //每次都从本地中获取token，这里存的token字段名为Authorization
  Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : ''

}

const mutations = {
  changePressionList(state,list){
    state.pressionList = list;
  },
  // 修改token，并将token存入localStorage
  changeLogin (state, user) {
    state.Authorization = user.Authorization;
    localStorage.setItem('Authorization', user.Authorization);
  }
}
export default new Vuex.Store({
  state,
  mutations
})
