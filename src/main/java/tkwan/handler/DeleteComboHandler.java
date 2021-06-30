package tkwan.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.db.ComboDAO;
import tkwan.http.DeleteComboRequest;
import tkwan.http.DeleteComboResponse;

public class DeleteComboHandler implements RequestHandler<DeleteComboRequest, DeleteComboResponse> {

	LambdaLogger logger;
	
    @Override
    public DeleteComboResponse handleRequest(DeleteComboRequest input, Context context) {
    	logger = context.getLogger();
      	logger.log("Loading Java Lambda handler of DeleteComboHandler");
		logger.log(input.toString());
		
		DeleteComboResponse response;
	
		try {
			deleteComboIntoRDS(input.getComboFrom(), input.getComboInto());
			response = new DeleteComboResponse(200, input.getComboFrom(), input.getComboInto());
			
		} catch (Exception e) {
			response = new DeleteComboResponse(400, "Unable to Delete Combo: " + input.toString() + "(" + e.getMessage() + ")");
		}
		return response;
    }

	private void deleteComboIntoRDS(int comboFrom, int comboInto) throws Exception {
		if (logger!=null) logger.log("in deleteComboIntoRDS");
		ComboDAO dao = new ComboDAO();
		try {
			dao.deleteCombo(comboFrom, comboInto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Couldn't delete combo: " + e.getMessage());
		}
	}
}
