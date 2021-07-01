package tkwan.http;

public class DeleteCustomTrickResponse {
	final public int idTrick;
	final public int statusCode;
	final public String error;

	public DeleteCustomTrickResponse(int statusCode, int idTrick) {
		this.idTrick = idTrick;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeleteCustomTrickResponse(int statusCode, String errorMessage) {
		idTrick = -1;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	@Override
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Result( Trick id: " + idTrick + "Deleted successfully)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
