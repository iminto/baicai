<%@ tag language="java" body-content="scriptless" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="page" type="com.baicai.domain.system.Pagination"
	required="true" description="分页实体类"%>
<%
	//分页组件 
	int range = 8;//显示多少个连接，超过这个值要处理
	int begin = 1;
	int end = 0;
	int pageNumber = 10;//每页条数
	Integer totalPages = 1;
	if (page == null) { //处理页面可能误用导致的空指针
		pageNumber = 10;
		totalPages = 1;
	} else {
		pageNumber = this.page.getPageSize();//每页条数
		totalPages = this.page.getPageTotal();//总页数
	}
	if (range >= totalPages) {
		begin = 1;
		end = totalPages;
	} else {
		begin = page.getPage() - 4 < 0 ? 1 : (page.getPage() - 4);
		end = page.getPage() + 4 > totalPages ? totalPages : (page
				.getPage() + 4);
	}

	request.setAttribute("begin", begin);
	request.setAttribute("end", end);
%>
<form id="pageForm" class="form-inline" method="get">
	<input id="page" type="hidden" name="page" />
	<c:forEach items="${page.pageParaMap}" var="entry">
		<input type="hidden" name="${entry.key}" value="${entry.value}">
	</c:forEach>
	<ul class="pagination">
		<li><a href="#" onclick="goPage(0);">首页</a></li>
		<c:forEach begin="${begin}" end="${end}" var="i">
			<c:if test="${page.page == i}">
				<li class="active"><a href="#" onclick="goPage(${i});"><span>${i}</span></a></li>
			</c:if>
			<c:if test="${page.page != i}">
				<li><a href="#" onclick="goPage(${i});">${i}</a></li>
			</c:if>
		</c:forEach>
		<li><a href="#" onclick="goPage(${page.pageTotal});">尾页</a></li>
		<li><input type="text" id="tpage"
			onkeydown="if (event.keyCode==13){turnPage();}"></li>
		<li><span>共${page.pageTotal}页，每页${page.pageSize}条，总共${page.total}条</span></li>
	</ul>
</form>

<script type="text/javascript">
    var goPage = function(page) {
        $("#page").val(page);
        $("#pageForm").submit();
    };
    var turnPage = function() {
        $("#page").val($("#tpage").val());
        $("#pageForm").submit();
    }
</script>