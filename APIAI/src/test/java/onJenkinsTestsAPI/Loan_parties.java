package onJenkinsTestsAPI;

public class Loan_parties {
	
	String role;
	int ssn;
	String last_name;
	String birth_date;
	
	public Loan_parties(String role, int ssn, String last_name, String birth_date) {
		this.role = role;
		this.ssn = ssn;
		this.last_name = last_name;
		this.birth_date = birth_date;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public int getSSN() {
		return ssn;
	}
	
	public void setSSN(int ssn) {
		this.ssn = ssn;
	}
	
	public String getlast_name() {
		return last_name;
	}
	
	public void setlast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getbirth_date() {
		return birth_date;
	}
	
	public void setbirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

}
