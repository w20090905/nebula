<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Book Select All</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form>
    <table border=1>
        <tr>
            <td colspan="7"><input type="button" value="Add" onclick="location.href='<%=request.getContextPath()%>/brm/book/new'"/></td>
        </tr>
        <tr bgcolor="#cccccc">
            <td>type</td>
            <td>name</td>
            <td>description</td>
            <td>publisher</td>
            <td>count</td>
            <td>price</td>
            <td></td>
        </tr>
        <c:forEach var="book" items="${list}">
        <tr>
            <td><c:out value="${book.type}"/></td>
            <td><c:out value="${book.name}"/></td>
            <td><c:out value="${book.description}"/></td>
            <td><c:out value="${book.publisher}"/></td>
            <td><c:out value="${book.count}"/></td>
            <td><c:out value="${book.price}"/></td>
            <td>
                <a href="<%=request.getContextPath()%>/brm/book/${book.id}!editable/">Update</a>&nbsp;&nbsp;
                <a href="<%=request.getContextPath()%>/brm/book/${book.id}!removable/">Delete</a>
            </td>
        </tr>
        </c:forEach>  
    </table>
</form>
</body>
</html>