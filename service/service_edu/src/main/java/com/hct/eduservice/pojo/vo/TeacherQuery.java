package com.hct.eduservice.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 讲师表单数据的封装类
 * @author: HCT
 * @create: 2021/04/25 14:18
 * @VVVersion 1.0
 **/
@Data
public class TeacherQuery {


    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1-高级讲师 2-首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间 ",example = "2021-01-01 13:14:00")
    private String begin;

    @ApiModelProperty(value = "查询结束时间 ",example = "2021-01-01 13:14:00")
    private String end;


}

