package tkwan.http;

public class GetUserStatusRequest {
	int idUser;
	
	public GetUserStatusRequest(int idUser) {
		this.idUser = idUser;
	}
	
	public GetUserStatusRequest() {}

	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Override
	public String toString() {
		return "GetUserStatus( idUser : " + idUser + ")";
	}
}
