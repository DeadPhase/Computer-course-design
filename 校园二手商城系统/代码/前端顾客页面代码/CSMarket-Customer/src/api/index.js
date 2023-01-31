import { Get, Post, Put, MyPut, Patch, Delete } from "@/api/request";

export default {



    Login: (params) => {
        return Get('http://localhost:8282/user/userLogin', params)
    },

    isLogin: (params) => {
        return Get('http://localhost:8282/user/isLogin', params)
    },


    findAllGoodsInfo: (params) => {
        return Get('http://localhost:8282/goodsInfo/getInfo', params)
    },

    findGoodsDetail: (params) => {
        return Get('http://localhost:8282/goodsInfo/GoodsDetail', params)
    },

    /**
     * 购物车 addCartShopInfo
     */
    addCartShopInfo: (params) => {
        return Post('http://localhost:8282/cartshop/add', params)
    },

    queryCartShopInfo: (params) => {
        return Get('http://localhost:8282/cartshop/find', params)
    },

    deleteCartShopInfo: (params) => {
        return Delete('http://localhost:8282/cartshop/delete', params)
    },

    /**
     * 订单
     */

    addOrder: (params) => {
        return Post('http://localhost:8282/order/add', params)
    },
    queryOrder: (params) => {
        return Get('http://localhost:8282/order/findAll', params)
    },

    onPaymoney: (params) => {
        return Put('http://localhost:8282/order/paymoney', params)
    },

    onRefund: (params) => {
        return Put('http://localhost:8282/order/refund', params)
    },

    onCancelRefund: (params) => {
        return Put('http://localhost:8282/order/cancelRefund', params)
    },

    onReturnable: (params) => {
        return Put('http://localhost:8282/order/onReturnable', params)
    },

    onCancelReturnable: (params) => {
        return Put('http://localhost:8282/order/onCancelReturnable', params)
    },

    /**
     * 用户信息
     */
    rechargeMoney: (params) => {
        return Put('http://localhost:8282/user/rechargeMoney', params)
    },
    findUserById: (params) => {
        return Get('http://localhost:8282/user/findUserById', params)
    },

    onChangePWD: (params) => {
        return Put('http://localhost:8282/user/changePwd', params)
    },

    UpdateUserInfo: (params) => {
        return Put('http://localhost:8282/user/updateUserInfo', params)
    },

 
    //获取验证码
    GetCode: (params) => {
        return Get('http://localhost:8282/user/getCode', params)
    },

    //验证验证码
    VerifyCode: (params) => {
        return Get('http://localhost:8282/user/verifyCode', params)
    },

    //修改手机号
    UpdatePhone: (params) => {
        return Put('http://localhost:8282/user/updatePhone', params)
    },



}
