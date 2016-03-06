package com.baicai.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;
/**
 * 验证码生产类
 * @author waitfox@qq.com
 *
 */
public class CaptchaRender {
	
	public static Color getRandColor(int fc, int bc) {//给定范围获得随机颜色
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + ThreadLocalRandom.current().nextInt(bc - fc);
		int g = fc + ThreadLocalRandom.current().nextInt(bc - fc);
		int b = fc + ThreadLocalRandom.current().nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	public static String drawGraphic(BufferedImage image){
		int width = 60, height = 20;
		// 获取图形上下文
	 	Graphics g = image.getGraphics();
	 	// 设定背景色
	 	g.setColor(getRandColor(210, 250));
	 	g.fillRect(0, 0, width, height);
	 	//设定字体
	 	g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	 	// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
	 	g.setColor(getRandColor(160, 200));
	 	for (int i = 0; i < 155; i++) {
	 		int x = ThreadLocalRandom.current().nextInt(width);
	 		int y = ThreadLocalRandom.current().nextInt(height);
	 		int xl = ThreadLocalRandom.current().nextInt(12);
	 		int yl = ThreadLocalRandom.current().nextInt(12);
	 		g.drawLine(x, y, x + xl, y + yl);
	 	}
	 	char[] chars = { '2', '3', '4', '5', '6','7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' ,'J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' ,'j','k','m','n','p','q','r','s','t','v','w','x','y','z'};
	 	// 取随机产生的认证码(4位数字)
	 	String sRand = "";
	 	for (int i = 0; i < 4; i++) {
	 		int r = ThreadLocalRandom.current().nextInt(chars.length);
	 		String rand = String.valueOf(chars[r]);
	 		sRand += rand;
	 		// 将认证码显示到图象中
	 		g.setColor(new Color(20 + ThreadLocalRandom.current().nextInt(100), 20 + ThreadLocalRandom.current().nextInt(110), 20 + ThreadLocalRandom.current().nextInt(120)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
	 		g.drawString(rand, 13 * i + 6, 16);
	 	}
	 	// 图象生效
	 	g.dispose();
	 	return sRand;
	}

	 
}
