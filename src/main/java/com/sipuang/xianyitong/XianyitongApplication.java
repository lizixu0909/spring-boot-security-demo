package com.sipuang.xianyitong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;
import tk.mybatis.spring.annotation.MapperScan;


@EnableCaching
@MapperScan("com.sipuang.xianyitong.system.mapper")
@SpringBootApplication
public class XianyitongApplication {
    public static void main(String[] args) {
        SpringApplication.run(XianyitongApplication.class, args);
    }
}
