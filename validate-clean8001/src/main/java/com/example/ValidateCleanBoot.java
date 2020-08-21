package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/21 15:04
 * @desc
 */
@SpringBootApplication
@EntityScan(basePackages = "entity")
public class ValidateCleanBoot {

    public static void main(String[] args) {
        SpringApplication.run(ValidateCleanBoot.class, args);
    }
}
