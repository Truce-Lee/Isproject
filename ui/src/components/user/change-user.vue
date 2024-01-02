<template>
  <el-container style="height: auto">
    <!-- First Table -->
    <el-main style="height: auto; overflow-y: auto; border: 20px solid #eee">
      <el-table :data="tableData1">
        <el-table-column prop="student_id" label="Student_ID" width=auto></el-table-column>
        <el-table-column prop="name" label="Student_Name" width=auto></el-table-column>
        <el-table-column prop="email" label="E-mail" width=auto></el-table-column>
        <el-table-column prop="role" label="Role" width=auto></el-table-column>
        <el-table-column label="Action">
          <template slot-scope="scope">
            <el-button prop="syllabus" @click="change(scope.row)" type="text" size="small">Change</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
            layout="prev, pager, next"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange1"
            :page-size="pageSize"
            :total="total1"
        ></el-pagination>
      </div>
    </el-main>

    <!-- Second Table -->
    <el-main style="height: auto; overflow-y: auto; border: 20px solid #eee">
      <el-table :data="tableData2">
        <el-table-column prop="teacher_id" label="Teacher_ID" width=auto></el-table-column>
        <el-table-column prop="name" label="Teacher_Name" width=auto></el-table-column>
        <el-table-column prop="email" label="E-mail" width=auto></el-table-column>
        <el-table-column prop="role" label="Role" width=auto></el-table-column>
        <el-table-column label="Action">
          <template slot-scope="scope">
            <el-button prop="syllabus" @click="change(scope.row)" type="text" size="small">Change</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
            layout="prev, pager, next"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange2"
            :page-size="pageSize"
            :total="total2"
        ></el-pagination>
      </div>
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

      tableData1: [],
      tableData2: [],
      total1: 0,
      total2: 0,
      currentPage: 1,
      pageNum1: 1,
      pageNum2: 1,
      pageSize: 5,
      userJudge: false

    }
  },
  created() {
    this.load()
    if (localStorage.getItem('honey-user')) {
      const user = JSON.parse(localStorage.getItem('honey-user'));
      if (user.role === "administrator") {
        this.userJudge = true
      }
    }

  },
  methods: {
    load(){
      this.request.get("/student/page",{
        params:{
          pageNum:this.pageNum1,
          pageSize:this.pageSize

        }
      }).then(res =>{
        console.log(res)
        this.tableData1=res.data
        this.total1=res.total
      })
      this.request.get("/teacher/page",{
        params:{
          pageNum:this.pageNum2,
          pageSize:this.pageSize

        }
      }).then(res =>{
        console.log(res)
        this.tableData2=res.data
        this.total2=res.total
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

    handleCurrentChange1(pageNum){
      this.pageNum1=pageNum
      this.load()
    },
    handleCurrentChange2(pageNum){
      this.pageNum2=pageNum
      this.load()
    },
    change(row){
      console.log(row.role)
      if (row.role === "teacher"){
        request.post("/student/change",row).then(res =>{
          if (res){
            console.log(res)
            this.$message.success("change success")
          }else {
            this.$message.error("change failed")
          }
        })
        request.delete("/teacher/"+row.teacher_id)
      }else if (row.role ==="student"){
        request.post("/teacher/change",row).then(res =>{
          if (res){
            console.log(res)
            this.$message.success("change success")
          }else {
            this.$message.error("change failed")
          }
        })
        request.delete("/student/"+row.student_id)
      }

    }
  }
};
</script>
