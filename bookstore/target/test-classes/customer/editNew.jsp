<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Book Insert</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form action="<%=request.getContextPath()%>/brm/book/" method="post">
    <table border=1>
        <tr>
            <td><label for="type">type</label></td>
            <td><input type="text" name="book.type" id="type"/></td>
        </tr>
        <tr>
            <td><label for="name">name</label></td>
            <td><input type="text" name="book.name" id="name"/></td>
        </tr>
        <tr>
            <td><label for="description">description</label></td>
            <td><input type="text" name="book.description" id="description"/></td>
        </tr>
        <tr>
            <td><label for="publisher">publisher</label></td>
            <td><input type="text" name="book.publisher" id="publisher"/></td>
        </tr>
        <tr>
            <td><label for="count">count</label></td>
            <td><input type="text" name="book.count" id="count"/></td>
        </tr>
        <tr>
            <td><label for="price">price</label></td>
            <td><input type="text" name="book.price" id="price"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"/></td>
            <td><input type="reset" value="Reset"/></td>
        </tr>
    </table>
</form>
</body>
</html>