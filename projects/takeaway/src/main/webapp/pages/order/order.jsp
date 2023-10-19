<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>我的订单</title>
	<%--   静态包含头 base标签，css样式，jqery文件--%>
	<%@include file="/pages/common/head.jsp" %>
	<style type="text/css">
		h1 {
			text-align: center;
			margin-top: 200px;
		}
	</style>

	<script type="text/javascript">
		$(function () {
			//name为ReceivingGoods绑定单击事件。
			$("a.ReceivingGoods").click(function () {
				return confirm("您确定将这笔订单日期为：【" + $(this).parent().parent().find("td:first").text() + "】的订单收货吗？");
			});

		});
	</script>


</head>
<body>

<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.jpg">
	<span class="wel_word">我的订单</span>
	<div>
		<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
		<a href="orderServlet?action=showAllOrders">注销</a>&nbsp;&nbsp;
		<a href="index.jsp">返回</a>
	</div>
</div>

<div id="main">

	<table>
		<tr>
			<td>日期</td>
			<td>金额</td>
			<td>状态</td>
			<td>详情</td>
			<td>确认收货</td>
		</tr>

		<%--如果订单为空，则提示用户到首页进行选购--%>
		<c:if test="${empty requestScope.user_orders}">
			<tr>
				<td colspan="5"><a href="index.jsp">亲，没得东西撒，去首页看看喽！</a></td>
			</tr>
		</c:if>

		<c:if test="${not empty requestScope.user_orders}">
			<c:forEach items="${requestScope.user_orders}" var="user_orders">
				<tr>
						<%--格式化输出日期--%>
					<td><fmt:formatDate value="${user_orders.createTime}" pattern="yyyy.MM.dd"/></td>
					<td>${user_orders.price}</td>
					<td>
						<c:if test="${user_orders.status eq 0}">未发货</c:if>
						<c:if test="${user_orders.status eq 1}">已发货</c:if>
						<c:if test="${user_orders.status eq 2}">已签收</c:if>
					</td>
					<td><a href="order?action=showOrderDetail&orderId=${user_orders.orderId}">查看详情</a></td>
					<td>

							<%--当订单状态为2时，才可以点击确认收货--%>
						<c:if test="${user_orders.status eq 1}">
							<a class="ReceivingGoods"
							   href="order?action=receiverOrder&orderId=${user_orders.orderId}">确认收货</a>
						</c:if>
							<%--否则无法点击，显示对应状态信息。--%>
						<c:if test="${user_orders.status eq 0}"> <div>待发货<div/> </c:if>
						<c:if test="${user_orders.status eq 2}"> <div>已确认收货<div/> </c:if>


					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>

</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>