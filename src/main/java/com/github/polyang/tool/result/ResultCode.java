package com.github.polyang.tool.result;

/**
 * @Author polyang
 * @Description 返回码
 * @Date 2020/3/28 21:05
 **/
public enum ResultCode {

	/**
	 * 操作成功
	 */
	SUCCESS(200, "操作成功"),
	/**
	 * 业务异常
	 */
	FAILURE(400, "业务异常"),
	/**
	 * 请求未授权
	 */
	UN_AUTHORIZED(401, "请求未授权"),
	/**
	 * 访问无权限
	 */
	PERMISSION_DENIED(403, "访问无权限"),
	/**
	 * 没找到请求
	 */
	NOT_FOUND(404, "没找到请求"),
	/**
	 * 服务器异常
	 */
	SERVER_ERROR(500, "服务器异常");
	
	final int code;
	final String msg;
	
	private ResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}
