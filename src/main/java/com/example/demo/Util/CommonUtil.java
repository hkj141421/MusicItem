package com.example.demo.Util;

import java.util.Random;

/**
 * Created by forget on 2019/3/26.
 */
public class CommonUtil {
    public static Integer getRandomNumber(Integer bound) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        return random.nextInt(bound);
    }
}
