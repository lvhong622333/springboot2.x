package com.lvhong.web.pojo;

import java.io.Serializable;
import java.util.List;

public class PageList<T> implements Serializable{
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -786707852343430043L;
	
	private Integer total; //总记录数
	private List<T> rows; //每页显示的数据
	private String jsonStr; //每页显示数据json格式
	private Integer currentPage; //当前页码数
	private Long pageSize; //页码数
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
}
