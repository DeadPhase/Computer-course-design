import { Get, Post, Put, MyPut, Patch, Delete } from "@/api/request";

export default {
    getListData: (params) => {
        return Get('../../static/data.json', params);
    },
    postListData: (params) => {
        return Post('../../static/data.json', params);
    },
    deleteListData: (params) => {
        return Delete('../../static/data.json', params);
    },
    Register: (params) => {
        return Post('http://localhost:8282/user/register', params)
    },
    adminRegister: (params) => {
        return Post('http://localhost:8282/admin/register', params)
    },
    Login: (params) => {
        return Post('http://localhost:8282/user/login', params)
    },

    findAllUser: (params) => {
        return Get('http://localhost:8282/user/findAllUser', params)
    },


    findAllAdmin: (params) => {
        return Get('http://localhost:8282/admin/findAllUser', params)
    },
    submitUpdateAdminForm: (params) => {
        return Put('http://localhost:8282/admin/updateUserInfo', params)
    },

    handleAdminDelete: (params) => {
        return Delete('http://localhost:8282/admin/deleteUser/', params)
    },

    submitUpdateUserForm: (params) => {
        return Put('http://localhost:8282/user/updateUserInfo', params)
    },

    handleDelete: (params) => {
        return Delete('http://localhost:8282/user/deleteUser/', params)
    },

    /**
     * 商品种类
     */
    addTypeInfo: (params) => {
        return Post('http://localhost:8282/typeInfo/addTypeInfo', params)
    },
    findAllTypeInfo: (params) => {
        return Get('http://localhost:8282/typeInfo/findAllTypeInfo', params)
    },
    handleDeleteTypeInfo: (params) => {
        return Delete('http://localhost:8282/typeInfo/deleteTypeInfo', params)
    },
    submitUpdateUserForm: (params) => {
        return Put('http://localhost:8282/typeInfo/updateTypeInfo', params)
    },

    /**
     * 商品详情
     */
    getTypeInfoOption: (params) => {
        return Get('http://localhost:8282/goodsInfo/getTypeInfo', params)
    },
 
    addGoodsInfo: (params) => {
        return Post('http://localhost:8282/goodsInfo/addGoodsInfo', params)
    },
    handleDeleteGoodsInfo: (params) => {
        return Delete('http://localhost:8282/goodsInfo/deleteGoodsInfo', params)
    },
    updateGoodsInfo: (params) => {
        return Put('http://localhost:8282/goodsInfo/updateGoodsInfo', params)
    },


    findAllGoodsInfo: (params) => {
        return Get('http://localhost:8282/goodsInfo/findAllGoodsInfo', params)
    },
    updateSaleStatus: (params) => {
        return Put('http://localhost:8282/goodsInfo/updateSaleStatus',params)
    },

    /**
     * 文件上传
     */
    uploadFile: (params) => {
        return Post('http://localhost:8282/goodsInfo/upload', params);
    },

    /**
     * 订单
     */
    findAllOrder: (params) => {
        return Get('http://localhost:8282/order/findAllOrderInfo', params)
    },

    onShipments: (params) => {
        return Put('http://localhost:8282/order/shipments', params)
    },




}
