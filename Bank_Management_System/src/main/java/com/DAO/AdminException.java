package com.DAO;

public class AdminException extends RuntimeException {
	String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public AdminException(String msg) {
	}

}
