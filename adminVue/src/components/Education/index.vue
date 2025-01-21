<template>
  <el-card class="box-card" shadow="always">
    <div slot="header">
      <span>学历参数</span>
    </div>
    <el-table
      ref="multipleTable"
      :loading="listLoading"
      :default-sort = "{prop: 'level', order: 'descending'}"
      :data="list.slice((currentPage-1)*pagesize,currentPage*pagesize)"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column
        label="序号"
        type="index"
        width="50"/>
      <el-table-column
        align="center"
        prop="name"
        label="名称"/>
      <el-table-column
        align="center"
        prop="level"
        sortable
        label="权值"/>
      <el-table-column label="操作" align="center" >
        <template slot-scope="scope">
          <el-button
            size="small"
            @click="edit(scope.row)">编辑</el-button>
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
        <el-button type="primary" @click="add()">添加</el-button>
      </div>

    </div>
    <el-dialog
      :visible.sync="showDialog"
      width="30%"
      center>
      <el-row>
        分类名：
        <el-input v-model="name"/>
      </el-row>
      <el-row>
        权值：
        <el-input-number v-model="level"/>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showDialog = false">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </span>
    </el-dialog>
  </el-card>
</template>

<script>
  import { listEducation,createEducation,updateEducation, delEducation } from "@/api/manager";

  export default {
        name: "Guest",
      data(){
        return{
          currentPage: 1,
          pagesize: 10,
          list: [],
          visible2: false,
          loading: null,
          listLoading: false,
          multipleSelection: null,
          id: '',
          name: '',
          level: '',
          showDialog: false,
          editing: false
        }
      },
      created: function () {
        this.getListData()
      },
      methods:{
        add() {
          this.level = ''
          this.name === ''
          this.showDialog = true
          this.editing = false
        },
        edit(row) {
          this.id = row.id
          this.name = row.name
          this.level = row.level
          this.showDialog = true
          this.editing = true
        },
        save() {
          if(this.second === '') {
            this.$message({
              type: 'error',
              message: '二级分类不能为空'
            });
            return
          }
          if(this.editing) {
            updateEducation(this.id, this.name, this.level)
            .then(res => {
            if(res.data){
              this.$message({
                type: 'success',
                message: '修改成功!'
              });
              this.editing=false
              this.showDialog=false
              this.getListData()
            }
            else {
              this.$message({
                type: 'error',
                message: '修改失败!'
              });
            }
          });
            return
          }
          createEducation(this.name,this.level, '默认')
          .then(res => {
            if(res.data){
              this.$message({
                type: 'success',
                message: '添加成功!'
              });
              this.getListData()
              this.editing=false
              this.showDialog=false
            }
            else {
              this.$message({
                type: 'error',
                message: '添加失败!'
              });
            }
          });
        },
        getListData(){
          listEducation()
          .then(res => {
            this.list = res.data
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
          this.$confirm('此操作将删除该参数, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            delEducation(row.id)
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
