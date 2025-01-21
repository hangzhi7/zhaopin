<template>
  <div id="postResume">
    <el-button v-show="checking" @click="checking = false" type="primary"
      >返回</el-button
    >
    <el-table v-show="!checking" :data="jobList" stripe style="width: 100%">
      <el-table-column prop="applicationId" label="ID" width="180">
      </el-table-column>
      <el-table-column label="投递状态" width="110">
        <template slot-scope="scope">
          {{ scope.row.applicationState | stateFilter }}
        </template>
      </el-table-column>
      <el-table-column label="投递时间" width="100">
        <template slot-scope="scope">
          {{ scope.row.recentTime | formatDate }}
        </template>
      </el-table-column>
      <el-table-column prop="positionId" label="岗位编号" width="100">
      </el-table-column>
      <el-table-column prop="reply" label="答复"> </el-table-column>
      <el-table-column fixed="right" label="操作" width="300">
        <template slot-scope="scope">
          <el-button @click="checkMsg(scope.row)" type="primary" size="mini"
            >查看投递信息</el-button
          >
          <el-button @click="deal(scope.row, false)" type="primary" size="mini"
            >接受</el-button
          >
          <el-button @click="deal(scope.row, true)" type="danger" size="mini"
            >拒绝</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div
      v-show="checking"
      style="display: flex; justify-content: center; align-items: center"
    >
      <div style="border: 1px solid #bfbfbf; width: 70%; margin: 40px">
        <el-form ref="userForm" :model="userForm" label-width="120px">
          <el-form-item> </el-form-item>
          <el-form-item>
            <el-col :span="6" :offset="6">
              <el-avatar
                id="avatar"
                :shape="'square'"
                :size="120"
                :src="'http://121.4.140.112:8080/api/user/' + userForm.avatar"
              ></el-avatar>
            </el-col>
          </el-form-item>
          <el-form-item label="基本信息">
            <el-row></el-row>
            <el-row>
              <el-col :span="12" class="item-title">姓名:</el-col>
              <el-col :span="12">{{ userForm.nickname }}</el-col>
              <el-col :span="12" class="item-title">性别:</el-col>
              <el-col :span="12">{{ userForm.gender ? '男' : '女' }}</el-col>
              <el-col :span="12" class="item-title">联系电话:</el-col>
              <el-col :span="12">{{ userForm.mobile }}</el-col>
              <el-col :span="12" class="item-title">邮箱地址:</el-col>
              <el-col :span="12">{{ userForm.email }}</el-col>
              <el-col :span="12" class="item-title">出生年份:</el-col>
              <el-col :span="12">{{ userForm.birthYear }}(年)</el-col>
              <el-col :span="12" class="item-title">毕业院校:</el-col>
              <el-col :span="12">{{ userForm.graduation }}</el-col>
              <el-col :span="12" class="item-title">学历:</el-col>
              <el-col :span="12">{{ userForm.eduDegree }}</el-col>
              <el-col :span="12" class="item-title">毕业年份:</el-col>
              <el-col :span="12">{{ userForm.graduateYear }}</el-col>
              <el-col :span="12" class="item-title">专业:</el-col>
              <el-col :span="12">{{ userForm.major }}</el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="工作期望">
            <el-row></el-row>
            <el-row>
              <el-col :span="12" class="item-title">期望薪资:</el-col>
              <el-col :span="12">{{ userForm.dirDesire }}</el-col>
              <el-col :span="12" class="item-title">期望岗位:</el-col>
              <el-col :span="12">{{ resumeform.jobDesire }}</el-col>
              <el-col :span="12" class="item-title">工作城市:</el-col>
              <el-col :span="12"
                >{{ userForm.province }} {{ userForm.city }}</el-col
              >
            </el-row>
          </el-form-item>
        </el-form>
        <el-form ref="resumeform" :model="resumeform" label-width="120px">
          <el-form-item label="工作技能和证书">
            <el-row></el-row>
            <el-row>
              <el-col :span="12" class="item-title">证书:</el-col>
              <el-col :span="12">{{ resumeform.certificate }}</el-col>
              <el-col :span="12" class="item-title">技能:</el-col>
              <el-col :span="12">{{ resumeform.ability }}</el-col>
              <el-col :span="24" class="item-title">获得荣誉与奖励:</el-col>
              <el-col :span="24">
                <el-row v-for="(item, index) in honor" :key="index">
                  <el-row>
                    <el-col :span="8">{{ item.time }}</el-col>
                    <el-col :span="8">{{ item.reword }}</el-col>
                    <el-col :span="8">{{ item.level }}</el-col>
                  </el-row>
                </el-row>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="工作经验和履历">
            <el-row></el-row>
            <el-row>
              <el-col :span="24" class="item-title">实习经验:</el-col>
              <el-col :span="24">{{ resumeform.internshipExperience }}</el-col>
              <el-col :span="24" class="item-title">工作经历:</el-col>
              <el-col :span="24">
                <el-row v-for="(item, index) in work" :key="index">
                  <el-row>
                    <el-col :span="8"
                      >{{ item.time[0] }}至{{ item.time[1] }}</el-col
                    >
                    <el-col :span="8">{{ item.company }}</el-col>
                    <el-col :span="8">{{ item.job }}</el-col>
                    <el-col :span="4">责任描述:</el-col>
                    <el-col :span="20">{{ item.description }}</el-col>
                  </el-row>
                </el-row>
              </el-col>
              <el-col :span="24" class="item-title">项目经验:</el-col>
              <el-col :span="24">
                <el-row v-for="(item, index) in project" :key="index">
                  <el-row>
                    <el-col :span="12"
                      >{{ item.time[0] }}至{{ item.time[1] }}</el-col
                    >
                    <el-col :span="12">{{ item.job }}</el-col>
                    <el-col :span="4">项目描述:</el-col>
                    <el-col :span="20">{{ item.project }}</el-col>
                    <el-col :span="4">责任描述:</el-col>
                    <el-col :span="20">{{ item.description }}</el-col>
                  </el-row>
                </el-row>
              </el-col>
              <el-col :span="24" class="item-title">自我评价:</el-col>
              <el-col :span="24">{{ resumeform.selfAssessment }}</el-col>
            </el-row>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <el-dialog title="回复" :visible.sync="dialogVisible" width="30%" center>
      <el-input type="textarea" :rows="2" v-model="reply" />
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  receiveApplication,
  allowApplication,
  refuseApplication,
  getApplicMsg,
} from '@/api/hr'

export default {
  name: 'postResume',
  data() {
    return {
      row: {},
      type: '',
      reply: '',
      delivers: [],
      jobList: [],
      checking: false,
      userForm: {
        avatar: 'default',
        nickname: '',
        email: '',
        gender: 0,
        birthYear: 0,
        graduation: '',
        eduDegree: '',
        graduateYear: 2019,
        major: '',
        dirDesire: 0,
        province: '',
        city: '',
      },
      resumeform: {},
      options: [
        {
          value: 0,
          label: '女',
        },
        {
          value: 1,
          label: '男',
        },
      ],
      dialogVisible: false,
    }
  },
  created() {
    this.getList()
  },
  computed: {
    honor() {
      if (
        this.resumeform.honour === '' ||
        this.resumeform.honour === undefined ||
        this.resumeform.honour === null
      ) {
        return []
      } else {
        return JSON.parse(this.resumeform.honour)
      }
    },
    work() {
      if (
        this.resumeform.workExperience === '' ||
        this.resumeform.workExperience === undefined ||
        this.resumeform.workExperience === null
      ) {
        return []
      } else {
        return JSON.parse(this.resumeform.workExperience)
      }
    },
    project() {
      if (
        this.resumeform.projectExperience === '' ||
        this.resumeform.projectExperience === undefined ||
        this.resumeform.projectExperience === null
      ) {
        return []
      } else {
        return JSON.parse(this.resumeform.projectExperience)
      }
    },
  },
  methods: {
    deal(row, type) {
      this.row = row
      this.type = type
      this.dialogVisible = true
    },
    save() {
      this.dialogVisible = false
      this.row.reply = this.reply
      if (this.type) {
        refuseApplication(this.row).then((res) => {
          if (res.data) {
            this.$message.success('处理成功')
            this.getList()
          }
        })
      } else {
        allowApplication(this.row).then((res) => {
          if (res.data) {
            this.$message.success('处理成功')
            this.getList()
          }
        })
      }
    },
    checkMsg(row) {
      this.checking = true
      // 获取信息
      getApplicMsg(row).then((res) => {
        this.userForm = res.data.user
        this.resumeform = res.data.resume
      })
    },
    getList() {
      this.jobList = []
      receiveApplication().then((res) => {
        this.jobList = res.data
      })
    },
  },
  filters: {
    stateFilter(state) {
      if (state === 1) return '已接受'
      if (state === 2) return '已拒绝'
      return '未处理'
    },
  },
}
</script>

<style scoped>
#postResume {
  overflow: auto;
}
.item-title{
  color: #ff6000;
}
</style>
