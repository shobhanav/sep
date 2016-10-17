package order_management;

import java.util.ArrayList;
import java.util.Iterator;

public class FinancialRequestManagementImpl implements FinancialRequestManagementInterface{
	
	private ArrayList<FinancialRequest> requests = new ArrayList<FinancialRequest>();
	private ArrayList<FinancialRequest> historyRequests = new ArrayList<FinancialRequest>();
	
	@Override
	public FinancialRequest createFinancialRequest(Crd crd,String uname) {
			return new FinancialRequest(crd,uname);
	}

	@Override
	public FinancialRequest createFinancialRequest(int project_reference,String uname) {
		return new FinancialRequest(project_reference,uname);
	}

	@Override
	public boolean addFinancialRequest(FinancialRequest request) {
		if(request == null || getFinancialRequest(request.getIdentifier()) != null){
			return false;
		}
		return requests.add(request);
	}

	@Override
	public FinancialRequest getFinancialRequest(int id) {
		for (FinancialRequest request : requests){
			if(request.getIdentifier() == id){
				return request;
			}
		}
		return null;
	}

	@Override
	public ArrayList<FinancialRequest> getFinancialRequestCrd(Crd crd) {
		ArrayList<FinancialRequest> requestsCrd = new ArrayList<FinancialRequest>();
		for (FinancialRequest request : requests){
			if(request.getProject_reference() == crd.getIdentifier()){
				requestsCrd.add(request);
			}
		}
		return requestsCrd;
	}
	
	@Override
	public ArrayList<FinancialRequest> getFinancialRequestProject(int project_reference) {
		ArrayList<FinancialRequest> requestsCrd = new ArrayList<FinancialRequest>();
		for (FinancialRequest request : requests){
			if(request.getProject_reference() == project_reference){
				requestsCrd.add(request);
			}
		}
		return requestsCrd;
	}

	@Override
	public ArrayList<FinancialRequest> getFinancialRequestUser(String uname) {
		ArrayList<FinancialRequest> requestsCrd = new ArrayList<FinancialRequest>();
		for (FinancialRequest request : requests){
			if(request.getUname().equals(uname)){
				requestsCrd.add(request);
			}
		}
		return requestsCrd;
	}
	
	@Override
	public ArrayList<FinancialRequest> getAllFinancialRequest() {
		return requests;
	}

	@Override
	public void deleteFinancialRequest(int id) {
		for (Iterator<FinancialRequest> itRequest = requests.iterator();itRequest.hasNext();){
			FinancialRequest request = itRequest.next();
			if(request.getIdentifier() == id){
				historyRequests.add(request);
				itRequest.remove();
			}
		}
	}

	@Override
	public ArrayList<FinancialRequest> getFinancialRequestHistory() {
		return historyRequests;
	}
	
}
