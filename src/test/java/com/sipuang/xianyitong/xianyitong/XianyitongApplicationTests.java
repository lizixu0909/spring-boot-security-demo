package com.sipuang.xianyitong.xianyitong;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XianyitongApplicationTests {

    @Test
    public void contextLoads() {
        String pattern = "(?i)^asc$";
        boolean res = Pattern.matches(pattern, "Asec");
        System.out.println(res);
    }
}
