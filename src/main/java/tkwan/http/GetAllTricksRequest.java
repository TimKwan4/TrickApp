package tkwan.http;

public class GetAllTricksRequest {
	public int idUser;
	
	public GetAllTricksRequest(int idUser) {
		this.idUser = idUser;
	}
	
	public GetAllTricksRequest() {}

	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Override
	public String toString() {
		return "GetAllTricks( idUser : " + idUser + ")";
	}
}
