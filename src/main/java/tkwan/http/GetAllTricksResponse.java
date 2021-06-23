package tkwan.http;

import java.util.List;

import tkwan.model.Trick;

public class GetAllTricksResponse {
	final public List<Trick> tricks;
	final public int statusCode;
	final public String error;

	public GetAllTricksResponse(int statusCode, List<Trick> tricks) {
		this.tricks = tricks;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public GetAllTricksResponse(int statusCode, String errorMessage) {
		this.tricks = null;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	@Override
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Result(" + this.tricks.toString() + " tricks have been loaded)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
