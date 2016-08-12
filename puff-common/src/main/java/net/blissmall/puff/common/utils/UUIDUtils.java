package net.blissmall.puff.common.utils;

import java.util.UUID;

/**
 * 根据JDK自带的lib生成uuid
 */
public class UUIDUtils {

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] uuid(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = uuid();
        }
        return ss;
    }
}