package tkwan.http;

public class LoginRequest {
	String name;
	
	public LoginRequest(String name) {
		this.name = name;
	}
	
	public LoginRequest() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Login( name : " + name + ")";
	}
}
