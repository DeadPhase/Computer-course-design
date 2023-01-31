<template>
  <div class="myMain">
    <div>
      <div class="table-title">商品信息</div>
    </div>
    <div class="row">
      <div style="margin-top: 20px">
        <div style="border: 1px solid #c9c9c9">
          <div class="panel-heading">商品信息表</div>
          <div class="panel-body">
            <div style="width: 100%">
              <div class="nx-table-header" style="">
                <div style="float: left; margin-bottom: 10px">
                  <el-button
                    type="primary"
                    size="small"
                    round
                    @click="addNewGoods()"
                    >添加</el-button
                  >
                </div>
                <div
                  style="float: left; margin-bottom: 10px; margin-left: 10px"
                >
                  <el-input
                    placeholder="请输入商品信息"
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
                  border
                  style="width: 100%"
                  :header-cell-style="{
                    color: '#000000',
                    fontSize: '16px',
                    backgroundColor: '#qua',
                  }"
                >
                  <el-table-column
                    prop="id"
                    v-if="false"
                    label="编号"
                    width="100"
                  >
                  </el-table-column>
                  <el-table-column prop="name" label="名称"> </el-table-column>
                  <el-table-column prop="description" label="描述">
                  </el-table-column>
                  <el-table-column prop="type" label="所属类别">
                  </el-table-column>
                  <el-table-column prop="price" label="价格" width="100px">
                  </el-table-column>
                  <el-table-column prop="discount" label="折扣" width="100px">
                  </el-table-column>
                  <el-table-column prop="count" label="库存" width="100px">
                  </el-table-column>
                  <el-table-column prop="sales" label="销量" width="100px">
                  </el-table-column>
                  <el-table-column label="点赞数" width="100px">
                  </el-table-column>
                  <el-table-column prop="id" label="上架" width="100px">
                    <template slot-scope="scope">
                      <el-switch
                        v-model="scope.row.isSale"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        active-text="是"
                        @change="switchSale(scope.row.isSale, scope.row.id)"
                      >
                      </el-switch>
                    </template>
                  </el-table-column>
                  <el-table-column label="商品图片">
                    <template slot-scope="scopes">
                      <button
                        class="btn btn-primary btn-xs"
                        @click="showImage(scopes.$index, scopes.row)"
                      >
                        预览
                      </button>
                    </template>
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
                        title="确定删除该商品吗？"
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

                <el-dialog
                  title="文件预览"
                  :visible.sync="dialogVisibleImage"
                  width="30%"
                  :before-close="handleClose"
                >
                  <div
                    id="view-img"
                    class="modal-body"
                    style="text-align: center"
                  ></div>
                </el-dialog>

                <!--    ====================================增加商品====================================-->
                <el-dialog
                  title="请填写商品信息"
                  :visible.sync="adddialogVisible"
                  width="30%"
                  :close-on-click-modal="false"
                >
                  <el-form
                    style="width: 80%"
                    :model="GoodsInfoForm"
                    :rules="rules"
                    label-width="100px"
                    ref="GoodsInfoForm"
                    class="demo-ruleForm"
                  >
                    <el-form-item label="名称" prop="name">
                      <el-input
                        maxlength="10"
                        show-word-limit
                        v-model="GoodsInfoForm.name"
                        placeholder="请输入名称"
                      ></el-input>
                    </el-form-item>
                    <el-form-item label="描述" prop="description">
                      <el-input
                        maxlength="255"
                        show-word-limit
                        v-model="GoodsInfoForm.description"
                        placeholder="请输入商品描述"
                      ></el-input>
                    </el-form-item>

                    <el-form-item label="所属类别" prop="type">
                      <el-select
                        v-model="GoodsInfoForm.type"
                        placeholder="请选择"
                        style="width: 100%"
                      >
                        <el-option
                          v-for="item in options"
                          :key="item.id"
                          :value="item.name"
                        >
                          {{ item.name }}
                        </el-option>
                      </el-select>
                    </el-form-item>

                    <el-form-item label="价格" prop="price">
                      <el-input
                        type="number"
                        v-model="GoodsInfoForm.price"
                        placeholder="请输入商品价格"
                      ></el-input>
                    </el-form-item>
                    <el-form-item label="折扣" prop="discount">
                      <el-input
                        v-model="GoodsInfoForm.discount"
                        placeholder="请输入不大于1的整数，例如：0.6"
                      ></el-input>
                    </el-form-item>
                    <el-form-item label="库存" prop="count">
                      <el-input
                        v-model="GoodsInfoForm.count"
                        type="number"
                        placeholder="请输入商品库存"
                      ></el-input>
                    </el-form-item>

                    <el-form-item label="是否上架" prop="isSale">
                      <el-radio
                        style="margin-left: -170px"
                        v-model="GoodsInfoForm.isSale"
                        label="是"
                        >是</el-radio
                      >
                      <el-radio
                        style="margin-left: -20px"
                        v-model="GoodsInfoForm.isSale"
                        label="否"
                        >否</el-radio
                      >
                    </el-form-item>
                    <el-form-item label="上传文件">
                      <input
                        @change="upload"
                        @click="clearFile"
                        multiple="multiple"
                        type="file"
                        ref="file"
                      />
                    </el-form-item>
                  </el-form>

                  <span slot="footer" class="dialog-footer">
                    <el-button @click="resetForm('GoodsInfoForm')"
                      >重置</el-button
                    >
                    <el-button @click="adddialogVisible = false"
                      >取 消</el-button
                    >
                    <el-button
                      type="primary"
                      round
                      @click="submitForm('GoodsInfoForm')"
                      >确 定</el-button
                    >
                  </span>
                </el-dialog>

                <!--    ====================================修改用户信息====================================-->
                <el-dialog
                  title="编辑商品信息"
                  :visible.sync="updateDialogVisible"
                  width="30%"
                  :close-on-click-modal="false"
                >
                  <el-form
                    style="width: 80%"
                    :model="GoodsInfoForm"
                    :rules="rules"
                    label-width="100px"
                    ref="GoodsInfoForm"
                    class="demo-ruleForm"
                  >
                    <el-form-item label="名称" prop="name">
                      <el-input v-model="GoodsInfoForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="描述" prop="description">
                      <el-input v-model="GoodsInfoForm.description"></el-input>
                    </el-form-item>
                    <el-form-item label="所属类别" prop="type">
                      <el-select
                        v-model="GoodsInfoForm.type"
                        placeholder="请选择"
                        style="width: 100%"
                      >
                        <el-option
                          v-for="item in options"
                          :key="item.id"
                          :value="item.name"
                        >
                          {{ item.name }}
                        </el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="价格" prop="price">
                      <el-input v-model="GoodsInfoForm.price"></el-input>
                    </el-form-item>
                    <el-form-item label="折扣" prop="discount">
                      <el-input v-model="GoodsInfoForm.discount"></el-input>
                    </el-form-item>
                    <el-form-item label="库存" prop="count">
                      <el-input
                        readonly:true
                        v-model="GoodsInfoForm.count"
                      ></el-input>
                    </el-form-item>
                    <el-form-item label="上传文件" >
                      <input
                        @change="upload"
                        @click="clearFile"
                        multiple="multiple"
                        type="file"
                        ref="file"
                      />
                    </el-form-item>
                  </el-form>

                  <span slot="footer" class="dialog-footer">
                    <el-button @click="updateDialogVisible = false"
                      >取 消</el-button
                    >
                    <el-button
                      type="primary"
                      round
                      @click="submitUpdateGoodsInfoForm('GoodsInfoForm')"
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
import $ from "jquery";
export default {
  data() {
    return {
      options: [], //下拉框
      total: 0,
      now: 1,
      size: 6,
      idCard: "",
      tableDatas: [],
      GoodsInfoForm: {
        isSale: "是",
        id: "null",
      },
      adddialogVisible: false,
      updateDialogVisible: false,
      dialogVisibleImage: false,
      rules: {
        name: [
          {
            required: true,
            message: "请输入名称",
            trigger: ["change", "blur"],
          },
          { validator: rules.checkNickName, trigger: ["change", "blur"] },
        ],
        type: [
          {
            required: true,
            message: "请选择商品类型",
            trigger: ["change", "blur"],
          },
        ],
        price: [
          {
            required: true,
            message: "请输入商品价格",
            trigger: ["change", "blur"],
          },
        ],
        discount: [
          { validator: rules.checkDiscount, trigger: ["change", "blur"] },
        ],

        count: [
          {
            required: true,
            message: "请输入库存数量",
            trigger: ["change", "blur"],
          },
          { validator: rules.checkCount, trigger: ["change", "blur"] },
        ],
      },
    };
  },
  methods: {
    handleClose(done) {
      this.dialogVisibleImage = false;
    },
    cancel: function () {},

    openMethods() {
      //获取元素
    },
    //文件显示
    showImage: function (index, row) {
      console.log(row.fileName);
      const myimgUrl = row.fileName;
      this.dialogVisibleImage = true;
      this.$nextTick(function () {
        //获取元素
        $("#view-img").empty();
        $("#view-img").append(
          $(
            `<img  src="${myimgUrl}" style="width: 400px; margin-bottom: 5px" alt="">`
          )
        );
      });
    },
    //文件上传
    upload: function () {
      let files = this.$refs.file.files;
      const _this = this;
      for (let i = 0; i < files.length; i++) {
        let formData = new FormData();
        formData.append("file", files[i]);
        this.$axios.uploadFile(formData).then((res) => {
          if (res.code == 200) {
            _this.$message("文件上传成功");
            console.log(res.data.fileName);
            this.GoodsInfoForm.fileName = res.data.fileName;
            console.log(this.GoodsInfoForm.fileName);
          } else {
            _this.$message("error", res.data.msg);
          }
        });
      }
    },
    clearFile: function () {
      this.GoodsInfoForm.fileName = "";
    },
    //搜索用户
    search() {
      const _this = this;
      console.error("搜索的信息" + this.idCard);
      if (this.idCard == null || this.idCard == "") {
        this.showAllUserInfo();
      } else {
        axios.get("/user/search/" + this.idCard).then(function (resp) {
          console.log(resp);
          if (resp.data.code == 200) {
            //将查询的信息赋值给当前的
            _this.tableDatas = resp.data.data.result.userList;
          } else {
            _this.$message.error(resp.data.data.errMessage);
          }
        });
      }
    },
    //编辑用户信息
    UpdateUserInfo(index, row) {
      this.updateDialogVisible = true;
      this.GoodsInfoForm = Object.assign({}, row);
      this.GoodsInfoForm.index = index;
    },
    submitUpdateGoodsInfoForm(GoodsInfoForm) {
      const _this = this;
      this.$refs[GoodsInfoForm].validate((valid) => {
        if (valid) {
          this.$axios
            .updateGoodsInfo(this.GoodsInfoForm)
            .then(function (resp) {
              if (resp.code == 200) {
                _this.$alert(
                  "《" + _this.GoodsInfoForm.name + "》用户信息修改成功",
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
                _this.$message.error(resp.data.data.errMessage);
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    //删除商品
    handleDelete(index, row) {
      const _this = this;
      const params = {
        id: row.id,
      };
      this.$axios.handleDeleteGoodsInfo(params).then((resp) => {
        if (resp.code == 200) {
          _this.$alert("删除商品成功", "消息", {
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
    //添加商品
    addNewGoods() {
      this.adddialogVisible = true;
    },
    // //添加商品
    submitForm(GoodsInfoForm) {
      const _this = this;
      this.$refs[GoodsInfoForm].validate((valid) => {
        if (valid) {
          this.$axios.addGoodsInfo(this.GoodsInfoForm).then(function (resp) {
            if (resp.code == 200) {
              _this.$alert(
                "《" + _this.GoodsInfoForm.name + "》添加成功",
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

    // 展示所有的商品信息
    showAllUserInfo(currentPage, pageSize) {
      //异步请求显示所有数据
      currentPage = currentPage ? currentPage : this.now;
      pageSize = pageSize ? pageSize : this.size;
      const params = {
        page: currentPage,
        size: pageSize,
      };
      this.$axios.findAllGoodsInfo(params).then((res) => {
        if (res.code === 200) {
          console.log("findAllGoodsInfo");
          console.log(res);
          this.tableDatas = res.data.result.goodsInfoList;
          this.tableDatas.map(function (val) {
            if (val.isSale === "是") {
              val.isSale = true;
            } else {
              val.isSale = false;
            }
          });
          this.total = res.data.result.totals;
        } else {
          _this.$message.error(res.data.errMessage);
        }
      });
    },
    //选择下拉框
    getOptions() {
      this.$axios.getTypeInfoOption().then((res) => {
        console.log(res);
        this.options = res.data.goodsInfoListList;
      });
    },
    //切换是否上架
    switchSale(isSale, id) {
      const _this = this;
      if (isSale == true) {
        isSale = "是";
      } else {
        isSale = "否";
      }
      const params = {
        myId: id,
        myIsSale: isSale,
      };
      this.$axios.updateSaleStatus(params).then((res) => {
        if (res.code == 200) {
          _this.showAllUserInfo();
        } else {
          _this.$message.error(res.data.errMessage);
        }
      });
    },
  },
  created() {
    this.showAllUserInfo();
    this.getOptions();
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
.myMain {
  margin-left: 20px;
  margin-right: 20px;
}

.modal-body {
  position: relative;
  padding: 15px;
}
.modal-footer {
  padding: 15px;
  text-align: right;
  border-top: 1px solid #e5e5e5;
}
</style>

