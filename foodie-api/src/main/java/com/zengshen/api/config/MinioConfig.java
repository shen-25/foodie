package com.zengshen.api.config;

import com.zengshen.common.template.MinioTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author word
 */
@Configuration
@EnableConfigurationProperties(MinioConfigProperties.class)
@ConditionalOnProperty(prefix = "minio", name = {"endpoint"}, matchIfMissing = false)
public class MinioConfig {

    @Bean("minioTemplate")
    public MinioTemplate minioTemplate(MinioConfigProperties minioConfigProperties) {

        return new MinioTemplate(minioConfigProperties.getEndpoint(), minioConfigProperties.getBucketName(),
                minioConfigProperties.getAccessKey(), minioConfigProperties.getSecretKey(), minioConfigProperties.getFileHost());
    }
}
