<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请假列表页面</title>

    <%@ include file="/pages/common/head.jsp"%>
</head>

<body>

<div class="div_5Zq4A8">
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
    <h2>请假信息列表</h2>

    <form action="manager/selectByUsername" method="get">
        <table width="500px">
            <tr>
                <td>姓名：</td>
                <td><input type="text"  name="username" id="username"></td>
                <td><input type="submit" value="搜索"></td>
            </tr>
        </table>
    </form>
    <form action="manager/selectNoApp" method="get">
        <table width="500px">
            <tr>
                <td></td>
                <td></td>
                <td><input type="submit" value="查看未审批"></td>
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
                <td>请假类型</td>
                <td>具体原因</td>
                <td>开始日期</td>
                <td>结束日期</td>
                <td>是否审批</td>
                <td>审批结果</td>
                <td colspan="2">操作</td>
            </tr>

            <c:forEach items="${list}" var="list">
                <tr>
                    <td>${list.leaveid}</td>
                    <td>${list.userid}</td>
                    <td>${list.username}</td>
                    <td>${list.leaveType}</td>
                    <td>${list.leaveContent}</td>
                    <td>${list.startTime}</td>
                    <td>${list.endTime}</td>
                    <td>${list.approvalStatus}</td>
                    <td>${list.approvalResult}</td>
                    <td><a a:visited="black" href="manager/updateAgree?id=${list.leaveid}">同意</a></td>
                    <td><a a:visited="black" href="manager/updateRefuse?id=${list.leaveid}">拒绝</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
