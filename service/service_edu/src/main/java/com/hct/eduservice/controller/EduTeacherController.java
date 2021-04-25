package com.hct.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hct.commonutils.R;
import com.hct.eduservice.pojo.EduTeacher;
import com.hct.eduservice.pojo.vo.TeacherQuery;
import com.hct.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author HCT
 * @since 2021-04-25
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;


    /**
     * 1.查询所有讲师
     * restful风格
     * @return
     */

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public com.hct.commonutils.R findAllTeacher(){
        //调用service方法，查询所有
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }
    /**
     * 2.逻辑删除讲师
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("/{id}") //id需要通过路径传递
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    /**
     * 3.分页查询讲师数据
     * @param current 当前页
     * @param limit 每页记录数
     * @return
     */
    @ApiOperation(value = "分页查询讲师数据")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current",value = "当前页",required = true) @PathVariable long current,
                             @ApiParam(name = "limit",value = "每页记录数",required = true) @PathVariable long limit){

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //调用方法实现分页
        //调用方法的时候，底层封装，把分页的所有数据封装到pageTeacher对象里面
        eduTeacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //每页数据的list集合

//        //返回值 方法一
//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("records",records);
//        return R.ok().data(map);

        //返回值 方法二
        return R.ok().data("total",total).data("records",records);

    }
    /**
     * 4.多条件查询讲师-带分页
     * @param  current 当前页
     * @param  limit 每页记录数
     * @param teacherQuery
     * @return
     */
    @ApiOperation(value = "多条件查询讲师-带分页")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "current",value = "当前页",required = true)@PathVariable long current,
                                  @ApiParam(name = "limit",value = "每页记录数",required = true)@PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty((name))) {
            //构建条件
            wrapper.like("name",name);

        }
        if (!StringUtils.isEmpty((level))) {
            //构建条件
            wrapper.eq("level",level);

        }
        if (!StringUtils.isEmpty((begin))) {
            //构建条件
            wrapper.ge("gmt_create",begin);

        }
        if (!StringUtils.isEmpty((end))) {
            //构建条件
            wrapper.le("gmt_create",end);

        }

        //按照添加的时间进行降序排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现-条件查询分页
        eduTeacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //每页数据的list集合
        return R.ok().data("total",total).data("records",records);
    }
    /**
     * 添加讲师
     * @param eduTeacher
     * @return
     */
    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        } else {
            return R.error();
        }
    }
    /**
     * 根据ID查询讲师
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("/getTeacher/{id}")
    public R getTeachetById(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable String id){
        EduTeacher eduTeacherById = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacherById);
    }


    /**
     * 讲师信息修改
     * @param eduTeacher
     * @return
     */
    @ApiOperation(value = "讲师修改")
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }


}

