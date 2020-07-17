<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>寿司管理</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
		//给【删除】的a标签绑定单击事件 ，用于删除的确认提示操作
			$("a.deleteClass").click(function () {
				// 在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的dom对象 。
			/**
			 * confirm是确认提示框函数
			 * 参数是它的提示内容它有两个按钮 ，一个确认，一个是取消 。
			 * 返回 true表示点击了确认
			 * 返回false表示点击取消
			 */
			return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
			});
		});
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.jpg" >
			<span class="wel_word">寿司管理系统</span>

		<%-- 静态包含 manager管理模块的菜单  --%>
		<%@include file="/pages/common/manager_menu.jsp"%>


	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.sushis}" var="sushi">
				<tr>
					<td>${sushi.name}</td>
					<td>${sushi.price}</td>
					<td>${sushi.sales}</td>
					<td>${sushi.stock}</td>
					<td><a href="manager/sushi?action=getSushi&id=${sushi.id}">修改</a></td>
					<td><a class="deleteClass" href="manager/sushi?action=delete&id=${sushi.id}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/sushi_edit.jsp">添加寿司</a></td>
			</tr>	
		</table>
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>