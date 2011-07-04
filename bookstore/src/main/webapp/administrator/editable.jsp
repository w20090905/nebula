<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>administrator login</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form action="<%=request.getContextPath()%>/brm/administrator/${result.id}/" method="post">
     <input type="hidden" name="__http_method" value="put"/> 
    <input type="hidden" name="result.id" value="${result.id}">
    <table border=1>
        <tr>
            <td><label for="name">groupId</label></td>
            <td><input type="text" name="result.groupId" id="groupId" value="${result.groupId}"/>
            </td>
        </tr>
         <tr>
            <td><label for="name">name</label></td>
            <td><input type="text" name="result.name" id="name" value="${result.name}"/></td>
        </tr>
        <tr>
            <td><label for="type">password</label></td>
            <td><input type="text" name="result.password" id="password" value="${result.password}"/></td>
        </tr>
        <tr>
            <td><label for="type">role</label></td>
            <td><input type="text" name="result.role" id="password" value="${result.role}"/></td>
        </tr>
        <tr>
            <td><label for="type">memo</label></td>
            <td><input type="text" name="result.memo" id="memo" value="${result.memo}"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Update"/></td>
            <td><input type="reset" value="Reset"/></td>
        </tr>
    </table>
</form>
</body>
</html>