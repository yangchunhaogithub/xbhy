<%@ page import="java.io.Console" %>
<%@ page import="org.apache.tomcat.jni.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>
<div id="right">

    <a href="/user/add">添加</a><br>
    <form action="/user/list" method="post">
        根据用户名模糊查询:<input type="text" name="username" value="${username}">
        <select name="gender">
            <option value="1">男</option>
            <option value="0">女</option>
            <option value="-1">请选择</option>
        </select>
        <button type="submit">查询</button>
    </form>


    <table border="1">
        <tr>
            <td>用户名</td>
            <td>部门</td>
            <td>邮箱</td>
            <td>真实姓名</td>
            <td>年龄</td>
            <td>手机</td>
            <td>性别</td>
            <td>描述</td>
            <td>注册时间</td>
            <td>操作</td>
        </tr>

            <tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.deptName}</td>
                    <td>${user.email}</td>
                    <td>${user.realName}</td>
                    <td>${user.age}</td>
                    <td>${user.phone}</td>
                    <c:choose>
                        <c:when test="${user.gender==1}">
                            <td>男</td>
                        </c:when>
                        <c:when test="${user.gender==0}">
                            <td>女</td>
                        </c:when>
                        <c:otherwise>
                            <td>其他</td>
                        </c:otherwise>
                    </c:choose>

                    <td>${user.description}</td>
                    <td>${user.registerTime}</td>
                    <td><a href="/user/add?uid=${user.id}">修改</a>&nbsp&nbsp<a href="/user/delete?uid=${user.id}">删除</a></td>
                </tr>
            </c:forEach>

    </table>

    当前页：${page.pageCurrent}
    总页数：${page.pageCount}
    总记录数：${page.count}
    &nbsp;&nbsp;
    <a href="/user/list?page=${page.pageCurrent-1>0?page.pageCurrent-1:1}&username=${username}">上一页</a>
    <a href="/user/list?page=${page.pageCurrent+1>=page.pageCount?page.pageCount:page.pageCurrent+1}&username=${username}">下一页</a>

    <form action="/user/list?page=1&username=${username}">
        设置页面大小：<input style="width: 50px" type="number" name="pageSize" min="5" value="${page.size}">
        <button type="submit">确定</button>
    </form>
</div>
</body>
</html>
