package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import com.lagou.utils.FileUploadUtils;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 多条件查询课程
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO) {

        List<Course> list = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;
    }

    // 图片上传：使用Tomcat作为图片服务器
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) {

        try {

            Map<String, String> map = FileUploadUtils.upload(file, request);

            ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);

            return responseResult;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 新增&修改课程信息
    @RequestMapping("/saveOrUpdateCourse ")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException,
            IllegalAccessException {

        if (courseVO.getId() == null) {
            // 新增课程
            // 调用Service层保存课程信息
            courseService.saveCourseAndTeacher(courseVO);
            // 将处理结果响应给客户端
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        } else {
            // 修改课程
            courseService.updateCourseOrTeacher(courseVO);
            // 将处理结果响应给客户端
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }

    // 回显课程信息：根据课程ID查询课程信息和关联的讲师信息
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id) {

        CourseVO courseVO = courseService.findCourseById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseVO);
        return responseResult;
    }

    // 修改课程状态
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id, Integer status) {

        // 调用Service层修改课程状态
        courseService.updateCourseStatus(id, status);

        // 把课程状态封装到响应结果并返回
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);

        ResponseResult responseResult = new ResponseResult(true, 200, "更新课程状态成功", map);
        return responseResult;
    }

}
