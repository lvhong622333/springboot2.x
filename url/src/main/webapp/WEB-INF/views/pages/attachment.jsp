<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../module/pageModule.jsp"%>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-fileInput/css/fileinput.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/bootstrap-fileInput/js/fileinput.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/bootstrap-fileInput/js/locales/zh.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>附件</title>
<script type="text/javascript">
	$(function() {
		$("#urlPage").removeClass("active");
		$("#urlSearch").removeClass("active");
		$("#urlInput").removeClass("active");
		$("#urlTypeInput").removeClass("active");
		initFileInput("input-id");
	})
	function initFileInput(ctrlName) {
		var control = $('#' + ctrlName);
		control.fileinput({ language : 'zh', //设置语言
							uploadUrl : "${pageContext.request.contextPath}/urlInfo/attachment/upload", //上传的地址
							allowedFileExtensions : [ 'jpg', 'gif', 'png','docx','doc','xls','xlsx','pdf','zip','rar','gz'],//接收的文件后缀
							uploadAsync : true, //默认异步上传
							showUpload : true, //是否显示上传按钮
							showRemove : true, //显示移除按钮
							showPreview : true, //是否显示预览
							showCaption : true,//是否显示标题
							browseClass : "btn btn-primary", //按钮样式
							dropZoneEnabled: true,//是否显示拖拽区域
							//minImageWidth: 50, //图片的最小宽度
							//minImageHeight: 50,//图片的最小高度
							//maxImageWidth: 1000,//图片的最大宽度
							//maxImageHeight: 1000,//图片的最大高度
							//maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
							minFileCount: 1,
							maxFileCount: 6, //表示允许同时上传的最大文件个数
							enctype : 'multipart/form-data',
							validateInitialCount : true,
							previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
							msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
						}).on('filepreupload',function(event, data, previewId, index) { //上传中
							var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
						}).on("fileuploaded",function(event, data, previewId, index) { //一个文件上传成功
							
						}).on('fileerror', function(event, data, msg) { //一个文件上传失败
							
						})
	}
</script>
</head>
<body>
	<%@include file="../module/header.jsp"%>
	<%@include file="../module/urlTag.jsp"%>
	<div class="container-fluid" style="margin-top: 20px;height: 500px;">
		<form id="form" method="post"
			enctype="multipart/form-data">
			<div class="row form-group">
				<label class="col-md-1 col-md-offset-2">文件上传:</label>
				<div class="col-md-6">
					<input id="input-id" name="file" multiple type="file"
						data-show-caption="true">
				</div>
			</div>
		</form>
	</div>
	<%@include file="../module/footer.jsp"%>
</body>
</html>