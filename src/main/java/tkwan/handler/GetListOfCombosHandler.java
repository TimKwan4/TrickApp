package tkwan.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.db.ComboDAO;
import tkwan.http.GetListOfCombosResponse;
import tkwan.model.Combo;


public class GetListOfCombosHandler implements RequestHandler<Object,GetListOfCombosResponse> {

	LambdaLogger logger;

	@Override
	public GetListOfCombosResponse handleRequest(Object input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of GetListOfCombosHandler");
		logger.log(input.toString());
		
		List<Combo> listOfCombos = getListOfCombos();
		if(listOfCombos==null)
			return new GetListOfCombosResponse(400, "List of Combos not loaded properly! code recorded: ");
		else
			return new GetListOfCombosResponse(200, listOfCombos);
	}

	private List<Combo> getListOfCombos() {
		return getListOfCombosFromRDS();
	}

	private List<Combo> getListOfCombosFromRDS() {
		if (logger!=null) logger.log("in getListOfComboFromRDS");
		ComboDAO dao = new ComboDAO();
		List<Combo> list;
		try {
			list = dao.getListOfCombos();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
