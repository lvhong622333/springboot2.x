<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%
response.setStatus(404);
out.print("页面不存在.");
%>
<%@include file="../module/module.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>页面不存在</title>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header"><h1>页面不存在.</h1></div>
		<div><a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a></div>
		<script>try{top.$.jBox.closeTip();}catch(e){}</script>
	</div>
</body>
</html>
<%
out = pageContext.pushBody();
%>