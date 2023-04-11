package com.zengshen;

import com.zengshen.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author word
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.zengshen.mapper"})
@ComponentScan(basePackages = {"com.zengshen", "org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public IdWorker idWorker() {
       // 雪花算法集成,每台服务器这个数字改一下-
        return new IdWorker(1, 1);
    }
}
