<template>
    <el-container style="height: auto">
      <el-main  style="height: auto; overflow-y: auto;border: 20px solid #eee">
        <div style="display: flex; align-items: center;">
          <el-input v-model="courseName" style="margin-right: 5px; width: 200px " placeholder="input the name for search" suffix-icon="el-icon-search" ></el-input>
          <el-button type="primary" @click="load">search</el-button>
        </div>
        <el-table :data="tableData">
          <el-table-column prop="course_id" label="Course_ID" width="150"></el-table-column>
          <el-table-column prop="name" label="Course_Name" width="150"></el-table-column>
          <el-table-column prop="activity_time" label="Planned_Hours"></el-table-column>
          <el-table-column prop="start_time" label="Start_Time"></el-table-column>
          <el-table-column prop="finish_time" label="Finish_Time"></el-table-column>
          <el-table-column prop="description" label="Details"></el-table-column>
          <el-table-column label="Syllabus">
            <template slot-scope="scope">
              <el-button prop="syllabus" @click="download(scope.row)" type="text" size="small">Download</el-button>
              <el-upload
                  class="upload-demo"
                  action="http://192.168.1.104:9090/file/upload"
                  :on-success="(row,file,fileList) => handleTableFileUpload(scope.row , file, fileList)">
                <el-button size="small" type="text" v-if="userJudge">Upload</el-button>
              </el-upload>
            </template>
          </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination
              layout="prev, pager, next"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :page-size="pageSize"
              :total="total">
          </el-pagination>
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

      tableData: [],
      total: 0,
      currentPage: 1,
      pageNum: 1,
      pageSize: 5,
      userJudge: false,
      courseName:""

    }
  },
  created() {
    this.load()
    if (localStorage.getItem('honey-user')) {
      const user = JSON.parse(localStorage.getItem('honey-user'));
      if (user.role === "teacher") {
        this.userJudge = true
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
