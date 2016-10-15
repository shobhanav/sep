package order_management;

import java.util.ArrayList;

public interface RepManagementInterface {
	
	public Rep createRep( String uname, String clientName);
	
	public boolean addRep(Rep rep);
	
	public ArrayList<Rep> listRep(String uname);
	
	public Rep getRep(int id);
	
	public ArrayList<Rep> getRep(RepState state);
	
	public void deleteRep(int id);

}
