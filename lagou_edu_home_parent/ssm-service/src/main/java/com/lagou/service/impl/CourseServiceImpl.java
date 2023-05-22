package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    // 多条件查询课程
    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {

        List<Course> list = courseMapper.findCourseByCondition(courseVO);
        return list;
    }

    // 保存课程和讲师信息
    @Override
    public void saveCourseAndTeacher(CourseVO courseVO) {

        try {
            // 1. 封装Course
            Course course = new Course();

            BeanUtils.copyProperties(course, courseVO);

            // 补全信息
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);

            // 2. 调用Dao层保存课程信息, 成功插入记录后，会将ID值封装到Course对象中
            courseMapper.saveCourse(course);

            // 3. 封装Teacher信息
            Teacher teacher = new Teacher();

            BeanUtils.copyProperties(teacher, courseVO);

            // 补全信息
            Integer courseId = course.getId();

            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            teacher.setCourseId(courseId);

            // 4. 调用Dao层保存教师信息
            courseMapper.saveTeacher(teacher);
        } catch (IllegalAccessException|InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 回显课程信息：根据课程ID查询课程信息和关联的讲师信息
    @Override
    public CourseVO findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    // 更新課程和/或讲师信息
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        // 封装Course
        Course course = new Course();
        BeanUtils.copyProperties(course, courseVO);

        // 补全信息
        Date date = new Date();
        course.setUpdateTime(date);

        // 调用Dao层更新课程信息
        courseMapper.updateCourse(course);

        // 封装Teacher
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVO);

        // 补全信息
        teacher.setCourseId(courseVO.getId());
        teacher.setUpdateTime(date);

        // 调用Dao层更新课程信息
        courseMapper.updateTeacher(teacher);
    }

    // 修改课程状态
    @Override
    public void updateCourseStatus(Integer courseId, Integer status) {

        // 封装数据
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        // 调用DAO
        courseMapper.updateCourseStatus(course);
    }
}
