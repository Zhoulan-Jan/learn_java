<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考勤列表页面</title>

    <%@ include file="/pages/common/head.jsp"%>
</head>

<body>

<div class="div_5Zq4A8">
    <!--    <img class="c-image image_EGlaC6 c-action-click" src="http://qty83k.creatby.com/materials/origin/back.png">-->
    <img class="c-image image_w1h7fG" src="static/img/h.png">
    <h2 class="heading_2xCzX3">Index</h2>
    <ul class="list_lULt8t">
        <li class="listitem_6kWaEd">
            <p class="paragraph_xTNAT7">考勤管理</p>
        </li>
        <li class="listitem_y3BOqp">
            <a class="paragraph_xTNAT7a" href="manager/clockList" >考勤信息查询</a>
        </li>
        <li class="listitem_6kWaEd">
            <p class="paragraph_xTNAT7">请假管理</p>
        </li>
        <li class="listitem_nF59K6">
            <a class="paragraph_xTNAT7a" href="manager/leaveList" >请假信息查询</a>
        </li>
    </ul>
</div>

<div class="section_JtmC1r">
    <h2>考勤信息列表</h2>
    <form action="manager/selectClockByUsername" method="get">
        <table width="500px">
            <tr>
                <td>姓名：</td>
                <td><input type="text"  name="username" id="user"></td>
                <td><input type="submit" value="搜索"></td>
            </tr>
        </table>
    </form>
    <hr width="70%">
    <div width="70%" border="1" cellspacing="0">
        <table width="70%">
            <tr>
                <td>序号</td>
                <td>工号</td>
                <td>姓名</td>
                <td>日期</td>
                <td>上班时间</td>
                <td>上班状态</td>
                <td>下班时间</td>
                <td>下班状态</td>
            </tr>

            <c:forEach items="${list}" var="list">
                <tr>
                    <td>${list.clockid}</td>
                    <td>${list.userid}</td>
                    <td>${list.username}</td>
                    <td>${list.time}</td>
                    <td>${list.startTime}</td>
                    <td>${list.startStatus}</td>
                    <td>${list.endTime}</td>
                    <td>${list.endStatus}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>