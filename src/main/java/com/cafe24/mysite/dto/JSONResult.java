package com.cafe24.mysite.dto;
public class JSONResult {
	private String result; 		// "success" or "fail"
	private String message; 	//result가 "fail"일 경우 원인 메시지
	private Object data; 		//result가 "success"일 경우 전달 데이터
	
	private JSONResult(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	
	public String getResult() {
		return result;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return "JSonResult [result=" + result + ", message=" + message + ", data=" + data + "]";
	}
	
	public static JSONResult success(Object data) {
		return new JSONResult("success", null, data);
	}
	
	public static JSONResult fail(String message) {
		return new JSONResult("fail", message, null);
	}
}
