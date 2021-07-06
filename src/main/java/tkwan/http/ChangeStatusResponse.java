package tkwan.http;

import tkwan.model.Status;

public class ChangeStatusResponse {
	final public Status status;
	final public int statusCode;
	final public String error;

	public ChangeStatusResponse(int statusCode, Status status) {
		this.status = status;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public ChangeStatusResponse(int statusCode, String errorMessage) {
		this.status = null;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	@Override
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Result(" + this.status.toString() + " status has been updated)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
