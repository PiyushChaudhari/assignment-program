package org.backend.technical.vo;

import org.springframework.http.HttpStatus;

public class SearchCityResponseVO {

	public Boolean isSuccess = Boolean.TRUE;
	public String message;
	public String responseBody;
	public HttpStatus status;

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchCityResponseVO [isSuccess=");
		builder.append(isSuccess);
		builder.append(", message=");
		builder.append(message);
		builder.append(", responseBody=");
		builder.append(responseBody);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
