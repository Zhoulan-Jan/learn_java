
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临SUSHI餐厅</span>
    <a href="pages/order/order.jsp">我的订单</a>
    <a href="user?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>