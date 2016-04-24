package com.baicai.core;

import com.baicai.domain.system.Pagination;
import com.baicai.util.PropertiesTool;

/**
 * DAO辅助类
 * @author 95
 *
 */
public class DaoUtil {
	public static final String tableFix=PropertiesTool.get("system", "tableFix");
	public static String format(String sql) {
		String result = sql.replace("{",  tableFix);
		result = result.replace("}", " ");
		return result;
	}
	
	public static String  limit(Pagination page){
		return " limit "+page.getOffset()+","+page.getPageSize()+" ";
	}
}
