package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /**
     * 多条件查询课程
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /**
     * 保存课程和讲师信息
     */
    public void saveCourseAndTeacher(CourseVO courseVO);

    /**
     * 回显课程信息：根据课程ID查询课程信息和关联的讲师信息
     */
    public CourseVO findCourseById(Integer id);

    /**
     * 更新課程和/或讲师信息
     */
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /**
     * 修改课程状态
     */
    public void updateCourseStatus(Integer courseId, Integer status);
}
