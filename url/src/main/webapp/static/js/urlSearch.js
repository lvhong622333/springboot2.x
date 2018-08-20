function setTableInfo() {
		$("#urlInfo_table").bootstrapTable({
			url : window.ctx + '/urlInfo/urlSearch',
			method : 'get',
			pagination : true,
			// 是否启用点击选中行
			clickToSelect : false,
			// 最少要显示的列数
			//minimumCountColumns : 2,
			// 显示隐藏列
			showColumns : true,
			// cell没有值时显示
			undefinedText : '-',
			// 分页方式：client客户端分页，server服务端分页
			sidePagination : "server",
			// 初始化
			pageSize : 10,
			// 初始化加载第1页，默认第1页
			pageNumber : 1,
			// 可供选择的每页的行数
			pageList : "[2 , 10 , 20, 50, 80, 100]",
			paginationFirstText : "首页",
			paginationPreText : "上一页",
			paginationNextText : "下一页",
			paginationLastText : "末页",
			// 按钮样式
			buttonsClass : 'btn',
			// 分页器class
			iconSize : 'pager',
			queryParamsType : '',
			// 查询条件
			queryParams : function queryParams(params) { // 设置查询参数
				var param = {
					limit : params.pageSize, // 每页多少条数据
					pageNo : params.pageNumber, // 页码
					searchText : params.searchText, // 搜索文本
					sortName : params.sortName,
					sortOrder : params.sortOrder
				};
				return param;
			},
			responseHandler : responseHandler,
			columns : [ {
				// field : 'id',
				checkbox : true,
				rowspan : 1,
				align : 'center',
				valign : 'middle'
			}, {
				title : '网址名称',
				field : 'urlName',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '网址',
				field : 'url',
				rowspan : 1,
				align : 'right',
				valign : 'middle',
				sortable : true,
				formatter : aFormatter
			}, {
				title : '网址类型',
				field : 'urlType',
				rowspan : 1,
				align : 'right',
				valign : 'middle',
				sortable : true
			}, {
				title : '网址描述',
				field : 'urlDesc',
				rowspan : 1,
				align : 'right',
				valign : 'middle',
				sortable : true
			} ],
			filter : true
		});
	}
// 请求成功方法
function responseHandler(result) {
	return result;
}

function aFormatter(value, row, index) {
	return [ "<a href='" + value + "' target='_blank'>" + value + "</a>" ]
			.join("")
}

function updateUrlInfo() {
	$(".urlType").selectpicker({
		width : 400,
		actionsBox : true, // 在下拉选项添加选中所有和取消选中的按钮
		countSelectedText : "已选中{0}项",
		selectedTextFormat : "count > 5",
		multiple : "true"
	})
	// 初始化页面时从后台获取参数
	$.get(window.ctx + "/urlInfo/getSelectors", function(data) {
		opt = "";
		for (var i = 0; i < data.length; i++) {
			opt += "<option value='" + data[i].dictValue + "'>"
					+ data[i].dictName + "</option>";
		}
		$("#urlType").append(opt);
		$("#urlType").selectpicker('refresh');
	})
	var getSelectRows = $("#urlInfo_table").bootstrapTable('getSelections',
			function(row) {
				return row;
			});
	var length1 = getSelectRows.length;
	if (length1 === 1) {
		// 数据回选
		$("#urlName").val(getSelectRows[0].urlName);
		$("#url").val(getSelectRows[0].url);
		$("#urlDesc").val(getSelectRows[0].urlDesc);
		$("#urlType").selectpicker('val', getSelectRows[0].urlType);
		$("#UrlId").val(getSelectRows[0].id);
		$("#btn-add").attr("data-target", "#myModal");
	} else {
		$("#btn-add").attr("data-target", "#infoCue")
	}
}

function submitUpdateUrlInfo() {
	$.post(window.ctx + "/urlInfo/updateUrlInfo", {
		urlName : $("#urlName").val(),
		url : $("#url").val(),
		urlDesc : $("#urlDesc").val(),
		urlType : $("#urlType").val(),
		id : $("#UrlId").val()
	}, function(result) {
		$.MsgBox.Confirm("确认框", result.statusInfo, function() {
			$("#myModal").modal().hide();
			$(".modal-backdrop").remove();
			window.location.reload();
		})
	});
}

function deleteUrlInfo() {
	var getSelectRows = $("#urlInfo_table").bootstrapTable('getSelections',
			function(row) {
				return row;
			});
	var lengthX = getSelectRows.length;
	if (lengthX > 0) {
		var param = "";
		for (var i = 0; i < lengthX; i++) {
			if (i == 0) {
				param = getSelectRows[i].id;
			} else {
				param = param + "," + getSelectRows[i].id;
			}
		}
		$.MsgBox.Confirm("确认框", "确认删除选中的信息吗", function() {
			$.post(window.ctx + '/urlInfo/deleteUrlInfo', {
				param : param
			}, function(data) {
				window.location.reload();
			});
		});
	}
}
