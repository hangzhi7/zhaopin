<template>
  <div id="deliveryList">
    <div id="main-list">
      <div>
        <h1 style="text-align: center">招聘详情</h1>
        <el-form
          style="width: 600px"
          ref="jobForm"
          label-position="top"
          :model="job"
          label-width="200px"
        >
          <el-form-item>
            <el-col :offset="2" :span="12" class="job-title">{{
              job.title
            }}</el-col>
            <el-col :span="10" class="job-salary"
              >{{ job.salaryDown }} ~ {{ job.salaryUp }}（元/月）</el-col
            >
            <el-col :offset="1" :span="23" class="job-yaoqiu">
              {{ job.workProvince }}-{{ job.workCity }}
              <a class="hengxian">|</a>
              招{{ job.quantity }}人
              <a class="hengxian">|</a>
              {{ educationMap[job.educationId] }}
            </el-col>
          </el-form-item>
          <el-divider></el-divider>
          <el-form-item>
            <el-col
              :offset="1"
              :span="23"
              style="
                border-left: 4px solid #ff6000;
                height: 20px;
                line-height: 20px;
              "
            >
              分类
            </el-col>
            <el-col :offset="1" :span="23">
              {{ categoryMap[job.categoryId] }}
            </el-col>
          </el-form-item>
          <el-divider></el-divider>
          <el-form-item>
            <el-col
              :offset="1"
              :span="23"
              style="
                border-left: 4px solid #ff6000;
                height: 20px;
                line-height: 20px;
              "
            >
              要求
            </el-col>
            <el-col :offset="1" :span="23">
              {{ job.requirement }}
            </el-col>
          </el-form-item>
          <el-divider></el-divider>
          <el-form-item>
            <el-col
              :offset="1"
              :span="23"
              style="
                border-left: 4px solid #ff6000;
                height: 20px;
                line-height: 20px;
              "
            >
              公司信息
            </el-col>
            <el-col :offset="1" :span="11">
              {{ company.companyName }}
            </el-col>
            <el-col :offset="1" :span="11">
              <i class="el-icon-location-information" />
              {{ company.companyAddress }}
            </el-col>
            <el-col :offset="1" :span="23" style="color: #ff6000">
              联系人及联系方式：
            </el-col>
            <el-col :offset="1" :span="6">
              <i class="el-icon-user" />
              {{ company.hrName }}
            </el-col>
            <el-col :span="6">
              <i class="el-icon-phone" />
              {{ company.hrMobile }}
            </el-col>
            <el-col :offset="1" :span="23">
              <i class="el-icon-message" />
              {{ company.hrEmail }}
            </el-col>
            <el-col :offset="1" :span="23" style="color: #ff6000">
              公司简介：
            </el-col>
            <el-col :offset="1" :span="23">
              {{ company.description }}
            </el-col>
          </el-form-item>
          <el-divider></el-divider>
        </el-form>

        <div id="operation">
          <el-button type="primary" size="small" @click="fav()">{{
            isfav ? '已收藏' : '收藏'
          }}</el-button>
          <el-button type="primary" size="small" @click="post()"
            >投递</el-button
          >
        </div>
      </div>
    </div>
    <div class="side">
      <div class="comment-area">
        <h2 style="text-align: center">评论区：</h2>
        <el-row v-for="item in comments" :key="item.comment.commentId">
          <el-col class="user">
            <el-popover placement="top-left" width="200" trigger="click">
              <el-row>{{ item.comment.nickname }}</el-row>
              <el-row>城市： {{ item.comment.city }}</el-row>
              <el-row>毕业于{{ item.comment.graduation }}</el-row>
              <el-row>学历：{{ item.comment.eduDegree }}</el-row>
              <div slot="reference" style="font-size: 14px">
                <i class="el-icon-user"></i>{{ item.comment.nickname }}
              </div>
            </el-popover>
          </el-col>
          <el-col :span="18" class="comment">{{ item.comment.content }}</el-col>
          <el-col :span="3">
            <el-button
              size="mini"
              type="text"
              @click="replyComment(item.comment.commentId, item.comment.userId)"
              >回复</el-button
            >
          </el-col>
          <!-- 回复框 -->
          <el-col
            class="reply"
            :span="24"
            v-if="item.comment.commentId === replyingCommentId&& replyingReplyId ===''"
          >
            <el-input
              v-model="replyCommentContent"
              type="textarea"
              size="mini"
            ></el-input>
            <el-col
              :span="24"
              style="
                display: flex;
                justify-content: flex-end;
                height: 30px;
                margin-top: 10px;
              "
            >
              <el-button type="mini" @click="saveRely()">发表</el-button>
              <el-button type="mini" @click="cancel()">取消</el-button>
            </el-col>
          </el-col>
          <!-- 回复区 -->
          <el-col
            class="reply"
            :span="24"
            v-for="(replyItem, index) in item.reply"
            :key="index"
          >
            <el-col :span="24" style="display: flex">
              <div class="user">
                <el-popover placement="top-left" width="200" trigger="click">
                  <el-row>{{ replyItem.user.nickname }}</el-row>
                  <el-row>城市： {{ replyItem.user.city }}</el-row>
                  <el-row>毕业于{{ replyItem.user.graduation }}</el-row>
                  <el-row>学历：{{ replyItem.user.eduDegree }}</el-row>
                  <div slot="reference" style="font-size: 14px">
                    <i class="el-icon-user"></i>{{ replyItem.user.nickname }}
                  </div>
                </el-popover>
              </div>
              <div
                style="
                  font-size: 12px;
                  line-height: 30px;
                  margin: 0 4px;
                  color: #999;
                "
              >
                回复
              </div>
              <div class="user" :span="8">
                <el-popover placement="top-left" width="200" trigger="click">
                  <el-row>{{ replyItem.replyUser.nickname }}</el-row>
                  <el-row>城市： {{ replyItem.replyUser.city }}</el-row>
                  <el-row>毕业于{{ replyItem.replyUser.graduation }}</el-row>
                  <el-row>学历：{{ replyItem.replyUser.eduDegree }}</el-row>
                  <div slot="reference" style="font-size: 14px">
                    <i class="el-icon-user"></i
                    >{{ replyItem.replyUser.nickname }}
                  </div>
                </el-popover>
              </div>
            </el-col>
            <el-col :span="20" style="margin-left: 6px; background: #f5f5f5">
              {{ replyItem.reply.content }}
            </el-col>
            <el-col :span="3">
              <el-button size="mini" type="text" @click="replyReply(replyItem.reply.replyId,item.comment.commentId, replyItem.user.userId)">回复</el-button>
            </el-col>
            <!-- 回复框 -->
            <el-col
              :span="22"
              v-if="replyItem.reply.replyId === replyingReplyId"
            >
              <el-input
                v-model="replyCommentContent"
                type="textarea"
                size="mini"
              ></el-input>
              <el-col
                :span="24"
                style="
                  display: flex;
                  justify-content: flex-end;
                  height: 30px;
                  margin-top: 10px;
                "
              >
                <el-button type="mini" @click="saveRely()">发表</el-button>
                <el-button type="mini" @click="cancel()">取消</el-button>
              </el-col>
            </el-col>
          </el-col>
        </el-row>
        <div class="no-comment" v-if="comments.length === 0">暂无评论</div>
        <el-divider></el-divider>
        <el-input
          type="textarea"
          :rows="3"
          placeholder="请在此发表您的评论"
          v-model="textarea"
        >
        </el-input>
        <div id="operation">
          <el-button type="primary" @click="comment()">发表</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Tool from './tool'
import {
  favor,
  favorOrNot,
  disFavor,
  positionApply,
  comment,
  reply,
  getComment,
  getCompanyInfo,
} from '@/api/user'

export default {
  name: 'deliveryList',
  components: {
    Tool,
  },
  data() {
    return {
      deList: [],
      job: JSON.parse(localStorage.getItem('job')),
      categoryMap: JSON.parse(localStorage.getItem('categoryMap')),
      educationMap: JSON.parse(localStorage.getItem('educationMap')),
      textarea: '',
      comments: [],
      isfav: false,
      company: {},
      replyingUser: '',
      replyingCommentId: '',
      replyCommentContent: '',
      replyingReplyId: '',
    }
  },
  mounted() {
    this.getComment()
    this.getFav()
    console.log(this.job.hrIdPub)
    this.getCompanyInfo()
  },
  methods: {
    cancel() {
      this.replyingUser = ''
      this.replyCommentContent = ''
      this.replyingCommentId = ''
      this.replyingReplyId = ''
    },
    saveRely() {
      this.reply(
        this.replyingCommentId,
        this.replyingUser,
        this.replyCommentContent
      )
    },
    replyComment(commentId, userId) {
      this.replyingCommentId = commentId
      this.replyingUser = userId
      this.replyingReplyId = ''
    },
    replyReply(replyId, commentId, userId) {
      this.replyingReplyId = replyId
      this.replyingCommentId = commentId
      this.replyingUser = userId
    },
    getCompanyInfo() {
      getCompanyInfo(this.job.hrIdPub).then((res) => {
        this.company = res.data.hr
      })
    },
    getFav() {
      favorOrNot(this.job.positionId).then((res) => {
        if (res.data) {
          this.isfav = true
          return
        }
        this.isfav = false
      })
    },
    getComment() {
      getComment(this.job.positionId, 0).then((res) => {
        this.comments = res.data.comments
      })
    },
    reply(commentId, userId, content) {
      if (content === '') {
        this.$message.error('回复不能为空')
        return
      }
      reply(commentId, userId, content).then((res) => {
        if (res.data === 'success') {
          this.$message.success('发表成功')
          this.cancel()
          this.getComment()
          return
        }
        this.$message.error('发表失败')
      })
    },
    comment() {
      if (this.textarea === '') {
        this.$message.error('评论不能为空')
        return
      }
      comment(this.job.positionId, '1', this.textarea).then((res) => {
        if (res.data === 'success') {
          this.$message.success('发表成功')
          this.textarea = ''
          this.getComment()
          return
        }
        this.$message.error('发表失败')
      })
    },
    fav() {
      if (this.isfav) {
        disFavor(this.job.positionId).then((res) => {
          if (res.data === 'success') {
            this.$message.success('已取消收藏')
            this.getFav()
            return
          }
          this.$message.error('操作失败')
        })
        return
      }
      favor(this.job.positionId).then((res) => {
        if (res.data === 'success') {
          this.$message.success('收藏成功')
          this.getFav()
          return
        }
        this.$message.error('收藏失败')
      })
    },
    post() {
      positionApply(this.job.positionId).then((res) => {
        if (res.data === 'success') {
          this.$message.success('投递成功')
          return
        }
        if (res.data === 'had applied') {
          this.$message.info('已投递')
          return
        }
        this.$message.error('投递失败')
      })
    },
  },
}
</script>

<style lang="stylus" scoped>
.job-title {
  margin-top: 20px;
  font-size: 24px;
  font-weight: 700;
}

.job-salary {
  margin-top: 20px;
  color: #ff6000;
  font-size: 20px;
  font-weight: 700;
}

.job-yaoqiu {
  height: auto;
  line-height: 24px;
  font-size: 16px;
  color: #666;
  margin-bottom: 10px;
  margin-top: 10px;
}

.hengxian {
  color: #e8e8e8;
}

.no-comment {
  font-size: 30px;
  color: #848479;
}

.user {
  margin: 5px;
}

.comment {
  margin-left: 5px;
  border: 1px solid #f5f5f5;
  font-size: 14px;
  background: #f5f5f5;
}

.reply {
  border-left: 2px solid #f5f6f7;
  margin-left: 20px;
}

#operation {
  text-align: center;
  margin-top: 20px;
}

/deep/.el-form--label-top .el-form-item__label {
  padding: 0;
}

/deep/.el-form-item {
  margin-bottom: 0;
}

#deliveryList {
  margin-top: 20px;
  min-height: 500px;
  width: 80vw;
  height: auto;
  display: flex;
  justify-content: space-between;
}

#main-list {
  min-height: 650px;
  background: white;
}

.side {
  width: 40%;
  min-height: 400px;
}

.comment-area {
  width: 100%;
  padding: 20px;
  min-height: 500px;
  background: white;
}
</style>
