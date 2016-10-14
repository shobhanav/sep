package order_management;

import java.util.ArrayList;

public interface CrdManagementInterface {
	
	public Crd createCrd( Rep rep);
	
	public Crd getCrd(Rep rep);
	
	public ArrayList<Crd> getAllCrd();

}
