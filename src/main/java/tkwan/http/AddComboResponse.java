package tkwan.http;

public class AddComboResponse {
	final public int statusCode;
	final public String error;

	public AddComboResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public AddComboResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	@Override
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Result(Combo Added)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
