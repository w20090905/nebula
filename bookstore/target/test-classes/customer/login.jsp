<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>customer login</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form action="<%=request.getContextPath()%>/brm/customer!login/" method="post">
    <input type="hidden" name="customer.id" value="${customer.id}">
    <table border=1>
        <tr>
            <td><label for="name">name</label></td>
            <td><input type="text" name="customer.name" id="name" value="${customer.userName}"/></td>
        </tr>
        <tr>
            <td><label for="password">password</label></td>
            <td><input type="text" name="customer.password" id="password" value="${customer.userPassword}"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Update"/></td>
            <td><input type="reset" value="Reset"/></td>
        </tr>
    </table>
</form>
</body>
</html>