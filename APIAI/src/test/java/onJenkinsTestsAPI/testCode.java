package onJenkinsTestsAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.RestAssured.given;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;
import onJenkinsTestsAPI.Loan_parties;
import onJenkinsTestsAPI.Payload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.regex.Pattern;

public class testCode {
	
	public String respJWT_TokenID;
	public String final_respJWT_TokenID;
	
	@BeforeTest
	final void getJWTToken() throws JSONException {
		String CONTEXT_PATH="https://websso.frbnp2.com/as/token.oauth2";
		
		Map<String, Object> bodyContent = new HashMap<String, Object>();
		bodyContent.put("client_id", "los_eagle_lending");
		bodyContent.put("client_secret", "07mQhHNyon1CTIkeL4XS41WbqSi1oW0rBgOtFznbuO3MnKSf9TXPFQrZqflI63zU");
		bodyContent.put("scope", "eagle_lending_los_api_client");
		bodyContent.put("grant_type", "client_credentials");
						  
		Response response = given().
		contentType("application/x-www-form-urlencoded").
		formParams(bodyContent).
		when().
		post(CONTEXT_PATH).
		then().
		statusCode(200).
		contentType("application/json;charset=utf-8").
		extract().
		response();
		
		JSONObject jsonObj = new JSONObject(response.prettyPrint());
        String access_tk = jsonObj.getString("access_token");
        System.out.println("getJWTToken access_tk : " + access_tk);
		
		response.prettyPrint();
		
		String responseData = response.asString();
		System.out.println("getJWTToken Response Data is as follows : " + responseData);
		
		int code=response.getStatusCode();
		System.out.println("BeforeTest getJWTToken's Status code is : " + code);
		Assert.assertEquals(code, 200);
		
		respJWT_TokenID=responseData;
		System.out.println("getJWTToken respJWT_TokenID Data is as follows : " + respJWT_TokenID);
		
        String[] dataStr = respJWT_TokenID.split(":");
        System.out.println("getJWTToken dataStr : " + Arrays.toString(dataStr));
        System.out.println(("getJWTToken dataStr : " + dataStr[1]));
        String[] dataFinal = dataStr[1].split(",");
        System.out.println("getJWTToken Final : " + Arrays.toString(dataFinal));
        System.out.println(("getJWTToken Final : " + dataFinal[0].toString()));
        //final_respJWT_TokenID=dataFinal[0];
        final_respJWT_TokenID=access_tk;
		
	}

	@Test
	final void getReferralOnBoardGetAdvCode() throws JSONException {
		//String CONTEXT_PATH="https://api.corp.frbnp2.com/lending/referral/v1/profile/fetch/code?code=travis58";
		String CONTEXT_PATH="https://api.frbnp2.com/elending/referral/v1/profile/fetch/code?code=travis58";
		String Token;
		Token = final_respJWT_TokenID;
		String Token1 = "";		  
		Response response = 
				given()
				  .proxy("http://peldhose:12Dec%402020@vsproxynp2:8080")
				  .relaxedHTTPSValidation()
		          .headers("Authorization", "Bearer " + Token1)	          
		          .when()
		          .get(CONTEXT_PATH)
		          .then()
		          .statusCode(500)
		          .contentType("application/json")
		          .extract()
		          .response();
		
		JSONObject jsonObj = new JSONObject(response.prettyPrint());		
		response.prettyPrint();
		
		String responseData = response.asString();
		
		responseData = "";
		
		try {
			
			
			if ( (responseData == null ) || (responseData.isEmpty()) ) {
				System.out.println("Empty");
				
			} else {
				
				System.out.println("Test1 getReferralOnBoardGetAdvCode Response Data is as follows : " + responseData);
				
				int code=response.getStatusCode();
				System.out.println("Test1 getReferralOnBoardGetAdvCode Status code is : " + code);
				try {
					Assert.assertEquals(code, 500);
				} catch (Exception e) {
					System.out.println("Exception of Test1 getReferralOnBoardGetAdvCode : " + e);
					
				}
				
			}

			
			
			
			
			
		} catch (Exception e) {
			System.out.println("Exception is as follows : " + e);
		}
		

		
	
	}
	

	
}
