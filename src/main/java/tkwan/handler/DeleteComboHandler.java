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
			//if something is deleted, 200
			if (deleteComboIntoRDS(input.getComboFrom(), input.getComboInto())) {
				response = new DeleteComboResponse(200, input.getComboFrom(), input.getComboInto());
			}else {
				response = new DeleteComboResponse(400, "Nothing to delete in Combo: " + input.toString());
			}
			
		} catch (Exception e) {
			response = new DeleteComboResponse(400, "Unable to Delete Combo: " + input.toString() + "(" + e.getMessage() + ")");
		}
		return response;
    }

	private boolean deleteComboIntoRDS(int comboFrom, int comboInto) throws Exception {
		if (logger!=null) logger.log("in deleteComboIntoRDS");
		ComboDAO dao = new ComboDAO();
		try {
			boolean isDeleted = dao.deleteCombo(comboFrom, comboInto);
			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Couldn't delete combo: " + e.getMessage());
		}
	}
}
