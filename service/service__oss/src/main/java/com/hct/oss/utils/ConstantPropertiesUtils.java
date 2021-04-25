package com.hct.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description:  用来读取application.properties中的配置信息
 * @author: HCT
 * @create: 2021/04/25 20:01
 * @VVVersion 1.0
 **/
//当项目启动时，spring接口，spring加载之后，执行一个接口
@Component
public class ConstantPropertiesUtils implements InitializingBean {


    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyid;

    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;


    //定义公开静态的常量
    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    //当上面的定义的值被spring加载后会执行下面这个方法
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        KEY_ID = keyid;
        KEY_SECRET = keysecret;
        BUCKET_NAME = bucketname;
    }
}
