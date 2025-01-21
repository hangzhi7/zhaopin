<template>
  <el-card class="box-card" shadow="always">
    <div slot="header">
      <span>职业分类</span>
    </div>
    <el-row style="text-align:left">
      一级分类：
      <el-select @change="changeFirst" v-model="first" placeholder="请选择">
        <el-option
          v-for="item in firstOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-row>
    <el-table
      ref="multipleTable"
      :loading="listLoading"
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
        prop="secondCategoryName"
        label="二级分类"/>
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
        <el-button type="primary" @click="add()">添加二级分类</el-button>
      </div>

    </div>
    <el-dialog
      :visible.sync="showDialog"
      width="30%"
      center>
      <el-row>
        分类名：
        <el-input v-model="second"/>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showDialog = false">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </span>
    </el-dialog>
  </el-card>
</template>

<script>
  import { listCategory,createCategory,updateCategory, delCategory } from "@/api/manager";

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
          firstOptions: [
            {
              value: '餐饮服务',
              label: '餐饮服务'
            }, {
              value: '人事/财政/财务',
              label: '人事/财政/财务'
            }, {
              value: '超市/零售',
              label: '超市/零售'
            }, {
              value: '互联网/IT',
              label: '互联网/IT'
            }, {
              value: '销售/采购',
              label: '销售/采购'
            }, {
              value: '家政/保洁',
              label: '家政/保洁'
            }, {
              value: '物流/仓储/司机',
              label: '物流/仓储/司机'
            }, {
              value: '运动/健身',
              label: '运动/健身'
            }
          ],
          categoryId: '',
          first: '',
          second: '',
          showDialog: false,
          editing: false,
          origin: []
        }
      },
      created: function () {
        this.getListData()
      },
      methods:{
        add() {
          this.second = ''
          if(this.first === '') return
          this.showDialog = true
          this.editing = false
        },
        edit(row) {
          this.categoryId = row.categoryId
          this.second = row.secondCategoryName
          if(this.first === '') return
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
            updateCategory(this.second, this.categoryId)
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
          createCategory(this.first,this.second, '默认')
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
        changeFirst() {
          console.log(1111)
          this.list = []
          this.origin.forEach(item=>{
            if(item.firstCategoryName === this.first) this.list.push(item)
          })
        },
        getListData(){
          listCategory()
          .then(res => {
            this.origin = res.data
            this.changeFirst()
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
          this.$confirm('此操作将删除该分类, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            delCategory(row.categoryId)
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
