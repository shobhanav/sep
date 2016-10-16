package order_management;

public class EmployeeRequest {
	private int identifier;
	
	private static int index =0 ;
	
	private String department;
	
	private String uname;
	
	private int yearsOfExperience;
	
	private String jobTitle;
	
	private String jobDescription;
	
	private boolean fullTime;
	
	private StateEmployeeRequest state;
	
	public EmployeeRequest(String uname){
		this.identifier = index;
		index = index +1;
		this.uname = uname;
		this.state = StateEmployeeRequest.CREATED;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public boolean isFullTime() {
		return fullTime;
	}

	public void setFullTime(boolean fullTime) {
		this.fullTime = fullTime;
	}

	public StateEmployeeRequest getState() {
		return state;
	}

	public void setState(StateEmployeeRequest state) {
		this.state = state;
	}

	public int getIdentifier() {
		return identifier;
	}

}
