<template>
  <div>
    <el-row v-for="(item, index) in honor" :key="index">
      <el-row v-show="honorIndex !== index">
        <el-col :span="6">{{ item.time }}</el-col>
        <el-col :span="6">{{ item.reword }}</el-col>
        <el-col :span="6">{{ item.level }}</el-col>
        <el-col :span="6">
          <i
            class="el-icon-edit-outline hover-btn"
            @click="editHonor(index)"
            >编辑</i
          >
          <i
            class="el-icon-remove-outline hover-btn"
            @click="delHonor(index)"
            >删除</i
          >
        </el-col>
      </el-row>
      <el-row v-if="honorIndex === index">
        <div style="border: 1px solid #ff6000;padding:10px;">
          <el-row>
            <el-col :span="8">时间</el-col>
            <el-col :span="16">
              <el-date-picker
                value-format="yyyy-M-d"
                size="small"
                v-model="honorItem.time"
                type="date"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">奖项</el-col>
            <el-col :span="16">
              <el-input size="small" v-model="honorItem.reword" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">级别</el-col>
            <el-col :span="16">
              <el-input size="small" v-model="honorItem.level" />
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
                v-model="honorItem.time"
                type="date"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">奖项</el-col>
            <el-col :span="16">
              <el-input size="small" v-model="honorItem.reword" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">级别</el-col>
            <el-col :span="16">
              <el-input size="small" v-model="honorItem.level" />
            </el-col>
          </el-row>
          <el-button type="primary" size="small" @click="save()">保存</el-button>
          <el-button size="small" @click="cancel()">取消</el-button>
        </div>
      </el-row>
    <i
      class="el-icon-circle-plus-outline hover-btn"
      v-show="!adding&&honorIndex===''"
      @click="addHonor()"
      >添加</i
    >
  </div>
</template>

<script>
export default {
  name: 'honor',
  props: {
    experience: {
      require: true,
      default: '',
    }
  },
  watch: {
    experience() {
      if (this.experience === '' || this.experience=== undefined|| this.experience=== null) {
        this.honor = []
      } else {
        this.honor = JSON.parse(this.experience)
      }
    }
  },
  data() {
    return {
      honorIndex: '',
      adding: false,
      honorItem: {
        time: '',
        reword: '',
        level: '',
      },
      honor: [
      ],
    }
  },
  methods: {
    save() {
      if(this.honorItem.time === ''|| this.honorItem.reword === ''|| this.honorItem.level === '') {
        this.$message.error('请完成表单')
        return
      }
      if(this.adding) {
        this.honor.push(this.honorItem)
        this.adding = false
        this.$emit('input', JSON.stringify(this.honor))
        return
      }
      this.honor[this.honorIndex] = JSON.parse(JSON.stringify(this.honorItem))
      this.honorIndex = ''
      this.$emit('input', JSON.stringify(this.honor))
    },
    cancel() {
      this.adding=false
      this.honorIndex = ''
    },
    addHonor() {
      this.adding = true
      this.honorItem = {
        time: '',
        reword: '',
        level: '',
      }
    },
    editHonor(index) {
      this.honorIndex = index
      this.honorItem.time = this.honor[index].time
      this.honorItem.reword = this.honor[index].reword
      this.honorItem.level = this.honor[index].level
    },
    delHonor(index) {
      this.honor.splice(index, 1)
      this.$emit('input', JSON.stringify(this.honor))
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
