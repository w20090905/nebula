<%@ page language="java" import="java.util.*" pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%%>
<html>
<head>
<title>Book Select All</title>
<meta http-equiv="content-type" content="text/html;charset=gb2312">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"/>
</head>
<body>
<form>
	<table border=1>
        <tr>
            <td colspan="7"><input type="button" value="Add" onclick="location.href='<%=request.getContextPath()%>/simple/book/new/'"/></td>
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
		<c:forEach var="book" items="${result}">
			<tr>
                <td><c:out value="${book.type}"/></td>
                <td><c:out value="${book.name}"/></td>
                <td><c:out value="${book.description}"/></td>
                <td><c:out value="${book.publisher}"/></td>
                <td><c:out value="${book.count}"/></td>
                <td><c:out value="${book.price}"/></td>
                <td>
                    <a href="<%=request.getContextPath()%>/simple/book/${book.id}!editable/">Update</a>&nbsp;&nbsp;
                    <a href="<%=request.getContextPath()%>/simple/book/${book.id}!removable/">Del</a>
                </td>
			</tr>
		</c:forEach>  
	</table>
</form>
</body>
</html>