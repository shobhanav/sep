package order_management;

public class FinancialRequest {
	
	private int identifier;
	
	private static int index =0 ;
	
	private String department;
	
	private int project_reference;
	
	private String uname;
	
	private int required_Amount;
	
	private String comment;
	
	private FinancialRequestState state;
	
	public FinancialRequest(Crd crd, String uname){
		this(crd.getIdentifier(),uname);
	}
	
	public FinancialRequest(int project_reference, String uname){
		this.identifier = index;
		index = index +1;
		this.project_reference = project_reference;
		this.uname = uname;
		this.state = FinancialRequestState.CREATED;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getProject_reference() {
		return project_reference;
	}

	public void setProject_reference(int project_reference) {
		this.project_reference = project_reference;
	}

	public int getRequired_Amount() {
		return required_Amount;
	}

	public void setRequired_Amount(int required_Amount) {
		this.required_Amount = required_Amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getIdentifier() {
		return identifier;
	}

	public FinancialRequestState getState() {
		return state;
	}

	public void setState(FinancialRequestState state) {
		this.state = state;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

}
