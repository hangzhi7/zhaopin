<template>
  <el-card class="box-card" shadow="always">
    <div slot="header">
      <span>岗位列表</span>
    </div>
    <el-table
      ref="multipleTable"
      :loading="listLoading"
      :data="list.slice((currentPage-1)*pagesize,currentPage*pagesize)"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column
        prop="positionId"
        label="岗位编号"/>
      <el-table-column
        prop="title"
        label="岗位名称"/>
      <el-table-column
        prop="requirement"
        width="255"
        label="岗位要求">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.requirement" placement="top-start">
         <div style="overflow: hidden;text-overflow: ellipsis; display: -webkit-box; -webkit-line-clamp: 3;-webkit-box-orient: vertical;">
           {{scope.row.requirement}}
         </div>
         </el-tooltip>
        </template>
        </el-table-column>
      <el-table-column
        prop="quantity" 
        label="岗位数量">
        <template
        ></template>
      </el-table-column>
      <el-table-column
        prop="workCity"
        label="城市">
      </el-table-column>
      <el-table-column
        prop="salaryUp"
        label="工资上限">
      </el-table-column>
      <el-table-column
        prop="salaryDown"
        label="工资下限">
      </el-table-column>
      <el-table-column
        prop="statePub"
        label="是否下架">
      </el-table-column>
      <el-table-column
        prop="categoryId"
        label="公司id">
      </el-table-column>
      <el-table-column width="155" label="操作" >
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
        <el-button type="primary" @click="navigateTo('add')">添加岗位</el-button>
      </div>

    </div>

  </el-card>
</template>

<script>
  import axios from "axios";

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
          multipleSelection: null,
          userinfo:{}
        }
      },
      created: function () {
        this.userinfo = JSON.parse(sessionStorage.getItem("user")) 
        this.getListData()
      },
      methods:{
        getListData(){
          axios.post(
            "api/queryApplication",
            {
              'hrId':this.userinfo.hrId
            }
          )
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
          console.log(row)
          this.$confirm('此操作将永久删除该岗位, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            axios
            .post("api/delPostion",{'positionId':row.positionId})
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
          localStorage.setItem("goodsInfo",JSON.stringify(row))
          this.$router.push({
            path: '/edit',
            params: {
              id: row.positionId
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
