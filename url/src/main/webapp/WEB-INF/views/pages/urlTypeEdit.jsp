<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../module/pageModule.jsp"%>
<script type="text/javascript">
	function ApproveDict(flags) {
		var taskId = $("#taskId").val();
		var processInstanceId = $("#processInstanceId").val();
		var approveAdvice = $("#approveAdvice").val();
		$.post("${pageContext.request.contextPath}/urlInfo/agencyApply", {
			taskId : taskId,
			approveAdvice : approveAdvice,
			processInstanceId : processInstanceId,
			flags : flags,
			dictValue:$("#dictValue").val(),
			dictName:$("#dictName").val(),
			dictDesc:$("#dictDesc").val(),
			dictType:$("#dictType").val(),
			id:$("#dictId").val()
		}, function(data) {
			window.location.reload();
		});
	}
</script>
<input type="hidden" value="${taskId}" id="taskId" />
<input type="hidden" value="${processInstanceId}" id="processInstanceId" />
<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">修改网址信息</h4>
	</div>
	<div class="modal-body">
		<div class="container">
			<div class="row">
				<label for="dictValue"
					class="col-md-2 col-md-offset-1 control-label">字典数据编号</label>
				<div class="col-md-4">
					<input type="hidden" id="dictId" value="${tmDictionary.id}" /> <input type="text"
						id="dictValue" style="width: 200px;"
						value="${tmDictionary.dictValue}" />
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<label for="dictName" class="col-md-2 col-md-offset-1 control-label">字典数据名称</label>
				<div class="col-md-4">
					<input type="text" id="dictName" style="width: 200px;"
						value="${tmDictionary.dictName}" />
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<label for="dictDesc" class="col-md-2 col-md-offset-1 control-label">字典数据描述</label>
				<div class="col-md-4">
					<input type="text" id="dictDesc" style="width: 200px;"
						value="${tmDictionary.dictDesc}" />
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<label for="dictType" class="col-md-2 col-md-offset-1 control-label">字典数据类别</label>
				<div class="col-md-4">
					<input type="text" id="dictType" style="width: 200px;"
						value="${tmDictionary.dictType}" />
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<label for="approveAdvice" class="col-md-2 col-md-offset-1 control-label">审批意见</label>
				<div class="col-md-4">
					<textarea style="width: 200px;" rows="3" placeholder="审批意见"
						id="approveAdvice">
							</textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal"
			id="closeX">关闭</button>
		<button type="button" class="btn btn-primary"
			onclick="ApproveDict(true)">重新申请</button>
		<button type="button" class="btn btn-primary"
			onclick="ApproveDict(false)">作废</button>
	</div>
</div>