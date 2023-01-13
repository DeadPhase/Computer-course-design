
<template>
  <div>
    <h1 style="color: black;text-align: left">汽车信息管理</h1>
    <hr>
    <div style="width: 100%;height: 50px" >
      <div style="float: left">
        <form>
          汽车名称：<input type="text" style="width: 100px;height: 20px" v-model="loginForm.name" placeholder="名称"   ></input>&nbsp;&nbsp;
          汽车种类：<input type="text" style="width: 100px;height: 20px" v-model="loginForm.type" placeholder="种类"></input>
          汽车颜色：<input type="text" style="width: 100px;height: 20px" v-model="loginForm.color" placeholder="颜色"></input>
          <el-button icon="el-icon-search" @click="research" circle></el-button>
        </form>
      </div>

      <div style="float: right">
        <el-button type="primary" round @click="addCar">增加汽车</el-button>
        <el-button type="primary" round @click="findAllCar">显示全部汽车</el-button>
      </div>
    </div>

    <div id="list">
      <el-table  :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"  style="width: 100%" height="500">
        <el-table-column label="编号" width="50" prop="car_id"/>
        <el-table-column label="车牌号" width="100" prop="license_plate_car"/>
        <el-table-column label="汽车名称" width="130" prop="name"/>
        <el-table-column label="颜色" width="100" prop="color"/>
        <el-table-column label="押金($/天)" width="100" prop="guarantee_money"/>
        <el-table-column label="车辆图片" prop="">
          <template width="100" slot-scope="scope">
            <el-popover
              placement="top-start"
              title=""
              trigger="click">
              <a :href="scope.row.imageUrl" target="_blank" title="查看最大化图片">
                <img :src="scope.row.imageUrl" style="width:300px;height:300px">
              </a>
              <img slot="reference" :src="scope.row.imageUrl" style="width:100px;height:100px;cursor:pointer">
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="类型" width="100" prop="type"/>
        <el-table-column label="上架时间" width="200" prop="date"/>
        <el-table-column prop="car_id" label="操作" width="200">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.status" :active-value=true active-text="上架" :inactive-value=false active-color="#13ce66" inactive-color="#ff4949"
                       @change="switchStatus(scope.row.status,scope.row.car_id)">
            </el-switch>
          </template>
        </el-table-column>

        <el-table-column prop="car_num" label="库存数量"></el-table-column>

        <el-table-column align="right" width="300">
          <template slot="header" slot-scope="scope">
            <el-input v-model="search" size="mini" style="width: auto" placeholder="输入姓名关键字搜索"/>
          </template>

          <template slot-scope="scope">

            <el-button type="primary" @click="lookremark(scope.$index,scope.row)" icon="el-icon-edit" circle>备注</el-button>

            <el-button type="primary" @click="editCar(scope.$index,scope.row)" icon="el-icon-edit" circle>编辑</el-button>

            &nbsp;&nbsp;
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

      <!--    ==================================增加汽车==========================================-->
      <el-dialog
        title="添加汽车"
        :visible.sync="adddialogVisible"
        width="30%"
        :close-on-click-modal="false">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" style="width: 60%" label-width="100px" class="demo-ruleForm">
          <el-form-item label="车牌号" prop="license_plate_car">
            <el-input v-model="ruleForm.license_plate_car" placeholder="请输入车牌号"/>
          </el-form-item>
          <el-form-item label="汽车名称" prop="name">
            <el-input v-model="ruleForm.name" placeholder="请输入汽车名称"/>
          </el-form-item>
          <el-form-item label="汽车颜色" prop="color">
            <el-input v-model="ruleForm.color" placeholder="请输入汽车颜色"/>
          </el-form-item>
          <el-form-item label="汽车押金" prop="guarantee_money">
            <el-input v-model="ruleForm.guarantee_money" placeholder="请输入汽车押金"/>
          </el-form-item>
            <el-form-item label="汽车数量" prop="car_num">
            <el-input v-model="ruleForm.car_num" placeholder="请输入汽车数量"/>
          </el-form-item>

          <el-form-item  label="汽车类型" prop="type" >
          <el-select v-model="ruleForm.type" placeholder="请选择汽车类型">
            <el-option v-for="item in options" :key="item.id"  :value="item.type">
              {{item.type}}
            </el-option>
          </el-select>
        </el-form-item>

          <el-form-item label="上传图片"  prop="imageUrl">
            <el-upload
              class="avatar-uploader"
              action="http://localhost:8282/upload/img"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl!=''" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon">上传图片</i>
            </el-upload>
          </el-form-item>

          <el-form-item label="备注信息" prop="remark">
            <el-input   :rows="3" placeholder="请输入内容"   type="textarea" v-model="ruleForm.remark"/>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
         <el-button @click="resetForm('ruleForm')">重置</el-button>
    <el-button @click="adddialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
  </span>
      </el-dialog>

      <!--   ======================================= 汽车备注信息==========================-->
      <el-dialog
        title="汽车备注信息"
        :visible.sync="lookdialogVisible"
        width="30%"
        :close-on-click-modal="false">
        <el-form style="width: 60%" :model="ruleForm" ref="ruleForm" label-width="100px">
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" v-model="ruleForm.remark"></el-input>
          </el-form-item>
        </el-form>
    <el-button type="primary"  @click="lookdialogVisible = false">好的</el-button>
      </el-dialog>
      <!--   ======================================= 修改汽车信息==========================-->
      <el-dialog
        title="修改汽车信息"
        :visible.sync="dialogVisible"
        width="30%"
        :close-on-click-modal="false">
        <el-form style="width: 60%" :model="editForm" :rules="rules" ref="editForm" label-width="100px">
          <el-form-item label="汽车编号" prop="car_id">
            <el-input v-model="editForm.car_id" disabled></el-input>
          </el-form-item>
          <el-form-item label="车牌号" prop="license_plate_car">
            <el-input v-model="editForm.license_plate_car"></el-input>
          </el-form-item>
          <el-form-item label="汽车名称" prop="name">
            <el-input v-model="editForm.name"></el-input>
          </el-form-item>
          <el-form-item label="颜色" prop="color">
            <el-input v-model="editForm.color"></el-input>
          </el-form-item>
          <el-form-item label="押金($/天)" prop="guarantee_money">
            <el-input v-model="editForm.guarantee_money"></el-input>
          </el-form-item>
          <el-form-item label="种类" prop="type">
            <el-input v-model="editForm.type"></el-input>
          </el-form-item>

          <el-form-item label="图片" prop="imageUrl">
            <img :src="editForm.imageUrl" style="width:100px;height:130px">
          </el-form-item>

        </el-form>

        <span slot="footer" class="dialog-footer">

    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="update('editForm')">确 定</el-button>
  </span>
      </el-dialog>

      <!--分页组件-->
      <el-row>
        <el-col :span="12" offset="12">
          <el-pagination
            style="margin: 15px 0px"
            background
            layout="prev, pager, next, jumper, total, sizes"
            :page-size="size"
            :current-page="now"
            :page-sizes="[2,4,6,8,10,12]"
            @current-change="findPage"
            @size-change="findSize"
            :total="total">
          </el-pagination>
        </el-col>
      </el-row>


    </div>

  </div>
</template>

<script>
  export default {
    name: "list",
    
    data() {
     //表单验证方法

     //验证车牌号
        const checkLicense=(rule,value,callback)=>{
        const regN = /^[0-9]*$/;
   
        if(value.length <0 || value.length >8){
          callback(new Error("车牌号的长度是0-8，请输入合法的名称！！！"))
        }else if(regN.test(value)){
            callback(new Error("输入的内容不能是纯数字！！！"))
        }else{
          callback()
        }
      }

     //验证汽车名称
      const checkName =(rule,value,callback)=>{
        const regN = /^[0-9]*$/;
   
        if(value.length <0 || value.length >10){
          callback(new Error("汽车的名称长度范围是0-10，请输入合法的名称！！！"))
        }else if(regN.test(value)){
            callback(new Error("输入的内容不能是纯数字！！！"))
        }else{
          callback()
        }
      }

      //验证汽车颜色
       const checkColor =(rule,value,callback)=>{
        const regN = /^[0-9]*$/;
   
        if(value.length <0 || value.length >6){
          callback(new Error("汽车的颜色长度范围是0-6，请输入合法的名称！！！"))
        }else if(regN.test(value)){
            callback(new Error("输入的内容不能是纯数字！！！"))
        }else{
          callback()
        }
      }

      //验证汽车价格
       const checkPrice =(rule,value,callback)=>{
        const regN = /^[0-9]*$/;
   
        if(value  < 0 ){
          callback(new Error("押金不能为负！！！"))
        }else if(!regN.test(value)){
            callback(new Error("请输入合法的数据！！！"))
        }else{
          callback()
        }
      }



      return {
        imageUrl:'',
        final_fileName:'',
        options: [], //列表数据


        isUploadShowHide:true,
        lookdialogVisible:false,
        dialogVisible:false,
        adddialogVisible:false,//增加用户弹窗
        loginForm:{
          name:'',
          color:'',
          type:'',
        },
        editForm: {
          car_id:"",
          name: '',
          car_num:'',
          color: '',
          imageUrl: '',
          guarantee_money: '',
          license_plate_car:'',
          type:0,
          date:'',
          remark:'',
          status:1,

        },

        ruleForm: {
          license_plate_car:'',
          name:'',
          color: '',
          guarantee_money: '',
          imageUrl: '',
          remark:'',
        },
        tableData: [],
        show: false,
        show2: false,
        search: '',
        form: {
          id: '',
          name: '',
          sex: '男',
          date: '',
          address: ''
        },
        rules: {
          name: [
            { required: true, message: '请输入汽车类型名称', trigger: 'blur' },
            { min: 0, max: 12, message: '长度在 0 到 12 个字符', trigger: 'blur' },
            {validator:checkName,trigger:'blur'}
          ],
          color: [
            { required: true, message: '请输入汽车颜色', trigger: 'blur' },
            {validator:checkColor,trigger:'blur'}
          ],
          license_plate_car: [
            { required: true, message: '请输入车牌号', trigger: 'blur' },
            {validator:checkLicense,trigger:'blur'}
          ],
          date: [
            { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
          ],
          guarantee_money:[
            { required: true, message: '请输入租车押金', trigger: 'blur' },
            {validator:checkPrice,trigger:'blur'}
          ],
          price:[
            { required: true, message: '请输入租车价格', trigger: 'blur' },
          ],
          type:[
            { required: true, message: '请输入汽车种类', trigger: 'blur' },
          ],
          imageUrl: [
            {required: true, message: '请上传图片', trigger: 'blur'},
          ],


        },
        total: 0,
        now: 1,
        size: 8
      }
    },


    methods: {

      //图片回显
      handleAvatarSuccess(res, file) {
        console.log(res)
        this.imageUrl = res.data.final_fileName
        sessionStorage.setItem("imgUrl",this.imageUrl)
      },
      //图片上传大小限制
      beforeAvatarUpload(file) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isLt2M;
      },

      //提交表单
      submitForm() {
        let _this = this;
        _this.ruleForm.imageUrl =sessionStorage.getItem("imgUrl")
        axios.post('/car/addcar',this.ruleForm).then(resp => {
          if(resp.data.code==200){
            _this.$alert('《'+_this.ruleForm.name+'》添加成功', '消息', {
              confirmButtonText: '确定',
              callback: action => {
                _this.adddialogVisible=false
                _this.showAllUsers();
              }
            });
          }
        });
      },


      findPage(now_page) {//用来处理当前页的方法,page为当前页
        this.now = now_page;
        this.showAllUsers(now_page, this.size);
      },
      findSize(now_size) {//用来处理每页条数的方法,size为当前页条数
        this.size = now_size;
        this.showAllUsers(this.now, now_size);
      },
      //切换状态
      switchStatus:function(status,car_id){

        if(status == true){
          status = 1
        }else{
          status =0
        }
        //方法 如果status为true,此时status=1.需要修改该商品的状态从0---》1
        axios.put('/car/updatecarstatus/'+car_id+"/"+status).then(function (resp) {
          if(resp.data.code==200){
            _this.showAllUsers();
          }
        })

      },


      handleChange (file, fileList) {
        this.ruleForm.imageUrl = URL.createObjectURL(file.raw);
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.ruleForm.imageUrl='';
      },

      beforeUpload(file) {
        return true;
      },


      lookremark(index,row){
        this.lookdialogVisible = true;//dialog对话窗口打开
        this.ruleForm = Object.assign({}, row);//将数据传入dialog页面
        this.ruleForm.index=index;//传递当前index
      },

      editCar(index, row) {
        this.dialogVisible = true;//dialog对话窗口打开
        this.editForm = Object.assign({}, row);//将数据传入dialog页面
        this.editForm.index=index;//传递当前index

      },
      update(formName) {
        const _this = this;
        this.editForm.status=0
        console.log(this.editForm)
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.put('/car/editCar',this.editForm).then(function (resp) {
              if(resp.data.code==200){
                _this.$alert('修改成功', '消息', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.dialogVisible = false;//dialog对话窗口关闭
                    _this.showAllUsers();
                  }
                });

              }
            })
          }
        });
      },

      research(){
        axios.post('/car/findsomecar',this.loginForm).then(res => {

          console.log(res)
          this.tableData = res.data.data.result.cars;
          this.total = res.data.data.result.totals;
        });
      },


      addCar(){
        this.adddialogVisible = true;//dialog对话窗口打开
      },




      handleDelete(index, row) {
        const _this =this
        axios.delete("/car/deletecarlogical/" + row.car_id).then(resp => {

          if(resp.data.code==200){
            _this.$alert('删除成功', '消息', {
              confirmButtonText: '确定',
              callback: action => {
                _this.showAllUsers();
              }
            });
          }

        });
      },

      findAllCar(){
        this.showAllUsers();
      },

      //选择下拉框
      getOptions(){
        axios.get("/car/cartypelist").then(res => {
          console.log(res)
          this.options = res.data.data.cartypeList;
        });
      },


      showAllUsers(currentPage, pageSize) {  //异步请求显示所有数据
        currentPage = currentPage ? currentPage : this.now;
        pageSize = pageSize ? pageSize : this.size;
        axios.get("/car/findAll/" + currentPage + "/" + pageSize).then(res => {
          console.log(res)
          this.tableData = res.data.data.result.cars;
          this.tableData.map(function (val) {
            if(val.status === 0){
              //0代表上下架
              val.status = false
            }else if(val.status === 1){
              //1代表上架
              val.status = true
            }
          })

          this.total = res.data.data.result.totals;
        });
      }
    },
    created() {
      this.showAllUsers();
      this.getOptions()
    }
  }
</script>

<style scoped>
  input[type="file"] {
    display: none;
  }

  .transition-box {
    margin-bottom: 10px;
    width: 100%;
    height: 500px;
    border-radius: 4px;
    background-color: #ffffff;
    text-align: left;
    color: #fff;
    padding: 0px 10px;
    box-sizing: border-box;
    margin-right: 20px;

  }
  .switchStyle .el-switch__label {
    position: absolute;
    display: none;
    color: #fff;
  }
  .switchStyle .el-switch__label--left {
    z-index: 9;
    left: 6px;
  }
  .switchStyle .el-switch__label--right {
    z-index: 9;
    left: -14px;
  }
  .switchStyle .el-switch__label.is-active {
    display: block;
  }
  .switchStyle.el-switch .el-switch__core,
  .el-switch .el-switch__label {
    width: 50px !important;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>














