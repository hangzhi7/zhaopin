package com.kai.recruit.controller.hr;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import com.kai.recruit.controller.BaseController;
import com.kai.recruit.model.*;
import com.kai.recruit.service.*;
import com.kai.recruit.constant.GlobalConst;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@CrossOrigin
@RestController
@Api(value="HR接口", description="HR接口")
public class HrController extends BaseController {
    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    HRService hrService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    PositionService positionService;

    @Autowired
    CompanyService companyService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    private FriendUrlService friendUrlService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/hr/register")
    @ResponseBody
    public boolean hrRegister(@RequestParam("hr") String hr, @RequestParam("password") String password) {
        if (hr==null || password==null) {

            return false;
        }
        if (hrService.getHRByHr(hr) != null) {
            return false;
        }

        HRModel hrModel = new HRModel();
        hrModel.setHr(hr);
        hrModel.setHrPassword(password);
        if(!hrService.registerHR(hrModel)) {
            return false;
        }
        return true;
    }

    // 用户登录
    @PostMapping(value = "/api/hr/login")
    @ResponseBody
    public String userLogin(HttpSession httpSession,
                         @RequestParam("hr") String hr,
                         @RequestParam("password") String password) {
        if (hr == null || password == null) {
            return "please input account and password!";
        }

        if (hrService.loginHR(hr, password)) {
            httpSession.setAttribute("hr", hrService.getHRByHr(hr));
            return "login success";
        }
        return "login failed";
    }

    // 用户个人信息 输出
    @GetMapping(value = "/api/hr/info/show")
    @ResponseBody
    public String showInfo(HttpServletRequest request) {

        HRModel hr = this.getHR(request);

        if (hr == null) {
            this.errorDirect_404();
            //其实应该返回的是401，或者403
        }
        int id = hr.getHrId();
        HRModel HR = hrService.getHR(id);
        //收件箱
//        List<ApplicationModel> applyPosList = applicationService.listApplyInfoByHr(id);

        //创建的职位
//        List<PositionModel> positionModels = positionService.listPositionByHr(id);

        Map output = new TreeMap();
        output.put("hr", HR);
//        output.put("applyPosList", applyPosList);
//        output.put("positions",positionModels);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    @PostMapping("/api/hr/password/update")
    @ResponseBody
    public String updatePwd(HttpServletRequest request, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        HRModel hrModel = this.getHR(request);
        if (hrModel == null) {
            return "hr not login!";
        }
        int hrId = this.getHRId(request);


        if (!hrService.updatePwd(hrId, oldPassword, newPassword)) {
            return "old password is not correct!!";
        }
        HttpSession session = request.getSession();
        session.removeAttribute("hr");
        return "update password is success!";
    }

    // 个人信息更新 功能
    @PostMapping("/api/hr/info/update")
    public String updateInfo(HttpServletRequest request, @RequestBody HRModel hr ) {
        HRModel hrModel = this.getHR(request);
        if (hrModel == null) {
            return "hr not login!";
        }
        if(!hrService.updateHr(hr)) {
            return "update Hr information is failed!";
        }
        return "success";
    }

    // 用户注销 功能
    @PostMapping(value = "/api/hr/logout")
    public String userLogout(HttpServletRequest request) {
        // 清除session
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        request.getSession().removeAttribute(GlobalConst.HR_LOGIN_SESSION_KEY);
        request.getSession().invalidate();

        return userDirect("logout success");
    }

    //获取投递的简历信息
    @PostMapping("/api/hr/application/receive")
    @ResponseBody
    public List<ApplicationModel> queryApplication(HttpServletRequest request){
        HRModel hr = this.getHR(request);

        if (hr == null) {
            this.errorDirect_404();
        }
        int hrId = hr.getHrId();
        List<ApplicationModel> applicationList =  hrService.queryApplication(hrId);
        return applicationList;
    }

    // 同意申请面试
    @PostMapping("/api/hr/application/allow")
    @ResponseBody
    public String allowApplication(HttpServletRequest request, @RequestBody ApplicationModel application) {
        // 当前HR用户
        HRModel hr = this.getHR(request);

        if (application == null) {
            return "something is wrong";
        }

        if (application.getHrId() != hr.getHrId()) {
            return "permission denied";
        }

        if (!applicationService.allowApplication(application.getApplicationId(),application.getReply())) {
            return "operation failed";
        }
        return "operation success";
    }

    @PostMapping("/api/hr/application/refuse")
    @ResponseBody
    public String refuseApplication(HttpServletRequest request, @RequestBody ApplicationModel application) {
        // 当前HR用户
        HRModel hr = this.getHR(request);

        if (application == null) {
            return "something is wrong";
        }

        if (application.getHrId() != hr.getHrId()) {
            return "permission denied";
        }

        if (!applicationService.refuseApplication(application.getApplicationId(), application.getReply())) {
            return "operation failed";
        }
        return "operation success";
    }

//    // 新增公司部门
//    @PostMapping("/hr/department/add")
//    @ResponseBody
//    public String addDepartment(HttpServletRequest request, @RequestBody DepartmentModel departmentModel) {
//        if (departmentModel==null || departmentModel.getDepartmentName()==null) {
//            return "please confim inpur information is valid!(departmentName is must need!!!)";
//        }
//        HRModel hr = this.getHR(request);
//
//        if (hr == null) {
//            this.errorDirect_404();
//            //其实应该返回的是401，或者403
//        }
//        int companyId = hr.getHrCompanyId();
//
//        departmentModel.setCompanyId(companyId);
//
//        if(!departmentService.addDepartment(departmentModel.getDepartmentName(), departmentModel.getDescription(),companyId)) {
//            return "add department is failed!";
//        }
//        return "add department is success!";
//    }

    //发布岗位
    @PostMapping("/api/hr/position/publish")
    @ResponseBody
    public String pubPosition(HttpServletRequest request, @RequestBody PositionModel positionModel){
        HRModel hr = this.getHR(request);
        PositionModel position = new PositionModel();

        Date date = new Date();

        if (hr == null) {
            return "hr not exists!!";
            //其实应该返回的是401，或者403
        }
        int hrId = hr.getHrId();
        position.setHrIdPub(hrId);
        position.setReleaseDate(date);

        hrService.addPostion(position);
        return "publish position success";
    }

    @PostMapping("/api/hr/resumeAndUser/show")
    @ResponseBody
    public String getResumeAndUser(HttpServletRequest request, @RequestBody ApplicationModel applicationModel){
        HRModel hr = this.getHR(request);
        if (hr == null)  {
            return "permission denied!";
        }
        int resumeId = applicationModel.getResumeId();
        ResumeModel resume = resumeService.getResumeByRId(resumeId);
        int userId = resume.getUserId();
        UserModel user = userService.getUser(userId);

        Map output = new TreeMap();
        output.put("user", user);
        output.put("resume", resume);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    //删除岗位
    @PostMapping("/api/hr/position/del")
    @ResponseBody
    public String delPosition(@RequestBody PositionModel positionModel){
        int hrId = hrService.delPostion(positionModel.getPositionId());
        if(hrId<=0) {
            return "failed";
        }
        return "delete position success";
    }

    //修改岗位
    @PostMapping("/api/hr/position/update")
    @ResponseBody
    public boolean updatePosition(@RequestBody PositionModel positionModel){
        if(!hrService.updatePostion(positionModel)) {
            return false;
        }
        return true;
    }

    @GetMapping("/api/hr/position/show")
    @ResponseBody
    public List<PositionModel> showPosition(HttpServletRequest request){
        HRModel hr = this.getHR(request);

        if (hr == null) {
            this.errorDirect_404();
        }
        int id = hr.getHrId();
        return positionService.listPositionByHr(id);
    }

    @GetMapping("/api/hr/friendUrl/list")
    @ResponseBody
    public String listFriendUrl() {
        Gson gson = new Gson();
        ArrayList<FriendUrlsModel> friendUrls = friendUrlService.listUrls();
        String friendUrlsInfo = gson.toJson(friendUrls);
        return friendUrlsInfo;
    }
}

