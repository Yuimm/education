package com.hct.eduservice.pojo.subjectTree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:  课程分类列表（树形）的一级分类
 * @author: HCT
 * @create: 2021/04/25 22:53
 * @VVVersion 1.0
 **/
@Data
public class OneSubject {

    private String id;
    private String title;

    //一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();

}
