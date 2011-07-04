<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>administrator Select All</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="<c:out value='/css/main.css'/>"/>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery.ui.flow.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/wz_jsgraphics.js"></script>
	
<style type="text/css">
	.before{
		padding:5px;width:40px;height:20px;border:1px solid black;
		background:#AAAAAA;
		font-size:9pt;
		color:white;
		font-weight:bolder;
		float: left;
		cursor:hand;
	};
	.hover{
		background: green;
		color: black;
	}
</style>
</head>
<body>
<form>
    <table border=1>
        <tr>
            <td colspan="7"><input type="button" value="Add" onclick="location.href='<%=request.getContextPath()%>/brm/administrator/new'"/></td>
        </tr>
        <tr bgcolor="#cccccc">
            <td>Id</td>
            <td>groupId</td>
            <td>name</td>
            <td>password</td>
            <td>role</td>
            <td>memo</td>
            <td></td>
        </tr>
        <c:forEach var="administrator" items="${result}">
        <tr>
            <td><c:out value="${administrator.id}"/></td>
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

<div id="spanBefore">
	<div class="before" begin=-1 id="1" next="2,3">环节一</div>
	<div class="before" id="2" next="4">环节二</div>
	<div class="before" id="3" next="5">环节三</div>
	<div class="before" id="4" next="6">环节四</div>
	<div class="before" id="5" next="6">环节五</div>
	<div class="before" id="6" next="7,8">环节六</div>
	<div class="before" id="7" next="9">环节七</div>
	<div class="before" id="8" next="10,11">环节八</div>
	<div class="before" id="9" next="12">环节九</div>
	<div class="before" id="10" next="12">环节10</div>
	<div class="before" id="11" next="12">环节11</div>
	<div class="before" id="12" next="-1">环节12</div>
</div>

<div style="padding:30px;border:1px dotted black;">
<div style="position:relative; width:200px; height:100px;" id="draw"></div>
</div>
<script type="text/javascript">
	var createFlow = function(){
		$("#spanBefore").flow({hover:function(){
			$(this).addClass("hover");
		},remove:function(){
			$(this).removeClass("hover");
		},click:function(){
			alert($(this).attr("id") + "->" + $(this).attr("next") + " Click");
		}});
	}
	
	createFlow();
</script>
</body>
</html>