package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    // 根据课程ID查询课程内容（包括章节和课时）
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {

        return courseContentMapper.findSectionAndLessonByCourseId(courseId);
    }

    // 回显章节对应的课程信息：根据课程ID查询课程信息
    @Override
    public Course findCourseByCourseId(Integer courseId) {

        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    // 新增章节信息
    @Override
    public void saveSection(CourseSection section) {

        // 补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);

        // 调用Dao层新增章节
        courseContentMapper.saveSection(section);
    }

    // 更新章节信息
    @Override
    public void updateSection(CourseSection section) {

        // 补全信息
        section.setUpdateTime(new Date());

        // 调用Dao层修改章节信息
        courseContentMapper.updateSection(section);
    }

    // 修改章节状态
    @Override
    public void updateSectionStatus(int id, int status) {

        // 封装实体
        CourseSection section = new CourseSection();
        section.setStatus(status);
        section.setUpdateTime(new Date());
        section.setId(id);

        // 调用Dao层完成章节状态修改
        courseContentMapper.updateSectionStatus(section);
    }

    //新增课时信息
    @Override
    public void saveLesson(CourseLesson lesson) {

        // 补全信息
        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);

        // 调用DAO层保存课时信息
        courseContentMapper.saveLesson(lesson);
    }
}
