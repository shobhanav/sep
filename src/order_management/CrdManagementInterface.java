package order_management;

import java.util.ArrayList;

public interface CrdManagementInterface {
	
	public Crd createCrd( Rep rep);
	
	public Crd getCrd(Rep rep);
	
	public Crd getCrd(int id);
	
	public ArrayList<Crd> getAllCrd();
	
	public void deleteCrd (int id);
	
	public ArrayList<Crd> getCrdHistory();

}
