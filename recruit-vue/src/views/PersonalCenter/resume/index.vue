<template>
  <div id="resume">
    <el-form
      style="width: 600px"
      ref="userForm"
      :model="resume"
      label-position="top"
      label-width="200px"
    >
      <el-form-item> </el-form-item>
      <el-form-item prop="ability" label="技能">
        <el-input v-model="resume.ability" />
      </el-form-item>
      <el-form-item prop="honour" label="获得荣誉与奖励">
        <honor :experience="resume.honour" @input="getHonor" />
      </el-form-item>
      <el-form-item prop="certificate" label="证书">
        <el-input v-model="resume.certificate" />
      </el-form-item>
      <el-form-item prop="jobDesire" label="工作期望">
        <el-input v-model="resume.jobDesire" />
      </el-form-item>
      <el-form-item prop="internshipExperience" label="实习经验">
        <el-input v-model="resume.internshipExperience" />
      </el-form-item>
      <el-form-item prop="workExperience" label="工作经历">
        <work :experience="resume.workExperience" @input="getWorkExperience"/>
      </el-form-item>
      <el-form-item prop="projectExperience" label="项目经验">
        <project :experience="resume.projectExperience" @input="getProjectExperience"/>
      </el-form-item>
      <el-form-item prop="selfAssessment" label="自我评价">
        <el-input v-model="resume.selfAssessment" type="textarea" :rows="2"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { showResume, updateResume } from '@/api/user'
import work from './work'
import project from './project'
import honor from './honor'
export default {
  name: 'myConcern',
  components: {
    work,
    project,
    honor
  },
  data() {
    return {
      resume: {},
    }
  },

  mounted() {
    this.getData()
  },
  methods: {
    getWorkExperience(data) {
      this.resume.workExperience = data
    },
    getProjectExperience(data) {
      this.resume.projectExperience = data
    },
    getHonor(data) {
      this.resume.honour = data
    },
    getData() {
      showResume().then((res) => {
        this.resume = res.data.resume
      })
    },
    onSubmit() {
      updateResume(this.resume).then((res) => {
        if (res.data) {
          this.$message.success('更新简历成功')
          this.getData()
          return
        }
        this.$message.error('更新简历失败')
      })
    },
  },
}
</script>

<style lang="stylus" scoped>
fontColor = rgb(109, 109, 109);
/deep/ .el-form--label-top .el-form-item__label{
  color: #ff6000;
}
#resume {
  width: 70%;
  margin-top: 30px;
  margin-bottom: 30px;
  margin-left: 15%;
  display: flex;
  justify-content: center
  box-shadow: 0 2px 4px #e0e0e0;
}

#noBody {
  display: flex;
  justify-content: center;
  align-items: center;
  color: fontColor;
}

.isNull {
  box-shadow: 0 0 1px gray;
  width: 100%;
  height: 120px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: fontColor;

  &:hover {
    box-shadow: 0 0 2px gray;
  }
}

.dynamic {
  width: 65%;
  margin: 10px;
  box-sizing: border-box;
  padding: 10px;
}

.notNull {
  box-sizing: border-box;
  padding: 15px;
  width: 100%;
  display: flex;
  box-shadow: 0 0 1px gray;
  flex-direction: column;
  justify-content: space-between;
  word-break: break-all;

  &:hover {
    box-shadow: 0 0 2px gray;
  }

  & header {
    width: 100%;
    display: flex;
    justify-content: space-between;
  }

  & article {
    margin-top: 10px;
    font-size: 14px;
    width: 100%;
    color: fontColor;
  }

  & footer {
    width: 100%;
    height: 24px;
    display: flex;
    align-items: flex-end;
    justify-content: space-between;

    & i {
      width: 80px;
      cursor: pointer;
      display: flex;
    }
  }
}
</style>
