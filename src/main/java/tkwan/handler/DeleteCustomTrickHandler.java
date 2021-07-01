package tkwan.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.db.TricksDAO;
import tkwan.http.DeleteCustomTrickRequest;
import tkwan.http.DeleteCustomTrickResponse;

public class DeleteCustomTrickHandler implements RequestHandler<DeleteCustomTrickRequest, DeleteCustomTrickResponse> {

	LambdaLogger logger;
	
    @Override
    public DeleteCustomTrickResponse handleRequest(DeleteCustomTrickRequest input, Context context) {
    	logger = context.getLogger();
      	logger.log("Loading Java Lambda handler of DeleteCustomTrickHandler");
		logger.log(input.toString());
		
		DeleteCustomTrickResponse response;
	
		try {
			//if something is deleted, 200
			if (deleteCustomTrickInRDS(input.getIdTrick())) {
				response = new DeleteCustomTrickResponse(200, input.getIdTrick());
			}else {
				response = new DeleteCustomTrickResponse(400, "Nothing to delete in CustomTrick: " + input.toString());
			}
			
		} catch (Exception e) {
			response = new DeleteCustomTrickResponse(400, "Unable to Delete custom Trick: " + input.toString() + "(" + e.getMessage() + ")");
		}
		return response;
    }

	private boolean deleteCustomTrickInRDS(int idTrick) throws Exception {
		if (logger!=null) logger.log("in deleteCustomTrickInRDS");
		TricksDAO dao = new TricksDAO();
		try {
			boolean isDeleted = dao.deleteCustomTrick(idTrick);
			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Couldn't delete custom Trick: " + e.getMessage());
		}
	}
}
