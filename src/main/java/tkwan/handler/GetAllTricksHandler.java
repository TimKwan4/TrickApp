package tkwan.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.db.TricksDAO;
import tkwan.http.GetAllTricksRequest;
import tkwan.http.GetAllTricksResponse;
import tkwan.model.Trick;

public class GetAllTricksHandler implements RequestHandler<GetAllTricksRequest, GetAllTricksResponse> {

	LambdaLogger logger;
	
    @Override
    public GetAllTricksResponse handleRequest(GetAllTricksRequest input, Context context) {
    	logger = context.getLogger();
      	logger.log("Loading Java Lambda handler of GetAllTricksHandler");
		logger.log(input.toString());
		
		GetAllTricksResponse response;
	
		try {
			List<Trick> list = getListOfTricksFromRDS(input.getIdUser());
			response = new GetAllTricksResponse(200, list);
			
		} catch (Exception e) {
			response = new GetAllTricksResponse(400, "Unable to retrieve Tricks: " + input.toString() + "(" + e.getMessage() + ")");
		}
		return response;
    }

	private List<Trick> getListOfTricksFromRDS(int idUser) throws Exception {
		if (logger!=null) logger.log("in getListOfTricksFromRDS");
		TricksDAO dao = new TricksDAO();
		List<Trick> list;
		logger.log("about to get list");
		try {
			list = dao.getListOfTricks(idUser);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
