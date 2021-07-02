package tkwan.http;

import tkwan.model.User;

public class LoginResponse {
	final public User user;
	final public int statusCode;
	final public String error;

	public LoginResponse(int statusCode, User user) {
		this.user = user;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public LoginResponse(int statusCode, String errorMessage) {
		this.user = null;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	@Override
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Result(" + this.user.toString() + " user logged in)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
