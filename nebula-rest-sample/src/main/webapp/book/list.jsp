<%@ page language="java" import="java.util.*" pageEncoding="gb2312"
	contentType="text/html; charset=gb2312"%>
<html>
<head>
<title>Book Select All</title>
<meta http-equiv="content-type" content="text/html;charset=gb2312">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.6.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-jtemplates.js"></script>
<script type="text/javascript">
ctx = "<%=request.getContextPath()%>";
$(document).ready(function() {
	$("#mybtn").click(function() {
		$.ajax({
			type : "GET",
			url : ctx + "/simple/book/",
			data : "",
			contentType : "application/json; charset=utf-8",
			cache : false,
			dataType : "json",
			success : function(data) {
				if (data) {
					$("#jtdiv").setTemplateURL(ctx + "/json/list.html");
					$("#jtdiv").processTemplate(data);
				}
			}
		})
	});
});
</script>
</head>
<body id="body">
	<input type="button" value="Add"
		onclick="location.href='<%=request.getContextPath()%>/simple/book/new/'" />
	<div id="jtdiv"></div>
	<input id="mybtn" type="button" value="json" />
</body>
</html>