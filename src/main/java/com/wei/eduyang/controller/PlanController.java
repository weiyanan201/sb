package com.wei.eduyang.controller;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.bean.PageSize;
import com.wei.eduyang.bean.ResultEntity;
import com.wei.eduyang.domain.Plan;
import com.wei.eduyang.exception.CustomException;
import com.wei.eduyang.mapper.PlanMapper;
import com.wei.eduyang.service.PlanService;
import com.wei.eduyang.util.FileUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.UploadContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan")

public class PlanController {

    private static Log logger = LogFactory.getLog(PlanController.class);

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
    public ResultEntity savePlan(HttpServletRequest request,@RequestParam("planFile") MultipartFile[] planFiles,@RequestParam("planShow") MultipartFile[] showFiles) throws Exception {
        try {
            String fileName = null;
            String msg = "";

            if (planFiles!=null && planFiles.length>0){
                System.out.println(planFiles[0].getOriginalFilename());
            }
            throw new Exception("xxxxx");
        }catch (Exception e){
            logger.error(e);
            throw e;
        }

    }

}
