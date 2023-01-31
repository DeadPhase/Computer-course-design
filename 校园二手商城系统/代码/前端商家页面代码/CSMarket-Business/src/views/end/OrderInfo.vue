<template>
  <div class="myMain">
    <div>
      <div class="table-title">订单信息</div>
    </div>
    <div class="row">
      <div style="margin-top: 20px">
        <div style="border: 1px solid #c9c9c9">
          <div class="panel-heading">订单信息表</div>
          <div class="panel-body">
            <div style="width: 100%">
              <div class="nx-table-header" style="">
                <div
                  style="float: left; margin-bottom: 10px; margin-left: 10px"
                >
                  <el-input
                    placeholder="请输入订单编号"
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
                  <el-table-column prop="orderId" label="订单编号" width="200">
                  </el-table-column>
                  <el-table-column prop="totalPrice" label="总价格" width="100">
                  </el-table-column>
                  <el-table-column prop="fahuoName" label="发货人" width="100">
                  </el-table-column>
                  <el-table-column prop="fahuoAddress" label="发货地址">
                  </el-table-column>
                  <el-table-column
                    prop="fahuoPhone"
                    label="发货电话"
                    width="150px"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="harvestName"
                    label="收货人"
                    width="100px"
                  >
                  </el-table-column>
                  <el-table-column prop="harvestAddress" label="收货地址">
                  </el-table-column>
                  <el-table-column
                    prop="harvestPhone"
                    label="收货电话"
                    width="150px"
                  >
                  </el-table-column>
                  <el-table-column prop="status" label="订单状态" width="100px">
                  </el-table-column>
                  <el-table-column
                    prop="createTime"
                    label="创建时间"
                    width="180px"
                  >
                  </el-table-column>

                  <el-table-column label="操作">
                    <template slot-scope="scope">
                      <el-button
                        type="primary"
                        v-if="scope.row.status === '申请退货中' ? true : false"
                        size="mini"
                        >确认
                      </el-button>
                      <el-button
                        type="primary"
                        v-if="scope.row.status === '待发货' ? true : false"
                        @click="onShipments(scope.$index, scope.row)"
                        size="mini"
                        >发货
                      </el-button>
                      <el-button
                        type="warning"
                        size="mini"
                        v-if="scope.row.status === '申请退款中' ? true : false"
                        @click="onRefund(scope.$index, scope.row)"
                        >退款</el-button
                      >
                      <el-popconfirm
                        confirm-button-text="好的"
                        cancel-button-text="不用了"
                        icon-color="red"
                        title="确定删除该吗？"
                        @confirm="handleDelete(scope.$index, scope.row)"
                      >
                        <el-button
                          type="danger"
                          slot="reference"
                          size="mini"
                          v-if="
                            scope.row.status === '待付款' ||
                            scope.row.status === '已发货'
                              ? true
                              : false
                          "
                          >删除</el-button
                        >
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
    //发货
    onShipments(index, row) {
      const _this = this;
      const params = {
        orderId: row.orderId,
      };

      this.$axios.onShipments(params).then(function (res) {
        if (res.code == 200) {
          console.error("退款后的信息");
          _this.showAllUserInfo();
        } else {
          _this.$message.error(res.data.errMessage);
        }
      });
    },
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

    // 展示所有的订单
    showAllUserInfo(currentPage, pageSize) {
      //异步请求显示所有数据
      currentPage = currentPage ? currentPage : this.now;
      pageSize = pageSize ? pageSize : this.size;
      const params = {
        page: currentPage,
        size: pageSize,
      };
      this.$axios.findAllOrder(params).then((res) => {
        if (res.code === 200) {
          console.log("管理员查看所有订单");
          console.log(res);
          this.tableDatas = res.data.result.orderList;
          this.tableDatas.map(function (val) {
            if (val.status === 0) {
              val.status = "待付款";
            } else if (val.status === 1) {
              val.status = "待发货";
            } else if (val.status === 2) {
              val.status = "申请退款中";
            } else if (val.status === 3) {
              val.status = "已发货";
            } else if (val.status === 4) {
              val.status = "申请退货中";
            }
          });
          this.total = res.data.result.totals;
        } else {
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

