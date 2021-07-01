package tkwan.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.db.TricksDAO;
import tkwan.http.AddCustomTrickRequest;
import tkwan.http.AddCustomTrickResponse;

public class AddCustomTrickHandler implements RequestHandler<AddCustomTrickRequest, AddCustomTrickResponse> {

	LambdaLogger logger;
	
    @Override
    public AddCustomTrickResponse handleRequest(AddCustomTrickRequest input, Context context) {
    	logger = context.getLogger();
      	logger.log("Loading Java Lambda handler of AddCustomTrickHandler");
		logger.log(input.toString());
		
		AddCustomTrickResponse response;
	
		try {
			addCustomTrickIntoRDS(input.getIdTrick(), input.getTrickName(), input.getTrickDes(), input.getCustomUser());
			response = new AddCustomTrickResponse(200);
			
		} catch (Exception e) {
			response = new AddCustomTrickResponse(400, "Unable to Add Trick: " + input.toString() + "(" + e.getMessage() + ")");
		}
		return response;
    }

	private void addCustomTrickIntoRDS(int idTrick, String trickName, String trickDes, int customUser) throws Exception {
		if (logger!=null) logger.log("in addCustomTrickIntoRDS");
		TricksDAO dao = new TricksDAO();
		try {
			dao.addCustomTrick(idTrick, trickName, trickDes, customUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Couldn't add custom Trick: " + e.getMessage());
		}
	}
}