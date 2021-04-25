package com.hct.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hct.eduservice.pojo.EduSubject;
import com.hct.eduservice.pojo.excel.SubjectData;
import com.hct.eduservice.service.EduSubjectService;
import com.hct.servicebase.exceptionhandler.HctException;

/**
 * @description: 读取Excel监听器
 * @author: HCT
 * @create: 2021/04/25 22:19
 * @VVVersion 1.0
 **/
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    /**
     * 因为SubjectExcelListener不能交给spring管理，需要自己new，不能注入其他对象
     * 不能实现数据库操作
     * 因此需要下面手动操作
     */
    public EduSubjectService eduSubjectService;
    public SubjectExcelListener(){}
    public SubjectExcelListener(EduSubjectService eduSubjectService){
        this.eduSubjectService = eduSubjectService;
    }



    //一行一行的读取excel内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new HctException(20001,"文件数据为空");
        }
        //每次读取都有两个值，第一个值是一级分类，第二个值是二级分类

        //添加一级分类
        //判断一级分类名是否重复
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null){ //没有相同的一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(existOneSubject);
        }

        //获取一级分类的id值
        String pid = existOneSubject.getId();
        //添加二级分类
        //判断二级分类名是否重复
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null){
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(existTwoSubject);
        }

    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }



    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }
}

