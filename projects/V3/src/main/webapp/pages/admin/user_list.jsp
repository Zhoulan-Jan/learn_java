<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员页面</title>

    <%@ include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function () {
            //给【删除】的a标签绑定单击事件 ，用于删除的确认提示操作
            $("a.deleteClass").click(function () {
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
            });
        });
    </script>

</head>

<body>

<div class="div_5Zq4A8">
    <!--    <img class="c-image image_EGlaC6 c-action-click" src="http://qty83k.creatby.com/materials/origin/back.png">-->
    <img class="c-image image_w1h7fG" src="static/img/h.png">
    <h2 class="heading_2xCzX3">Index</h2>
    <ul class="list_lULt8t">
        <li class="listitem_6kWaEd">
            <p class="paragraph_xTNAT7">人员信息管理</p>
        </li>
        <li class="listitem_y3BOqp">
            <a class="paragraph_xTNAT7a" href="tuser/userList" >人员信息查询</a>
        </li>
        <li class="listitem_nF59K6">
            <a class="paragraph_xTNAT7a" href="pages/admin/user_add.jsp" >人员信息添加</a>
        </li>
    </ul>
</div>


<div class="section_JtmC1r">
    <h2>用户信息列表</h2>
    <form action="tuser/select_by_username" method="get">
        <table width="500px">
            <tr>
                <td>姓名：</td>
                <td><input type="text"  name="username" id="username"></td>
                <td><input type="submit" value="搜索"></td>
            </tr>
        </table>
    </form>

    <hr width="70%">

    <div width="70%" border="1" cellspacing="0">
        <table width="70%">
            <tr bgcolor="#f0c2a2">
                <td>工号</td>
                <td>姓名</td>
                <td>密码</td>
                <td>性别</td>
                <td>职位</td>
                <td>手机</td>
                <td>邮箱</td>
                <td colspan="2">操作</td>
            </tr>

            <c:forEach items="${key}" var="list">
                <tr>
                    <td>${list.userid}</td>
                    <td>${list.username}</td>
                    <td>****</td>
                    <td>${list.gender}</td>
                    <td>${list.position}</td>
                    <td>${list.tele}</td>
                    <td>${list.email}</td>
                    <td><a href="tuser/userInfo?id=${list.userid}">修改</a></td>
                    <td><a class="deleteClass" href="tuser/delete?id=${list.userid}">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>

</body>
</html>
