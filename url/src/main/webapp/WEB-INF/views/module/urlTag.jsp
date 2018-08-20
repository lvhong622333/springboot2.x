<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav nav-tabs" style="margin-top: 10px; margin-left: 40px;">
	<li id="urlPage" class="active"><a href="#"
		onclick="window.location.href='${pageContext.request.contextPath}/urlInfo/page/homePage'">首页</a></li>
	<li id="urlSearch" class="active"><a href="#"
		onclick="window.location.href='${pageContext.request.contextPath}/urlInfo/page/urlSearch'">网址检索</a></li>
	<li id="urlInput"><a href="#"
		onclick="window.location.href='${pageContext.request.contextPath}/urlInfo/page/urlInput'">网址录入</a></li>
	<li id="urlTypeInput"><a href="#"
		onclick="window.location.href='${pageContext.request.contextPath}/urlInfo/page/urlTypeInput'">数据字典</a></li>
</ul>