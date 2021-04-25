package com.hct.eduservice.service;

import com.hct.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hct.eduservice.pojo.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author HCT
 * @since 2021-04-25
 */
public interface EduCourseService extends IService<EduCourse> {

    // 添加课程信息
    String addCourseInfo(CourseInfoVo courseInfoVo);
}
