<template>
<section>
    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="">
      <el-form-item >
        <el-input v-model="formInline.user" placeholder="姓名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getUser">查询</el-button>
      </el-form-item>
    </el-form>
      <el-table
        :data="tableData"
        style="width: 100%"
        :default-sort = "{prop: 'date', order: 'descending'}"
        highlight-current-row
        v-loading = "loading"
        >
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <el-table-column
          prop="uuid"
          label="员工工号"
          sortable
         >
        </el-table-column>
        <el-table-column
          prop="username"
          label="员工姓名"
          >
        </el-table-column>
        <el-table-column
          prop="intime"
          label="员工入职时间"
          >
        </el-table-column>
          <el-table-column
          prop="outtime"
          label="员工离职时间"
          >
        </el-table-column>
         <el-table-column
        prop="status"
        label="员工权限">
        <template slot-scope="scope">
          <div v-if="scope.row.status=='1'">员工</div>
          <div v-if="scope.row.status=='2'">管理员</div>
        </template>
      </el-table-column>
          <el-table-column
          prop="userpwd"
          label="用户密码"
          >
        </el-table-column>
         <el-table-column width="155" label="操作" fixed="right">
        <template slot-scope="scope">
          <el-popover
            v-model="scope.row.visible2"
            placement="top"
            width="160">
            <p>确定删除吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" round @click="scope.row.visible2 = false">取消</el-button>
              <el-button type="danger" size="mini" round @click="handleDel(scope.row)">确定</el-button>
            </div>
            <el-button  slot="reference" :loading="scope.row.loading" size="mini" type="danger"  @click="handleDel(scope.row)">删除</el-button>
          </el-popover>
        </template>
      </el-table-column>
      </el-table>
</section>
</template>

<script>
  import axios from "axios";

export default {
  data() {
    return {
      formInline: {
        user: "",
        region: ""
      },
      filters: {
        name: ""
      },
      loading: false,
      users: [],
      tableData: [],
      currentRow: null
    };
  },
  methods: {
      getListData(){
          axios.post(
            "api/getUser"
          )
          .then(res => {
            this.tableData = res.data
            console.log(res)
          });
        },
    formatter(row, column) {
      return row.addr;
    },
    getUser() {
      this.loading = true;
      let name = this.formInline.user;
       axios
          .post(
            "api/queryUserAdmin" ,{username:name}
          )
          .then(res => {
            console.log(res);
            this.tableData = res.data
      this.loading = false;
          });    
    },
     handleDel(row){
         console.log(row)
          this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            axios
            .post("api/delmUser",{id:row.id})
            .then(response => {
              this.getUser()
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            })
            .catch(function(error) {
              console.log(error);
            });
         
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });          
          });
        },
  },
  mounted() {
    this.getListData();
  }
};
</script>

<style scoped>
.el-table .cell {
  text-align: left;
}
.demo-form-inline {
  /* display: flex;
  align-items: center;
  justify-content: flex-start; */
  padding: 10px;
  text-align: left;
  background-color: #f2f2f2;
}
.el-form-item {
  margin-bottom: 0;
}
</style>
