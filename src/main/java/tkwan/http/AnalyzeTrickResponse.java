package tkwan.http;

public class AnalyzeTrickResponse {
	final public int statusCode;
	final public String error;

	public AnalyzeTrickResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public AnalyzeTrickResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	@Override
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Result(TrickAnalyzed)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
