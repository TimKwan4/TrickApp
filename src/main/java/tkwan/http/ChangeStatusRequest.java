package tkwan.http;

public class ChangeStatusRequest {
	int idUser;
	int idTrick;
	int status;
	
	public ChangeStatusRequest(int idUser, int idTrick, int status) {
		this.idUser = idUser;
		this.idTrick = idTrick;
		this.status = status;
	}

	public ChangeStatusRequest() {}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdTrick() {
		return idTrick;
	}

	public void setIdTrick(int idTrick) {
		this.idTrick = idTrick;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ChangeStatus( idUser : " + idUser + " idTrick: " + idTrick + " status:" + status + ")";
	}
}
