<%@ page language="java" import="java.util.*" pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%%>
<html>
<head>
<title>${name} Select All</title>
<meta http-equiv="content-type" content="text/html;charset=gb2312">
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
</head>
<body>
<form>
	<table border=1>
        <tr>
            <td colspan="${commonFields?size + 1}"><input type="button" value="Add" onclick="location.href='<%=request.getContextPath()%>/${name}/toInsert'"/></td>
        </tr>
		<tr bgcolor="#cccccc">
            <#list commonFields as f>
            <td>${f.name}</td>
            </#list>
            <td></td>
		</tr>
		<c:forEach var="${name?uncap_first}" items="${r"${list}"}">
			<tr>
                <#list commonFields as f>
                <td><c:out value="${"${"}${name?uncap_first}.${f.name}${"}"}"/></td>
                </#list>
                <td>
                    <a href="<%=request.getContextPath()%>/${name}/toUpdate?id=${"${"}${name?uncap_first}.id${"}"}">Update</a>&nbsp;&nbsp;
                    <a href="<%=request.getContextPath()%>/${name}/toDelete?id=${"${"}${name?uncap_first}.id${"}"}">Del</a>
                </td>
			</tr>
		</c:forEach>  
	</table>
</form>
</body>
</html>
