package com.hct.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.hct.oss.service.OssService;
import com.hct.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @description:
 * @author: HCT
 * @create: 2021/04/25 20:03
 * @VVVersion 1.0
 **/
@Service
public class OssServiceImpl implements OssService {

    //上传头像的方法
    @Override
    public String uploadFileAvatar(MultipartFile file) {

        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        OSS ossClient = null;
        String url = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取文件的名称
            String fileName = file.getOriginalFilename();

            //在每个文件的名称添加随机的唯一值，使每个文件不重名
            String uuid = UUID.randomUUID().toString().replace("-","");
            fileName = uuid + fileName;

            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath + "/" + fileName;

            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            // 第一个参数 Bucket名称
            // 第二个参数 上传到oss文件路径和名称
            // 第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            //把上传文件之后的路径返回
            url = "https://" + bucketName + "." + endpoint + "/" + fileName;

        }catch (Exception e){

        }finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }


        return url;
    }
}
