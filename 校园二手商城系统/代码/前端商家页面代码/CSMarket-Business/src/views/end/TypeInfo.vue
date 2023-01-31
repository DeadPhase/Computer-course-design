<template>
  <div class="myMain">
    <div>
      <div class="table-title">化妆品种类</div>
    </div>
    <div class="row">
      <div style="margin-top: 20px">
        <div style="border: 1px solid #c9c9c9">
          <div class="panel-heading">化妆品信息表</div>
          <div class="panel-body">
            <div style="width: 100%">
              <div class="nx-table-header" style="">
                <div style="float: left; margin-bottom: 10px">
                  <el-button
                    type="primary"
                    size="small"
                    round
                    @click="addNewTypeInfo()"
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
                  :header-cell-style="{
                    color: '#000000',
                    fontSize: '16px',
                    backgroundColor: '#qua',
                  }"
                  :data="tableDatas"
                  border
                  style="width: 99.9%"
                >
                  <el-table-column prop="id" v-if="false" label="编号">
                  </el-table-column>
                  <el-table-column prop="name" label="类别名称">
                  </el-table-column>
                  <el-table-column prop="description" label="类别介绍">
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
                        title="确定删除吗？"
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

                <!--    ====================================增加化妆品种类====================================-->
                <el-dialog
                  title="添加化妆品"
                  :visible.sync="adddialogVisible"
                  width="30%"
                  @close="closeDialog"
                >
                  <hr />
                  <el-form
                    style="width: 80%"
                    :model="TypeInfoForm"
                    :rules="rules"
                    :validate-on-rule-change="false"
                    label-width="100px"
                    ref="TypeInfoForm"
                    class="add-AdminForm"
                  >
                    <el-form-item
                      style="font-size: 20px"
                      label="类别名称"
                      prop="name"
                    >
                      <el-input
                        v-model="TypeInfoForm.name"
                        placeholder="请输入类别名称"
                        maxlength="10"
                        show-word-limit
                      ></el-input>
                    </el-form-item>
                    <el-form-item label="类别介绍" prop="description">
                      <el-input
                        v-model="TypeInfoForm.description"
                        placeholder="请输入类别介绍"
                        maxlength="255"
                        show-word-limit
                      ></el-input>
                    </el-form-item>
                  </el-form>
                  <hr />
                  <span slot="footer" class="dialog-footer">
                    <el-button @click="resetForm('TypeInfoForm')"
                      >重置</el-button
                    >
                    <el-button @click="adddialogVisible = false"
                      >取 消</el-button
                    >
                    <el-button
                      type="primary"
                      round
                      @click="submitForm('TypeInfoForm')"
                      >确 定</el-button
                    >
                  </span>
                </el-dialog>

                <!--    ====================================修改用户信息====================================-->
                <el-dialog
                  title="修改信息"
                  :visible.sync="updateDialogVisible"
                  width="30%"
                  :close-on-click-modal="false"
                >
                  <el-form
                    style="width: 80%"
                    :model="TypeInfoForm"
                    :rules="rules"
                    label-width="100px"
                    ref="TypeInfoForm"
                    class="demo-ruleForm"
                  >
                    <el-form-item label="类别名称" prop="name">
                      <el-input
                        v-model="TypeInfoForm.name"
                        maxlength="10"
                        show-word-limit
                      ></el-input>
                    </el-form-item>
                    <el-form-item label="类别介绍" prop="description">
                      <el-input
                        v-model="TypeInfoForm.description"
                        maxlength="255"
                        show-word-limit
                      ></el-input>
                    </el-form-item>
                  </el-form>

                  <span slot="footer" class="dialog-footer">
                    <el-button
                      type="primary"
                      round
                      @click="updateDialogVisible = false"
                      >取 消</el-button
                    >
                    <el-button
                      type="primary"
                      round
                      @click="updateTypeInfo('TypeInfoForm')"
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
      TypeInfoForm: {
        id: "",
        name: "",
        description: "",
      },
      adddialogVisible: false,
      updateDialogVisible: false,
      rules: {
        name: [
          {
            required: true,
            message: "请输入类别名称",
            trigger: ["blur", "change"],
          },
          //   { validator: rules.checkNickName, trigger: ["change", "blur"] },
        ],
        description: [
          {
            required: true,
            message: "请输入类别备注",
            trigger: ["blur", "change"],
          },
          //   { validator: rules.checkUserName, trigger: ["blur", "change"] },
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
    //修改化妆品种类信息
    UpdateUserInfo(index, row) {
      this.updateDialogVisible = true;
      this.TypeInfoForm = Object.assign({}, row);
      this.TypeInfoForm.index = index;
    },
    updateTypeInfo(TypeInfoForm) {
      const _this = this;
      this.$refs[TypeInfoForm].validate((valid) => {
        if (valid) {
          this.$axios
            .submitUpdateUserForm(this.TypeInfoForm)
            .then(function (resp) {
              if (resp.code == 200) {
                _this.$alert(
                  "《" + _this.TypeInfoForm.name + "》信息修改成功",
                  "消息",
                  {
                    confirmButtonText: "确定",
                    callback: (action) => {
                      _this.updateDialogVisible = false;
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
    //删除化妆品种类
    handleDelete(index, row) {
      const _this = this;
      const params = {
        id: row.id,
      };
      this.$axios.handleDeleteTypeInfo(params).then((resp) => {
        if (resp.code == 200) {
          _this.$alert("删除成功", "消息", {
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
    //添加化妆品种类窗口
    addNewTypeInfo() {
      this.adddialogVisible = true;
    },
    closeDialog() {
      this.$refs.TypeInfoForm.resetFields();
    },

    //添加化妆品信息
    submitForm(TypeInfoForm) {
      const _this = this;
      this.$refs[TypeInfoForm].validate((valid) => {
        if (valid) {
          this.$axios.addTypeInfo(this.TypeInfoForm).then(function (resp) {
            if (resp.code == 200) {
              _this.$alert(
                "《" + _this.TypeInfoForm.name + "》添加成功",
                "消息",
                {
                  confirmButtonText: "确定",
                  callback: (action) => {
                    _this.adddialogVisible = false; //dialog对话窗口关闭
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

    // 展示所有的化妆品种类信息
    showAllUserInfo(currentPage, pageSize) {
      const _this = this;
      //异步请求显示所有数据
      currentPage = currentPage ? currentPage : this.now;
      pageSize = pageSize ? pageSize : this.size;
      const params = {
        page: currentPage,
        size: pageSize,
      };
      this.$axios.findAllTypeInfo(params).then((res) => {
        if (res.code === 200) {
          console.log(res.data.result.typeInfoList);
          this.tableDatas = res.data.result.typeInfoList;
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

.panel-body {
  padding: 10px;
  background: #ffffff;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-start;
}

hr {
  border: none;
  height: 1px;
  background-color: #e0e0e0;
}

.myMain {
  margin-left: 20px;
  margin-right: 20px;
}
</style>

