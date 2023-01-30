package com.musala.alvaro.testdrones.exceptions;

import java.util.Date;

public class ExceptionDetail {

	private Date date;
	private String httpStatus;
	private String message;
	private String code;
	private String backendMessage;
	public ExceptionDetail(Date date, String httpStatus, String message, String code, String backendMessage) {
		super();
		this.date = date;
		this.httpStatus = httpStatus;
		this.message = message;
		this.code = code;
		this.backendMessage = backendMessage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBackendMessage() {
		return backendMessage;
	}
	public void setBackendMessage(String backendMessage) {
		this.backendMessage = backendMessage;
	}
	@Override
	public String toString() {
		return "ExceptionDetail [date=" + date + ", httpStatus=" + httpStatus + ", message=" + message + ", code="
				+ code + ", backendMessage=" + backendMessage + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backendMessage == null) ? 0 : backendMessage.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((httpStatus == null) ? 0 : httpStatus.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionDetail other = (ExceptionDetail) obj;
		if (backendMessage == null) {
			if (other.backendMessage != null)
				return false;
		} else if (!backendMessage.equals(other.backendMessage))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (httpStatus == null) {
			if (other.httpStatus != null)
				return false;
		} else if (!httpStatus.equals(other.httpStatus))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
	
}
