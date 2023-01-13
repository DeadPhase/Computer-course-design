<template>

  <div>
    <h3 style="color: black;text-align: left">修改优惠券信息</h3>
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
            <el-input v-model="ruleForm.startTime" placeholder="请输入活动开始时间"></el-input>
          </el-form-item>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="6">
          <el-form-item prop="endTime">
            <el-input v-model="ruleForm.endTime" placeholder="请输入活动结束时间"></el-input>
          </el-form-item>
        </el-col>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即更新</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>
<script>
  export default {
    data() {
      return {
        ruleForm: {
          name: '',
          date1: '',
          date2: '',
          num: '',
          type: '',
          userType:'',
          getNum:'',
          instructions: ''
        },
        rules: {
          name: [
            {required: true, message: '请输入活动名称', trigger: 'blur'},
            {min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur'}
          ],
          num: [
            {required: true, message: '请输入发放数量', trigger: 'blur'},
            {min: 1, max: 10000, message: '发放数量在1--10000', trigger: 'blur'}
          ],

          date1: [
            {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
          ],
          date2: [
            {type: 'date', required: true, message: '请选择时间', trigger: 'change'}
          ],
          type: [
            { required: true, message: '请选择优惠性质', trigger: 'blure'}
          ],  
          getNum: [
            {required: true, message: '请输入领取数量', trigger: 'blur'},
            {min: 1, max: 10000, message: '发放数量在1--10000', trigger: 'blur'}
          ],

          instructions: [
            {required: true, message: '请说明优惠券使用情况', trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.put('/discount/updatediscountcoupon',this.ruleForm).then(function (resp) {
              if(resp.data.code==200){
                // alert("添加成功") 跳转的路由
                _this.$alert('《'+_this.ruleForm.name+'》修改成功', '消息', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.$router.push('/discountcouponshow')
                  }
                });

              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }

    },
    created() {
      const _this = this

      axios.get('/discount/findonediscount/'+this.$route.query.id).then(function (resp) {
        console.log(resp)

        _this.ruleForm=resp.data.data.one_discount_coupon
      })

    }
  }
</script>
