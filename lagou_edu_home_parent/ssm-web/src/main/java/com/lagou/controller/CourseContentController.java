package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /**
     * 根据课程ID查询课程内容（包括章节和课时）
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam Integer courseId) {

        // 调用Service层 查询课程内容
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        // 把查询结果封装到响应结果中，进行响应
        ResponseResult responseResult = new ResponseResult(true, 200, "章节和课时信息查询成功", list);

        return responseResult;
    }

    /**
     * 回显章节对应的课程信息：根据课程ID查询课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam Integer courseId) {

        // 调用Service层查询课程信息
        Course course = courseContentService.findCourseByCourseId(courseId);

        // 响应
        ResponseResult responseResult = new ResponseResult(true, 200, "课程信息查询成功", course);

        return responseResult;
    }

    /**
     * 新增&更新章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section) {

        // 根据章节ID是否存在判断要执行的添加还是更新
        if (section.getId() == null) {
            // 新增
            // 调用Service层完成新增章节
            courseContentService.saveSection(section);
            // 进行响应
            return new ResponseResult(true, 200, "新增章节成功", null);
        } else {
            // 更新
            courseContentService.updateSection(section);
            // 进行响应
            return new ResponseResult(true, 200, "更新章节成功", null);
        }

    }

    /**
     * 修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id, Integer status) {

        // 调用Service层修改章节状态
        courseContentService.updateSectionStatus(id, status);

        // 进行响应
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);

        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功", map);

        return responseResult;
    }

    /**
     * 新增课时信息
     */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson lesson) {

        // 调用Service层保存课时信息
        courseContentService.saveLesson(lesson);

        // 进行响应
        return new ResponseResult(true, 200, "新增课时成功", null);
    }

}
