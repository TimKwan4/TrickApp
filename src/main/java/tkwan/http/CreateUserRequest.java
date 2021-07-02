package tkwan.http;

public class CreateUserRequest {
	String name;

	public CreateUserRequest(String name) {
		this.name = name;
	}
	
	public CreateUserRequest() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CreateUser( username : " + name + ")";
	}
}
