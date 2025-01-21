<template>
  <div id="form-container">
    <h1>企业信息</h1>
    <br />
    <br />
    <transition name="slide-fade">
      <el-form
        v-if="active == 1"
        :model="company"
        style="width: 100%; height: 80%"
        ref="ruleForm"
        label-width="100px"
      >
        <el-form-item
          prop="companyName"
          status-icon
          label="公司名称"
          :rules="[
            {
              required: true,
              message: '请输入企业名称',
              trigger: ['blur', 'change'],
            },
          ]"
        >
          <el-input
            size="small"
            v-model="company.companyName"
            placeholder="请输入内容"
          ></el-input>
        </el-form-item>

        <el-form-item
          status-icon
          prop="hrMobile"
          label="联系电话"
          :rules="[
            { required: true, message: '请输入联系电话', trigger: 'blur' },
          ]"
        >
          <el-input
            size="small"
            placeholder="请输入联系电话"
            v-model="company.hrMobile"
          ></el-input>
        </el-form-item>

        <el-form-item
          status-icon
          prop="hrEmail"
          label="邮箱"
          :rules="[
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            {
              type: 'email',
              message: '请输入正确的邮箱地址',
              trigger: ['blur', 'change'],
            },
          ]"
        >
          <el-input
            size="small"
            placeholder="请输入邮箱"
            v-model="company.hrEmail"
          ></el-input>
        </el-form-item>

        <el-form-item
          prop="hrName"
          status-icon
          label="负责人"
          :rules="[
            {
              required: true,
              message: '请输入负责人',
              trigger: ['blur', 'change'],
            },
          ]"
        >
          <el-input
            size="small"
            v-model="company.hrName"
            placeholder="请输入内容"
          ></el-input>
        </el-form-item>

        <el-form-item
          prop="companyAddress"
          status-icon
          label="地址"
          :rules="[
            {
              required: true,
              message: '请选择地址',
              trigger: ['blur', 'change'],
            },
          ]"
        >
          <el-input
            size="small"
            v-model="company.companyAddress"
            placeholder="请输入详细地址"
          ></el-input>
        </el-form-item>

        <el-form-item
          prop="description"
          status-icon
          label="简介"
          :rules="[
            {
              required: true,
              message: '请输入公司简介',
              trigger: ['blur', 'change'],
            },
          ]"
        >
          <el-input
            size="small"
            v-model="company.description"
            placeholder="请输入内容"
          ></el-input>
        </el-form-item>

        <el-form-item
          status-icon
          prop="createTime"
          label="注册日期"
          :rules="[{ required: true, message: '请选择日期', trigger: 'blur' }]"
        >
          <el-date-picker
            size="small"
            type="date"
            placeholder="选择日期"
            :picker-options="pickerOptions"
            v-model="company.createTime"
          >
          </el-date-picker>
        </el-form-item>

        <el-form-item>
          <el-form-item>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
            <el-button @click="save()" type="primary">保存</el-button>
          </el-form-item>
        </el-form-item>
      </el-form>
    </transition>
  </div>
</template>

<script>
import { CodeToText, regionData } from 'element-china-area-data'
import { hrInfoShow, hrInfoUpdate } from '@/api/hr'
export default {
  name: 'regCompany',
  data() {
    return {
      options: regionData,
      selectedOptions: [],
      active: 1,
      detailAddress: '',
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now()
        },
        shortcuts: [
          {
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date())
            },
          },
          {
            text: '昨天',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24)
              picker.$emit('pick', date)
            },
          },
          {
            text: '一周前',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', date)
            },
          },
        ],
      },
      company: {
        companyName: '',
        companyAddress: '',
        createTime: '',
        description: '',
        hrName: '',
        hrEmail: '',
        hrMobile: '',
      },
    }
  },
  mounted() {
    this.getInfo()
  },
  methods: {
    getInfo() {
      hrInfoShow().then((res) => {
        if (res.data) {
          sessionStorage.setItem('hr', JSON.stringify(res.data.hr))
          this.company.hrId = res.data.hr.hrId
          this.company.companyName = res.data.hr.companyName
          this.company.companyAddress = res.data.hr.companyAddress
          this.company.createTime = res.data.hr.createTime.time
          this.company.description = res.data.hr.description
          this.company.hrName = res.data.hr.hrName
          this.company.hrEmail = res.data.hr.hrEmail
          this.company.hrMobile = res.data.hr.hrMobile
        }
      })
    },
    handleChange(value) {
      console.log(value)
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
      this.getInfo()
    },
    save() {
      hrInfoUpdate(this.company).then((res) => {
        if (res.data === 'success') {
          this.$message.success('保存成功')
          this.$router.push('/company/jobManage')
          return
        }
        this.$message.success('保存失败')
      })
    },
  },
}
</script>

<style scoped>
#form-container {
  overflow: hidden;
  width: 100%;
  height: 80%;
  box-shadow: 0 2px 4px gray;
  background-color: #fff;
  box-sizing: border-box;
  margin-top: 30px;
  padding: 30px 30px 300px 30px;
}
p {
  line-height: 30px;
}
#submit {
  position: fixed;
  left: 21%;
  bottom: 15%;
  width: 80px;
}
#success {
  width: 100%;
  height: 80%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
#material {
  padding: 10px;
  box-sizing: border-box;
  width: 100%;
  height: 80%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: gainsboro 1px dotted;
}
.slide-fade-enter-active {
  transition: all 0.2s ease;
  transition-delay: 0.2s;
}

.slide-fade-leave-active {
  transition: all 0.2s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter, .slide-fade-leave-to
  /* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateX(20px);
  opacity: 0;
}
</style>
