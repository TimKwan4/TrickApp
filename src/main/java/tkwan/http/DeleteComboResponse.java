package tkwan.http;

public class DeleteComboResponse {
	final public int comboFrom;
	final public int comboInto;
	final public int statusCode;
	final public String error;

	public DeleteComboResponse(int statusCode, int comboFrom, int comboInto) {
		this.comboFrom = comboFrom;
		this.comboInto = comboInto;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeleteComboResponse(int statusCode, String errorMessage) {
		comboFrom = -1;
		comboInto = -1;
		
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	@Override
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Result( From: " + comboFrom + "into: "+ comboInto +"Deleted successfully)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
