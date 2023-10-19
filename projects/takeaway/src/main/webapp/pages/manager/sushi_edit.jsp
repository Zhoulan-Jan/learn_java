<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑寿司</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="takeaway/static/img/logo.jpg" >
			<span class="wel_word">编辑寿司</span>

			<%-- 静态包含 manager管理模块的菜单  --%>
			<%@include file="/pages/common/manager_menu.jsp"%>


		</div>


		<div id="main">
			<form action="manager/sushi" method="get">
				<%--既增加也实现修改--%>
<%--					方案一：请求发起时，附带当前要操作的值 --%>
<%--					方案二：请求参数有id参数即为修改操作--%>
				<input type="hidden" name="action" value="${empty param.id? "add":"update"}"/>
					<input type="hidden" name="pageNo" value="${param.pageNo}">
					<input type="hidden" name="id" value="${requestScope.sushi.id}"/>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.sushi.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.sushi.price}"/></td>
						<td><input name="sales" type="text" value="${requestScope.sushi.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.sushi.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>


		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/footer.jsp"%>


</body>
</html>