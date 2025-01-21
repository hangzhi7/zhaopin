package com.kai.recruit.controller.manager;

import com.google.gson.Gson;
import com.kai.recruit.controller.BaseController;
import com.kai.recruit.mapper.CategoryMapper;
import com.kai.recruit.mapper.CompanyMapper;
import com.kai.recruit.mapper.PositionMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import com.kai.recruit.model.*;
import com.kai.recruit.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
@Api("")
public class ManagerController extends BaseController{

    @Autowired
    private ManagerService managerService;

    @Autowired
    private HRService hrService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private FriendUrlService friendUrlService;

    @Autowired
    private EducationService educationService;

//    @RequestMapping("/manager/login")
//    public String init() {
//        return "manager/login";
//    }
//
//    @RequestMapping("/index")
//    public String index() {
//        return "manager/index";
//    }
//
//    @RequestMapping("/index_v3")
//    public String index_v3() {
//        return "manager/index_v3";
//    }
//
//    @RequestMapping("/nestable_list")
//    public String teams_board() {
//        return "manager/nestable_list";
//    }
//
//    @RequestMapping("/contacts")
//    public String contacts() {
//        return "manager/contacts";
//    }
//
//    @RequestMapping("/table_jqgrid")
//    public String table() {
//        return "manager/table_jqgrid";
//    }
//
//    @RequestMapping("/widgets")
//    public String widgets() {
//        return "manager/widgets";
//    }

    @PostMapping(value="/api/manager/login")
    @ResponseBody
    public boolean userLogin(HttpSession httpSession, @RequestParam String mobile, @RequestParam String password) {
        if (mobile==null || password==null) {
            return false;
        }

        if(!managerService.managerLogin(mobile, password)) {//login fail
            return false;
        }
        httpSession.setAttribute("manager", managerService.getManagerByMobile(mobile));
        return true;
    }

    @PostMapping("/api/manager/password/update")
    @ResponseBody
    public boolean updatePwd(HttpServletRequest request, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        ManagerModel managerModel = this.getManager(request);
        if (managerModel == null) {
            return false;
        }
        String mobile = managerModel.getMobile();

        if (!oldPassword.equals(managerModel.getPassword())) {
            return false;
        }

        managerModel.setPassword(newPassword);
        if (!managerService.updatePwd(mobile, newPassword)){
            return false;
        }
        return true;
    }

    @RequestMapping("/api/manager/addCompany")
    @ResponseBody
    public String addCompany(@RequestParam("companyName") String companyName,@RequestParam("companyCode") String companyCode,@RequestParam("description") String description) {
        if (companyCode==null || companyName==null || description==null) {
            return "must need some info!";
        }
        int result = managerService.addCompany(companyName,companyCode,description);
        if (result==0){
            return "fail to add company!";
        }
        return "success to add company!";
    }

    @RequestMapping(value = "/userareachart", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> area() {
        Map<String,Object> map = new HashMap<>();
        ArrayList<UserAreaModel> area = managerService.userArea();
        UserAreaModel userAreaModel;
        for(int i=0; i<area.size(); i++) {
            userAreaModel= area.get(i);
            map.put(userAreaModel.getArea(),userAreaModel.getUserNum());
        }
        return map;
    }

    @RequestMapping("/api/manager/webCount")
    @ResponseBody
    public Map<String,Object> webCount() {
        Map<String,Object> map = new HashMap<>();
        WebCountModel webCountModel = managerService.getWebCount();
        map.put("companyNum",webCountModel.getCompanyNum());
        map.put("offerNum" ,webCountModel.getOfferNum());
        map.put("userNum",webCountModel.getUserNum());
        map.put("visitNum",webCountModel.getVisitNum());
        System.out.println(map);
        return map;
    }

    @RequestMapping("/api/manager/Company/list")
    @ResponseBody
    public String listCompany() {
        Gson gson = new Gson();
        ArrayList<HRModel> companyEntities = hrService.listHR();
        String companyInfo = gson.toJson(companyEntities);
        System.out.println(companyInfo);
        return companyInfo;
    }

    @RequestMapping("/api/manager/User/list")
    @ResponseBody
    public String listUser() {
        Gson gson = new Gson();
        ArrayList<UserModel> users = managerService.getAllUsers();
        String userInfo = gson.toJson(users);
        return userInfo;
    }

    @RequestMapping("/api/manager/application/list")
    @ResponseBody
    public String listApplicatoin() {
        Gson gson = new Gson();
        ArrayList<ApplicationModel> applications = managerService.getAllApplications();
        String applicationsInfo = gson.toJson(applications);
        return applicationsInfo;
    }

    @RequestMapping("/api/manager/resume/list")
    @ResponseBody
    public String listResume() {
        Gson gson = new Gson();
        ArrayList<ResumeModel> resumes = managerService.getAllResumes();
        String resumesInfo = gson.toJson(resumes);
        return resumesInfo;
    }

    @PostMapping("/api/manager/company/{id}/del")
    @ResponseBody
    public boolean delCompany(HttpServletRequest request, @PathVariable int id) {
        ManagerModel manager = this.getManager(request);
        if (manager == null) {
            return false;
        }
        if (managerService.delCompany(id) > 0) {
            return true;
        }
        return false;
    }

    @PostMapping("/api/manager/company/update")
    @ResponseBody
    public boolean updateCompany(HttpServletRequest request, @RequestBody HRModel hrModel) {
        ManagerModel manager = this.getManager(request);
        if(manager == null) {
            return false;
        }
        if (hrModel == null) {
            return false;
        }

        if(!hrService.updateHr(hrModel)) {
            return false;
        }
        return true;
    }

    @PostMapping("/api/manager/user/update")
    @ResponseBody
    public boolean updateUser(HttpServletRequest request, @RequestBody UserModel userModel) {
        ManagerModel manager = this.getManager(request);
        if(manager == null) {
            return false;
        }
        if (userModel == null) {
            return false;
        }

        if(!userService.updateUser(userModel)) {
            return false;
        }
        return true;
    }

    @PostMapping("/api/manager/user/{id}/del")
    @ResponseBody
    public boolean delUser(HttpServletRequest request, @PathVariable int id) {
        ManagerModel manager = this.getManager(request);
        if (manager == null) {
            return false;
        }
        if (managerService.delUser(id) > 0) {
            return true;
        }
        return false;
    }

    @PostMapping("/api/manager/position/{id}/setCategory")
    @ResponseBody
    public String setCategory(HttpServletRequest request, @PathVariable int id, @RequestParam("categoryName") String categoryName) {
        ManagerModel manager = this.getManager(request);
        if (!managerService.isManager(manager.getManagerId())) {
            return "this account is not manager, permission denied";
        }
        CategoryModel category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            return "please input correct category name!";
        }
        if (positionService.setCategory(category.getCategoryId(), id)) {
            return "del success!";
        }
        return "del failed";
    }

    //删除岗位
    @PostMapping("/api/manager/position/{id}/del")
    @ResponseBody
    public boolean delPosition(HttpServletRequest request, @PathVariable int id){
        ManagerModel manager = this.getManager(request);
        if (manager == null) {
            return false;
        }
         if (hrService.delPostion(id) > 0){
             return true;
         }
       return false;
    }

    //删除岗位
    @PostMapping("/api/manager/application/{id}/del")
    @ResponseBody
    public boolean delApplication(HttpServletRequest request, @PathVariable int id){
        ManagerModel manager = this.getManager(request);
        if (manager == null) {
            return false;
        }
        if (managerService.delApplication(id)){
            return true;
        }
        return false;
    }

    @GetMapping("/api/manager/position/list")
    @ResponseBody
    public String showPosition(HttpServletRequest request){
        ManagerModel manager = this.getManager(request);
        if (manager == null) {
            return "false";
        }
        Gson gson = new Gson();
        ArrayList<PositionModel> positionList = hrService.listAllPosition();

        String companyInfo = gson.toJson(positionList);
        return companyInfo;
    }

    @PostMapping("/api/manager/category/create")
    @ResponseBody
    public String createCategory(HttpServletRequest request, @RequestParam("firstCategoryName") String firstCategoryName, @RequestParam("secondCategoryName") String secondCategoryName, @RequestParam("description") String description) {
        ManagerModel manager = this.getManager(request);
//        if (!managerService.isManager(manager.getManagerId())) {
//            return "this account is not manager, permission denied";
//        }
        if (categoryService.addCategory(firstCategoryName,secondCategoryName,description)) {
            return "create category success";
        }
        return "create category failed";
    }

    @GetMapping("/api/manager/category/list")
    @ResponseBody
    public String listCategory() {
        Gson gson = new Gson();
        ArrayList<CategoryModel> categorys = categoryService.getAll();
        String categoryInfo = gson.toJson(categorys);
        return categoryInfo;
    }

    @GetMapping("/api/manager/category/{id}/del")
    @ResponseBody
    public boolean delCategory(HttpServletRequest request, @PathVariable int id) {
        ManagerModel manager = this.getManager(request);
        if(manager == null) {
            return false;
        }

        if (categoryService.delCategory(id)) {
            return true;
        }
        return true;
    }

    @PostMapping("/api/manager/category/{id}/update")
    @ResponseBody
    public boolean updateCategory(HttpServletRequest request, @RequestBody CategoryModel categoryModel,@PathVariable int id) {
        ManagerModel manager = this.getManager(request);
        if (manager == null) {
            return false;
        }
        String Second = categoryModel.getSecondCategoryName();
        if (categoryService.updateSecond(Second, id)) {
            return true;
        }
        return false;
    }

    @PostMapping("/api/manager/friendUrl/create")
    @ResponseBody
    public boolean createFriendUrl(HttpServletRequest request, @RequestBody FriendUrlsModel friendUrlsModel) {
        ManagerModel manager = this.getManager(request);
        if (manager == null) {
            return false;
        }
        if (friendUrlsModel == null) {
            return false;
        }
        String url = friendUrlsModel.getUrl();
        String name = friendUrlsModel.getName();
        if (friendUrlService.createUrl(url,name)) {
            return true;
        }
        return false;
    }

    @PostMapping("/api/manager/friendUrl/{id}/del")
    @ResponseBody
    public String delFriendUrl(HttpServletRequest request, @PathVariable int id) {
        ManagerModel manager = this.getManager(request);
        if (manager == null) {
            return "this account is not manager, permission denied";
        }
        if (friendUrlService.delUrl(id)) {
            return "del friendUrl success";
        }
        return "del friendUrl failed";
    }

    @PostMapping("/api/manager/friendUrl/{id}/update")
    @ResponseBody
    public boolean updateFriendUrl(HttpServletRequest request, @RequestBody FriendUrlsModel friendUrlsModel,@PathVariable int id) {
        ManagerModel manager = this.getManager(request);
        if (manager == null) {
            return false;
        }
        String url = friendUrlsModel.getUrl();
        String name = friendUrlsModel.getName();
        if (friendUrlService.updateUrl(url,name,id)) {
            return true;
        }
        return false;
    }

    @GetMapping("/api/manager/friendUrl/list")
    @ResponseBody
    public String listFriendUrl() {
        Gson gson = new Gson();
        ArrayList<FriendUrlsModel> friendUrls = friendUrlService.listUrls();
        String friendUrlsInfo = gson.toJson(friendUrls);
        return friendUrlsInfo;
    }

    @GetMapping("/api/manager/education/list")
    @ResponseBody
    public String listEducation() {
        Gson gson = new Gson();
        ArrayList<EducationModel> educations = educationService.listEducation();
        String educationsInfo = gson.toJson(educations);
        return educationsInfo;
    }

    @PostMapping("/api/manager/education/create")
    @ResponseBody
    public boolean createEducation(HttpServletRequest request, @RequestBody EducationModel educationModel) {
        ManagerModel manager = this.getManager(request);
        if(manager == null) {
            return false;
        }
        if (educationModel == null) {
            return false;
        }

        if(!educationService.createEducation(educationModel)) {
            return false;
        }
        return true;
    }

    @PostMapping("/api/manager/education/update")
    @ResponseBody
    public boolean updateEducation(HttpServletRequest request, @RequestBody EducationModel educationModel) {
        ManagerModel manager = this.getManager(request);
        if(manager == null) {
            return false;
        }
        if (educationModel == null) {
            return false;
        }

        if(!educationService.updateEducation(educationModel)) {
            return false;
        }
        return true;
    }

    @PostMapping("/api/manager/education/del")
    @ResponseBody
    public boolean delEducation(HttpServletRequest request, @RequestBody EducationModel educationModel) {
        ManagerModel manager = this.getManager(request);
        if(manager == null) {
            return false;
        }
        if (educationModel == null) {
            return false;
        }

        if(!educationService.delEducation(educationModel)) {
            return false;
        }
        return true;
    }

}
