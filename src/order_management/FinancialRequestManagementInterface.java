package order_management;

import java.util.ArrayList;

public interface FinancialRequestManagementInterface {
	
	public FinancialRequest createFinancialRequest(Crd crd, String uname);
	
	public FinancialRequest createFinancialRequest(int project_reference, String uname);
	
	public boolean addFinancialRequest(FinancialRequest request);
	
	public FinancialRequest getFinancialRequest(int id);
	
	public ArrayList<FinancialRequest> getFinancialRequestCrd(Crd crd);
	
	public ArrayList<FinancialRequest> getFinancialRequestProject(int project_reference);
	
	public ArrayList<FinancialRequest> getFinancialRequestUser(String uname);
	
	public ArrayList<FinancialRequest> getAllFinancialRequest();
	
	public void deleteFinancialRequest(int id);
	
	public ArrayList<FinancialRequest> getFinancialRequestHistory();
}
