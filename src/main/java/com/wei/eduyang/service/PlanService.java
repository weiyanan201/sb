package com.wei.eduyang.service;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.bean.ResultEntity;
import com.wei.eduyang.domain.Plan;
import com.wei.eduyang.exception.CustomException;
import com.wei.eduyang.mapper.PlanMapper;
import com.wei.eduyang.util.CommonUtil;
import com.wei.eduyang.util.ErrorMsg;
import com.wei.eduyang.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class PlanService {

    @Autowired
    private PlanMapper planMapper;

    public ResultEntity searchQuery(JSONObject jsonParam){
        ResultEntity resultEntity = new ResultEntity();

        CommonUtil.convertPageSizeFromJson(jsonParam);
        Map paraMap = JSONObject.toJavaObject(jsonParam,Map.class);
        List<Plan> list = planMapper.searchQuery(paraMap);
        int count = planMapper.searchQueryCount(paraMap);
        JSONObject resultJson = new JSONObject();
        resultJson.put("list",list);
        resultJson.put("total",count);
        resultEntity.setData(resultJson);
        resultEntity.setReturnCode(ResultEntity.SUCCESS);

        return resultEntity;
    }

    public ResultEntity savePlan(JSONObject jsonParam){
        Plan plan = jsonParam.toJavaObject(Plan.class);
        if (plan.getId()!=0){
            //update
           planMapper.updatePlan(plan);
        }else{
            //insert
           planMapper.insertPlan(plan);
        }
        return new ResultEntity();
    }

    public ResultEntity downLoadPlan(HttpServletResponse response, int id ) throws IOException {
        ResultEntity resultEntity = new ResultEntity();
        if (id==0){
            throw new CustomException("");
        }
        Plan plan = planMapper.getPlanById(id);
        if (plan==null){
            throw new CustomException("");
        }
        String filePath = plan.getPlanPath();
        if (!FileUtil.exists(filePath)){
            throw new CustomException(ErrorMsg.FILE_NOT_EXIST);
        }
        File file = new File(filePath);
        System.out.println(file.getName());
        String fileName = file.getName();
        response.setHeader("content-disposition", "attachment;filename=" +fileName);
        response.setHeader("fileName",fileName);
        response.setHeader("content-type","application/octet-stream");
        InputStream is = FileUtils.openInputStream(file);
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        os.flush();
        os.close();
        return resultEntity;
    }

    public ResultEntity deletePlan(int id) throws IOException {
        Plan plan = planMapper.getPlanById(id);
        //删除数据库
        planMapper.deletePlan(id);
        //删除文件
        String planPath = plan.getPlanPath();
        String planShowPath = plan.getPlanShowPath();
        if (!StringUtils.isBlank(planPath)){
            FileUtil.deleteFile(planPath);
        }
        if (!StringUtils.isBlank(planShowPath)){
            FileUtil.deleteDir(planShowPath);
        }
        return new ResultEntity();
    }

}
