<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../module/pageModule.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/register.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/register.css" />
<script type="text/javascript">
	$(function() {
		var telePhoneError = "${telePhoneError}";
		var userNameError = "${userNameError}";
		var confirmPasswordError = "${confirmPasswordError}";
		$("#telePhoneError").text(telePhoneError);
		$("#telePhoneError").css({
			"color" : "red"
		});
		$("#userNameError").text(userNameError);
		$("#userNameError").css({
			"color" : "red"
		});
		$("#confirmPasswordError").text(confirmPasswordError);
		$("#confirmPasswordError").css({
			"color" : "red"
		});
	});
</script>
</head>
<body>
	<div class="container" style="margin: 160px 330px;">
		<form
			action="${pageContext.request.contextPath}/urlInfo/register/info"
			method="post" class="form-horizontal" role="form"
			onsubmit="return validate()">
			<div class="form-group">
				<label for="userName" class="col-md-3 control-label">用户名:</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="userName"
						name="userName" placeholder="请输入8-12个字符"
						onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" />
				</div>
				<label for="userNameError" id="userNameError"
					class="col-md-2 control-label"></label>
			</div>
			<div class="form-group">
				<label for="realName" class="col-md-3 control-label">真实姓名:</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="realName"
						name="realName" placeholder="请输入真实姓名"
						onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" />
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-md-3 control-label">性别:</label>
				<div class="radio col-md-3">
					<label> <input type="radio" id="sex0" name="sex" value="0"
						checked> 男
					</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label> <input type="radio"
						id="sex1" name="sex" value="1"> 女
					</label>
				</div>
			</div>
			<div class="form-group">
				<label for="birthDay" class="col-md-3 control-label">出生日期:</label>
				<div class="col-md-3">
					<input type="date" class="form-control" id="birthDay"
						name="birthDay" />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-md-3 control-label">密码:</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="password"
						name="password" placeholder="请输入密码"
						onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" />
				</div>
			</div>
			<div class="form-group">
				<label for="confirmPassword" class="col-md-3 control-label">确认密码:</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="confirmPassword"
						name="confirmPassword" placeholder="请输入确认密码"
						onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" />
				</div>
				<label for="confirmPasswordError" id="confirmPasswordError"
					class="col-md-2 control-label"></label>
			</div>
			<div class="form-group">
				<label for="telePhone" class="col-md-3 control-label">手机号码:</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="telePhone"
						name="telePhone" placeholder="请输入手机号码"
						onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" />
				</div>
				<label for="telePhoneError" id="telePhoneError"
					class="col-md-2 control-label"></label>
			</div>
			<div class="form-group">
				<div class="col-md-3 col-md-offset-3">
					<div class="col-md-3">
						<input type="submit" class="btn btn-primary" value="注册" />
					</div>
					<div class="col-md-3">
						<input type="reset" class="btn btn-primary" value="重置" />
					</div>
					<div class="col-md-3">
						<input type="button" class="btn btn-primary" value="登录"
							onclick="window.location.href='${pageContext.request.contextPath}/'" />
					</div>
				</div>
			</div>
	</div>
	</form>
	</div>
</body>
</html>