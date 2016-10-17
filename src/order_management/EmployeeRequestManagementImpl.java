package order_management;

import java.util.ArrayList;
import java.util.Iterator;

public class EmployeeRequestManagementImpl implements EmployeeRequestManagementInterface{
	private ArrayList<EmployeeRequest> requests = new ArrayList<EmployeeRequest>();
	private ArrayList<EmployeeRequest> historyRequests = new ArrayList<EmployeeRequest>();
	
	@Override
	public EmployeeRequest createEmployeeRequest(String uname) {
		return new EmployeeRequest(uname);
	}

	@Override
	public boolean addEmployeeRequest(EmployeeRequest request) {
		if (request == null || getEmployeeRequest(request.getIdentifier()) != null){
			return false;
		}
		return requests.add(request);
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
	public void deleteEmployeeRequest(int id) {
		for(Iterator<EmployeeRequest> itRequest = requests.iterator(); itRequest.hasNext();){
			EmployeeRequest request = itRequest.next();
			if(request.getIdentifier()==id){
				historyRequests.add(request);
				itRequest.remove();
			}
		}
	}

	@Override
	public ArrayList<EmployeeRequest> getEmployeeRequestHistory() {
		return historyRequests;
	}

}
