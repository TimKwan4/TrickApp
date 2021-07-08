package tkwan.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.db.StatusDAO;
import tkwan.http.GetUserStatusRequest;
import tkwan.http.GetUserStatusResponse;
import tkwan.model.Status;

public class GetUserStatusHandler implements RequestHandler<GetUserStatusRequest, GetUserStatusResponse>{

	LambdaLogger logger;
	
    @Override
    public GetUserStatusResponse handleRequest(GetUserStatusRequest input, Context context) {
    	logger = context.getLogger();
      	logger.log("Loading Java Lambda handler of GetUserStatusHandler");
		logger.log(input.toString());
		
		GetUserStatusResponse response;
	
		try {
			List<Status> list = getListOfStatusFromRDS(input.getIdUser());
			response = new GetUserStatusResponse(200, list);
			
		} catch (Exception e) {
			response = new GetUserStatusResponse(400, "Unable to retrieve Status: " + input.toString() + "(" + e.getMessage() + ")");
		}
		return response;
    }

	private List<Status> getListOfStatusFromRDS(int idUser) throws Exception {
		if (logger!=null) logger.log("in getListOfStatusFromRDS");
		StatusDAO dao = new StatusDAO();
		List<Status> list;
		try {
			list = dao.getListOfStatus(idUser);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("unable to retrieve Status from RDS");
		}
	}
}
