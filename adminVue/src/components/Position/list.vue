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
          prop="positionId"
          label=ID
          width="180">
      </el-table-column>
      <el-table-column
          label="编辑日期"
          width="180">
        <template slot-scope="scope">
          {{scope.row.releaseDate}}
        </template>
      </el-table-column>
      <el-table-column
          label="岗位名称"
          width="180">
        <template slot-scope="scope">
          {{scope.row.title||'未填写'}}
        </template>
      </el-table-column>
      <el-table-column
          prop="requirement"
          label="岗位要求">
      </el-table-column>
      <el-table-column
          label="学历要求"
          width="180">
        <template slot-scope="scope">
          {{educations[scope.row.educationId]}}
        </template>
      </el-table-column>
      <el-table-column
          prop="quantity"
          label="招聘人数">
      </el-table-column>
      <el-table-column
          prop="workCity"
          label="工作城市">
      </el-table-column>
      <el-table-column
        label="薪资">
        <template slot-scope="scope">
          {{ scope.row.salaryDown&&scope.row.salaryUp?scope.row.salaryDown + '-' + scope.row.salaryUp:'未填写'}}
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          {{scope.row.statePub?'已发布':'未发布'}}
        </template>
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
  import { listCategory, listEducation, listPosition, delPosition} from '@/api/manager'

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
          educations: {}
        }
      },
      created: function () {
        this.userinfo = JSON.parse(sessionStorage.getItem("user")) 
        this.listCategory()
        this.listEducation()
        this.getListData()
      },
      methods:{
        listCategory() {
          listCategory()
          .then(res=>{
            this.categorys = res.data
          })
        },
        listEducation() {
          listEducation()
          .then(res=>{
            this.educations={}
            res.data.forEach(item=>{
              this.educations[item.id] = item.name
            })
          })
        },
        getListData(){
          listPosition()
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
            delPosition(row.positionId)
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
      }
    }
</script>

<style scoped>

</style>
