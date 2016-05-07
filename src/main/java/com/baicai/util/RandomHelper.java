package com.baicai.util;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
/**
 * 
* @Description: 随机数生成器，JVM启动时加参数-Djava.security.egd=file:/dev/./urandom ，可以提高性能防止阻塞
* 安全性比较高的场合，你需要使用SecureRandom
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年5月7日 下午2:50:02 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public class RandomHelper {
    private RandomHelper() {
    }
    
    private static Random random = new Random();

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
        return min + random.nextInt(n);
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
    
    /**
     * 生成一个更安全的随机数，当然也更慢。构造一个SecureRandom对象需要388ns
     * @return
     */
    public int getSecInt(){
    	return new SecureRandom().nextInt();
    }
    
    /**
     * 更快的UUID算法，但是随机性有所降低
     * @return
     */
    public static UUID getUUID(){
    	return new UUID(ThreadLocalRandom.current().nextLong(), ThreadLocalRandom.current().nextLong());
    }
}
