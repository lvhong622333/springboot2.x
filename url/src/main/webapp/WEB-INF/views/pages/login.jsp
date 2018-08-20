<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../module/pageModule.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>登陆页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		var userNameError = "${userNameError}";
		var passwordError = "${passwordError}";
		if (userNameError != "") {
			$("#userNameError").text(userNameError);
			$("#userNameError").css({
				"color" : "red"
			});
		}
		if (passwordError != "") {
			$("#passwordError").text(passwordError);
			$("#passwordError").css({
				"color" : "red"
			});
		}
	});
</script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/login.css"></link>
</head>
<body>
	<div class="container" style="margin: 280px 360px;">
		<form class="form-horizontal" role="form"
			action="${pageContext.request.contextPath}/urlInfo/login"
			method="post">
			<div class="form-group">
				<label for="userName" class="col-md-2 control-label">用户名:</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="userName"
						name="userName" placeholder="请输入用户名" />
				</div>
				<label for="userNameError" id="userNameError"
					class="col-md-2 control-label"></label>
			</div>
			<div class="form-group">
				<label for="password" class="col-md-2 control-label">密码:</label>
				<div class="col-md-3">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="请输入八位数密码" />
				</div>
				<label for="passwordError" id="passwordError"
					class="col-md-2 control-label"></label>
			</div>
			<div class="form-group">
				<div class="col-md-3 col-md-offset-2">
					<div class="col-md-3">
						<input type="submit" class="btn btn-primary" value="登录">
					</div>
					<div class="col-md-3">
						<input type="reset" class="btn btn-primary" value="重置">
					</div>
					<div class="col-md-3">
						<input type="button" class="btn btn-primary" value="注册"
							onclick="window.location.href='${pageContext.request.contextPath}/urlInfo/register'">
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>