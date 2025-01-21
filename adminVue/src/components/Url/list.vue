<template>
  <el-card class="box-card" shadow="always">
    <div slot="header">
      <span>友情链接管理</span>
    </div>
    <el-table
      ref="multipleTable"
      :loading="listLoading"
      :data="list.slice((currentPage-1)*pagesize,currentPage*pagesize)"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column
        align="center"
        prop="urlId"
        label="编号"/>
      <el-table-column
        align="center"
        prop="name"
        label="名称"/>
      <el-table-column
        align="center"
        prop="url"
        label="友情链接"/>
      <el-table-column width="155" label="操作" align="center" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button  slot="reference" :loading="scope.row.loading" size="mini" type="danger"  @click="handleDel(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 14px;">
      <el-pagination

        style="float: right"
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pagesize"
        layout="total, prev, pager, next, jumper"
        :total="list.length">
      </el-pagination>
      <div class="bottom">
        <el-button type="primary" @click="navigateTo('url/add')">添加友情链接</el-button>
      </div>

    </div>

  </el-card>
</template>

<script>
  import { listFriendUrl,delFriendUrl } from "@/api/manager";

  export default {
        name: "Guest",
      data(){
        return{
          currentPage: 1,
          pagesize: 5,
          list: [],
          visible2: false,
          loading: null,
          listLoading: false,
          multipleSelection: null
        }
      },
      created: function () {
        this.getListData()
      },
      methods:{
        getListData(){
          listFriendUrl()
          .then(res => {
            this.list = res.data
            console.log(res)
          });
        },
        handleSizeChange(val) {
          this.pagesize = val;
        },
        handleCurrentChange(val) {
          this.currentPage = val;
        },
        navigateTo(val){
          this.$router.push("/"+val)
        },
        massDeletion() {
        },
        handleDel(row){
          this.$confirm('此操作将永久删除该链接, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            delFriendUrl(row.urlId)
            .then(response => {
              this.getListData()
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
        handleEdit(index,row){
          localStorage.setItem("urlInfo",JSON.stringify(row))
          this.$router.push({
            path: '/url/edit',
            params: {
              id: row.urlId
            }
          })
        },
        toggleSelection(rows) {
          if (rows) {
            rows.forEach(row => {
              this.$refs.multipleTable.toggleRowSelection(row)
            })
          } else {
            this.$refs.multipleTable.clearSelection()
          }
        },
        handleSelectionChange(){
          this.multipleSelection = val
        },
      },
    }
</script>

<style scoped>

</style>
