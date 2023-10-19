<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%--   静态包含头 base标签，css样式，jqery文件--%>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.jpg" >
    <span class="wel_word">订单管理系统</span>
    <%--静态包含manager管理模块的菜单--%>
    <%@ include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>编号</td>
            <td>名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>总价格</td>
            <td>订单号</td>
        </tr>

        <%--用户通过非正常的访问方式进来的--%>
        <c:if test="${ empty requestScope.orderAdminDetails}">
            <tr>
                <td colspan="5"><a href="index.jsp">亲，请通过正常的方式查看订单详细信息！点我回到首页</a></td>
            </tr>
        </c:if>

        <%--正常访问方式，request域中存有订单细项信息--%>
        <c:if test="${ not empty requestScope.orderAdminDetails}">
            <%--循环取出request域中存有订单细项信息--%>
            <c:forEach items="${requestScope.orderAdminDetails}" var="orderAdminDetails">
                <tr>
                    <td>${orderAdminDetails.id}</td>
                    <td>${orderAdminDetails.name}</td>
                    <td>${orderAdminDetails.count}</td>
                    <td>${orderAdminDetails.price}</td>
                    <td>${orderAdminDetails.totalPrice}</td>
                    <td>${orderAdminDetails.orderId}</td>
                </tr>
            </c:forEach>
        </c:if>

    </table>
</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
