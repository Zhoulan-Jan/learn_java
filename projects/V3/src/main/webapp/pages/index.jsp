<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>考勤列表页面</title>

    <%@ include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function () {
            $("#sub_btn").click(function () {
                var useridTxt = $("#userid").val();
                var useridPatt = /^[0-9]*$/;

                if (!useridPatt.text(useridTxt)) {
                    $("span.errorMsg").text("工号不合法！");

                    return false;
                } else {
                    return true;
                }

                var passwordTxt = $("#password").val();
                var passwordPatt = /[a-zA-Z]/;
                if (!passwordPatt.text(passwordTxt)) {
                    $("span.errorMsg").text("密码不合法！");

                    return false;
                }

            });
        });
    </script>
</head>
<body class="body_D8NeG3">
<%--<h2>Hello World!</h2>--%>
<%--<a href="tuser/userList">进入管理员页面</a>--%>
<%--<br>--%>
<%--<a href="manager/leaveList">进入请假页面</a>--%>
<%--<br>--%>
<%--<a href="manager/clockList">进入考勤页面</a>--%>
<%--<input type="button" name="人事">--%>
<%--<button formaction="manager/leaveList" value="按钮" name="人事">点击</button>--%>
<div class="section_Wl9Fwc">
    <!--
                <div class="div_QzQBWm c-div">
                </div>
    -->
    <div class="container_1oVg7Q signup container c-container">
        <div class="row_Kjbz8z row c-row">
            <div class="column_mvZe5k col-lg-6 col-md-6 col-sm-6 col-xs-12 c-column">

            </div>
            <div class="column_EXJ39w signup col-lg-6 col-md-6 col-sm-6 col-xs-12 c-column">
                <p class="paragraph_ziD2v8">
                    登录 LOGIN：
                </p>

                <div class="msg">
                    <b></b>
                    <span class="errorMsg">
                        ${ empty requestScope.msg ? "请输入工号和密码":requestScope.msg }
                    </span>
                </div>

                <form class="form_mgltEd c-form" action="tuser/loginWeb" method="post" name="form">
<%--                    <input type="hidden" name="csrfmiddlewaretoken" value="">--%>

                    <label class="label_hq1X7i c-label">工号:</label>
                    <input class="input_ju04Ga signup c-input" name="useridStr" id="userid" placeholder="请输入工号" required="True" type="text" autocomplete="off" tabindex="1">

                    <label class="label_3UG6Ic c-label">密码:</label>
                    <input class="input_8FlkY6 signup c-input" name="password" id="password" placeholder="请输入密码" required="True" type="password">

<%--                    <button class="submit_n7xCQ7" type="submit" id="btn">登录</button>--%>
                    <input type="submit" value="登录" id="sub_btn" />
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
