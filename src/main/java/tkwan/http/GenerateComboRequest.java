package tkwan.http;

public class GenerateComboRequest {
	int idUser;
	int length;
	int status;
	
	public GenerateComboRequest(int idUser, int length, int status) {
		this.idUser = idUser;
		this.length = length;
		this.status = status;
	}

	public GenerateComboRequest() {}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GenerateCombo( idUser : " + idUser + " length: " + length + " status:" + status + ")";
	}
}
