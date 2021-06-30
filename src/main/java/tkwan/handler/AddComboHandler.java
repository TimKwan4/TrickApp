package tkwan.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.db.ComboDAO;
import tkwan.http.AddComboRequest;
import tkwan.http.AddComboResponse;

public class AddComboHandler implements RequestHandler<AddComboRequest, AddComboResponse> {

	LambdaLogger logger;
	
    @Override
    public AddComboResponse handleRequest(AddComboRequest input, Context context) {
    	logger = context.getLogger();
      	logger.log("Loading Java Lambda handler of AddComboHandler");
		logger.log(input.toString());
		
		AddComboResponse response;
	
		try {
			addComboIntoRDS(input.getComboFrom(), input.getComboInto());
			response = new AddComboResponse(200);
			
		} catch (Exception e) {
			response = new AddComboResponse(400, "Unable to Add Combo: " + input.toString() + "(" + e.getMessage() + ")");
		}
		return response;
    }

	private void addComboIntoRDS(int comboFrom, int comboInto) throws Exception {
		if (logger!=null) logger.log("in addComboIntoRDS");
		ComboDAO dao = new ComboDAO();
		try {
			dao.addCombo(comboFrom, comboInto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Couldn't add combo: " + e.getMessage());
		}
	}
}
