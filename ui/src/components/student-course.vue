<template>
  <el-container style="height: auto; border: 15px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246); height: auto">
      <el-menu :default-openeds="['1', '2']" style="height: 100%" @select="handleSelect">
        <el-menu-item index="/student-course" >
          <template slot="title"><i class="el-icon-s-home"></i>COURSE</template>
        </el-menu-item>
        <el-menu-item index="/my-info">
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
          <el-table-column prop="student_id" label="ID" width="150"></el-table-column>
          <el-table-column prop="name" label="Name" width="150"></el-table-column>
          <el-table-column prop="age" label="Age"></el-table-column>
        </el-table>

      </el-main>
      <el-pagination
          small
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-size="pageSize"
          :pager-count="pageNum"
          layout="prev, pager, next"
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

export default {
  components: {MyInfo},
  data() {

    return {
      tableData: [],
      total: 0,
      currentPage: 1,
      pageNum: 1,
      pageSize: 5

    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      fetch("http://192.168.1.109:9090/student/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize).then(res => res.json()).then(res =>{
        console.log(res)
        this.tableData = res.data
        this.total = res.total
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
    }
  }
};
</script>
