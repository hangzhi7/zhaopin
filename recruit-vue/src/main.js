import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import {PageHeader,Popconfirm,Image,Divider,Tag,Button,Input,Icon,Message,Checkbox,Dropdown,DropdownItem,Steps,Form,FormItem,Upload,Step,Table,TableColumn,Switch,
  DatePicker,DropdownMenu,Carousel,CarouselItem,Badge,Dialog,Avatar,Select,Option,Tooltip,Pagination,Cascader, InputNumber, Row, Col, Popover} from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import Composition from "@vue/composition-api"
import "./assets/font/font.css"
Vue.config.productionTip = false

Vue.use(Composition)
Vue.use(Popconfirm)
Vue.use(Button)
Vue.use(Row)
Vue.use(Col)
Vue.use(Popover)
Vue.use(PageHeader)
Vue.use(Input)
Vue.use(InputNumber)
Vue.use(Icon)
Vue.use(Checkbox)
Vue.use(Dropdown)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Carousel)
Vue.use(CarouselItem)
Vue.use(Badge)
Vue.use(Dialog)
Vue.use(Avatar)
Vue.use(Select)
Vue.use(Option)
Vue.use(Tooltip)
Vue.use(Pagination)
Vue.use(Steps)
Vue.use(FormItem)
Vue.use(Form)
Vue.use(Upload)
Vue.use(Step)
Vue.use(Cascader)
Vue.use(DatePicker)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Switch)
Vue.use(Tag)
Vue.use(Divider)
Vue.use(Image)
Vue.component("Message",Message)      //use引入页面刷新自动弹窗,原因暂时未知


Vue.prototype.$message=Message

Vue.filter('formatDate',function (str){
    let date=new Date(str)
    let y = date.getFullYear();
    let m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    let d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return y + '-' + m + '-' + d;
})

Vue.filter('setAvatar',function (url){
    if(url){
        return 'data:image/png;base64,'+url
    }else {
        return require("@/assets/img/adminAvatar.jpg")
    }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
