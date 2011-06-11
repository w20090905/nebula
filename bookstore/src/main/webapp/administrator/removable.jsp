<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Book Update</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form action="<%=request.getContextPath()%>/brm/book/${book.id}/" method="post">
    <input type="hidden" name="__http_method" value="delete"/> 
    <input type="hidden" name="id" value="${book.id}">
    <table border=1>
        <tr>
            <td><label>type</label></td>
            <td>${book.type}</td>
        </tr>
        <tr>
            <td><label>name</label></td>
            <td>${book.name}</td>
        </tr>
        <tr>
            <td><label>description</label></td>
            <td>${book.description}</td>
        </tr>
        <tr>
            <td><label>publisher</label></td>
            <td>${book.publisher}</td>
        </tr>
        <tr>
            <td><label>count</label></td>
            <td>${book.count}</td>
        </tr>
        <tr>
            <td><label>price</label></td>
            <td>${book.price}</td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Delete"/></td>
        </tr>
    </table>
</form>
</body>
</html>