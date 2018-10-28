package com.wei.eduyang.controller;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.bean.ResultEntity;
import com.wei.eduyang.domain.Plan;
import com.wei.eduyang.mapper.PlanMapper;
import com.wei.eduyang.service.PlanService;
import com.wei.eduyang.util.FileUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.UploadContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.testng.collections.Maps;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan")

public class PlanController {

    @Value("${app.uploadDir}")
    private  String UPLOAD_DIR ;

    @Autowired
    private PlanService planService;
    @Autowired
    private  PlanMapper planMapper;

    @PostMapping("searchQuery")
    public ResultEntity searchQuery(@RequestBody JSONObject jsonParam){

        return planService.searchQuery(jsonParam);
    }

    @PostMapping("savePlan")
    public ResultEntity savePlan(HttpServletRequest request) throws Exception {

        StandardMultipartHttpServletRequest standardMultipartHttpServletRequest = (StandardMultipartHttpServletRequest) request;

        //表单参数
        JSONObject paraJson = new JSONObject();
        request.getParameterMap().keySet().forEach(i->{
            paraJson.put(i,request.getParameter(i));
        });
        String planName = paraJson.getString("planName");
        String planDir = UPLOAD_DIR+"/"+planName+"/";
        String planShowDir = planDir+"show/";


        MultiValueMap<String, MultipartFile>  fileMap = standardMultipartHttpServletRequest.getMultiFileMap();
        for(Map.Entry<String, List<MultipartFile>> entry : fileMap.entrySet()){
            if (StringUtils.equals("planFile",entry.getKey())){
                //教案文件
                Plan oldPlan = planMapper.getPlanByName(paraJson.getString(planName));
                if (oldPlan!=null){
                    String oldPath = oldPlan.getPlanPath();
                    FileUtil.deleteFile(oldPath);
                }
                List<MultipartFile> files = entry.getValue();
                MultipartFile planFile = files.get(0);
                String filePath = planDir + planFile.getOriginalFilename();
                paraJson.put("planPath",filePath);
                FileUtil.uploadFile(planFile.getBytes(), filePath);
            }else if (StringUtils.equals("planShow",entry.getKey())){
                //展示文件
                if (FileUtil.exists(planShowDir)){
                    FileUtil.deleteDir(planShowDir);
                }
                List<MultipartFile> files = entry.getValue();
                for(MultipartFile file : files){
                    FileUtil.uploadFile(file.getBytes(),planShowDir+file.getOriginalFilename());
                }
            }
        }
        return planService.savePlan(paraJson);

    }
}
