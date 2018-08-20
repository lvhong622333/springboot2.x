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
		$("#urlSearch").addClass("active");
		$("#urlTypeInput").removeClass("active");
	})
	function importUrlInfo(){
		window.location.href="${pageContext.request.contextPath}/urlInfo/importUrlInfo";
	}
</script>
</head>
<body>
	<%@include file="../module/header.jsp"%>
	<%@include file="../module/urlTag.jsp"%>
	<div class="container">
		<div class="row" style="overflow: scroll; height: 522px;"
			class="pre-scrollable">
			<table id="urlInfo_table" data-toolbar="#toolbar" data-search="true"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true">
			</table>
		</div>
		<div id="toolbar" class="btn-group">
			<button id="btn-add" type="button" class="btn"
				onclick="updateUrlInfo()" data-toggle="modal" data-target="">
				<span aria-hidden="true" class="icon icon-plus-sign"></span>修改
			</button>
			<button id="btn-del" type="button" class="btn"
				onclick="deleteUrlInfo()">
				<span aria-hidden="true" class="icon icon-remove-sign"></span>删除
			</button>
			<button id="btn-del" type="button" class="btn"
				onclick="importUrlInfo()">
				<span aria-hidden="true" class="icon icon-remove-sign"></span>导出
			</button>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 800px; height: 400px;margin-left: 300px;margin-top: 150px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">修改网址信息</h4>
					</div>
					<div class="modal-body">
						<div class="container">
							<div class="row">
								<label for="urlName"
									class="col-md-2 col-md-offset-1 control-label">网址名称</label>
								<div class="col-md-4">
									<input type="hidden" id="UrlId" value="" /> <input type="text"
										id="urlName" value="" style="width: 400px;" />
								</div>
							</div>
							<div class="row" style="margin-top: 10px;">
								<label for="url" class="col-md-2 col-md-offset-1 control-label">网址</label>
								<div class="col-md-4">
									<input type="text" id="url" value="" style="width: 400px;" />
								</div>
							</div>
							<div class="row" style="margin-top: 10px;">
								<label for="urlType"
									class="col-md-2 col-md-offset-1 control-label">网址类型</label>
								<div class="col-md-4">
									<select class="urlType" id="urlType" data-live-search="true">
									</select>
								</div>
							</div>
							<div class="row" style="margin-top: 10px;">
								<label for="urlDesc"
									class="col-md-2 col-md-offset-1 control-label">网址描述</label>
								<div class="col-md-4">
									<textarea rows="10" id="urlDesc" style="width: 400px;"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="closeX">关闭</button>
						<button type="button" class="btn btn-primary"
							onclick="submitUpdateUrlInfo()">提交更改</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="infoCue" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 400px; height: 400px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">提示</h4>
					</div>
					<div class="modal-body">选框不能为空且不能多选</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../module/footer.jsp"%>
</body>
</html>