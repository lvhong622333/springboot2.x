package com.lvhong.web.pojo;

public class ResponseResult {
	private Integer statusCode;//状态码
	private String successOrError; //成功or失败
	private String statusInfo;//响应信息
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getSuccessOrError() {
		return successOrError;
	}
	public void setSuccessOrError(String successOrError) {
		this.successOrError = successOrError;
	}
	public String getStatusInfo() {
		return statusInfo;
	}
	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}
	
}
