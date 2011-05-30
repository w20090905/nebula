<%@ page language="java" import="java.util.*" pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>${name} Update</title>
<meta http-equiv="content-type" content="text/html;charset=gb2312">
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form action="<%=request.getContextPath()%>/${name}/delete" method="post">
    <input type="hidden" name="id" value="${"${"}${name?uncap_first}.id${"}"}">
    <table border=1>
        <#list commonFields as f>
        <tr>
            <td><label>${f.name}</label></td>
            <td>${"${"}${name?uncap_first}.${f.name}${"}"}</td>
        </tr>
        </#list>
        <tr>
            <td colspan="2"><input type="submit" value="Delete"/></td>
        </tr>
    </table>
</form>
</body>
</html>