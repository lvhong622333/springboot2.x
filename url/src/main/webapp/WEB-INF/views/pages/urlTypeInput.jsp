<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../module/pageModule.jsp"%>
<title>数据字典</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/urlTypeInput.js"></script>
<script type="text/javascript">
	$(function() {
		window.ctx = '${pageContext.request.contextPath}';
		$("#urlPage").removeClass("active");
		$("#urlInput").removeClass("active");
		$("#urlSearch").removeClass("active");
		$("#urlTypeInput").addClass("active");
		setTableInfo();
	});

	function addDictInfo() {
		$("#btn-add").attr("data-target", "#myModal");
		$(".modal-footer").html("");
		var html = "<button type='button' class='btn btn-default' data-dismiss='modal' id='closeX'>关闭</button> <button type='button' class='btn btn-primary' onclick='addDictInfo2()'>添加</button>";
		$(".modal-footer").append(html);
	}
	
	function addDictInfo2(){
		$.post(ctx+"/urlInfo/addDictInfo",{
			dictDesc:$("#dictDesc").val(),
			dictValue:$("#dictValue").val(),
			dictName:$("#dictName").val(),
			dictType:$("#dictType").val()
		},function(data){
			$.MsgBox.Confirm("确认框", "字典信息添加成功，等待审批", function() {
				window.location.reload();
			});
		});
	}
	
	function updateDictInfo() {
		$("#btn-update").attr("data-target", "#myModal");
		$(".modal-footer").html("");
		var html = "<button type='button' class='btn btn-default' data-dismiss='modal' id='closeX'>关闭</button> <button type='button' class='btn btn-primary' onclick='UpdateDictInfo2()'>更新</button>";
		$(".modal-footer").append(html);
		var getSelectRows = $("#urlType_table").bootstrapTable('getSelections',
				function(row) {
					return row;
		});
		if(getSelectRows.length == 1){
				$("#dictId").val(getSelectRows[0].id);
				$("#dictDesc").val(getSelectRows[0].dictDesc);
				$("#dictValue").val(getSelectRows[0].dictValue);
				$("#dictName").val(getSelectRows[0].dictName);
				$("#dictType").val(getSelectRows[0].dictType);
		}
	}

	function UpdateDictInfo2(){
		$.post(ctx + "/urlInfo/updateDictInfo",{
			id : $("#dictId").val(),
			dictDesc:$("#dictDesc").val(),
			dictValue:$("#dictValue").val(),
			dictName:$("#dictName").val(),
			dictType:$("#dictType").val()
		},function(data){
			$.MsgBox.Confirm("确认框", "信息已更新，等待审批", function() {
				window.location.reload();
			});
		})
	}
	
	function deleteDictInfo() {
		var getSelectRows = $("#urlType_table").bootstrapTable('getSelections',
				function(row) {
					return row;
				});
		if(getSelectRows.length > 0){
			var dictInfos = "";
			for(var i = 0 ; i < getSelectRows.length;i++){
				if(i == 0 ){					
					dictInfos  +=  getSelectRows[i].id;
				}else{
					dictInfos  = dictInfos + "," + getSelectRows[i].id ;
				}
			}
			$.post(window.ctx + "/urlInfo/deleteDictInfo",{dictInfos:dictInfos},function(data){
				$.MsgBox.Confirm("确认框", "信息已删除，等待审批", function() {
					window.location.reload();
				});
			});
		}
		
	}
	function importDictInfo(){
		window.location.href="${pageContext.request.contextPath}/urlInfo/importDictInfo";
	}
</script>
</head>
<body>
	<%@include file="../module/header.jsp"%>
	<%@include file="../module/urlTag.jsp"%>
	<div class="container">
		<div class="row" style="overflow: scroll; height: 522px;"
			class="pre-scrollable">
			<table id="urlType_table" data-toolbar="#typeToolbar"
				data-search="true" data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true">
			</table>
		</div>
		<div id="typeToolbar" class="btn-group">
			<button id="btn-add" type="button" class="btn"
				onclick="addDictInfo()" data-toggle="modal">
				<span aria-hidden="true" class="icon icon-plus-sign"></span>增加
			</button>
			<button id="btn-update" type="button" class="btn"
				onclick="updateDictInfo()" data-toggle="modal">
				<span aria-hidden="true" class="icon icon-plus-sign"></span>更新
			</button>
			<button id="btn-del" type="button" class="btn"
				onclick="deleteDictInfo()">
				<span aria-hidden="true" class="icon icon-remove-sign"></span>删除
			</button>
			<button id="btn-del" type="button" class="btn"
				onclick="importDictInfo()">
				<span aria-hidden="true" class="icon icon-remove-sign"></span>导出
			</button>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"
			style="width: 600px; height: 400px; margin-left: 450px; margin-top: 250px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加字典数据</h4>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<label for="dictValue"
								class="col-md-2 col-md-offset-1 control-label">字典数据编号</label>
							<div class="col-md-4">
								<input type="hidden" id="dictId" value="" /> <input type="text"
									id="dictValue" value="" style="width: 200px;" />
							</div>
						</div>
						<div class="row" style="margin-top: 10px;">
							<label for="dictName" class="col-md-2 col-md-offset-1 control-label">字典数据名称</label>
							<div class="col-md-4">
								<input type="text" id="dictName" value="" style="width: 200px;" />
							</div>
						</div>
						<div class="row" style="margin-top: 10px;">
							<label for="dictDesc"
								class="col-md-2 col-md-offset-1 control-label">字典数据描述</label>
							<div class="col-md-4">
								<input type="text" id="dictDesc" value="" style="width: 200px;" />
							</div>
						</div>
						<div class="row" style="margin-top: 10px;">
							<label for="dictType"
								class="col-md-2 col-md-offset-1 control-label">字典数据类别</label>
							<div class="col-md-4">
								<input type="text" id="dictType" value="" style="width: 200px;" />
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	<%@include file="../module/footer.jsp"%>
</body>
</html>