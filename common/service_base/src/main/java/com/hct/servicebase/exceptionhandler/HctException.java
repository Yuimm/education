package com.hct.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 自定义异常
 * @author: HCT
 * @create: 2021/04/25 14:48
 * @VVVersion 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HctException extends RuntimeException{

    private Integer code; //状态码

    private String msg; //异常信息


}