<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网址检索正文</title>
<%@include file="../module/pageModule.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/urlSearch.js"></script>
<script type="text/javascript">
	$(function() {
		window.ctx = '${pageContext.request.contextPath}';
		setTableInfo();
		$("#urlPage").removeClass("active");
		$("#urlInput").removeClass("active");
		$("#urlSearch").removeClass("active");
		$("#urlTypeInput").removeClass("active");
		$("#urlMail").addClass("active");
	})
	function importUrlInfo(){
		window.location.href="${pageContext.request.contextPath}/urlInfo/importUrlInfo";
	}
</script>
</head>
<body>
	<%@include file="../module/header.jsp"%>
	<%@include file="../module/urlTag.jsp"%>
	<form class="form-horizontal" role="form"
		action="${pageContext.request.contextPath}/urlInfo/urlInput"
		method="post" style="margin-top: 30px; margin-left: 200px;"
		onsubmit="return validateUrlInfo()">
		<div class="form-group">
			<label for="urlName" class="col-md-2 control-label">收件人邮箱</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="urlName" id="urlName"
					placeholder="请输入发件人邮箱" />
			</div>
			<div class="col-md-2">
				<em style="color: red;" id="urlNameError"></em>
			</div>
		</div>
		<div class="form-group">
			<label for="url" class="col-md-2 control-label">邮件主题</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="url" id="urlX"
					placeholder="请输入邮件主题" />
			</div>
			<div class="col-md-2">
				<em style="color: red;" id="urlError"></em>
			</div>
		</div>
		<div class="form-group">
			<label for="urlDesc" class="col-md-2 control-label">邮件内容</label>
			<div class="col-md-6">
				<textarea rows="10px" class="form-control" name="urlDesc"
					id="urlDesc" placeholder="请输入邮件内容"></textarea>
			</div>
			<div class="col-md-2">
				<em style="color: red;" id="urlDescError"></em>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-3 col-md-offset-2">
				<div class="col-md-4">
					<input type="submit" class="btn btn-primary" value="发送邮件">
				</div>
				<div class="col-md-4">
					<input type="reset" class="btn btn-primary" value="重置内容">
				</div>
			</div>
		</div>
	</form>
	<%@include file="../module/footer.jsp"%>
</body>
</html>