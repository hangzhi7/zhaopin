<template>
  <div>
    <el-row v-for="(item, index) in projectExperience" :key="index">
      <el-row v-show="projectExperienceIndex !== index">
        <el-col :span="18">
          <el-col :span="12">{{ item.time[0] }}至{{ item.time[1] }}</el-col>
          <el-col :span="12">{{ item.job }}</el-col>
          <el-col :span="4">项目描述</el-col>
          <el-col :span="20">{{ item.project }}</el-col>
          <el-col :span="4">责任描述</el-col>
          <el-col :span="20">{{ item.description }}</el-col>
        </el-col>
        <el-col :span="6">
          <i
            class="el-icon-edit-outline hover-btn"
            @click="editProjectExperience(index)"
            >编辑</i
          >
          <i
            class="el-icon-remove-outline hover-btn"
            @click="delProjectExperience(index)"
            >删除</i
          >
        </el-col>
      </el-row>
      <el-row v-if="projectExperienceIndex === index">
        <div style="border: 1px solid #ff6000;padding:10px;">
          <el-row>
            <el-col :span="8">时间</el-col>
            <el-col :span="16">
              <el-date-picker
                value-format="yyyy-M-d"
                size="small"
                v-model="projectExperienceItem.time"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              >
              </el-date-picker>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">工作</el-col>
            <el-col :span="16">
              <el-input size="small" v-model="projectExperienceItem.job" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">项目描述</el-col>
            <el-col :span="16">
              <el-input size="small" type="textarea" :rows="3" v-model="projectExperienceItem.project" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">责任描述</el-col>
            <el-col :span="16">
              <el-input size="small" type="textarea" :rows="3" v-model="projectExperienceItem.description" />
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
                v-model="projectExperienceItem.time"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              >
              </el-date-picker>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">工作</el-col>
            <el-col :span="16">
              <el-input size="small" v-model="projectExperienceItem.job" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">项目描述</el-col>
            <el-col :span="16">
              <el-input size="small" type="textarea" :rows="3" v-model="projectExperienceItem.project" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">责任描述</el-col>
            <el-col :span="16">
              <el-input size="small" type="textarea" :rows="3" v-model="projectExperienceItem.description" />
            </el-col>
          </el-row>
          <el-button type="primary" size="small" @click="save()">保存</el-button>
          <el-button size="small" @click="cancel()">取消</el-button>
        </div>
      </el-row>
    <i
      class="el-icon-circle-plus-outline hover-btn"
      v-show="!adding&&projectExperienceIndex===''"
      @click="addProjectExperience()"
      >添加</i
    >
  </div>
</template>

<script>
export default {
  name: 'project',
  props: {
    experience: {
      require: true,
      default: '',
    }
  },
  watch: {
    experience() {
      if (this.experience === '' || this.experience=== undefined|| this.experience=== null) {
        this.projectExperience = []
      } else {
        this.projectExperience = JSON.parse(this.experience)
      }
    }
  },
  data() {
    return {
      projectExperienceIndex: '',
      adding: false,
      projectExperienceItem: {
        time: ['', ''],
        job: '',
        project: '',
        description: '',
      },
      projectExperience: [
      ],
    }
  },
  methods: {
    save() {
      if(this.projectExperienceItem.time[0] === '' || this.projectExperienceItem.time[1] === ''|| this.projectExperienceItem.project === ''|| this.projectExperienceItem.job === ''|| this.projectExperienceItem.description === '') {
        this.$message.error('请完成表单')
        return
      }
      if(this.adding) {
        this.projectExperience.push(this.projectExperienceItem)
        this.adding = false
        this.$emit('input', JSON.stringify(this.projectExperience))
        return
      }
      this.projectExperience[this.projectExperienceIndex] = JSON.parse(JSON.stringify(this.projectExperienceItem))
      this.projectExperienceIndex = ''
      this.$emit('input', JSON.stringify(this.projectExperience))
    },
    cancel() {
      this.adding=false
      this.projectExperienceIndex = ''
    },
    addProjectExperience() {
      this.adding = true
      this.projectExperienceItem = {
        time: ['', ''],
        project: '',
        job: '',
        description: '',
      }
    },
    editProjectExperience(index) {
      this.projectExperienceIndex = index
      this.projectExperienceItem.time = this.projectExperience[index].time
      this.projectExperienceItem.project = this.projectExperience[index].project
      this.projectExperienceItem.job = this.projectExperience[index].job
      this.projectExperienceItem.description = this.projectExperience[index].description
    },
    delProjectExperience(index) {
      this.projectExperience.splice(index, 1)
      this.$emit('input', JSON.stringify(this.worprojectExperienceExperience))
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
