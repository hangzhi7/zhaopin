<template>
  <div style="display: flex;align-items: center">
    <el-dialog
      title="举报"
      :visible.sync="reportVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-select v-model="value" sizi="mini" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.id"
          :label="item.title"
          :value="item.id"
        >
        </el-option>
      </el-select>
      <p>举报说明:</p>
      <el-input
        :autosize="{ minRows: 5 }"
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        v-model="textarea"
      >
      </el-input>

      <span slot="footer" class="dialog-footer">
        <el-button @click="reportVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitReport">提交</el-button>
      </span>
    </el-dialog>

    <i @click="toReport()" class="el-icon-warning waring">举报</i>
  </div>
</template>

<script>
import { Message } from "element-ui";
import {getReportList, reportDynamic} from "@/api/report";
export default {
  props: ["msg","type"], //拿到跳转举报处的内容和作者
  data(){
    return{
      report:{

      },
      options:[],
      textarea:'',
      value:'选择举报类型',
      reportVisible:false
    }
  },
  methods:{
    getReportKind(){
      getReportList().then((res) => this.options=res.data.extend.reportKinds);
    },
    toReport(){
      this.reportVisible= true;
      this.getReportKind();
    },
    submitReport(){
      if(this.value=='选择举报类型'){
        this.$message.warning("请选择举报类型~")
        return
      }else if(this.textarea.length==0){
        this.$message.warning("请填写举报说明~")
        return
      }
      let report={
        bid:this.msg.id,
        content:this.textarea,
        rkid:this.value,
        rtype:this.type,
        type:this.$store.getters["user/getUser"].uType,
        uid:this.$store.getters["user/getUser"].uid
      }
      reportDynamic(report).then(res=>{
        this.reportVisible=false
        console.log(res)
        Message.success("成功");
      })

      console.log(this.msg);
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
          .then((_) => {
            done();
          })
          .catch((_) => {});
    }
  }

  //不是吧
};
</script>

<style scoped>
.waring {
  color:rgb(133,144,166);
  cursor: pointer;
}
.waring:hover {
  color: rgb(156, 165, 156);
}

</style>
