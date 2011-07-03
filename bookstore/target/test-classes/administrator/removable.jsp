<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Book removable</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form action="<%=request.getContextPath()%>/brm/administrator/${result.id}/" method="post">
    <input type="hidden" name="__http_method" value="delete"/> 
    <input type="hidden" name="id" value="${result.id}">
    <table border=1>
        <tr>
            <td><label>name</label></td>
            <td>${result.name}</td>
        </tr>
        <tr>
            <td><label>password</label></td>
            <td>${result.password}</td>
        </tr>
        <tr>
            <td><label>groupId</label></td>
            <td>${result.groupId}</td>
        </tr>
        <tr>
            <td><label>role</label></td>
            <td>${result.role}</td>
        </tr>
        <tr>
            <td><label>memo</label></td>
            <td>${result.memo}</td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Delete"/></td>
        </tr>
    </table>
</form>
</body>
</html>