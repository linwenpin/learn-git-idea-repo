package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /**
     * 根据课程ID查询课程内容（包括章节和课时）
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /**
     * 回显章节对应的课程信息：根据课程ID查询课程信息
     */
    public Course findCourseByCourseId(Integer courseId);

    /**
     * 新增章节信息
     */
    public void saveSection(CourseSection section);

    /**
     * 更新章节信息
     */
    public void updateSection(CourseSection section);

    /**
     * 修改章节状态
     */
    public void updateSectionStatus(CourseSection section);

    /**
     * 新增课时信息
     */
    public void saveLesson(CourseLesson lesson);
}
