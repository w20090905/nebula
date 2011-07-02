<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>administrator login</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form action="<%=request.getContextPath()%>/brm/administrator/${administrator.id}/" method="post">
    <table border=1>
        <tr>
            <td><label for="name">name</label></td>
            <td><input type="text" name="administrator.name" id="name" value="${administrator.name}"/></td>
        </tr>
        <tr>
            <td><label for="type">password</label></td>
            <td><input type="password" name="administrator.password" id="password" value="${administrator.password}"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Update"/></td>
            <td><input type="reset" value="Reset"/></td>
        </tr>
    </table>
</form>
</body>
</html>