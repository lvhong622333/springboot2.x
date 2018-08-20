<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../module/pageModule.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/bootstrap-paginator/bootstrap-paginator.js"></script>
<title>代办</title>
<script type="text/javascript">
	$(function() {
		$("#urlPage").removeClass("active");
		$("#urlSearch").removeClass("active");
		$("#urlInput").removeClass("active");
		$("#urlTypeInput").removeClass("active");
		$.post("${pageContext.request.contextPath}/urlInfo/agency/info",
						{
							page : 1
						},
						function(data) {
							var count = data.total;
							var agencyInfo = JSON.parse(data.jsonStr);
							var html = '';
							for (var i = 0; i < agencyInfo.length; i++) {
								html = html
										+ '<div class="row" style="margin-top: 10px;">'
										+ '<div class="col-md-1">'
										+ (i + 1)
										+ '</div>'
										+ '<div class="col-md-3"> <a href="javascript:void(0)" onclick="agencyInfo('
										+ agencyInfo[i].taskId + ','
										+ agencyInfo[i].processInstanceId + ','
										+ agencyInfo[i].flowUrl
										+ ')">' +
										/* '<div class="col-md-3"> <a href="${pageContext.request.contextPath}/urlInfo/agencyUrl?id=' + agencyInfo[i].taskId + '&processInstanceId='+ agencyInfo[i].processInstanceId +  '">'+ */
										agencyInfo[i].name + '</a> </div>'
										+ '<div class="col-md-3">'
										+ agencyInfo[i].description
										+ '</div><div class="col-md-3">'
										+ agencyInfo[i].createTime
										+ '</div></div>';
							}
							$("#agencyInfo").html(html);
							pageLimit(data.pageSize, data.currentPage);
						})
	});
	function pageLimit(pagSize, currentPage) {
		var options = {
			bootstrapMajorVersion : 1, //版本
			currentPage : currentPage, //当前页数
			numberOfPages : 5, //最多显示Page页
			totalPages : pagSize, //所有数据可以显示的页数
			itemTexts : function(type, page, current) {
				switch (type) {
				case "first":
					return "首页";
				case "prev":
					return "上一页";
				case "next":
					return "下一页";
				case "last":
					return "末页";
				case "page":
					return page;
				}
			},
			onPageClicked : function(e, originalEvent, type, page) {
				$
						.ajax({
							async : true,
							url : "${pageContext.request.contextPath}/urlInfo/agency/info",
							type : "post",
							dataType : "json",
							data : {
								page : page
							},
							cache : false,
							success : function(data) {
								var agencyInfo = JSON.parse(data.jsonStr);
								var html = '';
								for (var i = 0; i < agencyInfo.length; i++) {
									html = html
											+ '<div class="row" style="margin-top: 10px;">'
											+ '<div class="col-md-1">'
											+ (i + 1)
											+ '</div>'
											+ '<div class="col-md-3"> <a href="javascript:void(0)" onclick="agencyInfo('
											+ agencyInfo[i].taskId + ','
											+ agencyInfo[i].processInstanceId + ','
											+ agencyInfo[i].flowUrl
											+ ')">' +
											/* '<div class="col-md-3"> <a href="${pageContext.request.contextPath}/urlInfo/agencyUrl?id=' + agencyInfo[i].taskId + '&processInstanceId='+ agencyInfo[i].processInstanceId + '">'+ */
											agencyInfo[i].name + '</a> </div>'
											+ '<div class="col-md-3">'
											+ agencyInfo[i].description
											+ '</div><div class="col-md-3">'
											+ agencyInfo[i].createTime
											+ '</div></div>';
								}
								$("#agencyInfo").html(html);
								pageLimit(data.pageSize, data.currentPage);
							}
						});
			}
		}
		$("#pageLimit").bootstrapPaginator(options);
	}

	function agencyInfo(taskId, processInstanceId , flowUrl) {
		$("#myModal").on("hidden.bs.modal",function(){
			$(this).removeData("bs.modal");
		});
		$("#myModal").modal({
				remote : "${pageContext.request.contextPath}" + flowUrl + "&taskId=" + taskId + "&processInstanceId=" + processInstanceId,
				backdrop : "static",
				keyboard : true
		})
	}
</script>
</head>
<body>
	<%@include file="../module/header.jsp"%>
	<%@include file="../module/urlTag.jsp"%>
	<div class="container" style="margin-top: 10px; height: 510px;">
		<!-- <input type="hidden" id="taskId">
		<input type="hidden" id="processInstanceId"> -->
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-1">序号</div>
			<div class="col-md-3">任务名</div>
			<div class="col-md-3">任务描述</div>
			<div class="col-md-3">任务创建时间</div>
		</div>
		<div id="agencyInfo"></div>
		<div class="row">
			<div style="text-align: center">
				<ul id="pageLimit"></ul>
			</div>
		</div>
		<div class="row" style="overflow: scroll;" class="pre-scrollable">
			<table id="agency_table" data-toolbar="#toolbar" data-search="true"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true">
			</table>
		</div>
		<div id="toolbar" class="btn-group">
			<button id="btn-add" type="button" class="btn" data-toggle="modal"
				data-target="#myModal" style="display: none"></button>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 200px;">
				<div class="modal-content"></div>
			</div>
		</div>
	</div>
	<%@include file="../module/footer.jsp"%>
</body>
</html>