function setTableInfo() {
	$("#urlType_table").bootstrapTable({
		url : window.ctx + '/urlInfo/urlTypeSearch',
		method : 'get',
		pagination : true,
		// 是否启用点击选中行
		clickToSelect : false,
		// 最少要显示的列数
		// minimumCountColumns : 2,
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
			checkbox : true,
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : '字典数据编号',
			field : 'dictValue',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}, {
			title : '字典数据名称',
			field : 'dictName',
			rowspan : 1,
			align : 'right',
			valign : 'middle',
			sortable : true
		}, {
			title : '字典数据描述',
			field : 'dictDesc',
			rowspan : 1,
			align : 'right',
			valign : 'middle'
		}, {
			title : '字典数据类别',
			field : 'dictType',
			rowspan : 1,
			align : 'right',
			valign : 'middle'
		}],
		filter : true
	});
}
// 请求成功方法
function responseHandler(result) {
	return result;
}