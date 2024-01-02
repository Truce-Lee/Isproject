<template>
    <el-container style="height: auto">

      <el-main  style="height: auto; overflow-y: auto;border: 20px solid #eee">
        <el-form label-width="150px" >
          <el-form-item label="MY NAME:">
            <span class="el-form-item__label">{{ form.name }}</span>
          </el-form-item>
          <el-form-item label="MY EMIAL:">
            <span class="el-form-item__label">{{ form.email }}</span>
          </el-form-item>
          <el-form-item label="PHONE:">
            <span class="el-form-item__label">{{ form.phone }}</span>
          </el-form-item>
          <el-form-item label="INFORMATION:">
            <span class="el-form-item__label">{{ form.name }}</span>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" size="small" @click="logout">LOG OUT</el-button>
            <el-button type="text" size="mini" @click="handleChange">change password</el-button>
          </el-form-item>
        </el-form >
        <el-dialog title="Course Information" :visible.sync="dialogFormVisible">
          <el-form label-width="180px" :model="newForm">
            <el-form-item label="Email for confirm identify" prop="email">
              <el-input   autocomplete="off" v-model="newForm.email"></el-input>
            </el-form-item>
            <el-form-item label="Your New Password" prop="password">
              <el-input  autocomplete="off" v-model="newForm.password"></el-input>
            </el-form-item>

          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">cancel</el-button>
            <el-button type="primary" @click="changePasswd">complete</el-button>
          </div>
        </el-dialog>



      </el-main>


    </el-container>
</template>

<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      currentPage: 1,
      form: JSON.parse(localStorage.getItem('honey-user')),
      name: 'test',
      dialogFormVisible: false,
      newForm:{},
    }
  },
  created() {

  },
  methods: {
    logout(){
      localStorage.removeItem('honey-user')
      this.$router.push('/login')
    },
    handleSelect(val) {
      const currentRoute = this.$route;

      // 检查是否尝试导航到当前路由
      if (currentRoute.path !== val) {
        // 执行导航
        this.$router.push(val);
      }
    },
    handleChange(){
      this.dialogFormVisible=true
    },
    changePasswd(){
      // this.form.email = this.newForm.email
      this.form.password = this.newForm.password
      request.put("/password",this.form).then(res =>{
        if (res.code === "200"){
          console.log(res)
          this.$message.success("change success")
          this.dialogFormVisible = false
        }else {
          this.$message.error("change failed")
        }
      })
    }
  }
};
</script>
