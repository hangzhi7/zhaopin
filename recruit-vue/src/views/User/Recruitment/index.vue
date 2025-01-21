<template>
  <div id="recruit">
    <div class="recTop">
      <div id="search-input">
        <el-select
          style="width: 120px"
          @change="getCitys"
          v-model="selectProVin"
          @visible-change="getPro"
          placeholder="请选择"
        >
          <el-option
            v-for="item in provinces"
            :key="item.adcode"
            :label="item.name"
            :value="item.name"
          >
          </el-option>
        </el-select>
        <el-input
          @keyup.enter.native="getData"
          v-model="keywords"
          style="width: 80%"
          placeholder="搜索职位"
        ></el-input>
        <el-button @click="getData" type="danger">搜索</el-button>
      </div>
      <div id="city-list">
        <i class="el-icon-office-building">市:</i>
        <div v-if="cities.length == 0">
          <span>无对应市级</span>
        </div>
        <span
          v-else
          :class="{ 'active-city': selectCity === city.name }"
          @click="selectCity = city.name"
          v-for="city in cities"
          :key="city.name"
          >{{ city.name }}</span
        >
      </div>
      <div id="screening">
        <div>
          <span>分类: </span>
        </div>
        <div class="sc-type">
          <el-cascader v-model="value" :options="options"> </el-cascader>
        </div>

        <div class="">
          <span>学历要求</span>
        </div>
        <div class="sc-type">
          <el-select v-model="educationLevel">
            <el-option
              v-for="option in educations"
              :key="option.id"
              :label="option.name"
              :value="option.id"
            ></el-option>
          </el-select>
        </div>
        <div class="sc-type">
          <el-button @click="clear()" size="mini">清除筛选</el-button>
        </div>
      </div>
    </div>
    <div class="rec-bottom">
      <div class="recList">
        <el-row class="pagination">
          <el-pagination
            @current-change="handleCurrentChange"
            :current-page.sync="curPage"
            :page-size="pageSize"
            layout="total, prev, pager, next, jumper"
            :total="total"
          >
          </el-pagination>
        </el-row>
        <el-row
          v-for="job in jobList"
          style="
            height: 120px;
            background: #fff;
            border: 1px solid #f5f5f5;
            margin-bottom: 5px;
            overflow: hidden;
          "
          :key="job.positionId"
        >
          <el-col :span="8">
            <el-row
              style="
                margin-top: 20px;
                text-align: center;
                height: 30px;
                overflow: hidden;
              "
              ><h3>{{ job.workCity }} | {{ job.title }}</h3></el-row
            >
            <el-row
              style="
                margin-top: 20px;
                text-align: center;
                color: red;
                font-size: 10px;
              "
            >
              <div style="font-size: 18px">{{ getSalary(job) }}</div>
              元/月
            </el-row>
          </el-col>
          <el-col :span="8" style="margin-top: 30px; color: blue">
            <el-row style="margin-top: 10px; margin-left: 40px">{{
              getEdu(job)
            }}</el-row>
            <el-row
              style="margin-top: 10px; margin-left: 40px; color: #343a86"
              >{{ job.companyName }}</el-row
            >
          </el-col>
          <el-col
            :span="8"
            style="
              display: flex;
              justify-content: center;
              flex-direction: column;
              align-items: center;
              height: 100%;
            "
          >
            <el-row>
              <el-button size="small" type="primary" @click="getDetail(job)"
                >查看详情</el-button
              >
            </el-row>
          </el-col>
        </el-row>
      </div>

      <div class="resume">
        <tool></tool>
        <div style="border-top: 10px solid #f5f5f5">
          <el-row
            style="font-size：18px;margin-bottom:20px;text-align:center;color:#ff6000;"
            >友情链接</el-row
          >
          <div style="height: 300px; over-flow: auto; margin-left: 20px">
            <el-row v-for="item in frendlyUrls" :key="item.urlId">
              <li style="height: 25px; color: blue" @click="openUrl(item.url)">
                {{ item.name }}
              </li>
            </el-row>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import { getProvinces } from '@/api/map'
import Tool from './tool'
import Annex from '@/views/User/Recruitment/annex'
import { listCategory, listEducation, listFriendUrl } from '@/api/hr'
import { searchPosition } from '@/api/user'

export default {
  name: 'index',
  components: { Annex, Tool },
  created() {
    getProvinces('中国').then((res) => {
      this.provinces = res
      this.provinces.unshift({ adcode: 999, name: '全部' })
      this.selectProVin = res[0].name
    })
  },
  mounted() {
    console.log(this.$route.params, '参数')
    let keyWords = this.$route.params.keyWords
    let categoryId = this.$route.params.categoryId
    if (keyWords) {
      this.keywords = keyWords
    }
    this.listCategory(categoryId)
    this.listEducation()
    this.getFrendlyUrls()
  },
  data() {
    return {
      curPage: 1,
      pageSize: 5,
      total: 0,
      cityActive: 0,
      provinces: [],
      cities: [],
      jobList: [],
      options: [],
      // 搜索条件
      keywords: '',
      value: [],
      educationLevel: '',
      selectProVin: '',
      selectCity: '',
      categorys: [],
      educations: [],
      educationMap: {},
      // 友情链接
      frendlyUrls: [],
      positions: [],
    }
  },
  methods: {
    handleCurrentChange() {
      this.getData()
    },
    getDetail(job) {
      localStorage.setItem('job', JSON.stringify(job))
      this.$router.push('/user/position')
    },
    getCitys() {
      this.getCity()
    },
    getSalary(job) {
      if (job.salaryUp > 0 && job.salaryDown > 0)
        return job.salaryDown + '~' + job.salaryUp
      return '面谈'
    },
    getEdu(job) {
      return this.educationMap[job.educationId]
    },
    openUrl(url) {
      window.location.href = url
    },
    getFrendlyUrls() {
      listFriendUrl().then((res) => {
        this.frendlyUrls = res.data
      })
    },
    listCategory(categoryId) {
      const map = {}
      const categoryMap = {}
      listCategory().then((res) => {
        this.categorys = res.data
        res.data.forEach((item) => {
          categoryMap[item.categoryId] =
            item.firstCategoryName + ' | ' + item.secondCategoryName
          if (map[item.firstCategoryName]) {
            map[item.firstCategoryName].push(item)
          } else {
            map[item.firstCategoryName] = []
            map[item.firstCategoryName].push(item)
          }
        })
        this.options = []

        localStorage.setItem('categoryMap', JSON.stringify(categoryMap))
        Object.keys(map).forEach((key) => {
          const option = {
            value: key,
            label: key,
            children: [],
          }
          map[key].forEach((sec) => {
            if (categoryId && categoryId === sec.categoryId) {
              this.value = [sec.firstCategoryName, categoryId]
            }
            const secOption = {
              value: sec.categoryId,
              label: sec.secondCategoryName,
            }
            option.children.push(secOption)
          })
          this.options.push(option)
        })
        this.getData()
      })
    },
    listEducation() {
      listEducation().then((res) => {
        this.educations = res.data
        this.educations.forEach((item) => {
          this.educationMap[item.id] = item.name
        })
        localStorage.setItem('educationMap', JSON.stringify(this.educationMap))
      })
    },
    currentChange(val) {
      this.getData(val)
    },
    getData() {
      this.jobList = []
      let categoryId = 0,educationId=0,selectProVin='',selectCity = ''
      if (this.value.length > 0) {
          categoryId = this.value[1]
      }
      if(this.selectProVin !== '全部') {
        selectProVin = this.selectProVin
      }
      if(this.selectCity !== '全部') {
        selectCity = this.selectCity
      }
      searchPosition(this.keywords,categoryId,this.educationLevel,selectProVin,selectCity,this.curPage,5).then((res) => {
        this.jobList = res.data.posInfo.list
        this.total = res.data.posInfo.total
        this.curPage = res.data.posInfo.pageNum
        // this.filterData()
      })
    },
    // filterData() {
    //   this.jobList = []
    //   console.log(this.selectProVin, this.selectCity)
    //   this.positions.forEach((job) => {
    //     if (
    //       this.selectProVin !== '全部' &&
    //       job.workProvince !== this.selectProVin
    //     )
    //       return
    //     if (
    //       this.selectCity !== '全部' &&
    //       this.selectCity !== '' &&
    //       job.workCity !== this.selectCity
    //     )
    //       return
    //     if (this.value.length > 0) {
    //       const id = this.value[1]
    //       if (id !== job.categoryId) return
    //     }
    //     let level = 0
    //     this.educations.forEach((item) => {
    //       if (item.id === job.educationId) level = item.level
    //     })
    //     if (level < this.educationLevel) return
    //     if (job.title.indexOf(this.keywords) === -1) return
    //     this.jobList.push(job)
    //   })
    // },
    clear() {
      console.log(this.value)
      this.value = []
      this.educationLevel = ''
      this.getData()
    },
    getPro() {
      getProvinces('中国').then((res) => {
        this.provinces = res
        this.provinces.unshift({ adcode: 999, name: '全部' })
      })
    },
    getCity() {
      getProvinces(this.selectProVin).then((res) => {
        this.cities = res
        this.cities.unshift({ adcode: 997, name: '全部' })
        this.selectCity = this.cities[0].name
      })
    },
  },
}
</script>

<style lang="stylus" scoped>
.pagination{
  display flex
  align-items center
  height 40px
  background #fff
  margin-bottom 10px
}
.recList {
  :hover {
    color: red;
  }
}

.el-select .el-input {
  width: 130px;
}

.input-with-select .el-input-group__prepend {
  background-color: #fff;
}

#search-input {
  width: 95%;
  display: flex;
  justify-content: space-between;
}

#recruit {
  margin-top: 10px;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.recTop {
  width: 100%;
  height: 150px;
  padding: 5px;
  background: white;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: sticky;
  top: -110px;
  box-shadow: 1px 1px 3px #eadbdb;
}

.rec-bottom {
  margin-top: 10px;
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.recList {
  width: 75%;
}

.recList-uni {
  width: 100%;
}

.recListCon {
  width: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: 5px;
}

.recListCon-uni {
  width: 100%;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-gap: 5px;
}

.resume {
  width: 24%;
  height: 460px;
  background: white;
}

#city-list, #area-list {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  width: 100%;

  &>div {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
  }

  & span {
    color: rgb(163, 163, 163);
    position: relative;
    font-size: 14px;
    border-radius: 10px;
    margin-right: 5px;
    flex: none;
    cursor: pointer;
    transition: all 0.2s;
    padding: 0 2px;
    animation: left ease-out 0.2s;

    &:hover {
      background: #f5f1ec;
      color: #2696ee;
    }
  }
}

@keyframes left {
  0% {
    left: 20px;
  }

  100% {
    left: 0;
  }
}

.active-city, .active-area {
  background: rgb(64, 158, 255);
  color: white !important;
}

#screening {
  font-size: 14px;
  display: flex;
  align-items: center;
}

.el-dropdown {
  width: 80px;
  display: flex;
  justify-content: center;
}

.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.sc-type {
  display: flex;
  width: 200px;
  align-items: center;
}

#Pagination {
  display: flex;
  justify-content: center;
}
</style>
