package com.wei.eduyang.controller;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.bean.ResultEntity;
import com.wei.eduyang.enums.UserType;
import com.wei.eduyang.exception.CustomException;
import com.wei.eduyang.mapper.PlanMapper;
import com.wei.eduyang.service.PlanService;
import com.wei.eduyang.util.Constants;
import com.wei.eduyang.util.ErrorMsg;
import com.wei.eduyang.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.split;

@RestController
@RequestMapping("/plan")

public class PlanController {

    private static Log logger = LogFactory.getLog(PlanController.class);

    private  String RESOURCE_DIR = Constants.RESOURCE_DIR;

    @Value("${app.uploadDir}")
    private String UPLOAD_DIR ;

    @Autowired
    private PlanService planService;


    @PostMapping("searchQuery")
    public ResultEntity searchQuery(@RequestBody JSONObject jsonParam){

        return planService.searchQuery(jsonParam);
    }

    @PostMapping("savePlan")
    @RequiresRoles(value = {UserType.ADMIN_STR})
    public ResultEntity savePlan(HttpServletRequest request,@RequestParam("planFile") MultipartFile[] planFiles,@RequestParam("pptShow") MultipartFile[] pptShow,@RequestParam("pdfShow") MultipartFile[] pdfShow) throws Exception {

        try {
            JSONObject paraJson = new JSONObject();
            request.getParameterMap().keySet().forEach(i->{
                paraJson.put(i,request.getParameter(i));
            });
            String planName = paraJson.getString("planName");
            String planDir = UPLOAD_DIR+planName+"/";
            String planShowDir = planDir+"show/";

            String relateDir = RESOURCE_DIR + planName+"/";
            String relateShowDir = relateDir+"show/";

            if (planFiles!=null && planFiles.length>0){
                MultipartFile planFile = planFiles[0];
                String filePath = planDir + planFile.getOriginalFilename();
                paraJson.put("planPath",relateDir+planFile.getOriginalFilename());
                FileUtil.uploadFile(planFile.getBytes(), filePath);
            }
            if (pptShow!=null && pptShow.length>0){
                MultipartFile tmp = pptShow[0];
                String fullName = tmp.getOriginalFilename();
                //mac
                String[] ss = StringUtils.split(fullName,"/");
                if (ss!=null&&ss.length==2){
                    paraJson.put("planShowPath",relateShowDir+"/"+ss[0]+"/index.html");
                }
                //TODO windows
                for(MultipartFile file : pptShow){
                    FileUtil.uploadFile(file.getBytes(),planShowDir+file.getOriginalFilename());
                }
            }
            if (pdfShow!=null && pdfShow.length>0){
                MultipartFile pdfFile = pdfShow[0];
                String pdfName = pdfFile.getOriginalFilename();
                String pdfPath = planShowDir + pdfFile.getOriginalFilename();
                paraJson.put("planShowPath",relateShowDir+pdfName);
                FileUtil.uploadFile(pdfFile.getBytes(), pdfPath);
            }
            planService.savePlan(paraJson);
        }catch (Exception e){
            logger.error(e);
            throw new CustomException(ErrorMsg.ADD_UPDATE_PLAN_FAILD);
        }
        return new ResultEntity();
    }

    @PostMapping("download")
    public ResultEntity downloadFile(HttpServletResponse response,@RequestParam(value = "id") int id){
        try {
            return planService.downLoadPlan(response,id);
        } catch (IOException e) {
            throw new CustomException("下载失败");
        }
    }

    @PostMapping("delete")
    @RequiresRoles(value = {UserType.ADMIN_STR})
    public ResultEntity deletePlan(int id){
        try{
            return planService.deletePlan(id);
        }catch (Exception e){
            throw new CustomException("删除失败!");
        }
    }

    @GetMapping("getShowPath")
    public ResultEntity getShowPath(int id){
        return planService.getShowPath(id);
    }

    @GetMapping("role")
    @RequiresRoles(value = {UserType.ADMIN_STR})
    public String test() {
        return "aaaa";
    }
}
