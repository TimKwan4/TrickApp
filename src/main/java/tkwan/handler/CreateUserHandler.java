package tkwan.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.db.UserDAO;
import tkwan.http.CreateUserRequest;
import tkwan.http.CreateUserResponse;
import tkwan.model.User;

public class CreateUserHandler implements RequestHandler<CreateUserRequest, CreateUserResponse> {
	LambdaLogger logger;
	
    @Override
    public CreateUserResponse handleRequest(CreateUserRequest input, Context context) {
    	logger = context.getLogger();
      	logger.log("Loading Java Lambda handler of CreateUserHandler");
		logger.log(input.toString());
		
		CreateUserResponse response;
	
		try {
			int id = createUserInRDS(input.getName());
			response = new CreateUserResponse(200, new User(id, input.getName()));
			
		} catch (Exception e) {
			response = new CreateUserResponse(400, "Unable to Create user: " + input.toString() + "(" + e.getMessage() + ")");
		}
		return response;
    }

	private int createUserInRDS(String name) throws Exception {
		if (logger!=null) logger.log("in createUserInRDS");
		UserDAO dao = new UserDAO();
		try {
			return dao.createUser(name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Couldn't create User: " + e.getMessage());
		}
	}
}
