package order_management;

import java.util.ArrayList;

public class EmployeeRequestManagementImpl implements EmployeeRequestManagementInterface{
	private ArrayList<EmployeeRequest> requests = new ArrayList<EmployeeRequest>();
	private ArrayList<EmployeeRequest> historyRequests = new ArrayList<EmployeeRequest>();
	
	@Override
	public EmployeeRequest createEmployeeRequest(String uname) {
		return new EmployeeRequest(uname);
	}

	@Override
	public boolean addEmployeeRequest(EmployeeRequest request) {
		requests.add(request);
		return true;
	}

	@Override
	public EmployeeRequest getEmployeeRequest(int id) {
		for(EmployeeRequest request: requests){
			if(request.getIdentifier()==id){
				return request;
			}
		}
		return null;
	}

	@Override
	public ArrayList<EmployeeRequest> getAllEmployeeRequest() {
		return requests;
	}

	@Override
	public void deleteFinancialRequest(int id) {
		for(EmployeeRequest request: requests){
			if(request.getIdentifier()==id){
				historyRequests.add(request);
				requests.remove(request);
			}
		}
	}

}
