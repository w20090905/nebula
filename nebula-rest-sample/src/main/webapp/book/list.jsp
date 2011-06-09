<%@ page language="java" import="java.util.*" pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>

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
            <td colspan="7"><input type="button" value="Add" onclick="location.href='<%=request.getContextPath()%>/Book/toInsert'"/></td>
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
	</table>
</form>
</body>
</html>