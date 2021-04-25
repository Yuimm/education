package com.hct.oss.controller;

import com.hct.commonutils.R;
import com.hct.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: HCT
 * @create: 2021/04/25 20:05
 * @VVVersion 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传头像的方法
     * @param file
     * @return 文件的oss路径
     */
    @PostMapping("/uploadfile")
    public R uploadFile(MultipartFile file){
        //获取上传文件 MultipartFile
        String url = ossService.uploadFileAvatar(file);

        return R.ok().data("url",url);
    }


}
