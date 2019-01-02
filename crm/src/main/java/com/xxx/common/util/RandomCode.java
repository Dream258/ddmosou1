package com.xxx.common.util;

import java.util.Random;

public class RandomCode {

    /**
     * @Description: 获取6为随机数字
     * @return String 返回类型
     */
    public static String getCode(int length) {
        String str = "";

        str += (int) (Math.random() * length + 1);

        for (int i = 0; i < (length-1); i++) {

            str += (int) (Math.random() * 10);
        }
        return str;
    }

    /**
     * java生成随机数字和字母组合
     * @return
     */
    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
