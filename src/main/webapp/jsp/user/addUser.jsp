<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主界面</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>

<div id="right">
    <c:if test="${userInfo!=null}">
        <form action="/user/addUser" method="post">
            <label>用户名&nbsp:</label><input id="name2" name="username" onblur="userExit2()"
                                           value="${userInfo.username}"/><br>
            <label>部门&nbsp&nbsp:</label>
            <select name="deptId">
                <c:forEach items="${DeptNam}" var="dept">
                    <option value="${dept.id}" <c:if test="${userInfo.deptId==dept.id}"> selected</c:if>>${dept.name}</option>
                </c:forEach>
                <option value="" <c:if test="${userInfo.deptId==null}"> selected</c:if>>请选择</option>
            </select><br>
            <label>邮箱&nbsp&nbsp:</label><input name="email" value="${userInfo.email}"/><br>
            <label>真实姓名:</label><input name="realName" value="${userInfo.realName}"/><br>
            <label>年龄&nbsp&nbsp:</label><input name="age" value="${userInfo.age}"/><br>
            <label>手机&nbsp&nbsp:</label><input name="phone" value="${userInfo.phone}"/><br>
            <label>性别&nbsp&nbsp:</label> 男<input name="gender" value="1" type="radio" <c:if
                    test="${userInfo.gender==1}"> checked</c:if>> 女<input name="gender" value="0" type="radio" <c:if
                    test="${userInfo.gender==0}"> checked</c:if>><br>
            <label>描述&nbsp&nbsp:</label><input name="desc" value="${userInfo.description}"/><br>
            <input type="hidden" id="uid" name="uid" value="${userInfo.id}">
            <input value="提交" type="submit">
        </form>
    </c:if>
    <c:if test="${userInfo==null}">
        <form action="/user/addUser" method="post">
            <label>用户名&nbsp:</label><input id="name1" name="username" onblur="userExit1()"/><br>
            <label>部门&nbsp&nbsp:</label>
            <select name="deptId">
                <c:forEach items="${DeptNam}" var="dept">
                    <option value="${dept.id}">${dept.name}</option>
                </c:forEach>
                <option value="">请选择</option>
            </select><br>
            <label>邮箱&nbsp&nbsp:</label><input name="email"/><br>
            <label>真实姓名:</label><input name="realName"/><br>
            <label>年龄&nbsp&nbsp:</label><input name="age"/><br>
            <label>手机&nbsp&nbsp:</label><input name="phone"/><br>
            <label>性别&nbsp&nbsp:</label> 男<input name="gender" value="1" type="radio" checked> 女<input name="gender"
                                                                                                       value="0"
                                                                                                       type="radio"><br>
            <label>描述&nbsp&nbsp:</label><input name="desc"/><br>
            <%
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String date = format.format(new Date());
            %>
            <input type="hidden" name="registerTime" value="<%=date%>"/><br>
            <input value="提交" type="submit">
        </form>
    </c:if>

</div>

<script>
    <%--判断用户是否存在--%>

    function userExit1() {
        var name = $('#name1').val();
        // alert(name)
        $.ajax({
            url: "/user/exitUser",
            type: "get",
            data: {
                "name": name
            },
            dataType: "text",
            success: function (data) {
                if (data == "isExit") {
                    alert("新增用户名已存在")
                    $('#name1').val("")
                }
            }
        })
    }

    function userExit2() {
        var name = $('#name2').val();
        var uid = $('#uid').val();
        // alert(uid)
        $.ajax({
            url: "/user/exitUser",
            type: "get",
            data: {
                "name": name,
                "uid": uid
            },
            dataType: "json",
            success: function (data) {
                if (data.flag == "isExit") {
                    alert("修改用户名已存在")
                    // alert(data.name)
                    $('#name2').val(data.name)
                }
            }
        })
    }

</script>
</body>
</html>
