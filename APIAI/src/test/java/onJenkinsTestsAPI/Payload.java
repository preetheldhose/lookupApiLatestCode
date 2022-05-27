package onJenkinsTestsAPI;

public class Payload {
	
	String loanId;
	String application_date;
	Loan_parties loan_parties;
	
	public Payload(String loanId, String application_date, Loan_parties loan_partiesx) {
		this.loanId = loanId;
		this.application_date = application_date;
		this.loan_parties = loan_partiesx;
	}
	
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getApplication_date() {
		return application_date;
	}
	public void setApplication_date(String application_date) {
		this.application_date = application_date;
	}
	public Loan_parties getLoan_parties() {
		return loan_parties;
	}
	public void setLoan_parties(Loan_parties loan_parties) {
		this.loan_parties = loan_parties;
	}


}
