package tkwan.http;

import java.util.List;

import tkwan.model.Status;

public class GetUserStatusResponse {
	final public List<Status> status;
	final public int statusCode;
	final public String error;

	public GetUserStatusResponse(int statusCode, List<Status> status) {
		this.status = status;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public GetUserStatusResponse(int statusCode, String errorMessage) {
		this.status = null;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	@Override
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Result(" + this.status.toString() + " status have been loaded)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
