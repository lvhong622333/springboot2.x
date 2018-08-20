<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<div style="padding-left: 1200px; margin-top: 10px;">
	<span style="color: white;"> 
		<em>欢迎您：</em>${user.realName}<em>
				<c:if test="${user.sex == '0'}">
							先生
				</c:if> 
				<c:if test="${user.sex == '1'}">
							女士
				</c:if>
		</em>
	</span>&nbsp; 
	<span> 
		<em>
			<a href="${pageContext.request.contextPath}/urlInfo/logout" style="color: white;">
				注销
			</a>
		</em>
	</span>
</div>
<div class="container">
	<div class="top-menu">
		<ul class="nav navbar-nav pull-right">
			<li
				class="dropdown dropdown-extended dropdown-notification dropdown-dark"
				id="header_notification_bar" style="padding: 0 0 0 0 !important;"><a
				href="javascript:;" id="indexHistory" class="dropdown-toggle"
				data-toggle="dropdown" data-hover="dropdown"
				data-close-others="true" onclick="window.location.href='${pageContext.request.contextPath}/urlInfo/myFile'"> 我的文件 </a></li>
			<!-- <li class="dropdown dropdown-extended dropdown-tasks dropdown-dark"
				id="header_task_bar"><a href="javascript:;" id="indexFavorite"
				class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
				data-close-others="true" onclick="toNewPage(this)"> 收藏 </a></li> -->
			<li class="dropdown dropdown-extended dropdown-inbox dropdown-dark"
				id="header_inbox_bar"><a href="javascript:;"
				class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
				data-close-others="true" onclick="window.location.href='${pageContext.request.contextPath}/urlInfo/attachment'">文件存储<span class="badge badge-default"
					style="right: -17px !important;" id="news_count"></span></a></li>
			<li class="dropdown dropdown-extended dropdown-inbox dropdown-dark"
				id="header_inbox_bar"><a href="javascript:void(0);" onclick="window.location.href='${pageContext.request.contextPath}/urlInfo/agency'"
				class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
				data-close-others="true"> 待办 <span class="badge badge -default"
					style="right: -17px !important;"></span></a></li>
		</ul>
	</div>
</div>