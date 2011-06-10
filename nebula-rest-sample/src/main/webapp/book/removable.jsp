<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Book Update</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form action="<%=request.getContextPath()%>/simple/book/${result.id}/" method="post">
    <input type="hidden" name="__http_method" value="delete"/> 
    <input type="hidden" name="id" value="${result.id}">
    <table border=1>
        <tr>
            <td><label>type</label></td>
            <td>${result.type}</td>
        </tr>
        <tr>
            <td><label>name</label></td>
            <td>${result.name}</td>
        </tr>
        <tr>
            <td><label>description</label></td>
            <td>${result.description}</td>
        </tr>
        <tr>
            <td><label>publisher</label></td>
            <td>${result.publisher}</td>
        </tr>
        <tr>
            <td><label>count</label></td>
            <td>${result.count}</td>
        </tr>
        <tr>
            <td><label>price</label></td>
            <td>${result.price}</td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Delete"/></td>
        </tr>
    </table>
</form>
</body>
</html>