<template>
  <div>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="汽车编号" prop="car_id">
        <el-input v-model="ruleForm.car_id" disabled></el-input>
      </el-form-item>
      <el-form-item label="汽车类型" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>

      <el-form-item label="颜色" prop="color">
        <el-input v-model="ruleForm.color"></el-input>
      </el-form-item>
      <el-form-item label="种类" prop="type">
        <el-input v-model="ruleForm.type"></el-input>
      </el-form-item>
      <el-form-item label="租借押金（元/天）" prop="price">
        <el-input v-model="ruleForm.price"></el-input>
      </el-form-item>
      <el-form-item label="租借数量" prop="num">
        <el-input v-model="ruleForm.num"></el-input>
      </el-form-item>
      <el-form-item label="租借天数" prop="daynum">
        <el-input v-model="ruleForm.daynum"></el-input>
      </el-form-item>

      <el-form-item label="租车总价格" prop="total_price">
        <el-input v-model="ruleForm.total_price"></el-input>
      </el-form-item>


      <el-form-item label="图片" prop="imageUrl">
        <img :src="ruleForm.imageUrl" style="width:100px;height:130px">
      </el-form-item>



      <el-form-item>
        <el-button type="primary" @click="update('ruleForm')">修改</el-button>
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
          car_id:'',
          num:'',
          name: '',
          color: '',
          type: '',
          price: '',
          daynum: '',
          total_price: '',
          imageUrl:'',
          password: '',
          phone:''
        },
        rules: {
          car_id:[
            { required: true, message: '请输入用户编号', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '请输入姓名', trigger: 'blur' }
          ],
          daynum: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ],
          price:[
            { required: true, message: '请输入号码', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {

      update(formName) {
        const _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.put('/user/updateoneuser',this.ruleForm).then(function (resp) {
              if(resp.data.code==200){
                // alert("添加成功") 跳转的路由
                _this.$alert('《'+_this.ruleForm.name+'》修改成功', '消息', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.$router.push('/userlistshow')
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
      axios.get('/order/queryOneOrderItem/'+this.$route.query.id).then(function (resp) {
        console.log(resp)

        _this.ruleForm=resp.data.data.orderItem
      })

    }
  }
</script>
