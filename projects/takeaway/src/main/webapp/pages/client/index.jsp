<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>餐厅首页</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>
	<Script type="text/javascript">
		$(function () {
			// 给加入购物车按钮绑定单击事件
			$("button.addToCart").click(function () {
				/**
				 * 在事件响应的function函数 中，有一个this对象，这个this对象，是当前正在响应事件的dom对象
				 * @type {jQuery}
				 */
				var sushiId = $(this).attr("sushiId");
				 location.href = "http://localhost:8080/takeaway/cart?action=addItem&id=" + sushiId;
			});
		});
	</Script>

</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.jpg" >
			<span class="wel_word">网上餐厅</span>
			<div>
				<%--如果用户还没有登录，显示     【登录 和注册的菜单】 --%>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/register.jsp">注册</a> &nbsp;&nbsp;
				</c:if>
				<%--如果已经登录，则显示 登录 成功之后的用户信息。--%>
				<c:if test="${not empty sessionScope.user}">
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临SUSHI餐厅</span>

					<a href="order?action=showUserOrders">我的订单</a>
					<a href="user?action=logout">注销</a>&nbsp;&nbsp;&nbsp;
				</c:if>

				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>

	<div id="main">
		<div id="sushi">

			<div class="sushi_cond">
				<form action="client/sushi" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>

			<div style="text-align: center">
				<c:if test="${empty sessionScope.cart.items}">
					<%--购物车为空的输出--%>
					<span id="cartTotalCount"> </span>
					<div>
						<span style="color: red">当前购物车为空</span>
					</div>
				</c:if>
				<c:if test="${not empty sessionScope.cart.items}">
					<%--购物车非空的输出--%>
					<span id="cartTotalCount">您的购物车中有 ${sessionScope.cart.totalCount} 件商品</span>
					<div>
						您刚刚将<span style="color: red">${sessionScope.lastName}</span>加入到了购物车中
					</div>
				</c:if>
			</div>

			<c:forEach items="${requestScope.page.items}" var="sushi">
			<div class="b_list">
				<div class="img_div">
					<img class="sushi_img" alt="" src="${sushi.imgPath}" />
				</div>
				<div class="sushi_info">
					<div class="sushi_name">
						<span class="sp1">名:</span>
						<span class="sp2">${sushi.name}</span>
					</div>
					<div class="sushi_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${sushi.price}</span>
					</div>
					<div class="sushi_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${sushi.sales}</span>
					</div>
					<div class="sushi_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${sushi.stock}</span>
					</div>
					<div class="sushi_add">
						<button sushiId="${sushi.id}" class="addToCart">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>

		<%--静态包含分页条--%>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>