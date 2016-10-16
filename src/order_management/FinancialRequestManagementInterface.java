package order_management;

import java.util.ArrayList;

public interface FinancialRequestManagementInterface {
	
	public FinancialRequest createFinancialRequest(Crd crd, String uname);
	
	public FinancialRequest createFinancialRequest(int project_reference, String uname);
	
	public boolean addFinancialRequest(FinancialRequest request);
	
	public FinancialRequest getFinancialRequest(int id);
	
	public ArrayList<FinancialRequest> getFinancialRequest(Crd crd);
	
	public ArrayList<FinancialRequest> getAllFinancialRequest();
	
	public void deleteFinancialRequest(int id);
}
