
<template>
  <div class="GoodsDetail">
    <div class="container">
      <!-- <el-container style="margin-top: 20px"> -->
      <el-row :gutter="20">
        <el-col :span="8">
          <div class="grid-content bg-purple">
            <div class="left-box">
              <div style="width: 80%;display: flex; justify-content: right; height: 300px" class="rightullidiv">
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
                  <span class="spanshow">商品名称：</span>
                  <span class="right-title" style="color:black">{{ goodsInfo.name }}</span>
                </li>
                <li><span class="spanshow">描述：</span>
                  <span style="color: red; margin-left: 5px">{{
                    goodsInfo.description
                  }}</span>
                </li>
                <!-- <li v-if="goodsInfo.typeId === 1 || goodsInfo.typeId === 4"> -->
                <li>
                <span class="spanshow">价格：</span>
                  <span style="color: red; margin-left: 5px"
                    >￥ {{ goodsInfo.price }} / 瓶</span
                  >
                </li>
                <!-- <li v-if="goodsInfo.typeId === 2 || goodsInfo.typeId === 3">
                  价格：<span style="color: red; margin-left: 20px"
                    >￥ {{ goodsInfo.price }}/件</span
                  >
                </li> -->
                <li>
                  <span class="spanshow"> 促销：</span>
                  <span style="color: red; margin-left: 5px"
                    >{{ goodsInfo.discount }} 折</span
                  >
                </li>
                <li>
                  <span class="spanshow">选择数量:</span>
                  
                  <span class="count-group">
                    <button @click="changeCount('-')">-</button
                    ><span id="count-id" style="margin: 0 5px;color:black">1</span
                    ><button @click="changeCount('+')">+</button></span
                  >
                  <span style="margin-left: 10px; color: #666"
                    >剩余<span id="store-id">{{ goodsInfo.count }}</span></span
                  >份
                </li>
                <li><span class="spanshow">运费：包邮&nbsp;&nbsp;预计48小时内发货</span></li>
                <li>
                  <el-button-group>
                    <el-button type="warning" size="samll" @click="addShopCar()"
                      >加入购物车</el-button
                    >
                    <el-button
                      type="danger"
                      size="samll"
                      @click="confirmOrder()"
                      >立即购买</el-button
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
            >用户评价</span
          >
        </div>
        <div id="comment-content"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "GoodsDetail",
  data() {
    return {
      goodsInfo: {
        goodsId: "",
      },
      user: {
        uid: "",
        userName: "",
        level: "",
      },
    };
  },

  methods: {
    //确认订单
    confirmOrder() {
      this.$router.push("/ConfirmTheOrder");
    },
    //添加购物车
    addShopCar() {
      const _this = this;
      if (!this.user.userName) {
        _this.$alert("您还未登录系统，请登录系统后购买！！！", {
          confirmButtonText: "确定",
          callback: (action) => {
            _this.$router.push("/Login");
          },
        });
      } else {
        let $count = $("#count-id");
        let count = parseInt($count.text());
        console.error(this.$route.params.id);
        const params = {
          userId: this.user.uid,
          level: this.user.level,
          goodsId: this.$route.params.id,
          count: count,
        };
        this.$axios.addCartShopInfo(params).then((res) => {
          if (res.code === 200) {
            console.error(res);
            _this.$router.push("/ShopCarInfo");
          } else {
            _this.$message.error(res.data.errMessage);
          }
        });
      }
    },
    changeCount(type) {
      let $count = $("#count-id");
      let $store = $("#store-id");
      let count = parseInt($count.text());
      let store = parseInt($store.text());
      if (type === "+") {
        count = count === store ? store : count + 1;
      } else {
        count = count === 1 ? 1 : count - 1;
      }
      $count.text(count);
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
          // _this.$router.push("/Login");
        }
      });
    },
  },
  created() {
    this.showGoodsDetail();
    this.isLogin();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* li {
  list-style-type: none;
} */
.spanshow{
  color: black;
}

ul {
  padding: 0;
  margin: 0;
  text-align: left;
  list-style-type: none;
}
.left-box {
  width: 100%;
  height: 400px;
  background-color: pink;
}

.right-box {
  width: 100%;
  height: 400px;
  background-color: pink;
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
