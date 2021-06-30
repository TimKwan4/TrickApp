package tkwan.http;

public class AddComboRequest {
	int comboFrom;
	int comboInto;
	
	public AddComboRequest(int comboFrom, int comboInto) {
		this.comboFrom = comboFrom;
		this.comboInto = comboInto;
	}
	
	public AddComboRequest() {}
	
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
		return "AddCombo( comboFrom : " + comboFrom + "comboInto: "+ comboInto +")";
	}
}
