<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../module/pageModule.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/bootstrap-paginator/bootstrap-paginator.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的附件</title>
<script type="text/javascript">
	$(function() {
		$("#urlPage").removeClass("active");
		$("#urlSearch").removeClass("active");
		$("#urlInput").removeClass("active");
		$("#urlTypeInput").removeClass("active");
		pageLimit('${pageSize}', 1);
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
							url : "${pageContext.request.contextPath}/urlInfo/attachment/fileInfo",
							type : "post",
							dataType : "json",
							data : {
								page : page
							},
							cache : false,
							success : function(data) {
								debugger;
								var rows = data.rows;
								$("#tmFileRoute").html("");
								var html = "";
								for (var i = 0; i < rows.length; i++) {
									html = html
											+ '<div class="row" style="margin-top: 10px;">'
											+ '<div class="col-md-3">'
											+ "<a href='javascript:void(0)' onclick='upload("
											+ '"'
											+ rows[i].routeName
											+ '"'
											+ ","
											+ '"'
											+ rows[i].fileName
											+ '"'
											+ ")'>"
											+ rows[i].fileName
											+ '</a>'
											+ '</div>'
											+ '<div class="col-md-4">'
											+ rows[i].routeName
											+ '</div>'
											+ '<div class="col-md-1">'
											+ parseFloat(
													rows[i].fileSize / 1024)
													.toFixed(2)
											+ ' kb'
											+ '</div>'
											+ '<div class="col-md-2">'
											+ dateFtt("yyyy-MM-dd", new Date(
													rows[i].createTime))
											+ '</div>' + 
											'<div class="col-md-2">'+
												"<button class='btn btn-primary btn-xs' onclick='deleteFile("+ rows[i].id + ',' + '"' +  rows[i].routeName + '"'+")'>删除</button>"+
											'</div>'+
											'</div>';
								}
								$("#tmFileRoute").html(html);
								pageLimit(data.pageSize, data.currentPage);
							}
						});
			}
		}
		$("#pageLimit").bootstrapPaginator(options);
	}
	function dateFtt(fmt, date) { //author: meizz   
		var o = {
			"M+" : date.getMonth() + 1, //月份   
			"d+" : date.getDate(), //日   
			"h+" : date.getHours(), //小时   
			"m+" : date.getMinutes(), //分   
			"s+" : date.getSeconds(), //秒   
			"q+" : Math.floor((date.getMonth() + 3) / 3),
			"S" : date.getMilliseconds()
		//毫秒   
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
	function upload(routeName, fileName) {
		window.location.href = "${pageContext.request.contextPath}/urlInfo/attachment/uploadFile?routeName="
				+ routeName + "&fileName=" + fileName;
	}
	function deleteFile(id,routeName){
		$.post("${pageContext.request.contextPath}/urlInfo/attachment/deleteFile",{id:id,routeName:routeName},function(data){
				$.MsgBox.Confirm("提示框",data.statusInfo,function(){
					window.location.reload();
				});
		});
	}
</script>
</head>
<body>
	<%@include file="../module/header.jsp"%>
	<%@include file="../module/urlTag.jsp"%>
	<div class="container" style="height: 500px; margin-top: 20px;">
		<div class="row">
			<div class="col-md-3">文件名称</div>
			<div class="col-md-4">文件存储位置</div>
			<div class="col-md-1">文件大小</div>
			<div class="col-md-2">文件上传时间</div>
			<div class="col-md-2">操作</div>
		</div>
		<div id="tmFileRoute">
			<c:forEach items="${tmFileRoute}" var="item">
				<div class="row" style="margin-top: 10px;">
					<div class="col-md-3">
						<a href="javascript:void(0)"
							onclick="upload('${item.routeName}','${item.fileName}')">${item.fileName}</a>
					</div>
					<div class="col-md-4">${item.routeName}</div>
					<div class="col-md-1">
						<fmt:formatNumber value="${item.fileSize/1024}" pattern="0.00" />
						kb
					</div>
					<div class="col-md-2">
						<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />
					</div>
					<div class="col-md-2">
						<button class="btn btn-primary btn-xs"
							onclick="deleteFile(${item.id},'${item.routeName}')">删除</button>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="row">
			<div style="text-align: center">
				<ul id="pageLimit"></ul>
			</div>
		</div>
	</div>
	<%@include file="../module/footer.jsp"%>
</body>
</html>