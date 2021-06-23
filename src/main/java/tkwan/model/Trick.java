package tkwan.model;

public class Trick {

	private int idTrick;
	private String trickName;
	private String trickDes;
	private int customUser;
	
	public Trick(int idTrick, String trickName, String trickDes, int customUser) {
		this.idTrick = idTrick;
		this.trickName = trickName;
		this.trickDes = trickDes;
		this.customUser = customUser;
	}
	
	public Trick() {}

	public int getIdTrick() {
		return idTrick;
	}

	public void setIdTrick(int idTrick) {
		this.idTrick = idTrick;
	}

	public String getTrickName() {
		return trickName;
	}

	public void setTrickName(String trickName) {
		this.trickName = trickName;
	}

	public String getTrickDes() {
		return trickDes;
	}

	public void setTrickDes(String trickDes) {
		this.trickDes = trickDes;
	}

	public int getCustomUser() {
		return customUser;
	}

	public void setCustomUser(int customUser) {
		this.customUser = customUser;
	}
	
	@Override
	public String toString() {
		return "TRICK. Name: " + this.trickName + " Des: " + this.trickDes;
	}
}
