package com.hct.eduservice.service.impl;

import com.hct.eduservice.pojo.EduCourse;
import com.hct.eduservice.mapper.EduCourseMapper;
import com.hct.eduservice.pojo.EduCourseDescription;
import com.hct.eduservice.pojo.vo.CourseInfoVo;
import com.hct.eduservice.service.EduCourseDescriptionService;
import com.hct.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hct.servicebase.exceptionhandler.HctException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author HCT
 * @since 2021-04-25
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    // 需要向简介表中添加数据，所以需要把EduCourseDescriptionService注入才能操作
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    // 添加课程信息
    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {

        //1.向课程表添加数据
        // 把courseInfoVo转为eduCourse才能向课程表中添加
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) {
            throw new HctException(20001,"添加课程信息失败");
        }

        //获取添加之后课程id
        String cid = eduCourse.getId();

        //2.向课程简介表中添加数据
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id 就是课程id，记得将实体类中的填充模式改为INPUT
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);
        return cid;
    }

}
