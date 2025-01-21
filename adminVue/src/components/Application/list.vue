<template>
  <el-card class="box-card" shadow="always">
    <div slot="header">
      <span>岗位列表</span>
    </div>
    <el-table
        :data="list.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        stripe
        style="width: 100%">
      <el-table-column
          prop="applicationId"
          label=ID
          width="180">
      </el-table-column>
      <el-table-column
          label="编辑日期"
          width="180">
        <template slot-scope="scope">
          {{scope.row.recentTime}}
        </template>
      </el-table-column>
      <el-table-column
          label="应聘状态"
          width="180">
        <template slot-scope="scope">
          {{scope.row.applicationState?'已聘用':'未聘用'}}
        </template>
      </el-table-column>
      <el-table-column
          prop="resumeId"
          label="简历编号">
      </el-table-column>
      <el-table-column
          prop="positionId"
          label="招聘编号">
      </el-table-column>
      <el-table-column
          prop="hrId"
          label="企业编号">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="210">
        <template slot-scope="scope">
          <el-button @click="handleDel(scope.row)"  type="danger" size="mini">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
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

    </div>

  </el-card>
</template>

<script>
  import { listApplication, delApplication} from '@/api/manager'

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
          userinfo:{},
          categorys: [],
          educations: []
        }
      },
      created: function () {
        this.userinfo = JSON.parse(sessionStorage.getItem("user")) 
        this.getListData()
      },
      methods:{
        getListData(){
          listApplication()
          .then(res=>{
            this.list = res.data
          })
          
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
        handleDel(row){
          this.$confirm('此操作将永久删除该岗位, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            delApplication(row.applicationId)
            .then(res => {
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
        educationFilter(id) {
          this.educations.forEach(item=>{
            if(item.id===id) return item.name
          })
          return '不限'
        },
      }
    }
</script>

<style scoped>

</style>
