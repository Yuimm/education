package com.hct.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.hct.eduservice.listener.SubjectExcelListener;
import com.hct.eduservice.pojo.EduSubject;
import com.hct.eduservice.mapper.EduSubjectMapper;
import com.hct.eduservice.pojo.excel.SubjectData;
import com.hct.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author HCT
 * @since 2021-04-25
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void addSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //文件的输入流
            InputStream fileIn = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(fileIn, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
