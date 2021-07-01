package tkwan.http;

public class AddCustomTrickRequest {
	String trickName;
	String trickDes;
	int customUser;
	
	public AddCustomTrickRequest(String trickName, String trickDes, int customUser) {
		this.trickName = trickName;
		this.trickDes = trickDes;
		this.customUser = customUser;
	}

	public AddCustomTrickRequest() {}

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
		return "AddCustomTrick( trickName: " + trickName + ")";
	}
}
