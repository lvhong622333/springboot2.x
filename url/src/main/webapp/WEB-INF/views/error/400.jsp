<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%
response.setStatus(400);
// 获取异常类
Throwable ex = ExceptionUtils.getThrowable(request);
// 编译错误信息
StringBuilder sb = new StringBuilder("错误信息：\n");
if (ex != null) {
	if (ex instanceof BindException) {
		for (ObjectError e : ((BindException)ex).getGlobalErrors()){
			sb.append("☆" + e.getDefaultMessage() + "(" + e.getObjectName() + ")\n");
		}
		for (FieldError e : ((BindException)ex).getFieldErrors()){
			sb.append("☆" + e.getDefaultMessage() + "(" + e.getField() + ")\n");
		}
		LoggerFactory.getLogger("400.jsp").warn(ex.getMessage(), ex);
	}else if (ex instanceof ConstraintViolationException) {
		for (ConstraintViolation<?> v : ((ConstraintViolationException)ex).getConstraintViolations()) {
			sb.append("☆" + v.getMessage() + "(" + v.getPropertyPath() + ")\n");
		}
	} else {
		sb.append("☆" + ex.getMessage());
	}
} else {
	sb.append("参数有误,服务器无法解析.\n\n");
}
// 如果是异步请求或是手机端，则直接返回信息
if (Servlets.isAjaxRequest(request)) {
	out.print(sb);
}
// 输出异常信息页面
else {
%>
<%@include file="../module/module.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>400 - 请求出错</title>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header"><h1>参数有误,服务器无法解析.</h1></div>
		<div class="errorMessage">
			<%=StringUtils.toHtml(sb.toString())%> <br/>
		</div>
		<a href="javascript:void(0)" onclick="history.go(-1);" class="btn">返回上一页</a> &nbsp;
		<br/> <br/>
		<script>try{top.$.jBox.closeTip();}catch(e){}</script>
	</div>
</body>
</html>
<%
} out = pageContext.pushBody();
%>