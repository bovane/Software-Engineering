package com.example.demo.util;

import java.util.Random;

public class CodeUtil {
    //生成唯一的激活码
    public static String generateUniqueCode(){
       StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i=0; i<6;i++) {
            buffer.append(random.nextInt(10)+"");
        }
        String code = buffer.toString();
        return code;
    }
}
