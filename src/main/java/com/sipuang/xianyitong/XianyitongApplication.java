package com.sipuang.xianyitong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.sipuang.xianyitong.mapper")
@SpringBootApplication
public class XianyitongApplication {

    public static void main(String[] args) {
        SpringApplication.run(XianyitongApplication.class, args);
    }
}
