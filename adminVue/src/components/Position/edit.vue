<template>
  <el-card class="app-container" shadow="always">
    <div slot="header">
      <span>编辑岗位信息</span>
    </div>
  <el-form ref="form1" :model="form1" :rules="rules2" label-width="120px">
     <el-form-item
        prop="positionId"
        label="岗位Id">
        <el-input v-model="form1.positionId" readonly="true" placeholder="请输入岗位名称"/>
      </el-form-item>
      <el-form-item
        prop="title"
        label="岗位名称">
        <el-input v-model="form1.title"  placeholder="请输入岗位名称"/>
      </el-form-item>
      <el-form-item
        label="岗位要求"
        prop="requirement"
      >
        <textarea rows="10" style="width:98%" v-model="form1.requirement" placeholder="请输入岗位要求"/>
      </el-form-item>
      <el-form-item
        label="岗位数量"
        prop="quantity"
      >
        <el-input v-model="form1.quantity"  type="number" placeholder="请再次输入岗位数量"/>
      </el-form-item>
      <el-form-item
        label="城市"
        prop="workCity"
      >
        <el-input v-model="form1.workCity"  placeholder="请再次输入城市"/>
      </el-form-item>
      <el-form-item
        label="工资上限"
        prop="salaryUp"
      > 
        <el-input v-model="form1.salaryUp" type="number" placeholder="请再次输入工资上限"/>
      </el-form-item>
      <el-form-item
        label="工资下限"
        prop="salaryDown"
      >
        <el-input v-model.number="form1.salaryDown" placeholder="请输入工资下限"/>
      </el-form-item>
      <el-form-item
        label="是否下架"
        prop="statePub"
      >
          <el-select style="width:100%" v-model="form1.statePub" placeholder="请输入是否上架">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
       <el-form-item
        label="公司id"
        prop="categoryId"
      >
        <el-input v-model="form1.categoryId" placeholder="请再次输入公司id"/>
      </el-form-item>
      <el-form-item>
        <el-button :loading="loading" type="primary" @click="onSubmit()">提交</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
  import ElCard from "element-ui/packages/card/src/main";
  import axios from "axios";

  export default {
    components: {ElCard},
    data() {
      var validatePass = (rule, value, callback) => {
       
      }
      return {
        form1: {
          title: "",
          requirement: "",
          quantity: "",
          workCity: "",
          salaryUp: "",
          salaryDown: "",
          statePub: "",
          categoryId: "",
          hrIdPub:"",
          positionId:""
        },
        loading: false,
        rules2: {
         
        },
         rules2: {
         
        },
          options: [{
          value: '1',
          label: '是'
        }, {
          value: '0',
          label: '否'
        }],
      }
    },
    mounted(){
      this.form1 =JSON.parse(localStorage.getItem("goodsInfo"))
      console.log(this.$route.params.positionId,this.form1)
    },
    methods: {
      onSubmit() {
        console.log(this.form1)
        let data = JSON.stringify(this.form1)
          axios
          .post("api/updatePostion", this.form1,
          )
          .then(response => {
            if(response.data==1){
               this.$alert('修改成功', '温馨提示', {
                confirmButtonText: '确定',
                callback: action => {
                      this.$router.push({path:"/table"})
                }
              });
              }
          })
          .catch(function(error) {
            console.log(error);
          });
      },
      showError() {
        this.$message({
          message: '提交失败！',
          type: 'error'
        })
      },
      onCancel() {
        this.$router.back()
      }
    }
  }
</script>

<style scoped>
  .line{
    text-align: center;
  }
</style>
