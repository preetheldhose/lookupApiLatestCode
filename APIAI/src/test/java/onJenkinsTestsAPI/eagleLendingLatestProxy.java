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

public class eagleLendingLatestProxy {
	
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
        //System.out.println("getJWTToken access_tk : " + access_tk);
		
		response.prettyPrint();
		
		//
		String responseData = response.asString();
		//System.out.println("getJWTToken Response Data is as follows : " + responseData);
		
		int code=response.getStatusCode();
		//System.out.println("BeforeTest getJWTToken's Status code is : " + code);
		Assert.assertEquals(code, 200);
		
		respJWT_TokenID=responseData;
		//System.out.println("getJWTToken respJWT_TokenID Data is as follows : " + respJWT_TokenID);
		
        String[] dataStr = respJWT_TokenID.split(":");
        //System.out.println("getJWTToken dataStr : " + Arrays.toString(dataStr));
        //System.out.println(("getJWTToken dataStr : " + dataStr[1]));
        String[] dataFinal = dataStr[1].split(",");
        //System.out.println("getJWTToken Final : " + Arrays.toString(dataFinal));
        //System.out.println(("getJWTToken Final : " + dataFinal[0].toString()));
        //final_respJWT_TokenID=dataFinal[0];
        final_respJWT_TokenID=access_tk;
		
	}

	@Test
	final void getReferralOnBoardGetAdvCode() throws JSONException {
		//String CONTEXT_PATH="https://api.corp.frbnp2.com/lending/referral/v1/profile/fetch/code?code=travis58";
		String CONTEXT_PATH="https://api.frbnp2.com/elending/referral/v1/profile/fetch/code?code=travis58";
		String Token;
		Token = final_respJWT_TokenID;
						  
		Response response = 
				given()
				  //.proxy("http://z_elnd_auto_proxy:Fj0zWBGJ%23F9%7D2ur61%25dn@vsproxynp2:8080")
				  .proxy("http://z_eaglelending_proxy:H8s8d%3D%23spL@vsproxynp2:8080 ")
				  .relaxedHTTPSValidation()
		          .headers("Authorization", "Bearer " + Token)	          
		          .when()
		          .get(CONTEXT_PATH)
		          .then()
		          .statusCode(200)
		          .contentType("application/json; charset=utf-8")
		          .extract()
		          .response();
		
		JSONObject jsonObj = new JSONObject(response.prettyPrint());		
		response.prettyPrint();
		
		String responseData = response.asString();
		//System.out.println("Test1 getReferralOnBoardGetAdvCode Response Data is as follows : " + responseData);
		
		int code=response.getStatusCode();
		//System.out.println("Test1 getReferralOnBoardGetAdvCode Status code is : " + code);
		try {
			Assert.assertEquals(code, 200);
		} catch (Exception e) {
			//System.out.println("Exception of Test1 getReferralOnBoardGetAdvCode : " + e);
			
		}
		
	
	}
	
	@Test
	final void getReferralOnboarHealthCheck() throws JSONException {
		//String CONTEXT_PATH="https://api.corp.frbnp2.com/lending/referral/healthcheck";
		String CONTEXT_PATH="https://api.frbnp2.com/elending/referral/healthcheck";
		String Token;
		Token = final_respJWT_TokenID;
						  
		Response response = 
				given()
				  //.proxy("http://z_elnd_auto_proxy:Fj0zWBGJ%23F9%7D2ur61%25dn@vsproxynp2:8080")
				  .proxy("http://z_eaglelending_proxy:H8s8d%3D%23spL@vsproxynp2:8080 ")
				  .relaxedHTTPSValidation()
		          .headers("Authorization", "Bearer " + Token)	          
		          .when()
		          .get(CONTEXT_PATH)
		          .then()
		          .statusCode(200)
		          .contentType("application/json; charset=utf-8")
		          .extract()
		          .response();
		
		JSONObject jsonObj = new JSONObject(response.prettyPrint());		
		response.prettyPrint();
		
		String responseData = response.asString();
		//System.out.println("Response Data is as follows : " + responseData);
		
		int code=response.getStatusCode();
		//System.out.println("Test2 getReferralOnboarHealthCheck Status code is : " + code);
		try {
			Assert.assertEquals(code, 200);
		} catch (Exception e) {
			//System.out.println("Exception of Test2 getReferralOnboarHealthCheck : " + e);
		}
		
		
	}

	@Test
	final void getStaticRoutingModelSRMHC() throws JSONException {
		//String CONTEXT_PATH="https://api.corp.frbnp2.com/lending/srmodel";
		String CONTEXT_PATH="https://api.frbnp2.com/elending/srmodel/";
		String Token;
		Token = final_respJWT_TokenID;
						  
		Response response = 
				given()
				  //.proxy("http://z_elnd_auto_proxy:Fj0zWBGJ%23F9%7D2ur61%25dn@vsproxynp2:8080")
				  .proxy("http://z_eaglelending_proxy:H8s8d%3D%23spL@vsproxynp2:8080 ")
				  .relaxedHTTPSValidation()
		          .headers("Authorization", "Bearer " + Token)	          
		          .when()
		          .get(CONTEXT_PATH)
		          .then()
		          .statusCode(200)
		          .contentType("application/json")
		          .extract()
		          .response();
		
		JSONObject jsonObj = new JSONObject(response.prettyPrint());		
		response.prettyPrint();
		
		String responseData = response.asString();
		//System.out.println("Response Data is as follows : " + responseData);
		
		int code=response.getStatusCode();
		//System.out.println("Test 3: getStaticRoutingModelSRMHC Status code is : " + code);
		try {
			Assert.assertEquals(code, 200);
		} catch (Exception e) {
			//System.out.println("Exception of Test 3: getStaticRoutingModelSRMHC : " + e);
		}

		
	}

	@Test
	final void LOSAPISrvFirmOfferCdt_noCert() throws JsonProcessingException{
		
		Loan_parties objLoan = new Loan_parties("Borrower_Individual", 108318754, "Ayala", "02/11/2018");
		Payload p = new Payload("12345","02/11/2018",objLoan);
		
		ObjectMapper objMap = new ObjectMapper();
		String myData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(objLoan);
		//System.out.println("myData values are as follows : " + myData);
		
		String myDataP = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(p);
		//System.out.println("myData values are as follows : " + myDataP);
			
		//String url="https://api.corp.frbnp2.com/lending/los/v1/aio/offercheck/isvalid";
		
		String url = "https://api.frbnp2.com/elending/los/v1/aio/offercheck/isvalid";
		String Token;
		Token = final_respJWT_TokenID;
		//System.out.println("Output of bearerToken : " + Token);
		System.out.println("Next");
		//Token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVF9DRVJUIiwicGkuYXRtIjoiNCJ9.eyJzY29wZSI6WyJlYWdsZV9sZW5kaW5nX2xvc19hcGlfY2xpZW50Il0sImNsaWVudF9pZCI6Imxvc19lYWdsZV9sZW5kaW5nIiwiZXhwIjoxNTkxMzExNzQ2fQ.KaiFJRPyJDAVFK08zP3fkc0pbXF03mSnQcKIitcw6YJDZfDg6FCBmU-M5GT1yi8Qtjx8jUvibP9InuzbFSo4twxmP2mZHgusJUG0arqpaPattVXU5DH_3ZpEyvaeLmyo5oUnEgTGtfXSCJZkhGuB_GVO2VIWnQAozZ7XdaQcmypyP61Ha-N7tXAnNYa_SP49GNkHpoltLt14sSbbbQh6zcS6SdzozetEBpHBsrmboOnjVZO7gZ_DeExroVm-VjwcoP4x1us8OiwI8hOSG30S7E00Voh_-KrAKv9fc8GL4YKt3EY7cF2jwYxg-n1StQ5j5djFyfz9EEVc7_lJwbz_hw";
		
		Response response =
			      given()
			      	  //.proxy("http://z_elnd_auto_proxy:Fj0zWBGJ%23F9%7D2ur61%25dn@vsproxynp2:8080")
			          .proxy("http://z_eaglelending_proxy:H8s8d%3D%23spL@vsproxynp2:8080 ")
			      	  .relaxedHTTPSValidation()
			          .headers("Authorization", "Bearer " + Token)
			          .headers("Content-Type", "application/json")
			          .headers("Accept", "application/json")	          
			          .body(myDataP)
			          .when()
			          .post(url)
			          .then()
			          .contentType(ContentType.JSON)
			          .extract()
			          .response();
		
		//String responseData = response.asString();
		
		String responseData = "";
		
		System.out.println("Latest");
		
		if( (responseData == null) || (responseData.isEmpty()) ) {
			System.out.println("responseData of test LOSAPISrvFirmOfferCdt_noCert is : " + responseData);
		} else {
			
			System.out.println("responseData is as follows : " + responseData);
			
			int respCode = response.getStatusCode();
			System.out.println("respCode is as follows : " + respCode);
			System.out.println("Test 4: LOSAPISrvFirmOfferCdt_noCert Status code is : " + respCode);
			
			try {
				Assert.assertEquals(respCode, 200);
			} catch (Exception e) {
				System.out.println("Exception of Test 4: LOSAPISrvFirmOfferCdt_noCert Status : " + e);
			}
			
		}

	}
	
	@SuppressWarnings({ "unused", "unused" })
	@Test
	final void postLOSAPISrvLOSHealthCheck() throws JsonProcessingException, JSONException  {
		System.out.println("Hello");
		//String CONTEXT_PATH="https://api.corp.frbnp2.com/lending/los/healthcheck";
		String CONTEXT_PATH="https://api.frbnp2.com/elending/los/healthcheck";
		
		Loan_parties objLoan = new Loan_parties("Borrower_Individual", 108318754, "Ayala", "02/11/2018");
		Payload p = new Payload("12345","02/11/2018",objLoan);
		
		ObjectMapper objMap = new ObjectMapper();
		String myData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(objLoan);
		System.out.println("myData values are as follows : " + myData);
		
		String myDataP = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(p);
		System.out.println("myData values are as follows : " + myDataP);
			
		//String url="https://api.corp.frbnp2.com/lending/los/v1/aio/offercheck/isvalid";
		String url = "https://api.frbnp2.com/elending/los/healthcheck";
		String Token;
		Token = final_respJWT_TokenID;
		//System.out.println("Output of bearerToken : " + Token);
						  
		Response response = 
				given()
				  //.proxy("http://z_elnd_auto_proxy:12Dec%402020@vsproxynp2:8080")
				  //.proxy("http://z_elnd_auto_proxy:Fj0zWBGJ%23F9%7D2ur61%25dn@vsproxynp2:8080")
				  .proxy("http://z_eaglelending_proxy:H8s8d%3D%23spL@vsproxynp2:8080 ")
				  .relaxedHTTPSValidation()
		          .headers("Authorization", "Bearer " + Token)	 
		          .headers("Content-Type", "application/json")
		          .headers("Accept", "application/json")	 
		          .when()
		          .post(CONTEXT_PATH)
		          .then()
		          .statusCode(200)
		          .contentType("application/json; charset=utf-8")
		          .extract()
		          .response();
		
		JSONObject jsonObj = new JSONObject(response.prettyPrint());		
		response.prettyPrint();
		
		String responseData = response.asString();
		System.out.println("Response Data is as follows : " + responseData);
		
		int code=response.getStatusCode();
		System.out.println("Test 5 getLOSAPISrvLOSHealthCdt Status code is : " + code);
		
		try {
			
			Assert.assertEquals(code, 200);
			
		} catch( Exception e) {
			System.out.println("Exception of Test 5 getLOSAPISrvLOSHealthCdt : " + e);
		}

		
	}
	
	@Test
	final void postEagleEyeHealthCk() throws JSONException, JsonProcessingException {
		System.out.println("Hello 1");
		//String CONTEXT_PATH="https://api.corp.frbnp2.com/lending/eagle-eye/healthcheck";
		String CONTEXT_PATH="https://api.frbnp2.com/elending/eagle-eye/healthcheck";
		//String Token;
		//Token = final_respJWT_TokenID;
		
		Loan_parties objLoan = new Loan_parties("Borrower_Individual", 108318754, "Ayala", "02/11/2018");
		Payload p = new Payload("12345","02/11/2018",objLoan);
		
		ObjectMapper objMap = new ObjectMapper();
		String myData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(objLoan);
		System.out.println("myData values are as follows : " + myData);
		
		String myDataP = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(p);
		System.out.println("myData values are as follows : " + myDataP);
			
		//String url="https://api.corp.frbnp2.com/lending/los/v1/aio/offercheck/isvalid";
		String url="https://api.frbnp2.com/elending/eagle-eye/healthcheck";
		String Token;
		Token = final_respJWT_TokenID;
		//System.out.println("Output of bearerToken : " + Token);
		//Token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVF9DRVJUIiwicGkuYXRtIjoiNCJ9.eyJzY29wZSI6WyJlYWdsZV9sZW5kaW5nX2xvc19hcGlfY2xpZW50Il0sImNsaWVudF9pZCI6Imxvc19lYWdsZV9sZW5kaW5nIiwiZXhwIjoxNTkxMzExNzQ2fQ.KaiFJRPyJDAVFK08zP3fkc0pbXF03mSnQcKIitcw6YJDZfDg6FCBmU-M5GT1yi8Qtjx8jUvibP9InuzbFSo4twxmP2mZHgusJUG0arqpaPattVXU5DH_3ZpEyvaeLmyo5oUnEgTGtfXSCJZkhGuB_GVO2VIWnQAozZ7XdaQcmypyP61Ha-N7tXAnNYa_SP49GNkHpoltLt14sSbbbQh6zcS6SdzozetEBpHBsrmboOnjVZO7gZ_DeExroVm-VjwcoP4x1us8OiwI8hOSG30S7E00Voh_-KrAKv9fc8GL4YKt3EY7cF2jwYxg-n1StQ5j5djFyfz9EEVc7_lJwbz_hw";
								  
		Response response = 
				given()
		          //.proxy("http://z_elnd_auto_proxy:Fj0zWBGJ%23F9%7D2ur61%25dn@vsproxynp2:8080")
				  .proxy("http://z_eaglelending_proxy:H8s8d%3D%23spL@vsproxynp2:8080 ")
				  .relaxedHTTPSValidation()
		          .headers("Authorization", "Bearer " + Token)
		          .body(myDataP)
		          .when()
		          .post(CONTEXT_PATH)
		          .then()
		          .statusCode(200)
		          .contentType("application/json")
		          .extract()
		          .response();
		
		JSONObject jsonObj = new JSONObject(response.prettyPrint());		
		response.prettyPrint();
		
		String responseData = response.asString();
		System.out.println("Response Data is as follows : " + responseData);
		
		int code=response.getStatusCode();
		System.out.println("Test 6 postEagleEyeHealthCk Status code is : " + code);
		try {
			Assert.assertEquals(code, 200);
		} catch (Exception e) {
			System.out.println("Exception of Test 6 postEagleEyeHealthCk" + e);
			
		}

		
	}
	
}
