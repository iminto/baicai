/**
 * @Description: 分页类
 * @author 猪肉有毒 waitfox@qq.com  
 * @date 2015年6月1日 上午9:13:32 
 * @version V1.0  
 * @modify 2015-06-10 重构类，使用容器来存放数据
 * 					   解决getPageTotal可能导致的空指针问题
 * 					   去掉泛型，避免在tomcat 7及以下版本可能产生的类型转换支持不佳bug
 * 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
package com.baicai.domain.system;

import java.util.List;
import java.util.Map;

public class Pagination {
	public Integer total=0;// 总条数
	public Integer pageSize = 10;// 每页条数
	public Integer page = 1;// 当前页数
	private Map pageParaMap;// 页面参数
	public Integer pageTotal=0;// 总页数
	public Integer offset;
	
	private List list;//容器

	public Pagination() {
	}

	public Pagination(int total) {
		this.total = total;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page>0?page:1;
	}

	public void setPage(Integer page) {
		if(page==null) page=1;
		this.page = page;
	}

	public Map getPageParaMap() {
		return pageParaMap;
	}

	public void setPageParaMap(Map pageParaMap) {
		this.pageParaMap = pageParaMap;
	}

	public Integer getPageTotal() {
		if(total==0) return 1;
		Integer totalPage = (total % pageSize == 0) ? (total / pageSize ): (total
				/ pageSize + 1);
		return (totalPage==null || totalPage == 0) ? 1 : totalPage;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public static Integer pageInteger(String page) {
		Integer p = 1;
		if (page != null && !page.equals("")) {
			p = Integer.valueOf(page);
		}
		return p;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getOffset() {
		return (this.getPage()-1)*this.getPageSize();
	}
	
	/**
	 * TODO：生成连接字符串，可用于GET方式的分页，待补充
	 * @return
	 */
	public String generateLink(){
		return "";
	}

}
