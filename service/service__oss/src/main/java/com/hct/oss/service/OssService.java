package com.hct.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    //上传头像的方法
    String uploadFileAvatar(MultipartFile file);
}
