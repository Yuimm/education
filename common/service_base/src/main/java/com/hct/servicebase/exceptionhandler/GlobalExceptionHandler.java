package com.hct.servicebase.exceptionhandler;


import com.hct.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:  统一异常处理
 * @author: HCT
 * @create: 2021/04/25 14:48
 * @VVVersion 1.0
 **/
@ControllerAdvice
@Slf4j //将异常输出到日志文件中
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class) //指定出现什么异常时执行这个方法
    @ResponseBody
    public R error(Exception e){
        log.error(e.getMessage()); //将异常输出到日志文件中
        e.printStackTrace();
        return R.error().message("执行了全局异常处理......");
    }

    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理......");
    }

    //自定义异常
    @ExceptionHandler(HctException.class)
    @ResponseBody
    public R error(HctException e){
        log.error(e.getMsg());
        e.printStackTrace();
        return R.error()
                .code(e.getCode())
                .message(e.getMsg());
    }

}
