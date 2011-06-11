<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>administrator Update</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form action="<%=request.getContextPath()%>/brm/administrator/login/" method="post">
    <input type="hidden" name="__http_method" value="put"/> 
    <input type="hidden" name="administrator.id" value="${administrator.id}">
    <table border=1>
        <tr>
            <td><label for="type">groupId</label></td>
            <td><input type="text" name="administrator.groupId" id="groupId" value="${administrator.groupId}"/></td>
        </tr>
        <tr>
            <td><label for="name">name</label></td>
            <td><input type="text" name="administrator.name" id="name" value="${administrator.name}"/></td>
        </tr>
        <tr>
            <td><label for="password">password</label></td>
            <td><input type="text" name="administrator.password" id="password" value="${administrator.password}"/></td>
        </tr>
        <tr>
            <td><label for="role">role</label></td>
            <td><input type="text" name="administrator.role" id="role" value="${administrator.role}"/></td>
        </tr>
        <tr>
            <td><label for="memo">memo</label></td>
            <td><input type="text" name="administrator.memo" id="memo" value="${administrator.memo}"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Update"/></td>
            <td><input type="reset" value="Reset"/></td>
        </tr>
    </table>
</form>
</body>
</html>