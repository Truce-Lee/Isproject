<template>
  <el-container style="height: auto; border: 15px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246); height: auto">
      <el-menu :default-openeds="['1', '2']" style="height: 100%" @select="handleSelect">
        <el-submenu><template slot="title"><i class="el-icon-s-home"></i>COURSE</template>
          <el-menu-item index="/teacher-course">
            <template slot="title">VIEW COURSE</template>
          </el-menu-item >
          <el-menu-item index="/add-course" >
            <template slot="title">ADD COURSE</template>
          </el-menu-item >
          <el-menu-item index="/delete-course" >
            <template slot="title">EDIT COURSE</template>
          </el-menu-item >
        </el-submenu>
        <el-menu-item index="/teacher-info">
          <template slot="title"><i class="el-icon-s-custom"></i>MY INFO</template>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container style="height: auto">
      <el-header style="background-color: #3498db; color: #fff; line-height: 60px; text-align: left; font-size: 12px; display: flex; justify-content: space-between; align-items: center; padding: 0 15px;">
        <i class="el-icon-s-unfold" style="font-size: 20px;"></i>
        <span style="margin-left: auto;">USER</span>
      </el-header>

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
          <el-form-item label="SYLLABUS">

          </el-form-item>
          <el-form-item class="button">
            <el-button type="primary" @click="save">ADD</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </el-container>
</template>

<style>
.narrow-input .el-input__inner {
  width: 500px;  /* 或者你想要的任何宽度 */
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
import MyInfo from "@/components/my-info.vue";
import request from "@/utils/request";

export default {
  components: {MyInfo},
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
