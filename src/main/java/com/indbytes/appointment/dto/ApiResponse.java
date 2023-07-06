package com.indbytes.appointment.dto;

public class ApiResponse {
	private boolean success;
	int status;
	private String message;
	private Object data;

	public ApiResponse() {
		super();
	}

	public ApiResponse(boolean success, int status, String message, Object data) {
		super();
		this.success = success;
		this.status = status;
		this.message = message;
		this.data = data;
	}



	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
