package com.hct.eduservice.controller;


import com.hct.commonutils.R;
import com.hct.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author HCT
 * @since 2021-04-25
 */
@Api(description = "课程分类")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    //获取上传过来的文件，读取文件内容
    @ApiOperation(value = "添加课程分类")
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){

        eduSubjectService.addSubject(file,eduSubjectService);

        return R.ok();
    }


}

