package order_management;

import java.util.ArrayList;

public interface CrdManagementInterface {
	
	public boolean createCrd( Rep rep);
	
	public Crd getCrd(Rep rep);
	
	public ArrayList<Crd> getAllCrd();

}
