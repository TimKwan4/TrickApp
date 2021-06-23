package tkwan.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.http.GetAllTricksRequest;
import tkwan.http.GetAllTricksResponse;

public class GetAllTricksHandler implements RequestHandler<GetAllTricksRequest, GetAllTricksResponse> {

	LambdaLogger logger;
	
    @Override
    public GetAllTricksResponse handleRequest(GetAllTricksRequest input, Context context) {
    	logger = context.getLogger();
      	logger.log("Loading Java Lambda handler of GetAllTricksHandler");
		logger.log(input.toString());
		
		AddFeedbackResponse response;
	
		try {
			if (isChoiceCompleted(input.getIdAlternative())) {
				response = new AddFeedbackResponse("Choice has already been completed", 400);
			} else {
				Feedback feedback = addFeedback(input.getMemberName(), input.getContents(), input.getIdAlternative());
				response = new AddFeedbackResponse(feedback);
			}
			
		} catch (Exception e) {
			response = new AddFeedbackResponse("Unable to add Feedback: " + input.getContents() + "(" + e.getMessage() + ")", 400);
		}
		return response;
    }

	private boolean isChoiceCompleted(String idAlternative) throws Exception{
		if (logger != null) logger.log("in feedback isChoiceCompleted");
		AlternativesDAO adao = new AlternativesDAO();
		String idChoice = adao.getIdChoice(idAlternative);
		ChoicesDAO cdao = new ChoicesDAO();
		return cdao.isChoiceCompleted(idChoice);
	}

	private Feedback addFeedback(String memberName, String contents, String idAlternative) throws Exception {
		if (logger != null) logger.log("in createFeedback");
		FeedbackDAO dao = new FeedbackDAO();
		Feedback feedback = new Feedback(memberName, contents);
		dao.addFeedback(feedback, idAlternative);
		return feedback;
	}
}
