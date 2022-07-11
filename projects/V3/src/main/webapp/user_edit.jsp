<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>修改用户</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <%@ include file="/pages/common/head.jsp"%>
</head>

<body>
<h2>修改用户</h2>
<hr/>
<form action="../../tuser" method="get">
    <input type="hidden" name="action" value="${empty param.userid? "add" : "update"}">
    <input type="hidden" name="userid" value="${ user.userid }"/>

    <table id="edit" border="1">
        <tr>
            <td width="30%">姓名</td>
            <td><input type="text" name="username" value="${user.username}"/></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="text" name="password" value="${user.password}"/></td>
        </tr>
        <tr>
            <td>性别</td>
            <td><input type="text" name="gender" value="${user.gender}"/></td>
        </tr>
        <tr>
            <td>职位</td>
            <td><input type="text" name="position" value="${user.position}"/></td>
        </tr>
        <tr>
            <td>手机</td>
            <td><input type="text" name="tele" value="${user.tele}"/></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input type="text" name="email" value="${user.email}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提 	交"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

