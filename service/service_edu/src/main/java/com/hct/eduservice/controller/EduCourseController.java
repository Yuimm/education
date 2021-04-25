package com.hct.eduservice.controller;


import com.hct.commonutils.R;
import com.hct.eduservice.pojo.vo.CourseInfoVo;
import com.hct.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author HCT
 * @since 2021-04-25
 */
@Api(description = "课程管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 添加课程信息
     *
     * @param courseInfoVo 课程信息的封装类
     * @return
     */
    @ApiOperation("添加课程信息")
    @PostMapping("/addCourseInfo")
    public R addCourse(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加之后课程id，为了后面添加大纲使用
        String id = eduCourseService.addCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }

}

