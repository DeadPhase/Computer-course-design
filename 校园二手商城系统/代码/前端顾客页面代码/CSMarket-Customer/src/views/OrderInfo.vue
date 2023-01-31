<template>
  <div class="orderInfo">
    <div style="float: left">
      <h1 style="padding-left: 1px">全部商品 ({{ number }})</h1>
    </div>
    <div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="orderId" v-if="false" label="编号" width="100">
        </el-table-column>
        <el-table-column prop="name" label="商品" width="180">
          <template width="100" slot-scope="scope">
            <el-popover placement="top-start" title="" trigger="click">
              <a
                :href="scope.row.fileName"
                target="_blank"
                title="查看最大化图片"
              >
                <img
                  :src="scope.row.fileName"
                  style="width: 300px; height: 300px"
                />
                <!-- <span style="font-size: 12px; margin-left: 10px">{{g.name}} X {{g.count}}</span> -->
              </a>
              <img
                slot="reference"
                :src="scope.row.fileName"
                style="width: 100px; height: 100px; cursor: pointer"
              />
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column prop="orderId" label="订单编号" width="180">
        </el-table-column>

        <el-table-column prop="totalPrice" label="总金额" width="180">
        </el-table-column>
        <el-table-column prop="harvestAddress" label="送货地址" width="180">
        </el-table-column>
        <el-table-column prop="harvestName" label="收货人"> </el-table-column>
        <el-table-column prop="harvestPhone" label="联系方式">
        </el-table-column>
        <el-table-column prop="createTime" label="时间"> </el-table-column>
        <el-table-column prop="status" label="状态"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              v-if="scope.row.status != '待付款' ? false : true"
              type="success"
              size="mini"
              @click="onPayment(scope.$index, scope.row)"
              >付款</el-button
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
                size="mini"
                slot="reference"
                v-if="
                  scope.row.status === '待付款' || scope.row.status === '完成'
                    ? true
                    : false
                "
                >删除</el-button
              >
            </el-popconfirm>
            <el-button
              type="warning"
              size="mini"
              v-if="scope.row.status === '待发货' ? true : false"
              @click="onRefund(scope.$index, scope.row)"
              >退款</el-button
            >
            <el-button
              type="warning"
              size="mini"
              v-if="scope.row.status === '申请退款中' ? true : false"
              @click="onCancelRefund(scope.$index, scope.row)"
              >取消</el-button
            >
            <el-button
              type="primary"
              size="mini"
              v-if="scope.row.status === '已发货' ? true : false"
              >确认</el-button
            >
            <el-button
              type="danger"
              size="mini"
              v-if="scope.row.status === '已发货' ? true : false"
              @click="onReturnable(scope.$index, scope.row)"
              >退货</el-button
            >
            <el-button
              type="warning"
              size="mini"
              v-if="scope.row.status === '申请退货中' ? true : false"
              @click="onCancelReturnable(scope.$index, scope.row)"
              >取消</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "orderInfo",
  data() {
    return {
      number: 0,
      user: {
        uid: "",
        userName: "",
        level: "",
      },
      tableData: [],
      // goodsDetail: []
    };
  },

  methods: {
    //取消退货 onCancelReturnable
        onCancelReturnable(index, row) {
      const _this = this;
      const params = {
        orderId: row.orderId,
      };

      this.$axios.onCancelReturnable(params).then(function (res) {
        if (res.code == 200) {
          console.error("取消退货后的信息");
          console.error(res.data.user);
          _this.user = res.data.user;
          _this.findCartShop();
        } else {
          _this.$message.error(resp.data.errMessage);
        }
      });
    },

    //退货 onReturnable
    onReturnable(index, row) {
      const _this = this;
      const params = {
        orderId: row.orderId,
      };

      this.$axios.onReturnable(params).then(function (res) {
        if (res.code == 200) {
          console.error("退货后的信息");
          console.error(res.data.user);
          _this.user = res.data.user;
          _this.findCartShop();
        } else {
          _this.$message.error(resp.data.errMessage);
        }
      });
    },
    //取消退款
    onCancelRefund(index, row) {
      const _this = this;
      const params = {
        orderId: row.orderId,
      };

      this.$axios.onCancelRefund(params).then(function (res) {
        if (res.code == 200) {
          console.error("取消退款后的信息");
          console.error(res.data.user);
          _this.user = res.data.user;
          _this.findCartShop();
        } else {
          _this.$message.error(resp.data.errMessage);
        }
      });
    },
    //退款
    onRefund(index, row) {
      const _this = this;
      const params = {
        orderId: row.orderId,
      };

      this.$axios.onRefund(params).then(function (res) {
        if (res.code == 200) {
          console.error("退款后的信息");
          console.error(res.data.user);
          _this.user = res.data.user;
          _this.findCartShop();
        } else {
          _this.$message.error(resp.data.errMessage);
        }
      });
    },

    //付款
    onPayment(index, row) {
      const _this = this;
      console.error("付款订单信息");
      console.error(row);
      const params = {
        uid: localStorage.getItem("uid"),
        orderId: row.orderId,
        paymoney: row.totalPrice,
      };

      this.$axios.onPaymoney(params).then(function (res) {
        if (res.code == 200) {
          console.error("付款后订单返回信息");
          console.error(res.data.user);
          _this.user = res.data.user;
          _this.findCartShop();
        } else {
          _this.$message.error(resp.data.errMessage);
        }
      });
    },
    //删除商品
    handleDelete(index, row) {
      const _this = this;

      const params = {
        goodsId: row.id,
        userId: localStorage.getItem("uid"),
      };
      console.error("要删除商品id:" + row.id);

      this.$axios.deleteCartShopInfo(params).then((resp) => {
        if (resp.code == 200) {
          _this.$alert("删除商品成功", "消息", {
            confirmButtonText: "确定",
            callback: (action) => {
              _this.findCartShop();
            },
          });
        } else {
          _this.$message.error(resp.data.errMessage);
        }
      });
    },
    findCartShop() {
      const _this = this;
      const params = {
        userId: localStorage.getItem("uid"),
        level: localStorage.getItem("level"),
      };

      this.$axios.queryOrder(params).then((res) => {
        if (res.code === 200) {
          console.error("查看订单：");
          console.error(res.data.orderList);

          // let order = JSON.stringify(res.data.orderList);
          // alert(o)

          _this.tableData = res.data.orderList;

          let orderData = res.data.orderList;
          // alert(orderData.length)
          for (var i = 0; i < orderData.length; i++) {
            console.error("开始");
            console.error(orderData[i].goodsList);
            let goodsList = orderData[i].goodsList;
          }
          // orderData.array.forEach(item => {
          //   let goodsList = item.goodsList

          //   console.error(goodsList)
          // });

          this.tableData.map(function (val) {
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

          _this.number = res.data.orderList.length;

          // localStorage.removeItem("uid");
          // localStorage.removeItem("level");
        } else {
          //  localStorage.removeItem("uid");
          // localStorage.removeItem("level");
        }
      });
    },

    isLogin() {
      const _this = this;
      const params = "";
      this.$axios.isLogin(params).then(function (res) {
        if (res.code == 200) {
          console.error(res.data.user);
          _this.user = res.data.user;
          localStorage.setItem("uid", _this.user.uid);
          localStorage.setItem("level", _this.user.level);
        } else {
          _this.$alert("您还未登录系统，请登录系统查看购物车！！！", {
            confirmButtonText: "确定",
            callback: (action) => {
              _this.$router.push("/UserLogin");
            },
          });
        }
      });
    },
  },
  created() {
    this.isLogin();
    this.findCartShop();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
