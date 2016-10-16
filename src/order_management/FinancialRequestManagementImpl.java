package order_management;

import java.util.ArrayList;

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
		requests.add(request);
		return true;
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
	public ArrayList<FinancialRequest> getFinancialRequest(Crd crd) {
		ArrayList<FinancialRequest> requestsCrd = new ArrayList<FinancialRequest>();
		for (FinancialRequest request : requests){
			if(request.getProject_reference() == crd.getIdentifier()){
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
		for (FinancialRequest request : requests){
			if(request.getIdentifier() == id){
				historyRequests.add(request);
				requests.remove(request);
			}
		}
	}
	
}
