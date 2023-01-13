<template>

  <div>
    <h3 style="color: black;text-align: left">新增优惠券信息</h3>
    <hr>
    <h3 style="color: blue;text-align: left">基本信息</h3>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-form-item label="优惠券名称" prop="name" style="width: 40%">
        <el-input v-model="ruleForm.name" placeholder="请输入优惠券名称"></el-input>
      </el-form-item>

      <el-form-item label="优惠折扣" prop="type" style="width: 30%">
        <el-input v-model="ruleForm.type" placeholder="请输入折扣"></el-input>
      </el-form-item>


      <el-form-item label="发放数量" prop="num" style="width: 40%">
        <el-input v-model="ruleForm.num" placeholder="请输入发放数量"></el-input>
      </el-form-item>


      <el-form-item label="使用说明" prop="instructions" style="width: 70%">
        <el-input type="textarea" v-model="ruleForm.instructions"></el-input>
      </el-form-item>

      <h3 style="color: blue;text-align: left">基本规则</h3>


<el-form-item label="每人限领"  prop="userType">
        <el-col :span="8">
          <el-form-item prop="receive" style="width: 50%">
            <el-input v-model="ruleForm.receive" placeholder="请输入领取次数"></el-input>
          </el-form-item>
<!--          <el-form-item  prop="userType" style="width: 50%">-->
<!--            <el-select v-model="ruleForm.userType" placeholder="请选择用户类型">-->
<!--              <el-option label="新用户" value="newUser"></el-option>-->
<!--              <el-option label="老用户" value="oldUser"></el-option>-->
<!--              <el-option label="VIP用户" value="vipUser"></el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->
        </el-col>

<!--        <el-col :span="11">-->
<!--          <el-form-item label="每人限领" prop="getNum" style="width: 50%">-->
<!--            <el-input v-model="ruleForm.getNum" placeholder="请输入领取次数"></el-input>-->
<!--          </el-form-item>-->
<!--        </el-col>-->


</el-form-item>


      <el-form-item label="有效期" required>
        <el-col :span="6">
          <el-form-item prop="startTime">
            <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择开始日期" v-model="ruleForm.startTime"
                            style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="6">
          <el-form-item prop="endTime">
            <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择结束日期" v-model="ruleForm.endTime"
                            style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>
<script>
  export default {
    data() {
      
      //验证优惠券名称
       const checkName =(rule,value,callback)=>{
        const regN = /^[0-9]*$/;
   
        if(value.length <0 || value.length >10){
          callback(new Error("优惠券的长度范围是0-6，请输入合法的名称！！！"))
        }else if(regN.test(value)){
            callback(new Error("输入的内容不能是纯数字！！！"))
        }else{
          callback()
        }
      }

            //验证优惠折扣力度
       const checkType =(rule,value,callback)=>{
        const regN = /^[0-9]*$/;
  
        if(value  < 0 || value >=1 ){
          callback(new Error("请输入合理的折扣，例如0.3  ！！！"))
        }else if(!regN.test(value)){
            callback(new Error("请输入合法的数据！！！"))
        }else{
          callback()
        }
      }

       //验证发方数量
       const checkNum =(rule,value,callback)=>{
        const regN = /^[0-9]*$/;
  
        if(value  < 0 ){
          callback(new Error("发放数量不能为负！！！"))
        }else if(!regN.test(value)){
            callback(new Error("请输入合法的数据！！！"))
        }else{
          callback()
        }
      }

            //验证领取数量
       const checkReceive =(rule,value,callback)=>{
        const regN = /^[0-9]*$/;
  
        if(value  < 0 ){
          callback(new Error("领取数量不能为负！！！"))
        }else if(!regN.test(value)){
            callback(new Error("请输入合法的数据！！！"))
        }else{
          callback()
        }
      }

      return {
        ruleForm: {
          name: '',
          startTime: '',
          endTime: '',
          num: '',
          type: '',
          userType:'',
          receive:'',
          instructions: ''
        },
        rules: {
          name: [
            {required: true, message: '请输入优惠券名称', trigger: 'blur'},
            {min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur'},
            {validator:checkName,trigger:'blur'}
          ],
          num: [
            {required: true, message: '请输入发放数量', trigger: 'blur'},
            {min: 1, max: 10000, message: '发放数量在1--10000', trigger: 'blur'},
            {validator:checkNum,trigger:'blur'}
          ],

          startTime: [
            { required: true, message: '请选择日期', trigger: 'change'}
          ],
          endTime: [
            { required: true, message: '请选择时间', trigger: 'change'}
          ],
          type: [
            {required: true, message: '请输入折扣力度', trigger: 'blur'},
            {validator:checkType,trigger:'blur'}
          ],
          receive: [
            {required: true, message: '请输入领取数量', trigger: 'blur'},
            {validator:checkReceive,trigger:'blur'}
          
          ],
          instructions: [
            {required: true, message: '请填写优惠券使用说明', trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        const _this =this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.post('/discount/adddiscountcoupon',this.ruleForm).then(resp => {
              if(resp.data.code==200){
                _this.$alert('《'+_this.ruleForm.name+'》添加成功', '消息', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.$router.push('/discount/discountcouponshow')


                  }
                });
              }
            });

          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>
