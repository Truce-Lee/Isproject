<template>

  <el-container style="height: auto; border: 15px solid #eee">

    <el-aside width="200px" style="background-color: rgb(238, 241, 246); height: auto">
      <el-menu :default-openeds="['1', '2']" style="height: 100%" @select="handleSelect">
        <el-submenu><template slot="title"><i class="el-icon-s-home"></i>COURSE</template>
          <el-menu-item index="/user-course">
            <template slot="title">VIEW COURSE</template>
          </el-menu-item >
          <el-menu-item index="/add-course" v-if="userJudge===1">
            <template slot="title">ADD COURSE</template>
          </el-menu-item >
          <el-menu-item index="/delete-course" v-if="userJudge===1">
            <template slot="title">EDIT COURSE</template>
          </el-menu-item >
        </el-submenu>
        <el-menu-item index="/change-user" v-if="userJudge===2">
          <template slot="title"><i class="el-icon-menu" ></i>THE ROLE</template>
        </el-menu-item>
        <el-menu-item index="/user-info" >
          <template slot="title"><i class="el-icon-s-custom"></i>MY INFO</template>
        </el-menu-item>

      </el-menu>
    </el-aside>

    <el-container style="height: auto">
      <el-header style="background-color: #3498db; color: #fff; line-height: 60px; text-align: left; font-size: 12px; display: flex; justify-content: space-between; align-items: center; padding: 0 15px;">
        <i class="el-icon-s-unfold" style="font-size: 20px;"></i>
        <span style="margin-left: auto;">{{ user.name }}</span>
      </el-header>
      <router-view />
    </el-container>
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
      tableData: [],
      total: 0,
      currentPage: 1,
      pageNum: 1,
      pageSize: 5,
      userJudge: 0,
      user:JSON.parse(localStorage.getItem('honey-user')),
      courseName:""

    }
  },
  created() {
    // this.load()
    if (localStorage.getItem('honey-user')) {
      const user = JSON.parse(localStorage.getItem('honey-user'));
      if (user.role === "teacher") {
        this.userJudge = 1
      }else if (user.role === "administrator"){
        this.userJudge = 2
      }
    }
  },
  methods: {
    load(){
      this.request.get("/course/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          name:this.courseName
        }
      }).then(res =>{
        console.log(res)
        this.tableData=res.data
        this.total=res.total
      })

    },
    handleTableFileUpload(row,file,fileList){
      row.syllabus = file.response.data
      console.log(file.response.data)
      request.post("/course",row).then(res =>{
        if (res){
          this.$message.success("save success")
        }else {
          this.$message.error("save failed")
        }
      })
    },
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
    download(row){
      console.log(row.syllabus)
      window.open(row.syllabus)

    }
  }
};
</script>
