package tkwan.model;

public class Combo {
	private int comboFrom;
	private int comboInto;
	
	public Combo(int comboFrom, int comboInto) {
		this.comboFrom = comboFrom;
		this.comboInto = comboInto;
	}
	
	public Combo() {}

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
		return "Combo: " + this.comboFrom + " into: " + this.comboInto;
	}
}
