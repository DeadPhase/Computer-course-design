
<template>
  <div class="confirmTheOrder">
    <div class="container">
      <!-- <el-container style="margin-top: 20px"> -->
      <el-row :gutter="20">
        <el-col :span="8">
          <div class="grid-content bg-purple">
            <div class="left-box">
              <div style="width: 80%; height: 300px" class="rightullidiv">
                <img
                  id="main-img-id"
                  :src="goodsInfo.fileName"
                  class="rightulliimg"
                  style="height: 80%; width: 80%"
                  alt=""
                />
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="16">
          <div class="grid-content bg-purple">
            <div class="right-box">
              <ul>
                <li>
                  商品名称：
                  <span class="right-title">{{ goodsInfo.name }}</span>
                </li>
                <li>
                  描述：<span style="color: red; margin-left: 20px">{{
                    goodsInfo.description
                  }}</span>
                </li>
                <!-- <li v-if="goodsInfo.typeId === 1 || goodsInfo.typeId === 4"> -->
                <li>
                  价格：<span style="color: red; margin-left: 20px"
                    >￥ {{ goodsInfo.price }} / 瓶</span
                  >
                </li>
                <!-- <li v-if="goodsInfo.typeId === 2 || goodsInfo.typeId === 3">
                  价格：<span style="color: red; margin-left: 20px"
                    >￥ {{ goodsInfo.price }}/件</span
                  >
                </li> -->
                <li>
                  促销：<span style="color: red; margin-left: 20px"
                    >{{ goodsInfo.discount }} 折</span
                  >
                </li>
                <li>
                  数量:
                  <span style="color: red; margin-left: 20px">
                    {{ goodsInfo.count }}
                  </span>
                </li>
                <li>
                  合计:
                  <span style="color: red; margin-left: 20px">
                    ￥ {{ goodsInfo.totalPrice }}
                  </span>
                </li>
                <li>运费：<span>包邮&nbsp;&nbsp;预计48小时内发货</span></li>
                <li>
                  <el-button-group>
                    <el-button
                      type="danger"
                      size="samll"
                      @click="confirmOrder()"
                      >提交订单</el-button
                    >
                  </el-button-group>
                </li>
              </ul>
            </div>
          </div></el-col
        >
      </el-row>
    </div>
    <!-- </el-container> -->

    <div class="row">
      <div style="background-color: white; min-height: 300px">
        <div
          style="
            width: 100%;
            height: 50px;
            background-color: #f8eeee;
            line-height: 50px;
            margin: 30px 0;
          "
        >
          <span style="font-weight: bold; margin-left: 20px; font-size: 16px"
            >收货地址</span
          >
          <div>
            <ul>
              <li>
                收货人姓名：
                <span
                  class="right-title"
                  style="color: red; margin-left: 20px"
                  >{{ user.userName }}</span
                >
              </li>
              <li>
                收货人地址：<span style="color: red; margin-left: 20px">{{
                  user.address
                }}</span>
              </li>
              <!-- <li v-if="goodsInfo.typeId === 1 || goodsInfo.typeId === 4"> -->
              <li>
                收货人电话：<span style="color: red; margin-left: 20px">{{
                  user.phone
                }}</span>
              </li>
            </ul>
          </div>
        </div>
        <div id="comment-content"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "confirmTheOrder",
  data() {
    return {
      goodsInfo: {
        goodsId: "",
      },
      user: {
        uid: "",
        userName: "",
        level: "",
        address: "",
        phone: "",
        money: "",
      },
    };
  },

  methods: {
    //提交订单
    confirmOrder() {
      const _this = this;
      const userCurrentMoney = this.user.money;
      console.error(this.goodsInfo.totalPrice);
      console.error(this.user.money);
      if (userCurrentMoney < this.goodsInfo.totalPrice) {
        _this.$message.error("余额不足、请充值后购买");
        return;
      }
      const params = {
        userId: localStorage.getItem("uid"),
        level: localStorage.getItem("level"),
        goodsInfo:  this.goodsInfo
      };

      this.$axios.addOrder(params).then((res) => {
        if (res.code === 200) {
          console.error(res);
          // this.goodsInfo = res.data.result.goodsDetail;
        } else {
          _this.$router.push("/HomePage");
        }
      });

      // this.$router.push("/ConfirmTheOrder");
    },

    showGoodsDetail() {
      //异步请求数据
      const _this = this;
      const id = this.$route.params.id;
      const params = {
        id: id,
      };
      this.$axios.findGoodsDetail(params).then((res) => {
        if (res.code === 200) {
          console.error(res);
          this.goodsInfo = res.data.result.goodsDetail;
        } else {
          _this.$router.push("/HomePage");
        }
      });
    },

    isLogin() {
      const _this = this;
      const params = "";
      this.$axios.isLogin(params).then(function (res) {
        if (res.code == 200) {
          _this.user = res.data.user;
        } else {
          // _this.$message.error(res.data.errMessage);
          // _this.$router.push("/UserLogin");
        }
      });
    },
  },
  created() {
    this.goodsInfo = this.$route.params.goodsInfo;
    this.isLogin();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* li {
  list-style-type: none;
} */

ul {
  padding: 0;

  margin: 0;

  text-align: left;

  list-style-type: none;
}
.left-box {
  width: 100%;
  height: 400px;
  background-color: #ffffff;
}

.right-box {
  width: 100%;
  height: 400px;
  background-color: #ffffff;
}

.right-box li {
  padding: 10px 40px;
}
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
