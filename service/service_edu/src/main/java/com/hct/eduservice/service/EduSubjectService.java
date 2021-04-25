package com.hct.eduservice.service;

import com.hct.eduservice.pojo.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hct.eduservice.pojo.subjectTree.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author HCT
 * @since 2021-04-25
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void addSubject(MultipartFile file, EduSubjectService eduSubjectService);

    //课程分类列表-树形
    List<OneSubject> getAllOneTwoSubject();
}
