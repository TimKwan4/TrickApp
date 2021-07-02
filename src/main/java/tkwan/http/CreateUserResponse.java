package tkwan.http;

import tkwan.model.User;

public class CreateUserResponse {
	final public User user;
	final public int statusCode;
	final public String error;

	public CreateUserResponse(int statusCode, User user) {
		this.user = user;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public CreateUserResponse(int statusCode, String errorMessage) {
		this.user = null;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	@Override
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Result(" + this.user.toString() + " user has been loaded)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
