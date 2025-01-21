<template>
  <div id="jobManagement">
    <el-dialog fullscreen title="岗位编辑" :visible.sync="dialogFormVisible">
      <div style="display: flex;justify-content: space-between">
        <el-form ref="ruleForm" :label-position="labelPosition" style="width: 40%" :model="position">
          <el-form-item prop="title"
                        :rules="[{required:true,message:'请输入岗位名称'}]"
                        label="岗位名称" :label-width="formLabelWidth">
            <el-input size="mini" v-model="position.title" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item prop="categoryId"
                        label="岗位类别"
                        :rules="[{required:true,message:'请输入岗位类别'}]"
                        :label-width="formLabelWidth">
            <!-- <el-input size="mini" v-model="position.categoryId" autocomplete="off"></el-input> -->
            <el-cascader
              v-model="value"
              :options="options">
            </el-cascader>
          </el-form-item>
          <el-form-item prop="requirement"
                        label="岗位要求"
                        :rules="[{required:true,message:'请输入岗位要求'}]"
                        :label-width="formLabelWidth">
            <el-input size="mini" v-model="position.requirement" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item prop="education"
                        label="学历要求"
                        :label-width="formLabelWidth">
            <el-select v-model="position.educationId">
              <el-option v-for="option in educations" :key="option.id" :label="option.name" :value="option.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="quantity"
                        label="需要人数"
                        :rules="[{required:true,message:'请输入需要人数'}]"
                        :label-width="formLabelWidth">
            <el-input-number size="mini" v-model="position.quantity" autocomplete="off" />
          </el-form-item>
          <el-form-item label="地址" :label-width="formLabelWidth">
            <el-select class="address" size="mini" v-model="selectPro" placeholder="省">
              <el-option
                  v-for="item in provinces"
                  :key="item.adcode"
                  :label="item.name"
                  :value="item.name">
              </el-option>
            </el-select>
            <el-select class="address" size="mini" v-model="selectCity" placeholder="市">
              <el-option
                  v-for="item in cities"
                  :key="item.adcode"
                  :label="item.name"
                  :value="item.name">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
                        label="薪酬"
                        :label-width="formLabelWidth">
            <el-input-number size="mini" v-model="position.salaryDown" autocomplete="off"/> - <el-input-number size="mini" v-model="position.salaryUp" autocomplete="off"/>
          </el-form-item>
        </el-form>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </div>
    </el-dialog>
    <el-button @click="addJobHandle()" size="mini">新增岗位</el-button>
    <el-table
        :data="jobList"
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
          {{scope.row.releaseDate|formatDate}}
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
          {{educationMap[scope.row.educationId]}}
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
          <el-button @click="handleClick(scope.row)" type="primary" size="mini">编辑</el-button>
          <el-button @click="publishSwitch(scope.row)" type="success"  size="mini">{{scope.row.statePub?"下架":"发布"}}</el-button>
          <el-button @click="deleteHandle(scope.row)"  type="danger" size="mini">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {publishPosition,showPosition, modifyPosition, delPosition, listCategory,listEducation} from '@/api/hr'
import {getProvinces} from "@/api/map";

export default {
  name: "jobManagement",
  data() {
    return {
      provinces:[],
      cities:[],
      areas:[],
      selectPro:'',
      selectCity:'',
      selectArea:'',
      labelPosition: 'left',
      position:{
        positionId:0,
        title: '',
        requirement:'',
        quantity: 0,
        workCity: '',
        workProvince: '',
        salaryUp: 0,
        salaryDown: 0,
        categoryId: 0,
      },
      jobList: [],
      dialogFormVisible: false,
      formLabelWidth: '120px',
      options: [],
      value: [],
      categorys: [],
      educations: [],
      educationMap: {}
    }
  },
  created() {
    // this.getList()
    this.getProvince()
    this.listCategory()
    this.listEducation()
  },
  watch: {
    selectPro: function () {
      this.getCity()
    },
  },
  methods:{
    listCategory() {
      const map = {}
      listCategory().then(res=> {
        this.categorys = res.data
        res.data.forEach(item=>{
          if(map[item.firstCategoryName]){
            map[item.firstCategoryName].push(item)
          } else {
            map[item.firstCategoryName] = []
            map[item.firstCategoryName].push(item)
          }
        })
        this.options=[]
        Object.keys(map).forEach(key=>{
          const option = {
            value: key,
            label: key,
            children: []
          }
          map[key].forEach(sec=>{
            const secOption = {
              value: sec.categoryId,
              label: sec.secondCategoryName
            }
            option.children.push(secOption)
          })
          this.options.push(option)
        })
      })
    },
    listEducation() {
      listEducation()
      .then(res=>{
        this.educations = res.data
        res.data.forEach(item=>{
          this.educationMap[item.id] = item.name
        })
        this.getList()
      })
    },
    save() {
      if (this.selectPro ==='') {
        this.$message.error('请选择省份')
        return
      }
      if (this.selectCity ==='') {
        this.$message.error('请选择城市')
        return
      }
      if (this.position.title ==='') {
        this.$message.error('请填写岗位名称')
        return
      }
      if (this.position.requirement ==='') {
        this.$message.error('请填写岗位要求')
        return
      }
      if (this.position.quantity ===0) {
        this.$message.error('请填写岗位需要人数')
        return
      }
      if (this.position.salaryUp<=this.position.salaryDown) {
        this.$message.error('请输入合理的薪酬区间')
        return
      }
      if(this.value.length===0) {
        this.$message.error('请选择类别')
        return
      }
      this.position.categoryId = this.value[1]
      this.position.workProvince = this.selectPro
      this.position.workCity = this.selectCity
      modifyPosition(this.position)
      .then( res=> {
        if(res.data) {
          this.$message.success("修改成功")
          this.dialogFormVisible = false
          return
        }
      })
    },
    getList(){
      showPosition().then(res=>{
        this.jobList=res.data
      })
    },
    handleClick(row){
      this.selectPro=row.workProvince
      this.selectCity=row.workCity
      this.position = row
      this.categorys.forEach(item=>{
        if(item.categoryId === row.categoryId) this.value = [item.firstCategoryName,item.categoryId]
      })
      console.log(this.value)
      this.dialogFormVisible=true
    },
    deleteHandle(row){
      delPosition(row).then(res=>{
        if(res.data){
          this.$message.success('删除成功')
          this.getList()
          return
        }
        this.$message.error('删除失败')
      })
    },
    publishSwitch(row){
      if (row.workProvince ==='') {
        this.$message.error('信息不全，无法发布，请完善岗位信息')
        return
      }
      if (row.workCity ==='') {
        this.$message.error('信息不全，无法发布，请完善岗位信息')
        return
      }
      if (row.title ==='') {
        this.$message.error('信息不全，无法发布，请完善岗位信息')
        return
      }
      if (row.requirement ==='') {
        this.$message.error('信息不全，无法发布，请完善岗位信息')
        return
      }
      if (row.quantity ===0) {
        this.$message.error('信息不全，无法发布，请完善岗位信息')
        return
      }
      if (row.salaryUp<=row.salaryDown) {
        this.$message.error('信息不全，无法发布，请完善岗位信息')
        return
      }
      row.statePub = row.statePub?0:1
      modifyPosition(row)
      .then( res=> {
        this.dialogFormVisible = false
        if(res.data) {
          this.$message.success("修改成功")
          this.dialogFormVisible = false
          return
        }
      })
    },
    addJobHandle(){
      const position = {
      }
      publishPosition(position).then(res=>{
        this.$message.success("新增岗位成功!请在岗位列表中编辑岗位或者发布~")
        this.getList()
      })
    },
    getProvince(){
      getProvinces('中国').then(res=>{
        this.provinces=res
      })
    },
    getCity(){
      getProvinces(this.selectPro).then(res=>{
        this.cities=res
        this.selectCity=this.cities[0].name
      })
    },
  }
}
</script>

<style lang="stylus" scoped>
#jobManagement
  padding 10px
  overflow-y scroll
  overflow-x hidden
  height 100%
.address
  width 30%
  margin-right 3%
</style>
