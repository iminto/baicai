/**
 * @Description: 测试类
 * @author 猪肉有毒 waitfox@qq.com  
 * @date 2015年6月10日 上午11:46:46 
 * @version V1.0  
 * 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
package com.baicai.util;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import com.baicai.core.BaseTool;
import com.baicai.util.help.GenModel;

public class UtilTest {
	
	@Test
	public  void testCookieEncype(){
		String uid="5201314";
		String cookie=BaseTool.encryptCookieValue(uid);
		String cookiede=BaseTool.decryptCookie(cookie);
		assertEquals(uid,cookiede);
	}
	
	@Test
	public void genTable() throws IOException{
		GenModel.generetor("p2p_user");
	}
}
