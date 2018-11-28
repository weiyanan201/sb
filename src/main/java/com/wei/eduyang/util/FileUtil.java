package com.wei.eduyang.util;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.domain.Plan;
import com.wei.eduyang.exception.CustomException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static String SEPARATOR = File.separator;

    public static void uploadFile(byte[] fileByte, String fileName) throws Exception{

        File file = new File(fileName);
        if (!file.exists()) {
            FileUtils.forceMkdirParent(file);
        }

        FileUtils.writeByteArrayToFile(file,fileByte);

    }

    public  static boolean exists(String path){
        if (StringUtils.isBlank(path)){
            throw new CustomException(ErrorMsg.FILE_STRING_BLANK);
        }
        File file = new File(path);
        return file.exists();
    }

    public  static boolean exists(File file){
        return file.exists();
    }

    public static void deleteDir(String path) throws IOException {
        if (exists(path)){
            FileUtils.deleteDirectory(new File(path));
        }
    }

    public static void deleteFile(String path){
        File file = new File(path);
        if (exists(file)){
            file.delete();
        }
    }

    public static void main(String[] args) {
        System.out.println(FileUtil.SEPARATOR);

        JSONObject object = new JSONObject();
        object.put("planNameR","test");
        object.put("planDesc","test");
        object.put("tagCourse","1");
        object.put("tagTheme","2");

        Plan plan = object.toJavaObject(Plan.class);
        System.out.println(plan);


    }

}
