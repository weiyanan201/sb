package com.wei.eduyang.controller;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.bean.ResultEntity;
import com.wei.eduyang.exception.CustomException;
import com.wei.eduyang.mapper.PlanMapper;
import com.wei.eduyang.service.PlanService;
import com.wei.eduyang.util.ErrorMsg;
import com.wei.eduyang.util.FileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            JSONObject paraJson = new JSONObject();
            request.getParameterMap().keySet().forEach(i->{
                paraJson.put(i,request.getParameter(i));
            });
            String planName = paraJson.getString("planName");
            String planDir = UPLOAD_DIR+planName+"/";
            String planShowDir = planDir+"show/";

            if (planFiles!=null && planFiles.length>0){
                MultipartFile planFile = planFiles[0];
                String filePath = planDir + planFile.getOriginalFilename();
                paraJson.put("planPath",filePath);
                FileUtil.uploadFile(planFile.getBytes(), filePath);
            }
            if (showFiles!=null && showFiles.length>0){
                paraJson.put("planShowPath",planShowDir);
                for(MultipartFile file : showFiles){
                    FileUtil.uploadFile(file.getBytes(),planShowDir+file.getOriginalFilename());
                }
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
}
