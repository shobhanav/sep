package order_management;

import java.util.ArrayList;

public interface EmployeeRequestManagementInterface {
	public EmployeeRequest createEmployeeRequest(String uname);
	
	public boolean addEmployeeRequest(EmployeeRequest request);
	
	public EmployeeRequest getEmployeeRequest(int id);
	
	public ArrayList<EmployeeRequest> getAllEmployeeRequest();
	
	public void deleteFinancialRequest(int id);
}
