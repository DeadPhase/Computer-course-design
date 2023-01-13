<template>
  <div>
    <h2 style="color: black;text-align: left;">汽车类型</h2>
    <hr>
<div style="float: right">
  <el-button type="primary" round @click="addCarType">增加汽车类型</el-button>
</div>
    <br>
    <br>


  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column prop="id" label="编号" width="180"/>
    <el-table-column prop="type" label="汽车类型" width="180"/>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button type="primary" icon="el-icon-edit" circle @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-popconfirm
          confirm-button-text='好的'
          cancel-button-text='不用了'
          icon="el-icon-info"
          icon-color="red"
          title="确定删除该汽车信息吗？"
          @confirm="handleDelete(scope.$index, scope.row)">
          <el-button type="danger" icon="el-icon-delete"  slot="reference"  circle>删除</el-button>
        </el-popconfirm>
      </template>


    </el-table-column>
  </el-table>


<!--    新增类型弹窗-->
    <el-dialog
      title="新增汽车类型"
      :visible.sync="adddialogVisible"
      width="20%"
      >
      <el-form style="width: 100%" :model="addForm" :rules="rules" label-width="100px"  ref="addForm" class="demo-ruleForm">


        <el-form-item label="汽车类型" prop="type">
          <el-input v-model="addForm.type"></el-input>
        </el-form-item>


      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="adddialogVisible = false">取 消</el-button>

    <el-button type="primary" @click="submitForm('addForm')">确 定</el-button>
  </span>
    </el-dialog>

    <!--    修改汽车类型-->
    <el-dialog
      title="修改汽车类型"
      :visible.sync="dialogVisible"
      width="20%"
    >
      <el-form :model="addForm" :rules="rules" label-width="80px"  ref="addForm">
        <el-form-item label="类型编号" prop="user_id">
          <el-input v-model="addForm.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="汽车类型" prop="type">
          <el-input v-model="addForm.type"></el-input>
        </el-form-item>

      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitEditForm('addForm')">确 定</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        adddialogVisible:false,
        dialogVisible:false,
        addForm:{
          id:'',
          type:'',
        },
        rules: {
          type: [
            { required: true, message: '请输入汽车类型名称', trigger: 'blur' },
          ]},

        tableData: []
      }
    },
    methods: {

      addCarType(){
        this.adddialogVisible=true;
      },
      handleEdit(index, row) {
        this.dialogVisible = true;//dialog对话窗口打开
        this.addForm = Object.assign({}, row);//将数据传入dialog页面
        this.addForm.index=index;//传递当前index

      },

      handleDelete(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        const _this=this
        axios.delete("/car/deletecartype/" + row.id).then(res => {
          console.log(res)
          if (res.data.code = 200) {
            _this.$alert('删除成功', '消息', {
              confirmButtonText: '确定',
              callback: action => {
                _this.showAllUsers();
              }
            });
          } else
            this.$message.error(res.data.message);
          this.showAllUsers();//重新渲染数据
        });
      },
      submitEditForm(formName) {
        const _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.put('/car/updatecartype',this.addForm).then(function (resp) {
              if(resp.data.code==200){
                _this.dialogVisible = false;//dialog对话窗口关闭
                _this.$alert(''+_this.addForm.type+'修改成功', '消息', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.showAllUsers();
                  }
                });
                _this.showAllUsers();
              }else{
                _this.$message.error(resp.data.data.errMessage);
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      submitForm(formName) {
        const _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.post('/car/addcartype',this.addForm).then(function (resp) {
              if(resp.data.code==200){
                _this.adddialogVisible = false;//dialog对话窗口关闭
                _this.$alert('《'+_this.addForm.type+'》添加成功', '消息', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.showAllUsers();
                  }
                });
                _this.showAllUsers();
              }else{
                _this.$message.error(resp.data.data.errMessage);
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      showAllUsers() {  //异步请求显示所有数据
        axios.get("/car/cartypelist").then(res => {
          console.log(res)
          this.tableData = res.data.data.cartypeList;
        });
      }

    },
    created() {
      this.showAllUsers();
    }

  }
</script>

<style scoped>

</style>
