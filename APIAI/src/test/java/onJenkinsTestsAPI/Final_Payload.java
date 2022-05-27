package onJenkinsTestsAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Final_Payload {

	public static void main(String[] args) throws JsonProcessingException {
		// TODO Auto-generated method stub
		
		Loan_parties objLoan = new Loan_parties("Borrower_Individual", 108318754, "Ayala", "02/11/2018");
		Payload p = new Payload("12345","02/11/2018",objLoan);
		
		ObjectMapper objMap = new ObjectMapper();
		String myData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(objLoan);
		System.out.println("myData values are as follows : " + myData);
		
		String myDataP = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(p);
		System.out.println("myData values are as follows : " + myDataP);
	}

}
