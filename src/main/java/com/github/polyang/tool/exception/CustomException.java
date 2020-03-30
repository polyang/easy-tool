package com.github.polyang.tool.exception;

import com.github.polyang.tool.result.ResultCode;

/**
 * @Author polyang
 * @Description 自定义异常类
 * @Date 2020/3/28 20:56
 **/
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -2191356558975498483L;

	private Integer code;

	public CustomException(Throwable cause) {
		super(cause);
		this.code = ResultCode.SERVER_ERROR.getCode();
	}
	
	public CustomException(String msg) {
		super(msg);
		this.code = ResultCode.FAILURE.getCode();
	}
	
	public CustomException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
