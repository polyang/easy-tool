package com.github.polyang.tool.result;

import java.io.Serializable;

/**
 * @Author polyang
 * @Description 返回对象
 * @Date 2020/3/28 21:04
 **/
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 4489072884122061965L;
	
	private int code;
	private boolean success;
	private String msg;
	private T data;
	
	private Result(ResultCode resultCode) {
		this(resultCode.code, resultCode.msg, null);
	}
	
	private Result(int code, String msg) {
		this(code, msg, null);
	}
	
	private Result(ResultCode resultCode, T data) {
		this(resultCode.code, resultCode.msg, data);
	}
	
	private Result(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.success = ResultCode.SUCCESS.code == code;
	}
	
	public static <T> Result<T> success(String msg) {
		return new Result<T>(ResultCode.SUCCESS.code, msg);
	}
	
	public static <T> Result<T> fail(String msg) {
		return new Result<T>(ResultCode.FAILURE.code, msg);
	}
	
	public static <T> Result<T> fail(ResultCode resultCode) {
		return new Result<T>(resultCode);
	}
	
	public static <T> Result<T> status(boolean flag) {
		return flag ? success("操作成功") : fail("操作失败");
	}
	
	public static <T> Result<T> status(int flag) {
		return flag > 0 ? success("操作成功") : fail("操作失败");
	}
	
	public static <T> Result<T> data(T data) {
		return data(ResultCode.SUCCESS.code, ResultCode.SUCCESS.msg, data);
	}
	
	public static <T> Result<T> data(String msg, T data) {
		return data(ResultCode.SUCCESS.code, msg, data);
	}
	
	public static <T> Result<T> data(ResultCode resultCode, T data) {
		return data(resultCode.code, resultCode.msg, data);
	}
	
	public static <T> Result<T> data(int code, String msg, T data) {
		return new Result<T>(code, msg == null ? "暂无数据" : msg, data);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
