<template>
  <div class="myMain">
    <div>
      <div class="table-title">管理员信息</div>
    </div>
    <div class="row">
      <div style="margin-top: 20px">
        <div style="border: 1px solid #c9c9c9">
          <div class="panel-heading">管理员息表</div>
          <div class="panel-body">
            <div style="width: 100%">
              <div class="nx-table-header" style="">
                <div style="float: left; margin-bottom: 10px">
                  <el-button
                    type="primary"
                    size="small"
                    round
                    @click="addNewAdmin()"
                    >添加</el-button
                  >
                </div>
                <div
                  style="float: left; margin-bottom: 10px; margin-left: 10px"
                >
                  <el-input
                    placeholder="请输入身份证"
                    suffix-icon="el-icon-search"
                    size="mini"
                    v-model.trim="idCard"
                    @keyup.enter.native="search()"
                  >
                  </el-input>
                </div>
              </div>
              <div>
                <el-table
                  :data="tableDatas"
                  :header-cell-style="{
                    color: '#000000',
                    fontSize: '16px',
                    backgroundColor: '#qua',
                  }"
                  border
                  style="width: 100%"
                >
                  <el-table-column
                    prop="uid"
                    v-if="false"
                    label="编号"
                    width="100"
                  >
                  </el-table-column>
                  <el-table-column prop="userName" label="姓名" width="100">
                  </el-table-column>
                  <el-table-column prop="nickName" label="昵称" width="100">
                  </el-table-column>
                  <el-table-column prop="gender" label="性别" width="100">
                  </el-table-column>
                  <el-table-column
                    prop="birthDate"
                    label="出生日期"
                    width="100"
                  >
                  </el-table-column>
                  <el-table-column prop="idCard" label="身份证" width="200">
                  </el-table-column>
                  <el-table-column prop="email" label="邮箱" width="200">
                  </el-table-column>
                  <el-table-column prop="phone" label="手机号" width="200">
                  </el-table-column>
                  <el-table-column
                    prop="registerTime"
                    label="注册时间"
                    width="200"
                  >
                  </el-table-column>
                  <el-table-column label="操作">
                    <template slot-scope="scope">
                      <el-button
                        type="primary"
                        @click="UpdateUserInfo(scope.$index, scope.row)"
                        icon="el-icon-edit"
                      >
                      </el-button>
                      <el-popconfirm
                        confirm-button-text="好的"
                        cancel-button-text="不用了"
                        icon="el-icon-info"
                        icon-color="red"
                        title="确定删除该吗？"
                        @confirm="handleDelete(scope.$index, scope.row)"
                      >
                        <el-button
                          type="danger"
                          icon="el-icon-delete"
                          slot="reference"
                        ></el-button>
                      </el-popconfirm>
                    </template>
                  </el-table-column>
                </el-table>

                <!--    ====================================增加新用户====================================-->
                <el-dialog
                  title="添加管理员"
                  :visible.sync="adddialogVisible"
                  width="30%"
                  :close-on-click-modal="false"
                >
                  <hr />
                  <el-form
                    style="width: 80%"
                    :model="UserForm"
                    :rules="rules"
                    label-width="100px"
                    ref="UserForm"
                    class="add-AdminForm"
                  >
                    <el-form-item
                      style="font-size: 20px"
                      label="姓名"
                      prop="userName"
                    >
                      <el-input
                        v-model="UserForm.userName"
                        placeholder="请输入姓名"
                      ></el-input>
                    </el-form-item>
                    <el-form-item label="昵称" prop="nickName">
                      <el-input
                        v-model="UserForm.nickName"
                        placeholder="请输入昵称"
                      ></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="gender">
                      <el-radio
                        style="margin-left: -170px"
                        v-model="UserForm.gender"
                        label="男"
                        >男</el-radio
                      >
                      <el-radio
                        style="margin-left: -20px"
                        v-model="UserForm.gender"
                        label="女"
                        >女</el-radio
                      >
                    </el-form-item>
                    <el-form-item label="生日" prop="birthDate">
                      <el-date-picker
                        style="width: 100%"
                        v-model="UserForm.birthDate"
                        type="date"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="选择日期"
                      >
                      </el-date-picker>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                      <el-input
                        v-model="UserForm.email"
                        placeholder="请输入邮箱"
                      ></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="phone">
                      <el-input
                        v-model="UserForm.phone"
                        placeholder="请输入手机号"
                      ></el-input>
                    </el-form-item>
                    <el-form-item label="身份证" prop="idCard">
                      <el-input
                        v-model="UserForm.idCard"
                        placeholder="请输入身份证"
                      ></el-input>
                    </el-form-item>
                  </el-form>
                  <hr />
                  <span slot="footer" class="dialog-footer">
                    <el-button @click="resetForm('UserForm')">重置</el-button>
                    <el-button @click="adddialogVisible = false"
                      >取 消</el-button
                    >
                    <el-button
                      type="primary"
                      round
                      @click="submitForm('UserForm')"
                      >确 定</el-button
                    >
                  </span>
                </el-dialog>

                <!--    ====================================修改用户信息====================================-->
                <el-dialog
                  title="修改用户信息"
                  :visible.sync="updateDialogVisible"
                  width="30%"
                  :close-on-click-modal="false"
                >
                  <el-form
                    style="width: 80%"
                    :model="UserForm"
                    :rules="rules"
                    label-width="100px"
                    ref="UserForm"
                    class="demo-ruleForm"
                  >
                    <el-form-item label="姓名" prop="userName">
                      <el-input
                        prefix-icon="el-icon-user"
                        class="letLine"
                        v-model="UserForm.userName"
                      ></el-input>
                    </el-form-item>
                    <el-form-item label="昵称" prop="nickName">
                      <el-input v-model="UserForm.nickName"></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="gender">
                      <el-input v-model="UserForm.gender"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                      <el-input v-model="UserForm.email"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="phone">
                      <el-input v-model="UserForm.phone"></el-input>
                    </el-form-item>
                    <el-form-item
                      label="身份证"
                      prop="idCard"
                      placeholder="请输入身份证"
                    >
                      <el-input
                        readonly:true
                        v-model="UserForm.idCard"
                      ></el-input>
                    </el-form-item>
                  </el-form>

                  <span slot="footer" class="dialog-footer">
                    <el-button @click="updateDialogVisible = false"
                      >取 消</el-button
                    >
                    <el-button
                      type="primary"
                      round
                      @click="submitUpdateUserForm('UserForm')"
                      >确 定</el-button
                    >
                  </span>
                </el-dialog>
              </div>
              <!--分页组件-->
              <div style="text-align: left; margin-top: 20px">
                <el-row>
                  <el-col :span="12">
                    <el-pagination
                      style="margin: 15px 0px"
                      background
                      layout="prev, pager, next, jumper, total, sizes"
                      :page-size="size"
                      :current-page="now"
                      :page-sizes="[2, 4, 6]"
                      @current-change="findPage"
                      @size-change="findSize"
                      :total="total"
                    >
                    </el-pagination>
                  </el-col>
                </el-row>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import rules from "@/api/rules.js";
export default {
  data() {
    return {
      total: 0,
      now: 1,
      size: 6,
      idCard: "",
      tableDatas: [],
      UserForm: {
        uid: "",
        userName: "",
        password: "",
        nickName: "",
        gender: "男",
        birthDate: "",
        idCard: "",
        email: "",
        phone: "",
      },
      adddialogVisible: false,
      updateDialogVisible: false,
      rules: {
        nickName: [
          {
            required: true,
            message: "请输入昵称",
            trigger: ["blur", "change"],
          },
          { validator: rules.checkNickName, trigger: ["change", "blur"] },
        ],
        userName: [
          {
            required: true,
            message: "请输入姓名",
            trigger: ["blur", "change"],
          },
          { validator: rules.checkUserName, trigger: ["blur", "change"] },
        ],
        gender: [
          {
            required: true,
            message: "请输入性别",
            trigger: ["blur", "change"],
          },
          { validator: rules.checkGender, trigger: ["blur", "change"] },
        ],

        birthDate: [
          {
            required: true,
            message: "请选择日期",
            trigger: ["blur", "change"],
          },
        ],
        idCard: [
          // {
          //   required: true,
          //   message: "请输入身份证号",
          //   trigger: ["blur", "change"],
          // },
          // { validator: rules.checkIdCard, trigger: "blur" },
        ],
        email: [
          {
            required: true,
            message: "请输入邮箱",
            trigger: ["blur", "change"],
          },
          { validator: rules.checkEmail, trigger: "blur" },
        ],
        phone: [
          // {
          //   required: true,
          //   message: "请输入电话号码",
          //   trigger: ["blur", "change"],
          // },
          // { validator: rules.checkPhone, trigger: "blur" },
          // {pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur"}
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: ["blur", "change"],
          },
          { validator: rules.checkPwd, trigger: ["blur", "change"] },
        ],
      },
    };
  },
  methods: {
    //搜索用户
    search() {
      const _this = this;
      console.error("搜索的信息" + this.idCard);
      if (this.idCard == null || this.idCard == "") {
        this.showAllUserInfo();
      } else {
        axios.get("/user/search/" + this.idCard).then(function (resp) {
          console.log(resp);
          if (resp.code == 200) {
            //将查询的信息赋值给当前的
            _this.tableDatas = resp.data.result.userList;
          } else {
            _this.$message.error(resp.data.errMessage);
          }
        });
      }
    },
    //编辑用户信息
    UpdateUserInfo(index, row) {
      this.updateDialogVisible = true;
      this.UserForm = Object.assign({}, row);
      this.UserForm.index = index;
    },
    submitUpdateUserForm(UserForm) {
      const _this = this;
      console.log('userform--start')
      console.log(this.UserForm)
      console.log('userform--end')
      this.$refs[UserForm].validate((valid) => {
        if (valid) {
          this.$axios
            .submitUpdateAdminForm(this.UserForm)
            .then(function (resp) {
              if (resp.code == 200) {
                _this.$alert(
                  "《" + _this.UserForm.userName + "》用户信息修改成功",
                  "消息",
                  {
                    confirmButtonText: "确定",
                    callback: (action) => {
                      _this.updateDialogVisible = false; //dialog对话窗口关闭
                      _this.showAllUserInfo();
                    },
                  }
                );
              } else {
                _this.$message.error(resp.data.errMessage);
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    //删除用户
    handleDelete(index, row) {
      const _this = this;
      const params = {
        IdCard: row.idCard,
      };
      this.$axios.handleAdminDelete(params).then((resp) => {
        if (resp.code == 200) {
          _this.$alert("删除用户成功", "消息", {
            confirmButtonText: "确定",
            callback: (action) => {
              _this.showAllUserInfo();
            },
          });
        } else {
          _this.$message.error(resp.data.errMessage);
        }
      });
    },

    //重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //添加用户窗口
    addNewAdmin() {
      this.adddialogVisible = true;
    },
    // //添加用户
    submitForm(UserForm) {
      const _this = this;
      this.$refs[UserForm].validate((valid) => {
        if (valid) {
          this.$axios.adminRegister(this.UserForm).then(function (resp) {
            if (resp.code == 200) {
              _this.adddialogVisible = false; //dialog对话窗口关闭
              _this.$alert(
                "《" + _this.UserForm.userName + "》添加成功",
                "消息",
                {
                  confirmButtonText: "确定",
                  callback: (action) => {
                    _this.showAllUserInfo();
                  },
                }
              );
              _this.showAllUserInfo();
            } else {
              _this.$message.error(resp.data.errMessage);
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    handleClick(row) {
      console.log(row);
    },

    // 第n页信息
    findPage(now_page) {
      //用来处理当前页的方法,page为当前页
      this.now = now_page;
      this.showAllUserInfo(now_page, this.size);
    },
    findSize(now_size) {
      //用来处理每页条数的方法,size为当前页条数
      this.size = now_size;
      this.showAllUserInfo(this.now, now_size);
    },

    // 展示所有的用户信息
    showAllUserInfo(currentPage, pageSize) {
      const _this = this;
      //异步请求显示所有数据
      currentPage = currentPage ? currentPage : this.now;
      pageSize = pageSize ? pageSize : this.size;
      const params = {
        page: currentPage,
        size: pageSize,
      };
      this.$axios.findAllAdmin(params).then((res) => {
        if (res.code === 200) {
          console.log(res.data.result.userList);
          this.tableDatas = res.data.result.userList;
          this.total = res.data.result.totals;
        } else {
          console.log(res);
          _this.$message.error(res.data.errMessage);
        }
      });
    },
  },
  created() {
    this.showAllUserInfo();
  },
};
</script>
<style >
.letLine::before {
  content: "";
  width: 1px;
  height: 12px;
  position: absolute;
  top: 12px;
  left: 30px;
  background: #a1aff9;
}

.el-form-item__label {
  font-size: 16px;
  font-weight: bold;
}
</style>

<style scoped="scoped">
@import "../../assets/css/inputbase.css";

hr {
  border: none;
  height: 1px;
  background-color: #e0e0e0;
}

.panel-body {
  padding: 10px;
  background: #ffffff;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-start;
}

.myMain {
  margin-left: 20px;
  margin-right: 20px;
}
</style>

