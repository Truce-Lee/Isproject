<template>
  <div class="login-wrapper">

    <div class="login-box">
      <!-- 头像 -->
      <div class="login-avatar">
        <img src="@/assets/th.jpeg"  />
      </div>
      <!-- 登录表单 -->
      <el-form class="login-form" ref="loginFormRef" :model="user" :rules="studentLoginFormRules">
        <h3>login</h3>
        <!-- 用户名 -->
        <el-form-item prop="name">
          <el-input  v-model="user.name" placeholder="enter the username" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input show-password v-model="user.password" placeholder="enter the passwd"  prefix-icon="el-icon-lock" ></el-input>
        </el-form-item>
        <el-form-item prop="password">
        <el-radio-group v-model="ratio">
          <el-radio-button label="teacher" ></el-radio-button>
          <el-radio-button label="student" ></el-radio-button>
          <el-radio-button label="administrator"></el-radio-button>
        </el-radio-group>
        </el-form-item>
        <!-- 按钮 -->
        <el-form-item class="button">
          <el-button type="primary" @click="login">login</el-button>
          <el-button type="info" @click="registerNo">register</el-button>
        </el-form-item>
      </el-form>

    </div>
      <el-dialog class="el-dialog__body" title="Register" :visible.sync="dialogFormVisible" >
        <el-form width="1000px" ref="loginFormRef" :model="user" :rules="studentLoginFormRules">
          <!-- Username -->
          <el-form-item prop="name">
            <el-input v-model="user.name" placeholder="Enter the username" prefix-icon="el-icon-user"></el-input>
          </el-form-item>
          <!-- Password -->
          <el-form-item prop="password">
            <el-input v-model="user.password" placeholder="Enter the password" prefix-icon="el-icon-lock"></el-input>
          </el-form-item>
          <el-form-item>
            <el-radio-group v-model="ratio">
              <el-radio-button label="teacher" ></el-radio-button>
              <el-radio-button label="student" ></el-radio-button>
              <el-radio-button label="administrator"></el-radio-button>
            </el-radio-group>
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">Cancel</el-button>
          <el-button type="primary" @click="handleRegister">Complete</el-button>
        </div>
      </el-dialog>

  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      ratio: "user",
      dialogFormVisible: false,
      // 登录表单的对象
      user: {
        name: '',
        password: '',
        role:''
      },

      studentLoginFormRules: {
        name: [
          { required: true, message: 'enter the name', trigger: 'blur' },
          { min: 3, max: 12, message: 'length between 3 to 12', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'enter the passwd', trigger: 'blur' },
          { min: 3, max: 15, message: 'length between 3 to 12', trigger: 'blur' }
        ]
      },
    }
  },
  methods: {

    registerNo() {
      this.dialogFormVisible = true
    },
    handleRegister(){
      this.$refs.loginFormRef.validate(valid => {
        if (valid){
          this.user.role = this.ratio
          this.request.post('/register',this.user).then(res =>{
            console.log(res)
            if (res.code === '200'){
              this.$message.success(res.msg)
            }else {
              this.$message.error(res.msg)
            }
          })
        }})
    },

    login(){
      this.$refs.loginFormRef.validate(valid => {
            if (valid){
              this.user.role = this.ratio
              this.request.post('/login',this.user).then(res =>{
                console.log(res)
                if (res.code === '200'){
                  this.$router.push('/user-course')
                  this.$message.success(res.msg)
                  localStorage.setItem("honey-user",JSON.stringify(res.data))
                }else {
                  this.$message.error(res.msg)
                }
              })
            }})


    }
  }
};
</script>

<style scoped>
.el-dialog__body {
  width: 800px;
  margin: 0 auto;
  //overflow: hidden;
}

.login-form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}



.button {
  display: flex;
  margin-top: 15px;
  margin-right: 30px;
  justify-content: center;
}

.login-wrapper {
  background: #2b4b6b;
  height: 100%;
}

.login-avatar {
  height: 130px;
  width: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  img {
    height: 100%;
    width: 100%;
    border-radius: 50%;
    background-color: #eee;
  }
}

.title {
  color: #fff;
}

.login-box {
  width: 500px;
  height: 320px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}



</style>
