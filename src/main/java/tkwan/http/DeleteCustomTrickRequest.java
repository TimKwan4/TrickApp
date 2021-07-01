package tkwan.http;

public class DeleteCustomTrickRequest {
	int idTrick;
	
	public DeleteCustomTrickRequest(int idTrick) {
		this.idTrick = idTrick;
	}
	
	public DeleteCustomTrickRequest() {}

	public int getIdTrick() {
		return idTrick;
	}

	public void setIdTrick(int idTrick) {
		this.idTrick = idTrick;
	}

	@Override
	public String toString() {
		return "DeleteCustomTrick( idTrick : " + idTrick + ")";
	}
}
