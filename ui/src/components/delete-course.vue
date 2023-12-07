<template>
  <el-container style="height: auto; border: 15px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246); height: auto">
      <el-menu :default-openeds="['1', '2']" style="height: 100%" @select="handleSelect">
        <el-submenu><template slot="title"><i class="el-icon-s-home"></i>COURSE</template>
          <el-menu-item index="/teacher-course">
            <template slot="title">VIEW COURSE</template>
          </el-menu-item >
          <el-menu-item index="/add-course" >
            <template slot="title">ADD  COURSE</template>
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
        <el-table :data="tableData">
          <el-table-column prop="course_id" label="Course_ID" width="150"></el-table-column>
          <el-table-column prop="name" label="Course_Name" width="150"></el-table-column>
          <el-table-column prop="activity_time" label="Planned_Hours"></el-table-column>
          <el-table-column prop="start_time" label="Start_Time"></el-table-column>
          <el-table-column prop="finish_time" label="Finish_Time"></el-table-column>
          <el-table-column
              fixed="right"
              label="Actions"
              width="150">
            <template slot-scope="scope">
              <el-popconfirm
                  style="margin-right: 10px "
                  confirm-button-text='YES'
                  cancel-button-text='NO'
                  title="SURE TO DELETE IT?"
                  @confirm="del(scope.row.course_id)"
              >
                <el-button slot="reference" type="text" size="small" >DELETE</el-button>
              </el-popconfirm>
              <el-button @click="handleUpdate(scope.row)" type="text" size="small">UPDATE</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog title="Course Information" :visible.sync="dialogFormVisible">
          <el-form label-width="120px">
            <el-form-item label="COURSE_ID" >
              <el-input v-model="form.course_id" :disabled="true" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="COURSE_NAME" >
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="PLANNED_HOURS" >
              <el-input v-model="form.activity_time" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="START_TIME" >
              <el-input v-model="form.start_time" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="FINISH_TIME" >
              <el-input v-model="form.finish_time" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="DETAILS" >
              <el-input v-model="form.description" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">cancel</el-button>
            <el-button type="primary" @click="update">complete</el-button>
          </div>
        </el-dialog>
      </el-main>
      <el-pagination
          layout="prev, pager, next"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-size="pageSize"
          :total="total">
      </el-pagination>
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
import MyInfo from "@/components/my-info.vue";
import request from "@/utils/request";

export default {
  components: {MyInfo},
  data() {
    return {
      tableData: [],
      total: 0,
      currentPage: 1,
      pageNum: 1,
      pageSize: 5,
      form: {},
      dialogFormVisible: false,
      course_id:"",
      name:"",
      start_time: "",
      finish_time: "",
      description: "",
      activity_time: ""

    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      this.request.get("/course/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize


        }
      }).then(res =>{
        console.log(res)
        this.tableData=res.data
        this.total=res.total
      })

    },
    update(){
      request.post("/course",this.form).then(res =>{
        if (res){
          this.$message.success("save success")
          this.dialogFormVisible = false
          this.load()
        }else {
          this.$message.error("save failed")
        }
      })
    },
    del(id){
      request.delete("/course/"+id).then(res =>{
        if (res){
          this.$message.success("delete success")
          this.load()
        }else {
          // this.$message.error("delete failed")
        }
      })
    },
    handleSelect(val){
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
    handleUpdate(row){
      this.form =row
      this.dialogFormVisible = true
    }
  }
};
</script>
