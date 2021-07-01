package tkwan.http;

public class AddCustomTrickResponse {
	final public int statusCode;
	final public String error;

	public AddCustomTrickResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public AddCustomTrickResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	@Override
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Result(Trick Added)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
