package com.kai.recruit.controller.user;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kai.recruit.controller.BaseController;
import com.kai.recruit.model.*;
import com.kai.recruit.pojo.*;
import com.kai.recruit.service.*;
import com.kai.recruit.constant.GlobalConst;

import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.apache.commons.collections.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.File;

import org.apache.commons.io.FileUtils;
import sun.reflect.generics.tree.Tree;

import java.io.IOException;

@Controller
@EnableAutoConfiguration
@Api("")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @Autowired
    HRService hrService;

    @Resource
    private ApplicationService applicationService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private FavorService favorService;

    @Resource
    private ResumeService resumeService;

    @Resource
    private PositionService positionService;

    @Resource
    private CommentService commentService;

    @Resource
    private ReplyService replyService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private CompanyService companyService;

    @Autowired
    private FriendUrlService friendUrlService;

    // 用户账号注册
    @PostMapping(value="/api/user/register")
    @ResponseBody
    public boolean userRegister (@RequestParam String user, @RequestParam String password) {
        //Check user or password or is null
        if (user==null || password==null ) {
            return false;
        }
        
        if (userService.getUserByUser(user) != null) {//check if user was registed
        	return false;
        }

        UserModel userModel = new UserModel();
        userModel.setUser(user);
        userModel.setPassword(password);

        if(!userService.registerUser(userModel)) {//call register service
        	return false;
        }

        ResumeModel resume = new ResumeModel();
        resume.setUserId(userService.getUserByUser(user).getUserId());
        if (!resumeService.createResume(resume)) {
            return false;
        }

        return true;
    }

    // 用户登录
    @PostMapping(value="/api/user/login")
    @ResponseBody
    public boolean userLogin(HttpSession httpSession, @RequestParam String user, @RequestParam String password) {
    	if (user==null || password==null) {
    		return false;
    	}

    	if(!userService.loginUser(user, password)) {//login fail
    		return false;
    	}
        httpSession.setAttribute("user", userService.getUserByUser(user));
    	return true;
    }

    // 用户个人信息展示
    @GetMapping(value="/api/user/info/show")
    @ResponseBody
    public String userInfoShow(HttpServletRequest request) {
        UserModel user = this.getUser(request);
        if (user==null) {
            return "user not find~!";
        }
        //个人简历信息
        ResumeModel resume = resumeService.getResumeById(user.getUserId());
        //个人收藏职位
        List<FavorPositionBO> favorPosList = favorService.listFavorPosition(user.getUserId());
        Map output = new TreeMap();
        output.put("user", user);
        output.put("resume", resume);
        output.put("favorPosList", favorPosList);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    @GetMapping(value="/api/user/company/{id}")
    @ResponseBody
    public String CompanyInfo(HttpServletRequest request,@PathVariable int id) {
        //个人简历信息
        HRModel hr = hrService.getHR(id);

        Map output = new TreeMap();
        output.put("hr", hr);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    @PostMapping("/api/user/avatar/upload")
    @ResponseBody
    public String uploadAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        UserModel user = this.getUser(request);

        if(user == null) {
            return "please login!";
        }

        String contentType = file.getContentType();
        String fileName = String.valueOf(user.getUserId());
        String filePath = "upload/pictures";

        if (file.isEmpty()){
            return "you have no pick pic !";
        }

        try{
            uploadFile(file.getBytes(), filePath,fileName);
        }catch (Exception e) {
            return "upload is failed, please try again!";
        }
        if (!userService.updateAvatar(fileName, user.getUserId())){
            return "upload is failed";
        }
        return "upload success";
    }

    @RequestMapping("/api/user/{filename}")
    public void download(HttpServletRequest request,HttpServletResponse resp,@PathVariable String filename) throws IOException {
        //设置响应流中文件进行下载
        resp.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes("utf-8"),"ISO8859_1"));
        //把二进制流放进响应体中
        ServletOutputStream out = resp.getOutputStream();
        //获取images文件夹的路径
        String path = "upload/pictures";
        //获取某个文件夹下某个文件的file对象
        File file = new File(path,filename);
        //使用工具类把file对象转换成字节数组
        byte[] bytes = FileUtils.readFileToByteArray(file);
        //使用输出流把字节数组响应给客户端进行下载
        out.write(bytes);
        out.close();
    }

    // 用户个人信息更新
    @PostMapping(value="/api/user/password/update")
    @ResponseBody
    public String userPwdUpdate(HttpServletRequest request, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        UserModel userModel1 = this.getUser(request);
        if (userModel1 == null) {
            return "user not login!";
        }
        if (!userService.updatePwd(userModel1.getUserId(), oldPassword, newPassword)) {
            return "old password is not correct!!";
        }
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "update password is success!";
    }

    // 用户个人信息更新
    @PostMapping("/api/user/info/update")
    @ResponseBody
    public boolean updateUser(HttpServletRequest request, @RequestBody UserModel userModel) {
        int userId = this.getUserId(request);
        userModel.setUserId(userId);
        if (userModel == null) {
            return false;
        }

        if(!userService.updateUser(userModel)) {
            return false;
        }
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.setAttribute("user",userService.getUser(userId));
        return true;
    }

    // 用户注销
    @GetMapping(value="/api/user/logout")
    public String userLogout(HttpServletRequest request) {
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        request.getSession().removeAttribute(GlobalConst.USER_LOGIN_SESSION_KEY);
        request.getSession().invalidate();

        return "redirect:/user/login";
    }

    // 职位申请 功能
    @GetMapping(value = "/api/user/apply/{id}")
    @ResponseBody
    public String apply(HttpServletRequest request, @PathVariable int id) {

        //当前用户
        UserModel user = this.getUser(request);

        //当前用户简历
        ResumeModel resume = resumeService.getResumeById(user.getUserId());
        //当前浏览职位
        PositionModel position = positionService.getPositionById(id);

        if(applicationService.isApplyPosition(resume.getResumeId(),position.getPositionId())) {
            return "had applied";
        }

        if (user == null) {
            this.errorDirect_404();
        }
        if (resume == null) {
            return "error";
        }
        boolean result = applicationService.applyPosition(resume.getResumeId(), position.getPositionId(), position.getHrIdPub());
        if (!result) {
            return "error";
        }

        return "success";
    }

    // 职位收藏与否
    @GetMapping(value = "/api/user/favorOrNot/{id}")
    @ResponseBody
    public boolean favorOrNot(HttpServletRequest request, @PathVariable int id) {

        //当前用户
        UserModel user = this.getUser(request);
        //当前浏览职位
        PositionModel position = positionService.getPositionById(id);

        if (!favorService.favorOrNot(user.getUserId(), id)) {
            return false;
        } else {
            return true;
        }
    }

    // 用户收藏职位
    @GetMapping(value = "/api/user/favor/{id}")
    @ResponseBody
    public String favor(HttpServletRequest request, @PathVariable int id) {

        //当前用户
        UserModel user = this.getUser(request);
        //当前浏览职位
        PositionModel position = positionService.getPositionById(id);

        if (user == null) {
            return "fail";
        }
        boolean result = favorService.favorPosition(user.getUserId(), id);
        if (!result) {
            return "fail";
        }
        return "success";
    }

    // 用户取消收藏职位
    @GetMapping(value = "/api/user/disfavor/{id}")
    @ResponseBody
    public String disfavor(HttpServletRequest request, @PathVariable int id) {

        //当前用户
        UserModel user = this.getUser(request);
        //当前浏览职位
        PositionModel position = positionService.getPositionById(id);

        if (user == null) {
            return "fail";
        }
        boolean result = favorService.disfavorPosition(user.getUserId(), id);
        if (!result) {
            return "fail";
        }
        return "success";
    }

    // 职位评论 功能
    @PostMapping(value = "/api/user/comment")
    @ResponseBody
    public String comment(HttpServletRequest request, @RequestParam("posId") int id,
                          @RequestParam("type") int type, @RequestParam("content") String content) {
        //当前用户
        UserModel user = this.getUser(request);

        if (user == null) {
            return  "failed";
        }

        boolean result = commentService.commentPosition(type, content, user.getUserId(), id);
        if (!result) {
            return  "failed";
        }
        return "success";
    }

    @PostMapping(value = "/api/user/reply")
    @ResponseBody
    public String reply(HttpServletRequest request, @RequestParam("commentId") int commentId,
                          @RequestParam("replyUserId") int replyUserId, @RequestParam("content") String content) {
        //当前用户
        UserModel user = this.getUser(request);

        if (user == null) {
            return  "failed";
        }

        boolean result = replyService.replyComment(commentId,user.getUserId(), replyUserId, content);
        if (!result) {
            return  "failed";
        }
        return "success";
    }

    // 用户简历信息 输出
    @GetMapping(value = "/api/user/resume/show")
    @ResponseBody
    public String showResume(HttpServletRequest request) {
        //用户个人信息
        UserModel user = this.getUser(request);
        ResumeModel resume = resumeService.getResumeById(user.getUserId());
        Map output = new TreeMap();
        output.put("user", user);
        output.put("resume", resume);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    // 简历更新 功能
    @PostMapping(value = "/api/user/resume/update")
    @ResponseBody
    public Boolean updateResume(HttpServletRequest request, @RequestBody ResumeModel resumeModel) {

        if(!resumeService.updateResume(resumeModel)) {
            return false;
        }
        return true;
    }

    @GetMapping(value = "/api/user/comment/{id}/")
    @ResponseBody
    public String  listComment(HttpServletRequest request,@PathVariable int id) {

        ArrayList<UserCommentBO> comments = commentService.getComments(id);
        ArrayList<TreeMap> commentItems = new ArrayList<TreeMap>();
        for (UserCommentBO comment : comments) {
            int commitId = comment.getCommentId();
            ArrayList<ReplyModel> replies = replyService.getReply(commitId);
            TreeMap commentItem = new TreeMap();
            ArrayList<TreeMap> replyItems = new ArrayList<TreeMap>();
            for (ReplyModel reply:replies) {
                TreeMap replyItem = new TreeMap();
                int userId = reply.getUserId();
                int replyUserId = reply.getReplyUserId();
                UserModel user = userService.getUser(userId);
                UserModel replyUser = userService.getUser(replyUserId);
                replyItem.put("reply", reply);
                replyItem.put("user", user);
                replyItem.put("replyUser", replyUser);
                replyItems.add(replyItem);
            }
            commentItem.put("comment", comment);
            commentItem.put("reply", replyItems);
            commentItems.add(commentItem);
        }
        Map output = new TreeMap();
        output.put("comments", commentItems);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    // 职位细节页 评论分页输出 （职位，部门，公司，分类，评论列表）
    @GetMapping(value = "/position/{id}/{page}")
    @ResponseBody
    public String getPosition(HttpSession httpSession, @PathVariable int id, @PathVariable int page,
                              @RequestParam(value = "limit", defaultValue = "12") int limit) {

        PositionModel position = positionService.getPositionById(id);
        if (position == null) {
            this.errorDirect_404();
        }

        //当前用户信息
        UserModel user = (UserModel) httpSession.getAttribute("user");
        HRModel hr = (HRModel) httpSession.getAttribute("hr");

        //所属部门信息
        DepartmentModel department = departmentService.getDepartment(position.getDepartmentId());
        //所属公司信息
        CompanyModel company = companyService.getCompany(department.getCompanyId());
        //职位所属分类信息
        CategoryModel category = categoryService.getCategory(position.getCategoryId());
        //分页评论信息
        PageInfo<UserCommentBO> comList = commentService.listComment(id, page, limit);

        if (!positionService.updateHits(id)) {
            this.errorDirect_404();
        }

        Map output = new TreeMap();
        output.put("position", position);
        output.put("department", department);
        output.put("company", company);
        output.put("category", category);
        output.put("comList", comList);
        if (user != null) {
            output.put("user", user);
        }
        if (hr != null) {
            output.put("hr", hr);
        }

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    // 主页分页输出 （用户信息，职位列表）
    @GetMapping(value = "/page/{page}")
    @ResponseBody
    public String index(HttpSession httpSession, @PathVariable int page, @RequestParam(value = "limit", defaultValue = "1") int limit) {
        //测试用户
        UserModel user = (UserModel) httpSession.getAttribute("user");
        HRModel hr = (HRModel) httpSession.getAttribute("hr");

//        UserEntity user = new UserEntity();
//        user = userService.getUser(5);

        //推荐职位列表
        page = (page < 1 || page > GlobalConst.MAX_PAGE) ? 1 : page;

        Map output = new TreeMap();
        try {
            List<PositionCompanyBO> posInfo = positionService.recPosition(user, page, limit);
            output.put("posInfo", posInfo);
        } catch (Exception ex) {
            return ex.toString();
        }

        if (user != null) {
            output.put("user", user);
        }
        if (hr != null) {
            output.put("hr", hr);
        }

        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    // 职位分类页分页输出 （职位分类，职位列表）
    @PostMapping(value = "/category/{id}/{page}")
    @ResponseBody
    public String list(HttpServletRequest request, @PathVariable int id, @PathVariable int page, @RequestParam(value = "limit", defaultValue = "12") int limit) {

        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;
        CategoryModel category = categoryService.getCategory(id);
        if (category == null) {
            this.errorDirect_404();
        }
        PageInfo<PositionCompanyBO> posInfo = positionService.listPosition(id, page, limit);

        Map output = new TreeMap();
        output.put("title", ("第" + page + "页"));
        output.put("category", category);
        output.put("posInfo", posInfo);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    //职位搜索页分页输出 （关键字，职位列表）
    @GetMapping(value = "/api/position/search")
    @ResponseBody
    public String searchPosition(HttpServletRequest request,
           @RequestParam(value = "keyword",defaultValue="") String keyword,
           @RequestParam(value="categoryId", defaultValue="0") int categoryId,
           @RequestParam(value="workProvince", defaultValue="") String workProvince,
           @RequestParam(value="workCity",defaultValue = "") String workCity,
           @RequestParam(value="educationId",defaultValue = "0") int educationId,
           @RequestParam(value="page",defaultValue = "1") int page,
           @RequestParam(value = "limit", defaultValue = "3") int limit) {
        UserModel user = this.getUser(request);
        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;
        PageInfo<PositionCompanyBO> posInfo = positionService.searchPosition(keyword,categoryId,workProvince,workCity,educationId, page,limit);
        Map output = new TreeMap();
        output.put("user",user);
        output.put("pages", ("第" + page + "页"));
        if (!keyword.equals("")) {
            output.put("keyword", keyword);
        }
        output.put("posInfo", posInfo);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    @GetMapping(value = "/api/hr/position/list")
    @ResponseBody
    public String listAllPosition(){
        Gson gson = new Gson();
        ArrayList<PositionModel> positionList = positionService.listPosAll();

        String companyInfo = gson.toJson(positionList);
        return companyInfo;
    }

    @GetMapping(value = "/api/user/fav/list")
    @ResponseBody
    public String listAllFav(HttpServletRequest request){
        UserModel user = this.getUser(request);
        int id = user.getUserId();
        List<FavorPositionBO> favs = favorService.listFavorPosition(id);
        Gson gson = new Gson();
        String favInfo = gson.toJson(favs);
        return favInfo;
    }

    @GetMapping(value = "/api/user/app/{id}/list")
    @ResponseBody
    public String listMyApp(@PathVariable int id){
        ArrayList<ApplicationPositionHRBO> allApp = userService.listMyApp(id);
        Gson gson = new Gson();
        String apps = gson.toJson(allApp);
        return apps;
    }

    @GetMapping("/api/user/friendUrl/list")
    @ResponseBody
    public String listFriendUrl() {
        Gson gson = new Gson();
        ArrayList<FriendUrlsModel> friendUrls = friendUrlService.listUrls();
        String friendUrlsInfo = gson.toJson(friendUrls);
        return friendUrlsInfo;
    }
}
