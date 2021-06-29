package tkwan.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import cs3733.zig.choice.AddFeedbackHandler;
import cs3733.zig.choice.RequestChoicesHandler;
import cs3733.zig.choice.http.AddFeedbackRequest;
import cs3733.zig.choice.http.AddFeedbackResponse;
import cs3733.zig.choice.http.RequestChoiceResponse;
import cs3733.zig.choice.model.Feedback;
import tkwan.handler.GetAllTricksHandler;
import tkwan.http.GetAllTricksRequest;
import tkwan.http.GetAllTricksResponse;
import tkwan.model.Trick;

public class testGetAllTricks {
	//the first element in test schema has a description "Where to eat"
		//the first element in test schema has a id 5dfc1ca7-7c26-44a2-8c12-77f8f1278ab6

		@Test
		public void testRequestChoicesResponse() {
			GetAllTricksResponse error = new GetAllTricksResponse(400, "Tricks not loaded correctly");
			GetAllTricksResponse rcr = new GetAllTricksResponse(200, new ArrayList<Trick>());
			String errorMsg = error.toString();
			String successMsg = rcr.toString();
			
			assertTrue(errorMsg.startsWith("ErrorResult"));
			assertTrue(successMsg.startsWith("Result"));
		}
		
		Context createContext(String apiCall) {
			TestContext ctx = new TestContext();
			ctx.setFunctionName(apiCall);
			return ctx;
		}
		
		void testInput(String incoming, Feedback feedback) throws IOException{
			AddFeedbackHandler handler = new AddFeedbackHandler();
			AddFeedbackRequest req = new Gson().fromJson(incoming, AddFeedbackRequest.class);
			
			AddFeedbackResponse response = handler.handleRequest(req, createContext("addFeedback"));
			
			assertEquals(response.feedback.getMemberName(), feedback.getMemberName());
			assertEquals(response.feedback.getContents(), feedback.getContents());
			assertEquals(200, response.statusCode);
		}
}
