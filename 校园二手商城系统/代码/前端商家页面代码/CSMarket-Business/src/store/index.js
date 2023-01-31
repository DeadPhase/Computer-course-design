import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const actions = {

}

const mutations = {
    //修改token,将token放入localStorage中
    changeLogin(state, user) {
        state.Authorization = user.Authorization
        localStorage.setItem('Authorization', user.Authorization)
    },

    //注销
    logout(state) {
        state.Authorization = null
        localStorage.removeItem('Authorization')
    }

}

const state = {
    //存储token
    Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',


}

//向外暴露
const store = new Vuex.Store({
    actions,
    mutations,
    state
})



export default store
