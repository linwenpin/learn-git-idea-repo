package com.lagou.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUploadUtils {

    public final static String LOCAL_URL = "http://localhost:8080";

    public static Map<String, String> upload(MultipartFile file, HttpServletRequest request) throws IOException {

            // 1. 判断文件是否为空
            if (file.isEmpty()) {
                throw new RuntimeException();
            }

            // 2. 获取项目部署路径
            // D:\apache-tomcat-8.5.56\webapps\ssm_web\
            String realPath = request.getServletContext().getRealPath("/");
            // \ssm_web
            String contextPath = request.getContextPath();
            // D:\apache-tomcat-8.5.56\webapps\
            String webappsPath = realPath.substring(0, realPath.lastIndexOf(contextPath.substring(1)));

            // 3. 获取文件名
            String fileName = file.getOriginalFilename();

            // 4. 获取新文件名
            String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));

            // 5. 上传文件的路径
            String uploadPath = webappsPath + "upload\\";
            File uploadFile = new File(uploadPath, newFileName);

            // 6. 如果目录不存在则创建目录
            if (!uploadFile.getParentFile().exists()) {
                uploadFile.getParentFile().mkdirs();
                System.out.println("创建目录：" + uploadFile.getParentFile());
            }

            // 7. 上传文件
            file.transferTo(uploadFile);

            // 8. 将文件名和文件路径返回
            Map<String, String> map = new HashMap<>();
            map.put("fileName", newFileName);
            map.put("filePath", LOCAL_URL + "/upload/" + newFileName);

            return map;
    }
}
