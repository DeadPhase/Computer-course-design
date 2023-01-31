<template>
  <div class="PersonalData">
    <div class="my_header">
      <div class="user-info-avatar" style="overflow: hidden">
        <div class="logo_pic">
          <el-avatar
            :size="80"
            :src="circleUrl"
            style="float: left"
          ></el-avatar>
        </div>
        <div class="logo_pic_bottom">
          <span
            style="
              color: black;
              float: left;
              margin-top: 30px;
              margin-left: 20px;
            "
            >账号名称</span
          >
        </div>
      </div>
      <div class="border-bottom">
        <div style="float: left; margin-left: 55px">
          <div style="float: left; padding-top: 15px">
            <i
              class="el-icon-collection"
              style="color: black; font-size: 28px"
            ></i>
          </div>
          <span style="color: red; margin-left: 25px; margin-right: 16px"
            >我的余额 {{ money }}</span
          >

          <el-button type="danger" size="middle" round style="margin-top: 10px"
            >充值</el-button
          >
        </div>
      </div>
    </div>

    <div class="my_content">
      <div style="overflow: hidden">
        <span
          style="
            float: left;
            color: pink;
            font-size: 24px;
            margin-top: 10px;
            margin-left: 10px;
          "
          >基本信息</span
        >
      </div>
      <hr />
      <div>
        <el-form ref="form" :model="form" label-width="80px">
          <div v-if="showInput" style="float: right; margin-right: 550px">
            <el-button type="danger" round>提交</el-button>
            <el-button type="danger" round @click="cancle">取消</el-button>
          </div>
          <el-form-item label="昵称" style="margin-bottom: 10px">
            <el-input v-if="showInput" v-model="form.name"></el-input>

            <div
              v-on="{ mouseenter: showImg, mouseleave: hiddenImg }"
              v-show="showUser"
              class="spanshow"
            >
              <span style="color: black; margin-left: -80px">{{
                form.name
              }}</span>
              <div
                v-show="showPic"
                class="editshow"
                style="float: right; min-width: 50px; cursor: pointer"
              >
                <i class="el-icon-edit" style="color: #1989fa"></i
                ><button
                  @click="myshowInput()"
                  style="
                    border: none;
                    cursor: pointer;
                    background-color: white;
                    font-size: 14px;
                    color: #1989fa;
                  "
                >
                  编辑
                </button>
              </div>
            </div>
          </el-form-item>

          <el-form-item label="ID">
            <span style="color: black; margin-left: -80px">我是张三</span>
          </el-form-item>

          <div v-if="showInput" style="float: right; margin-right: 550px">
            <el-button type="danger" round>提交</el-button>
            <el-button type="danger" round @click="cancle">取消</el-button>
          </div>
          <el-form-item  label="性别">
      
            <el-radio-group v-if="showInput" v-model="form.resource">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>

            <div
              v-on="{ mouseenter: showImg, mouseleave: hiddenImg }"
              v-show="showUser"
              class="spanshow"
            >
              <span style="color: black; margin-left: -80px">男</span>
              <div
                v-show="showPic"
                class="editshow"
                style="float: right; min-width: 50px; cursor: pointer"
              >
                <i class="el-icon-edit" style="color: #1989fa"></i
                ><button
                  @click="myshowInput()"
                  style="
                    border: none;
                    cursor: pointer;
                    background-color: white;
                    font-size: 14px;
                  "
                >
                  编辑
                </button>
              </div>
            </div>
          </el-form-item>
          <el-form-item label="个人简介" style="width: 80%">
            <el-input type="textarea" v-model="form.desc"></el-input>
          </el-form-item>

          <el-form-item label="所在地区">
            <el-input v-model="form.region"></el-input>
          </el-form-item>
          <el-form-item label="地址管理">
            <el-input v-model="form.region"></el-input>
          </el-form-item>
          <el-form-item label="出生日期">
            <el-input v-model="form.region"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PersonalData",
  data() {
    return {
      showPic: false,
      showUser: true,
      showInput: false,
      money: 45,
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      form: {
        name: "架构师全栈之路",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "男",
        desc: "",
      },
    };
  },

  methods: {
    cancle() {
      this.showUser = true;
      this.showInput = false;
    },
    myshowInput() {
      this.showUser = false;
      this.showInput = true;
    },
    showImg() {
      this.showPic = true;
    },
    hiddenImg() {
      this.showPic = false;
    },

    onSubmit() {
      console.log("submit!");
    },
  },
};
</script>


<style scoped>
.el-form-item {
  margin-bottom: 10px;
  width: 30%;
}
.user-info-avatar {
  padding: 24px 0 40px 24px;
  background-color: white;
}
.logo_pic {
  margin-left: 25px;
}
div {
  padding: 0;
  margin: 0;
}
.border-bottom {
  display: flex;
  background-color: #f0f0f0;
  height: 60px;
}

.my_content {
  width: 100%;
  min-height: 200px;
  background: #fff;
  font-size: 14px;
  margin-top: -5px;
}
</style>
