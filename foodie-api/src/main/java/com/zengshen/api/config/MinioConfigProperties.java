package com.zengshen.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author word
 */

@ConfigurationProperties(prefix = "minio")
@Data
public class MinioConfigProperties {

    private String endpoint;

    private String bucketName;

    private String accessKey;

    private String secretKey;

    private String fileHost;

}
