package com.zengshen.api.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {



    @GetMapping("/test")
    public String test() {
        RedisTemplate redisTemplate = new RedisTemplate();
        System.out.println(redisTemplate);

        return "你好世界";
    }

//    @Autowired
//    private MinioTemplate minioTemplate;
//
//
//
//    @Autowired
//    private MinioConfigProperties minioConfigProperties;


//    @PostMapping("upload")
//    public String upload(MultipartFile file) throws Exception {
//        String fileName = file.getOriginalFilename();
//        ObjectWriteResponse objectWriteResponse = minioTemplate.uploadFile(fileName, file.getInputStream());
//
//        return minioTemplate.getFileHost() + "/" + minioConfigProperties.getBucketName() + "/" + fileName;
//    }
//
//    @PostMapping("upload2")
//    public String upload2(MultipartFile file) throws Exception {
//        String fileName = file.getOriginalFilename();
//        return this.minioTemplate.getFileHost() + "/" + minioConfigProperties.getBucketName() + "/" + fileName;
//    }


}
