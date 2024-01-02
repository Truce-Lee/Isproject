<template>
    <el-container style="height: auto">
      <el-main  style="height: auto; overflow-y: auto;border: 20px solid #eee">
        <el-form label-width="80px" :model="form">
          <el-form-item label="COURSE_NAME">
            <el-input
                class="narrow-input"
                size="small"
                placeholder=""
                maxlength="30"
                v-model="form.name">
            </el-input>
          </el-form-item>
          <el-form-item label="PLANNED_HOURS">
            <el-input
                class="narrow-input"
                size="small"
                placeholder=""
                maxlength="30"
                v-model="form.activity_time">
            </el-input>
          </el-form-item>
          <el-form-item label="START_TIME">
            <el-input
                class="narrow-input"
                size="small"
                placeholder=""
                maxlength="30"
                v-model="form.start_time">
            </el-input>
          </el-form-item>
          <el-form-item label="FINISH_TIME">
            <el-input
                class="narrow-input"
                size="small"
                placeholder=""
                maxlength="30"
                v-model="form.finish_time">
            </el-input>
          </el-form-item>
          <el-form-item label="DETAILS">
            <el-input
                class="narrow-input"
                size="small"
                placeholder=""
                maxlength="30"
                v-model="form.description">
            </el-input>
          </el-form-item>
          <el-form-item class="button">
            <el-button type="primary" @click="save">ADD</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>

</template>

<style>
.narrow-input .el-input__inner {
  width: 500px;
}
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
      form: {},
      course_id:"",
      name:"",
      start_time: "",
      finish_time: "",
      description: "",
      activity_time: "",
      fileList:[]
    }
  },
  methods: {
    handleSelect(val) {
      const currentRoute = this.$route;

      // 检查是否尝试导航到当前路由
      if (currentRoute.path !== val) {
        // 执行导航
        this.$router.push(val);
      }
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    },
    save(){
      request.post("/course",this.form).then(res =>{
        if (res){
          this.$message.success("save success")
        }else {
          this.$message.error("save failed")
        }
      })
    }

  }
};
</script>
