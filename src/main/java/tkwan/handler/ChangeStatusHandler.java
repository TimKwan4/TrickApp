package tkwan.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.db.StatusDAO;
import tkwan.http.ChangeStatusRequest;
import tkwan.http.ChangeStatusResponse;
import tkwan.model.Status;

public class ChangeStatusHandler implements RequestHandler<ChangeStatusRequest, ChangeStatusResponse>{
	
	LambdaLogger logger;
	
    @Override
    public ChangeStatusResponse handleRequest(ChangeStatusRequest input, Context context) {
    	logger = context.getLogger();
      	logger.log("Loading Java Lambda handler of ChangeStatusHandler");
		logger.log(input.toString());
		
		ChangeStatusResponse response;
	
		try {
			Status status = updateStatusInRDS(input.getIdUser(), input.getIdTrick(), input.getStatus());
			if (status != null) {response = new ChangeStatusResponse(200, status);}
			else {response = new ChangeStatusResponse(400, "Name doesn't exists: " + input.toString());}
			
		} catch (Exception e) {
			response = new ChangeStatusResponse(400, "Status RDS error: " + input.toString() + "(" + e.getMessage() + ")");
		}
		return response;
    }
    
    private Status updateStatusInRDS(int idUser, int idTrick, int status) throws Exception {
		if (logger!=null) logger.log("in updateStatusInRDS");
		StatusDAO dao = new StatusDAO();
		Status statusObj;
		try {
			statusObj = dao.updateStatus(idUser,idTrick,status);
			return statusObj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error:" + e.getMessage());
		}
	}
}
