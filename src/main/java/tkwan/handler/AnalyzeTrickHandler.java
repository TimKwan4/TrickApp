package tkwan.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tkwan.http.AnalyzeTrickResponse;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;
import java.util.List;

public class AnalyzeTrickHandler implements RequestHandler<Object, AnalyzeTrickResponse> {

	LambdaLogger logger;
	
    @Override
    public AnalyzeTrickResponse handleRequest(Object input, Context context) {
    	logger = context.getLogger();
      	logger.log("Loading Java Lambda handler of AnalyzeTrick");
		logger.log(input.toString());
		
		AnalyzeTrickResponse response;
		
		String photo = "input.jpg";
		String bucket = "bucket";


		AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

		DetectLabelsRequest request = new DetectLabelsRequest()
			.withImage(new Image()
			.withS3Object(new S3Object()
			.withName(photo).withBucket(bucket)))
			.withMaxLabels(10)
			.withMinConfidence(75F);
		
		try {
			addComboIntoRDS(input.getComboFrom(), input.getComboInto());
			response = new AddComboResponse(200);
			
		} catch (Exception e) {
			response = new AddComboResponse(400, "Unable to Add Combo: " + input.toString() + "(" + e.getMessage() + ")");
		}
		return response;
    }
}