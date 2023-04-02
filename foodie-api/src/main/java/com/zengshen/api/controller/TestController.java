package com.zengshen.api.controller;

import com.zengshen.api.config.MinioConfigProperties;
import com.zengshen.api.config.MinioTemplate;
import io.minio.ObjectWriteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "你好世界";
    }

    @Autowired
    private MinioTemplate minioTemplate;

    @Autowired
    private MinioConfigProperties minioConfigProperties;


    @PostMapping("upload")
    public String upload(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        ObjectWriteResponse objectWriteResponse = minioTemplate.uploadFile(fileName, file.getInputStream());

        return minioTemplate.getFileHost() + "/" + minioConfigProperties.getBucketName() + "/" + fileName;
    }


}
