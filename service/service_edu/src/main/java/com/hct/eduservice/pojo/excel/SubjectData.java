package com.hct.eduservice.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @description:
 * @author: HCT
 * @create: 2021/04/25 22:17
 * @VVVersion 1.0
 **/
@Data
public class SubjectData {

    // index的值代表excel表中的第几列

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;

}

