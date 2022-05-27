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

public class postJWT_Token_Offer_Check_Final {
	
	public String respJWT_TokenID;
	public String final_respJWT_TokenID;
	
	@BeforeTest
	final void justGet() throws JSONException {
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
        System.out.println("access_tk : " + access_tk);
		
		response.prettyPrint();
		
		String responseData = response.asString();
		System.out.println("Response Data is as follows : " + responseData);
		
		int code=response.getStatusCode();
		System.out.println("Status code is : " + code);
		Assert.assertEquals(code, 200);
		
		respJWT_TokenID=responseData;
		System.out.println("respJWT_TokenID Data is as follows : " + respJWT_TokenID);
		
        String[] dataStr = respJWT_TokenID.split(":");
        System.out.println("dataStr : " + Arrays.toString(dataStr));
        System.out.println(("dataStr : " + dataStr[1]));
        String[] dataFinal = dataStr[1].split(",");
        System.out.println("Final : " + Arrays.toString(dataFinal));
        System.out.println(("Final : " + dataFinal[0].toString()));
        //final_respJWT_TokenID=dataFinal[0];
        final_respJWT_TokenID=access_tk;
		
	}

	@Test
	final void offerCheckNP_noCert() throws JsonProcessingException{
		
		Loan_parties objLoan = new Loan_parties("Borrower_Individual", 108318754, "Ayala", "02/11/2018");
		Payload p = new Payload("12345","02/11/2018",objLoan);
		
		ObjectMapper objMap = new ObjectMapper();
		String myData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(objLoan);
		System.out.println("myData values are as follows : " + myData);
		
		String myDataP = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(p);
		System.out.println("myData values are as follows : " + myDataP);
			
		//String url="https://api.corp.frbnp2.com/lending/los/v1/aio/offercheck/isvalid";
		String url="https://api.frbnp2.com/elending/los/v1/aio/offercheck/isvalid";
		String Token;
		Token = final_respJWT_TokenID;
		System.out.println("Output of bearerToken : " + Token);
		//Token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVF9DRVJUIiwicGkuYXRtIjoiNCJ9.eyJzY29wZSI6WyJlYWdsZV9sZW5kaW5nX2xvc19hcGlfY2xpZW50Il0sImNsaWVudF9pZCI6Imxvc19lYWdsZV9sZW5kaW5nIiwiZXhwIjoxNTkxMzExNzQ2fQ.KaiFJRPyJDAVFK08zP3fkc0pbXF03mSnQcKIitcw6YJDZfDg6FCBmU-M5GT1yi8Qtjx8jUvibP9InuzbFSo4twxmP2mZHgusJUG0arqpaPattVXU5DH_3ZpEyvaeLmyo5oUnEgTGtfXSCJZkhGuB_GVO2VIWnQAozZ7XdaQcmypyP61Ha-N7tXAnNYa_SP49GNkHpoltLt14sSbbbQh6zcS6SdzozetEBpHBsrmboOnjVZO7gZ_DeExroVm-VjwcoP4x1us8OiwI8hOSG30S7E00Voh_-KrAKv9fc8GL4YKt3EY7cF2jwYxg-n1StQ5j5djFyfz9EEVc7_lJwbz_hw";
		
		Response response =
			      given()
			      	  .proxy("http://peldhose:12Dec%402020@vsproxynp2:8080")
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
		
		String responseData = response.asString();
		System.out.println("responseData is as follows : " + responseData);
		
		int respCode = response.getStatusCode();
		System.out.println("respCode is as follows : " + respCode);
		
	}
	
	@Test
    public void SplitString() {
		System.out.println("respJWT_TokenID : " + respJWT_TokenID);
        String[] data = respJWT_TokenID.split(":");
        System.out.println(Arrays.toString(data));

        Pattern pattern = Pattern.compile(":");
        data = pattern.split(respJWT_TokenID);
        System.out.println(Arrays.toString(data));

        pattern = Pattern.compile(Pattern.quote(":"));
        data = pattern.split(respJWT_TokenID);
        System.out.println(Arrays.toString(data));
    }
	
}
