<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>administrator Select All</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form>
    <table border=1>
        <tr>
            <td colspan="7"><input type="button" value="Add" onclick="location.href='<%=request.getContextPath()%>/brm/administrator/new'"/></td>
        </tr>
        <tr bgcolor="#cccccc">
            <td>groupId</td>
            <td>name</td>
            <td>password</td>
            <td>role</td>
            <td>memo</td>
            <td></td>
        </tr>
        <c:forEach var="administrator" items="${list}">
        <tr>
            <td><c:out value="${administrator.groupId}"/></td>
            <td><c:out value="${administrator.name}"/></td>
            <td><c:out value="${administrator.password}"/></td>
            <td><c:out value="${administrator.role}"/></td>
            <td><c:out value="${administrator.memo}"/></td>
            <td>
                <a href="<%=request.getContextPath()%>/brm/administrator/${administrator.id}!editable/">Update</a>&nbsp;&nbsp;
                <a href="<%=request.getContextPath()%>/brm/administrator/${administrator.id}!removable/">Delete</a>
            </td>
        </tr>
        </c:forEach>  
    </table>
</form>
</body>
</html>