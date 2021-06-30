package tkwan.http;

public class DeleteComboRequest {
	int comboFrom;
	int comboInto;
	
	public DeleteComboRequest(int comboFrom, int comboInto) {
		this.comboFrom = comboFrom;
		this.comboInto = comboInto;
	}
	
	public DeleteComboRequest() {}
	
	public int getComboFrom() {
		return comboFrom;
	}

	public void setComboFrom(int comboFrom) {
		this.comboFrom = comboFrom;
	}

	public int getComboInto() {
		return comboInto;
	}

	public void setComboInto(int comboInto) {
		this.comboInto = comboInto;
	}

	@Override
	public String toString() {
		return "DeleteCombo( comboFrom : " + comboFrom + "comboInto: "+ comboInto +")";
	}
}
