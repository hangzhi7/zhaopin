<template>
  <div id="annex">
    <h4>附件管理</h4>
    <div id="fileList">
      <div class="file">
        <i class="el-icon-document">
          <p>软件工程师_龙年生</p>
        </i>
        <i class="el-icon-delete"></i>
      </div>
    </div>
    <el-button type="primary" size="small" id="upFile">上传简历</el-button>
  </div>
</template>

<script>
export default {
name: "annex"
}
</script>

<style lang="stylus" scoped>
#annex
  margin-top 10px
  padding 10px
  background white
  &>h4
    font-weight 400
#fileList
  margin 15px 0
  display flex
  flex-direction column
.file
  display flex
  align-items center
  justify-content space-between
  & p
    font-size 14px
  & i
    display flex
#upFile
  width 100%
</style>
