
//验证纯数字
let regNumber = /^[0-9]*$/;

//验证身份证
let IDRe18 = /^([1-6][1-9]|50)\d{4}(18|19|20)\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
let IDre15 = /^([1-6][1-9]|50)\d{4}\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}$/;

//验证邮箱
let regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;//验证邮箱的正则表达式

//验证手机号
let regPhone = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;


let PositiveInteger = /^[+]{0,1}(\d+)$/;

let priceReg = /^((0{1}\.\d{1,2})|([1-9]\d*\.{1}\d{1,2})|([1-9]+\d*))$/;





/**
 * 
 * 
 * 正数的正则表达式(包括0，小数保留两位)：^((0{1}\.\d{1,2})|([1-9]\d*\.{1}\d{1,2})|([1-9]+\d*)|0)$

   正数的正则表达式(不包括0，小数保留两位)：^((0{1}\.\d{1,2})|([1-9]\d*\.{1}\d{1,2})|([1-9]+\d*))$

   正整数的正则表达式(包括0)：^[+]{0,1}(\d+)$

   正整数的正则表达式(不包括0)：^[1-9]\d*$

  折扣（0-1）的小数的正则表达式(包括1)：^(0\.\d+|1)$

  折扣（0-1）的小数的正则表达式(不包括1)：^(0\.\d+)$

 */



export default {

    //验证折扣
    checkDiscount: (rule, value, callback) => {
        // value = value.replace(/\s*/g, "");//去除空格
        if (value > 1 || value <= 0) {
            callback(new Error("请输入合法的数字"));
        } else {
            callback()
        }
    },

    //验证价格
    checkPrice: (rule, value, callback) => {
        // value = value.replace(/\s*/g, "");//去除空格
        if (!priceReg.test(value)) {
            callback(new Error("请输入合法的数字"))
        } else {
            callback()
        }
    },
    //验证库存
    checkCount: (rule, value, callback) => {
        // value = value.replace(/\s*/g, "");//去除空格
        if (!PositiveInteger.test(value)) {
            callback(new Error("请输入合法的数字"))
        } else {
            callback()
        }

    },

    //验证用户昵称
    checkNickName: (rule, value, callback) => {
        // value = value.replace(/\s*/g, "");//去除空格
        if (!value) {
            return callback(new callback('请输入昵称'))
        }
        if (value.length < 0 || value.length > 10) {
            callback(new Error("昵称的长度范围是0-10，请输入合法的名称！！！"))
        } else if (regNumber.test(value)) {
            callback(new Error("输入的内容不能是纯数字！！！"))
        } else {
            callback()
        }
    },

    //验证用户名
    checkUserName: (rule, value, callback) => {
        // value = value.replace(/\s*/g, "");//去除空格
        if (!value) {
            return callback(new callback('请输入用户名'))
        }
        if (regNumber.test(value)) {
            callback(new Error("输入的内容不能是纯数字！！！"))
        } else {
            callback()
        }
    },


    //验证用户性别
    checkGender: (rule, value, callback) => {
        // value = value.replace(/\s*/g, "");//去除空格
        if (!value) {
            return callback(new callback('请输入性别'))
        }

        if (value == '男' || value == '女') {
            callback()
        } else {
            callback(new Error("请输入合法的性别"))
        }

    },


    //验证身份证
    checkIdCard: (rule, value, callback) => {
        // value = value.replace(/\s*/g, "");//去除空格
        if (!value) {
            return callback(new callback('请输入身份证'))
        }

        // 校验身份证：
        if (IDRe18.test(value) || IDre15.test(value)) {
            callback()
        } else {
            callback(new Error("身份证不合法！！！"))
        }

    },

    //验证邮箱
    checkEmail: (rule, value, callback) => {
        // value = value.replace(/\s*/g, "");//去除空格
        if (!value) {
            return callback(new callback('请输入邮箱'))
        }

        if (value.length < 0 || value.length > 15) {
            callback(new Error("邮箱的长度输入有误！！！，合法长度为0-15"))
        } else if (!regEmail.test(value)) {
            callback(new Error("邮箱格式不合法！！！"))
        } else {
            callback()
        }
    },

    //验证手机号
    checkPhone: (rule, value, callback) => {
        // value = value.replace(/\s*/g, "");//去除空格
        if (!value) {
            return callback(new callback('请输入箱手机号'))
        }

        if (!regPhone.test(value)) {
            callback(new Error("请输入正确的手机号！！！"))
        } else {
            callback()
        }
    },

    //验证输入的密码
    checkPwd: (rule, value, callback) => {
        // value = value.replace(/\s*/g, "");//去除空格
        if (!value) {
            return callback(new callback('请输入密码'))
        }

        if (regNumber.test(value)) {
            callback()
        } else {
            return callback(new callback('输入的密码是整数'))
        }
    }






}












