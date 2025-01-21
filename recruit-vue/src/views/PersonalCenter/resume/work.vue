<template>
  <div>
    <el-row v-for="(item, index) in workExperience" :key="index">
      <el-row v-show="workExperienceIndex !== index">
        <el-col :span="6">{{ item.time[0] }}至{{ item.time[1] }}</el-col>
        <el-col :span="6">{{ item.company }}</el-col>
        <el-col :span="6">{{ item.job }}</el-col>
        <el-col :span="6">
          <i
            class="el-icon-edit-outline hover-btn"
            @click="editWorkExperience(index)"
            >编辑</i
          >
          <i
            class="el-icon-remove-outline hover-btn"
            @click="delWorkExperience(index)"
            >删除</i
          >
        </el-col>
        <el-col :span="24">{{ item.description }}</el-col>
      </el-row>
      <el-row v-if="workExperienceIndex === index">
        <div style="border: 1px solid #ff6000;padding:10px;">
          <el-row>
            <el-col :span="8">时间</el-col>
            <el-col :span="16">
              <el-date-picker
                value-format="yyyy-M-d"
                size="small"
                v-model="workExperienceItem.time"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              >
              </el-date-picker>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">公司</el-col>
            <el-col :span="16">
              <el-input size="small" v-model="workExperienceItem.company" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">工作岗位</el-col>
            <el-col :span="16">
              <el-input size="small" v-model="workExperienceItem.job" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">工作描述</el-col>
            <el-col :span="16">
              <el-input size="small" type="textarea" :rows="3" v-model="workExperienceItem.description" />
            </el-col>
          </el-row>
          <el-button type="primary" size="small" @click="save()">保存</el-button>
          <el-button size="small" @click="cancel()">取消</el-button>
        </div>
      </el-row>
    </el-row>
    <el-row v-if="adding">
        <div style="border: 1px solid #ff6000;padding:10px;">
          <el-row>
            <el-col :span="8">时间</el-col>
            <el-col :span="16">
              <el-date-picker
                value-format="yyyy-M-d"
                size="small"
                v-model="workExperienceItem.time"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              >
              </el-date-picker>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">公司</el-col>
            <el-col :span="16">
              <el-input size="small" v-model="workExperienceItem.company" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">工作岗位</el-col>
            <el-col :span="16">
              <el-input size="small" v-model="workExperienceItem.job" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">工作描述</el-col>
            <el-col :span="16">
              <el-input size="small" type="textarea" :rows="3" v-model="workExperienceItem.description" />
            </el-col>
          </el-row>
          <el-button type="primary" size="small" @click="save()">保存</el-button>
          <el-button size="small" @click="cancel()">取消</el-button>
        </div>
      </el-row>
    <i
      class="el-icon-circle-plus-outline hover-btn"
      v-show="!adding&&workExperienceIndex===''"
      @click="addWorkExperience()"
      >添加</i
    >
  </div>
</template>

<script>
export default {
  name: 'work',
  props: {
    experience: {
      require: true,
      default: '',
    }
  },
  watch: {
    experience() {
      if (this.experience === '' || this.experience=== undefined|| this.experience=== null) {
        this.workExperience = []
      } else {
        this.workExperience = JSON.parse(this.experience)
      }
    }
  },
  data() {
    return {
      workExperienceIndex: '',
      adding: false,
      workExperienceItem: {
        time: ['', ''],
        company: '',
        job: '',
        description: '',
      },
      workExperience: [
      ],
    }
  },
  methods: {
    save() {
      if(this.workExperienceItem.time[0] === '' || this.workExperienceItem.time[1] === ''|| this.workExperienceItem.company === ''|| this.workExperienceItem.job === ''|| this.workExperienceItem.description === '') {
        this.$message.error('请完成表单')
        return
      }
      if(this.adding) {
        this.workExperience.push(this.workExperienceItem)
        this.adding = false
        this.$emit('input', JSON.stringify(this.workExperience))
        return
      }
      this.workExperience[this.workExperienceIndex] = JSON.parse(JSON.stringify(this.workExperienceItem))
      this.workExperienceIndex = ''
      this.$emit('input', JSON.stringify(this.workExperience))
    },
    cancel() {
      this.adding=false
      this.workExperienceIndex = ''
    },
    addWorkExperience() {
      this.adding = true
      this.workExperienceItem = {
        time: ['', ''],
        company: '',
        job: '',
        description: '',
      }
    },
    editWorkExperience(index) {
      this.workExperienceIndex = index
      this.workExperienceItem.time = this.workExperience[index].time
      this.workExperienceItem.company = this.workExperience[index].company
      this.workExperienceItem.job = this.workExperience[index].job
      this.workExperienceItem.description = this.workExperience[index].description
    },
    delWorkExperience(index) {
      this.workExperience.splice(index, 1)
      this.$emit('input', JSON.stringify(this.workExperience))
    },
  },
}
</script>

<style scoped>
.hover-btn {
  border: 1px solid #757373;
  padding: 2px 5px;
  margin: 2px;
  border-radius: 2px;
  cursor: pointer;
  color: #757373;
}
</style>
