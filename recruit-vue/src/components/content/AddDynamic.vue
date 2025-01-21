<template>
  <div id="addDy">
    <el-dialog
        title="提示"
        :visible.sync="pictureCloseConfirm"
        width="30%"
    >
      <span>确定要关闭图片上传么，这将清空您已上传的图片！</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="pictureCloseConfirm= false">取 消</el-button>
    <el-button type="primary" @click="closeImg">确 定</el-button>
  </span>
    </el-dialog>
    <header>
      <h4>说点想说的吧!</h4>
    </header>
    <article>
      <textarea ref="addContent" id="textarea" v-model="dynamic.context"  style="resize:none;height: 100%;width: 99%">

      </textarea>
    </article>
    <footer>
      <div id="pic">
        <i @click="openImg" class="el-icon-picture-outline">图片</i>
        <div id="upImg" v-if="upImgShow">
          <div style="width: 100%;height: 26px;display: flex;justify-content: space-between">
            <h5 style="margin: 0">本地上传</h5>

            <i @click="openConfirm()" class="el-icon-close"></i>
          </div>
          <el-upload
              :on-remove="handleRemove"
              ref="upload"
              :headers="header"
              :action="baseUrl+'/file/uploadDynamic/'+id"
              list-type="picture-card"
              :limit="8"
              :before-upload="beforeUpload"
              :on-success="success"
              name="multipartFiles"
              :on-preview="handlePictureCardPreview"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible2">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </div>
      </div>

      <div id="submit">
        <el-checkbox v-model="checked">仅自己可见</el-checkbox>
        <button @click="submit" type="success">提交</button>
      </div>
    </footer>
  </div>
</template>

<script>
import {removeImg,addDynamic} from "@/api/dynamic"
import {baseUrl} from "@/api/baseUrl";

export default {
  name: "AddDynamic",
  data() {
    return {
      header:{
        TOKEN:this.$store.getters["user/getToken"]
      },
      pictureCloseConfirm:false,
      baseUrl:baseUrl,
      uid:{},
      isUp:false,
      id:0,
      fileList:[],
      upImgShow:false,
      checked:false,
      title:'',
      content:'',
      dialogImageUrl: '',
      dialogVisible: false,
      dialogVisible2: false,
      dynamic:{
        context:'',
        type:this.$store.getters["user/getUser"].uType,
        visible:1,
        imageId:0,
        uid:this.$store.getters["user/getUser"].uid
      }
    };
  },
  methods: {

    beforeUpload(){
      if(this.id==0){
        return true
      }else {
        if(this.isUp){
          this.isUp=false
          return true
        }else {
          this.$message.warning("请等待前置上传~")
          return false
        }
      }

    },
    success(res,file){
      this.uid[file.uid]=res.extend.fileName
      this.fileList.push(res.extend.fileName)
      this.isUp=true
      console.log(res)
      this.id=res.extend.id
      this.dynamic.imageId=this.id
    },
    submit() {
      console.log(this.checked)
      console.log("是否可见")
      if(this.checked){
        this.dynamic.visible=0
      }else {
        this.dynamic.visible=1
      }
      if(this.dynamic.context.length<=0){
        this.$message.error("你说的太少啦~再多说一点吧~~~")
      }else {
        addDynamic(this.dynamic)
            .then(res=>{
              console.log(res)
              this.upImgShow=false
              this.dynamic.context=''
              this.$parent.getData(1)
              this.id=0
              this.$message.warning(res.data.extend.msg)
            })
      }

    },
    cancelHandel(){
      this.$emit('addSwitch')
    },
    handleRemove(file, fileList) {
      console.log(file,fileList);
      removeImg(this.id,[this.uid[file.uid]])
          .then(res=>{
            console.log(res)
            console.log("移除成功!")
          }).catch(err=>{
        console.log(err);
      })
    },
    openConfirm(){
      if(this.fileList.length==0){
        this.upImgShow=false
      }else {
        this.pictureCloseConfirm=true
      }

    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible2 = true;
    },
    openImg(){
      this.upImgShow=true
    },
    closeImg(){
      console.log(this.fileList)
      removeImg(this.id,this.fileList)
          .then(res=>{
            console.log(res)
            console.log("移除成功!")
          }).catch(err=>{
        console.log(err);
      })
      this.upImgShow=false
      this.pictureCloseConfirm=false
      this.fileList=[]
     this.id=0
    }
  }
}
</script>

<style scoped>
#addDy{
  display: flex;
  flex-direction: column;
  /*border: deepskyblue 1px dotted;*/
  box-sizing: border-box;
  padding: 10px;
  margin-bottom: 10px;
  width: 100%;
  height: 190px;
  background-color: #fff;
}
#textarea{
  outline: none;
  border: 1px solid gray;
  box-shadow: 0px 0px 1px 1px #d0c6c6 inset;
}
#textarea:focus{
  box-shadow: none;
}
header{
  width: 100%;
  height: 25%;
  display: flex;
  align-items: center;
}
header h4{
  background: linear-gradient(90deg, #f29914 15%, #0bbbe3 76%);
  background-clip: text;
  -webkit-background-clip: text;
  color: transparent;
}
article{
  width: 100%;
  height: 50%;
}
footer{
  width: 100%;
  height: 25%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  /*background-color: #130;*/
}
#submit{
  width: 50%;
  height: 80%;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  /*background-color: #000;*/
}
#pic{
  cursor: pointer;
  position: relative;
  height: 80%;
  width: 20vw;
  display: flex;
  align-items: flex-end;
}
#submit button{
  height: 70%;
  width: 100px;
  border: none;
  outline: none;
  color: white;
  cursor: pointer;
  background-color: #f6bb4f;
}
/*.el-upload-list--picture-card .el-upload-list__item {*/
/*  height: 90px;*/
/*  width: 90px;*/
/*}*/
/deep/.el-upload--picture-card {
  height: 5vw;
  width: 5vw;
  line-height: 5vw;
}
/deep/.el-upload-list--picture-card .el-upload-list__item{
  height: 5vw;
  width: 5vw;
}
.btn{
  height: 25%;
  border: none;
  outline: none;
}
#upImg{
  transition: all 1s;
  display: flex;
  flex-wrap: wrap;
  box-sizing: border-box;
  padding: 3% 5%;
  position: absolute;
  width: 100%;
  top: 34%;
  background-color: #fff;
  z-index: 3;
  box-shadow: 0 2px 3px grey;
}
@media screen and (max-width: 500px){
  #addDy{
    display: flex;
    justify-content: space-between;
    border: deepskyblue 1px dotted;
    box-sizing: border-box;
    padding: 5px;
    width: 100vw;
    height: 110px;
    background-color: #fff;
  }

  /deep/.el-upload--picture-card {
    height: 80px;
    width: 80px;
    line-height: 80px;
  }
}
</style>
