package tkwan.http;

import java.util.List;

import tkwan.model.Combo;

public class GetListOfCombosResponse {
	
	final public List<Combo> combos;
	final public int statusCode;
	final public String error;

	public GetListOfCombosResponse(int statusCode, List<Combo> combos) {
		this.combos = combos;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public GetListOfCombosResponse(int statusCode, String errorMessage) {
		this.combos = null;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	@Override
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Result(" + this.combos.toString() + " combos have been loaded)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
