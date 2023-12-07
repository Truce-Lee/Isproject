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
        <el-form label-width="80px" :model="form">
          <el-form-item label="MY NAME">
          </el-form-item>
          <el-form-item label="MY EMIAL">

          </el-form-item>
          <el-form-item label="AGE">

          </el-form-item>
          <el-form-item label="PHONE">

          </el-form-item>
          <el-form-item label="INFORMATION">

          </el-form-item>

          <el-form-item>
            <!--            <el-button type="primary" size="small" @click="saveInfo()">保存</el-button>-->
          </el-form-item>
        </el-form>

      </el-main>
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
    const item = {
      date: '2016-05-02',
      name: 'test',
      address: 'test'
    };
    return {
      tableData: Array(15).fill(item),
      currentPage: 1
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
    }
  }
};
</script>
