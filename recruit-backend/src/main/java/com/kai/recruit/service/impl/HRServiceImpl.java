package com.kai.recruit.service.impl;

import com.kai.recruit.mapper.PositionMapper;
import com.kai.recruit.pojo.ApplicationPositionHRBO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.kai.recruit.model.ApplicationModel;
import com.kai.recruit.model.HRModel;
import com.kai.recruit.model.PositionModel;
import com.kai.recruit.mapper.HRMapper;
import com.kai.recruit.service.HRService;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HRServiceImpl implements HRService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private HRMapper hrMapper;

    @Resource
    private PositionMapper positionMapper;

    @Override
    public HRModel getHR(int HRId) {
        return hrMapper.getHR(HRId);
    }

    @Override
    public ArrayList<HRModel> listHR() {
        return hrMapper.listHR();
    }

    @Override
    public boolean updateHr(HRModel HRModel) {
        if (hrMapper.updateHr(HRModel)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delHR(int hrId) {
        if (hrMapper.delHR(hrId)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean registerHR(HRModel HRModel) {

        String password = HRModel.getHrPassword();
        String hr  = HRModel.getHr();

        if(hrMapper.getHRByHr(hr) != null) {
            return false;
        }

        int result = -1;
        try {
            String encPass = this.EncodingByMd5(password);
            HRModel.setHrPassword(encPass);
            result = hrMapper.saveHR(hr, encPass);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("md5加密出错");
        } catch (UnsupportedEncodingException e) {
            System.out.println("编码转化出错");
        } finally {
            if (result > 0) {
                return true;
            }
            System.out.println("bbbbbbbbbbbbbbbbbb");
            return false;
        }
    }

    @Override
    public boolean loginHR(String hr, String password) {
        if(hrMapper.getHRByHr(hr) == null) {
            return false;
        }
        String passwordDB = hrMapper.getHRByHr(hr).getHrPassword();

        try {
            if (this.EncodingByMd5(password).equals(passwordDB)) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("md5加密出错");
        } catch (UnsupportedEncodingException e) {
            System.out.println("编码转化错误");
        }
        return false;
    }


    @Override
    public HRModel getHRByHr(String hr){

        return hrMapper.getHRByHr(hr);
    }

    @Override
    public ArrayList<ApplicationModel> queryApplication(int hrId) {
        return hrMapper.queryApplication(hrId);
    }

    @Override
    public int addPostion(PositionModel positionModel) {
        return hrMapper.addPostion(positionModel);
    }

    @Override
    public int delPostion(int positionId) {
        return  hrMapper.delPostion(positionId);
    }

    @Override
    public boolean updatePostion(PositionModel positionModel) {
        if (hrMapper.updatePostion(positionModel) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<PositionModel> listAllPosition() {
        return hrMapper.listAllPositioni();
    }

//    @Override
//    public List<PositionModel> showPosition(int hrId) {
//        return positionMapper.listHRPos(hrId);
//    }
    @Override
    public boolean updatePwd(int hrId, String oldpassword, String newpassword) {
        String orginpassword = this.getHR(hrId).getHrPassword();
        String encPass = "";
        try {
            encPass = this.EncodingByMd5(oldpassword);
        } catch (NoSuchAlgorithmException e) {
            return false;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        if(!encPass.equals(orginpassword)) {
            return false;
        }
        try {
            encPass = this.EncodingByMd5(newpassword);
        } catch (NoSuchAlgorithmException e) {
            return false;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        if (hrMapper.updatePwd(hrId, encPass) > 0){
            return true;
        }
        return false;
    }

    public String EncodingByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();

        //加密后的字符串
        String encStr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return encStr;
    }

}
