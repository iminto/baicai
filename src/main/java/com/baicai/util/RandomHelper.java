package com.baicai.util;
import java.util.Random;

public class RandomHelper {
    private RandomHelper() {
    }

    private final static char[] alphabet = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', // 0
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', // 1
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', // 2
        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', // 3
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', // 4
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', // 5
        'w', 'x', 'y', 'z', '0', '1', '2', '3', // 6
        '4', '5', '6', '7', '8', '9' // 7
    };

    /**
     * 限定范围内的随机数字。<br />
     * 注意：当max小于min时，max与min将换位。
     * @param min   最小数值
     * @param max   最大数值
     * @return  在min与max之间的随机数字
     */
    public static int toInt(int min, int max) {
        int n = max - min;
        if (n == 0) {
            return min;
        }
        if (n < 0) {
            min = max;
            n = -n;
        }
        return min + new Random().nextInt(n);
    }

    /**
     * 不定长度的随机字符串
     * @param minLength     字符串最小长度
     * @param maxLength     字符串最大长度
     * @return  一定长度范围内的随机字符串
     */
    public static String toString(int minLength, int maxLength) {
        StringBuilder strBuilder = new StringBuilder();
        int length = 0;
        if (minLength != maxLength) {
            length = toInt(minLength, maxLength);
        } else {
            length = minLength;
        }
        for (int i = 0; i < length; i++) {
            strBuilder.append(alphabet[toInt(0, 62)]);
        }
        return strBuilder.toString();
    }

    /**
     * 固定长度的随机字符串
     * @param length    字符串长度
     * @return      长度固定的随机字符串
     */
    public static String toString(int length) {

        return toString(length, length);
    }

    /**
     * 将当前十进制时间（精确到毫秒）转换为其他进制
     * @param radix  进制 2 和 62 之间（包括 2 和 62）。
     * @return  指定进制表示的当前时间
     */
    public static String toTimeString(int radix) {
        return NumberHelper.dec2Any(System.currentTimeMillis(), radix);
    }
}
