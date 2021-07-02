package tkwan.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.db.UserDAO;
import tkwan.http.LoginRequest;
import tkwan.http.LoginResponse;
import tkwan.model.User;

public class LoginHandler implements RequestHandler<LoginRequest, LoginResponse>{
	
	LambdaLogger logger;
	
    @Override
    public LoginResponse handleRequest(LoginRequest input, Context context) {
    	logger = context.getLogger();
      	logger.log("Loading Java Lambda handler of LoginHandler");
		logger.log(input.toString());
		
		LoginResponse response;
	
		try {
			int idUser = getIDUserFromRDS(input.getName());
			User user = new User(idUser, input.getName());
			if (idUser != -1) {response = new LoginResponse(200, user);}
			else {response = new LoginResponse(400, "Name doesn't exists: " + input.toString());}
			
		} catch (Exception e) {
			response = new LoginResponse(400, "Unable to Login: " + input.toString() + "(" + e.getMessage() + ")");
		}
		return response;
    }

	private int getIDUserFromRDS(String name) throws Exception {
		if (logger!=null) logger.log("in getIDUserFromRDS");
		UserDAO dao = new UserDAO();
		int idUser;
		try {
			idUser = dao.getIDUser(name);
			return idUser;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error:" + e.getMessage());
		}
	}
}
