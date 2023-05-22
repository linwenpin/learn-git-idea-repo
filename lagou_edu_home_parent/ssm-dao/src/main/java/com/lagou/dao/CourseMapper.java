package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    /**
     * 多条件查询课程
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /**
     * 添加课程信息
     */
    public void saveCourse(Course course);

    /**
     * 添加教师信息
     */
    public void saveTeacher(Teacher teacher);

    /**
     * 回显课程信息：根据课程ID查询课程信息和关联的讲师信息
     */
    public CourseVO findCourseById(Integer id);

    /**
     * 更新课程信息
     */
    public void updateCourse(Course course);

    /**
     * 更新讲师信息
     */
    public void updateTeacher(Teacher teacher);

    /**
     * 修改课程状态
     */
    public void updateCourseStatus(Course course);
}
