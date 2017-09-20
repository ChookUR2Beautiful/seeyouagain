<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Joney
  Date: 2017/8/15
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style type="text/css">
        table {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #999999;
            border-collapse: collapse;
        }
        table th {
            background-color:#c3dde0;
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
        table tr {
            background-color:#d4e3e5;
        }
        table td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
    </style>
</head>
<body>
<table style="display: inline">
    <caption><h3>已匹配用户</h3></caption>
    <thead>
        <tr><th>用户编号</th><th>用户帐号</th><th>昵称</th><th>V客等级</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${matchedUsers}" var="user">
            <tr><td>${user.uid}</td><td>${user.uname}</td><td>${user.nname}</td><td>${user.rankName}</td></tr>
        </c:forEach>
    </tbody>
</table>


<table style="display: inline">
    <caption><h3>匹配失败!</h3></caption>
    <thead><tr><th>号码</th><th>原因</th></tr></thead>
    <tbody>
    <c:forEach items="${unmatchedUsers}" var="user">
        <tr><td>${user}</td><td>未注册</td></tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
