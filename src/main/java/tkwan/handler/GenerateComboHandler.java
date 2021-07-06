package tkwan.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.db.TricksDAO;
import tkwan.http.GenerateComboRequest;
import tkwan.http.GenerateComboResponse;
import tkwan.http.GetAllTricksRequest;
import tkwan.http.GetAllTricksResponse;
import tkwan.model.Trick;

public class GenerateComboHandler implements RequestHandler<GenerateComboRequest, GenerateComboResponse>{
	
	//get list of tricks
	//get list of combos
	//get list of status'
	//build combo
    
}
