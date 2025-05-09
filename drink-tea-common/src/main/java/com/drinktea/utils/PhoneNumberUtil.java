package com.drinktea.utils;

import java.util.concurrent.ThreadLocalRandom;

public class PhoneNumberUtil {

    // 常见的中国大陆手机号码段（示例，不一定穷尽所有）
    private static final String[] PREFIXES = {
            "130", "131", "132", "133", "134", "135", "136", "137", "138", "139",
            "147", "150", "151", "152", "153", "155", "156", "157", "158", "159",
            "166", "173", "174", "175", "176", "177", "178",
            "180", "181", "182", "183", "184", "185", "186", "187", "188", "189",
            "198", "199"
    };

    /**
     * 随机生成一个中国大陆手机号
     * @return 11 位手机号码字符串
     */
    public static String randomPhoneNumberGenerate() {
        // 1. 随机选取前缀
        String prefix = PREFIXES[ThreadLocalRandom.current().nextInt(PREFIXES.length)];

        // 2. 随机生成后 8 位数字
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = 0; i < 8; i++) {
            int digit = ThreadLocalRandom.current().nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
}
