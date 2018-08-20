<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网址信息录入</title>
<%@include file="../module/pageModule.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/urlInput.js"></script>
<script type="text/javascript">
	$(function() {
		$("#urlPage").removeClass("active");
		$("#urlSearch").removeClass("active");
		$("#urlInput").addClass("active");
		$("#urlTypeInput").removeClass("active");
		$(".selectpicker").selectpicker({
			width : 605,
			actionsBox : true, // 在下拉选项添加选中所有和取消选中的按钮
			countSelectedText : "已选中{0}项",
			selectedTextFormat : "count > 5",
			multiple : "true"
		})
		// 初始化页面时从后台获取参数
		$.get("${pageContext.request.contextPath}/urlInfo/getSelectors",
				function(data) {
					opt = "";
					for (var i = 0; i < data.length; i++) {
						opt += "<option value='" + data[i].dictValue + "'>"
								+ data[i].dictName + "</option>";
					}
					$("#selectpicker").append(opt);
					$("#selectpicker").selectpicker('refresh');
				})
		debugger;
		if('${inputFailInfo}' != null && '${inputFailInfo}' != ''){
			$.MsgBox.Alert("确认框",'${inputFailInfo}');
		}
	});
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
			<label for="urlName" class="col-md-2 control-label">网址名称</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="urlName" id="urlName"
					placeholder="请输入网址名称（字符数少于20）" />
			</div>
			<div class="col-md-2">
				<em style="color: red;" id="urlNameError"></em>
			</div>
		</div>
		<div class="form-group">
			<label for="url" class="col-md-2 control-label">网址URL</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="url" id="urlX"
					placeholder="请输入网址（字符数少于200）" />
			</div>
			<div class="col-md-2">
				<em style="color: red;" id="urlError"></em>
			</div>
		</div>
		<div class="form-group">
			<label for="urlDesc" class="col-md-2 control-label">网址描述</label>
			<div class="col-md-6">
				<textarea rows="10px" class="form-control" name="urlDesc"
					id="urlDesc" placeholder="请输入网址描述（字符数少于200）"></textarea>
			</div>
			<div class="col-md-2">
				<em style="color: red;" id="urlDescError"></em>
			</div>
		</div>
		<div class="form-group">
			<label for="urlType" class="col-md-2 control-label">网址类型</label>
			<div class="col-md-6">
				<select class="selectpicker" id="selectpicker" name="urlType"
					data-live-search="true">
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-3 col-md-offset-2">
				<div class="col-md-3">
					<input type="submit" class="btn btn-primary" value="提交">
				</div>
				<div class="col-md-3">
					<input type="reset" class="btn btn-primary" value="重置">
				</div>
			</div>
		</div>
	</form>
	<div style="margin-top: 82px;">
		<%@include file="../module/footer.jsp"%>
	</div>
</body>
</html>