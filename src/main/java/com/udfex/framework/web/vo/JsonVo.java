package com.udfex.framework.web.vo;

import com.udfex.framework.mybatis.page.Page;
import com.udfex.framework.mybatis.page.PageInfo;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.List;

/**
 * Json统一应答Vo对象
 * @author Jeng
 *
 */
public class JsonVo implements Serializable {

	private static final long serialVersionUID = 5426628546577087825L;

	/**
	 * 状态码：请求成功
	 */
	public static final int STATUS_SUCCESS = 0;
	/**
	 * 状态码：业务异常
	 */
	public static final int STATUS_BUSINESS_EXCEPTION = 1;
	/**
	 * 状态码：系统异常
	 */
	public static final int STATUS_SYSTEM_EXCEPTION = 2;
	/**
	 * 信息：默认"请求成功"
	 */
	public static final String MSG_DEFAULT = "SUCCESS";
	
	private int status;
	private Object result;
	private String msg;
	private String exceptionMsg;
	private PageInfo pageInfo;
	private List<ObjectError> errors;
	private boolean isFormat;

	public JsonVo() {
		this.isFormat = true;
	}

	public JsonVo(Object result) {
		this.status = STATUS_SUCCESS;
		this.result = result;
		this.isFormat = true;
		this.msg =  "success";
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public List<ObjectError> getErrors() {
		return errors;
	}

	public void setErrors(List<ObjectError> errors) {
		this.errors = errors;
	}

	public boolean isFormat() {
		return isFormat;
	}

	public void setFormat(boolean isFormat) {
		this.isFormat = isFormat;
	}
}
